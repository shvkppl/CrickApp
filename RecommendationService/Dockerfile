FROM adoptopenjdk/openjdk11:latest
WORKDIR usr/src
ADD ./target/RecommendationService-0.0.1-SNAPSHOT.jar /usr/src/RecommendationService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "RecommendationService-0.0.1-SNAPSHOT.jar"]