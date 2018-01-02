import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class BlacklinedPanel extends Panel
{
    int \u00c0;
    int \u00c1;
    
    BlacklinedPanel() {
        this.\u00c0 = 0;
        this.\u00c1 = 0;
    }
    
    BlacklinedPanel(final int \u00e0, final int \u00e1) {
        this.\u00c0 = \u00e0;
        this.\u00c1 = \u00e1;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, this.\u00c0 - 1, this.\u00c1 - 1);
    }
}
