// 
// Decompiled by Procyon v0.5.30
// 

package jfig.objects;

import java.awt.Rectangle;
import java.awt.Point;

public final class FigBbox
{
    private int xl;
    private int yt;
    private int xr;
    private int yb;
    
    public final void set(final int n, final int n2, final int n3, final int n4) {
        this.xl = Math.min(n, n3);
        this.xr = Math.max(n, n3);
        this.yt = Math.min(n2, n4);
        this.yb = Math.max(n2, n4);
    }
    
    public final void set(final FigBbox figBbox) {
        this.xl = figBbox.xl;
        this.yt = figBbox.yt;
        this.xr = figBbox.xr;
        this.yb = figBbox.yb;
    }
    
    public final FigBbox get() {
        return this;
    }
    
    public int getXl() {
        return this.xl;
    }
    
    public int getYt() {
        return this.yt;
    }
    
    public int getXr() {
        return this.xr;
    }
    
    public int getYb() {
        return this.yb;
    }
    
    public final boolean isInside(final Point point) {
        return point.x >= this.xl && point.x <= this.xr && point.y >= this.yt && point.y <= this.yb;
    }
    
    public final boolean inside(final int n, final int n2) {
        return this.xl <= n && n <= this.xr && this.yt <= n2 && n2 <= this.yb;
    }
    
    public final boolean inside(final FigBbox figBbox) {
        return this.xl >= figBbox.xl && this.xr <= figBbox.xr && this.yt >= figBbox.yt && this.yb <= figBbox.yb;
    }
    
    public final boolean isVisible(final FigBbox figBbox) {
        return this.xl < figBbox.xr && this.xr > figBbox.xl && this.yt < figBbox.yb && this.yb > figBbox.yt;
    }
    
    public final boolean isOnBorder(final Point point, final int n) {
        if (Math.abs(point.y - this.yt) < n || Math.abs(point.y - this.yb) < n) {
            return point.x > this.xl && point.x < this.xr;
        }
        return (Math.abs(point.x - this.xl) < n || Math.abs(point.x - this.xr) < n) && point.y > this.yt && point.y < this.yb;
    }
    
    public final FigBbox union(final FigBbox figBbox) {
        final FigBbox figBbox2 = new FigBbox(this.xl, this.yt, this.xr, this.yb);
        figBbox2.xl = Math.min(this.xl, figBbox.xl);
        figBbox2.xr = Math.max(this.xr, figBbox.xr);
        figBbox2.yt = Math.min(this.yt, figBbox.yt);
        figBbox2.yb = Math.max(this.yb, figBbox.yb);
        return figBbox2;
    }
    
    public static final FigBbox union(final FigBbox figBbox, final FigBbox figBbox2) {
        final FigBbox figBbox3 = new FigBbox(0, 0, 0, 0);
        figBbox3.xl = Math.min(figBbox.xl, figBbox2.xl);
        figBbox3.xr = Math.max(figBbox.xr, figBbox2.xr);
        figBbox3.yt = Math.min(figBbox.yt, figBbox2.yt);
        figBbox3.yb = Math.max(figBbox.yb, figBbox2.yb);
        return figBbox3;
    }
    
    public final double minDistance(final Point point) {
        return Math.min(Math.abs(this.xl - point.x), Math.abs(this.xr - point.x)) + Math.min(Math.abs(this.yt - point.y), Math.abs(this.yb - point.y));
    }
    
    public final double minDistanceInsideZero(final Point point) {
        if (this.isInside(point)) {
            return 0.0;
        }
        return this.minDistance(point);
    }
    
    public final double minDistanceInside(final Point point) {
        final double minDistance = this.minDistance(point);
        if (this.isInside(point)) {
            return 0.9 * minDistance;
        }
        return minDistance;
    }
    
    public final Point getNearestCorner(final Point point) {
        final int abs = Math.abs(point.x - this.xl);
        final int abs2 = Math.abs(point.x - this.xr);
        final int abs3 = Math.abs(point.y - this.yt);
        final int abs4 = Math.abs(point.y - this.yb);
        int n;
        if (abs > abs2) {
            n = this.xr;
        }
        else {
            n = this.xl;
        }
        int n2;
        if (abs3 > abs4) {
            n2 = this.yb;
        }
        else {
            n2 = this.yt;
        }
        return new Point(n, n2);
    }
    
    public final Point getOppositeCorner(final Point point) {
        final int abs = Math.abs(point.x - this.xl);
        final int abs2 = Math.abs(point.x - this.xr);
        final int abs3 = Math.abs(point.y - this.yt);
        final int abs4 = Math.abs(point.y - this.yb);
        int n;
        if (abs < abs2) {
            n = this.xr;
        }
        else {
            n = this.xl;
        }
        int n2;
        if (abs3 < abs4) {
            n2 = this.yb;
        }
        else {
            n2 = this.yt;
        }
        return new Point(n, n2);
    }
    
    public final Point getCenterPoint() {
        return new Point((int)(0.5 * (this.xl + this.xr)), (int)(0.5 * (this.yt + this.yb)));
    }
    
    public Rectangle getRectangle() {
        return new Rectangle(this.xl, this.yt, this.xr - this.xl, this.yb - this.yt);
    }
    
    public String toString() {
        return "bbox: (" + this.xl + ", " + this.yt + ") (" + this.xr + ", " + this.yb + ") ";
    }
    
    public FigBbox(final int n, final int n2, final int n3, final int n4) {
        this.xl = Math.min(n, n3);
        this.xr = Math.max(n, n3);
        this.yt = Math.min(n2, n4);
        this.yb = Math.max(n2, n4);
    }
    
    public FigBbox(final Point point, final Point point2) {
        this.xl = Math.min(point.x, point2.x);
        this.xr = Math.max(point.x, point2.x);
        this.yt = Math.min(point.y, point2.y);
        this.yb = Math.max(point.y, point2.y);
    }
    
    public FigBbox(final FigBbox figBbox) {
        this.xl = figBbox.xl;
        this.xr = figBbox.xr;
        this.yt = figBbox.yt;
        this.yb = figBbox.yb;
    }
}
