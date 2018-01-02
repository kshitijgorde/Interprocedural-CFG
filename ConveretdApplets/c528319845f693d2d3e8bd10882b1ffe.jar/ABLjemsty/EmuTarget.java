// 
// Decompiled by Procyon v0.5.30
// 

package ABLjemsty;

import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Event;

public interface EmuTarget
{
    String j();
    
    boolean a(final Event p0);
    
    boolean handleEvent(final Event p0);
    
    boolean keyDown(final Event p0, final int p1);
    
    Dimension getSize();
    
    Insets getInsets();
}
