# groupe-microservice

Minimal Spring Boot microservice for Groupe (adapted from a Department example). Uses H2 in-memory DB and exposes a GET endpoint:

- GET /api/groupes/{nom} — returns groupe by name

Run with:

```bash
# from project root
mvn -DskipTests package
java -jar target/groupe-microservice-0.0.1-SNAPSHOT.jar

Docker
------

Follow these steps to containerize and run the Groupe microservice locally using Docker:

1. Build the jar file

```bash
# From the project root
mvn -DskipTests clean package
```

2. Build the Docker image (replace 'yourhubuser' with your Docker Hub username)

```bash
docker build . -t yourhubuser/groupe:v1
```

3. Run the container (map ports if needed)

```bash
docker run -d -p 8081:8081 yourhubuser/groupe:v1
```

4. Test the endpoint

Use Postman or curl to call the endpoint:

```bash
curl http://localhost:8081/api/groupes/{nom}
```

Notes:
- We disabled the Spring Cloud Discovery/Eureka client and config server in `application.yml` so this container won't attempt to register with a Eureka server. If you want to keep service discovery enabled in a Docker Compose stack, re-enable discovery in production configuration.
```

H2 console: http://localhost:8081/h2-console (JDBC URL: jdbc:h2:mem:grpdb)
