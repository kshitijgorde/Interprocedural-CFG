// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Panel;

public final class U extends Panel
{
    private Color q;
    
    public U(final cw cw) {
        this.q = cw.q("mi", 0, 0);
    }
    
    public final void paint(Graphics graphics) {
        final int width = this.getSize().width;
        this.getSize();
        (graphics = graphics).setColor(this.q.darker());
        graphics.fillRect(0, 0, width, 3);
        graphics.setColor(this.q.brighter());
        graphics.fillRect(3, 2, width, 2);
        graphics.setColor(this.q);
        graphics.fillRect(2, 1, width, 1);
    }
}
