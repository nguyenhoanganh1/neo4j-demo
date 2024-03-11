FROM openjdk:17.0.2-jdk-slim

LABEL author="Neo4j Demo"
RUN apt-get update && apt-get install -y --no-install-recommends net-tools && apt-get clean && rm -rf /var/lib/apt/lists/*
RUN set -eux; \
    apt-get update; \
    apt-get install -y --no-install-recommends \
        bzip2 \
        unzip \
        xz-utils \
        ca-certificates p11-kit \
        fontconfig libfreetype6 && \
    rm -rf /var/lib/apt/lists/*
COPY ./build/resources/main/entrypoint.sh ./entrypoint.sh
RUN chmod +x ./entrypoint.sh

COPY ./build/libs/*.jar /app/service.jar

#create log folder
CMD [mkdir -p /var/log/neo4j]

#CMD ["java", "-Xmx512m", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/service.jar"]
ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 8080
