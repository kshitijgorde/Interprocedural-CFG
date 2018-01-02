// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.graphics;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Insets;

public class BorderSingle extends Border
{
    private static Insets myInsets;
    
    static {
        BorderSingle.myInsets = new Insets(1, 1, 1, 1);
    }
    
    public BorderSingle() {
        super(Color.black, Color.white);
    }
    
    public BorderSingle(final Color foreground) {
        super(foreground, Color.white);
    }
    
    public void paint(final Graphics g, final int x, final int y, final int width, final int height) {
        final Color c = g.getColor();
        g.setColor(super.foreground);
        g.drawRect(x, y, width - 1, height - 1);
        g.setColor(c);
    }
    
    protected void setInsets() {
        super.insets = BorderSingle.myInsets;
    }
}
