// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.event;

import java.util.EventListener;

public interface ListDataListener extends EventListener
{
    void contentsChanged(final ListDataEvent p0);
    
    void intervalAdded(final ListDataEvent p0);
    
    void intervalRemoved(final ListDataEvent p0);
}
