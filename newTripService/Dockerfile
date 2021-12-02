FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/newTripService-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} newtrip.jar
ENTRYPOINT ["java","-jar","/newtrip.jar"]

#FROM openjdk:8-jdk-alpine
#WORKDIR /usr/shr/new-trip-service
#COPY ./target/newTripService-0.0.1-SNAPSHOT.jar /usr/shr/new-trip-service/
#CMD ["java", "-jar", "/usr/shr/new-trip-service/newTripService-0.0.1-SNAPSHOT.jar"]