import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.image.MemoryImageSource;

// 
// Decompiled by Procyon v0.5.30
// 

public class b extends r
{
    private MemoryImageSource v;
    int t;
    private an u;
    
    public b() {
        this.t = 0;
        this.u = null;
    }
    
    public void a() {
        super.a();
        if (this.u != null) {
            this.u.a();
        }
        this.u = null;
        this.v = null;
    }
    
    public void a(final Component p3, final boolean q, final ak s) {
        super.q = q;
        super.p = p3;
        super.s = s;
        this.if();
        System.out.println("Fullscreen Render mode 2.");
    }
    
    public void for() {
    }
    
    public void do() {
        if (this.v == null) {
            return;
        }
        this.v.newPixels();
        try {
            super.p.getGraphics().drawImage(super.r, 0, 0, super.p);
        }
        catch (Exception ex) {}
    }
    
    public void if() {
        try {
            if (super.long == super.s.width && super.e == super.s.height) {
                return;
            }
            super.long = super.s.width;
            super.e = super.s.height;
            super.d = super.long;
            super.void = super.e;
            super.c = null;
            this.v = null;
            super.r = null;
            System.gc();
            super.c = new int[super.long * super.e];
            for (int i = 0; i < super.long * super.e; ++i) {
                super.c[i] = super.s.p;
            }
            (this.v = new MemoryImageSource(super.long, super.e, super.c, 0, super.long)).setFullBufferUpdates(true);
            this.v.setAnimated(true);
            super.r = Toolkit.getDefaultToolkit().createImage(this.v);
        }
        catch (Exception ex) {}
    }
}
