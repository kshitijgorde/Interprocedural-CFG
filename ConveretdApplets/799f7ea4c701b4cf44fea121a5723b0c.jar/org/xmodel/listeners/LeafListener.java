// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.listeners;

import java.util.Iterator;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.IPath;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.IModelListener;
import org.xmodel.IPathListener;
import org.xmodel.ModelListener;

public class LeafListener extends ModelListener implements IPathListener
{
    IModelListener \u00e0;
    
    public LeafListener() {
        this.\u00e0 = this;
    }
    
    public LeafListener(final IModelListener \u00e0) {
        this.\u00e0 = \u00e0;
    }
    
    public void notifyAdd(final IContext context, final IPath path, final List<IModelObject> list) {
    }
    
    public void notifyRemove(final IContext context, final IPath path, final List<IModelObject> list) {
    }
    
    @Override
    public void notifyAdd(final IContext context, final IPath path, final int n, final List<IModelObject> list) {
        if (n == path.length()) {
            final Iterator<IModelObject> iterator = list.iterator();
            while (iterator.hasNext()) {
                iterator.next().addModelListener(this.\u00e0);
            }
            this.notifyAdd(context, path, list);
        }
    }
    
    @Override
    public void notifyRemove(final IContext context, final IPath path, final int n, final List<IModelObject> list) {
        if (n == path.length()) {
            this.notifyRemove(context, path, list);
            final Iterator<IModelObject> iterator = list.iterator();
            while (iterator.hasNext()) {
                iterator.next().removeModelListener(this.\u00e0);
            }
        }
    }
}
