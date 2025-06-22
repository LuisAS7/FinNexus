/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PresonalizacionVista;

import java.util.ArrayList;
import java.util.List;

public class PrestamoObservable {
    private static final List<PrestamoObserver> observadores = new ArrayList<>();

    public static void agregarObservador(PrestamoObserver obs) {
        observadores.add(obs);
    }

    public static void notificar(String paso) {
        for (PrestamoObserver obs : observadores) {
            obs.actualizarVista(paso);
        }
    }
}
