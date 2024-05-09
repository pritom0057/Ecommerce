# Use a base image with OpenJDK 17
FROM adoptopenjdk/openjdk17:jre

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target/Ecommerce-0.0.1-SNAPSHOT.jar /app

# Command to run the application
CMD ["java", "-jar", "Ecommerce-0.0.1-SNAPSHOT.jar"]
