// 
// Decompiled by Procyon v0.5.30
// 

package javaviewer;

import java.awt.Rectangle;

public class MSRectangle extends Rectangle
{
    protected Rectangle rectangle;
    
    public MSRectangle(final int n, final int n2, final int n3, final int n4) {
        this.rectangle = new Rectangle(n, n2, n3, n4);
    }
    
    public double getWidth() {
        return this.rectangle.getSize().width;
    }
    
    public double getHeight() {
        return this.rectangle.getSize().height;
    }
    
    public String toString() {
        return this.getClass().getName() + "[x=" + this.rectangle.x + ",y=" + this.rectangle.y + ",width=" + this.rectangle.width + ",height=" + this.rectangle.height + "]";
    }
}
