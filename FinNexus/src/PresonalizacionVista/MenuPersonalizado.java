/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PresonalizacionVista;

import Vista.Capacitacion;
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
    private static final Color mcolorFondo = new Color(26, 35, 126);

    public static JMenuBar crearMenuBar(String rolUsuario) {
        JMenuBar barra = new JMenuBar();
        barra.setBackground(mcolorFondo);
        barra.setPreferredSize(new Dimension(700, 50)); // ancho, alto

     // ==== Menú Capacitación ====
    JMenu mnicapacitacion = new JMenu("Capacitación");
    mnicapacitacion.setIcon(obtenerIcono("/icons/inventario.png"));
    aplicarEstiloMenu(mnicapacitacion);

    if (rolUsuario.equalsIgnoreCase("Invitado")) {
        mnicapacitacion.addMenuListener(mostrarMensajeLogin());
    } else {
        JMenuItem subMostrarCurso = crearItem("Mostrar Cursos disponibles", null);
        mnicapacitacion.add(subMostrarCurso);
        subMostrarCurso.addActionListener(e -> NavegadorCapacitacion.irACapacitacion("mostrar"));

        if (rolUsuario.equalsIgnoreCase("Empleado") || rolUsuario.equalsIgnoreCase("Admin")) {
            JMenuItem subInscribircurso = crearItem("Inscribir Cliente", null);
            JMenuItem subConsultarCursos = crearItem("Consulta de Cursos", null);
            mnicapacitacion.add(subInscribircurso);
            mnicapacitacion.add(subConsultarCursos);

            subInscribircurso.addActionListener(e -> NavegadorCapacitacion.irACapacitacion("inscribir"));
            subConsultarCursos.addActionListener(e -> NavegadorCapacitacion.irACapacitacion("consultar"));
        }
    }
    barra.add(mnicapacitacion);
    personalizarPopup(mnicapacitacion);

    // ==== Menú Préstamo ====
    JMenu mniprestamo = new JMenu("Préstamo");
    mniprestamo.setIcon(obtenerIcono("/icons/saldo.png"));
    aplicarEstiloMenu(mniprestamo);

    if (rolUsuario.equalsIgnoreCase("Admin")) {
        JMenuItem subRegistrar = crearItem("Registrar Préstamo", null);
        JMenuItem subVer = crearItem("Ver Préstamos", null);
        mniprestamo.add(subRegistrar);
        mniprestamo.add(subVer);

        subRegistrar.addActionListener(e -> NavegadorPrestamo.irACapacitacion("Registrar"));
        subVer.addActionListener(e -> NavegadorPrestamo.irACapacitacion("Ver"));
    } else {
        mniprestamo.addMenuListener(mostrarMensajeLogin());
    }
    barra.add(mniprestamo);
    personalizarPopup(mniprestamo);

    // ==== Menú Seguimiento ====
    JMenu mniseguimiento = new JMenu("Seguimiento");
    mniseguimiento.setIcon(obtenerIcono("/icons/busqueda.png"));
    aplicarEstiloMenu(mniseguimiento);

    if (rolUsuario.equalsIgnoreCase("Admin")) {
        JMenuItem subRegistrartransaccion = crearItem("Registrar Transacción", null);
        JMenuItem subReporte = crearItem("Generar Reporte", null);
        JMenuItem subConsultaBalance = crearItem("Consultar Balance", null);

        mniseguimiento.add(subRegistrartransaccion);
        mniseguimiento.add(subReporte);
        mniseguimiento.add(subConsultaBalance);

        subRegistrartransaccion.addActionListener(e -> NavegadorSeguimiento.irACapacitacion("RegistrarT"));
        subReporte.addActionListener(e -> NavegadorSeguimiento.irACapacitacion("Reporte"));
        subConsultaBalance.addActionListener(e -> NavegadorSeguimiento.irACapacitacion("Balance"));
    } else {
        mniseguimiento.addMenuListener(mostrarMensajeLogin());
    }
    barra.add(mniseguimiento);
    personalizarPopup(mniseguimiento);

    // Panel de relleno
    JPanel panelFondo = new JPanel();
    panelFondo.setOpaque(true);
    panelFondo.setBackground(mcolorFondo);
    panelFondo.setPreferredSize(new Dimension(2000, 40));
    barra.add(panelFondo);

        return barra;
    }
    
    private static MenuListener mostrarMensajeLogin() {
    return new MenuListener() {
        public void menuSelected(MenuEvent e) {
            JOptionPane.showMessageDialog(null, "Debe iniciar sesión o registrarse para acceder a esta opción.", "Acceso restringido", JOptionPane.WARNING_MESSAGE);
        }
        public void menuDeselected(MenuEvent e) {}
        public void menuCanceled(MenuEvent e) {}
    };
}
    
private static void personalizarPopup(JMenu menu) {
    JPopupMenu popup = menu.getPopupMenu();
    popup.setBackground(mcolorFondo);
    popup.setOpaque(true);
    popup.setBorder(BorderFactory.createEmptyBorder());
}


    private static JMenuItem crearItem(String texto, String iconPath) {
        JMenuItem item = new JMenuItem(texto, iconPath != null ? obtenerIcono(iconPath) : null);
        item.setOpaque(true);
        item.setPreferredSize(new Dimension(165, 40));
        aplicarCursorMano(item);
        item.setBackground(mcolorFondo);
        item.setForeground(Color.WHITE);
        aplicarHover(item);
        return item;
    }

    private static void aplicarEstiloMenu(JMenu menu) {
        menu.setOpaque(true);
        menu.setBackground(mcolorFondo);
        menu.setForeground(Color.WHITE);
        aplicarCursorMano(menu);
        aplicarHover(menu);
    }
    
    public static void aplicarCursorMano(Component comp) {
    comp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }


    private static void aplicarHover(JMenuItem item) {
        item.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                item.setBackground(new Color(63, 81, 181));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                item.setBackground(mcolorFondo);
            }
        });
    }

    private static Icon obtenerIcono(String ruta) {
        return new ImageIcon(new ImageIcon(MenuPersonalizado.class.getResource(ruta)).getImage().getScaledInstance(30, 30, 0));
    }
}
