// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding.table;

import org.xmodel.IModelObject;
import org.xidget.config.ITagHandler;
import org.xidget.config.TagProcessor;
import org.xidget.IXidget;
import org.xidget.table.SubTableXidget;
import org.xidget.binding.XidgetTagHandler;

public class SubTableTagHandler extends XidgetTagHandler
{
    public SubTableTagHandler() {
        super(SubTableXidget.class);
    }
    
    @Override
    public boolean filter(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) {
        final IModelObject parent = modelObject.getParent();
        return parent.isType("table") || parent.isType("tree");
    }
}
