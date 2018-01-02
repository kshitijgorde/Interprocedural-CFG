// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$DPB;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Component;

public class $IPB extends Component
{
    boolean $NHB;
    Dimension preferredSize;
    
    public boolean $NHB() {
        return this.$NHB;
    }
    
    public void $PHB(final boolean $nhb) {
        if ($nhb != this.$NHB) {
            this.$NHB = $nhb;
            this.repaint();
        }
    }
    
    public $IPB() {
        this.$NHB = false;
        this.preferredSize = null;
    }
    
    public Dimension getPreferredSize() {
        final Dimension preferredSize = this.preferredSize;
        if (preferredSize == null) {
            return super.getPreferredSize();
        }
        return preferredSize;
    }
    
    public void paint(final Graphics graphics) {
        if (this.$NHB) {
            final Rectangle clipBounds = graphics.getClipBounds();
            graphics.setColor(this.getBackground());
            graphics.fillRect(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
        }
        graphics.setColor(this.getForeground());
        graphics.setFont(this.getFont());
    }
    
    public void setPreferredSize(final int n, final int n2) {
        this.preferredSize = new Dimension(n, n2);
    }
    
    public void setPreferredSize(final Dimension preferredSize) {
        this.preferredSize = preferredSize;
    }
}
