import netscape.javascript.JSObject;
import java.util.StringTokenizer;
import java.io.OutputStream;
import java.net.URLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.util.Date;
import java.awt.Event;
import java.io.InputStream;
import java.applet.Applet;
import java.awt.Graphics;
import java.util.Vector;
import java.net.URL;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class c extends Frame implements Runnable, b
{
    public boolean a;
    public boolean b;
    public boolean c;
    public Thread d;
    public boolean e;
    public d f;
    public int[] g;
    public String h;
    public double i;
    public int j;
    public j k;
    public boolean l;
    public boolean m;
    public String n;
    public String o;
    public URL p;
    public URL q;
    public Vector r;
    public String s;
    public int t;
    public long u;
    public int v;
    public volatile int w;
    public volatile int x;
    public volatile int y;
    public volatile int z;
    public volatile int aa;
    public int ab;
    public int ac;
    public volatile boolean ad;
    public volatile int ae;
    public volatile int af;
    public Graphics ag;
    public boolean ah;
    public boolean ai;
    public boolean aj;
    public boolean ak;
    public Applet al;
    public boolean am;
    public byte[] an;
    public int ao;
    public int ap;
    public int[] aq;
    public boolean[] ar;
    public boolean as;
    public boolean at;
    public int au;
    public int av;
    public int aw;
    public int ax;
    public double ay;
    public g az;
    public boolean a0;
    public InputStream a1;
    public f a2;
    public final int a3 = 8000;
    public byte[] a4;
    public byte[] a5;
    public int a6;
    public int a7;
    public int a8;
    public int a9;
    public int ba;
    public int bb;
    public boolean bc;
    
    public c(final Applet al, final URL p21, final URL q, final Vector r, final String n, final String s, final Graphics ag, final int ae, final int af, final int x, final int y, final Object o, final boolean ak, final int n2, final int ac, final int a6, final boolean ah, final boolean ai, final boolean aj, final int j, final String o2) {
        this.a = false;
        this.b = false;
        this.c = false;
        this.d = null;
        this.e = false;
        this.f = null;
        this.g = new int[16];
        this.h = "";
        this.i = 0.0;
        this.j = 1;
        this.l = true;
        this.m = false;
        this.t = 0;
        this.u = 0L;
        this.v = 1;
        this.w = 0;
        this.ab = 10;
        this.ad = false;
        this.an = null;
        this.as = false;
        this.at = true;
        this.au = 0;
        this.av = 0;
        this.aw = 0;
        this.ax = 0;
        this.ay = 0.0;
        this.az = null;
        this.a0 = false;
        this.a2 = null;
        this.a4 = new byte[256];
        this.bc = false;
        this.al = al;
        this.o = o2;
        this.j = j;
        this.ag = ag;
        this.ae = ae;
        this.af = af;
        this.x = x;
        this.y = y;
        this.k = (j)o;
        this.aj = aj;
        this.ak = ak;
        this.ab = ((n2 < 2) ? 2 : n2);
        this.ac = ac;
        this.ah = ah;
        this.ai = ai;
        this.a7 = a6 * 8000;
        this.a5 = new byte[this.a7];
        this.a6 = a6;
        this.n = n;
        this.p = p21;
        this.q = q;
        this.r = r;
        this.s = s;
        int i = 0;
        for (int n3 = 0, k = 2; k <= 256; k <<= 1, ++n3) {
            while (i < k) {
                this.a4[i] = (byte)n3;
                ++i;
            }
        }
    }
    
    public void a(final boolean aj) {
        this.aj = aj;
        if (this.a2 != null) {
            this.a2.a(this.aj);
        }
    }
    
    public long a() {
        long n = 0L;
        if (this.a2 != null && this.a2.d != Long.MAX_VALUE) {
            double n2 = 0.9985022466300549;
            double n3 = 0.9985022466300549;
            if (this.c) {
                n3 = (n2 = 1.0);
            }
            final long n4 = (long)(n2 * this.a2.a) / 8L + ((this.a2.a > 0) ? 150 : 0);
            final long n5 = (long)(n2 * this.a2.c) / 8L + (long)(n3 * (System.currentTimeMillis() - this.a2.d));
            if (!this.a2.as || this.a0) {
                n = n5;
            }
            else if (this.a) {
                n = this.f.f() / 8L + 150L;
            }
            else if (this.c) {
                if (n5 < n4) {
                    n = n5;
                }
                else {
                    n = n4;
                }
            }
            else {
                n = n4;
            }
        }
        return n;
    }
    
    public int b() {
        final int av = this.au + (int)this.a();
        if (av > this.av) {
            this.av = av;
        }
        return av;
    }
    
    public int c() {
        if (this.a2 != null) {
            return this.a2.ar;
        }
        return -1;
    }
    
    public String d() {
        if (this.d == null || this.a2 == null) {
            return zkmToString("W|\u001d:");
        }
        if (!this.bc) {
            return zkmToString("Nt\u0010&");
        }
        if (this.a2.a != 0) {
            return zkmToString("Ny\u0004,7");
        }
        if (this.v == 1) {
            return zkmToString("L}\u0010;+");
        }
        return zkmToString("Wv\u0007>>W|Q47G");
    }
    
    public void e() {
        if (this.d == null) {
            this.bc = true;
            (this.d = new Thread(this, zkmToString("h[\"\t;Z}\u001e"))).start();
        }
        if (this.az == null) {
            (this.az = new g(this, zkmToString("hq\u0015:="), this.aw, this.ax)).a();
        }
    }
    
    public void f() {
        this.e();
        this.bc = false;
    }
    
    public void b(final boolean b) {
        if (this.bc != b) {
            this.g();
        }
    }
    
    public void g() {
        this.bc = !this.bc;
        if (this.f != null) {
            this.f.a(this.a2, this.bc);
            this.a2.b();
        }
        if (!this.bc) {
            if (this.a0) {
                this.a2.d = System.currentTimeMillis();
            }
        }
        else if (this.a0) {
            final f a2 = this.a2;
            a2.c += (int)(System.currentTimeMillis() - this.a2.d) * 8;
            this.a2.d = Long.MAX_VALUE;
        }
    }
    
    private void k() {
        if (this.az != null) {
            this.az.b();
            this.az = null;
        }
        if (this.a2 != null) {
            if (!this.a0 && this.f != null) {
                this.f.b(this.a2);
            }
            try {
                this.w = 1;
                Thread.currentThread();
                Thread.sleep(100L);
                this.a2.close();
                this.a2 = null;
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
            if (!this.as) {
                this.al.action(new Event(this.al, this.am ? 103 : 102, null), null);
            }
        }
    }
    
    public void h() {
        if (this.d != null) {
            this.w = 1;
            this.d.stop();
            this.d = null;
        }
        this.k();
    }
    
    public void a(final String s) {
        if ((this.j & 0x1) == 0x1) {
            h.a(this.al, this.ag, this.ae, this.af + this.y - 16, this.x, 16, s);
        }
        if ((this.j & 0x2) == 0x2) {
            this.al.showStatus(s);
        }
    }
    
    public void run() {
        boolean b = true;
        try {
            final boolean b2 = false;
            this.bb = (b2 ? 1 : 0);
            this.ba = (b2 ? 1 : 0);
            final boolean b3 = false;
            this.a9 = (b3 ? 1 : 0);
            this.a8 = (b3 ? 1 : 0);
            long n = 0L;
            if (this.a(new Date().getTime(), this.p, this.s) != 1) {
                this.v = 1;
                n = 20000L;
            }
            do {
                this.am = false;
                this.a(zkmToString("\u001e820<P}\u0012+;P\u007f_q|"));
                try {
                    Date date = new Date();
                    final URL url = new URL(this.p, this.n);
                    if (this.ah && !this.m) {
                        int n2 = -1;
                        final byte[] array = new byte[2048];
                        int i = 0;
                        int n3 = -1;
                        final URLConnection openConnection = url.openConnection();
                        openConnection.setUseCaches(this.l);
                        this.t = openConnection.getContentLength();
                        this.a1 = openConnection.getInputStream();
                        this.a2 = new f(this);
                        this.a(zkmToString("\u001e8=03Zq\u001f8|\u00106_"));
                        do {
                            final int n4 = (int)(n2 / this.t * 100.0f);
                            n2 += i;
                            if (n4 != n3) {
                                final String string = zkmToString("rw\u0010;;P\u007fK\u007f") + n4 + "%";
                                if (this.k != null) {
                                    this.k.a(n2 / this.t);
                                    this.k.t = string;
                                }
                                this.a(zkmToString("\u001e8") + string + zkmToString("\u001e0") + n2 + ")");
                                n3 = n4;
                            }
                            i = this.a2.b(array);
                        } while (i != -1);
                        this.a(zkmToString("\u001e8=03Zq\u001f8r]w\u001c/>[l\u0014;|"));
                        this.m = true;
                        this.a2.close();
                        this.a2 = null;
                        this.l = true;
                        date = new Date();
                    }
                    if (this.as && this.an != null) {
                        if (this.au >= 0 && this.au < this.ao * this.ap) {
                            final String host = url.getHost();
                            int port = url.getPort();
                            if (port == -1) {
                                port = 80;
                            }
                            if (host.length() > 0 && !this.ah) {
                                if (this.a && !this.b) {
                                    final URLConnection openConnection2 = url.openConnection();
                                    openConnection2.setRequestProperty(zkmToString("ly\u001f87"), zkmToString("\\a\u0005:!\u0003") + this.aq[this.au / this.ap] + "-");
                                    openConnection2.setUseCaches(false);
                                    openConnection2.connect();
                                    this.a1 = openConnection2.getInputStream();
                                    System.out.println(this.a1);
                                }
                                else {
                                    while (!b) {
                                        String s = zkmToString("i}\u0013\u007f\u0013Kl\u00190 Wb\u0010+;Qv");
                                        try {
                                            final String j = this.j();
                                            final int n5 = j.indexOf(zkmToString("}K'(7\\Y\u0004+:\u0003")) + 11;
                                            if (n5 != 10) {
                                                int n6 = j.indexOf(";", n5);
                                                if (n6 == -1) {
                                                    n6 = j.length();
                                                }
                                                this.h = zkmToString("\u007fm\u00057=Lq\u000b>&Ww\u001fer|y\u000261\u001e") + j.substring(n5, n6) + zkmToString("3\u0012");
                                            }
                                        }
                                        catch (Exception ex5) {}
                                        final Socket socket = new Socket(host, port);
                                        final String string2 = zkmToString("y]%\u007f") + url.getFile() + zkmToString("\u001eP%\u000b\u0002\u0011)_n_4") + zkmToString("ly\u001f87\u00048\u0013&&[kL") + this.aq[this.au / this.ap] + zkmToString("\u0013\u0015{") + zkmToString("vw\u0002+h\u001e") + url.getHost() + zkmToString("3\u0012") + this.h + zkmToString("}w\u001f17]l\u00180<\u00048\u00123=M}|U") + zkmToString("3\u0012|U");
                                        this.a1 = socket.getInputStream();
                                        final OutputStream outputStream = socket.getOutputStream();
                                        outputStream.write(string2.getBytes());
                                        outputStream.flush();
                                        final String a = this.a(this.a1);
                                        String zkmToString = zkmToString("vL%\u000f}\u000f6A\u007ff\u000e,");
                                        if (a.indexOf(zkmToString("vL%\u000f}")) == -1) {
                                            b = true;
                                        }
                                        else {
                                            zkmToString = a;
                                        }
                                        while (true) {
                                            final String a2 = this.a(this.a1);
                                            if (a2.length() < 3) {
                                                break;
                                            }
                                            final int index = a2.toLowerCase().indexOf(zkmToString("]w\u001f+7Pl\\37P\u007f\u00057h"));
                                            if (index >= 0) {
                                                this.t = Integer.parseInt(a2.substring(index + 16));
                                            }
                                            final int index2 = a2.toLowerCase().indexOf(zkmToString("Io\u0006r3Kl\u0019:<Jq\u0012>&[\"Q=3Mq\u0012\u007f [y\u001d2o"));
                                            if (index2 < 0) {
                                                continue;
                                            }
                                            s = a2.substring(index2 + 30);
                                        }
                                        if (zkmToString.indexOf(zkmToString("\n(@")) > 0 || zkmToString.indexOf(zkmToString("\n)G")) > 0) {
                                            final m m = new m(new Frame(), s);
                                            if (m.a != null) {
                                                this.h = zkmToString("\u007fm\u00057=Lq\u000b>&Ww\u001fer|y\u000261\u001e") + m.a + zkmToString("3\u0012");
                                                try {
                                                    this.b(zkmToString("}K'(7\\Y\u0004+:\u0003") + m.a + zkmToString("\u00058!>&V%^d"));
                                                }
                                                catch (Exception ex6) {}
                                                socket.close();
                                            }
                                            else {
                                                b = true;
                                            }
                                        }
                                        else {
                                            if (zkmToString.indexOf(zkmToString("\f(G")) > 0) {
                                                break;
                                            }
                                            b = true;
                                        }
                                    }
                                    if (b) {
                                        this.al.action(new Event(this.al, 102, null), null);
                                        break;
                                    }
                                    this.t += this.aq[this.au / this.ap];
                                }
                            }
                            else {
                                final URLConnection openConnection3 = url.openConnection();
                                openConnection3.setUseCaches(this.l);
                                this.t = openConnection3.getContentLength();
                                this.a1 = openConnection3.getInputStream();
                                byte[] array2;
                                int k;
                                for (array2 = new byte[1024], k = 0; k + 1024 < this.aq[this.au / this.ap]; k += this.a1.read(array2)) {}
                                while (k < this.aq[this.au / this.ap]) {
                                    this.a1.read();
                                    ++k;
                                }
                            }
                        }
                        else {
                            final URLConnection openConnection4 = url.openConnection();
                            openConnection4.setUseCaches(this.l);
                            this.t = openConnection4.getContentLength();
                            this.a1 = openConnection4.getInputStream();
                        }
                    }
                    else {
                        final URLConnection openConnection5 = url.openConnection();
                        openConnection5.setUseCaches(this.l);
                        this.t = openConnection5.getContentLength();
                        this.a1 = openConnection5.getInputStream();
                    }
                    this.a2 = new f(this);
                    this.a2.y = this.aj;
                    if (this.as && this.an != null) {
                        this.a2.a(this.an);
                        this.as = false;
                    }
                    if (this.a2.d()) {
                        final int n7 = this.a7 / 2;
                        final int n8 = this.a7 - this.a2.l;
                        int n9 = 0;
                        this.an = this.a2.c();
                        this.a0 = !this.i();
                        this.f = new d(this.a0, 3, this.a);
                        this.a0 = this.f.a;
                        if (this.f.b != null) {
                            this.a5 = new byte[this.a7 * 2];
                        }
                        while (this.a2.g() && this.a2.ad < n8 - this.a2.l) {
                            final String string3 = zkmToString("|m\u001797Lq\u001f8h\u001e") + 100 * this.ba / n8 + "%";
                            if (!this.az.s) {
                                this.a(zkmToString("\u001e8") + string3);
                            }
                            if (this.k != null) {
                                this.k.a(this.ba / n8);
                                this.k.t = string3;
                            }
                            final Date date2 = new Date();
                        }
                        if (this.k != null) {
                            this.k.a(1.0f);
                        }
                        this.a(zkmToString("\u001e8!33Gq\u001f8"));
                        this.a0 = (this.a0 || !this.i());
                        this.a2.aw = this.a2.z;
                        this.al.repaint();
                        if (!this.a0) {
                            this.f.a(this.a2);
                        }
                        else {
                            this.a2.c = 1;
                            this.a2.a = (this.a2.ar + 5000) * 8;
                            if (!this.bc) {
                                this.a2.d = System.currentTimeMillis();
                            }
                        }
                        this.al.action(new Event(this.al, 104, null), null);
                        this.a2.a(this.aj);
                        long n10 = -1L;
                        this.w = 0;
                        while (this.w == 0 && this.a2.g()) {
                            this.a2.b();
                            while (this.w == 0 && !this.a0 && this.f.a(this.ba) > n7 * 7 / 4) {
                                this.a2.b();
                                final long n11 = this.b() * 8;
                                if (n11 - this.au * 8 > this.a2.a) {
                                    break;
                                }
                                if (n10 >> 12 != n11 >> 12) {
                                    this.a(n11 / 8000L);
                                    n10 = n11;
                                }
                                Thread.currentThread();
                                Thread.sleep(10L);
                            }
                            this.a((long)(this.b() / 1000));
                            if (n > 0L && this.b() > n) {
                                this.v = 0;
                                this.w = 1;
                                k.a(zkmToString("gw\u0004x$[8\u001b*!J8\u0014'\"[j\u0018:<]}\u0015\u007f3\u001e*A\u007f![{\u001e16\u001en\u0018;7Q{\u001d6\"Ml\u0003:3S8\u0001-7Hq\u0014(|3N\u0018;7Q{\u001d6\"Ml\u0003:3S8\u0018,rJp\u0014\u007f>[y\u00156<Y8\u000133G}\u000337MkQ);Z}\u001e\u007f!Jj\u0014>?Wv\u0016\u007f!Qt\u0004+;Qv_R\u0006Q8\u00176<Z8\u001e*&\u001eu\u001e-7\u001ey\u001f;rXw\u0003\u007f+Qm\u0003\u007f=IvQ9 [}Q+ Wy\u001d\u007f$[j\u00026=P4Q8=\u001el\u001eR%Io_);Z}\u001e<>Wh\u0002+ [y\u001cq1Qu"));
                                n = 0L;
                            }
                            if (n9 == 0) {
                                if (this.ba < n7) {
                                    n9 = this.a2.z;
                                    final Date date3 = new Date();
                                }
                            }
                            else if (this.ba >= n7) {
                                n9 = this.a2.z;
                                final Date date4 = new Date();
                            }
                            if (this.f.b != null) {
                                final boolean b4 = this.a2.c + 8L * (System.currentTimeMillis() - this.a2.d) >= this.a2.a;
                            }
                            else {
                                final boolean b5 = this.ba < 1600;
                            }
                            final boolean b6 = this.ba < 1600 && this.a2.c + 8L * (System.currentTimeMillis() - this.a2.d) >= this.a2.a + 4000;
                            if (!this.a0 && b6) {
                                this.bc = true;
                                final Date date5 = new Date();
                                while (this.ba < n8 - this.a2.l * 2) {
                                    final Date date6 = new Date();
                                    this.a(zkmToString("\u001e83*4X}\u00036<Y\"Q") + 100 * this.ba / n8 + "%");
                                    if (this.k != null) {
                                        this.k.a(this.ba / n8);
                                        this.k.t = zkmToString("|m\u001797Lq\u001f8");
                                    }
                                    if (!this.a2.g()) {
                                        break;
                                    }
                                }
                                if (this.k != null) {
                                    this.k.a(1.0f);
                                }
                                this.a(zkmToString("\u001e83*4X}\u00036<Y\"Q\u001b=P}_"));
                                if (new Date().getTime() > date5.getTime() + n8 * 1000 / 8000) {
                                    this.a(zkmToString("\u001e8\"3=I8\u00120<P}\u0012+;QvQ\u007f"));
                                }
                                this.bc = false;
                                if (this.f.b == null) {
                                    continue;
                                }
                                this.a2.c = this.a2.a;
                                this.a2.d = System.currentTimeMillis();
                            }
                        }
                        this.bb += this.a2.ad;
                        while (this.w == 0 && !this.a0) {
                            if (this.ba <= 0) {
                                break;
                            }
                            this.a2.b();
                            this.a((long)(this.b() / 1000));
                            Thread.sleep(100L);
                        }
                        while (this.w == 0 && !this.a0) {
                            if (this.a2.c + 8L * (System.currentTimeMillis() - this.a2.d) >= this.a2.a) {
                                break;
                            }
                            this.a((long)(this.b() / 1000));
                            Thread.sleep(100L);
                        }
                        while (this.w == 0 && (this.az.r[this.az.n] || this.a2.aj[this.a2.al].d == 1)) {
                            Thread.sleep(100L);
                        }
                        if (this.a2 != null) {
                            this.a2.as = false;
                        }
                        System.gc();
                        this.a2.close();
                        if (!this.a0) {
                            this.f.b(this.a2);
                        }
                    }
                }
                catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                catch (MalformedURLException ex2) {
                    ex2.printStackTrace();
                }
                catch (IOException ex3) {
                    final String message = ex3.getMessage();
                    if (message.indexOf(zkmToString("\f(G")) >= 0 || message.indexOf(zkmToString("{W\"")) >= 0) {
                        this.a(zkmToString("\u001e8#:!Jy\u0003+;P\u007f_q|"));
                        this.am = true;
                    }
                    else {
                        ex3.printStackTrace();
                    }
                }
                this.au = 0;
            } while (this.ai);
            this.k();
            this.d = null;
        }
        catch (Exception ex4) {
            ex4.printStackTrace();
        }
    }
    
    private String a(final InputStream inputStream) throws IOException {
        int i = 0;
        String string = "";
        while (i < 2) {
            final int read = inputStream.read();
            final char c = (char)read;
            if (i == 0) {
                if (read == 13) {
                    ++i;
                }
            }
            else if (i == 1) {
                if (read == 10) {
                    ++i;
                }
                else {
                    i = 0;
                }
            }
            if (read != 13 && read != 10) {
                string += c;
            }
        }
        return string;
    }
    
    public synchronized void a(int au, final boolean at) {
        if (this.ap > 0) {
            this.as = true;
            au = au / this.ap * this.ap;
            this.at = at;
            if (at) {
                while (!this.ar[au / this.ap] && au >= this.ap) {
                    au -= this.ap;
                }
            }
            this.au = au;
            this.aw = this.az.h;
            this.ax = this.az.i;
            this.w = 1;
            this.h();
            if (!this.bc) {
                this.f();
            }
            else {
                this.e();
            }
        }
        else {
            this.a(zkmToString("\u001e8#><Zw\u001c\u007f\u0013]{\u0014,!\u001ev\u001e+r_n\u00106>_z\u001d:rXw\u0003\u007f&Vq\u0002\u007f1Rq\u0001q"));
        }
    }
    
    public boolean i() {
        boolean b = false;
        try {
            if (!this.a && System.getProperty(zkmToString("Ty\u0007>|H}\u001f;=L")).indexOf(zkmToString("\u007fh\u000137")) == -1 && System.getProperty(zkmToString("Qk_13S}")).indexOf(zkmToString("sy\u0012")) == -1) {
                final p p = new p();
                p.start();
                try {
                    Thread.sleep(250L);
                }
                catch (InterruptedException ex) {}
                if (p != null && p.isAlive()) {
                    p.stop();
                    b = true;
                }
                else {
                    System.out.println(zkmToString("mw\u000416\u001e{\u0010-6\u001ev\u001e+r_n\u00106>_z\u001d:"));
                    this.a(zkmToString("mw\u000416\u001e{\u0010-6\u001ev\u001e+r_n\u00106>_z\u001d:"));
                }
            }
            else {
                b = true;
            }
        }
        catch (SecurityException ex2) {
            b = true;
        }
        return b;
    }
    
    public int a(final long n, final URL url, final String s) {
        final String host = url.getHost();
        final l l = new l(zkmToString("\bBI\u0019\u001fy*B\u001bf\u000bL(\f\u0010r@&\t\u0003k/H\u0014\u001ap[#\u001a\u0018nY"));
        if (host.length() >= 0) {
            this.v = 1;
        }
        else if (s == null) {
            this.v = -1;
        }
        else {
            this.v = 0;
            int n2 = 0;
            final StringTokenizer stringTokenizer = new StringTokenizer(s, zkmToString("\u0004\u0015{Vr"));
            while (this.v != 1 && stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                final double a = l.a(nextToken.substring((nextToken.toUpperCase().indexOf("S") == 0) ? 2 : 1), url.toString(), this.g);
                if (this.g[0] == 0 && this.g[1] == 0 && this.g[2] == 0 && this.g[3] == 0) {
                    n2 = 1;
                }
                else {
                    n2 = 2;
                }
                if (Math.abs(a - Math.log(n) - 1398.0) < 9.0E-5) {
                    this.v = 1;
                }
                else {
                    this.v = 0;
                }
            }
            if (this.v == 1 && (n2 == 2 || url.toString().toLowerCase().indexOf(zkmToString("ak\u0012,\r")) != -1)) {
                this.v = (this.p.getHost().equals(this.q.getHost()) ? 1 : 0);
                final String lowerCase = this.q.toString().toLowerCase();
                for (int n3 = 0; this.v == 0 && n3 < this.r.size(); ++n3) {
                    if (lowerCase.indexOf((String)this.r.elementAt(n3)) == 0) {
                        this.v = 1;
                    }
                }
                if (this.v == 0) {
                    k.a(zkmToString("\u007f{\u0012:!M85:<W}\u0015q_") + this.q + zkmToString("3q\u0002\u007f<QlQ>'Jp\u001e-;D}\u0015\u007f&Q8\u0010<1[k\u0002\u007f1Qv\u0005:<J8\u001e1r3") + this.p);
                    this.v = 1;
                    final int[] g = this.g;
                    final int n4 = 0;
                    final int[] g2 = this.g;
                    final int n5 = 1;
                    final int[] g3 = this.g;
                    final int n6 = 2;
                    final int[] g4 = this.g;
                    final int n7 = 3;
                    final boolean b = true;
                    g3[n6] = (g4[n7] = (b ? 1 : 0));
                    g[n4] = (g2[n5] = (b ? 1 : 0));
                }
            }
        }
        if (this.v != 1) {
            final int[] g5 = this.g;
            final int n8 = 0;
            final int[] g6 = this.g;
            final int n9 = 1;
            final int[] g7 = this.g;
            final int n10 = 2;
            final int[] g8 = this.g;
            final int n11 = 3;
            final boolean b2 = false;
            g7[n10] = (g8[n11] = (b2 ? 1 : 0));
            g5[n8] = (g6[n9] = (b2 ? 1 : 0));
        }
        return this.v;
    }
    
    public void a(final long n) {
        String s = a((int)n);
        if (this.a2 != null && this.a2.ar > 0) {
            s = s + zkmToString("\u00118") + a(this.a2.ar / 1000);
        }
        if (this.k != null) {
            this.k.a((int)n, this.a2.ar / 1000, s);
        }
        if (this.a2 != null) {
            s = s + zkmToString("\u00128Q\u007f\u0002Ry\b6<Y8") + this.o;
        }
        if ((this.j & 0x2) == 0x2) {
            this.al.showStatus(s);
        }
        if (this.i > 0.0 && n > this.i) {
            this.i = 0.0;
            this.al.action(new Event(this.al, 105, null), null);
        }
    }
    
    public static String a(final int n) {
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
    
    public String j() {
        return (String)((JSObject)JSObject.getWindow(this.al).getMember(zkmToString("Zw\u0012*?[v\u0005"))).getMember(zkmToString("]w\u001e4;["));
    }
    
    public void b(final String s) {
        JSObject.getWindow(this.al).eval(zkmToString("Zw\u0012*?[v\u0005q1Qw\u001a67\u0003:") + s + zkmToString("\u001c#"));
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
                    c2 = '>';
                    break;
                }
                case 1: {
                    c2 = '\u0018';
                    break;
                }
                case 2: {
                    c2 = 'q';
                    break;
                }
                case 3: {
                    c2 = '_';
                    break;
                }
                default: {
                    c2 = 'R';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
