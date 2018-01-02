// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$DPB;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Container;

public class $EPB extends Container
{
    private boolean $NHB;
    
    public boolean $NHB() {
        return this.$NHB;
    }
    
    public void $PHB(final boolean $nhb) {
        if ($nhb != this.$NHB) {
            this.$NHB = $nhb;
            this.repaint();
        }
    }
    
    public $EPB() {
        this.$NHB = false;
        this.setLayout(new FlowLayout());
    }
    
    public $EPB(final LayoutManager layout) {
        this.$NHB = false;
        this.setLayout(layout);
    }
    
    public void paint(final Graphics graphics) {
        if (this.$NHB) {
            final Rectangle clipBounds = graphics.getClipBounds();
            graphics.setColor(this.getBackground());
            graphics.fillRect(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
        }
        graphics.setColor(this.getForeground());
        graphics.setFont(this.getFont());
        super.paint(graphics);
    }
}
