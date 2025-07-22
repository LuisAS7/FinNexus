/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Procedimientos.BuscarCursosTomados;
import Modelo.Procedimientos.ProcedimientoAlmacenado;
import Controlador.ProcedimientoFactory;
import java.util.List;

public class FacadeBuscarCursosTomados {
    public List<Object[]> buscar(String modo, String valor) {
        ProcedimientoAlmacenado base = ProcedimientoFactory.obtener("BuscarCursosTomados");

        if (base == null) {
            ProcedimientoFactory.registrar("BuscarCursosTomados", new BuscarCursosTomados());
            base = ProcedimientoFactory.obtener("BuscarCursosTomados");
        }

        BuscarCursosTomados buscar = (BuscarCursosTomados) base.clonar();
        buscar.cargar(modo, valor);

        return buscar.obtenerResultados(); // nuevo método que devuelve datos directamente
    }

}
