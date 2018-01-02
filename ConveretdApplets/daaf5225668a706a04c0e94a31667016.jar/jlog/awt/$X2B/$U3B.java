// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$X2B;

import java.awt.Rectangle;
import jlog.awt.$XRB.$CSB;
import java.awt.LayoutManager;
import jlog.awt.layout.$M2B;
import jlog.awt.$XRB.$ASB;
import java.awt.Component;
import java.awt.Dimension;
import jlog.awt.layout.$Q2B;
import jlog.awt.$XRB.$ZRB;
import java.awt.Container;

public class $U3B extends Container
{
    private $ZRB $V3B;
    private $Q2B $W3B;
    private Container $X3B;
    private Container $Y3B;
    private $Y2B $Z3B;
    
    private $Y2B $A4B() {
        return new $Y2B() {
            public void $Z2B(final $A3B $a3B) {
                final Dimension screenSize = $a3B.getScreenSize();
                $U3B.this.$W3B.$T2B($U3B.this.$G4B(), screenSize.width, screenSize.height);
                $U3B.this.$V3B.$RSB($a3B.$F3B(), screenSize.width, screenSize.height);
            }
        };
    }
    
    public $Y2B $B4B() {
        return this.$Z3B;
    }
    
    public $ZRB $C4B() {
        return this.$V3B;
    }
    
    public void $D4B(final int n, final int n2) {
        this.$W3B.$T2B(this.$X3B, n, n2);
        final Container parent = this.getParent();
        if (parent != null) {
            parent.validate();
            parent.repaint();
        }
    }
    
    public void $E4B(final Component component) {
        component.setSize(0, 0);
        this.$Y3B.add(component);
        component.invalidate();
    }
    
    public void $F4B(final Component component) {
        this.$Y3B.remove(component);
    }
    
    private Container $G4B() {
        return this;
    }
    
    public static $ASB $YSB(final $H3B $h3B) {
        return new $H4B($h3B);
    }
    
    public $U3B() {
        (this.$V3B = new $ZRB()).setPreferredSize(200, 150);
        (this.$Y3B = new $EPB(new $M2B())).add(this.$V3B);
        this.setLayout(this.$W3B = new $Q2B());
        this.add(this.$Y3B);
        this.$Z3B = this.$A4B();
    }
    
    static class $H4B implements $ASB
    {
        $H3B $X2B;
        
        public void $BSB(final $CSB $csb) {
            final Dimension screenSize = this.$X2B.getScreenSize();
            final Rectangle $gsb = $csb.$GSB(screenSize.width, screenSize.height);
            this.$X2B.$P2B($gsb.x, $gsb.y);
        }
        
        $H4B(final $H3B $x2B) {
            this.$X2B = $x2B;
        }
    }
}
