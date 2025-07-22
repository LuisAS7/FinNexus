/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Procedimientos.ProcedimientoAlmacenado;
import Modelo.Procedimientos.RegistrarUsuario;

/**
 *
 * @author Asus
 */
public class FacadeUsuarios {
  public String registrarUsuario(String nombreUsuario, String email, String contrasena) {

        if (nombreUsuario.isEmpty() || email.isEmpty() || contrasena.isEmpty()) {
            return "Todos los campos son obligatorios.";
        }

        ProcedimientoAlmacenado base = ProcedimientoFactory.obtener("registrarUsuario");

        if (base == null) {
            ProcedimientoFactory.registrar("registrarUsuario", new RegistrarUsuario());
            base = ProcedimientoFactory.obtener("registrarUsuario");
        }

        RegistrarUsuario proc = (RegistrarUsuario) base.clonar();
        proc.setNombreUsuario(nombreUsuario);
        proc.setEmail(email);
        proc.setContrasena(contrasena);

        return proc.ejecutar();
    }
}
