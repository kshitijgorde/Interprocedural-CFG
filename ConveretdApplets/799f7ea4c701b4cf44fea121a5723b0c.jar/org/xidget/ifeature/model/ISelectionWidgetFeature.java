// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature.model;

import java.util.List;

public interface ISelectionWidgetFeature
{
    void select(final Object p0);
    
    void deselect(final Object p0);
    
    void setSelection(final List<?> p0);
    
    List<?> getSelection();
}
