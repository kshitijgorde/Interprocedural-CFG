// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;

public class bq extends bp
{
    Image f;
    
    public bq() {
        this.f = null;
    }
    
    public void c(final Graphics graphics) {
        if (this.f != null) {
            graphics.drawImage(this.f, 15, 30, this);
        }
    }
    
    public void a(final Image f) {
        this.f = f;
    }
    
    public Dimension getPreferredSize() {
        final Image f = this.f;
        if (h.q == 0) {
            if (f == null) {
                return new Dimension(30, 40);
            }
            ds.a(this.f);
            final Image f2 = this.f;
        }
        return new Dimension(f.getWidth(this) + 30, this.f.getHeight(this) + 40);
    }
}
