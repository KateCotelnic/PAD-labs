FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/cache-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} cache.jar
ENTRYPOINT ["java","-jar","/cache.jar"]