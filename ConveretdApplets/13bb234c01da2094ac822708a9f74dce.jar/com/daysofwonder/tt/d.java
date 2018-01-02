// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.tt;

import org.slf4j.LoggerFactory;
import java.util.Iterator;
import com.daysofwonder.a.a;
import com.daysofwonder.util.y;
import com.daysofwonder.util.t;
import com.daysofwonder.a.o;
import com.daysofwonder.util.G;
import org.slf4j.Logger;
import com.daysofwonder.a.i;

public class d extends i
{
    private static final Logger k;
    private static final com.daysofwonder.tt.i l;
    private G m;
    private n n;
    private G o;
    private G p;
    private int q;
    private int r;
    private int s;
    private int t;
    private boolean u;
    private int v;
    private boolean w;
    private int x;
    private int[] y;
    private int z;
    private boolean A;
    private int B;
    private int C;
    private G D;
    private static final int[] E;
    private e F;
    
    public d(final String s) {
        super(s);
        this.m = new G();
        this.n = new n();
        this.o = new G();
        this.p = new G();
        this.q = 45;
        this.r = 3;
        this.s = -1;
        this.t = -1;
    }
    
    public void a() {
        this.n.b();
    }
    
    public void a(final int n) {
        for (int i = 0; i < n; ++i) {
            this.n.a(d.l);
        }
        if (this.s != -1) {
            this.s += n;
        }
    }
    
    public d() {
        this.m = new G();
        this.n = new n();
        this.o = new G();
        this.p = new G();
        this.q = 45;
        this.r = 3;
        this.s = -1;
        this.t = -1;
    }
    
    public void b() {
        super.b();
        this.u = false;
        this.w = false;
        this.m.b();
        this.n.b();
        this.o.b();
        this.p.b();
        this.r = 3;
        this.q = 45;
        this.s = -1;
        this.t = -1;
        this.v = 0;
        this.x = 0;
        this.z = 0;
        this.y = null;
        this.A = false;
        this.D = null;
        this.B = 0;
        this.F = null;
    }
    
    public int c() {
        return (this.s == -1) ? this.n.d() : this.s;
    }
    
    public int d() {
        return this.q;
    }
    
    public int e() {
        return this.r;
    }
    
    public int f() {
        return (this.t == -1) ? this.m.a() : this.t;
    }
    
    public void a(final G m) {
        this.t = -1;
        this.m = m;
    }
    
    public void b(final int n) {
        this.t += n;
    }
    
    public void g() {
        this.B = 0;
        for (int i = 0; i < this.m.a(); ++i) {
            if (this.F.a((f)this.m.b(i), this.o) > 0) {
                ++this.B;
            }
        }
    }
    
    public boolean h() {
        return this.A;
    }
    
    public boolean a(final com.daysofwonder.tt.o o, final int n) {
        for (int i = 0; i < this.o.a(); ++i) {
            final com.daysofwonder.tt.o o2 = (com.daysofwonder.tt.o)this.o.b(i);
            if (o == o2) {
                return true;
            }
            if (n <= this.F.d() && o.b() && o.a() == o2) {
                return true;
            }
        }
        return false;
    }
    
    public boolean a(final c c) {
        for (int i = 0; i < this.p.a(); ++i) {
            if (c == this.p.b(i)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean b(final com.daysofwonder.tt.o o, final int n) {
        return this.F.a(o, n, this.n);
    }
    
    public boolean a(final c c, final int n) {
        final int n2 = 4 - this.r;
        int n3 = (n != 9) ? 1 : 0;
        int n4 = (n == 9) ? 1 : 0;
        if (c.b()) {
            return false;
        }
        for (int i = 0; i < this.n.d(); ++i) {
            final com.daysofwonder.tt.i j = (com.daysofwonder.tt.i)this.n.a(i);
            if (j.b() == n) {
                ++n3;
            }
            else if (j.c()) {
                ++n4;
            }
        }
        return n3 >= n2 || n4 + n3 >= n2;
    }
    
    public int a(final com.daysofwonder.tt.o o, final int n, final int n2, final n n3, final n n4, final n n5) {
        boolean b = false;
        int d = o.d();
        final int n6 = o.h() ? o.i() : 0;
        int n8;
        final int n7 = n8 = (this.F.l() ? 10 : 9);
        if (n == n7) {
            b = true;
            if (o.c() == 9) {
                n8 = -1;
            }
            else {
                n8 = o.c();
            }
        }
        final boolean b2 = !this.F.l() || (o.j() && this.F.l());
        if (o.j() && n2 > 0) {
            d += n2;
        }
        for (int i = 0; i < this.n.d(); ++i) {
            final com.daysofwonder.tt.i j = (com.daysofwonder.tt.i)this.n.a(i);
            if (j.b() == n) {
                n3.a(j);
            }
        }
        for (int k = 0; k < this.n.d(); ++k) {
            final com.daysofwonder.tt.i l = (com.daysofwonder.tt.i)this.n.a(k);
            if (l.b() == n8) {
                n4.a(l);
            }
        }
        if (o.h()) {
            if (n6 > 0 && n3.d() + n4.d() >= d && ((!b && n4.d() >= n6) || (b && n3.d() >= n6))) {
                int n9 = n6;
                int d2 = d - n9;
                final n n10 = b ? n4 : n3;
                final n n11 = b ? n3 : n4;
                if (n10.d() < d2) {
                    n9 += d2 - n10.d();
                    d2 = n10.d();
                }
                if (n10.d() >= d2 && n11.d() >= n9) {
                    this.a(n10, d2, n11, n9, n5);
                    return 2;
                }
            }
        }
        else {
            final int n12 = (d <= n3.d()) ? d : n3.d();
            final int n13 = (d <= n3.d()) ? 0 : (d - n3.d());
            if (!o.j() && this.F.l()) {
                if (b) {
                    n3.b();
                }
                else {
                    n4.b();
                }
            }
            if (n3.d() >= n12 && n4.d() >= n13) {
                this.a(n3, n12, n4, n13, n5);
                return 2;
            }
            if (n8 == -1 && o.j() && this.F.l()) {
                final int[] array = new int[11];
                this.b(array);
                for (int n14 = 1; n14 < array.length; ++n14) {
                    com.daysofwonder.util.t.a("color occ:" + array[n14] + " need: " + n13);
                    if (array[n14] != n7 && array[n14] >= n13 && n14 != 10) {
                        int n15 = 0;
                        for (int n16 = 0; n16 < this.n.d(); ++n16) {
                            final com.daysofwonder.tt.i m = (com.daysofwonder.tt.i)this.n.a(n16);
                            com.daysofwonder.util.t.a("analysing: " + m);
                            if (m.b() == n14 && n15 == 0) {
                                com.daysofwonder.util.t.a("match: " + m);
                                n5.a(m);
                                n8 = m.b();
                                n15 = 1;
                            }
                        }
                    }
                }
                if (n5.d() > 1) {
                    return 3;
                }
                n5.b();
                for (int n17 = 0; n17 < this.n.d(); ++n17) {
                    final com.daysofwonder.tt.i i2 = (com.daysofwonder.tt.i)this.n.a(n17);
                    if (i2.b() == n8) {
                        n4.a(i2);
                    }
                }
                com.daysofwonder.util.t.a("Fill after:" + n4);
                if (n3.d() >= n12 && n4.d() >= n13) {
                    this.a(n3, n12, n4, n13, n5);
                    return 2;
                }
            }
        }
        return 1;
    }
    
    public int a(final com.daysofwonder.tt.o o, final int n, final int n2, final int n3, final n n4, final n n5, final n n6) {
        final boolean b = false;
        int d = o.d();
        final int n7 = o.h() ? o.i() : 0;
        final int n8 = this.F.l() ? 10 : 9;
        com.daysofwonder.util.t.a("primary: " + n);
        com.daysofwonder.util.t.a("fillColor: " + n2);
        final boolean b2 = !this.F.l() || (o.j() && this.F.l());
        if (o.j() && n3 > 0) {
            d += n3;
        }
        for (int i = 0; i < this.n.d(); ++i) {
            final com.daysofwonder.tt.i j = (com.daysofwonder.tt.i)this.n.a(i);
            if (j.b() == n) {
                n4.a(j);
            }
        }
        for (int k = 0; k < this.n.d(); ++k) {
            final com.daysofwonder.tt.i l = (com.daysofwonder.tt.i)this.n.a(k);
            if (l.b() == n2) {
                n5.a(l);
            }
        }
        com.daysofwonder.util.t.a("primary: " + n4);
        com.daysofwonder.util.t.a("fill: " + n5);
        if (o.h()) {
            if (n7 > 0 && n4.d() + n5.d() >= d && ((!b && n5.d() >= n7) || (b && n4.d() >= n7))) {
                int n9 = n7;
                int d2 = d - n9;
                if (n4.d() < d2) {
                    n9 += d2 - n4.d();
                    d2 = n4.d();
                }
                if (n4.d() >= d2 && n5.d() >= n9) {
                    this.a(n4, d2, n5, n9, n6);
                    return 2;
                }
            }
        }
        else {
            final int n10 = (d <= n4.d()) ? d : n4.d();
            final int n11 = (d <= n4.d()) ? 0 : (d - n4.d());
            com.daysofwonder.tt.d.k.a("nbprimary: " + n10);
            com.daysofwonder.tt.d.k.a("nbfill: " + n11);
            if (!o.j() && this.F.l()) {
                n4.b();
            }
            com.daysofwonder.tt.d.k.a("primary: " + n4);
            if (n4.d() >= n10 && n5.d() >= n11) {
                this.a(n4, n10, n5, n11, n6);
                com.daysofwonder.tt.d.k.a("composeHand: " + n6);
                return 2;
            }
        }
        return 1;
    }
    
    public int a(final com.daysofwonder.tt.o o, final com.daysofwonder.tt.i i, final n n) {
        final n n2 = new n();
        final n n3 = new n();
        n2.a(i);
        return this.a(o, i.b(), 0, n2, n3, n);
    }
    
    private void a(final n n, final int n2, final n n3, final int n4, final n n5) {
        for (int i = 0; i < n2; ++i) {
            n5.a(n.a(i));
        }
        for (int j = 0; j < n4; ++j) {
            n5.a(n3.a(j));
        }
    }
    
    public n a(final c c, final com.daysofwonder.tt.i i) {
        final int n = 4 - this.r;
        final n n2 = new n();
        final n n3 = new n();
        if (i.c()) {
            n3.a(i);
        }
        else {
            n2.a(i);
        }
        final int b = i.b();
        for (int j = 0; j < this.n.d(); ++j) {
            final com.daysofwonder.tt.i k = (com.daysofwonder.tt.i)this.n.a(j);
            if (!k.c() && k.b() == b) {
                if (n2.d() >= n) {
                    break;
                }
                n2.a(k);
            }
            else if (k.c()) {
                n3.a(k);
            }
        }
        if (n2.d() >= n) {
            return n2;
        }
        if (n3.d() + n2.d() >= n) {
            n2.a(n3, n - n2.d());
            return n2;
        }
        return null;
    }
    
    public void a(final n n, final com.daysofwonder.tt.o o) {
        if (this.s != -1) {
            this.s -= n.d();
        }
        this.c(n.d());
        this.o.c(o);
        this.h += d.E[o.d()];
        this.q -= o.d();
    }
    
    public void b(final n n, final com.daysofwonder.tt.o o) {
        if (this.s != -1) {
            this.s -= n.d();
        }
        this.c(n.d());
        this.o.c(o);
    }
    
    public void c(final n n, final com.daysofwonder.tt.o o) {
        this.h += d.E[o.d()];
        this.q -= o.d();
    }
    
    public void a(final n n) {
        if (this.s != -1) {
            this.s -= n.d();
        }
    }
    
    public void a(final n n, final c c) {
        if (this.s != -1) {
            this.s -= n.d();
        }
        this.c(n.d());
        this.p.c(c);
        --this.r;
    }
    
    public void a(final n n, final com.daysofwonder.tt.o o, final G g) {
        for (int i = 0; i < n.d(); ++i) {
            final com.daysofwonder.tt.i j = (com.daysofwonder.tt.i)n.a(i);
            if (this.n.b(j) == null) {
                com.daysofwonder.util.t.c("TTPlayer.play(): Couldn't remove: " + j + " not there");
            }
            if (g != null) {
                g.c(j);
            }
        }
        this.o.c(o);
        this.h += d.E[o.d()];
        this.q -= o.d();
    }
    
    public void a(final n n, final c c, final G g) {
        for (int i = 0; i < n.d(); ++i) {
            final com.daysofwonder.tt.i j = (com.daysofwonder.tt.i)n.a(i);
            if (this.n.b(j) == null) {
                com.daysofwonder.util.t.c("TTPlayer.play(): Couldn't remove: " + j + " not there");
            }
            if (g != null) {
                g.c(j);
            }
        }
        this.p.c(c);
        --this.r;
    }
    
    public void c(final int n) {
        int n2 = 0;
        for (y c = this.n.c(); c.a() && n2 < n; ++n2) {
            final o o = (o)c.b();
            c.c();
        }
    }
    
    public void a(final com.daysofwonder.tt.i i) {
        this.n.b(i);
    }
    
    public void b(final n n) {
        for (int i = 0; i < n.d(); ++i) {
            this.n.b(n.a(i));
        }
    }
    
    public void b(final com.daysofwonder.tt.i i) {
        this.n.a(i);
    }
    
    public boolean c(final com.daysofwonder.tt.i i) {
        return this.n.c(i);
    }
    
    public void a(final f f) {
        this.m.c(f);
    }
    
    public G i() {
        return this.o;
    }
    
    public G j() {
        return this.p;
    }
    
    public G k() {
        return this.m;
    }
    
    public n l() {
        return this.n;
    }
    
    public void d(final int v) {
        this.u = true;
        this.v = v;
    }
    
    public int m() {
        return this.v;
    }
    
    public int n() {
        return this.z;
    }
    
    public void e(final int z) {
        this.z = z;
    }
    
    public void f(final int x) {
        this.w = true;
        this.x = x;
    }
    
    public int o() {
        return this.x;
    }
    
    public void a(final int[] array) {
        this.C = 0;
        this.y = array.clone();
        for (int i = 0; i < this.y.length; ++i) {
            if (this.y[i] > 0) {
                ++this.C;
            }
        }
    }
    
    public int p() {
        return this.C;
    }
    
    public int[] q() {
        return this.y;
    }
    
    public int r() {
        int n = this.A() - ((this.v == -1) ? 0 : this.v) - ((this.x == -1) ? 0 : this.x);
        if (this.y != null) {
            for (int i = 0; i < this.y.length; ++i) {
                n -= this.y[i];
            }
        }
        return n - this.r * 4;
    }
    
    public void b(final G d) {
        this.D = d;
    }
    
    public G s() {
        return this.D;
    }
    
    public void a(final e f, final com.daysofwonder.tt.req.f f2, final boolean b) {
        this.F = f;
        this.o = f.b(f2.a(this.v()));
        this.p = f.a(f2.b(this.v()));
        this.q = f2.f(this.v());
        this.r = f2.g(this.v());
        this.h = f2.c(this.v());
        this.A = f2.h(this.v());
        if (this.v() == f2.j()) {
            this.n = f2.k();
            com.daysofwonder.util.t.a("hand: " + this.n);
            this.m = f.c(f2.l());
            com.daysofwonder.util.t.a("tickets: " + this.m);
        }
        else {
            this.s = f2.d(this.v());
            if (b) {
                this.t = f2.e(this.v());
            }
            else {
                this.t = 0;
            }
        }
    }
    
    public int t() {
        return this.n.g();
    }
    
    public void b(final int[] array) {
        for (int i = 0; i < this.n.d(); ++i) {
            final int b = ((com.daysofwonder.tt.i)this.n.a(i)).b();
            ++array[b];
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(super.toString());
        sb.append("{[");
        sb.append(this.n);
        sb.append("],[");
        sb.append(this.m);
        sb.append("],[");
        sb.append(this.o);
        sb.append("]}");
        return sb.toString();
    }
    
    public a g(final int n) {
        final p p = new p();
        final a g = this.G();
        for (final String s : g) {
            p.a(s, g.a(s) || this.b(s) < n);
        }
        return p;
    }
    
    static {
        k = LoggerFactory.a(d.class);
        l = new com.daysofwonder.tt.i(-1);
        E = new int[] { 0, 1, 2, 4, 7, 10, 15, 17, 21 };
    }
}
