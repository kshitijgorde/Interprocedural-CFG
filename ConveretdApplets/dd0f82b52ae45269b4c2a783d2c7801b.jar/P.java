import javax.sound.sampled.Mixer;
import javax.sound.sampled.Line;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

// 
// Decompiled by Procyon v0.5.30
// 

public final class P
{
    V a;
    SourceDataLine b;
    A c;
    A d;
    w e;
    o f;
    q g;
    private int[] p;
    int[] h;
    int[] i;
    private int[] q;
    private int[] r;
    byte[] j;
    private int s;
    private int t;
    private int u;
    int k;
    private int v;
    boolean l;
    boolean m;
    boolean n;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;
    private boolean A;
    public boolean o;
    private int B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G;
    private int H;
    private int I;
    private int J;
    private int K;
    private int L;
    private int M;
    private int N;
    private int O;
    private int P;
    private int Q;
    private int R;
    private int S;
    private int T;
    private int U;
    private int V;
    private int W;
    private int X;
    private int Y;
    private int Z;
    private int aa;
    private int[] ab;
    private int ac;
    private int ad;
    private int ae;
    private int af;
    private int ag;
    private int ah;
    private int ai;
    private int aj;
    private int ak;
    private int al;
    private int am;
    private int an;
    
    public P(final V a) {
        this.u = 2048;
        this.v = 44100;
        this.n = true;
        this.w = false;
        this.x = true;
        this.y = true;
        this.z = true;
        this.A = true;
        this.o = true;
        this.J = 0;
        this.S = 0;
        this.T = 0;
        this.U = 0;
        this.V = 0;
        this.W = 0;
        this.X = 0;
        this.Y = 0;
        this.Z = 0;
        this.a = a;
        this.a(this.v, false);
        this.j = new byte[this.u * (this.n ? 4 : 2)];
        this.k = 0;
        this.l = false;
        this.t = 2048;
        this.c = new A(this, true);
        this.d = new A(this, false);
        this.e = new w(this);
        this.f = new o(this);
        this.g = new q(this);
        this.aa = 256;
        this.a(this.ab = new int[] { 80, 170, 100, 150, 128 });
        this.p = new int[] { 10, 254, 20, 2, 40, 4, 80, 6, 160, 8, 60, 10, 14, 12, 26, 14, 12, 16, 24, 18, 48, 20, 96, 22, 192, 24, 72, 26, 16, 28, 32, 30 };
        (this.h = new int[16])[0] = 3424;
        this.h[1] = 3040;
        this.h[2] = 2720;
        this.h[3] = 2560;
        this.h[4] = 2288;
        this.h[5] = 2032;
        this.h[6] = 1808;
        this.h[7] = 1712;
        this.h[8] = 1520;
        this.h[9] = 1280;
        this.h[10] = 1136;
        this.h[11] = 1024;
        this.h[12] = 848;
        this.h[13] = 672;
        this.h[14] = 576;
        this.h[15] = 432;
        (this.i = new int[16])[0] = 4;
        this.i[1] = 8;
        this.i[2] = 16;
        this.i[3] = 32;
        this.i[4] = 64;
        this.i[5] = 96;
        this.i[6] = 128;
        this.i[7] = 160;
        this.i[8] = 202;
        this.i[9] = 254;
        this.i[10] = 380;
        this.i[11] = 508;
        this.i[12] = 762;
        this.i[13] = 1016;
        this.i[14] = 2034;
        this.i[15] = 4068;
        this.f();
        this.l = false;
        this.s = 4;
    }
    
    public final synchronized void a() {
        if (this.b != null && this.b.isActive()) {
            return;
        }
        this.k = 0;
        final Mixer.Info[] mixerInfo;
        if ((mixerInfo = AudioSystem.getMixerInfo()) == null || mixerInfo.length == 0) {
            X.h = false;
            return;
        }
        AudioSystem.getMixer(mixerInfo[1]);
        final AudioFormat audioFormat = new AudioFormat(this.v, 16, this.n ? 2 : 1, true, false);
        final DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat, this.v);
        try {
            (this.b = (SourceDataLine)AudioSystem.getLine(info)).open(audioFormat);
            this.b.start();
        }
        catch (Exception ex) {}
    }
    
    public final void a(final int n, final short n2) {
        if (n >= 16384 && n < 16388) {
            this.c.a(n, n2);
            return;
        }
        if (n >= 16388 && n < 16392) {
            this.d.a(n, n2);
            return;
        }
        if (n >= 16392 && n < 16396) {
            final w e = this.e;
            if (n == 16392) {
                e.f = ((n2 & 0x80) != 0x0);
                e.l = (n2 & 0x7F);
                e.d = !e.f;
            }
            else if (n == 16394) {
                final w w = e;
                w.h &= 0x700;
                final w w2 = e;
                w2.h |= n2;
            }
            else if (n == 16395) {
                final w w3 = e;
                w3.h &= 0xFF;
                final w w4 = e;
                w4.h |= (n2 & 0x7) << 8;
                e.j = e.a.b(n2 & 0xF8);
                e.e = true;
            }
            e.a();
            return;
        }
        if (n >= 16396 && n <= 16399) {
            final o f = this.f;
            if (n == 16396) {
                f.c = ((n2 & 0x10) != 0x0);
                f.j = (n2 & 0xF);
                f.d = ((n2 & 0x20) != 0x0);
                f.e = ((n2 & 0x20) == 0x0);
                f.m = (f.c ? f.j : f.l);
            }
            else {
                if (n != 16398) {
                    if (n == 16399) {
                        f.g = f.a.b(n2 & 0xF8);
                        f.f = true;
                    }
                    return;
                }
                final o o = f;
                final P a = f.a;
                final short n3 = (short)(n2 & 0xF);
                final P p2 = a;
                o.i = ((n3 >= 0 && n3 < 16) ? p2.i[n3] : 0);
                f.p = n2 >> 7;
            }
        }
        else {
            if (n == 16400) {
                this.g.a(n, n2);
                return;
            }
            if (n == 16401) {
                this.g.a(n, n2);
                return;
            }
            if (n == 16402) {
                this.g.a(n, n2);
                return;
            }
            if (n == 16403) {
                this.g.a(n, n2);
                return;
            }
            if (n == 16405) {
                this.d(n2);
                if (n2 != 0 && this.t > 0) {
                    this.w = true;
                }
                this.g.a(n, n2);
                return;
            }
            if (n == 16407) {
                this.D = (n2 >> 7 & 0x1);
                this.B = 0;
                this.m = false;
                if ((n2 >> 6 & 0x1) == 0x0) {
                    this.l = true;
                }
                else {
                    this.l = false;
                }
                if (this.D == 0) {
                    this.s = 4;
                    this.C = 4;
                    return;
                }
                this.s = 5;
                this.C = 0;
                this.e();
            }
        }
    }
    
    private void d(final int n) {
        this.c.a(this.x && (n & 0x1) != 0x0);
        this.d.a(this.y && (n & 0x2) != 0x0);
        final w e = this.e;
        final boolean b = this.z && (n & 0x4) != 0x0;
        final w w = e;
        if (!(e.b = b)) {
            w.j = 0;
        }
        w.a();
        final o f = this.f;
        final boolean b2 = this.A && (n & 0x8) != 0x0;
        final o o = f;
        if (!(f.b = b2)) {
            o.g = 0;
        }
        o.a();
        final q g = this.g;
        final boolean b3 = this.o && (n & 0x10) != 0x0;
        final q q = g;
        if (!g.b && b3) {
            q.k = q.j;
        }
        q.b = b3;
    }
    
    public final void a(int n) {
        if (this.t > 0 && this.w) {
            this.t -= n;
            if (this.t <= 0) {
                this.w = false;
            }
            return;
        }
        n += this.am;
        this.an = this.G - this.E;
        if (n << 10 > this.an) {
            this.am = (n << 10) - this.an >> 10;
            n -= this.am;
        }
        else {
            this.am = 0;
        }
        if (this.g.b) {
            final q g = this.g;
            g.l -= n << 3;
            while (this.g.l <= 0 && this.g.e > 0) {
                final q g2 = this.g;
                g2.l += this.g.e;
                this.g.a();
            }
        }
        if (this.e.h > 0) {
            final w e = this.e;
            e.g -= n;
            while (this.e.g <= 0) {
                final w e2 = this.e;
                e2.g += this.e.h + 1;
                if (this.e.k > 0 && this.e.j > 0) {
                    final w e3 = this.e;
                    ++e3.i;
                    final w e4 = this.e;
                    e4.i &= 0x1F;
                    if (!this.e.b) {
                        continue;
                    }
                    if (this.e.i >= 16) {
                        this.e.m = (this.e.i & 0xF);
                    }
                    else {
                        this.e.m = 15 - (this.e.i & 0xF);
                    }
                    final w e5 = this.e;
                    e5.m <<= 4;
                }
            }
        }
        final A c = this.c;
        c.a -= n;
        if (this.c.a <= 0) {
            final A c2 = this.c;
            c2.a += this.c.b + 1 << 1;
            final A c3 = this.c;
            ++c3.c;
            final A c4 = this.c;
            c4.c &= 0x7;
            this.c.d();
        }
        final A d = this.d;
        d.a -= n;
        if (this.d.a <= 0) {
            final A d2 = this.d;
            d2.a += this.d.b + 1 << 1;
            final A d3 = this.d;
            ++d3.c;
            final A d4 = this.d;
            d4.c &= 0x7;
            this.d.d();
        }
        int n2 = n;
        if (this.f.h - n2 > 0) {
            final o f = this.f;
            f.h -= n2;
            final o f2 = this.f;
            f2.s += n2;
            final o f3 = this.f;
            f3.r += n2 * this.f.q;
        }
        else {
            while (n2-- > 0) {
                final o f4 = this.f;
                final int h = f4.h - 1;
                f4.h = h;
                if (h <= 0 && this.f.i > 0) {
                    final o f5 = this.f;
                    f5.n <<= 1;
                    this.f.t = ((this.f.n << ((this.f.p == 0) ? 1 : 6) ^ this.f.n) & 0x8000);
                    Label_0754: {
                        if (this.f.t != 0) {
                            final o f6 = this.f;
                            f6.n |= 0x1;
                            this.f.o = 0;
                        }
                        else {
                            this.f.o = 1;
                            if (this.f.b && this.f.g > 0) {
                                this.f.q = this.f.m;
                                break Label_0754;
                            }
                        }
                        this.f.q = 0;
                    }
                    final o f7 = this.f;
                    f7.h += this.f.i;
                }
                final o f8 = this.f;
                f8.r += this.f.q;
                final o f9 = this.f;
                ++f9.s;
            }
        }
        if (this.l && this.m) {
            this.a.b.a(0);
        }
        this.B += n << 1;
        if (this.B >= this.F) {
            this.B -= this.F;
            this.e();
        }
        final int n3 = n;
        if (this.e.c) {
            this.J = (this.e.g << 4) / (this.e.h + 1);
            if (this.J > 16) {
                this.J = 16;
            }
            if (this.e.i >= 16) {
                this.J = 16 - this.J;
            }
            this.J += this.e.m;
        }
        if (n3 == 2) {
            this.M += this.J << 1;
            this.O += this.g.m << 1;
            this.K += this.c.d << 1;
            this.L += this.d.d << 1;
            this.P += 2;
        }
        else if (n3 == 4) {
            this.M += this.J << 2;
            this.O += this.g.m << 2;
            this.K += this.c.d << 2;
            this.L += this.d.d << 2;
            this.P += 4;
        }
        else {
            this.M += n3 * this.J;
            this.O += n3 * this.g.m;
            this.K += n3 * this.c.d;
            this.L += n3 * this.d.d;
            this.P += n3;
        }
        this.E += n << 10;
        if (this.E >= this.G) {
            if (this.P > 0) {
                this.K <<= 4;
                this.K /= this.P;
                this.L <<= 4;
                this.L /= this.P;
                this.M /= this.P;
                this.O <<= 4;
                this.O /= this.P;
                this.P = 0;
            }
            else {
                this.K = this.c.d << 4;
                this.L = this.d.d << 4;
                this.M = this.e.m;
                this.O = this.g.m << 4;
            }
            this.N = (int)((this.f.r << 4) / this.f.s);
            this.f.r = this.N >> 4;
            this.f.s = 1L;
            if (this.n) {
                this.Q = this.K * this.ac + this.L * this.ad >> 8;
                this.R = 3 * this.M * this.ae + (this.N << 1) * this.af + this.O * this.ag >> 8;
                if (this.Q >= this.q.length) {
                    this.Q = this.q.length - 1;
                }
                if (this.R >= this.r.length) {
                    this.R = this.r.length - 1;
                }
                this.H = this.q[this.Q] + this.r[this.R] - this.Z;
                this.Q = this.K * this.ah + this.L * this.ai >> 8;
                this.R = 3 * this.M * this.aj + (this.N << 1) * this.ak + this.O * this.al >> 8;
                if (this.Q >= this.q.length) {
                    this.Q = this.q.length - 1;
                }
                if (this.R >= this.r.length) {
                    this.R = this.r.length - 1;
                }
                this.I = this.q[this.Q] + this.r[this.R] - this.Z;
            }
            else {
                this.Q = this.K + this.L;
                this.R = 3 * this.M + 2 * this.N + this.O;
                if (this.Q >= this.q.length) {
                    this.Q = this.q.length - 1;
                }
                if (this.R >= this.r.length) {
                    this.R = this.r.length - 1;
                }
                this.H = 3 * (this.q[this.Q] + this.r[this.R] - this.Z);
                this.H >>= 2;
            }
            this.W = this.H - this.S;
            this.S += this.W;
            this.U += this.W - (this.U >> 10);
            this.H = this.U;
            if (this.n) {
                this.X = this.I - this.T;
                this.T += this.X;
                this.V += this.X - (this.V >> 10);
                this.I = this.V;
                if (this.k + 4 < this.j.length) {
                    this.j[this.k++] = (byte)this.H;
                    this.j[this.k++] = (byte)(this.H >> 8);
                    this.j[this.k++] = (byte)this.I;
                    this.j[this.k++] = (byte)(this.I >> 8);
                }
            }
            else if (this.k + 2 < this.j.length) {
                this.j[this.k++] = (byte)this.H;
                this.j[this.k++] = (byte)(this.H >> 8);
            }
            this.K = 0;
            this.L = 0;
            this.M = 0;
            this.O = 0;
            this.E -= this.G;
        }
    }
    
    private void e() {
        ++this.C;
        if (this.C >= this.s) {
            this.C = 0;
        }
        if (this.C == 1 || this.C == 3) {
            final w e;
            if ((e = this.e).d && e.j > 0) {
                final w w = e;
                --w.j;
                if (e.j == 0) {
                    e.a();
                }
            }
            this.c.a();
            this.d.a();
            final o f;
            if ((f = this.f).e && f.g > 0) {
                final o o = f;
                --o.g;
                if (f.g == 0) {
                    f.a();
                }
            }
            this.c.c();
            this.d.c();
        }
        if (this.C >= 0 && this.C < 4) {
            this.c.b();
            this.d.b();
            final o f2;
            Label_0270: {
                o o2;
                int l;
                if ((f2 = this.f).f) {
                    f2.f = false;
                    f2.k = f2.j + 1;
                    o2 = f2;
                    l = 15;
                }
                else {
                    final o o3 = f2;
                    if (--o3.k > 0) {
                        break Label_0270;
                    }
                    f2.k = f2.j + 1;
                    if (f2.l > 0) {
                        l = (o2 = f2).l - 1;
                    }
                    else {
                        o2 = f2;
                        l = (f2.d ? 15 : 0);
                    }
                }
                o2.l = l;
            }
            f2.m = (f2.c ? f2.j : f2.l);
            f2.a();
            final w e2;
            if ((e2 = this.e).e) {
                e2.k = e2.l;
                e2.a();
            }
            else if (e2.k > 0) {
                final w w2 = e2;
                --w2.k;
                e2.a();
            }
            if (!e2.f) {
                e2.e = false;
            }
        }
        if (this.C == 3 && this.D == 0) {
            this.m = true;
        }
    }
    
    public final void b() {
        if (this.b == null) {
            return;
        }
        if (this.b != null && this.b.isOpen() && this.b.isActive()) {
            this.b.close();
        }
        this.b = null;
    }
    
    public final void c() {
        this.a(this.v, false);
        this.d(0);
        this.B = 0;
        this.C = 0;
        this.D = 0;
        this.t = 2048;
        this.l = false;
        this.w = false;
        if (this.D == 0) {
            this.C = 4;
        }
        else {
            this.C = 0;
        }
        this.c.f();
        this.d.f();
        final w e;
        (e = this.e).g = 0;
        e.h = 0;
        e.i = 0;
        e.b = false;
        e.c = false;
        e.j = 0;
        e.d = false;
        e.k = 0;
        e.l = 0;
        e.e = true;
        e.f = false;
        e.m = 15;
        final o f;
        (f = this.f).h = 0;
        f.i = 0;
        f.b = false;
        f.g = 0;
        f.e = false;
        f.c = false;
        f.d = false;
        f.j = 0;
        f.k = 0;
        f.l = 0;
        f.m = 0;
        f.n = 1;
        f.o = 0;
        f.p = 0;
        f.q = 0;
        f.t = 0;
        final q g;
        (g = this.g).b = false;
        g.c = false;
        g.d = 0;
        g.e = 0;
        g.f = 0;
        g.g = 0;
        g.h = 0;
        g.i = 0;
        g.j = 0;
        g.k = 0;
        g.m = 0;
        g.n = 0;
        g.l = 0;
        g.o = 0;
        this.k = 0;
        this.P = 0;
        this.K = 0;
        this.L = 0;
        this.M = 0;
        this.N = 0;
        this.O = 0;
        this.l = false;
        this.s = 4;
        this.H = 0;
        this.I = 0;
        this.S = 0;
        this.T = 0;
        this.U = 0;
        this.V = 0;
        this.W = 0;
        this.X = 0;
    }
    
    public final int b(final int n) {
        return this.p[n >> 3];
    }
    
    private synchronized void a(final int v, final boolean b) {
        final boolean m;
        if (m = this.a.m) {
            this.a.b();
        }
        this.v = v;
        this.G = (int)(1024.0 * X.a * X.b / (this.v * 60.0));
        this.F = (int)(14915.0 * X.b / 60.0);
        this.E = 0;
        this.k = 0;
        if (m) {
            this.a.a();
        }
    }
    
    private void a(final int[] array) {
        for (int i = 0; i < 5; ++i) {
            this.ab[i] = array[i];
        }
        this.ac = this.ab[0] * this.aa >> 8;
        this.ad = this.ab[1] * this.aa >> 8;
        this.ae = this.ab[2] * this.aa >> 8;
        this.af = this.ab[3] * this.aa >> 8;
        this.ag = this.ab[4] * this.aa >> 8;
        this.ah = this.aa - this.ac;
        this.ai = this.aa - this.ad;
        this.aj = this.aa - this.ae;
        this.ak = this.aa - this.af;
        this.al = this.aa - this.ag;
    }
    
    public final boolean d() {
        return this.b != null && this.b.isActive();
    }
    
    public final int c(final int n) {
        final int available;
        if ((available = this.b.available()) >= n) {
            return 0;
        }
        return (n - available) * 1000 / this.v / (this.n ? 4 : 2);
    }
    
    private void f() {
        this.q = new int[512];
        this.r = new int[3264];
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < 512; ++i) {
            final int n3 = (int)(95.52 / (8128.0 / (i / 16.0) + 100.0) * 0.98411 * 50000.0);
            if ((this.q[i] = n3) > n) {
                n = n3;
            }
        }
        for (int j = 0; j < 3264; ++j) {
            final int n4 = (int)(163.67 / (24329.0 / (j / 16.0) + 100.0) * 0.98411 * 50000.0);
            if ((this.r[j] = n4) > n2) {
                n2 = n4;
            }
        }
        this.Y = n + n2;
        this.Z = this.Y / 2;
    }
}
