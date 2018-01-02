// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.external;

import org.xmodel.IModelObject;

public interface ICache
{
    void configure(final IModelObject p0);
    
    void add(final IExternalReference p0);
    
    void remove(final IExternalReference p0);
    
    void touch(final IExternalReference p0);
    
    int size();
    
    int capacity();
}
