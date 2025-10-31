package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import clasespoo.Material;

public class Buscador {
    private Connection conn = ConexionDB.getConexion();
    private String sqlBuscador = "SELECT * FROM material WHERE titulo LIKE ? OR tipo = ?";
    private String sqlTodoMaterial =  "SELECT * FROM material ORDER BY fecha DESC LIMIT 10";

    public ArrayList<Material> buscador(String busqueda){
        ArrayList<Material> resultado = new ArrayList<Material>();

        try (PreparedStatement buscar = conn.prepareStatement(this.sqlBuscador)) {
            buscar.setString(1, "%" + busqueda + "%");
            buscar.setString(2, busqueda);
            ResultSet res =  buscar.executeQuery();

            while (res.next()) {
                resultado.add(new Material(res.getString("titulo"), res.getString("tipo"), res.getInt("unidades")));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return resultado;
    }

    public ArrayList<Material> obtenerUtimoMaterial(){
        ArrayList<Material> resultado = new ArrayList<Material>();
        try (PreparedStatement material = conn.prepareStatement(sqlTodoMaterial);) {
            ResultSet res = material.executeQuery();
            while (res.next()) {
                resultado.add(new Material(res.getString("titulo"), res.getString("tipo"), res.getInt("unidades")));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return resultado;
    }
}
