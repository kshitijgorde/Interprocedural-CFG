// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Component;
import java.awt.Container;

public class du
{
    public static int a;
    private volatile bv b;
    private volatile e c;
    private volatile Container d;
    private volatile Component e;
    private volatile Component f;
    private volatile int g;
    private volatile int h;
    private volatile int i;
    private volatile int j;
    private ed k;
    private volatile long l;
    private volatile long m;
    
    static {
        du.a = 16;
    }
    
    public du() {
        this.b = new bv();
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = 500;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = null;
        this.l = 0L;
        this.m = 0L;
    }
    
    public void a() {
        (this.k = new ee(this)).start();
    }
    
    public void stopWatch() {
        try {
            if (this.k != null) {
                this.k.stopWatch();
                this.k = null;
            }
            if (this.b != null) {
                this.b.destroyResource();
                this.b = null;
            }
            this.d = null;
            this.e = null;
            this.f = null;
        }
        catch (Exception ex) {}
    }
    
    public void a(final Container d) {
        final Container d2 = this.d;
        if (!com.easypano.tw.g.q) {
            if (d2 != null) {
                this.d.remove(this.b);
            }
            d.add(this.b, 0);
        }
        this.d = d;
    }
    
    public void a(final Component e, final e c, final int i, final int j) {
        final boolean q = com.easypano.tw.g.q;
        Component component = e;
        final Component e2 = this.e;
        du du3 = null;
        Label_0147: {
            Label_0146: {
                du du2 = null;
                Label_0139: {
                    if (!q) {
                        Label_0106: {
                            if (e == e2) {
                                final bv bv = (bv)(component = this.b);
                                if (q) {
                                    break Label_0106;
                                }
                                if (bv.isVisible()) {
                                    e c2 = c;
                                    du du = null;
                                    Label_0069: {
                                        if (!q) {
                                            if (c == null) {
                                                this.b.setVisible(false);
                                                if (!q) {
                                                    return;
                                                }
                                            }
                                            du = this;
                                            if (q) {
                                                break Label_0069;
                                            }
                                            c2 = this.c;
                                        }
                                        if (c2 == c) {
                                            return;
                                        }
                                        du = this;
                                    }
                                    du.b.a(c);
                                    if (!q) {
                                        return;
                                    }
                                }
                            }
                            this.h = 0;
                            this.i = i;
                            this.j = j;
                            du2 = this;
                            if (q) {
                                break Label_0139;
                            }
                            component = this.e;
                        }
                    }
                    if (component == e2) {
                        du3 = this;
                        if (q) {
                            break Label_0147;
                        }
                        if (this.c == c) {
                            break Label_0146;
                        }
                    }
                    this.e = e;
                    this.f = null;
                    this.c = c;
                    du2 = this;
                }
                du2.b.a(c);
            }
            du3 = this;
        }
        du3.l = System.currentTimeMillis();
    }
    
    public void a(final Component f) {
        if (f.equals(this.e)) {
            this.b.setVisible(false);
            this.h = 0;
            this.l = System.currentTimeMillis();
            this.f = f;
        }
    }
    
    static void a(final du du, final long m) {
        du.m = m;
    }
    
    static int a(final du du) {
        return du.h;
    }
    
    static long b(final du du) {
        return du.m;
    }
    
    static long c(final du du) {
        return du.l;
    }
    
    static void a(final du du, final int h) {
        du.h = h;
    }
    
    static void b(final du du, final long l) {
        du.l = l;
    }
    
    static int d(final du du) {
        return du.g;
    }
    
    static bv e(final du du) {
        return du.b;
    }
    
    static Component f(final du du) {
        return du.e;
    }
    
    static Container g(final du du) {
        return du.d;
    }
    
    static Component h(final du du) {
        return du.f;
    }
    
    static int i(final du du) {
        return du.i;
    }
    
    static int j(final du du) {
        return du.j;
    }
}
