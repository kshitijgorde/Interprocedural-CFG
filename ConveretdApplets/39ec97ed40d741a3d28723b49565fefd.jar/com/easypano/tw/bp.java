// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

public class bp extends Panel implements da
{
    private Image a;
    private Graphics b;
    protected Color c;
    
    public bp() {
        this.a = null;
        this.b = null;
        this.c = null;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        bp bp = this;
        if (!g.q) {
            if (this.a == null) {
                this.a = this.createImage(this.getBounds().width, this.getBounds().height);
                this.b = this.a.getGraphics();
            }
            this.a(this.b);
            this.c(this.b);
            super.paint(this.b);
            bp = this;
        }
        bp.b(this.b);
        graphics.drawImage(this.a, 0, 0, this);
    }
    
    public void a(final Graphics graphics) {
    }
    
    public void b(final Graphics graphics) {
        bp bp = this;
        if (!g.q) {
            if (this.c == null) {
                return;
            }
            this.b.setColor(this.c);
            bp = this;
        }
        final Rectangle bounds = bp.getBounds();
        this.b.drawRect(0, 0, bounds.width - 1, bounds.height - 1);
    }
    
    public void c(final Graphics graphics) {
    }
    
    public void a(final Color c) {
        this.c = c;
    }
    
    public void a(final String s) {
    }
    
    public void a(final String s, final Color color) {
    }
    
    public void a(final String s, final Color color, final Color color2) {
    }
    
    public void a(final e e) {
    }
    
    public void a(final dt dt) {
    }
    
    public void destroyResource() {
        if (this.a != null) {
            this.b.dispose();
            this.b = null;
            this.a = null;
        }
    }
}
