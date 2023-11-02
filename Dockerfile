
FROM openjdk:11-jdk-slim
LABEL authors="KJJ"
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} /opt/app/app.jar
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]
