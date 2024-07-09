# Galvix Backend Developer Assignment
This project is a Spring Boot application designed to manage and display logs from a PostgreSQL database. The application provides an API endpoint to filter and paginate log entries based on various criteria.

### Requirements
- Java 21
- Maven
- PostgreSQL

### Dependencies Used
- Spring Boot 3.3.1
- Spring Data JPA
- Spring Web
- PostgreSQL
- Lombok
- Springdoc OpenAPI

### Lombok Plugin
Install the Lombok plugin in IntelliJ IDEA:
1. Go to File -> Settings (or IntelliJ IDEA -> Preferences on macOS).
2. Select Plugins from the sidebar.
3. Search for Lombok and click Install.
4. Restart IntelliJ IDEA.

### Springdoc OpenAPI
Springdoc OpenAPI is used to generate interactive API documentation. For more details and configurations, visit the [Springdoc OpenAPI documentation](https://springdoc.org/) and refer to the "Getting Started" section.

## Database Setup

### 1. Setting Up the Database
   #### PostgreSQL
If you're using PostgreSQL, you can create the `logs` database by running the following SQL command:

```sql
CREATE DATABASE logs;
```
### 2. Creating the Logs Table
To set up the `logs` table in your database, run the following SQL command:

```sql
CREATE TABLE logs (
    id SERIAL PRIMARY KEY,
    service_name VARCHAR(50) NOT NULL,
    status_code INTEGER NOT NULL,
    logged_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);
```

### 3. Populating the Logs Table
To insert sample data into the `logs` table, use the following SQL command:

```sql
INSERT INTO logs (service_name, status_code) VALUES 
('Invoice', 200), 
('User', 205),
('Client', 204),
('Invoice', 208), 
('Client', 210),
('User', 215);
```
4. ### Verify the data
To fetch all records from the `logs` table, execute the following SQL query:

```sql
SELECT * FROM logs;
```
## Project Setup
### 1. Clone the repository

```bash
   git clone <repository-url>
   cd <repository-directory>
```
### 2. Configure the application properties
Edit the `src/main/resources/application.properties` file to match your PostgreSQL database configuration:

```properties
spring.application.name=assignment

spring.datasource.url=jdbc:postgresql://localhost:5432/database_name
spring.datasource.username= your_username
spring.datasource.password= your_password
```
### 3. Run the application

## API Documentation
The API provides an endpoint to filter and paginate logs. Swagger UI is integrated to facilitate API exploration.

- ` Swagger UI: http://localhost:8080/swagger-ui.html`

## API Endpoints
` GET /logs`

Fetch logs with optional filters and pagination.

### Query Parameters:
- `serviceNames` (optional): Comma-separated list of service names to filter by.
- `statusCode` (optional): Status code to filter by.
- `startDate` (optional): Start date to filter by (format: `YYYY-MM-DD`).
- `endDate` (optional): End date to filter by (format: `YYYY-MM-DD`).
- `page` (optional): Page number for pagination (default is 0).
- `size` (optional): Number of records per page (default is 10).

### Example Requests: 
/logs?serviceNames[is]=Invoice
/logs?serviceNames[isAnyOf]=Invoice,User
/logs?startDate[onOrAfter]=2024-01-01
/logs?startDate[onOrAfter]=2024-01-01&endDate[onOrBefore]=2024-03-30

## Author
Developed by S Nandita, currently working at Mountblue Technologies.
