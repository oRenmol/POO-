public class Casillero {
    private String color;
    private String coordenada;

    public String getColor() { // Get
        return color;
    }
    public void setColor(String color) { // Set
        this.color = color;
    }
    public String getCoordenada() {
        return coordenada;
    }
    public void setCoordenada(String coordenada) {
        this.coordenada = coordenada;
    }
    public Casillero(String color, String coordenada) {
        this.color = color;
        this.coordenada = coordenada;
    }
        
    public Casillero(){
    }
}
