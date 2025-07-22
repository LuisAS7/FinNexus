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
    public String registrarSolicitudPrestamo(String dni, double monto,Date fechaSolicitud, int plazomeses,String ocupacion) {

        ProcedimientoAlmacenado base = ProcedimientoFactory.obtener("SolicitudPrestamo");

        if (base == null) {                                 // Registro si faltara
            ProcedimientoFactory.registrar("SolicitudPrestamo", new SolicitudPrestamo());
            base = ProcedimientoFactory.obtener("SolicitudPrestamo");
        }

        // Clonamos para evitar contaminación de estado
        SolicitudPrestamo solicitud = (SolicitudPrestamo) base.clonar();
        solicitud.cargar(dni, monto, fechaSolicitud, plazomeses, ocupacion);

        return solicitud.ejecutar();
    }
}  
