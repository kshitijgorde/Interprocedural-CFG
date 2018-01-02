// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.draw;

import java.awt.Graphics;
import java.awt.Color;

public class Grid extends Drawable
{
    private Color gcol;
    private double xsp;
    private double ysp;
    private final int PIX_MAX = 50;
    private final int PIX_MIN = 20;
    
    public Grid() {
        this(1.0, 1.0);
    }
    
    public Grid(final double xsp, final double ysp) {
        this.gcol = new Color(220, 220, 220);
        this.xsp = xsp;
        this.ysp = ysp;
    }
    
    public Color getColor() {
        return this.gcol;
    }
    
    public void setColor(final Color gcol) {
        if (gcol != null && !gcol.equals(this.gcol)) {
            this.gcol = gcol;
            this.needsRedraw();
        }
    }
    
    public double getXSP() {
        return this.xsp;
    }
    
    public double getYSP() {
        return this.ysp;
    }
    
    public void setXSP(final double xsp) {
        this.xsp = xsp;
        this.needsRedraw();
    }
    
    public void setYSP(final double ysp) {
        this.ysp = ysp;
        this.needsRedraw();
    }
    
    public void draw(final Graphics graphics, final boolean b) {
        if (super.coords == null) {
            return;
        }
        final double pixelWidth = super.coords.getPixelWidth();
        final double pixelHeight = super.coords.getPixelHeight();
        if (Double.isNaN(pixelWidth) || Double.isNaN(pixelHeight) || Double.isInfinite(pixelHeight) || Double.isInfinite(pixelWidth) || pixelWidth == 0.0 || pixelHeight == 0.0) {
            return;
        }
        graphics.setColor(this.gcol);
        if (this.xsp > 0.0) {
            double xsp;
            for (xsp = this.xsp; xsp > pixelWidth * 50.0; xsp /= 10.0) {}
            if (xsp < pixelWidth * 20.0) {
                xsp *= 5.0;
            }
            if (xsp > pixelWidth * 50.0) {
                xsp /= 2.0;
            }
            for (double n = (int)Math.ceil(super.coords.getXmin() / xsp) * xsp; super.coords.xToPixel(n) < super.coords.getWidth() + super.coords.getLeft(); n += xsp) {
                graphics.drawLine(super.coords.xToPixel(n), super.coords.getTop(), super.coords.xToPixel(n), super.coords.getTop() + super.coords.getHeight());
            }
        }
        if (this.ysp > 0.0) {
            double ysp;
            for (ysp = this.ysp; ysp > pixelHeight * 50.0; ysp /= 10.0) {}
            if (ysp < pixelHeight * 20.0) {
                ysp *= 5.0;
            }
            if (ysp > pixelHeight * 50.0) {
                ysp /= 2.0;
            }
            for (double n2 = (int)Math.ceil(super.coords.getYmin() / ysp) * ysp; super.coords.yToPixel(n2) > super.coords.getTop(); n2 += ysp) {
                graphics.drawLine(super.coords.getLeft(), super.coords.yToPixel(n2), super.coords.getLeft() + super.coords.getWidth(), super.coords.yToPixel(n2));
            }
        }
    }
}
