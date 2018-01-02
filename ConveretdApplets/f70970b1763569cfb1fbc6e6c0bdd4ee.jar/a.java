import java.io.DataInputStream;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.util.Enumeration;
import java.util.Stack;
import java.net.SocketException;
import java.io.IOException;
import java.util.Vector;
import com.alphatrade.applet.BaseBannerApplet;
import java.util.Hashtable;
import java.util.Properties;

// 
// Decompiled by Procyon v0.5.30
// 

public final class a extends Thread
{
    public static a a;
    private Properties b;
    private k c;
    private f d;
    private h e;
    private e f;
    private i g;
    private int h;
    private int i;
    private int j;
    private j k;
    private g l;
    private boolean m;
    private int n;
    private Hashtable o;
    private boolean p;
    
    public static synchronized a a(final String s, final int n) {
        if (a.a == null) {
            a.a = new a(s, n);
        }
        return a.a;
    }
    
    public a(final String s, final int n) {
        super("FreeQuotesPublisher");
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = 0;
        this.i = 0;
        this.j = 5;
        this.k = null;
        this.l = null;
        this.m = false;
        this.n = 0;
        this.o = null;
        this.p = false;
        final byte[] array = new byte[13];
        this.n = 0;
        this.o = new Hashtable(20);
        ((Hashtable<String, String>)(this.b = new Properties())).put("host", s);
        ((Hashtable<String, String>)this.b).put("port", "1580");
        ((Hashtable<String, String>)this.b).put("tunnelPort", "80");
        ((Hashtable<String, String>)this.b).put("proxyPort", Integer.toString(n));
        ((Hashtable<String, String>)this.b).put("serverBlock", "false");
        this.k = new j();
        (this.g = new i()).a(60000, new g("P", 11));
        this.l = new g("AUTH", 1);
        int i = 0;
        final byte[] array2 = array;
        final int n2 = 0;
        ++i;
        array2[n2] = 0;
        final byte[] array3 = array;
        final int n3 = 1;
        ++i;
        array3[n3] = 0;
        final byte[] array4 = array;
        final int n4 = 2;
        ++i;
        array4[n4] = 0;
        final byte[] array5 = array;
        final int n5 = 3;
        ++i;
        array5[n5] = 1;
        while (i < array.length) {
            array[i] = 0;
            ++i;
        }
        this.l.b(array);
        this.p = true;
        this.start();
    }
    
    public final synchronized void a() {
        this.d();
    }
    
    public final synchronized void b() {
        if (this.e() <= 0) {
            if (a.a == this) {
                a.a = null;
            }
            this.c();
        }
    }
    
    private void c() {
        this.p = false;
        this.k.a();
        try {
            if (this.g != null) {
                this.g.a();
                this.g = null;
            }
        }
        catch (Exception ex) {}
        try {
            if (this.f != null) {
                this.f.b();
                this.f = null;
            }
        }
        catch (Exception ex2) {}
    }
    
    private void d() {
        ++this.n;
    }
    
    private int e() {
        return --this.n;
    }
    
    public final synchronized void a(final BaseBannerApplet baseBannerApplet, final String s) {
        this.c(baseBannerApplet, "L1.D.S." + s);
        this.c(baseBannerApplet, "L1.D.U." + s);
    }
    
    public final synchronized void b(final BaseBannerApplet baseBannerApplet, final String s) {
        this.d(baseBannerApplet, "L1.D.S." + s);
        this.d(baseBannerApplet, "L1.D.U." + s);
    }
    
    private void c(final BaseBannerApplet baseBannerApplet, final String s) {
        final Vector<BaseBannerApplet> vector;
        if ((vector = this.o.get(s)) == null) {
            final Vector<BaseBannerApplet> vector2;
            (vector2 = new Vector<BaseBannerApplet>(1, 2)).addElement(baseBannerApplet);
            this.o.put(s, vector2);
            final g g = new g(s, 1);
            if (!this.m) {
                this.g.a(g);
            }
        }
        else if (!vector.contains(baseBannerApplet)) {
            vector.addElement(baseBannerApplet);
        }
    }
    
    private void d(final BaseBannerApplet baseBannerApplet, final String s) {
        final Vector vector;
        if ((vector = this.o.get(s)) != null) {
            vector.removeElement(baseBannerApplet);
            if (vector.size() <= 0) {
                this.o.remove(s);
                final g g = new g(s, 2);
                if (!this.m) {
                    this.g.a(g);
                }
            }
        }
    }
    
    private boolean f() throws IOException {
        String s = null;
        try {
            if (this.f != null) {
                try {
                    this.g.a((e)null);
                    this.f.b();
                    this.f = null;
                }
                catch (Exception ex3) {}
            }
            this.h = this.i;
            while (this.f == null && this.p) {
                if (this.h == 0) {
                    this.h = 1;
                }
                if (this.h == 1) {
                    if (this.c == null) {
                        this.c = new k(this.b);
                    }
                    if (this.c.a(10000)) {
                        this.f = this.c;
                        s = "Direct";
                        break;
                    }
                    this.c.b();
                    if (this.i == 0) {
                        this.h = 2;
                    }
                }
                if (this.h == 2) {
                    if (this.d == null) {
                        this.d = new f(this.b);
                    }
                    if (this.d.a(10000)) {
                        this.f = this.d;
                        s = "Tunnel";
                        break;
                    }
                    this.d.b();
                    if (this.i == 0) {
                        this.h = 3;
                    }
                }
                if (this.h == 3) {
                    if (this.e == null) {
                        this.e = new h(this.b);
                    }
                    if (this.e.a(10000)) {
                        this.f = this.e;
                        s = "Proxy";
                        break;
                    }
                    this.e.b();
                    if (this.i == 0) {
                        this.h = 0;
                    }
                }
                if (this.f == null) {
                    --this.j;
                    if (this.j <= 0) {
                        this.i = 0;
                    }
                    try {
                        Thread.sleep(1000L);
                    }
                    catch (Exception ex4) {}
                }
            }
            if (s != null) {
                System.out.println(s);
            }
            this.f.a(this.k.b());
            this.f.a();
            this.g.a(this.f);
            this.i = 0;
            this.j = 5;
            this.g.a(this.l);
        }
        catch (IOException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            System.out.println("FreeQuotesPublisher.getActiveService(): " + ex2);
            ex2.printStackTrace();
        }
        return this.f != null;
    }
    
    public final void run() {
        final byte[] array = new byte[4096];
        while (this.p) {
            try {
                if (!this.f()) {
                    System.out.println("Can't connect to a Service.");
                    Thread.sleep(1000L);
                }
                do {
                    final int a;
                    if ((a = this.f.a(array, 0, 4096)) > 0) {
                        if (this.k.a(array, 0, a) <= 0) {
                            continue;
                        }
                        try {
                            g c;
                            while ((c = this.k.c()) != null && this.p) {
                                this.a(c);
                            }
                        }
                        catch (Exception ex) {
                            System.err.println("FreeQuotesPublisher.run(): " + ex);
                            ex.printStackTrace();
                        }
                    }
                    else {
                        if (a < 0) {
                            break;
                        }
                        continue;
                    }
                } while (this.k.a && this.p && this.f != null);
                System.err.println("FreeQuotesPublisher.run(): Service stream closed!");
                Thread.sleep(5000L);
            }
            catch (SocketException ex3) {}
            catch (IOException ex4) {}
            catch (Exception ex2) {
                System.err.println("FreeQuotesPublisher.run(): " + ex2);
                ex2.printStackTrace();
                try {
                    Thread.sleep(1000L);
                }
                catch (Exception ex5) {}
            }
            finally {
                if (this.f != null) {
                    try {
                        this.g.a((e)null);
                        this.f.b();
                        this.f = null;
                    }
                    catch (Exception ex6) {}
                }
            }
        }
    }
    
    private void a(final g g) {
        Vector vector = null;
        switch (g.d) {
            case 24: {
                vector = this.o.remove(g.b);
            }
            case 23:
            case 25: {
                if (vector == null) {
                    vector = this.o.get(g.b);
                }
                if (vector != null) {
                    final Stack b;
                    (b = b(g)).push(g.b.substring(7));
                    a(vector, b);
                    return;
                }
                break;
            }
            case 34:
            case 35: {}
            case 36: {
                this.i = this.h;
                this.j = 5;
                final Enumeration<String> keys = this.o.keys();
                while (keys.hasMoreElements()) {
                    this.g.a(new g(keys.nextElement(), 1));
                }
                break;
            }
        }
    }
    
    private static void a(final Vector vector, final Stack stack) {
        final Enumeration<BaseBannerApplet> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final BaseBannerApplet baseBannerApplet;
            if ((baseBannerApplet = elements.nextElement()) != null) {
                baseBannerApplet.a((Stack)stack.clone());
            }
        }
    }
    
    private static Stack b(final g g) {
        DataInputStream dataInputStream = null;
        Vector<String> vector = null;
        String line = null;
        try {
            if (g.b() <= 0) {
                try {
                    null.close();
                }
                catch (Exception ex2) {}
                return null;
            }
            dataInputStream = new DataInputStream(new d(new ByteArrayInputStream(g.e)));
            Label_0301: {
                while ((line = dataInputStream.readLine()) != null) {
                    if (line.length() > 0) {
                        vector = new Stack<String>();
                        int n = -1;
                        while (true) {
                            final int n2 = n + 1;
                            if ((n = line.indexOf("\u0000", n2)) <= n2) {
                                if (n == n2) {
                                    continue;
                                }
                                if (n >= 0 || n2 >= line.length()) {
                                    break Label_0301;
                                }
                                n = line.length();
                            }
                            final String substring;
                            final char char1;
                            if ((char1 = (substring = line.substring(n2, n)).charAt(0)) == 'D') {
                                vector.addElement((String)new Double(substring.substring(1)));
                            }
                            else if (char1 == 'F') {
                                vector.addElement((String)new Float(substring.substring(1)));
                            }
                            else if (char1 == 'L') {
                                vector.addElement((String)new Long(substring.substring(1)));
                            }
                            else if (char1 == 'I') {
                                vector.addElement((String)new Integer(substring.substring(1)));
                            }
                            else if (char1 == 'S') {
                                vector.addElement(substring.substring(1));
                            }
                            else {
                                vector.addElement(substring.substring(1));
                            }
                        }
                    }
                }
                try {}
                catch (Exception ex3) {}
            }
        }
        catch (Exception ex) {
            System.err.println("Alpha Message Convert Error: " + ex);
            System.err.println("Alpha Message Convert Error: line: {" + line + "}");
            vector = null;
            try {
                dataInputStream.close();
            }
            catch (Exception ex4) {}
        }
        finally {
            try {
                dataInputStream.close();
            }
            catch (Exception ex5) {}
        }
        return (Stack)vector;
    }
    
    static {
        a.a = null;
    }
}
