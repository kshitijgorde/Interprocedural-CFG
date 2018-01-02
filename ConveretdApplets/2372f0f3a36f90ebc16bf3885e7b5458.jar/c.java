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
    public String[] g;
    public boolean h;
    public int i;
    public int j;
    public String k;
    public String l;
    public long m;
    public int n;
    public String o;
    public Thread p;
    public boolean q;
    public boolean r;
    public e s;
    public int t;
    public int u;
    public int v;
    public int w;
    public String x;
    public URL y;
    public String z;
    public boolean aa;
    
    public void a() {
        this.f = new String[this.b.al];
        this.g = new String[this.b.al];
        if (this.o == null) {
            this.o = this.b();
        }
        for (int i = 0; i < this.b.al; ++i) {
            if (this.b.aj[i] != null) {
                this.f[i] = this.b.aj[i];
                this.g[i] = this.b.ak[i];
                int index;
                while ((index = this.f[i].indexOf(" ")) >= 0) {
                    this.f[i] = this.f[i].substring(0, index) + zkmToString("$(\u001a") + this.f[i].substring(index + 1, this.f[i].length());
                }
                this.j = i;
                if (this.i == -1) {
                    this.i = i;
                }
            }
        }
    }
    
    public String b() {
        final Long n = new Long(new Date().getTime());
        if (this.b.ae.getHost().length() > 0) {
            return zkmToString(">nYn") + n.toString();
        }
        return "";
    }
    
    public c(final audioclipstream b, final String s) {
        super(s);
        this.a = false;
        this.c = null;
        this.d = 0;
        this.e = 0;
        this.h = true;
        this.i = -1;
        this.j = -2;
        this.k = null;
        this.l = zkmToString("3*");
        this.m = 0L;
        this.n = 0;
        this.o = null;
        this.p = null;
        this.q = false;
        this.r = false;
        this.s = null;
        this.t = 0;
        this.u = -1;
        this.v = 0;
        this.w = 0;
        this.x = null;
        this.y = null;
        this.z = "";
        this.aa = false;
        this.b = b;
        this.a();
        try {
            this.z = System.getProperty(zkmToString("k{\\2Yw\u007fX \u001ent"));
            if (this.z.compareTo(zkmToString("04\u0019")) >= 0) {
                Class.forName(zkmToString("k{\\2\u000f/iE&\u0019e4Y2\u001aqvO7Y@oN:\u0018GuX>\u0016u"));
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
        if (this.s != null) {
            this.s.k();
            this.s = null;
        }
        if (this.p != null) {
            this.p.stop();
            this.p = null;
        }
        this.a = true;
        super.dispose();
    }
    
    public void a(final int n) {
        if (this.p != null && this.s != null) {
            this.s.k();
            if (n < 0) {
                this.t -= 2;
                if (this.t < this.i - 1) {
                    this.t = this.j - 1;
                }
            }
        }
    }
    
    public void b(final int n) {
        if (this.p != null && this.s != null) {
            if (n <= this.j && n >= this.i) {
                this.u = n - 1;
            }
            this.s.k();
        }
    }
    
    public void c(final int v) {
        if (this.p != null && this.s != null) {
            this.v = v;
            this.s.r = 1;
            this.s.k();
            --this.t;
        }
    }
    
    public void run() {
        if (this.d == 1) {
            this.d = 0;
            this.f();
            return;
        }
        if (this.i == -1) {
            k.a(zkmToString("Ou\n2\u0002esEs\u0014msZs\u0004q\u007fI:\u0011h\u007fNs\u001eo:^;\u0012!R~\u001e;!yE7\u0012"));
            this.e();
        }
        System.gc();
        do {
            try {
                this.t = this.i;
                while (this.t <= this.j) {
                    if (this.u >= 0) {
                        this.t = this.u;
                        this.u = -1;
                    }
                    if (this.b.aj[this.t] != null) {
                        if (this.k == null || this.b.ap) {}
                        boolean b = false;
                        this.x = this.f[this.t];
                        if (this.x.endsWith(zkmToString("`oN:\u0018/~D*")) || this.b.z == -1) {
                            b = true;
                        }
                        if (b || !this.b.ar) {
                            this.x += this.b();
                        }
                        if (this.k == null && this.b.ap) {
                            this.x = this.f[this.t] + this.o;
                        }
                        if (this.b.bc != null) {
                            this.b.b(this.b.bc);
                        }
                        this.b.bt = 0;
                        (this.s = new e(this.b.ae, this.b.af, this.b.bv, this.x, this.b.au, this.b.am, true, false, this.b.al <= 1 && this.v <= 0 && this.b.ao, this.b.ap, this.g[this.t])).a(this.b.e, this.k);
                        this.s.a(this.v);
                        this.s.a = this.z.startsWith(zkmToString("04\u0019"));
                        this.s.b = this.aa;
                        this.w = this.v;
                        this.v = 0;
                        this.d = 1;
                        (this.c = new Thread(this, zkmToString("UsG6\u0005"))).start();
                        while (this.h && this.s.r == 0) {
                            Thread.sleep(250L);
                        }
                        this.b.bf = false;
                        String string = null;
                        int n = this.w * 1000;
                        if (this.s.r == 0) {
                            this.s.i();
                            this.y = null;
                            while (this.s.g().compareTo(zkmToString("h~F6")) != 0) {
                                if (this.k == null) {
                                    this.k = this.s.a();
                                }
                                if (this.y == null && this.k != null && this.s.f() > 0) {
                                    this.b.bf = true;
                                    this.y = new URL(this.b.ae, this.s.f);
                                    if ((this.b.bn & 0x2) == 0x2) {
                                        this.b.a(zkmToString("rnK!\u0003"), this.y, this.s.c() / 1000, this.w * 1000, this.w * 1000, this.s.f());
                                    }
                                }
                                this.n = this.s.b() / 1000;
                                if (this.s.g().startsWith(zkmToString("coL5\u0012ssD4"))) {
                                    this.a(zkmToString("CoL5\u0012ssD4W") + this.b.aj[this.t]);
                                }
                                else if (!this.s.al) {
                                    this.a(this.s.e() / 1000, this.s.f() / 1000, this.n, this.b.aj[this.t]);
                                    if (n < this.s.e()) {
                                        n = this.s.e();
                                    }
                                }
                                if (this.s.m != 1) {
                                    string = zkmToString("Ht\\2\u001bh~\u0005\u0016\u000fqsX6\u0013!QO*WrjO0\u001egsO7Wht\n'\u001fd:b\u0007:M:I<\u0013d:L<\u0005!") + this.b.ae;
                                    this.b.ao = false;
                                    break;
                                }
                                Thread.sleep(250L);
                            }
                        }
                        if (n < this.s.e()) {
                            n = this.s.e();
                        }
                        if ((this.b.bn & 0x4) == 0x4 && this.y != null) {
                            this.b.a(zkmToString("rnE#"), this.y, this.s.c() / 1000, this.w * 1000, n, this.s.f());
                        }
                        this.b.a(99999999);
                        this.s.k();
                        this.s = null;
                        if (string != null) {
                            k.a(string);
                        }
                    }
                    ++this.t;
                }
            }
            catch (InterruptedException ex) {
                k.a(zkmToString("Ht^6\u0005soZ'\u0012e:o+\u0014dj^:\u0018o:];\u001em\u007f\n!\u0002otC=\u0010"));
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
            this.b.u.f = false;
            this.b.u.repaint();
        } while (this.b.ao && this.i <= this.j);
        this.e();
    }
    
    public void c() {
        this.h = false;
        if (this.p != null && !this.p.isAlive()) {
            this.p = null;
            if (this.s != null) {
                this.s.k();
                this.s = null;
            }
        }
        if (this.p == null) {
            (this.p = new Thread(this, zkmToString("E\u007fI<\u0013dh"))).start();
        }
        if (this.s != null) {
            this.s.i();
        }
        this.a(zkmToString(",7\u0007~Z,7\u0007~Z!JF2\u000ehtMsZ,7\u0007~Z,7\u0007~"));
    }
    
    public void d() {
        this.h = true;
        if (this.p != null && this.p.isAlive()) {
            if (this.s != null) {
                this.s.j();
            }
            this.a(zkmToString(",7\u0007~Z,7\u0007~Z!JK&\u0004d~\n~Z,7\u0007~Z,7\u0007~"));
        }
        this.b.u.f = false;
        this.b.u.repaint();
    }
    
    public void e() {
        this.b.q.enable(true);
        this.b.r.enable(true);
        this.b.s.enable(false);
        this.b.t.enable(false);
        if (this.s != null && this.y != null) {
            if ((this.b.bn & 0x4) == 0x4 && this.y != null) {
                this.b.a(zkmToString("rnE#"), this.y, this.s.c() / 1000, this.w * 1000, this.s.e(), this.s.f());
            }
            this.b.a(99999999);
        }
        if (this.b.ag) {
            this.b.q.b(false);
            this.b.q.b();
            this.b.ag = false;
        }
        if (this.c != null) {
            this.c.stop();
            this.c = null;
        }
        this.a(zkmToString("rnE#\u0007d~"));
        if (this.p != null) {
            if (this.b.bd != null) {
                this.b.b(this.b.bd);
            }
            this.p.stop();
            this.p = null;
        }
        if (this.s != null) {
            this.s.k();
            this.s = null;
        }
        this.b.u.f = false;
        this.b.u.repaint();
    }
    
    public void a(int n, final int n2, final int n3, String s) {
        String s2 = d(n);
        if (this.b.al == 1 && this.b.ao && n2 > 0) {
            n %= n2;
            s2 = d(n);
        }
        else if (n2 > 0 && this.b.z > 0) {
            s2 = s2 + zkmToString(".:") + d(n2);
        }
        this.b.t.a(n, n2, s2);
        this.b.t.a((float)this.s.o);
        if ((this.b.bo & 0x1) == 0x1) {
            this.b.u.f = true;
            this.b.u.repaint();
        }
        final int lastIndex = s.lastIndexOf("/");
        final int lastIndex2 = s.lastIndexOf("_");
        if (lastIndex >= 0 && lastIndex2 >= 0) {
            s = s.substring(lastIndex + 1, lastIndex2);
        }
        if (lastIndex >= 0 && lastIndex2 < 0) {
            s = s.substring(lastIndex + 1);
        }
        if (lastIndex < 0 && lastIndex2 >= 0) {
            s = s.substring(0, lastIndex2);
        }
        this.a(s2 + zkmToString("-:") + n3 + zkmToString("jxZ [!") + s);
    }
    
    public static String d(final int n) {
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
        if ((this.b.bo & 0x2) == 0x2) {
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
            while (this.s != null) {
                if (this.s.u != null) {
                    final int n4 = (int)(this.s.u.p / 64000.0 * this.s.u.a);
                    if (n4 >= this.e && this.e > 0) {
                        break;
                    }
                    if (this.s.g().compareTo(zkmToString("qvK*")) == 0 && n3 % 2000L == 0L && n4 == n) {
                        this.a(zkmToString("O\u007f^$\u0018sq\n'\u0005`tY:\u0003huDs\u0014ntM6\u0004u\u007fN}WV{C'\u001eo}\u0004}Y"));
                    }
                    n = n4;
                    if (this.b.ba > 0.0) {
                        if (this.b.ba <= this.s.e() / 1000.0) {
                            this.b.ba = 0.0;
                            this.b.postEvent(new Event(this.b, 1001, zkmToString("Nt\u0007\u0007\u001el\u007fX")));
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
            System.out.println(zkmToString("BvC#WgsD:\u0004i\u007fNr"));
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
                    c2 = '\u0001';
                    break;
                }
                case 1: {
                    c2 = '\u001a';
                    break;
                }
                case 2: {
                    c2 = '*';
                    break;
                }
                case 3: {
                    c2 = 'S';
                    break;
                }
                default: {
                    c2 = 'w';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
