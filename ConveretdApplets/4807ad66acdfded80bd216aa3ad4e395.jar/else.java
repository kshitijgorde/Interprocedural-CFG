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

public abstract class else implements ActionListener
{
    public static String t = "\uea9f\uea8e\uea93\uea9f";
    public static String u = "\uea8d\uea8c\ueaa8\uea84\uea87\uea84\uea99";
    public static String v = "\uea89\uea8c\ueaa8\uea84\uea87\uea84\uea99";
    public static String w = "\uea83\uea84\uea9d\uea8e\uea99\ueaa9\uea8c\ueaa8\uea84\uea87\uea84\uea99";
    public static String x = "\uea83\uea84\uea9d\uea8e\uea99\ueaa8\uea84\uea87\uea84\uea99";
    public static String y = "\uea8d\uea84\uea85\uea9f";
    public static String z = "\uea83\uea84\uea9d\uea8e\uea99\ueaad\uea84\uea85\uea9f";
    public static String A = "\uea9e\uea99\uea87";
    public static final Object _b;
    public static final boolean B = true;
    protected Applet o;
    protected implements n;
    protected native q;
    protected interface p;
    protected int ra;
    protected int height;
    private static String ja = "\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6\uead6";
    private static String na = "\ueacb\ueacb\ueacb\ueacb\ueaa5\uea8e\uea9c\uea98\ueacb\ueacd\ueacb\ueaba\uea9e\uea84\uea9f\uea8e\uea98\ueacb\ueabf\uea82\uea88\uea80\uea8e\uea99\ueacb\ueac6\ueacb\ueaa1\uea8a\uea9d\uea8a\ueacb\ueaaa\uea9b\uea9b\uea87\uea8e\uea9f\ueacb\uead8\ueac5\ueada";
    private static String oa = "\ueacb\ueacb\ueacb\ueacb\uea9c\uea9c\uea9c\ueac5\uea81\uea8a\uea9d\uea8a\ueac6\uea9f\uea82\uea88\uea80\uea8e\uea99\ueac5\uea85\uea8e\uea9f\ueae1";
    private static String pa = "\ueacb\ueacb\ueacb\ueacb\ueaaf\uea8e\uea86\uea84\ueacb\ueabd\uea8e\uea99\uea98\uea82\uea84\uea85";
    private static String sa = "\uea98\uea9f\uea8a\uea99\uea9f\ueaaf\uea8e\uea87\uea8a\uea92";
    private static String ta = "\uea8f\uea8e\uea87\uea8a\uea92";
    private static String ua = "\uea98\uea9f\uea8e\uea9b";
    private static String va = "\uea9f\uea82\uea88\uea80\uea8e\uea99\ueaaf\uea82\uea99\uea8e\uea88\uea9f\uea82\uea84\uea85";
    private static String wa = "\uea87\uea99";
    private static String rb = "\uea89\uea8c\ueaa8\uea84\uea87\uea84\uea99";
    private static String sb = "\uea98\uea8e\uea9b\uea8a\uea99\uea8a\uea9f\uea84\uea99\ueabc\uea82\uea8f\uea9f\uea83";
    private static String tb = "\uea98\uea8e\uea9b\uea8a\uea99\uea8a\uea9f\uea84\uea99\ueaa2\uea86\uea8a\uea8c\uea8e";
    private static String _ = "\ueaa6\uea82\uea98\uea98\uea82\uea85\uea8c\ueacb\uea9b\uea8a\uea99\uea8a\uea86\uea8e\uea9f\uea8e\uea99\ueacb\ueacc";
    private static String a = "\ueacc";
    private static String b = "\uea83\uea9f\uea9f\uea9b\uead1\ueac4\ueac4";
    private static String c = "\uea83\uea9f\uea9f\uea9b\uea98\uead1\ueac4\ueac4";
    private static String d = "\ueaa2\uea85\uea9d\uea8a\uea87\uea82\uea8f\ueacb\uea9b\uea8a\uea99\uea8a\uea86\uea8e\uea9f\uea8e\uea99\ueacb\ueacc";
    private static String e = "\ueacc\ueacb";
    private static String f = "\uea9f\uea8e\uea93\uea9f";
    private static String g = "\uea8d\uea8c\ueaa8\uea84\uea87\uea84\uea99";
    private static String h = "\uea8d\uea84\uea85\uea9f";
    private static String i = "\ueaaa\uea99\uea82\uea8a\uea87";
    private static String j = "\uea9e\uea99\uea87";
    private static String k = "\uea8f\uea82\uea99\uea8e\uea88\uea9f\uea82\uea84\uea85";
    private static String C = "\uead7\uead7";
    private static String D = "\uead5\uead5";
    
    private void c() {
        System.out.println(else.ja);
        System.out.println(else.na);
        System.out.println(else.oa);
        System.out.println(else.pa);
        System.out.println(else.ja);
    }
    
    protected else(final Applet o) {
        this.c();
        this.o = o;
        this.d();
        (this.n = new implements(extends.a(o, else.sa, 3500))).a(extends.a(o, else.ta, 15));
        this.n.j(extends.a(o, else.ua, 1));
        if (else.wa.equalsIgnoreCase(o.getParameter(else.va))) {
            this.n.a((byte)1);
        }
        else {
            this.n.a((byte)(-1));
        }
        this.e();
        this.init();
    }
    
    private void e() {
        this.n.setBackground(extends.b(this.o, else.rb, 16777215));
        final int a = extends.a(this.o, else.sb, -1);
        if (a != 0) {
            Image b = null;
            if (this.o.getParameter(else.tb) != null) {
                b = this.b(else.tb);
                final MediaTracker mediaTracker = new MediaTracker(this.o);
                mediaTracker.addImage(b, 0);
                try {
                    mediaTracker.waitForAll();
                }
                catch (InterruptedException ex) {}
            }
            if ((b != null && b.getWidth(this.o) > 0 && b.getHeight(this.o) > 0) || a > 0) {
                this.p = new interface(b, a, this.height);
            }
        }
    }
    
    protected void d() {
        final Insets insets = this.o.getInsets();
        this.height = this.o.getSize().height - insets.top - insets.bottom;
    }
    
    protected abstract void init();
    
    public void start() {
        this.n.start();
        this.q.start();
    }
    
    public void stop() {
        this.n.stop();
        this.q.stop();
    }
    
    protected Image b(final String s, final MediaTracker mediaTracker, final int n) {
        final Image b = this.b(s);
        if (b != null) {
            mediaTracker.addImage(b, n);
        }
        return b;
    }
    
    private Image b(final String s) {
        final String parameter = this.o.getParameter(s);
        if (parameter == null) {
            System.out.println(else._ + s + else.a);
            return this.o.createImage(1, 1);
        }
        if (!parameter.startsWith(else.b)) {
            if (!parameter.startsWith(else.c)) {
                return this.o.getImage(this.o.getDocumentBase(), parameter);
            }
        }
        try {
            return this.o.getImage(new URL(parameter));
        }
        catch (MalformedURLException ex) {
            System.out.println(else.d + s + else.e + ex);
            return this.o.createImage(1, 1);
        }
        return this.o.getImage(this.o.getDocumentBase(), parameter);
    }
    
    public Component b() {
        return this.n;
    }
    
    protected instanceof a(final String s, final String s2) {
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        hashtable.put(else.f, s);
        final Color b = extends.b(this.o, else.rb, 16777215);
        hashtable.put(else.g, (String)extends.b(this.o, else.g, 16711680));
        hashtable.put(else.rb, (String)b);
        hashtable.put(else.h, (String)extends.b(this.o, else.h, else.i, 1, 12));
        if (s2 != null) {
            hashtable.put(else.j, s2);
        }
        return new private(this.height, this.ra)._(hashtable, this.o);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (else.k.equals(actionEvent.getActionCommand())) {
            if (this.n.a() == -1) {
                this.n.a((byte)1);
                ((Button)actionEvent.getSource()).setLabel(else.C);
            }
            else {
                this.n.a((byte)(-1));
                ((Button)actionEvent.getSource()).setLabel(else.D);
            }
        }
    }
    
    static {
        else.t = d(else.t);
        else.u = d(else.u);
        else.v = d(else.v);
        else.w = d(else.w);
        else.x = d(else.x);
        else.y = d(else.y);
        else.z = d(else.z);
        else.A = d(else.A);
        else.ja = d(else.ja);
        else.na = d(else.na);
        else.oa = d(else.oa);
        else.pa = d(else.pa);
        else.sa = d(else.sa);
        else.ta = d(else.ta);
        else.ua = d(else.ua);
        else.va = d(else.va);
        else.wa = d(else.wa);
        else.rb = d(else.rb);
        else.sb = d(else.sb);
        else.tb = d(else.tb);
        else._ = d(else._);
        else.a = d(else.a);
        else.b = d(else.b);
        else.c = d(else.c);
        else.d = d(else.d);
        else.e = d(else.e);
        else.f = d(else.f);
        else.g = d(else.g);
        else.h = d(else.h);
        else.i = d(else.i);
        else.j = d(else.j);
        else.k = d(else.k);
        else.C = d(else.C);
        else.D = d(else.D);
        _b = new Object();
    }
    
    private static String d(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\ueaeb');
        }
        return new String(array);
    }
}
