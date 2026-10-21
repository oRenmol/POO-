public class Peon extends Pieza{
    public Peon() {
    }
    public Peon(String color, String comportamiento, String movimiento, String velocidad) {
        super(color, comportamiento, movimiento, velocidad);
    }

    @Override 
    public void mover(){
        System.out.println("Agresores");
    }

}
