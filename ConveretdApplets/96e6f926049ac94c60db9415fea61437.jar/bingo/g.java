// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.p;
import neat.kb;
import neat.system.f;
import neat.system.cb;

public class g implements cb
{
    private static f a;
    private int b;
    private int c;
    private kb d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int[] j;
    private int[] k;
    private int[] l;
    private char[] m;
    private int[] n;
    private int[] o;
    private int[] p;
    private int q;
    private int r;
    private boolean s;
    private int t;
    private p u;
    private static /* synthetic */ Class v;
    private static String[] z;
    
    public void a(final int b, final int c, final kb kb, final int e, final int f, final int g) {
        this.a();
        if (kb == null) {
            return;
        }
        this.b = b;
        this.c = c;
        this.d = kb.b();
        this.e = e;
        this.f = f;
        this.g = g;
        this.i();
    }
    
    public void a() {
        if (this.d != null) {
            this.d.f();
            this.d = null;
            this.e = -1;
            this.i();
        }
    }
    
    public int b() {
        return this.e;
    }
    
    public boolean a(final int n) {
        return n >= 0 && n < this.i * this.h;
    }
    
    public int b(int e) {
        e = this.e(e);
        if (e < 0) {
            return -1;
        }
        return this.j[e];
    }
    
    public int c(int e) {
        e = this.e(e);
        if (e < 0) {
            return -1;
        }
        return this.k[e];
    }
    
    public int d(int e) {
        e = this.e(e);
        if (e < 0) {
            return -1;
        }
        return this.l[e];
    }
    
    public int c() {
        return this.f(this.q);
    }
    
    public int d() {
        return this.f(this.r);
    }
    
    public int e() {
        return this.i * this.h;
    }
    
    private void i() {
        this.k();
        this.i = 0;
        this.q = -1;
        this.r = -1;
        if (this.d == null) {
            return;
        }
        final int d = this.d.d();
        if (d == 0) {
            return;
        }
        ++this.i;
        for (int i = 0; i < d; ++i) {
            final char b = this.d.b(i);
            if (b >= '0' && b <= '7') {
                ++this.i;
            }
        }
        this.i += this.f * 2;
        if (this.j.length < this.i) {
            this.j = new int[this.i];
            this.k = new int[this.i];
            this.l = new int[this.i];
            this.m = new char[this.i];
            this.n = new int[this.i];
            this.o = new int[this.i];
            this.p = new int[this.i];
        }
        int f = this.f;
        char c = 'p';
        int b2 = this.b;
        int c2 = this.c;
        int n = 0;
        int n2 = 0;
        while (true) {
            this.j[f] = b2;
            this.k[f] = c2;
            this.l[f] = n;
            this.m[f] = c;
            this.n[f] = 0;
            this.o[f] = 0;
            this.p[f] = 0;
            if (c == 'd') {
                if (this.q == -1) {
                    this.q = f;
                }
                c = 'p';
            }
            else if (c == 'e') {
                break;
            }
            if (n2 >= d) {
                c = 'e';
            }
            else {
                final char b3 = this.d.b(n2);
                if (b3 < '0' || b3 > '7') {
                    c = b3;
                }
                else {
                    int n3 = 0;
                    int n4 = 0;
                    switch (b3) {
                        case '0': {
                            n3 = -1;
                            n4 = -1;
                            break;
                        }
                        case '1': {
                            n4 = -1;
                            break;
                        }
                        case '2': {
                            n3 = 1;
                            n4 = -1;
                            break;
                        }
                        case '3': {
                            n3 = -1;
                            break;
                        }
                        case '4': {
                            n3 = 1;
                            break;
                        }
                        case '5': {
                            n3 = -1;
                            n4 = 1;
                            break;
                        }
                        case '6': {
                            n4 = 1;
                            break;
                        }
                        case '7': {
                            n3 = 1;
                            n4 = 1;
                            break;
                        }
                    }
                    this.n[f] = n3;
                    this.o[f] = n4;
                    this.p[f] = 0;
                    b2 += n3;
                    c2 += n4;
                    n = 0;
                    ++f;
                }
            }
            ++n2;
        }
        this.r = f;
        if (this.f > 0) {
            for (int j = this.f - 1; j >= 0; --j) {
                this.j[j] = this.j[j + 1];
                this.k[j] = this.k[j + 1];
                this.l[j] = this.l[j + 1] - 1;
                this.m[j] = 'p';
                this.n[j] = 0;
                this.o[j] = 0;
                this.p[j] = 1;
            }
            if (this.q == this.f) {
                this.q = 0;
            }
            for (int k = this.i - this.f - this.g; k < this.i; ++k) {
                this.j[k] = this.j[k - 1];
                this.k[k] = this.k[k - 1];
                this.l[k] = this.l[k - 1] + this.p[k - 1];
                this.m[k] = 'p';
                this.n[k] = 0;
                this.o[k] = 0;
                this.p[k] = -1;
            }
        }
        if (this.q < 0) {
            this.q = 0;
        }
        if (this.r < 0) {
            if (this.f > 0) {
                this.r = this.i - this.f - 1;
            }
            else {
                this.r = this.i - 1;
            }
        }
        this.j();
    }
    
    public int e(int n) {
        if (this.d == null) {
            return -1;
        }
        if (n < 0) {
            n = 0;
        }
        n = (n + this.h / 2) / this.h;
        if (n >= this.i) {
            return this.i - 1;
        }
        return n;
    }
    
    private int f(int n) {
        if (this.d == null) {
            return -1;
        }
        if (n < 0) {
            n = 0;
        }
        else if (n >= this.i) {
            n = this.i - 1;
        }
        return n * this.h;
    }
    
    public int g(final int n) {
        return n * this.h;
    }
    
    private int h(final int n) {
        final int e = this.e(n);
        if (e < 0) {
            return 0;
        }
        final int n2 = this.j[e] * this.h;
        if (e >= this.i - 1) {
            return n2;
        }
        return n2 + (this.j[e + 1] * this.h - n2) * (n % this.h) / this.h;
    }
    
    private int i(final int n) {
        final int e = this.e(n);
        if (e < 0) {
            return 0;
        }
        final int n2 = this.k[e] * this.h;
        if (e >= this.i - 1) {
            return n2;
        }
        return n2 + (this.k[e + 1] * this.h - n2) * (n % this.h) / this.h;
    }
    
    private int j(final int n) {
        final int e = this.e(n);
        if (e < 0) {
            return 0;
        }
        final int n2 = this.l[e] * this.h;
        if (e >= this.i - 1) {
            return n2;
        }
        return n2 + (this.l[e + 1] * this.h - n2) * (n % this.h) / this.h;
    }
    
    public boolean a(final int n, final int n2, final int n3, final int n4) {
        if (!this.a(n) || !this.a(n3)) {
            return false;
        }
        final long n5 = this.h(n) - this.h(n3);
        final long n6 = this.i(n) - this.i(n3);
        final long n7 = this.j(n) - this.j(n3);
        final long n8 = this.g(n2 + n4 - 1);
        final int n9 = n3 - n;
        return n5 * n5 + n6 * n6 + n7 * n7 < n8 * n8 && n9 * n9 < n8 * n8;
    }
    
    public boolean b(final int n, final int n2, final int n3, final int n4) {
        if (n2 > n4) {
            return false;
        }
        if (!this.a(n) || !this.a(n3)) {
            return false;
        }
        final long n5 = this.h(n) - this.h(n3);
        final long n6 = this.i(n) - this.i(n3);
        final long n7 = this.j(n) - this.j(n3);
        final long n8 = this.g(n4 - n2);
        return n5 * n5 + n6 * n6 + n7 * n7 <= n8 * n8;
    }
    
    public int a(int n, final int n2, final int n3, final int n4, final int n5) {
        if (this.a(n2, n4, n5)) {
            return this.a(n, n3);
        }
        if (!this.a(n) || !this.a(n3)) {
            return 0;
        }
        final int n6 = n;
        final long n7 = this.h(n);
        final long n8 = this.i(n);
        final long n9 = this.j(n);
        final long n10 = this.h(n3);
        final long n11 = this.i(n3);
        final long n12 = this.j(n3);
        final long n13 = this.g(n2 + n4 - 1);
        final int g = this.g(this.i);
        final long n14 = n7 - n10;
        final long n15 = n8 - n11;
        final long n16 = n9 - n12;
        if (n14 * n14 + n15 * n15 + n16 * n16 >= n13 * n13) {
            return 0;
        }
        final int n17 = n3 - n;
        if (n17 * n17 >= n13 * n13) {
            return 0;
        }
        long n18 = 0L;
    Label_0617:
        while (n18 < 2L) {
            long n19;
            if (n5 == 0) {
                n19 = ((n < n3) ? -1 : 1);
                if (n18 != 0L) {
                    n19 *= -1L;
                }
            }
            else {
                if (n18 != 0L) {
                    return -1000000000;
                }
                n19 = ((n5 < 0) ? -1 : 1);
            }
            long n20;
            if (n19 < 0L) {
                n20 = n3 - n13 - n;
            }
            else {
                n20 = n3 + n13 - n;
            }
            if (n20 == 0L) {
                n20 = n19;
            }
            n += (int)n20;
            if (n < 0) {
                n = 0;
            }
            else if (n >= g) {
                n = g - 1;
            }
            final long n21 = this.h(n);
            final long n22 = this.i(n);
            final long n23 = this.j(n);
            final long n24 = n21 - n10;
            final long n25 = n22 - n11;
            final long n26 = n23 - n12;
            final int n27 = n3 - n;
            final boolean b = n24 * n24 + n25 * n25 + n26 * n26 < n13 * n13 && n27 * n27 < n13 * n13;
            if (b) {
                final long n28 = n19;
            }
            else {
                final long n28 = -n19;
            }
            boolean b2 = b;
            int n29 = n;
            while (true) {
                Label_0589: {
                    break Label_0589;
                    do {
                        final long n30 = this.h(n);
                        final long n31 = this.i(n);
                        final long n32 = this.j(n);
                        final long n33 = n30 - n10;
                        final long n34 = n31 - n11;
                        final long n35 = n32 - n12;
                        final int n36 = n3 - n;
                        b2 = (n33 * n33 + n34 * n34 + n35 * n35 < n13 * n13 && n36 * n36 < n13 * n13);
                        if (b != b2) {
                            if (!b) {
                                n = n29;
                            }
                            else if (b2) {
                                ++n18;
                                continue Label_0617;
                            }
                            return n - n6;
                        }
                        n29 = n;
                        final long n28;
                        n += (int)n28;
                    } while (n >= 0 && n < g);
                }
                continue;
            }
        }
        return -1000000000;
    }
    
    public void k(final int t) {
        if (t <= 0) {
            throw new RuntimeException(bingo.g.z[0] + t);
        }
        if (this.t == t) {
            return;
        }
        this.t = t;
        this.j();
    }
    
    private void j() {
        this.k();
        if (this.d == null || this.t <= 0) {
            return;
        }
        final int g = this.g(this.i);
        this.u.c(g);
        int n = -1;
        int n2 = 0;
        while (true) {
            Label_0297: {
                break Label_0297;
                int i = 0;
                do {
                    if (i != -1000000000) {
                        final int n3 = n2 + i;
                        if (n >= 0) {
                            final int n4 = 2;
                            int n5 = n;
                            int n6 = n2;
                            for (int j = 1; j <= n4; ++j) {
                                final int n7 = n + (n2 - n) * j / n4;
                                final int a = this.a(n7, this.t, n7, this.t, 1);
                                if (a == -1000000000) {
                                    break;
                                }
                                int n8 = n7 + a;
                                if (n8 < n6) {
                                    n8 = n6;
                                }
                                else if (n8 > n3) {
                                    n8 = n3;
                                }
                                for (int k = n5; k < n7; ++k) {
                                    final int n9 = n6 + (k - n5) * (n8 - n6) / (n7 - n5);
                                    if (n9 < 0 || n9 >= g) {
                                        this.u.a(k, -1000000000);
                                    }
                                    else {
                                        this.u.a(k, n9 - k);
                                    }
                                }
                                n5 = n7;
                                n6 = n8;
                            }
                        }
                        n = n2;
                        if (n < g - 1) {
                            n2 = n3;
                            if (n2 > g) {
                                n2 = g - 1;
                            }
                            i = this.a(n2, this.t, n2, this.t, 1);
                            continue;
                        }
                    }
                    this.s = true;
                    return;
                } while (i != 0);
            }
            continue;
        }
    }
    
    private void k() {
        if (this.s) {
            return;
        }
        this.s = false;
        this.u.d();
    }
    
    private boolean a(final int n, final int n2, final int n3) {
        return this.s && n == this.t && n2 == this.t && n3 > 0;
    }
    
    private int a(int n, final int n2) {
        if (!this.s) {
            return -1000000000;
        }
        if (!this.a(n) || !this.a(n2)) {
            return 0;
        }
        final int n3 = n;
        final boolean b = false;
        if (n2 < 0 || n2 >= this.u.a()) {
            return 0;
        }
        final int d = this.u.d(n2);
        if (d == -1000000000) {
            return -1000000000;
        }
        final int n4 = n2 + d;
        if (n4 > n) {
            n = n4;
        }
        if (n == (b ? 1 : 0)) {
            return 0;
        }
        return n - n3;
    }
    
    public static g l() {
        return (g)g.a.a();
    }
    
    public void f() {
        bingo.g.a.a(this);
    }
    
    public void g() {
        this.b = 0;
        this.c = 0;
        this.e = -1;
        this.f = 0;
        this.g = 0;
        this.h = 10;
        this.i = 0;
        this.q = -1;
        this.r = -1;
        this.s = false;
        this.t = -1;
        this.u = neat.p.e();
    }
    
    public void h() {
        this.a();
        this.k();
        this.u.f();
        this.u = null;
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public g() {
        this.d = null;
        this.j = new int[0];
        this.k = new int[0];
        this.l = new int[0];
        this.m = new char[0];
        this.n = new int[0];
        this.o = new int[0];
        this.p = new int[0];
        this.u = null;
    }
    
    static {
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = "4!\u0010e>\u0004%\n1l\u0016$\u0017dmW.\u001bvw\u00036\u001b1q\u0005`N+".toCharArray();
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
                            c2 = 'w';
                            break;
                        }
                        case 1: {
                            c2 = '@';
                            break;
                        }
                        case 2: {
                            c2 = '~';
                            break;
                        }
                        case 3: {
                            c2 = '\u0011';
                            break;
                        }
                        default: {
                            c2 = '\u001e';
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
        final char[] charArray2 = "\u0015)\u0010vqY'".toCharArray();
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
                            c4 = 'w';
                            break;
                        }
                        case 1: {
                            c4 = '@';
                            break;
                        }
                        case 2: {
                            c4 = '~';
                            break;
                        }
                        case 3: {
                            c4 = '\u0011';
                            break;
                        }
                        default: {
                            c4 = '\u001e';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 <= n8) {
                z[n5] = new String(charArray2).intern();
                g.z = z;
                g.a = new f((g.v != null) ? g.v : (g.v = a(g.z[1])));
                return;
            }
            continue;
        }
    }
}
