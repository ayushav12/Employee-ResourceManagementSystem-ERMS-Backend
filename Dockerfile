FROM openjdk:8-jre-alpine
WORKDIR /opt
ENV APP_FILE bootCrudApi-0.0.1-SNAPSHOT.jar
EXPOSE 8080
COPY ./target/bootCrudApi-0.0.1-SNAPSHOT.jar /opt/bootCrudApi-0.0.1-SNAPSHOT.jar
ENTRYPOINT exec java $JAVA_OPTS -jar bootCrudApi-0.0.1-SNAPSHOT.jar
MAINTAINER ayushav12