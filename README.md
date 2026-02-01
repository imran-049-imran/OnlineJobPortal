💼 Online Job Portal – Backend API

Spring Boot | JWT | MySQL

A secure, scalable, and production-ready Online Job Portal Backend built using Spring Boot, Spring Security (JWT), and MySQL.
This backend exposes RESTful APIs for User Management, Job Posting, Job Applications, and Admin Operations, following clean architecture and industry best practices.

🚀 Tech Stack

Java 17

Spring Boot 3.x

Spring Security + JWT

Spring Data JPA (Hibernate)

MySQL

Maven

Swagger / OpenAPI

Lombok

📁 Project Structure (Layered Architecture)
```
backend/
├── src/main/java/com/jobportal
│   ├── controller     # REST Controllers
│   ├── service        # Business Logic
│   ├── repository     # JPA Repositories
│   ├── model          # Entity Classes
│   ├── dto            # Data Transfer Objects
│   ├── security       # JWT & Spring Security Config
│   ├── exception      # Global Exception Handling
│   └── JobPortalApplication.java
│
├── src/main/resources
│   ├── application.properties
│   └── data.sql
│
├── pom.xml
└── README.md
```

✨ Features
🔐 Authentication & Authorization

User Registration & Login

JWT-based authentication

Role-based access control:

ADMIN

USER

Secure APIs using Spring Security

Password encryption using BCrypt

💼 Job Management

Create, Update, Delete jobs (Admin only)

View all available jobs (Public / User)

Filter jobs by:

Job Title

Location

Company

📝 Job Applications

Users can apply for jobs

View applied jobs (User-specific)

Admin can view all job applications

🛡️ Security Highlights

Stateless authentication using JWT

Protected endpoints with role-based access

Centralized exception handling

Clean separation of concerns

📘 API Documentation

Swagger UI integrated

All APIs documented and testable from browser

⚙️ Configuration
🔧 application.properties
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/job_portal_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=your_jwt_secret_key
jwt.expiration=86400000

🗄️ Database Design

Database Name: job_portal_db

Tables

users

roles

jobs

applications

Relationships

User ↔ Applications (One-to-Many)

Job ↔ Applications (One-to-Many)

User ↔ Roles (Many-to-Many)

▶️ How to Run the Project
1️⃣ Clone the Repository
git clone https://github.com/imran-049-imran/OnlineJobPortal.git
cd OnlineJobPortal/backend

2️⃣ Build the Project
mvn clean install

3️⃣ Run the Application
mvn spring-boot:run

📌 Sample API Endpoints
🔑 Authentication
POST /api/auth/register
POST /api/auth/login

💼 Jobs
GET    /api/jobs
POST   /api/admin/jobs
DELETE /api/admin/jobs/{id}

📝 Applications
POST /api/applications/apply
GET  /api/admin/applications

📘 Swagger UI

After running the application, open:

http://localhost:8080/swagger-ui.html


Use Swagger to test all secured and public APIs.

🎯 Future Enhancements

Resume upload (PDF/DOC)

Email notifications (Application status)

Admin analytics dashboard

Pagination & sorting

Cloud deployment (AWS / Docker)

Search optimization

👨‍💻 Author

Imran
Java | Spring Boot | Full Stack Developer

🔗 GitHub: https://github.com/imran-049-imran
