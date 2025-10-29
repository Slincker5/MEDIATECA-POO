import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // DAOs
        LibroDAO libroDAO = new LibroDAO();
        RevistaDAO revistaDAO = new RevistaDAO();
        CdDAO cdDAO = new CdDAO();
        DvdDAO dvdDAO = new DvdDAO();

        // Generador de códigos
        GeneradorCodigoMediateca generador = new GeneradorCodigoMediateca();

        // =========================
        // 1) INSERTAR REGISTROS
        // =========================

        // ----- LIBRO -----
        String codigoLibro = generador.nuevoCodigo("LIBRO");
        Libro libro = new Libro(
            codigoLibro,
            "El Señor de los Anillos",  // titulo
            "J.R.R. Tolkien",                    // tipo
            1200,           // autor
            "Minotauro",                       // paginas
            "978-0007525546",                // editorial
            1954,           // isbn
            "LIBRO",               // lanzamiento (String en tu modelo)
            3                           // unidades
        );
        libroDAO.ingresarLibro(libro);
        System.out.println("✔ Libro insertado: " + codigoLibro);

        // ----- REVISTA -----
        String codigoRevista = generador.nuevoCodigo("REVISTA");
        Revista revista = new Revista(
            codigoRevista,
            "National Geographic - Vida Marina", // titulo
            "REVISTA",                           // tipo
            "National Geographic Society",       // editorial
            "Mensual",                           // periodicidad
            "2025-10-01",                        // fecha (String en tu modelo)
            10                                   // unidades
        );
        revistaDAO.ingresarRevista(revista);
        System.out.println("✔ Revista insertada: " + codigoRevista);

        // ----- CD -----
        String codigoCd = generador.nuevoCodigo("CD");
        Cd cd = new Cd(
            codigoCd,
            "Grandes Éxitos",    // titulo
            "Queen",             // artista
            "Rock",              // genero
            "01:15:00",          // duracion HH:mm:ss
            17,                  // canciones
            "CD",                // tipo
            5                    // unidades
        );
        cdDAO.ingresarCd(cd);
        System.out.println("✔ CD insertado: " + codigoCd);

        // ----- DVD -----
        String codigoDvd = generador.nuevoCodigo("DVD");
        Dvd dvd = new Dvd(
            codigoDvd,
            "The Matrix",                  // titulo
            "Lana & Lilly Wachowski",      // director
            "02:16:00",             // genero
            "Ciencia Ficción",                    // duracion HH:mm:ss
            "DVD",                         // tipo
            4                              // unidades
        );
        dvdDAO.ingresarDvd(dvd);
        System.out.println("✔ DVD insertado: " + codigoDvd);


        // =========================
        // 2) LISTAR DESPUÉS DE INSERTAR
        // =========================

        System.out.println("\n===== LISTA DE LIBROS =====");
        ArrayList<Libro> libros = libroDAO.obtenerLibros();
        for (Libro l : libros) {
            System.out.println(
                l.getCodigo() + " | " +
                l.getTitulo() + " | " +
                l.getAutor() + " | " +
                l.getUnidades() + " unidades"
            );
        }

        System.out.println("\n===== LISTA DE REVISTAS =====");
        ArrayList<Revista> revistas = revistaDAO.obtenerRevistas();
        for (Revista r : revistas) {
            System.out.println(
                r.getCodigo() + " | " +
                r.getTitulo() + " | " +
                r.getEditorial() + " | " +
                r.getPeriodicidad() + " | " +
                r.getUnidades() + " unidades"
            );
        }

        System.out.println("\n===== LISTA DE CDs =====");
        ArrayList<Cd> cds = cdDAO.obtenerCds();
        for (Cd c : cds) {
            System.out.println(
                c.getCodigo() + " | " +
                c.getTitulo() + " | " +
                c.getArtista() + " | " +
                c.getGenero() + " | " +
                c.getCanciones() + " canciones | " +
                c.getUnidades() + " unidades"
            );
        }

        System.out.println("\n===== LISTA DE DVDs =====");
        ArrayList<Dvd> dvds = dvdDAO.obtenerDvds();
        for (Dvd d : dvds) {
            System.out.println(
                d.getCodigo() + " | " +
                d.getTitulo() + " | " +
                d.getDirector() + " | " +
                d.getGenero() + " | " +
                d.getUnidades() + " unidades"
            );
        }


        // =========================
        // 3) ELIMINAR (PRUEBA DE DELETE)
        // =========================
        // IMPORTANTE:
        // Esto llama tus métodos eliminarX() como los tengas AHORITA,
        // aunque sean void. Si todavía no los tienes, comenta esta sección.

        System.out.println("\n===== ELIMINANDO REGISTROS DE PRUEBA =====");
        libroDAO.eliminarLibro(codigoLibro);
        revistaDAO.eliminarRevista(codigoRevista);
        cdDAO.eliminarCd(codigoCd);
        dvdDAO.eliminarDvd(codigoDvd);
        System.out.println("Se intentó eliminar libro, revista, cd y dvd insertados.");


        // =========================
        // 4) LISTAR DESPUÉS DE BORRAR
        // =========================

        System.out.println("\n===== LISTA DE LIBROS (DESPUÉS DELETE) =====");
        ArrayList<Libro> librosRestantes = libroDAO.obtenerLibros();
        for (Libro l : librosRestantes) {
            System.out.println(l.getCodigo() + " | " + l.getTitulo());
        }

        System.out.println("\n===== LISTA DE REVISTAS (DESPUÉS DELETE) =====");
        ArrayList<Revista> revistasRestantes = revistaDAO.obtenerRevistas();
        for (Revista r : revistasRestantes) {
            System.out.println(r.getCodigo() + " | " + r.getTitulo());
        }

        System.out.println("\n===== LISTA DE CDs (DESPUÉS DELETE) =====");
        ArrayList<Cd> cdsRestantes = cdDAO.obtenerCds();
        for (Cd c2 : cdsRestantes) {
            System.out.println(c2.getCodigo() + " | " + c2.getTitulo());
        }

        System.out.println("\n===== LISTA DE DVDs (DESPUÉS DELETE) =====");
        ArrayList<Dvd> dvdsRestantes = dvdDAO.obtenerDvds();
        for (Dvd d2 : dvdsRestantes) {
            System.out.println(d2.getCodigo() + " | " + d2.getTitulo());
        }

        System.out.println("\n✅ PRUEBA CRUD COMPLETA");
    }
}
