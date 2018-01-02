// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.io.IOException;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.DataInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.applet.Applet;

public abstract class dh extends Applet implements ak, bo
{
    public static final boolean a;
    private static boolean e;
    public static boolean b;
    public static boolean c;
    public static boolean d;
    private dl a;
    private dd a;
    private boolean f;
    public cb a;
    public ac a;
    public String a;
    public String b;
    private y a;
    es a;
    private boolean g;
    private boolean h;
    
    public dh() {
        this.a = new dl(this);
        this.a = new dd(this);
        this.f = true;
        this.a = new y();
        this.g = false;
        this.h = false;
        this.a.start();
        this.a.start();
        this.a = new bn(this);
        this.a = this.a.a;
    }
    
    public final void a(final String s) {
        try {
            if (dh.a && !dh.e) {
                new er(this.a(1716524426), this);
                return;
            }
            final dj dj;
            if ((dj = new dj(this)).a != null) {
                dj.a.eval(s);
            }
        }
        catch (Throwable t) {}
    }
    
    public final void a(final String s, final String s2) {
        try {
            this.getAppletContext().showDocument(new URL(this.getDocumentBase(), s), s2);
        }
        catch (MalformedURLException ex) {}
    }
    
    public final String a(final int n) {
        return this.a.a(n);
    }
    
    public final void b(final String s) {
        if (this.f) {
            this.a.a(s);
        }
    }
    
    public void c() {
    }
    
    public void b() {
    }
    
    public void a(final Throwable t) {
    }
    
    public final void a(int n, final Object o) {
        switch (n) {
            case 0: {
                final String parameter = this.getParameter("ldict_url");
                final String parameter2 = this.getParameter("loading_messsage");
                if (parameter != null) {
                    this.a.a.a(this.a = new es((parameter2 == null) ? "Loading text" : parameter2, 0), 1, 1, 0, 0, true);
                    final String s = parameter;
                    final dl a = this.a;
                    n = 0;
                    this.a.a(new cf(s, a));
                    return;
                }
                this.g = true;
                this.c();
            }
            case 2: {
                if (this.g) {
                    return;
                }
                break;
            }
            case 3: {
                if (this.g) {
                    return;
                }
                break;
            }
            case 5: {
                final cf cf;
                if ((cf = (cf)o).a == this.a && !this.h) {
                    if (cf.a != null) {
                        try {
                            final y a2 = this.a;
                            final String a3 = cf.a;
                            final y y = a2;
                            final byte[] array = new byte[a3.length()];
                            a3.getBytes(0, array.length, array, 0);
                            y.a(new DataInputStream(new bs(array)));
                            this.a.a.a(this.a);
                            this.g = true;
                            this.c();
                            this.a.a.f();
                            return;
                        }
                        catch (IOException ex) {
                            try {
                                this.a.a("Malformed text data from " + InetAddress.getByName(this.getCodeBase().getHost()).toString());
                                return;
                            }
                            catch (UnknownHostException ex2) {
                                return;
                            }
                        }
                    }
                    this.a.a("Error loading text: " + ((cf.a == null) ? "no data" : cf.a.toString()));
                    break;
                }
                break;
            }
        }
    }
    
    public final void init() {
        this.a = this.getParameter("intl_host");
        this.b = this.getParameter("intl_code");
        if (this.a == null) {
            this.a = "games.yahoo.com";
        }
        if (this.b == null) {
            this.b = "us";
        }
        this.a.a(this, 0, null);
    }
    
    public final void destroy() {
        this.a.a.a();
        this.a.a.a();
        synchronized (this.a.a) {
            this.a.a.a();
            this.a.a();
            this.a.a();
            if (this.g) {
                this.b();
            }
            else {
                this.h = true;
            }
            this.a.a.a();
        }
    }
    
    public final void start() {
        this.a.a(this, 2, null);
    }
    
    public final void stop() {
        this.a.a(this, 3, null);
    }
    
    static {
        a = (System.getProperty("os.name").indexOf("Mac OS") != -1 || System.getProperty("os.name").indexOf("MacOS") != -1);
        dh.e = (System.getProperty("os.name").indexOf("Mac OS X") != -1);
        dh.b = (System.getProperty("os.name").indexOf("Windows") != -1);
        System.getProperty("java.vendor").indexOf("Microsoft");
        dh.c = (System.getProperty("java.vendor").indexOf("Netscape") != -1);
        dh.d = System.getProperty("java.version").startsWith("1.0");
    }
}
