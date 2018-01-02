import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class v extends d
{
    public int p;
    public int d;
    public char p;
    public Color p;
    public int a;
    public q[] p;
    public boolean p;
    public boolean d;
    public boolean a;
    public int n;
    public z p;
    public f p;
    public y p;
    public f d;
    public f a;
    public f n;
    public a p;
    
    public final void p(final int n, final int n2, final int d, final Color p6, final y p7, final c c) {
        this.p(n, n2, this, c);
        this.d = d;
        this.p = p6;
        this.p = p7;
    }
    
    public final void d(final double n, final double n2, final double n3, final double n4, final String s, final boolean b, final Color color, final boolean b2) {
        this.p[this.a] = new q(true, -1, s, b, 0, n, n2, n3, n4, this, b2, super.p);
        ++this.a;
    }
    
    public final void p(final double n, final double n2, final double n3, final double n4, final String s, final boolean b, final Color color, final boolean b2) {
        this.p[this.a] = new q(true, -1, s, b, 0, n, n2, n3, n4, this, b2, super.p);
        if (super.p.c) {
            this.p[this.a].b = true;
        }
        ++this.a;
    }
    
    public final void c() {
        for (int i = 0; i < this.a; ++i) {
            this.p[i].f();
        }
    }
    
    public final void v() {
        if (super.p.d) {
            super.p.p();
        }
        else {
            super.d.p();
        }
    }
    
    public final void l() {
        if (super.p.d) {
            super.p.a();
        }
        else {
            super.d.a();
        }
    }
    
    public final boolean p(final z z, final boolean b) {
        final f f = new f(this.n);
        final z z2 = new z();
        if (b) {
            f.p(0.12);
        }
        else {
            f.p(-0.1);
        }
        final double p2 = this.p.p(z);
        z2.d(super.p[0].p, f);
        final double p3 = this.p.p(z2);
        boolean b2;
        if (b) {
            if (p3 < 0.0) {
                b2 = (p3 < p2);
            }
            else {
                b2 = (p3 > p2);
            }
        }
        else {
            b2 = (this.d(p3, p2) && Math.abs(p2) >= Math.abs(p3));
        }
        return b2;
    }
    
    public final boolean p(final z z) {
        final z z2 = new z();
        z2.d(super.p[0].p, this.n);
        return this.p(this.p.p(z2), this.p.p(z));
    }
    
    public final void g() {
        this.p.p(super.p[0].p, super.p[1].p, super.p[2].p);
    }
    
    public final void p(final z z, final z z2, final z z3) {
        super.p[0].d(z, z2);
        super.p[1].d(z2, z3);
        super.p[2].d(z3, z);
    }
    
    public final void d() {
        this.d.p(super.p[0].p, super.p[0].a);
        this.a.p(super.p[1].p, super.p[1].a);
        this.n.p(this.d, this.a);
        this.n.p();
    }
    
    public final boolean a() {
        return super.p.a > 500.0 && this.n.p(super.p.p) > 0.64;
    }
    
    public final char p() {
        if (this.p(super.p[1].p.p - super.p[1].a.p)) {
            return 'x';
        }
        if (this.p(super.p[1].p.d - super.p[1].a.d)) {
            return 'y';
        }
        if (this.p(super.p[1].p.a - super.p[1].a.a)) {
            return 'z';
        }
        System.out.println("err:giveRotType");
        return 'e';
    }
    
    public final char p(final b b) {
        final f f = new f(super.p[0].p, super.p[0].a);
        this.d();
        final char p = this.p();
        this.p(f, 0.0, 0.0, 1.5707963267948966, p);
        if (this.n.p(f)) {
            b.p = 1.5707963267948966;
        }
        else {
            b.p = -1.5707963267948966;
        }
        return p;
    }
    
    public final void d(final boolean b) {
        if (super.p.a > 2) {
            if (super.d.d(super.p)) {
                if (b) {
                    this.v();
                }
                this.l();
            }
        }
        else if (super.p.a > 0) {
            System.out.println("Err:coreDirectFillings, nbSegments==" + super.p.a);
        }
    }
    
    public final void p(final boolean b) {
        if (this.p.i) {
            this.k();
        }
        else {
            this.i();
        }
        this.d(b);
    }
    
    public final void f() {
        this.k();
        if (super.p.a > 2 && super.d.d(super.p)) {
            super.d.a();
        }
    }
    
    public final double p() {
        final f f = new f(this.n);
        boolean b = false;
        boolean b2 = false;
        if (!f.p(super.p.p, super.p[0].p)) {
            f.d();
        }
        double n = 145.0 * super.p.d.d(f);
        if (super.p || this.p.l) {
            b = true;
        }
        if (n < 0.0) {
            n = -n;
            b2 = true;
        }
        final double n2 = 69.9;
        if (b2) {
            n /= 1.3;
        }
        double n3 = n + n2;
        if (b) {
            n3 /= 1.2;
        }
        final double n4 = n3 / 255.0;
        if (n4 <= 1.0 && n4 >= 0.0) {
            return n4;
        }
        System.out.println("Err:colorIntensity");
        return 0.0;
    }
    
    public final void d(final int n) {
        final double p = this.p();
        Color color = null;
        if (n == 100) {
            final int n2 = (int)(127.0 * p);
            color = new Color(n2, n2, n2);
        }
        else if (this.p != null) {
            color = new Color((int)(p * this.p.getRed()), (int)(p * this.p.getGreen()), (int)(p * this.p.getBlue()));
        }
        super.p.p.setColor(color);
    }
    
    public final void p(final int n) {
        final i i = new i(true);
        final int n2 = n + 1;
        final f f = new f(super.p[1].p, super.p[1].a);
        final f f2 = new f(super.p[3].a, super.p[3].p);
        final z z = new z(super.p[1].p);
        final z z2 = new z(super.p[3].a);
        final w w = new w(z, z2);
        f.d(n2);
        f2.d(n2);
        z.d(z, f);
        z2.d(z2, f2);
        w.p(z, z2);
        for (int j = 1; j < n2; ++j) {
            w.p(i, super.p);
            if (i.p) {
                w.p(super.p);
            }
            z.d(z, f);
            z2.d(z2, f2);
            w.p(z, z2);
        }
    }
    
    public final boolean p(final int p5, final z z, final z z2, final z z3, final z z4) {
        final v d = super.p.d;
        d.p(super.v, super.p, this.d, this.p, this.p, super.p);
        d.p = p5;
        d.a = true;
        d.n = this.n;
        d.p = this.p;
        d.p = this.p;
        d.d = this.d;
        d.p(z, z2, z3, z4);
        d.a = this.a;
        d.p = this.p;
        if (this.p.d) {
            if (this.p.i) {
                d.a();
            }
            else {
                d.i();
            }
            d.d();
            return true;
        }
        System.out.print("Err:createAndFillSubPlane, unvisible should have been screened out before");
        return false;
    }
    
    public final void p() {
        this.p.p(super.p[2].a, super.p[2].p, (this.p.c - this.p.b) * (this.n / 100.0) / this.p.c);
        this.p.p(super.p[2].a, this.p);
    }
    
    public final void n() {
        final Graphics p = super.p.p;
        final z z = new z();
        final z z2 = new z();
        final z z3 = new z();
        final z z4 = new z();
        final double n = (this.p.c - this.p.b) * (this.n / 100.0);
        final double n2 = n / this.p.c;
        final double n3 = (n + this.p.b) / this.p.c;
        if (this.n != 0) {
            z.p(super.p[0].p, super.p[0].a, n2);
            z3.p(super.p[2].a, super.p[2].p, n2);
        }
        if (this.n != 100) {
            z2.p(super.p[0].p, super.p[0].a, n3);
            z4.p(super.p[2].a, super.p[2].p, n3);
        }
        switch (this.n) {
            case 100: {
                this.p(0, super.p[0].p, z, z3, super.p[2].a);
                break;
            }
            case 0: {
                this.p(0, z2, super.p[0].a, super.p[2].p, z4);
                break;
            }
            default: {
                this.p(0, super.p[0].p, z, z3, super.p[2].a);
                this.p(1, z2, super.p[0].a, super.p[2].p, z4);
                break;
            }
        }
    }
    
    public final boolean p() {
        final Graphics p = super.p.p;
        if (this.p) {
            if (this.p != null && this.p.e) {
                this.d(100);
                this.l();
            }
            this.d(this.d);
            new n(this, super.p).p();
            p.setColor(Color.black);
        }
        else if (this.d) {
            this.v();
            this.d(this.d);
            this.l();
            if (!super.p.b) {
                p.setColor(Color.black);
                this.p(8);
            }
        }
        else {
            this.d(this.d);
            this.l();
        }
        for (int i = 0; i < this.a; ++i) {
            if (this.p[i] != null) {
                this.p[i].n();
            }
        }
        return true;
    }
    
    public final boolean n() {
        if (!this.a && this.n >= 0) {
            this.n();
        }
        else {
            if (!super.d.d(super.p)) {
                return false;
            }
            this.p();
        }
        return true;
    }
    
    public final boolean d() {
        if (!super.d.d(super.p)) {
            return false;
        }
        this.p();
        return true;
    }
    
    public v() {
        this.p = 0;
        this.d = -1;
        this.p = '0';
        this.p = null;
        this.a = 0;
        this.p = new q[5];
        this.p = false;
        this.d = false;
        this.a = false;
        this.n = -1;
        this.p = new z();
        this.p = new f();
        this.p = null;
        this.d = new f();
        this.a = new f();
        this.n = new f();
        this.p = new a();
    }
    
    public v(final int n, final int n2, final int d, final Color p6, final y p7, final c c) {
        this.p = 0;
        this.d = -1;
        this.p = '0';
        this.p = null;
        this.a = 0;
        this.p = new q[5];
        this.p = false;
        this.d = false;
        this.a = false;
        this.n = -1;
        this.p = new z();
        this.p = new f();
        this.p = null;
        this.d = new f();
        this.a = new f();
        this.n = new f();
        this.p = new a();
        this.p(n, n2, this, c);
        this.d = d;
        this.p = p6;
        this.p = p7;
    }
    
    public v(final int n, final int n2, final c c) {
        this.p = 0;
        this.d = -1;
        this.p = '0';
        this.p = null;
        this.a = 0;
        this.p = new q[5];
        this.p = false;
        this.d = false;
        this.a = false;
        this.n = -1;
        this.p = new z();
        this.p = new f();
        this.p = null;
        this.d = new f();
        this.a = new f();
        this.n = new f();
        this.p = new a();
        this.p(n, n2, this, c);
    }
}
