/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Modelo.Procedimientos.ProcedimientoAlmacenado;
import Controlador.ProcedimientoFactory;
import Modelo.Procedimientos.VerCursosDisponibles;
import java.sql.SQLException;
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
    }
}
