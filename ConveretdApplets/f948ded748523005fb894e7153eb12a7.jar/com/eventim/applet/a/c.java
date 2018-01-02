// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.a;

import java.awt.Graphics2D;
import java.util.HashSet;
import java.awt.Stroke;
import java.awt.Shape;
import java.awt.Color;
import java.util.Set;
import java.util.Hashtable;

public final class c extends a implements b
{
    private Hashtable f;
    private Set g;
    private String h;
    private boolean i;
    private boolean j;
    private int k;
    private int l;
    private long m;
    private String n;
    private short o;
    private long p;
    private k[] q;
    private static String r;
    private long s;
    
    static {
        c.r = "Standing";
    }
    
    c(final long p10, final long s, final long m, final String n, final String h, final Hashtable f, final byte b, final k[] q, final Color color, final Color color2) {
        super(null, null, b, color, color2);
        this.g = new HashSet();
        this.l = 10;
        this.j = true;
        this.i = true;
        this.p = p10;
        this.s = s;
        this.m = m;
        this.n = n;
        this.h = h;
        this.f = f;
        this.q = q;
        if (this.q != null) {
            for (int i = 0; i < q.length; ++i) {
                final k k;
                (k = q[i]).a(color);
                k.b(color2);
            }
        }
    }
    
    public final void a(final Graphics2D graphics2D) {
        for (int i = 0; i < this.q.length; ++i) {
            this.q[i].a(graphics2D);
        }
    }
    
    public final boolean equals(final Object o) {
        return this == o || (o != null && this.getClass() == o.getClass() && this.p == ((c)o).p);
    }
    
    public final Hashtable i() {
        return this.f;
    }
    
    public final String a_() {
        return this.h;
    }
    
    public final String b_() {
        return c.r;
    }
    
    public final String c_() {
        return this.n;
    }
    
    public final long j() {
        return this.m;
    }
    
    public final String d_() {
        return this.n;
    }
    
    public final short e() {
        return this.o;
    }
    
    public final long k() {
        return this.p;
    }
    
    public final k[] l() {
        return this.q;
    }
    
    public final long e_() {
        return this.s;
    }
    
    public final void a(final int n) {
        this.i = (n <= this.l);
        this.n();
    }
    
    public final int hashCode() {
        return 31 + (int)(this.p ^ this.p >>> 32);
    }
    
    public final boolean b(final int n) {
        final Integer n2 = new Integer(n);
        return this.f.containsKey(n2) && (int)this.f.get(n2) > 0;
    }
    
    public final boolean f_() {
        return false;
    }
    
    public final boolean m() {
        return this.j && this.i;
    }
    
    public final boolean h() {
        return true;
    }
    
    public final void c(final int k) {
        this.k = k;
        this.n();
    }
    
    public final void a(final Set set) {
        this.j = set.contains(new Long(this.s));
        this.n();
    }
    
    public final void b(final Set g) {
        this.g = g;
    }
    
    public final void a(final Color color) {
        super.a(color);
        if (this.q != null) {
            for (int i = 0; i < this.q.length; ++i) {
                this.q[i].a(color);
            }
        }
    }
    
    public final void b(final Color color) {
        super.b(color);
        if (this.q != null) {
            for (int i = 0; i < this.q.length; ++i) {
                this.q[i].b(color);
            }
        }
    }
    
    public final void a(final short o) {
        this.o = o;
    }
    
    public static void a(final String r) {
        c.r = r;
    }
    
    public final void a(final Stroke stroke) {
        super.a(stroke);
        if (this.q != null) {
            for (int i = 0; i < this.q.length; ++i) {
                this.q[i].a(stroke);
            }
        }
    }
    
    private void n() {
        Stroke stroke = com.eventim.applet.a.q.l;
        Color color = this.c();
        Color color2 = this.d();
        if (this.b(this.k)) {
            if (this.j && this.i) {
                if (this.k > 0) {
                    color2 = com.eventim.applet.a.q.h;
                }
            }
            else {
                color = com.eventim.applet.a.n.a(color);
                if (this.k > 0) {
                    color2 = com.eventim.applet.a.q.h;
                }
                color2 = com.eventim.applet.a.n.a(color2);
            }
        }
        else {
            stroke = com.eventim.applet.a.q.m;
            color = com.eventim.applet.a.q.j;
            if (this.g.contains(new Integer(this.k)) && this.k > 0) {
                color2 = com.eventim.applet.a.q.h;
            }
            else {
                color2 = com.eventim.applet.a.q.k;
            }
        }
        this.a(stroke);
        this.a(color);
        this.b(color2);
    }
}
