import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GeneradorCodigoMediateca {

    // contadores internos
    private Connection conn = ConexionDB.getConexion();
    private static String sql = "SELECT codigo FROM material WHERE tipo = ? ORDER BY fecha DESC LIMIT 1";

    public Integer obtenerCodigo(String tipo) {
        try (PreparedStatement obtenerUltimoCodigo = conn.prepareStatement(sql)) {
            obtenerUltimoCodigo.setString(1, tipo);
            try (ResultSet res = obtenerUltimoCodigo.executeQuery()) {
                if (res.next()) {
                    String codigoCompleto = res.getString("codigo");
                    Integer parteNumerica = Integer.parseInt(codigoCompleto.substring(3));

                    return parteNumerica;
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String nuevoCodigo(String tipo) {
        String prefijo;
        Integer numero;
        Integer ultimoReg = this.obtenerCodigo(tipo);
        switch (tipo.toUpperCase()) {
            case "LIBRO":
                prefijo = "LIB";
                numero = ultimoReg + 1;
                break;
            case "REVISTA":
                prefijo = "REV";
                numero = ultimoReg + 1;
                break;
            case "CD":
                prefijo = "CDA";
                numero = ultimoReg + 1;
                break;
            case "DVD":
                prefijo = "DVD";
                numero = ultimoReg + 1;
                break;
            default:
                throw new IllegalArgumentException("Tipo no válido: " + tipo);
        }

        String numeroFormateado = String.format("%05d", numero);
        return prefijo + numeroFormateado;
    }
}
