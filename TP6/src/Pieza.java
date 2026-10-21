public abstract class Pieza {
    private String color;
    private String comportamiento;
    private String movimiento;
    private String velocidad = "Lento";

    public abstract void mover();

    public String getColor() { // Get
        return color;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(String velocidad) {
        this.velocidad = velocidad;
    }

    public void setColor(String color) { // Set
        this.color = color;
    }
    public String getComportamiento() {
        return comportamiento;
    }
    public void setComportamiento(String comportamiento) {
        this.comportamiento = comportamiento;
    }
    public String getMovimiento() {
        return movimiento;
    }
    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public Pieza(String color, String comportamiento, String movimiento, String velocidad) {
        this.color = color;
        this.comportamiento = comportamiento;
        this.movimiento = movimiento;
        this.velocidad = velocidad;
    }

    public Pieza(){ // Constructor vacio
    }

    public static void imprimirPiezas(Pieza[] arreglo) {
        int cont = 1;
        for (Pieza p : arreglo) {
            if (p != null) {
                System.out.println(cont + ". " + p.toString());
                cont++;
            }
        }
        System.out.println("|-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-|");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + getColor() +
            "|- -| Comportamiento: " + getComportamiento() + 
            "|- -| Movimiento: " + getMovimiento() + ")";
    }
}
