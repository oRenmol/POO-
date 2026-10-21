public class Torre extends Pieza{
    public Torre(String color, String comportamiento, String movimiento, String velocidad) {
        super(color, comportamiento, movimiento, velocidad);
    }

    public Torre() {
    }
    
    @Override 
    public void mover(){
        System.out.println("Homérica");
    }
}
