import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Adjustable;
import java.awt.event.AdjustmentEvent;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Scrollbar;
import java.awt.event.AdjustmentListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class WScrollPane extends LightPanel implements AdjustmentListener
{
    Scrollbar I;
    Scrollbar Z;
    private WViewPort viewport;
    
    public WScrollPane(final Component component) {
        super.setLayout(new BorderLayout());
        this.I = new WScrollPane$WScrollBar(this, 0);
        this.Z = new WScrollPane$WScrollBar(this, 1);
        this.add(this.I, "South");
        this.add(this.Z, "East");
        this.add(this.viewport = new WViewPort(component), "Center");
    }
    
    public final void setLayout(final LayoutManager layoutManager) {
    }
    
    protected final void addImpl(final Component component, final Object o, final int n) {
        if (component == this.I || component == this.Z || component == this.viewport) {
            super.addImpl(component, o, n);
        }
        else {
            this.viewport.add(component);
        }
    }
    
    public final WViewPort getViewport() {
        return this.viewport;
    }
    
    public final void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        final Adjustable adjustable = adjustmentEvent.getAdjustable();
        final Point viewPortLocation = this.viewport.getViewPortLocation();
        if (adjustable == this.I) {
            viewPortLocation.x = adjustable.getValue();
        }
        else if (adjustable == this.Z) {
            viewPortLocation.y = adjustable.getValue();
        }
        this.viewport.setViewPortLocation(viewPortLocation.x, viewPortLocation.y);
    }
    
    public final void doLayout() {
        final Dimension size = this.getSize();
        final Insets insets = this.getInsets();
        final Dimension dimension = size;
        dimension.width -= insets.left + insets.right;
        final Dimension dimension2 = size;
        dimension2.height -= insets.top + insets.bottom;
        final boolean[] needScrolling = this.viewport.needScrolling(size);
        this.I.setVisible(needScrolling[0]);
        this.Z.setVisible(needScrolling[1]);
        super.doLayout();
        if (needScrolling[0] ^ needScrolling[1]) {
            if (needScrolling[0]) {
                final Dimension dimension3 = size;
                dimension3.height -= this.I.getSize().height;
            }
            else {
                final Dimension dimension4 = size;
                dimension4.width -= this.Z.getSize().width;
            }
            final boolean[] needScrolling2 = this.viewport.needScrolling(size);
            this.I.setVisible(needScrolling2[0]);
            this.Z.setVisible(needScrolling2[1]);
            super.doLayout();
        }
    }
    
    public final void validate() {
        super.validate();
        if (this.viewport.getComponentCount() > 0) {
            final Dimension viewportSize = this.viewport.getViewportSize();
            final Component component = this.viewport.getComponent(0);
            final Point viewPortLocation = this.viewport.getViewPortLocation();
            final Dimension size = component.getSize();
            this.I.setValues(viewPortLocation.x, viewportSize.width, 0, size.width);
            this.Z.setValues(viewPortLocation.y, viewportSize.height, 0, size.height);
        }
    }
    
    public final void setScrollPosition(final int value, final int value2) {
        this.I.setValue(value);
        this.Z.setValue(value2);
        this.viewport.setViewPortLocation(value, value2);
    }
}
