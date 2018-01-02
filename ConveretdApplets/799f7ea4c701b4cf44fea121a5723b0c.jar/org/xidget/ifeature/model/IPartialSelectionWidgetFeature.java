// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature.model;

import java.util.List;
import org.xidget.IXidget;

public interface IPartialSelectionWidgetFeature
{
    void select(final IXidget p0, final Object p1);
    
    void deselect(final IXidget p0, final Object p1);
    
    void setSelection(final IXidget p0, final List<?> p1);
    
    List<?> getSelection(final IXidget p0);
}
