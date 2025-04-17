FROM maven:3.9-eclipse-temurin
WORKDIR /app
COPY . .
RUN mvn clean
CMD ["mvn", "spring-boot:run"]
