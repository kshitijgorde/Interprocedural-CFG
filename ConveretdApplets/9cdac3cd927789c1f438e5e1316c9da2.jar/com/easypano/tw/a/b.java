// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw.a;

import java.awt.Dimension;
import java.awt.image.ImageObserver;
import com.easypano.tw.dt;
import java.awt.Graphics;
import java.awt.Image;

public class b implements c
{
    Image a;
    Image b;
    Image c;
    Graphics d;
    int e;
    int f;
    double g;
    
    public b() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
    }
    
    public void a(final Image b, final Image a) {
        this.b = b;
        this.a = a;
    }
    
    public void a(final int[] array) {
    }
    
    public void a(final Image c) {
        this.c = c;
        if (c != null) {
            this.d = c.getGraphics();
            this.a(new Dimension(c.getWidth(dt.d), c.getHeight(dt.d)));
        }
        else {
            this.d = null;
        }
    }
    
    public void a(final Dimension dimension) {
        this.e = dimension.width;
        this.f = dimension.height;
        this.g = this.e / 10.0;
    }
    
    public void a(final int n) {
        if (this.d != null && this.b != null && this.a != null) {
            this.d.drawImage(this.b, 0, 0, dt.d);
            final double n2 = this.g * n / 100.0;
            double n3 = 0.0;
            double n4 = (this.g - n2) / 2.0;
            for (int i = 0; i < 10; ++i) {
                this.d.drawImage(this.a, (int)n4, 0, (int)(n4 + n2), this.f, (int)n3, 0, (int)(n3 + this.g), this.f, dt.d);
                n3 += this.g;
                n4 += this.g;
            }
        }
    }
    
    public int a() {
        return 1;
    }
    
    public void b() {
        if (this.d != null) {
            this.d.dispose();
            this.d = null;
            this.c = null;
        }
        this.b = null;
        this.a = null;
    }
}
