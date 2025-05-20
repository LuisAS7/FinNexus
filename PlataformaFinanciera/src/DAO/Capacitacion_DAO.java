/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Modelo.ConexionBD;
import Modelo.Curso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Luis Carlos
 */
public class Capacitacion_DAO {
     private Connection conexion;

    public Capacitacion_DAO() throws SQLException {
        this.conexion = ConexionBD.getInstancia().getConexion();
    }

    // 1. Ver todos los cursos disponibles
    public List<Curso> obtenerCursosDisponibles() throws SQLException {
        List<Curso> cursos = new ArrayList<>();

        String sql = "{CALL VerCursosDisponibles}";
        try (CallableStatement cs = conexion.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                Curso curso = new Curso();
                curso.setCursoID(rs.getInt("CursoID"));
                curso.setNombre(rs.getString("Nombre"));
                curso.setDescripcion(rs.getString("Descripcion"));
                curso.setDuracionHoras(rs.getInt("DuracionHoras"));
                curso.setFechaInicio(rs.getDate("FechaInicio"));
                cursos.add(curso);
            }
        }
        return cursos;
    }

    // 2. Inscribir a un cliente en un curso
     public String inscribirClienteEnCurso(int clienteID, int cursoID, Date fechaInscripcion)  throws SQLException {
        String mensaje = "";
        String sql = "{CALL InscribirClienteEnCurso(?, ?, ?, ?)}";

        try (CallableStatement cs = conexion.prepareCall(sql)) {
        cs.setInt(1, clienteID);
        cs.setInt(2, cursoID);
        cs.setDate(3, fechaInscripcion);
        cs.registerOutParameter(4, java.sql.Types.NVARCHAR);

        cs.execute();
        mensaje = cs.getString(4); // Obtener el mensaje del procedimiento
    } catch (SQLException ex) {
        mensaje = "❌ Error al inscribir cliente: " + ex.getMessage();
    }

    return mensaje;
    }

    // 3. Ver cursos inscritos por un cliente
    public List<Curso> obtenerCursosPorCliente(int clienteID) throws SQLException {
        List<Curso> cursos = new ArrayList<>();

        String sql = "{CALL VerCursosInscritosPorCliente(?)}";
        try (CallableStatement cs = conexion.prepareCall(sql)) {
            cs.setInt(1, clienteID);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Curso curso = new Curso();
                    curso.setCursoID(rs.getInt("CursoID"));
                    curso.setNombre(rs.getString("Nombre"));
                    curso.setDescripcion(rs.getString("Descripcion"));
                    curso.setDuracionHoras(rs.getInt("DuracionHoras"));
                    curso.setFechaInicio(rs.getDate("FechaInicio"));
                    cursos.add(curso);
                }
            }
        }
        return cursos;
    }
}
