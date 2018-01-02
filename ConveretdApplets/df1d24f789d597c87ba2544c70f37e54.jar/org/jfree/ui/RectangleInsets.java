// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui;

import java.awt.geom.Rectangle2D;
import org.jfree.util.UnitType;

public class RectangleInsets
{
    private UnitType unitType;
    private double top;
    private double bottom;
    private double left;
    private double right;
    
    public RectangleInsets(final UnitType unitType, final double top, final double bottom, final double left, final double right) {
        if (unitType == null) {
            throw new IllegalArgumentException("Null 'unitType' argument.");
        }
        this.unitType = unitType;
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }
    
    public UnitType getUnitType() {
        return this.unitType;
    }
    
    public double getTop() {
        return this.top;
    }
    
    public double getBottom() {
        return this.bottom;
    }
    
    public double getLeft() {
        return this.left;
    }
    
    public double getRight() {
        return this.right;
    }
    
    public Rectangle2D createInsetRectangle(final Rectangle2D rectangle2D) {
        return this.createInsetRectangle(rectangle2D, true, true);
    }
    
    public Rectangle2D createInsetRectangle(final Rectangle2D rectangle2D, final boolean b, final boolean b2) {
        if (rectangle2D == null) {
            throw new IllegalArgumentException("Null 'base' argument.");
        }
        double calculateTopMargin = 0.0;
        double calculateBottomMargin = 0.0;
        if (b2) {
            calculateTopMargin = this.calculateTopMargin(rectangle2D.getHeight());
            calculateBottomMargin = this.calculateBottomMargin(rectangle2D.getHeight());
        }
        double calculateLeftMargin = 0.0;
        double calculateRightMargin = 0.0;
        if (b) {
            calculateLeftMargin = this.calculateLeftMargin(rectangle2D.getWidth());
            calculateRightMargin = this.calculateRightMargin(rectangle2D.getWidth());
        }
        return new Rectangle2D.Double(rectangle2D.getX() + calculateLeftMargin, rectangle2D.getY() + calculateTopMargin, rectangle2D.getWidth() - calculateLeftMargin - calculateRightMargin, rectangle2D.getHeight() - calculateTopMargin - calculateBottomMargin);
    }
    
    public Rectangle2D createOutsetRectangle(final Rectangle2D rectangle2D) {
        return this.createOutsetRectangle(rectangle2D, true, true);
    }
    
    public Rectangle2D createOutsetRectangle(final Rectangle2D rectangle2D, final boolean b, final boolean b2) {
        if (rectangle2D == null) {
            throw new IllegalArgumentException("Null 'base' argument.");
        }
        double calculateTopMargin = 0.0;
        double calculateBottomMargin = 0.0;
        if (b2) {
            calculateTopMargin = this.calculateTopMargin(rectangle2D.getHeight());
            calculateBottomMargin = this.calculateBottomMargin(rectangle2D.getHeight());
        }
        double calculateLeftMargin = 0.0;
        double calculateRightMargin = 0.0;
        if (b) {
            calculateLeftMargin = this.calculateLeftMargin(rectangle2D.getWidth());
            calculateRightMargin = this.calculateRightMargin(rectangle2D.getWidth());
        }
        return new Rectangle2D.Double(rectangle2D.getX() - calculateLeftMargin, rectangle2D.getY() - calculateTopMargin, rectangle2D.getWidth() + calculateLeftMargin + calculateRightMargin, rectangle2D.getHeight() + calculateTopMargin + calculateBottomMargin);
    }
    
    public double calculateTopMargin(final double n) {
        double top = this.top;
        if (this.unitType == UnitType.RELATIVE) {
            top = this.top * n;
        }
        return top;
    }
    
    public double calculateBottomMargin(final double n) {
        double bottom = this.bottom;
        if (this.unitType == UnitType.RELATIVE) {
            bottom = this.bottom * n;
        }
        return bottom;
    }
    
    public double calculateLeftMargin(final double n) {
        double left = this.left;
        if (this.unitType == UnitType.RELATIVE) {
            left = this.left * n;
        }
        return left;
    }
    
    public double calculateRightMargin(final double n) {
        double right = this.right;
        if (this.unitType == UnitType.RELATIVE) {
            right = this.right * n;
        }
        return right;
    }
}
