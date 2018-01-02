// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding;

import org.xidget.config.TagException;
import org.xmodel.IModelObject;
import org.xidget.config.TagProcessor;
import org.xidget.config.ITagHandler;

public class SkipTagHandler implements ITagHandler
{
    @Override
    public boolean filter(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) {
        return true;
    }
    
    @Override
    public boolean enter(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) throws TagException {
        return false;
    }
    
    @Override
    public void exit(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) throws TagException {
    }
    
    @Override
    public <T> T getFeature(final Class<T> clazz) {
        return null;
    }
}
