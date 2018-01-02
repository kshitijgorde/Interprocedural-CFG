import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class WViewPort extends WPanel
{
    private Component pane;
    private Point viewLocation;
    private Dimension viewSize;
    private Rectangle paneBounds;
    
    public WViewPort(final Component component) {
        this.pane = null;
        this.viewLocation = new Point();
        this.paneBounds = new Rectangle();
        this.viewSize = new Dimension();
        if (component != null) {
            this.add(component);
        }
        this.setBackground(null);
    }
    
    protected final void addImpl(final Component pane, final Object o, final int n) {
        if (this.pane != null && this.pane != pane) {
            this.remove(this.pane);
        }
        super.addImpl(pane, o, n);
        this.pane = pane;
    }
    
    public final void remove(final int n) {
        if (n == 0 && this.pane != null) {
            this.pane = null;
            super.remove(0);
        }
    }
    
    public final void setLayout(final LayoutManager layoutManager) {
    }
    
    public final void doLayout() {
        if (this.pane == null) {
            super.doLayout();
            return;
        }
        this.computeBounds(this.getSize(), false);
        this.pane.setBounds(this.paneBounds);
    }
    
    public final Dimension getMaximumSize() {
        if (this.pane != null) {
            return this.pane.getMaximumSize();
        }
        return super.getMaximumSize();
    }
    
    public final Dimension getMinimumSize() {
        return new Dimension(0, 0);
    }
    
    public final Dimension getPreferredSize() {
        if (this.pane != null) {
            final Insets insets = this.getInsets();
            final Dimension preferredSize;
            final Dimension dimension = preferredSize = this.pane.getPreferredSize();
            preferredSize.width += insets.left + insets.right;
            final Dimension dimension2 = dimension;
            dimension2.height += insets.top + insets.bottom;
            return dimension;
        }
        return super.getPreferredSize();
    }
    
    public final void setViewPortLocation(final int n, final int n2) {
        this.viewLocation = new Point((n < 0) ? 0 : n, (n2 < 0) ? 0 : n2);
        this.doLayout();
    }
    
    public final Dimension getViewportSize() {
        return new Dimension(this.viewSize);
    }
    
    public final Point getViewPortLocation() {
        return new Point(this.viewLocation);
    }
    
    public final void translateViewPort(final int n, final int n2) {
        final Point viewLocation = this.viewLocation;
        this.setViewPortLocation(viewLocation.x + n, viewLocation.y + n2);
    }
    
    public final boolean[] needScrolling(final Dimension dimension) {
        if (this.pane == null) {
            return new boolean[] { false, false };
        }
        return this.computeBounds(new Dimension(dimension), true);
    }
    
    private boolean[] computeBounds(final Dimension viewSize, final boolean b) {
        final boolean[] array = new boolean[2];
        final Insets insets = this.getInsets();
        final Dimension preferredSize = this.pane.getPreferredSize();
        viewSize.width -= insets.left + insets.right;
        viewSize.height -= insets.top + insets.bottom;
        array[0] = (preferredSize.width > viewSize.width);
        array[1] = (preferredSize.height > viewSize.height);
        if (!b) {
            this.viewSize = viewSize;
            if (!array[0]) {
                preferredSize.width = this.viewSize.width;
            }
            if (!array[1]) {
                preferredSize.height = this.viewSize.height;
            }
            this.viewLocation.x = Math.min(this.viewLocation.x, preferredSize.width - this.viewSize.width);
            this.viewLocation.y = Math.min(this.viewLocation.y, preferredSize.height - this.viewSize.height);
            this.paneBounds.setLocation(-this.viewLocation.x + insets.left, -this.viewLocation.y + insets.top);
            this.paneBounds.setSize(preferredSize);
        }
        return array;
    }
}
