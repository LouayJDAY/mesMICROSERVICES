#Start with a base image containing Java runtime
FROM openjdk:21
#Information around who maintains the image
LABEL "org.opencontainers.image.authors"="nadhem.com"
# Add the application's jar to the image
COPY target/animal-microservice-0.0.1-SNAPSHOT.jar animal-microservice-0.0.1-SNAPSHOT.jar
# execute the application
ENTRYPOINT ["java", "-jar", "animal-microservice-0.0.1-SNAPSHOT.jar"]