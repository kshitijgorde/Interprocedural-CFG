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

public class package
{
    public static String Na = "\u980a\u9800\u9814\u981b\u9816\u9815";
    public static String Oa = "\u9817\u9818\u9814\u981c";
    public static String Pa = "\u980d\u980b\u981c\u9817\u981d";
    public static String Qa = "\u9818\u980b\u980b\u9816\u980e";
    public static String Ra = "\u980c\u980b\u9815";
    public static final byte Sa = 0;
    public static final byte Ta = 1;
    public static final byte Ua = 2;
    private static final int da = 5;
    private FontMetrics Va;
    private FontMetrics Wa;
    private Color Xa;
    private Color Ya;
    private Color[] Za;
    private Image[] _b;
    private Color ab;
    private Color bb;
    private Object[] cb;
    private int height;
    private int ea;
    private static String V = "\u982c\u9829\u981a\u9836\u9835\u9836\u982b";
    private static String W = "\u982a\u982d\u9838\u9820\u981a\u9836\u9835\u9836\u982b";
    private static String _a = "\u983d\u9836\u982e\u9837\u981a\u9836\u9835\u9836\u982b";
    private static String aa = "\u983b\u983e\u981a\u9836\u9835\u9836\u982b";
    private static String ba = "\u9831\u9836\u982f\u983c\u982b\u981b\u983e\u981a\u9836\u9835\u9836\u982b";
    private static String fa = "\u983f\u983e\u981a\u9836\u9835\u9836\u982b";
    private static String ga = "\u982a\u9820\u9834\u983b\u9836\u9835\u981a\u9836\u9835\u9836\u982b";
    private static String ha = "\u9831\u9836\u982f\u983c\u982b\u980a\u9820\u9834\u983b\u9836\u9835\u981a\u9836\u9835\u9836\u982b";
    private static String ia = "\u983f\u9836\u9837\u982d";
    private static String db = "\u980f\u983c\u982b\u983d\u9838\u9837\u9838";
    private static String eb = "\u982a\u9820\u9834\u983b\u9836\u9835\u981f\u9836\u9837\u982d";
    private static String fb = "\u982f\u9838\u9835\u982c\u983c\u981f\u9836\u9837\u982d";
    private static String gb = "\u983f\u9830\u983c\u9835\u983d\u982a";
    private static String hb = "\u9830\u9837\u982f\u9838\u9835\u9830\u983d\u9879\u9829\u9838\u982b\u9838\u9834\u983c\u982d\u983c\u982b\u9879\u987e\u983f\u9830\u983c\u9835\u983d\u982a\u987e";
    private static String ib = "\u980a\u9800\u9814\u981b\u9816\u9815";
    private static String jb = "\u9815\u9818\u980a\u980d";
    private static String kb = "\u9875";
    private static String lb = "\u9817\u9876\u9818";
    private static String mb = "\u980d\u980b\u981c\u9817\u981d";
    private static String nb = "\u9872";
    private static String ob = "\u9874";
    private static String pb = "\u9817\u9818\u9814\u981c";
    private static String qb = "\u9818\u980b\u980b\u9816\u980e";
    private static String rb = "\u980c\u980b\u9815";
    
    public package(final int height, final int ea, final Image[] b, final Component component, final Object o) {
        this._b = new Image[3];
        this.height = height;
        this.ea = ea;
        this._b = b;
        this.Za = new Color[] { instanceof.b(o, package.V, 65280), instanceof.b(o, package.W, 11053224), instanceof.b(o, package._a, 16711680) };
        this.cb = this.a(o);
        this.Xa = instanceof.b(o, package.aa, 16777215);
        this.Ya = instanceof.b(o, package.ba, this.Xa.getRGB());
        this.ab = instanceof.b(o, package.ga, instanceof.b(o, package.fa, 0).getRGB());
        this.bb = instanceof.b(o, package.ha, this.ab.getRGB());
        final Font a = instanceof.a(o, package.ia, package.db, 0, 13);
        final Font a2 = instanceof.a(o, package.eb, a.getName(), a.getStyle(), a.getSize());
        final Font a3 = instanceof.a(o, package.fb, a.getName(), a.getStyle(), a.getSize());
        this.Va = component.getFontMetrics(a2);
        this.Wa = component.getFontMetrics(a3);
    }
    
    private Object[] a(final Object o) {
        final String _ = instanceof._(o, package.gb, null);
        if (_ == null) {
            System.out.println(package.hb);
            return new String[] { package.ib, package.jb };
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(_, package.kb);
        final String[] array = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken();
        }
        return array;
    }
    
    private String c(final String s) {
        if (s == null) {
            return package.lb;
        }
        return s;
    }
    
    public public b(final Hashtable hashtable, final Component component) {
        int n = 1;
        final Object value = hashtable.get(package.mb);
        if (value != null) {
            if (value.equals(package.nb)) {
                n = 0;
            }
            else if (value.equals(package.ob)) {
                n = 2;
            }
        }
        int n2 = 5;
        final int n3 = 2 * this.Va.charWidth(' ');
        for (int i = 0; i < this.cb.length; ++i) {
            final String c = this.c(hashtable.get(this.cb[i]));
            if (this.cb[i].equals(package.pb) || this.cb[i].equals(package.ib)) {
                n2 += this.Va.stringWidth(c);
            }
            else if (this.cb[i].equals(package.qb)) {
                if (this._b[n] != null) {
                    n2 += this._b[n].getWidth(component);
                }
            }
            else {
                n2 += this.Wa.stringWidth(c);
            }
            n2 += n3;
        }
        final int n4 = n2 - (n3 - 5);
        final Image a = this.a(component, this.ab, this.Xa, hashtable, n4, n);
        final String s = hashtable.get(package.rb);
        final Image a2 = this.a(component, this.bb, this.Ya, hashtable, n4, n);
        String s2 = hashtable.get(package.pb);
        if (s2 == null) {
            s2 = hashtable.get(package.ib);
        }
        this.c(s2);
        final public public1 = new public(a, (a2 != null) ? a2 : a, new Rectangle(n4, this.height));
        public1.b(s);
        return public1;
    }
    
    private Image a(final Component component, final Color color, final Color color2, final Hashtable hashtable, final int n, final int n2) {
        final Image image = component.createImage(n, this.height);
        if (image == null) {
            return null;
        }
        final Graphics graphics = image.getGraphics();
        graphics.setColor(color2);
        graphics.fillRect(0, 0, n, this.height);
        final int n3 = 2 * this.Va.charWidth(' ');
        int n4 = 5;
        for (int i = 0; i < this.cb.length; ++i) {
            final String c = this.c(hashtable.get(this.cb[i]));
            if (this.cb[i].equals(package.pb) || this.cb[i].equals(package.ib)) {
                graphics.setColor(color);
                graphics.setFont(this.Va.getFont());
                graphics.drawString(c, n4, this.ea);
                n4 += this.Va.stringWidth(c);
            }
            else if (this.cb[i].equals(package.qb)) {
                if (this._b[n2] != null) {
                    graphics.drawImage(this._b[n2], n4, (this.height - this._b[n2].getHeight(component)) / 2, component);
                    n4 += this._b[n2].getWidth(component);
                }
            }
            else {
                graphics.setColor(this.Za[n2]);
                graphics.setFont(this.Wa.getFont());
                graphics.drawString(c, n4, this.ea);
                n4 += this.Wa.stringWidth(c);
            }
            n4 += n3;
        }
        graphics.dispose();
        return image;
    }
    
    private static String d(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF9859);
        }
        return new String(array);
    }
    
    static {
        package.Na = d(package.Na);
        package.Oa = d(package.Oa);
        package.Pa = d(package.Pa);
        package.Qa = d(package.Qa);
        package.Ra = d(package.Ra);
        package.V = d(package.V);
        package.W = d(package.W);
        package._a = d(package._a);
        package.aa = d(package.aa);
        package.ba = d(package.ba);
        package.fa = d(package.fa);
        package.ga = d(package.ga);
        package.ha = d(package.ha);
        package.ia = d(package.ia);
        package.db = d(package.db);
        package.eb = d(package.eb);
        package.fb = d(package.fb);
        package.gb = d(package.gb);
        package.hb = d(package.hb);
        package.ib = d(package.ib);
        package.jb = d(package.jb);
        package.kb = d(package.kb);
        package.lb = d(package.lb);
        package.mb = d(package.mb);
        package.nb = d(package.nb);
        package.ob = d(package.ob);
        package.pb = d(package.pb);
        package.qb = d(package.qb);
        package.rb = d(package.rb);
    }
}
