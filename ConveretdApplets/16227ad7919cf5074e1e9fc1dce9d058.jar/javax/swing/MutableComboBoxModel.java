// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

public interface MutableComboBoxModel extends ComboBoxModel
{
    void addElement(final Object p0);
    
    void insertElementAt(final Object p0, final int p1);
    
    void removeElement(final Object p0);
    
    void removeElementAt(final int p0);
}
