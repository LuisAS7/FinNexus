/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;


import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class AccesoProxy {
    private String rolUsuario;

    public AccesoProxy(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public JMenuBar crearMenuPersonalizado() {
        JMenuBar barra = new JMenuBar();

        // Capacitacion - solo Admin o Empleado
        JMenu capacitacionMenu = new JMenu("Capacitación");

        JMenuItem mostrarCursos = new JMenuItem("Mostrar Cursos");
        mostrarCursos.addActionListener(e -> {
            if (tieneAcceso("Admin", "Empleado")) {
                // acción real
                JOptionPane.showMessageDialog(null, "Mostrando cursos disponibles...");
            } else {
                accesoDenegado();
            }
        });

        JMenuItem inscribirCliente = new JMenuItem("Inscribir Cliente");
        inscribirCliente.addActionListener(e -> {
            if (tieneAcceso("Admin", "Empleado")) {
                JOptionPane.showMessageDialog(null, "Inscribiendo cliente...");
            } else {
                accesoDenegado();
            }
        });

        capacitacionMenu.add(mostrarCursos);
        capacitacionMenu.add(inscribirCliente);

        if (tieneAcceso("Admin", "Empleado")) {
            barra.add(capacitacionMenu);
        }

        // Seguimiento - solo Admin
        JMenu seguimientoMenu = new JMenu("Seguimiento");

        JMenuItem generarReporte = new JMenuItem("Generar Reporte");
        generarReporte.addActionListener(e -> {
            if (tieneAcceso("Admin")) {
                JOptionPane.showMessageDialog(null, "Generando reporte...");
            } else {
                accesoDenegado();
            }
        });

        seguimientoMenu.add(generarReporte);

        if (tieneAcceso("Admin")) {
            barra.add(seguimientoMenu);
        }

        // Cliente - todos lo ven
        JMenu clienteMenu = new JMenu("Cursos");
        JMenuItem verCursos = new JMenuItem("Ver Cursos Disponibles");
        verCursos.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Cursos abiertos para visualización.");
        });
        clienteMenu.add(verCursos);
        barra.add(clienteMenu);

        return barra;
    }

    private boolean tieneAcceso(String... rolesPermitidos) {
        for (String rol : rolesPermitidos) {
            if (rolUsuario.equalsIgnoreCase(rol)) {
                return true;
            }
        }
        return false;
    }

    private void accesoDenegado() {
        JOptionPane.showMessageDialog(null, "Acceso denegado. No tienes permisos para esta acción.");
    }
}
