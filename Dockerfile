FROM eclipse-temurin:17.0.2_8-jdk as builder
EXPOSE 8080
ARG JAR_FILE=main/target/main.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
