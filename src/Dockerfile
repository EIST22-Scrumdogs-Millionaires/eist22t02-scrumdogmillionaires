FROM openjdk:17-jdk-alpine

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

ARG JAR_FILE=build/libs/T01E01-Exercise-1.0.0.jar

COPY ${JAR_FILE} app.jar
RUN mkdir src/
RUN mkdir src/main/
RUN mkdir src/main/java/
RUN mkdir src/main/java/hello/
RUN mkdir src/main/java/hello/world/
RUN mkdir src/main/java/hello/world/demo/
RUN mkdir src/main/java/hello/world/demo/data/
COPY src/main/java/hello/world/demo/data/email.json ./src/main/java/hello/world/demo/data/email.json

ENTRYPOINT ["java","-jar","/app.jar"]
