// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.external;

import org.xmodel.IModelListener;
import org.xmodel.IModelObject;
import org.xmodel.ModelListener;

public class NonSyncingListener extends ModelListener
{
    @Override
    public void notifyParent(final IModelObject modelObject, final IModelObject modelObject2, final IModelObject modelObject3) {
        if (modelObject2 == null) {
            this.uninstall(modelObject);
        }
    }
    
    @Override
    public void notifyAddChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
        this.install(modelObject2);
    }
    
    @Override
    public void notifyRemoveChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
        this.uninstall(modelObject2);
    }
    
    public void install(final IModelObject modelObject) {
        final NonSyncingIterator nonSyncingIterator = new NonSyncingIterator(modelObject);
        while (nonSyncingIterator.hasNext()) {
            nonSyncingIterator.next().addModelListener(this);
        }
    }
    
    public void uninstall(final IModelObject modelObject) {
        final NonSyncingIterator nonSyncingIterator = new NonSyncingIterator(modelObject);
        while (nonSyncingIterator.hasNext()) {
            nonSyncingIterator.next().removeModelListener(this);
        }
    }
}
