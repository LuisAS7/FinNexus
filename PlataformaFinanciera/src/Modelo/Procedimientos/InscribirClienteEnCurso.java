/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Procedimientos;
import java.util.Date;
import Modelo.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
/**
 *
 * @author Asus
 */
public class InscribirClienteEnCurso implements ProcedimientoAlmacenado{

    private int clienteID;
    private int cursoID;
    private Date fechaInscripcion;

    public InscribirClienteEnCurso(int clienteID, int cursoID, Date fechaInscripcion) {
        this.clienteID = clienteID;
        this.cursoID = cursoID;
        this.fechaInscripcion = fechaInscripcion;
    }
    @Override
    public void ejecutar() { 
        try {
            Connection conexion = ConexionBD.getInstancia().getConexion();
            try (CallableStatement stmt = conexion.prepareCall("{call InscribirClienteEnCurso(?, ?, ?)}")) {
                stmt.setInt(1, clienteID);
                stmt.setInt(2, cursoID);
                stmt.setDate(3, new java.sql.Date(fechaInscripcion.getTime()));
                
                boolean tieneResultado = stmt.execute();
                
                if (tieneResultado) {
                    ResultSet rs = stmt.getResultSet();
                    while (rs.next()) {
                        System.out.println("Resultado: " + rs.getString(1));
                    }
                    rs.close();
                } else {
                    System.out.println("✅ Procedimiento ejecutado correctamente.");
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error al ejecutar el procedimiento: " + e.getMessage());
        }
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
