// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.b;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Canvas;

public class a extends Canvas
{
    private Dimension a;
    
    public Dimension getPreferredSize() {
        return this.a;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        final int height = this.getSize().height;
        graphics.setColor(Color.gray);
        graphics.drawLine(0, 1, 0, height);
        graphics.setColor(Color.gray.brighter().brighter());
        graphics.drawLine(1, 1, 1, height);
    }
    
    public a() {
        this.a = new Dimension(2, 15);
    }
}
