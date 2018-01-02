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

public class if
{
    public static String ab = "\u9426\u942c\u9438\u9437\u943a\u9439";
    public static String bb = "\u943b\u9434\u9438\u9430";
    public static String cb = "\u9421\u9427\u9430\u943b\u9431";
    public static String db = "\u9434\u9427\u9427\u943a\u9422";
    public static String eb = "\u9420\u9427\u9439";
    public static final byte fb = 0;
    public static final byte gb = 1;
    public static final byte hb = 2;
    private FontMetrics ib;
    private FontMetrics jb;
    private Color kb;
    private Color lb;
    private Color[] mb;
    private Image[] nb;
    private Color ob;
    private Color pb;
    private Object[] qb;
    private int height;
    private int ra;
    private static String ja = "\u9400\u9405\u9436\u941a\u9419\u941a\u9407";
    private static String na = "\u9406\u9401\u9414\u940c\u9436\u941a\u9419\u941a\u9407";
    private static String oa = "\u9411\u941a\u9402\u941b\u9436\u941a\u9419\u941a\u9407";
    private static String pa = "\u9417\u9412\u9436\u941a\u9419\u941a\u9407";
    private static String sa = "\u941d\u941a\u9403\u9410\u9407\u9437\u9412\u9436\u941a\u9419\u941a\u9407";
    private static String ta = "\u9413\u9412\u9436\u941a\u9419\u941a\u9407";
    private static String ua = "\u9406\u940c\u9418\u9417\u941a\u9419\u9436\u941a\u9419\u941a\u9407";
    private static String va = "\u941d\u941a\u9403\u9410\u9407\u9426\u940c\u9418\u9417\u941a\u9419\u9436\u941a\u9419\u941a\u9407";
    private static String wa = "\u9413\u941a\u941b\u9401";
    private static String rb = "\u9423\u9410\u9407\u9411\u9414\u941b\u9414";
    private static String sb = "\u9406\u940c\u9418\u9417\u941a\u9419\u9433\u941a\u941b\u9401";
    private static String tb = "\u9403\u9414\u9419\u9400\u9410\u9433\u941a\u941b\u9401";
    private static String _ = "\u9413\u941c\u9410\u9419\u9411\u9406";
    private static String a = "\u941c\u941b\u9403\u9414\u9419\u941c\u9411\u9455\u9405\u9414\u9407\u9414\u9418\u9410\u9401\u9410\u9407\u9455\u9452\u9413\u941c\u9410\u9419\u9411\u9406\u9452";
    private static String b = "\u9426\u942c\u9438\u9437\u943a\u9439";
    private static String c = "\u9439\u9434\u9426\u9421";
    private static String d = "\u9459";
    private static String e = "\u943b\u945a\u9434";
    private static String f = "\u9421\u9427\u9430\u943b\u9431";
    private static String g = "\u945e";
    private static String h = "\u9458";
    private static String i = "\u943b\u9434\u9438\u9430";
    private static String j = "\u9434\u9427\u9427\u943a\u9422";
    private static String k = "\u9420\u9427\u9439";
    
    public if(final int height, final int ra, final Image[] nb, final Component component, final Object o) {
        this.nb = new Image[3];
        this.height = height;
        this.ra = ra;
        this.nb = nb;
        this.mb = new Color[] { extends.b(o, if.ja, 65280), extends.b(o, if.na, 11053224), extends.b(o, if.oa, 16711680) };
        this.qb = this._(o);
        this.kb = extends.b(o, if.pa, 16777215);
        this.lb = extends.b(o, if.sa, this.kb.getRGB());
        final Color b = extends.b(o, if.ta, 0);
        this.ob = extends.b(o, if.ua, b.getRGB());
        this.pb = extends.b(o, if.va, b.getRGB());
        final Font b2 = extends.b(o, if.wa, if.rb, 0, 13);
        final Font b3 = extends.b(o, if.sb, b2.getName(), b2.getStyle(), b2.getSize());
        final Font b4 = extends.b(o, if.tb, b2.getName(), b2.getStyle(), b2.getSize());
        this.ib = component.getFontMetrics(b3);
        this.jb = component.getFontMetrics(b4);
    }
    
    private Object[] _(final Object o) {
        final String _ = extends._(o, if._, null);
        if (_ == null) {
            System.out.println(if.a);
            return new String[] { if.b, if.c };
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(_, if.d);
        final String[] array = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken();
        }
        return array;
    }
    
    private String j(final String s) {
        if (s == null) {
            return if.e;
        }
        return s;
    }
    
    public instanceof a(final Hashtable hashtable, final Component component) {
        int n = 1;
        final Object value = hashtable.get(if.f);
        if (value != null) {
            if (value.equals(if.g)) {
                n = 0;
            }
            else if (value.equals(if.h)) {
                n = 2;
            }
        }
        int n2 = 2;
        final int n3 = 2 * this.ib.charWidth(' ');
        for (int i = 0; i < this.qb.length; ++i) {
            final String j = this.j(hashtable.get(this.qb[i]));
            if (this.qb[i].equals(if.i) || this.qb[i].equals(if.b)) {
                n2 += this.ib.stringWidth(j);
            }
            else if (this.qb[i].equals(if.j)) {
                if (this.nb[n] != null) {
                    n2 += this.nb[n].getWidth(component);
                }
            }
            else {
                n2 += this.jb.stringWidth(j);
            }
            n2 += n3;
        }
        final int n4 = n2 - n3;
        final Image a = this.a(component, this.ob, this.kb, hashtable, n4, n);
        final String s = hashtable.get(if.k);
        if (s == null) {
            return new instanceof(a);
        }
        final Image a2 = this.a(component, this.pb, this.lb, hashtable, n4, n);
        String s2 = hashtable.get(if.i);
        if (s2 == null) {
            s2 = hashtable.get(if.b);
        }
        final instanceof instanceof1 = new instanceof(a, (a2 != null) ? a2 : a, new Rectangle(this.ib.stringWidth(this.j(s2)), this.height));
        instanceof1._(s);
        return instanceof1;
    }
    
    private Image a(final Component component, final Color color, final Color color2, final Hashtable hashtable, final int n, final int n2) {
        final Image image = component.createImage(n, this.height);
        if (image == null) {
            return null;
        }
        final Graphics graphics = image.getGraphics();
        graphics.setColor(color2);
        graphics.fillRect(0, 0, n, this.height);
        final int n3 = 2 * this.ib.charWidth(' ');
        int n4 = 2;
        for (int i = 0; i < this.qb.length; ++i) {
            final String j = this.j(hashtable.get(this.qb[i]));
            if (this.qb[i].equals(if.i) || this.qb[i].equals(if.b)) {
                graphics.setColor(color);
                graphics.setFont(this.ib.getFont());
                graphics.drawString(j, n4, this.ra);
                n4 += this.ib.stringWidth(j);
            }
            else if (this.qb[i].equals(if.j)) {
                if (this.nb[n2] != null) {
                    graphics.drawImage(this.nb[n2], n4, (this.height - this.nb[n2].getHeight(component)) / 2, component);
                    n4 += this.nb[n2].getWidth(component);
                }
            }
            else {
                graphics.setColor(this.mb[n2]);
                graphics.setFont(this.jb.getFont());
                graphics.drawString(j, n4, this.ra);
                n4 += this.jb.stringWidth(j);
            }
            n4 += n3;
        }
        graphics.dispose();
        return image;
    }
    
    private static String k(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u9475');
        }
        return new String(array);
    }
    
    static {
        if.ab = k(if.ab);
        if.bb = k(if.bb);
        if.cb = k(if.cb);
        if.db = k(if.db);
        if.eb = k(if.eb);
        if.ja = k(if.ja);
        if.na = k(if.na);
        if.oa = k(if.oa);
        if.pa = k(if.pa);
        if.sa = k(if.sa);
        if.ta = k(if.ta);
        if.ua = k(if.ua);
        if.va = k(if.va);
        if.wa = k(if.wa);
        if.rb = k(if.rb);
        if.sb = k(if.sb);
        if.tb = k(if.tb);
        if._ = k(if._);
        if.a = k(if.a);
        if.b = k(if.b);
        if.c = k(if.c);
        if.d = k(if.d);
        if.e = k(if.e);
        if.f = k(if.f);
        if.g = k(if.g);
        if.h = k(if.h);
        if.i = k(if.i);
        if.j = k(if.j);
        if.k = k(if.k);
    }
}
