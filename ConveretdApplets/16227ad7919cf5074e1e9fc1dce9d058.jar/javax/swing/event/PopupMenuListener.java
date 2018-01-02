// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.event;

import java.util.EventListener;

public interface PopupMenuListener extends EventListener
{
    void popupMenuCanceled(final PopupMenuEvent p0);
    
    void popupMenuWillBecomeInvisible(final PopupMenuEvent p0);
    
    void popupMenuWillBecomeVisible(final PopupMenuEvent p0);
}
