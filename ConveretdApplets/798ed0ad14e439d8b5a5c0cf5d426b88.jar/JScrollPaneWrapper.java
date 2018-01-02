import java.awt.Dimension;
import java.awt.Point;
import java.awt.Component;
import javax.swing.JScrollPane;

// 
// Decompiled by Procyon v0.5.30
// 

public class JScrollPaneWrapper extends ScrollPaneWrapper
{
    private JScrollPane jScrollPane;
    
    public JScrollPaneWrapper() {
        this(null);
    }
    
    public JScrollPaneWrapper(JScrollPane jScrollPane) {
        if (jScrollPane == null) {
            jScrollPane = new JScrollPane(20, 30);
        }
        this.jScrollPane = jScrollPane;
        this.jScrollPane.getViewport().setOpaque(false);
        this.jScrollPane.setOpaque(false);
        super.I(this.jScrollPane);
    }
    
    public final void setView(final Component viewportView) {
        this.jScrollPane.setViewportView(viewportView);
    }
    
    public final Point getScrollPosition() {
        return this.jScrollPane.getViewport().getViewPosition();
    }
    
    public final Dimension getViewportSize() {
        return this.jScrollPane.getViewport().getExtentSize();
    }
    
    public final void setScrollPosition(final int n, final int n2) {
        this.jScrollPane.getViewport().setViewPosition(new Point(n, n2));
    }
    
    public final void setUnitIncrement(final int unitIncrement, final int unitIncrement2) {
        this.jScrollPane.getHorizontalScrollBar().setUnitIncrement(unitIncrement);
        this.jScrollPane.getVerticalScrollBar().setUnitIncrement(unitIncrement2);
    }
    
    public final void setBlockIncrement(final int blockIncrement, final int blockIncrement2) {
        this.jScrollPane.getHorizontalScrollBar().setBlockIncrement(blockIncrement);
        this.jScrollPane.getVerticalScrollBar().setBlockIncrement(blockIncrement2);
    }
}
