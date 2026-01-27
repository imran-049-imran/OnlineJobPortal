# Online Job Portal – Backend (Spring Boot)

A secure and scalable **Online Job Portal Backend** built using **Spring Boot**, **JWT Authentication**, and **MySQL**.  
This backend provides REST APIs for **Users, Jobs, Applications, and Admin Management**.

## Tech Stack

- **Java** 17
- **Spring Boot** 3.x
- **Spring Security + JWT**
- **Spring Data JPA (Hibernate)**
- **MySQL**
- **Maven**
- **Swagger / OpenAPI**
- **Lombok**

---

##  Project Structure

```
backend/
├── src/main/java/com/jobportal
│ ├── controller # REST Controllers
│ ├── service # Business Logic
│ ├── repository # JPA Repositories
│ ├── model # Entities
│ ├── dto # DTOs
│ ├── security # JWT & Security Config
│ ├── exception # Global Exception Handling
│ └── JobPortalApplication.java
│
├── src/main/resources
│ ├── application.properties
│ └── data.sql
│
├── pom.xml
└── README.md
```

---

##  Features

###  Authentication & Authorization
- User Registration & Login
- JWT-based Authentication
- Role-based Access Control (`ADMIN`, `USER`)

### Job Management
- Create, Update, Delete Jobs (Admin)
- View Jobs (Public/User)
- Filter jobs by title, location, company

### Job Applications
- Apply for jobs (User)
- View applied jobs
- Admin can view all applications

### Security
- Spring Security with JWT
- Protected APIs using roles
- Password encryption using BCrypt

### 📘 API Documentation
- Swagger UI integrated for API testing

---

## ⚙️ Configuration

### 🔧 application.properties

```
properties
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/job_portal_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=your_jwt_secret_key
jwt.expiration=86400000
```
#  Database

MySQL Database Name: job_portal_db

Tables:

users

roles

jobs

applications

▶️ Run the Project
1️⃣ Clone Repository
git clone https://github.com/imran-049-imran/OnlineJobPortal.git
cd OnlineJobPortal/backend

2️⃣ Build Project
mvn clean install

3️⃣ Run Application
mvn spring-boot:run

📌 API Endpoints (Sample)
🔑 Auth

POST /api/auth/register

POST /api/auth/login

💼 Jobs

GET /api/jobs

POST /api/admin/jobs

DELETE /api/admin/jobs/{id}

📝 Applications

POST /api/applications/apply

GET /api/admin/applications

📘 Swagger UI

After running the project, open:

http://localhost:8080/swagger-ui.html

🎯 Future Enhancements

Resume Upload

Email Notifications

Admin Analytics Dashboard

Pagination & Sorting

Cloud Deployment (AWS)

👨‍💻 Author

Imran
Java | Spring Boot | Full Stack Developer

🔗 GitHub: https://github.com/imran-049-imran

⭐ Support

If you like this project, please ⭐ the repository!
