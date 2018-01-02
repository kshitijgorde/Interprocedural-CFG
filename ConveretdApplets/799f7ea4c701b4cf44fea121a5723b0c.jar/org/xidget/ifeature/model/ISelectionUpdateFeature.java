// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature.model;

import java.util.List;

public interface ISelectionUpdateFeature
{
    void updateWidget();
    
    void updateModel();
    
    void displaySelect(final List<?> p0);
    
    void displayDeselect(final List<?> p0);
    
    void modelSelect(final List<?> p0);
    
    void modelDeselect(final List<?> p0);
}
