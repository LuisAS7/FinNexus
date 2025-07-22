/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Procedimientos;
import Modelo.ConexionBD;
import Modelo.Curso;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Luis Carlos
 */
public class VerCursosDisponibles implements ProcedimientoAlmacenado{
    public List<Curso> obtenerCursos() {
        List<Curso> listaCursos = new ArrayList<>();

        try {
            Connection conexion = ConexionBD.getInstancia().getConexion();
            CallableStatement stmt = conexion.prepareCall("{call VerCursosDisponibles}");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Curso curso = new Curso();
                curso.setCursoID(rs.getInt("CursoID"));
                curso.setNombre(rs.getString("Nombre"));
                curso.setDescripcion(rs.getString("Descripcion"));
                curso.setDuracionHoras(rs.getInt("DuracionHoras"));
                curso.setFechaInicio(rs.getDate("FechaInicio"));

                listaCursos.add(curso);
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error al obtener cursos: " + e.getMessage());
        }

        return listaCursos;
    }

    @Override
    public String ejecutar() {
        return null; // Ya no se usa en este caso
    }

    @Override
    public ProcedimientoAlmacenado clonar() {
        try {
            return (ProcedimientoAlmacenado) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("No se pudo clonar el procedimiento");
        }
    }
    
}
