import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class BorderPanel extends Panel
{
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.gray);
        graphics.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
    }
}
