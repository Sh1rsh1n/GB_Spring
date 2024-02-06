FROM maven:3.9.6-eclipse-temurin-17-alpine
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml package -Dmaven.test.skip=true
CMD mvn spring-boot:run

#FROM eclipse-temurin:17-jre-alpine
#WORKDIR /app
#COPY --from=builder /app/target/*.jar /app/*.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "/app/*.jar"]
