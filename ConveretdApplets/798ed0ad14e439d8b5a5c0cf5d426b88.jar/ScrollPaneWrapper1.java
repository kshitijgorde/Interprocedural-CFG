import java.awt.Dimension;
import java.awt.Point;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

final class ScrollPaneWrapper1 extends ScrollPaneWrapper
{
    private WScrollPane scrollPane;
    
    ScrollPaneWrapper1() {
        this.I(this.scrollPane = new WScrollPane((Component)null));
    }
    
    public final void setView(final Component component) {
        this.scrollPane.add(component);
    }
    
    public final Point getScrollPosition() {
        return this.scrollPane.getViewport().getViewPortLocation();
    }
    
    public final Dimension getViewportSize() {
        return this.scrollPane.getViewport().getViewportSize();
    }
    
    public final void setScrollPosition(final int n, final int n2) {
        this.scrollPane.setScrollPosition(n, n2);
    }
    
    public final void setUnitIncrement(final int unitIncrement, final int unitIncrement2) {
        this.scrollPane.I.setUnitIncrement(unitIncrement);
        this.scrollPane.Z.setUnitIncrement(unitIncrement2);
    }
    
    public final void setBlockIncrement(final int blockIncrement, final int blockIncrement2) {
        this.scrollPane.I.setBlockIncrement(blockIncrement);
        this.scrollPane.Z.setBlockIncrement(blockIncrement2);
    }
}
