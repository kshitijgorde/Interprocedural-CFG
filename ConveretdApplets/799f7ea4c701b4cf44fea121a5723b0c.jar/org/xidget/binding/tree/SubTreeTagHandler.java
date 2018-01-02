// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding.tree;

import org.xmodel.IModelObject;
import org.xidget.config.ITagHandler;
import org.xidget.config.TagProcessor;
import org.xidget.IXidget;
import org.xidget.tree.SubTreeXidget;
import org.xidget.binding.XidgetTagHandler;

public class SubTreeTagHandler extends XidgetTagHandler
{
    public SubTreeTagHandler() {
        super(SubTreeXidget.class);
    }
    
    @Override
    public boolean filter(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) {
        return modelObject.getParent().isType("tree");
    }
}
