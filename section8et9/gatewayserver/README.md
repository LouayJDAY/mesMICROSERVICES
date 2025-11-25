# Gateway Server

This is the API Gateway for the microservices architecture.

## Configuration

- Port: 8888
- Eureka Client: Connects to Eureka server at localhost:8761
- Routes:
  - /api/animals/** -> ANIMAL1 service
  - /api/groupes/** -> GROUPE service

## Running

```bash
mvn spring-boot:run
```

## Testing

- Check routes: http://localhost:8888/actuator/gateway/routes
- Animal service: http://localhost:8888/api/animals/id/1
- Groupe service: http://localhost:8888/api/groupes/Félin