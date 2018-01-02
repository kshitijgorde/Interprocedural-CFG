// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.axis;

import java.awt.geom.Rectangle2D;
import org.jfree.ui.RectangleEdge;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class AxisSpace implements Cloneable, PublicCloneable, Serializable
{
    private double top;
    private double bottom;
    private double left;
    private double right;
    
    public AxisSpace() {
        this.top = 0.0;
        this.bottom = 0.0;
        this.left = 0.0;
        this.right = 0.0;
    }
    
    public double getTop() {
        return this.top;
    }
    
    public void setTop(final double space) {
        this.top = space;
    }
    
    public double getBottom() {
        return this.bottom;
    }
    
    public void setBottom(final double space) {
        this.bottom = space;
    }
    
    public double getLeft() {
        return this.left;
    }
    
    public void setLeft(final double space) {
        this.left = space;
    }
    
    public double getRight() {
        return this.right;
    }
    
    public void setRight(final double space) {
        this.right = space;
    }
    
    public void add(final double space, final RectangleEdge edge) {
        if (edge == RectangleEdge.TOP) {
            this.top += space;
        }
        else if (edge == RectangleEdge.BOTTOM) {
            this.bottom += space;
        }
        else if (edge == RectangleEdge.LEFT) {
            this.left += space;
        }
        else {
            if (edge != RectangleEdge.RIGHT) {
                throw new IllegalStateException("AxisSpace.add(...): unrecognised RectangleEdge.");
            }
            this.right += space;
        }
    }
    
    public void ensureAtLeast(final AxisSpace space) {
        this.top = Math.max(this.top, space.top);
        this.bottom = Math.max(this.bottom, space.bottom);
        this.left = Math.max(this.left, space.left);
        this.right = Math.max(this.right, space.right);
    }
    
    public void ensureAtLeast(final double space, final RectangleEdge edge) {
        if (edge == RectangleEdge.TOP) {
            if (this.top < space) {
                this.top = space;
            }
        }
        else if (edge == RectangleEdge.BOTTOM) {
            if (this.bottom < space) {
                this.bottom = space;
            }
        }
        else if (edge == RectangleEdge.LEFT) {
            if (this.left < space) {
                this.left = space;
            }
        }
        else {
            if (edge != RectangleEdge.RIGHT) {
                throw new IllegalStateException("AxisSpace.ensureAtLeast(...): unrecognised AxisLocation.");
            }
            if (this.right < space) {
                this.right = space;
            }
        }
    }
    
    public Rectangle2D shrink(final Rectangle2D area, Rectangle2D result) {
        if (result == null) {
            result = new Rectangle2D.Double();
        }
        result.setRect(area.getX() + this.left, area.getY() + this.top, area.getWidth() - this.left - this.right, area.getHeight() - this.top - this.bottom);
        return result;
    }
    
    public Rectangle2D shrinkLeftAndRight(final Rectangle2D area, Rectangle2D result) {
        if (result == null) {
            result = new Rectangle2D.Double();
        }
        result.setRect(area.getX() + this.left, area.getY(), area.getWidth() - this.left - this.right, area.getHeight());
        return result;
    }
    
    public Rectangle2D shrinkTopAndBottom(final Rectangle2D area, Rectangle2D result) {
        if (result == null) {
            result = new Rectangle2D.Double();
        }
        result.setRect(area.getX(), area.getY() + this.top, area.getWidth(), area.getHeight() - this.top - this.bottom);
        return result;
    }
    
    public Rectangle2D expand(final Rectangle2D area, Rectangle2D result) {
        if (result == null) {
            result = new Rectangle2D.Double();
        }
        result.setRect(area.getX() - this.left, area.getY() - this.top, area.getWidth() + this.left + this.right, area.getHeight() + this.top + this.bottom);
        return result;
    }
    
    public Rectangle2D reserved(final Rectangle2D area, final RectangleEdge edge) {
        Rectangle2D result = null;
        if (edge == RectangleEdge.TOP) {
            result = new Rectangle2D.Double(area.getX(), area.getY(), area.getWidth(), this.top);
        }
        else if (edge == RectangleEdge.BOTTOM) {
            result = new Rectangle2D.Double(area.getX(), area.getMaxY() - this.top, area.getWidth(), this.bottom);
        }
        else if (edge == RectangleEdge.LEFT) {
            result = new Rectangle2D.Double(area.getX(), area.getY(), this.left, area.getHeight());
        }
        else if (edge == RectangleEdge.RIGHT) {
            result = new Rectangle2D.Double(area.getMaxX() - this.right, area.getY(), this.right, area.getHeight());
        }
        return result;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    public boolean equals(final Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (object instanceof AxisSpace) {
            final AxisSpace s = (AxisSpace)object;
            final boolean b0 = this.top == s.top;
            final boolean b2 = this.bottom == s.bottom;
            final boolean b3 = this.left == s.left;
            final boolean b4 = this.right == s.right;
            return b0 && b2 && b3 && b4;
        }
        return false;
    }
    
    public String toString() {
        return super.toString() + "[left=" + this.left + ",right=" + this.right + ",top=" + this.top + ",bottom=" + this.bottom + "]";
    }
}
