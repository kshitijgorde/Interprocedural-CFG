import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class ae
{
    int a;
    int b;
    int c;
    int d;
    int e;
    int f;
    boolean g;
    
    ae() {
        this.g = false;
    }
    
    ae(final ae ae) {
        this.a = ae.a;
        this.b = ae.b;
        this.e = ae.e;
        this.f = ae.f;
        this.c = ae.c;
        this.d = ae.d;
        this.g = ae.g;
    }
    
    ae(final ae ae, final Point point, final Point point2) {
        final boolean l = c.l;
        this.a = point.x;
        this.b = point.y;
        this.e = point2.x;
        this.f = point2.y;
        final int n = ae.c - ae.a;
        final int n2 = ae.d - ae.b;
        final int n3 = ae.c - ae.e;
        final int n4 = ae.d - ae.f;
        final int c = ac.c(ae.a - ae.e, ae.b - ae.f);
        int n5 = 0;
        Label_0158: {
            if (c > 0) {
                n5 = (ac.c(point.x - point2.x, point.y - point2.y) << 16) / c;
                if (!l) {
                    break Label_0158;
                }
            }
            n5 = 65536;
        }
        Label_0276: {
            if (ac.b(n, n2) > ac.b(n3, n4)) {
                this.c = (int)(n5 * n + 32768L >> 16) + point.x;
                this.d = (int)(n5 * n2 + 32768L >> 16) + point.y;
                if (!l) {
                    break Label_0276;
                }
            }
            this.c = (int)(n5 * n3 + 32768L >> 16) + point2.x;
            this.d = (int)(n5 * n4 + 32768L >> 16) + point2.y;
        }
        this.g = false;
    }
    
    final void a(final Point point, final Point point2) {
        this.a = point.x;
        this.b = point.y;
        this.e = point2.x;
        this.f = point2.y;
        this.c = (this.a + this.e) / 2;
        this.d = (this.b + this.f) / 2;
        this.g = true;
    }
    
    final void a(final Point point, final Point point2, final Point point3) {
        this.a = point.x;
        this.b = point.y;
        this.e = point3.x;
        this.f = point3.y;
        this.c = point2.x;
        this.d = point2.y;
        this.g = false;
    }
    
    final ae a(final int n) {
        final ae ae = new ae();
        ae.g = this.g;
        ae.e = this.e;
        ae.f = this.f;
        ae.c = (int)(n * (this.e - this.c) + 32768L >> 16) + this.c;
        ae.d = (int)(n * (this.f - this.d) + 32768L >> 16) + this.d;
        this.c = (int)(n * (this.c - this.a) + 32768L >> 16) + this.a;
        this.d = (int)(n * (this.d - this.b) + 32768L >> 16) + this.b;
        final ae ae2 = ae;
        final int n2 = (int)(n * (ae.c - this.c) + 32768L >> 16) + this.c;
        ae2.a = n2;
        this.e = n2;
        final ae ae3 = ae;
        final int n3 = (int)(n * (ae.d - this.d) + 32768L >> 16) + this.d;
        ae3.b = n3;
        this.f = n3;
        return ae;
    }
    
    final ae a() {
        final ae ae = new ae();
        ae.g = this.g;
        ae.a = this.e;
        ae.b = this.f;
        ae.c = this.c;
        ae.d = this.d;
        ae.e = this.a;
        ae.f = this.b;
        return ae;
    }
    
    final int b() {
        return ac.b(this.c - (this.a + this.e) / 2, this.d - (this.b + this.f) / 2);
    }
    
    final int a(final Point point, int n) {
        final boolean l = c.l;
        final f f = new f(this.a, this.b, this.e, this.f);
        Label_0072: {
            if (this.c < f.a) {
                f.a = this.c;
                if (!l) {
                    break Label_0072;
                }
            }
            if (this.c > f.c) {
                f.c = this.c;
            }
        }
        Label_0115: {
            if (this.d < f.b) {
                f.b = this.d;
                if (!l) {
                    break Label_0115;
                }
            }
            if (this.d > f.d) {
                f.d = this.d;
            }
        }
        if (f.c < point.x || f.b > point.y || f.d <= point.y) {
            return 0;
        }
        if (f.b(point) && n < 12 && ac.b(f.c - f.a, f.d - f.b) > 4) {
            final ae ae = new ae(this);
            final ae a = ae.a(32768);
            ++n;
            return ae.a(point, n) + a.a(point, n);
        }
        if (this.b == this.f) {
            return 0;
        }
        int b = this.b;
        int f2 = this.f;
        if (this.b > this.f) {
            final int n2 = b;
            b = f2;
            f2 = n2;
        }
        return (point.y >= b && point.y < f2) ? 1 : 0;
    }
}
