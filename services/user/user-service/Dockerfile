# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Install tzdata
RUN apk --update add --no-cache tzdata curl bash

# Default timezone
ENV TZ Asia/Tehran

# Add a volume pointing to /tmp
VOLUME /tmp

# Port
EXPOSE 8081

# Health check
HEALTHCHECK --interval=30s --timeout=30s CMD curl -f http://localhost:8081/actuator/health || exit 1

ARG DEPENDENCY

COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

# Run the jar file
ENTRYPOINT ["java","-Xmx200m","-cp","app:app/lib/*","com.farazpardazan.iranmall.auth.me.mahdiyar.AuthServiceApplication"]
