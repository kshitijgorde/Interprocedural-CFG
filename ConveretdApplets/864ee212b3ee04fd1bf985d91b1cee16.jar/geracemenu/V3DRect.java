// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu;

import java.awt.Color;
import java.awt.Graphics;

public class V3DRect extends VRect
{
    protected synchronized void paintOn(final Graphics graphics) {
        super.paintOn(graphics);
        this.paintBorder(graphics);
    }
    
    private void paintBorder(final Graphics graphics) {
        graphics.setColor(this.getForeground().brighter());
        graphics.drawLine(0, 0, 0, this.getHeight());
        graphics.drawLine(1, 1, 1, this.getHeight() - 1);
        graphics.drawLine(1, 0, this.getWidth(), 0);
        graphics.drawLine(2, 1, this.getWidth() - 1, 1);
        graphics.setColor(this.getForeground().darker());
        graphics.drawLine(this.getWidth(), 0, this.getWidth(), this.getHeight());
        graphics.drawLine(this.getWidth() - 1, 1, this.getWidth() - 1, this.getHeight() - 1);
        graphics.drawLine(0, this.getHeight(), this.getWidth(), this.getHeight());
        graphics.drawLine(1, this.getHeight() - 1, this.getWidth() - 1, this.getHeight() - 1);
    }
    
    public V3DRect() {
    }
    
    public V3DRect(final Color color) {
        super(color);
    }
}
