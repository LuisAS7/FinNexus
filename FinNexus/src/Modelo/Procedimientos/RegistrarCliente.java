package Modelo.Procedimientos;


import Modelo.ConexionBD;
import Modelo.Procedimientos.ProcedimientoAlmacenado;
import java.sql.CallableStatement;
import java.sql.Connection;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Asus
 */
public class RegistrarCliente implements ProcedimientoAlmacenado{

    private String dni;
    private String nombre;
    private String email;
    private String telefono;
    
    public RegistrarCliente(){
        
    }

    public RegistrarCliente(String dni, String nombre, String email, String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    @Override
    public String ejecutar() {
        String mensaje = "No se pudo registrar el cliente.";
    try {
                Connection conexion = ConexionBD.getInstancia().getConexion();
                try (CallableStatement stmt = conexion.prepareCall("{call RegistrarCliente(?, ?, ?, ?, ?)}")) {
                    stmt.setString(1, dni);
                    stmt.setString(2, nombre);
                    stmt.setString(3, email);
                    stmt.setString(4, telefono);
                    stmt.registerOutParameter(5, java.sql.Types.NVARCHAR);

                    stmt.execute();

                    mensaje = stmt.getString(5);
                    System.out.println("Resultado: " + mensaje);
                    // JOptionPane.showMessageDialog(null, mensaje); // para mostrarlo gráficamente si deseas
                }
            } catch (Exception e) {
                System.out.println("❌ Error al registrar cliente: " + e.getMessage());
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
}
