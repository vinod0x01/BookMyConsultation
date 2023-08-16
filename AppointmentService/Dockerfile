FROM maven:3.6.3-jdk-14 AS builder
WORKDIR /usr/src/app
COPY src ./src
COPY pom.xml .
RUN mvn clean install -DskipTests

FROM openjdk:14-jdk-alpine
MAINTAINER vinod
WORKDIR /usr/app
COPY --from=builder /usr/src/app/target/appointment-service.jar /usr/app/appointment-service.jar
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 8082
ENTRYPOINT [ "java", "-jar", "/usr/app/appointment-service.jar"]
