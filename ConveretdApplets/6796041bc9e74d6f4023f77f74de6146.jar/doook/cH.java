// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Canvas;

public class cH extends Canvas
{
    private int g;
    
    public void paint(final Graphics graphics) {
        final Color background = this.getBackground();
        final int width = this.size().width;
        graphics.setColor(background.darker());
        graphics.drawLine(1, 0, width, 0);
        graphics.drawLine(0, 1, 0, 0);
        graphics.setColor(background.brighter());
        graphics.drawLine(width, 1, 1, 1);
        graphics.drawLine(width, 0, width, 1);
    }
    
    public Dimension minimumSize() {
        return new Dimension(this.g, 2);
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public cH(final int g) {
        this.resize(this.g = g, 2);
    }
    
    public cH() {
        this(50);
    }
}
