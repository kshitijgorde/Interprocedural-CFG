// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.menu;

import org.xmodel.IModelObject;
import org.xidget.config.ITagHandler;
import org.xidget.config.TagProcessor;
import org.xidget.IXidget;
import org.xidget.binding.XidgetTagHandler;

public class MenuTagHandler extends XidgetTagHandler
{
    public MenuTagHandler() {
        super(JPopupMenuXidget.class);
    }
    
    @Override
    public boolean filter(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) {
        return !modelObject.getParent().isType("menu") && !modelObject.getParent().isType("menubar");
    }
}
