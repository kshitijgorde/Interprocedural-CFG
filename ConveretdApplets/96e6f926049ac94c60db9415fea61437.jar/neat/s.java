// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import neat.system.graphics.g;
import neat.system.graphics.e;
import java.util.Properties;
import java.net.MalformedURLException;
import java.net.URL;
import neat.system.f;
import neat.system.cb;

public class s implements cb, Runnable
{
    private static f a;
    public static final kb b;
    private static final kb c;
    private static final kb d;
    private static final kb e;
    public static final kb f;
    public static final kb g;
    public static final kb h;
    public static final kb i;
    public static final kb j;
    public static final kb k;
    public static final kb l;
    private URL m;
    private URL n;
    private ob o;
    private Object p;
    private i q;
    private kb r;
    private kb s;
    private boolean t;
    private boolean u;
    private boolean v;
    private long w;
    private u x;
    private static Class y;
    public static int A;
    private static String[] z;
    
    public void a(final URL m) {
        if (m == null) {
            return;
        }
        this.m = m;
        try {
            this.n = new URL(m.getProtocol(), m.getHost(), m.getPort(), "");
        }
        catch (MalformedURLException ex) {
            this.n = null;
        }
    }
    
    public void a(final boolean v) {
        this.v = v;
    }
    
    public boolean a(final String s) {
        synchronized (this.p) {
            if (this.o != null) {
                // monitorexit(this.p)
                return true;
            }
            if (this.n == null) {
                // monitorexit(this.p)
                return false;
            }
        }
        // monitorexit(this.p)
        this.w = 0L;
        final kb a = kb.a(s.z[6]);
        this.o = ob.a(a);
        a.f();
        this.o.a();
        this.o.a(this);
        this.c(s);
        return true;
    }
    
    public void a() {
        synchronized (this.p) {
            if (this.o == null) {
                // monitorexit(this.p)
                return;
            }
            this.u = true;
            this.p.notify();
        }
        // monitorexit(this.p)
    }
    
    protected void a(kb b, final kb kb) {
        this.b();
        final lb a = lb.a();
        a.c(neat.s.z[2]);
        final kb a2 = nb.a();
        a.a(a2);
        a2.f();
        a.c(neat.s.z[4]);
        a.a(this.w);
        a.c(neat.s.z[3]);
        a.c("[");
        a.a(b);
        a.c(neat.s.z[3]);
        if (kb != null) {
            a.a(kb);
        }
        b = a.b();
        synchronized (this.p) {
            if (this.o != null) {
                this.q.a(b);
                b = null;
                this.p.notify();
            }
        }
        // monitorexit(this.p)
        if (b != null) {
            b.f();
        }
    }
    
    public void a(final kb kb) {
        this.a(kb, (kb)null);
    }
    
    public void b(final String s) {
        final kb a = kb.a(s);
        this.a(a);
        a.f();
    }
    
    public void a(final kb kb, final Throwable t) {
        final lb a = lb.a();
        a.c(neat.s.z[5]);
        final kb b = nb.b(t);
        a.a(b);
        b.f();
        a.c(neat.s.z[3]);
        synchronized (this.p) {
            if (this.s != null) {
                this.t = true;
                a.a(this.s);
            }
            try {
                this.a(a);
            }
            catch (Throwable t2) {}
        }
        // monitorexit(this.p)
        final kb b2 = a.b();
        this.a(kb, b2);
        b2.f();
    }
    
    public void a(final String s, final Throwable t) {
        final kb a = kb.a(s);
        this.a(a, t);
        a.f();
    }
    
    private void c(final String s) {
        final lb a = lb.a();
        a.c(s.z[25]);
        try {
            a.c(System.getProperty(s.z[16]));
        }
        catch (Throwable t) {}
        a.c("]");
        try {
            final String property = System.getProperty(s.z[7]);
            a.c(s.z[18]);
            a.c(property);
            a.c("]");
            if (property.startsWith(s.z[21])) {
                final String property2 = ((Properties)Class.forName(s.z[22]).getMethod(s.z[19], (Class<?>[])null).invoke(null, (Object[])null)).getProperty(s.z[11]);
                a.c(s.z[15]);
                a.c(property2);
                a.c("]");
            }
            neat.system.graphics.e.a();
            a.c(s.z[29]);
            final e b = neat.system.graphics.e.b();
            if (b == null) {
                a.c(s.z[9]);
            }
            else if (b instanceof g) {
                a.c(s.z[28]);
            }
            else {
                a.c(s.z[24]);
            }
            a.c("]");
        }
        catch (Throwable t2) {}
        a.c("\n");
        a.c(s.z[20]);
        try {
            final String property3 = System.getProperty(s.z[23]);
            final String property4 = System.getProperty(s.z[27]);
            final String property5 = System.getProperty(s.z[14]);
            a.c(property3);
            a.c(s.z[12]);
            a.c(property4);
            a.c(s.z[13]);
            a.c(property5);
        }
        catch (Throwable t3) {}
        a.c(s.z[8]);
        a.a(Runtime.getRuntime().freeMemory());
        a.c(s.z[26]);
        a.a(Runtime.getRuntime().totalMemory());
        a.c(s.z[30]);
        a.d(neat.system.f.a());
        a.c(s.z[3]);
        a.c(s.z[10]);
        a.c(this.m.toExternalForm());
        a.c(s.z[3]);
        if (s != null) {
            a.c(s.z[17]);
            a.c(s);
            a.c(s.z[3]);
        }
        if (this.s != null) {
            this.s.f();
        }
        this.s = a.b();
    }
    
    public void b() {
        kb b = null;
        synchronized (this.p) {
            if (this.s != null) {
                if (this.t) {
                    // monitorexit(this.p)
                    return;
                }
                this.t = true;
                b = this.s.b();
            }
        }
        // monitorexit(this.p)
        if (b != null) {
            this.b(neat.s.z[1]);
            final kb a = kb.a(neat.s.z[0]);
            this.a(a, b);
            a.f();
            b.f();
        }
    }
    
    protected void a(final lb lb) {
    }
    
    public void run() {
    }
    
    protected void a(final int n) {
        if (this.x != null) {
            this.x.b(n);
        }
    }
    
    private void a(final URL url, final kb kb, final kb kb2) {
    }
    
    public static s c() {
        return (s)s.a.a();
    }
    
    public void f() {
        neat.s.a.a(this);
    }
    
    public void g() {
        this.q = neat.i.k();
        this.u = false;
        this.v = false;
        this.t = false;
        if (this == null) {
            throw null;
        }
        this.x = new u(this, null);
    }
    
    public void h() {
        if (this.x != null) {
            this.x.a();
        }
        synchronized (this.p) {
            this.o.f();
            this.o = null;
            this.m = null;
            this.n = null;
            this.q.j();
            this.q.f();
            this.q = null;
            if (this.r != null) {
                this.r.f();
                this.r = null;
            }
            if (this.s != null) {
                this.s.f();
                this.s = null;
            }
        }
        // monitorexit(this.p)
    }
    
    static void a(final s s, final URL url, final kb kb, final kb kb2) {
        s.a(url, kb, kb2);
    }
    
    static boolean a(final s s) {
        return s.u;
    }
    
    static Class d(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public s() {
        this.m = null;
        this.n = null;
        this.p = new Object();
        this.q = null;
        this.r = null;
        this.s = null;
        this.x = null;
    }
    
    static {
        s.z = new String[] { z(z("\"2")), z(z("5\u000e\u0002F{][\u0002Ji\u0014[\fDl\u001f\u001e\f_k\u001e\u0015A\u0005,")), z(z("*\u000f\u0006FgL")), z(z(",q")), z(z(", \u001fBl\u0016F")), z(z("*\u001e\u0017\u0016")), z(z("\u001f\u0003OHn\u0018\u001e\u0001_")), z(z("\u001b\u001a\u0019J,\u0007\u001e\u0001Om\u0003")), z(z(", \tFg\u001cF")), z(z("\u001f\u0014\u001cEf")), z(z("*\u000e\u001dG?")), z(z("3\u000e\u0006Gf8\u0015\fYg\u001c\u001e\u0001_")), z(z(", \u0000Xt\u0014\tR")), z(z(", \u0000Xc\u0003\u0018\u0007\u0016")), z(z("\u001e\bAJp\u0012\u0013")), z(z("*\r\u0002]g\u0003F")), z(z("\u001b\u001a\u0019J,\u0007\u001e\u001dXk\u001e\u0015")), z(z("*\rR")), z(z("*\r\nEf\u001e\tR")), z(z("\u0016\u001e\u001b}O'\u001e\u001dXk\u001e\u0015")), z(z("*\u0014\u001c\u0016")), z(z("<\u0012\fYm\u0002\u0014\t_")), z(z("\u0012\u0014\u0002\u0005o\u0002U\u001a_k\u001dU<Rq\u0005\u001e\u0002}g\u0003\b\u0006Dl<\u001a\u0001Je\u0014\t")), z(z("\u001e\bAEc\u001c\u001e")), z(z("\u0012\u0017\u0006[")), z(z("*\r\u0002\u0016")), z(z(", \u001bFg\u001cF")), z(z("\u001e\bA]g\u0003\b\u0006Dl")), z(z("\u0002\u000e\u0001")), z(z("*\b\f\u0016")), z(z(", \u001dD?")), z(z("\u001f\u001e\u000e_,\u0002")) };
        s.a = new f((s.y != null) ? s.y : (s.y = d(s.z[31])));
        b = kb.a(z(z("\u0002\u001e\u001d]n\u0014\u000f@Eg\u0010\u000fAXg\u0003\r\nY,\u001d\u0014\b\u0005L\u0014\u000f#De\"\u001e\u001d]n\u0014\u000f")));
        c = kb.a("?");
        d = kb.a("&");
        e = kb.a("=");
        f = kb.a(z(z("\u001f\u0017")));
        g = kb.a(z(z("\u001f\u0017\u0006O")));
        h = kb.a(z(z("\u001f\u0017\u001d")));
        i = kb.a(z(z("\u001f\u0017\bDn")));
        j = kb.a("x");
        k = kb.a("y");
        l = kb.a(z(z("?\u001e\u001bgm\u00168\u0003Bg\u001f\u000fO\u0006\"\u0014\u0015\fDf\u0014\tODdQ\u0017\u0000L")));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        int i;
        do {
            i = charArray.length;
            if (i < 2) {
                continue;
            }
            return charArray;
        } while (i == 0);
        final int n = 0;
        charArray[n] ^= '\u0002';
        return charArray;
    }
    
    private static String z(final char[] array) {
        int length;
        int n2;
        final int n = n2 = (length = array.length);
        int n3 = 0;
        while (true) {
            Label_0085: {
                if (n > 1) {
                    break Label_0085;
                }
                length = (n2 = n3);
                do {
                    final char c = array[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = 'q';
                            break;
                        }
                        case 1: {
                            c2 = '{';
                            break;
                        }
                        case 2: {
                            c2 = 'o';
                            break;
                        }
                        case 3: {
                            c2 = '+';
                            break;
                        }
                        default: {
                            c2 = '\u0002';
                            break;
                        }
                    }
                    array[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                return new String(array).intern();
            }
            continue;
        }
    }
}
