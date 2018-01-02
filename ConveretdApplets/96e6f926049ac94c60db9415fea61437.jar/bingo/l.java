// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.bb;
import neat.p;
import neat.r;
import neat.i;
import neat.system.graphics.renderer.m;
import neat.system.f;
import neat.system.cb;

public class l implements cb
{
    private static f a;
    private s b;
    private int c;
    private float d;
    private float e;
    private float f;
    private float g;
    private int h;
    private int i;
    private int j;
    private boolean k;
    private m l;
    private i m;
    private boolean n;
    private float o;
    private float p;
    private float q;
    private float r;
    private int s;
    private boolean t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int[] y;
    private int z;
    private g A;
    private i B;
    private boolean C;
    private float D;
    private int E;
    private int F;
    private int G;
    private int H;
    private int I;
    private float J;
    private int K;
    private int L;
    private static /* synthetic */ Class M;
    private static String[] N;
    
    public void a(final int n) {
        this.h(n);
    }
    
    public void b(final int j) {
        this.j = j;
    }
    
    public void a(final wb wb) {
        this.c = wb.e;
        if (this.c <= 0) {
            this.c = 1;
        }
        this.d = wb.f;
        this.e = wb.g;
        this.o = 0.0f;
        this.f = wb.h;
        this.g = wb.i;
        this.q = 0.0f;
        this.h = wb.j;
        this.i = wb.k;
        this.b(wb);
        this.c(wb);
    }
    
    private void b(final wb wb) {
        this.a();
    }
    
    private void a() {
    }
    
    public void a(final boolean k) {
        if (this.k == k) {
            return;
        }
        this.k = k;
    }
    
    void a(final bingo.i i) {
        if (this.A == null) {
            throw new RuntimeException(bingo.l.N[2]);
        }
        if (i.a() != this.z) {
            throw new RuntimeException(bingo.l.N[3] + this.z + bingo.l.N[4] + i.a() + ")");
        }
        final r f = this.m.f();
        while (true) {
            while (f.a()) {
                if (((bingo.i)f.b()).c() >= i.c()) {
                    this.m.a(f.d(), i);
                    f.f();
                    return;
                }
            }
            this.m.a(i);
            continue;
        }
    }
    
    void b(final bingo.i i) {
        this.m.d(i);
    }
    
    public void b() {
        this.n = true;
        this.o = 0.0f;
        this.p = 0.0f;
        this.q = 0.0f;
        this.r = 0.0f;
        this.s = 0;
    }
    
    public void c() {
        this.n = false;
    }
    
    public boolean c(final int n) {
        if (!this.n) {
            return false;
        }
        if (this.A == null) {
            return true;
        }
        if (this.m.e()) {
            return true;
        }
        boolean b = false;
        Label_0259: {
            break Label_0259;
            int i = 0;
            do {
                final int n2 = i + this.c;
                int n3 = n;
                if (n3 > n2) {
                    n3 = n2;
                }
                final p e = neat.p.e();
                e.e(n3);
                for (int j = 1; j < this.m.i(); ++j) {
                    final bingo.i k = (bingo.i)this.m.a(j);
                    final int c = k.c();
                    final bingo.i l;
                    final int a = this.A.a(c, k.h(), n3, l.h(), 1);
                    if (a == 0) {
                        break;
                    }
                    if (a == -1000000000) {
                        b = true;
                        break;
                    }
                    final int n4 = c + a;
                    e.e(n4);
                    l = k;
                    n3 = n4;
                }
                if (!b) {
                    for (int n5 = 0; n5 < e.a(); ++n5) {
                        ((bingo.i)this.m.a(n5)).b(e.d(n5));
                    }
                }
                e.f();
                if (b) {
                    return !b;
                }
                bingo.i l = (bingo.i)this.m.a(0);
                i = l.c();
            } while (i < n);
        }
        return true;
    }
    
    public boolean a(final int n, final boolean b) {
        if (!this.n) {
            return false;
        }
        if (this.A == null) {
            return true;
        }
        if (this.m.e()) {
            return true;
        }
        if (this.o == 0.0f) {
            this.p = 0.0f;
        }
        this.o += this.e * n;
        if (this.o > this.d) {
            this.o = this.d;
        }
        final int n2 = (int)this.p;
        this.p += this.o * n;
        int i = (int)this.p - n2;
        int n3 = this.m().c();
        final k h = this.b.h(this.n());
        if (h != null) {
            n3 = h.h();
        }
        boolean b2 = false;
        while (i != 0) {
            int c = i;
            if (c > this.c) {
                c = this.c;
            }
            i -= c;
            final int g = this.A.g(c);
            final p e = neat.p.e();
            boolean b3 = false;
            bingo.i j = (bingo.i)this.m.a(0);
            int c2 = j.c();
            if (c2 > n3 && b && !j.u()) {
                c2 -= g;
                if (c2 < n3) {
                    c2 = n3;
                }
                else {
                    b3 = true;
                }
            }
            e.b(0, c2);
            for (int k = 1; k < this.m.i(); ++k) {
                final bingo.i l = (bingo.i)this.m.a(k);
                int c3 = l.c();
                if ((c3 > n3 || b) && !l.u()) {
                    c3 -= g;
                    final int a = this.A.a(c3, l.h(), c2, j.h(), 1);
                    if (a == -1000000000) {
                        b2 = true;
                        break;
                    }
                    if (a > 0) {
                        c3 += a;
                    }
                    else {
                        b3 = true;
                    }
                }
                e.b(k, c3);
                c2 = c3;
                j = l;
            }
            if (!b2) {
                for (int n4 = 0; n4 < e.a(); ++n4) {
                    ((bingo.i)this.m.a(n4)).b(e.d(n4));
                }
            }
            e.f();
            if (!b3 || b2) {
                this.o = 0.0f;
                return !b2;
            }
        }
        return !b2;
    }
    
    public void d(final int s) {
        if (this.s == 0) {
            this.q = 0.0f;
        }
        if (s > this.s) {
            this.s = s;
        }
    }
    
    public boolean d() {
        return this.s > 0;
    }
    
    public boolean e(final int n) {
        if (this.n && this.A != null && !this.m.e()) {
            if (this.q == 0.0f) {
                this.r = 0.0f;
            }
            this.q += this.g * n;
            if (this.q > this.f) {
                this.q = this.f;
            }
            final int n2 = (int)this.r;
            this.r += this.q * n;
            int i = (int)this.r - n2;
            int n4;
            final int n3 = n4 = this.A.c();
            final k h = this.b.h(this.n());
            if (h != null) {
                n4 = h.h();
            }
            boolean b = false;
            while (i != 0) {
                int c = i;
                if (c > this.c) {
                    c = this.c;
                }
                i -= c;
                final int g = this.A.g(c);
                final p e = neat.p.e();
                bingo.i j = null;
                int n5 = 0;
                for (int k = 0; k < this.m.i(); ++k) {
                    final bingo.i l = (bingo.i)this.m.a(k);
                    final int c2 = l.c();
                    int n6 = c2 - g;
                    if (l.u()) {
                        n6 = c2;
                    }
                    else {
                        if (this.s <= 1 && n6 <= n4 && c2 >= n4) {
                            this.s = 0;
                            n6 = n4;
                        }
                        if (j != null && n5 > n3) {
                            final int a = this.A.a(n6, l.h(), n5, j.h(), 1);
                            if (a == -1000000000) {
                                b = true;
                                break;
                            }
                            if (a > 0) {
                                n6 += a;
                            }
                        }
                    }
                    e.b(k, n6);
                    n5 = n6;
                    j = l;
                }
                if (!b) {
                    final int n7 = (this.s == 0) ? (n4 - 1) : n3;
                    for (int n8 = e.a() - 1; n8 >= 0; --n8) {
                        final bingo.i m = (bingo.i)this.m.a(n8);
                        final int d = e.d(n8);
                        if (d > n7) {
                            m.b(d);
                        }
                        else {
                            m.b(n3);
                            if (!m.p()) {
                                this.b.a(m, 0, -1);
                                if (this.s > 1) {
                                    --this.s;
                                }
                            }
                        }
                    }
                }
                e.f();
                if (b) {
                    this.o = 0.0f;
                }
                else if (this.s != 0) {
                    continue;
                }
                return this.d();
            }
            return this.d();
        }
        this.s = 0;
        return this.d();
    }
    
    public void e() {
        this.t = false;
    }
    
    public boolean f(final int n) {
        boolean t = false;
        if (this.A != null) {
            if (!this.m.e()) {
                final int n2 = this.m.i() - 1;
                Label_0207: {
                    if (n2 >= 0) {
                        final bingo.i i = (bingo.i)this.m.a(n2);
                        if (!i.p()) {
                            final int c = i.c();
                            int d = this.A.d();
                            if (this.j != 0) {
                                d -= this.A.g(this.j);
                            }
                            if (c >= d) {
                                t = true;
                            }
                            else {
                                if (this.h < 0) {
                                    final int n3 = i.h() + this.h;
                                    if (n3 <= 0) {
                                        break Label_0207;
                                    }
                                    if (!this.A.a(c, n3, d, 1)) {
                                        break Label_0207;
                                    }
                                }
                                else if (!this.A.a(c, i.h(), d, this.h)) {
                                    break Label_0207;
                                }
                                t = true;
                            }
                        }
                    }
                }
            }
        }
        if (this.t != t && (this.t = t)) {
            this.u = 0;
        }
        if (t) {
            this.u += n;
            if (this.u >= this.i) {
                this.u = this.i;
                return true;
            }
        }
        return false;
    }
    
    public int a(final bingo.i i, int n, int n2, final int n3, final int n4) {
        if (this.A == null) {
            return 0;
        }
        if (this.m.e()) {
            return 0;
        }
        final int c = this.m.c(i);
        if (c < 0) {
            return 0;
        }
        int n5 = 0;
        final int n6 = i.h() * 3;
        int n7 = 1;
        do {
            final int n8 = n6 * n7;
            for (int n9 = 0; n9 < 2 && n > 0; --n, ++n9) {
                final int n10 = c + ((n9 == 0) ? n7 : (-n7));
                if (n10 >= 0 && n10 < this.m.i()) {
                    final bingo.i j = (bingo.i)this.m.a(n10);
                    if (!j.p()) {
                        final int n11 = i.e() - j.e();
                        final int n12 = i.f() - j.f();
                        if (n11 * n11 + n12 * n12 <= n8 * n8) {
                            this.b.a(j, n2, n4);
                            ++n5;
                        }
                    }
                }
            }
            n2 += n3;
            ++n7;
        } while (n > 0);
        return n5;
    }
    
    public int a(int n, int n2, final int n3, final int n4) {
        if (this.A == null) {
            return 0;
        }
        if (this.m.e()) {
            return 0;
        }
        int n5 = 0;
        for (int n6 = this.m.i() - 1; n6 >= 0 && n > 0; --n6) {
            final bingo.i i = (bingo.i)this.m.a(n6);
            if (!i.p()) {
                this.b.a(i, n2, n4);
                --n;
                n2 += n3;
                ++n5;
            }
        }
        return n5;
    }
    
    public int a(int d, int i, int n, final int n2, final int n3) {
        if (this.A == null) {
            return 0;
        }
        if (this.m.e()) {
            return 0;
        }
        if (d < 0) {
            d = 0;
        }
        else if (d > this.A.d()) {
            d = this.A.d();
        }
        int n4 = 0;
        while (i > 0) {
            if (this.m.i() != 0) {
                bingo.i j = null;
                int n5 = 0;
                for (int n6 = this.m.i() - 1; n6 >= 0 && i > 0; --n6) {
                    final bingo.i k = (bingo.i)this.m.a(n6);
                    if (!k.p()) {
                        int n7 = k.c() - d;
                        if (n7 < 0) {
                            n7 = -n7;
                        }
                        if (j == null || n7 < n5) {
                            j = k;
                            n5 = n7;
                        }
                    }
                }
                if (j != null) {
                    this.b.a(j, n, n3);
                    --i;
                    n += n2;
                    ++n4;
                    continue;
                }
            }
            return n4;
        }
        return n4;
    }
    
    public int f() {
        if (this.m.e()) {
            return this.A.c();
        }
        int n = ((bingo.i)this.m.a(this.m.i() - 1)).c();
        if (n > this.A.d()) {
            n = this.A.d();
        }
        return n;
    }
    
    public int g() {
        if (this.A == null) {
            return 0;
        }
        if (this.m.e()) {
            return this.A.c();
        }
        final int g = this.A.g(this.b.D());
        final int n = g * 2 * 8 / 5;
        int n2 = this.A.c() - g;
        for (int i = 0; i < this.m.i(); ++i) {
            final int c = ((bingo.i)this.m.a(i)).c();
            if (c - n2 >= n) {
                break;
            }
            n2 = c;
        }
        return n2 + g;
    }
    
    public bingo.i h() {
        bingo.i i = null;
        for (int j = this.m.i() - 1; j >= 0; --j) {
            final bingo.i k = (bingo.i)this.m.a(j);
            if (!k.p()) {
                if (k.b() == 0) {
                    if (!this.b.c(k)) {
                        i = k;
                        break;
                    }
                }
            }
        }
        return i;
    }
    
    public bingo.i g(final int n) {
        bingo.i i = null;
        for (int j = this.m.i() - 1; j >= 0; --j) {
            final bingo.i k = (bingo.i)this.m.a(j);
            if (!k.p()) {
                if (n == -1) {
                    if (k.b() == 0) {
                        continue;
                    }
                }
                else if (k.b() != n) {
                    continue;
                }
                i = k;
                break;
            }
        }
        return i;
    }
    
    public int i() {
        if (this.A == null) {
            return 0;
        }
        if (this.x == 0) {
            return 0;
        }
        return this.v;
    }
    
    public int j() {
        if (this.A == null) {
            return 0;
        }
        if (this.m.e()) {
            return this.i();
        }
        final int c = ((bingo.i)this.m.d()).c();
        if (this.x == 0) {
            return 0;
        }
        final int n = this.A.e(c) - this.w;
        if (n < 0) {
            return this.v;
        }
        if (n >= this.x) {
            return 0;
        }
        return this.y[n];
    }
    
    private void k() {
        if (this.A == null) {
            return;
        }
        final int c = this.A.c();
        final int n = this.A.d() - this.A.g(this.j);
        this.v = 0;
        this.w = this.A.e(c);
        this.x = this.A.e(n) - this.w;
        if (this.x <= 0) {
            return;
        }
        if (this.y.length < this.x) {
            this.y = new int[this.x + 500];
        }
        this.y[0] = 0;
        for (int i = 1; i < this.x; ++i) {
            this.y[i] = -1;
        }
        int n2 = c;
        final int d = this.b.D();
        while (true) {
            Label_0375: {
                break Label_0375;
                int a = 0;
                do {
                    int n3 = this.A.e(n2) - this.w + 1;
                    if (n3 < 0) {
                        n3 = 0;
                    }
                    if (n3 < this.x) {
                        int n4 = this.A.e(n2 + a) - this.w;
                        if (n4 >= n3) {
                            if (n4 >= this.x) {
                                n4 = this.x - 1;
                            }
                            for (int j = n3; j <= n4; ++j) {
                                this.y[j] = this.v;
                            }
                            n2 += a;
                            if (n2 < n) {
                                if (this.h < 0) {
                                    final int n5 = d + this.h;
                                    if (n5 > 0 && this.A.a(n2, n5, n, 1)) {
                                        break;
                                    }
                                }
                                else if (this.A.a(n2, d, n, this.h)) {
                                    break;
                                }
                                ++this.v;
                                a = this.A.a(n2, d, n2, d, 1);
                                continue;
                            }
                        }
                    }
                    if (this.v > 0) {
                        --this.v;
                    }
                    for (int k = 0; k < this.x; ++k) {
                        int n6 = this.y[k];
                        if (n6 >= 0) {
                            n6 = this.v - n6;
                        }
                        if (n6 < 0) {
                            n6 = 0;
                        }
                        this.y[k] = n6;
                    }
                    return;
                } while (a != 0 && a != -1000000000);
            }
            continue;
        }
    }
    
    public void a(final int z, final g a) {
        this.z = z;
        this.A = a;
        this.k();
    }
    
    public void l() {
        this.z = -1;
        this.A = null;
    }
    
    public g m() {
        return this.A;
    }
    
    public int n() {
        return this.z;
    }
    
    private void c(final wb wb) {
        this.o();
        if (wb.l != null) {
            final neat.bb a = wb.l.a();
            if (!(a instanceof vb)) {
                throw new RuntimeException(bingo.l.N[0] + wb.l);
            }
            final vb vb = (vb)a;
            this.B = neat.i.k();
            this.C = true;
            int n = 1;
            if (vb.j > 0) {
                n = vb.i / vb.j;
            }
            if (n <= 0) {
                n = 1;
            }
            for (int i = 0; i < n; ++i) {
                final bingo.m a2 = bingo.m.a();
                this.B.a(a2);
                a2.b = true;
                if (vb.e != null) {
                    a2.c = this.b.a(vb.e);
                    if (a2.c == null) {
                        throw new RuntimeException(bingo.l.N[1] + vb.e);
                    }
                    a2.c.a(false);
                    a2.d = a2.c.e() / 2;
                    a2.e = a2.c.f() / 2;
                }
                a2.h = vb.f;
                a2.i = vb.g;
                a2.k = vb.i;
            }
            this.D = vb.h;
            this.E = vb.j;
            this.H = vb.k;
            this.J = vb.l;
            this.L = 0;
            this.K = 10000;
            vb.f();
        }
    }
    
    private void o() {
        if (this.B != null) {
            final r f = this.B.f();
            while (f.a()) {
                final bingo.m m = (bingo.m)f.b();
                if (m.c != null) {
                    this.b.a(m.c);
                    m.c = null;
                }
            }
            f.f();
            this.B.j();
            this.B.f();
            this.B = null;
        }
    }
    
    public void p() {
        this.C = false;
        if (this.B != null) {
            final r f = this.B.f();
            while (f.a()) {
                final bingo.m m = (bingo.m)f.b();
                m.b = true;
                if (m.c != null) {
                    m.c.a(false);
                }
            }
            f.f();
        }
    }
    
    public boolean q() {
        if (this.B == null) {
            return false;
        }
        if (!this.C) {
            return false;
        }
        boolean b = false;
        final r f = this.B.f();
        while (f.a()) {
            if (!((bingo.m)f.b()).b) {
                b = true;
                break;
            }
        }
        f.f();
        return b;
    }
    
    private void h(final int n) {
        if (this.B == null) {
            return;
        }
        if (!this.C) {
            return;
        }
        if (this.K > 0) {
            if (!this.b.V()) {
                return;
            }
            int k = (int)((this.A.d() - this.f()) * this.E / this.A.g(this.H) * this.J);
            if (k < 100) {
                k = 100;
            }
            if (k > this.K) {
                k = this.K;
            }
            this.L += n;
            if (this.L < k) {
                return;
            }
            this.K = 0;
            this.L = 0;
            this.G = -1;
            this.I = 0;
            this.F = 0;
        }
        boolean b = false;
        final r f = this.B.f();
        while (f.a()) {
            final bingo.m m = (bingo.m)f.b();
            if (m.b) {
                continue;
            }
            b = true;
            final bingo.m i = m;
            i.j += n;
            if (m.j >= m.k) {
                m.b = true;
                if (m.c == null) {
                    continue;
                }
                m.c.a(false);
            }
            else {
                if (m.c == null) {
                    continue;
                }
                final float n2 = m.j / m.k;
                float n3;
                if (n2 >= this.D) {
                    n3 = (1.0f - n2) / (1.0f - this.D);
                }
                else {
                    n3 = n2 / this.D;
                }
                if (n3 < 0.0f) {
                    n3 = 0.0f;
                }
                else if (n3 > 1.0f) {
                    n3 = 1.0f;
                }
                m.c.a(m.h + (m.i - m.h) * n3, 1.0f);
            }
        }
        f.f();
        if (this.G <= this.A.d()) {
            this.I += n;
            final int g = this.A.g(this.H);
            this.F -= n;
            while (this.F <= 0) {
                this.F += this.E;
                if (this.G < 0) {
                    final int f2 = this.f();
                    final int n4 = this.A.d() - this.b.D();
                    final int n5 = n4 - f2;
                    this.G = n4 - (n5 - n5 % g);
                }
                bingo.m j = null;
                final r f3 = this.B.f();
                while (f3.a()) {
                    final bingo.m l = (bingo.m)f3.b();
                    if (l.b) {
                        j = l;
                        break;
                    }
                }
                f3.f();
                if (j == null) {
                    break;
                }
                j.b = false;
                j.j = 0;
                if (j.c != null) {
                    j.f = this.A.b(this.G);
                    j.g = this.A.c(this.G);
                    j.c.a(j.f - j.c.e() / 2, j.g - j.c.f() / 2);
                    j.c.a(j.h, 1.0f);
                    j.c.a(true);
                }
                this.G += g;
                if (this.G > this.A.d()) {
                    this.r();
                    break;
                }
            }
            return;
        }
        this.I -= n;
        if (!b) {
            this.K = 10000;
            this.L = 0;
            this.b(false);
            return;
        }
        this.b(true);
    }
    
    private void b(final boolean b) {
        final j i = this.b.i(this.z);
        if (i != null) {
            i.c(b);
        }
    }
    
    private void r() {
        final j i = this.b.i(this.z);
        if (i != null) {
            i.c();
        }
    }
    
    public static l a(final s b) {
        final l l = (l)bingo.l.a.a();
        l.b = b;
        return l;
    }
    
    public void f() {
        bingo.l.a.a(this);
    }
    
    public void g() {
        this.j = 0;
        this.c = 1;
        this.d = 0.0f;
        this.e = 0.0f;
        this.f = 0.0f;
        this.g = 0.0f;
        this.h = 0;
        this.i = 0;
        this.x = 0;
        this.z = -1;
        this.k = false;
        this.m = neat.i.k();
        this.n = false;
        this.o = 0.0f;
        this.p = 0.0f;
    }
    
    public void h() {
        this.l();
        this.m.f();
        this.m = null;
        this.a();
        this.o();
        this.b = null;
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public l() {
        this.b = null;
        this.l = null;
        this.m = null;
        this.y = new int[0];
        this.A = null;
        this.B = null;
    }
    
    static {
        final String[] n = new String[6];
        final int n2 = 0;
        final char[] charArray = "\u00036\u001c\tQ`;\u001dOA`'\u0013ZM`2\u0014H@##H".toCharArray();
        int length;
        int n4;
        final int n3 = n4 = (length = charArray.length);
        int n5 = 0;
        while (true) {
            Label_0102: {
                if (n3 > 1) {
                    break Label_0102;
                }
                length = (n4 = n5);
                do {
                    final char c = charArray[n4];
                    char c2 = '\0';
                    switch (n5 % 5) {
                        case 0: {
                            c2 = '@';
                            break;
                        }
                        case 1: {
                            c2 = 'W';
                            break;
                        }
                        case 2: {
                            c2 = 'r';
                            break;
                        }
                        case 3: {
                            c2 = '.';
                            break;
                        }
                        default: {
                            c2 = '%';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n5;
                } while (n3 == 0);
            }
            if (n3 > n5) {
                continue;
            }
            break;
        }
        n[n2] = new String(charArray).intern();
        final int n6 = 1;
        final char[] charArray2 = "\u00036\u001c\tQ`;\u001dOA`'\u0013ZM`2\u0014H@##R]U2>\u0006K\u001f".toCharArray();
        int length2;
        int n8;
        final int n7 = n8 = (length2 = charArray2.length);
        int n9 = 0;
        while (true) {
            Label_0222: {
                if (n7 > 1) {
                    break Label_0222;
                }
                length2 = (n8 = n9);
                do {
                    final char c3 = charArray2[n8];
                    char c4 = '\0';
                    switch (n9 % 5) {
                        case 0: {
                            c4 = '@';
                            break;
                        }
                        case 1: {
                            c4 = 'W';
                            break;
                        }
                        case 2: {
                            c4 = 'r';
                            break;
                        }
                        case 3: {
                            c4 = '.';
                            break;
                        }
                        default: {
                            c4 = '%';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n9;
                } while (n7 == 0);
            }
            if (n7 > n9) {
                continue;
            }
            break;
        }
        n[n6] = new String(charArray2).intern();
        final int n10 = 2;
        final char[] charArray3 = "\u0014?\u0017\\@`>\u0001\u000eK/w\u0002OQ(v".toCharArray();
        int length3;
        int n12;
        final int n11 = n12 = (length3 = charArray3.length);
        int n13 = 0;
        while (true) {
            Label_0338: {
                if (n11 > 1) {
                    break Label_0338;
                }
                length3 = (n12 = n13);
                do {
                    final char c5 = charArray3[n12];
                    char c6 = '\0';
                    switch (n13 % 5) {
                        case 0: {
                            c6 = '@';
                            break;
                        }
                        case 1: {
                            c6 = 'W';
                            break;
                        }
                        case 2: {
                            c6 = 'r';
                            break;
                        }
                        case 3: {
                            c6 = '.';
                            break;
                        }
                        default: {
                            c6 = '%';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n13;
                } while (n11 == 0);
            }
            if (n11 > n13) {
                continue;
            }
            break;
        }
        n[n10] = new String(charArray3).intern();
        final int n14 = 3;
        final char[] charArray4 = "\u00036\u001c\tQ`6\u0016J\u0005\"6\u001eB\u000548RZM)$R]Q2>\u0002K\t`5\u0017MD5$\u0017\u000eA)1\u0014KW%9\u0006\u000eU!#\u001a\u000eV%#S\u000e\r".toCharArray();
        int length4;
        int n16;
        final int n15 = n16 = (length4 = charArray4.length);
        int n17 = 0;
        while (true) {
            Label_0454: {
                if (n15 > 1) {
                    break Label_0454;
                }
                length4 = (n16 = n17);
                do {
                    final char c7 = charArray4[n16];
                    char c8 = '\0';
                    switch (n17 % 5) {
                        case 0: {
                            c8 = '@';
                            break;
                        }
                        case 1: {
                            c8 = 'W';
                            break;
                        }
                        case 2: {
                            c8 = 'r';
                            break;
                        }
                        case 3: {
                            c8 = '.';
                            break;
                        }
                        default: {
                            c8 = '%';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n17;
                } while (n15 == 0);
            }
            if (n15 > n17) {
                continue;
            }
            break;
        }
        n[n14] = new String(charArray4).intern();
        final int n18 = 4;
        final char[] charArray5 = "aj".toCharArray();
        int length5;
        int n20;
        final int n19 = n20 = (length5 = charArray5.length);
        int n21 = 0;
        while (true) {
            Label_0570: {
                if (n19 > 1) {
                    break Label_0570;
                }
                length5 = (n20 = n21);
                do {
                    final char c9 = charArray5[n20];
                    char c10 = '\0';
                    switch (n21 % 5) {
                        case 0: {
                            c10 = '@';
                            break;
                        }
                        case 1: {
                            c10 = 'W';
                            break;
                        }
                        case 2: {
                            c10 = 'r';
                            break;
                        }
                        case 3: {
                            c10 = '.';
                            break;
                        }
                        default: {
                            c10 = '%';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n21;
                } while (n19 == 0);
            }
            if (n19 > n21) {
                continue;
            }
            break;
        }
        n[n18] = new String(charArray5).intern();
        final int n22 = 5;
        final char[] charArray6 = "\">\u001cIJn;".toCharArray();
        int length6;
        int n24;
        final int n23 = n24 = (length6 = charArray6.length);
        int n25 = 0;
        while (true) {
            Label_0690: {
                if (n23 > 1) {
                    break Label_0690;
                }
                length6 = (n24 = n25);
                do {
                    final char c11 = charArray6[n24];
                    char c12 = '\0';
                    switch (n25 % 5) {
                        case 0: {
                            c12 = '@';
                            break;
                        }
                        case 1: {
                            c12 = 'W';
                            break;
                        }
                        case 2: {
                            c12 = 'r';
                            break;
                        }
                        case 3: {
                            c12 = '.';
                            break;
                        }
                        default: {
                            c12 = '%';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n25;
                } while (n23 == 0);
            }
            if (n23 <= n25) {
                n[n22] = new String(charArray6).intern();
                l.N = n;
                l.a = new f((l.M != null) ? l.M : (l.M = a(l.N[5])));
                return;
            }
            continue;
        }
    }
}
