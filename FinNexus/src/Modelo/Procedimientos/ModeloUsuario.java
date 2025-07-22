/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Procedimientos;


import Modelo.ConexionBD;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModeloUsuario {

    public Usuario login(String email, String contrasena) {
        Usuario usuario = null;

        try (Connection conexion = ConexionBD.getInstancia().getConexion()) {
            CallableStatement cs = conexion.prepareCall("{call spLogin(?, ?)}");
            cs.setString(1, email);
            cs.setString(2, contrasena);

            boolean tieneResultados = cs.execute();  // más seguro

            if (tieneResultados) {
                ResultSet rs = cs.getResultSet();
                if (rs.next()) {
                    int id = rs.getInt("UsuarioID");
                    String nombre = rs.getString("NombreUsuario");
                    String correo = rs.getString("Email");

                    usuario = new Usuario(id, nombre, correo);

                    // Obtener roles
                    CallableStatement csRoles = conexion.prepareCall("{call spObtenerRolesUsuario(?)}");
                    csRoles.setInt(1, id);
                    ResultSet rsRoles = csRoles.executeQuery();

                    List<String> roles = new ArrayList<>();
                    while (rsRoles.next()) {
                        roles.add(rsRoles.getString("Rol"));
                    }
                    usuario.setRoles(roles);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }
}

