// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;

class bt extends bp
{
    private Image d;
    private Graphics e;
    final /* synthetic */ o f;
    
    bt(final o f) {
        this.f = f;
        this.d = null;
        this.e = null;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        bt bt = this;
        if (!g.q) {
            if (this.d == null) {
                final Rectangle bounds = this.getBounds();
                this.d = this.createImage(bounds.width, bounds.height);
                this.e = this.d.getGraphics();
            }
            bt = this;
        }
        bt.paint(this.e);
        graphics.drawImage(this.d, 0, 0, this);
    }
    
    public void destroyResource() {
        try {
            if (this.d != null) {
                this.e.dispose();
                this.e = null;
                this.d = null;
            }
        }
        catch (Exception ex) {}
    }
}
