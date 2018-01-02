// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Component;
import java.awt.Container;

public class dt
{
    public static int a;
    private volatile bu b;
    private volatile e c;
    private volatile Container d;
    private volatile Component e;
    private volatile Component f;
    private volatile int g;
    private volatile int h;
    private volatile int i;
    private volatile int j;
    private ea k;
    private volatile long l;
    private volatile long m;
    
    static {
        dt.a = 16;
    }
    
    public dt() {
        this.b = new bu();
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
        (this.k = new eb(this)).start();
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
        dt dt3 = null;
        Label_0147: {
            Label_0146: {
                dt dt2 = null;
                Label_0139: {
                    if (!q) {
                        Label_0106: {
                            if (e == e2) {
                                final bu bu = (bu)(component = this.b);
                                if (q) {
                                    break Label_0106;
                                }
                                if (bu.isVisible()) {
                                    e c2 = c;
                                    dt dt = null;
                                    Label_0069: {
                                        if (!q) {
                                            if (c == null) {
                                                this.b.setVisible(false);
                                                if (!q) {
                                                    return;
                                                }
                                            }
                                            dt = this;
                                            if (q) {
                                                break Label_0069;
                                            }
                                            c2 = this.c;
                                        }
                                        if (c2 == c) {
                                            return;
                                        }
                                        dt = this;
                                    }
                                    dt.b.a(c);
                                    if (!q) {
                                        return;
                                    }
                                }
                            }
                            this.h = 0;
                            this.i = i;
                            this.j = j;
                            dt2 = this;
                            if (q) {
                                break Label_0139;
                            }
                            component = this.e;
                        }
                    }
                    if (component == e2) {
                        dt3 = this;
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
                    dt2 = this;
                }
                dt2.b.a(c);
            }
            dt3 = this;
        }
        dt3.l = System.currentTimeMillis();
    }
    
    public void a(final Component f) {
        if (f.equals(this.e)) {
            this.b.setVisible(false);
            this.h = 0;
            this.l = System.currentTimeMillis();
            this.f = f;
        }
    }
    
    static void a(final dt dt, final long m) {
        dt.m = m;
    }
    
    static int a(final dt dt) {
        return dt.h;
    }
    
    static long b(final dt dt) {
        return dt.m;
    }
    
    static long c(final dt dt) {
        return dt.l;
    }
    
    static void a(final dt dt, final int h) {
        dt.h = h;
    }
    
    static void b(final dt dt, final long l) {
        dt.l = l;
    }
    
    static int d(final dt dt) {
        return dt.g;
    }
    
    static bu e(final dt dt) {
        return dt.b;
    }
    
    static Component f(final dt dt) {
        return dt.e;
    }
    
    static Container g(final dt dt) {
        return dt.d;
    }
    
    static Component h(final dt dt) {
        return dt.f;
    }
    
    static int i(final dt dt) {
        return dt.i;
    }
    
    static int j(final dt dt) {
        return dt.j;
    }
}
