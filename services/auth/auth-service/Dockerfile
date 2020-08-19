FROM maven:3.6.3-jdk-8
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
ADD . /usr/src/app
RUN mvn clean package
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

## Start with a base image containing Java runtime
#FROM openjdk:8-jdk-alpine
#
## Install tzdata
#RUN apk --update add --no-cache tzdata curl bash
#
## Default timezone
#ENV TZ Asia/Tehran
#
## Add a volume pointing to /tmp
#VOLUME /tmp
#
## Port
#EXPOSE 8888
#
## Health check
#HEALTHCHECK --interval=30s --timeout=30s CMD curl -f http://localhost:8082/actuator/health || exit 1
#
#ARG DEPENDENCY
#
##COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
##COPY ${DEPENDENCY}/META-INF /app/META-INF
#COPY ${DEPENDENCY}/BOOT-INF/classes /app
#
## Run the jar file
#ENTRYPOINT ["java","-Xmx200m","-cp","app:app/lib/*","me.mahdiyar.digipay.auth.service.AuthServiceApplication"]
