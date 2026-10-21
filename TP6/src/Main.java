import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {    
    public static void main(String[] args){
        Caballo []cabArrN = new Caballo[2];
        Caballo []cabArrB = new Caballo[2];
        Caballo cabN = new Caballo();
        Caballo cabB = new Caballo();
        crearPiezas(cabArrN, cabArrB, null, "Ligero", cabN, cabB);
        
        Alfil []alfArrN = new Alfil[2];
        Alfil []alfArrB = new Alfil[2];
        Alfil alfN = new Alfil();
        Alfil alfB = new Alfil();
        crearPiezas(alfArrN, alfArrB, "Sesgo", "Oblicuo", alfN, alfB);
    
        Torre []torArrN = new Torre[2];
        Torre []torArrB = new Torre[2];
        Torre torN = new Torre();
        Torre torB = new Torre();
        crearPiezas(torArrN, torArrB, "Directa", "Homerica", torN, torB);

        Peon []peonArrN = new Peon[8];
        Peon []peonArrB = new Peon[8];
        Peon peonN = new Peon();
        Peon peonB = new Peon();
        crearPiezas(peonArrN, peonArrB,"Ladino", "Agresor", peonN, peonB);

        Rey []reyArrN = new Rey[1];
        Rey []reyArrB = new Rey[1];
        Rey reyN = new Rey();
        Rey reyB = new Rey();
        crearPiezas(reyArrN, reyArrB, "Tenue", "Postrero", reyN, reyB);
    
        Reina []reinaArrN = new  Reina[1];
        Reina []reinaArrB = new  Reina[1];
        Reina reinaN = new Reina();
        Reina reinaB = new Reina();
        crearPiezas(reinaArrN, reinaArrB,"Encarnizada","Armada", reinaN, reinaB);

        /* Impresiones */
        System.out.println("|- - - - Peonones Blancos & Negros - - - - |");
        Peon.imprimirPiezas(peonArrB);
        Peon.imprimirPiezas(peonArrN);

        System.out.println("|- - - - - Torre Blancos & Negros - - - - - |");
        Torre.imprimirPiezas(torArrB);
        Torre.imprimirPiezas(torArrN);

        System.out.println("|- - - - Caballo Blancos & Negros - - - - |");
        Caballo.imprimirPiezas(cabArrB);
        Caballo.imprimirPiezas(cabArrN);

        System.out.println("|- - - - - Alfil Blancos & Negros - - - - - |");
        Caballo.imprimirPiezas(alfArrB);
        Caballo.imprimirPiezas(alfArrN);

        System.out.println("|- - - - - Rey Blancos & Negros - - - - -|");
        Rey.imprimirPiezas(reyArrB);
        Rey.imprimirPiezas(reyArrN);
        
        System.out.println("|- - - - Reina Blancos & Negros - - - - |");
        Reina.imprimirPiezas(reinaArrB);
        Reina.imprimirPiezas(reinaArrN);

        Tablero tab = new Tablero();
        System.out.println("\n|- - - - Tablero del Ajedrez - - - -|");
        tab.MostrarTablero();

        System.out.println("|- - - - Transferencia de piezas a la BD - - - -|");
        procesarPieza(reinaArrN);
        procesarPieza(reyArrN);
        procesarPieza(torArrN);
        procesarPieza(alfArrN);
        procesarPieza(cabArrN);
        procesarPieza(peonArrN);
        procesarPieza(reinaArrB);
        procesarPieza(reyArrB);
        procesarPieza(torArrB);
        procesarPieza(alfArrB);
        procesarPieza(cabArrB);
        procesarPieza(peonArrB);
        System.out.println("\n|- - - - Piezas Del Ajedrez en SQL - - - -|\n");
        mostrarPiezasBD();
    }

    // metodos
    public static void mostrarPiezasBD()
	{
		
		//Objeto para ejecutar la consulta en la base de datos
    	AccesoDatos accesoBD = null; //Driver Mannager
    	Connection con = null; //Establece la conexion con la base de dato
    	Statement sentencia =null; //Query
		ResultSet rs=null; //Resultado de la Query si devuelve algo 
		
		// Se crea la consulta usando lenguaje SQL Estandar y almacenando en la variable query
		String query = "select idPieza, \n" + //
                        "TP.DescripciondePieza as TipoPieza , \n" + //
                        "C.Descripcion as Color, \n" + //
                        "P.Descripcion as ResumenPieza, \n" + //
                        "Posicion, \n" + //
                        "Movimiento, \n" + //
                        "Comportamiento, \n" + //
                        "Velocidad \n" + //
                        "from Pieza P inner join tipopieza TP on P.idtipoPieza = TP.idtipoPieza\n" + //
                        "inner join Color C on P.idColor = C.idColor";
        //En este bloque se ejecutan las sentencias necesarias para acceder a la BD y obtener la informacion
        try {
        	// Instancio un objeto de acceso a datos
        	accesoBD = new AccesoDatos();
			
            //Obtener la conexion para poder generar la sentencia de consulta
            con = accesoBD.getConexion();
            sentencia = con.createStatement();
            //Ejecuta la consulta y almacena el resultado en rs

            rs  = sentencia.executeQuery(query);
            // Procesa el resultSet y muestra la informacion obtenida desde la BD
            while(rs.next()){
            	
                  int idPieza = rs.getInt("IDPIEZA");
            		System.out.println("Pieza Nro: " + String.valueOf(idPieza)
                    + ", Tipo: " + rs.getString("TipoPieza") + ", Color: " + rs.getString("Color")
                    + ", Movimiento: " + rs.getString("MOVIMIENTO")
                    + ", Comportamiento: " + rs.getString("COMPORTAMIENTO") + ", Velocidad: " + rs.getString("VELOCIDAD"));   	            			
			}    
             
        } catch (SQLException e) {
            System.err.println("Error al CARGAR DATOS");
        }
       finally
        {
        	try
        	{
        		// Cierra el ResultSet
        		if (rs!= null) rs.close();
        		// Cierra la sentencia
        		if (sentencia!= null) sentencia.close();
        		// Cierra la conexion
        		if (con != null) con.close();
        		
        	}catch (SQLException e)
        	{
        		System.err.println("Error al cerrar conexion");
        	}
        }
	}

// tipopieza VALUES (1,'Reina'),(2,'Rey'),(3,'Torre'),(4,'Alfil'),(5,'Caballo'),(6,'Peon');
    public static void crearPiezas(Pieza arr[], Pieza arr2[], String mov, String com, Pieza obj1, Pieza obj2){
        String blanco = "Blanco", negro = "Negro";        
        for(int i = 0; i < arr2.length; i++){
            obj2.setColor(blanco);
            arr2[i]=obj2;
            obj2.setComportamiento(com);
            obj2.setMovimiento(mov);
        }
        for(int i = 0; i < arr.length; i++){
            obj1.setColor(negro);
            arr[i]=obj1;
            obj1.setComportamiento(com);
            obj1.setMovimiento(mov);
        }
    }
    public static void insertarPieza(Pieza pieza) {
        String sql = "INSERT INTO PIEZA (IDTIPOPIEZA, DESCRIPCION, MOVIMIENTO, COMPORTAMIENTO, IDCOLOR, VELOCIDAD) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conexion = new AccesoDatos().getConexion();
        PreparedStatement stmt = conexion.prepareStatement(sql)) {

        // Obtener los IDs necesarios
        int idColor = obtenerIdColor(pieza);
        int idTipoPieza = obtenerIdTipoPieza(pieza);

        // Asignar los parámetros en la consulta
        stmt.setInt(1, idTipoPieza);
        stmt.setString(2, pieza.getClass().getSimpleName()+" "+pieza.getColor());
        stmt.setString(3, pieza.getMovimiento());
        stmt.setString(4, pieza.getComportamiento());
        stmt.setInt(5, idColor);
        stmt.setString(6, pieza.getVelocidad());

        stmt.executeUpdate();
        System.out.println("Pieza insertada con éxito.");
        }   catch (SQLException e) {
            System.err.println("Error al insertar la pieza.");
            e.printStackTrace();
        }
    }

    public static void procesarPieza(Pieza arr[]){
        for (Pieza p: arr){
            insertarPieza(p);
        }
    }

    public static int obtenerIdTipoPieza(Pieza p){
    int idTipoPieza = 0;
    if(p.getClass().getSimpleName()=="Reina"){
        idTipoPieza = 1;
    } else if(p.getClass().getSimpleName()=="Rey"){
    idTipoPieza = 2;
    } else if(p.getClass().getSimpleName()=="Torre"){
        idTipoPieza = 3;
    } else if(p.getClass().getSimpleName()=="Alfil"){
        idTipoPieza = 4;
    } else if(p.getClass().getSimpleName()=="Caballo"){
        idTipoPieza = 5;
    } else if(p.getClass().getSimpleName()=="Peon"){
        idTipoPieza = 6;
    }
    return idTipoPieza;
    }
    
    public static int obtenerIdColor(Pieza p){
        int idColorPieza = 0;
        if(p.getColor() == "Negro"){
            idColorPieza = 1;
        } else if (p.getColor() == "Blanco"){
            idColorPieza = 2;
        }
        return idColorPieza;
    }
}