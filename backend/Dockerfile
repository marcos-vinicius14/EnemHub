FROM eclipse-temurin:21-jre-alpine-3.21

LABEL maintainer="marcos-vinicius14"
WORKDIR /app
COPY target/backend-0.0.1-SNAPSHOT.jar /app/enemhub_backend.jar
ENTRYPOINT ["java", "-jar", "enemhub_backend.jar"]
EXPOSE 8080