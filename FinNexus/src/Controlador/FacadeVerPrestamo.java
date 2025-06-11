/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Procedimientos.ProcedimientoAlmacenado;
import Modelo.Procedimientos.VerPrestamosCliente;
import Controlador.ProcedimientoFactory;
import java.util.Collections;
import java.util.List;


public class FacadeVerPrestamo {
 public List<VerPrestamosCliente.Prestamo> verPrestamosPorCliente(String dni) {
        ProcedimientoAlmacenado procedimiento = ProcedimientoFactory.obtener("VerPrestamosCliente");

        if (procedimiento instanceof VerPrestamosCliente) {
            VerPrestamosCliente verPrestamos = (VerPrestamosCliente) procedimiento;
            // Asignamos el DNI
            verPrestamos = new VerPrestamosCliente(dni);
            String mensaje = verPrestamos.ejecutar(); // Ejecuta el procedimiento
            System.out.println(mensaje);
            return verPrestamos.getPrestamos(); // Retorna la lista
        }

        System.out.println("❌ No se pudo ejecutar el procedimiento VerPrestamosCliente.");
        return Collections.emptyList(); // Retorna una lista vacía si falla
    }   
}
