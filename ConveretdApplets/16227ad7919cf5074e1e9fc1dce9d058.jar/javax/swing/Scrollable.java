// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.awt.Rectangle;
import java.awt.Dimension;

public interface Scrollable
{
    Dimension getPreferredScrollableViewportSize();
    
    int getScrollableBlockIncrement(final Rectangle p0, final int p1, final int p2);
    
    boolean getScrollableTracksViewportHeight();
    
    boolean getScrollableTracksViewportWidth();
    
    int getScrollableUnitIncrement(final Rectangle p0, final int p1, final int p2);
}
