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

public abstract class case implements ActionListener
{
    public static String N = "\u6124\u6135\u6128\u6124";
    public static String O = "\u6136\u6137\u6113\u613f\u613c\u613f\u6122";
    public static String P = "\u6132\u6137\u6113\u613f\u613c\u613f\u6122";
    public static String Q = "\u6138\u613f\u6126\u6135\u6122\u6112\u6137\u6113\u613f\u613c\u613f\u6122";
    public static String R = "\u6138\u613f\u6126\u6135\u6122\u6116\u6137\u6113\u613f\u613c\u613f\u6122";
    public static String S = "\u6136\u613f\u613e\u6124";
    public static String T = "\u6138\u613f\u6126\u6135\u6122\u6116\u613f\u613e\u6124";
    public static String U = "\u6125\u6122\u613c";
    public static final Object z;
    public static final boolean V = false;
    public static final long W = -1L;
    protected Applet F;
    protected do E;
    protected extends G;
    protected finally H;
    protected int B;
    protected int height;
    private static String a = "\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d\u616d";
    private static String b = "\u6170\u6170\u6170\u6170\u611a\u6131\u6126\u6131\u6170\u6102\u6103\u6103\u6170\u611e\u6135\u6127\u6123\u6170\u6104\u6139\u6133\u613b\u6135\u6122\u6170\u6111\u6120\u6120\u613c\u6135\u6124\u6170\u6161\u617e\u6160";
    private static String c = "\u6170\u6170\u6170\u6170\u6127\u6127\u6127\u617e\u613a\u6131\u6126\u6131\u613e\u6135\u6127\u6123\u6124\u6139\u6133\u613b\u6135\u6122\u617e\u6133\u613f\u613d\u615a";
    private static String d = "\u6170\u6170\u6170\u6170\u6103\u6139\u6124\u6135\u616a\u6170\u6135\u6132\u6131\u6122\u6122\u6135\u613c\u6122\u6131\u6133\u6139\u613e\u6137\u617e\u6133\u613f\u613d";
    private static String e = "\u6170\u6170\u6170\u6170\u6114\u6131\u6124\u6135\u616a\u6170\u6162\u6160\u6161\u6160\u617d\u6160\u6168\u617d\u6162\u6167";
    private static String f = "\u6123\u6124\u6131\u6122\u6124\u6114\u6135\u613c\u6131\u6129";
    private static String g = "\u6134\u6135\u613c\u6131\u6129";
    private static String h = "\u6123\u6124\u6135\u6120";
    private static String i = "\u6124\u6139\u6133\u613b\u6135\u6122\u6114\u6139\u6122\u6135\u6133\u6124\u6139\u613f\u613e";
    private static String I = "\u613c\u6122";
    private static String J = "\u6135\u6132\u6131\u6122\u6122\u6135\u613c\u6122\u6131\u6133\u6139\u613e\u6137\u617e\u6133\u613f\u613d";
    private static String K = "\u6123\u6124\u613f\u6133\u613b\u6131\u6120\u6120\u613c\u6135\u6124\u6123\u617e\u613e\u6135\u6124";
    private static String L = "\u6123\u6124\u613f\u6133\u613b\u6131\u6120\u6120\u613c\u6135\u6124\u6123\u617e\u6133\u613f\u613d";
    private static String M = "\u613a\u6131\u6126\u6131\u613e\u6135\u6127\u6123\u6124\u6139\u6133\u613b\u6135\u6122\u617e\u6133\u613f\u613d";
    private static String X = "\u613a\u6131\u6126\u6131\u617d\u6124\u6139\u6133\u613b\u6135\u6122\u617e\u613e\u6135\u6124";
    private static String Y = "\u613c\u613f\u6133\u6131\u613c\u6138\u613f\u6123\u6124";
    private static String Z = "\u6127\u6127\u6127\u617e\u613a\u6131\u6126\u6131\u613e\u6135\u6127\u6123\u6124\u6139\u6133\u613b\u6135\u6122\u617e\u6133\u613f\u613d";
    private static String _a = "\u6138\u6124\u6124\u6120\u616a\u617f\u617f\u6127\u6127\u6127\u617e\u613a\u6131\u6126\u6131\u613e\u6135\u6127\u6123\u6124\u6139\u6133\u613b\u6135\u6122\u617e\u6133\u613f\u613d";
    private static String aa = "\u6132\u6137\u6113\u613f\u613c\u613f\u6122";
    private static String ba = "\u6123\u6135\u6120\u6131\u6122\u6131\u6124\u613f\u6122\u6107\u6139\u6134\u6124\u6138";
    private static String ca = "\u6123\u6135\u6120\u6131\u6122\u6131\u6124\u613f\u6122\u6119\u613d\u6131\u6137\u6135";
    private static String da = "\u611d\u6139\u6123\u6123\u6139\u613e\u6137\u6170\u6120\u6131\u6122\u6131\u613d\u6135\u6124\u6135\u6122\u6170\u6177";
    private static String ea = "\u6177";
    private static String fa = "\u6138\u6124\u6124\u6120\u616a\u617f\u617f";
    private static String ga = "\u6138\u6124\u6124\u6120\u6123\u616a\u617f\u617f";
    private static String ha = "\u6119\u613e\u6126\u6131\u613c\u6139\u6134\u6170\u6120\u6131\u6122\u6131\u613d\u6135\u6124\u6135\u6122\u6170\u6177";
    private static String ia = "\u6177\u6170";
    private static String ja = "\u6124\u6135\u6128\u6124";
    private static String ka = "\u6136\u6137\u6113\u613f\u613c\u613f\u6122";
    private static String la = "\u6138\u613f\u6126\u6135\u6122\u6112\u6137\u6113\u613f\u613c\u613f\u6122";
    private static String ma = "\u6136\u613f\u613e\u6124";
    private static String na = "\u6111\u6122\u6139\u6131\u613c";
    private static String oa = "\u6125\u6122\u613c";
    private static String pa = "\u6134\u6139\u6122\u6135\u6133\u6124\u6139\u613f\u613e";
    private static String qa = "\u616c\u616c";
    private static String ra = "\u616e\u616e";
    
    private void k() {
        System.out.println(case.a);
        System.out.println(case.b);
        System.out.println(case.c);
        System.out.println(case.d);
        System.out.println(case.e);
        System.out.println(case.a);
    }
    
    protected case(final Applet f) {
        this.k();
        this.F = f;
        this.l();
        (this.E = new do(catch.a(f, case.f, 3500))).a(catch.a(f, case.g, 15));
        this.E.n(catch.a(f, case.h, 1));
        if (case.I.equalsIgnoreCase(f.getParameter(case.i))) {
            this.E.b((byte)1);
        }
        else {
            this.E.b((byte)(-1));
        }
        this.m();
        this.init();
        if (System.currentTimeMillis() >= -1L) {
            final String[] array = { case.J, case.K, case.L, case.M, case.X, case.Y };
            boolean b = true;
            for (int i = 0; i < array.length; ++i) {
                if (f.getDocumentBase().getHost().toLowerCase().endsWith(array[i].toLowerCase())) {
                    b = false;
                    break;
                }
            }
            if (b) {
                this.E.a(this.b(case.Z, case._a), case.z, this.H);
            }
        }
    }
    
    private void m() {
        this.E.setBackground(catch.b(this.F, case.aa, 16777215));
        final int a = catch.a(this.F, case.ba, -1);
        if (a != 0) {
            Image _ = null;
            if (this.F.getParameter(case.ca) != null) {
                _ = this._(case.ca);
                final MediaTracker mediaTracker = new MediaTracker(this.F);
                mediaTracker.addImage(_, 0);
                try {
                    mediaTracker.waitForAll();
                }
                catch (InterruptedException ex) {}
            }
            if ((_ != null && _.getWidth(this.F) > 0 && _.getHeight(this.F) > 0) || a > 0) {
                this.H = new finally(_, a, this.height);
            }
        }
    }
    
    protected void l() {
        final Insets insets = this.F.getInsets();
        this.height = this.F.getSize().height - insets.top - insets.bottom;
    }
    
    protected abstract void init();
    
    public void start() {
        this.E.start();
        this.G.start();
    }
    
    public void stop() {
        this.E.stop();
        this.G.stop();
    }
    
    protected Image _(final String s, final MediaTracker mediaTracker, final int n) {
        final Image _ = this._(s);
        if (_ != null) {
            mediaTracker.addImage(_, n);
        }
        return _;
    }
    
    private Image _(final String s) {
        final String parameter = this.F.getParameter(s);
        if (parameter == null) {
            System.out.println(case.da + s + case.ea);
            return this.F.createImage(1, 1);
        }
        if (!parameter.startsWith(case.fa)) {
            if (!parameter.startsWith(case.ga)) {
                return this.F.getImage(this.F.getDocumentBase(), parameter);
            }
        }
        try {
            return this.F.getImage(new URL(parameter));
        }
        catch (MalformedURLException ex) {
            System.out.println(case.ha + s + case.ia + ex);
            return this.F.createImage(1, 1);
        }
        return this.F.getImage(this.F.getDocumentBase(), parameter);
    }
    
    public Component a() {
        return this.E;
    }
    
    protected final b(final String s, final String s2) {
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        hashtable.put(case.ja, s);
        final Color b = catch.b(this.F, case.aa, 16777215);
        final Color b2 = catch.b(this.F, case.ka, 0);
        final Color b3 = catch.b(this.F, case.la, b.getRGB());
        hashtable.put(case.ka, (String)b2);
        hashtable.put(case.aa, (String)b);
        hashtable.put(case.la, (String)b3);
        hashtable.put(case.ma, (String)catch.b(this.F, case.ma, case.na, 1, 12));
        if (s2 != null) {
            hashtable.put(case.oa, s2);
        }
        return new default(this.height, this.B).a(hashtable, this.F);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (case.pa.equals(actionEvent.getActionCommand())) {
            if (this.E.a() == -1) {
                this.E.b((byte)1);
                ((Button)actionEvent.getSource()).setLabel(case.qa);
            }
            else {
                this.E.b((byte)(-1));
                ((Button)actionEvent.getSource()).setLabel(case.ra);
            }
        }
    }
    
    static {
        case.N = g(case.N);
        case.O = g(case.O);
        case.P = g(case.P);
        case.Q = g(case.Q);
        case.R = g(case.R);
        case.S = g(case.S);
        case.T = g(case.T);
        case.U = g(case.U);
        case.a = g(case.a);
        case.b = g(case.b);
        case.c = g(case.c);
        case.d = g(case.d);
        case.e = g(case.e);
        case.f = g(case.f);
        case.g = g(case.g);
        case.h = g(case.h);
        case.i = g(case.i);
        case.I = g(case.I);
        case.J = g(case.J);
        case.K = g(case.K);
        case.L = g(case.L);
        case.M = g(case.M);
        case.X = g(case.X);
        case.Y = g(case.Y);
        case.Z = g(case.Z);
        case._a = g(case._a);
        case.aa = g(case.aa);
        case.ba = g(case.ba);
        case.ca = g(case.ca);
        case.da = g(case.da);
        case.ea = g(case.ea);
        case.fa = g(case.fa);
        case.ga = g(case.ga);
        case.ha = g(case.ha);
        case.ia = g(case.ia);
        case.ja = g(case.ja);
        case.ka = g(case.ka);
        case.la = g(case.la);
        case.ma = g(case.ma);
        case.na = g(case.na);
        case.oa = g(case.oa);
        case.pa = g(case.pa);
        case.qa = g(case.qa);
        case.ra = g(case.ra);
        z = new Object();
    }
    
    private static String g(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF6150);
        }
        return new String(array);
    }
}
