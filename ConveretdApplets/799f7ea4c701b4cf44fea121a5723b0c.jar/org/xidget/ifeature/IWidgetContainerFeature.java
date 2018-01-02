// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature;

import org.xidget.IXidget;

public interface IWidgetContainerFeature
{
    void addWidget(final IXidget p0);
    
    void addWidget(final int p0, final IXidget p1);
    
    void removeWidget(final IXidget p0);
    
    void relayout();
    
    void setSpacing(final int p0);
    
    int getSpacing();
}
