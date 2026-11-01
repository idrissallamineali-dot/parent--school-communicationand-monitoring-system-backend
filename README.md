# Parent-School Communication and Monitoring System (Backend)

### Student Information
- **Student Name:** Idriss Allamine Ali
- **Student ID:** 27836
- **Course:** INSY 8322 - Web Technology and Internet
- **Institution:** Adventist University of Central Africa (AUCA)
- **Lecturer:** Patrick DUSHIMIMANA

---

## Project Overview
A RESTful backend service built with **Spring Boot 3**, **Spring Data JPA**, and **PostgreSQL** to manage parent-school communication, meeting schedules, academic notes, teacher assignments, and classroom allocations.

---

## Technologies Used
- **Language:** Java 17
- **Framework:** Spring Boot 3.3.5
- **Persistence:** Spring Data JPA / Hibernate
- **Database:** PostgreSQL
- **Build Tool:** Maven

---

## Prerequisites
Before running the application, make sure you have installed:
1. **Java Development Kit (JDK 17 or higher)**
2. **PostgreSQL** (running locally on port `5432`)
3. **Maven** (or use the included Maven wrapper `./mvnw`)
4. **Postman** (to import and test API endpoints)

---

## Database Setup
1. Open pgAdmin or PostgreSQL CLI (`psql -U postgres`).
2. Create the project database:
   ```sql
   CREATE DATABASE "ParentSchoolMonitoringSystem";
   ```
3. Check `src/main/resources/application.properties` to ensure database credentials match your local setup:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/ParentSchoolMonitoringSystem
   spring.datasource.username=postgres
   spring.datasource.password=Games243
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

---

## Steps to Run the API

1. **Clone the repository:**
   ```bash
   git clone https://github.com/idrissallamineali-dot/parent--school-communicationand-monitoring-system-backend.git
   cd parent--school-communicationand-monitoring-system-backend
   ```

2. **Build and package the project:**
   ```bash
   mvn clean compile
   ```

3. **Run the Spring Boot application:**
   - Using Maven Wrapper:
     ```bash
     ./mvnw spring-boot:run
     ```
   - Or using standard Maven:
     ```bash
     mvn spring-boot:run
     ```

4. The server will start and be available at:
   ```
   http://localhost:8080
   ```

---

## API Testing with Postman
A complete Postman collection is included in the root directory:
- **File:** `parent--school-communicationand-monitoring-system-midterm.postman_collection.json`

### How to use:
1. Open **Postman**.
2. Click **Import** and select `parent--school-communicationand-monitoring-system-midterm.postman_collection.json`.
3. The collection is organized by assignment requirement IDs:
   - **Part A2:** CRUD operations for Student, Teacher, Classroom, Subject, ParentMeeting, MeetingNote.
   - **Part B (Derived Queries):** `B1`, `B2`, `B3`.
   - **Part C (JPQL Queries):** `C1`, `C2`, `C3`, `C4`.
   - **Part D (Business Rules):** `D1`, `D2`, `D3`, `D4`.
   - **Part E (Meeting Notes):** `E1`, `E2`, `E3`.
   - **Part F (Reports & Bulk):** `F1`, `F2`.
   - **Bonus Features:** Dashboard Summary (`GET /api/dashboard/summary`) and Pagination (`GET /api/parent-meetings/page`).
