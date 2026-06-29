# My System:


My System es una APi Rest que simula  el compartamiento de una cafeteria, esta hecho con  Arquitectura Hexagonal.


-----------------------------------------------------------------------------------------------------------------------

Cómo organicé el proyecto (Arquitectura)
Decidí usar Arquitectura Hexagonal para separar la lógica del negocio de las herramientas externas (como la base de datos o el propio Spring Boot). El código está dividido en tres partes:

Dominio: Es el núcleo del proyecto. Aquí están las reglas del negocio y las entidades básicas. Es Java puro.
Puertos: Son interfaces que definen qué acciones se pueden hacer y qué datos se necesitan para conectar el dominio con el exterior.
Adaptadores: Es la implementación real de las tecnologías. Aquí están los controladores de Spring (para recibir las peticiones HTTP) y los repositorios de JPA (para guardar la información en MySQL).


--------------------------------------------------------------------------------------------------------------------------


# Tecnologías utilizadas:

Java 21
Spring Boot 3.x (Spring Web, Spring Data JPA)
Hibernate y MySQL
Maven
Validaciones con Jakarta Bean Validation (@NotNull)

-------------------------------------------------------------------------------------------------------------------------------

# Entidades Principales:

Order: Entidad que representa el pedido de los clientes.
Payment : Entidad que se encarga del pago de las ordenes.
CoffeItems : Entidad que representa los ingredientes de preferencia por el cliente.
Receipt: Entidad que se encarga de dar la factura de la orden.

-----------------------------------------------------------------------------------------------------------------------------------

### Estados

# EnumStatus:

 PAYMENT_EXPECTED,
    PAID,
    PREPARING,
    READY,
    TAKEN,
    CANCELLED

# EnumSize:

SMALL(BigDecimal.valueOf(4.0)),
LARGE(BigDecimal.valueOf(5.0));

Los Precios del coffe estan en el enum para que el mismo enum se encargue de sus costos, lo hice para practicar y ver su viavilidad en el proyecto

# EnumMilck:

 WHOLE,
 SKIMMED,
 SOY

 # EnumLocation:

 TAKE_AWAY,
 IN_STORE

  # EnumTypeCoffe:

  LATTE,
  ESPRESSO,
  CAPPUCCINO

  # EnumPaymentmethod:

  CreditCard,
  pix,
  DebitCard
------------------------------------------------------------------------------------------------------------------------------------------------


## Rutas de la API (Endpoints)


* POST  http://localhost:8081/api/v1/coffee
* GET  http://localhost:8081/api/v1/coffee/{id}/take 
* GET  http://localhost:8081/api/v1/coffee/{id}/Order
* GET  http://localhost:8081/api/v1/coffee/{id}
* PACHT http://localhost:8081/api/v1/coffee/{id}
* DELETE http://localhost:8081/api/v1/coffee/{id}/cancel

Para que no fallen los endpoints debe seguir el flujo del proyecto:

Primero debes crear la orden, Luego a tienes que pagar, luego tomarla (Take), ya con eso puedes leer tu recibo o buscar la orden por id.  

-------------------------------------------------------------------------------------------------------------------------------------------------


### Ejemplo de JSON (/api/v2/pedidos)

* POST / Crear order :

       { 
        "enumLocation": "IN_STORE",
         "items": [
        {
            "drink": "LATTE",
            "size": "LARGE",
            "milk": "SOY",
            "quantity": 6
        },
        {
            "drink": "LATTE",
            "size": "SMALL",
            "milk": "SOY",
            "quantity": 5
        }
           ]
      }


  * PACHT / Pagar order :
 
         {
            "cardHoldernName": "Perez",
            "numCard": "12345678",
            "paymentmethod": "CreditCard"
         }



    ------------------------------------------------------------------------------------------------------------------------------


    ## Cómo correr el proyecto en tu computadora:

    ### Pre requisitos:
    
      * Tener instalado JDK 21 y Maven.
      * Tener MySQL corriendo.
   

-------------------------------------------------------------------------------------------------------------------------------------------

   ### 2. Configurar la base de datos (src/main/resources/application.properties)
   Asegúrate de cambiar los datos de acceso por los tuyos:


   ```properties
spring.datasource.url=jdbc:mysql://localhost:3306/delivery_app?createDatabaseIfNotExist=true
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contrasenia
spring.jpa.hibernate.ddl-auto=update
```




**Desarrollado por:** Adalberto Ahumada — Programador Backend enfocado en aprender buenas prácticas y código limpio.




