FROM openjdk:21-jdk-slim

WORKDIR /app

RUN apt-get update \
    && apt-get install -y netcat-openbsd \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

COPY target/MyApp.jar app.jar

EXPOSE 8080 3306

CMD service mysql start && \
    until nc -z localhost 3306; do sleep 1; done && \
    java -jar app.jar
