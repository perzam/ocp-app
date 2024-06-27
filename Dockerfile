# Etapa 1: Construcción del WAR
FROM maven:3.8.4-openjdk-11 AS build
WORKDIR /app

# Copiar el archivo de configuración de Maven y las dependencias
COPY . .

# Construir el proyecto y generar el WAR
RUN mvn clean package

# Etapa 2: Construcción de la imagen de WildFly
FROM jboss/wildfly:latest
WORKDIR /opt/jboss/wildfly

# Copiar el WAR generado en la etapa anterior a la carpeta de despliegue de WildFly
COPY --from=build /app/target/your-app-1.0-SNAPSHOT.war ./standalone/deployments/

# Exponer el puerto 8080
EXPOSE 8080
