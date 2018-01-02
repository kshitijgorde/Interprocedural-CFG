import java.net.URL;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.applet.Applet;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class interface extends import
{
    private this d;
    private Hashtable e;
    private static String V = "\u8d5e\u8d57\u8d56\u8d4c";
    private static String W = "\u8d79\u8d4a\u8d51\u8d59\u8d54";
    private static String _a = "\u8d56\u8d5d\u8d4f\u8d4b\u8d7e\u8d57\u8d56\u8d4c";
    private static String aa = "\u8d56\u8d5d\u8d4f\u8d4b\u8d70\u8d57\u8d4e\u8d5d\u8d4a\u8d7e\u8d57\u8d56\u8d4c";
    private static String ba = "\u8d5e\u8d5f\u8d7b\u8d57\u8d54\u8d57\u8d4a";
    private static String fa = "\u8d56\u8d5d\u8d4f\u8d4b\u8d7b\u8d57\u8d54\u8d57\u8d4a";
    private static String ga = "\u8d5a\u8d5f\u8d7b\u8d57\u8d54\u8d57\u8d4a";
    private static String ha = "\u8d50\u8d57\u8d4e\u8d5d\u8d4a\u8d7a\u8d5f\u8d7b\u8d57\u8d54\u8d57\u8d4a";
    private static String ia = "\u8d50\u8d57\u8d4e\u8d5d\u8d4a\u8d7e\u8d5f\u8d7b\u8d57\u8d54\u8d57\u8d4a";
    private static String db = "\u8d56\u8d5d\u8d4f\u8d4b\u8d70\u8d57\u8d4e\u8d5d\u8d4a\u8d7b\u8d57\u8d54\u8d57\u8d4a";
    private static String eb = "\u8d50\u8d57\u8d4e\u8d5d\u8d4a\u8d7e\u8d57\u8d56\u8d4c";
    private static String fb = "\u8d4c\u8d5d\u8d40\u8d4c";
    private static String gb = "\u8d4d\u8d4a\u8d54";
    private static String hb = "\u8d4c\u8d59\u8d4a\u8d5f\u8d5d\u8d4c";
    private static String ib = "\u8d67\u8d5a\u8d54\u8d59\u8d56\u8d53";
    
    public interface(final Applet applet) {
        super(applet);
        this.d = new this(super.height, super.ea);
        super._.addActionListener(this);
    }
    
    protected void init() {
        final Font a = instanceof.a(super.a, interface.V, interface.W, 1, 12);
        final Font a2 = instanceof.a(super.a, interface._a, a.getName(), a.getStyle(), a.getSize());
        final Font a3 = instanceof.a(super.a, interface.aa, a2.getName(), a2.getStyle(), a2.getSize());
        int max = Math.max(Math.max(super.a.getFontMetrics(a).getAscent(), super.a.getFontMetrics(a2).getAscent()), super.a.getFontMetrics(a3).getAscent());
        max -= 2;
        super.ea = (super.height - max) / 2 + max;
        super.c = new throw(this, super.a);
        this.e = new Hashtable();
        final Color b = instanceof.b(super.a, interface.fa, instanceof.b(super.a, interface.ba, 0).getRGB());
        final Color b2 = instanceof.b(super.a, interface.ga, 16777215);
        this.e.put(interface.ba, b);
        this.e.put(interface.ga, b2);
        this.e.put(interface.ha, instanceof.b(super.a, interface.ha, b2.getRGB()));
        this.e.put(interface.V, a2);
        this.e.put(interface.ia, instanceof.b(super.a, interface.db, b.getRGB()));
        this.e.put(interface.eb, a3);
    }
    
    public void _() {
        super._.d();
    }
    
    public void a(final String s, final String s2) {
        if (s == null || s.length() == 0) {
            return;
        }
        this.e.put(interface.fb, s);
        if (s2 != null) {
            this.e.put(interface.gb, s2);
        }
        else {
            this.e.remove(interface.gb);
        }
        super._.a(this.d.a(this.e, super.a), new Object(), super.b);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof Button) {
            super.actionPerformed(actionEvent);
            return;
        }
        final String _ = instanceof._(super.a, interface.hb, interface.ib);
        final URL _2 = instanceof._((Object)super.a, actionEvent.getActionCommand(), false);
        if (_2 != null) {
            super.a.getAppletContext().showDocument(_2, _);
        }
    }
    
    private static String g(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u8d38');
        }
        return new String(array);
    }
    
    static {
        interface.V = g(interface.V);
        interface.W = g(interface.W);
        interface._a = g(interface._a);
        interface.aa = g(interface.aa);
        interface.ba = g(interface.ba);
        interface.fa = g(interface.fa);
        interface.ga = g(interface.ga);
        interface.ha = g(interface.ha);
        interface.ia = g(interface.ia);
        interface.db = g(interface.db);
        interface.eb = g(interface.eb);
        interface.fb = g(interface.fb);
        interface.gb = g(interface.gb);
        interface.hb = g(interface.hb);
        interface.ib = g(interface.ib);
    }
}
