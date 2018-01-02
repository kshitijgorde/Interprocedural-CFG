// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.border;

import java.awt.Graphics;
import java.awt.Component;
import java.awt.Insets;
import java.io.Serializable;

public class EmptyBorder extends AbstractBorder implements Serializable
{
    protected int left;
    protected int right;
    protected int top;
    protected int bottom;
    
    public EmptyBorder(final int top, final int left, final int bottom, final int right) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }
    
    public EmptyBorder(final Insets insets) {
        this.top = insets.top;
        this.right = insets.right;
        this.bottom = insets.bottom;
        this.left = insets.left;
    }
    
    public Insets getBorderInsets(final Component component) {
        return new Insets(this.top, this.left, this.bottom, this.right);
    }
    
    public Insets getBorderInsets(final Component component, final Insets insets) {
        insets.left = this.left;
        insets.top = this.top;
        insets.right = this.right;
        insets.bottom = this.bottom;
        return insets;
    }
    
    public boolean isBorderOpaque() {
        return false;
    }
    
    public void paintBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
    }
}
