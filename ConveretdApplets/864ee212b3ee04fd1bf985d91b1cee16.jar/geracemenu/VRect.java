// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu;

import java.awt.Color;
import java.awt.Graphics;

public class VRect extends TTComponent
{
    protected synchronized void paintOn(final Graphics graphics) {
        graphics.setColor(this.getForeground());
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
    
    public VRect() {
        this.setForeground(Color.lightGray);
    }
    
    public VRect(final Color foreground) {
        this.setForeground(foreground);
    }
}
