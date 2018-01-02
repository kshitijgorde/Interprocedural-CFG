// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.event;

import java.util.EventListener;

public interface MenuListener extends EventListener
{
    void menuCanceled(final MenuEvent p0);
    
    void menuDeselected(final MenuEvent p0);
    
    void menuSelected(final MenuEvent p0);
}
