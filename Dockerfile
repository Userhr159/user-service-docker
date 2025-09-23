# Usamos imagen oficial de Java
FROM openjdk:21-jdk-slim

# Carpeta de trabajo
WORKDIR /app

# Copiar el JAR generado por Gradle
COPY build/libs/User-ServiceDocker-0.0.1-SNAPSHOT.jar app.jar

# Ejecutar el servicio
ENTRYPOINT ["java", "-jar", "app.jar"]
