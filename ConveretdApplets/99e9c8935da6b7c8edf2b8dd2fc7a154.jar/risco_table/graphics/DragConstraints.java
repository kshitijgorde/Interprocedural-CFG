// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.graphics;

import java.awt.Point;

public class DragConstraints
{
    int left;
    int right;
    int top;
    int bottom;
    
    public DragConstraints(final int left, final int right, final int top, final int bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }
    
    public Point dragBoth(final int x, final int y) {
        return new Point(this.dragHorizontally(x), this.dragVertically(y));
    }
    
    public int dragHorizontally(final int x) {
        if (x < this.left) {
            return this.left;
        }
        if (x > this.right) {
            return this.right;
        }
        return x;
    }
    
    public int dragVertically(final int y) {
        if (y < this.top) {
            return this.top;
        }
        if (y > this.bottom) {
            return this.bottom;
        }
        return y;
    }
}
