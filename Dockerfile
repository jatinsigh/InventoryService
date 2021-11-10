FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app.jar
COPY src/main/resources/secure-connect-inventory.zip /secure-connect-inventory.zip
ENTRYPOINT ["java","-jar","/app.jar"]
