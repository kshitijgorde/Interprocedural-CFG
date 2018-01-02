// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$Z6;

import java.awt.Rectangle;
import jlog.$Y_B.$Z_B;
import java.awt.Component;
import jlog.awt.$W4;
import java.util.Hashtable;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.LayoutManager;

public class $X_B implements LayoutManager, $S_B
{
    private Container $TR;
    private float $A1B;
    private float $B1B;
    private float $U_B;
    private float $V_B;
    private Dimension originalSize;
    public Hashtable $PI;
    private Object $C1B;
    
    public float $A7() {
        return this.$U_B;
    }
    
    public float $B7() {
        return this.$V_B;
    }
    
    public void $D1B(final Container $tr) {
        if (this.$TR != $tr) {
            this.$PI.clear();
            this.$TR = $tr;
        }
    }
    
    public boolean $E1B(final int n, final int n2) {
        return this.$E1B(new Dimension(n, n2));
    }
    
    public boolean $E1B(final Dimension dimension) {
        final Dimension size;
        synchronized (this.$C1B) {
            if (!this.originalSize.equals(dimension)) {
                this.originalSize = new Dimension(dimension.width, dimension.height);
            }
            size = this.$TR.getSize();
        }
        // monitorexit(this.$C1B)
        final Dimension $g1B = this.$G1B(dimension);
        new $W4(this.$TR).grow($g1B);
        if (!size.equals($g1B)) {
            this.$TR.setSize($g1B.width, $g1B.height);
            return true;
        }
        return false;
    }
    
    public Dimension $G1B(final Dimension dimension) {
        return new Dimension((int)(dimension.width * this.$U_B), (int)(dimension.height * this.$V_B));
    }
    
    public float $H1B(final Dimension dimension) {
        if (this.$TR == null) {
            return 1.0f;
        }
        synchronized (this.$C1B) {
            final Dimension size = this.$TR.getSize();
            if (size.height < 1 || size.width < 1) {
                // monitorexit(this.$C1B)
                return 1.0f;
            }
            // monitorexit(this.$C1B)
            return Math.min(dimension.width / (size.width / this.$U_B), dimension.height / (size.height / this.$V_B));
        }
    }
    
    public $Z_B $L1B(final Component component) {
        final $Z_B $z_B = this.$PI.get(component);
        if ($z_B != null) {
            return $z_B;
        }
        final Dimension size = component.getSize();
        if (size.width < 1 || size.height < 1) {
            final Dimension preferredSize = component.getPreferredSize();
            component.setBounds(0, 0, Math.min(preferredSize.width, 1), Math.min(preferredSize.height, 1));
        }
        final $Z_B $z_B2 = new $Z_B(component.getBounds());
        $z_B2.$Z6(1.0f / this.$A1B, 1.0f / this.$B1B);
        this.$PI.put(component, $z_B2);
        return $z_B2;
    }
    
    public boolean $T_B(float max, float max2) {
        max = Math.max(0.001f, max);
        max2 = Math.max(0.001f, max2);
        if (this.$U_B != max || this.$V_B != max2) {
            this.$U_B = max;
            this.$V_B = max2;
            final Dimension dimension;
            synchronized (this.$C1B) {
                dimension = new Dimension((int)(this.originalSize.width * max), (int)(this.originalSize.height * max2));
            }
            // monitorexit(this.$C1B)
            new $W4(this.$TR).grow(dimension);
            this.$TR.setSize(dimension.width, dimension.height);
            return true;
        }
        return false;
    }
    
    public Rectangle $V1B(final Rectangle rectangle) {
        return new Rectangle((int)(rectangle.x * this.$U_B), (int)(rectangle.y * this.$V_B), (int)(rectangle.width * this.$U_B), (int)(rectangle.height * this.$V_B));
    }
    
    public Dimension $W_B() {
        return new Dimension(this.originalSize.width, this.originalSize.height);
    }
    
    public $X_B(final Container container) {
        this.$TR = null;
        this.$A1B = 1.0f;
        this.$B1B = 1.0f;
        this.$U_B = this.$A1B;
        this.$V_B = this.$B1B;
        this.originalSize = new Dimension(200, 200);
        this.$C1B = new Object();
        this.$PI = new Hashtable();
        this.$D1B(container);
    }
    
    public void addLayoutComponent(final String s, final Component component) {
    }
    
    public void layoutContainer(final Container container) {
        final float $u_B;
        final float $v_B;
        synchronized (this.$C1B) {
            $u_B = this.$U_B;
            $v_B = this.$V_B;
        }
        // monitorexit(this.$C1B)
        if ($u_B == this.$A1B && $v_B == this.$B1B) {
            return;
        }
        final Component[] components = container.getComponents();
        int length = components.length;
        while (length-- != 0) {
            final Component component = components[length];
            final $Z_B $l1B = this.$L1B(component);
            final Rectangle bounds = component.getBounds();
            final Rectangle $s1B = $l1B.$S1B($u_B, $v_B);
            if (component instanceof $O_B) {
                final Rectangle bounds2 = component.getBounds();
                final int $r_B = (($O_B)component).$R_B();
                if (($r_B & 0x2) != 0x0) {
                    $s1B.width = bounds2.width;
                    $s1B.height = bounds2.height;
                }
                if (($r_B & 0x1) != 0x0) {
                    $s1B.x = bounds2.x;
                    $s1B.y = bounds2.y;
                }
            }
            if (!$s1B.equals(bounds)) {
                component.setBounds($s1B.x, $s1B.y, $s1B.width, $s1B.height);
            }
        }
        this.$A1B = $u_B;
        this.$B1B = $v_B;
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        return this.preferredLayoutSize(container);
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        final Dimension $g1B = this.$G1B(this.originalSize);
        new $W4(container).grow($g1B);
        return $g1B;
    }
    
    public void removeLayoutComponent(final Component component) {
        this.$PI.remove(component);
    }
}
