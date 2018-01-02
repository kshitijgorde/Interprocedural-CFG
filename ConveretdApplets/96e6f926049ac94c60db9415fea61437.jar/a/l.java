// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.system.n;
import neat.kb;
import neat.h;
import neat.i;
import neat.system.f;
import neat.system.cb;

public class l implements cb
{
    private static f a;
    private boolean b;
    private i c;
    private h d;
    private i e;
    private i f;
    private int g;
    private int h;
    private i i;
    private boolean j;
    private static /* synthetic */ Class k;
    private static String[] z;
    
    public b a(final gb gb) {
        return this.a(gb, null);
    }
    
    public b a(final gb gb, final m m) {
        if (this.b) {
            throw new RuntimeException(l.z[0]);
        }
        kb kb = gb.d;
        if (kb == null) {
            kb = gb.a();
        }
        if (kb == null) {
            throw new RuntimeException(l.z[2] + gb);
        }
        final b a = gb.a();
        final Object g = this.d.g(kb);
        if (g == null) {
            this.d.a(kb.b(), a);
        }
        else if (g instanceof i) {
            ((i)g).a(a);
        }
        else {
            this.d.b(kb);
            final i k = neat.i.k();
            k.a(g);
            k.a(a);
            this.d.a(kb.b(), k);
        }
        this.c.a(a);
        if (a instanceof j) {
            this.e.a(a);
        }
        a.a(this);
        if (m != null) {
            m.c(a);
        }
        a.a(gb);
        this.b(a);
        a.n();
        if (this.j && a instanceof r) {
            ((r)a).b();
        }
        return a;
    }
    
    public void a(final b b) {
        if (this.b) {
            throw new RuntimeException(l.z[0]);
        }
        if (b.l()) {
            b.m();
            return;
        }
        final kb b2 = b.b();
        if (b2 == null) {
            return;
        }
        final Object g = this.d.g(b2);
        if (g == null) {
            throw new RuntimeException(l.z[3] + b);
        }
        if (g instanceof i) {
            if (!((i)g).d(b)) {
                throw new RuntimeException(l.z[3] + b);
            }
        }
        else {
            if (g != b) {
                throw new RuntimeException(l.z[3] + b);
            }
            this.d.b(b2);
        }
        this.c.d(b);
        if (b instanceof j) {
            this.e.d(b);
        }
        b.b(this);
        b.o();
        this.c(b);
        b.f();
    }
    
    public void a() {
        if (this.b) {
            throw new RuntimeException(l.z[0]);
        }
        final neat.r f = this.c.f();
        while (f.a()) {
            this.a((b)f.b());
        }
        f.f();
        final neat.r a = this.d.a();
        while (a.a()) {
            final Object g = this.d.g(a.b());
            if (g instanceof i) {
                ((i)g).f();
            }
        }
        a.f();
        this.d.d();
        this.e.c();
        this.c.j();
    }
    
    public boolean a(final kb kb) {
        return this.d.f(kb);
    }
    
    public b b(final kb kb) {
        final Object g = this.d.g(kb);
        if (!(g instanceof i)) {
            return (b)g;
        }
        if (((i)g).e()) {
            return null;
        }
        return (b)((i)g).a(0);
    }
    
    public neat.r b() {
        return this.c.f();
    }
    
    public void a(final k k) {
        if (this.f.b(k)) {
            throw new RuntimeException(l.z[1] + k);
        }
        this.f.a(k);
    }
    
    public void b(final k k) {
        if (!this.f.d(k)) {
            throw new RuntimeException(l.z[4] + k);
        }
    }
    
    private void b(final b b) {
        if (this.f.e()) {
            return;
        }
        final neat.r f = this.f.f();
        while (f.a()) {
            ((k)f.b()).a(b);
        }
        f.f();
    }
    
    private void c(final b b) {
        if (this.f.e()) {
            return;
        }
        final neat.r f = this.f.f();
        while (f.a()) {
            ((k)f.b()).b(b);
        }
        f.f();
    }
    
    public void c() {
        this.g = 0;
        this.b = false;
        this.i.c();
        this.j = false;
    }
    
    public void a(final int h) {
        this.h = h;
    }
    
    public void d() {
        if (this.j) {
            return;
        }
        this.j = true;
        final neat.r b = this.b();
        while (b.a()) {
            final b b2 = (b)b.b();
            if (b2 instanceof r) {
                ((r)b2).b();
            }
        }
        b.f();
    }
    
    public void e() {
        if (!this.j) {
            return;
        }
        this.j = false;
        final neat.r b = this.b();
        while (b.a()) {
            final b b2 = (b)b.b();
            if (b2 instanceof r) {
                ((r)b2).c();
            }
        }
        b.f();
    }
    
    public boolean f() {
        return this.j;
    }
    
    public void b(final int n) {
        final neat.r b = this.b();
        while (b.a()) {
            final b b2 = (b)b.b();
            if (b2 instanceof s) {
                if (this.j && b2 instanceof r) {
                    continue;
                }
                b2.j();
                if (!b2.f()) {
                    ((s)b2).d();
                    b2.i();
                }
                ((s)b2).a(n);
                if (!b2.k()) {
                    continue;
                }
                this.a(b2);
            }
        }
        b.f();
        if (this.j) {
            return;
        }
    }
    
    public void i() {
        final neat.r b = this.b();
        while (b.a()) {
            final b b2 = (b)b.b();
            if (b2 instanceof q) {
                ((q)b2).a();
            }
        }
        b.f();
    }
    
    public void a(final n n) {
        final neat.r f = this.e.f();
        while (f.a()) {
            final b b = (b)f.b();
            if (b instanceof a.i && b instanceof o) {
                if (this.j && b instanceof r) {
                    continue;
                }
                if (((o)b).a(n)) {
                    f.f();
                    return;
                }
                continue;
            }
        }
        f.f();
        final neat.r f2 = this.e.f();
        while (f2.a()) {
            final b b2 = (b)f2.b();
            if (b2 instanceof o) {
                if (this.j && b2 instanceof r) {
                    continue;
                }
                if (((o)b2).a(n)) {
                    break;
                }
                continue;
            }
        }
        f2.f();
    }
    
    public void a(final neat.system.o o) {
        final neat.r b = this.b();
        while (b.a()) {
            final b b2 = (b)b.b();
            if (b2 instanceof a.i && b2 instanceof o) {
                if (this.j && b2 instanceof r) {
                    continue;
                }
                if (((o)b2).a(o)) {
                    b.f();
                    return;
                }
                continue;
            }
        }
        b.f();
        final neat.r b3 = this.b();
        while (b3.a()) {
            final b b4 = (b)b3.b();
            if (b4 instanceof o) {
                if (this.j && b4 instanceof r) {
                    continue;
                }
                if (((o)b4).a(o)) {
                    break;
                }
                continue;
            }
        }
        b3.f();
    }
    
    public void a(final neat.system.m m) {
        final neat.r b = this.b();
        while (b.a()) {
            final b b2 = (b)b.b();
            if (b2 instanceof a.i && b2 instanceof p) {
                if (this.j && b2 instanceof r) {
                    continue;
                }
                if (((p)b2).a(m)) {
                    b.f();
                    return;
                }
                continue;
            }
        }
        b.f();
        final neat.r b3 = this.b();
        while (b3.a()) {
            final b b4 = (b)b3.b();
            if (b4 instanceof p) {
                if (this.j && b4 instanceof r) {
                    continue;
                }
                if (((p)b4).a(m)) {
                    break;
                }
                continue;
            }
        }
        b3.f();
    }
    
    public void a(final neat.system.l l) {
        final neat.r b = this.b();
        while (b.a()) {
            final b b2 = (b)b.b();
            if (b2 instanceof a.i && b2 instanceof p) {
                if (this.j && b2 instanceof r) {
                    continue;
                }
                if (((p)b2).a(l)) {
                    b.f();
                    return;
                }
                continue;
            }
        }
        b.f();
        final neat.r b3 = this.b();
        while (b3.a()) {
            final b b4 = (b)b3.b();
            if (b4 instanceof p) {
                if (this.j && b4 instanceof r) {
                    continue;
                }
                if (((p)b4).a(l)) {
                    break;
                }
                continue;
            }
        }
        b3.f();
    }
    
    public void a(final neat.system.i i) {
        final neat.r f = this.e.f();
        while (f.a()) {
            final b b = (b)f.b();
            if (b instanceof a.i && b instanceof a.n) {
                if (this.j && b instanceof r) {
                    continue;
                }
                if (((a.n)b).a(i)) {
                    f.f();
                    return;
                }
                continue;
            }
        }
        f.f();
        final neat.r f2 = this.e.f();
        while (f2.a()) {
            final b b2 = (b)f2.b();
            if (b2 instanceof a.n) {
                if (this.j && b2 instanceof r) {
                    continue;
                }
                if (((a.n)b2).a(i)) {
                    break;
                }
                continue;
            }
        }
        f2.f();
    }
    
    public void a(final neat.system.j j) {
        final neat.r b = this.b();
        while (b.a()) {
            final b b2 = (b)b.b();
            if (b2 instanceof a.i && b2 instanceof a.n) {
                if (this.j && b2 instanceof r) {
                    continue;
                }
                if (((a.n)b2).a(j)) {
                    b.f();
                    return;
                }
                continue;
            }
        }
        b.f();
        final neat.r b3 = this.b();
        while (b3.a()) {
            final b b4 = (b)b3.b();
            if (b4 instanceof a.n) {
                if (this.j && b4 instanceof r) {
                    continue;
                }
                if (((a.n)b4).a(j)) {
                    break;
                }
                continue;
            }
        }
        b3.f();
    }
    
    public static l j() {
        return (l)l.a.a();
    }
    
    public void f() {
        l.a.a(this);
    }
    
    public void g() {
        this.b = false;
        this.c = neat.i.k();
        this.d = neat.h.e();
        this.e = neat.i.k();
        this.f = neat.i.k();
        this.i = neat.i.k();
        this.h = 0;
        this.c();
    }
    
    public void h() {
        this.a();
        this.i.f();
        this.i = null;
        this.f.f();
        this.f = null;
        this.c.f();
        this.c = null;
        this.d.f();
        this.d = null;
        this.e.f();
        this.e = null;
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public l() {
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.i = null;
    }
    
    static {
        final String[] z = new String[6];
        final int n = 0;
        final char[] charArray = "Y[ZI&0NMAu|@\\O0t\u0003\u001fG4~\bK\u00048\u007fKVB,0[WA80\u000e".toCharArray();
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
                            c2 = '\u0010';
                            break;
                        }
                        case 1: {
                            c2 = '/';
                            break;
                        }
                        case 2: {
                            c2 = '?';
                            break;
                        }
                        case 3: {
                            c2 = '$';
                            break;
                        }
                        default: {
                            c2 = 'U';
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
        final char[] charArray2 = "DGVWutJKA6d@M\u0004<c\u000f^H'uN[]uqK[A10[P\u00042qBZ\u001e".toCharArray();
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
                            c4 = '\u0010';
                            break;
                        }
                        case 1: {
                            c4 = '/';
                            break;
                        }
                        case 2: {
                            c4 = '?';
                            break;
                        }
                        case 3: {
                            c4 = '$';
                            break;
                        }
                        default: {
                            c4 = 'U';
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
        final char[] charArray3 = "]ZLPutJYM;u\u000f^\u0004;qBZ\u0004<~\u000fKL<c\u000fLL4t@H\u001e".toCharArray();
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
                            c6 = '\u0010';
                            break;
                        }
                        case 1: {
                            c6 = '/';
                            break;
                        }
                        case 2: {
                            c6 = '?';
                            break;
                        }
                        case 3: {
                            c6 = '$';
                            break;
                        }
                        default: {
                            c6 = 'U';
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
        final char[] charArray4 = "DGVWuy[ZIuy\\\u001fJ:d\u000fWA9t\u0015".toCharArray();
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
                            c8 = '\u0010';
                            break;
                        }
                        case 1: {
                            c8 = '/';
                            break;
                        }
                        case 2: {
                            c8 = '?';
                            break;
                        }
                        case 3: {
                            c8 = '$';
                            break;
                        }
                        default: {
                            c8 = 'U';
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
        final char[] charArray5 = "DGVWutJKA6d@M\u0004<c\u000fQK!0N[@0t\u000fKKuwNRAo".toCharArray();
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
                            c10 = '\u0010';
                            break;
                        }
                        case 1: {
                            c10 = '/';
                            break;
                        }
                        case 2: {
                            c10 = '?';
                            break;
                        }
                        case 3: {
                            c10 = '$';
                            break;
                        }
                        default: {
                            c10 = 'U';
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
        final char[] charArray6 = "q\u0001S".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0682: {
                if (n22 > 1) {
                    break Label_0682;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = '\u0010';
                            break;
                        }
                        case 1: {
                            c12 = '/';
                            break;
                        }
                        case 2: {
                            c12 = '?';
                            break;
                        }
                        case 3: {
                            c12 = '$';
                            break;
                        }
                        default: {
                            c12 = 'U';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 <= n24) {
                z[n21] = new String(charArray6).intern();
                l.z = z;
                l.a = new f((l.k != null) ? l.k : (l.k = a(l.z[5])));
                return;
            }
            continue;
        }
    }
}
