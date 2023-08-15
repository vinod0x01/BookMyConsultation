FROM openjdk:14-jdk-alpine
MAINTAINER rupa
ADD ./target/payment-service.jar /opt/app/payment-service.jar
WORKDIR /opt/app
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 8086
ENTRYPOINT [ "java", "-jar", "/opt/app/payment-service.jar"]
