// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import a.gb;
import neat.nb;
import neat.lb;
import neat.kb;
import neat.system.f;
import a.r;
import a.s;

public class z extends p implements s, r
{
    private static f l;
    private boolean m;
    private int n;
    private kb o;
    private int p;
    private boolean q;
    private kb r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private static /* synthetic */ Class z;
    private static String[] A;
    
    public void a() {
        this.k();
        this.m = false;
        this.n = 0;
        if (this.o != null) {
            this.o.f();
            this.o = null;
        }
        this.p = 0;
    }
    
    private void k() {
        this.q = false;
        if (this.r != null) {
            this.r.f();
            this.r = null;
        }
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.y = 0;
    }
    
    public void l() {
        this.a();
        this.m = true;
    }
    
    public void b(final int n) {
        this.k();
        final bingo.bb a = this.a();
        this.r = a.e(n);
        this.x = a.f(n);
    }
    
    public void p() {
        this.k();
        final bingo.bb a = this.a();
        final bingo.y z = a.z();
        if (z == null) {
            return;
        }
        this.b(z.n());
        this.q = true;
        this.y = a.db();
    }
    
    public void q() {
        if (this.q) {
            this.q = false;
            ++this.n;
            if (this.o != null) {
                this.o.f();
                this.o = null;
            }
            final bingo.bb a = this.a();
            this.w = this.a().db() - this.y;
            final bingo.s fb = a.fb();
            if (fb != null) {
                final lb a2 = lb.a();
                final kb q = fb.Q();
                if (q != null) {
                    a2.a(q);
                }
                final int p = fb.P();
                if (p >= 0) {
                    a2.d(p);
                }
                this.o = a2.b();
                this.s = fb.sb();
            }
        }
    }
    
    public void c(final int n) {
        if (n < 2) {
            return;
        }
        if (this.q) {
            ++this.u;
            if (n > this.v) {
                this.v = n;
            }
        }
        if (this.m && n > this.p) {
            this.p = n;
        }
    }
    
    public void r() {
        final bingo.r u = this.a().U();
        if (u == null) {
            return;
        }
        final zc zc = (zc)this.a();
        if (zc.i != null) {
            final kb a = nb.a(this.a().db());
            u.a(zc.i, a);
            a.f();
        }
        if (zc.j != null) {
            final kb a2 = nb.a(this.n);
            u.a(zc.j, a2);
            a2.f();
        }
        if (this.o != null && zc.k != null) {
            u.a(zc.k, this.o);
        }
        if (zc.l != null) {
            int p = this.p;
            if (p <= 1) {
                p = 0;
            }
            final kb a3 = nb.a(p);
            u.a(zc.l, a3);
            a3.f();
        }
        if (this.r != null && zc.m != null) {
            u.a(zc.m, this.r);
        }
        if (zc.n != null) {
            final kb g = this.a().g(this.s);
            u.a(zc.n, g);
            g.f();
        }
        if (zc.o != null) {
            kb kb;
            if (this.t > 0) {
                kb = this.a().g(this.t);
            }
            else {
                kb = neat.kb.a("-");
            }
            u.a(zc.o, kb);
            kb.f();
        }
        if (zc.r != null) {
            int u2 = this.u;
            if (u2 <= 1) {
                u2 = 0;
            }
            final kb a4 = nb.a(u2);
            u.a(zc.r, a4);
            a4.f();
        }
        if (zc.s != null) {
            int v = this.v;
            if (v <= 1) {
                v = 0;
            }
            final kb a5 = nb.a(v);
            u.a(zc.s, a5);
            a5.f();
        }
        if (zc.p != null) {
            final kb a6 = nb.a(this.w);
            u.a(zc.p, a6);
            a6.f();
        }
        if (zc.q != null) {
            final kb a7 = nb.a(this.x);
            u.a(zc.q, a7);
            a7.f();
        }
    }
    
    protected void a(final gb gb) {
        super.a(gb);
        if (!(gb instanceof zc)) {
            throw new RuntimeException(bingo.z.A[0] + gb);
        }
        final zc zc = (zc)gb;
    }
    
    public void d() {
    }
    
    public void a(final int n) {
    }
    
    public void b() {
    }
    
    public void c() {
    }
    
    public static z s() {
        return (z)bingo.z.l.a();
    }
    
    public void f() {
        bingo.z.l.a(this);
    }
    
    public void g() {
        super.g();
    }
    
    public void h() {
        if (this.o != null) {
            this.o.f();
            this.o = null;
        }
        if (this.r != null) {
            this.r.f();
            this.r = null;
        }
        super.h();
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public z() {
        this.o = null;
        this.r = null;
    }
    
    static {
        final String[] a = new String[2];
        final int n = 0;
        final char[] charArray = "C\\\u0000z\rd\\\bmB`\u0014\u0006o\r~@\fd\rzA\u001a}\ruQIh\rD@\b}^D\\\bmB`\u0014\u0000d]{Q\u0004lCcU\u001d`By\u000e".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '\u0017';
                            break;
                        }
                        case 1: {
                            c2 = '4';
                            break;
                        }
                        case 2: {
                            c2 = 'i';
                            break;
                        }
                        case 3: {
                            c2 = '\t';
                            break;
                        }
                        default: {
                            c2 = '-';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        a[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "u]\u0007nB9N".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '\u0017';
                            break;
                        }
                        case 1: {
                            c4 = '4';
                            break;
                        }
                        case 2: {
                            c4 = 'i';
                            break;
                        }
                        case 3: {
                            c4 = '\t';
                            break;
                        }
                        default: {
                            c4 = '-';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 <= n8) {
                a[n5] = new String(charArray2).intern();
                bingo.z.A = a;
                bingo.z.l = new f((bingo.z.z != null) ? bingo.z.z : (bingo.z.z = a(bingo.z.A[1])));
                return;
            }
            continue;
        }
    }
}
