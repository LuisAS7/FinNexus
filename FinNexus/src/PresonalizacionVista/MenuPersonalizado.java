/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PresonalizacionVista;

import Vista.Login;
import Vista.PRESTAMO_RAPIDO;
import javax.swing.*;
import java.awt.*;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 *
 * @author Asus
 */
public class MenuPersonalizado {
    private static final Color COLOR_FONDO = new Color(26, 35, 126);

    public static JMenuBar crearMenuBar(String rol) {
        if (rol == null) rol = "Invitado";   // Seguridad ante nulos

        boolean esAdmin    = "Admin".equalsIgnoreCase(rol);
        boolean esEmpleado = "Empleado".equalsIgnoreCase(rol);
        boolean esInvitado = "Invitado".equalsIgnoreCase(rol);
        // Cliente: no es admin, ni empleado, ni invitado
        boolean esCliente  = !esAdmin && !esEmpleado && !esInvitado;

        JMenuBar barra = new JMenuBar();
        barra.setBackground(COLOR_FONDO);
        barra.setPreferredSize(new Dimension(700, 50));

        /* ==== Menú Capacitación ==== */
        JMenu mCap = crearMenu("Capacitación", "/icons/inventario.png");
        if (esInvitado) {
            mCap.addMenuListener(mensajeLogin());
        } else {
            agregarItem(mCap, "Mostrar Cursos disponibles",
                        e -> NavegadorCapacitacion.irACapacitacion("mostrar"));

            if (esEmpleado || esAdmin) {
                agregarItem(mCap, "Inscribir Cliente",
                            e -> NavegadorCapacitacion.irACapacitacion("inscribir"));
                agregarItem(mCap, "Consulta de Cursos",
                            e -> NavegadorCapacitacion.irACapacitacion("consultar"));
            }
        }
        barra.add(mCap);

        /* ==== Menú Préstamo ==== */
        JMenu mPrest = crearMenu("Préstamo", "/icons/saldo.png");
        if (esAdmin) {
            agregarItem(mPrest, "Registrar Préstamo",
                        e -> NavegadorPrestamo.irACapacitacion("Registrar"));
            agregarItem(mPrest, "Ver Préstamos",
                        e -> NavegadorPrestamo.irACapacitacion("Ver"));
        } else {
            mPrest.addMenuListener(mensajeLogin());
        }
        barra.add(mPrest);

        /* ==== Menú Seguimiento ==== */
        JMenu mSeg = crearMenu("Seguimiento", "/icons/busqueda.png");
        if (esAdmin) {
            agregarItem(mSeg, "Registrar Transacción",
                        e -> NavegadorSeguimiento.irACapacitacion("RegistrarT"));
            agregarItem(mSeg, "Generar Reporte",
                        e -> NavegadorSeguimiento.irACapacitacion("Reporte"));
            agregarItem(mSeg, "Consultar Balance",
                        e -> NavegadorSeguimiento.irACapacitacion("Balance"));
        } else {
            mSeg.addMenuListener(mensajeLogin());
        }
        barra.add(mSeg);
        
      JMenu mPrestamoRapido = crearMenu("Préstamo Rápido", "/icons/money.png");
        agregarItem(mPrestamoRapido, "Ingresar", e -> {
            JFrame actual = (JFrame) SwingUtilities.getWindowAncestor(barra);
            if (actual != null) {
                actual.dispose(); // Cierra el frame actual
            }
            new PRESTAMO_RAPIDO().setVisible(true); // Abre el nuevo frame
        });
       
        barra.add(mPrestamoRapido);

        /* Panel de relleno — mantiene color de fondo */
        JPanel filler = new JPanel();
        filler.setPreferredSize(new Dimension(2000, 40));
        filler.setBackground(COLOR_FONDO);
        barra.add(filler);
        
        
        
                /* ---- separador que empuja lo que sigue hacia la derecha ---- */
        barra.add(Box.createHorizontalGlue());

        /* ==== Menú / Label  Iniciar Sesión  ==== */
        JMenu mLogin = crearMenu("Iniciar Sesión", "/icons/user.png"); // usa tu propio icono
        mLogin.addMenuListener(new MenuListener() {                     // disponible para todos
            public void menuSelected(MenuEvent e) {
                new Login().setVisible(true);   // abre tu ventana de login
                mLogin.getPopupMenu().setVisible(false); // evita desplegar popup vacío
            }
            public void menuDeselected(MenuEvent e) {}
            public void menuCanceled(MenuEvent e)   {}
        });
        barra.add(mLogin);
        

        return barra;
    }
    
   private static JMenu crearMenu(String texto, String icono) {
        JMenu menu = new JMenu(texto);
        menu.setIcon(icono != null ? obtenerIcono(icono) : null);
        estilizar(menu);
        personalizarPopup(menu);
        return menu;
    }

    private static void agregarItem(JMenu menu, String texto, java.awt.event.ActionListener al) {
        JMenuItem item = new JMenuItem(texto);
        item.addActionListener(al);
        estilizar(item);
        menu.add(item);
    }

    private static void estilizar(JComponent comp) {
        comp.setOpaque(true);
        comp.setBackground(COLOR_FONDO);
        comp.setForeground(Color.WHITE);
        comp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        comp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { comp.setBackground(new Color(63,81,181)); }
            public void mouseExited(java.awt.event.MouseEvent e)  { comp.setBackground(COLOR_FONDO); }
        });
    }

    private static void personalizarPopup(JMenu menu) {
        JPopupMenu popup = menu.getPopupMenu();
        popup.setBackground(COLOR_FONDO);
        popup.setBorder(BorderFactory.createEmptyBorder());
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

    private static Icon obtenerIcono(String ruta) {
        return new ImageIcon(new ImageIcon(MenuPersonalizado.class.getResource(ruta))
                             .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
    }
}
