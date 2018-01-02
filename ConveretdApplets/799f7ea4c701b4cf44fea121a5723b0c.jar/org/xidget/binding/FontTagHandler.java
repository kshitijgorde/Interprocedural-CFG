// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding;

import org.xidget.config.TagException;
import org.xmodel.IModelObject;
import org.xidget.config.TagProcessor;
import org.xidget.config.ifeature.IXidgetFeature;
import org.xidget.config.ITagHandler;

public class FontTagHandler extends BindingTagHandler
{
    private ITagHandler parent;
    
    public FontTagHandler() {
        super(new FontBindingRule());
    }
    
    @Override
    public <T> T getFeature(final Class<T> clazz) {
        if (clazz == IXidgetFeature.class) {
            return this.parent.getFeature(clazz);
        }
        return super.getFeature(clazz);
    }
    
    @Override
    public boolean enter(final TagProcessor tagProcessor, final ITagHandler parent, final IModelObject modelObject) throws TagException {
        super.enter(tagProcessor, this.parent = parent, modelObject);
        return true;
    }
}
