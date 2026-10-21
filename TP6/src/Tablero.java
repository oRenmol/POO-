public class Tablero{
    private Casillero[][] untablero;

    //Construcctor sin parametros
    public Tablero() {
        untablero = new Casillero[8][8];
    }

    //Getter y Setter 
    public Casillero[][] getUntablero() {
        return untablero;
    }

    public void setUntablero(Casillero[][] untablero) {
        this.untablero = untablero;
    }

    //metodos
    public void MostrarTablero(){
        int fila, colum;
        for(fila = 0; fila < 8; fila++){
            for(colum = 0; colum < 8; colum++){     
                // Alternar los colores en blanco y negro
                if ((fila + colum) % 2 == 0) {
                    System.out.print("[B] ");  // Casillero blanco
                } else {
                    System.out.print("[N] ");  // Casillero negro
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    
}
