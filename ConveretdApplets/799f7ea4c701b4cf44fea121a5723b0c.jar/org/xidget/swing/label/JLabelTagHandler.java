// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.label;

import org.xmodel.IModelObject;
import org.xidget.config.ITagHandler;
import org.xidget.config.TagProcessor;
import org.xidget.IXidget;
import org.xidget.binding.XidgetTagHandler;

public class JLabelTagHandler extends XidgetTagHandler
{
    public JLabelTagHandler(final Class<? extends IXidget> clazz) {
        super(clazz);
    }
    
    @Override
    public boolean filter(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) {
        return super.filter(tagProcessor, tagHandler, modelObject) && modelObject.getParent().isType("form");
    }
}
