// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt;

import java.awt.Container;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Insets;

public class $W4 extends Insets
{
    public static final $W4 $X4;
    
    public Rectangle $AX(final Dimension dimension) {
        if (dimension != null) {
            dimension.width -= super.left + super.right;
            dimension.height -= super.top + super.bottom;
        }
        return new Rectangle(super.top, super.left, dimension.width, dimension.height);
    }
    
    public void $AX(final Rectangle rectangle) {
        if (rectangle != null) {
            rectangle.x += super.left;
            rectangle.y += super.top;
            rectangle.width -= super.left + super.right;
            rectangle.height -= super.top + super.bottom;
        }
    }
    
    public void $Y4(final Point point) {
        if (point != null) {
            point.x += super.left;
            point.y += super.top;
        }
    }
    
    public void $Z4(final Point point) {
        if (point != null) {
            point.x -= super.left;
            point.y -= super.top;
        }
    }
    
    static {
        $X4 = new $W4(0, 0, 0, 0);
    }
    
    public $W4(final int n, final int n2, final int n3, final int n4) {
        super(n, n2, n3, n4);
    }
    
    public $W4(final Container container) {
        this(container.insets());
    }
    
    public $W4(final Insets insets) {
        this(insets.top, insets.left, insets.bottom, insets.right);
    }
    
    public void grow(final Dimension dimension) {
        if (dimension != null) {
            dimension.width += super.left + super.right;
            dimension.height += super.top + super.bottom;
        }
    }
    
    public void grow(final Rectangle rectangle) {
        if (rectangle != null) {
            rectangle.x -= super.left;
            rectangle.y -= super.top;
            rectangle.width += super.left + super.right;
            rectangle.height += super.top + super.bottom;
        }
    }
}
