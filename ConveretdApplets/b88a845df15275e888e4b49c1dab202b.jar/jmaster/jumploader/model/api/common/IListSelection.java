// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.api.common;

public interface IListSelection
{
    void addListener(final IListSelectionListener p0);
    
    void removeListener(final IListSelectionListener p0);
    
    int[] getSelectedIndices();
    
    String[] getSelectedIndicesAsStrings();
    
    void setSelectedIndices(final int[] p0);
    
    int getSelectedItemCount();
    
    int getSelectedItemIndexAt(final int p0);
    
    boolean isIndexSelected(final int p0);
    
    int indexOf(final int p0);
}
