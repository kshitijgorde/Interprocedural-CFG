// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;

public class StackLayout extends Layout
{
    public int marginWidth;
    public int marginHeight;
    public Control topControl;
    
    public StackLayout() {
        this.marginWidth = 0;
        this.marginHeight = 0;
    }
    
    protected Point computeSize(final Composite composite, final int n, final int n2, final boolean b) {
        final Control[] children = composite.getChildren();
        int max = 0;
        int max2 = 0;
        for (int i = 0; i < children.length; ++i) {
            final Point computeSize = children[i].computeSize(n, n2, b);
            max = Math.max(computeSize.x, max);
            max2 = Math.max(computeSize.y, max2);
        }
        int n3 = max + 2 * this.marginWidth;
        int n4 = max2 + 2 * this.marginHeight;
        if (n != -1) {
            n3 = n;
        }
        if (n2 != -1) {
            n4 = n2;
        }
        return new Point(n3, n4);
    }
    
    protected boolean flushCache(final Control control) {
        return true;
    }
    
    protected void layout(final Composite composite, final boolean b) {
        final Control[] children = composite.getChildren();
        final Rectangle clientArea;
        final Rectangle bounds = clientArea = composite.getClientArea();
        clientArea.x += this.marginWidth;
        final Rectangle rectangle = bounds;
        rectangle.y += this.marginHeight;
        final Rectangle rectangle2 = bounds;
        rectangle2.width -= 2 * this.marginWidth;
        final Rectangle rectangle3 = bounds;
        rectangle3.height -= 2 * this.marginHeight;
        for (int i = 0; i < children.length; ++i) {
            children[i].setBounds(bounds);
            children[i].setVisible(children[i] == this.topControl);
        }
    }
    
    String getName() {
        final String name = this.getClass().getName();
        final int lastIndex = name.lastIndexOf(46);
        if (lastIndex == -1) {
            return name;
        }
        return name.substring(lastIndex + 1, name.length());
    }
    
    public String toString() {
        String s = String.valueOf(this.getName()) + " {";
        if (this.marginWidth != 0) {
            s = String.valueOf(s) + "marginWidth=" + this.marginWidth + " ";
        }
        if (this.marginHeight != 0) {
            s = String.valueOf(s) + "marginHeight=" + this.marginHeight + " ";
        }
        if (this.topControl != null) {
            s = String.valueOf(s) + "topControl=" + this.topControl + " ";
        }
        return String.valueOf(s.trim()) + "}";
    }
}
