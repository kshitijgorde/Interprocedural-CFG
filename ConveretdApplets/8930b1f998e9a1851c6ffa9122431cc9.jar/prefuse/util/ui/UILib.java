// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.ui;

import java.awt.Font;
import java.awt.Container;
import java.awt.Color;
import java.awt.ComponentOrientation;
import javax.swing.JComponent;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.UIManager;
import java.awt.event.InputEvent;

public class UILib
{
    public static boolean isButtonPressed(final InputEvent inputEvent, final int n) {
        return (inputEvent.getModifiers() & n) == n;
    }
    
    public static final void setPlatformLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex) {}
    }
    
    public static Box getBox(final Component[] array, final boolean b, final int n, final int n2) {
        return getBox(array, b, n, n, n2);
    }
    
    public static Box getBox(final Component[] array, final boolean b, final int n, final int n2, final int n3) {
        final Box box = new Box(b ? 0 : 1);
        addStrut(box, b, n);
        for (int i = 0; i < array.length; ++i) {
            if (i > 0) {
                addStrut(box, b, n3);
                addGlue(box, b);
            }
            box.add(array[i]);
        }
        addStrut(box, b, n2);
        return box;
    }
    
    public static void addStrut(final JComponent component, final boolean b, final int n) {
        if (n < 1) {
            return;
        }
        component.add(b ? Box.createHorizontalStrut(n) : Box.createVerticalStrut(n));
    }
    
    public static void addGlue(final JComponent component, final boolean b) {
        component.add(b ? Box.createHorizontalGlue() : Box.createVerticalGlue());
    }
    
    public static void addStrut(final JComponent component, final int n, final int n2) {
        if (n2 < 1) {
            return;
        }
        component.add((getAxis(component, n) == 0) ? Box.createHorizontalStrut(n2) : Box.createVerticalStrut(n2));
    }
    
    public static void addGlue(final JComponent component, final int n) {
        component.add((getAxis(component, n) == 0) ? Box.createHorizontalGlue() : Box.createVerticalGlue());
    }
    
    public static int getAxis(final JComponent component, final int n) {
        final ComponentOrientation componentOrientation = component.getComponentOrientation();
        switch (n) {
            case 2: {
                return componentOrientation.isHorizontal() ? 0 : 1;
            }
            case 3: {
                return componentOrientation.isHorizontal() ? 1 : 0;
            }
            default: {
                return n;
            }
        }
    }
    
    public static void setColor(final Component component, final Color background, final Color foreground) {
        component.setBackground(background);
        component.setForeground(foreground);
        if (component instanceof Container) {
            final Container container = (Container)component;
            for (int i = 0; i < container.getComponentCount(); ++i) {
                setColor(container.getComponent(i), background, foreground);
            }
        }
    }
    
    public static void setFont(final Component component, final Font font) {
        component.setFont(font);
        if (component instanceof Container) {
            final Container container = (Container)component;
            for (int i = 0; i < container.getComponentCount(); ++i) {
                setFont(container.getComponent(i), font);
            }
        }
    }
}
