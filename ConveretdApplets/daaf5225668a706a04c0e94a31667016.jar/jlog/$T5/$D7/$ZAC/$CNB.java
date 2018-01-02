// 
// Decompiled by Procyon v0.5.30
// 

package jlog.$T5.$D7.$ZAC;

import java.awt.Rectangle;
import java.awt.Point;
import java.util.EventObject;

public class $CNB extends EventObject
{
    private Point start;
    private Point $KC;
    private int type;
    public static final int $GMD = 0;
    public static final int $HMD = 1;
    public static final int $IMD = 2;
    
    public Point $HNB() {
        return new Point(this.start);
    }
    
    public $CNB(final Object o, final Point point, final Point point2, final int type) {
        super(o);
        this.start = new Point(point);
        this.$KC = new Point(point2);
        this.type = type;
    }
    
    public Rectangle getBounds() {
        final int min = Math.min(this.start.x, this.$KC.x);
        final int min2 = Math.min(this.start.y, this.$KC.y);
        return new Rectangle(min, min2, Math.max(this.start.x, this.$KC.x) - min + 1, Math.max(this.start.y, this.$KC.y) - min2 + 1);
    }
    
    public Point getPosition() {
        return new Point(this.$KC);
    }
    
    public int getType() {
        return this.type;
    }
}
