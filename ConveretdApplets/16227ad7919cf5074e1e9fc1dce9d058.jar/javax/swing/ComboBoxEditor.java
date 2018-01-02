// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.awt.Component;
import java.awt.event.ActionListener;

public interface ComboBoxEditor
{
    void addActionListener(final ActionListener p0);
    
    Component getEditorComponent();
    
    Object getItem();
    
    void removeActionListener(final ActionListener p0);
    
    void selectAll();
    
    void setItem(final Object p0);
}
