// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.nb;
import neat.system.graphics.renderer.g;
import neat.bb;
import neat.r;
import neat.eb;
import neat.system.graphics.renderer.p;
import neat.i;
import neat.h;
import neat.kb;
import neat.system.cb;

public abstract class x implements cb
{
    public static final boolean a;
    public static final boolean b;
    public static final int c;
    public static final String d;
    public static final boolean e;
    public static final boolean f;
    public static final boolean g;
    public static final int h;
    public static final boolean i;
    public static final boolean j;
    public static final boolean k;
    public static final boolean l;
    public static final boolean m;
    public static final boolean n;
    public static final boolean o;
    public static final boolean p;
    public static final int q;
    public static final int r;
    public static final int s;
    public static final int t;
    public static final int u;
    public static final boolean v;
    public static final boolean w;
    public static final boolean x;
    public static final boolean y;
    public static final boolean z;
    public static final kb A;
    public static final int B;
    public static final kb C;
    public static final kb D;
    public static final kb E;
    public static final kb F;
    public static final kb G;
    public static final kb H;
    public static final int I;
    public static final boolean J;
    public static final boolean K;
    public static final int L;
    public static final boolean M;
    public static final kb N;
    public static final kb O;
    public static final boolean P;
    public static final kb Q;
    public static final kb R;
    private neat.system.kb S;
    private l T;
    private boolean U;
    private long V;
    private kb W;
    private kb X;
    private kb Y;
    private h Z;
    private h ab;
    private h bb;
    private i cb;
    private a.cb db;
    private boolean eb;
    private int fb;
    public static int gb;
    private static String[] hb;
    
    public void a(final neat.system.kb s) {
        if (this.S != null) {
            throw new RuntimeException(a.x.hb[2] + s);
        }
        this.S = s;
    }
    
    public boolean a() {
        return this.S != null;
    }
    
    public void b(final neat.system.kb kb) {
        if (this.S == null) {
            throw new RuntimeException(a.x.hb[1]);
        }
        if (this.S != kb) {
            throw new RuntimeException(a.x.hb[4] + this.S + a.x.hb[3] + kb);
        }
        this.S = null;
    }
    
    public neat.system.kb a(final Object o) {
        if (this.S == null) {
            throw new RuntimeException(a.x.hb[1]);
        }
        return this.S.b(o);
    }
    
    public p b() {
        if (this.S == null) {
            throw new RuntimeException(a.x.hb[1]);
        }
        return (p)this.S.b(neat.system.graphics.renderer.p.j);
    }
    
    protected l c() {
        return this.T;
    }
    
    public b a(final gb gb) {
        if (this instanceof m) {
            return this.T.a(gb, (m)this);
        }
        return this.T.a(gb);
    }
    
    public void a(final eb eb) {
        final r c = eb.c();
        while (c.a()) {
            final neat.bb a = ((neat.cb)c.b()).a();
            if (a instanceof gb) {
                this.a((gb)a);
            }
            else {
                a.f();
            }
        }
        c.f();
    }
    
    public void d(final b b) {
        this.T.a(b);
    }
    
    public void d() {
        this.T.a();
    }
    
    public boolean a(final kb kb) {
        return this.T.a(kb);
    }
    
    public b b(final kb kb) {
        return this.T.b(kb);
    }
    
    public r e() {
        return this.T.b();
    }
    
    public void i() {
        this.T.d();
    }
    
    public void j() {
        this.T.e();
    }
    
    public boolean k() {
        return this.T.f();
    }
    
    public void a(final k k) {
        this.T.a(k);
    }
    
    public void b(final k k) {
        this.T.b(k);
    }
    
    public void a(final boolean u, final long v) {
        this.U = u;
        this.V = v;
    }
    
    public boolean l() {
        return this.U;
    }
    
    public void c(final kb kb) {
        if (this.W != null) {
            this.W.f();
            this.W = null;
        }
        if (kb != null) {
            this.W = kb.b();
        }
    }
    
    public kb m() {
        return this.W;
    }
    
    public void a(final kb kb, final kb kb2) {
        if (this.X != null) {
            this.X.f();
            this.X = null;
        }
        if (kb != null) {
            this.X = kb.b();
        }
        if (this.Y != null) {
            this.Y.f();
            this.Y = null;
        }
        if (kb2 != null) {
            this.Y = kb2.b();
        }
    }
    
    public kb n() {
        return this.X;
    }
    
    public kb o() {
        return this.Y;
    }
    
    public void p() {
        final p b = this.b();
        final r a = this.bb.a();
        while (a.a()) {
            b.b((neat.system.graphics.renderer.b)this.bb.g(a.b()));
        }
        a.f();
        this.bb.d();
        final r f = this.cb.f();
        while (f.a()) {
            b.b((neat.system.graphics.renderer.m)f.b());
        }
        f.f();
        this.cb.c();
        final r a2 = this.Z.a();
        while (a2.a()) {
            b.a((g)this.Z.g(a2.b()));
        }
        a2.f();
        this.Z.d();
        final r a3 = this.ab.a();
        while (a3.a()) {
            b.b((g)this.ab.g(a3.b()));
        }
        a3.f();
        this.ab.d();
    }
    
    public g a(kb a, final int n) {
        a = nb.a(a, this.W);
        g a2 = (g)this.Z.g(a);
        if (a2 == null) {
            a2 = this.b().a(a, n, false);
        }
        a.f();
        return a2;
    }
    
    public g b(final kb kb, final int n) {
        return this.a(kb, n, false);
    }
    
    private g a(kb a, final int n, final boolean b) {
        a = nb.a(a, this.W);
        g a2 = (g)this.ab.g(a);
        if (a2 != null) {
            if (!b) {
                a2.e(false);
            }
        }
        else {
            a2 = this.b().a(a, n);
            if (a2 != null) {
                this.ab.a(a.b(), a2);
                a2.e(b);
            }
        }
        a.f();
        return a2;
    }
    
    public void a(final g g) {
        if (!g.c()) {
            final kb b = g.b();
            if (b != null) {
                this.ab.b(b);
            }
            this.b().b(g);
        }
    }
    
    private void b(final g g) {
        if (!g.c() && g.r()) {
            final kb b = g.b();
            if (b != null) {
                this.ab.b(b);
            }
            this.b().b(g);
        }
    }
    
    public boolean a(final neat.bb bb, final boolean b) {
        if (bb == null) {
            return false;
        }
        final boolean b2 = true;
        this.b();
        boolean b3 = false;
        try {
            if (bb instanceof vb) {
                final vb vb = (vb)bb;
                if (vb.e != null) {
                    final g a = this.a(vb.e, vb.q, false);
                    if (a != null) {
                        b3 = true;
                        a.a(vb.u);
                        if (b2) {
                            this.a(a);
                        }
                    }
                }
            }
        }
        catch (Throwable t) {
            b3 = false;
        }
        bb.f();
        return b3;
    }
    
    public boolean a(final neat.bb bb) {
        this.q();
        boolean b = false;
        if (bb instanceof tb) {
            final tb tb = (tb)bb;
            final neat.system.graphics.renderer.b f = this.b().f();
            if (tb.e != null) {
                final g a = this.a(tb.e, -1);
                if (a != null) {
                    f.a(a);
                    f.a(true);
                }
            }
            f.a(tb.f);
            if (this.bb.a(bb.a().b(), f) != null) {
                throw new RuntimeException(a.x.hb[6] + f);
            }
            b = true;
        }
        bb.f();
        return b;
    }
    
    public void q() {
        final p b = this.b();
        final neat.system.graphics.renderer.b f = b.f();
        final g q = f.q();
        if (q != null) {
            f.p();
            b.a(q);
        }
        final kb kb = (kb)this.bb.d(f);
        if (kb != null) {
            this.bb.b(kb);
        }
    }
    
    public neat.system.graphics.renderer.b r() {
        final p b = this.b();
        final int d = b.d();
        final int e = b.e();
        final r a = this.bb.a();
        while (a.a()) {
            final neat.system.graphics.renderer.b b2 = (neat.system.graphics.renderer.b)this.bb.g(a.b());
            if (b2.m() == d && b2.n() == e) {
                a.f();
                return b2;
            }
        }
        a.f();
        return b.f();
    }
    
    public neat.system.graphics.renderer.b a(final neat.cb cb) {
        final neat.bb a = cb.a();
        if (a == null) {
            return null;
        }
        if (!(a instanceof ub)) {
            a.f();
            return null;
        }
        return this.b(a);
    }
    
    public neat.system.graphics.renderer.b b(final neat.bb bb) {
        neat.system.graphics.renderer.b a = null;
        if (bb instanceof ub) {
            final ub ub = (ub)bb;
            a = this.b().a();
            this.a(a, ub);
            if (this.bb.a(bb.a().b(), a) != null) {
                throw new RuntimeException(a.x.hb[7] + a);
            }
        }
        bb.f();
        return a;
    }
    
    public void a(final neat.system.graphics.renderer.b b, final neat.cb cb) {
        final neat.bb a = cb.a();
        if (a == null) {
            return;
        }
        if (!(a instanceof ub)) {
            a.f();
            return;
        }
        this.a(b, (ub)a);
        a.f();
    }
    
    public void a(final neat.system.graphics.renderer.b b) {
        final g q = b.q();
        if (q != null) {
            b.p();
            this.b().a(q);
        }
    }
    
    public void a(final neat.system.graphics.renderer.b b, final ub ub) {
        final p b2 = this.b();
        final g q = b.q();
        if (q != null) {
            b.p();
            b2.a(q);
        }
        if (ub.e != null) {
            final g a = this.a(ub.e, ub.q);
            if (a != null) {
                b.a(a);
                a.a(ub.s);
            }
        }
        if (ub.h >= 0) {
            b.c(ub.f, ub.g, ub.h, ub.i);
        }
        b.a(ub.j);
        if (ub.m >= 0) {
            b.b(ub.k, ub.l, ub.m, ub.n);
        }
        if (ub.o >= 0.0f) {
            b.a(ub.o, ub.p);
        }
        else {
            b.w();
        }
        b.a(ub.r);
    }
    
    public void b(final neat.system.graphics.renderer.b b) {
        final p b2 = this.b();
        if (b2.f() == b) {
            this.q();
            return;
        }
        final kb kb = (kb)this.bb.d(b);
        if (kb == null) {
            throw new RuntimeException(a.x.hb[5] + kb);
        }
        if (this.bb.b(kb) == null) {
            throw new RuntimeException(a.x.hb[5] + kb);
        }
        final g q = b.q();
        if (q != null) {
            b.p();
            b2.a(q);
        }
        b2.b(b);
    }
    
    public neat.system.graphics.renderer.b d(final kb kb) {
        return (neat.system.graphics.renderer.b)this.bb.g(kb);
    }
    
    public r s() {
        return this.bb.a();
    }
    
    public neat.system.graphics.renderer.m t() {
        final neat.system.graphics.renderer.m b = this.b().b();
        this.cb.a(b);
        return b;
    }
    
    public neat.system.graphics.renderer.m b(final neat.cb cb) {
        final neat.bb a = cb.a();
        if (a == null) {
            return null;
        }
        if (!(a instanceof vb)) {
            a.f();
            return null;
        }
        return this.c(a);
    }
    
    public neat.system.graphics.renderer.m c(final neat.bb bb) {
        neat.system.graphics.renderer.m b = null;
        if (bb instanceof vb) {
            final vb vb = (vb)bb;
            b = this.b().b();
            this.cb.a(b);
            this.a(b, vb);
        }
        bb.f();
        return b;
    }
    
    private void a(final neat.system.graphics.renderer.m m, final vb vb) {
        this.b();
        if (vb.e != null) {
            final g a = this.a(vb.e, vb.q, true);
            if (a != null) {
                a.i();
                final g j = m.j();
                m.a(a);
                if (j != null) {
                    this.b(j);
                }
                a.a(vb.u);
                a.c(true);
            }
        }
        if (vb.h >= 0) {
            m.a(vb.f, vb.g, vb.h, vb.i);
        }
        m.a(vb.j);
        if (vb.k > -1000) {
            m.a(vb.k, vb.l);
        }
        if (vb.m >= 0) {
            m.b(vb.m, vb.n);
        }
        if (vb.o >= 0.0f) {
            m.a(vb.o, vb.p);
        }
        else {
            m.s();
        }
        if (vb.s != null) {
            final r c = vb.s.c();
            while (c.a()) {
                final neat.system.graphics.renderer.b d = this.d((kb)c.b());
                if (d != null) {
                    m.a(d);
                }
            }
            c.f();
        }
        else if (vb.t != null) {
            final r a2 = this.bb.a();
            while (a2.a()) {
                final kb kb = (kb)a2.b();
                if (!vb.t.a(kb)) {
                    final neat.system.graphics.renderer.b d2 = this.d(kb);
                    if (d2 == null) {
                        continue;
                    }
                    m.a(d2);
                }
            }
            a2.f();
        }
        m.a(vb.r);
    }
    
    public void a(final neat.system.graphics.renderer.m m) {
        if (!this.cb.d(m)) {
            throw new RuntimeException(a.x.hb[0] + m);
        }
        if (m.n()) {
            m.b(0);
        }
        m.o();
        m.s();
        final g j = m.j();
        m.i();
        if (j != null) {
            this.b(j);
        }
        this.b().b(m);
    }
    
    public void g() {
        this.T = a.l.j();
        this.Z = neat.h.e();
        this.ab = neat.h.e();
        this.bb = neat.h.e();
        this.cb = neat.i.k();
        this.U = false;
        this.V = 0L;
        this.eb = false;
        this.fb = 0;
    }
    
    public void h() {
        this.T.f();
        this.T = null;
        this.p();
        this.Z.f();
        this.Z = null;
        this.ab.f();
        this.ab = null;
        this.bb.f();
        this.bb = null;
        this.cb.f();
        this.cb = null;
        if (this.W != null) {
            this.W.f();
            this.W = null;
        }
        if (this.X != null) {
            this.X.f();
            this.X = null;
        }
        if (this.Y != null) {
            this.Y.f();
            this.Y = null;
        }
        this.S = null;
        if (this.db != null) {
            this.db.f();
            this.db = null;
        }
    }
    
    public abstract void f();
    
    public x() {
        this.S = null;
        this.T = null;
        this.W = null;
        this.X = null;
        this.Y = null;
        this.Z = null;
        this.ab = null;
        this.bb = null;
        this.cb = null;
        this.db = null;
    }
    
    static {
        a.x.hb = new String[] { z(z("lAn(@KYu2\u0014]\tf7\u0012]Hc\"@\\Lk>\u0014]M=")), z(z("jFh/@WKm>\u0003L\tp:\u0013\u0018Gh/@]Gs>\u0012]M'z")), z(z("y\tu4\u000fL\th9\n]Js{\tK\tf7\u0012]Hc\"@]Gs>\u0012]M=")), z(z("\u0014\tb#\tL@i<Z")), z(z("|@a=\u0005JLi/@JFh/@WKm>\u0003L\tn(@]Gs>\u0012]M+{\u0001[]r:\f\u0002")), z(z("lAn(@[Hi-\u0001K\tn(@YEu>\u0001\\P')\u0005UFq>\u0004\u0002")), z(z("lAn(@ZN'8\u0001V_f(@QZ':\fJLf?\u0019\u0018Hc?\u0005\\\u0013")), z(z("lAn(@[Hi-\u0001K\tf7\u0012]Hc\"@TFf?\u0005\\\u0013")) };
        a = false;
        b = false;
        c = 0;
        d = z(z("Z@i<\u000f"));
        switch (4131) {
            default: {
                e = false;
                f = false;
                g = false;
                h = 0;
                i = false;
                j = false;
                k = false;
                break;
            }
            case 0: {
                f = false;
                i = false;
                k = true;
                e = true;
                g = true;
                h = 300;
                j = true;
                break;
            }
        }
        switch (4131) {
            default: {
                l = false;
                m = false;
                n = false;
                o = false;
                p = false;
                q = 0;
                r = 0;
                s = 0;
                t = 0;
                u = 0;
                break;
            }
            case 0: {
                l = true;
                m = false;
                n = false;
                o = false;
                p = false;
                q = 0;
                r = 0;
                s = 0;
                t = 0;
                u = 0;
                break;
            }
        }
        switch (4131) {
            default: {
                v = false;
                break;
            }
            case 0: {
                v = true;
                break;
            }
        }
        switch (4131) {
            default: {
                w = false;
                x = false;
                y = false;
                z = false;
                A = null;
                B = 80;
                C = null;
                D = null;
                E = null;
                F = null;
                G = null;
                H = null;
                I = -1;
                break;
            }
            case 0: {
                w = false;
                x = false;
                y = false;
                z = false;
                A = null;
                B = 80;
                C = null;
                D = null;
                E = null;
                F = null;
                G = null;
                H = null;
                I = 1;
                break;
            }
        }
        J = false;
        K = false;
        L = 0;
        switch (4131) {
            default: {
                M = false;
                N = null;
                O = null;
                break;
            }
            case 0: {
                M = true;
                N = kb.a(z(z("Z@`=\tKA`:\r]Z)8\u000fU\u0012n-\tTEf<\u0005\u0016Jh6[Q_n7\fYNb<\u0001ULtu\u0003WD<5\t[Bm)\u0007JFp5\u0015HNf6\u0005K\u0007d4\r\u0003Gn8\u000bR[)8\u000fU\u0012f7\u0017YPt5\u0005Y])8\u000fU")));
                O = kb.a(z(z("Z@`=\tKA`:\r]Z)8\u000fU")));
                break;
            }
        }
        switch (4131) {
            default: {
                P = false;
                Q = null;
                R = null;
                break;
            }
            case 0: {
                P = true;
                Q = null;
                R = kb.a(z(z("YMR)\f")));
                break;
            }
        }
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
        charArray[n] ^= '`';
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
                            c2 = '8';
                            break;
                        }
                        case 1: {
                            c2 = ')';
                            break;
                        }
                        case 2: {
                            c2 = '\u0007';
                            break;
                        }
                        case 3: {
                            c2 = '[';
                            break;
                        }
                        default: {
                            c2 = '`';
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
