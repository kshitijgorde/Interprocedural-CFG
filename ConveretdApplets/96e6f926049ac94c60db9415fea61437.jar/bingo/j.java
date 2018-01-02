// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.system.graphics.renderer.m;
import neat.system.f;

public class j extends h
{
    private static f h;
    private boolean i;
    private m j;
    private int k;
    private int l;
    private m m;
    private int n;
    private int o;
    private boolean p;
    private int q;
    private int r;
    private int s;
    private boolean t;
    private m u;
    private boolean v;
    private int w;
    private int x;
    private float y;
    private m z;
    private int A;
    private int B;
    private float C;
    private float D;
    private float E;
    private boolean F;
    private int G;
    private int H;
    private static /* synthetic */ Class I;
    private static String[] J;
    
    public void a(final kb kb) {
        this.a();
        this.b(kb);
    }
    
    private void b(final kb kb) {
        this.a();
        if (kb.e != null) {
            this.j = this.a.a(kb.e);
            if (this.j == null) {
                throw new RuntimeException(bingo.j.J[2] + kb.e);
            }
            this.j.a(false);
            this.k = kb.f;
            this.l = kb.g;
        }
        if (kb.h != null) {
            this.m = this.a.a(kb.h);
            if (this.m == null) {
                throw new RuntimeException(bingo.j.J[3] + kb.h);
            }
            this.m.a(false);
            this.n = kb.i;
            this.o = kb.j;
        }
        this.p = false;
        this.r = 0;
        this.s = 0;
        if (kb.k != null) {
            this.u = this.a.a(kb.k);
            if (this.u == null) {
                throw new RuntimeException(bingo.j.J[1] + kb.k);
            }
            this.u.a(false);
            this.w = kb.l;
            this.x = kb.m;
            this.y = 0.0f;
            this.v = (this.u.p() > 1);
        }
        if (kb.n != null) {
            this.z = this.a.a(kb.n);
            if (this.z == null) {
                throw new RuntimeException(bingo.j.J[0] + kb.n);
            }
            this.z.a(false);
            this.A = kb.o;
            this.B = kb.p;
            this.C = 0.0f;
            this.D = 0.0f;
            this.E = 0.0f;
        }
        this.t = true;
        this.F = false;
        this.G = 0;
        this.H = 0;
        this.b();
    }
    
    private void a() {
        if (this.j != null) {
            this.a.a(this.j);
            this.j = null;
        }
        if (this.m != null) {
            this.a.a(this.m);
            this.m = null;
        }
        if (this.u != null) {
            this.a.a(this.u);
            this.u = null;
        }
        if (this.z != null) {
            this.a.a(this.z);
            this.z = null;
        }
    }
    
    public void a(final boolean t) {
        if (this.t != t) {
            this.t = t;
            this.b();
        }
    }
    
    public void b(final boolean i) {
        if (this.i == i) {
            return;
        }
        this.i = i;
        this.b();
    }
    
    private void b() {
        if (this.j != null) {
            final int e = this.e();
            final int f = this.f();
            if (e >= 0 && f >= 0) {
                this.j.a(e + this.k, f + this.l);
            }
            this.j.a(this.i);
        }
        if (this.m != null) {
            final int e2 = this.e();
            final int f2 = this.f();
            if (e2 >= 0 && f2 >= 0) {
                this.m.a(e2 + this.n, f2 + this.o);
            }
            this.m.a(this.i);
        }
        if (this.u != null) {
            if (this.y == 0.0f || !this.i || !this.t) {
                this.u.a(false);
            }
            else {
                final int e3 = this.e();
                final int f3 = this.f();
                if (e3 >= 0 && f3 >= 0) {
                    this.u.a(e3 + this.w, f3 + this.x);
                }
                if (this.v) {
                    int p = this.u.p();
                    if (this.E <= 0.5f) {
                        --p;
                    }
                    int n = (int)(this.y * p);
                    if (n >= p) {
                        n = p - 1;
                    }
                    else if (n < 0) {
                        n = 0;
                    }
                    this.u.b(n);
                }
                else if (this.y == 1.0f) {
                    this.u.a(1.0f, 0.0f);
                }
                else {
                    this.u.a(this.y, 1.0f - this.y);
                }
                this.u.a(true);
            }
        }
        if (this.z != null) {
            if (this.E == 0.0f || !this.i || !this.t) {
                this.z.a(false);
            }
            else {
                final int e4 = this.e();
                final int f4 = this.f();
                if (e4 >= 0 && f4 >= 0) {
                    this.z.a(e4 + this.A, f4 + this.B);
                }
                this.z.a(this.E * 1.0f, 1.0f);
                this.z.a(true);
            }
        }
    }
    
    public void c(final int n) {
        this.d(n);
        if (this.m == null) {
            return;
        }
        final int p = this.m.p();
        if (p <= 1) {
            return;
        }
        if (!this.p) {
            if (this.q <= 0) {
                return;
            }
            this.q -= n;
        }
        else {
            this.q = 1000;
        }
        final int n2 = 100;
        final int n3 = this.s / n2;
        this.s += n;
        final int n4 = this.s / n2 - n3;
        this.s %= n2;
        if (n4 <= 0) {
            return;
        }
        final int q = this.m.q();
        int n5;
        if (this.r == 0) {
            n5 = q + n4;
            if (n5 >= p) {
                n5 = p - 2;
                this.r = 1;
            }
        }
        else {
            n5 = q - n4;
            if (n5 < 0) {
                n5 = 1;
                this.r = 0;
            }
        }
        this.m.b(n5);
    }
    
    public void c(final boolean p) {
        if (this.p != p) {
            this.p = p;
        }
    }
    
    public void c() {
        this.F = true;
    }
    
    private void d(final int n) {
        if (this.u == null) {
            return;
        }
        final int n2 = this.v ? 200 : 150;
        final int n3 = 100;
        final int n4 = this.v ? 300 : 800;
        final int n5 = 200;
        final int n6 = 2000;
        this.H += n;
        float y = 1.0f;
        while (true) {
            switch (this.G) {
                case 0: {
                    if (this.F) {
                        ++this.G;
                        this.H = 0;
                        this.F = false;
                        this.C = this.D;
                        if (this.C < 0.0f) {
                            this.C = 0.0f;
                        }
                        this.a.a(this.D);
                        continue;
                    }
                    y = 0.0f;
                    break;
                }
                case 1: {
                    if (this.H >= n2) {
                        ++this.G;
                        this.H = 0;
                        continue;
                    }
                    y = this.H / n2;
                    break;
                }
                case 2: {
                    if (this.H >= n3) {
                        ++this.G;
                        this.H = 0;
                        continue;
                    }
                    this.D = 1.0f;
                    y = 1.0f;
                    break;
                }
                case 3: {
                    if (this.H >= n4) {
                        ++this.G;
                        this.H = 0;
                        continue;
                    }
                    y = 1.0f - this.H / n4;
                    break;
                }
                case 4: {
                    if (this.H >= n5) {
                        this.G = 0;
                        this.H = 0;
                        continue;
                    }
                    y = 0.0f;
                    break;
                }
            }
            if (this.G == 0 || this.G >= 4) {
                this.D -= n / n6;
            }
            if (this.G < 3 || !this.F) {
                break;
            }
            this.G = 1;
            this.H = (int)(y * n2);
            this.F = false;
            this.C = this.D;
            if (this.C < 0.0f) {
                this.C = 0.0f;
            }
            this.a.a(this.D);
        }
        boolean b = false;
        if (this.y != y) {
            this.y = y;
            b = true;
        }
        float e = y * (this.C + 0.2f);
        if (e < 0.0f) {
            e = 0.0f;
        }
        else if (e > 1.0f) {
            e = 1.0f;
        }
        if (this.E != e) {
            this.E = e;
            b = true;
        }
        if (b) {
            this.b();
        }
    }
    
    protected void d() {
        super.d();
        this.b();
    }
    
    public static j a(final s a) {
        final j j = (j)bingo.j.h.a();
        j.a = a;
        return j;
    }
    
    public void f() {
        bingo.j.h.a(this);
    }
    
    public void g() {
        super.g();
        this.i = false;
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
    
    public j() {
        this.j = null;
        this.m = null;
        this.u = null;
        this.z = null;
    }
    
    static {
        final String[] j = new String[5];
        final int n = 0;
        final char[] charArray = "G'l~6$*m8&$#{<\u0005h)uy1t4k-'$ m+b`#c5'v|".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0096: {
                if (n2 > 1) {
                    break Label_0096;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '\u0004';
                            break;
                        }
                        case 1: {
                            c2 = 'F';
                            break;
                        }
                        case 2: {
                            c2 = '\u0002';
                            break;
                        }
                        case 3: {
                            c2 = 'Y';
                            break;
                        }
                        default: {
                            c2 = 'B';
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
        final char[] charArray2 = "G'l~6$*m8&$#{<bw6p06afd60$\"g8.a48".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0212: {
                if (n6 > 1) {
                    break Label_0212;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '\u0004';
                            break;
                        }
                        case 1: {
                            c4 = 'F';
                            break;
                        }
                        case 2: {
                            c4 = '\u0002';
                            break;
                        }
                        case 3: {
                            c4 = 'Y';
                            break;
                        }
                        default: {
                            c4 = 'B';
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
        final char[] charArray3 = "G'l~6$*m8&$$c*'$5r++p#\"?-vff<#h#pc".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0328: {
                if (n10 > 1) {
                    break Label_0328;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '\u0004';
                            break;
                        }
                        case 1: {
                            c6 = 'F';
                            break;
                        }
                        case 2: {
                            c6 = '\u0002';
                            break;
                        }
                        case 3: {
                            c6 = 'Y';
                            break;
                        }
                        default: {
                            c6 = 'B';
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
        final char[] charArray4 = "G'l~6$*m8&$'l0/$5r++p#\"?-vff<#h#pc".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0444: {
                if (n14 > 1) {
                    break Label_0444;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = '\u0004';
                            break;
                        }
                        case 1: {
                            c8 = 'F';
                            break;
                        }
                        case 2: {
                            c8 = '\u0002';
                            break;
                        }
                        case 3: {
                            c8 = 'Y';
                            break;
                        }
                        default: {
                            c8 = 'B';
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
        j[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "f/l>-*,".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0560: {
                if (n18 > 1) {
                    break Label_0560;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = '\u0004';
                            break;
                        }
                        case 1: {
                            c10 = 'F';
                            break;
                        }
                        case 2: {
                            c10 = '\u0002';
                            break;
                        }
                        case 3: {
                            c10 = 'Y';
                            break;
                        }
                        default: {
                            c10 = 'B';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 <= n20) {
                j[n17] = new String(charArray5).intern();
                bingo.j.J = j;
                bingo.j.h = new f((bingo.j.I != null) ? bingo.j.I : (bingo.j.I = a(bingo.j.J[4])));
                return;
            }
            continue;
        }
    }
}
