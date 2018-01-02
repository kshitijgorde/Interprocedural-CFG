// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.swing.event.ListSelectionListener;

public interface ListSelectionModel
{
    public static final int SINGLE_SELECTION = 0;
    public static final int SINGLE_INTERVAL_SELECTION = 1;
    public static final int MULTIPLE_INTERVAL_SELECTION = 2;
    
    void addListSelectionListener(final ListSelectionListener p0);
    
    void addSelectionInterval(final int p0, final int p1);
    
    void clearSelection();
    
    int getAnchorSelectionIndex();
    
    int getLeadSelectionIndex();
    
    int getMaxSelectionIndex();
    
    int getMinSelectionIndex();
    
    int getSelectionMode();
    
    boolean getValueIsAdjusting();
    
    void insertIndexInterval(final int p0, final int p1, final boolean p2);
    
    boolean isSelectedIndex(final int p0);
    
    boolean isSelectionEmpty();
    
    void removeIndexInterval(final int p0, final int p1);
    
    void removeListSelectionListener(final ListSelectionListener p0);
    
    void removeSelectionInterval(final int p0, final int p1);
    
    void setAnchorSelectionIndex(final int p0);
    
    void setLeadSelectionIndex(final int p0);
    
    void setSelectionInterval(final int p0, final int p1);
    
    void setSelectionMode(final int p0);
    
    void setValueIsAdjusting(final boolean p0);
}
