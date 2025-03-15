# Build stage
FROM maven:3.9-eclipse-temurin-22 AS build

WORKDIR /app
# Copy pom.xml first to leverage Docker cache for dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM tomcat:10-jdk22-openjdk-jammy

# Remove default Tomcat applications
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the built WAR file to Tomcat's webapps directory as ROOT application
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Tomcat runs on port 8080
EXPOSE 8080

# Database connection environment variables (customize as needed)
ENV DB_URL=jdbc:postgresql://db:5432/jaba
ENV DB_USER=postgres
ENV DB_PASSWORD=1

# Start Tomcat
CMD ["catalina.sh", "run"]