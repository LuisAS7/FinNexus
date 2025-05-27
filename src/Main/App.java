/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Modelo.Procedimientos.ProcedimientoAlmacenado;
import Controlador.ProcedimientoFactory;
import Modelo.Procedimientos.InscribirClienteEnCurso;

import Modelo.Procedimientos.VerCursosDisponibles;
import java.sql.SQLException;
import java.util.Date;
/**
 *
 * @author Luis Carlos
 */
public class App {
    public static void main (String[] args) throws SQLException {
        // Registrar el prototipo
        ProcedimientoFactory.registrar("verCursos", new VerCursosDisponibles());

        // Clonar y ejecutar
        ProcedimientoAlmacenado procedimiento = ProcedimientoFactory.obtener("verCursos");
        if (procedimiento != null) {
            procedimiento.ejecutar();
        } else {
            System.out.println("No se encontró el procedimiento.");
        }
        
        // Registrar prototipo con valores dummy
        ProcedimientoFactory.registrar("inscribirCliente", new InscribirClienteEnCurso(1, 2, new Date()));

        // Obtener clon
        InscribirClienteEnCurso procedimiento1 =  (InscribirClienteEnCurso) ProcedimientoFactory.obtener("inscribirCliente");
        if (procedimiento != null) {
            procedimiento1.ejecutar();
        } else {
            System.out.println("No se encontró el procedimiento.");
        }
    }
}

