// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature.model;

public interface IMultiValueUpdateFeature
{
    void updateWidget();
    
    void updateModel();
    
    void displayInsert(final int p0, final Object p1);
    
    void displayRemove(final int p0);
    
    void displayUpdate(final int p0, final Object p1);
    
    void modelInsert(final int p0, final Object p1);
    
    void modelRemove(final int p0);
    
    void modelUpdate(final int p0, final Object p1);
}
