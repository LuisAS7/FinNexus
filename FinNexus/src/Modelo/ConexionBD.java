/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Luis Carlos
 */
public class ConexionBD {
    private static ConexionBD instancia;
    private Connection conexion;
    
    private final String URL = "jdbc:sqlserver://localhost:1433;databaseName=FinNexus;encrypt=true;trustServerCertificate=true;user=demonking;password=9123;";
    private final String Usu = "demonking";
    private final String Contra = "9123";
    
    private ConexionBD() {
        try {
            conexion = DriverManager.getConnection(URL, Usu, Contra);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static ConexionBD getInstancia() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }
    
    private void conectar() {
        try {
            conexion = DriverManager.getConnection(URL, Usu, Contra);
        } catch (SQLException e) {
            System.err.println("Error al conectar a la BD: " + e.getMessage());
        }
    }
    
    public Connection getConexion() {
       try {
            if (conexion == null || conexion.isClosed()) {
                conectar(); // reintenta conexión si está cerrada
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar conexión: " + e.getMessage());
        }
        return conexion;
    }
}
