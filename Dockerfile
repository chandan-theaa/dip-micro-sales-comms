FROM openjdk:8-jdk-alpine


# Convert build Args to runtime Env Vars
ARG VERSION
ENV version ${VERSION}

# Install Java App

WORKDIR /

ARG JAR_FILE

ADD ${JAR_FILE} /app.jar

HEALTHCHECK \
	--interval=1m \
	--timeout=3s \
	--start-period=30s \
	--retries=2 \
	CMD wget -qO - localhost:82/actuator/health || exit 1

EXPOSE 80 81 82

ENTRYPOINT [ "java", "-server", "-jar", "app.jar"]
