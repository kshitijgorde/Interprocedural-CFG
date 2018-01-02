// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.system.j;
import neat.system.i;
import zylom.ZylomDataGather;
import neat.system.lb;
import neat.nb;
import a.y;
import a.b;
import neat.bb;
import a.gb;
import a.d;
import a.ib;
import a.yb;
import neat.kb;
import neat.system.f;
import a.n;
import a.u;
import a.q;
import a.s;

public class w extends p implements s, q, u, n
{
    private static f l;
    private kb m;
    private boolean n;
    private boolean o;
    private boolean p;
    private int q;
    private int r;
    private boolean s;
    private yb t;
    private boolean u;
    private static /* synthetic */ Class v;
    private static String[] z;
    
    public void b() {
        this.c();
        final wc wc = (wc)this.a();
        if (wc.i != null) {
            final neat.bb a = wc.i.a();
            if (!(a instanceof ib)) {
                throw new RuntimeException(w.z[2] + a);
            }
            this.m = a.a().b();
            final d d = (d)this.i.a((gb)a);
            if (d != null) {
                d.a(this);
                d.a(this.n);
                d.b(this.o);
                d.d(this.p);
            }
        }
    }
    
    public void c() {
        if (this.m != null) {
            final d d = (d)this.i.b(this.m);
            if (d != null) {
                d.b(this);
                this.i.d(d);
            }
            this.m.f();
            this.m = null;
        }
    }
    
    public void b(final int q) {
        if (q == 2) {
            return;
        }
        final boolean b = this.o && this.n;
        if (b) {
            this.q();
        }
        this.q = q;
        if (b) {
            this.k();
        }
    }
    
    public void a(final int r, final boolean s) {
        final boolean b = this.o && this.n;
        if (b) {
            this.q();
        }
        this.q = 2;
        this.r = r;
        this.s = s;
        if (b) {
            this.k();
        }
    }
    
    public boolean d() {
        yb s = this.a().S();
        if (s == null) {
            final wc wc = (wc)this.a();
            if (wc.Nb == null) {
                return false;
            }
            s = (yb)wc.Nb.a();
            if (s == null) {
                return false;
            }
        }
        final boolean b = this.o && this.n;
        if (b) {
            this.q();
        }
        this.q = 8;
        if (this.t != null) {
            this.t.f();
        }
        this.t = s;
        this.u = false;
        if (b) {
            this.k();
        }
        return true;
    }
    
    public boolean a(final boolean u) {
        yb t = this.a().T();
        if (t == null) {
            final wc wc = (wc)this.a();
            if (wc.Nb == null) {
                return false;
            }
            t = (yb)wc.Nb.a();
            if (t == null) {
                return false;
            }
        }
        final boolean b = this.o && this.n;
        if (b) {
            this.q();
        }
        this.q = 8;
        if (this.t != null) {
            this.t.f();
        }
        this.t = t;
        this.u = u;
        if (b) {
            this.k();
        }
        return true;
    }
    
    public void k() {
        if (!this.n) {
            return;
        }
        if (this.o) {
            return;
        }
        this.o = true;
        this.a().V();
        if (!this.n()) {
            this.q();
        }
    }
    
    public boolean m() {
        return this.n && this.o;
    }
    
    private boolean n() {
        final r u = this.a().U();
        if (u == null) {
            return false;
        }
        if (!this.o) {
            this.p();
            return false;
        }
        final wc wc = (wc)this.a();
        boolean b = false;
        final float n = 1.0f;
        switch (this.q) {
            case 0: {
                u.a(wc.o);
                if (wc.N != null) {
                    boolean b2 = false;
                    if (!this.i.l()) {
                        if (a.y.k) {
                            b2 = true;
                        }
                    }
                    u.a(wc.N, b2 ? ld.VARIABLE__BUTTON__TRUE : ld.VARIABLE__BUTTON__FALSE);
                }
                if (!this.i.l()) {
                    final dd c = this.a().C();
                    if (c != null) {
                        if (wc.w != null) {
                            u.a(wc.w, c.h ? ld.VARIABLE__BUTTON__TRUE : ld.VARIABLE__BUTTON__FALSE);
                        }
                        if (wc.y != null) {
                            u.a(wc.y, c.j ? ld.VARIABLE__BUTTON__TRUE : ld.VARIABLE__BUTTON__FALSE);
                        }
                        if (wc.B != null) {
                            u.a(wc.B, c.t ? ld.VARIABLE__BUTTON__TRUE : ld.VARIABLE__BUTTON__FALSE);
                        }
                        if (wc.C != null) {
                            u.a(wc.C, c.u ? ld.VARIABLE__BUTTON__TRUE : ld.VARIABLE__BUTTON__FALSE);
                        }
                    }
                }
                u.s();
                b = true;
                break;
            }
            case 2: {
                boolean b3 = false;
                switch (this.r) {
                    case 0: {
                        if (!a.y.e) {
                            b3 = true;
                        }
                        break;
                    }
                    case 1: {
                        b3 = true;
                        break;
                    }
                    case 2: {
                        b3 = true;
                        break;
                    }
                }
                if (b3) {
                    break;
                }
                u.a(wc.X);
                if (wc.db != null) {
                    final kb a = nb.a(this.r);
                    u.a(wc.db, a);
                    a.f();
                }
                if (wc.gb != null) {
                    u.a(wc.gb, this.s ? ld.VARIABLE__BUTTON__TRUE : ld.VARIABLE__BUTTON__FALSE);
                }
                u.s();
                b = true;
                break;
            }
            case 8: {
                final yb t = this.t;
                this.t = null;
                if (!this.a().G() && !this.u) {
                    if (t != null) {
                        t.f();
                    }
                    break;
                }
                if (!this.u) {
                    boolean h = true;
                    if (wc.Pb != null) {
                        if (t != null) {
                            if (t.e != null) {
                                final kb d = t.e.d(wc.Pb);
                                if (d != null) {
                                    final int b4 = nb.b(d, -1);
                                    if (b4 >= 0) {
                                        h = this.a().h(b4);
                                    }
                                }
                            }
                        }
                    }
                    if (!h) {
                        if (t != null) {
                            t.f();
                        }
                        break;
                    }
                }
                u.a(t);
                if (wc.Ob != null) {
                    u.a(wc.Ob, ld.VARIABLE__BUTTON__FALSE);
                }
                if (wc.Qb != null) {
                    u.a(wc.Qb, this.a().l());
                }
                u.s();
                b = true;
                break;
            }
            case 9: {
                u.a(wc.Rb);
                u.s();
                b = true;
                break;
            }
        }
        if (b) {
            u.a(n);
        }
        u.B();
        if (this.m != null) {
            final d d2 = (d)this.i.b(this.m);
            if (d2 != null) {
                d2.b(false);
                d2.a(false);
            }
        }
        return b;
    }
    
    private void p() {
        final r u = this.a().U();
        if (u != null) {
            u.x();
        }
    }
    
    public void q() {
        if (!this.n) {
            return;
        }
        if (!this.o) {
            return;
        }
        this.o = false;
        this.a().W();
        this.p();
        if (this.m != null) {
            final d d = (d)this.i.b(this.m);
            if (d != null) {
                d.b(false);
                d.a(true);
            }
        }
    }
    
    private void r() {
        if (!this.n) {
            return;
        }
        if (!this.o) {
            return;
        }
        switch (this.q) {
            case 0: {
                this.s();
                break;
            }
            case 2: {
                this.t();
                break;
            }
            case 8: {
                this.u();
                break;
            }
            case 9: {
                this.v();
                break;
            }
        }
    }
    
    private void s() {
        final r u = this.a().U();
        if (u == null) {
            return;
        }
        final wc wc = (wc)this.a();
        if (wc.E != null) {
            final kb b = u.b(wc.p);
            if (b != null) {
                if (b.equals(wc.t)) {
                    final kb a = kb.a(w.z[1]);
                    u.a(wc.p, a);
                    a.f();
                    this.q();
                    if (!this.i.l()) {
                        final dd c = this.a().C();
                        if (c.y != null) {
                            this.a().E();
                            ((lb)this.i.a(lb.i)).c(c.y);
                        }
                    }
                    else {
                        this.d();
                        this.k();
                    }
                    return;
                }
                if (!b.equals(wc.s)) {
                    if (b.equals(wc.u)) {
                        final kb a2 = kb.a(w.z[1]);
                        u.a(wc.p, a2);
                        a2.f();
                        if (a.y.e) {
                            final dd c2 = this.a().C();
                            if (c2.z != null) {
                                this.a().E();
                                ((lb)this.i.a(lb.i)).c(c2.z);
                            }
                            return;
                        }
                    }
                    else if (b.equals(wc.q)) {
                        final kb a3 = kb.a(w.z[1]);
                        u.a(wc.p, a3);
                        a3.f();
                        if (!this.i.l()) {
                            final dd c3 = this.a().C();
                            if (wc.w != null) {
                                c3.h = ld.VARIABLE__BUTTON__TRUE.equals(u.b(wc.w));
                                ZylomDataGather.GetHelper().setMuted(!c3.h);
                            }
                            if (wc.y != null) {
                                c3.j = ld.VARIABLE__BUTTON__TRUE.equals(u.b(wc.y));
                            }
                            if (wc.B != null) {
                                c3.t = ld.VARIABLE__BUTTON__TRUE.equals(u.b(wc.B));
                            }
                            if (wc.C != null) {
                                c3.u = ld.VARIABLE__BUTTON__TRUE.equals(u.b(wc.C));
                            }
                            if (wc.D != null) {
                                c3.v = ld.VARIABLE__BUTTON__TRUE.equals(u.b(wc.D));
                            }
                            this.a().B();
                        }
                        if (this.a().x()) {
                            if (this.a().db() == 0) {
                                this.a().K();
                                return;
                            }
                            this.q();
                            this.b(9);
                            this.k();
                            return;
                        }
                    }
                }
            }
        }
        if (wc.j != null) {
            final kb b2 = u.b(wc.j);
            if (b2 != null && b2.equals(ld.VARIABLE__BUTTON__TRUE)) {
                u.a(wc.j, ld.VARIABLE__BUTTON__FALSE);
                if (!this.i.l()) {
                    final dd c4 = this.a().C();
                    if (wc.w != null) {
                        c4.h = ld.VARIABLE__BUTTON__TRUE.equals(u.b(wc.w));
                        ZylomDataGather.GetHelper().setMuted(!c4.h);
                    }
                    if (wc.y != null) {
                        c4.j = ld.VARIABLE__BUTTON__TRUE.equals(u.b(wc.y));
                    }
                    if (wc.B != null) {
                        c4.t = ld.VARIABLE__BUTTON__TRUE.equals(u.b(wc.B));
                    }
                    if (wc.C != null) {
                        c4.u = ld.VARIABLE__BUTTON__TRUE.equals(u.b(wc.C));
                    }
                    if (wc.D != null) {
                        c4.v = ld.VARIABLE__BUTTON__TRUE.equals(u.b(wc.D));
                    }
                    this.a().B();
                }
                this.q();
            }
        }
        if (wc.k != null) {
            final kb b3 = u.b(wc.k);
            if (b3 != null && b3.equals(ld.VARIABLE__BUTTON__TRUE)) {
                u.a(wc.k, ld.VARIABLE__BUTTON__FALSE);
                this.q();
            }
        }
    }
    
    private void t() {
        final r u = this.a().U();
        if (u == null) {
            return;
        }
        final wc wc = (wc)this.a();
        if (wc.l != null) {
            final kb b = u.b(wc.l);
            if (b != null && b.equals(ld.VARIABLE__BUTTON__TRUE)) {
                u.a(wc.l, ld.VARIABLE__BUTTON__FALSE);
                this.q();
            }
        }
        if (wc.Y != null) {
            final kb b2 = u.b(wc.Y);
            if (b2 != null && b2.equals(ld.VARIABLE__BUTTON__TRUE)) {
                u.a(wc.Y, ld.VARIABLE__BUTTON__FALSE);
                final dd c = this.a().C();
                if (c.z != null) {
                    this.a().E();
                    ((lb)this.i.a(lb.i)).c(c.z);
                }
            }
        }
        if (wc.Z != null) {
            final kb b3 = u.b(wc.Z);
            if (b3 != null && b3.equals(ld.VARIABLE__BUTTON__TRUE)) {
                u.a(wc.Z, ld.VARIABLE__BUTTON__FALSE);
                final dd c2 = this.a().C();
                if (c2.A != null) {
                    this.a().E();
                    ((lb)this.i.a(lb.i)).c(c2.A);
                }
                else if (c2.z != null) {
                    this.a().E();
                    ((lb)this.i.a(lb.i)).c(c2.z);
                }
            }
        }
        if (wc.cb != null) {
            final kb b4 = u.b(wc.cb);
            if (b4 != null && b4.equals(ld.VARIABLE__BUTTON__TRUE)) {
                u.a(wc.cb, ld.VARIABLE__BUTTON__FALSE);
                final dd c3 = this.a().C();
                if (c3.B != null) {
                    this.a().E();
                    ((lb)this.i.a(lb.i)).c(c3.B);
                }
                else if (c3.z != null) {
                    this.a().E();
                    ((lb)this.i.a(lb.i)).c(c3.z);
                }
            }
        }
    }
    
    private void u() {
        final r u = this.a().U();
        if (u == null) {
            return;
        }
        final wc wc = (wc)this.a();
        if (wc.l != null) {
            final kb b = u.b(wc.l);
            if (b != null && b.equals(ld.VARIABLE__BUTTON__TRUE)) {
                u.a(wc.l, ld.VARIABLE__BUTTON__FALSE);
                if (wc.Ob != null) {
                    final kb b2 = u.b(wc.Ob);
                    if (b2 != null && b2.equals(ld.VARIABLE__BUTTON__TRUE)) {
                        boolean b3 = false;
                        if (wc.Pb != null) {
                            final kb b4 = u.b(wc.Pb);
                            if (b4 != null) {
                                final int b5 = nb.b(b4, -1);
                                if (b5 >= 0) {
                                    this.a().a(b5, false);
                                    b3 = true;
                                }
                            }
                        }
                        if (!b3) {
                            this.a().c(false);
                        }
                    }
                }
                this.q();
            }
        }
    }
    
    private void v() {
        final r u = this.a().U();
        if (u == null) {
            return;
        }
        final wc wc = (wc)this.a();
        if (wc.k != null) {
            final kb b = u.b(wc.k);
            if (b != null && b.equals(ld.VARIABLE__BUTTON__TRUE)) {
                u.a(wc.k, ld.VARIABLE__BUTTON__FALSE);
                this.q();
                return;
            }
        }
        if (wc.j != null) {
            final kb b2 = u.b(wc.j);
            if (b2 != null && b2.equals(ld.VARIABLE__BUTTON__TRUE)) {
                u.a(wc.j, ld.VARIABLE__BUTTON__FALSE);
                if (this.a().x()) {
                    this.a().K();
                }
            }
        }
    }
    
    private void w() {
        if (!this.n) {
            return;
        }
        if (this.o) {
            this.q();
        }
        else if (!this.o || this.a().U() != null) {}
    }
    
    protected void a(final gb gb) {
        super.a(gb);
        if (!(gb instanceof wc)) {
            throw new RuntimeException(w.z[0] + gb);
        }
        final wc wc = (wc)gb;
    }
    
    public void d() {
        this.b();
    }
    
    public void a(final int n) {
        this.r();
    }
    
    public void a() {
    }
    
    public void a(final d d) {
    }
    
    public void b(final d d) {
    }
    
    public void c(final d d) {
        if (d.b().equals(this.m)) {
            this.m.f();
            this.m = null;
        }
    }
    
    public boolean a(final i i) {
        if (i.e == 27) {
            this.w();
            return true;
        }
        return false;
    }
    
    public boolean a(final neat.system.j j) {
        return true;
    }
    
    public static w x() {
        return (w)w.l.a();
    }
    
    public void f() {
        w.l.a(this);
    }
    
    public void g() {
        super.g();
        this.n = true;
        this.o = false;
        this.p = false;
    }
    
    public void h() {
        this.c();
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
    
    public w() {
        this.m = null;
        this.t = null;
    }
    
    static {
        final String[] z = new String[4];
        final int n = 0;
        final char[] charArray = "ZF0&V}F81\u0019y\u000e63VgZ<8Vc[*!VlKy4VA^-<\u0019`}14\u0012aYy<\u001b~B<8\u0013`Z8!\u001fa@c".toCharArray();
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
                            c2 = '\u000e';
                            break;
                        }
                        case 1: {
                            c2 = '.';
                            break;
                        }
                        case 2: {
                            c2 = 'Y';
                            break;
                        }
                        case 3: {
                            c2 = 'U';
                            break;
                        }
                        default: {
                            c2 = 'v';
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
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "`A-=\u001f`I".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0218: {
                if (n6 > 1) {
                    break Label_0218;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '\u000e';
                            break;
                        }
                        case 1: {
                            c4 = '.';
                            break;
                        }
                        case 2: {
                            c4 = 'Y';
                            break;
                        }
                        case 3: {
                            c4 = 'U';
                            break;
                        }
                        default: {
                            c4 = 'v';
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
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "A^-<\u0019`\u000e; \u0002zA7u\u001b{]-u\u0014k\u000e08\u0006bK40\u0018zO-<\u0019`\u000e63VL[-!\u0019`}14\u0012aYc".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0334: {
                if (n10 > 1) {
                    break Label_0334;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '\u000e';
                            break;
                        }
                        case 1: {
                            c6 = '.';
                            break;
                        }
                        case 2: {
                            c6 = 'Y';
                            break;
                        }
                        case 3: {
                            c6 = 'U';
                            break;
                        }
                        default: {
                            c6 = 'v';
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
        z[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "lG72\u0019 Y".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0454: {
                if (n14 > 1) {
                    break Label_0454;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = '\u000e';
                            break;
                        }
                        case 1: {
                            c8 = '.';
                            break;
                        }
                        case 2: {
                            c8 = 'Y';
                            break;
                        }
                        case 3: {
                            c8 = 'U';
                            break;
                        }
                        default: {
                            c8 = 'v';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 <= n16) {
                z[n13] = new String(charArray4).intern();
                w.z = z;
                w.l = new f((w.v != null) ? w.v : (w.v = a(w.z[3])));
                return;
            }
            continue;
        }
    }
}
