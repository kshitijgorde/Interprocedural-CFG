// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.layout;

import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

public class $Q2B implements LayoutManager
{
    private float wh;
    
    private float $S2B(final Dimension dimension) {
        if (dimension == null || dimension.width == 0 || dimension.height == 0) {
            return 1.0f;
        }
        return Math.abs(dimension.width / dimension.height);
    }
    
    public void $T2B(final Container container, final int n, final int n2) {
        this.$T2B(container, new Dimension(n, n2));
    }
    
    public void $T2B(final Container container, final Dimension dimension) {
        final float $s2B = this.$S2B(dimension);
        if ($s2B == this.wh) {
            return;
        }
        this.wh = $s2B;
        this.layoutContainer(container);
    }
    
    public float $U2B() {
        return this.wh;
    }
    
    private Dimension $V2B(final Dimension dimension, final Insets insets) {
        dimension.width = dimension.width + insets.left + insets.right;
        dimension.height = dimension.height + insets.top + insets.bottom;
        return dimension;
    }
    
    private Dimension $W2B(final Dimension dimension, final Insets insets) {
        dimension.width = dimension.width + insets.left + insets.right;
        dimension.height = dimension.height + insets.top + insets.bottom;
        return dimension;
    }
    
    public $Q2B() {
        this(1, 1);
    }
    
    public $Q2B(final int n, final int n2) {
        this.wh = 1.0f;
        this.wh = this.$S2B(new Dimension(n, n2));
    }
    
    public $Q2B(final Dimension dimension) {
        this(dimension.width, dimension.height);
    }
    
    public void addLayoutComponent(final String s, final Component component) {
    }
    
    public void layoutContainer(final Container container) {
        final Component[] components = container.getComponents();
        final Dimension size = container.getSize();
        final float wh = this.wh;
        for (int i = 0; i < components.length; ++i) {
            final Component component = components[i];
            final float n = size.height * wh;
            final float n2 = size.height;
            final float max = Math.max(n / size.width, n2 / size.height);
            final int n3 = (int)(n / max);
            final int n4 = (int)(n2 / max);
            final int max2 = Math.max((size.width - n3) / 2, 0);
            final int max3 = Math.max((size.height - n4) / 2, 0);
            if (!component.getBounds().equals(new Rectangle(max2, max3, n3, n4))) {
                component.setBounds(max2, max3, n3, n4);
            }
        }
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        final Dimension dimension = new Dimension(0, 0);
        final Component[] components = container.getComponents();
        final float wh = this.wh;
        for (int i = 0; i < components.length; ++i) {
            final Dimension minimumSize = components[i].getMinimumSize();
            dimension.width = Math.max(dimension.width, (int)(minimumSize.height * wh));
            dimension.height = Math.max(dimension.height, minimumSize.height);
        }
        return this.$V2B(dimension, container.getInsets());
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        final Dimension dimension = new Dimension(0, 0);
        final Component[] components = container.getComponents();
        final float wh = this.wh;
        for (int i = 0; i < components.length; ++i) {
            final Dimension preferredSize = components[i].getPreferredSize();
            dimension.width = Math.max(dimension.width, (int)(preferredSize.height * wh));
            dimension.height = Math.max(dimension.height, preferredSize.height);
        }
        return this.$V2B(dimension, container.getInsets());
    }
    
    public void removeLayoutComponent(final Component component) {
    }
}
