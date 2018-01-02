// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.external;

import org.xmodel.IModelObject;

public interface IExternalReference extends IModelObject
{
    void setCachingPolicy(final ICachingPolicy p0);
    
    ICachingPolicy getCachingPolicy();
    
    String[] getStaticAttributes();
    
    void setDirty(final boolean p0);
    
    boolean isDirty();
    
    void sync() throws CachingException;
    
    ITransaction transaction();
    
    void clearCache() throws CachingException;
    
    String toString(final String p0);
}
