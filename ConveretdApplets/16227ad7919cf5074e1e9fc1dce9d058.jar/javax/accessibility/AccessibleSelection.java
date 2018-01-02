// 
// Decompiled by Procyon v0.5.30
// 

package javax.accessibility;

public interface AccessibleSelection
{
    void addAccessibleSelection(final int p0);
    
    void clearAccessibleSelection();
    
    Accessible getAccessibleSelection(final int p0);
    
    int getAccessibleSelectionCount();
    
    boolean isAccessibleChildSelected(final int p0);
    
    void removeAccessibleSelection(final int p0);
    
    void selectAllAccessibleSelection();
}
