/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import PresonalizacionVista.Comando;
import java.util.Stack;

/**
 *
 * @author Asus
 */
public class ControladorPrestamo {
private static final Stack<Comando> historial = new Stack<>();

    public static void ejecutar(Comando comando) {
        comando.ejecutar();
        historial.push(comando);
    }

    public static void deshacer() {
        if (!historial.isEmpty()) {
            historial.pop().deshacer();
        }
    }
}
