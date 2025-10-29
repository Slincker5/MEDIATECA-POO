import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CdDAO {
    // inicializando la conexion a la db
    private Connection conn = ConexionDB.getConexion();
    // consultas para insert de una nueva revista
    private String sqlInsertMaterial = "INSERT INTO material (codigo, titulo, tipo, unidades) VALUES (?, ?, ?, ?)";
    private String sqlInsertMateriaAudioVisual = "INSERT INTO materialaudiovisual (codigo) VALUES (?)";
    private String sqlInsertCd = "INSERT INTO cd (codigo, artista, genero, duracion, canciones) VALUES (?, ?, ?, ?, ?)";
    // consulta para obtener un revista en especifico
    private String sqlObtenerCd = "SELECT m.codigo, m.titulo, m.tipo, m.unidades, c.artista, c.genero, c.duracion, c.canciones, m.fecha FROM material AS m INNER JOIN materialaudiovisual AS mau ON mau.codigo = m.codigo INNER JOIN cd AS c ON c.codigo = m.codigo WHERE m.codigo = ?";
    // consulta para obtener las revistas
    private String sqlObtenerCds = "SELECT m.codigo, m.titulo, m.tipo, m.unidades, c.artista, c.duracion, c.genero, m.fecha FROM material AS m INNER JOIN materialescrito AS me ON me.codigo = m.codigo INNER JOIN cd AS c ON c.codigo = m.codigo ORDER BY m.fecha DESC";
    // consulta editar revista
    private String sqlEditarMaterial = "UPDATE material SET titulo = ?, unidades = ? WHERE codigo = ?";
    private String sqlEditarCd = "UPDATE cd SET artista = ?, genero = ?, duracion = ?, canciones = ? WHERE codigo = ?";
    // consulta para eliminar revista
    private String sqlDeleteCd = "DELETE FROM material WHERE codigo = ?";

    public void ingresarCd(Cd cd) {
        try (
                PreparedStatement insertMaterial = conn.prepareStatement(this.sqlInsertMaterial);
                PreparedStatement insertMaterialAudioVisual = conn.prepareStatement(this.sqlInsertMateriaAudioVisual);
                PreparedStatement insertCd = conn.prepareStatement(this.sqlInsertCd);) {
            insertMaterial.setString(1, cd.getCodigo());
            insertMaterial.setString(2, cd.getTitulo());
            insertMaterial.setString(3, cd.getTipo());
            insertMaterial.setInt(4, cd.getUnidades());
            insertMaterialAudioVisual.setString(1, cd.getCodigo());
            insertCd.setString(1, cd.getCodigo());
            insertCd.setString(2, cd.getArtista());
            insertCd.setString(3, cd.getGenero());
            insertCd.setTime(4, cd.getDuracion());
            insertCd.setInt(5, cd.getCanciones());

            insertMaterial.executeUpdate();
            insertMaterialAudioVisual.executeUpdate();
            insertCd.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cd obtenerCd(String codigo) {
        Cd cd = null;
        try (PreparedStatement obtenerCd = conn.prepareStatement(this.sqlObtenerCd)) {
            obtenerCd.setString(1, codigo);
            try (ResultSet res = obtenerCd.executeQuery()) {
                if (res.next()) {
                    cd = new Cd(
                            res.getString("codigo"),
                            res.getString("titulo"),
                            res.getString("artista"),
                            res.getString("genero"),
                            res.getTime("duracion").toString(),
                            res.getInt("canciones"),
                            res.getString("tipo"),
                            res.getInt("unidades"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cd;
    }

    public ArrayList<Cd> obtenerCds() {
        ArrayList<Cd> cds = new ArrayList<Cd>();
        try (PreparedStatement obtenerCds = conn.prepareStatement(this.sqlObtenerCds)) {
            try (ResultSet res = obtenerCds.executeQuery()) {
                while (res.next()) {
                    cds.add(new Cd(
                            res.getString("codigo"),
                            res.getString("titulo"),
                            res.getString("artista"),
                            res.getString("genero"),
                            res.getTime("duracion").toString(),
                            res.getInt("canciones"),
                            res.getString("tipo"),
                            res.getInt("unidades"))
                            );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cds;
    }

    public void editarCd(Cd cd) {
        try (PreparedStatement editarMaterial = conn.prepareStatement(this.sqlEditarMaterial);
                PreparedStatement editarCd = conn.prepareStatement(this.sqlEditarCd);) {
            editarMaterial.setString(1, cd.getTitulo());
            editarMaterial.setInt(2, cd.getUnidades());
            editarMaterial.setString(3, cd.getCodigo());
            editarCd.setString(1, cd.getArtista());
            editarCd.setString(2, cd.getGenero());
            editarCd.setTime(3, cd.getDuracion());
            editarCd.setInt(4, cd.getCanciones());

            editarMaterial.executeUpdate();
            editarCd.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void eliminarCd(String codigo) {
        try (PreparedStatement deleteCd = conn.prepareStatement(this.sqlDeleteCd)) {
            deleteCd.setString(1, codigo);
            deleteCd.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}