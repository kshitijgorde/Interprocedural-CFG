// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public class Spacer implements Serializable
{
    public static final int RELATIVE = 0;
    public static final int ABSOLUTE = 1;
    private int type;
    private double left;
    private double right;
    private double top;
    private double bottom;
    
    public Spacer(final int type, final double left, final double top, final double right, final double bottom) {
        this.type = type;
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }
    
    public double getLeftSpace(final double n) {
        double left = 0.0;
        if (this.type == 1) {
            left = this.left;
        }
        else if (this.type == 0) {
            left = this.left * n;
        }
        return left;
    }
    
    public double getRightSpace(final double n) {
        double right = 0.0;
        if (this.type == 1) {
            right = this.right;
        }
        else if (this.type == 0) {
            right = this.right * n;
        }
        return right;
    }
    
    public double getTopSpace(final double n) {
        double top = 0.0;
        if (this.type == 1) {
            top = this.top;
        }
        else if (this.type == 0) {
            top = this.top * n;
        }
        return top;
    }
    
    public double getBottomSpace(final double n) {
        double bottom = 0.0;
        if (this.type == 1) {
            bottom = this.bottom;
        }
        else if (this.type == 0) {
            bottom = this.bottom * n;
        }
        return bottom;
    }
    
    public double getAdjustedWidth(final double n) {
        double n2 = n;
        if (this.type == 1) {
            n2 = n2 + this.left + this.right;
        }
        else if (this.type == 0) {
            n2 = n2 + this.left * n + this.right * n;
        }
        return n2;
    }
    
    public double getAdjustedHeight(final double n) {
        double n2 = n;
        if (this.type == 1) {
            n2 = n2 + this.top + this.bottom;
        }
        else if (this.type == 0) {
            n2 = n2 + this.top * n + this.bottom * n;
        }
        return n2;
    }
    
    public void trim(final Rectangle2D rectangle2D) {
        final double x = rectangle2D.getX();
        final double y = rectangle2D.getY();
        final double height = rectangle2D.getHeight();
        final double width = rectangle2D.getWidth();
        final double leftSpace = this.getLeftSpace(width);
        final double rightSpace = this.getRightSpace(width);
        final double topSpace = this.getTopSpace(height);
        rectangle2D.setRect(x + leftSpace, y + topSpace, width - leftSpace - rightSpace, height - topSpace - this.getBottomSpace(height));
    }
    
    public double trimWidth(final double n) {
        return n - this.getLeftSpace(n) - this.getRightSpace(n);
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o instanceof Spacer) {
            final Spacer spacer = (Spacer)o;
            final boolean b = this.type == spacer.type;
            final boolean b2 = this.left == spacer.left;
            final boolean b3 = this.right == spacer.right;
            final boolean b4 = this.top == spacer.top;
            final boolean b5 = this.bottom == spacer.bottom;
            return b && b2 && b3 && b4 && b5;
        }
        return false;
    }
}
