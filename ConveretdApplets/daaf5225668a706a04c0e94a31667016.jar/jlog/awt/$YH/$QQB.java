// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$YH;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Component;

class $QQB extends Component
{
    boolean $RQB;
    
    $QQB() {
        this.$RQB = false;
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(48, 20);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        graphics.setColor(this.getBackground());
        graphics.fillRect(2, 2, size.width - 5, size.height - 5);
        graphics.draw3DRect(2, 2, size.width - 6, size.height - 6, this.$RQB);
    }
}
