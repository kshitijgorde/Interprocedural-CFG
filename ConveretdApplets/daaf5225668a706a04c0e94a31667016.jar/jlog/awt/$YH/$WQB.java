// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$YH;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import jlog.awt.$DPB.$EPB;
import java.awt.Container;
import java.awt.Component;

public class $WQB implements $R3
{
    Component $XQB;
    $YQB $ZQB;
    
    public void $S3(final $T3 $t3) {
        this.$XQB.setBackground($t3.getColor());
        this.$XQB.repaint();
    }
    
    public $WQB(final Container container) {
        this.$XQB = new $QQB();
        final $EPB $epb = new $EPB();
        (this.$ZQB = new $YQB($epb, false)).$W3(this);
        container.setLayout(new BorderLayout());
        container.add("North", this.$XQB);
        container.add("Center", $epb);
    }
    
    public Color getColor() {
        return this.$ZQB.getColor();
    }
    
    public void setColor(final Color color) {
        this.$ZQB.setColor(color);
        this.$XQB.setBackground(color);
        this.$XQB.repaint();
    }
}
