// 
// Decompiled by Procyon v0.5.30
// 

public class _zv
{
    public int p;
    public int d;
    public int a;
    public int n;
    public int v;
    public boolean p;
    public double p;
    public double d;
    public double a;
    public double n;
    public double v;
    public double i;
    public double l;
    public double b;
    public double c;
    public double e;
    public double f;
    public int[] p;
    
    public synchronized double p(final double n) {
        double n3;
        if (this.p() || this.d()) {
            this.d = this.a(n);
            double n2;
            for (n2 = Math.floor(this.v * 10.0) / 10.0; n2 > this.n; n2 -= 0.1) {
                this.p(n, this.d, n2, this.p, this.d);
                if (this.b < this.v && this.e < this.a && this.c > this.n) {
                    break;
                }
            }
            n3 = n2;
        }
        else {
            n3 = 90.0;
        }
        return n3;
    }
    
    public final synchronized double p() {
        return this.p(this.p);
    }
    
    public final synchronized boolean p() {
        return Math.abs(this.n + 90.0) > 1.0E-4 || Math.abs(this.v - 90.0) > 1.0E-4;
    }
    
    public final synchronized double d() {
        return this.a;
    }
    
    public final synchronized void p() {
        this.p = null;
    }
    
    public _zv() {
        this.p = false;
        this.n = -90.0;
        this.v = 90.0;
        this.i = 5.0;
        this.l = 60.0;
        this.f = 120.0;
    }
    
    public final synchronized double a(final double n) {
        return this.n(Math.atan2(this.d / 2, this.p / 2 / Math.tan(this.v(n / 2.0)))) * 2.0;
    }
    
    public final synchronized void p(final boolean p) {
        this.p = p;
    }
    
    public final double v(final double n) {
        return n / 180.0 * 3.141592653589793;
    }
    
    public synchronized int p(final int[] array, final double n, final double n2, final double n3) {
        return 0;
    }
    
    public final synchronized double a() {
        return this.d;
    }
    
    public final synchronized double n() {
        return this.l;
    }
    
    public final synchronized void p(final double n, final double n2, final double n3, final long n4, final long n5) {
        final double tan = Math.tan(this.v(n / 2.0));
        final double tan2 = Math.tan(this.v(n2 / 2.0));
        final double sin = Math.sin(this.v(n3));
        final double cos = Math.cos(this.v(n3));
        final double n6 = tan2;
        final double n7 = cos - n6 * sin;
        final double n8 = sin + n6 * cos;
        final double n9 = -tan;
        final double atan2 = Math.atan2(n9, n7);
        final double atan3 = Math.atan2(n8, Math.sqrt(n9 * n9 + n7 * n7));
        final double n10 = -atan2;
        final double n11 = 0.0;
        Math.atan2(n11, n7);
        final double atan4 = Math.atan2(n8, Math.sqrt(n11 * n11 + n7 * n7));
        final double n12 = -tan2;
        final double n13 = cos - n12 * sin;
        final double n14 = sin + n12 * cos;
        final double n15 = -tan;
        final double atan5 = Math.atan2(n15, n13);
        final double atan6 = Math.atan2(n14, Math.sqrt(n15 * n15 + n13 * n13));
        final double n16 = -atan5;
        final double n17 = 0.0;
        Math.atan2(n17, n13);
        final double atan7 = Math.atan2(n14, Math.sqrt(n17 * n17 + n13 * n13));
        final double n18 = (atan3 > atan4) ? atan3 : atan4;
        final double n19 = (atan6 < atan7) ? atan6 : atan7;
        this.b = this.n(n18);
        this.c = this.n(n19);
        this.e = this.n(((n10 > n16) ? n10 : n16) * 2.0);
    }
    
    public synchronized double p(final int n, final int n2) {
        double l = this.f;
        final double n3 = this.v - this.n;
        final long n4 = n / 2;
        if (n3 <= 0.0) {
            return 0.0;
        }
        if (l > this.a) {
            l = this.a;
        }
        while (l > this.i) {
            final double n5 = this.n(Math.atan2(n2 / 2, n4 / Math.tan(this.v(l / 2.0)))) * 2.0;
            if (this.d() || this.p()) {
                this.p(l, n5, Math.floor((this.v + this.n) / 2.0 * 10.0) / 10.0, this.p, this.d);
                if (this.b <= this.v && this.c >= this.n && this.e < this.a) {
                    break;
                }
            }
            else if (n5 < n3) {
                break;
            }
            l -= 0.1;
        }
        this.l = l;
        final double n6 = l;
        if (this.i > this.l) {
            this.i = this.l;
        }
        return n6;
    }
    
    public synchronized double d(final double n) {
        double n3;
        if (this.p() || this.d()) {
            this.d = this.a(n);
            double n2;
            for (n2 = Math.floor(this.n * 10.0) / 10.0 + 0.1; n2 < this.v; n2 += 0.1) {
                this.p(n, this.d, n2, this.p, this.d);
                if (this.c > this.n && this.e < this.a && this.b < this.v) {
                    break;
                }
            }
            n3 = n2;
        }
        else {
            n3 = -90.0;
        }
        return n3;
    }
    
    public synchronized int p(final int[] p8, final int v, final int n, final int p9, final int d, final double a, final double n2, final double v2) {
        if (p8 == null) {
            return 1;
        }
        if (v > 100000 || n > 100000 || p9 > 100000 || d > 100000 || v <= 0 || n <= 0 || p9 <= 0 || d <= 0 || a < 0.0 || v2 < n2) {
            return 1;
        }
        this.a = a;
        this.n = n2;
        this.v = v2;
        this.p = p8;
        this.n = n - 1;
        this.a = v - 1;
        this.v = v;
        this.l = 179.0;
        this.i = 1.0;
        this.p = this.l;
        this.p = p9;
        this.d = d;
        return 0;
    }
    
    public final synchronized boolean d() {
        return this.a < 360.0;
    }
    
    public synchronized double p(final double n, final double n2) {
        double n3;
        if (this.d()) {
            n3 = this.n(Math.atan2(Math.tan(this.v(n2 / 2.0)), Math.cos(this.v(n)) - Math.tan(this.v(this.a(n2) / 2.0)) * Math.sin(this.v(Math.abs(n))))) + 0.5;
        }
        else {
            n3 = 0.0;
        }
        return n3;
    }
    
    public final double n(final double n) {
        return n / 3.141592653589793 * 180.0;
    }
    
    public final synchronized boolean a() {
        return this.d() || this.p();
    }
}
