# Usar una imagen base de OpenJDK
FROM eclipse-temurin:17-jdk

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Actualizar el almacén de certificados del sistema
RUN update-ca-certificates


# Copiar el archivo JAR de la aplicación al contenedor
COPY build/libs/*.jar app.jar

# Exponer el puerto que usa Spring Boot
EXPOSE 9090

# Comando para ejecutar la aplicación Spring Boot
CMD ["java", "-jar", "app.jar"]
