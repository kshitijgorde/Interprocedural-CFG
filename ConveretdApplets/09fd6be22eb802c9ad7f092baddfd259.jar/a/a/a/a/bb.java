// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.image.MemoryImageSource;

public class bb extends i
{
    private MemoryImageSource G;
    int E;
    private ad F;
    
    public bb() {
        this.E = 0;
        this.F = null;
    }
    
    public void if() {
        super.if();
        if (this.F != null) {
            this.F.if();
        }
        this.F = null;
        this.G = null;
    }
    
    public void a(final Component a, final boolean b, final l d) {
        super.B = b;
        super.A = a;
        super.D = d;
        this.new();
        if (d.E == null) {
            System.out.println("Render mode 2.");
        }
    }
    
    public void try() {
    }
    
    public void for() {
        if (this.G == null) {
            return;
        }
        this.G.newPixels();
        try {
            if (super.D.E != null && !super.D.E.z) {
                super.A.getGraphics().drawImage(super.C, 0, 0, super.A);
            }
            else {
                super.A.getGraphics().drawImage(super.C, 0, 0, super.A);
            }
        }
        catch (Exception ex) {}
    }
    
    public void new() {
        try {
            if (super.s == super.D.width && super.r == super.D.height) {
                return;
            }
            super.s = super.D.width;
            super.r = super.D.height;
            super.l = super.s;
            super.p = super.r;
            super.x = null;
            this.G = null;
            super.C = null;
            System.gc();
            super.x = new int[super.s * super.r];
            (this.G = new MemoryImageSource(super.s, super.r, super.x, 0, super.s)).setFullBufferUpdates(true);
            this.G.setAnimated(true);
            super.C = Toolkit.getDefaultToolkit().createImage(this.G);
        }
        catch (Exception ex) {}
    }
}
