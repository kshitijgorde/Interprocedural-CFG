import java.awt.Dimension;
import java.awt.Point;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class ScrollPaneWrapper
{
    private Component comp;
    
    protected final void I(final Component comp) {
        this.comp = comp;
    }
    
    public static final ScrollPaneWrapper newInstance() {
        if (WPanel.lightweight) {
            return new JScrollPaneWrapper();
        }
        if (System.getProperty("java.vendor").toLowerCase().indexOf("blackdown") > -1) {
            return new ScrollPaneWrapper1();
        }
        return new ScrollPaneWrapper0();
    }
    
    public abstract void setView(final Component p0);
    
    public abstract Point getScrollPosition();
    
    public abstract Dimension getViewportSize();
    
    public abstract void setScrollPosition(final int p0, final int p1);
    
    public abstract void setUnitIncrement(final int p0, final int p1);
    
    public abstract void setBlockIncrement(final int p0, final int p1);
    
    public final void doLayout() {
        this.comp.doLayout();
    }
    
    public final void invalidate() {
        this.comp.invalidate();
    }
    
    public final void validate() {
        this.comp.validate();
        final Dimension viewportSize = this.getViewportSize();
        this.setBlockIncrement(viewportSize.width, viewportSize.height);
    }
    
    public final Component getScrollPane() {
        return this.comp;
    }
}
