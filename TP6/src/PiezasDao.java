import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PiezasDao {
    
    public int insertarPiezas(Pieza[] piezas) {
        String sql = "INSERT INTO pieza "
            + "(Descripcion, idColor, idTipoPieza, Movimiento, Comportamiento, Velocidad, Fecha_Creacion) "
            + "SELECT ?, c.idColor, t.idTipoPieza, ?, ?, ?, CURDATE() "
            + "FROM color c, tipopieza t "
            + "WHERE c.Descripcion = ? AND t.Descripciondepieza = ?";

        int insertadas = 0;

        try (Connection con = new AccesoDatos().getConexion()) {
            if (con == null) {
                return 0;
            }
            con.setAutoCommit(false); // todo se guarda junto o no se guarda nada

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                for (Pieza p : piezas) {
                    if (p == null) continue;

                    String tipo = p.getClass().getSimpleName();
                    ps.setString(1, tipo + " " + p.getColor());
                    ps.setString(2, p.getMovimiento());
                    ps.setString(3, p.getComportamiento());
                    ps.setString(4, p.getVelocidad());
                    ps.setString(5, p.getColor());
                    ps.setString(6, tipo);
                    insertadas += ps.executeUpdate();
                }
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Error al insertar, se deshicieron los cambios: " + e.getMessage());
                return 0;
            }
        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        }
        return insertadas;
    }

    public void listarTodo() {
        String sql = "SELECT p.idPieza, p.Descripcion, c.Descripcion AS color, "
            + "t.Descripciondepieza AS tipo, p.Posicion, p.Movimiento, "
            + "p.Comportamiento, p.Velocidad, p.Fecha_Creacion "
            + "FROM pieza p "
            + "JOIN color c ON p.idColor = c.idColor "
            + "JOIN tipopieza t ON p.idTipoPieza = t.idTipoPieza "
            + "ORDER BY p.idPieza";

        try (Connection con = new AccesoDatos().getConexion()) {
            if (con == null) {
                return;
            }
            try (Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {

                System.out.println("=== PIEZAS ===");
                while (rs.next()) {
                    System.out.println(
                        rs.getInt("idPieza") + " | "
                        + rs.getString("Descripcion") + " | "
                        + rs.getString("color") + " | "
                        + rs.getString("tipo") + " | "
                        + rs.getString("Posicion") + " | "
                        + rs.getString("Movimiento") + " | "
                        + rs.getString("Comportamiento") + " | "
                        + rs.getString("Velocidad") + " | "
                        + rs.getDate("Fecha_Creacion"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }
    }
}