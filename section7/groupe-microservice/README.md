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

Using MySQL via docker-compose
--------------------------------

You can run MySQL and the microservice together using the `docker-compose.yml` at the repository root. The compose file will:

- Start a `mysqldb` MySQL container and create a `test` database.
- Build and run `groupe-ms` with environment variables pointing it at the `mysqldb` container.

From the repository root:

```bash
docker compose up --build
```

Then, verify the service is up at `http://localhost:8081` and the DB container is reachable on port `3306`.

To connect with SQL Electron or another MySQL client, use connection details:

- Host: localhost
- Port: 3306
- User: root
- Password: root
- Database: test

Note: the microservice datasource is configured to use environment variables; if those variables are not provided it falls back to the H2 in-memory DB used for development.
