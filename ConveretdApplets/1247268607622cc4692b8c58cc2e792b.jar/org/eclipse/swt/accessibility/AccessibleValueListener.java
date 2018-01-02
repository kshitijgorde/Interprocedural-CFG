// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.SWTEventListener;

public interface AccessibleValueListener extends SWTEventListener
{
    void getCurrentValue(final AccessibleValueEvent p0);
    
    void setCurrentValue(final AccessibleValueEvent p0);
    
    void getMaximumValue(final AccessibleValueEvent p0);
    
    void getMinimumValue(final AccessibleValueEvent p0);
}
