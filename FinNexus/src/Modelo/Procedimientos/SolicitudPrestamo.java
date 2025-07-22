/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Procedimientos;

import Modelo.ConexionBD;
import java.util.Date;
import java.sql.CallableStatement;
import java.sql.Connection;

/**
 *
 * @author Asus
 */
public class SolicitudPrestamo implements ProcedimientoAlmacenado, Cloneable {

    private String dni;
    private double monto;
    private Date fechaSolicitud;
    private String estado;
    private int plazomeses;
    private String ocupacion;
    
    public SolicitudPrestamo() {
    /* sin argumentos; imprescindible para la Factory */
}
    
    public SolicitudPrestamo(String dni, double monto, Date fechaSolicitud,  int plazomeses, String ocupacion) {
    this.dni = dni;
    this.monto = monto;
    this.fechaSolicitud = fechaSolicitud;
    this.estado = "Pendiente"; // Estado por defecto
    this.plazomeses = plazomeses;
    this.ocupacion = ocupacion;
}
    
public void cargar(String dni, double monto, Date fecha, int plazos, String ocupacion) {
    this.dni = dni;
    this.monto = monto;
    this.fechaSolicitud = fecha;
    this.plazomeses = plazos;
    this.ocupacion = ocupacion;
    this.estado = "Pendiente";
}
    
    @Override
    public String ejecutar() {
 String mensaje = "No se pudo registrar la solicitud de préstamo.";
        try {
            Connection conexion = ConexionBD.getInstancia().getConexion();
            try (CallableStatement stmt = conexion.prepareCall("{call SolicitarPrestamo(?, ?, ?, ?, ?, ?, ?)}")) {
                stmt.setString(1, dni);
                stmt.setDouble(2, monto);
                stmt.setDate(3, new java.sql.Date(fechaSolicitud.getTime()));
                stmt.setString(4, estado);
                stmt.setInt(5,plazomeses);
                stmt.setString(6, ocupacion);
                stmt.registerOutParameter(7, java.sql.Types.NVARCHAR);

                stmt.execute();

                mensaje = stmt.getString(7);
                System.out.println("Resultado: " + mensaje);
            }
        } catch (Exception e) {
            System.out.println("❌ Error al registrar la solicitud de préstamo: " + e.getMessage());
        }
        return mensaje;

    }

    @Override
    public ProcedimientoAlmacenado clonar() {
        try {
            return (ProcedimientoAlmacenado) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("No se pudo clonar el procedimiento");
        }
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setPlazomeses(int plazomeses) {
        this.plazomeses = plazomeses;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }
    
    
    
}
