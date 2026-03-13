# Streakify – Habit Tracking Application

### Overview

Streakify is a backend-driven habit tracking application designed to help users build consistent habits and improve productivity by maintaining daily streaks. The system allows users to create habits, log their daily progress, and track their current and longest streaks.

The application provides a structured backend using **Spring Boot** and **PostgreSQL**, enabling efficient habit tracking and analytics through RESTful APIs. The project follows a **layered architecture** to ensure clean code organization, scalability, and maintainability.

Streakify also analyzes user progress by calculating productivity metrics such as **current streak**, **longest streak**, and **weekly consistency**, providing users with insights into their habit-building performance.

---

# Tech Stack

* Java
* Spring Boot
* PostgreSQL
* JPA / Hibernate
* Maven
* Postman

---

# Setup Steps

### 1. Clone the repository

```
git clone https://github.com/PRAVEENVP2255/Streakify.git
cd STREAKIFY
```


### 2. Create PostgreSQL Database

Create a database named:

```
streakify_db
```


### 3. Configure Database

Update **application.properties**

```
spring.datasource.url=jdbc:postgresql://localhost:5432/streakify
spring.datasource.username=postgres
spring.datasource.password=112299

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```


### 4. Run the Application

```
mvn spring-boot:run
```

Server runs on:

```
http://localhost:8080
```


# Database Schema

### users

| Column      | Type                  |
|-------------|-----------------------|
| id          | BIGINT (Primary Key)  |
| name        | VARCHAR               |
| email       | VARCHAR (Unique)      |
| created_at  | TIMESTAMP             |

---

### habits

| Column                 | Type                              |
|------------------------|-----------------------------------|
| id                     | BIGINT (Primary Key)              |
| user_id                | BIGINT (Foreign Key → users.id)   |
| name                   | VARCHAR                           |
| target_days_per_week   | INTEGER                           |
| created_at             | TIMESTAMP                         |

---

### habit_logs

| Column      | Type                               |
|-------------|------------------------------------|
| id          | BIGINT (Primary Key)               |
| habit_id    | BIGINT (Foreign Key → habits.id)   |
| log_date    | DATE                               |
| completed   | BOOLEAN                            |

---


## API Endpoints

### User APIs

* POST /users
* GET /users/{id}
* DELETE /users/{id}

---

### Habit APIs

* POST /habits
* GET /users/{userId}/habits
* DELETE /habits/{id}

---

### Habit Log APIs

* POST /habits/{habitId}/logs
* PUT /habits/{habitId}/logs/{date}
* GET /habits/{habitId}/logs

---

### Streak API

* GET /habits/{habitId}/streak

---

### Dashboard API

* GET /users/{userId}/dashboard

---


# Screenshots

### Create User

<img width="1383" height="738" alt="Screenshot 2026-03-11 171142" src="https://github.com/user-attachments/assets/461b57a9-0592-49aa-bc8b-72719a2e1505" />

### Get User

<img width="1366" height="764" alt="Screenshot 2026-03-11 171617" src="https://github.com/user-attachments/assets/2f62fc55-f182-4df1-ab68-a3101a25a0c6" />

### Delete User

<img width="1382" height="666" alt="Screenshot 2026-03-11 171909" src="https://github.com/user-attachments/assets/7b7389cc-9158-4b55-9883-8d80091d269d" />

### Create Habit

<img width="1384" height="722" alt="Screenshot 2026-03-11 172701" src="https://github.com/user-attachments/assets/08474ef2-86ab-404f-8773-50f3ee08d139" />

### Get User Habits

<img width="1389" height="821" alt="Screenshot 2026-03-11 173703" src="https://github.com/user-attachments/assets/eeca4f60-7f39-4f7c-a296-07d52066b478" />

### Delete Habit

<img width="1379" height="482" alt="Screenshot 2026-03-11 174750" src="https://github.com/user-attachments/assets/86add98f-88e5-411a-85f8-c6d1ce764fd3" />

### Log Habit

<img width="1371" height="645" alt="Screenshot 2026-03-13 161434" src="https://github.com/user-attachments/assets/1f79f645-dc44-4087-aa61-968f30aad9c0" />

### Update Log

<img width="1376" height="727" alt="Screenshot 2026-03-12 110157" src="https://github.com/user-attachments/assets/f4017c2c-f595-443b-aeab-5b8865f2b61d" />

### View Habit Logs

<img width="1376" height="812" alt="Screenshot 2026-03-12 121733" src="https://github.com/user-attachments/assets/c7b66930-fff9-4162-9f6a-830fc7571add" />

### Fetch Streak

<img width="1384" height="464" alt="Screenshot 2026-03-12 122604" src="https://github.com/user-attachments/assets/1a9628ad-1932-491d-ad50-e2b7afaaa136" />

### Dashboard

<img width="1387" height="566" alt="Screenshot 2026-03-13 162443" src="https://github.com/user-attachments/assets/8cce0ffe-afaa-48e5-ac7c-2fb522ad5953" />



### Negative Cases

### Duplicate Email

<img width="1386" height="731" alt="Screenshot 2026-03-11 171338" src="https://github.com/user-attachments/assets/e8fb22c0-160a-46e1-82de-301c370d6bde" />

### Invalid Email

<img width="1369" height="723" alt="Screenshot 2026-03-11 171455" src="https://github.com/user-attachments/assets/d4ed0c8c-f68b-42c2-a774-6b80470c952c" />

### User Not Found

<img width="1379" height="714" alt="Screenshot 2026-03-11 171706" src="https://github.com/user-attachments/assets/870a09b6-9808-4a5d-9b7b-aabeeaa85873" />

### Delete non-existing habit

<img width="1373" height="578" alt="Screenshot 2026-03-12 102228" src="https://github.com/user-attachments/assets/e9c41a3a-5901-402f-94fa-386bee373ef8" />

### Future Date Not Allowed

<img width="1377" height="657" alt="Screenshot 2026-03-12 104219" src="https://github.com/user-attachments/assets/d7881caf-e53d-4989-80ac-fcb29723e7d4" />

### Duplicate Log

<img width="1380" height="647" alt="Screenshot 2026-03-12 104436" src="https://github.com/user-attachments/assets/1b8eb89e-1dcf-4557-a973-20411a4c7442" />


















