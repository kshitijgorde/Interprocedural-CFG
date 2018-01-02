// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.util.StringTokenizer;
import javax.swing.text.JTextComponent;
import javax.swing.plaf.IconUIResource;
import java.io.InputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.BufferedInputStream;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.plaf.UIResource;

public abstract class LookAndFeel
{
    private static ModifierKeyword[] modifierKeywords;
    static /* synthetic */ Class class$java$awt$event$KeyEvent;
    
    static {
        LookAndFeel.modifierKeywords = new ModifierKeyword[] { new ModifierKeyword("shift", 1), new ModifierKeyword("control", 2), new ModifierKeyword("meta", 4), new ModifierKeyword("alt", 8), new ModifierKeyword("button1", 16), new ModifierKeyword("button2", 8), new ModifierKeyword("button3", 4) };
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public UIDefaults getDefaults() {
        return null;
    }
    
    public abstract String getDescription();
    
    public abstract String getID();
    
    public abstract String getName();
    
    public void initialize() {
    }
    
    public static void installBorder(final JComponent component, final String s) {
        final Border border = component.getBorder();
        if (border == null || border instanceof UIResource) {
            component.setBorder(UIManager.getBorder(s));
        }
    }
    
    public static void installColors(final JComponent component, final String s, final String s2) {
        final Color background = component.getBackground();
        if (background == null || background instanceof UIResource) {
            component.setBackground(UIManager.getColor(s));
        }
        final Color foreground = component.getForeground();
        if (foreground == null || foreground instanceof UIResource) {
            component.setForeground(UIManager.getColor(s2));
        }
    }
    
    public static void installColorsAndFont(final JComponent component, final String s, final String s2, final String s3) {
        final Font font = component.getFont();
        if (font == null || font instanceof UIResource) {
            component.setFont(UIManager.getFont(s3));
        }
        installColors(component, s, s2);
    }
    
    public abstract boolean isNativeLookAndFeel();
    
    public abstract boolean isSupportedLookAndFeel();
    
    public static Object makeIcon(final Class clazz, final String s) {
        return new UIDefaults.LazyValue() {
            private final /* synthetic */ Class val$baseClass = val$baseClass;
            
            public Object createValue(final UIDefaults uiDefaults) {
                final byte[][] array = new byte[1][];
                SwingUtilities.doPrivileged(new Runnable() {
                    private final /* synthetic */ Class val$baseClass = val$baseClass;
                    
                    public void run() {
                        try {
                            final InputStream resourceAsStream = this.val$baseClass.getResourceAsStream(s);
                            if (resourceAsStream == null) {
                                return;
                            }
                            final BufferedInputStream bufferedInputStream = new BufferedInputStream(resourceAsStream);
                            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
                            array[0] = new byte[1024];
                            int read;
                            while ((read = bufferedInputStream.read(array[0])) > 0) {
                                byteArrayOutputStream.write(array[0], 0, read);
                            }
                            bufferedInputStream.close();
                            byteArrayOutputStream.flush();
                            array[0] = byteArrayOutputStream.toByteArray();
                        }
                        catch (IOException ex) {
                            System.err.println(ex.toString());
                        }
                    }
                });
                if (array[0] == null) {
                    System.err.println(String.valueOf(this.val$baseClass.getName()) + "/" + s + " not found.");
                    return null;
                }
                if (array[0].length == 0) {
                    System.err.println("warning: " + s + " is zero-length");
                    return null;
                }
                return new IconUIResource(new ImageIcon(array[0]));
            }
        };
    }
    
    public static JTextComponent.KeyBinding[] makeKeyBindings(final Object[] array) {
        final JTextComponent.KeyBinding[] array2 = new JTextComponent.KeyBinding[array.length / 2];
        for (int i = 0; i < array.length; i += 2) {
            array2[i / 2] = new JTextComponent.KeyBinding((array[i] instanceof KeyStroke) ? ((KeyStroke)array[i]) : parseKeyStroke((String)array[i]), (String)array[i + 1]);
        }
        return array2;
    }
    
    private static KeyStroke parseKeyStroke(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        int n = 0;
        String nextToken;
        while ((nextToken = stringTokenizer.nextToken()) != null) {
            int modifierMask = 0;
            for (int n2 = 0; modifierMask == 0 && n2 < LookAndFeel.modifierKeywords.length; modifierMask = LookAndFeel.modifierKeywords[n2].getModifierMask(nextToken), ++n2) {}
            if (modifierMask == 0) {
                final String string = "VK_" + nextToken;
                int int1;
                try {
                    int1 = ((LookAndFeel.class$java$awt$event$KeyEvent != null) ? LookAndFeel.class$java$awt$event$KeyEvent : (LookAndFeel.class$java$awt$event$KeyEvent = class$("java.awt.event.KeyEvent"))).getField(string).getInt((LookAndFeel.class$java$awt$event$KeyEvent != null) ? LookAndFeel.class$java$awt$event$KeyEvent : (LookAndFeel.class$java$awt$event$KeyEvent = class$("java.awt.event.KeyEvent")));
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    throw new Error("Unrecognized keycode name: " + string);
                }
                return KeyStroke.getKeyStroke(int1, n);
            }
            n |= modifierMask;
        }
        throw new Error("Can't parse KeyStroke: \"" + s + "\"");
    }
    
    public String toString() {
        return "[" + this.getDescription() + " - " + this.getClass().getName() + "]";
    }
    
    public void uninitialize() {
    }
    
    public static void uninstallBorder(final JComponent component) {
        if (component.getBorder() instanceof UIResource) {
            component.setBorder(null);
        }
    }
    
    private static class ModifierKeyword
    {
        final String keyword;
        final int mask;
        
        ModifierKeyword(final String keyword, final int mask) {
            this.keyword = keyword;
            this.mask = mask;
        }
        
        int getModifierMask(final String s) {
            return s.equals(this.keyword) ? this.mask : 0;
        }
    }
}
