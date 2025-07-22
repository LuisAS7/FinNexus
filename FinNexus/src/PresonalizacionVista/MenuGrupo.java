package PresonalizacionVista;


import PresonalizacionVista.MenuPersonalizado;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Asus
 */
public class MenuGrupo {
    private final JMenu menu;

    public MenuGrupo(String titulo, String icono) {
        menu = new JMenu(titulo);
        menu.setIcon(obtenerIcono(icono));
        estilizar(menu);
        personalizarPopup(menu);
    }

    // Este método añade una opción si el usuario tiene uno de los roles permitidos
    public void agregarOpcionSiRol(String texto, ActionListener accion, String icono, String rolUsuario, String... rolesPermitidos) {
        for (String rol : rolesPermitidos) {
            if (rol.equalsIgnoreCase(rolUsuario)) {
                agregarOpcion(texto, accion, icono);
                return;
            }
        }
    }

    // Este método añade una opción normalmente
    public void agregarOpcion(String texto, ActionListener accion, String icono) {
        JMenuItem item = new JMenuItem(texto, obtenerIcono(icono));
        item.addActionListener(accion);
        estilizar(item);
        menu.add(item);
    }

        private static void estilizar(JComponent comp) {
    comp.setOpaque(true);
    comp.setBackground(new Color(175, 26, 70)); // Puedes parametrizarlo si deseas
    comp.setForeground(Color.WHITE);
    comp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    comp.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent e) {
            comp.setBackground(new Color(63, 81, 181));
        }

        public void mouseExited(java.awt.event.MouseEvent e) {
            comp.setBackground(new Color(175, 26, 70));
        }
    });
}

private static void personalizarPopup(JMenu menu) {
    JPopupMenu popup = menu.getPopupMenu();
    popup.setBackground(new Color(175, 26, 70));
    popup.setBorder(BorderFactory.createEmptyBorder());
}

private static Icon obtenerIcono(String ruta) {
    if (ruta == null || ruta.isBlank()) {
        return null; // o puedes retornar un ícono por defecto si lo prefieres
    }

    URL recurso = MenuGrupo.class.getResource(ruta);
    if (recurso == null) {
        System.err.println("Icono no encontrado: " + ruta);
        return null;
    }

    ImageIcon original = new ImageIcon(recurso);
    Image imagenEscalada = original.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    return new ImageIcon(imagenEscalada);
}

public static JMenu crearMenu(String texto, String icono) {
    JMenu menu = new JMenu(texto);
    if (icono != null) {
        menu.setIcon(obtenerIcono(icono));
    }
    estilizar(menu);
    personalizarPopup(menu);
    return menu;
}

public void construirSobre(JMenuBar barra) {
    barra.add(menu);          // inserta este sub-menú en la barra
}

    public JMenu obtenerMenu() {
        return menu;
    }
    

}
