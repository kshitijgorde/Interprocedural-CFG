import java.net.URLConnection;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.net.URL;
import au.com.rocketdog.project.awc.applet.Main;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class n implements o
{
    public static final Hashtable a;
    private boolean b;
    public static long c;
    public static long d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;
    private int n;
    private String o;
    private int p;
    private int q;
    private int r;
    private boolean s;
    private int t;
    private String u;
    private String v;
    private String w;
    private int x;
    private boolean y;
    private static n z;
    
    public int a() {
        switch (this.h()) {
            case 0: {
                if (this.w()) {
                    return 5;
                }
                return 1;
            }
            case 2: {
                return 15;
            }
            case 1: {
                return 10;
            }
            case 3: {
                return 20;
            }
            default: {
                return 1;
            }
        }
    }
    
    public static synchronized n b() {
        if (n.z == null) {
            n.z = new n();
        }
        return n.z;
    }
    
    private n() {
        this.b = false;
        this.i = false;
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = false;
        this.j = false;
        this.k = false;
        this.l = false;
        this.m = false;
        this.n = 0;
        this.p = 0;
        this.q = 0;
        this.s = false;
        this.t = 0;
        this.u = "";
    }
    
    public static synchronized void c() {
        n.z = null;
    }
    
    public int d() {
        return 9004;
    }
    
    public String e() {
        return "camsv4.anywebcam.com";
    }
    
    public void a(final String w) {
        this.w = w;
    }
    
    public String f() {
        return this.w;
    }
    
    public int g() {
        return this.t;
    }
    
    public int h() {
        return this.r;
    }
    
    private boolean ad() {
        b.a("called canTempUpgrade()", 3);
        try {
            final URL url = new URL("http://" + Main.b + "/awc/servlet/camup?id=" + this.y());
            final URLConnection openConnection = url.openConnection();
            openConnection.setUseCaches(false);
            openConnection.connect();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(url.openStream())));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.indexOf(89) >= 0) {
                    b.a("called canTempUpgrade() TRUE", 3);
                    bufferedReader.close();
                    return true;
                }
            }
            bufferedReader.close();
        }
        catch (Exception ex) {
            b.a(ex, 3);
        }
        b.a("called canTempUpgrade() FALSE", 3);
        return false;
    }
    
    public void i() {
        final ba ba = h.f().ab.get(this.aa());
        if (ba != null && ba.w() == 2) {
            this.k();
        }
        else {
            this.l();
        }
    }
    
    public int j() {
        return h.f().ab.containsKey(this.aa()) ? 1 : 0;
    }
    
    public void k() {
        if (this.r == 0) {
            if (this.ad()) {
                System.out.println(this.f);
                this.e = true;
                this.f = true;
                this.g = true;
                this.h = true;
                be.f = n.c;
            }
            else {
                this.l();
            }
        }
    }
    
    public void l() {
        b.a("called downgradeTemp()", 3);
        b.a("name = " + n.z.z(), 3);
        b.a("membership = " + n.z.h(), 3);
        if (this.r == 0 && !this.ac()) {
            b.a("downgraded! ", 3);
            this.e = this.j;
            this.f = this.k;
            this.g = this.l;
            this.h = this.m;
            if (this.p()) {
                be.f = n.c;
            }
            else {
                be.f = n.d;
            }
            if (!this.w()) {
                l.b().c();
            }
        }
    }
    
    public void m() {
        b.a("called bonusUpgrade()", 3);
        b.a("name = " + n.z.z(), 3);
        b.a("membership = " + n.z.h(), 3);
        this.e = true;
        this.f = true;
        this.h = true;
        be.f = n.c;
        this.h(true);
    }
    
    public void n() {
        this.h(false);
        b.a("called bonusDowngrade()", 3);
        b.a("name = " + n.z.z(), 3);
        b.a("membership = " + n.z.h(), 3);
        this.e = this.j;
        this.f = this.k;
        this.h = this.m;
        if (this.p()) {
            be.f = n.c;
        }
        else {
            be.f = n.d;
        }
        if (!this.w()) {
            l.b().c();
        }
    }
    
    public void a(final int r) {
        this.r = r;
        if (r == 3) {
            this.a(true);
        }
    }
    
    public boolean o() {
        return this.s;
    }
    
    public boolean p() {
        return this.h;
    }
    
    public void a(final boolean s) {
        this.s = s;
    }
    
    public void b(final boolean m) {
        this.m = m;
        this.h = this.m;
    }
    
    public void b(final int t) {
        this.t = t;
    }
    
    public void b(final String u) {
        this.u = u;
    }
    
    public void q() {
        this.v = this.t + "^" + this.r + this.x();
    }
    
    public String r() {
        return this.v;
    }
    
    public void c(final int q) {
        this.q = q;
    }
    
    public int s() {
        return this.q;
    }
    
    public boolean t() {
        return this.e;
    }
    
    public boolean u() {
        return this.g;
    }
    
    public boolean v() {
        return this.b;
    }
    
    public boolean w() {
        return this.f;
    }
    
    public int x() {
        return this.n;
    }
    
    public void d(final int n) {
        this.n = n;
    }
    
    public void c(final boolean j) {
        this.j = j;
        this.e = this.j;
    }
    
    public void d(final boolean b) {
        this.b = b;
    }
    
    public void e(final boolean l) {
        this.l = l;
        this.g = this.l;
    }
    
    public void f(final boolean k) {
        this.k = k;
        this.f = this.k;
    }
    
    public int y() {
        return this.t;
    }
    
    public String z() {
        return this.u;
    }
    
    public String aa() {
        if (this.o == null) {
            this.o = (this.u + "_" + this.q).toLowerCase();
        }
        return this.o;
    }
    
    public String ab() {
        return System.getProperty("user.home") + System.getProperty("file.separator") + "awc_" + this.z();
    }
    
    public void e(final int x) {
        this.x = x;
    }
    
    public void g(final boolean y) {
        this.y = y;
    }
    
    public boolean ac() {
        return this.i;
    }
    
    public void h(final boolean i) {
        this.i = i;
    }
    
    static {
        a = new Hashtable();
        n.c = 1000L;
        n.d = 1000L;
    }
}
