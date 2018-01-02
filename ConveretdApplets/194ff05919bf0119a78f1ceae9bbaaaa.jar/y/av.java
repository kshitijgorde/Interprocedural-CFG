// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Event;
import java.util.Vector;

public class av extends u
{
    private Vector a;
    private u[][] a;
    private int[] a;
    private int[] b;
    private int[] c;
    private int[] d;
    private int[] e;
    private int[] f;
    private int[] g;
    private int[] h;
    private boolean[] a;
    private boolean[] b;
    private int a;
    private int b;
    private boolean a;
    private int t;
    private int u;
    
    public av(final byte b) {
        this(0);
    }
    
    public av(final char c) {
        this(0, true);
    }
    
    public av(final int n) {
        this(n, false);
    }
    
    private av(final int t, final boolean b) {
        super(b);
        this.a = new Vector();
        this.a = new u[16][16];
        this.a = new int[16];
        this.b = new int[16];
        this.c = new int[17];
        this.d = new int[17];
        this.e = new int[16];
        this.f = new int[16];
        this.g = new int[17];
        this.h = new int[17];
        this.a = new boolean[16];
        this.b = new boolean[16];
        this.a = 0;
        this.b = 0;
        this.a = true;
        this.u = 0;
        this.t = t;
    }
    
    public av() {
        super(101, 181);
        this.a = new Vector();
        this.a = new u[16][16];
        this.a = new int[16];
        this.b = new int[16];
        this.c = new int[17];
        this.d = new int[17];
        this.e = new int[16];
        this.f = new int[16];
        this.g = new int[17];
        this.h = new int[17];
        this.a = new boolean[16];
        this.b = new boolean[16];
        this.a = 0;
        this.b = 0;
        this.a = true;
        this.u = 0;
    }
    
    public final void a(final u u, final int n, final int n2) {
        this.a(u, 0, 0, 0, 0, 0, 0, 0, n2, n, 0, 0, true, true);
    }
    
    public final void a(final u u, final int n, final int n2, final int n3) {
        this.a(u, 1, 1, n, n2, false, (n2 > 0) ? n3 : 0, (n > 0) ? n3 : 0, 0, 0);
    }
    
    public final void a(final u u, final int n) {
        this.a(u, 1, 1, n, 0);
    }
    
    public final void a(final u u, final int n, final int n2, int n3, int n4, int n5, int n6, int n7, final int n8, final int n9, final int n10, final int n11) {
        final int n12 = n3;
        final int n13 = n4;
        final int n14 = n5;
        final int n15 = n6;
        n7 = n7;
        n6 = n15;
        n5 = n14;
        n4 = n13;
        n3 = n12;
        this.a(u, n, n2, n12, n4, n5, n6, n7, n8, n9, n10, n11, false, false);
    }
    
    public final void a(final u u, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        this.a(u, n, n2, n3, n4, n5, n6, n7, 0, 0, 0, 0);
    }
    
    public final void a(final u u, final int n, final int n2, final int n3, final int n4) {
        this.a(u, 10, 0, 0, n, n2, n3, n4);
    }
    
    public final void a(final u u, final int n, final int n2, final int n3, final int n4, final int n5) {
        this.a(u, 10, 0, 0, 1, n, n2, n3, n4, 4, n5, 4);
    }
    
    public final void a(final u u, final int n, final int n2, final int n3, final int n4, final boolean b) {
        this.a(u, n, n2, n3, n4, b, 0, 0, 0, 0);
    }
    
    public final void a(final u u, final int n, final int n2, final int n3, final int n4, final boolean b, final int n5, final int n6, final int n7, final int n8) {
        this.a(u, 10, 1, b ? 1 : 0, n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    void a(final u u, int i, int j, final int q, final int k, final int l, final int m, final int l2, final int m2, final int n, final int o, final int p14, final boolean b, final boolean i2) {
        if (!this.a.contains(u)) {
            u.g = i;
            u.h = j;
            u.q = q;
            u.i = k;
            u.j = l;
            u.k = m;
            u.l = l2;
            u.m = m2;
            u.n = n;
            u.o = o;
            u.p = p14;
            u.a = this;
            u.i = i2;
            if (super.c) {
                u.b();
            }
            this.a.addElement(u);
            if (!b) {
                for (i = m; i < m + k; ++i) {
                    for (j = l2; j < l2 + l; ++j) {
                        this.a[j][i] = u;
                    }
                }
            }
            else {
                u.d = true;
            }
            if (super.c) {
                if (u.d) {
                    b(u);
                    if (!super.i) {
                        u.h();
                    }
                }
                else {
                    this.d();
                }
            }
        }
    }
    
    private static void b(final u u) {
        u.e = u.a();
        u.f = u.b();
        u.d = u.m;
        u.c = u.n;
        u.e();
    }
    
    protected final void b(final ei ei, final int n, final int n2, final int n3, final int n4) {
        if (super.i) {
            super.b(ei, n, n2, n3, n4);
            return;
        }
        final int a = ei.a;
        final int b = ei.b;
        for (int i = 0; i < this.a.size(); ++i) {
            final u u;
            if ((u = this.a.elementAt(i)).a(n, n2, n3, n4) && (this.t != 2 || u.k == this.u)) {
                ei.a = a + u.c;
                ei.b = b + u.d;
                u.b(ei, n - u.c, n2 - u.d, n3 - u.c, n4 - u.d);
            }
        }
        for (int j = 0; j < this.a.size(); ++j) {
            this.a.elementAt(j);
        }
    }
    
    public final void c(final int u) {
        this.u = u;
        this.h();
    }
    
    public final u a() {
        if (super.a == null) {
            return null;
        }
        if (super.c == null) {
            return super.a.a();
        }
        return null;
    }
    
    public final void h() {
        if (super.c) {
            this.j();
            super.h();
        }
    }
    
    protected void j() {
        final boolean b = true;
        this.a = b;
        super.e = b;
        for (int i = 0; i < this.a.size(); ++i) {
            ((u)this.a.elementAt(i)).j();
        }
    }
    
    public final void a(final u u) {
        this.a.removeElement(u);
        if (super.c) {
            u.g();
        }
        if (!u.d) {
            for (int i = 0; i < 16; ++i) {
                for (int j = 0; j < 16; ++j) {
                    if (this.a[i][j] == u) {
                        this.a[i][j] = null;
                    }
                }
            }
            this.a();
            return;
        }
        this.b(u.c, u.d, u.e, u.f);
        if (!u.i) {
            this.h();
        }
    }
    
    public void d() {
        this.a();
    }
    
    private void a() {
        super.d();
        this.a = 0;
        this.b = 0;
        int i;
        for (i = 0; i < 16; ++i) {
            this.b[i] = false;
            this.a[i] = false;
            this.a[i] = 0;
            this.b[i] = 0;
            this.c[i] = 0;
            this.d[i] = 0;
            this.e[i] = 0;
            this.f[i] = 0;
            this.g[i] = 0;
            this.h[i] = 0;
            this.a[i] = false;
            this.b[i] = false;
        }
        this.c[i] = 0;
        this.d[i] = 0;
        this.g[i] = 0;
        this.h[i] = 0;
    }
    
    public void b() {
        super.b();
        for (int i = 0; i < this.a.size(); ++i) {
            final u u;
            (u = this.a.elementAt(i)).b();
            if (u.d) {
                b(u);
            }
        }
    }
    
    protected void e() {
        super.e();
        this.a = true;
        this.l();
        int n = super.e - this.c[16];
        int n2 = super.f - this.d[16];
        this.g[0] = (this.h[0] = 0);
        if (this.t == 1) {
            int n3 = 0;
            int n4 = 0;
            for (int i = 0; i < 16; ++i) {
                for (int j = 0; j < 16; ++j) {
                    if (this.a[i][j] != null) {
                        if (i > n4) {
                            n4 = i;
                        }
                        if (j > n3) {
                            n3 = j;
                        }
                    }
                }
            }
            ++n4;
            ++n3;
            final int n5 = (super.e + n3 - 1) / n3;
            final int n6 = (super.f + n4 - 1) / n4;
            for (int k = 0; k < 16; ++k) {
                this.g[k + 1] = this.g[k] + n5;
                this.h[k + 1] = this.h[k] + n6;
                this.e[k] = n5;
                this.f[k] = n6;
            }
        }
        else if (this.t == 0 || this.t == 3) {
            if (this.a == 0) {
                this.g[0] = n / 2;
            }
            if (this.b == 0) {
                this.h[0] = n2 / 2;
            }
            int n7 = 0;
            int n8 = 0;
            for (int l = 0; l < 16; ++l) {
                if (this.a[l] && n > 0) {
                    final int n9 = n / (this.a - n7);
                    this.e[l] = this.a[l] + n9;
                    n -= n9;
                    ++n7;
                }
                else {
                    this.e[l] = this.a[l];
                }
                this.g[l + 1] = this.g[l] + this.e[l];
                if (this.b[l] && n2 > 0) {
                    final int n10 = n2 / (this.b - n8);
                    this.f[l] = this.b[l] + n10;
                    n2 -= n10;
                    ++n8;
                }
                else {
                    this.f[l] = this.b[l];
                }
                this.h[l + 1] = this.h[l] + this.f[l];
            }
        }
        for (int n11 = 0; n11 < this.a.size(); ++n11) {
            final u u;
            if ((u = this.a.elementAt(n11)).d) {
                u.e = u.a();
                u.f = u.b();
            }
            else if (this.t == 2) {
                u.c = 0;
                u.d = 0;
                u.e = super.e;
                u.f = super.f;
            }
            else if (this.t == 1 || this.t == 0 || this.t == 3) {
                final int e = this.g[u.k + u.i] - this.g[u.k] - u.n - u.p;
                final int f = this.h[u.l + u.j] - this.h[u.l] - u.m - u.o;
                if (u.h == 1 || u.h == 2) {
                    u.e = e;
                }
                else {
                    u.e = u.a();
                }
                if (u.h == 1 || u.h == 3) {
                    u.f = f;
                }
                else {
                    u.f = u.b();
                }
                if (u.g == 13 || u.g == 12 || u.g == 14) {
                    u.c = this.g[u.k + u.i] - u.e - u.p;
                }
                else if (u.g == 17 || u.g == 18 || u.g == 16) {
                    u.c = this.g[u.k] + u.n;
                }
                else {
                    u.c = this.g[u.k] + u.n + (e - u.e) / 2;
                }
                if (u.g == 15 || u.g == 14 || u.g == 16) {
                    u.d = this.h[u.l + u.j] - u.f - u.o;
                }
                else if (u.g == 11 || u.g == 12 || u.g == 18) {
                    u.d = this.h[u.l] + u.m;
                }
                else {
                    u.d = this.h[u.l] + u.m + (f - u.f) / 2;
                }
            }
        }
        for (int n12 = 0; n12 < this.a.size(); ++n12) {
            final u u2;
            (u2 = this.a.elementAt(n12)).e();
            u2.e = true;
        }
    }
    
    public void g() {
        super.g();
        for (int i = 0; i < this.a.size(); ++i) {
            ((u)this.a.elementAt(i)).g();
        }
    }
    
    private void l() {
        if (super.g) {
            return;
        }
        super.g = true;
        this.c[0] = (this.d[0] = 0);
        if (this.t == 0 || this.t == 3) {
            for (int i = 0; i < 16; ++i) {
                this.b[i] = 0;
                final int[] array = new int[i + 1];
                for (int j = 0; j < 16; ++j) {
                    final u u;
                    if ((u = this.a[i][j]) != null && u.l + u.j == i + 1) {
                        final int f = u.f();
                        boolean b = false;
                        for (int k = u.l; k < i; ++k) {
                            if (this.b[k]) {
                                b = true;
                            }
                        }
                        if (!b && (u.q == 1 || u.q == 3) && !this.b[i]) {
                            this.b[i] = true;
                            ++this.b;
                        }
                        if (f > array[u.j - 1]) {
                            array[u.j - 1] = f;
                        }
                    }
                }
                for (int l = 1; l <= i + 1; ++l) {
                    int n = 0;
                    for (int n2 = 0; n2 < l; ++n2) {
                        n += this.b[i - n2];
                    }
                    int n3;
                    if ((n3 = array[l - 1] - n) > 0) {
                        int n4 = 0;
                        for (int n5 = 0; n5 < l; ++n5) {
                            if (this.b[i - n5]) {
                                ++n4;
                            }
                        }
                        if (n4 == 0) {
                            final int[] b2 = this.b;
                            final int n6 = i;
                            b2[n6] += n3;
                        }
                        int n7 = 0;
                        for (int n8 = 0; n8 < l; ++n8) {
                            if (this.b[i - n8]) {
                                final int n9 = n3 / (n4 - n7);
                                final int[] b3 = this.b;
                                final int n10 = i - n8;
                                b3[n10] += n9;
                                n3 -= n9;
                                ++n7;
                            }
                        }
                    }
                }
            }
            for (int n11 = 0; n11 < 16; ++n11) {
                this.a[n11] = 0;
                final int[] array2 = new int[n11 + 1];
                for (int n12 = 0; n12 < 16; ++n12) {
                    final u u2;
                    if ((u2 = this.a[n12][n11]) != null && u2.k + u2.i == n11 + 1) {
                        final int e = u2.e();
                        boolean b4 = false;
                        for (int m = u2.k; m < n11; ++m) {
                            if (this.a[m]) {
                                b4 = true;
                            }
                        }
                        if (!b4 && (u2.q == 1 || u2.q == 2) && !this.a[n11]) {
                            this.a[n11] = true;
                            ++this.a;
                        }
                        if (e > array2[u2.i - 1]) {
                            array2[u2.i - 1] = e;
                        }
                    }
                }
                for (int n13 = 1; n13 <= n11 + 1; ++n13) {
                    int n14 = 0;
                    for (int n15 = 0; n15 < n13; ++n15) {
                        n14 += this.a[n11 - n15];
                    }
                    int n16;
                    if ((n16 = array2[n13 - 1] - n14) > 0) {
                        int n17 = 0;
                        for (int n18 = 0; n18 < n13; ++n18) {
                            if (this.a[n11 - n18]) {
                                ++n17;
                            }
                        }
                        if (n17 == 0) {
                            final int[] a = this.a;
                            final int n19 = n11;
                            a[n19] += n16;
                        }
                        int n20 = 0;
                        for (int n21 = 0; n21 < n13; ++n21) {
                            if (this.a[n11 - n21]) {
                                final int n22 = n16 / (n17 - n20);
                                final int[] a2 = this.a;
                                final int n23 = n11 - n21;
                                a2[n23] += n22;
                                n16 -= n22;
                                ++n20;
                            }
                        }
                    }
                }
            }
            if (this.t == 3) {
                if (this.a[2]) {
                    this.a[0] = (this.a[2] = Math.max(this.a[0], this.a[2]));
                }
                if (this.b[2]) {
                    this.b[0] = (this.b[2] = Math.max(this.b[0], this.b[2]));
                }
            }
            for (int n24 = 0; n24 < 16; ++n24) {
                this.c[n24 + 1] = this.c[n24] + this.a[n24];
                this.d[n24 + 1] = this.d[n24] + this.b[n24];
            }
            return;
        }
        if (this.t == 2) {
            int n25 = 0;
            int n26 = 0;
            for (int n27 = 0; n27 < this.a.size(); ++n27) {
                final u u3;
                final int e2 = (u3 = this.a.elementAt(n27)).e();
                final int f2 = u3.f();
                if (e2 > n25) {
                    n25 = e2;
                }
                if (f2 > n26) {
                    n26 = f2;
                }
            }
            this.c[16] = n25;
            this.d[16] = n26;
            return;
        }
        if (this.t == 1) {
            int n28 = 0;
            int n29 = 0;
            int n30 = 0;
            int n31 = 0;
            for (int n32 = 0; n32 < 16; ++n32) {
                for (int n33 = 0; n33 < 16; ++n33) {
                    final u u4;
                    if ((u4 = this.a[n32][n33]) != null) {
                        final int n34;
                        if ((n34 = n33 + u4.i) > n30) {
                            n30 = n34;
                        }
                        final int n35;
                        if ((n35 = (u4.e() + u4.i - 1) / u4.i) > n28) {
                            n28 = n35;
                        }
                        final int n36;
                        if ((n36 = n32 + u4.j) > n31) {
                            n31 = n36;
                        }
                        final int n37;
                        if ((n37 = (u4.f() + u4.j - 1) / u4.j) > n29) {
                            n29 = n37;
                        }
                    }
                }
            }
            this.a[0] = n28;
            this.b[0] = n29;
            for (int n38 = 1; n38 <= 16; ++n38) {
                this.c[n38] = this.c[n38 - 1];
                this.d[n38] = this.d[n38 - 1];
                if (n38 <= n30) {
                    if (n38 < n30) {
                        this.a[n38] = n28;
                    }
                    final int[] c = this.c;
                    final int n39 = n38;
                    c[n39] += n28;
                }
                if (n38 <= n31) {
                    if (n38 < n31) {
                        this.b[n38] = n29;
                    }
                    final int[] d = this.d;
                    final int n40 = n38;
                    d[n40] += n29;
                }
            }
        }
    }
    
    public void a(ei ei) {
        final av av = this;
        ei = ei;
        this = av;
        if (!av.g) {
            this.e();
        }
        if (this.a) {
            this.b(ei);
            this.j();
            final av av2 = this;
            final av av3 = this;
            final boolean b = false;
            av3.e = b;
            av2.a = b;
        }
        final int a = ei.a;
        final int b2 = ei.b;
        for (int i = 0; i < this.a.size(); ++i) {
            final u u;
            if (!(u = this.a.elementAt(i)).i && (this.t != 2 || u.d || this.u == u.k)) {
                ei.a = a + u.c;
                ei.b = b2 + u.d;
                final u u2 = u;
                ei a2 = ei;
                final u u3 = u2;
                if (u2.e && u3.f) {
                    u3.e = false;
                    if (u3.h) {
                        a2 = a2.a(0, u3.e, u3.f);
                    }
                    u3.a(a2);
                    if (u3.h) {
                        a2.a.dispose();
                    }
                }
            }
        }
        ei.a = a;
        ei.b = b2;
    }
    
    protected int a() {
        if (super.r != 0) {
            return super.r;
        }
        this.l();
        return this.c[16];
    }
    
    protected final int b() {
        if (super.s != 0) {
            return super.s;
        }
        this.l();
        return this.d[16];
    }
    
    public u a(int n, int n2) {
        for (int i = this.a.size() - 1; i >= 0; --i) {
            final u u;
            if ((u = this.a.elementAt(i)).d && u.a(n, n2)) {
                n -= u.c;
                n2 -= u.d;
                return u.a(n, n2);
            }
        }
        for (int j = 0; j < this.a.size(); ++j) {
            final u u2;
            if (!(u2 = this.a.elementAt(j)).d && u2.a(n, n2) && (this.t != 2 || u2.k == this.u)) {
                n -= u2.c;
                n2 -= u2.d;
                return u2.a(n, n2);
            }
        }
        return this;
    }
    
    public boolean c(final Event event, final int n, final int n2) {
        return false;
    }
    
    static {
        new ej();
    }
}
