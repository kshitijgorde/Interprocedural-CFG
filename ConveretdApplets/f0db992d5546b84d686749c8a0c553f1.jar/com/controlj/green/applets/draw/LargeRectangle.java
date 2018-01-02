// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets.draw;

public class LargeRectangle
{
    public double x;
    public double y;
    public double width;
    public double height;
    
    public LargeRectangle() {
        this(0.0, 0.0, 0.0, 0.0);
    }
    
    public LargeRectangle(final LargeRectangle r) {
        this(r.x, r.y, r.width, r.height);
    }
    
    public LargeRectangle(final double x, final double y, final double width, final double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public LargeRectangle(final double width, final double height) {
        this(0.0, 0.0, width, height);
    }
    
    public LargeRectangle(final LargePoint p) {
        this(p.x, p.y, 0.0, 0.0);
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getOtherX() {
        return this.x - this.width;
    }
    
    public double getY() {
        return this.y;
    }
    
    public double getOtherY() {
        return this.y + this.height;
    }
    
    public double getWidth() {
        return this.width;
    }
    
    public double getHeight() {
        return this.height;
    }
    
    public void setRect(final double x, final double y, final double width, final double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void move(final double x, final double y) {
        this.x = x;
        this.y = y;
    }
    
    public void translate(final double x, final double y) {
        this.x += x;
        this.y += y;
    }
    
    public void resize(final double width, final double height) {
        this.width = width;
        this.height = height;
    }
    
    public boolean contains(final LargePoint p) {
        return this.contains(p.x, p.y);
    }
    
    public boolean contains(final double x, final double y) {
        return this.inside(x, y);
    }
    
    public boolean contains(final LargeRectangle r) {
        return this.contains(r.x, r.y, r.width, r.height);
    }
    
    public boolean contains(final double X, final double Y, final double W, final double H) {
        return this.width > 0.0 && this.height > 0.0 && W > 0.0 && H > 0.0 && X >= this.x && Y >= this.y && X + W <= this.x + this.width && Y + H <= this.y + this.height;
    }
    
    public boolean inside(final double x, final double y) {
        return x >= this.x && x - this.x < this.width && y >= this.y && y - this.y < this.height;
    }
    
    public boolean intersects(final LargeRectangle r) {
        return r.x + r.width > this.x && r.y + r.height > this.y && r.x < this.x + this.width && r.y < this.y + this.height;
    }
    
    public LargeRectangle intersection(final LargeRectangle r) {
        final double x1 = Math.max(this.x, r.x);
        final double x2 = Math.min(this.x + this.width, r.x + r.width);
        final double y1 = Math.max(this.y, r.y);
        final double y2 = Math.min(this.y + this.height, r.y + r.height);
        if (x2 - x1 < 0.0 || y2 - y1 < 0.0) {
            return new LargeRectangle(0.0, 0.0, 0.0, 0.0);
        }
        return new LargeRectangle(x1, y1, x2 - x1, y2 - y1);
    }
    
    public LargeRectangle union(final LargeRectangle r) {
        final double x1 = Math.min(this.x, r.x);
        final double x2 = Math.max(this.x + this.width, r.x + r.width);
        final double y1 = Math.min(this.y, r.y);
        final double y2 = Math.max(this.y + this.height, r.y + r.height);
        return new LargeRectangle(x1, y1, x2 - x1, y2 - y1);
    }
    
    public void add(final double newx, final double newy) {
        final double x1 = Math.min(this.x, newx);
        final double x2 = Math.max(this.x + this.width, newx);
        final double y1 = Math.min(this.y, newy);
        final double y2 = Math.max(this.y + this.height, newy);
        this.x = x1;
        this.y = y1;
        this.width = x2 - x1;
        this.height = y2 - y1;
    }
    
    public void add(final LargePoint pt) {
        this.add(pt.x, pt.y);
    }
    
    public void add(final LargeRectangle r) {
        final double x1 = Math.min(this.x, r.x);
        final double x2 = Math.max(this.x + this.width, r.x + r.width);
        final double y1 = Math.min(this.y, r.y);
        final double y2 = Math.max(this.y + this.height, r.y + r.height);
        this.x = x1;
        this.y = y1;
        this.width = x2 - x1;
        this.height = y2 - y1;
    }
    
    public void grow(final double h, final double v) {
        this.x -= h;
        this.y -= v;
        this.width += h * 2.0;
        this.height += v * 2.0;
    }
    
    public boolean isEmpty() {
        return this.width <= 0.0 || this.height <= 0.0;
    }
    
    public boolean equals(final LargeRectangle r) {
        return r != null && this.x == r.x && this.y == r.y && this.width == r.width && this.height == r.height;
    }
    
    public String toString() {
        return this.getClass().getName() + "[x=" + this.x + ",y=" + this.y + ",width=" + this.width + ",height=" + this.height + "]";
    }
}
