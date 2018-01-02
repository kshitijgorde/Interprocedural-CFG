// 
// Decompiled by Procyon v0.5.30
// 

package javax.accessibility;

public interface AccessibleValue
{
    Number getCurrentAccessibleValue();
    
    Number getMaximumAccessibleValue();
    
    Number getMinimumAccessibleValue();
    
    boolean setCurrentAccessibleValue(final Number p0);
}
