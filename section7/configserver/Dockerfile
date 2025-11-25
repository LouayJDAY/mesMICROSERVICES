#Start with a base image containing Java runtime
FROM amazoncorretto:21
#Information around who maintains the image
LABEL "org.opencontainers.image.authors"="nadhem.com"
# Copy the entire config repo (with .git) to /config-repo
COPY src/main/resources/config/.git /config-repo/.git
COPY src/main/resources/config /config-repo
# Add the application's jar to the image
COPY target/configserver-0.0.1-SNAPSHOT.jar configserver-0.0.1-SNAPSHOT.jar
# execute the application
ENTRYPOINT ["java", "-jar", "configserver-0.0.1-SNAPSHOT.jar"]