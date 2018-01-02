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
    private MemoryImageSource F;
    int D;
    private ad E;
    
    public bb() {
        this.D = 0;
        this.E = null;
    }
    
    public void if() {
        super.if();
        if (this.E != null) {
            this.E.if();
        }
        this.E = null;
        this.F = null;
    }
    
    public void a(final Component z, final boolean a, final l c) {
        super.A = a;
        super.z = z;
        super.C = c;
        this.try();
        if (c.E == null) {
            System.out.println("Render mode 2.");
        }
    }
    
    public void byte() {
    }
    
    public void for() {
        if (this.F == null) {
            return;
        }
        this.case();
        this.F.newPixels();
        try {
            if (super.C.E != null && !super.C.E.z) {
                super.z.getGraphics().drawImage(super.B, 0, 0, super.z);
            }
            else {
                super.z.getGraphics().drawImage(super.B, 0, 0, super.z);
            }
        }
        catch (Exception ex) {}
    }
    
    protected void case() {
        if (super.C.E != null && !super.C.E.z) {
            if (!super.C.a && super.C.K != null) {
                final int width = super.C.K.getWidth(null);
                final int height = super.C.K.getHeight(null);
                if (this.E == null) {
                    this.E = new ad();
                    this.E.j = width;
                    this.E.u = height;
                    this.E.q = new int[width * height];
                    this.E.v = true;
                    final PixelGrabber pixelGrabber = new PixelGrabber(super.C.K, 0, 0, width, height, this.E.q, 0, width);
                    try {
                        pixelGrabber.grabPixels();
                    }
                    catch (Exception ex) {}
                }
                int new1 = super.j - width >> 1;
                int do1 = super.u - height >> 1;
                if (super.C.new != -1000 && super.C.do != -1000) {
                    new1 = super.C.new;
                    do1 = super.C.do;
                }
                ap.a(this.E, this, new1, do1, width, height);
            }
            else if (super.C.a) {
                ap.a(super.w, this, super.j - super.w.j >> 1, super.u - super.w.u >> 1, super.w.j, super.w.u);
            }
        }
    }
    
    public void try() {
        try {
            if (super.j == super.C.width && super.u == super.C.height) {
                return;
            }
            super.j = super.C.width;
            super.u = super.C.height;
            super.t = super.j;
            super.k = super.u;
            super.q = null;
            this.F = null;
            super.B = null;
            System.gc();
            super.q = new int[super.j * super.u];
            for (int i = 0; i < super.j * super.u; ++i) {
                super.q[i] = super.C.D;
            }
            (this.F = new MemoryImageSource(super.j, super.u, super.q, 0, super.j)).setFullBufferUpdates(true);
            this.F.setAnimated(true);
            super.B = Toolkit.getDefaultToolkit().createImage(this.F);
        }
        catch (Exception ex) {}
    }
}
