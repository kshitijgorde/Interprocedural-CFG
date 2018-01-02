// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.system.n;
import a.gb;
import a.g;
import neat.bb;
import neat.cb;
import neat.kb;
import a.db;
import a.z;
import neat.system.graphics.renderer.m;
import neat.system.f;
import a.o;
import a.q;
import a.s;

public class x extends p implements s, q, o
{
    private static f l;
    private int m;
    private boolean n;
    private boolean o;
    private m p;
    private int q;
    private int r;
    private m s;
    private int t;
    private int u;
    private z v;
    private db w;
    private m x;
    private nc y;
    private int z;
    private kb A;
    private int B;
    private int C;
    private float D;
    private static /* synthetic */ Class E;
    private static String[] F;
    
    public void b() {
        this.m = 0;
        this.o = false;
        if (this.p != null) {
            this.i.a(this.p);
            this.p = null;
        }
        if (this.s != null) {
            this.i.a(this.s);
            this.s = null;
        }
        if (this.w != null) {
            this.w.a(this.i);
            this.w = null;
        }
        if (this.x != null) {
            this.i.a(this.x);
            this.x = null;
        }
        if (this.v != null) {
            this.v.f();
            this.v = null;
        }
        if (this.y != null) {
            this.y.f();
            this.y = null;
        }
        this.q();
    }
    
    public void a(final boolean n) {
        if (this.n == n) {
            return;
        }
        this.n = n;
        this.k();
    }
    
    public void a(final neat.cb cb) {
        if (cb == null) {
            return;
        }
        this.b();
        final xc xc = (xc)this.a();
        final neat.bb a = cb.a();
        if (!(a instanceof nc)) {
            throw new RuntimeException(bingo.x.F[1] + cb);
        }
        this.y = (nc)a;
        if (xc.i != null) {
            this.p = this.i.b(xc.i);
            this.q = xc.j;
            this.r = xc.k;
            if (this.p != null) {
                this.p.a(this.q + this.y.e, this.r + this.y.f);
                this.p.a(false);
                if (this.y.m >= 0) {
                    this.p.a(this.y.m + 2);
                }
            }
        }
        if (xc.l != null) {
            this.s = this.i.b(xc.l);
            this.t = xc.m;
            this.u = xc.n;
            if (this.s != null) {
                this.s.a(this.t + this.y.e, this.u + this.y.f);
                this.s.a(false);
                if (this.y.m >= 0) {
                    this.s.a(this.y.m + 3);
                }
            }
        }
        if (xc.o != null) {
            this.v = this.i.b(xc.o);
            if (this.v == null) {
                throw new RuntimeException(bingo.x.F[0] + xc.o);
            }
        }
        if (this.v != null && this.y.g != null && this.p != null) {
            (this.w = this.v.b(this.i)).f(this.i);
            final int c = this.p.c();
            final int d = this.p.d();
            this.w.a(this.i, c, d, this.p.e(), this.p.f());
            this.w.a(this.i, c + this.y.h, d + this.y.i + this.p.f() / 2 - this.v.d() / 2 + xc.p);
            if (this.y.m >= 0) {
                this.w.a(this.i, this.y.m + 1);
            }
            else {
                this.w.a(this.i, this.p.g() - 1);
            }
            this.w.b(this.i, this.y.g);
        }
        if (this.y.j != null) {
            this.x = this.i.b(this.y.j);
            if (this.x != null) {
                this.x.a(this.p.c() + this.y.k, this.p.d() + this.y.l);
                this.x.a(false);
                if (this.y.m >= 0) {
                    this.x.a(this.y.m + 2);
                }
            }
        }
        this.m = 1;
        this.o = false;
        this.z = 0;
        final t jb = this.a().jb();
        if (jb != null) {
            jb.s();
        }
        this.k();
    }
    
    public boolean c() {
        if (this.y == null || this.m == 0) {
            return false;
        }
        if (this.m != 3) {
            this.m = 3;
            this.z = 0;
            this.p();
            return true;
        }
        return false;
    }
    
    private boolean d() {
        if (this.m != 2) {
            return false;
        }
        if (this.y != null) {
            if (this.y.o < 0) {
                return false;
            }
            if (this.y.p >= 0 && this.z < this.y.p) {
                return false;
            }
        }
        return this.c();
    }
    
    public boolean e() {
        return this.y != null;
    }
    
    private void b(final int n) {
        if (this.y == null) {
            return;
        }
        if (this.p == null) {
            this.b();
            return;
        }
        this.z += n;
        this.o = true;
        this.k();
        final xc xc = (xc)this.a();
        if (this.m == 1) {
            int n2 = xc.q;
            if (this.y.q >= 0) {
                n2 = this.y.q;
            }
            if (this.z >= n2) {
                this.a(1.0f);
                this.m = 2;
                this.z = 0;
                this.l();
            }
            else {
                this.a(this.z / n2);
            }
        }
        else if (this.m == 2) {
            if (this.z >= this.y.o && this.y.o >= 0) {
                this.m = 3;
                this.z = 0;
                this.p();
            }
        }
        else if (this.m == 3) {
            int n3 = xc.r;
            if (this.y.r >= 0) {
                n3 = this.y.r;
            }
            if (this.z >= n3) {
                this.m = 0;
                this.b();
                return;
            }
            this.a(1.0f - this.z / n3);
        }
    }
    
    public int f() {
        if (this.p == null) {
            return 0;
        }
        return this.p.c() + this.p.e() / 2;
    }
    
    public int g() {
        if (this.p == null) {
            return 0;
        }
        return this.p.d() + this.p.f() / 2;
    }
    
    private void k() {
        boolean n = this.n;
        if (this.y != null && this.y.n) {
            n |= true;
        }
        final boolean b = n & this.o;
        if (this.p != null) {
            this.p.a(b);
        }
        if (this.s != null) {
            this.s.a(b);
        }
        if (this.w != null) {
            if (b) {
                this.w.e(this.i);
            }
            else {
                this.w.f(this.i);
            }
        }
        if (this.x != null) {
            this.x.a(b);
        }
    }
    
    private void a(final float n) {
        float n3;
        final float n2 = n3 = 1.0f - n;
        if (n3 > 1.0f) {
            n3 = 1.0f;
        }
        if (this.p != null) {
            this.p.a(n, n3);
        }
        if (this.s != null) {
            this.s.a(-n, 1.0f);
        }
        if (this.w != null) {
            this.w.b(this.i, n, n2);
        }
        if (this.x != null) {
            this.x.a(n, n3);
        }
    }
    
    private void l() {
        this.q();
        if (this.y == null) {
            return;
        }
        if (this.y.s == null) {
            return;
        }
        this.A = this.y.s.b();
        this.B = 1;
        this.C = 0;
        this.D = 0.0f;
        final g a = this.a().a(this.A);
        if (a == null) {
            this.q();
            return;
        }
        a.a(this.D);
        a.c();
    }
    
    private void p() {
        if (this.y == null) {
            this.q();
            return;
        }
        if (this.A == null || this.m == 0 || this.m == 3) {
            return;
        }
        this.B = 3;
        this.C = 0;
    }
    
    private void q() {
        if (this.A != null) {
            final g a = this.a().a(this.A);
            if (a != null) {
                a.k();
            }
            this.A.f();
            this.A = null;
        }
        this.B = 0;
    }
    
    private void c(final int n) {
        if (this.A == null || this.m == 0) {
            return;
        }
        if (this.y == null) {
            this.q();
            return;
        }
        final g a = this.a().a(this.A);
        if (a == null) {
            this.q();
            return;
        }
        switch (this.B) {
            case 1: {
                float n2 = 1.0f;
                this.C += n;
                if (this.C >= this.y.t) {
                    this.B = 2;
                }
                else {
                    n2 = this.C / this.y.t;
                    if (n2 > 1.0f) {
                        n2 = 1.0f;
                    }
                }
                a.a(n2);
            }
            case 3: {
                this.C += n;
                if (this.C >= this.y.u) {
                    this.q();
                    return;
                }
                float n3 = 1.0f - this.C / this.y.u;
                if (n3 < 0.0f) {
                    n3 = 0.0f;
                }
                a.a(n3);
                break;
            }
        }
    }
    
    protected void a(final gb gb) {
        super.a(gb);
        if (!(gb instanceof xc)) {
            throw new RuntimeException(bingo.x.F[2] + gb);
        }
        final xc xc = (xc)gb;
    }
    
    public void d() {
    }
    
    public void a(final int n) {
        this.b(n);
        this.c(n);
    }
    
    public void a() {
    }
    
    public boolean a(final n n) {
        this.d();
        return false;
    }
    
    public boolean a(final neat.system.o o) {
        return false;
    }
    
    public static x r() {
        return (x)x.l.a();
    }
    
    public void f() {
        bingo.x.l.a(this);
    }
    
    public void g() {
        super.g();
        this.n = false;
        this.o = false;
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
    
    public x() {
        this.p = null;
        this.s = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.A = null;
    }
    
    static {
        final String[] f = new String[4];
        final int n = 0;
        final char[] charArray = "\u0016cI\\kpoFF}$,KG;4iC\b<\"cJ\b.8eT\b)8mCG-j".toCharArray();
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
                            c2 = 'P';
                            break;
                        }
                        case 1: {
                            c2 = '\f';
                            break;
                        }
                        case 2: {
                            c2 = '\'';
                            break;
                        }
                        case 3: {
                            c2 = '(';
                            break;
                        }
                        default: {
                            c2 = 'Z';
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
        f[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "\u0007~HF=p`NF1pxH\b)8mCG-pcA\b*?\u007fSM(j".toCharArray();
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
                            c4 = 'P';
                            break;
                        }
                        case 1: {
                            c4 = '\f';
                            break;
                        }
                        case 2: {
                            c4 = '\'';
                            break;
                        }
                        case 3: {
                            c4 = '(';
                            break;
                        }
                        default: {
                            c4 = 'Z';
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
        f[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "\u0004dN[z#dFL5',HNz9xBEz=yT\\z2i\u0007Iz\u0000cT\\?\"XHG6#_OI>?{\u0007A7 `BE?>xF\\3?b\u001d".toCharArray();
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
                            c6 = 'P';
                            break;
                        }
                        case 1: {
                            c6 = '\f';
                            break;
                        }
                        case 2: {
                            c6 = '\'';
                            break;
                        }
                        case 3: {
                            c6 = '(';
                            break;
                        }
                        default: {
                            c6 = 'Z';
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
        f[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "2eIO5~t".toCharArray();
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
                            c8 = 'P';
                            break;
                        }
                        case 1: {
                            c8 = '\f';
                            break;
                        }
                        case 2: {
                            c8 = '\'';
                            break;
                        }
                        case 3: {
                            c8 = '(';
                            break;
                        }
                        default: {
                            c8 = 'Z';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 <= n16) {
                f[n13] = new String(charArray4).intern();
                x.F = f;
                x.l = new f((x.E != null) ? x.E : (x.E = a(x.F[3])));
                return;
            }
            continue;
        }
    }
}
