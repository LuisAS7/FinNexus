/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Procedimientos.ProcedimientoAlmacenado;
import Modelo.Procedimientos.SolicitudPrestamo;
import Controlador.ProcedimientoFactory;
import java.util.Date;

public class FacadeSolicitudPrestamo {
    public String registrarSolicitudPrestamo(String dni, double monto, Date fechaSolicitud, int plazomeses, String ocupacion) {
    ProcedimientoAlmacenado procedimiento = ProcedimientoFactory.obtener("SolicitudPrestamo");

    if (procedimiento instanceof SolicitudPrestamo solicitud) {
        solicitud.cargar(dni, monto, fechaSolicitud, plazomeses, ocupacion);

        return solicitud.ejecutar();
    }
        return "No se pudo ejecutar el procedimiento SolicitudPrestamo.";
  }
}  
