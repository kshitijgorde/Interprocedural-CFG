// 
// Decompiled by Procyon v0.5.30
// 

class af
{
    int a;
    public int b;
    int c;
    int d;
    String e;
    int f;
    ar[] g;
    af h;
    af i;
    an[] m;
    an[] n;
    
    af(final int a, final int b, final int c, final int d, final ac ac, final ag ag, final int n, final String e, final int f, final ar[] g) {
        this.m = new an[3];
        this.n = new an[3];
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.a(a, ac, ag, n);
    }
    
    void a(final int n, final ac ac, final ag ag, final int n2) {
        final boolean l = c.l;
        if (ac != null) {
            final an f = new an(ac, n);
            Label_0065: {
                if (this.m[0] == null) {
                    this.m[0] = f;
                    if (!l) {
                        break Label_0065;
                    }
                }
                this.n[0].b = n - 1;
                this.n[0].f = f;
            }
            this.n[0] = f;
            f.b = this.b;
        }
        if (ag != null) {
            final an f2 = new an(ag, n);
            if (this.m[1] == null && n != this.a) {
                this.a(this.a, null, new ag(), -1);
            }
            Label_0176: {
                if (this.m[1] != null) {
                    this.n[1].b = n - 1;
                    this.n[1].f = f2;
                    if (!l) {
                        break Label_0176;
                    }
                }
                this.m[1] = f2;
            }
            this.n[1] = f2;
            f2.b = this.b;
        }
        if (n2 != -1) {
            final an f3 = new an(n2, n);
            Label_0256: {
                if (this.m[2] == null) {
                    this.m[2] = f3;
                    if (!l) {
                        break Label_0256;
                    }
                }
                this.n[2].b = n - 1;
                this.n[2].f = f3;
            }
            this.n[2] = f3;
            f3.b = this.b;
        }
    }
    
    an a(final int n, final an an, final int n2, final int n3) {
        final boolean l = c.l;
        final boolean b = n3 > n2;
        an an2;
        if (an == null || !b) {
            an2 = this.m[n];
        }
        else {
            an2 = an.f;
        }
        while (an2 != null) {
            final an an3 = an2;
            if (l || an3.a > n3) {
                return an3;
            }
            if ((!b || (an2.b >= n3 && l)) && an2.b >= n3 && an2.b < n2) {}
            an2 = an2.f;
        }
        goto Label_0127;
    }
    
    int a(final int n) {
        return this.f;
    }
}
