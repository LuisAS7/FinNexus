/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PresonalizacionVista;

import Vista.Capacitacion;
import java.awt.KeyboardFocusManager;
import java.awt.Window;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class NavegadorCapacitacion {
    // Variable global para controlar instancia única
    private static Capacitacion ventanaCapacitacion = null;
    
    public static void irACapacitacion(String panelObjetivo) {
    Window ventana = KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();

    // Si ya es la ventana de Capacitacion
    if (ventana instanceof Capacitacion) {
        Capacitacion cap = (Capacitacion) ventana;
        if (panelObjetivo.equals(cap.getPanelActual())) {
            JOptionPane.showMessageDialog(null, "Ya estás en este panel.");
        } else {
            cap.mostrarPanel(panelObjetivo);
        }
    } else {
        // Si hay una ventana previa de Capacitacion, cerrarla
        if (ventanaCapacitacion != null) {
            ventanaCapacitacion.dispose();
        }

        // Si hay otra ventana activa, cerrarla también
        if (ventana != null) {
            ventana.dispose();
        }

        // Crear nueva ventana
        ventanaCapacitacion = new Capacitacion();
        ventanaCapacitacion.setVisible(true);
        ventanaCapacitacion.mostrarPanel(panelObjetivo);
    }
}
}
