// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding;

import org.xidget.config.TagException;
import org.xmodel.IModelObject;
import org.xidget.config.ITagHandler;
import org.xidget.config.TagProcessor;
import org.xidget.config.AbstractTagHandler;

public class IgnoreElementHandler extends AbstractTagHandler
{
    @Override
    public boolean enter(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) throws TagException {
        return false;
    }
}
