# CMP Student - Reactive Web Service

A Spring Boot reactive web application built with WebFlux that provides a REST API to fetch student data from an external service.

## Overview

**CMP Student** is a Spring Boot 4.1.1 application that exposes a reactive REST API for managing and retrieving student information. It uses Spring WebFlux for non-blocking, asynchronous I/O operations and integrates with an external student data source.

## Features

- **Reactive API**: Built with Spring WebFlux for high-performance, non-blocking request handling
- **Student Data Retrieval**: Fetch student information via reactive streams
- **Lightweight DTOs**: Student data transfer objects with Lombok annotations for cleaner code
- **Configurable External Service**: Supports configuration of external student service URLs
- **Testing**: Includes WebFlux testing dependencies for comprehensive test coverage

## Technology Stack

| Component | Version/Details |
|-----------|-----------------|
| **Java** | 21 |
| **Spring Boot** | 4.1.1 |
| **Spring WebFlux** | 4.1.1 |
| **Lombok** | Latest (from parent) |
| **Build Tool** | Maven 3.x |
| **Test Framework** | WebFlux Test (Spring Boot Test) |

## Project Structure

```
cmp_student/
├── src/
│   ├── main/
│   │   ├── java/com/cmp_student/
│   │   │   ├── CmpStudentApplication.java       # Spring Boot entry point
│   │   │   ├── controller/
│   │   │   │   └── StudentController.java       # REST endpoints
│   │   │   ├── service/
│   │   │   │   └── StudentService.java          # Business logic & external API calls
│   │   │   ├── dto/
│   │   │   │   └── StudentDto.java              # Data Transfer Object
│   │   │   └── config/
│   │   │       └── WebClientConfig.java         # WebClient configuration
│   │   └── resources/
│   │       ├── application.properties           # Application configuration
│   │       └── application-dev.properties       # Development profile configuration
│   └── test/
│       └── java/com/cmp_student/                # Test classes
├── pom.xml                                       # Maven configuration
├── mvnw & mvnw.cmd                              # Maven wrapper scripts
└── README.md                                     # This file
```

## API Endpoints

### Get All Students

**Endpoint:**
```
GET /student
```

**Description:** Retrieves all students from the external service as a reactive stream.

**Response Type:** `Flux<StudentDto>` (reactive stream of student objects)

**Example Response:**
```json
[
  {
    "id": 1,
    "name": "Juan",
    "lastName": "Perez",
    "phone": "555-0001",
    "eMail": "juan.perez@example.com"
  },
  {
    "id": 2,
    "name": "Maria",
    "lastName": "Garcia",
    "phone": "555-0002",
    "eMail": "maria.garcia@example.com"
  }
]
```

## Data Model

### StudentDto

Student data transfer object containing the following fields:

| Field | Type | Description |
|-------|------|-------------|
| `id` | Integer | Unique student identifier |
| `name` | String | Student first name |
| `lastName` | String | Student last name |
| `phone` | String | Student phone number |
| `eMail` | String | Student email address |

## Configuration

### Application Properties

Configure the application via `application.properties` or `application-dev.properties`:

**Required Configuration:**
```properties
spring.application.name=cmp_student
url.base.student=<external_student_service_url>
```

**Example Development Configuration (`application-dev.properties`):**
```properties
url.base.student=http://localhost:8081/api/students
```

## Getting Started

### Prerequisites

- Java 21 or later
- Maven 3.6+ (or use included Maven Wrapper)
- Access to external student service API

### Installation & Setup

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd cmp_student
   ```

2. **Configure application properties:**
   - Edit `src/main/resources/application.properties` or the appropriate profile configuration
   - Set the `url.base.student` property to point to your external student service

3. **Build the project:**
   ```bash
   ./mvnw clean package
   ```
   (On Windows: `mvnw.cmd clean package`)

4. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```
   (On Windows: `mvnw.cmd spring-boot:run`)

   The application will start on the default port (8080) unless configured otherwise.

### Using Maven Wrapper

The project includes Maven Wrapper scripts for convenience:
- **Linux/Mac:** `./mvnw`
- **Windows:** `mvnw.cmd`

These eliminate the need to install Maven separately.

## Running Tests

Execute the test suite:

```bash
./mvnw test
```

Tests are configured with WebFlux testing support for reactive components.

## Building & Deployment

### Build an OCI Image

To create an OCI-compliant container image:

```bash
./mvnw spring-boot:build-image
```

This generates a production-ready image using Spring Boot's built-in image building support.

### Create an Executable JAR

```bash
./mvnw clean package
java -jar target/cmp_student-0.0.1-SNAPSHOT.jar
```

## Architecture

### Component Overview

- **Controller Layer (`StudentController`):** Handles HTTP requests and delegates to the service layer
- **Service Layer (`StudentService`):** Implements business logic and communicates with external services via WebClient
- **DTO Layer (`StudentDto`):** Defines data structures for request/response serialization
- **Configuration (`WebClientConfig`):** Configures and provides WebClient bean for HTTP communications

### Reactive Flow

The application uses Project Reactor's `Flux` for reactive streams:

```
HTTP Request → Controller → Service → WebClient (external API) → StudentDto Stream → HTTP Response
```

## Key Dependencies

- **Spring WebFlux:** Reactive web framework for building non-blocking APIs
- **Lombok:** Annotation-based code generation for reducing boilerplate
- **Spring Boot WebFlux Test:** Testing utilities for reactive components

## Development Notes

- The project uses **Lombok** for reducing boilerplate (getters, setters, constructors)
- **Reactive streams** are used throughout for non-blocking I/O operations
- The **WebClient** is configured as a bean and injected into services for external API communication
- Maven compiler plugin is configured with Lombok annotation processor support

## Troubleshooting

### Issue: Connection refused to external service
**Solution:** Verify the `url.base.student` property is correctly configured and the external service is running.

### Issue: Compilation errors with Lombok
**Solution:** Ensure your IDE has Lombok plugin installed and annotation processing is enabled.

### Issue: Application fails to start
**Solution:** Check Spring Boot logs for detailed error messages and verify all required configuration properties are set.

## References

- [Spring Boot Documentation - 4.1.1](https://docs.spring.io/spring-boot/docs/4.1.1/reference/htmlsingle/)
- [Spring WebFlux Documentation](https://docs.spring.io/spring-framework/reference/web/webflux.html)
- [Project Reactor Documentation](https://projectreactor.io/docs)
- [Lombok Documentation](https://projectlombok.org/)
- [Building a Reactive RESTful Web Service](https://spring.io/guides/gs/reactive-rest-service/)

## License

This project is part of the Computer Science curriculum at Universidad Técnica del Centro (UTCh).

## Author

**Alfonso J. Barroso**

---

*Last Updated: September 2026*
