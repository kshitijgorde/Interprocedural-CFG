// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.i;

import java.awt.Rectangle;
import flaxchat.e.g;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.MediaTracker;
import flaxchat.e.e;
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
        flaxchat.i.d.c = c;
    }
    
    public static void a() {
        flaxchat.i.d.d.clear();
        flaxchat.i.d.a = e(flaxchat.i.d.z[6]);
        flaxchat.i.d.b = flaxchat.i.b.a(flaxchat.i.d.z[7]);
        if (flaxchat.i.d.c != null) {
            flaxchat.i.d.c.repaint();
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
        Image a = flaxchat.i.d.d.get(s);
        if (a == null) {
            a = a(c(g(s)));
            flaxchat.i.d.d.put(s, a);
        }
        return a;
    }
    
    public static Image b(final String s) {
        Image a = flaxchat.i.d.d.get(s);
        if (a == null) {
            a = a(d(String.valueOf(flaxchat.i.a.a()) + "/" + s));
            flaxchat.i.d.d.put(s, a);
        }
        return a;
    }
    
    public static URL c(final String s) {
        try {
            return new URL(flaxchat.i.a.a, String.valueOf(flaxchat.i.a.b()) + "/" + s);
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    public static URL d(final String s) {
        try {
            return new URL(flaxchat.i.a.a, s);
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    public static Image e(final String s) {
        Image a = flaxchat.i.d.d.get(s);
        if (a == null) {
            a = a(c(s));
            flaxchat.i.d.d.put(s, a);
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
        synchronized (flaxchat.i.d.g) {
            final int c = c();
            flaxchat.i.d.g.addImage(image, c);
            try {
                flaxchat.i.d.g.waitForID(c, 5000L);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            flaxchat.i.d.g.statusID(c, false);
            flaxchat.i.d.g.removeImage(image, c);
        }
        // monitorexit(d.g)
    }
    
    private static int c() {
        synchronized (flaxchat.i.d.g) {
            // monitorexit(d.g)
            return ++flaxchat.i.d.e;
        }
    }
    
    public static Image f(final String s) {
        return a(s, -1);
    }
    
    public static Image a(final String s, final int n) {
        final Image image = flaxchat.i.d.d.get(s);
        if (image != null) {
            return image;
        }
        final Image a = flaxchat.e.g.a(flaxchat.i.d.c, flaxchat.i.d.a, g(s), n);
        if (a == null) {
            return null;
        }
        flaxchat.i.d.d.put(s, a);
        return a;
    }
    
    public static Image d() {
        return f(flaxchat.i.d.z[12]);
    }
    
    public static Image e() {
        return f(flaxchat.i.d.z[8]);
    }
    
    public static Rectangle f() {
        final int[] a = flaxchat.e.g.a(g(flaxchat.i.d.z[14]), ',');
        if (a == null) {
            return null;
        }
        if (a.length < 4) {
            return null;
        }
        return new Rectangle(a[0], a[1], a[2], a[3]);
    }
    
    public static Rectangle g() {
        final int[] a = flaxchat.e.g.a(g(flaxchat.i.d.z[13]), ',');
        if (a == null) {
            return null;
        }
        if (a.length < 4) {
            return null;
        }
        return new Rectangle(a[0], a[1], a[2], a[3]);
    }
    
    public static Image h() {
        Image a = flaxchat.i.d.d.get(flaxchat.i.d.z[18]);
        if (a == null) {
            final int[] a2 = flaxchat.e.g.a(g(flaxchat.i.d.z[9]), ',');
            final int[] a3 = flaxchat.e.g.a(g(flaxchat.i.d.z[2]), ',');
            if (a2 == null) {
                return null;
            }
            if (a2.length < 4) {
                return null;
            }
            a = flaxchat.e.g.a(flaxchat.i.d.c, flaxchat.i.d.a, a2[0], a2[1], a3[0] - a2[0] + 1, a2[3]);
            flaxchat.i.d.d.put(flaxchat.i.d.z[18], a);
        }
        return a;
    }
    
    public static Image i() {
        Image a = flaxchat.i.d.d.get(flaxchat.i.d.z[10]);
        if (a == null) {
            final int[] a2 = flaxchat.e.g.a(g(flaxchat.i.d.z[2]), ',');
            a = flaxchat.e.g.a(flaxchat.i.d.c, flaxchat.i.d.a, a2[0], a2[1], a2[2], a2[3]);
            flaxchat.i.d.d.put(flaxchat.i.d.z[10], a);
        }
        return a;
    }
    
    public static Image j() {
        Image a = flaxchat.i.d.d.get(flaxchat.i.d.z[15]);
        if (a == null) {
            final int[] a2 = flaxchat.e.g.a(g(flaxchat.i.d.z[9]), ',');
            final int[] a3 = flaxchat.e.g.a(g(flaxchat.i.d.z[2]), ',');
            a = flaxchat.e.g.a(flaxchat.i.d.c, flaxchat.i.d.a, a3[0] + a3[2], a3[1], a2[0] + a2[2] - (a3[0] + a3[2]), a3[3]);
            flaxchat.i.d.d.put(flaxchat.i.d.z[15], a);
        }
        return a;
    }
    
    public static Rectangle k() {
        final int g = flaxchat.i.b.g;
        final int[] a = flaxchat.e.g.a(g(flaxchat.i.d.z[9]), ',');
        final int[] a2 = flaxchat.e.g.a(g(flaxchat.i.d.z[2]), ',');
        final Rectangle rectangle = new Rectangle(a2[0] + a2[2], a2[1], a[0] + a[2] - (a2[0] + a2[2]), a2[3]);
        if (g != 0) {
            flaxchat.e.e.c = !flaxchat.e.e.c;
        }
        return rectangle;
    }
    
    public static Rectangle l() {
        final int[] a = flaxchat.e.g.a(g(flaxchat.i.d.z[9]), ',');
        return new Rectangle(a[0], a[1], a[2], a[3]);
    }
    
    public static Rectangle m() {
        final int[] a = flaxchat.e.g.a(g(flaxchat.i.d.z[2]), ',');
        return new Rectangle(a[0], a[1], a[2], a[3]);
    }
    
    private static String g(final String s) {
        return flaxchat.i.d.b.get(s);
    }
    
    public static Image n() {
        return f(flaxchat.i.d.z[1]);
    }
    
    public static Image o() {
        return a(flaxchat.i.d.z[20]);
    }
    
    public static Image p() {
        return f(flaxchat.i.d.z[19]);
    }
    
    public static Image q() {
        return f(flaxchat.i.d.z[22]);
    }
    
    public static Image r() {
        return f(flaxchat.i.d.z[0]);
    }
    
    public static Image s() {
        return f(flaxchat.i.d.z[5]);
    }
    
    public static Image t() {
        return f(flaxchat.i.d.z[3]);
    }
    
    public static Image u() {
        return f(flaxchat.i.d.z[4]);
    }
    
    public static Image v() {
        return f(flaxchat.i.d.z[11]);
    }
    
    public static Image w() {
        return a(flaxchat.i.d.z[17], flaxchat.i.b.a(flaxchat.i.d.z[16], -1));
    }
    
    public static Image x() {
        return a(flaxchat.i.d.z[21], flaxchat.i.b.a(flaxchat.i.d.z[16], -1));
    }
    
    static {
        flaxchat.i.d.z = new String[] { z(z("{\u001aOTcd?TCjl")), z(z("n\u000b\\VjJ\u001e")), z(z("n\u000b\\Vj\\\u0010IWj9")), z(z("{\u001aOTcd;RO{g\u0014rMjz")), z(z("{\u001aOTcd?TCjl6K^}")), z(z("{\u001aOTcd-RK@~\u001cO")), z(z("'\u0014\\Ra&\u001eT]")), z(z("a\u0014\\\\j")), z(z("j\fIO`f)O^|{")), z(z("n\u000b\\Vj\\\u0010IWj")), z(z("|K")), z(z("|\u0010IWjA\u001aRU")), z(z("j\fIO`f")), z(z("n\u000b\\VjK\u0015RHj")), z(z("n\u000b\\VjE\u0018E")), z(z("|J")), z(z("|\u0018_rlg\u0017nRum")), z(z("k\u0011\\Uam\u0015tX`f")), z(z("|H")), z(z("{\u001aOTcd-RK")), z(z("j\u0018^Phz\u0016HUk")), z(z("y\fXIvA\u001aRU")), z(z("{\u001aOTcd;RO{g\u0014")) };
        flaxchat.i.d.d = new Hashtable();
        f = new e();
        g = new MediaTracker(flaxchat.i.d.f);
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '\u000f';
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
                    c2 = '\b';
                    break;
                }
                case 1: {
                    c2 = 'y';
                    break;
                }
                case 2: {
                    c2 = '=';
                    break;
                }
                case 3: {
                    c2 = ';';
                    break;
                }
                default: {
                    c2 = '\u000f';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
