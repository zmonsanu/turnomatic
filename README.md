# ⭐ TURNOMATIC
Ejemplo de backend sencillo con SpringBoot para gestión de turnos y asignación de mesas.

## 📜 Summary
Servicio realizado con SpringBoot v2.7.17 para gestionar una serie de contadores por categorias y asignando 
una mesa de atención.
Contiene una tabla en una base de datos sencilla para almacenar los contadores por categorías:

-AT Atención al cliente

-EN Recogida de mercancías

-CD Cambios y devoluciones

-FA Facturas

-CS Contratación de servicios


El contador será numérico desde 1 hasta un máximo que se podrá parametrizar, pero el servicio devolverá un texto de la siguiente forma:
AT2024-1

es decir seguirá un patrón de CATEGORIA+AÑO-CONTADOR

El servicio ofrece los endpoints para create,getall, get y avanzar el contador.

Se utiliza una pseudo arquitectura hexagonal con sus tres capas dominio, infraestructura y aplicación.


## 📜 Base de datos
El sistema de base de datos elegido es una base de datos en archivo local de tipo H2.
la ubicación de dicho fichero será por defecto en ./data/turnosdb.mv.db

Solamente será necesario una tabla cuya estructura se encuentra en fichero schema.sql que se ejecutará
al arrancar el servicio de SpringBoot.

Se podrá administrar dicha base de datos desde la consola:
- http://localhost:8080/h2_console/

entrando con usuario sa (sin password)

## 📜 Configuraciones
En fichero application.properties se encuentran los parámetros necesarios para configurar el servicio.

Principalmente se tendrá en cuenta dos parámetros:

    - spring.datasource.url -> para indicar la ubicación de la base de datos
    - spring.datasource.schema -> ubicación del script de creación de la tabla necesaria para funcionamiento.
## 🚀 Puesta en marcha
Simplemente se deberá ejecutar el método main de la clase MicroServicioBoot.java
Por defecto va a arrancar en puerto 8080 ya que se no ha especificado otro.



En la pagina siguiente se puede comprobar que esté arrancado el  microservicio:
- http://localhost:8080/turnos/getall

## ‍💻  Endpoint para pruebas:
Creación de contadores por categoria:

    - POST http://localhost:8080/turnos/create?categoria=AT&mesa=mesa2&maximoContador=150

Obtencion de contadores:

    - GET http://localhost:8080/turnos/getall


Avanzar el contador de una categoria determinada y a una mesa:

    - POST http://localhost:8080/turnos/nextContadorByCategoria?categoria=AT&mesa=mesa2

Este último endpoint nos devolverá la cadena de texto con el valor del contador

EJEMPLO:

    - curl --location --request POST 'http://localhost:8080/turnos/nextContadorByCategoria?categoria=CS&mesa=mesa6'

Nos devolverá : CS2024-1



Reseteo de contador por categoria:

    - POST http://localhost:8080/turnos/resetContadorByCategoria?categoria=CS

Nos devolverá un booleano a true si la operación ha sido correcta.