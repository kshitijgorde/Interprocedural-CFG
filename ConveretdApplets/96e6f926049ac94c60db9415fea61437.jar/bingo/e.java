// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.nb;
import neat.r;
import neat.g;
import neat.system.graphics.renderer.m;
import neat.kb;
import neat.system.f;
import neat.system.cb;

public class e implements cb
{
    private static f a;
    private s b;
    private rb c;
    private boolean d;
    private boolean e;
    private int f;
    private kb g;
    private qb h;
    private int i;
    private float j;
    private int k;
    private int l;
    private boolean m;
    private int n;
    private int o;
    private kb p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private int u;
    private int v;
    private boolean w;
    private int x;
    private int y;
    private int z;
    private boolean A;
    private int B;
    private int C;
    private m D;
    private m E;
    private m F;
    private m G;
    private m H;
    private m I;
    private int J;
    private int K;
    private boolean L;
    private g M;
    private int N;
    private int O;
    private float P;
    private boolean Q;
    private int R;
    private int S;
    private static /* synthetic */ Class T;
    private static String U;
    
    public void a(final rb c) {
        this.a();
        if (c == null) {
            return;
        }
        this.c = c;
        this.o();
        this.q();
        this.A();
    }
    
    public void a() {
        this.i();
        this.r();
        this.B();
        if (this.c != null) {
            this.c.f();
            this.c = null;
        }
    }
    
    public void b() {
        if (this.d) {
            return;
        }
        this.d = true;
    }
    
    public void c() {
        if (!this.d) {
            return;
        }
        this.d = false;
        this.i();
    }
    
    public void a(final boolean e) {
        if (this.e == e) {
            return;
        }
        this.e = e;
        this.x();
        this.F();
    }
    
    public void a(final int n) {
        this.c(n);
        this.b(n);
        this.f(n);
    }
    
    public void d() {
        this.y();
        this.G();
    }
    
    public void a(final int n, final int n2) {
        this.i(n, n2);
        this.k(n, n2);
    }
    
    public boolean b(final int n, final int n2) {
        if (!this.m()) {
            return false;
        }
        if (this.l(n, n2)) {
            return false;
        }
        if (this.j(n, n2)) {
            return true;
        }
        if (this.f != 3) {
            return false;
        }
        if (this.h != null && (this.h.e == 4 || this.h.e == 3)) {
            return false;
        }
        this.e();
        return false;
    }
    
    public void a(final kb kb) {
        this.a(kb, false);
    }
    
    private boolean a(final qb qb) {
        if (qb == null) {
            return false;
        }
        if (!this.b.Fb()) {
            return false;
        }
        if (qb.z >= 0) {
            if ((this.k & 1 << qb.z) != 0x0) {
                return false;
            }
            if (!this.b.F(qb.z)) {
                return false;
            }
        }
        return true;
    }
    
    public void a(final kb kb, final boolean b) {
        if (!this.d) {
            return;
        }
        if (this.f != 0 && this.f != 4 && this.g != null && this.g.equals(kb)) {
            return;
        }
        if (this.c == null) {
            return;
        }
        qb h = (qb)this.c.qb.c(kb);
        if (h == null) {
            h = (qb)this.c.rb.c(kb);
        }
        if (h == null) {
            return;
        }
        if (!b && !this.a(h)) {
            return;
        }
        if (h.e != 10 && (this.b.vb() || this.b.yb())) {
            return;
        }
        this.i();
        this.g = kb.b();
        this.h = h;
        this.i = 0;
        this.j = 0.0f;
        this.m = false;
        int l = 1 << this.h.z;
        switch (this.h.e) {
            case 0:
            case 1:
            case 5:
            case 6:
            case 7:
            case 8: {
                this.f = 1;
                this.b.b(true);
                break;
            }
            case 2: {
                this.f = 1;
                break;
            }
            case 3:
            case 4: {
                this.f = 1;
                l = 0;
                break;
            }
            case 10:
            case 11: {
                this.f = 1;
                this.m = true;
                break;
            }
            default: {
                this.f = 2;
                this.b.c(this.c.e);
                break;
            }
        }
        this.l = l;
        this.s();
        this.v();
        this.z();
        this.D();
        this.x();
    }
    
    public void e() {
        if (this.f == 4 || this.f == 0) {
            return;
        }
        this.f = 4;
        this.i = 0;
        this.x();
        this.E();
    }
    
    public void i() {
        this.n();
        if (this.f == 0) {
            return;
        }
        this.f = 0;
        if (this.g != null) {
            this.g.f();
            this.g = null;
        }
        this.h = null;
        this.i = 0;
        this.j = 0.0f;
        this.t();
        this.C();
        this.b.b(false);
        this.x();
    }
    
    public boolean j() {
        return this.f != 0;
    }
    
    private void b(final int n) {
        this.i += n;
        switch (this.f) {
            case 1: {
                if (this.h == null) {
                    break;
                }
                if (!this.e && !this.m) {
                    break;
                }
                switch (this.h.e) {
                    case 2: {
                        if (!this.q) {
                            break;
                        }
                        this.b.b(true);
                        this.f = 2;
                        this.i = 0;
                        this.b.c(this.c.e);
                        break;
                    }
                    default: {
                        this.f = 2;
                        this.i = 0;
                        this.b.c(this.c.e);
                        break;
                    }
                }
                break;
            }
            case 2: {
                this.j += n / 500.0f;
                if (this.j >= 1.0f) {
                    this.f = 3;
                    this.i = 0;
                    this.j = 1.0f;
                    if (this.l != 0) {
                        this.k |= this.l;
                    }
                }
                this.x();
                break;
            }
            case 3: {
                if (this.i >= 50000) {
                    this.e();
                    break;
                }
                break;
            }
            case 4: {
                this.j -= n / 500.0f;
                if (this.j <= 0.0f) {
                    this.i();
                    break;
                }
                this.x();
                break;
            }
        }
        if (this.f >= 2 && this.f < 4) {
            switch (this.h.e) {
                case 1: {
                    final i l = this.l();
                    if (l == null) {
                        this.e();
                        break;
                    }
                    this.a(0, l.e(), l.f(), l.h(), l.h(), false, true, 1);
                    final c f = this.b.f(l);
                    if (f != null) {
                        this.a(1, f.c + 15, f.d + 15, 15, 15, true, true, 1);
                    }
                    this.u();
                    break;
                }
                case 2: {
                    if (!this.q || this.h == null) {
                        this.e();
                        break;
                    }
                    this.a(0, this.h.u, this.h.v, this.h.w, this.h.x, true, true, 2);
                    break;
                }
                case 3: {
                    if (this.h == null) {
                        this.e();
                        break;
                    }
                    break;
                }
                case 8: {
                    if (this.h == null) {
                        this.e();
                        break;
                    }
                    this.a(0, this.h.u, this.h.v, this.h.w, this.h.x, false, true, 2);
                    break;
                }
                case 7: {
                    if (this.h == null) {
                        this.e();
                        break;
                    }
                    final j c = this.b.C();
                    if (c != null) {
                        this.a(0, c.e(), c.f(), this.h.w, this.h.x, false, true, 1);
                    }
                    this.u();
                    break;
                }
            }
        }
    }
    
    private float k() {
        if (this.f == 0) {
            return 0.0f;
        }
        return this.j;
    }
    
    private i b(final qb qb) {
        if (qb == null) {
            return null;
        }
        i i = null;
        int a = this.b.a(qb.f);
        if (a < 0) {
            a = 0;
        }
        if (qb.g >= 0 && a != 1 && a != 2) {
            if (qb.g == 1000) {
                i = this.b.E();
            }
            else {
                i = this.b.j(qb.g);
            }
        }
        else if (a != 0) {
            i = this.b.l(a);
        }
        return i;
    }
    
    private i l() {
        return this.b(this.h);
    }
    
    public boolean m() {
        return this.f >= 2 && this.f <= 4;
    }
    
    private void n() {
        this.o = 0;
        this.n = 0;
        if (this.p != null) {
            this.p.f();
            this.p = null;
        }
    }
    
    private void c(final int n) {
        this.n -= n;
        if (this.n > 0) {
            return;
        }
        this.n = 1000;
        if (!this.d || this.c == null) {
            return;
        }
        if (this.c.rb == null) {
            return;
        }
        if (this.j()) {
            return;
        }
        if (this.o == 0) {
            if (this.b.Y()) {
                return;
            }
            boolean b = true;
            final r b2 = this.c.rb.b();
            while (b2.a()) {
                final kb kb = (kb)b2.b();
                final int c = this.c((qb)this.c.rb.c(kb));
                if (c == 0) {
                    continue;
                }
                b = false;
                if (c == 1) {
                    continue;
                }
                this.o = 1;
                if (this.p != null) {
                    this.p.f();
                    this.p = null;
                }
                this.p = kb.b();
                break;
            }
            b2.f();
            if (b) {
                this.o = 2;
            }
        }
        else if (this.o == 1) {
            if (this.p != null) {
                final qb qb = (qb)this.c.rb.c(this.p);
                if (qb != null) {
                    if (this.c(qb) >= 2) {
                        if (this.p != null) {
                            final kb b3 = this.p.b();
                            this.a(b3);
                            b3.f();
                        }
                    }
                }
            }
            this.n();
        }
    }
    
    private int c(final qb qb) {
        if (qb.e != 1) {
            return 0;
        }
        if (!this.a(qb)) {
            return 0;
        }
        int a = this.b.a(qb.f);
        if (a < 0) {
            a = 0;
        }
        if (!this.b.q(a)) {
            return 0;
        }
        final i b = this.b(qb);
        if (b == null) {
            return 1;
        }
        int h = -1;
        if (qb.h >= 0) {
            final bingo.g b2 = b.b();
            if (b2 != null) {
                h = b2.c() + b2.g(qb.h);
            }
        }
        else {
            final k h2 = this.b.h(b.a());
            if (h2 != null) {
                h = h2.h();
            }
        }
        if (h >= 0 && b.c() < h) {
            return 1;
        }
        return 2;
    }
    
    private void o() {
        this.p();
        this.u = 7;
        this.v = 5;
        this.w = false;
    }
    
    public void p() {
        this.r = false;
        this.s = false;
        this.t = false;
        this.x = -1;
    }
    
    public void a(final i i) {
    }
    
    public void b(final i i) {
    }
    
    public void a(final int x, final i i) {
        if (this.u < 7) {
            this.u = 0;
        }
        if (this.v < 5) {
            this.v = 0;
        }
        if (!this.w) {
            final int b = i.b();
            if (b != 1 && b != 2) {
                this.x = x;
            }
            else {
                this.x = -1;
            }
        }
        if (this.j() && this.g != null && (this.g.equals(this.c.tb) || this.g.equals(this.c.sb))) {
            this.e();
        }
    }
    
    public void d(final int n) {
        if (this.j()) {
            return;
        }
        if (this.c.Bb != null && !this.w && this.x >= 0 && this.x == n) {
            this.a(this.c.Bb);
            if (this.j()) {
                this.w = true;
            }
            return;
        }
        if (this.c.tb != null && ++this.u >= 7) {
            this.u = 0;
            this.a(this.c.tb);
        }
    }
    
    public void c(final int n, final int n2) {
        if (!this.b.c(n, n2)) {
            return;
        }
        if (this.c.sb == null) {
            return;
        }
        if (++this.v >= 5) {
            this.v = 0;
            if (!this.j()) {
                this.a(this.c.sb);
            }
        }
    }
    
    public void a(final int n, final int n2, final int n3) {
        if (this.c.ub == null) {
            return;
        }
        if (this.b.eb()) {
            return;
        }
        if (this.j()) {
            return;
        }
        this.a(this.c.ub);
    }
    
    public void b(final int n, final int n2, final int n3) {
        if (this.c.vb == null) {
            return;
        }
        if (this.b.eb()) {
            return;
        }
        if (this.j()) {
            return;
        }
        this.a(this.c.vb);
    }
    
    public void d(final int n, final int n2) {
        if (this.j()) {
            return;
        }
        if (!this.t && n2 < this.c.Db && this.c.Cb != null) {
            this.a(this.c.Cb);
            if (this.j()) {
                this.t = true;
                return;
            }
        }
        if (!this.r && n2 < this.c.xb) {
            if (this.c.wb != null) {
                this.a(this.c.wb);
            }
            if (this.j()) {
                this.r = true;
            }
        }
    }
    
    public void e(final int n) {
        if (this.s) {
            return;
        }
        if (n < this.c.Ab) {
            return;
        }
    }
    
    private void q() {
        this.r();
        if (this.c == null) {
            return;
        }
        this.y = -1;
        this.z = -1;
        this.A = false;
        if (this.c.f != null) {
            this.D = this.b.a(this.c.f);
            if (this.D != null) {
                this.D.a(false);
            }
        }
        if (this.c.i != null) {
            this.E = this.b.a(this.c.i);
            if (this.E != null) {
                this.E.a(false);
            }
        }
        if (this.c.s != null) {
            this.F = this.b.a(this.c.s);
            if (this.F != null) {
                this.F.a(false);
            }
        }
        if (this.c.v != null) {
            this.G = this.b.a(this.c.v);
            if (this.G != null) {
                this.G.a(false);
            }
        }
        this.z();
    }
    
    private void r() {
        this.t();
        if (this.D != null) {
            this.b.a(this.D);
            this.D = null;
        }
        if (this.E != null) {
            this.b.a(this.E);
            this.E = null;
        }
        if (this.F != null) {
            this.b.a(this.F);
            this.F = null;
        }
        if (this.G != null) {
            this.b.a(this.G);
            this.G = null;
        }
    }
    
    private void s() {
        if (this.c == null || this.h == null) {
            return;
        }
        this.t();
        if (this.h.k != null) {
            this.H = this.b.a(this.h.k);
            if (this.H != null) {
                this.H.a(false);
            }
        }
        if (this.h.n != null) {
            this.I = this.b.a(this.h.n);
            if (this.I != null) {
                this.I.a(false);
            }
        }
    }
    
    private void t() {
        if (this.H != null) {
            this.b.a(this.H);
            this.H = null;
        }
        if (this.I != null) {
            this.b.a(this.I);
            this.I = null;
        }
    }
    
    public void e(final int b, final int c) {
        if (this.A && this.B == b && this.C == c) {
            return;
        }
        this.A = true;
        this.B = b;
        this.C = c;
        this.w();
    }
    
    public void u() {
        if (this.c == null || this.h == null) {
            return;
        }
        if (this.c.r == null) {
            return;
        }
        final neat.i b = nb.b(this.c.r, ',');
        if (b == null) {
            return;
        }
        int n = -1;
        int n2 = -1;
        int n3 = -1;
        int n4 = -1;
        int n5 = 0;
        for (int i = 0; i < b.i() - 1; i += 2) {
            final int a = nb.a((kb)b.a(i), -1);
            final int a2 = nb.a((kb)b.a(i + 1), -1);
            if (a >= 0 && a2 >= 0) {
                if (n < 0 || n2 < 0) {
                    n = a;
                    n2 = a2;
                }
                if (this.a(a + this.c.l, a2 + this.c.m, a + this.c.n, a2 + this.c.o)) {
                    if (n5 != 0) {
                        continue;
                    }
                    if (this.a(a + this.c.l + 20, a2 + this.c.m + 20, a + this.c.n - 20, a2 + this.c.o - 20)) {
                        continue;
                    }
                    n5 = 1;
                }
                else {
                    n5 = 0;
                }
                n3 = a;
                n4 = a2;
                if (n5 == 0) {
                    break;
                }
            }
        }
        b.j();
        b.f();
        if (n3 < 0 || n4 < 0) {
            if (n < 0 || n2 < 0) {
                return;
            }
            n3 = n;
            n4 = n2;
        }
        this.e(n3, n4);
    }
    
    private void v() {
        this.A = false;
        this.w();
    }
    
    private void w() {
        if (this.A) {
            this.y = this.B;
            this.z = this.C;
        }
        else {
            if (this.c == null) {
                return;
            }
            this.y = this.c.p;
            this.z = this.c.q;
            if (this.h != null && this.h.i >= 0) {
                this.y = this.h.i;
                this.z = this.h.j;
            }
        }
        this.x();
    }
    
    private void x() {
        this.L = true;
    }
    
    private void y() {
        if (!this.L) {
            return;
        }
        this.L = false;
        boolean b = this.e || this.m;
        final float k = this.k();
        if (k == 0.0f) {
            b = false;
        }
        if (this.c != null) {
            if (this.D != null) {
                this.D.a(b);
                if (b) {
                    this.D.a(this.y + this.c.g, this.z + this.c.h);
                    this.D.a(k, 1.0f - k);
                }
            }
            if (this.E != null) {
                this.E.a(b);
                if (b) {
                    this.E.a(this.y + this.c.j, this.z + this.c.k);
                    this.E.a(-k, 1.0f);
                }
            }
            if (this.F != null) {
                this.F.a(b);
                if (b) {
                    final int n = this.y + this.c.t;
                    final int n2 = this.z + this.c.u;
                    this.F.a(n, n2);
                    this.F.a(k, 1.0f - k);
                    if (this.F.p() > 1) {
                        int n3 = 0;
                        if (this.J >= n && this.K >= n2 && this.J < n + this.F.e() && this.K < n2 + this.F.f()) {
                            n3 = 1;
                        }
                        if (this.F.q() != n3) {
                            this.F.b(n3);
                        }
                    }
                }
            }
            if (this.G != null) {
                this.G.a(b);
                if (b) {
                    final int n4 = this.y + this.c.w;
                    final int n5 = this.z + this.c.x;
                    this.G.a(n4, n5);
                    this.G.a(k, 1.0f - k);
                    if (this.G.p() > 1) {
                        int n6 = 0;
                        if (this.J >= n4 && this.K >= n5 && this.J < n4 + this.G.e() && this.K < n5 + this.G.f()) {
                            n6 = 1;
                        }
                        if (this.G.q() != n6) {
                            this.G.b(n6);
                        }
                    }
                }
            }
        }
        if (this.h != null) {
            if (this.H != null) {
                this.H.a(b);
                if (b) {
                    this.H.a(this.y + this.h.l, this.z + this.h.m);
                    this.H.a(k, 1.0f - k);
                }
            }
            if (this.I != null) {
                this.I.a(b);
                if (b) {
                    this.I.a(this.y + this.h.o, this.z + this.h.p);
                    this.I.a(k, 1.0f - k);
                }
            }
        }
    }
    
    private boolean f(final int n, final int n2) {
        if (!this.e && !this.m) {
            return false;
        }
        if (this.H != null) {
            final int n3 = this.y + this.h.l;
            final int n4 = this.z + this.h.m;
            if (n >= n3 && n2 >= n4 && n < n3 + this.H.e() && n2 < n4 + this.H.f()) {
                return true;
            }
        }
        else if (this.D != null) {
            final int n5 = this.y + this.c.g;
            final int n6 = this.z + this.c.h;
            if (n >= n5 && n2 >= n6 && n < n5 + this.D.e() && n2 < n6 + this.D.f()) {
                return true;
            }
        }
        return false;
    }
    
    private boolean g(final int n, final int n2) {
        if (!this.e && !this.m) {
            return false;
        }
        if (this.F != null) {
            final int n3 = this.y + this.c.t;
            final int n4 = this.z + this.c.u;
            if (n >= n3 && n2 >= n4 && n < n3 + this.F.e() && n2 < n4 + this.F.f()) {
                return true;
            }
        }
        return false;
    }
    
    private boolean h(final int n, final int n2) {
        if (!this.e && !this.m) {
            return false;
        }
        if (this.G != null) {
            final int n3 = this.y + this.c.w;
            final int n4 = this.z + this.c.x;
            if (n >= n3 && n2 >= n4 && n < n3 + this.G.e() && n2 < n4 + this.G.f()) {
                return true;
            }
        }
        return false;
    }
    
    private void z() {
        this.J = -1;
        this.K = -1;
    }
    
    private void i(final int j, final int k) {
        if (this.J != j || this.K != k) {
            this.J = j;
            this.K = k;
            this.x();
        }
    }
    
    private boolean j(final int n, final int n2) {
        if (this.f(n, n2)) {
            if (this.j() && this.f != 3) {
                return true;
            }
            this.e();
            return true;
        }
        else {
            if (this.j()) {
                if (this.f == 2) {
                    if (this.j <= 0.5f) {
                        return true;
                    }
                }
                else if (this.f != 3) {
                    return true;
                }
            }
            if (this.g(n, n2)) {
                if (this.h != null) {
                    if (this.h.z >= 0) {
                        this.b.a(this.h.z, false);
                    }
                    else {
                        this.b.g(false);
                    }
                }
                this.e();
                return true;
            }
            if (this.h(n, n2)) {
                this.e();
                return true;
            }
            return false;
        }
    }
    
    private void A() {
        this.B();
        this.M = neat.g.j();
        this.N = 0;
        this.H();
    }
    
    private void B() {
        this.C();
        if (this.M != null) {
            this.M.f();
            this.M = null;
        }
        this.N = 0;
    }
    
    private void a(final int n, final int b, final int c, final int d, final int e, final boolean b2, final boolean b3, final int n2) {
        if (this.M == null || this.c == null) {
            return;
        }
        if (n >= this.M.f()) {
            this.M.c(n + 1);
        }
        bingo.f a = (bingo.f)this.M.a(n);
        if (a == null) {
            a = bingo.f.a();
            if (n2 == 1) {
                if (this.c.B != null) {
                    a.i = this.b.a(this.c.B);
                    if (a.i != null) {
                        a.i.a(false);
                    }
                }
                if (this.c.E != null) {
                    a.j = this.b.a(this.c.E);
                    if (a.j != null) {
                        a.j.a(false);
                    }
                }
                if (this.c.V != null) {
                    a.m = this.b.a(this.c.V);
                    if (a.m != null) {
                        a.m.a(false);
                    }
                }
                if (this.c.Y != null) {
                    a.n = this.b.a(this.c.Y);
                    if (a.n != null) {
                        a.n.a(false);
                    }
                }
            }
            else {
                if (this.c.L != null) {
                    a.i = this.b.a(this.c.L);
                    if (a.i != null) {
                        a.i.a(false);
                    }
                }
                if (this.c.O != null) {
                    a.j = this.b.a(this.c.O);
                    if (a.j != null) {
                        a.j.a(false);
                    }
                }
                if (this.c.fb != null) {
                    a.m = this.b.a(this.c.fb);
                    if (a.m != null) {
                        a.m.a(false);
                    }
                }
                if (this.c.ib != null) {
                    a.n = this.b.a(this.c.ib);
                    if (a.n != null) {
                        a.n.a(false);
                    }
                }
            }
            this.M.a(n, a);
        }
        a.b = b;
        a.c = c;
        a.d = d;
        a.e = e;
        if (n2 == 1) {
            a.g = b2;
            a.k = b3;
        }
        else {
            a.h = b2;
            a.l = b3;
        }
        this.F();
    }
    
    private boolean a(final int n, final int n2, final int n3, final int n4) {
        if (this.c == null) {
            return false;
        }
        boolean b = false;
        if (this.M != null) {
            final r d = this.M.d();
            while (d.a()) {
                final bingo.f f = (bingo.f)d.b();
                if (f.g) {
                    final int n5 = f.b + this.c.H;
                    final int n6 = f.c + this.c.I;
                    final int n7 = f.b + this.c.J;
                    final int n8 = f.c + this.c.K;
                    if (n5 < n3 && n6 < n4 && n7 > n && n8 > n2) {
                        b = true;
                        break;
                    }
                }
                if (f.h) {
                    final int n9 = f.b + this.c.R;
                    final int n10 = f.c + this.c.S;
                    final int n11 = f.b + this.c.T;
                    final int n12 = f.c + this.c.U;
                    if (n9 < n3 && n10 < n4 && n11 > n && n12 > n2) {
                        b = true;
                        break;
                    }
                }
                if (f.k) {
                    final int n13 = f.b + this.c.bb;
                    final int n14 = f.c + this.c.cb;
                    final int n15 = f.b + this.c.db;
                    final int n16 = f.c + this.c.eb;
                    if (n13 < n3 && n14 < n4 && n15 > n && n16 > n2) {
                        b = true;
                        break;
                    }
                }
                if (f.l) {
                    final int n17 = f.b + this.c.lb;
                    final int n18 = f.c + this.c.mb;
                    final int n19 = f.b + this.c.nb;
                    final int n20 = f.c + this.c.ob;
                    if (n17 < n3 && n18 < n4 && n19 > n && n20 > n2) {
                        b = true;
                        break;
                    }
                    continue;
                }
            }
            d.f();
        }
        return b;
    }
    
    private void C() {
        if (this.M != null) {
            final r d = this.M.d();
            while (d.a()) {
                final bingo.f f = (bingo.f)d.b();
                if (f.i != null) {
                    this.b.a(f.i);
                    f.i = null;
                }
                if (f.j != null) {
                    this.b.a(f.j);
                    f.j = null;
                }
                if (f.m != null) {
                    this.b.a(f.m);
                    f.m = null;
                }
                if (f.n != null) {
                    this.b.a(f.n);
                    f.n = null;
                }
            }
            d.f();
            this.M.i();
        }
    }
    
    private void D() {
        if (this.M == null) {
            return;
        }
        if (this.N != 0) {
            if (this.N == 1) {
                return;
            }
            this.E();
        }
        else {
            this.P = 0.0f;
        }
        this.N = 1;
        this.O = 0;
        this.F();
    }
    
    private void E() {
        if (this.M == null) {
            return;
        }
        if (this.N == 3 || this.N == 0) {
            return;
        }
        this.N = 3;
        this.O = 0;
        this.F();
    }
    
    private void f(final int n) {
        if (this.M == null || this.c == null) {
            return;
        }
        this.O += n;
        switch (this.N) {
            case 0: {
                return;
            }
            case 1: {
                this.P += n / 1000.0f;
                if (this.P >= 1.0f) {
                    this.P = 1.0f;
                    this.N = 2;
                }
                this.F();
            }
            case 3: {
                this.P -= n / 500.0f;
                if (this.P <= 0.0f) {
                    this.P = 0.0f;
                    this.N = 0;
                    this.E();
                }
                this.F();
                break;
            }
        }
        final r d = this.M.d();
        while (d.a()) {
            final bingo.f f = (bingo.f)d.b();
            f.f += 6.2831855f * n / this.c.A;
        }
        d.f();
        this.F();
    }
    
    private void F() {
        this.Q = true;
    }
    
    private void G() {
        if (this.M == null || this.c == null) {
            return;
        }
        if (!this.Q) {
            return;
        }
        this.Q = false;
        float p = 0.0f;
        if (this.N != 0) {
            p = this.P;
        }
        final boolean b = p != 0.0f && this.e;
        final r d = this.M.d();
        while (d.a()) {
            final bingo.f f = (bingo.f)d.b();
            if (!b) {
                if (f.i != null) {
                    f.i.a(false);
                }
                if (f.j != null) {
                    f.j.a(false);
                }
                if (f.m != null) {
                    f.m.a(false);
                }
                if (f.n == null) {
                    continue;
                }
                f.n.a(false);
            }
            else {
                final int n = (int)(Math.sin(f.f) * this.c.z);
                if (!f.g && !f.h) {
                    if (f.i != null) {
                        f.i.a(false);
                    }
                    if (f.j != null) {
                        f.j.a(false);
                    }
                }
                else {
                    if (f.i != null) {
                        if (f.i.p() > 1) {
                            int n2 = 0;
                            if (this.R >= f.b - f.d && this.S >= f.c - f.e && this.R < f.b + f.d && this.S < f.c + f.e) {
                                n2 = 1;
                            }
                            if (f.i.q() != n2) {
                                f.i.b(n2);
                            }
                        }
                        f.i.a(f.b + (f.g ? this.c.C : this.c.M), f.c + (f.g ? (this.c.D - f.e) : (this.c.N + f.e)) + n);
                        f.i.a(p, 1.0f - p);
                        f.i.a(true);
                    }
                    if (f.j != null) {
                        f.j.a(f.b + (f.g ? this.c.F : this.c.P), f.c + (f.g ? (this.c.G - f.e) : (this.c.Q + f.e)) + n);
                        f.j.a(-p, 1.0f);
                        f.j.a(true);
                    }
                }
                if (!f.k && !f.l) {
                    if (f.m != null) {
                        f.m.a(false);
                    }
                    if (f.n == null) {
                        continue;
                    }
                    f.n.a(false);
                }
                else {
                    if (f.m != null) {
                        f.m.a(f.b + (f.k ? this.c.W : this.c.gb), f.c + (f.k ? (this.c.X - f.e) : (this.c.hb + f.e)) + n);
                        f.m.a(p, 1.0f - p);
                        f.m.a(true);
                    }
                    if (f.n == null) {
                        continue;
                    }
                    f.n.a(f.b + (f.k ? this.c.Z : this.c.jb), f.c + (f.k ? (this.c.ab - f.e) : (this.c.kb + f.e)) + n);
                    f.n.a(-p, 1.0f);
                    f.n.a(true);
                }
            }
        }
        d.f();
    }
    
    private void H() {
        this.R = -1;
        this.S = -1;
    }
    
    private void k(final int r, final int s) {
        if (this.R != r || this.S != s) {
            this.R = r;
            this.S = s;
            this.F();
        }
    }
    
    private boolean l(final int n, final int n2) {
        if (this.M == null) {
            return false;
        }
        final r d = this.M.d();
        while (d.a()) {
            final bingo.f f = (bingo.f)d.b();
            if ((f.g || f.h) && n >= f.b - f.d && n2 >= f.c - f.e && n < f.b + f.d && n2 < f.c + f.e) {
                this.e();
                d.f();
                return true;
            }
        }
        d.f();
        return false;
    }
    
    public static e a(final s b) {
        final e e = (e)bingo.e.a.a();
        e.b = b;
        return e;
    }
    
    public void f() {
        bingo.e.a.a(this);
    }
    
    public void g() {
        this.d = false;
        this.e = false;
        this.f = 2;
        this.i = 0;
        this.j = 0.0f;
        this.k = 0;
        this.m = false;
        this.o = 0;
        this.n = 0;
        this.y = -1;
        this.z = -1;
        this.L = false;
        this.Q = false;
        this.q = false;
    }
    
    public void h() {
        this.a();
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
    
    public e() {
        this.b = null;
        this.c = null;
        this.g = null;
        this.h = null;
        this.p = null;
        this.D = null;
        this.E = null;
        this.F = null;
        this.G = null;
        this.H = null;
        this.I = null;
        this.M = null;
    }
    
    static {
        final char[] charArray = "''begk+".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0093: {
                if (n > 1) {
                    break Label_0093;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = 'E';
                            break;
                        }
                        case 1: {
                            c2 = 'N';
                            break;
                        }
                        case 2: {
                            c2 = '\f';
                            break;
                        }
                        case 3: {
                            c2 = '\u0002';
                            break;
                        }
                        default: {
                            c2 = '\b';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                e.U = new String(charArray).intern();
                e.a = new f((e.T != null) ? e.T : (e.T = a(e.U)));
                return;
            }
            continue;
        }
    }
}
