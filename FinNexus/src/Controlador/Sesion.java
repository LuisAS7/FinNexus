/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Usuario;

/**
 *
 * @author Asus
 */
public class Sesion {
  private static Usuario usuario;
    private static String rolSeleccionado = "Invitado";

    public static void setUsuario(Usuario u) {
        usuario = u;
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setRolSeleccionado(String rol) {
        rolSeleccionado = rol;
    }

    public static String getRolSeleccionado() {
        return rolSeleccionado;
    }

    public static String getRolPrincipal() {
        if (usuario != null && usuario.getRoles() != null && !usuario.getRoles().isEmpty()) {
            return (String) usuario.getRoles().get(0);
        } else {
            return "Invitado";
        }
    }

    public static void cerrarSesion() {
        usuario = null;
        rolSeleccionado = "Invitado";
    }
}

