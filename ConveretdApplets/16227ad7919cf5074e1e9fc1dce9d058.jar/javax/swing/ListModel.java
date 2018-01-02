// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.swing.event.ListDataListener;

public interface ListModel
{
    void addListDataListener(final ListDataListener p0);
    
    Object getElementAt(final int p0);
    
    int getSize();
    
    void removeListDataListener(final ListDataListener p0);
}
