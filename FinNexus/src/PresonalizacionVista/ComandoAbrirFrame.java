/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PresonalizacionVista;

import Vista.PRESTAMO_RAPIDO1;
import vista.PRESTAMO_RFINAL;
import javax.swing.JFrame;
/**
 *
 * @author Asus
 */
public class ComandoAbrirFrame implements Comando {
    private JFrame actual;
    private JFrame destino;

    public ComandoAbrirFrame(JFrame actual, JFrame destino) {
        this.actual = actual;
        this.destino = destino;
    }

    @Override
    public void ejecutar() {
        destino.setVisible(true);
        destino.setLocationRelativeTo(null);
        actual.dispose();
    }

    @Override
    public void deshacer() {
        actual.setVisible(true);
        actual.setLocationRelativeTo(null);
        destino.dispose();
    }
}
