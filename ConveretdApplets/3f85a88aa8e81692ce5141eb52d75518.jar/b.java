import java.awt.Event;
import java.net.MalformedURLException;
import java.util.Date;
import java.net.URL;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class b extends Frame implements Runnable
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
    public d t;
    public int u;
    public int v;
    public int w;
    public String x;
    public URL y;
    
    public void a() {
        this.f = new String[this.b.t];
        this.g = new boolean[this.b.t];
        this.h = new boolean[this.b.t];
        if (this.p == null) {
            this.p = this.b();
        }
        for (int i = 0; i < this.b.t; ++i) {
            if (this.b.s[i] != null) {
                if (this.b.s[i].endsWith(zkmToString("\u001fEH;"))) {
                    this.g[i] = true;
                }
                this.f[i] = this.b.s[i];
                if (!this.g[i] && !this.f[i].endsWith(zkmToString("\u001f\u0013\u0016")) && !this.f[i].endsWith(zkmToString("\u001f\u0012\u0014"))) {
                    this.f[i] = this.f[i].concat("." + this.m);
                    this.h[i] = true;
                }
                int index;
                while ((index = this.f[i].indexOf(" ")) >= 0) {
                    this.f[i] = this.f[i].substring(0, index) + zkmToString("\u0014\u0013\u0016") + this.f[i].substring(index + 1, this.f[i].length());
                }
                this.k = i;
                if (this.j == -1) {
                    this.j = i;
                    if (this.b.x && !this.g[i] && !this.f[i].endsWith("." + this.m)) {
                        this.f[i] = this.f[i].substring(0, this.f[i].length() - 2) + this.m;
                    }
                }
            }
        }
    }
    
    public String b() {
        final Long n = new Long(new Date().getTime());
        if (this.b.getCodeBase().getHost().length() > 0) {
            return "?" + n.toString();
        }
        return "";
    }
    
    public b(final audioclipstream b, final String s) {
        super(s);
        this.a = false;
        this.c = null;
        this.d = 0;
        this.e = 0;
        this.i = true;
        this.j = -1;
        this.k = -2;
        this.l = null;
        this.m = zkmToString("\u0003\u0011");
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
        this.b = b;
        this.a();
    }
    
    public void show() {
    }
    
    public void dispose() {
        if (this.c != null) {
            this.c.stop();
            this.c = null;
        }
        if (this.t != null) {
            this.t.j();
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
            this.t.j();
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
            this.t.m = 1;
            this.t.j();
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
            i.a(zkmToString("\u007fN\u0006#\u0012UHIb\u0004]HVb\u0014ADE+\u0001XDBb\u000e_\u0001R*\u0002\u0011ir\u000f+\u0011BI&\u0002"));
            this.e();
        }
        System.gc();
        do {
            try {
                this.u = this.j;
                while (this.u <= this.k) {
                    if (this.b.s[this.u] != null) {
                        if (this.l != null && !this.g[this.u] && (this.b.x || this.h[this.u]) && !this.f[this.u].endsWith("." + this.l)) {
                            this.f[this.u] = this.f[this.u].substring(0, this.f[this.u].lastIndexOf(".") + 1) + this.l;
                        }
                        boolean b = false;
                        this.x = this.f[this.u];
                        if (this.x.endsWith(zkmToString("PTB+\b\u001fEH;")) || this.b.m == -1) {
                            b = true;
                        }
                        if (b || !this.b.y) {
                            this.x += this.b();
                        }
                        if (this.l == null && !this.g[this.u] && (this.b.x || this.h[this.u])) {
                            this.x = this.f[this.u] + this.p;
                        }
                        if (this.b.as != null) {
                            this.b.b(this.b.as);
                        }
                        this.b.a9 = 0;
                        (this.t = new d(this.b.getCodeBase(), this.b.getDocumentBase(), this.b.bb, this.x, this.b.ab, this.b.u, true, false, this.b.t <= 1 && this.v <= 0 && this.b.w, this.b.x)).a(this.g[this.u], this.h[this.u], this.l);
                        this.t.a(this.v);
                        this.w = this.v;
                        this.v = 0;
                        this.d = 1;
                        (this.c = new Thread(this, zkmToString("eHK'\u0015"))).start();
                        while (this.i && this.t.m == 0) {
                            Thread.sleep(250L);
                        }
                        this.b.av = false;
                        String string = null;
                        int n = this.w * 1000;
                        if (this.t.m == 0) {
                            this.t.h();
                            this.y = null;
                            while (this.t.f().compareTo(zkmToString("XEJ'")) != 0) {
                                if (this.l == null) {
                                    this.l = this.t.a();
                                }
                                if (this.y == null && this.l != null) {
                                    this.b.av = true;
                                    this.y = new URL(this.b.getCodeBase(), this.t.c);
                                    if ((this.b.a3 & 0x2) == 0x2) {
                                        this.b.a(zkmToString("BUG0\u0013TE"), this.y, this.t.c() / 1000, this.w * 1000, this.w * 1000);
                                    }
                                }
                                this.o = this.t.b() / 1000;
                                this.a(this.t.d() / 1000, this.t.e() / 1000, this.o, this.b.s[this.u]);
                                if (n < this.t.d()) {
                                    n = this.t.d();
                                }
                                if (this.t.i != 1) {
                                    string = zkmToString("xOP#\u000bXE\t\u0007\u001fAHT'\u0003\u0011jC;GBQC!\u000eWHC&GXO\u00066\u000fT\u0001n\u0016*}\u0001E-\u0003T\u0001@-\u0015\u0011") + this.b.getCodeBase();
                                    this.b.w = false;
                                    break;
                                }
                                Thread.sleep(250L);
                            }
                        }
                        if (n < this.t.d()) {
                            n = this.t.d();
                        }
                        if ((this.b.a3 & 0x4) == 0x4 && this.y != null) {
                            this.b.a(zkmToString("BUI2\u0017TE"), this.y, this.t.c() / 1000, this.w * 1000, n);
                        }
                        this.b.a(99999999);
                        this.t.j();
                        this.t = null;
                        if (string != null) {
                            i.a(string);
                        }
                    }
                    ++this.u;
                }
            }
            catch (InterruptedException ex) {
                i.a(zkmToString("xOR'\u0015CTV6\u0002U\u0001c:\u0004TQR+\b_\u0001Q*\u000e]D\u00060\u0012_OO,\u0000"));
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
        } while (this.b.w && this.j <= this.k);
        this.e();
    }
    
    public void c() {
        this.i = false;
        if (this.q != null && !this.q.isAlive()) {
            this.q = null;
            if (this.t != null) {
                this.t.j();
                this.t = null;
            }
        }
        if (this.q == null) {
            (this.q = new Thread(this, zkmToString("uDE-\u0003TS"))).start();
        }
        if (this.t != null) {
            this.t.h();
        }
        this.a(zkmToString("\u001c\f\u000boJ\u001c\f\u000boJ\u0011qJ#\u001eXOAbJ\u001c\f\u000boJ\u001c\f\u000bo"));
    }
    
    public void d() {
        this.i = true;
        if (this.q != null && this.q.isAlive()) {
            if (this.t != null) {
                this.t.i();
            }
            this.a(zkmToString("\u001c\f\u000boJ\u001c\f\u000boJ\u0011qG7\u0014TE\u0006oJ\u001c\f\u000boJ\u001c\f\u000bo"));
        }
    }
    
    public void e() {
        this.b.d.enable(true);
        this.b.e.enable(true);
        this.b.f.enable(false);
        this.b.g.enable(false);
        if (this.t != null && this.y != null) {
            if ((this.b.a3 & 0x4) == 0x4 && this.y != null) {
                this.b.a(zkmToString("BUI2\u0017TE"), this.y, this.t.c() / 1000, this.w * 1000, this.t.d());
            }
            this.b.a(99999999);
        }
        if (this.b.q) {
            this.b.d.b(false);
            this.b.d.b();
            this.b.q = false;
        }
        if (this.c != null) {
            this.c.stop();
            this.c = null;
        }
        if (this.t != null) {
            this.t.j();
            this.t = null;
        }
        this.a(zkmToString("BUI2\u0017TE"));
        if (this.q != null) {
            if (this.b.at != null) {
                this.b.b(this.b.at);
            }
            this.q.stop();
            this.q = null;
        }
    }
    
    public void a(int n, final int n2, final int n3, final String s) {
        String s2 = c(n);
        if (this.b.t == 1 && this.b.w && n2 > 0) {
            n %= n2;
            s2 = c(n);
        }
        else if (n2 > 0 && this.b.m > 0) {
            s2 = s2 + zkmToString("\u001e\u0001") + c(n2);
        }
        this.b.g.a(n, n2, s2);
        this.b.g.a((float)this.t.k);
        this.a(s2 + zkmToString("\u001d\u0001") + n3 + zkmToString("ZCV1K\u0011") + s);
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
        if (this.b.a4 != 0) {
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
                if (this.t.p != null) {
                    final int n4 = (int)(this.t.p.n / 64000.0 * this.t.p.a);
                    if (n4 >= this.e && this.e > 0) {
                        break;
                    }
                    if (this.t.f().compareTo(zkmToString("AMG;")) == 0 && n3 % 2000L == 0L && n4 == n) {
                        this.a(zkmToString("\u007fDR5\bCJ\u00066\u0015POU+\u0013XNHb\u0004^OA'\u0014EDBlGf@O6\u000e_F\blI"));
                    }
                    n = n4;
                    if (this.b.aq > 0.0) {
                        if (this.b.aq <= this.t.d() / 1000.0) {
                            this.b.aq = 0.0;
                            this.b.postEvent(new Event(this.b, 1001, zkmToString("~O\u000b\u0016\u000e\\DT")));
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
            System.out.println(zkmToString("rMO2GWHH+\u0014YDBc"));
        }
        catch (InterruptedException ex2) {
            i.a(zkmToString("xOR'\u0015CTV6\u0002U\u0001c:\u0004TQR+\b_\u0001Q*\u000e]D\u00066\u000e\\HH%"));
        }
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
                    c2 = '1';
                    break;
                }
                case 1: {
                    c2 = '!';
                    break;
                }
                case 2: {
                    c2 = '&';
                    break;
                }
                case 3: {
                    c2 = 'B';
                    break;
                }
                default: {
                    c2 = 'g';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
