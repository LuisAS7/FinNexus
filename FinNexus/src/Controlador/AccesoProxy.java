/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;


import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import Modelo.Procedimientos.ModeloUsuario;
import Modelo.Usuario;

public class AccesoProxy implements IAccesoSistema {
 private final ModeloUsuario modelo;

    public AccesoProxy(ModeloUsuario modelo) {
        this.modelo = modelo;
    }

    @Override
    public Usuario login(String email, String contrasena, String tipoSeleccionado) {
        Usuario usuario = modelo.login(email, contrasena);

        if (usuario == null) return null;

        // Validar si tiene el rol seleccionado
        if (usuario.tieneRol(tipoSeleccionado)) {
            return usuario;
        }

        return null; // El rol seleccionado no coincide con los que tiene
    }
}
