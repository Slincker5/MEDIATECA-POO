
public class Main {
    public static void main(String[] args) {
        LibroDAO dao = new LibroDAO();

        // Obtener el libro existente
        Libro libro = dao.obtenerLibro("LIB00001");

        // Modificar sus valores usando mis setters
        libro.setTitulo("Programación Orientada a Objetos en Java");
        libro.setAutor("Douglas De León");
        libro.setEditorial("Editorial Multimarcas");
        libro.setPaginas(520);
        libro.setUnidades(10);
        libro.setLanzamiento(2025);

        // Guardar los cambios en la base de datos
        dao.editarLibro(libro);

        // Comprobar resultado
        Libro actualizado = dao.obtenerLibro("LIB00001");
        System.out.println(actualizado.getTitulo());
    }

}