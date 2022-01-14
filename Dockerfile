FROM maven:3-jdk-8
COPY . /app
WORKDIR /app
RUN mvn --batch-mode package
EXPOSE 8080
CMD ["java", "-jar", "./target/messageboard-0.0.1-SNAPSHOT.jar"]