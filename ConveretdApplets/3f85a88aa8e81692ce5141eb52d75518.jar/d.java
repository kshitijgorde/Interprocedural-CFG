import java.util.StringTokenizer;
import java.io.OutputStream;
import java.net.URLConnection;
import java.net.MalformedURLException;
import sun.audio.AudioPlayer;
import java.net.Socket;
import java.io.IOException;
import java.util.Date;
import java.io.InputStream;
import java.util.Vector;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class d implements Runnable
{
    public Thread a;
    public boolean b;
    public String c;
    public URL d;
    public URL e;
    public Vector f;
    public int g;
    public long h;
    public int i;
    public int j;
    public double k;
    public e l;
    public volatile int m;
    public int[] n;
    public InputStream o;
    public g p;
    public boolean q;
    public boolean r;
    public boolean s;
    public boolean t;
    public boolean u;
    public String v;
    public byte[] w;
    public int[] x;
    public int y;
    public final int z = 8000;
    public byte[] aa;
    public byte[] ab;
    public int ac;
    public int ad;
    public int ae;
    public int af;
    public int ag;
    public boolean ah;
    
    public d(final URL d, final URL e, final Vector f, final String c, final String s, final int n, final boolean b, final boolean b2, final boolean r, final boolean q) {
        this.a = null;
        this.b = false;
        this.g = 0;
        this.h = 0L;
        this.i = 0;
        this.j = 0;
        this.k = 0.0;
        this.l = null;
        this.m = 0;
        this.n = new int[16];
        this.p = null;
        this.s = false;
        this.t = false;
        this.u = true;
        this.v = null;
        this.w = new byte[16];
        this.aa = new byte[256];
        this.ah = false;
        this.ac = n * 8000;
        this.ab = new byte[this.ac];
        this.c = c;
        this.d = d;
        this.r = r;
        this.q = q;
        this.e = e;
        this.f = f;
        if (s == null) {
            this.s = true;
        }
        this.i = this.a(new Date().getTime(), d, s);
        int i = 0;
        for (int n2 = 0, j = 2; j <= 256; j <<= 1, ++n2) {
            while (i < j) {
                this.aa[i] = (byte)n2;
                ++i;
            }
        }
    }
    
    public void a(final boolean u, final boolean t, final String v) {
        this.u = u;
        this.t = t;
        this.v = v;
    }
    
    public String a() {
        return this.v;
    }
    
    public int b() {
        if (this.p != null) {
            return this.p.n;
        }
        return 0;
    }
    
    public int c() {
        return (int)this.h * 1000;
    }
    
    public int d() {
        if (this.p == null || this.p.e == Long.MAX_VALUE) {
            return this.j * 1000;
        }
        final int n = this.p.d + (int)(System.currentTimeMillis() - this.p.e) * 8;
        if (n > this.p.a) {
            return this.j * 1000 + this.p.a / 8;
        }
        return this.j * 1000 + n / 8;
    }
    
    public int e() {
        if (this.p != null && this.p.n > 0) {
            return (int)(8000.0 * this.g / this.p.n);
        }
        return -1;
    }
    
    public String f() {
        if (this.a == null) {
            return zkmToString("\u00011p\f");
        }
        if (this.p == null) {
            return zkmToString("\u0001;u\u001dK\t9u\u0013K\u00062");
        }
        if (!this.ah) {
            return zkmToString("\u00189}\u0010K\u00062");
        }
        if (this.p.a == 0) {
            if (this.i == 1) {
                return zkmToString("\u001a0}\r[");
            }
            return zkmToString("\u0001;j\bN\u00011<\u0002G\u0011");
        }
        else {
            if (this.k < 0.9999) {
                return zkmToString("\n z\u000fG\u001a<r\u000e");
            }
            return zkmToString("\u00184i\u001aG\f");
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
        this.ag = (b ? 1 : 0);
        this.af = (b ? 1 : 0);
        final boolean b2 = false;
        this.ae = (b2 ? 1 : 0);
        this.ad = (b2 ? 1 : 0);
        try {
            do {
                if (this.v != null && !this.u && (this.q || this.t) && !this.c.endsWith("." + this.v)) {
                    this.c = this.c.substring(0, this.c.lastIndexOf(".") + 1) + this.v;
                }
                if (this.j > 0) {
                    final URLConnection openConnection = new URL(this.d, this.c + "i").openConnection();
                    final byte[] array = new byte[2];
                    final InputStream inputStream = openConnection.getInputStream();
                    inputStream.read(this.w);
                    int n = 16;
                    if (this.w[0] != 68 || this.w[1] != 78 || this.w[2] != 89) {
                        this.j = 0;
                        this.y = 0;
                    }
                    else {
                        this.y = ((this.w[14] << 8 & 0xFF00) | (this.w[15] & 0xFF));
                        this.x = new int[this.y];
                        for (int i = 0; i < this.y; ++i) {
                            inputStream.read(array);
                            this.x[i] = n;
                            n += ((array[1] << 8 & 0xFF00) | (array[0] & 0xFF));
                        }
                    }
                    inputStream.close();
                    if (this.j >= this.y) {
                        this.j = this.y - 1;
                    }
                }
                final URL url = new URL(this.d, this.c);
                if (this.j > 0 && this.j < this.y) {
                    System.out.println(zkmToString("89}\u0010\u0002") + this.j + "/" + this.y + zkmToString("H\u0015<") + this.x[this.j] + zkmToString("H\u000e") + this.c + "]");
                    final String host = url.getHost();
                    int port = url.getPort();
                    if (port == -1) {
                        port = 80;
                    }
                    if (host.length() > 0) {
                        final Socket socket = new Socket(host, port);
                        final String string = zkmToString("/\u0010HI") + url.getFile() + zkmToString("H\u001dH=rGd2X/b") + zkmToString(":4r\u000eGRu~\u0010V\r&!") + this.x[this.j] + zkmToString("EX\u0016") + zkmToString(" :o\u001d\u0018H") + url.getHost() + zkmToString("e_") + zkmToString("+:r\u0007G\u000b!u\u0006LRu_\u0005M\u001b0\u0011c") + zkmToString("e_\u0011c");
                        this.o = socket.getInputStream();
                        final OutputStream outputStream = socket.getOutputStream();
                        outputStream.write(string.getBytes());
                        outputStream.flush();
                        int n2 = 0;
                        while (true) {
                            final String a = this.a(this.o);
                            ++n2;
                            final int index = a.toLowerCase().indexOf(zkmToString("\u000b:r\u001dG\u0006!1\u0005G\u00062h\u0001\u0018"));
                            if (a.length() < 3) {
                                break;
                            }
                            if (index < 0) {
                                continue;
                            }
                            this.g = Integer.parseInt(a.substring(index + 16));
                        }
                        this.g += this.x[this.j];
                    }
                    else {
                        final URLConnection openConnection2 = url.openConnection();
                        this.g = openConnection2.getContentLength();
                        this.o = openConnection2.getInputStream();
                        byte[] array2;
                        int j;
                        for (array2 = new byte[1024], j = 0; j + 1024 < this.x[this.j]; j += this.o.read(array2)) {}
                        while (j < this.x[this.j]) {
                            this.o.read();
                            ++j;
                        }
                    }
                }
                else {
                    final URLConnection openConnection3 = url.openConnection();
                    this.g = openConnection3.getContentLength();
                    this.o = openConnection3.getInputStream();
                }
                if (this.g < 0) {
                    i.a(zkmToString("+:i\u0005FH;s\u001d\u0002\u000e<r\r\u0002\t x\u0000MH6p\u0000RHi") + this.c + zkmToString("Vuo\u0019G\u000b<z\u0000G\fuu\u0007\u0002\u001c=yIj<\u0018PIA\u00071y"));
                    this.k();
                    return;
                }
                this.p = new g(this);
                if (this.j > 0 && this.j < this.y) {
                    this.p.a(this.w);
                }
                this.l = new e(false, this.ac / 8000);
                if (this.l.b != null) {
                    this.ab = new byte[this.ac * 2];
                }
                if (this.p.c() && this.p.d()) {
                    final int n3 = this.ac / 2;
                    final int n4 = this.ac - this.p.l;
                    int n5 = 0;
                    Date date2;
                    Date date = date2 = new Date();
                    if (!this.s) {
                        while (this.p.d() && this.p.y < 32000 && date2.getTime() - date.getTime() < 4000L) {
                            if (this.g > 0) {
                                this.k = this.p.y / 32000.0f;
                                date2 = new Date();
                            }
                        }
                        this.k = 1.0;
                        if (this.v == null) {
                            final long n6 = new Date().getTime() - date.getTime();
                            this.h = 8 * this.p.u;
                            if (n6 > 0L) {
                                this.h /= n6;
                            }
                            this.v = ((this.h > 64L) ? zkmToString("[g") : zkmToString("Ze"));
                            if (!this.u && (this.q || this.t) && this.v.compareTo(zkmToString("Ze")) != 0) {
                                this.l.b(this.p);
                                this.p.close();
                                this.run();
                                return;
                            }
                        }
                    }
                    while (this.p.d() && this.p.y < n4 && this.p.n > this.h * 800L) {
                        final Date date3 = new Date();
                        if (!date3.equals(date)) {
                            this.h = 8 * this.p.u / (date3.getTime() - date.getTime());
                        }
                        this.k = this.p.y / n4;
                    }
                    this.k = 1.0;
                    if (!l()) {
                        this.k();
                        return;
                    }
                    if (this.l.b != null) {
                        this.l.a(this.p);
                    }
                    else {
                        AudioPlayer.player.start(this.p);
                    }
                    this.m = 0;
                    new Date().getTime();
                    final double n7 = this.p.n / 64000.0;
                    while (this.m == 0 && this.p.d()) {
                        while (this.m == 0 && this.l.a(this.af) > n3) {
                            this.p.b();
                            Thread.currentThread();
                            Thread.sleep(10L);
                        }
                        if (n5 == 0) {
                            if (this.af < n3) {
                                n5 = this.p.u;
                                date = new Date();
                            }
                        }
                        else if (this.af >= n3) {
                            n5 = this.p.u;
                            date = new Date();
                        }
                        if (this.af < 8000 && this.g > 0) {
                            final double n8 = (this.p.u - n5) / (new Date().getTime() - date.getTime());
                            final double n9 = (this.g - this.p.u) * 8.0 / this.p.n;
                            final double n10 = (this.g - this.p.u) / n8 / 1000.0;
                            if (n10 <= n9) {
                                continue;
                            }
                            this.ah = true;
                            for (int n11 = (int)((n10 - n9) * 8000.0), n12 = 0; n12 < n11 && this.af < n4; n12 += this.p.l, this.k = n12 / n11) {
                                if (!this.p.d()) {
                                    break;
                                }
                                this.h = 8 * (this.p.u - n5) / (new Date().getTime() - date.getTime());
                                if (this.p.n <= this.h * 1000L) {
                                    break;
                                }
                            }
                            this.k = 1.0;
                            n5 = 0;
                            this.ah = false;
                        }
                    }
                    this.ag = this.p.y;
                    while (this.m == 0 && (this.af > 0 || this.ah || this.p.d + (System.currentTimeMillis() - this.p.e) * 8L < this.p.a)) {
                        this.p.b();
                        Thread.sleep(50L);
                    }
                    if (this.r && this.ag <= this.ac) {
                        this.ac = this.ag;
                        final boolean b3 = false;
                        this.ae = (b3 ? 1 : 0);
                        this.ad = (b3 ? 1 : 0);
                        while (this.m == 0) {
                            this.af = this.ag;
                            if (this.l.b != null && this.l.b.b() < 8000) {
                                this.p.b();
                            }
                            Thread.sleep(200L);
                        }
                    }
                    this.p.close();
                    this.l.b(this.p);
                }
                this.j = 0;
            } while (this.m == 0 && this.r);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        catch (MalformedURLException ex2) {
            i.a(zkmToString("<=yIC\u001d1u\u0006\u0002\u000b9u\u0019\u0002\u0001&<\fK\u001c=y\u001b\u0002\u001c'i\u0007A\t!y\r\u0002\u0007'<\u001dJ\ruo\u001dP\r4qIU\t&<\u0000L\u001c0n\u001bW\u0018!y\r"));
            ex2.printStackTrace();
        }
        catch (IOException ex4) {}
        catch (NullPointerException ex3) {
            ex3.printStackTrace();
        }
        this.k();
    }
    
    public synchronized void g() {
        if (this.a == null) {
            this.ah = true;
            (this.a = new Thread(this)).start();
        }
    }
    
    public synchronized void a(final int j) {
        this.j = j;
        this.g();
    }
    
    public synchronized void h() {
        this.g();
        this.ah = false;
    }
    
    public synchronized boolean i() {
        return this.a != null && this.p != null && (this.ah = true);
    }
    
    private synchronized void k() {
        if (this.p != null) {
            if (this.l != null) {
                this.l.b(this.p);
            }
            try {
                this.p.close();
                this.p = null;
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        this.a = null;
    }
    
    public synchronized void j() {
        if (this.a != null) {
            this.j = 0;
            this.a.stop();
        }
        this.k();
    }
    
    public static boolean l() {
        boolean b = false;
        try {
            if (System.getProperty(zkmToString("\u00024j\b\f\u001e0r\rM\u001a")).indexOf(zkmToString(")%l\u0005G")) == -1 && System.getProperty(zkmToString("\u0007&2\u0007C\u00050")).indexOf(zkmToString("%4\u007f")) == -1 && System.getProperty(zkmToString("\u00024j\b\f\u001e0n\u001aK\u0007;")).compareTo(zkmToString("Y{/")) < 0) {
                final h h = new h();
                h.start();
                try {
                    Thread.sleep(250L);
                }
                catch (InterruptedException ex) {}
                if (h != null && h.isAlive()) {
                    h.stop();
                    b = true;
                }
                else {
                    System.out.println(zkmToString(";:i\u0007FH6}\u001bFH;s\u001d\u0002\t#}\u0000N\t7p\f"));
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
        final k k = new k(zkmToString("=a[Zn+cH>q]\u0003%[\u0015#\u0018L-z*\f$3l:\u0010]8j\"\u0013"));
        if (host.length() == 0) {
            this.i = 1;
        }
        else if (s == null) {
            this.i = -1;
        }
        else {
            this.i = 0;
            int n2 = 0;
            final StringTokenizer stringTokenizer = new StringTokenizer(s, zkmToString("RX\u0016`\u0002"));
            while (this.i != 1 && stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                final double a = k.a(nextToken.substring((nextToken.toUpperCase().indexOf("S") == 0) ? 2 : 1), url.toString(), this.n);
                if (this.n[0] == 0 && this.n[1] == 0 && this.n[2] == 0 && this.n[3] == 0) {
                    n2 = 1;
                }
                else {
                    n2 = 2;
                }
                if (Math.abs(a - Math.log(n) - 1398.0) < 9.0E-5) {
                    this.i = 1;
                }
                else {
                    this.i = 0;
                }
            }
            if (this.i == 1 && (n2 == 2 || url.toString().toLowerCase().indexOf(zkmToString("7&\u007f\u001a}")) != -1)) {
                this.i = (this.d.getHost().equals(this.e.getHost()) ? 1 : 0);
                final String lowerCase = this.e.toString().toLowerCase();
                for (int n3 = 0; this.i == 0 && n3 < this.f.size(); ++n3) {
                    if (lowerCase.indexOf((String)this.f.elementAt(n3)) == 0) {
                        this.i = 1;
                    }
                }
                if (this.i == 0) {
                    i.a(zkmToString(")6\u007f\fQ\u001buX\fL\u00010xG/") + this.e + zkmToString("e<oIL\u0007!<\bW\u001c=s\u001bK\u00120xIV\u0007u}\nA\r&oIA\u0007;h\fL\u001cus\u0007\u0002e") + this.d);
                    this.i = 1;
                    final int[] n4 = this.n;
                    final int n5 = 0;
                    final int[] n6 = this.n;
                    final int n7 = 1;
                    final int[] n8 = this.n;
                    final int n9 = 2;
                    final int[] n10 = this.n;
                    final int n11 = 3;
                    final boolean b = true;
                    n8[n9] = (n10[n11] = (b ? 1 : 0));
                    n4[n5] = (n6[n7] = (b ? 1 : 0));
                }
            }
        }
        if (this.i != 1) {
            final int[] n12 = this.n;
            final int n13 = 0;
            final int[] n14 = this.n;
            final int n15 = 1;
            final int[] n16 = this.n;
            final int n17 = 2;
            final int[] n18 = this.n;
            final int n19 = 3;
            final boolean b2 = false;
            n16[n17] = (n18[n19] = (b2 ? 1 : 0));
            n12[n13] = (n14[n15] = (b2 ? 1 : 0));
        }
        return this.i;
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
                    c2 = 'h';
                    break;
                }
                case 1: {
                    c2 = 'U';
                    break;
                }
                case 2: {
                    c2 = '\u001c';
                    break;
                }
                case 3: {
                    c2 = 'i';
                    break;
                }
                default: {
                    c2 = '\"';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
