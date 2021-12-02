FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/registry-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} discovery-server.jar
ENTRYPOINT ["java","-jar","/discovery-server.jar"]