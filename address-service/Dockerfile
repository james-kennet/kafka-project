FROM adoptopenjdk/openjdk11:ppc64le-ubuntu-jdk-11.0.24_8
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY ./address-service/target/*.jar address-app.jar

#Adding wait
ENV WAIT_VERSION 2.7.2
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/$WAIT_VERSION/wait /wait
RUN chmod +x /wait

CMD ["java", "-jar", "address-app.jar"]
EXPOSE 8093