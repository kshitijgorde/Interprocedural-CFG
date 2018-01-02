// 
// Decompiled by Procyon v0.5.30
// 

package b.a.d;

import javax.swing.InputMap;
import java.awt.Component;
import javax.swing.KeyStroke;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.UIManager;
import java.awt.Color;

public class b
{
    private static boolean a;
    
    public static Color a(final Color color, final Color color2) {
        return new Color((color.getRed() + color2.getRed() + 1) / 2, (color.getGreen() + color2.getGreen() + 1) / 2, (color.getBlue() + color2.getBlue() + 1) / 2, (color.getAlpha() + color2.getAlpha() + 1) / 2);
    }
    
    public static boolean a() {
        return "MacOS".equals(UIManager.getLookAndFeel().getName()) || "Mac OS X Aqua".equals(UIManager.getLookAndFeel().getName());
    }
    
    public static boolean b() {
        return "Metal".equals(UIManager.getLookAndFeel().getName());
    }
    
    public static boolean c() {
        return "Windows".equals(UIManager.getLookAndFeel().getName());
    }
    
    public static void a(final Graphics graphics, final boolean b) {
        if (graphics instanceof Graphics2D) {
            ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, b ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        }
    }
    
    public static void a(JComponent component) {
        if (b() && d.a()) {
            if (component instanceof JComboBox) {
                final Component editorComponent = ((JComboBox)component).getEditor().getEditorComponent();
                if (editorComponent instanceof JComponent) {
                    component = (JComponent)editorComponent;
                }
            }
            final InputMap inputMap = component.getInputMap();
            final KeyStroke[] allKeys = inputMap.allKeys();
            if (allKeys != null) {
                for (int i = 0; i < allKeys.length; ++i) {
                    final KeyStroke keyStroke = allKeys[i];
                    final String string = keyStroke.toString();
                    if (string.indexOf("ctrl ") >= 0) {
                        final Object value = inputMap.get(keyStroke);
                        final String a = c.a(string, "ctrl ", "meta ");
                        inputMap.remove(keyStroke);
                        inputMap.put(KeyStroke.getKeyStroke(a), value);
                    }
                }
            }
        }
    }
    
    static {
        b.a = (d.a() && d.b("1.4") < 0);
    }
}
