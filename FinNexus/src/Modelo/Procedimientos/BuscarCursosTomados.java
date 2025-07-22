/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Procedimientos;

import Modelo.ConexionBD;
import java.util.ArrayList;
import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;

public class BuscarCursosTomados implements ProcedimientoAlmacenado {
     private String modoBusqueda;
    private String valorBusqueda;

    public void cargar(String modoBusqueda, String valorBusqueda) {
        this.modoBusqueda = modoBusqueda;
        this.valorBusqueda = valorBusqueda;
    }

    public List<Object[]> obtenerResultados() {
        List<Object[]> lista = new ArrayList<>();
        try (Connection conn = ConexionBD.getInstancia().getConexion()) {
            CallableStatement cs = conn.prepareCall("{call sp_buscarCursosTomados(?, ?)}");
            cs.setString(1, modoBusqueda);
            cs.setString(2, valorBusqueda);

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Object[] fila = new Object[]{
                    rs.getInt("ID"),
                    rs.getString("NombreCliente"),
                    rs.getString("NombreCurso"),
                    rs.getDate("FechaInscripcion")
                };
                lista.add(fila);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar búsqueda: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public String ejecutar() {
        return "Este método no se usa en esta implementación.";
    }

    @Override
    public ProcedimientoAlmacenado clonar() {
        return new BuscarCursosTomados();
    }
}
