/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Asus
 */
public class Controlador {

    public static List<Object[]> obtenerPorNombre(String nombre) {
        FacadeBuscarCursosTomados facade = new FacadeBuscarCursosTomados();
        return facade.buscar("nombre", nombre);  // 'nombre' debe coincidir con lo esperado en el SP
    }

    public static List<Object[]> obtenerPorFecha(Date fecha) {
        FacadeBuscarCursosTomados facade = new FacadeBuscarCursosTomados();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return facade.buscar("fecha", sdf.format(fecha));
    }
}

