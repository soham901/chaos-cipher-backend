FROM openjdk:17-jdk-alpine

COPY chaoscipher-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.data.mongodb.uri=mongodb+srv://dbuser:dlsfSKLDsd23232424vfSdsfsfFSs@cluster0.rk6wy.mongodb.net/chaoscipher?retryWrites=true&w=majority"]
