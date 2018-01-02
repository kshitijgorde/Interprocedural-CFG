// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$ZMB;

import java.awt.Component;
import java.awt.Point;

public class $CNB
{
    Object source;
    Point start;
    Point $GNB;
    Component $OU;
    int modifiers;
    
    public Point $HNB() {
        return new Point(this.start.x, this.start.y);
    }
    
    public Point $INB() {
        return new Point(this.$GNB.x - this.start.x, this.$GNB.y - this.start.y);
    }
    
    public $CNB(final Object source, final Point start, final Point $gnb, final Component $ou, final int modifiers) {
        this.source = source;
        this.start = start;
        this.$GNB = $gnb;
        this.$OU = $ou;
        this.modifiers = modifiers;
    }
    
    public Point getActual() {
        return new Point(this.$GNB.x, this.$GNB.y);
    }
    
    public Component getComponent() {
        return this.$OU;
    }
    
    public int getModifiers() {
        return this.modifiers;
    }
    
    public Object getSource() {
        return this.source;
    }
}
