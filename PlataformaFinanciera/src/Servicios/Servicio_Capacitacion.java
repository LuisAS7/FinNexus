/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import Controlador.TipoOperacionCapacitacion;
import DAO.Capacitacion_DAO;
import Interfaces.ModuloFinanciero;
import Modelo.Curso;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis Carlos
 */
public class Servicio_Capacitacion implements ModuloFinanciero {
    private TipoOperacionCapacitacion tipoOperacion;
    private int clienteID;
    private int cursoID;
    private java.util.Date fechaInscripcion;
    private Capacitacion_DAO capacitacion;  // DAO para acceder a la base de datos
    
    
   public Servicio_Capacitacion(TipoOperacionCapacitacion tipoOperacion) throws SQLException {
        this.capacitacion = new Capacitacion_DAO();
        this.tipoOperacion = tipoOperacion;
    }

    public void configurarInscripcion(int clienteID, int cursoID, java.util.Date fechaInscripcion) {
        this.clienteID = clienteID;
        this.cursoID = cursoID;
        this.fechaInscripcion = fechaInscripcion;
    }
    

    @Override
    public void ejecutarModulo() {
        
        switch (tipoOperacion) {
            case MOSTRAR_CURSOS:
                mostrarCursosDisponibles();
                break;
            case INSCRIBIR_CLIENTE:
                inscribirCliente();
                break;
            case VER_CURSOS_INSCRITOS:
                mostrarCursosInscritos();
                break;    
            default:
                System.out.println("❌ Operación no soportada.");
        }
    }
    
    private void mostrarCursosDisponibles() {
       try {
            List<Curso> cursos = capacitacion.obtenerCursosDisponibles();
            System.out.println("Mostrando cursos disponibles: ");
            for (Curso curso : cursos) {
                System.out.println(curso.getNombre());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Servicio_Capacitacion.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    private void inscribirCliente() {
         try {
            // Convertir fecha de Java a SQL (required for SQL queries)
            java.sql.Date fechaSQL = new java.sql.Date(fechaInscripcion.getTime());
            
            // Llamar al DAO para inscribir y obtener el mensaje
            String mensaje = capacitacion.inscribirClienteEnCurso(clienteID, cursoID, fechaSQL);
            
            // Imprimir el mensaje de éxito o error
            System.out.println(mensaje);
        } catch (SQLException ex) {
            System.out.println("❌ Error al ejecutar inscripción: " + ex.getMessage());
        }
    }
    
    private void mostrarCursosInscritos() {
    try {
        List<Curso> inscritos = capacitacion.obtenerCursosPorCliente(clienteID);
        System.out.println("Cursos inscritos por el cliente:");
        for (Curso curso : inscritos) {
            System.out.println("- " + curso.getNombre() + " | Inscrito el: " + curso.getFechaInicio());
        }
    } catch (SQLException e) {
        System.out.println("❌ Error al obtener cursos inscritos: " + e.getMessage());
    }
  }
}
