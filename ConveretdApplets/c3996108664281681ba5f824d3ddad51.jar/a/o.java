// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Canvas;

public final class o extends Canvas
{
    private int q;
    
    public final void paint(final Graphics graphics) {
        final Color background = this.getBackground();
        final int width = this.size().width;
        graphics.setColor(background.darker());
        graphics.drawLine(1, 0, width, 0);
        graphics.drawLine(0, 1, 0, 0);
        graphics.setColor(background.brighter());
        graphics.drawLine(width, 1, 1, 1);
        graphics.drawLine(width, 0, width, 1);
    }
    
    public final Dimension minimumSize() {
        return new Dimension(this.q, 2);
    }
    
    public final Dimension preferredSize() {
        return this.minimumSize();
    }
    
    private o(final int n) {
        this.resize(this.q = 50, 2);
    }
    
    public o() {
        this(50);
    }
}
