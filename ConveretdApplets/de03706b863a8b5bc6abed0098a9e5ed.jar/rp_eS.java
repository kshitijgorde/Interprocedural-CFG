import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class rp_eS
{
    public boolean a;
    public boolean b;
    public boolean c;
    protected Color a;
    protected Color b;
    protected boolean d;
    protected Color c;
    protected Color d;
    
    public boolean a() {
        return false;
    }
    
    public final void a(final Color a, final Color b) {
        this.a = a;
        this.b = b;
    }
    
    public final void a(final boolean d) {
        this.d = d;
    }
    
    public final Color a() {
        return this.a;
    }
    
    public final Color b() {
        return this.b;
    }
    
    public final void b(final Color c, final Color d) {
        this.c = c;
        this.d = d;
    }
    
    rp_eS() {
        this.a = false;
        this.b = false;
        this.c = false;
        this.a = null;
        this.b = null;
        this.d = false;
        this.c = null;
        this.d = null;
    }
    
    public abstract Graphics a();
    
    public void a(final Color color) {
    }
    
    public void b(final Color color) {
    }
    
    public void a(final int n) {
    }
    
    public abstract void a(final rp_aV p0, final double p1, final int p2, final int p3, final int p4, final int p5);
    
    public abstract void a(final int p0, final int p1, final int p2, final int p3);
    
    public abstract void b(final int p0, final int p1, final int p2, final int p3);
    
    public abstract void c(final int p0, final int p1, final int p2, final int p3);
    
    public abstract void a(final int p0, final int p1, final int p2, final int p3, final int p4, final double p5, final double p6, final double p7);
    
    public abstract void a(final int[] p0, final int[] p1, final int p2);
    
    public abstract void b(final int[] p0, final int[] p1, final int p2);
    
    public abstract void a(final int[] p0, final int[] p1, final int p2, final Color p3);
    
    public abstract void a(final int[] p0, final int[] p1, final int p2, final int p3, final Point p4, final Point p5, final Color p6, final Color p7);
    
    public abstract void a(final String p0, final int p1, final int p2, final int p3, final int p4);
    
    public abstract Rectangle a(final String p0, final int p1, final int p2, final int p3, final int p4);
    
    public static Dimension a(final FontMetrics fontMetrics, final String s, final int n, final int n2) {
        final Dimension dimension = new Dimension(0, 0);
        switch (n) {
            case 2: {
                final Dimension dimension2 = dimension;
                dimension2.width -= fontMetrics.stringWidth(s);
                break;
            }
            case 1: {
                final Dimension dimension3 = dimension;
                dimension3.width -= fontMetrics.stringWidth(s) / 2;
                break;
            }
        }
        switch (n2) {
            case 3: {
                final Dimension dimension4 = dimension;
                dimension4.height += fontMetrics.getAscent();
                break;
            }
            case 1: {
                final Dimension dimension5 = dimension;
                dimension5.height -= fontMetrics.getDescent();
                break;
            }
            case 2: {
                final Dimension dimension6 = dimension;
                dimension6.height += fontMetrics.getAscent() - fontMetrics.getHeight() / 2;
                break;
            }
        }
        return dimension;
    }
}
