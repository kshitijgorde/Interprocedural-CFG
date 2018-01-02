// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.c;

class a
{
    int a;
    double b;
    boolean c;
    double d;
    double e;
    double f;
    double g;
    double h;
    int i;
    int j;
    double k;
    double l;
    double m;
    int n;
    private final g o;
    
    a(final g o) {
        this.o = o;
        this.m = com.easypano.tourweaver.c.g.a(this.o) * 0.5;
        this.n = com.easypano.tourweaver.c.g.a(this.o) - 1;
        this.a(1.0, -1.0, 0.0, 565.0);
    }
    
    a(final g o, final int a) {
        this.o = o;
        this.m = com.easypano.tourweaver.c.g.a(this.o) * 0.5;
        this.n = com.easypano.tourweaver.c.g.a(this.o) - 1;
        this.a = a;
        this.a(1.0, -1.0, 0.0, 565.0);
    }
    
    void a(final int a) {
        this.a = a;
    }
    
    void a(final double n, final double f, final double g, final double h) {
        final boolean h2 = f.h;
        a a = this;
        Label_0050: {
            if (!h2) {
                this.c = false;
                if (n == g) {
                    this.c = true;
                    this.d = n;
                    if (!h2) {
                        break Label_0050;
                    }
                }
                a = this;
            }
            a.b = (h - f) / (g - n);
        }
        this.e = n;
        this.f = f;
        this.g = g;
        this.h = h;
    }
    
    void a(final double n, final double n2) {
        if (n > n2) {
            this.k = n;
            this.l = n2;
            if (!com.easypano.tourweaver.c.f.h) {
                return;
            }
        }
        this.k = n2;
        this.l = n;
    }
    
    void a(final int i, final int j) {
        this.i = i;
        this.j = j;
    }
    
    int b(final int n) {
        int i = n;
        if (!com.easypano.tourweaver.c.f.h) {
            if (n == this.i) {
                return this.j;
            }
            i = this.i;
        }
        return i;
    }
    
    double c(final int n) {
        final boolean h = com.easypano.tourweaver.c.f.h;
        double c;
        final boolean b = (c = (this.c ? 1 : 0)) != 0.0;
        final double b2;
        if (!h) {
            if (b) {
                final double n2 = n;
                if (!h) {
                    if (n2 <= this.k) {
                        final double n3 = n;
                        if (!h) {
                            if (n3 >= this.l) {
                                double n5;
                                double n4 = n5 = this.d + this.m;
                                if (!h) {
                                    if (n4 > this.n) {
                                        n4 = this.n;
                                    }
                                    n5 = n4;
                                }
                                return n5;
                            }
                        }
                    }
                }
                return n2;
            }
            b2 = this.b;
            if (h) {
                return b2;
            }
            c = dcmpl(b2, 0.0);
        }
        if (c != 0) {
            final double n6 = n;
            if (!h) {
                if (n6 <= this.k) {
                    final double n7 = n;
                    if (!h) {
                        if (n7 >= this.l) {
                            double n9;
                            double n8 = n9 = this.g - (this.h - n) / this.b + this.m;
                            if (!h) {
                                if (n8 > this.n) {
                                    n8 = this.n;
                                }
                                n9 = n8;
                            }
                            return n9;
                        }
                    }
                }
            }
        }
        return b2;
    }
}
