FROM openjdk:17-jdk-alpine

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

ARG JAR_FILE=build/libs/T01E01-Exercise-1.0.0.jar
ARG JAR_FILE_2=build/libs/T01E01-Exercise-1.0.0-plain.jar

COPY ${JAR_FILE} app.jar
COPY ${JAR_FILE_2} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]