// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.swing.event.ChangeListener;

public interface BoundedRangeModel
{
    void addChangeListener(final ChangeListener p0);
    
    int getExtent();
    
    int getMaximum();
    
    int getMinimum();
    
    int getValue();
    
    boolean getValueIsAdjusting();
    
    void removeChangeListener(final ChangeListener p0);
    
    void setExtent(final int p0);
    
    void setMaximum(final int p0);
    
    void setMinimum(final int p0);
    
    void setRangeProperties(final int p0, final int p1, final int p2, final int p3, final boolean p4);
    
    void setValue(final int p0);
    
    void setValueIsAdjusting(final boolean p0);
}
