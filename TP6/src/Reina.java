public class Reina extends Pieza{
    public Reina(String color, String comportamiento, String movimiento, String velocidad) {
        super(color, comportamiento, movimiento, velocidad);
    }


    public Reina() {
    }

    @Override 
    public void mover(){
        System.out.println("Armada");
    }
}
