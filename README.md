# Task Management System
This README provides instructions on how to set up and run the Task Management System application, including the PostgreSQL setup, and outlines any assumptions or considerations made during development.

## Prerequisites
- ***Java 17+*** : Java Runtime Environment.
- ***Spring Boot*** : Web framework for building the application.
- ***PostgreSQL*** : Relational Database Management System.
- ***Maven*** : Build automation tool (to manage dependencies and run the project).
- ***IDE*** (e.g., IntelliJ IDEA, Eclipse, etc.) : For development and running the application.
- ***Postman*** : To test API endpoints.
## Setup Instruction
- Download and Install PostgreSQL
- ***Create Database*** :Create a new database for the application, e.g., task_directory.
- **Create Table tasks**:
  CREATE TABLE Task (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(50) CHECK (status IN ('Pending', 'In Progress', 'Completed')),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP

);
- **Create Table users** :
  CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    time_zone VARCHAR(100) NOT NULL,
    is_active BOOLEAN NOT NULL);
ALTER TABLE task ADD COLUMN user_id BIGINT NOT NULL;
ALTER TABLE task ADD CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id);

- **Configure Database Connection in application.properties** :
  ![Screenshot 2024-12-03 040311](https://github.com/user-attachments/assets/88465c2b-4e63-40e9-a5cb-c206db01b6cd)

## Assumption and Considerations
### Assumptions Made
- ***Task Ownership***: Each task must have an assigned user. The assignedTo field in the Task entity is a foreign key that references the User entity.
- ***TimeZone Handling***: The system uses UTC for the createdAt and updatedAt fields in tasks, and the user's timezone is recorded as part of the user entity.
- ***Validation***: Mandatory fields such as title and assignedTo are validated before creating or updating tasks.
### Database Schema Consideration
- ***task table*** : task table includes fields like id, title, description, status, createdAt, updatedAt, and assignedTo (foreign key referencing user).
- ***user table*** : user table includes fields like id, firstName, lastName, timezone, and isActive
## API Endpoints
- GET /api/tasks – Retrieve all tasks.

- POST /api/tasks – Create a new task.

- GET /api/tasks/{id} – Retrieve a task by ID.

- PUT /api/tasks/{id} – Update an existing task by ID.

- DELETE /api/tasks/{id} – Delete a task by ID.

- GET /api/users – Retrieve all users.

- POST /api/users – Create a new user.

- GET /api/users/{id} – Retrieve a user by ID.

- PUT /api/users/{id} – Update an existing user by ID.

- DELETE /api/users/{id} – Delete a user by ID.



