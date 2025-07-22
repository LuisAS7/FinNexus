/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Reporte {
   private int reporteID;
    private String nombreCliente;
    private Date fechaGeneracion;
    private double totalIngresos;
    private double totalGastos;
    private double balance;

    // Getters y setters
    public int getReporteID() { return reporteID; }
    public void setReporteID(int reporteID) { this.reporteID = reporteID; }

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public Date getFechaGeneracion() { return fechaGeneracion; }
    public void setFechaGeneracion(Date fechaGeneracion) { this.fechaGeneracion = fechaGeneracion; }

    public double getTotalIngresos() { return totalIngresos; }
    public void setTotalIngresos(double totalIngresos) { this.totalIngresos = totalIngresos; }

    public double getTotalGastos() { return totalGastos; }
    public void setTotalGastos(double totalGastos) { this.totalGastos = totalGastos; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
