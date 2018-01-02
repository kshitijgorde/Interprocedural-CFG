// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.listeners;

import java.util.Iterator;
import org.xmodel.IModelListener;
import java.util.List;
import org.xmodel.IPath;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.IModelObject;
import org.xmodel.IPathListener;
import org.xmodel.ModelListener;

public abstract class ValueObserver extends ModelListener implements IPathListener
{
    @Override
    public void notifyChange(final IModelObject modelObject, final String s, final Object o, final Object o2) {
        if (s.length() == 0) {
            this.notifyValue(modelObject, o, o2);
        }
    }
    
    @Override
    public void notifyClear(final IModelObject modelObject, final String s, final Object o) {
        if (s.length() == 0) {
            this.notifyValue(modelObject, null, o);
        }
    }
    
    @Override
    public void notifyDirty(final IModelObject modelObject, final boolean b) {
        if (b) {
            modelObject.getValue();
        }
    }
    
    @Override
    public void notifyAdd(final IContext context, final IPath path, final int n, final List<IModelObject> list) {
        if (n != path.length()) {
            return;
        }
        for (final IModelObject modelObject : list) {
            modelObject.addModelListener(this);
            final Object value = modelObject.getValue();
            if (value != null) {
                this.notifyValue(modelObject, value, null);
            }
        }
    }
    
    @Override
    public void notifyRemove(final IContext context, final IPath path, final int n, final List<IModelObject> list) {
        if (n != path.length()) {
            return;
        }
        final Iterator<IModelObject> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next().removeModelListener(this);
        }
    }
    
    protected abstract void notifyValue(final IModelObject p0, final Object p1, final Object p2);
}
