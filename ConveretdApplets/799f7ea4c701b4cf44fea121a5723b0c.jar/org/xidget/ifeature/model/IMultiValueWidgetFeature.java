// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature.model;

import java.util.List;

public interface IMultiValueWidgetFeature
{
    void insertValue(final int p0, final Object p1);
    
    void updateValue(final int p0, final Object p1);
    
    void removeValue(final int p0);
    
    void setValues(final List<?> p0);
    
    List<?> getValues();
}
