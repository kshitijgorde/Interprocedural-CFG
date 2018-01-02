// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding;

import org.xidget.IXidget;
import org.xidget.ifeature.model.ISingleValueUpdateFeature;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.Xlate;
import org.xidget.config.TagException;
import org.xidget.config.ifeature.IXidgetFeature;
import org.xmodel.IModelObject;
import org.xidget.config.TagProcessor;
import org.xidget.config.ITagHandler;

public class GetTagHandler implements ITagHandler
{
    @Override
    public boolean enter(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) throws TagException {
        final IXidgetFeature xidgetFeature = tagHandler.getFeature(IXidgetFeature.class);
        if (xidgetFeature == null) {
            throw new TagException("Parent tag handler must have an IXidgetFeature.");
        }
        final IXidget xidget = xidgetFeature.getXidget();
        final IExpression value = Xlate.get(modelObject, (IExpression)null);
        if (value != null) {
            final ISingleValueUpdateFeature singleValueUpdateFeature = xidget.getFeature(ISingleValueUpdateFeature.class);
            if (singleValueUpdateFeature != null) {
                singleValueUpdateFeature.setDisplayTransform(value);
            }
        }
        return false;
    }
    
    @Override
    public void exit(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) throws TagException {
    }
    
    @Override
    public boolean filter(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) {
        return true;
    }
    
    @Override
    public <T> T getFeature(final Class<T> clazz) {
        return null;
    }
}
