// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;

public class ao extends aY
{
    protected Color color;
    protected int left;
    protected int right;
    protected int top;
    protected int bottom;
    
    public ao(final int top, final int left, final int bottom, final int right, final Color color) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
        this.color = color;
    }
    
    public void paintBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final Insets borderInsets = this.getBorderInsets(component);
        final Color color = graphics.getColor();
        graphics.translate(n, n2);
        if (this.color != null) {
            graphics.setColor(this.color);
            graphics.fillRect(0, 0, n3 - borderInsets.right, borderInsets.top);
            graphics.fillRect(0, borderInsets.top, borderInsets.left, n4 - borderInsets.top);
            graphics.fillRect(borderInsets.left, n4 - borderInsets.bottom, n3 - borderInsets.left, borderInsets.bottom);
            graphics.fillRect(n3 - borderInsets.right, 0, borderInsets.right, n4 - borderInsets.bottom);
        }
        graphics.translate(-n, -n2);
        graphics.setColor(color);
    }
    
    public Insets getBorderInsets(final Component component) {
        return this.getBorderInsets();
    }
    
    public Insets getBorderInsets() {
        return this.a(new Insets(0, 0, 0, 0));
    }
    
    private Insets a(final Insets insets) {
        insets.left = this.left;
        insets.top = this.top;
        insets.right = this.right;
        insets.bottom = this.bottom;
        return insets;
    }
}
