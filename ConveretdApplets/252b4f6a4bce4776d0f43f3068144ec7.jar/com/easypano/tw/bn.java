// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

class bn extends Panel
{
    private Image a;
    private Graphics b;
    final /* synthetic */ k c;
    
    bn(final k c) {
        this.c = c;
        this.a = null;
        this.b = null;
    }
    
    public void a() {
        final Graphics b = this.b;
        if (h.q == 0) {
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
        final int q = h.q;
        bn bn = this;
        if (q == 0) {
            if (this.a == null) {
                final Rectangle bounds = this.getBounds();
                final int width = bounds.width;
                if (q != 0) {
                    return;
                }
                if (width > 0) {
                    final int height = bounds.height;
                    if (q != 0) {
                        return;
                    }
                    if (height > 0) {
                        this.a = this.createImage(bounds.width, bounds.height);
                        this.b = this.a.getGraphics();
                    }
                }
            }
            bn = this;
        }
        bn.paint(this.b);
        graphics.drawImage(this.a, 0, 0, this);
    }
}
