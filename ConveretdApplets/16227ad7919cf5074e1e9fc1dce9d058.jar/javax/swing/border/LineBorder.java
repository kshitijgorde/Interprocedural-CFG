// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.border;

import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Color;

public class LineBorder extends AbstractBorder
{
    private static Border blackLine;
    private static Border grayLine;
    protected int thickness;
    protected Color lineColor;
    protected boolean roundedCorners;
    
    public LineBorder(final Color color) {
        this(color, 1, false);
    }
    
    public LineBorder(final Color color, final int n) {
        this(color, n, false);
    }
    
    LineBorder(final Color lineColor, final int thickness, final boolean roundedCorners) {
        this.lineColor = lineColor;
        this.thickness = thickness;
        this.roundedCorners = roundedCorners;
    }
    
    public static Border createBlackLineBorder() {
        if (LineBorder.blackLine == null) {
            LineBorder.blackLine = new LineBorder(Color.black, 1);
        }
        return LineBorder.blackLine;
    }
    
    public static Border createGrayLineBorder() {
        if (LineBorder.grayLine == null) {
            LineBorder.grayLine = new LineBorder(Color.gray, 1);
        }
        return LineBorder.grayLine;
    }
    
    public Insets getBorderInsets(final Component component) {
        return new Insets(this.thickness, this.thickness, this.thickness, this.thickness);
    }
    
    public Insets getBorderInsets(final Component component, final Insets insets) {
        final int thickness = this.thickness;
        insets.bottom = thickness;
        insets.right = thickness;
        insets.top = thickness;
        insets.left = thickness;
        return insets;
    }
    
    public Color getLineColor() {
        return this.lineColor;
    }
    
    public int getThickness() {
        return this.thickness;
    }
    
    public boolean isBorderOpaque() {
        return true;
    }
    
    public void paintBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final Color color = graphics.getColor();
        graphics.setColor(this.lineColor);
        for (int i = 0; i < this.thickness; ++i) {
            if (!this.roundedCorners) {
                graphics.drawRect(n + i, n2 + i, n3 - i - i - 1, n4 - i - i - 1);
            }
            else {
                graphics.drawRoundRect(n + i, n2 + i, n3 - i - i - 1, n4 - i - i - 1, this.thickness, this.thickness);
            }
        }
        graphics.setColor(color);
    }
}
