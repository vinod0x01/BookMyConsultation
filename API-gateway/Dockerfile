FROM openjdk:14-jdk-alpine
MAINTAINER vinod
ADD ./target/gateway-service.jar /opt/app/gateway-service.jar
WORKDIR /opt/app
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/opt/app/gateway-service.jar"]
