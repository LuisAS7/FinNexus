/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Procedimientos.ProcedimientoAlmacenado;
import Modelo.Procedimientos.VerCursosDisponibles;
import Controlador.ProcedimientoFactory;
import Modelo.Curso;
import java.util.List;


public class FacadeCursos {

    public List<Curso> obtenerListaCursos() {
    VerCursosDisponibles verCursos = new VerCursosDisponibles();
    return verCursos.obtenerCursos();
}


}
