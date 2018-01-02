import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.Component;
import java.awt.Image;
import java.awt.Color;
import java.awt.FontMetrics;

// 
// Decompiled by Procyon v0.5.30
// 

public class r
{
    public static String ca = "\u93fa\u93f0\u93e4\u93eb\u93e6\u93e5";
    public static String da = "\u93e7\u93e8\u93e4\u93ec";
    public static String ea = "\u93fd\u93fb\u93ec\u93e7\u93ed";
    public static String fa = "\u93e8\u93fb\u93fb\u93e6\u93fe";
    public static String ga = "\u93fc\u93fb\u93e5";
    public static final byte ha = 0;
    public static final byte ia = 1;
    public static final byte ja = 2;
    private FontMetrics ka;
    private FontMetrics la;
    private Color ma;
    private Color[] na;
    private Image[] oa;
    private Color pa;
    private Color qa;
    private Object[] ra;
    private int height;
    private int t;
    private static String l = "\u93dc\u93d9\u93ea\u93c6\u93c5\u93c6\u93db";
    private static String m = "\u93da\u93dd\u93c8\u93d0\u93ea\u93c6\u93c5\u93c6\u93db";
    private static String o = "\u93cd\u93c6\u93de\u93c7\u93ea\u93c6\u93c5\u93c6\u93db";
    private static String p = "\u93cb\u93ce\u93ea\u93c6\u93c5\u93c6\u93db";
    private static String s = "\u93cf\u93ce\u93ea\u93c6\u93c5\u93c6\u93db";
    private static String u = "\u93da\u93d0\u93c4\u93cb\u93c6\u93c5\u93ea\u93c6\u93c5\u93c6\u93db";
    private static String v = "\u93c1\u93c6\u93df\u93cc\u93db\u93fa\u93d0\u93c4\u93cb\u93c6\u93c5\u93ea\u93c6\u93c5\u93c6\u93db";
    private static String w = "\u93cf\u93c6\u93c7\u93dd";
    private static String O = "\u93ff\u93cc\u93db\u93cd\u93c8\u93c7\u93c8";
    private static String P = "\u93da\u93d0\u93c4\u93cb\u93c6\u93c5\u93ef\u93c6\u93c7\u93dd";
    private static String sa = "\u93df\u93c8\u93c5\u93dc\u93cc\u93ef\u93c6\u93c7\u93dd";
    private static String ta = "\u93cf\u93c0\u93cc\u93c5\u93cd\u93da";
    private static String ua = "\u93c0\u93c7\u93df\u93c8\u93c5\u93c0\u93cd\u9389\u93d9\u93c8\u93db\u93c8\u93c4\u93cc\u93dd\u93cc\u93db\u9389\u938e\u93cf\u93c0\u93cc\u93c5\u93cd\u93da\u938e";
    private static String va = "\u93fa\u93f0\u93e4\u93eb\u93e6\u93e5";
    private static String wa = "\u93e5\u93e8\u93fa\u93fd";
    private static String xa = "\u9385";
    private static String ya = "\u93e7\u9386\u93e8";
    private static String za = "\u93fd\u93fb\u93ec\u93e7\u93ed";
    private static String Aa = "\u9382";
    private static String Ba = "\u9384";
    private static String Ca = "\u93e7\u93e8\u93e4\u93ec";
    private static String Da = "\u93e8\u93fb\u93fb\u93e6\u93fe";
    private static String Ea = "\u93fc\u93fb\u93e5";
    
    public r(final int height, final int t, final Image[] oa, final Component component, final Object o) {
        this.oa = new Image[3];
        this.height = height;
        this.t = t;
        this.oa = oa;
        this.na = new Color[] { m.a(o, r.l, 65280), m.a(o, r.m, 11053224), m.a(o, r.o, 16711680) };
        this.ra = this.a(o);
        this.ma = m.a(o, r.p, 16777215);
        final Color a = m.a(o, r.s, 0);
        this.pa = m.a(o, r.u, a.getRGB());
        this.qa = m.a(o, r.v, a.getRGB());
        final Font _ = m._(o, r.w, r.O, 0, 13);
        final Font _2 = m._(o, r.P, _.getName(), _.getStyle(), _.getSize());
        final Font _3 = m._(o, r.sa, _.getName(), _.getStyle(), _.getSize());
        this.ka = component.getFontMetrics(_2);
        this.la = component.getFontMetrics(_3);
    }
    
    private Object[] a(final Object o) {
        final String a = m.a(o, r.ta, null);
        if (a == null) {
            System.out.println(r.ua);
            return new String[] { r.va, r.wa };
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(a, r.xa);
        final String[] array = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken();
        }
        return array;
    }
    
    private String k(final String s) {
        if (s == null) {
            return r.ya;
        }
        return s;
    }
    
    public u b(final Hashtable hashtable, final Component component) {
        int n = 1;
        final Object value = hashtable.get(r.za);
        if (value != null) {
            if (value.equals(r.Aa)) {
                n = 0;
            }
            else if (value.equals(r.Ba)) {
                n = 2;
            }
        }
        int n2 = 2;
        final int n3 = 2 * this.ka.charWidth(' ');
        for (int i = 0; i < this.ra.length; ++i) {
            final String k = this.k(hashtable.get(this.ra[i]));
            if (this.ra[i].equals(r.Ca) || this.ra[i].equals(r.va)) {
                n2 += this.ka.stringWidth(k);
            }
            else if (this.ra[i].equals(r.Da)) {
                if (this.oa[n] != null) {
                    n2 += this.oa[n].getWidth(component);
                }
            }
            else {
                n2 += this.la.stringWidth(k);
            }
            n2 += n3;
        }
        final int n4 = n2 - n3;
        final Image _ = this._(component, this.pa, hashtable, n4, n);
        final String s = hashtable.get(r.Ea);
        if (s == null) {
            return new u(_);
        }
        final Image _2 = this._(component, this.qa, hashtable, n4, n);
        String s2 = hashtable.get(r.Ca);
        if (s2 == null) {
            s2 = hashtable.get(r.va);
        }
        final u u = new u(_, (_2 != null) ? _2 : _, new Rectangle(this.ka.stringWidth(this.k(s2)), this.height));
        u._(s);
        return u;
    }
    
    private Image _(final Component component, final Color color, final Hashtable hashtable, final int n, final int n2) {
        final Image image = component.createImage(n, this.height);
        if (image == null) {
            return null;
        }
        final Graphics graphics = image.getGraphics();
        graphics.setColor(this.ma);
        graphics.fillRect(0, 0, n, this.height);
        final int n3 = 2 * this.ka.charWidth(' ');
        int n4 = 2;
        for (int i = 0; i < this.ra.length; ++i) {
            final String k = this.k(hashtable.get(this.ra[i]));
            if (this.ra[i].equals(r.Ca) || this.ra[i].equals(r.va)) {
                graphics.setColor(color);
                graphics.setFont(this.ka.getFont());
                graphics.drawString(k, n4, this.t);
                n4 += this.ka.stringWidth(k);
            }
            else if (this.ra[i].equals(r.Da)) {
                if (this.oa[n2] != null) {
                    graphics.drawImage(this.oa[n2], n4, (this.height - this.oa[n2].getHeight(component)) / 2, component);
                    n4 += this.oa[n2].getWidth(component);
                }
            }
            else {
                graphics.setColor(this.na[n2]);
                graphics.setFont(this.la.getFont());
                graphics.drawString(k, n4, this.t);
                n4 += this.la.stringWidth(k);
            }
            n4 += n3;
        }
        graphics.dispose();
        return image;
    }
    
    private static String l(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u93a9');
        }
        return new String(array);
    }
    
    static {
        r.ca = l(r.ca);
        r.da = l(r.da);
        r.ea = l(r.ea);
        r.fa = l(r.fa);
        r.ga = l(r.ga);
        r.l = l(r.l);
        r.m = l(r.m);
        r.o = l(r.o);
        r.p = l(r.p);
        r.s = l(r.s);
        r.u = l(r.u);
        r.v = l(r.v);
        r.w = l(r.w);
        r.O = l(r.O);
        r.P = l(r.P);
        r.sa = l(r.sa);
        r.ta = l(r.ta);
        r.ua = l(r.ua);
        r.va = l(r.va);
        r.wa = l(r.wa);
        r.xa = l(r.xa);
        r.ya = l(r.ya);
        r.za = l(r.za);
        r.Aa = l(r.Aa);
        r.Ba = l(r.Ba);
        r.Ca = l(r.Ca);
        r.Da = l(r.Da);
        r.Ea = l(r.Ea);
    }
}
