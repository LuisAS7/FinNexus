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
        VerPrestamosCliente procedimiento = new VerPrestamosCliente(dni);
        String mensaje = procedimiento.ejecutar();
        System.out.println(mensaje);
        return procedimiento.getPrestamos();
    }
}
