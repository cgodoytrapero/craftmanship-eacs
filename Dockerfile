FROM debian:latest

RUN apt-get update
RUN apt-get install -y default-jdk maven

WORKDIR /code/component-test

ENTRYPOINT ["mvn", "test"]


