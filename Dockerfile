#
# Build 
#
FROM maven:3.6.3-jdk-14 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Server 
#
FROM openjdk:14
WORKDIR /app
COPY --from=build /home/app/target/despesas-spring-mvc.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "--enable-preview", "-Dspring.profiles.active=docker", "despesas-spring-mvc.jar"]