import javax.sound.sampled.Line;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import java.io.OutputStream;
import java.io.InputStream;
import javax.sound.sampled.SourceDataLine;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ae
{
    public au a;
    public SourceDataLine a;
    public boolean a;
    private I a;
    private I b;
    private D a;
    private p a;
    private r a;
    public int[] a;
    public int[] b;
    public int[] c;
    public int[] d;
    public int[] e;
    public byte[] a;
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public boolean b;
    public boolean c;
    public boolean d;
    public boolean e;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    public boolean f;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;
    public int u;
    public int v;
    public int w;
    public int x;
    public int y;
    public int z;
    public int A;
    public int B;
    public int C;
    public int D;
    public int E;
    public int[] f;
    public int F;
    public int G;
    public int H;
    public int I;
    public int J;
    public int K;
    public int L;
    public int M;
    public int N;
    public int O;
    public int P;
    public int Q;
    public boolean g;
    public boolean h;
    public static double a;
    public static int R;
    public static Class a;
    
    public final void a(final InputStream inputStream) throws Exception {
        this.a.a(inputStream);
        this.b.a(inputStream);
        this.a.a(inputStream);
        this.a.a(inputStream);
        this.a.a(inputStream);
    }
    
    public final void a(final OutputStream outputStream) throws Exception {
        outputStream.write("SND".getBytes());
        aK.a(outputStream, 308);
        this.a.a(outputStream);
        this.b.a(outputStream);
        this.a.a(outputStream);
        this.a.a(outputStream);
        this.a.a(outputStream);
    }
    
    public final void a(final boolean b) {
        if (!this.g || !this.a) {
            return;
        }
        if (b) {
            this.a.stop();
            this.a.flush();
        }
        if (!b && this.a.a.a) {
            this.a.start();
        }
        this.h = this.h;
    }
    
    public ae(final au a) {
        this.a = null;
        this.a = null;
        this.a = false;
        this.c = 2048;
        this.e = 44100;
        this.d = true;
        this.e = false;
        this.i = true;
        this.j = true;
        this.k = true;
        this.l = true;
        this.f = true;
        this.n = 0;
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.A = 0;
        this.B = 0;
        this.C = 0;
        this.D = 0;
        this.g = true;
        this.h = true;
        this.a = a;
        this.a(this.e, false);
        this.a = new byte[this.c * (this.d ? 4 : 2)];
        this.d = 0;
        this.b = false;
        this.b = 2048;
        this.a = new I(this, true);
        this.b = new I(this, false);
        this.a = new D(this);
        this.a = new p(this);
        this.a = new r(this);
        this.E = 256;
        this.a(this.f = new int[] { 80, 170, 100, 150, 128 });
        this.i();
        this.j();
        this.k();
        this.l();
        this.b = false;
        this.a = 4;
    }
    
    public final synchronized void a() {
        if (this.a != null && this.a.isActive()) {
            return;
        }
        try {
            this.d = 0;
            AudioSystem.getMixer(AudioSystem.getMixerInfo()[1]);
            final AudioFormat audioFormat = new AudioFormat(this.e, 16, this.d ? 2 : 1, true, false);
            (this.a = (SourceDataLine)AudioSystem.getLine(new DataLine.Info((ae.a == null) ? (ae.a = a("javax.sound.sampled.SourceDataLine")) : ae.a, audioFormat, this.e))).open(audioFormat);
            this.a.start();
            this.a = true;
            this.h = false;
        }
        catch (Error error) {}
        catch (Exception ex) {}
    }
    
    public final short a(final int n) {
        final int n2 = 0x0 | this.a.a() | this.b.a() << 1 | this.a.a() << 2 | this.a.a() << 3 | this.a.a() << 4 | ((this.c && this.b) ? 1 : 0) << 6 | this.a.b() << 7;
        this.c = false;
        this.a.c = false;
        return (short)n2;
    }
    
    public final void a(final int n, final short n2) {
        if (n >= 16384 && n < 16388) {
            this.a.a(n, n2);
            return;
        }
        if (n >= 16388 && n < 16392) {
            this.b.a(n, n2);
            return;
        }
        if (n >= 16392 && n < 16396) {
            this.a.a(n, n2);
            return;
        }
        if (n >= 16396 && n <= 16399) {
            this.a.a(n, n2);
            return;
        }
        if (n == 16400) {
            this.a.a(n, n2);
            return;
        }
        if (n == 16401) {
            this.a.a(n, n2);
            return;
        }
        if (n == 16402) {
            this.a.a(n, n2);
            return;
        }
        if (n == 16403) {
            this.a.a(n, n2);
            return;
        }
        if (n == 16405) {
            this.a((int)n2);
            if (n2 != 0 && this.b > 0) {
                this.e = true;
            }
            this.a.a(n, n2);
            return;
        }
        if (n == 16407) {
            this.h = (n2 >> 7 & 0x1);
            this.f = 0;
            this.c = false;
            if ((n2 >> 6 & 0x1) == 0x0) {
                this.b = true;
            }
            else {
                this.b = false;
            }
            if (this.h == 0) {
                this.a = 4;
                this.g = 4;
                return;
            }
            this.a = 5;
            this.g = 0;
            this.c();
        }
    }
    
    public final void b() {
        if (this.h == 0) {
            this.g = 4;
            return;
        }
        this.g = 0;
    }
    
    public final void a(final int n) {
        this.a.a(this.i && (n & 0x1) != 0x0);
        this.b.a(this.j && (n & 0x2) != 0x0);
        this.a.a(this.k && (n & 0x4) != 0x0);
        this.a.a(this.l && (n & 0x8) != 0x0);
        this.a.a(this.f && (n & 0x10) != 0x0);
    }
    
    public final void b(int n) {
        if (this.b > 0 && this.e) {
            this.b -= n;
            if (this.b <= 0) {
                this.e = false;
            }
            return;
        }
        n += this.P;
        this.Q = this.k - this.i;
        if (n << 10 > this.Q) {
            this.P = (n << 10) - this.Q >> 10;
            n -= this.P;
        }
        else {
            this.P = 0;
        }
        if (this.a.a) {
            final r a = this.a;
            a.i -= n << 3;
            while (this.a.i <= 0 && this.a.b > 0) {
                final r a2 = this.a;
                a2.i += this.a.b;
                this.a.a();
            }
        }
        if (this.a.b > 0) {
            final D a3 = this.a;
            a3.a -= n;
            while (this.a.a <= 0) {
                final D a4 = this.a;
                a4.a += this.a.b + 1;
                if (this.a.e > 0 && this.a.d > 0) {
                    final D a5 = this.a;
                    ++a5.c;
                    final D a6 = this.a;
                    a6.c &= 0x1F;
                    if (!this.a.a) {
                        continue;
                    }
                    if (this.a.c >= 16) {
                        this.a.g = (this.a.c & 0xF);
                    }
                    else {
                        this.a.g = 15 - (this.a.c & 0xF);
                    }
                    final D a7 = this.a;
                    a7.g <<= 4;
                }
            }
        }
        final I a8 = this.a;
        a8.a -= n;
        if (this.a.a <= 0) {
            final I a9 = this.a;
            a9.a += this.a.b + 1 << 1;
            final I a10 = this.a;
            ++a10.d;
            final I a11 = this.a;
            a11.d &= 0x7;
            this.a.d();
        }
        final I b = this.b;
        b.a -= n;
        if (this.b.a <= 0) {
            final I b2 = this.b;
            b2.a += this.b.b + 1 << 1;
            final I b3 = this.b;
            ++b3.d;
            final I b4 = this.b;
            b4.d &= 0x7;
            this.b.d();
        }
        int n2 = n;
        if (this.a.b - n2 > 0) {
            final p a12 = this.a;
            a12.b -= n2;
            final p a13 = this.a;
            a13.b += n2;
            final p a14 = this.a;
            a14.a += n2 * this.a.k;
        }
        else {
            while (n2-- > 0) {
                final p a15 = this.a;
                final int b5 = a15.b - 1;
                a15.b = b5;
                if (b5 <= 0 && this.a.c > 0) {
                    final p a16 = this.a;
                    a16.h <<= 1;
                    this.a.l = ((this.a.h << ((this.a.j == 0) ? 1 : 6) ^ this.a.h) & 0x8000);
                    if (this.a.l != 0) {
                        final p a17 = this.a;
                        a17.h |= 0x1;
                        this.a.i = 0;
                        this.a.k = 0;
                    }
                    else {
                        this.a.i = 1;
                        if (this.a.a && this.a.a > 0) {
                            this.a.k = this.a.g;
                        }
                        else {
                            this.a.k = 0;
                        }
                    }
                    final p a18 = this.a;
                    a18.b += this.a.c;
                }
                final p a19 = this.a;
                a19.a += this.a.k;
                final p a20 = this.a;
                ++a20.b;
            }
        }
        if (this.b && this.c) {
            this.a.a.f();
        }
        this.f += n << 1;
        if (this.f >= this.j) {
            this.f -= this.j;
            this.c();
        }
        this.c(n);
        this.i += n << 10;
        if (this.i >= this.k) {
            this.d();
            this.i -= this.k;
        }
    }
    
    private void c(final int n) {
        if (this.a.b) {
            this.n = (this.a.a << 4) / (this.a.b + 1);
            if (this.n > 16) {
                this.n = 16;
            }
            if (this.a.c >= 16) {
                this.n = 16 - this.n;
            }
            this.n += this.a.g;
        }
        if (n == 2) {
            this.q += this.n << 1;
            this.s += this.a.m << 1;
            this.o += this.a.o << 1;
            this.p += this.b.o << 1;
            this.t += 2;
            return;
        }
        if (n == 4) {
            this.q += this.n << 2;
            this.s += this.a.m << 2;
            this.o += this.a.o << 2;
            this.p += this.b.o << 2;
            this.t += 4;
            return;
        }
        this.q += n * this.n;
        this.s += n * this.a.m;
        this.o += n * this.a.o;
        this.p += n * this.b.o;
        this.t += n;
    }
    
    public final void c() {
        ++this.g;
        if (this.g >= this.a) {
            this.g = 0;
        }
        if (this.g == 1 || this.g == 3) {
            this.a.a();
            this.a.a();
            this.b.a();
            this.a.a();
            this.a.c();
            this.b.c();
        }
        if (this.g >= 0 && this.g < 4) {
            this.a.b();
            this.b.b();
            this.a.b();
            this.a.b();
        }
        if (this.g == 3 && this.h == 0) {
            this.c = true;
        }
    }
    
    public final void d() {
        if (this.t > 0) {
            this.o <<= 4;
            this.o /= this.t;
            this.p <<= 4;
            this.p /= this.t;
            this.q /= this.t;
            this.s <<= 4;
            this.s /= this.t;
            this.t = 0;
        }
        else {
            this.o = this.a.o << 4;
            this.p = this.b.o << 4;
            this.q = this.a.g;
            this.s = this.a.m << 4;
        }
        this.r = (int)((this.a.a << 4) / this.a.b);
        this.a.a = this.r >> 4;
        this.a.b = 1L;
        if (this.d) {
            this.u = this.o * this.F + this.p * this.G >> 8;
            this.v = 3 * this.q * this.H + (this.r << 1) * this.I + this.s * this.J >> 8;
            if (this.u >= this.d.length) {
                this.u = this.d.length - 1;
            }
            if (this.v >= this.e.length) {
                this.v = this.e.length - 1;
            }
            this.l = this.d[this.u] + this.e[this.v] - this.D;
            this.u = this.o * this.K + this.p * this.L >> 8;
            this.v = 3 * this.q * this.M + (this.r << 1) * this.N + this.s * this.O >> 8;
            if (this.u >= this.d.length) {
                this.u = this.d.length - 1;
            }
            if (this.v >= this.e.length) {
                this.v = this.e.length - 1;
            }
            this.m = this.d[this.u] + this.e[this.v] - this.D;
        }
        else {
            this.u = this.o + this.p;
            this.v = 3 * this.q + 2 * this.r + this.s;
            if (this.u >= this.d.length) {
                this.u = this.d.length - 1;
            }
            if (this.v >= this.e.length) {
                this.v = this.e.length - 1;
            }
            this.l = 3 * (this.d[this.u] + this.e[this.v] - this.D);
            this.l >>= 2;
        }
        this.A = this.l - this.w;
        this.w += this.A;
        this.y += this.A - (this.y >> 10);
        this.l = this.y;
        if (this.d) {
            this.B = this.m - this.x;
            this.x += this.B;
            this.z += this.B - (this.z >> 10);
            this.m = this.z;
            if (this.d + 4 < this.a.length) {
                this.a[this.d++] = (byte)(this.l & 0xFF);
                this.a[this.d++] = (byte)(this.l >> 8 & 0xFF);
                this.a[this.d++] = (byte)(this.m & 0xFF);
                this.a[this.d++] = (byte)(this.m >> 8 & 0xFF);
            }
        }
        else if (this.d + 2 < this.a.length) {
            this.a[this.d++] = (byte)(this.l & 0xFF);
            this.a[this.d++] = (byte)(this.l >> 8 & 0xFF);
        }
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.s = 0;
    }
    
    public final void e() {
        if (!this.g || !this.a) {
            return;
        }
        if (this.a == null) {
            return;
        }
        this.d -= this.d % (this.d ? 4 : 2);
        this.a.write(this.a, 0, this.d);
        this.d = 0;
    }
    
    public final void f() {
        if (!this.a) {
            return;
        }
        if (this.a == null) {
            return;
        }
        if (this.a != null && this.a.isOpen() && this.a.isActive()) {
            this.a.close();
        }
        this.a = null;
    }
    
    public final void g() {
        this.a(this.e, false);
        this.a(0);
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.b = 2048;
        this.b = false;
        this.e = false;
        this.b();
        this.a.e();
        this.b.e();
        this.a.d();
        this.a.d();
        this.a.b();
        this.d = 0;
        this.t = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.b = false;
        this.a = 4;
        this.l = 0;
        this.m = 0;
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.A = 0;
        this.B = 0;
        this.a.flush();
    }
    
    public final int a(final int n) {
        return this.a[n >> 3];
    }
    
    public final int b(final int n) {
        if (n >= 0 && n < 16) {
            return this.b[n];
        }
        return 0;
    }
    
    public final int c(final int n) {
        if (n >= 0 && n < 16) {
            return this.c[n];
        }
        return 0;
    }
    
    public final synchronized void a(final int e, final boolean b) {
        this.e = e;
        this.k = (int)(1024.0 * ae.a * ae.R / (this.e * 60.0));
        this.j = (int)(14915.0 * ae.R / 60.0);
        this.i = 0;
        this.d = 0;
        if (b) {
            this.f();
            this.a();
        }
    }
    
    public final void a(final int[] array) {
        for (int i = 0; i < 5; ++i) {
            this.f[i] = array[i];
        }
        this.h();
    }
    
    public final void h() {
        this.F = this.f[0] * this.E >> 8;
        this.G = this.f[1] * this.E >> 8;
        this.H = this.f[2] * this.E >> 8;
        this.I = this.f[3] * this.E >> 8;
        this.J = this.f[4] * this.E >> 8;
        this.K = this.E - this.F;
        this.L = this.E - this.G;
        this.M = this.E - this.H;
        this.N = this.E - this.I;
        this.O = this.E - this.J;
    }
    
    public final void i() {
        this.a = new int[] { 10, 254, 20, 2, 40, 4, 80, 6, 160, 8, 60, 10, 14, 12, 26, 14, 12, 16, 24, 18, 48, 20, 96, 22, 192, 24, 72, 26, 16, 28, 32, 30 };
    }
    
    public final void j() {
        (this.b = new int[16])[0] = 3424;
        this.b[1] = 3040;
        this.b[2] = 2720;
        this.b[3] = 2560;
        this.b[4] = 2288;
        this.b[5] = 2032;
        this.b[6] = 1808;
        this.b[7] = 1712;
        this.b[8] = 1520;
        this.b[9] = 1280;
        this.b[10] = 1136;
        this.b[11] = 1024;
        this.b[12] = 848;
        this.b[13] = 672;
        this.b[14] = 576;
        this.b[15] = 432;
    }
    
    public final void k() {
        (this.c = new int[16])[0] = 4;
        this.c[1] = 8;
        this.c[2] = 16;
        this.c[3] = 32;
        this.c[4] = 64;
        this.c[5] = 96;
        this.c[6] = 128;
        this.c[7] = 160;
        this.c[8] = 202;
        this.c[9] = 254;
        this.c[10] = 380;
        this.c[11] = 508;
        this.c[12] = 762;
        this.c[13] = 1016;
        this.c[14] = 2034;
        this.c[15] = 4068;
    }
    
    public final void l() {
        this.d = new int[512];
        this.e = new int[3264];
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < 512; ++i) {
            final int n3 = (int)(95.52 / (8128.0 / (i / 16.0) + 100.0) * 0.98411 * 50000.0);
            if ((this.d[i] = n3) > n) {
                n = n3;
            }
        }
        for (int j = 0; j < 3264; ++j) {
            final int n4 = (int)(163.67 / (24329.0 / (j / 16.0) + 100.0) * 0.98411 * 50000.0);
            if ((this.e[j] = n4) > n2) {
                n2 = n4;
            }
        }
        this.C = n + n2;
        this.D = this.C / 2;
    }
    
    public static final Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        ae.a = 1789772.5;
        ae.R = 60;
    }
}
