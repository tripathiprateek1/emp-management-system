# Employee–Project Management System

A backend application built using **Spring Boot** for managing employees, projects, and project assignments within an organization.
The system supports role-based management, project allocation, validation rules, and secure backend architecture.

## 🚀 Features
### 👨‍💼 Employee Management

- Create and manage employees
- Role-based employee system:
  - ADMIN
  - MANAGER
  - EMPLOYEE
- Email uniqueness validation
- Password encryption using BCrypt

## 📁 Project Management
  
 - Create and manage projects
 - Assign manager to project
 - Prevent duplicate project names
 - Project status handling:
   - PLANNED
   - IN_PROGRESS
   - COMPLETED

## 🔗 Project Assignment
- Assign employees to projects
- Prevent duplicate assignments
- Assignment date validation
- Manager validation before project creation

## 🔐 Security
- Spring Security integration
- JWT Authentication support
- Stateless session management
- Role-based authorization

## ⚠️ Exception Handling
- Global exception handling using @RestControllerAdvice:
- Resource not found
- Duplicate resources
- Validation failures
- Generic exception handling

## 🛠️ Tech Stack
| Technology      | Usage                          |
| --------------- | ------------------------------ |
| Java 21         | Backend Language               |
| Spring Boot     | Backend Framework              |
| Spring Security | Authentication & Authorization |
| Spring Data JPA | Database Operations            |
| Hibernate       | ORM                            |
| MySQL           | Relational Database            |
| JWT             | Authentication                 |
| ModelMapper     | DTO Mapping                    |
| Maven           | Build Tool                     |
| Lombok          | Boilerplate Reduction          |

## 📂 Project Structure
src/main/java/com/prateek/emp_management_system
│
├── controller
├── service
├── repository
├── entity
├── dto
├── exception
├── security
├── config
└── enums

## 🗄️ Database Design
### Employee
- id
- name
- email
- password
- designation
- dateOfJoining
- role
### Project
- id
- projectName
- description
- startDate
- endDate
- projectStatus
- manager_id
### ProjectAssignment
- id
- employee_id
- project_id
- assignedDate
  
