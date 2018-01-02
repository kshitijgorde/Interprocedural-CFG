// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$XRB;

import java.awt.Rectangle;

public class $CSB
{
    Object source;
    Rectangle $DSB;
    int $ESB;
    int $FSB;
    
    public Rectangle $GSB(final int n, final int n2) {
        final Rectangle rectangle = new Rectangle(this.$DSB.x, this.$DSB.y, this.$DSB.width, this.$DSB.height);
        if (n < 1 || n2 < 1 || this.$ESB < 1 || this.$FSB < 1 || rectangle.width < 1 || rectangle.height < 1) {
            return new Rectangle(0, 0, 0, 0);
        }
        final float n3 = n / this.$ESB;
        final float n4 = n2 / this.$FSB;
        return new Rectangle((int)(rectangle.x * n3), (int)(rectangle.y * n4), (int)(rectangle.width * n3), (int)(rectangle.height * n4));
    }
    
    public $CSB(final Object source, final Rectangle rectangle, final int $esb, final int $fsb) {
        this.source = source;
        this.$DSB = new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        this.$ESB = $esb;
        this.$FSB = $fsb;
    }
    
    public Object getSource() {
        return this.source;
    }
}
