/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Servicios.Servicio_Capacitacion;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Luis Carlos
 */
public class Controlador_Capacitacion {
    
 public void mostrarCursos() throws SQLException {
    Servicio_Capacitacion servicio = new Servicio_Capacitacion(TipoOperacionCapacitacion.MOSTRAR_CURSOS);
    servicio.ejecutarModulo();
}

public void inscribirCliente(int clienteID, int cursoID, Date fecha) throws SQLException {
    Servicio_Capacitacion servicio = new Servicio_Capacitacion(TipoOperacionCapacitacion.INSCRIBIR_CLIENTE);
    servicio.configurarInscripcion(clienteID, cursoID, fecha);
    servicio.ejecutarModulo();
}

public void verCursosInscritos(int clienteID) throws SQLException {
    Servicio_Capacitacion servicio = new Servicio_Capacitacion(TipoOperacionCapacitacion.VER_CURSOS_INSCRITOS);
    servicio.configurarInscripcion(clienteID, 0, null); // solo se necesita clienteID
    servicio.ejecutarModulo();
 }
}
