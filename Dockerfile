# Usa una imagen base con Java 17
FROM eclipse-temurin:21-jdk-jammy

# Define argumento para el archivo jar generado
ARG JAR_FILE=target/*.jar

# Copia el jar al contenedor con nombre app.jar
COPY ${JAR_FILE} app.jar

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]
