// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.d;

import java.awt.Rectangle;
import flaxchat.a.h;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.MediaTracker;
import flaxchat.a.e;
import java.awt.Component;
import java.util.Hashtable;
import java.awt.Image;

public class d
{
    private static Image a;
    private static Hashtable b;
    private static Component c;
    private static Hashtable d;
    private static int e;
    protected static final e f;
    protected static final MediaTracker g;
    private static String[] z;
    
    public static void a(final Component c) {
        flaxchat.d.d.c = c;
    }
    
    public static void a() {
        flaxchat.d.d.d.clear();
        flaxchat.d.d.a = e(flaxchat.d.d.z[19]);
        flaxchat.d.d.b = flaxchat.d.b.a(flaxchat.d.d.z[20]);
        if (flaxchat.d.d.c != null) {
            flaxchat.d.d.c.repaint();
        }
        b();
    }
    
    private static void b() {
        o();
        d();
        e();
        w();
        h();
        m();
        i();
        j();
        k();
        n();
        g();
        f();
        l();
        x();
        q();
        t();
        r();
        u();
        p();
        s();
        v();
    }
    
    public static Image a(final String s) {
        Image a = flaxchat.d.d.d.get(s);
        if (a == null) {
            a = a(c(g(s)));
            flaxchat.d.d.d.put(s, a);
        }
        return a;
    }
    
    public static Image b(final String s) {
        Image a = flaxchat.d.d.d.get(s);
        if (a == null) {
            a = a(d(String.valueOf(flaxchat.d.a.a()) + "/" + s));
            flaxchat.d.d.d.put(s, a);
        }
        return a;
    }
    
    public static URL c(final String s) {
        try {
            return new URL(flaxchat.d.a.a, String.valueOf(flaxchat.d.a.a()) + "/" + s);
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    public static URL d(final String s) {
        try {
            return new URL(flaxchat.d.a.a, s);
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    public static Image e(final String s) {
        Image a = flaxchat.d.d.d.get(s);
        if (a == null) {
            a = a(c(s));
            flaxchat.d.d.d.put(s, a);
        }
        return a;
    }
    
    public static Image a(final URL url) {
        final Image image = Toolkit.getDefaultToolkit().getImage(url);
        if (image == null) {
            return null;
        }
        a(image);
        return image;
    }
    
    public static void a(final Image image) {
        synchronized (flaxchat.d.d.g) {
            final int c = c();
            flaxchat.d.d.g.addImage(image, c);
            try {
                flaxchat.d.d.g.waitForID(c, 5000L);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            flaxchat.d.d.g.statusID(c, false);
            flaxchat.d.d.g.removeImage(image, c);
        }
        // monitorexit(d.g)
    }
    
    private static int c() {
        synchronized (flaxchat.d.d.g) {
            // monitorexit(d.g)
            return ++flaxchat.d.d.e;
        }
    }
    
    public static Image f(final String s) {
        return a(s, -1);
    }
    
    public static Image a(final String s, final int n) {
        final Image image = flaxchat.d.d.d.get(s);
        if (image != null) {
            return image;
        }
        final Image a = h.a(flaxchat.d.d.c, flaxchat.d.d.a, g(s), n);
        if (a == null) {
            return null;
        }
        flaxchat.d.d.d.put(s, a);
        return a;
    }
    
    public static Image d() {
        return f(flaxchat.d.d.z[7]);
    }
    
    public static Image e() {
        return f(flaxchat.d.d.z[3]);
    }
    
    public static Rectangle f() {
        final int[] a = h.a(g(flaxchat.d.d.z[11]), ',');
        if (a == null) {
            return null;
        }
        if (a.length < 4) {
            return null;
        }
        return new Rectangle(a[0], a[1], a[2], a[3]);
    }
    
    public static Rectangle g() {
        final int[] a = h.a(g(flaxchat.d.d.z[22]), ',');
        if (a == null) {
            return null;
        }
        if (a.length < 4) {
            return null;
        }
        return new Rectangle(a[0], a[1], a[2], a[3]);
    }
    
    public static Image h() {
        Image a = flaxchat.d.d.d.get(flaxchat.d.d.z[5]);
        if (a == null) {
            final int[] a2 = h.a(g(flaxchat.d.d.z[4]), ',');
            final int[] a3 = h.a(g(flaxchat.d.d.z[6]), ',');
            if (a2 == null) {
                return null;
            }
            if (a2.length < 4) {
                return null;
            }
            a = h.a(flaxchat.d.d.c, flaxchat.d.d.a, a2[0], a2[1], a3[0] - a2[0] + 1, a2[3]);
            flaxchat.d.d.d.put(flaxchat.d.d.z[5], a);
        }
        return a;
    }
    
    public static Image i() {
        Image a = flaxchat.d.d.d.get(flaxchat.d.d.z[17]);
        if (a == null) {
            final int[] a2 = h.a(g(flaxchat.d.d.z[6]), ',');
            a = h.a(flaxchat.d.d.c, flaxchat.d.d.a, a2[0], a2[1], a2[2], a2[3]);
            flaxchat.d.d.d.put(flaxchat.d.d.z[17], a);
        }
        return a;
    }
    
    public static Image j() {
        Image a = flaxchat.d.d.d.get(flaxchat.d.d.z[15]);
        if (a == null) {
            final int[] a2 = h.a(g(flaxchat.d.d.z[4]), ',');
            final int[] a3 = h.a(g(flaxchat.d.d.z[6]), ',');
            a = h.a(flaxchat.d.d.c, flaxchat.d.d.a, a3[0] + a3[2], a3[1], a2[0] + a2[2] - (a3[0] + a3[2]), a3[3]);
            flaxchat.d.d.d.put(flaxchat.d.d.z[15], a);
        }
        return a;
    }
    
    public static Rectangle k() {
        final int g = flaxchat.d.b.g;
        final int[] a = h.a(g(flaxchat.d.d.z[4]), ',');
        final int[] a2 = h.a(g(flaxchat.d.d.z[6]), ',');
        final Rectangle rectangle = new Rectangle(a2[0] + a2[2], a2[1], a[0] + a[2] - (a2[0] + a2[2]), a2[3]);
        if (g != 0) {
            int c = flaxchat.a.e.c;
            flaxchat.a.e.c = ++c;
        }
        return rectangle;
    }
    
    public static Rectangle l() {
        final int[] a = h.a(g(flaxchat.d.d.z[4]), ',');
        return new Rectangle(a[0], a[1], a[2], a[3]);
    }
    
    public static Rectangle m() {
        final int[] a = h.a(g(flaxchat.d.d.z[6]), ',');
        return new Rectangle(a[0], a[1], a[2], a[3]);
    }
    
    private static String g(final String s) {
        return flaxchat.d.d.b.get(s);
    }
    
    public static Image n() {
        return f(flaxchat.d.d.z[13]);
    }
    
    public static Image o() {
        return a(flaxchat.d.d.z[10]);
    }
    
    public static Image p() {
        return f(flaxchat.d.d.z[14]);
    }
    
    public static Image q() {
        return f(flaxchat.d.d.z[12]);
    }
    
    public static Image r() {
        return f(flaxchat.d.d.z[0]);
    }
    
    public static Image s() {
        return f(flaxchat.d.d.z[2]);
    }
    
    public static Image t() {
        return f(flaxchat.d.d.z[18]);
    }
    
    public static Image u() {
        return f(flaxchat.d.d.z[16]);
    }
    
    public static Image v() {
        return f(flaxchat.d.d.z[1]);
    }
    
    public static Image w() {
        return a(flaxchat.d.d.z[9], flaxchat.d.b.a(flaxchat.d.d.z[8], -1));
    }
    
    public static Image x() {
        return a(flaxchat.d.d.z[21], flaxchat.d.b.a(flaxchat.d.d.z[8], -1));
    }
    
    static {
        flaxchat.d.d.z = new String[] { z(z("\u0003aD5\\\u001cD_\"U\u0014")), z(z("\u0004kB6U9aY4")), z(z("\u0003aD5\\\u001cVY*\u007f\u0006gD")), z(z("\u0012wB._\u001eRD?C\u0003")), z(z("\u0016pW7U$kB6U")), z(z("\u00043")), z(z("\u0016pW7U$kB6UA")), z(z("\u0012wB._\u001e")), z(z("\u0004cT\u0013S\u001fle3J\u0015")), z(z("\u0013jW4^\u0015n\u007f9_\u001e")), z(z("\u0012cU1W\u0002mC4T")), z(z("\u0016pW7U=cN")), z(z("\u0003aD5\\\u001c@Y.D\u001fo")), z(z("\u0016pW7U2e")), z(z("\u0003aD5\\\u001cVY*")), z(z("\u00041")), z(z("\u0003aD5\\\u001cD_\"U\u0014M@?B")), z(z("\u00040")), z(z("\u0003aD5\\\u001c@Y.D\u001foy,U\u0002")), z(z("_oW3^^e_<")), z(z("\u0019oW=U")), z(z("\u0001wS(I9aY4")), z(z("\u0016pW7U3nY)U")) };
        flaxchat.d.d.d = new Hashtable();
        f = new e();
        g = new MediaTracker(flaxchat.d.d.f);
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '0';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = 'p';
                    break;
                }
                case 1: {
                    c2 = '\u0002';
                    break;
                }
                case 2: {
                    c2 = '6';
                    break;
                }
                case 3: {
                    c2 = 'Z';
                    break;
                }
                default: {
                    c2 = '0';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
