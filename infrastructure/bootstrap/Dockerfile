FROM registry.maib.md/eclipse-temurin:21.0.6_7-jre-alpine-3.20
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} transactional-outbox.jar
ENTRYPOINT ["java","-jar","/transactional-outbox.jar"]