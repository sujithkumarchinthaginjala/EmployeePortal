FROM openjdk:21-jdk-slim

WORKDIR /app

RUN apt-get update && \
    apt-get install -y netcat-openbsd && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

COPY target/MyApp.jar app.jar

EXPOSE 8080 3306

ENTRYPOINT ["sh", "-c", "until nc -z mysql 3307; do sleep 2; done; java -jar app.jar"]
