FROM openjdk:8-jdk

RUN apt-get update && apt-get install -y \
    libxext6 libxrender1 libxtst6 libxi6 libfontconfig1 \
 && rm -rf /var/lib/apt/lists/*

ARG JAR_FILE=target/currencyexchange-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]

