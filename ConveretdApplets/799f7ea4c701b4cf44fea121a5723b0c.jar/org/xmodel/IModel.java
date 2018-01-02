// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import java.util.List;
import java.util.Set;

public interface IModel
{
    void addRoot(final String p0, final IModelObject p1);
    
    void removeRoot(final String p0, final IModelObject p1);
    
    void removeRoots(final String p0);
    
    Set<String> getCollections();
    
    List<IModelObject> getRoots(final String p0);
    
    void revert();
    
    void restore();
    
    void lock(final IModelObject p0);
    
    void unlock(final IModelObject p0);
    
    IChangeSet isLocked(final IModelObject p0);
    
    A startUpdate();
    
    List<A> updateStack();
    
    void endUpdate();
    
    A getCurrentUpdate();
    
    int getUpdateID();
    
    void setDispatcher(final IDispatcher p0);
    
    IDispatcher getDispatcher();
    
    void dispatch(final Runnable p0);
    
    void setSyncLock(final boolean p0);
    
    boolean getSyncLock();
    
    void handleException(final Exception p0);
    
     <T> void setFeature(final Class<?> p0, final T p1);
    
     <T> T getFeature(final Class<T> p0);
}
