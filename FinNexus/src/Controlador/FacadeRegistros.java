/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Procedimientos.ProcedimientoAlmacenado;
import Modelo.Procedimientos.RegistrarCliente;
import Controlador.ProcedimientoFactory;

public class FacadeRegistros {
    
    public String registrarCliente(String dni, String nombre, String email, String telefono) {
        ProcedimientoAlmacenado procedimiento = ProcedimientoFactory.obtener("RegistrarCliente");

        // Validaciones
        if (dni.isEmpty() || nombre.isEmpty() || email.isEmpty() || telefono.isEmpty()) {
            return "Todos los campos son obligatorios.";
        }

        if (!dni.matches("\\d{8}")) {
            return "DNI debe tener 8 números.";
        }

        if (!telefono.matches("\\d{6,}")) {
            return "Teléfono debe tener al menos 6 números.";
        }

        // Registrar el prototipo si no está
        if (ProcedimientoFactory.obtener("registrarCliente") == null) {
            ProcedimientoFactory.registrar("registrarCliente", new RegistrarCliente());
        }

        // Obtener y configurar datos
        RegistrarCliente proc = (RegistrarCliente) ProcedimientoFactory.obtener("registrarCliente");
        proc.setDni(dni);
        proc.setNombre(nombre);
        proc.setEmail(email);
        proc.setTelefono(telefono);

        // Ejecutar
        return proc.ejecutar();
    }

       
    
}
