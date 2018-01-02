// 
// Decompiled by Procyon v0.5.30
// 

package neat.system.graphics.renderer;

import neat.system.cb;

public abstract class b implements cb
{
    static int a;
    public int b;
    protected d c;
    protected a d;
    protected a e;
    protected a f;
    private g g;
    private int h;
    private boolean i;
    private int j;
    private boolean k;
    private c l;
    private boolean m;
    private int n;
    private a o;
    protected boolean p;
    protected boolean q;
    protected int r;
    protected float s;
    protected float t;
    private static String[] z;
    
    protected abstract void a();
    
    protected abstract void b();
    
    void a(final d c) {
        if (this.c != null) {
            throw new RuntimeException(neat.system.graphics.renderer.b.z[4]);
        }
        this.c = c;
    }
    
    void c() {
        if (this.c == null) {
            throw new RuntimeException(neat.system.graphics.renderer.b.z[3]);
        }
        this.c = null;
    }
    
    void a(final int n, final int n2, final int n3, final int n4) {
        if (n < 0 || n2 < 0 || n3 < 0 || n4 < 0) {
            throw new RuntimeException(neat.system.graphics.renderer.b.z[1] + n + " " + n2 + " " + n3 + " " + n4);
        }
        this.d.a(n, n2, n3, n4);
        if (this.d()) {
            this.b(6);
        }
    }
    
    public void b(final int n, final int n2, final int n3, final int n4) {
        if (n3 < 0 || n4 < 0) {
            throw new RuntimeException(neat.system.graphics.renderer.b.z[0] + n3 + "x" + n4);
        }
        this.e.a(n, n2, n3, n4);
        this.e.a(this.d);
        this.f.b(this.e);
        if (this.d()) {
            this.b(6);
        }
    }
    
    public void a(final boolean k) {
        if (this.k != k) {
            this.k = k;
            this.f.c(this.e);
            this.b(2);
        }
    }
    
    public boolean d() {
        return this.k;
    }
    
    public final void a(final int j) {
        this.j = j;
        this.f.c(this.e);
        this.b(1);
    }
    
    public final int e() {
        return this.j;
    }
    
    public void b(final int h) {
        this.h = h;
        this.i = true;
        this.c.a(this);
        this.h = 0;
    }
    
    public void i() {
        this.i = false;
    }
    
    public boolean j() {
        return this.i;
    }
    
    public int k() {
        return this.e.d();
    }
    
    public int l() {
        return this.e.e();
    }
    
    public int m() {
        return this.e.f();
    }
    
    public int n() {
        return this.e.g();
    }
    
    public a o() {
        return this.e;
    }
    
    public boolean a(final a a) {
        return a.d(this.e);
    }
    
    public void a(final g g) {
        if (g == null) {
            throw new RuntimeException(neat.system.graphics.renderer.b.z[2]);
        }
        if (this.g != null) {
            this.g.b(this.l);
        }
        (this.g = g).a(this.l);
        if (this.d()) {
            this.f.b(this.e);
            this.b(7);
        }
    }
    
    public void p() {
        if (this.g == null) {
            return;
        }
        this.a(false);
        this.g.b(this.l);
        this.g = null;
    }
    
    public g q() {
        return this.g;
    }
    
    public void c(final int n, final int n2, final int n3, final int n4) {
        this.o.a(n, n2, n3, n4);
        this.b(false);
    }
    
    public boolean r() {
        return this.o.b();
    }
    
    public a s() {
        if (!this.r()) {
            throw new RuntimeException(neat.system.graphics.renderer.b.z[8]);
        }
        return this.o;
    }
    
    public void b(final boolean m) {
        if (m && !this.m) {
            this.o.a();
        }
        this.m = m;
    }
    
    public boolean t() {
        return this.m;
    }
    
    public int u() {
        if (this.g == null) {
            throw new RuntimeException(neat.system.graphics.renderer.b.z[9]);
        }
        if (this.t()) {
            return this.n;
        }
        return this.g.k();
    }
    
    public void a(final float s, final float t) {
        this.p = true;
        this.q = false;
        this.s = s;
        this.t = t;
    }
    
    public boolean v() {
        return this.p;
    }
    
    public void w() {
        this.p = false;
    }
    
    public void b(final a a) {
        if (this.a(a)) {
            this.f.c(a);
            this.f.a(this.e);
        }
    }
    
    protected boolean x() {
        return this.f.b();
    }
    
    protected void y() {
        this.f.c(this.e);
    }
    
    void z() {
        this.f.a();
    }
    
    private void A() {
        if (!this.x()) {
            throw new RuntimeException(neat.system.graphics.renderer.b.z[7]);
        }
    }
    
    a B() {
        this.A();
        return this.f;
    }
    
    void a(final m m) {
        if (!this.e.d(m.u())) {
            return;
        }
        final g q = this.q();
        if (q == null) {
            return;
        }
        m.v();
        final g j = m.j();
        final a h = neat.system.graphics.renderer.a.h();
        h.b(this.e);
        h.a(m.u());
        final a h2 = j.h();
        final a u = m.u();
        final float n = h.b - u.d();
        final float n2 = h.c - u.e();
        final float n3 = h.f();
        final float n4 = h.g();
        final float n5 = h2.f() * (n / u.f());
        final float n6 = h2.g() * (n2 / u.g());
        final float n7 = h2.f() * ((n + n3) / u.f());
        final float n8 = h2.g() * ((n2 + n4) / u.g());
        final int n9 = (int)(n5 + h2.d() + 0.5f);
        final int n10 = (int)(n6 + h2.e() + 0.5f);
        final int n11 = (int)(n7 + h2.d() + 0.5f);
        final int n12 = (int)(n8 + h2.e() + 0.5f);
        final int b = h.b;
        final int c = h.c;
        q.a(j, b, c, b + h.f(), c + h.g(), n9, n10, n11, n12);
        m.w();
        h.f();
    }
    
    void a(final h h, final a a) {
        if (!this.x()) {
            return;
        }
        final g q = this.q();
        if (q == null) {
            return;
        }
        this.a();
        this.f.a(a);
        final a h2 = a.h();
        h2.b(this.f);
        q.o();
        if (this.t()) {
            q.a(this.u());
        }
        else if (this.r()) {
            q.a(this.s());
        }
        if (this.v()) {
            if (this.q) {
                q.a(this.r, this.s, this.t);
            }
            else {
                q.a(this.s, this.t);
            }
        }
        final a h3 = q.h();
        final a o = this.o();
        final float n = h2.b - o.d();
        final float n2 = h2.c - o.e();
        final float n3 = h2.f();
        final float n4 = h2.g();
        final float n5 = h3.f() * (n / o.f());
        final float n6 = h3.g() * (n2 / o.g());
        final float n7 = h3.f() * ((n + n3) / o.f());
        final float n8 = h3.g() * ((n2 + n4) / o.g());
        final int n9 = (int)(n5 + h3.d() + 0.5f);
        final int n10 = (int)(n6 + h3.e() + 0.5f);
        final int n11 = (int)(n7 + h3.d() + 0.5f);
        final int n12 = (int)(n8 + h3.e() + 0.5f);
        final int b = h2.b;
        final int c = h2.c;
        h.a(q, b, c, b + h2.f(), c + h2.g(), n9, n10, n11, n12);
        q.p();
        h2.f();
    }
    
    void b(final h h, final a a) {
        this.b();
    }
    
    public boolean c(final int n) {
        return this.h == n;
    }
    
    public void g() {
        this.j = 0;
        this.g = null;
        this.c = null;
        this.k = false;
        this.i = false;
        this.e = neat.system.graphics.renderer.a.h();
        this.d = neat.system.graphics.renderer.a.h();
        this.f = neat.system.graphics.renderer.a.h();
        this.o = neat.system.graphics.renderer.a.h();
        if (this == null) {
            throw null;
        }
        this.l = new c(this);
    }
    
    public void h() {
        if (this.g != null) {
            throw new RuntimeException(neat.system.graphics.renderer.b.z[6]);
        }
        this.e.f();
        this.d.f();
        this.f.f();
        this.o.f();
        this.e = null;
        this.d = null;
        this.f = null;
        this.o = null;
        if (this.c != null) {
            throw new RuntimeException(neat.system.graphics.renderer.b.z[5]);
        }
    }
    
    public abstract void f();
    
    static g a(final b b) {
        return b.g;
    }
    
    public b() {
        this.b = neat.system.graphics.renderer.b.a++;
    }
    
    static {
        final String[] z = new String[10];
        final int n = 0;
        final char[] charArray = "\u001aW)h5\u001a]\u007f~0\u0017M7&1\u0016P8a-S".toCharArray();
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
                            c2 = 's';
                            break;
                        }
                        case 1: {
                            c2 = '9';
                            break;
                        }
                        case 2: {
                            c2 = '_';
                            break;
                        }
                        case 3: {
                            c2 = '\t';
                            break;
                        }
                        default: {
                            c2 = 'Y';
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
        final char[] charArray2 = "\u001aW)h5\u001a]\u007f{<\u0010Mb".toCharArray();
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
                            c4 = 's';
                            break;
                        }
                        case 1: {
                            c4 = '9';
                            break;
                        }
                        case 2: {
                            c4 = '_';
                            break;
                        }
                        case 3: {
                            c4 = '\t';
                            break;
                        }
                        default: {
                            c4 = 'Y';
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
        final char[] charArray3 = "0X1\u007f8\u0000\u0017,l-:T>n<[\u0010\u007f`7\u0005X3`=SP2h>\u0016\u0019/h+\u0012T:}<\u0001".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0330: {
                if (n10 > 1) {
                    break Label_0330;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = 's';
                            break;
                        }
                        case 1: {
                            c6 = '9';
                            break;
                        }
                        case 2: {
                            c6 = '_';
                            break;
                        }
                        case 3: {
                            c6 = '\t';
                            break;
                        }
                        default: {
                            c6 = 'Y';
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
        final char[] charArray4 = "\u001dV\u007ff;\u0000\\-\u007f<\u0001\u0019+fy\u0001\\2f/\u0016".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0446: {
                if (n14 > 1) {
                    break Label_0446;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = 's';
                            break;
                        }
                        case 1: {
                            c8 = '9';
                            break;
                        }
                        case 2: {
                            c8 = '_';
                            break;
                        }
                        case 3: {
                            c8 = '\t';
                            break;
                        }
                        default: {
                            c8 = 'Y';
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
        z[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "\u0003K:\u007f0\u001cL,)6\u0011J:{/\u0016K\u007fa8\u0000Wx}y\u0011\\:gy\u0001\\2f/\u0016]".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0562: {
                if (n18 > 1) {
                    break Label_0562;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = 's';
                            break;
                        }
                        case 1: {
                            c10 = '9';
                            break;
                        }
                        case 2: {
                            c10 = '_';
                            break;
                        }
                        case 3: {
                            c10 = '\t';
                            break;
                        }
                        default: {
                            c10 = 'Y';
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
        z[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "\u0010X1\u007f8\u0000v=z<\u0001O:{y\u001eL,}y\u0011\\\u007fg,\u001fU".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0678: {
                if (n22 > 1) {
                    break Label_0678;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = 's';
                            break;
                        }
                        case 1: {
                            c12 = '9';
                            break;
                        }
                        case 2: {
                            c12 = '_';
                            break;
                        }
                        case 3: {
                            c12 = '\t';
                            break;
                        }
                        default: {
                            c12 = 'Y';
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
        z[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "\u001aT>n<SJ7f,\u001f]\u007fk<SK:d6\u0005\\;)?\u001aK,}".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0798: {
                if (n26 > 1) {
                    break Label_0798;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = 's';
                            break;
                        }
                        case 1: {
                            c14 = '9';
                            break;
                        }
                        case 2: {
                            c14 = '_';
                            break;
                        }
                        case 3: {
                            c14 = '\t';
                            break;
                        }
                        default: {
                            c14 = 'Y';
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
        z[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "0X1\u007f8\u0000\u0017>z*\u0016K+@*&I;h-\u0016]w y\u0015X6e<\u0017".toCharArray();
        int length8;
        int n31;
        final int n30 = n31 = (length8 = charArray8.length);
        int n32 = 0;
        while (true) {
            Label_0918: {
                if (n30 > 1) {
                    break Label_0918;
                }
                length8 = (n31 = n32);
                do {
                    final char c15 = charArray8[n31];
                    char c16 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c16 = 's';
                            break;
                        }
                        case 1: {
                            c16 = '9';
                            break;
                        }
                        case 2: {
                            c16 = '_';
                            break;
                        }
                        case 3: {
                            c16 = '\t';
                            break;
                        }
                        default: {
                            c16 = 'Y';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n32;
                } while (n30 == 0);
            }
            if (n30 > n32) {
                continue;
            }
            break;
        }
        z[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "\u001dV\u007f\u007f8\u001fP;)0\u001eX8ly\u0010U6y)\u001aW8)8\u0005X6e8\u0011U:".toCharArray();
        int length9;
        int n35;
        final int n34 = n35 = (length9 = charArray9.length);
        int n36 = 0;
        while (true) {
            Label_1038: {
                if (n34 > 1) {
                    break Label_1038;
                }
                length9 = (n35 = n36);
                do {
                    final char c17 = charArray9[n35];
                    char c18 = '\0';
                    switch (n36 % 5) {
                        case 0: {
                            c18 = 's';
                            break;
                        }
                        case 1: {
                            c18 = '9';
                            break;
                        }
                        case 2: {
                            c18 = '_';
                            break;
                        }
                        case 3: {
                            c18 = '\t';
                            break;
                        }
                        default: {
                            c18 = 'Y';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c17 ^ c18);
                    ++n36;
                } while (n34 == 0);
            }
            if (n34 > n36) {
                continue;
            }
            break;
        }
        z[n33] = new String(charArray9).intern();
        final int n37 = 9;
        final char[] charArray10 = "\u001dV\u007f`4\u0012^:)5\u001aW4l=".toCharArray();
        int length10;
        int n39;
        final int n38 = n39 = (length10 = charArray10.length);
        int n40 = 0;
        while (true) {
            Label_1158: {
                if (n38 > 1) {
                    break Label_1158;
                }
                length10 = (n39 = n40);
                do {
                    final char c19 = charArray10[n39];
                    char c20 = '\0';
                    switch (n40 % 5) {
                        case 0: {
                            c20 = 's';
                            break;
                        }
                        case 1: {
                            c20 = '9';
                            break;
                        }
                        case 2: {
                            c20 = '_';
                            break;
                        }
                        case 3: {
                            c20 = '\t';
                            break;
                        }
                        default: {
                            c20 = 'Y';
                            break;
                        }
                    }
                    charArray10[length10] = (char)(c19 ^ c20);
                    ++n40;
                } while (n38 == 0);
            }
            if (n38 <= n40) {
                z[n37] = new String(charArray10).intern();
                b.z = z;
                b.a = 1000;
                return;
            }
            continue;
        }
    }
}
