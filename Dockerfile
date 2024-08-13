FROM openjdk:17-jdk-alpine

COPY chaoscipher-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.data.mongodb.uri=mongodb+srv://dbuser:123456@localhost:27017/chaoscipher"]
