FROM maven:3.8.4-openjdk-17 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM eclipse-temurin:17
COPY --from=build /usr/src/app/target/SecProgProject-0.0.1-SNAPSHOT.jar /usr/app/app.jar
ENTRYPOINT ["java","-jar","/usr/app/app.jar"]