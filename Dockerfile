FROM adoptopenjdk:latest
ARG JAR_FILE=out/artifacts/demo_jar/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]