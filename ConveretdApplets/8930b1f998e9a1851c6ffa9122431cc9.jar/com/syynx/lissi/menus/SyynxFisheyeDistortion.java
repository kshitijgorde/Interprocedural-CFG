// 
// Decompiled by Procyon v0.5.30
// 

package com.syynx.lissi.menus;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;
import prefuse.action.distortion.Distortion;

public class SyynxFisheyeDistortion extends Distortion
{
    private double dx;
    private double dy;
    private double sz;
    
    public SyynxFisheyeDistortion() {
        this(4.0);
    }
    
    public SyynxFisheyeDistortion(final double dfactor) {
        this(dfactor, dfactor);
    }
    
    public SyynxFisheyeDistortion(final double xfactor, final double yfactor) {
        this.sz = 3.0;
        this.dx = xfactor;
        this.dy = yfactor;
        this.m_distortX = (this.dx > 0.0);
        this.m_distortY = (this.dy > 0.0);
    }
    
    public double getXDistortionFactor() {
        return this.dx;
    }
    
    public void setXDistortionFactor(final double d) {
        this.dx = d;
        this.m_distortX = (this.dx > 0.0);
    }
    
    public double getYDistortionFactor() {
        return this.dy;
    }
    
    public void setYDistortionFactor(final double d) {
        this.dy = d;
        this.m_distortY = (this.dy > 0.0);
    }
    
    protected double distortX(final double x, final Point2D anchor, final Rectangle2D bounds) {
        return this.fisheye(x, anchor.getX(), this.dx, bounds.getMinX(), bounds.getMaxX());
    }
    
    protected double distortY(final double y, final Point2D anchor, final Rectangle2D bounds) {
        return this.fisheye(y, anchor.getY(), this.dy, bounds.getMinY(), bounds.getMaxY());
    }
    
    protected double distortSize(final Rectangle2D bbox, final double x, final double y, final Point2D anchor, final Rectangle2D bounds) {
        if (!this.m_distortX && !this.m_distortY) {
            return 1.0;
        }
        double fx = 1.0;
        double fy = 1.0;
        if (this.m_distortX) {
            final double ax = anchor.getX();
            final double minX = bbox.getMinX();
            final double maxX = bbox.getMaxX();
            double xx = (Math.abs(minX - ax) > Math.abs(maxX - ax)) ? minX : maxX;
            if (xx < bounds.getMinX() || xx > bounds.getMaxX()) {
                xx = ((xx == minX) ? maxX : minX);
            }
            fx = this.fisheye(xx, ax, this.dx, bounds.getMinX(), bounds.getMaxX());
            fx = Math.abs(x - fx) / bbox.getWidth();
        }
        if (this.m_distortY) {
            final double ay = anchor.getY();
            final double minY = bbox.getMinY();
            final double maxY = bbox.getMaxY();
            double yy = (Math.abs(minY - ay) > Math.abs(maxY - ay)) ? minY : maxY;
            if (yy < bounds.getMinY() || yy > bounds.getMaxY()) {
                yy = ((yy == minY) ? maxY : minY);
            }
            fy = this.fisheye(yy, ay, this.dy, bounds.getMinY(), bounds.getMaxY());
            fy = Math.abs(y - fy) / bbox.getHeight();
        }
        final double sf = this.m_distortY ? (this.m_distortX ? Math.min(fx, fy) : fy) : fx;
        if (Double.isInfinite(sf) || Double.isNaN(sf)) {
            return 1.0;
        }
        return this.sz * sf;
    }
    
    private double fisheye(final double x, final double a, final double d, final double min, final double max) {
        if (d != 0.0) {
            final boolean left = x < a;
            double m = left ? (a - min) : (max - a);
            if (m == 0.0) {
                m = max - min;
            }
            double v = Math.abs(x - a) / m;
            v = (d + 1.0) / (d + 1.0 / v);
            final double res = (left ? -1 : 1) * m * v + a;
            return res;
        }
        return x;
    }
}
