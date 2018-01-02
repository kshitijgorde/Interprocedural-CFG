import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.AffineTransform;
import java.awt.Graphics;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_an extends rp_eS
{
    public Rectangle a;
    private Graphics a;
    private int a;
    
    public final boolean a() {
        return true;
    }
    
    public rp_an(final Graphics a) {
        this.a = null;
        this.a = 0;
        this.a = a;
    }
    
    public final Graphics a() {
        return this.a;
    }
    
    private void a(final int n, final int n2) {
        if (this.a > 0) {
            this.b(n - this.a, n2 - this.a);
            this.b(n + this.a, n2 + this.a);
            return;
        }
        this.b(n, n2);
    }
    
    private void b(final int n, final int n2) {
        if (this.a == null) {
            this.a = new Rectangle(n, n2, 0, 0);
            return;
        }
        this.a.add(n, n2);
    }
    
    private void a(final Rectangle a) {
        if (this.a > 0) {
            a.add(a.x - this.a, a.y - this.a);
            a.width += this.a;
            a.height += this.a;
        }
        if (this.a == null) {
            this.a = a;
            return;
        }
        this.a.add(a);
    }
    
    public final void a(final int n) {
        this.a = (n + 1) / 2;
    }
    
    private void c(final int[] array, final int[] array2, final int n) {
        for (int i = 0; i < n; ++i) {
            this.a(array[i], array2[i]);
        }
    }
    
    public final void a(final int n, final int n2, final int n3, final int n4) {
        this.a(n, n2);
        this.a(n3, n4);
    }
    
    public final void a(final int n, final int n2, final int n3, int n4, int abs, double n5, double n6, final double n7) {
        final int n8 = (n3 == 0) ? abs : n3;
        n4 = ((n4 == 0) ? abs : n4);
        abs = Math.abs(n8 * 2);
        final int abs2 = Math.abs(n4 * 2);
        if (n3 == 0) {
            n5 += 90.0;
            n6 += 90.0;
        }
        this.a(AffineTransform.getRotateInstance(n7 * 3.141592653589793 / 180.0, n, n2).createTransformedShape(new Arc2D.Double(n - n8, n2 - n4, abs, abs2, n5, n6 - n5, 0)).getBounds2D().getBounds());
    }
    
    public final void b(final int n, final int n2, final int n3, final int n4) {
        this.a(n, n2);
        this.a(n + n3, n2 + n4);
    }
    
    public final void a(final rp_aV rp_aV, final double n, final int n2, final int n3, final int n4, final int n5) {
        this.a(AffineTransform.getRotateInstance(n * 3.141592653589793 / 180.0, n2 + n4 / 2, n3 + n5 / 2).createTransformedShape(new Rectangle2D.Double(n2, n3, n4, n5)).getBounds2D().getBounds());
    }
    
    public final void c(final int n, final int n2, final int n3, final int n4) {
        this.a(n, n2);
        this.a(n + n3, n2 + n4);
    }
    
    public final void a(final int[] array, final int[] array2, final int n) {
        this.c(array, array2, n);
    }
    
    public final void b(final int[] array, final int[] array2, final int n) {
        this.c(array, array2, n);
    }
    
    public final void a(final int[] array, final int[] array2, final int n, final Color color) {
        this.c(array, array2, n);
    }
    
    public final void a(final int[] array, final int[] array2, final int n, final int n2, final Point point, final Point point2, final Color color, final Color color2) {
        this.c(array, array2, n);
    }
    
    public final void a(final String s, final int n, final int n2, final int n3, final int n4) {
        if (this.a != null) {
            final FontMetrics fontMetrics;
            final Dimension a = rp_eS.a(fontMetrics = this.a.getFontMetrics(), s, n3, 2);
            this.a(n + 2000 * a.width, n2 + 2000 * a.height + 2000 * fontMetrics.getDescent());
            this.a(n + 2000 * (a.width + fontMetrics.stringWidth(s)), n2 + 2000 * (a.height - fontMetrics.getAscent()));
            return;
        }
        this.a(n, n2);
        this.a(n + 80000, n2 + 20000);
    }
    
    public final Rectangle a(final String s, final int n, final int n2, final int n3, final int n4) {
        return new Rectangle(n, n2, 10, 10);
    }
}
