// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

class bo extends Panel
{
    private Image a;
    private Graphics b;
    final /* synthetic */ j c;
    
    bo(final j c) {
        this.c = c;
        this.a = null;
        this.b = null;
    }
    
    public void a() {
        final Graphics b = this.b;
        if (!g.q) {
            if (b == null) {
                return;
            }
            final Graphics b2 = this.b;
        }
        b.dispose();
        this.b = null;
        this.a = null;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final boolean q = g.q;
        bo bo = this;
        if (!q) {
            if (this.a == null) {
                final Rectangle bounds = this.getBounds();
                final int width = bounds.width;
                if (q) {
                    return;
                }
                if (width > 0) {
                    final int height = bounds.height;
                    if (q) {
                        return;
                    }
                    if (height > 0) {
                        this.a = this.createImage(bounds.width, bounds.height);
                        this.b = this.a.getGraphics();
                    }
                }
            }
            bo = this;
        }
        bo.paint(this.b);
        graphics.drawImage(this.a, 0, 0, this);
    }
}
