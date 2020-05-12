#
# Build stage
#
FROM maven:3.6.3-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/demo2-1.0.0.jar /usr/local/lib/demo.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]
