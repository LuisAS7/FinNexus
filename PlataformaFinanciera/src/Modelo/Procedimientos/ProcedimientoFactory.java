/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Procedimientos;

import java.util.HashMap;

/**
 *
 * @author Luis Carlos
 */
public class ProcedimientoFactory {
    private static final HashMap<String, ProcedimientoAlmacenado> prototipos = new HashMap<>();

    public static void registrar(String nombre, ProcedimientoAlmacenado procedimiento) {
        prototipos.put(nombre, procedimiento);
    }

    public static ProcedimientoAlmacenado obtener(String nombre) {
        ProcedimientoAlmacenado prototipo = prototipos.get(nombre);
        return prototipo != null ? prototipo.clonar() : null;
    }
}
