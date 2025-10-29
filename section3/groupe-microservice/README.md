# groupe-microservice

Minimal Spring Boot microservice for Groupe (adapted from a Department example). Uses H2 in-memory DB and exposes a GET endpoint:

- GET /api/groupes/{nom} — returns groupe by name

Run with:

```bash
# from project root
mvn -DskipTests package
java -jar target/groupe-microservice-0.0.1-SNAPSHOT.jar
```

H2 console: http://localhost:8081/h2-console (JDBC URL: jdbc:h2:mem:grpdb)
