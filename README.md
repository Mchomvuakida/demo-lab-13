# Spring Boot Project with SQLite

This project is a Spring Boot application using SQLite as the database, Hibernate as the ORM, and Flyway for database migrations.

## Getting Started

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

### Contributing
Fork the repository.
Create a new branch (git checkout -b feature/your-feature).
Commit your changes (git commit -m "Add a new feature").
Push to the branch (git push origin feature/your-feature).
Open a pull request.

### License
This project is licensed under the MIT License.
