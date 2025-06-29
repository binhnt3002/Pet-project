# Build stage
FROM maven:3.9.10-eclipse-temurin-17 AS build
RUN apt-get update && apt-get install -y \
    libxext6 libxrender1 libxtst6 libxi6 libfontconfig1 \
 && rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package
CMD ["mvn","exec:java","-Dexec.mainClass=com.petproject.Main"]

