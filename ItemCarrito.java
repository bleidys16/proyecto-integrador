public class ItemCarrito {
    private Licor licor;
    private int cajas;

    public ItemCarrito(Licor licor, int cajas) {
        this.licor = licor;
        this.cajas = cajas;
    }

    public Licor getLicor() { return licor; }
    public int getCajas() { return cajas; }

    public double getSubtotal() {
        return cajas * licor.getPrecio();
    }
}

