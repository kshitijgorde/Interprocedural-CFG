// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.system.graphics.renderer.m;
import neat.system.f;

public class k extends h
{
    private static f h;
    private int i;
    private int j;
    private int k;
    private float l;
    private float m;
    private float n;
    private float o;
    private float p;
    private float q;
    private int r;
    private int s;
    private boolean t;
    private m u;
    private int v;
    private int w;
    private m x;
    private int y;
    private int z;
    private boolean A;
    private int B;
    private boolean C;
    private boolean D;
    private int E;
    private int F;
    private int G;
    private int H;
    private static /* synthetic */ Class I;
    private static String[] J;
    
    public void a(final nb nb) {
        this.a();
        this.i = nb.h;
        if (this.i < 0) {
            throw new RuntimeException(bingo.k.J[2] + this.i);
        }
        if (nb.l != null) {
            final mb mb = (mb)nb.l.a();
            if (mb != null) {
                this.j = mb.e;
                if (this.j <= 0) {
                    this.j = 1;
                }
                this.k = this.j;
                this.l = mb.f;
                this.m = mb.g;
                this.n = mb.h;
                this.o = mb.i;
                this.p = mb.j;
                this.q = mb.k;
                this.r = mb.l;
                this.s = mb.m;
                mb.f();
            }
        }
        this.b(nb);
    }
    
    public void a(final float l, final float m, final float n, final float o, final float p6, final float q) {
        if (l >= 0.0f) {
            this.l = l;
        }
        if (m >= 0.0f) {
            this.m = m;
        }
        if (n >= 0.0f) {
            this.n = n;
        }
        if (o >= 0.0f) {
            this.o = o;
        }
        if (p6 >= 0.0f) {
            this.p = p6;
        }
        if (q >= 0.0f) {
            this.q = q;
        }
    }
    
    private void b(final nb nb) {
        this.a();
        if (nb.e != null) {
            this.u = this.a.a(nb.e);
            if (this.u == null) {
                throw new RuntimeException(bingo.k.J[0] + nb.e);
            }
            this.u.a(false);
            this.v = nb.f;
            this.w = nb.g;
        }
        if (nb.i != null) {
            this.x = this.a.a(nb.i);
            if (this.x == null) {
                throw new RuntimeException(bingo.k.J[1] + nb.i);
            }
            this.x.a(false);
            this.y = nb.j;
            this.z = nb.k;
        }
        this.b();
    }
    
    private void a() {
        if (this.u != null) {
            this.a.a(this.u);
            this.u = null;
        }
        if (this.x != null) {
            this.a.a(this.x);
            this.x = null;
        }
    }
    
    public void a(final boolean t) {
        if (this.t == t) {
            return;
        }
        this.t = t;
        this.b();
    }
    
    private void b() {
        if (this.u != null) {
            final int e = this.e();
            final int f = this.f();
            if (e >= 0 && f >= 0) {
                this.u.a(e + this.v, f + this.w);
            }
            this.u.a(this.t);
        }
        if (this.x != null) {
            final int e2 = this.e();
            final int f2 = this.f();
            if (e2 >= 0 && f2 >= 0) {
                this.x.a(e2 + this.y, f2 + this.z);
            }
            this.x.a(this.t);
        }
    }
    
    public void c() {
        this.A = false;
    }
    
    public void c(final int b) {
        final g b2 = this.b();
        if (b2 == null) {
            return;
        }
        final int d = this.a.D();
        final int c = this.c();
        this.F = c;
        if (this.i > 0) {
            Label_0085: {
                break Label_0085;
                int i = 0;
                do {
                    if (!b2.b(i, d, c, this.i)) {
                        break;
                    }
                    this.F = i;
                    i = this.F + 1;
                } while (i < b2.e());
            }
        }
        this.G = b2.a(this.F, d, this.F, d, 1);
        if (this.G == -1000000000) {
            return;
        }
        this.A = true;
        this.B = b;
        this.C = false;
        this.D = false;
        this.E = this.j;
        this.H = -1;
    }
    
    public int d() {
        return this.B;
    }
    
    public void a(final int n, int n2, final boolean b) {
        if (!this.A || this.C) {
            return;
        }
        int x = this.a.X();
        final l d = this.a.d(this.a());
        if (d == null) {
            return;
        }
        final g b2 = this.b();
        if (b2 != null) {
            final int n3 = b2.d() - b2.c();
            final int g = b2.g(2 * this.a.D());
            final int db = this.a.db();
            float n4;
            if (db >= this.s) {
                n4 = this.l;
            }
            else if (db <= this.r) {
                n4 = this.q;
            }
            else {
                n4 = this.q + (this.l - this.q) * ((db - this.r) / (this.s - this.r));
            }
            final int n5 = (int)(x * n4);
            final int n6 = (int)(x * this.m);
            final int n7 = n3 - (int)(g * this.n);
            final int n8 = n3 - (int)(g * this.o);
            final int n9 = n3 - (int)(g * this.p * this.n);
            final int n10 = d.g() - b2.c();
            int n11 = d.f() - b2.c();
            if (n10 < n11 && n11 > n9) {
                n11 = n9;
            }
            if (n11 < 0) {
                n11 = 0;
            }
            if (n11 <= n7) {
                float n12;
                if (n11 <= n8) {
                    n12 = 0.0f;
                }
                else {
                    n12 = (n11 - n8) / (n7 - n8);
                }
                x = n5 + (int)((x - n5) * n12);
            }
            else {
                x += (n6 - x) * ((n11 - n7) / (n3 - n7));
            }
            if (x < this.j + 100) {
                x = this.j + 100;
            }
            if (this.H != -1 && x != this.H) {
                this.B = this.B * x / this.H;
            }
            this.H = x;
        }
        this.B -= n;
        int n13 = 0;
        if (n > 0) {
            while (this.B <= 0) {
                this.B += x;
                if (b) {
                    ++n13;
                }
            }
        }
        while (true) {
            if (this.D) {
                if (n13 <= 0) {
                    break;
                }
                --n13;
                final int t = this.a.t(this.a());
                if (t < 0) {
                    this.a.Hb();
                    break;
                }
                this.a.h(this.a.b(this.a(), this.F, t >> 16 & 0xFFFF, t & 0xFFFF));
                this.D = false;
                this.E = this.j;
            }
            else {
                int e = n2;
                if (e > this.E) {
                    e = this.E;
                }
                this.E -= e;
                if (!d.c(this.F + this.G * (this.j - this.E) / this.j)) {
                    this.E += e;
                    return;
                }
                n2 -= e;
                if (this.E != 0) {
                    return;
                }
                this.D = true;
            }
        }
        this.E = 0;
    }
    
    public boolean e() {
        return this.A && this.E > 0;
    }
    
    public int h() {
        int f = this.F;
        if (this.G != -1000000000) {
            f += this.G;
        }
        return f;
    }
    
    protected void d() {
        super.d();
        this.b();
    }
    
    public static k a(final s a) {
        final k k = (k)bingo.k.h.a();
        k.a = a;
        return k;
    }
    
    public void f() {
        bingo.k.h.a(this);
    }
    
    public void g() {
        super.g();
        this.i = 1;
        this.j = 1000;
        this.k = this.j;
        this.l = 1.0f;
        this.m = 1.0f;
        this.n = 0.0f;
        this.o = 0.0f;
        this.p = 0.0f;
        this.q = 1.0f;
        this.r = 0;
        this.s = 0;
        this.t = false;
    }
    
    public void h() {
        this.a();
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
    
    public k() {
        this.u = null;
        this.x = null;
    }
    
    static {
        final String[] j = new String[4];
        final int n = 0;
        final char[] charArray = ")`(\u0002~Jm)DnJc'VoJr6Wc\u001edfCe\u0018!\"@k\u0006d4\u001f".toCharArray();
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
                            c2 = 'j';
                            break;
                        }
                        case 1: {
                            c2 = '\u0001';
                            break;
                        }
                        case 2: {
                            c2 = 'F';
                            break;
                        }
                        case 3: {
                            c2 = '%';
                            break;
                        }
                        default: {
                            c2 = '\n';
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
        j[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = ")`(\u0002~Jm)DnJg'Ao\u0018!5Ux\u0003u#\u0005l\u0005sfAo\u000bm#W0".toCharArray();
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
                            c4 = 'j';
                            break;
                        }
                        case 1: {
                            c4 = '\u0001';
                            break;
                        }
                        case 2: {
                            c4 = 'F';
                            break;
                        }
                        case 3: {
                            c4 = '%';
                            break;
                        }
                        default: {
                            c4 = '\n';
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
        j[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "=s)KmJg'Ao\u000e!4Dn\u0003t5\u001f".toCharArray();
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
                            c6 = 'j';
                            break;
                        }
                        case 1: {
                            c6 = '\u0001';
                            break;
                        }
                        case 2: {
                            c6 = 'F';
                            break;
                        }
                        case 3: {
                            c6 = '%';
                            break;
                        }
                        default: {
                            c6 = '\n';
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
        j[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "\bh(BeDj".toCharArray();
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
                            c8 = 'j';
                            break;
                        }
                        case 1: {
                            c8 = '\u0001';
                            break;
                        }
                        case 2: {
                            c8 = 'F';
                            break;
                        }
                        case 3: {
                            c8 = '%';
                            break;
                        }
                        default: {
                            c8 = '\n';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 <= n16) {
                j[n13] = new String(charArray4).intern();
                k.J = j;
                k.h = new f((k.I != null) ? k.I : (k.I = a(k.J[3])));
                return;
            }
            continue;
        }
    }
}
