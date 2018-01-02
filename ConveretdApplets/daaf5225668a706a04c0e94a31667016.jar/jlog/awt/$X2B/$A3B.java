// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$X2B;

import jlog.$LM.$QM;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Container;
import jlog.$LM.$NO;

public class $A3B extends $NO
{
    private Container $RD;
    private Rectangle $C3B;
    private Dimension $VNB;
    
    private Dimension $D3B(final Dimension dimension) {
        if (dimension == null) {
            return dimension;
        }
        return new Dimension(dimension.width, dimension.height);
    }
    
    private Rectangle $E3B(final Rectangle rectangle) {
        if (rectangle == null) {
            return rectangle;
        }
        return new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public Rectangle $F3B() {
        return this.$E3B(this.$C3B);
    }
    
    public void $PO(final $QM $qm) {
        (($Y2B)$qm).$Z2B(this);
    }
    
    public $A3B(final Object o, final Container $rd, final Dimension dimension, final Rectangle rectangle) {
        super(o);
        this.$RD = $rd;
        this.$VNB = this.$D3B(dimension);
        this.$C3B = this.$E3B(rectangle);
    }
    
    public Container getContainer() {
        return this.$RD;
    }
    
    public Dimension getScreenSize() {
        return this.$D3B(this.$VNB);
    }
}
