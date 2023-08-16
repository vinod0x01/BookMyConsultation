FROM maven:3.6.3-jdk-14 AS builder
WORKDIR /usr/src/app
COPY src ./src
COPY pom.xml .
RUN mvn clean install -DskipTests

FROM openjdk:14-jdk-alpine
MAINTAINER rupa
WORKDIR /usr/app
COPY --from=builder /usr/src/app/target/eurekaServer.jar /usr/app/eurekaServer.jar
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 8761
ENTRYPOINT [ "java", "-jar", "/usr/app/eurekaServer.jar"]
