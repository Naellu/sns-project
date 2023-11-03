
FROM openjdk:11
LABEL authors="KJJ"
ARG JAR_FILE=./build/libs/sns-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} ./app/sns-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "./app/sns-0.0.1-SNAPSHOT.jar"]
