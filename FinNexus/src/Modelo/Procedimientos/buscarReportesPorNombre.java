/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Procedimientos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import Modelo.ConexionBD;
import Modelo.Reporte;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Asus
 */
public class buscarReportesPorNombre implements ProcedimientoAlmacenado {
   
 private String nombreCliente;
    private List<Reporte> listaReportes = new ArrayList<>();

    public buscarReportesPorNombre(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public List<Reporte> getListaReportes() {
        return listaReportes;
    }

    @Override
    public String ejecutar() {
        StringBuilder resultado = new StringBuilder();

        try (Connection conn = ConexionBD.getInstancia().getConexion()) {
            CallableStatement cs = conn.prepareCall("{call sp_mostrar_reportes(?)}");

            cs.setString(1, (nombreCliente == null || nombreCliente.trim().isEmpty()) ? null : nombreCliente);

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Reporte r = new Reporte();
                r.setReporteID(rs.getInt("ReporteID"));
                r.setNombreCliente(rs.getString("NombreCliente"));
                r.setFechaGeneracion(rs.getDate("FechaGeneracion"));
                r.setTotalIngresos(rs.getDouble("TotalIngresos"));
                r.setTotalGastos(rs.getDouble("TotalGastos"));
                r.setBalance(rs.getDouble("Balance"));
                listaReportes.add(r);

                // También, si deseas el resumen textual:
                resultado.append("ID: ").append(r.getReporteID()).append(" | ")
                         .append("Cliente: ").append(r.getNombreCliente()).append(" | ")
                         .append("Fecha: ").append(r.getFechaGeneracion()).append(" | ")
                         .append("Ingresos: ").append(r.getTotalIngresos()).append(" | ")
                         .append("Gastos: ").append(r.getTotalGastos()).append(" | ")
                         .append("Balance: ").append(r.getBalance()).append("\n");
            }

        } catch (Exception e) {
            return "Error al ejecutar procedimiento: " + e.getMessage();
        }

        return resultado.toString();
    }

    @Override
    public ProcedimientoAlmacenado clonar() {
        return new buscarReportesPorNombre(this.nombreCliente);
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
}
