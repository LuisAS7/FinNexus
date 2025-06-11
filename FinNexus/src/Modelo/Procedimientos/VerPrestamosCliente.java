/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Procedimientos;

import Modelo.ConexionBD;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Asus
 */
public class VerPrestamosCliente implements ProcedimientoAlmacenado {

   private String dni;

    // Lista donde guardamos los resultados
    private List<Prestamo> prestamos = new ArrayList<>();

    // Clase auxiliar para cada fila de resultados
    public static class Prestamo {
        public Date fecha;
        public BigDecimal monto;
        public int plazo;
        public String estado;

        public Prestamo(Date fecha, BigDecimal monto, int plazo, String estado) {
            this.fecha = fecha;
            this.monto = monto;
            this.plazo = plazo;
            this.estado = estado;
        }
    }

    public VerPrestamosCliente(String dni) {
        this.dni = dni;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    
    @Override
    public String ejecutar() {
     try {
            Connection conexion = ConexionBD.getInstancia().getConexion();
            CallableStatement stmt = conexion.prepareCall("{call VerPrestamosCliente(?)}");
            stmt.setString(1, dni);

            ResultSet rs = stmt.executeQuery();
            prestamos.clear(); // Limpia la lista antes de llenarla
            System.out.println("📄 Préstamos del cliente con DNI: " + dni);

            while (rs.next()) {
                Prestamo p = new Prestamo(
                    rs.getDate("Fecha"),
                    rs.getBigDecimal("Monto"),
                    rs.getInt("Plazo"),
                    rs.getString("Estado")
                );
                prestamos.add(p);

                // También los puedes imprimir si deseas
                System.out.println("- Fecha: " + p.fecha +
                                   ", Monto: " + p.monto +
                                   ", Plazo: " + p.plazo + " meses" +
                                   ", Estado: " + p.estado);
            }

            rs.close();
            stmt.close();
            return "Préstamos mostrados correctamente para el cliente.";
        } catch (Exception e) {
            System.out.println("❌ Error al obtener préstamos: " + e.getMessage());
            return "No se pudieron obtener los préstamos del cliente.";
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
