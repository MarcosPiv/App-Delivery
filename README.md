# Aplicación de Delivery

Este proyecto tiene como objetivo desarrollar una aplicación de delivery que permite gestionar pedidos de comida de restaurantes y rotiserías.

## Descripción General

La aplicación está diseñada para dos tipos de usuarios principales:
- **Vendedores:** Restaurantes o rotiserías que pueden gestionar sus datos, menú, y pedidos.
- **Compradores:** Clientes que pueden realizar pedidos, gestionarlos y ver su historial.

## Modelo de Datos
![image](https://github.com/user-attachments/assets/3cd371f1-c72d-45e3-9125-c1ce89df8de3)

### Funcionalidades principales

1. **Gestión de Usuarios:**
   - Vendedores y compradores pueden gestionar sus datos.
   - Búsqueda, adición y eliminación de vendedores y compradores.
   - Cálculo de distancia entre un cliente y un vendedor.

2. **Gestión de Menú:**
   - Creación y gestión del menú de comidas y bebidas.
   - Clasificación de ítems como comida o bebida, y verificación si son aptos para veganos.
   - Cálculo del peso total del pedido.

3. **Búsqueda y Filtrado:**
   - Búsqueda de ítems en el menú por diversos criterios.
   - Implementación de búsquedas utilizando Streams y manejo de excepciones.

4. **Creación de Pedidos:**
   - Creación de pedidos por parte de los clientes.
   - Selección de forma de pago con recargos dependiendo de la opción elegida.
   - Gestión de estados del pedido desde recibido hasta enviado.

5. **Gestión de Estados de Pedido:**
   - Actualización del estado del pedido por parte de los vendedores.
   - Notificación a los clientes sobre el estado de su pedido.

6. **Interfaz de Usuario (UI) con Swing:**
   - Creación de interfaces gráficas para la gestión de vendedores, clientes, menú y pedidos.

7. **Persistencia y Testing:**
   - Conexión a base de datos para la persistencia de información.
   - Unit Testing de las funcionalidades clave.

8. **Implementación con SpringBoot:**
   - Migración del proyecto a SpringBoot.
   - Creación de interfaces de consulta a través de HTML.

## Requisitos

- **Java:** Versión 21.
- **SpringBoot:** Versión 2.5 o superior.
- **Base de Datos:** PosgreSQL.
- **Maven:** Para la gestión de dependencias.
- **Swing:** Para la interfaz de usuario.

## Instalación

1. Clonar el repositorio.
2. Configurar la base de datos PosgreSQL.
3. Importar el proyecto en un IDE compatible con Java.
4. Ejecutar los scripts SQL para crear las tablas necesarias.
5. Ejecutar la aplicación.

## Autores

- **Marcos Poet**
- **Mario Betatti**
- **Marcos Pividori**
- **Joaquín Lell**
