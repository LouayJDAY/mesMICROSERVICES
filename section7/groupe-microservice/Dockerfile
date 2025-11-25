FROM amazoncorretto:21
LABEL "org.opencontainers.image.authors"="nadhem.com"

# copy built jar into the image
COPY target/groupe-microservice-0.0.1-SNAPSHOT.jar groupe-microservice-0.0.1-SNAPSHOT.jar

# run the jar
ENTRYPOINT ["java", "-jar", "groupe-microservice-0.0.1-SNAPSHOT.jar"]
EXPOSE 8081
