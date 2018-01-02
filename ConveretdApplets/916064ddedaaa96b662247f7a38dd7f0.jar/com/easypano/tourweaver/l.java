// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver;

import com.easypano.tourweaver.f.h;
import com.easypano.tourweaver.f.y;

class l
{
    y a;
    double b;
    double c;
    double d;
    double e;
    
    public l(final h h) {
        this.b = 0.0;
        this.c = 0.0;
        this.d = 0.0;
        l l = this;
        if (!g.v) {
            this.e = 57.29577951308232;
            if (!(h instanceof y)) {
                return;
            }
            l = this;
        }
        l.a = (y)h;
    }
    
    public l(final h h, final double n, final double n2, final double n3) {
        this.b = 0.0;
        this.c = 0.0;
        this.d = 0.0;
        this.e = 57.29577951308232;
        if (!g.v) {
            if (h instanceof y) {
                this.a = (y)h;
            }
            this.a(n, n2, n3);
        }
    }
    
    public void a(final y a) {
        this.a = a;
    }
    
    public y a() {
        return this.a;
    }
    
    public void a(double b, double c, double d) {
        b *= this.e;
        b -= 180.0;
        c *= this.e;
        d *= this.e;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    public double b() {
        return this.b;
    }
    
    public double c() {
        return this.c;
    }
    
    public double d() {
        return this.d;
    }
}
