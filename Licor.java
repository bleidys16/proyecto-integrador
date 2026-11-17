public class Licor {
    private String nombre;
    private double precio;
    private int stock;

    public Licor(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }

    // Resta cajas del stock. Solo se llama al facturar.
    public void descontarStock(int cajas) {
        stock -= cajas;
    }

    // Suma cajas al stock. Devuelve false si la cantidad no es valida.
    public boolean reabastecer(int cajas) {
        if (cajas <= 0) {
            return false;
        }
        stock += cajas;
        return true;
    }
}

