# animal-microservice

Minimal Spring Boot microservice for Animal entity with JPA, H2 in-memory DB. Exposes REST endpoints:

- GET /api/animals/id/{id} — returns animal by id
- GET /api/animals/nom/{nom} — returns animal by name

## Quick Start

```bash
cd animal-microservice
mvn -DskipTests package
java -jar target/animal-microservice-0.0.1-SNAPSHOT.jar
```

H2 console: http://localhost:8082/h2-console
JDBC URL: jdbc:h2:mem:animaldb
User: sa
Password: (empty)

## Seeded Data

Three animals are created at startup:
- Lion (poidsAnimal: 190.5)
- Elephant (poidsAnimal: 5000.0)
- Girafe (poidsAnimal: 1200.0)

## Example Requests

```bash
# Get by ID
curl http://localhost:8082/api/animals/id/1

# Get by Name
curl http://localhost:8082/api/animals/nom/Lion
```
