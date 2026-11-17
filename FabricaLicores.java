import java.util.Scanner;

public class FabricaLicores {

    // Datos simulados: arreglo de objetos Licor
    static Licor[] inventario = {
        new Licor("Aguardiente", 180000, 120),
        new Licor("Ron", 220000, 80),
        new Licor("Vodka", 200000, 60),
        new Licor("Whisky", 260000, 40)
    };

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int rol;
        do {
            System.out.println("\n=== FABRICA DE LICORES ===");
            System.out.println("1. Modulo Cliente");
            System.out.println("2. Modulo Administrador");
            System.out.println("3. Salir");
            rol = leerEntero("Seleccione una opcion: ");

            switch (rol) {
                case 1:
                    moduloCliente();
                    break;
                case 2:
                    moduloAdmin();
                    break;
                case 3:
                    System.out.println("Hasta pronto.");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (rol != 3);
    }

    // Repite la lectura mientras el usuario no escriba un entero.
    static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Dato invalido, escriba un numero entero: ");
        }
        int valor = sc.nextInt();
        sc.nextLine();
        return valor;
    }

    // ---------- Modulo Cliente ----------
    static void moduloCliente() {
        int edad = leerEntero("Ingrese su edad: ");
        if (edad < 0) {
            System.out.println("Edad invalida. Acceso denegado.");
            return;
        }
        if (edad < 18) {
            System.out.println("Acceso denegado: solo se permite la venta a mayores de edad (18 o mas).");
            return;
        }

        Carrito carrito = new Carrito();
        int opcion;
        do {
            System.out.println("\n--- MENU CLIENTE ---");
            System.out.println("1. Ver catalogo");
            System.out.println("2. Agregar al carrito");
            System.out.println("3. Ver carrito");
            System.out.println("4. Facturar carrito");
            System.out.println("5. Regresar");
            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    mostrarCatalogo();
                    break;
                case 2:
                    agregarAlCarrito(carrito);
                    break;
                case 3:
                    carrito.mostrar();
                    break;
                case 4:
                    if (carrito.estaVacio()) {
                        System.out.println("No hay nada que facturar: el carrito esta vacio.");
                    } else {
                        carrito.facturar();
                    }
                    break;
                case 5:
                    System.out.println("Regresando al menu principal.");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 5);
    }

    static void mostrarCatalogo() {
        System.out.println("\n#  Licor          Precio/caja    Stock (cajas)");
        for (int i = 0; i < inventario.length; i++) {
            System.out.printf("%d  %-13s  $%,12.0f  %d%n", i + 1,
                    inventario[i].getNombre(), inventario[i].getPrecio(), inventario[i].getStock());
        }
    }

    static void agregarAlCarrito(Carrito carrito) {
        mostrarCatalogo();
        int producto = leerEntero("Seleccione el licor (1-" + inventario.length + "): ");
        if (producto < 1 || producto > inventario.length) {
            System.out.println("Producto invalido.");
            return;
        }
        int cajas = leerEntero("Cantidad de cajas: ");
        String error = carrito.agregar(inventario[producto - 1], cajas);
        if (error == null) {
            System.out.println("Agregado al carrito.");
        } else {
            System.out.println(error);
        }
    }

    // ---------- Modulo Administrador ----------
    static void moduloAdmin() {
        int opcion;
        do {
            System.out.println("\n--- MENU ADMINISTRADOR ---");
            System.out.println("1. Consultar inventario");
            System.out.println("2. Reabastecer bodega");
            System.out.println("3. Regresar");
            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    consultarInventario();
                    break;
                case 2:
                    reabastecer();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 3);
    }

    static void consultarInventario() {
        System.out.println("\nInventario en bodega:");
        for (int i = 0; i < inventario.length; i++) {
            System.out.println((i + 1) + ". " + inventario[i].getNombre()
                    + ": " + inventario[i].getStock() + " cajas");
        }
    }

    static void reabastecer() {
        consultarInventario();
        int producto = leerEntero("Seleccione el licor (1-" + inventario.length + "): ");
        if (producto < 1 || producto > inventario.length) {
            System.out.println("Producto invalido.");
            return;
        }
        int cajas = leerEntero("Cajas recibidas: ");
        if (inventario[producto - 1].reabastecer(cajas)) {
            System.out.println("Reabastecimiento registrado. Nuevo stock de "
                    + inventario[producto - 1].getNombre() + ": "
                    + inventario[producto - 1].getStock() + " cajas.");
        } else {
            System.out.println("Datos invalidos: la cantidad debe ser mayor a cero.");
        }
    }
}

