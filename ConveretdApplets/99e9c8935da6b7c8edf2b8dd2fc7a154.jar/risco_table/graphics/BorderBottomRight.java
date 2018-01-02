// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.graphics;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Insets;

public class BorderBottomRight extends Border
{
    private static Insets myInsets;
    
    static {
        BorderBottomRight.myInsets = new Insets(0, 0, 1, 1);
    }
    
    public BorderBottomRight() {
        this(Color.gray);
    }
    
    public BorderBottomRight(final Color foreground) {
        super(foreground, Color.white);
    }
    
    public void paint(final Graphics g, final int x, final int y, final int width, final int height) {
        final Color c = g.getColor();
        g.setColor(super.foreground);
        g.drawLine(x, y + height - 1, x + width - 1, y + height - 1);
        g.drawLine(x + width - 1, y, x + width - 1, y + height - 1);
        g.setColor(c);
    }
    
    protected void setInsets() {
        super.insets = BorderBottomRight.myInsets;
    }
}
