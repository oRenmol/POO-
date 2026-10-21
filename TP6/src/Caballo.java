public class Caballo extends Pieza{
    public Caballo(String color, String comportamiento, String movimiento, String velocidad) {
        super(color, comportamiento, movimiento, velocidad);
    }

    public Caballo() {
    }
    
    @Override 
    public void mover(){
        System.out.println("Ligero");
    }
}
