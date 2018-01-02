// 
// Decompiled by Procyon v0.5.30
// 

public final class N implements Runnable
{
    private Thread b;
    private U c;
    private ad d;
    private short[] e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private boolean t;
    private int u;
    private int[] v;
    private int w;
    private boolean x;
    public boolean a;
    
    public N(final U c) {
        this.c = c;
    }
    
    public final void a() {
        this.v = B.a();
        this.d = this.c.h;
        this.a = false;
        this.p = 1;
        this.q = 1;
        this.n = 1;
        this.t = false;
    }
    
    public final void b() {
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.t = false;
        this.u = 0;
        this.k = 511;
        this.j = 32767;
        this.i = 40;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 1;
        this.p = 0;
        this.q = 1;
        this.r = 0;
        this.s = 0;
        this.a = false;
        this.l = 0;
        this.o = 0;
        this.n = 1;
        this.r = 0;
        this.s = 0;
        this.m = 0;
        this.q = 1;
        this.p = 1;
        this.w = 0;
    }
    
    public final synchronized void c() {
        if (this.b != null && this.b.isAlive()) {
            this.d();
        }
        (this.b = new Thread(this)).start();
        this.b.setPriority(1);
    }
    
    public final synchronized void d() {
        if (this.b != null && this.b.isAlive()) {
            try {
                this.x = true;
                this.b.join();
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public final boolean e() {
        return this.b != null && this.b.isAlive();
    }
    
    public final void run() {
        this.g();
        this.e = this.c.e.a;
        final ad h = this.c.h;
        final Q c = this.c.c;
        final O d = this.c.d;
        int f = this.f;
        int g = this.g;
        int h2 = this.h;
        final int i = this.i;
        int n = this.j;
        int l = this.l;
        int n2 = (this.m == 0) ? 1 : 0;
        int n3 = this.n;
        int o = this.o;
        int q = this.q;
        int p = this.p;
        int r = this.r;
        int s = this.s;
        int n4 = 0;
        final boolean h3 = W.h;
        final boolean e = W.e;
        this.x = false;
        while (!this.x) {
            if (this.t) {
                final int n5 = l | ((n2 == 0) ? 1 : 0) << 1 | n3 << 2 | o << 3 | p << 4 | q << 5 | r << 6 | s << 7;
                this.j = n;
                this.n = n3;
                switch (this.u) {
                    case 0: {
                        if (n3 == 0) {
                            final int n6 = n5;
                            ++this.j;
                            this.e(this.j >> 8 & 0xFF);
                            this.e(this.j & 0xFF);
                            this.e(n6);
                            this.n = 1;
                            this.p = 0;
                            this.j = (this.d.a(65534) | this.d.a(65535) << 8);
                            --this.j;
                            break;
                        }
                        break;
                    }
                    case 1: {
                        final int n7 = n5;
                        if ((this.d.a(8192) & 0x80) != 0x0) {
                            ++this.j;
                            this.e(this.j >> 8 & 0xFF);
                            this.e(this.j & 0xFF);
                            this.e(n7);
                            this.j = (this.d.a(65530) | this.d.a(65531) << 8);
                            --this.j;
                        }
                        break;
                    }
                    case 2: {
                        this.j = (this.d.a(65532) | this.d.a(65533) << 8);
                        --this.j;
                        break;
                    }
                }
                n = this.j;
                n3 = this.n;
                p = this.p;
                this.t = false;
            }
            final int n9;
            int n8 = (n9 = this.v[h.a(n + 1)]) >> 24;
            int n10 = 0;
            final int n11 = n9 >> 8 & 0xFF;
            final int n12 = n;
            n += (n9 >> 16 & 0xFF);
            switch (n11) {
                case 0: {
                    n4 = this.c(n12 + 2);
                    break;
                }
                case 1: {
                    final int c2;
                    if ((c2 = this.c(n12 + 2)) < 128) {
                        n4 = c2 + n;
                        break;
                    }
                    n4 = c2 + (n - 256);
                    break;
                }
                case 3: {
                    n4 = this.d(n12 + 2);
                    break;
                }
                case 4: {
                    n4 = f;
                    break;
                }
                case 5: {
                    n4 = n;
                    break;
                }
                case 6: {
                    n4 = (this.c(n12 + 2) + g & 0xFF);
                    break;
                }
                case 7: {
                    n4 = (this.c(n12 + 2) + h2 & 0xFF);
                    break;
                }
                case 8: {
                    final int d2;
                    if (((d2 = this.d(n12 + 2)) & 0xFF00) != (d2 + g & 0xFF00)) {
                        n10 = 1;
                    }
                    n4 = d2 + g;
                    break;
                }
                case 9: {
                    final int d3;
                    if (((d3 = this.d(n12 + 2)) & 0xFF00) != (d3 + h2 & 0xFF00)) {
                        n10 = 1;
                    }
                    n4 = d3 + h2;
                    break;
                }
                case 10: {
                    final int c3;
                    if (((c3 = this.c(n12 + 2)) & 0xFF00) != (c3 + g & 0xFF00)) {
                        n10 = 1;
                    }
                    n4 = this.d(c3 + g & 0xFF);
                    break;
                }
                case 11: {
                    final int d4;
                    if (((d4 = this.d(this.c(n12 + 2))) & 0xFF00) != (d4 + h2 & 0xFF00)) {
                        n10 = 1;
                    }
                    n4 = d4 + h2;
                    break;
                }
                case 12: {
                    final int d5;
                    if ((d5 = this.d(n12 + 2)) < 8191) {
                        n4 = this.e[d5] + (this.e[(d5 & 0xFF00) | ((d5 & 0xFF) + 1 & 0xFF)] << 8);
                        break;
                    }
                    n4 = h.a(d5) + (h.a((d5 & 0xFF00) | ((d5 & 0xFF) + 1 & 0xFF)) << 8);
                    break;
                }
            }
            n4 &= 0xFFFF;
            switch (n9 & 0xFF) {
                case 0: {
                    final int n13 = f + this.c(n4) + l;
                    r = ((((f ^ this.c(n4)) & 0x80) == 0x0 && ((f ^ n13) & 0x80) != 0x0) ? 1 : 0);
                    l = ((n13 > 255) ? 1 : 0);
                    s = (n13 >> 7 & 0x1);
                    n2 = (n13 & 0xFF);
                    f = (n13 & 0xFF);
                    n8 += n10;
                    break;
                }
                case 1: {
                    s = ((f &= this.c(n4)) >> 7 & 0x1);
                    n2 = f;
                    if (n11 != 11) {
                        n8 += n10;
                        break;
                    }
                    break;
                }
                case 2: {
                    if (n11 == 4) {
                        l = (f >> 7 & 0x1);
                        s = ((f = (f << 1 & 0xFF)) >> 7 & 0x1);
                        n2 = f;
                        break;
                    }
                    final int c4;
                    l = ((c4 = this.c(n4)) >> 7 & 0x1);
                    final int n14;
                    s = ((n14 = (c4 << 1 & 0xFF)) >> 7 & 0x1);
                    n2 = n14;
                    this.a(n4, (short)n14);
                    break;
                }
                case 3: {
                    if (l == 0) {
                        n8 += (((n12 & 0xFF00) != (n4 & 0xFF00)) ? 2 : 1);
                        n = n4;
                        break;
                    }
                    break;
                }
                case 4: {
                    if (l == 1) {
                        n8 += (((n12 & 0xFF00) != (n4 & 0xFF00)) ? 2 : 1);
                        n = n4;
                        break;
                    }
                    break;
                }
                case 5: {
                    if (n2 == 0) {
                        n8 += (((n12 & 0xFF00) != (n4 & 0xFF00)) ? 2 : 1);
                        n = n4;
                        break;
                    }
                    break;
                }
                case 6: {
                    final int c5;
                    s = ((c5 = this.c(n4)) >> 7 & 0x1);
                    r = (c5 >> 6 & 0x1);
                    n2 = (c5 & f);
                    break;
                }
                case 7: {
                    if (s == 1) {
                        ++n8;
                        n = n4;
                        break;
                    }
                    break;
                }
                case 8: {
                    if (n2 != 0) {
                        n8 += (((n12 & 0xFF00) != (n4 & 0xFF00)) ? 2 : 1);
                        n = n4;
                        break;
                    }
                    break;
                }
                case 9: {
                    if (s == 0) {
                        n8 += (((n12 & 0xFF00) != (n4 & 0xFF00)) ? 2 : 1);
                        n = n4;
                        break;
                    }
                    break;
                }
                case 10: {
                    n += 2;
                    this.e(n >> 8 & 0xFF);
                    this.e(n & 0xFF);
                    p = 1;
                    this.e(l | ((n2 == 0) ? 1 : 0) << 1 | n3 << 2 | o << 3 | 0x10 | q << 5 | r << 6 | s << 7);
                    n3 = 1;
                    n = this.d(65534);
                    --n;
                    break;
                }
                case 11: {
                    if (r == 0) {
                        n8 += (((n12 & 0xFF00) != (n4 & 0xFF00)) ? 2 : 1);
                        n = n4;
                        break;
                    }
                    break;
                }
                case 12: {
                    if (r == 1) {
                        n8 += (((n12 & 0xFF00) != (n4 & 0xFF00)) ? 2 : 1);
                        n = n4;
                        break;
                    }
                    break;
                }
                case 13: {
                    l = 0;
                    break;
                }
                case 14: {
                    o = 0;
                    break;
                }
                case 15: {
                    n3 = 0;
                    break;
                }
                case 16: {
                    r = 0;
                    break;
                }
                case 17: {
                    final int n15;
                    l = (((n15 = f - this.c(n4)) >= 0) ? 1 : 0);
                    s = (n15 >> 7 & 0x1);
                    n2 = (n15 & 0xFF);
                    n8 += n10;
                    break;
                }
                case 18: {
                    final int n16;
                    l = (((n16 = g - this.c(n4)) >= 0) ? 1 : 0);
                    s = (n16 >> 7 & 0x1);
                    n2 = (n16 & 0xFF);
                    break;
                }
                case 19: {
                    final int n17;
                    l = (((n17 = h2 - this.c(n4)) >= 0) ? 1 : 0);
                    s = (n17 >> 7 & 0x1);
                    n2 = (n17 & 0xFF);
                    break;
                }
                case 20: {
                    final int n18;
                    s = ((n18 = (this.c(n4) - 1 & 0xFF)) >> 7 & 0x1);
                    n2 = n18;
                    this.a(n4, (short)n18);
                    break;
                }
                case 21: {
                    s = ((g = (g - 1 & 0xFF)) >> 7 & 0x1);
                    n2 = g;
                    break;
                }
                case 22: {
                    s = ((h2 = (h2 - 1 & 0xFF)) >> 7 & 0x1);
                    n2 = h2;
                    break;
                }
                case 23: {
                    s = ((f = ((this.c(n4) ^ f) & 0xFF)) >> 7 & 0x1);
                    n2 = f;
                    n8 += n10;
                    break;
                }
                case 24: {
                    final int n19;
                    s = ((n19 = (this.c(n4) + 1 & 0xFF)) >> 7 & 0x1);
                    n2 = n19;
                    this.a(n4, (short)(n19 & 0xFF));
                    break;
                }
                case 25: {
                    s = ((g = (g + 1 & 0xFF)) >> 7 & 0x1);
                    n2 = g;
                    break;
                }
                case 26: {
                    s = ((h2 = (++h2 & 0xFF)) >> 7 & 0x1);
                    n2 = h2;
                    break;
                }
                case 27: {
                    n = n4 - 1;
                    break;
                }
                case 28: {
                    this.e(n >> 8 & 0xFF);
                    this.e(n & 0xFF);
                    n = n4 - 1;
                    break;
                }
                case 29: {
                    s = ((f = this.c(n4)) >> 7 & 0x1);
                    n2 = f;
                    n8 += n10;
                    break;
                }
                case 30: {
                    s = ((g = this.c(n4)) >> 7 & 0x1);
                    n2 = g;
                    n8 += n10;
                    break;
                }
                case 31: {
                    s = ((h2 = this.c(n4)) >> 7 & 0x1);
                    n2 = h2;
                    n8 += n10;
                    break;
                }
                case 32: {
                    int n21;
                    if (n11 == 4) {
                        final int n20;
                        l = ((n20 = (f & 0xFF)) & 0x1);
                        n21 = (f = n20 >> 1);
                    }
                    else {
                        final int n22;
                        l = ((n22 = (this.c(n4) & 0xFF)) & 0x1);
                        n21 = n22 >> 1;
                        this.a(n4, (short)n21);
                    }
                    s = 0;
                    n2 = n21;
                    break;
                }
                case 33: {
                    break;
                }
                case 34: {
                    final int n23;
                    s = ((n23 = ((this.c(n4) | f) & 0xFF)) >> 7 & 0x1);
                    n2 = n23;
                    f = n23;
                    if (n11 != 11) {
                        n8 += n10;
                        break;
                    }
                    break;
                }
                case 35: {
                    this.e(f);
                    break;
                }
                case 36: {
                    p = 1;
                    this.e(l | ((n2 == 0) ? 1 : 0) << 1 | n3 << 2 | o << 3 | 0x10 | q << 5 | r << 6 | s << 7);
                    break;
                }
                case 37: {
                    s = ((f = this.h()) >> 7 & 0x1);
                    n2 = f;
                    break;
                }
                case 38: {
                    final short h4;
                    l = ((h4 = this.h()) & 0x1);
                    n2 = (((h4 >> 1 & 0x1) != 0x1) ? 1 : 0);
                    n3 = (h4 >> 2 & 0x1);
                    o = (h4 >> 3 & 0x1);
                    p = (h4 >> 4 & 0x1);
                    r = (h4 >> 6 & 0x1);
                    s = (h4 >> 7 & 0x1);
                    q = 1;
                    break;
                }
                case 39: {
                    int n26;
                    if (n11 == 4) {
                        final int n24 = f;
                        final int n25 = l;
                        l = (n24 >> 7 & 0x1);
                        n26 = (f = (n24 << 1 & 0xFF) + n25);
                    }
                    else {
                        final int c6 = this.c(n4);
                        final int n27 = l;
                        l = (c6 >> 7 & 0x1);
                        n26 = (c6 << 1 & 0xFF) + n27;
                        this.a(n4, (short)n26);
                    }
                    s = (n26 >> 7 & 0x1);
                    n2 = n26;
                    break;
                }
                case 40: {
                    int n29;
                    if (n11 == 4) {
                        final int n28 = l << 7;
                        l = (f & 0x1);
                        n29 = (f = (f >> 1) + n28);
                    }
                    else {
                        final int c7 = this.c(n4);
                        final int n30 = l << 7;
                        l = (c7 & 0x1);
                        n29 = (c7 >> 1) + n30;
                        this.a(n4, (short)n29);
                    }
                    s = (n29 >> 7 & 0x1);
                    n2 = n29;
                    break;
                }
                case 41: {
                    final short h5;
                    l = ((h5 = this.h()) & 0x1);
                    n2 = (((h5 >> 1 & 0x1) == 0x0) ? 1 : 0);
                    n3 = (h5 >> 2 & 0x1);
                    o = (h5 >> 3 & 0x1);
                    p = (h5 >> 4 & 0x1);
                    r = (h5 >> 6 & 0x1);
                    s = (h5 >> 7 & 0x1);
                    if ((n = this.h() + (this.h() << 8)) != 65535) {
                        --n;
                        q = 1;
                        break;
                    }
                    return;
                }
                case 42: {
                    if ((n = this.h() + (this.h() << 8)) == 65535) {
                        return;
                    }
                    break;
                }
                case 43: {
                    final int n31;
                    s = ((n31 = f - this.c(n4) - (1 - l)) >> 7 & 0x1);
                    n2 = (n31 & 0xFF);
                    r = ((((f ^ n31) & 0x80) != 0x0 && ((f ^ this.c(n4)) & 0x80) != 0x0) ? 1 : 0);
                    l = ((n31 >= 0) ? 1 : 0);
                    f = (n31 & 0xFF);
                    if (n11 != 11) {
                        n8 += n10;
                        break;
                    }
                    break;
                }
                case 44: {
                    l = 1;
                    break;
                }
                case 45: {
                    o = 1;
                    break;
                }
                case 46: {
                    n3 = 1;
                    break;
                }
                case 47: {
                    this.a(n4, (short)f);
                    break;
                }
                case 48: {
                    this.a(n4, (short)g);
                    break;
                }
                case 49: {
                    this.a(n4, (short)h2);
                    break;
                }
                case 50: {
                    g = f;
                    s = (f >> 7 & 0x1);
                    n2 = f;
                    break;
                }
                case 51: {
                    h2 = f;
                    s = (f >> 7 & 0x1);
                    n2 = f;
                    break;
                }
                case 52: {
                    g = this.k - 256;
                    s = (this.k >> 7 & 0x1);
                    n2 = g;
                    break;
                }
                case 53: {
                    f = g;
                    s = (g >> 7 & 0x1);
                    n2 = g;
                    break;
                }
                case 54: {
                    this.k = g + 256;
                    this.k = (0x100 | (this.k & 0xFF));
                    break;
                }
                case 55: {
                    f = h2;
                    s = (h2 >> 7 & 0x1);
                    n2 = h2;
                    break;
                }
                default: {
                    if (!this.a) {
                        this.a = true;
                        this.x = true;
                        System.out.println("Game crashed, invalid opcode at address $" + L.a(n12));
                        break;
                    }
                    break;
                }
            }
            if (e) {
                c.g = n8 * 3;
                c.b();
            }
            if (h3) {
                d.a(n8);
            }
        }
        this.f = f;
        this.g = g;
        this.h = h2;
        this.i = i;
        this.j = n;
        this.l = l;
        this.m = ((n2 == 0) ? 1 : 0);
        this.n = n3;
        this.o = o;
        this.p = p;
        this.q = q;
        this.r = r;
        this.s = s;
    }
    
    private synchronized void g() {
        this.x = false;
    }
    
    private int c(final int n) {
        if (n < 8192) {
            return this.e[n & 0x7FF];
        }
        return this.d.a(n);
    }
    
    private int d(final int n) {
        if (n < 8191) {
            return this.e[n & 0x7FF] | this.e[n + 1 & 0x7FF] << 8;
        }
        return this.d.a(n) | this.d.a(n + 1) << 8;
    }
    
    private void a(final int n, final short n2) {
        if (n < 8192) {
            this.e[n & 0x7FF] = n2;
            return;
        }
        this.d.a(n, n2);
    }
    
    public final void a(final int u) {
        if (this.t && u == 0) {
            return;
        }
        this.t = true;
        this.u = u;
    }
    
    private void e(final int n) {
        this.d.a(this.k, (short)n);
        --this.k;
        this.k = (0x100 | (this.k & 0xFF));
    }
    
    private short h() {
        ++this.k;
        this.k = (0x100 | (this.k & 0xFF));
        return this.d.a(this.k);
    }
    
    public final void b(final int n) {
        this.w += n;
    }
    
    public final void a(final ad d) {
        this.d = d;
    }
    
    public final void f() {
        this.c = null;
        this.d = null;
    }
}
