FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=builder /app/target/recruiter-service-*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
