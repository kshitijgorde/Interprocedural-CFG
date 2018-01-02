// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.config;

import org.xmodel.IModelObject;

public abstract class AbstractTagHandler implements ITagHandler
{
    @Override
    public boolean filter(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) {
        return true;
    }
    
    @Override
    public void exit(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) throws TagException {
    }
    
    @Override
    public <T> T getFeature(final Class<T> clazz) {
        return null;
    }
}
