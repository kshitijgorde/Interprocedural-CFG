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

public class n extends l
{
    private class La;
    private Hashtable Ma;
    private static String l = "\u7d62\u7d6b\u7d6a\u7d70";
    private static String m = "\u7d45\u7d76\u7d6d\u7d65\u7d68";
    private static String o = "\u7d6a\u7d61\u7d73\u7d77\u7d42\u7d6b\u7d6a\u7d70";
    private static String p = "\u7d6a\u7d61\u7d73\u7d77\u7d4c\u7d6b\u7d72\u7d61\u7d76\u7d42\u7d6b\u7d6a\u7d70";
    private static String s = "\u7d62\u7d63\u7d47\u7d6b\u7d68\u7d6b\u7d76";
    private static String u = "\u7d6a\u7d61\u7d73\u7d77\u7d47\u7d6b\u7d68\u7d6b\u7d76";
    private static String v = "\u7d66\u7d63\u7d47\u7d6b\u7d68\u7d6b\u7d76";
    private static String w = "\u7d6c\u7d6b\u7d72\u7d61\u7d76\u7d47\u7d6b\u7d68\u7d6b\u7d76";
    private static String O = "\u7d6a\u7d61\u7d73\u7d77\u7d4c\u7d6b\u7d72\u7d61\u7d76\u7d47\u7d6b\u7d68\u7d6b\u7d76";
    private static String P = "\u7d6c\u7d6b\u7d72\u7d61\u7d76\u7d42\u7d6b\u7d6a\u7d70";
    private static String sa = "\u7d70\u7d61\u7d7c\u7d70";
    private static String ta = "\u7d71\u7d76\u7d68";
    private static String ua = "\u7d70\u7d65\u7d76\u7d63\u7d61\u7d70";
    private static String va = "\u7d5b\u7d66\u7d68\u7d65\u7d6a\u7d6f";
    
    public n(final Applet applet) {
        super(applet);
        this.La = new class(super.height, super.t);
        super.Ha.addActionListener(this);
    }
    
    protected void init() {
        final Font _ = m._(super.Ia, n.l, n.m, 1, 12);
        final Font _2 = m._(super.Ia, n.o, _.getName(), _.getStyle(), _.getSize());
        final Font _3 = m._(super.Ia, n.p, _2.getName(), _2.getStyle(), _2.getSize());
        int max = Math.max(Math.max(super.Ia.getFontMetrics(_).getAscent(), super.Ia.getFontMetrics(_2).getAscent()), super.Ia.getFontMetrics(_3).getAscent());
        max -= 2;
        super.t = (super.height - max) / 2 + max;
        super.Ka = new const(this, super.Ia);
        this.Ma = new Hashtable();
        final Color a = m.a(super.Ia, n.u, m.a(super.Ia, n.s, 0).getRGB());
        this.Ma.put(n.s, a);
        this.Ma.put(n.v, m.a(super.Ia, n.v, 16777215));
        this.Ma.put(n.l, _2);
        this.Ma.put(n.w, m.a(super.Ia, n.O, a.getRGB()));
        this.Ma.put(n.P, _3);
    }
    
    public void _() {
        super.Ha.h();
    }
    
    public void _(final String s, final String s2) {
        if (s == null || s.length() == 0) {
            return;
        }
        this.Ma.put(n.sa, s);
        if (s2 != null) {
            this.Ma.put(n.ta, s2);
        }
        else {
            this.Ma.remove(n.ta);
        }
        super.Ha._(this.La.a(this.Ma, super.Ia), new Object(), super.Ja);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof Button) {
            super.actionPerformed(actionEvent);
            return;
        }
        final String a = m.a(super.Ia, n.ua, n.va);
        final URL b = m.b(super.Ia, actionEvent.getActionCommand(), false);
        if (b != null) {
            super.Ia.getAppletContext().showDocument(b, a);
        }
    }
    
    private static String c(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u7d04');
        }
        return new String(array);
    }
    
    static {
        n.l = c(n.l);
        n.m = c(n.m);
        n.o = c(n.o);
        n.p = c(n.p);
        n.s = c(n.s);
        n.u = c(n.u);
        n.v = c(n.v);
        n.w = c(n.w);
        n.O = c(n.O);
        n.P = c(n.P);
        n.sa = c(n.sa);
        n.ta = c(n.ta);
        n.ua = c(n.ua);
        n.va = c(n.va);
    }
}
