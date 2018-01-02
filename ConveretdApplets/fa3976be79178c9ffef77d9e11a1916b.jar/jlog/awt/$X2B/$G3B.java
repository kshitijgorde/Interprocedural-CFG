// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$X2B;

import java.awt.Color;
import java.awt.LayoutManager;
import jlog.awt.$UHB.$HYB;
import jlog.$Y_B.$Z_B;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Container;
import java.awt.Dimension;
import jlog.awt.$Z6.$X_B;

public class $G3B extends $H3B
{
    private $X_B $E2B;
    
    public float $A7() {
        return this.$E2B.$A7();
    }
    
    public float $B7() {
        return this.$E2B.$B7();
    }
    
    public Dimension $G1B(final int n, final int n2) {
        return new Dimension((int)(n * this.$A7()), (int)(n2 * this.$B7()));
    }
    
    public Dimension $G1B(final Dimension dimension) {
        return this.$G1B(dimension.width, dimension.height);
    }
    
    public void $K3B(final Container container) {
        throw new RuntimeException("setScreenContainer disabled");
    }
    
    public void $L3B(final int n, final int n2) {
        this.$E2B.$E1B((int)(n / this.$A7()), (int)(n2 / this.$B7()));
    }
    
    public void $L3B(final Dimension dimension) {
        this.$L3B(dimension.width, dimension.height);
    }
    
    public $X_B $Q3B() {
        return this.$E2B;
    }
    
    public void $R3B(final int n, final int n2, final int n3, final int n4) {
        this.$S3B(n + n3 / 2, n2 + n4 / 2);
    }
    
    public void $R3B(final Rectangle rectangle) {
        if (rectangle != null) {
            this.$R3B(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
    }
    
    public Point $T3B(final int n, final int n2) {
        return new Point((int)(n * this.$A7()), (int)(n2 * this.$B7()));
    }
    
    public Point $T3B(final Point point) {
        return new Point((int)(point.x * this.$A7()), (int)(point.y * this.$B7()));
    }
    
    public void $T_B(final float n, final float n2) {
        final Rectangle $f3B = this.$F3B();
        final Point point = new Point((int)(($f3B.x + $f3B.width / 2) / this.$A7()), (int)(($f3B.y + $f3B.height / 2) / this.$B7()));
        if (this.$E2B.$T_B(n, n2)) {
            final Rectangle $f3B2 = this.$F3B();
            point.x = (int)(point.x * n - $f3B2.width / 2);
            point.y = (int)(point.y * n2 - $f3B2.height / 2);
            if (super.$N3B.$P2B(super.$O3B, point)) {
                super.$N3B.layoutContainer(super.$O3B);
            }
            this.validate();
            super.$P3B();
            super.$PB();
            super.$O3B.repaint();
        }
    }
    
    public Rectangle $V1B(final int n, final int n2, final int n3, final int n4) {
        return new $Z_B(n, n2, n3, n4).$S1B(this.$A7(), this.$B7());
    }
    
    public Rectangle $V1B(final Rectangle rectangle) {
        return new $Z_B(rectangle).$S1B(this.$A7(), this.$B7());
    }
    
    public $G3B() {
        this(2);
    }
    
    public $G3B(final int n) {
        super(n);
        this.$E2B = null;
        final $HYB $hyb = new $HYB();
        $hyb.$IYB = 3000L;
        $hyb.setLayout(this.$E2B = new $X_B($hyb));
        $hyb.setBackground(Color.white);
        $hyb.setForeground(Color.black);
        super.$K3B($hyb);
        this.$L3B(640, 640);
    }
    
    public Dimension getScreenSize() {
        return this.$G1B(this.$E2B.$W_B());
    }
    
    public void layout() {
        super.layout();
    }
}
