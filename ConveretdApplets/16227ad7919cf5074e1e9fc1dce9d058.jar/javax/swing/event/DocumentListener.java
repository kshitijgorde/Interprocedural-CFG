// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.event;

import java.util.EventListener;

public interface DocumentListener extends EventListener
{
    void changedUpdate(final DocumentEvent p0);
    
    void insertUpdate(final DocumentEvent p0);
    
    void removeUpdate(final DocumentEvent p0);
}
