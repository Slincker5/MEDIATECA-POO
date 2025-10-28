public class Main {
    public static void main(String[] args) {
        // Instancia del DAO y del generador de código
        RevistaDAO dao = new RevistaDAO();
        GeneradorCodigoMediateca codigo = new GeneradorCodigoMediateca();

        // Generar código automático
        String code = codigo.nuevoCodigo("REVISTA");

        // 1. INSERTAR UNA REVISTA
        Revista nuevaRevista = new Revista(
            code,               // codigo
            "Ciencia Hoy",      // titulo
            "REVISTA",          // tipo
            "Editorial Alfa",   // editorial
            "Mensual",          // periodicidad
            "2025-10-27",       // fecha (formato correcto)
            5                   // unidades
        );

        dao.ingresarRevista(nuevaRevista);
        System.out.println("Revista insertada correctamente.");

        // 2. OBTENER UNA REVISTA ESPECÍFICA
        Revista revistaBuscada = dao.obtenerRevista(code);
        if (revistaBuscada != null) {
            System.out.println("Revista encontrada:");
            System.out.println(revistaBuscada);
        } else {
            System.out.println("No se encontró la revista con ese código.");
        }

        // 3. OBTENER TODAS LAS REVISTAS
        System.out.println("\nListado de todas las revistas:");
        for (Revista r : dao.obtenerRevistas()) {
            System.out.println(r);
        }

    }
}
