// 
// Decompiled by Procyon v0.5.30
// 

package ino360both;

import java.awt.image.ImageObserver;
import java.awt.image.VolatileImage;
import java.awt.Graphics;

public class vimage
{
    int h;
    ptviewer ptv;
    int w;
    
    public vimage(final ptviewer ptv) {
        this.ptv = ptv;
    }
    
    void createBackBuffer() {
        if (this.ptv.backBuffer == null) {
            this.ptv.backBuffer = this.ptv.createVolatileImage(this.w, this.h);
        }
    }
    
    void drawAcceleratedFrame(final Graphics g) {
        this.createBackBuffer();
        do {
            final int valCode = ((VolatileImage)this.ptv.backBuffer).validate(this.ptv.getGraphicsConfiguration());
            if (valCode == 2) {
                this.recreateBackBuffer();
            }
            final Graphics gBB = this.ptv.backBuffer.getGraphics();
            this.ptv.renderFrame(gBB);
            g.drawImage(this.ptv.backBuffer, 0, 0, this.ptv);
        } while (((VolatileImage)this.ptv.backBuffer).contentsLost());
    }
    
    void recreateBackBuffer() {
        if (this.ptv.backBuffer != null) {
            this.ptv.backBuffer.flush();
            this.ptv.backBuffer = null;
        }
        this.createBackBuffer();
    }
    
    public void setSize(final int w, final int h) {
        this.w = w;
        this.h = h;
    }
}
