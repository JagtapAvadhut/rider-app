FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /workspace

COPY . .

RUN mvn clean install -DskipTests

FROM eclipse-temurin:17-jre-alpine

RUN addgroup -S spring && adduser -S spring -G spring \
    && apk add --no-cache curl

WORKDIR /app
COPY --from=build /workspace/pricing-service/target/pricing-service-1.0.0.jar app.jar

USER spring:spring
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
