import java.awt.Dimension;
import java.awt.Point;
import java.awt.Component;
import java.awt.ScrollPane;

// 
// Decompiled by Procyon v0.5.30
// 

final class ScrollPaneWrapper0 extends ScrollPaneWrapper
{
    private ScrollPane scrollPane;
    
    ScrollPaneWrapper0() {
        this.I(this.scrollPane = new ScrollPane(0));
    }
    
    public final void setView(final Component component) {
        this.scrollPane.add(component);
    }
    
    public final Point getScrollPosition() {
        return this.scrollPane.getScrollPosition();
    }
    
    public final Dimension getViewportSize() {
        return this.scrollPane.getViewportSize();
    }
    
    public final void setScrollPosition(final int n, final int n2) {
        this.scrollPane.setScrollPosition(n, n2);
    }
    
    public final void setUnitIncrement(final int unitIncrement, final int unitIncrement2) {
        this.scrollPane.getHAdjustable().setUnitIncrement(unitIncrement);
        this.scrollPane.getVAdjustable().setUnitIncrement(unitIncrement2);
    }
    
    public final void setBlockIncrement(final int blockIncrement, final int blockIncrement2) {
        this.scrollPane.getHAdjustable().setBlockIncrement(blockIncrement);
        this.scrollPane.getVAdjustable().setBlockIncrement(blockIncrement2);
    }
}
