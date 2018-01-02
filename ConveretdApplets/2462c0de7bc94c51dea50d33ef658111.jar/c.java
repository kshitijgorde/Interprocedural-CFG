import java.awt.Event;
import java.net.MalformedURLException;
import java.util.Date;
import java.net.URL;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class c extends Frame implements Runnable, b
{
    public boolean a;
    public audioclipstream b;
    public Thread c;
    public int d;
    public int e;
    public String[] f;
    public boolean[] g;
    public boolean[] h;
    public boolean i;
    public int j;
    public int k;
    public String l;
    public String m;
    public long n;
    public int o;
    public String p;
    public Thread q;
    public boolean r;
    public boolean s;
    public e t;
    public int u;
    public int v;
    public int w;
    public String x;
    public URL y;
    public String z;
    public boolean aa;
    
    public void a() {
        this.f = new String[this.b.ae];
        this.g = new boolean[this.b.ae];
        this.h = new boolean[this.b.ae];
        if (this.p == null) {
            this.p = this.b();
        }
        for (int i = 0; i < this.b.ae; ++i) {
            if (this.b.ad[i] != null) {
                if (this.b.ad[i].endsWith(zkmToString("#uF\u001d"))) {
                    this.g[i] = true;
                }
                this.f[i] = this.b.ad[i];
                if (!this.g[i] && !this.f[i].endsWith(zkmToString("##\u0018")) && !this.f[i].endsWith(zkmToString("#\"\u001a"))) {
                    this.f[i] = this.f[i].concat("." + this.m);
                    this.h[i] = true;
                }
                int index;
                while ((index = this.f[i].indexOf(" ")) >= 0) {
                    this.f[i] = this.f[i].substring(0, index) + zkmToString("(#\u0018") + this.f[i].substring(index + 1, this.f[i].length());
                }
                this.k = i;
                if (this.j == -1) {
                    this.j = i;
                    if (this.b.ai && !this.g[i] && !this.f[i].endsWith("." + this.m)) {
                        this.f[i] = this.f[i].substring(0, this.f[i].length() - 2) + this.m;
                    }
                }
            }
        }
    }
    
    public String b() {
        final Long n = new Long(new Date().getTime());
        if (this.b.z.getHost().length() > 0) {
            return "?" + n.toString();
        }
        return "";
    }
    
    public c(final audioclipstream b, final String s) {
        super(s);
        this.a = false;
        this.c = null;
        this.d = 0;
        this.e = 0;
        this.i = true;
        this.j = -1;
        this.k = -2;
        this.l = null;
        this.m = zkmToString("?!");
        this.n = 0L;
        this.o = 0;
        this.p = null;
        this.q = null;
        this.r = false;
        this.s = false;
        this.t = null;
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.x = null;
        this.y = null;
        this.z = "";
        this.aa = false;
        this.b = b;
        this.a();
        try {
            this.z = System.getProperty(zkmToString("gp^\u00056{tZ\u0017qb\u007f"));
            if (this.z.compareTo(zkmToString("<?\u001b")) >= 0) {
                Class.forName(zkmToString("gp^\u0005`#bG\u0011vi?[\u0005u}}M\u00006LdL\rwK~Z\tyy"));
                this.aa = true;
            }
        }
        catch (Exception ex) {}
    }
    
    public void show() {
    }
    
    public void dispose() {
        if (this.c != null) {
            this.c.stop();
            this.c = null;
        }
        if (this.t != null) {
            this.t.k();
            this.t = null;
        }
        if (this.q != null) {
            this.q.stop();
            this.q = null;
        }
        this.a = true;
        super.dispose();
    }
    
    public void a(final int n) {
        if (this.q != null && this.t != null) {
            this.t.k();
            if (n < 0) {
                this.u -= 2;
                if (this.u < this.j - 1) {
                    this.u = this.k - 1;
                }
            }
        }
    }
    
    public void b(final int v) {
        if (this.q != null && this.t != null) {
            this.v = v;
            this.t.q = 1;
            this.t.k();
            --this.u;
        }
    }
    
    public void run() {
        if (this.d == 1) {
            this.d = 0;
            this.f();
            return;
        }
        if (this.j == -1) {
            j.a(zkmToString("C~\b\u0005mixGD{axXDk}tK\r~dtLDqc1\\\f}-Y|)T-rG\u0000}"));
            this.e();
        }
        System.gc();
        do {
            try {
                this.u = this.j;
                while (this.u <= this.k) {
                    if (this.b.ad[this.u] != null) {
                        if (this.l != null && !this.g[this.u] && (this.b.ai || this.h[this.u]) && !this.f[this.u].endsWith("." + this.l)) {
                            this.f[this.u] = this.f[this.u].substring(0, this.f[this.u].lastIndexOf(".") + 1) + this.l;
                        }
                        boolean b = false;
                        this.x = this.f[this.u];
                        if (this.x.endsWith(zkmToString("ldL\rw#uF\u001d")) || this.b.v == -1) {
                            b = true;
                        }
                        if (b || !this.b.aj) {
                            this.x += this.b();
                        }
                        if (this.l == null && !this.g[this.u] && (this.b.ai || this.h[this.u])) {
                            this.x = this.f[this.u] + this.p;
                        }
                        if (this.b.a4 != null) {
                            this.b.b(this.b.a4);
                        }
                        this.b.bl = 0;
                        (this.t = new e(this.b.z, this.b.aa, this.b.bn, this.x, this.b.am, this.b.af, true, false, this.b.ae <= 1 && this.v <= 0 && this.b.ah, this.b.ai)).a(this.g[this.u], this.h[this.u], this.l);
                        this.t.a(this.v);
                        this.t.a = this.z.startsWith(zkmToString("<?\u001b"));
                        this.t.b = this.aa;
                        this.w = this.v;
                        this.v = 0;
                        this.d = 1;
                        (this.c = new Thread(this, zkmToString("YxE\u0001j"))).start();
                        while (this.i && this.t.q == 0) {
                            Thread.sleep(250L);
                        }
                        this.b.a7 = false;
                        String string = null;
                        int n = this.w * 1000;
                        if (this.t.q == 0) {
                            this.t.i();
                            this.y = null;
                            while (this.t.g().compareTo(zkmToString("duD\u0001")) != 0) {
                                if (this.l == null) {
                                    this.l = this.t.a();
                                }
                                if (this.y == null && this.l != null && this.t.f() > 0) {
                                    this.b.a7 = true;
                                    this.y = new URL(this.b.z, this.t.f);
                                    if ((this.b.bf & 0x2) == 0x2) {
                                        this.b.a(zkmToString("~eI\u0016l"), this.y, this.t.c() / 1000, this.w * 1000, this.w * 1000, this.t.f());
                                    }
                                }
                                this.o = this.t.b() / 1000;
                                if (this.t.g().startsWith(zkmToString("odN\u0002}\u007fxF\u0003"))) {
                                    this.a(zkmToString("OdN\u0002}\u007fxF\u00038") + this.b.ad[this.u]);
                                }
                                else if (!this.t.al) {
                                    this.a(this.t.e() / 1000, this.t.f() / 1000, this.o, this.b.ad[this.u]);
                                    if (n < this.t.e()) {
                                        n = this.t.e();
                                    }
                                }
                                if (this.t.l != 1) {
                                    string = zkmToString("D\u007f^\u0005tdu\u0007!`}xZ\u0001|-ZM\u001d8~aM\u0007qkxM\u00008d\u007f\b\u0010ph1`0UA1K\u000b|h1N\u000bj-") + this.b.z;
                                    this.b.ah = false;
                                    break;
                                }
                                Thread.sleep(250L);
                            }
                        }
                        if (n < this.t.e()) {
                            n = this.t.e();
                        }
                        if ((this.b.bf & 0x4) == 0x4 && this.y != null) {
                            this.b.a(zkmToString("~eG\u0014"), this.y, this.t.c() / 1000, this.w * 1000, n, this.t.f());
                        }
                        this.b.a(99999999);
                        this.t.k();
                        this.t = null;
                        if (string != null) {
                            j.a(string);
                        }
                    }
                    ++this.u;
                }
            }
            catch (InterruptedException ex) {
                j.a(zkmToString("D\u007f\\\u0001j\u007fdX\u0010}i1m\u001c{ha\\\rwc1_\fqat\b\u0016mc\u007fA\n\u007f"));
                ex.printStackTrace();
                this.e();
            }
            catch (NullPointerException ex2) {
                ex2.printStackTrace();
                this.e();
            }
            catch (MalformedURLException ex3) {
                ex3.printStackTrace();
                this.e();
            }
        } while (this.b.ah && this.j <= this.k);
        this.e();
    }
    
    public void c() {
        this.i = false;
        if (this.q != null && !this.q.isAlive()) {
            this.q = null;
            if (this.t != null) {
                this.t.k();
                this.t = null;
            }
        }
        if (this.q == null) {
            (this.q = new Thread(this, zkmToString("ItK\u000b|hc"))).start();
        }
        if (this.t != null) {
            this.t.i();
        }
        this.a(zkmToString(" <\u0005I5 <\u0005I5-AD\u0005ad\u007fOD5 <\u0005I5 <\u0005I"));
    }
    
    public void d() {
        this.i = true;
        if (this.q != null && this.q.isAlive()) {
            if (this.t != null) {
                this.t.j();
            }
            this.a(zkmToString(" <\u0005I5 <\u0005I5-AI\u0011khu\bI5 <\u0005I5 <\u0005I"));
        }
    }
    
    public void e() {
        this.b.m.enable(true);
        this.b.n.enable(true);
        this.b.o.enable(false);
        this.b.p.enable(false);
        if (this.t != null && this.y != null) {
            if ((this.b.bf & 0x4) == 0x4 && this.y != null) {
                this.b.a(zkmToString("~eG\u0014"), this.y, this.t.c() / 1000, this.w * 1000, this.t.e(), this.t.f());
            }
            this.b.a(99999999);
        }
        if (this.b.ab) {
            this.b.m.b(false);
            this.b.m.b();
            this.b.ab = false;
        }
        if (this.c != null) {
            this.c.stop();
            this.c = null;
        }
        this.a(zkmToString("~eG\u0014hhu"));
        if (this.q != null) {
            if (this.b.a5 != null) {
                this.b.b(this.b.a5);
            }
            this.q.stop();
            this.q = null;
        }
        if (this.t != null) {
            this.t.k();
            this.t = null;
        }
    }
    
    public void a(int n, final int n2, final int n3, final String s) {
        String s2 = c(n);
        if (this.b.ae == 1 && this.b.ah && n2 > 0) {
            n %= n2;
            s2 = c(n);
        }
        else if (n2 > 0 && this.b.v > 0) {
            s2 = s2 + zkmToString("\"1") + c(n2);
        }
        this.b.p.a(n, n2, s2);
        this.b.p.a((float)this.t.n);
        this.a(s2 + zkmToString("!1") + n3 + zkmToString("fsX\u00174-") + s);
    }
    
    public static String c(final int n) {
        String s = "";
        final int n2 = n / 3600;
        final int n3 = n % 60;
        final int n4 = n / 60 % 60;
        if (n2 > 0) {
            s = s + n2 + ":";
        }
        if (n4 < 10) {
            s += "0";
        }
        String s2 = s + n4 + ":";
        if (n3 < 10) {
            s2 += "0";
        }
        return s2 + n3;
    }
    
    public void a(final String s) {
        if (this.b.bg != 0) {
            this.b.showStatus(s);
        }
    }
    
    public boolean action(final Event event, final Object o) {
        return false;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void f() {
        int n = 0;
        long n2 = 2000L;
        long n3 = 2000L;
        try {
            while (this.t != null) {
                if (this.t.t != null) {
                    final int n4 = (int)(this.t.t.o / 64000.0 * this.t.t.a);
                    if (n4 >= this.e && this.e > 0) {
                        break;
                    }
                    if (this.t.g().compareTo(zkmToString("}}I\u001d")) == 0 && n3 % 2000L == 0L && n4 == n) {
                        this.a(zkmToString("Ct\\\u0013w\u007fz\b\u0010jl\u007f[\rld~FD{b\u007fO\u0001kytLJ8ZpA\u0010qcv\u0006J6"));
                    }
                    n = n4;
                    if (this.b.a2 > 0.0) {
                        if (this.b.a2 <= this.t.e() / 1000.0) {
                            this.b.a2 = 0.0;
                            this.b.postEvent(new Event(this.b, 1001, zkmToString("B\u007f\u00050q`tZ")));
                        }
                        n2 = 50L;
                        n3 += n2;
                    }
                    else {
                        n2 = 2000L;
                        n3 = 2000L;
                    }
                }
                Thread.sleep(n2);
            }
        }
        catch (NullPointerException ex) {
            System.out.println(zkmToString("N}A\u00148kxF\rketLE"));
        }
        catch (InterruptedException ex2) {}
    }
    
    private static String zkmToString(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '\r';
                    break;
                }
                case 1: {
                    c2 = '\u0011';
                    break;
                }
                case 2: {
                    c2 = '(';
                    break;
                }
                case 3: {
                    c2 = 'd';
                    break;
                }
                default: {
                    c2 = '\u0018';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
