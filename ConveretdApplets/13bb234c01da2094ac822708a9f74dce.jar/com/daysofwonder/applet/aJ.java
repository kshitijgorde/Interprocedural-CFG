// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import com.daysofwonder.b.a;
import com.daysofwonder.b.b;

public class aJ
{
    private b a;
    private int b;
    private int c;
    private boolean d;
    private int e;
    private int f;
    private float g;
    private float h;
    private float i;
    private float j;
    private float k;
    private float l;
    private b m;
    private a n;
    private Rectangle o;
    private ap p;
    private boolean q;
    private float r;
    
    public aJ(final ap p4, final b a, final int e, final int f) {
        this.d = true;
        this.r = 1.0f;
        this.p = p4;
        this.a = a;
        this.b = a.a(null);
        this.c = a.b(null);
        this.e = e;
        this.f = f;
        if (this.p.B()) {
            this.m = p4.a(this.b, this.c);
            this.n = this.m.a();
        }
        this.o = new Rectangle(0, 0, this.b, this.c);
    }
    
    public void a() {
        if (this.n != null) {
            this.n.g();
            this.m.c();
        }
        this.m = null;
        this.n = null;
    }
    
    public void a(final z z, final int n) {
        if (n == 0 && this.q && this.m != null) {
            z.a(this.m, (int)this.g - this.e, (int)this.h - this.f, null);
        }
        else {
            if (this.r != 1.0f) {
                z.a(this.a, (int)this.g - this.e, (int)this.h - this.f, this.r, null);
            }
            else {
                z.a(this.a, (int)this.g - this.e, (int)this.h - this.f, null);
            }
            this.q = true;
        }
    }
    
    public void a(final z z) {
        if (this.p.B()) {
            this.p.a(this.n, (int)this.g - this.e, (int)this.h - this.f, this.b, this.c);
        }
    }
    
    public Rectangle b() {
        return this.o;
    }
    
    public void a(final int n, final int n2) {
        this.k = this.g;
        this.l = this.h;
        this.g = n;
        this.h = n2;
        this.o.x = (int)this.g - this.e;
        this.o.y = (int)this.h - this.f;
    }
    
    public void a(final float i, final float j) {
        this.i = i;
        this.j = j;
    }
    
    public void c() {
        this.k = this.g;
        this.l = this.h;
        this.g += this.i;
        this.h += this.j;
        this.o.x = (int)this.g - this.e;
        this.o.y = (int)this.h - this.f;
    }
    
    public void a(final float r) {
        this.r = r;
    }
}
