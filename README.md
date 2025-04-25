# Java Application on OpenShift with MySQL

## Descripción

Esta es una aplicación Java que se conecta a una base de datos MySQL 5.7 y muestra el contenido de una tabla llamada `employees`. La aplicación utiliza WildFly como servidor de aplicaciones.

## Requisitos Previos

- Java Development Kit (JDK) 8 o superior
- Apache Maven

## Variables para la conexion a la base de datos mysql.

- DB_SERVICE_NAME
- DB_USER
- DB_PASSWORD
- DB_NAME

# Construir el WAR

```bash
mvn clean package
