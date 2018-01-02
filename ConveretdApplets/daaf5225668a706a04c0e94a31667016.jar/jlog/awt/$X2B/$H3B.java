// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$X2B;

import java.awt.event.AdjustmentEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import jlog.awt.$UHB.$CRB;
import java.awt.Insets;
import jlog.$LM.$QM;
import jlog.$LM.$NO;
import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import jlog.$LM.$KR;
import jlog.awt.layout.$N2B;
import java.awt.Container;
import jlog.awt.$GRB.$SUB;
import java.awt.Dimension;
import java.awt.event.AdjustmentListener;
import java.awt.Panel;

public class $H3B extends Panel implements AdjustmentListener
{
    public static final int SCROLLBARS_NONE = 0;
    public static final int SCROLLBARS_ALWAYS = 1;
    public static final int SCROLLBARS_AUTO = 2;
    private Dimension $I4B;
    private $SUB $J4B;
    private $SUB $K4B;
    private Container $L4B;
    private Container $M4B;
    private int $N4B;
    private Container $J3B;
    Container $O3B;
    $N2B $N3B;
    private $KR $WM;
    
    public Rectangle $F3B() {
        final Point offset = this.getOffset();
        final Dimension screenSize = this.getScreenSize();
        final Dimension size = this.size();
        if (this.$L4B.getParent() == this) {
            final Dimension dimension = size;
            dimension.height -= this.$J4B.size().height;
        }
        if (this.$M4B.getParent() == this) {
            final Dimension dimension2 = size;
            dimension2.width -= this.$K4B.size().width;
        }
        size.width = Math.min(size.width, screenSize.width);
        size.height = Math.min(size.height, screenSize.height);
        return new Rectangle(offset, size).intersection(new Rectangle(0, 0, screenSize.width, screenSize.height));
    }
    
    public void $K3B(final Container $j3B) {
        if ($j3B == this.$J3B) {
            return;
        }
        if (this.$J3B != null) {
            this.$O3B.remove(this.$J3B);
        }
        if ((this.$J3B = $j3B) != null) {
            this.$O3B.add($j3B);
        }
        this.layout();
    }
    
    public void $L3B(final int n, final int n2) {
        final Dimension size = this.$J3B.size();
        if (size.width != n || size.height != n2) {
            this.$J3B.resize(n, n2);
        }
    }
    
    public void $L3B(final Dimension dimension) {
        this.$L3B(dimension.width, dimension.height);
    }
    
    public void $O4B(final int $n4B) {
        if (this.$N4B != $n4B) {
            this.$N4B = $n4B;
            if (this.$T4B()) {
                this.validate();
            }
        }
    }
    
    public boolean $P2B(final int n, final int n2) {
        if (this.$N3B.$P2B(this.$O3B, n, n2)) {
            this.$N3B.layoutContainer(this.$O3B);
            this.$P3B();
            this.$PB();
            return true;
        }
        return false;
    }
    
    public boolean $P2B(final Point point) {
        return this.$P2B(point.x, point.y);
    }
    
    void $P3B() {
        final Rectangle $f3B = this.$F3B();
        final Dimension screenSize = this.getScreenSize();
        final Point offset = this.getOffset();
        final int max = Math.max(screenSize.width - $f3B.width, 0);
        final int max2 = Math.max(screenSize.height - $f3B.height, 0);
        if (offset.x > max || offset.y > max2) {
            offset.x = Math.min(offset.x, max);
            offset.y = Math.min(offset.y, max2);
            this.$N3B.$P2B(this.$O3B, offset);
        }
        this.$J4B.setValues(offset.x, $f3B.width, 0, screenSize.width - 1);
        this.$K4B.setValues(offset.y, $f3B.height, 0, screenSize.height - 1);
    }
    
    public Container $P4B() {
        return this.$J3B;
    }
    
    void $PB() {
        this.$WM.$PR(new $A3B(this, this.$J3B, this.getScreenSize(), this.$F3B()));
    }
    
    public void $Q4B(final $Y2B $y2B) {
        this.$WM.$AU($y2B, new $A3B(this, this.$J3B, this.getScreenSize(), this.$F3B()));
    }
    
    public void $R4B(final $Y2B $y2B) {
        this.$WM.$BU($y2B);
    }
    
    public void $S3B(final int n, final int n2) {
        final Rectangle $f3B = this.$F3B();
        this.$P2B(new Point(n - $f3B.width / 2, n2 - $f3B.height / 2));
    }
    
    public void $S3B(final Point point) {
        this.$S3B(point.x, point.y);
    }
    
    public void $S4B(final Dimension dimension) {
        this.$I4B.width = dimension.width;
        this.$I4B.height = dimension.height;
    }
    
    boolean $T4B() {
        final boolean b = this.$L4B.getParent() == this;
        final boolean b2 = this.$M4B.getParent() == this;
        boolean b3 = b;
        boolean b4 = b2;
        final int $n4B = this.$N4B;
        if ($n4B == 1) {
            b3 = true;
            b4 = true;
        }
        else if ($n4B == 0) {
            b3 = false;
            b4 = false;
        }
        else if ($n4B == 2) {
            final Dimension screenSize = this.getScreenSize();
            final Dimension size = this.size();
            final Insets insets = this.insets();
            b3 = (screenSize.width > size.width - insets.left - insets.right);
            b4 = (screenSize.height > size.height - insets.top - insets.bottom);
        }
        boolean b5 = false;
        if (b != b3) {
            if (b3) {
                super.add("South", this.$L4B);
            }
            else {
                super.remove(this.$L4B);
            }
            b5 = true;
        }
        if (b2 != b4) {
            if (b4) {
                super.add("East", this.$M4B);
            }
            else {
                super.remove(this.$M4B);
            }
            b5 = true;
        }
        return b5;
    }
    
    private boolean $U4B() {
        final Rectangle $f3B = this.$F3B();
        final Dimension screenSize = this.getScreenSize();
        final Point offset = this.getOffset();
        boolean b = false;
        final int max = Math.max(screenSize.width - $f3B.width, 0);
        final int max2 = Math.max(screenSize.height - $f3B.height, 0);
        if (offset.x > max || offset.y > max2) {
            offset.x = Math.min(offset.x, max);
            offset.y = Math.min(offset.y, max2);
            b = true;
            this.$N3B.$P2B(this.$O3B, offset);
        }
        return b;
    }
    
    public $H3B() {
        this(2);
    }
    
    public $H3B(final int n) {
        this.$N4B = 2;
        this.$J3B = null;
        (this.$WM = new $KR(1)).$VM(this);
        this.$I4B = new Dimension(160, 160);
        this.$O3B = new $CRB();
        this.$N3B = new $N2B();
        this.$O3B.setLayout(this.$N3B);
        this.$J4B = new $SUB(0);
        (this.$L4B = new $CRB(new GridLayout(1, 1))).add(this.$J4B);
        this.$K4B = new $SUB(1);
        (this.$M4B = new $CRB(new GridLayout(1, 1))).add(this.$K4B);
        this.setUnitIncrement(0);
        super.setLayout(new BorderLayout());
        super.add("Center", this.$O3B);
        this.$O4B(n);
        this.$J4B.addAdjustmentListener(this);
        this.$K4B.addAdjustmentListener(this);
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        final Object source = adjustmentEvent.getSource();
        final int value = adjustmentEvent.getValue();
        final Point offset = this.$N3B.getOffset();
        if (source == this.$J4B) {
            offset.x = value;
        }
        else if (source == this.$K4B) {
            offset.y = value;
        }
        this.$P2B(offset);
    }
    
    public Point getOffset() {
        return this.$N3B.getOffset();
    }
    
    public Dimension getScreenSize() {
        final Container $j3B = this.$J3B;
        if ($j3B == null) {
            return new Dimension(0, 0);
        }
        return $j3B.size();
    }
    
    public void layout() {
        this.$F3B();
        final boolean b = false | this.$T4B();
        super.layout();
        final boolean b2 = b | this.$U4B();
        this.$P3B();
        this.$PB();
    }
    
    public Dimension minimumSize() {
        return this.$I4B;
    }
    
    public Dimension preferredSize() {
        return this.$I4B;
    }
    
    public void setLayout(final LayoutManager layoutManager) {
        if (this.$J3B != null) {
            throw new RuntimeException("can not set Layout for Viewport");
        }
    }
    
    public void setUnitIncrement(final int n) {
        this.$J4B.setUnitIncrement(n);
        this.$K4B.setUnitIncrement(n);
    }
}
