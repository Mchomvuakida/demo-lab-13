# Secure Java Spring Boot Application
This project is a secure Java Spring Boot application with authentication, database migration, and security best practices.It follows the MVC pattern and includes user authentication, role-based access control, and data validation.

## Getting Started
### 🚀 Features
- User Authentication: Registration and login with BCrypt password hashing.
- Role-Based Access Control: Users can only access their own data.
- Database Migration: Flyway is used to manage database changes.
- Secure API: All endpoints (except authentication) require authentication.
- Input Validation: Prevents SQL Injection and other security vulnerabilities.
- Logging (optional): Tracks security-relevant events.

### Prerequisites
- Java 21
- Gradle (for building the project)
- Git (for version control)

### Running the Project

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd <project-directory>

2. Build the project
   ./gradlew build

3. Run the Project
   ./gradlew bootRun

### Database Configuration
- The project uses SQLite by default. Ensure that the database file is located as configured in application.properties:
  spring.datasource.url=jdbc:sqlite:./database/app.db
  spring.datasource.driver-class-name=org.sqlite.JDBC
  spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect

### Folder Structure
src/main/java: Application source code
src/main/resources: Configuration files and static assets
src/test: Unit and integration tests

### Key Dependencies
Spring Boot Starter Web: For building RESTful APIs.
Spring Boot Starter Data JPA: For database interactions with Hibernate.
SQLite JDBC Driver: Lightweight database solution.
Flyway: For database migrations.
Lombok: Reduces boilerplate code.
spring-boot-starter-validation

### 📌 API Endpoints
🛠 Authentication
-POST /auth/register - Register a new user
-POST /auth/login - Login and get a JWT token

✅ Task Management
- GET /tasks - Retrieve all tasks for the authenticated user
- POST /tasks - Create a new task
- PUT /tasks/{id} - Update a task (only if it belongs to the user)
- DELETE /tasks/{id} - Delete a task (only if it belongs to the user)

### 📌 Security Implementations
- JWT Authentication: Secures endpoints and provides user identity.
- Spring Security Configuration: Restricts unauthorized access.
- BCrypt Password Hashing: Ensures passwords are not stored in plaintext.
- Session Management: Stateless authentication using JWT.
- Error Handling: Global exception handler to return user-friendly errors

### Contributing
- Fork the repository.
- Create a new branch (git checkout -b feature/your-feature).
- Commit your changes (git commit -m "Add a new feature").
- Push to the branch (git push origin feature/your-feature).
- Open a pull request.

### License
This project is licensed under the MIT License.