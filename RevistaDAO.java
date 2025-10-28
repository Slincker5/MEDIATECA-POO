import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RevistaDAO {
    // inicializando la conexion a la db
    private Connection conn = ConexionDB.getConexion();
    // consultas para insert de una nueva revista
    private String sqlInsertMaterial = "INSERT INTO material (codigo, titulo, tipo, unidades) VALUES (?, ?, ?, ?)";
    private String sqlInsertMateriaEscrito = "INSERT INTO materialescrito (codigo) VALUES (?)";
    private String sqlInsertRevista = "INSERT INTO revista (codigo, editorial, periodicidad, fecha) VALUES (?, ?, ?, ?)";
    // consulta para obtener un revista en especifico
    private String sqlObtenerRevista = "SELECT m.codigo, m.titulo, m.tipo, m.unidades, r.editorial, r.periodicidad, r.fecha FROM material AS m INNER JOIN materialescrito AS me ON me.codigo = m.codigo INNER JOIN revista AS r ON r.codigo = m.codigo WHERE m.codigo = ?";
    // consulta para obtener las revistas
    private String sqlObtenerRevistas = "SELECT m.codigo, m.titulo, m.tipo, m.unidades, m.fecha, r.editorial, r.periodicidad, r.fecha FROM material AS m INNER JOIN materialescrito AS me ON me.codigo = m.codigo INNER JOIN revista AS r ON r.codigo = m.codigo ORDER BY m.fecha DESC";
    // consulta editar revista
    private String sqlEditarMaterial = "UPDATE material SET titulo = ?, unidades = ? WHERE codigo = ?";
    private String sqlEditarRevista = "UPDATE revista SET editorial = ?, periodicidad = ?, fecha = ? WHERE codigo = ?";
    // consulta para eliminar revista
    private String sqlDeleteRevista = "DELETE FROM material WHERE codigo = ?";

    public void ingresarRevista(Revista revista) {
        try (
                PreparedStatement insertMaterial = conn.prepareStatement(this.sqlInsertMaterial);
                PreparedStatement insertMaterialEscrito = conn.prepareStatement(this.sqlInsertMateriaEscrito);
                PreparedStatement insertRevista = conn.prepareStatement(this.sqlInsertRevista);) {
            insertMaterial.setString(1, revista.getCodigo());
            insertMaterial.setString(2, revista.getTitulo());
            insertMaterial.setString(3, revista.getTipo());
            insertMaterial.setInt(4, revista.getUnidades());
            insertMaterialEscrito.setString(1, revista.getCodigo());
            insertRevista.setString(1, revista.getCodigo());
            insertRevista.setString(2, revista.getEditorial());
            insertRevista.setString(3, revista.getPeriodicidad());
            insertRevista.setString(4, revista.getFecha());

            insertMaterial.executeUpdate();
            insertMaterialEscrito.executeUpdate();
            insertRevista.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Revista obtenerRevista(String codigo) {
        Revista revista = null;
        try (PreparedStatement obtenerRevista = conn.prepareStatement(sqlObtenerRevista)) {
            obtenerRevista.setString(1, codigo);
            try (ResultSet res = obtenerRevista.executeQuery()) {
                if (res.next()) {
                    revista = new Revista(res.getString("codigo"), res.getString("titulo"),
                           res.getString("editorial"), res.getString("periodicidad"),
                            res.getString("fecha"), res.getString("tipo"), res.getInt("unidades"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return revista;
    }

    public ArrayList<Revista> obtenerRevistas() {
        ArrayList<Revista> revista = new ArrayList<Revista>();
        try (PreparedStatement obtenerRevista = conn.prepareStatement(sqlObtenerRevistas)) {
            try (ResultSet res = obtenerRevista.executeQuery()) {
                while (res.next()) {
                    revista.add(new Revista(res.getString("codigo"), res.getString("titulo"),
                            res.getString("editorial"), res.getString("periodicidad"),
                            res.getString("fecha"), res.getString("tipo"), res.getInt("unidades")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return revista;
    }

    public void editarRevista(Revista revista) {
        try (PreparedStatement editarMaterial = conn.prepareStatement(sqlEditarMaterial);
                PreparedStatement editarRevista = conn.prepareStatement(sqlEditarRevista);) {
                    editarMaterial.setString(1, revista.getTitulo());
                    editarMaterial.setInt(2, revista.getUnidades());
                    editarMaterial.setString(3, revista.getCodigo());
                    editarRevista.setString(1, revista.getEditorial());
                    editarRevista.setString(2, revista.getPeriodicidad());
                    editarRevista.setString(3, revista.getFecha());
                    editarRevista.setString(4, revista.getCodigo());

                    editarMaterial.executeUpdate();
                    editarRevista.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void eliminarRevista(String codigo) {
        try (PreparedStatement deleteRevista = conn.prepareStatement(this.sqlDeleteRevista)) {
            deleteRevista.setString(1, codigo);
            deleteRevista.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}