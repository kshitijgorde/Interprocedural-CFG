// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.util.Vector;

public class ce
{
    public static final int a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public static final int e = 10;
    public static final int f = 11;
    public static final int g = 12;
    public String h;
    public int i;
    public boolean j;
    private Vector k;
    private int l;
    private long m;
    private long n;
    private boolean o;
    private cd p;
    private cd q;
    private int r;
    private int s;
    private long t;
    private cd u;
    private int v;
    private long w;
    int x;
    
    public ce() {
        this.h = "";
        this.i = -1;
        this.j = true;
        this.k = new Vector(5, 5);
        this.l = 0;
        this.m = -1L;
        this.n = 0L;
        this.o = false;
        this.p = new cd();
        this.q = new cd();
        this.s = -1;
        this.t = -1L;
        this.u = null;
        this.v = -1;
        this.w = -1L;
        this.x = 0;
    }
    
    public int a() {
        return this.k.size();
    }
    
    public void a(final cd cd) {
        this.k.addElement(cd);
    }
    
    public cd a(int v) {
        final boolean q = com.easypano.tw.g.q;
        int n2;
        final int n = n2 = v;
        if (!q) {
            if (n < 0) {
                v = 0;
            }
            final int n3;
            n2 = (n3 = v);
        }
        if (!q) {
            if (n >= this.k.size()) {
                v = this.k.size() - 1;
            }
            n2 = v;
        }
        if (n2 >= 0) {
            this.v = v;
            this.q.a(this.k.elementAt(v));
            this.w = this.q.e;
            return this.q;
        }
        this.v = -1;
        this.w = -1L;
        return null;
    }
    
    public cd b(final int n) {
        this.u = this.a(n);
        this.e();
        return this.u;
    }
    
    public void a(final long n) {
        long currentTimeMillis = n;
        if (!com.easypano.tw.g.q) {
            if (n < 0L) {
                currentTimeMillis = System.currentTimeMillis();
            }
            else {
                currentTimeMillis = n;
            }
        }
        this.m = currentTimeMillis;
        this.n = 0L;
    }
    
    public void b() {
        ce ce = this;
        if (!com.easypano.tw.g.q) {
            if (this.o) {
                return;
            }
            this.o = true;
            ce = this;
        }
        ce.n = System.currentTimeMillis();
    }
    
    public void c() {
        ce ce = this;
        ce ce2 = this;
        if (!com.easypano.tw.g.q) {
            if (!this.o) {
                return;
            }
            this.o = false;
            ce = this;
            ce2 = this;
        }
        ce.m = ce2.m + (System.currentTimeMillis() - this.n);
    }
    
    public long d() {
        return this.t;
    }
    
    private void e() {
        this.t = this.w;
        this.s = this.v;
    }
    
    public void f() {
        this.m = -1L;
        this.n = 0L;
        this.o = false;
        this.t = -1L;
        this.w = -1L;
    }
    
    public void b(final long n) {
        this.m -= n;
    }
    
    public cd c(long w) {
        final boolean q = com.easypano.tw.g.q;
        final int size = this.k.size();
        long n3;
        long n2;
        final int n = (int)(n2 = (n3 = size));
        if (!q) {
            if (n == 0) {
                this.r = 3;
                this.v = -1;
                return null;
            }
            w -= this.m;
            this.w = w;
            final long n4;
            n2 = (n4 = (n3 = lcmp(w, 0L)));
        }
        if (!q) {
            if (n < 0) {
                this.r = 3;
                this.v = -1;
                return null;
            }
            n3 = (n2 = lcmp(w, (long)this.k.elementAt(size - 1).e));
        }
        if (!q) {
            if (n2 >= 0) {
                this.p.a((cd)this.k.elementAt(size - 1));
                this.r = 4;
                this.v = size - 1;
                if (!q) {
                    return this.p;
                }
            }
            n3 = 0;
        }
        int v = (int)n3;
        long v2 = size - 1;
        long n6;
        final int n5 = (int)(n6 = v2);
        if (!q) {
            if (n5 == 0) {
                this.p.a((cd)this.k.elementAt(v));
                this.r = 4;
                this.v = v;
                if (!q) {
                    return this.p;
                }
            }
            final int n7;
            n6 = (n7 = (int)v2);
        }
        final int n8 = 1;
        if (!q) {
            if (n5 == n8) {
                final cd cd = this.k.elementAt(v);
                final cd cd2 = this.k.elementAt((int)v2);
                long d;
                final int n9 = (int)(d = cd.d);
                if (!q) {
                    if (n9 == cd2.d) {
                        final float n10 = (w - cd.e) / (cd2.e - cd.e);
                        this.p.d = cd.d;
                        this.p.e = (int)w;
                        this.p.f = this.a(cd.f, cd2.f, n10, cd.i);
                        this.p.g = cd.g * (1.0f - n10) + cd2.g * n10;
                        this.p.h = cd.h * (1.0f - n10) + cd2.h * n10;
                        this.r = 1;
                        this.v = v;
                        if (!q) {
                            return this.p;
                        }
                    }
                    final int n11;
                    d = (n11 = lcmp(w, (long)cd.e));
                }
                if (!q) {
                    if (n9 == 0) {
                        this.p.a(cd);
                        this.r = 1;
                        this.v = v;
                        if (!q) {
                            return this.p;
                        }
                    }
                    d = lcmp(w, (long)cd2.e);
                }
                if (d == 0) {
                    this.p.a(cd2);
                    this.r = 1;
                    this.v = (int)v2;
                    if (!q) {
                        return this.p;
                    }
                }
                this.p.a(cd2);
                this.r = 2;
                this.v = (int)v2;
                if (!q) {
                    return this.p;
                }
            }
            n6 = v + v2;
        }
        int n12 = (int)(n6 / n8);
        cd cd3;
        cd cd4;
        int n14;
        int n13 = 0;
        int d2;
        int n15 = 0;
        while (true) {
            long n17 = 0L;
            Label_0593: {
                Label_0587: {
                    if (v2 - v < 2) {
                        cd3 = this.k.elementAt(v);
                        cd4 = this.k.elementAt((int)v2);
                        n13 = (n14 = v);
                        n15 = (d2 = (int)v2);
                        if (!q) {
                            break;
                        }
                    }
                    else {
                        final cd cd5 = this.k.elementAt(n12);
                        final long n16 = n17 = lcmp(w, (long)cd5.e);
                        if (!q) {
                            if (n16 > 0) {
                                v = n12;
                                n12 = (int)((v + v2) / 2);
                                if (!q) {
                                    continue;
                                }
                            }
                            final int n18;
                            n17 = (n18 = lcmp(w, (long)cd5.e));
                        }
                        if (q) {
                            break Label_0593;
                        }
                        if (n16 >= 0) {
                            break Label_0587;
                        }
                        v2 = n12;
                    }
                    n12 = n13 / n15;
                    if (!q) {
                        continue;
                    }
                }
                v = n12;
                n17 = n12;
            }
            v2 = n17;
        }
        long d3 = 0L;
        int n19 = 0;
        Label_0845: {
            if (!q) {
                if (n13 == n15) {
                    this.p.a(cd4);
                    this.r = 1;
                    this.v = (int)v2;
                    if (!q) {
                        return this.p;
                    }
                }
                n19 = (n14 = (int)(d3 = cd3.d));
                if (q) {
                    break Label_0845;
                }
                d2 = cd4.d;
            }
            if (n14 == d2) {
                final float n20 = (w - cd3.e) / (cd4.e - cd3.e);
                this.p.d = cd3.d;
                this.p.e = (int)w;
                this.p.f = this.a(cd3.f, cd4.f, n20, cd3.i);
                this.p.g = cd3.g * (1.0f - n20) + cd4.g * n20;
                this.p.h = cd3.h * (1.0f - n20) + cd4.h * n20;
                this.r = 1;
                this.v = v;
                if (!q) {
                    return this.p;
                }
            }
            d3 = (n19 = lcmp(w, (long)cd3.e));
        }
        if (!q) {
            if (n19 == 0) {
                this.p.a(cd3);
                this.r = 1;
                this.v = v;
                if (!q) {
                    return this.p;
                }
            }
            d3 = lcmp(w, (long)cd4.e);
        }
        if (d3 == 0) {
            this.p.a(cd4);
            this.r = 1;
            this.v = (int)v2;
            if (!q) {
                return this.p;
            }
        }
        this.p.a(cd4);
        this.r = 2;
        this.v = (int)v2;
        return this.p;
    }
    
    public cd d(final long n) {
        this.u = this.c(n);
        this.e();
        return this.u;
    }
    
    public int g() {
        return this.r;
    }
    
    public double a(double n, double n2, final float n3, final int n4) {
        final boolean q = com.easypano.tw.g.q;
        double n5 = n4;
        final double n6;
        final double n7;
        Label_0078: {
            Label_0058: {
                if (!q) {
                    switch (n4) {
                        case 1: {
                            n6 = n2;
                            n7 = n;
                            if (!q) {
                                n5 = dcmpg(n6, n7);
                                break;
                            }
                            return n6 + n7;
                        }
                        case 2: {
                            break Label_0058;
                        }
                    }
                }
                if (n5 > 0) {
                    break Label_0078;
                }
                n2 += 6.283185307179586;
                if (!q) {
                    break Label_0078;
                }
            }
            final double n8 = n2;
            final double n9 = n;
            if (q) {
                return n6 + n7;
            }
            if (n8 >= n9) {
                n += 6.283185307179586;
            }
        }
        final double n10 = n * (1.0f - n3);
        final double n11 = n2 * n3;
        return n6 + n7;
    }
    
    public int h() {
        return this.s;
    }
}
