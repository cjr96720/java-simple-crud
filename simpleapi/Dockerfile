FROM openjdk:24-slim

COPY . /app
WORKDIR /app

RUN ./mvnw clean package -DskipTests

RUN mv target/*.jar target/app.jar

ENTRYPOINT ["java", "-jar", "target/app.jar"]
