/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Procedimientos;


import Modelo.Usuario;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModeloUsuario {

    private final Connection conexion;

    public ModeloUsuario(Connection conexion) {
        this.conexion = conexion;
    }

    // Método para registrar un nuevo usuario como Cliente
    public boolean registrarUsuario(String nombreUsuario, String email, String contrasena) {
        try {
            CallableStatement stmt = conexion.prepareCall("{call spRegistrarUsuario(?, ?, ?)}");
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, email);
            stmt.setString(3, contrasena);
            stmt.execute();
            stmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para validar login
    public Usuario login(String email, String contrasena) {
        try {
            CallableStatement stmt = conexion.prepareCall("{call spLogin(?, ?)}");
            stmt.setString(1, email);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("UsuarioID"));
                usuario.setNombre(rs.getString("NombreUsuario"));
                usuario.setRol(rs.getString("Rol"));
                return usuario;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

