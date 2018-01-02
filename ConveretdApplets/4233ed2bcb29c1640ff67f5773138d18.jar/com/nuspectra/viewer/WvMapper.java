// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.awt.Dimension;
import java.awt.Rectangle;

public class WvMapper
{
    protected float degMinX;
    protected float degMinY;
    protected float degMaxX;
    protected float degMaxY;
    protected float degRangeX;
    protected float degRangeY;
    protected Rectangle baseRect;
    
    public int getPosX(final int i) {
        return Math.round(this.baseRect.x + this.baseRect.width * (i - this.degMinX) / this.degRangeX);
    }
    
    public String toString() {
        return "x=" + this.degMinX + "," + this.degMaxX + "=" + this.degRangeX + ", y=" + this.degMinY + "," + this.degMaxY + "=" + this.degRangeY;
    }
    
    public WvMapper(final WvScope wvscope, final Dimension dimension) {
        final int w = dimension.width;
        final int h = dimension.height;
        this.degMinX = wvscope.getPanMin();
        this.degMaxX = wvscope.getPanMax();
        this.degMinY = wvscope.getTiltMin();
        this.degMaxY = wvscope.getTiltMax();
        this.degRangeX = this.degMaxX - this.degMinX;
        this.degRangeY = this.degMaxY - this.degMinY;
        this.baseRect = new Rectangle();
        this.baseRect.x = 0;
        this.baseRect.y = 0;
        this.baseRect.width = w;
        this.baseRect.height = h;
    }
    
    protected void mapRect(final WvScope wvscope, Rectangle rectangle) {
        final int panMin = wvscope.getPanMin();
        final int tiltMax = wvscope.getTiltMax();
        final int rx = wvscope.getDegRangeX();
        final int ry = wvscope.getDegRangeY();
        rectangle.x = Math.round(this.baseRect.width * (panMin - this.degMinX) / this.degRangeX);
        rectangle.y = Math.round(this.baseRect.height * (this.degMaxY - tiltMax) / this.degRangeY);
        rectangle.width = Math.round(this.baseRect.width * rx / this.degRangeX);
        rectangle.height = Math.round(this.baseRect.height * ry / this.degRangeY);
        rectangle.translate(this.baseRect.x, this.baseRect.y);
        rectangle = rectangle.intersection(this.baseRect);
    }
    
    public void mapRect(final int i, final int j, final int k, final Rectangle rectangle) {
        rectangle.width = this.getWidth(k);
        rectangle.height = rectangle.width * 3 / 4;
        rectangle.x = this.getPosX(i) - rectangle.width / 2;
        rectangle.y = this.getPosY(j) - rectangle.height / 2;
    }
    
    public int getTiltValue(int i) {
        i = this.baseRect.height - i;
        final float f1 = this.degMaxY - (i - this.baseRect.y) * this.degRangeY / this.baseRect.height;
        return Math.round(f1);
    }
    
    public int getPanValue(final int i) {
        return Math.round(this.degRangeX * (i - this.baseRect.x) / this.baseRect.width + this.degMinX);
    }
    
    public int getWidth(final int i) {
        final int n = Math.round(this.baseRect.width / this.degRangeX) * i;
        final int f2 = Math.round(this.baseRect.width * i / this.degRangeX);
        return f2;
    }
    
    public int getPosY(final int i) {
        return Math.round(this.baseRect.y + this.baseRect.height * (this.degMaxY - i) / this.degRangeY);
    }
}
