// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.system.graphics.renderer.b;
import neat.system.graphics.renderer.g;
import neat.nb;
import neat.lb;
import neat.r;
import neat.system.graphics.renderer.m;
import neat.i;
import neat.system.graphics.renderer.a;
import neat.kb;
import neat.system.f;
import neat.system.cb;

public class db implements cb
{
    private static f a;
    public static final kb b;
    private z c;
    private boolean d;
    private int e;
    private int f;
    private a g;
    private int h;
    private float i;
    private float j;
    private boolean k;
    private boolean l;
    private int m;
    private float n;
    private float o;
    public i p;
    public i q;
    private kb r;
    private boolean s;
    private kb t;
    private i u;
    private boolean v;
    private a w;
    private a x;
    private boolean y;
    private m z;
    private static /* synthetic */ Class A;
    private static String[] B;
    
    public void a(final x x) {
        if (this.c != null) {
            this.c.a(x, this);
        }
        else {
            this.f();
        }
    }
    
    public void a(final x x, final z c) {
        if (c == this.c) {
            return;
        }
        this.b(x);
        (this.c = c).a(this);
        this.j(x);
    }
    
    public void b(final x x) {
        if (this.c == null) {
            return;
        }
        this.l(x);
        this.c.b(this);
        this.c = null;
    }
    
    void c(final x x) {
        this.l(x);
    }
    
    void d(final x x) {
        this.i(x);
    }
    
    public void e(final x x) {
        if (this.d) {
            return;
        }
        this.d = true;
        this.n(x);
        this.o(x);
    }
    
    public void f(final x x) {
        if (!this.d) {
            return;
        }
        this.d = false;
        this.o(x);
    }
    
    public boolean a() {
        return this.d;
    }
    
    public void a(final x x, final int n, final int n2, final int n3, final int n4) {
        if (this.g.b() && n == this.g.d() && n2 == this.g.e() && n3 == this.g.f() && n4 == this.g.g()) {
            return;
        }
        this.g.a(n, n2, n3, n4);
        this.m(x);
    }
    
    public void a(final x x, final int h) {
        if (this.h == h) {
            return;
        }
        this.h = h;
        this.o(x);
    }
    
    public int b() {
        return this.h;
    }
    
    public void a(final x x, final int e, final int f) {
        if (this.e == e && this.f == f) {
            return;
        }
        final int n = e - this.e;
        final int n2 = f - this.f;
        this.e = e;
        this.f = f;
        this.c(x, n, n2);
    }
    
    public void b(final x x, final int n, final int n2) {
        if (n == 0 && n2 == 0) {
            return;
        }
        this.e += n;
        this.f += n2;
        if (this.g.b()) {
            this.g.a(this.g.d() + n, this.g.e() + n2);
        }
        this.c(x, n, n2);
    }
    
    public void a(final x x, final float i, final float j) {
        if (this.i == i && this.j == j) {
            return;
        }
        this.i = i;
        this.j = j;
        this.m(x);
    }
    
    public int a(final int n) {
        float i = this.i;
        if (this.c != null) {
            i *= this.c.i();
        }
        if (i == 1.0f) {
            return n;
        }
        return (int)(n * i);
    }
    
    public int b(final int n) {
        float j = this.j;
        if (this.c != null) {
            j *= this.c.j();
        }
        if (j == 1.0f) {
            return n;
        }
        return (int)(n * j);
    }
    
    public void b(final x x, final int m) {
        if (this.l && m == this.m) {
            return;
        }
        this.l = true;
        this.m = m;
        this.o(x);
    }
    
    public void g(final x x) {
        if (!this.l) {
            return;
        }
        this.l = false;
        this.o(x);
    }
    
    public void b(final x x, final float n, final float o) {
        if (this.n == n && this.o == o) {
            return;
        }
        this.n = n;
        this.o = o;
        this.o(x);
    }
    
    public void a(final x x, final r r) {
        while (r.a()) {
            this.p.a(((kb)r.b()).b());
        }
        this.o(x);
    }
    
    public void b(final x x, final r r) {
        while (r.a()) {
            this.q.a(((kb)r.b()).b());
        }
        this.o(x);
    }
    
    public void a(final x x, final kb kb, final boolean s) {
        if (this.r != null) {
            if (this.r.equals(kb)) {
                return;
            }
            this.r.f();
            this.r = null;
        }
        else if (kb == null) {
            return;
        }
        if (kb != null) {
            this.r = kb.b();
        }
        this.s = s;
        this.o(x);
    }
    
    public void a(final x x, final kb kb) {
        this.a(x, kb, false);
    }
    
    public void b(final x x, final kb kb) {
        if (this.t != null) {
            if (this.t.equals(kb)) {
                return;
            }
        }
        else if (kb == null) {
            return;
        }
        this.h(x);
        if (kb != null) {
            this.t = kb.b();
            this.j(x);
        }
    }
    
    public kb c() {
        return this.t;
    }
    
    public void h(final x x) {
        if (this.t != null) {
            this.l(x);
            this.t.f();
            this.t = null;
        }
    }
    
    public void i(final x x) {
        this.m(x);
    }
    
    private void j(final x x) {
        this.l(x);
        if (!this.a()) {
            this.v = true;
            return;
        }
        if (this.t == null || this.c == null) {
            return;
        }
        final int b = this.b(this.c.d());
        final int b2 = this.b(this.c.e());
        final int a = this.a(this.c.f());
        final int a2 = this.a(this.c.g());
        final int b3 = this.b(this.c.h());
        final kb b4 = this.c.b();
        int n = 60;
        this.w.a();
        this.x.a();
        if (this.r == null) {
            final g g = this.c.c();
        }
        else {
            final g g = this.c.a(this.r);
        }
        int n2 = this.e;
        int n3 = this.f;
        kb c = null;
        float i = this.i;
        float j = this.j;
        int f = this.f;
        kb b5 = this.t.b();
        final lb a3 = lb.a();
        final i k = neat.i.k();
        Label_1357: {
            break Label_1357;
            do {
                final kb l = a3.l();
                a3.c(0);
                final kb d = b5.d(l.d());
                b5.f();
                b5 = d;
                int e = this.e;
                int e2 = this.e;
                int e3 = this.e;
                final boolean b7;
                for (int n4 = 0; n4 < l.d(); ++n4) {
                    boolean b6 = false;
                    kb kb = null;
                    char c2 = l.b(n4);
                    if (c2 == '{') {
                        final int n5 = n4;
                        final int a4 = l.a(125, n5);
                        if (a4 == -1) {
                            throw new RuntimeException(db.B[1] + this.t);
                        }
                        n4 = a4;
                        if (a4 - n5 > 1) {
                            b6 = true;
                            c2 = l.b(n5 + 1);
                            if (c2 == '<' || c2 == '=' || c2 == '>') {
                                n = c2;
                            }
                            else if (c2 == '!') {
                                b6 = false;
                                kb = l.c(n5 + 1, a4);
                            }
                            else if (c2 == 'x') {
                                final kb c3 = l.c(n5 + 2, a4);
                                n2 = nb.c(c3);
                                c3.f();
                                if (this.g.b()) {
                                    n2 += this.g.b;
                                }
                            }
                            else if (c2 == 'y') {
                                final kb c4 = l.c(n5 + 2, a4);
                                n3 = nb.c(c4);
                                c4.f();
                                if (this.g.b()) {
                                    n3 += this.g.c;
                                }
                            }
                            else if (c2 == '@') {
                                if (!this.s) {
                                    if (c != null) {
                                        c.f();
                                    }
                                    c = l.c(n5 + 2, a4);
                                }
                            }
                            else if (c2 == 'z') {
                                final kb c5 = l.c(n5 + 2, a4);
                                i = nb.c(c5) / 100.0f;
                                c5.f();
                                j = i;
                            }
                        }
                    }
                    if (b7 && !b6) {
                        if (c2 == ' ' && b4 == null) {
                            e += a2;
                            if (e >= e3) {
                                e3 = e;
                            }
                        }
                        else {
                            if (c2 == ' ' && b4 != null) {
                                kb = b4.b();
                            }
                            if (kb == null) {
                                final lb a5 = lb.a();
                                a5.a('!');
                                a5.a(c2);
                                kb = a5.b();
                            }
                            final wb b8 = this.c.b(kb);
                            if (b8 != null) {
                                final int n6 = e - this.a(b8.j);
                                final int n7 = f + b2 - this.b(b8.i);
                                final int n8 = b8.g - b8.e;
                                final int n9 = b8.h - b8.f;
                                int a6 = this.a(n8);
                                int b9 = this.b(n9);
                                if (n8 != 0 && a6 > n8 && a6 % n8 == 0) {
                                    ++a6;
                                }
                                if (n9 != 0 && b9 > n9 && b9 % n9 == 0) {
                                    ++b9;
                                }
                                if (a6 > 0 && b9 > 0) {
                                    if (n6 < e2) {
                                        e2 = n6;
                                    }
                                    if (n6 + a6 > e3) {
                                        e3 = n6 + a6;
                                    }
                                    final a.bb b10 = a.bb.b();
                                    b10.a(x, b8);
                                    final g g;
                                    b10.a(g);
                                    b10.a(n6, n7);
                                    b10.c(a6, b9);
                                    this.u.a(b10);
                                    k.a(b10);
                                }
                                e += this.a(b8.k) + a;
                            }
                        }
                    }
                    if (kb != null) {
                        kb.f();
                    }
                }
                int n10 = 0;
                int f2 = 0;
                if (this.g.b() && this.g.d() == this.e) {
                    f2 = this.g.f();
                }
                final int a7;
                if (n == 61) {
                    n10 = f2 / 2 - ((e3 + e2) / 2 - this.e);
                }
                else if (n == 62) {
                    n10 = f2 - a7;
                }
                if (n10 != 0) {
                    final r f3 = k.f();
                    while (f3.a()) {
                        ((a.bb)f3.b()).b(n10, 0);
                    }
                    f3.f();
                }
                if (a7 > 0) {
                    final a h = neat.system.graphics.renderer.a.h();
                    final r f4 = k.f();
                    while (f4.a()) {
                        final a a8 = ((a.bb)f4.b()).a();
                        if (a8.b()) {
                            if (h.b()) {
                                h.c(a8);
                            }
                            else {
                                h.b(a8);
                            }
                        }
                        a8.f();
                    }
                    f4.f();
                    if (h.b()) {
                        this.x.c(h);
                        if (b7) {
                            this.w.c(h);
                        }
                    }
                    h.f();
                }
                final int n11;
                f = n11 + b3;
                k.c();
                l.f();
                n11 = f + b;
                b7 = (!this.g.b() || (n11 >= this.g.e() && f <= this.g.e() + this.g.g()));
                a7 = this.a(b5, a3);
            } while (a3.j() != 0);
        }
        b5.f();
        a3.f();
        k.f();
        if (i != this.i || j != this.j) {
            this.a(x, i, j);
            return;
        }
        this.o(x);
        if (c != null) {
            this.a(x, c);
            c.f();
        }
        this.a(x, n2, n3);
        this.p(x);
    }
    
    private void k(final x x) {
        if (this.u.e()) {
            return;
        }
        final r f = this.u.f();
        while (f.a()) {
            ((a.bb)f.b()).a(x);
        }
        f.f();
        this.u.j();
    }
    
    private void l(final x x) {
        this.k(x);
        this.w.a();
        this.x.a();
        this.q(x);
    }
    
    private void m(final x x) {
        this.j(x);
    }
    
    private void n(final x x) {
        if (this.v) {
            this.v = false;
            this.m(x);
        }
    }
    
    private void o(final x x) {
        this.r(x);
        if (this.u.e()) {
            return;
        }
        final boolean a = this.a();
        g g;
        if (this.r == null) {
            g = this.c.c();
        }
        else {
            g = this.c.a(this.r);
        }
        final r f = this.u.f();
        while (f.a()) {
            final a.bb bb = (a.bb)f.b();
            bb.a(false);
            bb.a(g);
            bb.a(this.h);
            bb.a(this.l, this.m, this.n, this.o);
            if (!this.p.e()) {
                final r f2 = this.p.f();
                while (f2.a()) {
                    final b d = x.d((kb)f2.b());
                    if (d != null) {
                        bb.a(d);
                    }
                }
                f2.f();
            }
            else if (!this.q.e()) {
                final r s = x.s();
                while (s.a()) {
                    final kb kb = (kb)s.b();
                    if (!this.q.b(kb)) {
                        final b d2 = x.d(kb);
                        if (d2 == null) {
                            continue;
                        }
                        bb.a(d2);
                    }
                }
                s.f();
            }
            bb.a(a);
        }
        f.f();
    }
    
    public void c(final x x, final int n, final int n2) {
        if (!this.x.b() || this.c == null) {
            return;
        }
        this.x.a(this.x.d() + n, this.x.e() + n2);
        if (this.w.b()) {
            this.w.a(this.w.d() + n, this.w.e() + n2);
        }
        if (this.g.b()) {
            if (this.w.b()) {
                final int b = this.b(this.c.d());
                final int b2 = this.b(this.c.h());
                final int e = this.g.e();
                final int n3 = e + this.g.g();
                final int e2 = this.w.e();
                final int n4 = e2 + this.w.g();
                final int e3 = this.x.e();
                final int n5 = e3 + this.x.g();
                final int n6 = b + b2;
                if ((e2 - b2 > e && e3 < e2) || (n4 + b2 < n3 && n5 > n4) || e2 + n6 < e || n4 - n6 > n3) {
                    this.m(x);
                    return;
                }
            }
            else if (this.g.d(this.x)) {
                this.m(x);
                return;
            }
        }
        final r f = this.u.f();
        while (f.a()) {
            ((a.bb)f.b()).b(n, n2);
        }
        f.f();
        this.d(x, n, n2);
    }
    
    public void a(final x x, final boolean b, final boolean b2) {
        if (!this.x.b() || !this.g.b()) {
            return;
        }
        int n = 0;
        if (b) {
            n = this.g.d() + (this.g.f() - this.x.f()) / 2 - this.x.d();
        }
        int n2 = 0;
        if (b2) {
            n2 = this.g.e() + (this.g.g() - this.x.g()) / 2 - this.x.e();
        }
        if (n != 0 || n2 != 0) {
            this.a(x, this.e + n, this.f + n2);
        }
    }
    
    public a d() {
        return this.w;
    }
    
    public int a(final kb kb, final lb lb) {
        if (this.c == null) {
            throw new RuntimeException(db.B[0]);
        }
        final boolean b = lb != null;
        int f;
        if (this.g.b()) {
            f = this.g.f();
        }
        else {
            f = -1;
        }
        int n = 1;
        final int a = this.a(this.c.f());
        final int a2 = this.a(this.c.g());
        int n2 = 0;
        int i = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        while (i < kb.d()) {
            boolean b2 = false;
            boolean b3 = false;
            kb kb2 = null;
            final int n8 = i;
            char c = kb.b(i);
            if (c == '{') {
                final int n9 = i;
                final int a3 = kb.a(125, n9);
                if (a3 == -1) {
                    throw new RuntimeException(db.B[1] + kb);
                }
                i = a3;
                if (a3 - n9 > 1) {
                    b3 = true;
                    c = kb.b(n9 + 1);
                    if (c == 'n') {
                        n2 = 0;
                        b2 = true;
                    }
                    else if (c == '!') {
                        b3 = false;
                        kb2 = kb.c(n9 + 1, a3);
                    }
                }
            }
            if (c == ' ') {
                n2 += a2;
            }
            else if (!b3) {
                if (kb2 == null) {
                    final lb a4 = lb.a();
                    a4.a('!');
                    a4.a(c);
                    kb2 = a4.b();
                }
                final wb b4 = this.c.b(kb2);
                if (b4 != null) {
                    final int n10 = n2 - this.a(b4.j);
                    final int n11 = n10 + this.a(b4.g - b4.e);
                    if (n3 > n10) {
                        n3 = n10;
                    }
                    if (n4 < n11) {
                        n4 = n11;
                    }
                    n2 += this.a(b4.k) + a;
                }
            }
            ++i;
            if (kb2 != null) {
                kb2.f();
            }
            if (b) {
                if (f >= 0 && n5 > 0 && n4 - n3 > f) {
                    n3 = n6;
                    n4 = n7;
                    lb.c(n5);
                }
                else {
                    for (int j = n8; j < i; ++j) {
                        lb.a(kb.b(j));
                    }
                    if (!b2) {
                        int n12 = 0;
                        if (c == ' ') {
                            n12 = 1;
                        }
                        else if (!this.k || n != 0) {
                            n12 = (b3 ? 0 : 1);
                        }
                        if (n12 != 0) {
                            n5 = i;
                            n6 = n3;
                            n7 = n4;
                        }
                        if (c == ' ') {
                            n = 0;
                            continue;
                        }
                        continue;
                    }
                }
                return n4 - n3;
            }
        }
        return n4 - n3;
    }
    
    public void a(final x x, boolean y) {
        if (x.l()) {
            y = false;
        }
        if (this.y == y) {
            return;
        }
        if (this.y = y) {
            this.p(x);
        }
        else {
            this.j(x);
        }
    }
    
    private void p(final x x) {
        if (x.l() && this.y) {
            this.q(x);
            this.y = false;
            return;
        }
        if (!this.y) {
            return;
        }
        if (!this.w.b()) {
            return;
        }
        final int f = this.w.f();
        final int g = this.w.g();
        final int k = this.c.k();
        g g2;
        if (k != -1) {
            g2 = this.c.a(x, f, g, k, -1);
        }
        else {
            g g3;
            if (this.r == null) {
                g3 = this.c.c();
            }
            else {
                g3 = this.c.a(this.r);
            }
            int n = -1;
            if (g3.m()) {
                n = g3.n();
            }
            g2 = this.c.a(x, f, g, -1, n);
        }
        if (g2 == null) {
            this.a(x, false);
            return;
        }
        final r f2 = this.u.f();
        while (f2.a()) {
            ((a.bb)f2.b()).a(g2, f, g, this.w.d(), this.w.e());
        }
        f2.f();
        (this.z = x.t()).a(g2);
        this.z.a(0, 0, f, g);
        this.z.b(f, g);
        this.r(x);
        this.k(x);
    }
    
    private void q(final x x) {
        if (this.z == null) {
            return;
        }
        final g j = this.z.j();
        this.z.i();
        x.a(this.z);
        this.z = null;
        this.c.a(x, j);
    }
    
    private void r(final x x) {
        if (this.z == null) {
            return;
        }
        this.z.a(this.w.d(), this.w.e());
        this.z.a(this.h);
        if (this.l) {
            this.z.a(this.m, this.n, this.o);
        }
        else {
            this.z.a(this.n, this.o);
        }
        if (!this.p.e()) {
            final r f = this.p.f();
            while (f.a()) {
                final b d = x.d((kb)f.b());
                if (d != null) {
                    this.z.a(d);
                }
            }
            f.f();
        }
        else if (!this.q.e()) {
            final r s = x.s();
            while (s.a()) {
                final kb kb = (kb)s.b();
                if (!this.q.b(kb)) {
                    final b d2 = x.d(kb);
                    if (d2 == null) {
                        continue;
                    }
                    this.z.a(d2);
                }
            }
            s.f();
        }
        this.z.a(this.a());
    }
    
    private void d(final x x, final int n, final int n2) {
        if (this.z == null) {
            return;
        }
        this.z.a(this.z.c() + n, this.z.d() + n2);
    }
    
    public void s(final x x) {
        if (this.z == null) {
            return;
        }
        if (!this.z.B()) {
            return;
        }
        this.j(x);
    }
    
    public static db e() {
        return (db)db.a.a();
    }
    
    public void f() {
        db.a.a(this);
    }
    
    public void g() {
        this.d = false;
        this.g = neat.system.graphics.renderer.a.h();
        this.h = 0;
        this.i = 1.0f;
        this.j = 1.0f;
        this.k = true;
        this.l = false;
        this.n = 1.0f;
        this.o = 0.0f;
        this.p = neat.i.k();
        this.q = neat.i.k();
        this.s = false;
        this.u = neat.i.k();
        this.v = false;
        this.w = neat.system.graphics.renderer.a.h();
        this.x = neat.system.graphics.renderer.a.h();
        this.y = false;
    }
    
    public void h() {
        this.c = null;
        this.e = 0;
        this.f = 0;
        this.g.f();
        this.g = null;
        if (this.t != null) {
            this.t.f();
            this.t = null;
        }
        this.p.j();
        this.p.f();
        this.p = null;
        this.q.j();
        this.q.f();
        this.q = null;
        if (this.r != null) {
            this.r.f();
            this.r = null;
        }
        this.u.f();
        this.u = null;
        this.w.f();
        this.w = null;
        this.x.f();
        this.x = null;
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public db() {
        this.c = null;
        this.g = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.t = null;
        this.u = null;
        this.w = null;
        this.x = null;
        this.z = null;
    }
    
    static {
        db.B = new String[] { z(z("u'L4FU*]3FR?W.\u0012DoM&\u0015\u0001!Jg\u0000N!QgG")), z(z("u'@5\u0003\u0001&V)AUo@)\u0002\u0001 Cg\u0005N!Q5\tMoG+\tB$\u0005.\b\u0001;@?\u0012\u001b")), z(z("@aA%")) };
        db.a = new f((db.A != null) ? db.A : (db.A = a(db.B[2])));
        b = kb.a(z(z("\u0000<U&\u0005D")));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        int i;
        do {
            i = charArray.length;
            if (i < 2) {
                continue;
            }
            return charArray;
        } while (i == 0);
        final int n = 0;
        charArray[n] ^= 'f';
        return charArray;
    }
    
    private static String z(final char[] array) {
        int length;
        int n2;
        final int n = n2 = (length = array.length);
        int n3 = 0;
        while (true) {
            Label_0086: {
                if (n > 1) {
                    break Label_0086;
                }
                length = (n2 = n3);
                do {
                    final char c = array[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = '!';
                            break;
                        }
                        case 1: {
                            c2 = 'O';
                            break;
                        }
                        case 2: {
                            c2 = '%';
                            break;
                        }
                        case 3: {
                            c2 = 'G';
                            break;
                        }
                        default: {
                            c2 = 'f';
                            break;
                        }
                    }
                    array[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                return new String(array).intern();
            }
            continue;
        }
    }
}
