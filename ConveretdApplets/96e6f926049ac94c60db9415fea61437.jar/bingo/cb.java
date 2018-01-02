// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.system.lb;
import a.y;
import neat.system.n;
import a.b;
import neat.bb;
import a.gb;
import neat.system.graphics.renderer.p;
import a.rb;
import neat.system.graphics.renderer.m;
import neat.kb;
import neat.system.f;
import a.o;
import a.q;
import a.s;
import a.h;

public class cb extends h implements s, q, o
{
    private static f m;
    private int n;
    private boolean o;
    private int p;
    private kb q;
    private m r;
    private m s;
    private m t;
    private m u;
    private m v;
    private m w;
    private int x;
    private int y;
    private boolean z;
    private boolean A;
    private int B;
    private static /* synthetic */ Class C;
    private static String[] D;
    
    private void b(final int n) {
        if (n == this.n) {
            return;
        }
        this.n = n;
        if (this.n == 0) {
            return;
        }
        final cd cd = (cd)this.a();
        switch (this.n) {
            case 1: {
                this.o = false;
                this.p = 0;
                break;
            }
            case 10: {
                this.r();
                if (!this.c()) {
                    this.b(20);
                    return;
                }
                break;
            }
            case 30: {
                this.s();
                break;
            }
            case 40: {
                final kb a = kb.a(cb.D[2]);
                this.b(a);
                a.f();
                break;
            }
        }
    }
    
    private void c(final int n) {
        if (this.n == 0) {
            return;
        }
        final cd cd = (cd)this.a();
        this.p += n;
        switch (this.n) {
            case 1: {
                this.b(10);
                return;
            }
            case 10: {
                if (!this.d()) {
                    this.l();
                    this.b(20);
                    return;
                }
                break;
            }
            case 20: {
                if (this.o) {
                    this.b(30);
                    return;
                }
                this.b();
                break;
            }
            case 30: {
                if (!this.t() && this.p >= cd.v) {
                    this.b(40);
                    return;
                }
                break;
            }
        }
        if (!this.o && this.p >= cd.s) {
            this.b();
        }
        this.d(n);
        this.e(0);
    }
    
    private void b() {
        if (this.o) {
            return;
        }
        this.o = true;
        final rb a = this.i.d().a();
        if (a instanceof dd) {
            final dd dd = (dd)a;
            final p b = this.i.b();
            b.a(dd.g);
            dd.g = b.o();
        }
    }
    
    private boolean c() {
        this.k();
        final cd cd = (cd)this.a();
        if (cd.x == null) {
            return false;
        }
        final neat.bb a = cd.x.a();
        if (!(a instanceof a.kb)) {
            throw new RuntimeException(cb.D[0] + cd.x);
        }
        final a.f f = (a.f)this.i.a((gb)a);
        if (f == null) {
            throw new RuntimeException(cb.D[0] + cd.x);
        }
        this.q = f.b().b();
        f.a();
        return true;
    }
    
    private boolean d() {
        if (this.q == null) {
            return false;
        }
        final a.f f = (a.f)this.i.b(this.q);
        return f != null && f.b();
    }
    
    private void k() {
        if (this.q == null) {
            return;
        }
        final a.f f = (a.f)this.i.b(this.q);
        if (f != null) {
            this.i.d(f);
        }
        this.q.f();
        this.q = null;
    }
    
    private void l() {
        if (this.q == null) {
            return;
        }
        final a.f f = (a.f)this.i.b(this.q);
        if (f != null) {
            f.c();
        }
    }
    
    public void p() {
        final cd cd = (cd)this.a();
        if (cd.l != null) {
            this.i.a(cd.l.a());
        }
        if (cd.m != null) {
            this.r = this.i.b(cd.m);
            if (this.r == null) {
                throw new RuntimeException(cb.D[1] + cd.m);
            }
            this.r.a(true);
        }
        if (cd.n != null) {
            this.s = this.i.b(cd.n);
            if (this.s == null) {
                throw new RuntimeException(cb.D[1] + cd.n);
            }
            this.s.a(true);
        }
        if (cd.o != null) {
            this.t = this.i.b(cd.o);
            if (this.t == null) {
                throw new RuntimeException(cb.D[1] + cd.o);
            }
            this.t.a(false);
        }
        if (cd.p != null) {
            this.u = this.i.b(cd.p);
            if (this.u == null) {
                throw new RuntimeException(cb.D[1] + cd.p);
            }
            this.u.a(false);
        }
        if (cd.q != null) {
            this.v = this.i.b(cd.q);
            if (this.v == null) {
                throw new RuntimeException(cb.D[1] + cd.q);
            }
            this.v.a(false);
        }
        if (cd.r != null) {
            this.w = this.i.b(cd.r);
            if (this.w == null) {
                throw new RuntimeException(cb.D[1] + cd.r);
            }
            this.w.a(false);
        }
    }
    
    public void q() {
        if (this.r != null) {
            this.i.a(this.r);
            this.r = null;
        }
        if (this.s != null) {
            this.i.a(this.s);
            this.s = null;
        }
        if (this.t != null) {
            this.i.a(this.t);
            this.t = null;
        }
        if (this.u != null) {
            this.i.a(this.u);
            this.u = null;
        }
        if (this.v != null) {
            this.i.a(this.v);
            this.v = null;
        }
        if (this.w != null) {
            this.i.a(this.w);
            this.w = null;
        }
        this.i.q();
    }
    
    private void r() {
        final cd cd = (cd)this.a();
        if (this.t != null) {
            this.t.a(true);
        }
        if (this.u != null) {
            this.u.a(true);
            if (this.u.p() > 0) {
                this.u.b(0);
            }
        }
        this.y = 0;
        this.x = 0;
        this.z = (this.u != null && cd.u > 0);
        this.A = false;
        if (this.v != null) {
            this.v.a(true);
        }
        this.e(0);
    }
    
    private void d(final int n) {
        if (!this.z) {
            return;
        }
        final cd cd = (cd)this.a();
        this.x += n;
        if (this.x < cd.t) {
            return;
        }
        final int p = this.u.p();
        if (p <= 0) {
            this.z = false;
            return;
        }
        final int n2 = this.x / cd.u % p;
        this.u.b(n2);
        if (this.A) {
            this.B -= n;
            int w = cd.w;
            if (w < 0) {
                w = p - 1;
            }
            if (n2 == w || this.B <= 0) {
                this.z = false;
            }
        }
    }
    
    private void s() {
        if (this.z) {
            this.A = true;
            this.B = 3000;
        }
    }
    
    private boolean t() {
        return this.z;
    }
    
    private void e(final int n) {
        if (this.w == null) {
            return;
        }
        final int n2 = this.w.j().f() * n / 100;
        if (n2 <= 0) {
            this.w.a(false);
        }
        else {
            this.w.a(true);
            this.w.b(n2, this.w.f());
            this.w.a(0, 0, n2, this.w.j().g());
        }
    }
    
    protected void a(final gb gb) {
        super.a(gb);
        if (!(gb instanceof cd)) {
            throw new RuntimeException(cb.D[3] + gb);
        }
        final cd cd = (cd)gb;
    }
    
    public void d() {
        this.p();
    }
    
    public void a(final int n) {
        this.c(n);
    }
    
    public void a() {
        if (this.n == 0) {
            this.b(1);
        }
        if (this.u != null) {
            this.y = this.u.q();
        }
    }
    
    public boolean a(final n n) {
        return false;
    }
    
    public boolean a(final neat.system.o o) {
        if (a.y.p) {
            if (this.n >= 10 && this.n != 20) {
                if (a.y.q == 1 || a.y.q == 2) {
                    if (o.d >= a.y.r && o.e >= a.y.s && o.d < a.y.t && o.e < a.y.u) {
                        final rb a = this.i.d().a();
                        if (a instanceof dd) {
                            final dd dd = (dd)a;
                            if (dd.C != null) {
                                this.i.b().a(false);
                                dd.g = false;
                                ((lb)this.i.a(lb.i)).c(dd.C);
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public static cb u() {
        return (cb)cb.m.a();
    }
    
    public void f() {
        cb.m.a(this);
    }
    
    public void g() {
        super.g();
        this.n = 0;
    }
    
    public void h() {
        this.q();
        this.k();
        super.h();
    }
    
    static /* synthetic */ Class b(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public cb() {
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
    }
    
    static {
        final String[] d = new String[5];
        final int n = 0;
        final char[] charArray = "9J\u0012LpZG\u0013\n`ZY\u0019\u0018k\u000fY\u001f\u000e$\u0016D\u001d\u000fa\b\u000b\u001a\u0019k\u0017\u000b\b\u0003m\t\u000b\u000f\u0003e\u001eD\u000bKh\u0013E\u0017Q".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0097: {
                if (n2 > 1) {
                    break Label_0097;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = 'z';
                            break;
                        }
                        case 1: {
                            c2 = '+';
                            break;
                        }
                        case 2: {
                            c2 = '|';
                            break;
                        }
                        case 3: {
                            c2 = 'k';
                            break;
                        }
                        default: {
                            c2 = '\u0004';
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
        d[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "9J\u0012LpZG\u0013\n`ZX\f\u0019m\u000eN\\\u0018l\u001bO\u0013\u001c$\u001cY\u0013\u0006$\u000eC\u0015\u0018$\u0016B\u0012\u0000>".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0213: {
                if (n6 > 1) {
                    break Label_0213;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = 'z';
                            break;
                        }
                        case 1: {
                            c4 = '+';
                            break;
                        }
                        case 2: {
                            c4 = '|';
                            break;
                        }
                        case 3: {
                            c4 = 'k';
                            break;
                        }
                        default: {
                            c4 = '\u0004';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        d[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "\u001dJ\u0011\u000e".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0329: {
                if (n10 > 1) {
                    break Label_0329;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = 'z';
                            break;
                        }
                        case 1: {
                            c6 = '+';
                            break;
                        }
                        case 2: {
                            c6 = '|';
                            break;
                        }
                        case 3: {
                            c6 = 'k';
                            break;
                        }
                        default: {
                            c6 = '\u0004';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        d[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = ".C\u0015\u0018$\tC\u001d\u000fk\r\u000b\u0013\r$\u0013_\u0019\u0006$\u0017^\u000f\u001f$\u0018N\\\n$.B\b\u0007a.J\u000f\u0000W\u0012J\u0018\u0004sZB\u0011\u001bh\u001fF\u0019\u0005p\u001b_\u0015\u0004j@".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0445: {
                if (n14 > 1) {
                    break Label_0445;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = 'z';
                            break;
                        }
                        case 1: {
                            c8 = '+';
                            break;
                        }
                        case 2: {
                            c8 = '|';
                            break;
                        }
                        case 3: {
                            c8 = 'k';
                            break;
                        }
                        default: {
                            c8 = '\u0004';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        d[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "\u0018B\u0012\fkTH\u001e".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0561: {
                if (n18 > 1) {
                    break Label_0561;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = 'z';
                            break;
                        }
                        case 1: {
                            c10 = '+';
                            break;
                        }
                        case 2: {
                            c10 = '|';
                            break;
                        }
                        case 3: {
                            c10 = 'k';
                            break;
                        }
                        default: {
                            c10 = '\u0004';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 <= n20) {
                d[n17] = new String(charArray5).intern();
                cb.D = d;
                cb.m = new f((cb.C != null) ? cb.C : (cb.C = b(cb.D[4])));
                return;
            }
            continue;
        }
    }
}
