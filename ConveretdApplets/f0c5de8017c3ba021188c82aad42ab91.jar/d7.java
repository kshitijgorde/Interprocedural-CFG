// 
// Decompiled by Procyon v0.5.30
// 

public class d7
{
    public d8[] a;
    public String b;
    public String c;
    public String d;
    public String e;
    public int f;
    public d0 g;
    public d3 h;
    public d5 i;
    public long j;
    private long k;
    private int l;
    
    public d7() {
    }
    
    public d7(final String c, final String b, final String e, final int f, final d0 g, final d3 h, final d5 i, final dy dy) {
        this.c = c;
        this.b = b;
        this.d = dy.a(c, b);
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.i = i;
        this.a = new d8[dy.i.length];
        for (int j = 0; j < dy.i.length; ++j) {
            this.a[j] = new d8(dy.i[j], dy);
        }
        this.l = dy.i.length;
        this.k = dy.g;
        this.a();
    }
    
    public void a() {
        this.i.b();
        for (int i = 0; i < this.l; ++i) {
            this.a[i].a();
        }
        this.j = 0L;
    }
    
    public void b() {
        final long a = this.i.a();
        this.j += a;
        for (int i = 0; i < this.l; ++i) {
            final int a2 = this.a[i].a(a);
            if (a2 > 0 && a2 % (this.k - 1L) == 0L) {
                this.c(i);
                this.d(i);
            }
        }
    }
    
    public double a(final int n, int n2) {
        if (n2 >= this.k) {
            n2 = (int)this.k - n2 - 1;
        }
        if (n2 >= 0 || n2 == -5) {
            return this.h.a(this, n, n2);
        }
        switch (n2) {
            case -1: {
                return this.a(n);
            }
            case -2: {
                return this.b(n);
            }
            case -3: {
                return this.c(n);
            }
            case -4: {
                return this.d(n);
            }
            default: {
                return -1.0;
            }
        }
    }
    
    public final double a(final int n) {
        double n2 = 0.0;
        for (int n3 = 0; n3 < this.k; ++n3) {
            final double a = this.a(n, n3);
            if (a > n2) {
                n2 = a;
            }
        }
        return n2;
    }
    
    public final double b(final int n) {
        double n2 = Double.MAX_VALUE;
        for (int c = this.a[n].c, n3 = 1; n3 < this.k && n3 < c; ++n3) {
            final double a = this.a(n, n3);
            if (a < n2) {
                n2 = a;
            }
        }
        if (n2 != Double.MAX_VALUE) {
            return n2;
        }
        return 0.0;
    }
    
    public final double c(final int n) {
        final double a = this.a(n);
        if (a > this.a[n].d) {
            this.a[n].d = a;
        }
        return this.a[n].d;
    }
    
    public final double d(final int n) {
        final double b = this.b(n);
        if (b > 0.0 && b < this.a[n].e) {
            this.a[n].e = b;
        }
        if (this.a[n].e != Double.MAX_VALUE) {
            return this.a[n].e;
        }
        return 0.0;
    }
    
    public long b(final int n, final int n2) {
        if (n2 >= 0 && n2 < this.k) {
            final d8 d8 = this.a[n];
            return d8.f[(int)(d8.g - n2 + this.k) % (int)this.k];
        }
        return this.j;
    }
    
    public String toString() {
        return "StatsItem " + this.d + "@" + this.f;
    }
}
