# Fabrica de Licores - Sistema de Inventario y Facturacion

**F30 - Proyecto integrador. Programación Orientada a Objetos. IU Digital de Antioquia.**

Sistema de consola en Java orientado a objetos para el control de inventarios en bodega, gestion de pedidos con carrito de compras y facturacion automatizada para una fabrica de licores.

## Estructura de Archivos

* `Licor.java`: Entidad que representa un licor (nombre, precio unitario por caja y stock), con metodos para reabastecer y descontar inventario.
* `ItemCarrito.java`: Representa una linea de compra en el carrito (referencia al licor, cantidad de cajas y subtotal).
* `Carrito.java`: Administra el carrito de compras (capacidad maxima de 5 lineas, validacion de existencias, calculo de 10% de descuento por mas de 50 cajas, 20% de impuesto al consumo y facturacion).
* `FabricaLicores.java`: Clase principal con el menu interactivo dividido en:
  * **Modulo Cliente**: Requiere verificacion de mayoria de edad (18+), catalogo en vivo, carrito y facturacion.
  * **Modulo Administrador**: Auditoria de inventario y reabastecimiento de bodega.

## Requisitos

* Java Development Kit (JDK) 8 o superior instalado y configurado en el sistema.

## Compilacion y Ejecucion

1. Abrir la terminal o consola en la carpeta raiz del proyecto.
2. Compilar todos los archivos `.java`:
   ```bash
   javac *.java
   ```
3. Ejecutar la clase principal:
   ```bash
   java FabricaLicores
   ```
