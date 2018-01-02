// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$Z7;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Component;

public class $FBB extends Component
{
    int width;
    int height;
    boolean $GBB;
    
    public $FBB(final int n) {
        this(n, false);
    }
    
    public $FBB(final int height, final boolean $gbb) {
        this.width = 2;
        this.height = height;
        this.$GBB = $gbb;
    }
    
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        final Color background = this.getBackground();
        if (background != null) {
            graphics.setColor(background);
        }
        graphics.draw3DRect(0, 0, size.width - 1, this.height - 1, this.$GBB);
    }
}
