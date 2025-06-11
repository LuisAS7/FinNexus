/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Procedimientos;
import Modelo.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
/**
 *
 * @author Luis Carlos
 */
public class VerCursosDisponibles implements ProcedimientoAlmacenado{
    @Override
    public String ejecutar() {
        try {
            Connection conexion = ConexionBD.getInstancia().getConexion();
                CallableStatement stmt = conexion.prepareCall("{call VerCursosDisponibles}");

                ResultSet rs = stmt.executeQuery();
                System.out.println("Cursos Disponibles:");
                while (rs.next()) {
                    System.out.println("- ID: " + rs.getInt("CursoID") +
                                       ", Nombre: " + rs.getString("Nombre") +
                                       ", Descripción: " + rs.getString("Descripcion") +
                                       ", Duración: " + rs.getInt("DuracionHoras") +
                                       ", Fecha Inicio: " + rs.getDate("FechaInicio"));
                }
                rs.close();
                stmt.close();
        } catch (Exception e) {
            System.out.println(" Error al ejecutar el procedimiento: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ProcedimientoAlmacenado clonar() {
         try {
            return (ProcedimientoAlmacenado) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("No se pudo clonar el procedimiento");
        }
    }
    
}
