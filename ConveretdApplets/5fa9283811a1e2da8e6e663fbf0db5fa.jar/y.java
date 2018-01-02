import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class y extends k
{
    public int p;
    public z[] p;
    public z[] d;
    public z[] a;
    public z p;
    public int d;
    public boolean p;
    public boolean d;
    public boolean a;
    public boolean n;
    public boolean v;
    public boolean i;
    public boolean l;
    public boolean b;
    public boolean c;
    public double p;
    public int a;
    public double d;
    public double a;
    public int[][] p;
    public int[][] d;
    public int n;
    public v[] p;
    public double n;
    public double v;
    public double i;
    public double l;
    public boolean e;
    public boolean f;
    public double b;
    public v p;
    public v d;
    public double c;
    public int v;
    public int i;
    public y[] p;
    public boolean g;
    public y p;
    public double e;
    public z d;
    public boolean h;
    
    public final void g() {
        for (int i = 0; i < this.n; ++i) {
            this.p[i].c();
        }
    }
    
    public final void c() {
        for (int i = 0; i < this.p; ++i) {
            this.a[i].v(this.p[i]);
        }
    }
    
    public final void l() {
        for (int i = 0; i < this.p; ++i) {
            this.d[i].v(this.a[i]);
        }
    }
    
    public final void d() {
        for (int i = 0; i < this.p; ++i) {
            this.a[i].v(this.d[i]);
        }
    }
    
    public final void n() {
        for (int i = 0; i < this.p; ++i) {
            this.p[i].v(this.d[i]);
        }
    }
    
    public final void a() {
        for (int i = 0; i < this.p; ++i) {
            this.p[i] = new z(this.a[i]);
            this.d[i] = new z(this.a[i]);
        }
    }
    
    public final void p(final c p2, final int d) {
        super.p = p2;
        this.d = d;
        this.b = super.p.g;
    }
    
    public final void p(final z z, final char c, final double n, final double n2, final Color color) {
        this.p = super.p.p.d;
        this.p(this.a, z, n, n2, c, color);
        this.b = false;
    }
    
    public final void p(final y y) {
        y.d(this);
        this.d(y);
    }
    
    public final void p(final double n, final double v, final double i, final double l) {
        this.n = n;
        this.v = v;
        this.i = i;
        this.l = l;
    }
    
    public final void p(final int n, final Color color) {
        for (int i = 0; i < this.n; ++i) {
            (this.p[i] = new v(4, i + n, this.d, color, this, super.p)).p(this.a[this.p[i + n][0]], this.a[this.p[i + n][1]], this.a[this.p[i + n][2]], this.a[this.p[i + n][3]]);
            if (i == 3 - n || i == 5 - n) {
                this.p[i].p = true;
            }
            else {
                this.p[i].d = true;
            }
        }
    }
    
    public final void a(final Color color) {
        (this.p = new v(4, 0, this.d, color, this, super.p)).p(this.a[this.p[0][0]], this.a[this.p[0][1]], this.a[this.p[0][2]], this.a[this.p[0][3]]);
        this.p(1, color);
    }
    
    public final void p(final Color color) {
        (this.p = new v(4, 0, this.d, color, this, super.p)).p(this.a[this.p[0][0]], this.a[this.p[0][1]], this.a[this.p[0][2]], this.a[this.p[0][3]]);
        (this.d = new v(4, 1, this.d, color, this, super.p)).p(this.a[this.p[1][0]], this.a[this.p[1][1]], this.a[this.p[1][2]], this.a[this.p[1][3]]);
        this.p(2, color);
    }
    
    public final void d(final Color color) {
        this.p(0, color);
    }
    
    public final void p(final z z, final double n, final double n2, final boolean b, final Color color) {
        this.p = false;
        this.d = super.p.p.p;
        this.b = !b;
        this.p(this.a, z, n, n2, color);
    }
    
    public final void p(final z[] array, final z z, final double n, final double n2, final char p6, final Color color) {
        this.n = 1;
        this.p = 4;
        for (int i = 0; i < 4; ++i) {
            array[i] = new z(z.p, z.d, z.a);
        }
        switch (p6) {
            case 'h': {
                array[1].a = z.a + n;
                array[2].p = z.p + n;
                array[2].a = z.a + n2;
                array[3].p = z.p + n2;
                break;
            }
            case 'f': {
                array[1].p = z.p + n;
                array[2].p = z.p + n;
                array[2].d = z.d + n2;
                array[3].d = z.d + n2;
                break;
            }
            case 's': {
                array[1].d = z.d + n;
                array[2].d = z.d + n;
                array[2].a = z.a + n2;
                array[3].a = z.a + n2;
                break;
            }
        }
        this.e();
        this.i();
        (this.p[0] = new v(4, 0, this.d, color, this, super.p)).p(this.a[this.p[0][0]], this.a[this.p[0][1]], this.a[this.p[0][2]], this.a[this.p[0][3]]);
        this.p[0].p = p6;
    }
    
    public final void p(final z z, final double n, final double n2, final double n3) {
        this.p = 8;
        for (int i = 0; i < this.p; ++i) {
            this.a[i] = new z(z.p, z.d, z.a);
        }
        this.a[1].p = z.p + n;
        this.a[2].p = z.p + n;
        this.a[2].d = z.d + n2;
        this.a[3].d = z.d + n2;
        this.a[4].a = z.a + n3;
        this.a[5].p = z.p + n;
        this.a[5].a = z.a + n3;
        this.a[6].p = z.p + n;
        this.a[6].d = z.d + n2;
        this.a[6].a = z.a + n3;
        this.a[7].d = z.d + n2;
        this.a[7].a = z.a + n3;
    }
    
    public final void p(final z z, final double n, final double n2, final double n3, final boolean b, final boolean b2, final Color color) {
        this.n = 6;
        this.p(z, n, n2, -n3);
        this.e();
        this.i();
        for (int i = 0; i < this.n; ++i) {
            (this.p[i] = new v(4, i, this.d, color, this, super.p)).p(this.a[this.p[i][0]], this.a[this.p[i][1]], this.a[this.p[i][2]], this.a[this.p[i][3]]);
            if (b) {
                if (b2) {
                    this.p[i].p = true;
                }
                else if (i != 2 && i != 4) {
                    this.p[i].p = true;
                }
            }
        }
    }
    
    public final void p(final z[] array, final z z, final double n, final double n2, final Color color) {
        final double n3 = Math.sqrt(3.0) / 2.0;
        final double sqrt = Math.sqrt(0.6666666666666666);
        this.n = 4;
        this.p = 4;
        for (int i = 0; i < this.p; ++i) {
            array[i] = new z(z.p, z.d, z.a);
        }
        array[1].p = z.p + n;
        array[2].p = z.p + 0.5 * n;
        array[2].a = z.a + n3 * n;
        array[3].p = z.p + 0.5 * n;
        array[3].d = z.d + sqrt * n * n2;
        array[3].a = z.a + n * n3 / 3.0;
        this.e();
        this.i();
        for (int j = 0; j < this.n; ++j) {
            (this.p[j] = new v(3, j, this.d, color, this, super.p)).p(this.a[this.d[j][0]], this.a[this.d[j][1]], this.a[this.d[j][2]]);
        }
    }
    
    public final void p(final z z) {
        z.p();
        int n = 0;
        for (int i = 0; i < this.i; ++i) {
            if (!this.p[i].f) {
                this.p[i].i();
                ++n;
                z.d(this.p[i].p);
            }
        }
        z.p(n);
    }
    
    public final void i() {
        this.p.p();
        for (int i = 0; i < this.p; ++i) {
            this.p.d(this.a[i]);
        }
        this.p.p(this.p);
    }
    
    public final void e() {
        this.p = this.a[0].a;
        this.a = 0;
        this.d = this.a[0].a;
        this.a = 0.0;
        for (int i = 1; i < this.p; ++i) {
            if (this.p < this.a[i].a) {
                this.p = this.a[i].a;
                this.a = i;
            }
            if (this.d > this.a[i].a) {
                this.d = this.a[i].a;
                this.a = i;
            }
        }
    }
    
    public final void f() {
        for (int i = 0; i < this.n; ++i) {
            this.p[i].d();
            this.p[i].g();
        }
        if (this.p != null) {
            this.p.d();
            this.p.g();
        }
        if (this.d != null) {
            this.d.d();
            this.d.g();
        }
    }
    
    public final void p() {
        for (int i = 0; i < this.n; ++i) {
            this.p[i].p();
        }
    }
    
    public final boolean p() {
        return this.e && super.p.p;
    }
    
    public final void a(final z z, final double n) {
        for (int i = 0; i < this.p; ++i) {
            this.d(this.a[i], z, n);
        }
        this.i();
        this.e();
        if (this.p()) {
            this.p();
        }
    }
    
    public final void d(final z z, final double n) {
        for (int i = 0; i < this.p; ++i) {
            this.p(this.a[i], z, n);
        }
        this.i();
        this.e();
        if (this.p()) {
            this.p();
        }
    }
    
    public final void p(final z z, final double n) {
        for (int i = 0; i < this.p; ++i) {
            this.a(this.a[i], z, n);
        }
        this.i();
        if (this.p()) {
            this.p();
        }
    }
    
    public final void p(final double n) {
        for (int i = 0; i < this.p; ++i) {
            this.a[i].a += n;
        }
        this.e();
        this.i();
    }
    
    public final void p(final double n, final double n2, final double n3) {
        for (int i = 0; i < this.p; ++i) {
            this.a[i].p += n;
            this.a[i].d += n2;
            this.a[i].a += n3;
        }
        this.e();
        this.i();
    }
    
    public final boolean v() {
        this.d = this.f(this.d);
        this.i = this.p(this.d, this.p);
        if (!this.d) {
            return false;
        }
        for (int i = 0; i < this.n; ++i) {
            if (this.i) {
                this.p[i].a();
            }
            else {
                this.p[i].i();
            }
        }
        if (this.p != null) {
            this.p.a();
        }
        if (this.d != null) {
            this.d.a();
        }
        if (!this.e) {
            if (this.b && !this.i) {
                this.p(false);
            }
            if (!this.b) {
                this.p(true);
            }
        }
        return true;
    }
    
    public final void b() {
        this.d = this.f(this.d);
        this.i = this.p(this.d, this.p);
        if (this.d && !this.a && !super.p.p.p.a) {
            for (int i = 0; i < this.n; ++i) {
                if (this.p[i].p.p && !this.p[i].a()) {
                    this.p[i].n();
                }
            }
        }
        if (this.d || super.p.b) {
            this.v();
        }
    }
    
    public final void p(final g g, final g g2, final boolean b) {
        if (b) {
            g2.p.p = false;
            g.p.p = true;
        }
        else {
            g.p = false;
        }
    }
    
    public final void p(final boolean b) {
        final Graphics p = super.p.p;
        final o p2 = super.p.p;
        final g g = new g(super.p);
        final z z = new z();
        final z z2 = new z();
        for (int i = 0; i < this.n - 1; ++i) {
            final g p3 = this.p[i].p;
            if (p3.p) {
                for (int j = i + 1; j < this.n; ++j) {
                    final g p4 = this.p[j].p;
                    if (p4.p) {
                        g.l();
                        if (g.a(p3, p4)) {
                            this.p(z, z2, g.v, p3, p4);
                            if (z.a < z2.a) {
                                this.p(p3, p4, b);
                            }
                            else {
                                this.p(p4, p3, b);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public final void d(final y y) {
        if (this.i < 15) {
            this.p[this.i] = y;
            ++this.i;
        }
        else {
            System.out.println("Err:pushVectAttachedVol");
        }
    }
    
    public final boolean a() {
        this.v();
        if (this.d) {
            for (int i = 0; i < this.n; ++i) {
                if (this.p[i].p.p) {
                    super.p.p.p(this.p[i].p);
                }
            }
        }
        return true;
    }
    
    public final void v() {
        final h p = super.p.p;
        boolean b = false;
        p.l();
        if (super.p.p.p != super.p.a || this != super.p.n) {
            for (int i = 0; i < this.i; ++i) {
                if (!this.p[i].f) {
                    this.p[i].a();
                    if (this.p[i].d) {
                        b = true;
                    }
                }
            }
        }
        if (super.p.b) {
            b = true;
            super.p.p.p.p[0].a();
        }
        if (b) {
            p.i();
            p.p();
            p.n();
        }
    }
    
    public final boolean p(final boolean b) {
        if (this.v || this.h || !this.f) {
            return false;
        }
        if (b) {
            this.v();
        }
        else {
            this.b();
        }
        return this.h = true;
    }
    
    public final void a(final boolean b) {
        super.p.p.a();
        this.h = true;
        for (int i = 0; i < this.i; ++i) {
            this.p[i].h = true;
            for (int j = 0; j < this.p[i].i; ++j) {
                this.p[i].p[j].h = true;
            }
        }
        for (int k = 0; k < this.i; ++k) {
            for (int l = 0; l < this.p[k].i; ++l) {
                for (int n = 0; n < this.p[k].p[l].i; ++n) {
                    this.p[k].p[l].p[n].p(b);
                }
            }
        }
        super.p.p.a();
        this.h = true;
        for (int n2 = 0; n2 < this.i; ++n2) {
            this.p[n2].h = true;
        }
        for (int n3 = 0; n3 < this.i; ++n3) {
            for (int n4 = 0; n4 < this.p[n3].i; ++n4) {
                this.p[n3].p[n4].p(b);
            }
        }
        this.h = false;
        for (int n5 = 0; n5 < this.i; ++n5) {
            this.p[n5].h = false;
        }
        for (int n6 = 0; n6 < this.i; ++n6) {
            this.p[n6].p(b);
        }
        this.p(b);
    }
    
    public final void d(final boolean b) {
        if (this.g && super.p.p.p(this.d) < this.e) {
            this.v = true;
            this.p.a(b);
            this.v = false;
            this.p(b);
        }
        else {
            this.a(b);
        }
    }
    
    public final boolean p(final z z) {
        boolean b = true;
        for (int i = 0; i < this.n; ++i) {
            if (!this.p[i].p(z)) {
                b = false;
                break;
            }
        }
        return b;
    }
    
    public final boolean d(final z z) {
        if (!this.e && this.b && this.p(z)) {
            return true;
        }
        if (!this.e && !this.b) {
            if (this.p(z)) {
                this.l = true;
            }
            else {
                this.l = false;
            }
        }
        return false;
    }
    
    public final boolean n() {
        final z p = super.p.p;
        boolean b = true;
        if (this.d != null && !this.d.p(p, true)) {
            b = false;
        }
        else if (this.p != null && !this.p.p(p, true)) {
            b = false;
        }
        else {
            for (int i = 0; i < this.n; ++i) {
                if (!this.p[i].p(p, false)) {
                    b = false;
                    break;
                }
            }
        }
        return b;
    }
    
    public final boolean d() {
        final z p = super.p.p;
        return (this.d == null || this.d.p(p)) && (this.p == null || this.p.p(p)) && this.p(p);
    }
    
    public final boolean d(final boolean b) {
        if (b) {
            return this.f && this.n();
        }
        return this.f && this.d();
    }
    
    public y(z p12, final int n, final double c, final int n2, final int n3, final int n4, final int n5, final y y, final int n6, final int n7, final Color color, final c c2) {
        this.p = new z[8];
        this.d = new z[8];
        this.a = new z[8];
        this.p = new z();
        this.d = -1;
        this.p = true;
        this.d = true;
        this.a = false;
        this.n = false;
        this.v = false;
        this.i = false;
        this.l = false;
        this.b = false;
        this.c = false;
        this.n = 0;
        this.p = new v[6];
        this.n = 0.0;
        this.v = 0.0;
        this.i = 0.0;
        this.l = 0.0;
        this.e = false;
        this.f = true;
        this.b = 20.0;
        this.p = null;
        this.d = null;
        this.c = -1.0;
        this.v = 0;
        this.i = 0;
        this.p = new y[15];
        this.g = false;
        this.p = null;
        this.e = 0.0;
        this.d = null;
        this.h = false;
        this.p(c2, n);
        int n8 = 0;
        this.v = 6 - n7;
        this.e = true;
        this.p = super.p.p.d;
        this.n = n7;
        this.c = c;
        this.p = 8;
        if (y != null) {
            this.p(y);
            n8 = n6 - y.v;
            p12 = y.p[n8].p;
            for (int i = 0; i < 4; ++i) {
                (this.a[i] = new z(y.a[i])).d(this.a[i], y.p[n8].p);
            }
            final f f = new f();
            f.p(y.p[2].p[2].a, y.p[2].p[2].p);
            f.p();
            f.p(c);
            f.d(y.p[n8].p);
            for (int j = 4; j < this.p; ++j) {
                (this.a[j] = new z(y.a[j - 4])).d(this.a[j], f);
            }
        }
        else {
            this.p(p12, this.b, this.b, -c);
        }
        this.e();
        this.i();
        switch (n7) {
            case 4: {
                this.p(color);
                break;
            }
            case 5: {
                this.a(color);
                break;
            }
            case 6: {
                this.d(color);
                break;
            }
            default: {
                System.out.println("err mazeConstruct: wrong number of faces");
                break;
            }
        }
        this.b = false;
        this.p[2 - this.v].n = n2;
        this.p[3 - this.v].n = n3;
        this.p[4 - this.v].n = n4;
        this.p[5 - this.v].n = n5;
        if (y != null) {
            final b b = new b();
            switch (y.p[n8].p(b)) {
                case 'x': {
                    this.a(p12, b.p);
                    break;
                }
                case 'y': {
                    this.d(p12, b.p);
                    break;
                }
                case 'z': {
                    this.p(p12, b.p);
                    break;
                }
                default: {
                    System.out.println("Err:loadNextPoint-switch");
                    break;
                }
            }
        }
        this.p();
        this.a();
    }
    
    public y(final z z, final int n, final double c, final y y, final Color color, final c c2) {
        this.p = new z[8];
        this.d = new z[8];
        this.a = new z[8];
        this.p = new z();
        this.d = -1;
        this.p = true;
        this.d = true;
        this.a = false;
        this.n = false;
        this.v = false;
        this.i = false;
        this.l = false;
        this.b = false;
        this.c = false;
        this.n = 0;
        this.p = new v[6];
        this.n = 0.0;
        this.v = 0.0;
        this.i = 0.0;
        this.l = 0.0;
        this.e = false;
        this.f = true;
        this.b = 20.0;
        this.p = null;
        this.d = null;
        this.c = -1.0;
        this.v = 0;
        this.i = 0;
        this.p = new y[15];
        this.g = false;
        this.p = null;
        this.e = 0.0;
        this.d = null;
        this.h = false;
        this.p(c2, n);
        this.e = true;
        this.p = super.p.p.d;
        this.n = 6;
        this.c = c;
        this.p = 8;
        this.p(y);
        for (int i = 0; i < this.p; ++i) {
            this.a[i] = new z(y.a[i]);
        }
        this.e();
        this.i();
        this.d(color);
        this.p();
        this.a();
    }
    
    public y(final z z, final int n, final double n2, final double n3, final double n4, final boolean b, final boolean b2, final y y, final Color color, final c c) {
        this.p = new z[8];
        this.d = new z[8];
        this.a = new z[8];
        this.p = new z();
        this.d = -1;
        this.p = true;
        this.d = true;
        this.a = false;
        this.n = false;
        this.v = false;
        this.i = false;
        this.l = false;
        this.b = false;
        this.c = false;
        this.n = 0;
        this.p = new v[6];
        this.n = 0.0;
        this.v = 0.0;
        this.i = 0.0;
        this.l = 0.0;
        this.e = false;
        this.f = true;
        this.b = 20.0;
        this.p = null;
        this.d = null;
        this.c = -1.0;
        this.v = 0;
        this.i = 0;
        this.p = new y[15];
        this.g = false;
        this.p = null;
        this.e = 0.0;
        this.d = null;
        this.h = false;
        this.p(c, n);
        this.p = super.p.p.d;
        this.p(z, n2, n3, n4, b, b2, color);
        this.b = !b;
        y.d(this);
        this.f = false;
        this.a();
    }
    
    public y(final z z, final int n, final double n2, final double n3, final boolean b, final y y, final Color color, final c c) {
        this.p = new z[8];
        this.d = new z[8];
        this.a = new z[8];
        this.p = new z();
        this.d = -1;
        this.p = true;
        this.d = true;
        this.a = false;
        this.n = false;
        this.v = false;
        this.i = false;
        this.l = false;
        this.b = false;
        this.c = false;
        this.n = 0;
        this.p = new v[6];
        this.n = 0.0;
        this.v = 0.0;
        this.i = 0.0;
        this.l = 0.0;
        this.e = false;
        this.f = true;
        this.b = 20.0;
        this.p = null;
        this.d = null;
        this.c = -1.0;
        this.v = 0;
        this.i = 0;
        this.p = new y[15];
        this.g = false;
        this.p = null;
        this.e = 0.0;
        this.d = null;
        this.h = false;
        this.p(c, n);
        this.p(z, n2, n3, b, color);
        if (b) {
            for (int i = 0; i < this.n; ++i) {
                this.p[i].p = true;
            }
        }
        y.d(this);
        this.f = false;
        this.a();
    }
    
    public y(final z z, final int n, final char c, final double n2, final double n3, final y y, final Color color, final c c2) {
        this.p = new z[8];
        this.d = new z[8];
        this.a = new z[8];
        this.p = new z();
        this.d = -1;
        this.p = true;
        this.d = true;
        this.a = false;
        this.n = false;
        this.v = false;
        this.i = false;
        this.l = false;
        this.b = false;
        this.c = false;
        this.n = 0;
        this.p = new v[6];
        this.n = 0.0;
        this.v = 0.0;
        this.i = 0.0;
        this.l = 0.0;
        this.e = false;
        this.f = true;
        this.b = 20.0;
        this.p = null;
        this.d = null;
        this.c = -1.0;
        this.v = 0;
        this.i = 0;
        this.p = new y[15];
        this.g = false;
        this.p = null;
        this.e = 0.0;
        this.d = null;
        this.h = false;
        this.p(c2, n);
        this.p(z, c, n2, n3, color);
        y.d(this);
        this.f = false;
        this.a();
    }
}
