// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.awt.image.ImageObserver;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;

public class jb extends Window
{
    k a;
    Image b;
    Graphics c;
    
    public jb(final Frame frame) {
        super(frame);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean isFocusTraversable() {
        return true;
    }
    
    public void a(final k a) {
        this.a = a;
    }
    
    public k a() {
        return this.a;
    }
    
    public void paint(final Graphics graphics) {
        jb jb = this;
        if (!f.u) {
            if (this.b == null) {
                this.b = this.createImage(this.getBounds().width, this.getBounds().height);
                this.c = this.b.getGraphics();
            }
            this.c.setColor(this.getBackground());
            this.c.fillRect(0, 0, this.getBounds().width, this.getBounds().height);
            jb = this;
        }
        jb.paint(this.c);
        graphics.drawImage(this.b, 0, 0, this);
    }
}
