// 
// Decompiled by Procyon v0.5.30
// 

package neat.system.graphics.renderer;

import neat.kb;
import neat.i;
import neat.system.cb;

public abstract class g implements cb
{
    static int a;
    private e b;
    public int c;
    private i d;
    private int e;
    private int f;
    private a g;
    private a h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private int m;
    private int n;
    private float o;
    private float p;
    private float q;
    private float r;
    private boolean s;
    private int t;
    private boolean u;
    private boolean v;
    private static String[] z;
    
    protected void a(final e b) {
        if (this.b != null) {
            throw new RuntimeException(neat.system.graphics.renderer.g.z[0]);
        }
        (this.b = b).a(this);
        if (b.f()) {
            this.b(b.g());
        }
        if (this.j() > 0) {
            this.a(0);
        }
        else {
            this.a(0, 0, b.b(), b.c());
        }
    }
    
    protected e a() {
        if (this.b == null) {
            throw new RuntimeException(neat.system.graphics.renderer.g.z[4]);
        }
        this.b.b(this);
        final e b = this.b;
        this.b = null;
        return b;
    }
    
    public kb b() {
        if (this.b == null) {
            return null;
        }
        return this.b.e();
    }
    
    public boolean c() {
        return !this.d.e();
    }
    
    void a(final neat.system.graphics.renderer.i i) {
        if (this.d.b(i)) {
            throw new RuntimeException(neat.system.graphics.renderer.g.z[1]);
        }
        this.d.a(i);
    }
    
    void b(final neat.system.graphics.renderer.i i) {
        if (!this.d.b(i)) {
            throw new RuntimeException(neat.system.graphics.renderer.g.z[2]);
        }
        this.d.d(i);
    }
    
    void d() {
        if (this.s) {
            return;
        }
        for (int i = 0; i < this.d.i(); ++i) {
            ((neat.system.graphics.renderer.i)this.d.a(i)).a(this);
        }
    }
    
    public j e() {
        return this.b.d();
    }
    
    public int f() {
        return this.b.b();
    }
    
    public int g() {
        return this.b.c();
    }
    
    protected void a(final int n, final int n2, final int n3, final int n4) {
        this.a(n, n2, n3, n4, false);
    }
    
    protected void a(final a a) {
        this.a(a.b, a.c, a.b + a.d, a.c + a.e, false);
    }
    
    private void a(final int n, final int n2, final int n3, final int n4, final boolean b) {
        if (!b) {
            this.e = -1;
        }
        this.g.a(n, n2, n3 - n, n4 - n2);
    }
    
    public a h() {
        this.g.c();
        return this.g;
    }
    
    public void i() {
        if (this.j() > 0) {
            this.a(0);
        }
    }
    
    protected int j() {
        if (this.b.h()) {
            return this.b.j();
        }
        if (this.b.e == null) {
            return 0;
        }
        if (this.b.e.intArrayFrames == null) {
            return 0;
        }
        return this.b.e.intArrayFrames.length;
    }
    
    protected void a(int e) {
        if (this.b.h()) {
            this.b.a(e);
            this.a(0, 0, this.b.b(), this.b.c());
            return;
        }
        if (this.b.e != null) {
            if (e < 0) {
                e = 0;
            }
            if (e >= this.b.e.intArrayFrames.length) {
                e = this.b.e.intArrayFrames.length - 1;
                if (e < 0) {
                    e = 0;
                }
            }
            this.e = e;
        }
        else {
            this.e = 0;
        }
        final int width = this.b.e.width;
        final int height = this.b.e.height;
        final int n = this.b.e.intArrayFrames[this.e];
        final int b = this.b.b();
        this.b.c();
        if (width > b) {
            return;
        }
        final int n2 = n / (b / width);
        final int n3 = n % (b / width) * width;
        final int n4 = n2 * height;
        this.a(n3, n4, n3 + width, n4 + height, true);
    }
    
    public int k() {
        if (this.b.h()) {
            return this.b.k();
        }
        if (this.b.e == null) {
            return -1;
        }
        return this.e;
    }
    
    public void b(final int m) {
        this.m = m;
        this.k = true;
    }
    
    public void a(final float o, final float q) {
        this.i = true;
        this.o = o;
        this.q = q;
    }
    
    public void a(final int m, final float o, final float q) {
        this.i = true;
        this.m = m;
        this.k = true;
        this.o = o;
        this.q = q;
    }
    
    public void l() {
        this.i = false;
    }
    
    public boolean m() {
        return this.k;
    }
    
    public int n() {
        return this.m;
    }
    
    void o() {
        this.s = true;
        this.f = this.e;
        this.h.a();
        if (this.g.b()) {
            this.h.b(this.g);
        }
        this.j = this.i;
        this.l = this.k;
        this.n = this.m;
        this.p = this.o;
        this.r = this.q;
    }
    
    void p() {
        this.e = this.f;
        this.g.a();
        if (this.h.b()) {
            this.g.b(this.h);
        }
        this.i = this.j;
        this.k = this.l;
        this.m = this.n;
        this.o = this.p;
        this.q = this.r;
        this.s = false;
    }
    
    public void a(final g g, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        this.d();
    }
    
    public void a(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.d();
    }
    
    public void a(final boolean b) {
        if (b) {
            ++this.t;
        }
        else if (this.t > 0) {
            --this.t;
        }
        this.b(this.q());
    }
    
    public boolean q() {
        return this.t > 0;
    }
    
    protected void b(final boolean b) {
    }
    
    public void c(final boolean u) {
        if (this.u != u) {
            this.d(this.u = u);
        }
    }
    
    protected void d(final boolean b) {
    }
    
    public void e(final boolean v) {
        this.v = v;
    }
    
    public boolean r() {
        return this.v;
    }
    
    public void g() {
        this.g = neat.system.graphics.renderer.a.h();
        this.h = neat.system.graphics.renderer.a.h();
        this.d = neat.i.k();
        this.i = false;
        this.k = false;
        this.t = 0;
        this.u = true;
        this.v = false;
    }
    
    public void h() {
        if (this.d.i() > 0) {
            throw new RuntimeException(neat.system.graphics.renderer.g.z[3]);
        }
        this.g.f();
        this.h.f();
        this.d.f();
        this.g = null;
        this.h = null;
        this.d = null;
    }
    
    public abstract void f();
    
    public g() {
        this.c = neat.system.graphics.renderer.g.a++;
        this.s = false;
    }
    
    static {
        final String[] z = new String[5];
        final int n = 0;
        final char[] charArray = "k\u0015X`hC3TejBZXh\u007fB\u001b]}-B\u0002Pwy".toCharArray();
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
                            c2 = '\'';
                            break;
                        }
                        case 1: {
                            c2 = 'z';
                            break;
                        }
                        case 2: {
                            c2 = '9';
                            break;
                        }
                        case 3: {
                            c2 = '\u0004';
                            break;
                        }
                        default: {
                            c2 = '\r';
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
        final char[] charArray2 = "H\u0018Ja\u007fQ\u001fK$lK\b\\ei^ZX`iB\u001e".toCharArray();
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
                            c4 = '\'';
                            break;
                        }
                        case 1: {
                            c4 = 'z';
                            break;
                        }
                        case 2: {
                            c4 = '9';
                            break;
                        }
                        case 3: {
                            c4 = '\u0004';
                            break;
                        }
                        default: {
                            c4 = '\r';
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
        final char[] charArray3 = "I\u0015W$h_\u0013JphI\u000e\u0019koT\u001fKrhU".toCharArray();
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
                            c6 = '\'';
                            break;
                        }
                        case 1: {
                            c6 = 'z';
                            break;
                        }
                        case 2: {
                            c6 = '9';
                            break;
                        }
                        case 3: {
                            c6 = '\u0004';
                            break;
                        }
                        default: {
                            c6 = '\r';
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
        final char[] charArray4 = "D\u001bWjbSZ[a-C\u001fUayB\u001e\u0002$~S\u0013Uh-N\u0014\u0019q~B".toCharArray();
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
                            c8 = '\'';
                            break;
                        }
                        case 1: {
                            c8 = 'z';
                            break;
                        }
                        case 2: {
                            c8 = '9';
                            break;
                        }
                        case 3: {
                            c8 = '\u0004';
                            break;
                        }
                        default: {
                            c8 = '\r';
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
        final char[] charArray5 = "I\u0015\u0019HbF\u001e\\`DJ\u001b^a-S\u0015\u0019vhJ\u0015Oa".toCharArray();
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
                            c10 = '\'';
                            break;
                        }
                        case 1: {
                            c10 = 'z';
                            break;
                        }
                        case 2: {
                            c10 = '9';
                            break;
                        }
                        case 3: {
                            c10 = '\u0004';
                            break;
                        }
                        default: {
                            c10 = '\r';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 <= n20) {
                z[n17] = new String(charArray5).intern();
                g.z = z;
                g.a = 10;
                return;
            }
            continue;
        }
    }
}
