// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.graphics;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Insets;

public class BorderRaisedWithLine extends Border
{
    private static Insets myInsets;
    private Color shadowColor;
    
    static {
        BorderRaisedWithLine.myInsets = new Insets(1, 1, 2, 2);
    }
    
    public BorderRaisedWithLine() {
        this(Color.lightGray);
    }
    
    public BorderRaisedWithLine(final Color background) {
        super(Color.black, background);
        this.shadowColor = background.darker();
    }
    
    public void paint(final Graphics g, final int x, final int y, final int width, final int height) {
        final Color c = g.getColor();
        int right = x + width - 1;
        int bottom = y + height - 1;
        g.setColor(super.foreground);
        g.drawLine(x, bottom, right, bottom);
        g.drawLine(right, y, right, bottom);
        --right;
        --bottom;
        g.setColor(Color.white);
        g.drawLine(x, y, right, y);
        g.drawLine(x, y, x, bottom);
        g.setColor(this.shadowColor);
        g.drawLine(x, bottom, right, bottom);
        g.drawLine(right, y, right, bottom);
        g.setColor(c);
    }
    
    protected void setInsets() {
        super.insets = BorderRaisedWithLine.myInsets;
    }
}
