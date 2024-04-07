FROM docker.io/library/eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /src/garasiku

# Copy only the pom.xml file and resolve dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the project files and build
COPY . .
RUN mvn clean package

FROM docker.io/library/eclipse-temurin:21-jre-alpine AS runner

ARG USER_NAME=garasiku
ARG USER_UID=1000
ARG USER_GID=${USER_UID}

RUN addgroup -g ${USER_GID} ${USER_NAME} && \
    adduser -h /opt/garasiku -D -u ${USER_UID} -G ${USER_NAME} ${USER_NAME}

USER ${USER_NAME}
WORKDIR /opt/garasiku
COPY --from=builder --chown=${USER_UID}:${USER_GID} /src/garasiku/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java"]
CMD ["-jar", "app.jar", "--server.port=8000"]