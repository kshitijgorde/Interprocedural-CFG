import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Color;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class ds implements dk
{
    public static boolean p;
    private int p;
    private int d;
    private int a;
    private URL p;
    private String p;
    private Color p;
    private Color d;
    private FontMetrics p;
    
    public ds(final String p6, final Color p7, final Color d, final URL p8, final FontMetrics fontMetrics, final ImageObserver imageObserver) {
        this.p = p6;
        this.p = p7;
        this.d = d;
        this.p = p8;
        this.p(fontMetrics);
        this.p(imageObserver);
    }
    
    public final int p() {
        return this.p;
    }
    
    public final int d() {
        return this.d;
    }
    
    public final void p(final Color p) {
        this.p = p;
    }
    
    public final void p(final FontMetrics p) {
        this.p = p;
        if (p == null) {
            return;
        }
        this.p = p.stringWidth(this.p);
        this.d = p.getHeight() + 1;
        this.a = p.getLeading() + p.getAscent() + 2;
    }
    
    public final void p(final ImageObserver imageObserver) {
    }
    
    public final void p(final Graphics graphics, final int n, final int n2) {
        if (ds.p && this.d != null) {
            graphics.setColor(this.d);
            graphics.fillRect(n, n2, this.p, this.d);
        }
        if (this.p != null) {
            graphics.setColor(this.p);
        }
        graphics.setFont(this.p.getFont());
        graphics.drawString(this.p, n, n2 + this.a);
    }
    
    public final String toString() {
        return String.valueOf(this.p) + ":" + this.p + "x" + this.d;
    }
}
