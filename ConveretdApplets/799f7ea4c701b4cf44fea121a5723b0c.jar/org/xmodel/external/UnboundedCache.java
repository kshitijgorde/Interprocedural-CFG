// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.external;

import org.xmodel.IModelObject;

public class UnboundedCache implements ICache
{
    @Override
    public void configure(final IModelObject modelObject) {
    }
    
    @Override
    public void add(final IExternalReference externalReference) {
    }
    
    @Override
    public void remove(final IExternalReference externalReference) {
    }
    
    @Override
    public void touch(final IExternalReference externalReference) {
    }
    
    @Override
    public int size() {
        return -1;
    }
    
    @Override
    public int capacity() {
        return -1;
    }
}
