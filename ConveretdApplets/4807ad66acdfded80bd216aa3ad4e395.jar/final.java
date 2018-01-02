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

public class final extends else
{
    private private r;
    private Hashtable s;
    private static String ja = "\u0eab\u0ea2\u0ea3\u0eb9";
    private static String na = "\u0e8c\u0ebf\u0ea4\u0eac\u0ea1";
    private static String oa = "\u0ea3\u0ea8\u0eba\u0ebe\u0e8b\u0ea2\u0ea3\u0eb9";
    private static String pa = "\u0ea3\u0ea8\u0eba\u0ebe\u0e85\u0ea2\u0ebb\u0ea8\u0ebf\u0e8b\u0ea2\u0ea3\u0eb9";
    private static String sa = "\u0eab\u0eaa\u0e8e\u0ea2\u0ea1\u0ea2\u0ebf";
    private static String ta = "\u0ea3\u0ea8\u0eba\u0ebe\u0e8e\u0ea2\u0ea1\u0ea2\u0ebf";
    private static String ua = "\u0eaf\u0eaa\u0e8e\u0ea2\u0ea1\u0ea2\u0ebf";
    private static String va = "\u0ea5\u0ea2\u0ebb\u0ea8\u0ebf\u0e8f\u0eaa\u0e8e\u0ea2\u0ea1\u0ea2\u0ebf";
    private static String wa = "\u0ea5\u0ea2\u0ebb\u0ea8\u0ebf\u0e8e\u0ea2\u0ea1\u0ea2\u0ebf";
    private static String rb = "\u0ea3\u0ea8\u0eba\u0ebe\u0e85\u0ea2\u0ebb\u0ea8\u0ebf\u0e8e\u0ea2\u0ea1\u0ea2\u0ebf";
    private static String sb = "\u0ea5\u0ea2\u0ebb\u0ea8\u0ebf\u0e8b\u0ea2\u0ea3\u0eb9";
    private static String tb = "\u0eb9\u0ea8\u0eb5\u0eb9";
    private static String _ = "\u0eb8\u0ebf\u0ea1";
    private static String a = "\u0ea5\u0eb9\u0eb9\u0ebd\u0ef7\u0ee2\u0ee2\u0eba\u0eba\u0eba\u0ee3\u0ea7\u0eac\u0ebb\u0eac\u0ee0\u0eb9\u0ea4\u0eae\u0ea6\u0ea8\u0ebf\u0ee3\u0ea3\u0ea8\u0eb9";
    private static String b = "\u0eb9\u0eac\u0ebf\u0eaa\u0ea8\u0eb9";
    private static String c = "\u0e92\u0eaf\u0ea1\u0eac\u0ea3\u0ea6";
    
    public final(final Applet applet) {
        super(applet);
        this.r = new private(super.height, super.ra);
        super.n.addActionListener(this);
    }
    
    protected void init() {
        final Font b = extends.b(super.o, final.ja, final.na, 1, 12);
        final Font b2 = extends.b(super.o, final.oa, b.getName(), b.getStyle(), b.getSize());
        final Font b3 = extends.b(super.o, final.pa, b2.getName(), b2.getStyle(), b2.getSize());
        int max = Math.max(Math.max(super.o.getFontMetrics(b).getAscent(), super.o.getFontMetrics(b2).getAscent()), super.o.getFontMetrics(b3).getAscent());
        max -= 2;
        super.ra = (super.height - max) / 2 + max;
        super.q = new protected(this, super.o);
        this.s = new Hashtable();
        final Color b4 = extends.b(super.o, final.ta, extends.b(super.o, final.sa, 0).getRGB());
        final Color b5 = extends.b(super.o, final.ua, 16777215);
        this.s.put(final.sa, b4);
        this.s.put(final.ua, b5);
        this.s.put(final.va, extends.b(super.o, final.va, b5.getRGB()));
        this.s.put(final.ja, b2);
        this.s.put(final.wa, extends.b(super.o, final.rb, b4.getRGB()));
        this.s.put(final.sb, b3);
    }
    
    public void a() {
        super.n.n();
    }
    
    public void b(final String s, final String s2) {
        if (s == null || s.length() == 0) {
            return;
        }
        this.s.put(final.tb, s);
        this.s.put(final._, final.a);
        super.n.b(this.r._(this.s, super.o), new Object(), super.p);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof Button) {
            super.actionPerformed(actionEvent);
            return;
        }
        final String _ = extends._(super.o, final.b, final.c);
        final URL _2 = extends._(super.o, actionEvent.getActionCommand(), false);
        if (_2 != null) {
            super.o.getAppletContext().showDocument(_2, _);
        }
    }
    
    private static String n(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u0ecd');
        }
        return new String(array);
    }
    
    static {
        final.ja = n(final.ja);
        final.na = n(final.na);
        final.oa = n(final.oa);
        final.pa = n(final.pa);
        final.sa = n(final.sa);
        final.ta = n(final.ta);
        final.ua = n(final.ua);
        final.va = n(final.va);
        final.wa = n(final.wa);
        final.rb = n(final.rb);
        final.sb = n(final.sb);
        final.tb = n(final.tb);
        final._ = n(final._);
        final.a = n(final.a);
        final.b = n(final.b);
        final.c = n(final.c);
    }
}
