<b>REST API Using Micronaut</b> <br><br>
This repository demonstrates a REST API built with the Micronaut Framework, designed to showcase best practices in API development, authentication, testing, and deployment.
The project includes features such as basic authentication, in-memory database operations, environment-specific configurations, OpenAPI integration, and more.

Features:
1. RESTful Endpoints<br>
GET: Fetch employee details from the in-memory H2 database.<br>
POST: Save new employee details to the database.<br>
DELETE: Remove an employee's details by their ID.<br><br>
2. Basic Authentication:<br>
APIs are secured using Basic Authentication.<br>
Valid credentials are required to access the endpoints.<br><br>
3. In-Memory Database (H2)<br>
Uses an H2 database to store and retrieve employee details.<br>
Schema initialization is automatic at application startup.<br><br>
4. Environment-Specific Configurations:<br>
Separate property files are maintained for:<br>
Development (dev)<br>
Performance (perf)<br>
Production (prod)<br>
The active environment can be selected dynamically.<br><br>
5. OpenAPI Documentation<br>
Includes Swagger UI for interactive API testing and documentation.<br><br>
6. Logging with AOP<br>
Implements Aspect-Oriented Programming (AOP) for method-level logging using a custom interceptor.<br><br>
7. Exception Handling<br>
Global exception handler ensures clean and meaningful error responses.<br><br>
8. Unit Testing<br>
Comprehensive JUnit test cases are provided, with instructions to check code coverage.<br><br><br>
Tech Stack-<br>
Language: Java 17<br>
Framework: Micronaut<br>
Build Tool: Maven<br>
Database: H2 (In-Memory)<br>
Authentication: Basic Authentication<br>
Documentation: OpenAPI (Swagger)<br>
Testing: JUnit<br><br>
Getting Started-<br>
Prerequisites:<br>
Java 17 or higher<br>
Maven 3.6+<br><br>
<b>Contact</b>
Abhishek Kumar: abhishekkumar25802@gmail.com<br><br>
Project Link: 
