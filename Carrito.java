public class Carrito {
    public static final int CAPACIDAD = 5;
    private static final int UMBRAL_DESCUENTO = 50;  // descuento si cajas > 50
    private static final double DESCUENTO = 0.10;
    private static final double IMPUESTO = 0.20;     // sobre el valor ya descontado

    private ItemCarrito[] items = new ItemCarrito[CAPACIDAD];
    private int cantidad = 0;

    public boolean estaVacio() {
        return cantidad == 0;
    }

    // Cajas de un licor que ya estan en el carrito
    public int cajasDe(Licor licor) {
        int total = 0;
        for (int i = 0; i < cantidad; i++) {
            if (items[i].getLicor() == licor) {
                total += items[i].getCajas();
            }
        }
        return total;
    }

    public int totalCajas() {
        int total = 0;
        for (int i = 0; i < cantidad; i++) {
            total += items[i].getCajas();
        }
        return total;
    }

    // Devuelve null si se agrego bien, o el mensaje de error si no.
    public String agregar(Licor licor, int cajas) {
        if (cajas <= 0) {
            return "Cantidad invalida: debe ser mayor a cero.";
        }
        if (cantidad == CAPACIDAD) {
            return "Carrito lleno: maximo " + CAPACIDAD + " lineas.";
        }
        int disponible = licor.getStock() - cajasDe(licor);
        if (cajas > disponible) {
            return "Stock insuficiente. Disponible para agregar: " + disponible + " cajas.";
        }
        items[cantidad] = new ItemCarrito(licor, cajas);
        cantidad++;
        return null;
    }

    public void mostrar() {
        if (estaVacio()) {
            System.out.println("El carrito esta vacio.");
            return;
        }
        System.out.println("\n--- CARRITO ---");
        for (int i = 0; i < cantidad; i++) {
            System.out.printf("%d. %-12s %3d cajas  $%,.0f%n", i + 1,
                    items[i].getLicor().getNombre(), items[i].getCajas(), items[i].getSubtotal());
        }
        System.out.println("Total de cajas: " + totalCajas());
    }

    // Calcula la factura, descuenta el stock y deja el carrito vacio.
    public void facturar() {
        double subtotal = 0;
        for (int i = 0; i < cantidad; i++) {
            subtotal += items[i].getSubtotal();
        }
        double descuento = (totalCajas() > UMBRAL_DESCUENTO) ? subtotal * DESCUENTO : 0;
        double baseGravable = subtotal - descuento;
        double impuesto = baseGravable * IMPUESTO;
        double total = baseGravable + impuesto;

        for (int i = 0; i < cantidad; i++) {
            items[i].getLicor().descontarStock(items[i].getCajas());
        }

        System.out.println("\n========== FACTURA ==========");
        for (int i = 0; i < cantidad; i++) {
            System.out.printf("%-12s %3d cajas  $%,.0f%n",
                    items[i].getLicor().getNombre(), items[i].getCajas(), items[i].getSubtotal());
        }
        System.out.printf("Subtotal:  $%,.2f%n", subtotal);
        System.out.printf("Descuento: $%,.2f%n", descuento);
        System.out.printf("Impuesto (20%%): $%,.2f%n", impuesto);
        System.out.printf("TOTAL:     $%,.2f%n", total);
        System.out.println("=============================");

        for (int i = 0; i < cantidad; i++) {
            items[i] = null;
        }
        cantidad = 0;
    }
}

