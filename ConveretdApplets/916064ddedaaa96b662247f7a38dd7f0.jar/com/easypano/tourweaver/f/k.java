// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.f;

public class k extends j
{
    int D;
    int E;
    int F;
    
    public k() {
        this.D = 0;
        this.E = 100;
        this.F = 0;
    }
    
    public long g() {
        return super.i;
    }
    
    public void a() {
        final boolean i = com.easypano.tourweaver.f.r.i;
        k k = this;
        if (!i) {
            if (super.m == null) {
                return;
            }
            k = this;
        }
        final h l = k.l;
        if (!i) {
            if (l == null) {
                return;
            }
            final h j = super.l;
        }
        final s s = (s)l;
        s.a(super.r, super.s, super.t);
        s.b(super.u, super.v, super.w);
        super.l.a(super.m);
        this.a(super.l, 3);
        super.m.k().setEnabled(false);
    }
    
    public void a(final int e) {
        this.E = e;
    }
    
    public void a(final double n) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        super.i = (int)(n * super.h);
        k k = this;
        Label_0058: {
            if (!i) {
                if (super.k) {
                    this.E = 1;
                    this.D = (int)(n * 100.0);
                    if (!i) {
                        break Label_0058;
                    }
                }
                this.E = 100;
                k = this;
            }
            k.D = 0;
        }
        this.b();
    }
    
    public void f() {
        final boolean i = com.easypano.tourweaver.f.r.i;
        int n;
        final boolean b = (n = (super.k ? 1 : 0)) != 0;
        final int f;
        Label_0121: {
            Label_0095: {
                Label_0038: {
                    k k = null;
                    Label_0028: {
                        if (!i) {
                            if (b) {
                                return;
                            }
                            k = this;
                            if (i) {
                                break Label_0028;
                            }
                            n = this.F;
                        }
                        if (n != 0) {
                            break Label_0038;
                        }
                        k = this;
                    }
                    k.o = System.currentTimeMillis();
                    if (!i) {
                        break Label_0095;
                    }
                }
                final long currentTimeMillis = System.currentTimeMillis();
                super.i = currentTimeMillis - super.o;
                f = this.F;
                if (i) {
                    break Label_0121;
                }
                if (f == 1) {
                    final int n3;
                    final long n2 = n3 = lcmp(super.i, 100L);
                    if (i) {
                        break Label_0121;
                    }
                    if (n2 >= 0) {
                        super.i = 30L;
                        super.o = currentTimeMillis - 30L;
                    }
                }
            }
            ++this.F;
            int n3 = (int)(100.0 * super.i / super.h);
        }
        int n4 = f;
        int e2;
        int e;
        int n6;
        final int n5 = n6 = (e = (e2 = n4));
        int e3;
        int n9;
        int n8;
        final int n7 = n8 = (n9 = (e3 = 100));
        if (!i) {
            if (n5 > n7) {
                n4 = 100;
            }
            final int n10;
            n6 = (n10 = (e = (e2 = n4)));
            final int n11;
            n8 = (n11 = (n9 = (e3 = 100)));
        }
        if (!i) {
            if (n5 != n7) {
                super.m.c(n4);
            }
            e = (n6 = (e2 = n4 - this.D));
            n9 = (n8 = (e3 = this.E));
        }
        if (!i) {
            if (n6 < n8) {
                return;
            }
            e2 = (e = this.E);
            e3 = (n9 = 100);
        }
        k j = null;
        Label_0205: {
            if (!i) {
                if (e >= n9) {
                    this.e();
                    if (!i) {
                        return;
                    }
                }
                j = this;
                if (i) {
                    break Label_0205;
                }
                e2 = this.E;
                e3 = 100;
            }
            if (e2 >= e3) {
                return;
            }
            j = this;
        }
        j.c();
    }
    
    public void e() {
        super.e();
        this.F = 0;
        super.i = 0L;
    }
    
    public void d() {
    }
    
    public boolean d(final a a) {
        return a == this.j();
    }
}
