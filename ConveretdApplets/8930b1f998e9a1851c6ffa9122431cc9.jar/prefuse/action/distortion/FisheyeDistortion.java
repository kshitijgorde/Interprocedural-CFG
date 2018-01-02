// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.distortion;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;

public class FisheyeDistortion extends Distortion
{
    private double dx;
    private double dy;
    private double sz;
    
    public FisheyeDistortion() {
        this(4.0);
    }
    
    public FisheyeDistortion(final double n) {
        this(n, n);
    }
    
    public FisheyeDistortion(final double dx, final double dy) {
        this.sz = 3.0;
        this.dx = dx;
        this.dy = dy;
        this.m_distortX = (this.dx > 0.0);
        this.m_distortY = (this.dy > 0.0);
    }
    
    public double getXDistortionFactor() {
        return this.dx;
    }
    
    public void setXDistortionFactor(final double dx) {
        this.dx = dx;
        this.m_distortX = (this.dx > 0.0);
    }
    
    public double getYDistortionFactor() {
        return this.dy;
    }
    
    public void setYDistortionFactor(final double dy) {
        this.dy = dy;
        this.m_distortY = (this.dy > 0.0);
    }
    
    protected double distortX(final double n, final Point2D point2D, final Rectangle2D rectangle2D) {
        return this.fisheye(n, point2D.getX(), this.dx, rectangle2D.getMinX(), rectangle2D.getMaxX());
    }
    
    protected double distortY(final double n, final Point2D point2D, final Rectangle2D rectangle2D) {
        return this.fisheye(n, point2D.getY(), this.dy, rectangle2D.getMinY(), rectangle2D.getMaxY());
    }
    
    protected double distortSize(final Rectangle2D rectangle2D, final double n, final double n2, final Point2D point2D, final Rectangle2D rectangle2D2) {
        if (!this.m_distortX && !this.m_distortY) {
            return 1.0;
        }
        double n3 = 1.0;
        double n4 = 1.0;
        if (this.m_distortX) {
            final double x = point2D.getX();
            final double minX = rectangle2D.getMinX();
            final double maxX = rectangle2D.getMaxX();
            double n5 = (Math.abs(minX - x) > Math.abs(maxX - x)) ? minX : maxX;
            if (n5 < rectangle2D2.getMinX() || n5 > rectangle2D2.getMaxX()) {
                n5 = ((n5 == minX) ? maxX : minX);
            }
            n3 = Math.abs(n - this.fisheye(n5, x, this.dx, rectangle2D2.getMinX(), rectangle2D2.getMaxX())) / rectangle2D.getWidth();
        }
        if (this.m_distortY) {
            final double y = point2D.getY();
            final double minY = rectangle2D.getMinY();
            final double maxY = rectangle2D.getMaxY();
            double n6 = (Math.abs(minY - y) > Math.abs(maxY - y)) ? minY : maxY;
            if (n6 < rectangle2D2.getMinY() || n6 > rectangle2D2.getMaxY()) {
                n6 = ((n6 == minY) ? maxY : minY);
            }
            n4 = Math.abs(n2 - this.fisheye(n6, y, this.dy, rectangle2D2.getMinY(), rectangle2D2.getMaxY())) / rectangle2D.getHeight();
        }
        final double n7 = this.m_distortY ? (this.m_distortX ? Math.min(n3, n4) : n4) : n3;
        if (Double.isInfinite(n7) || Double.isNaN(n7)) {
            return 1.0;
        }
        return this.sz * n7;
    }
    
    private double fisheye(final double n, final double n2, final double n3, final double n4, final double n5) {
        if (n3 != 0.0) {
            final boolean b = n < n2;
            double n6 = b ? (n2 - n4) : (n5 - n2);
            if (n6 == 0.0) {
                n6 = n5 - n4;
            }
            return (b ? -1 : 1) * n6 * ((n3 + 1.0) / (n3 + 1.0 / (Math.abs(n - n2) / n6))) + n2;
        }
        return n;
    }
}
