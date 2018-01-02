import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetTopLinePanel extends Panel
{
    int mWidth;
    int mHeight;
    Dimension d;
    
    public void paint(final Graphics graphics) {
        if (!this.isVisible()) {
            return;
        }
        this.d = this.size();
        this.mWidth = this.d.width;
        this.mHeight = this.d.height;
        graphics.setColor(IRCQNetColors.controlColor.darker());
        graphics.drawLine(0, 0, this.mWidth, 0);
        graphics.setColor(Color.white);
        graphics.drawLine(0, 1, this.mWidth, 1);
    }
}
