/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PresonalizacionVista;

import Vista.Capacitacion;
import Vista.Login;
import Vista.PRESTAMO_RAPIDO;
import javax.swing.*;
import java.awt.*;
import javax.swing.SwingUtilities;
import java.awt.Component;
import javax.swing.JPanel;




import java.awt.event.ActionEvent;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 *
 * @author Asus
 */
public class MenuPersonalizado {
    private static final Color COLOR_FONDO = new Color(175, 26, 70);

    public static JMenuBar crearMenuBar(String rol) {
         JMenuBar barra = new JMenuBar();
    barra.setBackground(COLOR_FONDO);
    barra.setPreferredSize(new Dimension(700, 50));

    /* === Grupo Capacitación === */
    MenuGrupo cap = new MenuGrupo("Capacitación", "/icons/inventario.png");
    cap.agregarOpcion("Mostrar Cursos disponibles",
        e -> NavegadorCapacitacion.irACapacitacion("mostrar"),null);
    cap.agregarOpcion("Inscribir Cliente",   
        e -> NavegadorCapacitacion.irACapacitacion("inscribir"),null);
    cap.agregarOpcion("Consulta de Cursos",   
        e -> NavegadorCapacitacion.irACapacitacion("consultar"),null);

    /* === Grupo Préstamo === */
    MenuGrupo prest = new MenuGrupo("Préstamo", "/icons/saldo.png");
    prest.agregarOpcion("Registrar Préstamo", 
        e -> NavegadorPrestamo.irACapacitacion("Registrar"),null);
    prest.agregarOpcion("Ver Préstamos", 
        e -> NavegadorPrestamo.irACapacitacion("Ver"),null);

    /* === Grupo Seguimiento === */
    MenuGrupo seg = new MenuGrupo("Seguimiento", "/icons/busqueda.png");
    seg.agregarOpcion("Registrar Transacción", 
        e -> NavegadorSeguimiento.irACapacitacion("RegistrarT"), null);
    seg.agregarOpcion("Generar Reporte", 
        e -> NavegadorSeguimiento.irACapacitacion("Reporte"),null);
    seg.agregarOpcion("Consultar Balance", 
        e -> NavegadorSeguimiento.irACapacitacion("Balance"), null);

    /* === Menú principal: decide qué grupos mostrar según rol === */
    if ("Invitado".equalsIgnoreCase(rol)) {
        // solo capacitaciones básicas
        cap.construirSobre(barra);
    } else if ("Cliente".equalsIgnoreCase(rol)) {
        cap.construirSobre(barra);
        prest.construirSobre(barra);
    } else if ("Empleado".equalsIgnoreCase(rol)) {
        cap.construirSobre(barra);
        prest.construirSobre(barra);
    } else if ("Admin".equalsIgnoreCase(rol)) {
        cap.construirSobre(barra);
        prest.construirSobre(barra);
        seg.construirSobre(barra);
    }

    /* Separador y botón Login */
    barra.add(Box.createHorizontalGlue());
    JPanel filler = new JPanel();
filler.setOpaque(true);
filler.setBackground(COLOR_FONDO);
filler.setPreferredSize(new Dimension(2000, 40)); // ancho grande, alto de la barra
barra.add(filler);
    
    JMenu login = MenuGrupo.crearMenu("Iniciar Sesión", "/icons/user.png");
    login.addMenuListener(new MenuListener() {
    @Override
    public void menuSelected(MenuEvent e) {
        new Login().setVisible(true);
        login.getPopupMenu().setVisible(false); // evita desplegar menú vacío
    }

    @Override public void menuDeselected(MenuEvent e) {}
    @Override public void menuCanceled(MenuEvent e) {}
    });
    barra.add(login);
   

    return barra;
    }
    
 

   
    private static MenuListener mensajeLogin() {
        return new MenuListener() {
            public void menuSelected(MenuEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Debe iniciar sesión o registrarse para acceder a esta opción.",
                        "Acceso restringido", JOptionPane.WARNING_MESSAGE);
            }
            public void menuDeselected(MenuEvent e) {}
            public void menuCanceled(MenuEvent e) {}
        };
    }

   
}
