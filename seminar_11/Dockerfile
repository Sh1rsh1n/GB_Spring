FROM maven:3.9.6-eclipse-temurin-17-alpine
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml package -Dmaven.test.skip=true
CMD mvn spring-boot:run