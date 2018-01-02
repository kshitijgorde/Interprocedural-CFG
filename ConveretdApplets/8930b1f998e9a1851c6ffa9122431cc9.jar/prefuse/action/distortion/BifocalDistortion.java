// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.distortion;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;

public class BifocalDistortion extends Distortion
{
    private double rx;
    private double ry;
    private double mx;
    private double my;
    
    public BifocalDistortion() {
        this(0.1, 3.0);
    }
    
    public BifocalDistortion(final double n, final double n2) {
        this(n, n2, n, n2);
    }
    
    public BifocalDistortion(final double rx, final double mx, final double ry, final double my) {
        this.rx = rx;
        this.mx = mx;
        this.ry = ry;
        this.my = my;
        this.m_distortX = (this.rx != 0.0 && this.mx != 1.0);
        this.m_distortY = (this.ry != 0.0 && this.my != 1.0);
    }
    
    protected double distortX(final double n, final Point2D point2D, final Rectangle2D rectangle2D) {
        return this.bifocal(n, point2D.getX(), this.rx, this.mx, rectangle2D.getMinX(), rectangle2D.getMaxX());
    }
    
    protected double distortY(final double n, final Point2D point2D, final Rectangle2D rectangle2D) {
        return this.bifocal(n, point2D.getY(), this.ry, this.my, rectangle2D.getMinY(), rectangle2D.getMaxY());
    }
    
    protected double distortSize(final Rectangle2D rectangle2D, final double n, final double n2, final Point2D point2D, final Rectangle2D rectangle2D2) {
        boolean b = false;
        boolean b2 = false;
        if (this.m_distortX) {
            final double centerX = rectangle2D.getCenterX();
            final double x = point2D.getX();
            final double minX = rectangle2D2.getMinX();
            final double maxX = rectangle2D2.getMaxX();
            double n3 = (centerX < x) ? (x - minX) : (maxX - x);
            if (n3 == 0.0) {
                n3 = maxX - minX;
            }
            if (Math.abs(centerX - x) <= this.rx * n3) {
                b = true;
            }
        }
        if (this.m_distortY) {
            final double centerY = rectangle2D.getCenterY();
            final double y = point2D.getY();
            final double minY = rectangle2D2.getMinY();
            final double maxY = rectangle2D2.getMaxY();
            double n4 = (centerY < y) ? (y - minY) : (maxY - y);
            if (n4 == 0.0) {
                n4 = maxY - minY;
            }
            if (Math.abs(centerY - y) <= this.ry * n4) {
                b2 = true;
            }
        }
        if (b && !this.m_distortY) {
            return this.mx;
        }
        if (b2 && !this.m_distortX) {
            return this.my;
        }
        if (b && b2) {
            return Math.min(this.mx, this.my);
        }
        return Math.min((1.0 - this.rx * this.mx) / (1.0 - this.rx), (1.0 - this.ry * this.my) / (1.0 - this.ry));
    }
    
    private double bifocal(double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        double n7 = (n < n2) ? (n2 - n5) : (n6 - n2);
        if (n7 == 0.0) {
            n7 = n6 - n5;
        }
        final double n8 = n - n2;
        final double n9 = n7 * n3;
        if (Math.abs(n8) <= n9) {
            return n = n8 * n4 + n2;
        }
        final double n10 = n3 * n4;
        n = (Math.abs(n8) - n9) / n7 * ((1.0 - n10) / (1.0 - n3));
        return ((n8 < 0.0) ? -1 : 1) * n7 * (n + n10) + n2;
    }
    
    public double getXMagnification() {
        return this.mx;
    }
    
    public void setXMagnification(final double mx) {
        this.mx = mx;
    }
    
    public double getYMagnification() {
        return this.my;
    }
    
    public void setYMagnification(final double my) {
        this.my = my;
    }
    
    public double getXRange() {
        return this.rx;
    }
    
    public void setXRange(final double rx) {
        this.rx = rx;
    }
    
    public double getYRange() {
        return this.ry;
    }
    
    public void setYRange(final double ry) {
        this.ry = ry;
    }
}
