// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$B8;

import java.awt.Rectangle;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.CardLayout;
import java.awt.Panel;

class $KJB extends Panel
{
    public $KJB() {
        this.setLayout(new CardLayout(4, 4));
    }
    
    public $KJB(final Color background) {
        this();
        this.setBackground(background);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        final Container parent = this.getParent();
        graphics.setColor(Color.white);
        graphics.drawLine(0, size.height - 1, 0, 0);
        graphics.drawLine(1, 0, size.width - 2, 0);
        graphics.setColor(Color.black);
        graphics.drawLine(1, size.height - 1, size.width - 1, size.height - 1);
        graphics.drawLine(size.width - 1, 1, size.width - 1, size.height - 1);
        graphics.setColor(this.getBackground().darker());
        graphics.drawLine(size.width - 2, 1, size.width - 2, size.height - 2);
        final $GJB $rjb;
        if (parent instanceof $BJB && ($rjb = (($BJB)parent).$RJB()) != null) {
            final Rectangle bounds = $rjb.getBounds();
            graphics.setColor(this.getBackground());
            graphics.drawLine(bounds.x + 1, 0, bounds.x + bounds.width - 2, 0);
            if (bounds.x + bounds.width >= size.width) {
                graphics.setColor(Color.black);
                graphics.drawLine(size.width - 1, 0, size.width - 1, size.height - 1);
                graphics.setColor(this.getBackground().darker());
                graphics.drawLine(size.width - 2, 0, size.width - 2, size.height - 2);
            }
        }
        super.paint(graphics);
    }
}
