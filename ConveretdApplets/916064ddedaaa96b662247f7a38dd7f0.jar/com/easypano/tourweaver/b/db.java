// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

class db extends Panel
{
    Image a;
    Graphics b;
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        int n5 = n3;
        final int width = this.getBounds().width;
        Label_0034: {
            Label_0029: {
                if (!f.u) {
                    if (n3 != width) {
                        break Label_0029;
                    }
                    n5 = n4;
                    final int height = this.getBounds().height;
                }
                if (n5 == width) {
                    break Label_0034;
                }
            }
            this.a = null;
        }
        super.setBounds(n, n2, n3, n4);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        db db = this;
        if (!f.u) {
            if (this.a == null) {
                this.a = this.createImage(this.getBounds().width, this.getBounds().height);
                this.b = this.a.getGraphics();
            }
            db = this;
        }
        db.paint(this.b);
        graphics.drawImage(this.a, 0, 0, this);
    }
}
