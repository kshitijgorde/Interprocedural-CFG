// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.system.l;
import neat.system.n;
import a.gb;
import neat.system.lb;
import neat.cb;
import neat.kb;
import neat.system.graphics.renderer.m;
import neat.g;
import neat.system.f;
import a.o;
import a.q;
import a.s;

public class ab extends p implements s, q, o, a.p
{
    private static f l;
    private int m;
    private int n;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;
    private int s;
    private int t;
    private boolean u;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;
    private boolean A;
    private g B;
    private g C;
    private m D;
    private kb E;
    private bingo.o F;
    private kb G;
    private int H;
    private m I;
    private kb J;
    private m K;
    private kb L;
    private boolean M;
    private boolean N;
    private m O;
    private kb P;
    private static /* synthetic */ Class Q;
    private static String[] R;
    
    public void b() {
        this.k();
        this.n = -1;
        this.o = false;
        this.p = false;
        this.q = false;
    }
    
    public boolean c() {
        return this.m != 0;
    }
    
    public void b(final int m) {
        this.k();
        this.m = m;
        this.n = -1;
        this.o = false;
        this.q = false;
        this.r = false;
        if (this.q()) {
            final int n = this.n();
            if (n >= 0) {
                if (n + 1 >= this.I().a() - 1) {
                    this.r = true;
                }
            }
        }
        this.v();
        this.r();
    }
    
    public void k() {
        if (this.m == 0) {
            return;
        }
        this.n = -1;
        this.s();
        this.w();
        this.w = false;
        this.x = false;
    }
    
    private void l() {
        this.y();
        if (!this.c()) {
            return;
        }
        this.C();
        if (!this.c()) {
            return;
        }
    }
    
    public int m() {
        if (this.M() && !this.N()) {
            return -1;
        }
        return this.n;
    }
    
    public int n() {
        final bingo.y b = this.b();
        if (b == null) {
            return -1;
        }
        return b.n();
    }
    
    public boolean o() {
        return this.o;
    }
    
    public void p() {
        this.p = true;
    }
    
    public boolean q() {
        return this.p;
    }
    
    private void r() {
        this.s();
        this.u = false;
        switch (this.m) {
            case 1: {
                this.c(10);
                break;
            }
            case 2: {
                this.c(21);
                break;
            }
            default: {
                this.c(10);
                break;
            }
        }
    }
    
    private void s() {
        this.s = 0;
    }
    
    private void c(final int s) {
        if (this.s == s) {
            return;
        }
        this.s = s;
        this.t = 0;
        switch (s) {
            case 0: {}
            case 20: {
                if (this.q()) {
                    this.c(21);
                }
                else {
                    this.c(10);
                }
                break;
            }
            case 22: {
                this.q = true;
                this.F();
                if (this.r) {
                    this.c(30);
                }
                else {
                    this.c(10);
                }
            }
        }
    }
    
    private void d(final int n) {
        if (this.s == 0) {
            return;
        }
        if (this.w) {
            return;
        }
        this.t += n;
        switch (this.s) {
            case 21: {
                if (this.t >= 1000) {
                    this.c(22);
                    break;
                }
                break;
            }
        }
    }
    
    private void t() {
    }
    
    private void u() {
        this.u = true;
        if (!this.q()) {
            this.n = 0;
            this.K();
        }
        else if (this.r) {
            this.o = true;
        }
        else {
            this.n = this.n();
            if (this.n >= 0) {
                ++this.n;
                this.K();
            }
        }
    }
    
    private void v() {
        if (!this.c()) {
            return;
        }
        this.w();
        this.w = false;
        this.x = false;
        final r u = this.a().U();
        if (u == null) {
            return;
        }
        final ad ad = (ad)this.a();
        if (ad.i == null) {
            return;
        }
        u.a(ad.i);
        u.a(this);
        this.v = true;
        this.x();
        this.D();
        this.A();
        if (this.r) {
            this.O();
            if (ad.B != null) {
                this.a().b(ad.B);
            }
        }
        this.x = false;
        u.s();
        u.B();
    }
    
    private void w() {
        if (!this.v) {
            return;
        }
        this.v = false;
        this.E();
        this.J();
        this.B();
        this.P();
        final r u = this.a().U();
        if (u != null) {
            u.b(this);
            u.x();
        }
    }
    
    private void x() {
        if (this.a().U() == null) {
            return;
        }
        final ad ad = (ad)this.a();
    }
    
    private void e(final int n) {
        if (this.v) {
            this.f(n);
            if (this.x) {
                this.x = false;
                this.x();
                final bingo.bb a = this.a();
                if (a != null) {
                    final r u = a.U();
                    if (u != null) {
                        u.A();
                        u.B();
                    }
                }
            }
        }
        else if (this.w) {
            int n2 = 0;
            final bingo.bb a2 = this.a();
            if (a2 != null) {
                final r u2 = a2.U();
                if (u2 != null) {
                    n2 = ((u2.u() && !u2.w()) ? 1 : 0);
                }
                if (a2.L()) {
                    n2 = 1;
                }
            }
            if (n2 == 0) {
                this.v();
                this.w = false;
                this.x = false;
            }
        }
    }
    
    private void y() {
    }
    
    private void z() {
        if (!this.v) {
            return;
        }
        this.H();
        this.L();
    }
    
    private void A() {
        this.B();
        final r u = this.a().U();
        if (u == null) {
            return;
        }
        final ad ad = (ad)this.a();
        this.y = true;
        neat.cb cb = ad.r;
        if (this.m == 1 && ad.s != null) {
            cb = ad.s;
        }
        else if (this.r && ad.t != null) {
            cb = ad.t;
        }
        if (cb != null && ad.u != null) {
            final m b = this.i.b(cb);
            if (b != null) {
                u.a(ad.u, ad.u);
                u.a(ad.u, b);
            }
        }
        if (ad.p != null && ad.q != null) {
            final m b2 = this.i.b(ad.p);
            if (b2 != null) {
                u.a(ad.q, ad.q);
                u.a(ad.q, b2);
            }
        }
    }
    
    private void B() {
        if (!this.y) {
            return;
        }
        this.y = false;
    }
    
    private void C() {
        if (!this.y) {
            return;
        }
        final r u = this.a().U();
        if (u == null) {
            this.B();
            return;
        }
        if (!u.u()) {
            this.B();
            return;
        }
        final ad ad = (ad)this.a();
        if (ad.j != null && ld.VARIABLE__BUTTON__TRUE.equals(u.b(ad.j))) {
            u.a(ad.j, ld.VARIABLE__BUTTON__FALSE);
            this.u();
        }
        if (ad.k != null && ld.VARIABLE__BUTTON__TRUE.equals(u.b(ad.k))) {
            u.a(ad.k, ld.VARIABLE__BUTTON__FALSE);
            final dd c = this.a().C();
            if (c.z != null) {
                ((lb)this.i.a(lb.i)).c(c.z);
            }
        }
    }
    
    private void D() {
        this.E();
        final r u = this.a().U();
        if (u == null) {
            return;
        }
        int n = this.n();
        if (n < 0) {
            return;
        }
        if (this.q) {
            ++n;
        }
        final ad ad = (ad)this.a();
        (this.B = neat.g.j()).c(n);
        (this.C = neat.g.j()).c(n);
        for (int i = 0; i < n; ++i) {
            final oc g = this.g(i);
            if (g != null) {
                if (g.f != null) {
                    final m b = this.i.b(g.f);
                    if (b == null) {
                        throw new RuntimeException(ab.R[2] + g.f);
                    }
                    b.a(false);
                    if (g.g >= 0 && g.h >= 0) {
                        b.a(g.g, g.h);
                    }
                    final neat.lb a = neat.lb.a();
                    if (ad.m != null) {
                        a.a(ad.m);
                    }
                    else {
                        a.c(ab.R[5]);
                    }
                    a.d(i);
                    final kb b2 = a.b();
                    u.a(b2, b);
                    this.B.a(i, b);
                    this.C.a(i, b2);
                }
            }
        }
        final oc g2 = this.g(n);
        if (g2 == null) {
            return;
        }
        if (g2.i != null) {
            this.D = this.i.b(g2.i);
            if (this.D == null) {
                throw new RuntimeException(ab.R[2] + g2.i);
            }
            this.D.a(false);
            if (g2.j >= 0 && g2.k >= 0) {
                this.D.a(g2.j, g2.k);
            }
            final neat.lb a2 = neat.lb.a();
            if (ad.m != null) {
                a2.a(ad.m);
            }
            else {
                a2.c(ab.R[5]);
            }
            a2.d(n);
            u.a(this.E = a2.b(), this.D);
        }
        if (g2.l != null) {
            final m b3 = this.i.b(g2.l);
            if (b3 == null) {
                throw new RuntimeException(ab.R[2] + g2.l);
            }
            b3.a(false);
            if (g2.m >= 0 && g2.n >= 0) {
                b3.a(g2.m, g2.n);
            }
            final neat.lb a3 = neat.lb.a();
            if (ad.o != null) {
                a3.a(ad.o);
            }
            else {
                a3.c(ab.R[6]);
            }
            a3.d(n);
            u.a(this.G = a3.b(), b3);
            this.F = bingo.o.b();
            this.F.b = b3;
            this.F.d = false;
            if (g2.o != null) {
                this.F.e = g2.o.b();
            }
        }
        if (ad.l != null) {
            u.a(ad.l, this.r ? ld.VARIABLE__BUTTON__TRUE : ld.VARIABLE__BUTTON__FALSE);
        }
        this.z = true;
        this.G();
    }
    
    private void E() {
        if (!this.z) {
            return;
        }
        this.z = false;
        if (this.B != null && this.C != null) {
            this.C.i();
            this.C.f();
            this.C = null;
            this.B.f();
            this.B = null;
        }
        if (this.E != null) {
            this.E.f();
            this.E = null;
        }
        this.D = null;
        if (this.G != null) {
            this.G.f();
            this.G = null;
        }
        if (this.F != null) {
            this.F.f();
            this.F = null;
        }
    }
    
    private void F() {
        final r u = this.a().U();
        if (this.B != null && this.C != null) {
            for (int i = 0; i < this.C.f(); ++i) {
                final kb kb = (kb)this.C.a(i);
                if (u != null) {
                    u.c(kb);
                }
            }
            this.C.i();
            this.B.c();
        }
        if (this.E != null) {
            if (u != null) {
                u.c(this.E);
            }
            this.D = null;
        }
        if (this.D != null) {
            this.i.a(this.D);
            this.D = null;
        }
        if (this.G != null) {
            if (u != null) {
                u.c(this.G);
            }
            if (this.F != null) {
                this.F.b = null;
            }
        }
        if (this.F != null) {
            if (this.F.b != null) {
                this.i.a(this.F.b);
                this.F.b = null;
            }
            this.F.f();
            this.F = null;
        }
        this.D();
    }
    
    private void f(final int n) {
        if (!this.z) {
            return;
        }
        if (this.F != null) {
            this.F.a(this.i, n);
        }
    }
    
    private void G() {
        this.A = true;
    }
    
    private void H() {
        if (!this.z) {
            return;
        }
        if (!this.A) {
            return;
        }
        this.A = false;
        final boolean d = !this.w;
        if (this.B != null) {
            for (int i = 0; i < this.B.f(); ++i) {
                final m m = (m)this.B.a(i);
                if (m != null) {
                    m.a(d);
                }
            }
        }
        if (this.D != null) {
            this.D.a(d);
        }
        if (this.F != null) {
            this.F.d = d;
            this.F.a();
        }
    }
    
    private kd I() {
        final bingo.y b = this.b();
        if (b == null) {
            return null;
        }
        final pc o = b.o();
        if (o == null) {
            return null;
        }
        return o.e;
    }
    
    private oc g(final int n) {
        final kd i = this.I();
        if (i == null) {
            return null;
        }
        if (n < 0 || n >= i.a()) {
            return null;
        }
        return (oc)i.c(n);
    }
    
    private void h(final int h) {
        this.J();
        final r u = this.a().U();
        if (u == null) {
            return;
        }
        final ad ad = (ad)this.a();
        if (ad.v != null) {
            this.I = this.i.b(ad.v);
            if (this.I == null) {
                throw new RuntimeException(ab.R[2] + ad.v);
            }
            this.I.a(true);
            final neat.lb a = neat.lb.a();
            if (ad.w != null) {
                a.a(ad.w);
            }
            else {
                a.c(ab.R[0]);
            }
            u.a(this.J = a.b(), this.I);
        }
        if (ad.x != null) {
            this.K = this.i.b(ad.x);
            if (this.K == null) {
                throw new RuntimeException(ab.R[2] + ad.x);
            }
            this.K.a(true);
            final neat.lb a2 = neat.lb.a();
            if (ad.y != null) {
                a2.a(ad.y);
            }
            else {
                a2.c(ab.R[1]);
            }
            u.a(this.L = a2.b(), this.K);
        }
        this.H = h;
        this.M = false;
    }
    
    private void J() {
        if (this.H == 0) {
            return;
        }
        this.H = 0;
        if (this.J != null) {
            this.J.f();
            this.J = null;
        }
        this.I = null;
        if (this.L != null) {
            this.L.f();
            this.L = null;
        }
        this.K = null;
    }
    
    private void K() {
        if (this.H != 0) {
            return;
        }
        this.h(1);
    }
    
    private void L() {
        if (this.H != 0) {
            this.M = true;
        }
    }
    
    public boolean M() {
        return this.H != 0;
    }
    
    public boolean N() {
        return this.M;
    }
    
    private void O() {
        this.P();
        final r u = this.a().U();
        if (u == null) {
            return;
        }
        final ad ad = (ad)this.a();
        if (ad.z != null) {
            this.O = this.i.b(ad.z);
            if (this.O == null) {
                throw new RuntimeException(ab.R[2] + ad.z);
            }
            this.O.a(true);
            final neat.lb a = neat.lb.a();
            if (ad.A != null) {
                a.a(ad.A);
            }
            else {
                a.c(ab.R[3]);
            }
            u.a(this.P = a.b(), this.O);
        }
        this.N = true;
    }
    
    private void P() {
        this.N = false;
        if (this.P != null) {
            this.P.f();
            this.P = null;
        }
        this.O = null;
    }
    
    protected void a(final gb gb) {
        super.a(gb);
        if (!(gb instanceof ad)) {
            throw new RuntimeException(ab.R[4] + gb);
        }
        final ad ad = (ad)gb;
    }
    
    public void d() {
    }
    
    public void a(final int n) {
        this.l();
        this.d(n);
        this.e(n);
    }
    
    public void a() {
        this.t();
        this.z();
    }
    
    public boolean a(final n n) {
        return false;
    }
    
    public boolean a(final neat.system.o o) {
        return false;
    }
    
    public boolean a(final neat.system.m m) {
        return false;
    }
    
    public boolean a(final l l) {
        return false;
    }
    
    public static ab Q() {
        return (ab)ab.l.a();
    }
    
    public void f() {
        ab.l.a(this);
    }
    
    public void g() {
        super.g();
        this.n = -1;
        this.o = false;
        this.q = false;
        this.m = 0;
        this.v = false;
        this.y = false;
        this.z = false;
        this.H = 0;
    }
    
    public void h() {
        this.b();
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
    
    public ab() {
        this.B = null;
        this.C = null;
        this.D = null;
        this.E = null;
        this.F = null;
        this.G = null;
        this.I = null;
        this.J = null;
        this.K = null;
        this.L = null;
        this.O = null;
        this.P = null;
    }
    
    static {
        final String[] r = new String[8];
        final int n = 0;
        final char[] charArray = "l\u0007HK\u0005n".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0102: {
                if (n2 > 1) {
                    break Label_0102;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '\u001c';
                            break;
                        }
                        case 1: {
                            c2 = 'h';
                            break;
                        }
                        case 2: {
                            c2 = ';';
                            break;
                        }
                        case 3: {
                            c2 = '?';
                            break;
                        }
                        default: {
                            c2 = '`';
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
        r[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "p\u0007Z[\tr\u000f".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0222: {
                if (n6 > 1) {
                    break Label_0222;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '\u001c';
                            break;
                        }
                        case 1: {
                            c4 = 'h';
                            break;
                        }
                        case 2: {
                            c4 = ';';
                            break;
                        }
                        case 3: {
                            c4 = '?';
                            break;
                        }
                        default: {
                            c4 = '`';
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
        r[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "_\tU\u0018\u0014<\u0004T^\u0004<\u001cSV\u0013<\u001bKM\th\r\u0001".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0342: {
                if (n10 > 1) {
                    break Label_0342;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '\u001c';
                            break;
                        }
                        case 1: {
                            c6 = 'h';
                            break;
                        }
                        case 2: {
                            c6 = ';';
                            break;
                        }
                        case 3: {
                            c6 = '?';
                            break;
                        }
                        default: {
                            c6 = '`';
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
        r[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "\u007f\u0007UX\u0012}\u001c".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0462: {
                if (n14 > 1) {
                    break Label_0462;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = '\u001c';
                            break;
                        }
                        case 1: {
                            c8 = 'h';
                            break;
                        }
                        case 2: {
                            c8 = ';';
                            break;
                        }
                        case 3: {
                            c8 = '?';
                            break;
                        }
                        default: {
                            c8 = '`';
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
        r[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "H\u0000RL@o\u0000Z[\u000fkHTY@u\u001c^R@q\u001dHK@~\r\u001b^@K\rYr\u0001l;^S\u0005\u007f\u001cTM3t\t_P\u0017<\u0001VO\fy\u0005^Q\u0014}\u001cRP\u000e&".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0582: {
                if (n18 > 1) {
                    break Label_0582;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = '\u001c';
                            break;
                        }
                        case 1: {
                            c10 = 'h';
                            break;
                        }
                        case 2: {
                            c10 = ';';
                            break;
                        }
                        case 3: {
                            c10 = '?';
                            break;
                        }
                        default: {
                            c10 = '`';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 > n20) {
                continue;
            }
            break;
        }
        r[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "p\rMZ\f".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0702: {
                if (n22 > 1) {
                    break Label_0702;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = '\u001c';
                            break;
                        }
                        case 1: {
                            c12 = 'h';
                            break;
                        }
                        case 2: {
                            c12 = ';';
                            break;
                        }
                        case 3: {
                            c12 = '?';
                            break;
                        }
                        default: {
                            c12 = '`';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 > n24) {
                continue;
            }
            break;
        }
        r[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "l\rO".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0822: {
                if (n26 > 1) {
                    break Label_0822;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = '\u001c';
                            break;
                        }
                        case 1: {
                            c14 = 'h';
                            break;
                        }
                        case 2: {
                            c14 = ';';
                            break;
                        }
                        case 3: {
                            c14 = '?';
                            break;
                        }
                        default: {
                            c14 = '`';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 > n28) {
                continue;
            }
            break;
        }
        r[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "~\u0001UX\u000f2\tY".toCharArray();
        int length8;
        int n31;
        final int n30 = n31 = (length8 = charArray8.length);
        int n32 = 0;
        while (true) {
            Label_0942: {
                if (n30 > 1) {
                    break Label_0942;
                }
                length8 = (n31 = n32);
                do {
                    final char c15 = charArray8[n31];
                    char c16 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c16 = '\u001c';
                            break;
                        }
                        case 1: {
                            c16 = 'h';
                            break;
                        }
                        case 2: {
                            c16 = ';';
                            break;
                        }
                        case 3: {
                            c16 = '?';
                            break;
                        }
                        default: {
                            c16 = '`';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n32;
                } while (n30 == 0);
            }
            if (n30 <= n32) {
                r[n29] = new String(charArray8).intern();
                ab.R = r;
                ab.l = new f((ab.Q != null) ? ab.Q : (ab.Q = a(ab.R[7])));
                return;
            }
            continue;
        }
    }
}
