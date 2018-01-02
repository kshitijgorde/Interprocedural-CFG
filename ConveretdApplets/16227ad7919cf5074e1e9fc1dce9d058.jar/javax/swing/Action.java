// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.beans.PropertyChangeListener;
import java.awt.event.ActionListener;

public interface Action extends ActionListener
{
    public static final String DEFAULT = "Default";
    public static final String NAME = "Name";
    public static final String SHORT_DESCRIPTION = "ShortDescription";
    public static final String LONG_DESCRIPTION = "LongDescription";
    public static final String SMALL_ICON = "SmallIcon";
    
    void addPropertyChangeListener(final PropertyChangeListener p0);
    
    Object getValue(final String p0);
    
    boolean isEnabled();
    
    void putValue(final String p0, final Object p1);
    
    void removePropertyChangeListener(final PropertyChangeListener p0);
    
    void setEnabled(final boolean p0);
}
