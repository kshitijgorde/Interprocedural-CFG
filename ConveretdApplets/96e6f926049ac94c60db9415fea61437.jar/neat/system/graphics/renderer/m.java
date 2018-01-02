// 
// Decompiled by Procyon v0.5.30
// 

package neat.system.graphics.renderer;

import neat.i;
import neat.system.cb;

public abstract class m implements cb
{
    static int a;
    public int b;
    private a c;
    private a d;
    private boolean e;
    private int f;
    private i g;
    private boolean h;
    int i;
    private o j;
    private g k;
    private n l;
    private boolean m;
    private int n;
    private a o;
    private boolean p;
    private boolean q;
    private int r;
    private float s;
    private float t;
    public static int u;
    private static String[] z;
    
    protected abstract void a();
    
    protected abstract void b();
    
    public final void a(final int n, final int n2) {
        if (this.c() != n || this.d() != n2) {
            this.c.a(n, n2);
            if (this.k()) {
                this.c(4);
            }
        }
    }
    
    public final int c() {
        this.c.c();
        return this.c.d();
    }
    
    public final int d() {
        this.c.c();
        return this.c.e();
    }
    
    public final void b(final int n, final int n2) {
        if (this.e() != n || this.f() != n2) {
            this.c.b(n, n2);
            if (this.k()) {
                this.c(6);
            }
        }
    }
    
    public final int e() {
        return this.c.f();
    }
    
    public final int f() {
        return this.c.g();
    }
    
    public final void a(final int f) {
        if (this.f != f) {
            this.f = f;
            this.c(1);
        }
    }
    
    public final int g() {
        return this.f;
    }
    
    public void a(final g k) {
        if (k == null) {
            throw new RuntimeException(neat.system.graphics.renderer.m.z[5]);
        }
        if (this.k != null) {
            this.k.b(this.l);
        }
        (this.k = k).a(this.l);
        if (k.j() > 0) {
            k.i();
            this.c.b(k.h().f(), k.h().g());
        }
        else {
            this.c.b(k.f(), k.g());
        }
        if (this.k()) {
            this.c(7);
        }
    }
    
    public void i() {
        if (this.k == null) {
            return;
        }
        this.a(false);
        this.k.b(this.l);
        this.k = null;
    }
    
    public g j() {
        return this.k;
    }
    
    public void a(final boolean e) {
        if (this.e != e && this.k != null) {
            this.e = e;
            this.c(2);
        }
    }
    
    public boolean k() {
        return this.e;
    }
    
    public boolean a(final a a) {
        return this.c.d(a);
    }
    
    public void a(final b b) {
        this.g.a(b);
        if (this.k()) {
            this.c(9);
        }
    }
    
    public void b(final b b) {
        this.g.d(b);
        if (this.k()) {
            this.c(10);
        }
    }
    
    public boolean c(final b b) {
        return !this.d(b);
    }
    
    public boolean d(final b b) {
        return this.g.b(b);
    }
    
    public boolean e(final b b) {
        return this.k() && this.c(b);
    }
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        if (this.m || n != this.o.b || n2 != this.o.c || n3 != this.o.d || n4 != this.o.e) {
            this.o();
            this.o.a(n, n2, n3, n4);
            if (this.k()) {
                this.c(7);
            }
        }
    }
    
    public boolean l() {
        return this.o.b();
    }
    
    public a m() {
        if (!this.l()) {
            throw new RuntimeException(neat.system.graphics.renderer.m.z[3]);
        }
        return this.o;
    }
    
    public boolean n() {
        return this.m;
    }
    
    public void o() {
        this.m = false;
        this.o.a();
    }
    
    public int p() {
        if (this.k == null) {
            throw new RuntimeException(neat.system.graphics.renderer.m.z[0]);
        }
        return this.k.j();
    }
    
    public void b(int n) {
        if (this.k == null) {
            throw new RuntimeException(neat.system.graphics.renderer.m.z[0]);
        }
        if (this.n == n && this.n()) {
            return;
        }
        if (n >= this.p()) {
            n = this.p() - 1;
        }
        if (n < 0) {
            n = 0;
        }
        this.m = true;
        this.n = n;
        this.o.a();
        this.l.a(this.j());
    }
    
    public int q() {
        if (this.k == null) {
            throw new RuntimeException(neat.system.graphics.renderer.m.z[0]);
        }
        if (this.n()) {
            return this.n;
        }
        return this.k.k();
    }
    
    public void a(final float s, final float t) {
        if (s == 1.0f && t == 0.0f) {
            this.s();
        }
        else {
            this.p = true;
            this.s = s;
            this.t = t;
        }
    }
    
    public void a(final int r, final float n, final float n2) {
        this.q = true;
        this.r = r;
        this.a(n, n2);
    }
    
    public boolean r() {
        return this.p;
    }
    
    public void s() {
        this.p = false;
    }
    
    void a(final o j) {
        this.j = j;
    }
    
    void t() {
        this.j = null;
    }
    
    a u() {
        this.c.c();
        return this.c;
    }
    
    void v() {
        final g j = this.j();
        j.o();
        if (this.n()) {
            j.a(this.q());
        }
        else if (this.l()) {
            j.a(this.m());
        }
        if (this.r()) {
            j.a(this.s, this.t);
        }
        else {
            j.l();
        }
        this.a();
    }
    
    void w() {
        this.b();
        this.j().p();
    }
    
    boolean x() {
        return this.d.b();
    }
    
    a y() {
        return this.d;
    }
    
    void c(final int i) {
        this.i = i;
        this.h = true;
        if (this.d(0)) {
            throw new RuntimeException(neat.system.graphics.renderer.m.z[2]);
        }
        if (this.j == null) {
            throw new RuntimeException(neat.system.graphics.renderer.m.z[1]);
        }
        this.j.a(this);
        this.i = 0;
    }
    
    boolean z() {
        return this.h;
    }
    
    void A() {
        this.h = false;
        this.d.b(this.u());
    }
    
    boolean d(final int n) {
        return this.i == n;
    }
    
    public boolean B() {
        return false;
    }
    
    public void g() {
        this.f = 0;
        this.k = null;
        this.j = null;
        this.e = false;
        this.h = false;
        this.p = false;
        this.q = false;
        this.c = neat.system.graphics.renderer.a.h();
        this.d = neat.system.graphics.renderer.a.h();
        this.g = neat.i.k();
        this.o = neat.system.graphics.renderer.a.h();
        if (this == null) {
            throw null;
        }
        this.l = new n(this);
    }
    
    public void h() {
        if (this.k != null) {
            throw new RuntimeException(neat.system.graphics.renderer.m.z[4]);
        }
        this.c.f();
        this.d.f();
        this.o.f();
        this.g.f();
        this.c = null;
        this.d = null;
        this.o = null;
        this.g = null;
        this.l = null;
    }
    
    public abstract void f();
    
    static g a(final m m) {
        return m.k;
    }
    
    public m() {
        this.b = neat.system.graphics.renderer.m.a++;
    }
    
    static {
        final String[] z = new String[6];
        final int n = 0;
        final char[] charArray = "!t[\rV.|\u001eDW&u\u0010\u0001_".toCharArray();
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
                            c2 = 'O';
                            break;
                        }
                        case 1: {
                            c2 = '\u001b';
                            break;
                        }
                        case 2: {
                            c2 = '{';
                            break;
                        }
                        case 3: {
                            c2 = 'd';
                            break;
                        }
                        default: {
                            c2 = ';';
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
        final char[] charArray2 = " y\b\u0001I9~\tD_ ~\b\n\u001c;;\u001e\u001cR<o".toCharArray();
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
                            c4 = 'O';
                            break;
                        }
                        case 1: {
                            c4 = '\u001b';
                            break;
                        }
                        case 2: {
                            c4 = '{';
                            break;
                        }
                        case 3: {
                            c4 = 'd';
                            break;
                        }
                        default: {
                            c4 = ';';
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
        final char[] charArray3 = "!t[\u0011K+z\u000f\u0001\u001b=~\u001a\u0017T!;\f\u0005Ho|\u0012\u0012^!".toCharArray();
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
                            c6 = 'O';
                            break;
                        }
                        case 1: {
                            c6 = '\u001b';
                            break;
                        }
                        case 2: {
                            c6 = '{';
                            break;
                        }
                        case 3: {
                            c6 = 'd';
                            break;
                        }
                        default: {
                            c6 = ';';
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
        final char[] charArray4 = "!t[\u0012Z#r\u001fDR\"z\u001c\u0001\u001b,w\u0012\u0014K&u\u001cDZ9z\u0012\bZ-w\u001e".toCharArray();
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
                            c8 = 'O';
                            break;
                        }
                        case 1: {
                            c8 = '\u001b';
                            break;
                        }
                        case 2: {
                            c8 = '{';
                            break;
                        }
                        case 3: {
                            c8 = 'd';
                            break;
                        }
                        default: {
                            c8 = ';';
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
        final char[] charArray5 = "&v\u001a\u0003^oh\u0013\u000bN#\u007f[\u0006^oi\u001e\tT9~\u001fD]&i\b\u0010".toCharArray();
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
                            c10 = 'O';
                            break;
                        }
                        case 1: {
                            c10 = '\u001b';
                            break;
                        }
                        case 2: {
                            c10 = '{';
                            break;
                        }
                        case 3: {
                            c10 = 'd';
                            break;
                        }
                        default: {
                            c10 = ';';
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
        final char[] charArray6 = "&u\r\u0005W&\u007f[\rV.|\u001eDK.i\u001a\t^;~\t".toCharArray();
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
                            c12 = 'O';
                            break;
                        }
                        case 1: {
                            c12 = '\u001b';
                            break;
                        }
                        case 2: {
                            c12 = '{';
                            break;
                        }
                        case 3: {
                            c12 = 'd';
                            break;
                        }
                        default: {
                            c12 = ';';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 <= n24) {
                z[n21] = new String(charArray6).intern();
                m.z = z;
                m.a = 10;
                return;
            }
            continue;
        }
    }
}
