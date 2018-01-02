// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editorpanel;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;
import java.awt.Canvas;

public class ColorCanvas extends Canvas
{
    private Color bgColor;
    private Component host;
    private boolean isOver;
    
    ColorCanvas(final Color bgColor) {
        this.isOver = false;
        this.bgColor = bgColor;
    }
    
    public void setColor(final Color bgColor) {
        this.bgColor = bgColor;
        this.repaint();
    }
    
    public Color getColor() {
        return this.bgColor;
    }
    
    public void paint(final Graphics graphics) {
        final Rectangle bounds = this.bounds();
        graphics.setColor(this.bgColor);
        graphics.fillRect(0, 0, bounds.width - 1, bounds.height - 1);
        if (this.isOver) {
            graphics.setColor(Color.black);
            graphics.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }
}
