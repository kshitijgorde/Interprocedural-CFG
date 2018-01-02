// 
// Decompiled by Procyon v0.5.30
// 

package com.next.gt;

import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Vector;

public class DirtyRectSet
{
    private Vector rects;
    final int GLUE = 64;
    
    public DirtyRectSet() {
        this.rects = new Vector();
    }
    
    public DirtyRectSet(final int n) {
        this.rects = new Vector(n);
    }
    
    public void addRect(final Rectangle rectangle) {
        for (int size = this.rects.size(), i = 0; i < size; ++i) {
            if (rectangle.x > ((Rectangle)this.rects.elementAt(i)).x) {
                this.rects.insertElementAt(rectangle, i);
                return;
            }
        }
        this.rects.addElement(rectangle);
    }
    
    private final boolean closeEnough(final Rectangle rectangle, final Rectangle rectangle2) {
        rectangle.width += 64;
        rectangle.height += 64;
        rectangle2.width += 64;
        rectangle2.height += 64;
        final boolean intersects = rectangle.intersects(rectangle2);
        rectangle.width -= 64;
        rectangle.height -= 64;
        rectangle2.width -= 64;
        rectangle2.height -= 64;
        return intersects;
    }
    
    private void collapse() {
        int n = 0;
        if (this.rects.size() < 2) {
            return;
        }
        Rectangle union = this.rects.elementAt(n);
        Rectangle rectangle = this.rects.elementAt(n + 1);
        while (true) {
            if (this.closeEnough(union, rectangle)) {
                union = union.union(rectangle);
                this.rects.setElementAt(union, n);
                this.rects.removeElementAt(n + 1);
                if (n + 1 >= this.rects.size()) {
                    return;
                }
                rectangle = this.rects.elementAt(n + 1);
            }
            else {
                if (n + 2 >= this.rects.size()) {
                    return;
                }
                union = rectangle;
                rectangle = this.rects.elementAt(n + 2);
                ++n;
            }
        }
    }
    
    public void drawImage(final Graphics graphics, final Image image, final Gamelication gamelication) {
        this.collapse();
        for (int i = 0; i < this.rects.size(); ++i) {
            final Rectangle rectangle = this.rects.elementAt(i);
            final Graphics create = graphics.create(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            create.drawImage(image, -rectangle.x, -rectangle.y, (ImageObserver)gamelication);
            create.dispose();
        }
    }
}
