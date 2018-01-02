// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.e;

import com.easypano.tourweaver.n;

public class a implements b
{
    double a;
    double b;
    double c;
    n d;
    String e;
    int f;
    public static boolean g;
    
    public a() {
        final boolean g = com.easypano.tourweaver.e.a.g;
        this.a = 0.0;
        this.b = 0.0;
        this.c = 0.0;
        this.d = null;
        this.e = null;
        this.f = 0;
        if (com.easypano.tourweaver.b.a.o != 0) {
            com.easypano.tourweaver.e.a.g = !g;
        }
    }
    
    public a(final n d, final double a, final double b, final double c, final String e, final int f) {
        final boolean g = com.easypano.tourweaver.e.a.g;
        this.a = 0.0;
        this.b = 0.0;
        this.c = 0.0;
        this.d = null;
        this.e = null;
        this.f = 0;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        if (g) {
            int o = com.easypano.tourweaver.b.a.o;
            com.easypano.tourweaver.b.a.o = ++o;
        }
    }
    
    public void a() {
        final boolean g = com.easypano.tourweaver.e.a.g;
        a a = this;
        if (!g) {
            if (this.f == 0) {
                this.d.autoPanScene((int)this.a, (int)this.b, (int)this.c);
                if (!g) {
                    return;
                }
            }
            a = this;
        }
        a.d.autoPanScene(this.a, this.b, this.c);
    }
}
