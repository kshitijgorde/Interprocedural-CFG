// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Image;

public class bb extends a
{
    Image p;
    Dimension q;
    
    public bb(final Image p) {
        this.q = new Dimension();
        this.p = p;
        this.q.width = p.getWidth(this);
        this.q.height = p.getHeight(this);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.p, 0, 0, this);
    }
    
    public Dimension getPreferredSize() {
        return this.q;
    }
}
