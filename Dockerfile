FROM maven:3.6.3-jdk-8-slim as build
COPY pom.xml /tmp/
RUN mvn -B dependency:go-offline -f /tmp/pom.xml -s /usr/share/maven/ref/settings-docker.xml
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn -B -s /usr/share/maven/ref/settings-docker.xml package

FROM openjdk:8-jre-alpine as production
WORKDIR /app
COPY --from=build /tmp/target/*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 5000