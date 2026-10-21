public class Rey extends Pieza{
    public Rey(String color, String comportamiento, String movimiento, String velocidad) {
        super(color, comportamiento, movimiento, velocidad);
    }


    public Rey() {
    }

    @Override 
    public void mover(){
        System.out.println("Postrero");
    }

}
