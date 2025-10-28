import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LibroDAO {
    // inicializando la conexion a la db
    private Connection conn = ConexionDB.getConexion();
    // consultas para insert de un nuevo libro
    private String sqlInsertMaterial = "INSERT INTO material (codigo, titulo, tipo, unidades) VALUES (?, ?, ?, ?)";
    private String sqlInsertMateriaEscrito = "INSERT INTO materialescrito (codigo) VALUES (?)";
    private String sqlInsertLibro = "INSERT INTO libro (codigo, autor, paginas, editorial, isbn, lanzamiento, unidades) VALUES (?, ?, ?, ?, ?, ?, ?)";
    // consulta para obtener un libro en especifico
    private String sqlObtenerLibro = "SELECT m.codigo, m.titulo, m.tipo, m.unidades, l.autor, l.paginas, l.editorial, l.isbn, l.lanzamiento FROM material AS m INNER JOIN materialescrito AS me ON me.codigo = m.codigo INNER JOIN libro AS l ON l.codigo = m.codigo WHERE m.codigo = ?";
    // consulta para obtener los libros
    private String sqlObtenerLibros = "SELECT m.codigo, m.titulo, m.tipo, m.unidades, m.fecha l.autor, l.paginas, l.editorial, l.isbn, l.lanzamiento FROM material AS m INNER JOIN materialescrito AS me ON me.codigo = m.codigo INNER JOIN libro AS l ON l.codigo = m.codigo ORDER BY fecha DESC";
    // consulta editar libro
    private String sqlEditarMaterial = "UPDATE material SET titulo = ?, unidades = ? WHERE codigo = ?";
    private String sqlEditarLibro = "UPDATE libro SET autor = ?, paginas = ?, editorial = ?, isbn = ?, lanzamiento = ? WHERE codigo = ?";
    // consulta para eliminar libro
    private String sqlDeleteLibro = "DELETE FROM material WHERE codigo = ?";

    public void ingresarLibro(Libro libro) {
        try (
                PreparedStatement insertMaterial = conn.prepareStatement(this.sqlInsertMaterial);
                PreparedStatement insertMaterialEscrito = conn.prepareStatement(this.sqlInsertMateriaEscrito);
                PreparedStatement insertLibro = conn.prepareStatement(this.sqlInsertLibro);) {
            insertMaterial.setString(1, libro.getCodigo());
            insertMaterial.setString(2, libro.getAutor());
            insertMaterial.setString(3, libro.getTipo());
            insertMaterial.setInt(4, libro.getUnidades());
            insertMaterialEscrito.setString(1, libro.getCodigo());
            insertLibro.setString(1, libro.getCodigo());
            insertLibro.setString(2, libro.getAutor());
            insertLibro.setInt(3, libro.getPaginas());
            insertLibro.setString(4, libro.getEditorial());
            insertLibro.setString(5, libro.getIsbn());
            insertLibro.setInt(6, libro.getLanzamiento());
            insertLibro.setInt(7, libro.getUnidades());

            insertMaterial.executeUpdate();
            insertMaterialEscrito.executeUpdate();
            insertLibro.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Libro obtenerLibro(String codigo) {
        Libro libro = null;
        try (PreparedStatement obtenerLibro = conn.prepareStatement(sqlObtenerLibro)) {
            obtenerLibro.setString(1, codigo);
            try (ResultSet res = obtenerLibro.executeQuery()) {
                if (res.next()) {
                    libro = new Libro(res.getString("codigo"), res.getString("titulo"), res.getString("autor"),
                            res.getInt("paginas"), res.getString("editorial"), res.getString("isbn"),
                            res.getInt("lanzamiento"), res.getString("tipo"), res.getInt("unidades"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return libro;
    }

    public ArrayList<Libro> obtenerLibros() {
        ArrayList<Libro> libro = new ArrayList<Libro>();
        try (PreparedStatement obtenerLibro = conn.prepareStatement(sqlObtenerLibros)) {
            try (ResultSet res = obtenerLibro.executeQuery()) {
                while (res.next()) {
                    libro.add(new Libro(res.getString("codigo"), res.getString("titulo"), res.getString("autor"),
                            res.getInt("paginas"), res.getString("editorial"), res.getString("isbn"),
                            res.getInt("lanzamiento"), res.getString("tipo"), res.getInt("unidades")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return libro;
    }

    public void editarLibro(Libro libro) {
        try (PreparedStatement editarMaterial = conn.prepareStatement(sqlEditarMaterial);
                PreparedStatement editarLibro = conn.prepareStatement(sqlEditarLibro);) {
                    editarMaterial.setString(1, libro.getTitulo());
                    editarMaterial.setInt(2, libro.getUnidades());
                    editarMaterial.setString(3, libro.getCodigo());
                    editarLibro.setString(1, libro.getAutor());
                    editarLibro.setInt(2, libro.getPaginas());
                    editarLibro.setString(3, libro.getEditorial());
                    editarLibro.setString(4, libro.getIsbn());
                    editarLibro.setInt(5, libro.getLanzamiento());
                    editarLibro.setString(6, libro.getCodigo());

                    editarMaterial.executeUpdate();
                    editarLibro.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void eliminarLibro(String codigo) {
        try (PreparedStatement deleteLibro = conn.prepareStatement(this.sqlDeleteLibro)) {
            deleteLibro.setString(1, codigo);
            deleteLibro.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}