package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import clasespoo.Dvd;

public class DvdDAO {
    // inicializando la conexion a la db
    private Connection conn = ConexionDB.getConexion();
    // consultas para insert de una nueva revista
    private String sqlInsertMaterial = "INSERT INTO material (codigo, titulo, tipo, unidades) VALUES (?, ?, ?, ?)";
    private String sqlInsertMateriaAudioVisual = "INSERT INTO materialaudiovisual (codigo) VALUES (?)";
    private String sqlInsertDvd = "INSERT INTO dvd (codigo, director, duracion, genero) VALUES (?, ?, ?, ?)";
    // consulta para obtener un revista en especifico
    private String sqlObtenerDvd = "SELECT m.codigo, m.titulo, m.tipo, m.unidades, d.director, d.duracion, d.genero, m.fecha FROM material AS m INNER JOIN materialaudiovisual AS mau ON mau.codigo = m.codigo INNER JOIN dvd AS d ON d.codigo = m.codigo WHERE m.codigo = ?";
    // consulta para obtener las revistas
    private String sqlObtenerDvds = "SELECT m.codigo, m.titulo, m.tipo, m.unidades, m.fecha, d.director, d.duracion, d.genero FROM material AS m INNER JOIN materialaudiovisual AS mau ON mau.codigo = m.codigo INNER JOIN dvd AS d ON d.codigo = m.codigo ORDER BY m.fecha DESC";
    // consulta editar revista
    private String sqlEditarMaterial = "UPDATE material SET titulo = ?, unidades = ? WHERE codigo = ?";
    private String sqlEditarDvd = "UPDATE dvd SET director = ?, duracion = ?, genero = ? WHERE codigo = ?";
    // consulta para eliminar revista
    private String sqlDeleteDvd = "DELETE FROM material WHERE codigo = ?";

    public void ingresarDvd(Dvd dvd) {
        try (
                PreparedStatement insertMaterial = conn.prepareStatement(this.sqlInsertMaterial);
                PreparedStatement insertMaterialAudioVisual = conn.prepareStatement(this.sqlInsertMateriaAudioVisual);
                PreparedStatement insertDvd = conn.prepareStatement(this.sqlInsertDvd);) {
            insertMaterial.setString(1, dvd.getCodigo());
            insertMaterial.setString(2, dvd.getTitulo());
            insertMaterial.setString(3, dvd.getTipo());
            insertMaterial.setInt(4, dvd.getUnidades());
            insertMaterialAudioVisual.setString(1, dvd.getCodigo());
            insertDvd.setString(1, dvd.getCodigo());
            insertDvd.setString(2, dvd.getDirector());
            insertDvd.setTime(3, dvd.getDuracion());
            insertDvd.setString(4, dvd.getGenero());

            insertMaterial.executeUpdate();
            insertMaterialAudioVisual.executeUpdate();
            insertDvd.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dvd obtenerDvd(String codigo) {
        Dvd dvd = null;
        try (PreparedStatement obtenerDvd = conn.prepareStatement(this.sqlObtenerDvd)) {
            obtenerDvd.setString(1, codigo);
            try (ResultSet res = obtenerDvd.executeQuery()) {
                if (res.next()) {
                    dvd = new Dvd(
                            res.getString("codigo"),
                            res.getString("titulo"),
                            res.getString("director"),
                            res.getTime("duracion").toString(),
                            res.getString("genero"),
                            res.getString("tipo"),
                            res.getInt("unidades"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dvd;
    }

    public ArrayList<Dvd> obtenerDvds() {
        ArrayList<Dvd> dvds = new ArrayList<Dvd>();
        try (PreparedStatement obtenerDvds = conn.prepareStatement(this.sqlObtenerDvds)) {
            try (ResultSet res = obtenerDvds.executeQuery()) {
                while (res.next()) {
                    dvds.add(new Dvd(
                            res.getString("codigo"),
                            res.getString("titulo"),
                            res.getString("director"),
                            res.getTime("duracion").toString(),
                            res.getString("genero"),
                            res.getString("tipo"),
                            res.getInt("unidades"))
                            );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dvds;
    }

    public void editarDvd(Dvd dvd) {
        try (PreparedStatement editarMaterial = conn.prepareStatement(this.sqlEditarMaterial);
                PreparedStatement editarDvd = conn.prepareStatement(this.sqlEditarDvd);) {
            editarMaterial.setString(1, dvd.getTitulo());
            editarMaterial.setInt(2, dvd.getUnidades());
            editarMaterial.setString(3, dvd.getCodigo());
            editarDvd.setString(1, dvd.getDirector());
            editarDvd.setTime(2, dvd.getDuracion());
            editarDvd.setString(3, dvd.getGenero());

            editarMaterial.executeUpdate();
            editarDvd.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void eliminarDvd(String codigo) {
        try (PreparedStatement deleteDvd = conn.prepareStatement(this.sqlDeleteDvd)) {
            deleteDvd.setString(1, codigo);
            deleteDvd.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}