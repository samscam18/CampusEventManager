# Campus Event Reporting System

## Project Overview
The project is a **Campus Event Reporting System** developed with **Spring Boot** as the backend and **PostgreSQL** as the database.  
It enables colleges to handle events, student registration, attendance, and feedback. The system also provides reports on the popularity of events and top students based on their attendance and participation.
## Features
- Create, view, and filter events based on college and event type.
- Manage colleges and students.  
- Register students for events.  
- Record attendance and collect feedback for events.  
- Generate reports:  
  - Event attendance and feedback summary  
  - Popular events in a college  
  - Top performing students  
## Project Structure
- **src/main/java/** → Contains all Java backend code:
  - **model/** → Java classes such as `Event`, `College`, `Student`  
  - **repository/** → JPA repositories to perform database operations  
  - **controller/** → REST controllers for API endpoints  
  - **service/** → Business logic and reports  

- **src/main/resources/** → Configuration files such as `application.properties`  

- **ai_logs/** → Screenshots of AI conversations utilized during development  

- **reports/** → Postman results, ER diagrams, and flowcharts  

- **sql/** (optional) → Example scripts for table creation or data insertion  
## How to Run the Project

Follow these steps to set up and run the Campus Event Reporting System:

### 1. Clone the Repository
First, clone the project to your local system:


git clone https://github.com/your-username/campus-event-reporting-system.git
cd campus-event-reporting-system

### 2. Install Prerequisites


Make sure you have the following installed:

Java 17+

Maven 3.8+

PostgreSQL 14+

You can verify installations with:
java -version
mvn -v
psql --version

3. Set Up the Database
Open PostgreSQL and create a database:
CREATE DATABASE eventdb;

4. Configure Application Properties
Update src/main/resources/application.properties with your PostgreSQL credentials:

spring.datasource.url=jdbc:postgresql://localhost:5432/eventdb
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

5. Build the Project

Run Maven to download dependencies and build the project:


mvn clean install

6. Run the Application
Start the Spring Boot server:


mvn spring-boot:run

If successful, the server will start on:
http://localhost:8080

7. Test the APIs
Use Postman or any REST client to test the APIs.
For example, to list all events:
GET http://localhost:8080/api/events
## API Usage Examples

Below are some example API calls you can use to test the system.  
Base URL: `http://localhost:8080`

---

### 1. Create Event
**Endpoint:**

POST /api/events
Content-Type: application/json

Request Body:
{
  "collegeId": "11111111-1111-1111-1111-111111111111",
  "title": "Science Seminar",
  "type": "SEMINAR",
  "startTime": "2025-09-06T12:00:00Z",
  "endTime": "2025-09-06T14:00:00Z",
  "location": "Auditorium",
  "description": "A seminar on science."
}

2. Get All Events

GET /api/events

3. Filter Events by College and Type
GET /api/events/filter?collegeId=11111111-1111-1111-1111-111111111111&type=SEMINAR

4. Register Student for Event
POST /api/registrations
Content-Type: application/json

{
  "eventId": "22222222-2222-2222-2222-222222222222",
  "studentId": "33333333-3333-3333-3333-333333333333"
}

5. Mark Attendance

POST /api/attendance
Content-Type: application/json

{
  "eventId": "22222222-2222-2222-2222-222222222222",
  "studentId": "33333333-3333-3333-3333-333333333333",
  "status": "PRESENT"
}

6. Submit Feedback

Endpoint:
POST /api/feedback
Content-Type: application/json

{
  "eventId": "22222222-2222-2222-2222-222222222222",
  "studentId": "33333333-3333-3333-3333-333333333333",
  "rating": 4,
  "comments": "Very informative session!"
}




## Reports

The system provides several reporting features to analyze event performance and student participation.

---

### 1. Event Report

GET /api/reports/events/{eventId}

Description:
Returns details about a specific event, including:

Total registrations

Attendance percentage

Number of feedback entries

Average rating

2. Popular Events

Endpoint:

GET /api/reports/popular?collegeId=11111111-1111-1111-1111-111111111111

## My Understanding

Working on this project gave me a solid understanding of how to design and implement a complete backend system.  

At the start, I focused on defining the entities (College, Student, Event, etc.) and their relationships using **JPA annotations**. This helped me see how real-world concepts like registrations and attendance can be translated into database models.  

I then learned how to write **REST controllers** to expose APIs and connect them with the **service layer**, where the actual business logic lives. For example, handling event registration or generating reports required combining data from multiple tables and applying logic to it.  

Integrating with **PostgreSQL** was another key learning. I configured Spring Boot to automatically create and update tables, wrote queries through repositories, and verified everything in the database.  

One of the most interesting parts was generating **reports**. Instead of just storing data, I learned how to extract insights from it — such as finding the most popular events or identifying the top students. This made me realize how backend systems can directly support decision-making.  

Overall, this project strengthened my skills in:
- **Backend development** with Spring Boot  
- **Database design and management** using PostgreSQL  
- **REST API development** and testing with Postman  
- Writing clean, modular code by separating models, repositories, services, and controllers  

This project not only gave me technical practice but also helped me think more about **how features should work in real scenarios**, which is an important skill for any developer.
## Screenshots 

For better understanding and verification, I have included supporting files in the project:

- **ai_logs/** → Screenshots of AI-assisted conversations that guided parts of the development process.  
- **reports/** → Contains supporting documents such as:  
  - **Postman results** – sample API request/response screenshots  
  - **ER diagram** – showing the relationships between entities like Event, Student, and College  
  - **Flowchart** – visual representation of how data flows through the system  

These resources are helpful for quickly reviewing the system’s design and verifying that the APIs work as intended.

## Tips for Running and Testing Quickly

## Tips for Running and Testing Quickly

- Make sure **PostgreSQL service is running** before starting the Spring Boot application.  
- Double-check your **application.properties** for correct username, password, and database name.  
- Always pre-populate at least **one college record** before creating events, since every event needs to be linked to a college.  
- Use **Postman** (or any REST client) to test APIs step by step:  
  1. Create a college  
  2. Create an event linked to that college  
  3. Register students for the event  
  4. Mark attendance and submit feedback  
  5. Generate reports to see participation and popularity  
- If tables don’t appear in PostgreSQL, set:  
  
- properties
  spring.jpa.hibernate.ddl-auto=update

  to allow automatic schema creation.

Use UUIDs consistently when making API requests — copying from database entries or sample values helps avoid errors.

After changes, you can restart the application using:
mvn spring-boot:run

Check logs in the console — Spring Boot usually gives clear error messages if something is misconfigured.