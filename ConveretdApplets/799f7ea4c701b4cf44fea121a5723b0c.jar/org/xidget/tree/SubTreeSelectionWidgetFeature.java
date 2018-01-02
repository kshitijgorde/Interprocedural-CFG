// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.tree;

import java.util.List;
import org.xidget.ifeature.model.IPartialSelectionWidgetFeature;
import org.xidget.IXidget;
import org.xidget.ifeature.model.ISelectionWidgetFeature;

public class SubTreeSelectionWidgetFeature implements ISelectionWidgetFeature
{
    private IXidget xidget;
    
    public SubTreeSelectionWidgetFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public void select(final Object o) {
        this.xidget.getParent().getFeature(IPartialSelectionWidgetFeature.class).select(this.xidget, o);
    }
    
    @Override
    public void deselect(final Object o) {
        this.xidget.getParent().getFeature(IPartialSelectionWidgetFeature.class).deselect(this.xidget, o);
    }
    
    @Override
    public void setSelection(final List<?> list) {
        this.xidget.getParent().getFeature(IPartialSelectionWidgetFeature.class).setSelection(this.xidget, list);
    }
    
    @Override
    public List<?> getSelection() {
        return this.xidget.getParent().getFeature(IPartialSelectionWidgetFeature.class).getSelection(this.xidget);
    }
}
