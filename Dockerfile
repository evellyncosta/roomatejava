FROM gradle:8.1.1-jdk17 AS build

WORKDIR /app
COPY . /app

RUN gradle clean build -x test

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/build/libs/RoomMate-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "RoomMate-0.0.1-SNAPSHOT.jar"]