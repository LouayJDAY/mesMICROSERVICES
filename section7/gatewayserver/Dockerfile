#Start with a base image containing Java runtime
FROM amazoncorretto:21
#Information around who maintains the image
LABEL "org.opencontainers.image.authors"="nadhem.com"
# Add the application's jar to the image
COPY target/gatewayserver-0.0.1-SNAPSHOT.jar gatewayserver-0.0.1-SNAPSHOT.jar
# execute the application
ENTRYPOINT ["java", "-jar", "gatewayserver-0.0.1-SNAPSHOT.jar"]