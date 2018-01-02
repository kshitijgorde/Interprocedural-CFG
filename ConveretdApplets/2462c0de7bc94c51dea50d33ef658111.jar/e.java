import java.util.StringTokenizer;
import java.io.OutputStream;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.io.IOException;
import java.util.Date;
import java.io.InputStream;
import java.util.Vector;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class e implements Runnable, b
{
    public boolean a;
    public boolean b;
    public boolean c;
    public Thread d;
    public boolean e;
    public String f;
    public URL g;
    public URL h;
    public Vector i;
    public int j;
    public long k;
    public int l;
    public int m;
    public double n;
    public f o;
    public boolean p;
    public volatile int q;
    public int[] r;
    public InputStream s;
    public h t;
    public boolean u;
    public boolean v;
    public boolean w;
    public boolean x;
    public boolean y;
    public String z;
    public byte[] aa;
    public int[] ab;
    public int ac;
    public final int ad = 8000;
    public byte[] ae;
    public byte[] af;
    public int ag;
    public int ah;
    public int ai;
    public int aj;
    public int ak;
    public boolean al;
    
    public e(final URL g, final URL h, final Vector i, final String f, final String s, final int n, final boolean b, final boolean b2, final boolean v, final boolean u) {
        this.a = false;
        this.b = false;
        this.c = false;
        this.d = null;
        this.e = false;
        this.j = 0;
        this.k = 0L;
        this.l = 0;
        this.m = 0;
        this.n = 0.0;
        this.o = null;
        this.p = false;
        this.q = 0;
        this.r = new int[16];
        this.t = null;
        this.w = false;
        this.x = false;
        this.y = true;
        this.z = null;
        this.aa = new byte[16];
        this.ae = new byte[256];
        this.al = false;
        this.ag = n * 8000;
        this.af = new byte[this.ag];
        this.f = f;
        this.g = g;
        this.v = v;
        this.u = u;
        this.h = h;
        this.i = i;
        this.l = this.a(new Date().getTime(), g, s);
        int j = 0;
        for (int n2 = 0, k = 2; k <= 256; k <<= 1, ++n2) {
            while (j < k) {
                this.ae[j] = (byte)n2;
                ++j;
            }
        }
    }
    
    public void a(final boolean y, final boolean x, final String z) {
        this.y = y;
        this.x = x;
        this.z = z;
    }
    
    public String a() {
        return this.z;
    }
    
    public int b() {
        if (this.t != null) {
            return this.t.o;
        }
        return 0;
    }
    
    public int c() {
        return (int)this.k * 1000;
    }
    
    public long d() {
        long n = 0L;
        if (this.t != null && this.t.e != Long.MAX_VALUE) {
            final long n2 = this.t.a / 8L + ((this.t.a > 0) ? 150 : 0);
            final long n3 = this.t.d / 8L + (System.currentTimeMillis() - this.t.e);
            if (this.p) {
                n = n3;
            }
            else if (this.b) {
                n = this.o.f() / 8L + 150L;
            }
            else if (this.c) {
                if (n3 < n2) {
                    n = n3;
                }
                else {
                    n = n2;
                }
            }
            else {
                n = n2;
            }
        }
        return n;
    }
    
    public int e() {
        return this.m * 1000 + (int)this.d();
    }
    
    public int f() {
        if (this.t != null && this.t.o > 0) {
            return (int)(8000.0 * this.j / this.t.o);
        }
        return -1;
    }
    
    public String g() {
        if (this.d == null) {
            return zkmToString("w\u0015>U");
        }
        if (this.t == null) {
            return zkmToString("w\u001f;Ds\u007f\u001d;Jsp\u0016");
        }
        if (this.al) {
            if (this.t.a != 0) {
                return zkmToString("n\u0010'C\u007fz");
            }
            if (this.l == 1) {
                return zkmToString("l\u00143Tc");
            }
            return zkmToString("w\u001f$Qvw\u0015r[\u007fg");
        }
        else {
            if (this.n < 0.9999) {
                return zkmToString("|\u00044V\u007fl\u0018<W");
            }
            return zkmToString("n\u001d3Isp\u0016");
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
    
    public void run() {
        final boolean b = false;
        this.ak = (b ? 1 : 0);
        this.aj = (b ? 1 : 0);
        final boolean b2 = false;
        this.ai = (b2 ? 1 : 0);
        this.ah = (b2 ? 1 : 0);
        try {
            do {
                if (this.z != null && !this.y && (this.u || this.x) && !this.f.endsWith("." + this.z)) {
                    this.f = this.f.substring(0, this.f.lastIndexOf(".") + 1) + this.z;
                }
                if (this.m > 0) {
                    final URLConnection openConnection = new URL(this.g, this.f + "i").openConnection();
                    final byte[] array = new byte[2];
                    final InputStream inputStream = openConnection.getInputStream();
                    inputStream.read(this.aa);
                    int n = 16;
                    if (this.aa[0] != 68 || this.aa[1] != 78 || this.aa[2] != 89) {
                        this.m = 0;
                        this.ac = 0;
                    }
                    else {
                        this.ac = ((this.aa[14] << 8 & 0xFF00) | (this.aa[15] & 0xFF));
                        this.ab = new int[this.ac];
                        for (int i = 0; i < this.ac; ++i) {
                            inputStream.read(array);
                            this.ab[i] = n;
                            n += ((array[1] << 8 & 0xFF00) | (array[0] & 0xFF));
                        }
                    }
                    inputStream.close();
                    if (this.m >= this.ac) {
                        this.m = this.ac - 1;
                    }
                }
                final URL url = new URL(this.g, this.f);
                if (this.m > 0 && this.m < this.ac) {
                    System.out.println(zkmToString("N\u001d3I:") + this.m + "/" + this.ac + zkmToString(">1r") + this.ab[this.m] + zkmToString(">*") + this.f + "]");
                    final String host = url.getHost();
                    int port = url.getPort();
                    if (port == -1) {
                        port = 80;
                    }
                    if (host.length() > 0) {
                        final Socket socket = new Socket(host, port);
                        final String string = zkmToString("Y4\u0006\u0010") + url.getFile() + zkmToString(">9\u0006dJ1@|\u0001\u0017\u0014") + zkmToString("L\u0010<W\u007f$Q0In{\u0002o") + this.ab[this.m] + zkmToString("3|X") + zkmToString("V\u001e!D >") + url.getHost() + zkmToString("\u0013{") + zkmToString("]\u001e<^\u007f}\u0005;_t$Q\u0011\\um\u0014_:") + zkmToString("\u0013{_:");
                        this.s = socket.getInputStream();
                        final OutputStream outputStream = socket.getOutputStream();
                        outputStream.write(string.getBytes());
                        outputStream.flush();
                        int n2 = 0;
                        while (true) {
                            final String a = this.a(this.s);
                            ++n2;
                            final int index = a.toLowerCase().indexOf(zkmToString("}\u001e<D\u007fp\u0005\u007f\\\u007fp\u0016&X "));
                            if (a.length() < 3) {
                                break;
                            }
                            if (index < 0) {
                                continue;
                            }
                            this.j = Integer.parseInt(a.substring(index + 16));
                        }
                        this.j += this.ab[this.m];
                    }
                    else {
                        final URLConnection openConnection2 = url.openConnection();
                        this.j = openConnection2.getContentLength();
                        this.s = openConnection2.getInputStream();
                        byte[] array2;
                        int j;
                        for (array2 = new byte[1024], j = 0; j + 1024 < this.ab[this.m]; j += this.s.read(array2)) {}
                        while (j < this.ab[this.m]) {
                            this.s.read();
                            ++j;
                        }
                    }
                }
                else {
                    final URLConnection openConnection3 = url.openConnection();
                    this.j = openConnection3.getContentLength();
                    this.s = openConnection3.getInputStream();
                }
                if (this.j < 0) {
                    j.a(zkmToString("]\u001e'\\~>\u001f=D:x\u0018<T:\u007f\u00046Yu>\u0012>Yj>M") + this.f + zkmToString(" Q!@\u007f}\u00184Y\u007fzQ;^:j\u00197\u0010RJ<\u001e\u0010yq\u00157"));
                    this.l();
                    return;
                }
                this.t = new h(this);
                if (this.m > 0 && this.m < this.ac) {
                    this.t.a(this.aa);
                }
                this.o = new f(false, this.ag / 8000, this.b);
                if (this.o.b != null) {
                    this.af = new byte[this.ag * 2];
                }
                if (this.t.c() && this.t.d()) {
                    final int n3 = this.ag / 2;
                    final int n4 = this.ag - 2 * this.t.m;
                    int n5 = 0;
                    Date date2;
                    Date date;
                    for (date = (date2 = new Date()); this.t.d() && this.t.z < n3 && date2.getTime() - date.getTime() < n3 / 8; date2 = new Date()) {
                        if (this.j > 0) {
                            this.n = this.t.z / n3;
                        }
                    }
                    this.n = 1.0;
                    if (!this.w && this.z == null) {
                        final long n6 = new Date().getTime() - date.getTime();
                        this.k = 8 * this.t.v;
                        if (n6 > 0L) {
                            this.k /= n6;
                        }
                        this.z = ((this.k > 64L) ? zkmToString("-C") : zkmToString(",A"));
                        if (!this.y && (this.u || this.x) && this.z.compareTo(zkmToString(",A")) != 0) {
                            this.o.b(this.t);
                            if (!this.a) {
                                this.t.close();
                            }
                            this.run();
                            return;
                        }
                    }
                    while (this.t.d() && this.t.z < n4 && this.t.o > this.k * 800L) {
                        final Date date3 = new Date();
                        if (!date3.equals(date)) {
                            this.k = 8 * this.t.v / (date3.getTime() - date.getTime());
                        }
                        this.n = this.t.z / n4;
                    }
                    this.n = 1.0;
                    this.p = !m();
                    if (this.p) {
                        this.l();
                        return;
                    }
                    this.o.a(this.t);
                    this.q = 0;
                    new Date().getTime();
                    final double n7 = this.t.o / 64000.0;
                    while (this.q == 0 && this.t.d()) {
                        while (this.q == 0 && this.o.a(this.aj) > n3) {
                            this.t.b();
                            Thread.currentThread();
                            Thread.sleep(10L);
                        }
                        if (n5 == 0) {
                            if (this.aj < n3) {
                                n5 = this.t.v;
                                date = new Date();
                            }
                        }
                        else if (this.aj >= n3) {
                            n5 = this.t.v;
                            date = new Date();
                        }
                        if (this.aj < 8000 && this.j > 0) {
                            final double n8 = (this.t.v - n5) / (new Date().getTime() - date.getTime());
                            final double n9 = (this.j - this.t.v) * 8.0 / this.t.o;
                            final double n10 = (this.j - this.t.v) / n8 / 1000.0;
                            if (n10 <= n9) {
                                continue;
                            }
                            this.al = true;
                            for (int n11 = (int)((n10 - n9) * 8000.0), n12 = 0; n12 < n11 && this.aj < n4; n12 += this.t.m, this.n = n12 / n11) {
                                if (!this.t.d()) {
                                    break;
                                }
                                this.k = 8 * (this.t.v - n5) / (new Date().getTime() - date.getTime());
                                if (this.t.o <= this.k * 1000L) {
                                    break;
                                }
                            }
                            this.n = 1.0;
                            n5 = 0;
                            this.al = false;
                        }
                    }
                    this.ak = this.t.z;
                    while (this.q == 0 && (this.aj > 0 || this.al || this.t.d + (System.currentTimeMillis() - this.t.e) * 8L < this.t.a)) {
                        this.t.b();
                        Thread.sleep(50L);
                    }
                    if (this.v && this.ak <= this.ag) {
                        this.ag = this.ak;
                        final boolean b3 = false;
                        this.ai = (b3 ? 1 : 0);
                        this.ah = (b3 ? 1 : 0);
                        while (this.q == 0) {
                            this.aj = this.ak;
                            if (this.o.b != null && this.o.b.b() < 8000) {
                                this.t.b();
                            }
                            Thread.sleep(200L);
                        }
                    }
                    if (!this.a) {
                        this.t.close();
                    }
                    this.o.b(this.t);
                }
                this.m = 0;
            } while (this.q == 0 && this.v);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        catch (MalformedURLException ex2) {
            j.a(zkmToString("J\u00197\u0010{k\u0015;_:}\u001d;@:w\u0002rUsj\u00197B:j\u0003'^y\u007f\u00057T:q\u0003rDr{Q!Dh{\u0010?\u0010m\u007f\u0002rYtj\u0014 Bon\u00057T"));
            ex2.printStackTrace();
        }
        catch (IOException ex4) {}
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
        this.l();
    }
    
    public synchronized void h() {
        if (this.d == null) {
            this.al = true;
            (this.d = new Thread(this)).start();
        }
    }
    
    public synchronized void a(final int m) {
        this.m = m;
        this.h();
    }
    
    public synchronized void i() {
        this.h();
        this.al = false;
        if (this.o != null) {
            this.o.a(this.t, this.al);
            this.t.b();
        }
    }
    
    public synchronized boolean j() {
        if (this.d == null || this.t == null) {
            return false;
        }
        this.al = true;
        if (this.o != null) {
            this.o.a(this.t, this.al);
            this.t.b();
        }
        return true;
    }
    
    private void l() {
        if (this.t != null) {
            if (this.o != null) {
                this.o.b(this.t);
            }
            try {
                if (!this.a) {
                    this.t.close();
                }
                this.t = null;
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        this.d = null;
    }
    
    public synchronized void k() {
        if (this.al) {
            this.al = false;
        }
        if (this.o != null) {
            this.o.a(this.t, true);
        }
        int n = 0;
        try {
            while (n < 100 && this.d != null) {
                this.q = 1;
                this.al = false;
                ++n;
                Thread.sleep(10L);
            }
            if (this.d != null) {
                this.d.stop();
                this.d = null;
                this.l();
            }
        }
        catch (Exception ex) {}
    }
    
    public static boolean m() {
        boolean b = false;
        try {
            if (System.getProperty(zkmToString("t\u0010$Q4h\u0014<Tul")).indexOf(zkmToString("_\u0001\"\\\u007f")) == -1 && System.getProperty(zkmToString("q\u0002|^{s\u0014")).indexOf(zkmToString("S\u00101")) == -1 && System.getProperty(zkmToString("t\u0010$Q4h\u0014 Csq\u001f")).compareTo(zkmToString("/_a")) < 0) {
                final i i = new i();
                i.start();
                try {
                    Thread.sleep(250L);
                }
                catch (InterruptedException ex) {}
                if (i != null && i.isAlive()) {
                    i.stop();
                    b = true;
                }
                else {
                    System.out.println(zkmToString("M\u001e'^~>\u00123B~>\u001f=D:\u007f\u00073Yv\u007f\u0013>U"));
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
        final l l = new l(zkmToString("KE\u0015\u0003V]G\u0006gI+'k\u0002-U<\u0002tB\\(jjTL4\u0013aRT7"));
        if (host.length() == 0) {
            this.l = 1;
        }
        else if (s == null) {
            this.l = -1;
        }
        else {
            this.l = 0;
            int n2 = 0;
            final StringTokenizer stringTokenizer = new StringTokenizer(s, zkmToString("$|X9:"));
            while (this.l != 1 && stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                final double a = l.a(nextToken.substring((nextToken.toUpperCase().indexOf("S") == 0) ? 2 : 1), url.toString(), this.r);
                if (this.r[0] == 0 && this.r[1] == 0 && this.r[2] == 0 && this.r[3] == 0) {
                    n2 = 1;
                }
                else {
                    n2 = 2;
                }
                if (Math.abs(a - Math.log(n) - 1398.0) < 9.0E-5) {
                    this.l = 1;
                }
                else {
                    this.l = 0;
                }
            }
            if (this.l == 1 && (n2 == 2 || url.toString().toLowerCase().indexOf(zkmToString("A\u00021CE")) != -1)) {
                this.l = (this.g.getHost().equals(this.h.getHost()) ? 1 : 0);
                final String lowerCase = this.h.toString().toLowerCase();
                for (int n3 = 0; this.l == 0 && n3 < this.i.size(); ++n3) {
                    if (lowerCase.indexOf((String)this.i.elementAt(n3)) == 0) {
                        this.l = 1;
                    }
                }
                if (this.l == 0) {
                    j.a(zkmToString("_\u00121UimQ\u0016Utw\u00146\u001e\u0017") + this.h + zkmToString("\u0013\u0018!\u0010tq\u0005rQoj\u0019=Bsd\u00146\u0010nqQ3Sy{\u0002!\u0010yq\u001f&UtjQ=^:\u0013") + this.g);
                    this.l = 1;
                    final int[] r = this.r;
                    final int n4 = 0;
                    final int[] r2 = this.r;
                    final int n5 = 1;
                    final int[] r3 = this.r;
                    final int n6 = 2;
                    final int[] r4 = this.r;
                    final int n7 = 3;
                    final boolean b = true;
                    r3[n6] = (r4[n7] = (b ? 1 : 0));
                    r[n4] = (r2[n5] = (b ? 1 : 0));
                }
            }
        }
        if (this.l != 1) {
            final int[] r5 = this.r;
            final int n8 = 0;
            final int[] r6 = this.r;
            final int n9 = 1;
            final int[] r7 = this.r;
            final int n10 = 2;
            final int[] r8 = this.r;
            final int n11 = 3;
            final boolean b2 = false;
            r7[n10] = (r8[n11] = (b2 ? 1 : 0));
            r5[n8] = (r6[n9] = (b2 ? 1 : 0));
        }
        return this.l;
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
                    c2 = '\u001e';
                    break;
                }
                case 1: {
                    c2 = 'q';
                    break;
                }
                case 2: {
                    c2 = 'R';
                    break;
                }
                case 3: {
                    c2 = '0';
                    break;
                }
                default: {
                    c2 = '\u001a';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
