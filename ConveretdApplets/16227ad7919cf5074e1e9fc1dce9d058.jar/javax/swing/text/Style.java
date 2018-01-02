// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import javax.swing.event.ChangeListener;

public interface Style extends MutableAttributeSet
{
    void addChangeListener(final ChangeListener p0);
    
    String getName();
    
    void removeChangeListener(final ChangeListener p0);
}
