// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.viewpanel;

import java.util.EventListener;

public interface HeaderListener extends EventListener
{
    void headerResized(final HeaderEvent p0);
    
    void headerStateChanged(final HeaderEvent p0);
}
