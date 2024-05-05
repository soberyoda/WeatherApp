FROM ubuntu:latest
LABEL authors="agamm"

ENTRYPOINT ["top", "-b"]

FROM openjdk:17
WORKDIR /app
COPY ${JAR_FILE} app.jar
EXPOSE 8080
CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]



