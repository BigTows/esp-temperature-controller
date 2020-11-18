FROM gradle:6.5.0-jdk11 AS build
MAINTAINER Alexander <BigTows> Chapchuk
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN gradle build -x test

FROM openjdk:11

EXPOSE 8080

COPY --from=build /home/gradle/src/build/libs/*.jar /app/application.jar
CMD ["java", "-jar","/app/application.jar"]