FROM openjdk:17-jdk-alpine

COPY chaoscipher.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
