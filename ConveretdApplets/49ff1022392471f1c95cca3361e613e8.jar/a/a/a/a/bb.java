// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.image.MemoryImageSource;

public class bb extends i
{
    private MemoryImageSource C;
    int A;
    private ad B;
    
    public bb() {
        this.A = 0;
        this.B = null;
    }
    
    public void a(final Component w, final boolean x, final l z) {
        super.x = x;
        super.w = w;
        super.z = z;
        this.try();
        if (z.v == null) {
            System.out.println("Render mode 2.");
        }
    }
    
    public void byte() {
    }
    
    public void for() {
        if (this.C == null) {
            return;
        }
        this.case();
        this.C.newPixels();
        try {
            if (super.z.v != null && !super.z.v.goto) {
                super.w.getGraphics().drawImage(super.y, 0, 0, super.w);
            }
            else {
                super.w.getGraphics().drawImage(super.y, 0, 0, super.w);
            }
        }
        catch (Exception ex) {}
    }
    
    protected void case() {
        if (super.z.v != null && !super.z.v.goto) {
            if (!super.z.a && super.z.D != null) {
                final int width = super.z.D.getWidth(null);
                final int height = super.z.D.getHeight(null);
                if (this.B == null) {
                    this.B = new ad();
                    this.B.i = width;
                    this.B.t = height;
                    this.B.p = new int[width * height];
                    this.B.u = true;
                    final PixelGrabber pixelGrabber = new PixelGrabber(super.z.D, 0, 0, width, height, this.B.p, 0, width);
                    try {
                        pixelGrabber.grabPixels();
                    }
                    catch (Exception ex) {}
                }
                int new1 = super.i - width >> 1;
                int do1 = super.t - height >> 1;
                if (super.z.new != -1000 && super.z.do != -1000) {
                    new1 = super.z.new;
                    do1 = super.z.do;
                }
                ap.a(this.B, this, new1, do1, width, height);
            }
            else if (super.z.a) {
                ap.a(super.v, this, super.i - super.v.i >> 1, super.t - super.v.t >> 1, super.v.i, super.v.t);
            }
        }
    }
    
    public void try() {
        try {
            if (super.i == super.z.width && super.t == super.z.height) {
                return;
            }
            super.i = super.z.width;
            super.t = super.z.height;
            super.s = super.i;
            super.j = super.t;
            super.p = null;
            this.C = null;
            super.y = null;
            System.gc();
            super.p = new int[super.i * super.t];
            for (int i = 0; i < super.i * super.t; ++i) {
                super.p[i] = super.z.t;
            }
            (this.C = new MemoryImageSource(super.i, super.t, super.p, 0, super.i)).setFullBufferUpdates(true);
            this.C.setAnimated(true);
            super.y = Toolkit.getDefaultToolkit().createImage(this.C);
        }
        catch (Exception ex) {}
    }
}
