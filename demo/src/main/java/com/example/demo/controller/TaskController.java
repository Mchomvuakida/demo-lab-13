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
    @GetMapping
    public List<Task> getUserTasks(@AuthenticationPrincipal UserDetails userDetails) {
        return taskService.getUserTasks(Long.valueOf(userDetails.getUsername()));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = Long.valueOf(userDetails.getUsername());
        Task createdTask = taskService.createTask(task, userId);
        return ResponseEntity.ok(createdTask);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
