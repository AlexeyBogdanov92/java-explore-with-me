FROM amazoncorretto:21-alpine-jdk
COPY target/*.jar stats-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/stats-server-0.0.1-SNAPSHOT.jar"]