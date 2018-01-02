// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing.icon;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Component;
import javax.swing.Icon;

public class ResizingIcon implements Icon
{
    private static final long D = 6997945159902070664L;
    protected int B;
    protected int A;
    private LinkedIcon C;
    
    public ResizingIcon() {
        this.B = 256;
        this.A = 256;
        this.C = new LinkedIcon(this);
    }
    
    public int getHeight() {
        return this.A;
    }
    
    public void setHeight(final int a) {
        this.A = a;
    }
    
    public int getWidth() {
        return this.B;
    }
    
    public void setWidth(final int b) {
        this.B = b;
    }
    
    public void setSize(final int width, final int height) {
        this.setWidth(width);
        this.setHeight(height);
    }
    
    public int getIconHeight() {
        return this.A;
    }
    
    public int getIconWidth() {
        return this.B;
    }
    
    public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
    }
    
    public LinkedIcon getLinkedIcon() {
        return this.C;
    }
    
    public float getAlpha() {
        return this.C.getAlpha();
    }
    
    public int getHorizontalSpace() {
        return this.C.getHorizontalSpace();
    }
    
    public Rectangle getIconRect(final Component component, final Rectangle rectangle) {
        return this.C.getIconRect(component, rectangle);
    }
    
    public Rectangle getIconRect(final int n, final int n2, final Rectangle rectangle) {
        return this.C.getIconRect(n, n2, rectangle);
    }
    
    public int getVerticalSpace() {
        return this.C.getVerticalSpace();
    }
    
    public void setAlpha(final float alpha) {
        this.C.setAlpha(alpha);
    }
    
    public void setHorizontalSpace(final int horizontalSpace) {
        this.C.setHorizontalSpace(horizontalSpace);
    }
    
    public void setVerticalSpace(final int verticalSpace) {
        this.C.setVerticalSpace(verticalSpace);
    }
    
    public void paintIcon(final Component component, final Graphics graphics) {
        this.C.paintIcon(component, graphics);
    }
    
    public String getAnchor() {
        return this.C.getAnchor();
    }
    
    public void setAnchor(final String anchor) {
        this.C.setAnchor(anchor);
    }
}
