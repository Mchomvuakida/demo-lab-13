package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Fetch tasks for the logged-in user
    @GetMapping
    public List<Task> getUserTasks(@AuthenticationPrincipal UserDetails userDetails) {
        Long userId = Long.valueOf(userDetails.getUsername()); // Get the user ID from UserDetails
        return taskService.getTasksByUserId(userId); // Fetch tasks for the specific user
    }

    // Assign a task to the logged-in user
    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = Long.valueOf(userDetails.getUsername()); // Get the user ID from UserDetails
        Task createdTask = taskService.createTask(task, userId); // Assign the task to the user
        return ResponseEntity.ok(createdTask);
    }

    // Delete a task by ID (only if the task belongs to the logged-in user)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = Long.valueOf(userDetails.getUsername()); // Get the user ID from UserDetails
        taskService.deleteTask(id, userId); // Delete the task if it belongs to the user
        return ResponseEntity.noContent().build();
    }
}