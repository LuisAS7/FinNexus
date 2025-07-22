/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Procedimientos;

import Modelo.ConexionBD;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class RegistrarUsuario implements ProcedimientoAlmacenado {
    private String nombreUsuario;
    private String email;
    private String contrasena;

    // Setters
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String ejecutar() {
        try (Connection conexion = ConexionBD.getInstancia().getConexion()) {
            CallableStatement stmt = conexion.prepareCall("{call spRegistrarUsuario(?, ?, ?)}");
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, email);
            stmt.setString(3, contrasena); // El procedimiento se encarga del salt y el hash

            stmt.execute();
            return "Usuario registrado correctamente.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al registrar usuario: " + e.getMessage();
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
