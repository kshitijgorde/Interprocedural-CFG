// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.layout;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Component;
import jlog.awt.$W4;
import java.awt.Container;
import java.awt.Point;
import java.awt.LayoutManager;

public class $N2B implements LayoutManager
{
    private Point $QDB;
    private Object $O2B;
    
    public boolean $P2B(final Container container, int n, int n2) {
        final Component[] components = container.getComponents();
        Component component = null;
        if (components.length != 0) {
            component = components[0];
            final Rectangle bounds = component.bounds();
            final Dimension size = container.size();
            new $W4(container).$AX(size);
            n = Math.min(n, bounds.width - size.width);
            n2 = Math.min(n2, bounds.height - size.height);
            n = Math.max(n, 0);
            n2 = Math.max(n2, 0);
        }
        if (n != this.$QDB.x || n2 != this.$QDB.y) {
            synchronized (this.$O2B) {
                this.$QDB = new Point(n, n2);
            }
            // monitorexit(this.$O2B)
            return component != null;
        }
        return false;
    }
    
    public boolean $P2B(final Container container, final Point point) {
        return this.$P2B(container, point.x, point.y);
    }
    
    public $N2B() {
        this.$O2B = new Object();
        this.$QDB = new Point(0, 0);
    }
    
    public void addLayoutComponent(final String s, final Component component) {
    }
    
    public Point getOffset() {
        synchronized (this.$O2B) {
            // monitorexit(this.$O2B)
            return new Point(this.$QDB.x, this.$QDB.y);
        }
    }
    
    public void layoutContainer(final Container container) {
        final Component[] components = container.getComponents();
        if (components.length == 0) {
            return;
        }
        final Component component = components[0];
        final Rectangle bounds = component.bounds();
        final $W4 $w4 = new $W4(container);
        final Dimension size = container.size();
        $w4.$AX(size);
        final Point point;
        synchronized (this.$O2B) {
            this.$QDB.x = Math.min(this.$QDB.x, bounds.width - size.width);
            this.$QDB.y = Math.min(this.$QDB.y, bounds.height - size.height);
            this.$QDB.x = Math.max(this.$QDB.x, 0);
            this.$QDB.y = Math.max(this.$QDB.y, 0);
            point = new Point(this.$QDB.x, this.$QDB.y);
        }
        // monitorexit(this.$O2B)
        final int n = size.width - bounds.width;
        final int n2 = size.height - bounds.height;
        int n3;
        if (n >= 0) {
            n3 = n / 2;
        }
        else {
            n3 = -Math.min(point.x, -n);
        }
        int n4;
        if (n2 >= 0) {
            n4 = n2 / 2;
        }
        else {
            n4 = -Math.min(point.y, -n2);
        }
        if (bounds.x - $w4.left != n3 || bounds.y - $w4.top != n4) {
            component.move(n3 + $w4.left, n4 + $w4.top);
        }
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        final Component[] components = container.getComponents();
        if (components.length != 0) {
            final Dimension minimumSize = components[0].minimumSize();
            new $W4(container).grow(minimumSize);
            return minimumSize;
        }
        return new Dimension(0, 0);
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        final Component[] components = container.getComponents();
        if (components.length != 0) {
            final Dimension preferredSize = components[0].preferredSize();
            new $W4(container).grow(preferredSize);
            return preferredSize;
        }
        return new Dimension(0, 0);
    }
    
    public void removeLayoutComponent(final Component component) {
    }
}
