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

<img width="1383" height="738" alt="Screenshot 2026-03-11 171142" src="https://github.com/user-attachments/assets/1f086dbd-e99e-49b7-94cd-11cf6e700925" />


### Get User

<img width="1366" height="764" alt="Screenshot 2026-03-11 171617" src="https://github.com/user-attachments/assets/1ed6d3dd-925c-4d79-a4d2-cf199a2cc99f" />


### Delete User

<img width="1382" height="666" alt="Screenshot 2026-03-11 171909" src="https://github.com/user-attachments/assets/c1f3e86f-199e-46c7-9f28-79a8ff182061" />


### Create Habit

<img width="1384" height="722" alt="Screenshot 2026-03-11 172701" src="https://github.com/user-attachments/assets/c0f05714-bf43-4c01-8658-34c8e387e253" />


### Get User Habits

<img width="1389" height="821" alt="Screenshot 2026-03-11 173703" src="https://github.com/user-attachments/assets/74016a99-7bad-4d49-9f48-ac11695ee14a" />


### Delete Habit

<img width="1379" height="482" alt="Screenshot 2026-03-11 174750" src="https://github.com/user-attachments/assets/4dfae5be-850f-4b02-9064-b6feb58d3bf7" />


### Log Habit

<img width="1371" height="645" alt="Screenshot 2026-03-13 161434" src="https://github.com/user-attachments/assets/29896ae1-2001-47aa-9d2b-545f1aa2dfaa" />


### Update Log

<img width="1376" height="727" alt="Screenshot 2026-03-12 110157" src="https://github.com/user-attachments/assets/23faa8a4-3e01-4685-9e4f-8e5a50fcd740" />


### View Habit Logs

<img width="1376" height="812" alt="Screenshot 2026-03-12 121733" src="https://github.com/user-attachments/assets/2ee63ddb-bf54-413c-9b67-ec94a6329777" />


### Fetch Streak

<img width="1384" height="464" alt="Screenshot 2026-03-12 122604" src="https://github.com/user-attachments/assets/10d11882-7041-411b-ae70-bae4501f6e27" />


### Dashboard

<img width="1387" height="566" alt="Screenshot 2026-03-13 162443" src="https://github.com/user-attachments/assets/d1d7c03f-e5b9-4aa8-866f-5ffc8c35ad68" />




### Negative Cases

### Duplicate Email

<img width="1386" height="731" alt="Screenshot 2026-03-11 171338" src="https://github.com/user-attachments/assets/691e9f20-1337-496d-9fe4-d91517380edc" />


### Invalid Email

<img width="1369" height="723" alt="Screenshot 2026-03-11 171455" src="https://github.com/user-attachments/assets/a57767cb-45d6-45a9-8cb5-de43c8519e09" />


### User Not Found

<img width="1379" height="714" alt="Screenshot 2026-03-11 171706" src="https://github.com/user-attachments/assets/9874a8d5-cc38-47d3-b25d-ae13fbe4b72d" />


### Delete non-existing habit

<img width="1373" height="578" alt="Screenshot 2026-03-12 102228" src="https://github.com/user-attachments/assets/454b44f8-e935-4ef5-ba65-8dfae0936597" />


### Future Date Not Allowed

<img width="1377" height="657" alt="Screenshot 2026-03-12 104219" src="https://github.com/user-attachments/assets/0ee1f073-e2a5-4b60-bbba-c104f2aa434e" />


### Duplicate Log

<img width="1380" height="647" alt="Screenshot 2026-03-12 104436" src="https://github.com/user-attachments/assets/5ced6951-2ecf-4ca7-8462-697e77038d4e" />



















