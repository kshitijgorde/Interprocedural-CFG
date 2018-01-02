// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Component;
import javax.swing.JScrollPane;

final class q extends JScrollPane
{
    private final EventimApplet a;
    
    q(final EventimApplet a, final Component component) {
        super(component);
        this.a = a;
    }
    
    public final void paint(final Graphics graphics) {
        super.paint(graphics);
        if (this.getVerticalScrollBar().isShowing()) {
            final Rectangle bounds = this.getVerticalScrollBar().getBounds();
            graphics.setColor(EventimApplet.b(this.a).C());
            graphics.fillRect(bounds.x, 0, bounds.x + bounds.width, bounds.y);
        }
    }
}
