// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.awt.Event;

public interface NFDwellObserver
{
    boolean dwellDisplay(final boolean p0, final NFActiveLabel p1);
    
    boolean dwellPress(final Event p0, final int p1, final int p2, final NFActiveLabel p3);
}
