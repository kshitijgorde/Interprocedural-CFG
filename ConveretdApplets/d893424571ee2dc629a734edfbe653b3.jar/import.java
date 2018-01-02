import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.Hashtable;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Insets;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.applet.Applet;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class import implements ActionListener
{
    public static String f = "\u0c41\u0c50\u0c4d\u0c41";
    public static String g = "\u0c53\u0c52\u0c76\u0c5a\u0c59\u0c5a\u0c47";
    public static String h = "\u0c57\u0c52\u0c76\u0c5a\u0c59\u0c5a\u0c47";
    public static String i = "\u0c5d\u0c5a\u0c43\u0c50\u0c47\u0c77\u0c52\u0c76\u0c5a\u0c59\u0c5a\u0c47";
    public static String j = "\u0c5d\u0c5a\u0c43\u0c50\u0c47\u0c73\u0c52\u0c76\u0c5a\u0c59\u0c5a\u0c47";
    public static String k = "\u0c53\u0c5a\u0c5b\u0c41";
    public static String l = "\u0c5d\u0c5a\u0c43\u0c50\u0c47\u0c73\u0c5a\u0c5b\u0c41";
    public static String m = "\u0c40\u0c47\u0c59";
    public static final Object Ma;
    public static final boolean n = false;
    public static final long o = -1L;
    protected Applet a;
    protected private _;
    protected static c;
    protected return b;
    protected int ea;
    protected int height;
    private static String V = "\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08\u0c08";
    private static String W = "\u0c15\u0c15\u0c15\u0c15\u0c7b\u0c50\u0c42\u0c46\u0c15\u0c13\u0c15\u0c64\u0c40\u0c5a\u0c41\u0c50\u0c46\u0c15\u0c61\u0c5c\u0c56\u0c5e\u0c50\u0c47\u0c15\u0c18\u0c15\u0c7f\u0c54\u0c43\u0c54\u0c15\u0c74\u0c45\u0c45\u0c59\u0c50\u0c41\u0c15\u0c06\u0c1b\u0c04";
    private static String _a = "\u0c15\u0c15\u0c15\u0c15\u0c42\u0c42\u0c42\u0c1b\u0c5f\u0c54\u0c43\u0c54\u0c18\u0c41\u0c5c\u0c56\u0c5e\u0c50\u0c47\u0c1b\u0c5b\u0c50\u0c41\u0c3f";
    private static String aa = "\u0c15\u0c15\u0c15\u0c15\u0c66\u0c5c\u0c41\u0c50\u0c0f\u0c15\u0c41\u0c47\u0c58\u0c56\u0c5a\u0c58\u0c1b\u0c56\u0c5a\u0c58";
    private static String ba = "\u0c15\u0c15\u0c15\u0c15\u0c71\u0c54\u0c41\u0c50\u0c0f\u0c15\u0c07\u0c05\u0c05\u0c03\u0c18\u0c05\u0c0c\u0c18\u0c04\u0c07";
    private static String fa = "\u0c46\u0c41\u0c54\u0c47\u0c41\u0c71\u0c50\u0c59\u0c54\u0c4c";
    private static String ga = "\u0c51\u0c50\u0c59\u0c54\u0c4c";
    private static String ha = "\u0c46\u0c41\u0c50\u0c45";
    private static String ia = "\u0c41\u0c5c\u0c56\u0c5e\u0c50\u0c47\u0c71\u0c5c\u0c47\u0c50\u0c56\u0c41\u0c5c\u0c5a\u0c5b";
    private static String db = "\u0c59\u0c47";
    private static String eb = "\u0c41\u0c47\u0c58\u0c56\u0c5a\u0c58\u0c1b\u0c56\u0c5a\u0c58";
    private static String fb = "\u0c46\u0c41\u0c5a\u0c56\u0c5e\u0c54\u0c45\u0c45\u0c59\u0c50\u0c41\u0c46\u0c1b\u0c56\u0c5a\u0c58";
    private static String gb = "\u0c5f\u0c54\u0c43\u0c54\u0c18\u0c41\u0c5c\u0c56\u0c5e\u0c50\u0c47\u0c1b\u0c5b\u0c50\u0c41";
    private static String hb = "\u0c59\u0c5a\u0c56\u0c54\u0c59\u0c5d\u0c5a\u0c46\u0c41";
    private static String ib = "\u0c42\u0c42\u0c42\u0c1b\u0c5f\u0c54\u0c43\u0c54\u0c18\u0c41\u0c5c\u0c56\u0c5e\u0c50\u0c47\u0c1b\u0c5b\u0c50\u0c41";
    private static String jb = "\u0c5d\u0c41\u0c41\u0c45\u0c0f\u0c1a\u0c1a\u0c42\u0c42\u0c42\u0c1b\u0c5f\u0c54\u0c43\u0c54\u0c18\u0c41\u0c5c\u0c56\u0c5e\u0c50\u0c47\u0c1b\u0c5b\u0c50\u0c41";
    private static String kb = "\u0c57\u0c52\u0c76\u0c5a\u0c59\u0c5a\u0c47";
    private static String lb = "\u0c46\u0c50\u0c45\u0c54\u0c47\u0c54\u0c41\u0c5a\u0c47\u0c62\u0c5c\u0c51\u0c41\u0c5d";
    private static String mb = "\u0c46\u0c50\u0c45\u0c54\u0c47\u0c54\u0c41\u0c5a\u0c47\u0c7c\u0c58\u0c54\u0c52\u0c50";
    private static String nb = "\u0c78\u0c5c\u0c46\u0c46\u0c5c\u0c5b\u0c52\u0c15\u0c45\u0c54\u0c47\u0c54\u0c58\u0c50\u0c41\u0c50\u0c47\u0c15\u0c12";
    private static String ob = "\u0c12";
    private static String pb = "\u0c5d\u0c41\u0c41\u0c45\u0c0f\u0c1a\u0c1a";
    private static String qb = "\u0c5d\u0c41\u0c41\u0c45\u0c46\u0c0f\u0c1a\u0c1a";
    private static String rb = "\u0c7c\u0c5b\u0c43\u0c54\u0c59\u0c5c\u0c51\u0c15\u0c45\u0c54\u0c47\u0c54\u0c58\u0c50\u0c41\u0c50\u0c47\u0c15\u0c12";
    private static String p = "\u0c12\u0c15";
    private static String q = "\u0c41\u0c50\u0c4d\u0c41";
    private static String r = "\u0c53\u0c52\u0c76\u0c5a\u0c59\u0c5a\u0c47";
    private static String s = "\u0c5d\u0c5a\u0c43\u0c50\u0c47\u0c77\u0c52\u0c76\u0c5a\u0c59\u0c5a\u0c47";
    private static String t = "\u0c53\u0c5a\u0c5b\u0c41";
    private static String u = "\u0c74\u0c47\u0c5c\u0c54\u0c59";
    private static String v = "\u0c40\u0c47\u0c59";
    private static String w = "\u0c51\u0c5c\u0c47\u0c50\u0c56\u0c41\u0c5c\u0c5a\u0c5b";
    private static String x = "\u0c09\u0c09";
    private static String y = "\u0c0b\u0c0b";
    
    private void e() {
        System.out.println(import.V);
        System.out.println(import.W);
        System.out.println(import._a);
        System.out.println(import.aa);
        System.out.println(import.ba);
        System.out.println(import.V);
    }
    
    protected import(final Applet a) {
        this.e();
        this.a = a;
        this.f();
        (this._ = new private(instanceof._(a, import.fa, 3500))).b(instanceof._(a, import.ga, 15));
        this._.k(instanceof._(a, import.ha, 1));
        if (import.db.equalsIgnoreCase(a.getParameter(import.ia))) {
            this._.b((byte)1);
        }
        else {
            this._.b((byte)(-1));
        }
        this.g();
        this.init();
        if (System.currentTimeMillis() >= -1L) {
            final String[] array = { import.eb, import.fb, import.gb, import.hb };
            boolean b = true;
            for (int i = 0; i < array.length; ++i) {
                if (a.getDocumentBase().getHost().toLowerCase().endsWith(array[i].toLowerCase())) {
                    b = false;
                    break;
                }
            }
            if (b) {
                this._.a(this.a(import.ib, import.jb), import.Ma, this.b);
            }
        }
    }
    
    private void g() {
        this._.setBackground(instanceof.b(this.a, import.kb, 16777215));
        final int _ = instanceof._(this.a, import.lb, -1);
        if (_ != 0) {
            Image _2 = null;
            if (this.a.getParameter(import.mb) != null) {
                _2 = this._(import.mb);
                final MediaTracker mediaTracker = new MediaTracker(this.a);
                mediaTracker.addImage(_2, 0);
                try {
                    mediaTracker.waitForAll();
                }
                catch (InterruptedException ex) {}
            }
            if ((_2 != null && _2.getWidth(this.a) > 0 && _2.getHeight(this.a) > 0) || _ > 0) {
                this.b = new return(_2, _, this.height);
            }
        }
    }
    
    protected void f() {
        final Insets insets = this.a.getInsets();
        this.height = this.a.getSize().height - insets.top - insets.bottom;
    }
    
    protected abstract void init();
    
    public void start() {
        this._.start();
        this.c.start();
    }
    
    public void stop() {
        this._.stop();
        this.c.stop();
    }
    
    protected Image b(final String s, final MediaTracker mediaTracker, final int n) {
        final Image _ = this._(s);
        if (_ != null) {
            mediaTracker.addImage(_, n);
        }
        return _;
    }
    
    private Image _(final String s) {
        final String parameter = this.a.getParameter(s);
        if (parameter == null) {
            System.out.println(import.nb + s + import.ob);
            return this.a.createImage(1, 1);
        }
        if (!parameter.startsWith(import.pb)) {
            if (!parameter.startsWith(import.qb)) {
                return this.a.getImage(this.a.getDocumentBase(), parameter);
            }
        }
        try {
            return this.a.getImage(new URL(parameter));
        }
        catch (MalformedURLException ex) {
            System.out.println(import.rb + s + import.p + ex);
            return this.a.createImage(1, 1);
        }
        return this.a.getImage(this.a.getDocumentBase(), parameter);
    }
    
    public Component b() {
        return this._;
    }
    
    protected public a(final String s, final String s2) {
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        hashtable.put(import.q, s);
        final Color b = instanceof.b(this.a, import.kb, 16777215);
        final Color b2 = instanceof.b(this.a, import.r, 0);
        final Color b3 = instanceof.b(this.a, import.s, b.getRGB());
        hashtable.put(import.r, (String)b2);
        hashtable.put(import.kb, (String)b);
        hashtable.put(import.s, (String)b3);
        hashtable.put(import.t, (String)instanceof.a(this.a, import.t, import.u, 1, 12));
        if (s2 != null) {
            hashtable.put(import.v, s2);
        }
        return new this(this.height, this.ea).a(hashtable, this.a);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (import.w.equals(actionEvent.getActionCommand())) {
            if (this._._() == -1) {
                this._.b((byte)1);
                ((Button)actionEvent.getSource()).setLabel(import.x);
            }
            else {
                this._.b((byte)(-1));
                ((Button)actionEvent.getSource()).setLabel(import.y);
            }
        }
    }
    
    static {
        import.f = i(import.f);
        import.g = i(import.g);
        import.h = i(import.h);
        import.i = i(import.i);
        import.j = i(import.j);
        import.k = i(import.k);
        import.l = i(import.l);
        import.m = i(import.m);
        import.V = i(import.V);
        import.W = i(import.W);
        import._a = i(import._a);
        import.aa = i(import.aa);
        import.ba = i(import.ba);
        import.fa = i(import.fa);
        import.ga = i(import.ga);
        import.ha = i(import.ha);
        import.ia = i(import.ia);
        import.db = i(import.db);
        import.eb = i(import.eb);
        import.fb = i(import.fb);
        import.gb = i(import.gb);
        import.hb = i(import.hb);
        import.ib = i(import.ib);
        import.jb = i(import.jb);
        import.kb = i(import.kb);
        import.lb = i(import.lb);
        import.mb = i(import.mb);
        import.nb = i(import.nb);
        import.ob = i(import.ob);
        import.pb = i(import.pb);
        import.qb = i(import.qb);
        import.rb = i(import.rb);
        import.p = i(import.p);
        import.q = i(import.q);
        import.r = i(import.r);
        import.s = i(import.s);
        import.t = i(import.t);
        import.u = i(import.u);
        import.v = i(import.v);
        import.w = i(import.w);
        import.x = i(import.x);
        import.y = i(import.y);
        Ma = new Object();
    }
    
    private static String i(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF0C35);
        }
        return new String(array);
    }
}
