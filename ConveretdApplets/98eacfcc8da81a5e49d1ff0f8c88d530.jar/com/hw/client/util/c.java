// 
// Decompiled by Procyon v0.5.30
// 

package com.hw.client.util;

import javax.swing.SwingUtilities;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.AbstractButton;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.net.URL;
import javax.swing.ImageIcon;

public final class c
{
    private static c a;
    
    public static ImageIcon a(final String s) {
        final URL resource;
        if ((resource = c.a.getClass().getResource(s)) != null) {
            return new ImageIcon(resource);
        }
        com.hw.client.util.a.e("Couldn't find file: " + s);
        return new ImageIcon();
    }
    
    public static JPanel a() {
        final JPanel panel;
        (panel = new JPanel()).setOpaque(false);
        panel.setMinimumSize(new Dimension(0, 0));
        panel.setPreferredSize(new Dimension(0, 0));
        return panel;
    }
    
    public static void a(final AbstractButton abstractButton) {
        final Icon icon;
        if ((icon = abstractButton.getIcon()) == null) {
            return;
        }
        abstractButton.setText(null);
        abstractButton.setBorder(null);
        a(abstractButton, new Dimension(icon.getIconWidth() + 2, icon.getIconHeight() + 2));
    }
    
    public static void a(final JComponent component, final Dimension dimension) {
        component.setPreferredSize(dimension);
        component.setMaximumSize(dimension);
        component.setMinimumSize(dimension);
        component.setSize(dimension);
    }
    
    public static void a(Component component) {
        if ((component = component).isVisible() && component.isShowing()) {
            if (SwingUtilities.isEventDispatchThread()) {
                component.repaint();
                return;
            }
            SwingUtilities.invokeLater(new i(component));
        }
    }
    
    static {
        c.a = new c();
    }
}
