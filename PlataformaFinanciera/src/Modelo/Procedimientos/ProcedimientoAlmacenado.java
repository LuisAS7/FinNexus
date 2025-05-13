/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Procedimientos;

/**
 *
 * @author Luis Carlos
 */
public interface ProcedimientoAlmacenado extends Cloneable {
    void ejecutar();
    ProcedimientoAlmacenado clonar();
}
