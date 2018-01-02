import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class WToolTip extends Panel
{
    public final WButton content;
    private Component currentComponent;
    
    public WToolTip(WButton content) {
        super(new GridLayout(1, 1));
        if (content == null) {
            content = new WButton(null, null, 0);
        }
        this.add(this.content = content);
        this.setVisible(false);
    }
    
    public final void showToolTip(final String text, final Component component, final Point point) {
        if (component == null) {
            return;
        }
        final String text2 = this.content.text;
        this.content.text = text;
        if (text == null || !text.equals(text2)) {
            this.content.clearCache();
        }
        if (text == null || text.length() == 0) {
            this.hideToolTip();
        }
        else {
            this.showToolTip(component, point);
        }
    }
    
    public final void showToolTip(final Component currentComponent, final Point point) {
        this.setVisible(false);
        if (currentComponent == null) {
            return;
        }
        final Rectangle bounds = new Rectangle(point);
        Component parent = currentComponent;
        while (!(parent instanceof WToolTipPanel) || !((WToolTipPanel)parent).acceptTooltip) {
            final Point location = parent.getLocation();
            bounds.translate(location.x, location.y);
            parent = parent.getParent();
            if (parent == null) {
                return;
            }
        }
        final WToolTipPanel wToolTipPanel = (WToolTipPanel)parent;
        if (this.getParent() != wToolTipPanel) {
            wToolTipPanel.add(this, WToolTipPanel.FLOATING, 0);
        }
        bounds.setSize(this.getPreferredSize());
        final Insets insets = wToolTipPanel.getInsets();
        final Dimension size = wToolTipPanel.getSize();
        if (bounds.y + 20 + bounds.height < size.height - insets.bottom) {
            final Rectangle rectangle = bounds;
            rectangle.y += 20;
        }
        else {
            bounds.y = size.height - insets.bottom - bounds.height - 1;
        }
        if (bounds.x + bounds.width >= size.width - insets.left) {
            bounds.x = size.width - insets.left - bounds.width - 1;
        }
        this.setBounds(bounds);
        this.validate();
        this.currentComponent = currentComponent;
        this.content.clearCache();
        this.setVisible(true);
    }
    
    public final void hideToolTip() {
        super.setVisible(false);
        this.invalidate();
    }
    
    public final void disposeToolTip() {
        super.setVisible(false);
        final Container parent = this.getParent();
        if (parent != null) {
            parent.remove(this);
        }
    }
    
    public static final WToolTip searchToolTip(Component parent) {
        WToolTip toolTipComp;
        for (toolTipComp = null; parent != null && toolTipComp == null; parent = parent.getParent()) {
            if (parent instanceof WToolContainer) {
                toolTipComp = ((WToolContainer)parent).getToolTipComp();
            }
        }
        return toolTipComp;
    }
}
