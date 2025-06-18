/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Controlador;
import Modelo.Usuario;
/**
 *
 * @author Asus
 */
public interface IAccesoSistema {
    Usuario login(String email, String contrasena, String tipoSeleccionado);
}
