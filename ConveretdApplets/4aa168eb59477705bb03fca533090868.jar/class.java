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

public class class extends case
{
    private default C;
    private Hashtable D;
    private static String a = "\ud9f3\ud9fa\ud9fb\ud9e1";
    private static String b = "\ud9d4\ud9e7\ud9fc\ud9f4\ud9f9";
    private static String c = "\ud9fd\ud9fa\ud9e3\ud9f0\ud9e7\ud9d3\ud9fa\ud9fb\ud9e1";
    private static String d = "\ud9f3\ud9f2\ud9d6\ud9fa\ud9f9\ud9fa\ud9e7";
    private static String e = "\ud9f7\ud9f2\ud9d6\ud9fa\ud9f9\ud9fa\ud9e7";
    private static String f = "\ud9fd\ud9fa\ud9e3\ud9f0\ud9e7\ud9d3\ud9f2\ud9d6\ud9fa\ud9f9\ud9fa\ud9e7";
    private static String g = "\ud9fd\ud9fa\ud9e3\ud9f0\ud9e7\ud9d7\ud9f2\ud9d6\ud9fa\ud9f9\ud9fa\ud9e7";
    private static String h = "\ud9e1\ud9f0\ud9ed\ud9e1";
    private static String i = "\ud9e0\ud9e7\ud9f9";
    private static String I = "\ud9e1\ud9f4\ud9e7\ud9f2\ud9f0\ud9e1";
    private static String J = "\ud9ca\ud9f7\ud9f9\ud9f4\ud9fb\ud9fe";
    
    public class(final Applet applet) {
        super(applet);
        this.C = new default(super.height, super.B);
        super.E.addActionListener(this);
    }
    
    protected void init() {
        final Font b = catch.b(super.F, class.a, class.b, 1, 12);
        final Font b2 = catch.b(super.F, class.c, b.getName(), b.getStyle(), b.getSize());
        int max = Math.max(Math.max(super.F.getFontMetrics(b).getAscent(), super.F.getFontMetrics(b).getAscent()), super.F.getFontMetrics(b2).getAscent());
        max -= 2;
        super.B = (super.height - max) / 2 + max;
        super.G = new else(this, super.F);
        this.D = new Hashtable();
        final Color b3 = catch.b(super.F, class.d, 0);
        final Color b4 = catch.b(super.F, class.e, 16777215);
        this.D.put(class.d, b3);
        this.D.put(class.e, b4);
        this.D.put(class.a, b);
        this.D.put(class.f, catch.b(super.F, class.f, 11141120));
        this.D.put(class.g, catch.b(super.F, class.g, b4.getRGB()));
        this.D.put(class.c, b2);
    }
    
    public void b() {
        super.E.j();
    }
    
    public void b(final String s, final String s2) {
        if (s == null || s.length() == 0) {
            return;
        }
        this.D.put(class.h, s);
        if (s2 != null) {
            this.D.put(class.i, s2);
        }
        else {
            this.D.remove(class.i);
        }
        super.E.a(this.C.a(this.D, super.F), new Object(), super.H);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof Button) {
            super.actionPerformed(actionEvent);
            return;
        }
        final String _ = catch._(super.F, class.I, class.J);
        final URL _2 = catch._((Object)super.F, actionEvent.getActionCommand(), false);
        if (_2 != null) {
            super.F.getAppletContext().showDocument(_2, _);
        }
    }
    
    private static String e(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\ud995');
        }
        return new String(array);
    }
    
    static {
        class.a = e(class.a);
        class.b = e(class.b);
        class.c = e(class.c);
        class.d = e(class.d);
        class.e = e(class.e);
        class.f = e(class.f);
        class.g = e(class.g);
        class.h = e(class.h);
        class.i = e(class.i);
        class.I = e(class.I);
        class.J = e(class.J);
    }
}
