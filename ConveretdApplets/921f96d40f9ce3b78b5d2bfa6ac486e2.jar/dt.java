import java.awt.Graphics;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class dt implements dk
{
    private int p;
    private int d;
    private URL p;
    private Image p;
    private ImageObserver p;
    
    public dt(final Image p4, final URL p5, final FontMetrics fontMetrics, final ImageObserver imageObserver) {
        this.p = p4;
        this.p = p5;
        this.p(fontMetrics);
        this.p(imageObserver);
    }
    
    public final int p() {
        return this.p;
    }
    
    public final int d() {
        return this.d;
    }
    
    public final void p(final Color color) {
    }
    
    public final void p(final FontMetrics fontMetrics) {
    }
    
    public final void p(final ImageObserver p) {
        this.p = p;
        this.p = this.p.getWidth(p);
        this.d = this.p.getHeight(p);
    }
    
    public final void p(final Graphics graphics, final int n, final int n2) {
        graphics.drawImage(this.p, n, n2, this.p);
    }
    
    public final String toString() {
        return "img" + this.p + "x" + this.d;
    }
}
