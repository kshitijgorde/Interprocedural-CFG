// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.a;

import java.util.Iterator;
import java.awt.Stroke;
import java.awt.Shape;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;

public final class p implements k
{
    private Map a;
    private Map b;
    private Map c;
    private boolean d;
    private boolean e;
    private Integer f;
    private Set g;
    private short h;
    private k i;
    
    public p(final k i, final short h) {
        this.a = new HashMap(1);
        this.b = new HashMap(1);
        this.c = new HashMap(1);
        this.f = new Integer(0);
        this.g = new HashSet(1);
        this.d = true;
        this.e = true;
        this.i = i;
        this.h = h;
    }
    
    public final void a(final Graphics2D graphics2D) {
        this.i.a(graphics2D);
    }
    
    public final Map h() {
        return this.b;
    }
    
    public final Color a() {
        return this.i.a();
    }
    
    public final Color b() {
        return this.i.b();
    }
    
    public final Color c() {
        return this.i.c();
    }
    
    public final Color d() {
        return this.i.d();
    }
    
    public final short i() {
        return this.h;
    }
    
    public final Shape g_() {
        return this.i.g_();
    }
    
    public final Stroke f() {
        return this.i.f();
    }
    
    public final int g() {
        return this.i.g();
    }
    
    public final void a(final int n) {
        this.e = false;
        if (this.c.containsKey(this.f)) {
            final Map<Object, Integer> map = this.c.get(this.f);
            for (final Long n2 : this.g) {
                if (map.containsKey(n2) && map.get(n2) >= n) {
                    this.e = true;
                    break;
                }
            }
        }
        this.k();
    }
    
    public final boolean j() {
        return this.d && this.e;
    }
    
    public final void b(final int n) {
        this.f = new Integer(n);
        this.k();
    }
    
    public final void a(final Set g) {
        this.d = false;
        if (this.b.containsKey(this.f)) {
            final Map<Object, Integer> map = this.b.get(this.f);
            for (final Long n : g) {
                if (map.containsKey(n) && map.get(n) > 0) {
                    this.d = true;
                    break;
                }
            }
        }
        this.g = g;
        this.k();
    }
    
    public final void a(final Map b) {
        this.b = b;
    }
    
    public final void a(final Color color) {
        this.i.a(color);
    }
    
    public final void b(final Map c) {
        this.c = c;
    }
    
    public final void b(final Color color) {
        this.i.b(color);
    }
    
    public final void a(final Stroke stroke) {
        this.i.a(stroke);
    }
    
    private void k() {
        Stroke stroke = com.eventim.applet.a.h.a;
        Color color = this.c();
        Color color2 = this.d();
        final Integer f = this.f;
        if (this.a.containsKey(f) && (int)this.a.get(f) > 0) {
            if (this.d && this.e) {
                if (this.f > 0) {
                    color2 = q.h;
                }
            }
            else {
                color = com.eventim.applet.a.h.e;
                color2 = com.eventim.applet.a.h.f;
            }
        }
        else {
            stroke = com.eventim.applet.a.h.d;
            color = com.eventim.applet.a.h.b;
            color2 = com.eventim.applet.a.h.c;
        }
        this.a(stroke);
        this.a(color);
        this.b(color2);
    }
    
    public final void c(final Map a) {
        this.a = a;
    }
}
