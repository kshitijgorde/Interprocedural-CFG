import java.awt.PaintContext;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import java.awt.image.ColorModel;
import java.awt.geom.AffineTransform;
import java.awt.Color;
import java.awt.geom.Point2D;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_en extends rp_cS
{
    private final Point2D a;
    private final Point2D b;
    private final float a;
    
    public rp_en(final Point2D point2D, final float n, final float[] array, final Color[] array2) {
        this(point2D, n, point2D, array, array2, rp_fP.a);
    }
    
    private rp_en(final Point2D point2D, final float n, final Point2D point2D2, final float[] array, final Color[] array2, final rp_fP rp_fP) {
        this(point2D, n, point2D2, array, array2, rp_fP, rp_cN.a, new AffineTransform());
    }
    
    private rp_en(final Point2D point2D, final float a, final Point2D point2D2, final float[] array, final Color[] array2, final rp_fP rp_fP, final rp_cN rp_cN, final AffineTransform affineTransform) {
        super(array, array2, rp_fP, rp_cN, affineTransform);
        if (point2D == null) {
            throw new NullPointerException("Center point must be non-null");
        }
        if (point2D2 == null) {
            throw new NullPointerException("Focus point must be non-null");
        }
        if (a <= 0.0f) {
            throw new IllegalArgumentException("Radius must be greater than zero");
        }
        this.b = new Point2D.Double(point2D.getX(), point2D.getY());
        this.a = new Point2D.Double(point2D2.getX(), point2D2.getY());
        this.a = a;
    }
    
    public final PaintContext createContext(final ColorModel colorModel, final Rectangle rectangle, final Rectangle2D rectangle2D, AffineTransform affineTransform, final RenderingHints renderingHints) {
        (affineTransform = new AffineTransform(affineTransform)).concatenate(this.a);
        return new rp_bX(this, colorModel, rectangle, rectangle2D, affineTransform, renderingHints, (float)this.b.getX(), (float)this.b.getY(), this.a, (float)this.a.getX(), (float)this.a.getY(), this.a, this.a, this.a, this.a);
    }
}
