/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;


import Controlador.Controlador_Capacitacion;
import Controlador.TipoOperacionCapacitacion;
import Modelo.ConexionBD;
import Modelo.Procedimientos.ProcedimientoAlmacenado;
import Modelo.Procedimientos.ProcedimientoFactory;
import Modelo.Procedimientos.VerCursosDisponibles;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
/**
 *
 * @author Luis Carlos
 */
public class App {
    public static void main (String[] args) throws SQLException {
      
        Connection conexion = ConexionBD.getInstancia().getConexion();

        if (conexion != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }
        
        // Registrar el prototipo
        ProcedimientoFactory.registrar("verCursos", new VerCursosDisponibles());

        // Clonar y ejecutar
        ProcedimientoAlmacenado procedimiento = ProcedimientoFactory.obtener("verCursos");
        if (procedimiento != null) {
            procedimiento.ejecutar();
        } else {
            System.out.println("No se encontró el procedimiento.");
        }
        
        Controlador_Capacitacion control = new Controlador_Capacitacion();
        control.mostrarCursos();
        
        // Inscribir cliente en curso
            int clienteID = 1;
            int cursoID = 1;
            Date fecha = new Date(); // hoy
            control.inscribirCliente(clienteID, cursoID, fecha);
        
           
         // Mostrar cursos inscritos por un cliente
          control.verCursosInscritos(clienteID);

    }
}
