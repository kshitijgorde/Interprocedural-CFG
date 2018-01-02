// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.swing.event.ChangeListener;

public interface SingleSelectionModel
{
    void addChangeListener(final ChangeListener p0);
    
    void clearSelection();
    
    int getSelectedIndex();
    
    boolean isSelected();
    
    void removeChangeListener(final ChangeListener p0);
    
    void setSelectedIndex(final int p0);
}
