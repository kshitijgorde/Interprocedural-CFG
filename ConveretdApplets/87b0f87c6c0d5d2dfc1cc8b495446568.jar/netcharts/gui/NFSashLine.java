// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.gui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Canvas;

public class NFSashLine extends Canvas
{
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int style;
    
    public NFSashLine(final int style) {
        this.style = style;
        this.resize(2, 2);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final Point location = this.location();
        this.x = location.x;
        this.y = location.y;
        final Dimension size = this.size();
        this.width = size.width;
        this.height = size.height;
        if (this.style == 2) {
            graphics.drawLine(0, 0, 0, this.height);
            graphics.drawLine(1, 0, 1, this.height);
        }
        else {
            graphics.drawLine(0, 0, this.width, 0);
            graphics.drawLine(0, 1, this.width, 1);
        }
    }
    
    public int width() {
        return this.width;
    }
    
    public int height() {
        return this.height;
    }
}
