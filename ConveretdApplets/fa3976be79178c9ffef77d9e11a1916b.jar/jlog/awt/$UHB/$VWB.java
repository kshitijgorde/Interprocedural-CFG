// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$UHB;

import java.awt.Rectangle;

public class $VWB
{
    private Object source;
    private Rectangle $RAB;
    private $XWB $YWB;
    
    public $XWB $ZWB() {
        return this.$YWB;
    }
    
    public $VWB(final Object source, final Rectangle rectangle, final $XWB $ywb) {
        this.$RAB = new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        this.source = source;
        this.$YWB = $ywb;
    }
    
    public Rectangle getClipRect() {
        return new Rectangle(this.$RAB.x, this.$RAB.y, this.$RAB.width, this.$RAB.height);
    }
    
    public Object getSource() {
        return this.source;
    }
}
