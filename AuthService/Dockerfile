FROM adoptopenjdk/openjdk11:latest
WORKDIR usr/src
ADD ./target/AuthService-0.0.1-SNAPSHOT.jar /usr/src/AuthService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "AuthService-0.0.1-SNAPSHOT.jar"]