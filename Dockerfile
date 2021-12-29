FROM openjdk:8-jdk-alpine
RUN mkdir /root/app
COPY target/*.jar /root/app
ENTRYPOINT java -jar /root/app/*.jar