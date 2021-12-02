FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/taxiApp-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} gateway.jar
ENTRYPOINT ["java","-jar","/gateway.jar"]