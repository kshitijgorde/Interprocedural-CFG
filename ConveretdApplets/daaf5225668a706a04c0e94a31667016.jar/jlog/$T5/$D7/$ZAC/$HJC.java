// 
// Decompiled by Procyon v0.5.30
// 

package jlog.$T5.$D7.$ZAC;

import java.util.Enumeration;
import jlog.$T5.util.$OKD;
import java.awt.Component;
import java.awt.Polygon;
import java.util.Vector;
import java.awt.Rectangle;
import jlog.awt.$V_;
import java.util.EventObject;

public class $HJC extends EventObject
{
    public static final int $CZC = 0;
    public static final int $DZC = 1;
    public static final int $ZYC = 2;
    public static final int $PUC = 3;
    public static final int $QUC = 4;
    public static final int $AZC = 5;
    public static final int $BZC = 6;
    public static final int COMPONENT_MOVED = 7;
    public static final int COMPONENT_RESIZED = 8;
    $V_ $W_;
    Rectangle bounds;
    Vector $JMD;
    Object source;
    int type;
    
    public Polygon $KCB() {
        return new $V_(this.$W_);
    }
    
    public $HJC(final Object o, final int n, final int n2, final int n3, final Component component) {
        this(o, n, component);
        this.bounds.setLocation(n2, n3);
    }
    
    public $HJC(final Object o, final int n, final Component component) {
        this(o, n, new $OKD(component));
    }
    
    public $HJC(final Object o, final int type, final Polygon polygon) {
        super(o);
        this.$W_ = null;
        this.bounds = null;
        this.$JMD = new Vector();
        this.type = type;
        this.bounds = polygon.getBounds();
        this.$W_ = new $V_(polygon);
    }
    
    public $HJC(final Object o, final int type, final Rectangle rectangle) {
        super(o);
        this.$W_ = null;
        this.bounds = null;
        this.$JMD = new Vector();
        this.type = type;
        this.bounds = new Rectangle(rectangle);
        this.$W_ = new $V_(rectangle);
    }
    
    public $HJC(final Object o, final int type, final Enumeration enumeration) {
        super(o);
        this.$W_ = null;
        this.bounds = null;
        this.$JMD = new Vector();
        this.type = type;
        this.bounds = new Rectangle();
        if (enumeration != null) {
            while (enumeration.hasMoreElements()) {
                final Component component = enumeration.nextElement();
                this.bounds = this.bounds.union(component.getBounds());
                this.$JMD.addElement(component);
            }
        }
        this.$W_ = new $V_(this.bounds);
    }
    
    public Rectangle getBounds() {
        return new Rectangle(this.bounds);
    }
    
    public Enumeration getComponents() {
        return this.$JMD.elements();
    }
    
    public int getType() {
        return this.type;
    }
}
