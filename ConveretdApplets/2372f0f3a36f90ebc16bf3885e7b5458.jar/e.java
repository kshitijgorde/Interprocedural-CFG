import java.net.URLConnection;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.util.StringTokenizer;
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
    public String g;
    public URL h;
    public URL i;
    public Vector j;
    public int k;
    public long l;
    public int m;
    public int n;
    public double o;
    public f p;
    public boolean q;
    public volatile int r;
    public int[] s;
    public InputStream t;
    public i u;
    public boolean v;
    public boolean w;
    public boolean x;
    public int y;
    public String z;
    public int aa;
    public byte[] ab;
    public int ac;
    public int ad;
    public byte[] ae;
    public byte[] af;
    public int ag;
    public int ah;
    public int ai;
    public int aj;
    public int ak;
    public boolean al;
    
    public e(final URL h, final URL i, final Vector j, final String f, final String s, final int n, final boolean b, final boolean b2, final boolean w, final boolean v, final String g) {
        this.a = false;
        this.b = false;
        this.c = false;
        this.d = null;
        this.e = false;
        this.k = 0;
        this.l = 0L;
        this.m = 0;
        this.n = 0;
        this.o = 0.0;
        this.p = null;
        this.q = false;
        this.r = 0;
        this.s = new int[16];
        this.u = null;
        this.x = false;
        this.y = 10;
        this.z = null;
        this.aa = 100;
        this.ab = new byte[16416];
        this.ac = 32;
        this.ad = 8000;
        this.ae = new byte[256];
        this.al = false;
        this.y = 10;
        this.ag = this.y * this.ad;
        this.af = new byte[this.ag];
        this.f = f;
        this.h = h;
        this.w = w;
        this.v = v;
        this.i = i;
        this.j = j;
        this.g = g;
        this.m = this.a(new Date().getTime(), h, s);
        int k = 0;
        for (int n2 = 0, l = 2; l <= 256; l <<= 1, ++n2) {
            while (k < l) {
                this.ae[k] = (byte)n2;
                ++k;
            }
        }
    }
    
    public void a(final int aa, final String z) {
        this.aa = aa;
        this.z = z;
    }
    
    public String a() {
        return this.z;
    }
    
    public int b() {
        if (this.u != null) {
            return this.u.p;
        }
        return 0;
    }
    
    public int c() {
        return (int)this.l * 1000;
    }
    
    public long d() {
        long n = 0L;
        if (this.u != null && this.u.e != Long.MAX_VALUE) {
            final long n2 = this.u.a * 1000L / this.ad + ((this.u.a > 0) ? 150 : 0);
            final long n3 = this.u.d * 1000L / this.ad + (System.currentTimeMillis() - this.u.e);
            if (this.q) {
                n = n3;
            }
            else if (this.b) {
                n = this.p.k() * 1000L / this.ad + 150L;
            }
            else if (this.p.c != null) {
                n = this.p.l() * 1000L / this.ad + 150L;
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
        return this.n * 1000 + (int)this.d();
    }
    
    public int f() {
        if (this.u != null && this.u.q > 0) {
            return (int)(this.u.t * 1000.0 / this.u.q);
        }
        return -1;
    }
    
    public String g() {
        if (this.d == null) {
            return zkmToString("Wp*\b");
        }
        if (this.u == null) {
            return zkmToString("Wz/\u00190_x/\u00170Ps");
        }
        if (this.al) {
            if (this.u.a != 0) {
                return zkmToString("Nu3\u001e<Z");
            }
            if (this.m == 1) {
                return zkmToString("Lq'\t ");
            }
            return zkmToString("Wz0\f5Wpf\u0006<G");
        }
        else {
            if (this.o < 0.9999) {
                return zkmToString("\\a \u000b<L}(\n");
            }
            return zkmToString("Nx'\u00140Ps");
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
    
    public static String a(final String s, final String s2, final long n) {
        String string = s;
        final StringTokenizer stringTokenizer = new StringTokenizer(s2, "/");
        while (stringTokenizer.hasMoreTokens()) {
            String s4;
            final String s3 = s4 = stringTokenizer.nextToken();
            int i = 0;
            while (i == 0) {
                try {
                    i = Integer.parseInt(s4);
                }
                catch (Exception ex) {
                    s4 = s4.substring(0, s4.length() - 1);
                    continue;
                }
                break;
            }
            if (i <= n) {
                final int lastIndex = s.lastIndexOf("_");
                final int index = s.indexOf(".", lastIndex);
                if (index <= 0 || lastIndex <= 0) {
                    continue;
                }
                string = s.substring(0, lastIndex + 1) + s3 + s.substring(index);
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
                if (this.z != null && this.v) {
                    this.l = Integer.parseInt(this.z);
                    this.f = a(this.f, this.g, this.l);
                }
                int n = 0;
                int n2 = 0;
                int n3 = 0;
                if (this.n > 0) {
                    final InputStream inputStream = new URL(this.h, this.f).openConnection().getInputStream();
                    int n4 = 0;
                    int i = 16416;
                    while (i > 0) {
                        final int read = inputStream.read(this.ab, n4, i);
                        if (read == -1) {
                            i = 0;
                        }
                        else {
                            i -= read;
                            n4 += read;
                        }
                    }
                    final int n5 = (this.ab[8] & 0xFF) * 4000;
                    n = ((this.ab[10] << 8 & 0xFF00) | (this.ab[11] & 0xFF));
                    n2 = (((this.ab[4] << 8 & 0xFF00) | (this.ab[5] & 0xFF)) << 16 | ((this.ab[6] << 8 & 0xFF00) | (this.ab[7] & 0xFF)));
                    n3 = (((this.ab[16] << 8 & 0xFF00) | (this.ab[17] & 0xFF)) << 16 | ((this.ab[18] << 8 & 0xFF00) | (this.ab[19] & 0xFF)));
                    this.ac = 32;
                    inputStream.close();
                }
                final URL url = new URL(this.h, this.f);
                if (this.n > 0) {
                    final long n6 = n2 * this.n / (n3 / n) & 0xFFFFFFFFFFFFFFF0L;
                    final String host = url.getHost();
                    int port = url.getPort();
                    if (port == -1) {
                        port = 80;
                    }
                    if (host.length() > 0) {
                        final Socket socket = new Socket(host, port);
                        final String string = zkmToString("yQ\u0012M") + url.getFile() + zkmToString("\u001e\\\u00129\t\u0011%h\\T4") + zkmToString("lu(\n<\u00044$\u0014-[g{") + n6 + zkmToString("\u0013\u0019L") + zkmToString("v{5\u0019c\u001e") + url.getHost() + zkmToString("3\u001e") + zkmToString("}{(\u0003<]`/\u00027\u00044\u0005\u00016MqKg") + zkmToString("3\u001eKg");
                        this.t = socket.getInputStream();
                        final OutputStream outputStream = socket.getOutputStream();
                        outputStream.write(string.getBytes());
                        outputStream.flush();
                        int n7 = 0;
                        while (true) {
                            final String a = this.a(this.t);
                            ++n7;
                            final int index = a.toLowerCase().indexOf(zkmToString("]{(\u0019<P`k\u0001<Ps2\u0005c"));
                            if (a.length() < 3) {
                                break;
                            }
                            if (index < 0) {
                                continue;
                            }
                            this.k = Integer.parseInt(a.substring(index + 16));
                        }
                        this.k += (int)n6;
                    }
                    else {
                        final URLConnection openConnection = url.openConnection();
                        this.k = openConnection.getContentLength();
                        this.t = openConnection.getInputStream();
                        byte[] array;
                        int n8;
                        for (array = new byte[1024], n8 = 0; n8 + 1024 < n6; n8 += this.t.read(array)) {}
                        while (n8 < n6) {
                            this.t.read();
                            ++n8;
                        }
                    }
                }
                else {
                    final URLConnection openConnection2 = url.openConnection();
                    this.k = openConnection2.getContentLength();
                    this.t = openConnection2.getInputStream();
                }
                if (this.k < 0) {
                    k.a(zkmToString("}{3\u0001=\u001ez)\u0019yX}(\ty_a\"\u00046\u001ew*\u0004)\u001e(") + this.f + zkmToString("\u000045\u001d<]} \u0004<Z4/\u0003yJ|#M\u0011jY\nM:Qp#"));
                    this.l();
                    return;
                }
                this.u = new i(this);
                if (this.n > 0) {
                    this.u.a(this.ab);
                }
                if (this.u.d()) {
                    this.u.a(this.aa);
                    if (this.u.q > 22000) {
                        this.y = 4;
                    }
                    this.p = new f(false, this.y, this.b, this.u.q, this.u.o);
                    if (this.p.b != null || this.p.c != null) {
                        this.ad = this.u.q;
                        this.ag = this.y * this.ad * this.u.o;
                        this.af = new byte[this.ag * 2];
                    }
                    else {
                        this.ag = this.y * this.ad;
                        this.af = new byte[this.ag];
                    }
                    if (this.u.e()) {
                        final int n9 = this.ag / 2;
                        final int n10 = this.ag - 3 * this.u.n;
                        int n11 = this.u.x;
                        Date date2;
                        Date date;
                        for (date = (date2 = new Date()); this.u.e() && this.u.aa < n9 && date2.getTime() - date.getTime() < n9 * 1000 / this.ad; date2 = new Date()) {
                            if (this.k > 0) {
                                this.o = this.u.aa / n9;
                            }
                        }
                        this.o = 1.0;
                        if (!this.x && this.z == null) {
                            final long n12 = new Date().getTime() - date.getTime();
                            if (this.u.x - n11 <= 0) {
                                this.l = 8 * this.u.x;
                            }
                            else {
                                this.l = 8 * (this.u.x - n11);
                            }
                            this.l = this.l * 115L / 100L;
                            if (n12 > 0L) {
                                this.l /= n12;
                            }
                            this.z = String.valueOf(this.l);
                            if (this.v && this.g.length() > 0 && a(this.f, this.g, this.l).compareTo(this.f) != 0) {
                                this.p.b(this.u);
                                if (!this.a) {
                                    this.u.close();
                                }
                                this.run();
                                return;
                            }
                        }
                        while (this.u.e() && this.u.aa < n10 && this.u.p > this.l * 800L) {
                            System.out.println(this.u.aa + zkmToString("\u001e.f") + this.u.z + zkmToString("\u001e;f") + n10);
                            final Date date3 = new Date();
                            if (!date3.equals(date)) {
                                this.l = 8 * this.u.x / (date3.getTime() - date.getTime());
                            }
                            this.o = this.u.aa / n10;
                        }
                        this.o = 1.0;
                        this.q = !m();
                        if (this.q) {
                            this.l();
                            return;
                        }
                        this.p.a(this.u);
                        this.r = 0;
                        new Date().getTime();
                        final double n13 = this.u.p / 64000.0;
                        while (this.r == 0 && this.u.e()) {
                            while (this.r == 0 && this.p.a(this.aj) > n9) {
                                this.u.b();
                                this.u.c();
                                Thread.currentThread();
                                Thread.sleep(10L);
                            }
                            if (n11 == 0) {
                                if (this.aj < n9) {
                                    n11 = this.u.x;
                                    date = new Date();
                                }
                            }
                            else if (this.aj >= n9) {
                                n11 = this.u.x;
                                date = new Date();
                            }
                            if (this.aj < this.ad && this.k > 0) {
                                final double n14 = (this.u.x - n11) / (new Date().getTime() - date.getTime());
                                final double n15 = (this.k - this.u.x) * 8.0 / this.u.p;
                                final double n16 = (this.k - this.u.x) / n14 / 1000.0;
                                if (n16 <= n15) {
                                    continue;
                                }
                                this.al = true;
                                for (int n17 = (int)((n16 - n15) * this.ad), n18 = 0; n18 < n17 && this.aj < n10; n18 += this.u.n, this.o = n18 / n17) {
                                    if (!this.u.e()) {
                                        break;
                                    }
                                    final long n19 = new Date().getTime() - date.getTime();
                                    if (n19 <= 0L) {
                                        break;
                                    }
                                    this.l = 8 * (this.u.x - n11) / n19;
                                    if (this.u.p <= this.l * 1000L) {
                                        break;
                                    }
                                }
                                this.o = 1.0;
                                n11 = 0;
                                this.al = false;
                            }
                        }
                        this.ak = this.u.aa;
                        while (this.r == 0 && (this.aj > 0 || this.al || this.u.d + (System.currentTimeMillis() - this.u.e) * this.u.o * this.ad / 1000L < this.u.a)) {
                            this.u.b();
                            this.u.c();
                            Thread.sleep(50L);
                        }
                        if (this.w && this.ak <= this.ag) {
                            this.ag = this.ak;
                            final boolean b3 = false;
                            this.ai = (b3 ? 1 : 0);
                            this.ah = (b3 ? 1 : 0);
                            while (this.r == 0) {
                                this.aj = this.ak;
                                if (this.p.b != null && this.p.b.b() < 8000) {
                                    this.u.b();
                                }
                                if (this.p.c != null && this.p.c.b() < 8000) {
                                    this.u.c();
                                }
                                Thread.sleep(200L);
                            }
                        }
                        if (!this.a) {
                            this.u.close();
                        }
                        this.p.b(this.u);
                    }
                }
                this.n = 0;
            } while (this.r == 0 && this.w);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        catch (MalformedURLException ex2) {
            k.a(zkmToString("j|#M8Kp/\u0002y]x/\u001dyWgf\b0J|#\u001fyJf3\u0003:_`#\tyQff\u00191[45\u0019+[u+M._gf\u00047Jq4\u001f,N`#\t"));
            ex2.printStackTrace();
        }
        catch (IOException ex3) {
            ex3.printStackTrace();
        }
        catch (Exception ex4) {
            ex4.printStackTrace();
        }
        this.l();
    }
    
    public synchronized void h() {
        if (this.d == null) {
            this.al = true;
            (this.d = new Thread(this)).start();
        }
    }
    
    public synchronized void a(final int n) {
        this.n = n;
        this.h();
    }
    
    public synchronized void i() {
        this.h();
        this.al = false;
        if (this.p != null) {
            this.p.a(this.u, this.al);
            this.u.b();
            this.u.c();
        }
    }
    
    public synchronized boolean j() {
        System.out.println(this.f + " " + this.u + " " + this.d);
        if (this.d == null || this.u == null) {
            return false;
        }
        this.al = true;
        if (this.p != null) {
            this.p.a(this.u, this.al);
            this.u.b();
            this.u.c();
        }
        return true;
    }
    
    private void l() {
        if (this.u != null) {
            if (this.p != null) {
                this.p.b(this.u);
            }
            try {
                if (!this.a) {
                    this.u.close();
                }
                this.u = null;
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
        if (this.p != null) {
            this.p.a(this.u, true);
            if (this.p.c != null) {
                this.p.g();
            }
        }
        int n = 0;
        try {
            while (n < 10 && this.d != null) {
                this.r = 1;
                this.al = false;
                ++n;
                Thread.sleep(100L);
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
            if (System.getProperty(zkmToString("Tu0\fwHq(\t6L")).indexOf(zkmToString("\u007fd6\u0001<")) == -1 && System.getProperty(zkmToString("Qgh\u00038Sq")).indexOf(zkmToString("su%")) == -1 && System.getProperty(zkmToString("Tu0\fwHq4\u001e0Qz")).compareTo(zkmToString("\u000f:u")) < 0) {
                final j j = new j();
                j.start();
                try {
                    Thread.sleep(250L);
                }
                catch (InterruptedException ex) {}
                if (j != null && j.isAlive()) {
                    j.stop();
                    b = true;
                }
                else {
                    System.out.println(zkmToString("m{3\u0003=\u001ew'\u001f=\u001ez)\u0019y_b'\u00045_v*\b"));
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
    
    public static int b(final long n, final URL url, final String s) {
        int n2 = 0;
        final int[] array = new int[16];
        final String host = url.getHost();
        final m m = new m(zkmToString("k \u0001^\u0015}\"\u0012:\n\u000bB\u007f_nuY\u0016)\u0001|M~7\u0017lQ\u0007<\u0011tR"));
        int n3;
        if (host.length() == 0) {
            n3 = 2;
        }
        else if (s == null) {
            n3 = -1;
        }
        else {
            n3 = 0;
            final StringTokenizer stringTokenizer = new StringTokenizer(s, zkmToString("\u0004\u0019Ldy"));
            while (n3 != 1 && stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                final int n4 = (nextToken.toUpperCase().indexOf("S") == 0) ? 2 : 1;
                final int[] array2 = { 0 };
                final double a = m.a(nextToken.substring(n4), url.toString(), array, array2);
                n2 = array2[0];
                if (array[0] == 0 && array[1] == 0 && array[2] == 0 && array[3] == 0) {}
                if (Math.abs(a - Math.log(n) - 1398.0) < 9.0E-5) {
                    n3 = 1;
                }
                else {
                    n3 = 0;
                }
            }
        }
        if (n3 != 1) {
            final int[] array3 = array;
            final int n5 = 0;
            final int[] array4 = array;
            final int n6 = 1;
            final int[] array5 = array;
            final int n7 = 2;
            final int[] array6 = array;
            final int n8 = 3;
            final boolean b = false;
            array5[n7] = (array6[n8] = (b ? 1 : 0));
            array3[n5] = (array4[n6] = (b ? 1 : 0));
        }
        if (n3 == 1 && n2 == 1) {
            n3 = 2;
        }
        return n3;
    }
    
    public int a(final long n, final URL url, final String s) {
        final String host = url.getHost();
        final m m = new m(zkmToString("k \u0001^\u0015}\"\u0012:\n\u000bB\u007f_nuY\u0016)\u0001|M~7\u0017lQ\u0007<\u0011tR"));
        if (host.length() == 0) {
            this.m = 1;
        }
        else if (s == null) {
            this.m = -1;
        }
        else {
            this.m = 0;
            int n2 = 0;
            final StringTokenizer stringTokenizer = new StringTokenizer(s, zkmToString("\u0004\u0019Ldy"));
            while (this.m != 1 && stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                final double a = m.a(nextToken.substring((nextToken.toUpperCase().indexOf("S") == 0) ? 2 : 1), url.toString(), this.s, new int[1]);
                if (this.s[0] == 0 && this.s[1] == 0 && this.s[2] == 0 && this.s[3] == 0) {
                    n2 = 1;
                }
                else {
                    n2 = 2;
                }
                if (Math.abs(a - Math.log(n) - 1398.0) < 9.0E-5) {
                    this.m = 1;
                }
                else {
                    this.m = 0;
                }
            }
            if (this.m == 1 && (n2 == 2 || url.toString().toLowerCase().indexOf(zkmToString("ag%\u001e\u0006")) != -1)) {
                this.m = (this.h.getHost().equals(this.i.getHost()) ? 1 : 0);
                final String lowerCase = this.i.toString().toLowerCase();
                for (int n3 = 0; this.m == 0 && n3 < this.j.size(); ++n3) {
                    if (lowerCase.indexOf((String)this.j.elementAt(n3)) == 0) {
                        this.m = 1;
                    }
                }
                if (this.m == 0) {
                    k.a(zkmToString("\u007fw%\b*M4\u0002\b7Wq\"CT") + this.i + zkmToString("3}5M7Q`f\f,J|)\u001f0Dq\"M-Q4'\u000e:[g5M:Qz2\b7J4)\u0003y3") + this.h);
                    this.m = 1;
                    final int[] s2 = this.s;
                    final int n4 = 0;
                    final int[] s3 = this.s;
                    final int n5 = 1;
                    final int[] s4 = this.s;
                    final int n6 = 2;
                    final int[] s5 = this.s;
                    final int n7 = 3;
                    final boolean b = true;
                    s4[n6] = (s5[n7] = (b ? 1 : 0));
                    s2[n4] = (s3[n5] = (b ? 1 : 0));
                }
            }
        }
        if (this.m != 1) {
            final int[] s6 = this.s;
            final int n8 = 0;
            final int[] s7 = this.s;
            final int n9 = 1;
            final int[] s8 = this.s;
            final int n10 = 2;
            final int[] s9 = this.s;
            final int n11 = 3;
            final boolean b2 = false;
            s8[n10] = (s9[n11] = (b2 ? 1 : 0));
            s6[n8] = (s7[n9] = (b2 ? 1 : 0));
        }
        return this.m;
    }
    
    public void b(final int aa) {
        this.aa = aa;
        if (this.u != null) {
            this.u.a(aa);
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
                    c2 = '>';
                    break;
                }
                case 1: {
                    c2 = '\u0014';
                    break;
                }
                case 2: {
                    c2 = 'F';
                    break;
                }
                case 3: {
                    c2 = 'm';
                    break;
                }
                default: {
                    c2 = 'Y';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
