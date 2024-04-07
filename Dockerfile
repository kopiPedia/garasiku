# Use an Alpine-based image for smaller size
FROM docker.io/library/eclipse-temurin:21-jdk-alpine AS builder

# Install Maven
RUN apk add --no-cache maven

# Set the working directory
WORKDIR /src/garasiku

# Copy only the pom.xml file and resolve dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the project files and build
COPY . .
RUN mvn clean package
RUN mvn clean install

# Second stage: Use a smaller JRE-based image for runtime
FROM docker.io/library/eclipse-temurin:21-jre-alpine AS runner

# Arguments for user and group
ARG USER_NAME=garasiku
ARG USER_UID=1000
ARG USER_GID=${USER_UID}

# Create non-root user
RUN addgroup -g ${USER_GID} ${USER_NAME} && \
    adduser -h /opt/garasiku -D -u ${USER_UID} -G ${USER_NAME} ${USER_NAME}

# Switch to non-root user
USER ${USER_NAME}

# Set working directory
WORKDIR /opt/garasiku

# Copy built JAR from builder stage
COPY --from=builder --chown=${USER_UID}:${USER_GID} /src/garasiku/target/*.jar app.jar

# Expose port
EXPOSE 8080

# Define entrypoint and default command
ENTRYPOINT ["java"]
CMD ["-jar", "app.jar", "--server.port=8000"]