// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

public interface IModelListener
{
    void notifyParent(final IModelObject p0, final IModelObject p1, final IModelObject p2);
    
    void notifyAddChild(final IModelObject p0, final IModelObject p1, final int p2);
    
    void notifyRemoveChild(final IModelObject p0, final IModelObject p1, final int p2);
    
    void notifyChange(final IModelObject p0, final String p1, final Object p2, final Object p3);
    
    void notifyClear(final IModelObject p0, final String p1, final Object p2);
    
    void notifyDirty(final IModelObject p0, final boolean p1);
}
