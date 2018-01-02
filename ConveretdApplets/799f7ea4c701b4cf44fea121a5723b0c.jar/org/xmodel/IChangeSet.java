// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import java.util.List;

public interface IChangeSet extends Runnable
{
    void setAttribute(final IModelObject p0, final String p1, final Object p2);
    
    void removeAttribute(final IModelObject p0, final String p1);
    
    void addChild(final IModelObject p0, final IModelObject p1);
    
    void addChild(final IModelObject p0, final IModelObject p1, final int p2);
    
    void removeChild(final IModelObject p0, final IModelObject p1);
    
    void removeChild(final IModelObject p0, final IModelObject p1, final int p2);
    
    List<IBoundChangeRecord> getRecords();
    
    int getSize();
    
    void applyChanges();
    
    void clearChanges();
}
