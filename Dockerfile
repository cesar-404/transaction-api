FROM maven:3.9.9-amazoncorretto-21-alpine AS build
COPY . .
RUN mvn package

FROM amazoncorretto:21-alpine
COPY --from=build target/TransactionApi.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]