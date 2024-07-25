FROM adoptopenjdk/openjdk11:ppc64le-ubuntu-jdk-11.0.24_8
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY ./kafka-consumer/target/*.jar consumer-app.jar

#Adding wait
ENV WAIT_VERSION 2.7.2
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/$WAIT_VERSION/wait /wait
RUN chmod +x /wait

CMD ["java", "-jar", "consumer-app.jar"]
EXPOSE 8091