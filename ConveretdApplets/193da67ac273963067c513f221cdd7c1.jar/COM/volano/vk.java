// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.Panel;

public abstract class vk extends Panel
{
    private static final String[] a;
    private static boolean b;
    
    public static vk b(final String s) {
        vk vk = null;
        final boolean b = s.equals("1.5.0") || s.equals("1.5.0_01") || s.startsWith("1.5.0-beta");
        try {
            vk = (vk)((COM.volano.vk.b && b) ? Class.forName("COM/volano/vj".replace('/', '.')).newInstance() : ((vk)Class.forName("COM/volano/vl".replace('/', '.')).newInstance()));
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
        return vk;
    }
    
    public abstract boolean a();
    
    public abstract void a(final boolean p0);
    
    public abstract void a(final String p0);
    
    static {
        a = new String[] { "javax.swing.JPanel", "javax.swing.JScrollBar", "javax.swing.JScrollPane", "javax.swing.JTextArea", "javax.swing.ScrollPaneConstants", "javax.swing.SwingUtilities", "javax.swing.event.DocumentEvent", "javax.swing.event.DocumentListener", "javax.swing.text.BadLocationException", "javax.swing.text.DefaultCaret" };
        try {
            for (int i = 0; i < vk.a.length; ++i) {
                Class.forName(vk.a[i]);
            }
            vk.b = true;
        }
        catch (ClassNotFoundException ex) {}
    }
}
