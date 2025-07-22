/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Procedimientos.buscarReportesPorNombre;

/**
 *
 * @author Asus
 */
public class ReporteFacade {
    public String buscarPorNombre(String nombreCliente) {
        buscarReportesPorNombre procedimiento = new buscarReportesPorNombre(nombreCliente);
        return procedimiento.ejecutar();
    }
}
