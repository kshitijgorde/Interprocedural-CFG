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
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.getClass().getName()))).append("[x=").append(this.rectangle.x).append(",y=").append(this.rectangle.y).append(",width=").append(this.rectangle.width).append(",height=").append(this.rectangle.height).append("]")));
    }
}
