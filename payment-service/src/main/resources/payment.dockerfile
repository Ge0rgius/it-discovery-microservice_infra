FROM eclipse-temurin:21-jre-alpine
ADD target/payment-service-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS --enable-preview -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
