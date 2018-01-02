// 
// Decompiled by Procyon v0.5.30
// 

public class r
{
    public t[][] a;
    public int b;
    public int c;
    public int d;
    public int e;
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
    public boolean q;
    private int r;
    private int s;
    private int t;
    private int u;
    public int v;
    public int[] w;
    private byte[] x;
    
    public r(final int n, final int n2, final int d, final int e, final int f, final int g) {
        final boolean dj = p.dJ;
        this.w = new int[405];
        this.x = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        this.a(n, n2);
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.a = new t[n][n2];
        int n3 = 0;
        while (true) {
            Label_0149: {
                if (!dj) {
                    break Label_0149;
                }
                int n4 = 0;
                while (true) {
                    Label_0140: {
                        if (!dj) {
                            break Label_0140;
                        }
                        this.a[n3][n4] = new t();
                        ++n4;
                    }
                    if (n4 < n2) {
                        continue;
                    }
                    break;
                }
                ++n3;
            }
            if (n3 >= n) {
                this.a();
                return;
            }
            continue;
        }
    }
    
    public void a(final int b, final int c) {
        this.b = b;
        this.c = c;
    }
    
    public void b(final int d, final int e) {
        this.d = d;
        this.e = e;
    }
    
    public void a(final d d) {
        final boolean dj = p.dJ;
        this.b(d);
        this.a();
        int n = 0;
        while (true) {
            Label_0054: {
                if (!dj) {
                    break Label_0054;
                }
                int n2 = 0;
                while (true) {
                    Label_0043: {
                        if (!dj) {
                            break Label_0043;
                        }
                        this.a[n][n2].d = 0;
                        ++n2;
                    }
                    if (n2 < this.c) {
                        continue;
                    }
                    break;
                }
                ++n;
            }
            if (n >= this.b) {
                return;
            }
            continue;
        }
    }
    
    public void a(final d d, final int n, final int n2) {
        this.b(d, n, n2);
        this.c(n, n2);
    }
    
    public void c(final int n, final int n2) {
        if (this.f(n, n2)) {
            this.a[n][n2].a();
        }
    }
    
    private void a() {
        final boolean dj = p.dJ;
        int n = 0;
        while (true) {
            Label_0036: {
                if (!dj) {
                    break Label_0036;
                }
                int n2 = 0;
                while (true) {
                    Label_0025: {
                        if (!dj) {
                            break Label_0025;
                        }
                        this.c(n, n2);
                        ++n2;
                    }
                    if (n2 < this.c) {
                        continue;
                    }
                    break;
                }
                ++n;
            }
            if (n >= this.b) {
                return;
            }
            continue;
        }
    }
    
    public boolean d(final int n, final int n2) {
        return this.f(n, n2) && this.a[n][n2].c(16);
    }
    
    public boolean e(final int n, final int n2) {
        return this.f(n, n2) && this.a[n][n2].c(8);
    }
    
    public int b() {
        final boolean dj = p.dJ;
        int n = 0;
        int n2 = 0;
        while (true) {
            Label_0053: {
                if (!dj) {
                    break Label_0053;
                }
                final int n4;
                int n3 = n4;
                while (true) {
                    Label_0042: {
                        if (!dj) {
                            break Label_0042;
                        }
                        if (this.a[n2][n3].b != -1) {
                            ++n;
                        }
                        ++n3;
                    }
                    if (n3 < this.c) {
                        continue;
                    }
                    break;
                }
                ++n2;
            }
            if (n2 < this.b) {
                continue;
            }
            final int n4 = n;
            if (!dj) {
                return n4;
            }
            continue;
        }
    }
    
    public int c() {
        final boolean dj = p.dJ;
        int n = 0;
        int n2 = 0;
        while (true) {
            Label_0070: {
                if (!dj) {
                    break Label_0070;
                }
                final int n4;
                int n3 = n4;
                while (true) {
                    Label_0059: {
                        if (!dj) {
                            break Label_0059;
                        }
                        if (this.a[n2][n3].b != -1 && u.i(this.a[n2][n3].b)) {
                            ++n;
                        }
                        ++n3;
                    }
                    if (n3 < this.c) {
                        continue;
                    }
                    break;
                }
                ++n2;
            }
            if (n2 < this.b) {
                continue;
            }
            final int n4 = n;
            if (!dj) {
                return n4;
            }
            continue;
        }
    }
    
    public int d() {
        final boolean dj = p.dJ;
        int n = 0;
        int n2 = 0;
        while (true) {
            Label_0070: {
                if (!dj) {
                    break Label_0070;
                }
                final int n4;
                int n3 = n4;
                while (true) {
                    Label_0059: {
                        if (!dj) {
                            break Label_0059;
                        }
                        if (this.a[n2][n3].b != -1 && !u.i(this.a[n2][n3].b)) {
                            ++n;
                        }
                        ++n3;
                    }
                    if (n3 < this.c) {
                        continue;
                    }
                    break;
                }
                ++n2;
            }
            if (n2 < this.b) {
                continue;
            }
            final int n4 = n;
            if (!dj) {
                return n4;
            }
            continue;
        }
    }
    
    public boolean f(final int n, final int n2) {
        return i.b(n, 0, this.b - 1) && i.b(n2, 0, this.c - 1);
    }
    
    public boolean g(final int n, final int n2) {
        return this.f(n, n2) && this.a[n][n2].b == -1;
    }
    
    public int h(final int n, final int n2) {
        if (this.f(n, n2)) {
            return this.a[n][n2].a;
        }
        return 0;
    }
    
    public void a(final int n, final int n2, final int a) {
        if (this.f(n, n2)) {
            this.a[n][n2].a = a;
        }
    }
    
    public int i(final int n, final int n2) {
        if (this.f(n, n2)) {
            return this.a[n][n2].c;
        }
        return 0;
    }
    
    public int j(final int n, final int n2) {
        if (this.f(n, n2)) {
            return this.a[n][n2].d;
        }
        return 0;
    }
    
    public boolean k(final int n, final int n2) {
        return this.j(n, n2) != 0;
    }
    
    public void b(final int n, final int n2, final int d) {
        if (this.f(n, n2)) {
            this.a[n][n2].d = d;
        }
    }
    
    public int l(final int n, final int n2) {
        if (this.f(n, n2)) {
            return this.a[n][n2].b;
        }
        return -1;
    }
    
    public void a(final int n, final int n2, final int n3, final int n4, final int n5) {
        if (this.f(n, n2)) {
            this.a[n][n2].a(n3, n4, n5);
        }
    }
    
    public int e() {
        return this.c * this.f;
    }
    
    public int f() {
        return this.b * this.g;
    }
    
    public int a(final int n) {
        return this.d + n * this.f;
    }
    
    public int b(final int n) {
        return this.a(n) + this.f / 2;
    }
    
    public int c(final int n) {
        return this.e + n * this.g;
    }
    
    public int d(final int n) {
        return this.c(n) + this.g / 2;
    }
    
    public v m(final int n, final int n2) {
        if (this.f(n, n2)) {
            return this.a[n][n2].f;
        }
        return null;
    }
    
    public boolean n(final int n, final int n2) {
        return this.f(n, n2) && this.a[n][n2].f.b();
    }
    
    public void b(final d d, final int n, final int n2) {
        if (this.f(n, n2)) {
            this.a[n][n2].f.a(d);
        }
    }
    
    public void b(final d d) {
        final boolean dj = p.dJ;
        int n = 0;
        while (true) {
            Label_0040: {
                if (!dj) {
                    break Label_0040;
                }
                int n2 = 0;
                while (true) {
                    Label_0029: {
                        if (!dj) {
                            break Label_0029;
                        }
                        this.b(d, n, n2);
                        ++n2;
                    }
                    if (n2 < this.c) {
                        continue;
                    }
                    break;
                }
                ++n;
            }
            if (n >= this.b) {
                return;
            }
            continue;
        }
    }
    
    public void c(final d d, final int n, final int n2) {
        if (this.f(n, n2)) {
            this.b(d, n, n2);
            this.a[n][n2].f.a(d, this.b(n2), this.d(n), this.a[n][n2].b, this.a[n][n2].c, this.a[n][n2].d);
        }
    }
    
    public void d(final d d, final int n, final int n2) {
        this.a[n][n2].f.c(d);
    }
    
    public void e(final d d, final int n, final int n2) {
        this.a[n][n2].f.d(d);
    }
    
    public void a(final d d, final int n, final int n2, final boolean b) {
        if (this.f(n, n2)) {
            this.a[n][n2].f.a(d, b);
        }
    }
    
    public void a(final d d, final boolean b) {
        final boolean dj = p.dJ;
        int n = 0;
        while (true) {
            Label_0044: {
                if (!dj) {
                    break Label_0044;
                }
                int n2 = 0;
                while (true) {
                    Label_0032: {
                        if (!dj) {
                            break Label_0032;
                        }
                        this.a(d, n, n2, b);
                        ++n2;
                    }
                    if (n2 < this.c) {
                        continue;
                    }
                    break;
                }
                ++n;
            }
            if (n >= this.b) {
                return;
            }
            continue;
        }
    }
    
    public boolean f(final d d, final int n, final int n2) {
        return this.f(n, n2) && this.a[n][n2].f.e(d);
    }
    
    public boolean a(final d d, final int n) {
        final boolean dj = p.dJ;
        int h = 0;
        while (true) {
            while (true) {
                Label_0115: {
                    if (!dj) {
                        break Label_0115;
                    }
                    final int n2 = 0;
                    int i = n2;
                    while (true) {
                        Label_0103: {
                            if (!dj) {
                                break Label_0103;
                            }
                            if (this.f(d, h, i) && this.h(h, i) >= n) {
                                this.h = h;
                                this.i = i;
                                this.l = this.l(h, i);
                                this.m = this.a[h][i].c;
                                this.a[h][i].f.b(d, 50000);
                                return true;
                            }
                            ++i;
                        }
                        if (i < this.c) {
                            continue;
                        }
                        break;
                    }
                    ++h;
                }
                if (h < this.b) {
                    continue;
                }
                break;
            }
            int n2;
            final int n3 = n2 = 0;
            if (!dj) {
                return n3 != 0;
            }
            continue;
        }
    }
    
    public boolean c(final d d) {
        boolean f = false;
        final int n = d.bo - this.d;
        final int n2 = d.bp - this.e;
        if (i.b(n, 0, this.e() - 1) && i.b(n2, 0, this.f() - 1)) {
            this.k = n / this.f;
            this.j = n2 / this.g;
            if (f = this.f(this.j, this.k)) {
                d.o();
            }
        }
        return f;
    }
    
    public void e(final int n) {
        final boolean dj = p.dJ;
        int n2 = 0;
        while (true) {
            Label_0045: {
                if (!dj) {
                    break Label_0045;
                }
                int n3 = 0;
                while (true) {
                    Label_0034: {
                        if (!dj) {
                            break Label_0034;
                        }
                        this.a[n2][n3].a(n);
                        ++n3;
                    }
                    if (n3 < this.c) {
                        continue;
                    }
                    break;
                }
                ++n2;
            }
            if (n2 >= this.b) {
                return;
            }
            continue;
        }
    }
    
    private void g() {
        this.e(-1);
    }
    
    public boolean c(final int n, final int n2, final int n3) {
        return this.a[n][n2].c(n3);
    }
    
    public void d(final int n, final int n2, final int n3) {
        this.a[n][n2].b(n3);
    }
    
    public void o(final int n, final int n2) {
        if (this.f(n, n2) && this.l(n, n2) != -1 && !this.a[n][n2].c(1)) {
            this.a[n][n2].b(1);
            this.o(n - 1, n2);
            this.o(n + 1, n2);
            this.o(n, n2 - 1);
            this.o(n, n2 + 1);
        }
    }
    
    public int p(final int n, final int n2) {
        final boolean dj = p.dJ;
        this.r = n2;
        this.s = n2;
        int i = 0;
        int n5 = 0;
        while (true) {
            int l = 0;
            int n3 = 0;
            Label_0077: {
                while (true) {
                    Label_0028: {
                        if (!dj) {
                            break Label_0028;
                        }
                        --this.r;
                    }
                    if (this.r > 0) {
                        l = this.l(n, this.r - 1);
                        n3 = -1;
                        if (dj) {
                            break Label_0077;
                        }
                        if (l != n3) {
                            continue;
                        }
                    }
                    break;
                }
                final int s = this.s;
                final int n4 = this.c - 1;
            }
            if (l < n3) {
                i = this.l(n, this.s + 1);
                n5 = -1;
                if (dj) {
                    return i + n5;
                }
                if (i != n5) {
                    ++this.s;
                    continue;
                }
            }
            break;
        }
        final int n6 = this.s - this.r;
        return i + n5;
    }
    
    public int q(final int n, final int n2) {
        final boolean dj = p.dJ;
        this.t = n;
        this.u = n;
        int i = 0;
        int n5 = 0;
        while (true) {
            int l = 0;
            int n3 = 0;
            Label_0077: {
                while (true) {
                    Label_0028: {
                        if (!dj) {
                            break Label_0028;
                        }
                        --this.t;
                    }
                    if (this.t > 0) {
                        l = this.l(this.t - 1, n2);
                        n3 = -1;
                        if (dj) {
                            break Label_0077;
                        }
                        if (l != n3) {
                            continue;
                        }
                    }
                    break;
                }
                final int u = this.u;
                final int n4 = this.b - 1;
            }
            if (l < n3) {
                i = this.l(this.u + 1, n2);
                n5 = -1;
                if (dj) {
                    return i + n5;
                }
                if (i != n5) {
                    ++this.u;
                    continue;
                }
            }
            break;
        }
        final int n6 = this.u - this.t;
        return i + n5;
    }
    
    public boolean h() {
        final boolean dj = p.dJ;
        this.q = true;
        if (this.b() > 0) {
            final byte[] array = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            this.g();
            int n = 0;
            int i = 0;
            int n2 = 0;
            int n3 = 0;
            int n4 = 0;
        Label_0448:
            while (true) {
                while (true) {
                    if (dj) {
                        break Label_0072;
                    }
                    Label_0459: {
                        break Label_0459;
                        int p;
                        int q;
                        int r;
                        boolean c;
                        int r2;
                        r r3;
                        int t;
                        boolean c2;
                        int t2;
                        r r4;
                        Label_0167_Outer:Label_0203_Outer:Label_0204_Outer:Label_0324_Outer:
                        while (i < this.c) {
                            if (this.l(n, i) != -1) {
                                p = 0;
                                q = 0;
                                if (!this.a[n][i].c(2) && (p = this.p(n, i)) > 1) {
                                    r = this.r;
                                    while (true) {
                                        while (true) {
                                            Label_0170: {
                                                if (!dj) {
                                                    break Label_0170;
                                                }
                                                array[r - this.r] = (byte)this.l(n, r);
                                                this.a[n][r].b(2);
                                                ++r;
                                            }
                                            if (r <= this.s) {
                                                continue Label_0167_Outer;
                                            }
                                            break;
                                        }
                                        c = n.c(array, p);
                                        r2 = this.r;
                                        if (dj) {
                                            continue Label_0203_Outer;
                                        }
                                        break;
                                    }
                                    while (true) {
                                        while (true) {
                                            Label_0230: {
                                                if (!dj) {
                                                    break Label_0230;
                                                }
                                                r3 = this;
                                                r3.a[n][r2].b(c ? 16 : 8);
                                                ++r2;
                                            }
                                            if (r2 <= this.s) {
                                                continue Label_0204_Outer;
                                            }
                                            break;
                                        }
                                        r3 = this;
                                        if (dj) {
                                            continue;
                                        }
                                        break;
                                    }
                                    this.q &= c;
                                }
                                if (!this.a[n][i].c(4) && (q = this.q(n, i)) > 1) {
                                    t = this.t;
                                    while (true) {
                                        while (true) {
                                            Label_0327: {
                                                if (!dj) {
                                                    break Label_0327;
                                                }
                                                array[t - this.t] = (byte)this.l(t, i);
                                                this.a[t][i].b(4);
                                                ++t;
                                            }
                                            if (t <= this.u) {
                                                continue Label_0324_Outer;
                                            }
                                            break;
                                        }
                                        c2 = n.c(array, q);
                                        t2 = this.t;
                                        if (dj) {
                                            if (dj) {
                                                continue;
                                            }
                                        }
                                        break;
                                    }
                                    while (true) {
                                        if (t2 > this.u) {
                                            r4 = this;
                                            if (!dj) {
                                                break;
                                            }
                                        }
                                        else {
                                            r4 = this;
                                        }
                                        r4.a[t2][i].b(c2 ? 16 : 8);
                                        ++t2;
                                    }
                                    this.q &= c2;
                                }
                                if (p == 1 && q == 1) {
                                    this.q = false;
                                    this.a[n][i].b(8);
                                }
                            }
                            ++i;
                        }
                        ++n;
                        break Label_0459;
                        i = 0;
                        if (dj) {}
                        continue Label_0448;
                    }
                    if (n < this.b) {
                        continue;
                    }
                    break;
                }
                i = 7;
                n2 = -1;
                n3 = -1;
                n4 = 0;
                if (dj) {
                    continue Label_0448;
                }
                break;
            }
            int n6 = 0;
            while (true) {
                Label_0566: {
                    if (!dj) {
                        break Label_0566;
                    }
                    int n5 = n6;
                    while (true) {
                        Label_0554: {
                            if (!dj) {
                                break Label_0554;
                            }
                            if (this.l(n4, n5) != -1 && this.c(n4, n5, 16) && this.h(n4, n5) < i) {
                                i = this.h(n4, n5);
                                n2 = n4;
                                n3 = n5;
                            }
                            ++n5;
                        }
                        if (n5 < this.c) {
                            continue;
                        }
                        break;
                    }
                    ++n4;
                }
                if (n4 < this.b) {
                    continue;
                }
                n6 = n2;
                if (dj) {
                    continue;
                }
                break;
            }
            if (n6 == -1) {
                this.q = false;
                if (!dj) {
                    return this.q;
                }
            }
            this.o(n2, n3);
            int n7 = 0;
            while (true) {
                Label_0684: {
                    if (!dj) {
                        break Label_0684;
                    }
                    int n8 = 0;
                    while (true) {
                        Label_0672: {
                            if (!dj) {
                                break Label_0672;
                            }
                            if (this.l(n7, n8) != -1 && !this.a[n7][n8].c(1)) {
                                this.a[n7][n8].b(8);
                                this.q = false;
                            }
                            ++n8;
                        }
                        if (n8 < this.c) {
                            continue;
                        }
                        break;
                    }
                    ++n7;
                }
                if (n7 < this.b) {
                    continue;
                }
                break;
            }
        }
        return this.q;
    }
    
    public int f(final int n) {
        final boolean dj = p.dJ;
        int n2 = 0;
        final boolean b = n == 0;
        this.n = 0;
        this.v = 0;
        this.o = 0;
        this.p = 0;
        if (this.b() > 0) {
            final byte[] array = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            final boolean[] array2 = { false, false, false, false, false, false, false, false, false };
            this.h();
            this.e(6);
            int n3 = 0;
            while (true) {
                Label_0850: {
                    if (!dj) {
                        break Label_0850;
                    }
                    int n4 = 0;
                    int p;
                    int r;
                    int n5;
                    int c = 0;
                    int n6;
                    int n7;
                    int n8;
                    int n9 = 0;
                    boolean b2;
                    int f;
                    int q;
                    int t;
                    int n10;
                    int c2 = 0;
                    int n11;
                    int n12;
                    int n13;
                    int n14 = 0;
                    boolean b3;
                    int f2;
                    Label_0257_Outer:Label_0571_Outer:
                    while (true) {
                        Label_0838: {
                            if (!dj) {
                                break Label_0838;
                            }
                            if (this.l(n3, n4) != -1 && !this.a[n3][n4].c(8) && (b || this.h(n3, n4) == n)) {
                                if (!this.a[n3][n4].c(2) && (p = this.p(n3, n4)) > 1) {
                                    r = this.r;
                                    while (true) {
                                        while (true) {
                                            Label_0303: {
                                                if (!dj) {
                                                    break Label_0303;
                                                }
                                                n5 = r - this.r;
                                                n6 = c;
                                                array[n6] = (byte)this.l(n3, r);
                                                array2[n6] = this.k(n3, r);
                                                this.a[n3][r].b(2);
                                                ++r;
                                            }
                                            if (r <= this.s) {
                                                continue Label_0257_Outer;
                                            }
                                            break;
                                        }
                                        c = (n.c(array, p) ? 1 : 0);
                                        if (dj) {
                                            continue;
                                        }
                                        break;
                                    }
                                    if (c != 0) {
                                        n7 = 0;
                                        n8 = 0;
                                        while (true) {
                                            Label_0379: {
                                                if (!dj) {
                                                    break Label_0379;
                                                }
                                                if (n9 == 0) {
                                                    b2 = array2[n8];
                                                }
                                                f = u.f(array[n8]);
                                                n7 += f;
                                                this.o += f;
                                                ++n8;
                                            }
                                            if (n8 < p) {
                                                continue;
                                            }
                                            this.w[this.v++] = n3;
                                            this.w[this.v++] = this.r;
                                            this.w[this.v++] = n3;
                                            this.w[this.v++] = this.s;
                                            this.w[this.v++] = n7;
                                            n9 = p;
                                            if (dj) {
                                                continue;
                                            }
                                            break;
                                        }
                                        if (n9 == 6 && !b) {
                                            n7 += 100;
                                            this.p += 100;
                                        }
                                        n2 += n7;
                                        this.n = p;
                                    }
                                }
                                if (!this.a[n3][n4].c(4) && (q = this.q(n3, n4)) > 1) {
                                    t = this.t;
                                    while (true) {
                                        while (true) {
                                            Label_0617: {
                                                if (!dj) {
                                                    break Label_0617;
                                                }
                                                n10 = t - this.t;
                                                n11 = c2;
                                                array[n11] = (byte)this.l(t, n4);
                                                array2[n11] = this.k(t, n4);
                                                this.a[t][n4].b(4);
                                                ++t;
                                            }
                                            if (t <= this.u) {
                                                continue Label_0571_Outer;
                                            }
                                            break;
                                        }
                                        c2 = (n.c(array, q) ? 1 : 0);
                                        if (dj) {
                                            continue;
                                        }
                                        break;
                                    }
                                    if (c2 != 0) {
                                        n12 = 0;
                                        n13 = 0;
                                        while (true) {
                                            Label_0693: {
                                                if (!dj) {
                                                    break Label_0693;
                                                }
                                                if (n14 == 0) {
                                                    b3 = array2[n13];
                                                }
                                                f2 = u.f(array[n13]);
                                                n12 += f2;
                                                this.o += f2;
                                                ++n13;
                                            }
                                            if (n13 < q) {
                                                continue;
                                            }
                                            this.w[this.v++] = this.t;
                                            this.w[this.v++] = n4;
                                            this.w[this.v++] = this.u;
                                            this.w[this.v++] = n4;
                                            this.w[this.v++] = n12;
                                            n14 = q;
                                            if (dj) {
                                                continue;
                                            }
                                            break;
                                        }
                                        if (n14 == 6 && !b) {
                                            n12 += 100;
                                            this.p += 100;
                                        }
                                        n2 += n12;
                                        this.n = q;
                                    }
                                }
                            }
                            ++n4;
                        }
                        if (n4 < this.c) {
                            continue;
                        }
                        break;
                    }
                    ++n3;
                }
                if (n3 < this.b) {
                    continue;
                }
                break;
            }
        }
        return n2;
    }
    
    private boolean a(final int n, final int n2, final int n3, final int n4) {
        final boolean dj = p.dJ;
        int n5 = n3;
        while (true) {
            while (true) {
                Label_0033: {
                    if (!dj) {
                        break Label_0033;
                    }
                    final byte[] x = this.x;
                    final byte[] x2;
                    final int n6;
                    x2[n6] = (byte)this.l(n, n5);
                    ++n5;
                }
                if (n5 <= n4) {
                    continue;
                }
                break;
            }
            final byte[] x2 = this.x;
            final int n6 = n4 - n3 + 1;
            if (!dj) {
                return n.c(x2, n6);
            }
            continue;
        }
    }
    
    private boolean b(final int n, final int n2, final int n3, final int n4) {
        final boolean dj = p.dJ;
        int n5 = n3;
        while (true) {
            while (true) {
                Label_0033: {
                    if (!dj) {
                        break Label_0033;
                    }
                    final byte[] x = this.x;
                    final byte[] x2;
                    final int n6;
                    x2[n6] = (byte)this.l(n5, n2);
                    ++n5;
                }
                if (n5 <= n4) {
                    continue;
                }
                break;
            }
            final byte[] x2 = this.x;
            final int n6 = n4 - n3 + 1;
            if (!dj) {
                return n.c(x2, n6);
            }
            continue;
        }
    }
    
    public boolean r(final int n, final int n2) {
        this.p(n, n2);
        return this.a(n, n2, this.r, this.s);
    }
    
    public boolean s(final int n, final int n2) {
        this.q(n, n2);
        return this.b(n, n2, this.t, this.u);
    }
    
    public int i() {
        return u.a(this.x, this.s - this.r + 1);
    }
    
    public int j() {
        return u.a(this.x, this.u - this.t + 1);
    }
    
    public boolean g(final int n) {
        final boolean dj = p.dJ;
        int n2 = -1;
        int n3 = -1;
        int n4 = 0;
        int n6 = 0;
        int n7;
        int n8;
        while (true) {
            Label_0072: {
                if (!dj) {
                    break Label_0072;
                }
                int n5 = n6;
                while (true) {
                    Label_0060: {
                        if (!dj) {
                            break Label_0060;
                        }
                        if (this.l(n4, n5) != -1) {
                            n2 = n4;
                            n3 = n5;
                            if (this.h(n4, n5) != n) {
                                return false;
                            }
                        }
                        ++n5;
                    }
                    if (n5 < this.c) {
                        continue;
                    }
                    break;
                }
                ++n4;
            }
            if (n4 < this.b) {
                continue;
            }
            this.g();
            this.o(n2, n3);
            n7 = -1;
            n8 = -1;
            n6 = 0;
            if (dj) {
                continue;
            }
            break;
        }
        int n9 = n6;
        int n11 = 0;
        while (true) {
            Label_0165: {
                if (!dj) {
                    break Label_0165;
                }
                int n10 = n11;
                while (true) {
                    Label_0153: {
                        if (!dj) {
                            break Label_0153;
                        }
                        if (this.l(n9, n10) != -1 && !this.c(n9, n10, 1)) {
                            n7 = n9;
                            n8 = n10;
                        }
                        ++n10;
                    }
                    if (n10 < this.c) {
                        continue;
                    }
                    break;
                }
                ++n9;
            }
            if (n9 < this.b) {
                continue;
            }
            n11 = n7;
            if (dj) {
                continue;
            }
            break;
        }
        if (n11 == -1) {
            return false;
        }
        final int p = this.p(n2, n3);
        final int q = this.q(n2, n3);
        if ((p != 1 || q < 3) && (p < 3 || q != 1)) {
            return false;
        }
        if (p > 1 && !this.a(n2, n3, this.r, this.s)) {
            return false;
        }
        if (q > 1 && !this.b(n2, n3, this.t, this.u)) {
            return false;
        }
        final int p2 = this.p(n7, n8);
        final int q2 = this.q(n7, n8);
        return ((p2 == 1 && q2 >= 3) || (p2 >= 3 && q2 == 1)) && (p2 <= 1 || this.a(n7, n8, this.r, this.s)) && (q2 <= 1 || this.b(n7, n8, this.t, this.u));
    }
    
    private void t(final int n, final int n2) {
        if (this.l(n, n2) != -1 && this.r(n, n2)) {
            int r = this.r;
            while (true) {
                Label_0041: {
                    if (!p.dJ) {
                        break Label_0041;
                    }
                    this.d(n, r, 32);
                    ++r;
                }
                if (r <= this.s) {
                    continue;
                }
                break;
            }
        }
    }
    
    private void u(final int n, final int n2) {
        if (this.l(n, n2) != -1 && this.s(n, n2)) {
            int t = this.t;
            while (true) {
                Label_0041: {
                    if (!p.dJ) {
                        break Label_0041;
                    }
                    this.d(t, n2, 32);
                    ++t;
                }
                if (t <= this.u) {
                    continue;
                }
                break;
            }
        }
    }
    
    public void h(final int n) {
        this.e(32);
        if (n == 0) {
            this.t(this.j, this.k);
            this.u(this.j, this.k);
        }
        if (n == 1) {
            this.t(this.h, this.i - 1);
            this.t(this.h, this.i + 1);
            this.u(this.h - 1, this.i);
            this.u(this.h + 1, this.i);
        }
    }
}
