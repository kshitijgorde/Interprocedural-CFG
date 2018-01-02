// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.system.graphics.renderer.p;
import neat.r;
import neat.kb;
import neat.i;
import neat.system.graphics.renderer.g;
import neat.h;
import neat.system.f;
import neat.system.cb;

public class z implements cb
{
    private static f a;
    private xb b;
    private h c;
    private g d;
    private h e;
    public float f;
    public float g;
    public int h;
    public boolean i;
    public int j;
    public float k;
    public float l;
    public i m;
    public i n;
    public kb o;
    private i p;
    private i q;
    private static /* synthetic */ Class r;
    private static String[] z;
    
    public void a(final x x, final xb b) {
        this.a(x);
        this.b = b;
        if (b.e == null) {
            throw new RuntimeException(a.z.z[2] + b);
        }
        final r b2 = b.e.b();
        while (b2.a()) {
            final kb kb = (kb)b2.b();
            final kb d = b.e.d(kb);
            final g b3 = x.b(d, b.n);
            b3.a(b.v);
            if (b3 == null) {
                throw new RuntimeException(a.z.z[3] + d);
            }
            if (this.d == null) {
                this.d = b3;
            }
            this.c.a(kb.b(), b3);
        }
        b2.f();
        final r b4 = b.y.b();
        while (b4.a()) {
            final kb kb2 = (kb)b4.b();
            this.e.a(kb2, b.y.c(kb2));
        }
        b4.f();
        this.f = b.k;
        this.g = b.l;
        this.h = b.m;
        this.j = b.n;
        this.k = b.o;
        this.l = b.p;
        if (this.e.g(db.b) != null) {
            this.o = db.b.b();
        }
        if (b.w != null) {
            this.a(x, b.w);
        }
        if (b.x != null) {
            this.b(x, b.x);
        }
        this.e(x);
        this.d(x);
    }
    
    public void a(final x x) {
        this.c(x);
        this.d = null;
        final r a = this.c.a();
        while (a.a()) {
            final g g = (g)this.c.g(a.b());
            if (g != null) {
                g.a(false);
            }
        }
        a.f();
        this.c.d();
        this.e.m();
        this.h = 0;
        this.i = false;
        this.k = 1.0f;
        this.l = 0.0f;
        this.m.j();
        this.n.j();
        if (this.o != null) {
            this.o.f();
            this.o = null;
        }
        if (this.b != null) {
            this.b.f();
            this.b = null;
        }
    }
    
    public boolean a() {
        return this.b != null;
    }
    
    public boolean a(final x x, final kb kb) {
        if (kb == null) {
            return false;
        }
        final g d = (g)this.c.g(kb);
        if (d == null) {
            return false;
        }
        this.d = d;
        this.d(x);
        return true;
    }
    
    public kb b() {
        return this.o;
    }
    
    public void a(final x x, final cc cc) {
        final r c = cc.c();
        while (c.a()) {
            this.m.a(((kb)c.b()).b());
        }
        c.f();
        this.d(x);
    }
    
    public void b(final x x, final cc cc) {
        final r c = cc.c();
        while (c.a()) {
            this.n.a(((kb)c.b()).b());
        }
        c.f();
        this.d(x);
    }
    
    g c() {
        if (!this.a()) {
            throw new RuntimeException(a.z.z[0]);
        }
        return this.d;
    }
    
    g a(final kb kb) {
        if (!this.a()) {
            throw new RuntimeException(a.z.z[0]);
        }
        final g g = (g)this.c.g(kb);
        if (g != null) {
            return g;
        }
        return this.d;
    }
    
    public int d() {
        if (!this.a()) {
            throw new RuntimeException(a.z.z[0]);
        }
        return this.b.f;
    }
    
    public int e() {
        if (!this.a()) {
            throw new RuntimeException(a.z.z[0]);
        }
        return this.b.g;
    }
    
    public int f() {
        if (!this.a()) {
            throw new RuntimeException(a.z.z[0]);
        }
        return this.b.h;
    }
    
    public int g() {
        if (!this.a()) {
            throw new RuntimeException(a.z.z[0]);
        }
        return this.b.i;
    }
    
    public int h() {
        if (!this.a()) {
            throw new RuntimeException(a.z.z[0]);
        }
        return this.b.j;
    }
    
    public float i() {
        return this.f;
    }
    
    public float j() {
        return this.g;
    }
    
    wb b(final kb kb) {
        if (!this.a()) {
            throw new RuntimeException(a.z.z[0]);
        }
        return (wb)this.e.g(kb);
    }
    
    public int k() {
        return this.b.q;
    }
    
    public db b(final x x) {
        final db e = db.e();
        e.a(x, this);
        e.a(x, this.h);
        if (this.j >= 0) {
            e.b(x, this.j);
        }
        else {
            e.g(x);
        }
        e.b(x, this.k, this.l);
        if (!this.m.e()) {
            final r f = this.m.f();
            e.a(x, f);
            f.f();
        }
        if (!this.n.e()) {
            final r f2 = this.n.f();
            e.b(x, f2);
            f2.f();
        }
        return e;
    }
    
    public void a(final x x, final db db) {
        db.h(x);
        db.b(x);
        db.f();
    }
    
    void a(final db db) {
        if (this.p.b(db)) {
            throw new RuntimeException(a.z.z[1] + db);
        }
        this.p.a(db);
    }
    
    void b(final db db) {
        if (!this.p.d(db)) {
            throw new RuntimeException(a.z.z[4] + db);
        }
    }
    
    public void c(final x x) {
        final r f = this.p.f();
        while (f.a()) {
            ((db)f.b()).c(x);
        }
        f.f();
        this.f(x);
    }
    
    public void d(final x x) {
        final r f = this.p.f();
        while (f.a()) {
            ((db)f.b()).d(x);
        }
        f.f();
    }
    
    private void e(final x x) {
        if (!this.b.r) {
            return;
        }
        this.f(x);
        if (this.b.t <= 0 || this.b.u <= 0) {
            return;
        }
        final p b = x.b();
        for (int i = 0; i < this.b.s; ++i) {
            g a;
            try {
                a = b.a(this.b.t, this.b.u);
                a.a(true);
            }
            catch (Throwable t) {
                break;
            }
            if (a == null) {
                break;
            }
            this.q.a(a);
        }
    }
    
    g a(final x x, int f, int g, final int n, final int n2) {
        final p b = x.b();
        if (n2 < 0 || n >= 0 || x.l()) {
            g a = null;
            final r f2 = this.q.f();
            while (f2.a()) {
                final g g2 = (g)f2.b();
                if (g2.f() >= f && g2.g() >= g) {
                    f2.e();
                    a = g2;
                    break;
                }
            }
            f2.f();
            if (a == null) {
                if (this.b.r) {
                    return null;
                }
                g g3 = null;
                if (!this.q.e()) {
                    g3 = (g)this.q.g();
                    if (g3.f() > f) {
                        f = g3.f();
                    }
                    if (g3.g() > g) {
                        g = g3.g();
                    }
                }
                f = f * 12 / 10;
                g = g * 12 / 10;
                try {
                    if (g3 != null) {
                        b.b(g3);
                    }
                    a = b.a(f, g);
                    a.a(true);
                }
                catch (Throwable t) {
                    return null;
                }
            }
            if (n >= 0) {
                a.a(0, 0, f, g, n);
            }
            else if (n2 >= 0) {
                a.b(n2);
                a.a(0, 0, f, g, n2);
            }
            return a;
        }
        g a2;
        try {
            a2 = b.a(f, g, n2);
            a2.a(true);
        }
        catch (Throwable t2) {
            return null;
        }
        return a2;
    }
    
    void a(final x x, final g g) {
        this.q.a(g);
    }
    
    private void f(final x x) {
        final p b = x.b();
        final r f = this.q.f();
        while (f.a()) {
            b.b((g)f.b());
        }
        f.f();
        this.q.c();
    }
    
    public static z l() {
        return (z)a.z.a.a();
    }
    
    public void f() {
        a.z.a.a(this);
    }
    
    public void g() {
        this.e = neat.h.e();
        this.c = neat.h.e();
        this.p = neat.i.k();
        this.f = 1.0f;
        this.g = 1.0f;
        this.h = 0;
        this.i = false;
        this.k = 1.0f;
        this.l = 0.0f;
        this.m = neat.i.k();
        this.n = neat.i.k();
        this.q = neat.i.k();
    }
    
    public void h() {
        this.q.f();
        this.q = null;
        if (this.b != null) {
            this.b.f();
            this.b = null;
        }
        this.m.j();
        this.m.f();
        this.m = null;
        this.n.j();
        this.n.f();
        this.n = null;
        this.e.f();
        this.e = null;
        this.d = null;
        this.c.f();
        this.c = null;
        this.p.f();
        this.p = null;
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
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
    }
    
    static {
        final String[] z = new String[6];
        final int n = 0;
        final char[] charArray = "'9\u0001@R\u0006#\u0013PR\u001e$\u0017\u0014\u0014\u0005\"\u0006\u0014S".toCharArray();
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
                            c2 = 'j';
                            break;
                        }
                        case 1: {
                            c2 = 'L';
                            break;
                        }
                        case 2: {
                            c2 = 'r';
                            break;
                        }
                        case 3: {
                            c2 = '4';
                            break;
                        }
                        default: {
                            c2 = 'r';
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
        final char[] charArray2 = ">$\u001bGR\u001e)\n@R\u0019<\u0000]\u0006\u000fl\u001bGR\u000b \u0000Q\u0013\u000e5RU\u0016\u000e)\u0016\u0014\u0006\u0005l\u0014[\u001c\u001ev".toCharArray();
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
                            c4 = 'j';
                            break;
                        }
                        case 1: {
                            c4 = 'L';
                            break;
                        }
                        case 2: {
                            c4 = 'r';
                            break;
                        }
                        case 3: {
                            c4 = '4';
                            break;
                        }
                        default: {
                            c4 = 'r';
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
        final char[] charArray3 = "#!\u0013S\u0017J*\u001dFR\u0006)\u0006@\u0017\u0018?R]\u0001J\"\u001d@R\u000e)\u0014]\u001c\u000f(R]\u001cJ8\u001a]\u0001J?\u001aU\u0016\u0005;H".toCharArray();
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
                            c6 = 'j';
                            break;
                        }
                        case 1: {
                            c6 = 'L';
                            break;
                        }
                        case 2: {
                            c6 = 'r';
                            break;
                        }
                        case 3: {
                            c6 = '4';
                            break;
                        }
                        default: {
                            c6 = 'r';
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
        final char[] charArray4 = ")-\u001c\u0013\u0006J \u001dU\u0016J%\u001fU\u0015\u000fl\u001dRR\f#\u001c@H".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0450: {
                if (n14 > 1) {
                    break Label_0450;
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
                            c8 = 'L';
                            break;
                        }
                        case 2: {
                            c8 = 'r';
                            break;
                        }
                        case 3: {
                            c8 = '4';
                            break;
                        }
                        default: {
                            c8 = 'r';
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
        final char[] charArray5 = ">$\u001bGR\u001e)\n@R\u0019<\u0000]\u0006\u000fl\u001bGR\u0004#\u0006\u0014\u0013\u000e(\u0017PR\u001e#RR\u001d\u00048H".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0570: {
                if (n18 > 1) {
                    break Label_0570;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = 'j';
                            break;
                        }
                        case 1: {
                            c10 = 'L';
                            break;
                        }
                        case 2: {
                            c10 = 'r';
                            break;
                        }
                        case 3: {
                            c10 = '4';
                            break;
                        }
                        default: {
                            c10 = 'r';
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
        final char[] charArray6 = "\u000bb\b".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0690: {
                if (n22 > 1) {
                    break Label_0690;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = 'j';
                            break;
                        }
                        case 1: {
                            c12 = 'L';
                            break;
                        }
                        case 2: {
                            c12 = 'r';
                            break;
                        }
                        case 3: {
                            c12 = '4';
                            break;
                        }
                        default: {
                            c12 = 'r';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 <= n24) {
                z[n21] = new String(charArray6).intern();
                a.z.z = z;
                a.z.a = new f((a.z.r != null) ? a.z.r : (a.z.r = a(a.z.z[5])));
                return;
            }
            continue;
        }
    }
}
