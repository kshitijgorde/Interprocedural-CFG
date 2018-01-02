// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.graphics;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Insets;

public class BorderLowered2 extends Border
{
    private static Insets myInsets;
    
    static {
        BorderLowered2.myInsets = new Insets(2, 2, 2, 2);
    }
    
    public BorderLowered2() {
        this(Color.black);
    }
    
    public BorderLowered2(final Color foreground) {
        super(foreground, Color.lightGray);
    }
    
    public void paint(final Graphics g, final int x, final int y, final int width, final int height) {
        final Color c = g.getColor();
        g.setColor(Color.white);
        g.drawLine(x, y + height - 1, x + width - 1, y + height - 1);
        g.drawLine(x + width - 1, y, x + width - 1, y + height - 1);
        g.setColor(Color.lightGray);
        g.drawLine(x + 1, y + height - 2, x + width - 2, y + height - 2);
        g.drawLine(x + width - 2, y + 1, x + width - 2, y + height - 2);
        g.setColor(super.foreground);
        g.drawLine(x + 1, y + 1, x + 1, y + height - 3);
        g.drawLine(x + 1, y + 1, x + width - 3, y + 1);
        g.setColor(GraphicsUtil.brighter(super.foreground));
        g.drawLine(x, y, x, y + height - 2);
        g.drawLine(x, y, x + width - 2, y);
        g.setColor(c);
    }
    
    protected void setInsets() {
        super.insets = BorderLowered2.myInsets;
    }
}
