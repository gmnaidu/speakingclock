# Speaking Clock Microservice

This microservice converts time to words. It has two endpoints:
1. Gets current time in words
2. Converts provided time (in HH:mm format) to words

## Build and Run

### Prerequisites
- JDK 8 or above
- Maven

### Build
```bash
mvn clean install

# http://localhost:8080/swagger-ui/index.html#/