public class Alfil extends Pieza{
    public Alfil(String color, String comportamiento, String movimiento, String velocidad) {
        super(color, comportamiento, movimiento, velocidad);
    }

    public Alfil() {
    }

    @Override 
    public void mover(){
        System.out.println("Oblicou");
    }

    

}
