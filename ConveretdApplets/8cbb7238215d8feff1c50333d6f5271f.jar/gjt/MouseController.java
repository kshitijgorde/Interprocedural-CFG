// 
// Decompiled by Procyon v0.5.30
// 

package gjt;

import java.awt.Event;

public interface MouseController
{
    boolean mouseEnter(final Event p0, final int p1, final int p2);
    
    boolean mouseExit(final Event p0, final int p1, final int p2);
    
    boolean mouseUp(final Event p0, final int p1, final int p2);
    
    boolean mouseDown(final Event p0, final int p1, final int p2);
    
    boolean mouseDrag(final Event p0, final int p1, final int p2);
    
    boolean mouseMove(final Event p0, final int p1, final int p2);
}
