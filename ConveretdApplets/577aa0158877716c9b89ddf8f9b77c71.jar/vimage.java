import java.awt.image.ImageObserver;
import java.awt.image.VolatileImage;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class vimage
{
    ptviewer ptv;
    int w;
    int h;
    
    public vimage(final ptviewer ptv) {
        this.ptv = ptv;
    }
    
    public void setSize(final int w, final int h) {
        this.w = w;
        this.h = h;
    }
    
    void createBackBuffer() {
        if (this.ptv.backBuffer == null) {
            this.ptv.backBuffer = this.ptv.createVolatileImage(this.w, this.h);
        }
    }
    
    void recreateBackBuffer() {
        if (this.ptv.backBuffer != null) {
            this.ptv.backBuffer.flush();
            this.ptv.backBuffer = null;
        }
        this.createBackBuffer();
    }
    
    void drawAcceleratedFrame(final Graphics graphics) {
        this.createBackBuffer();
        do {
            if (((VolatileImage)this.ptv.backBuffer).validate(this.ptv.getGraphicsConfiguration()) == 2) {
                this.recreateBackBuffer();
            }
            this.ptv.renderFrame(this.ptv.backBuffer.getGraphics());
            graphics.drawImage(this.ptv.backBuffer, 0, 0, this.ptv);
        } while (((VolatileImage)this.ptv.backBuffer).contentsLost());
    }
}
