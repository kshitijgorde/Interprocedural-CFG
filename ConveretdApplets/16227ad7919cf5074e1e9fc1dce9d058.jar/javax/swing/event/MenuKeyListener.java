// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.event;

import java.util.EventListener;

public interface MenuKeyListener extends EventListener
{
    void menuKeyPressed(final MenuKeyEvent p0);
    
    void menuKeyReleased(final MenuKeyEvent p0);
    
    void menuKeyTyped(final MenuKeyEvent p0);
}
