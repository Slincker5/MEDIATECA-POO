public class Main {
    public static void main(String[] args) {
        CdDAO dao = new CdDAO();
        GeneradorCodigoMediateca codigo = new GeneradorCodigoMediateca();

        // Generar código automático
        String code = codigo.nuevoCodigo("CD");

        // 1️⃣ INSERTAR UN NUEVO CD
        Cd nuevoCd = new Cd(
            code,               // codigo
            "Grandes Éxitos",   // titulo
            "Queen",            // artista
            "Rock",             // genero
            "01:15:00",         // duracion (formato HH:mm:ss)
            10,                 // canciones
            "CD",               // tipo
            5                   // unidades
        );

        dao.ingresarCd(nuevoCd);
        System.out.println("CD insertado correctamente.");

        // 2️⃣ CONSULTAR CD (puedes crear método obtenerCd en tu DAO si quieres)
        System.out.println("\nNo tienes método obtenerCd() aún, pero puedes crear uno similar a obtenerRevista()");
    }
}
