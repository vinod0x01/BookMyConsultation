FROM openjdk:14-jdk-alpine
MAINTAINER rupa
ADD ./targeJWT-generatort/eurekaServer.jar /opt/app/eurekaServer.jar
WORKDIR /opt/app
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 8761
ENTRYPOINT [ "java", "-jar", "/opt/app/eurekaServer.jar"]
