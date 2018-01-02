// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.listeners;

import org.xmodel.IModelObject;
import org.xmodel.IChangeSet;
import org.xmodel.IModelListener;

public class UndoListener implements IModelListener
{
    private IChangeSet A;
    private IChangeSet B;
    
    public UndoListener(final IChangeSet a) {
        this.A = a;
    }
    
    public UndoListener(final IChangeSet a, final IChangeSet b) {
        this.A = a;
        this.B = b;
    }
    
    @Override
    public void notifyParent(final IModelObject modelObject, final IModelObject modelObject2, final IModelObject modelObject3) {
    }
    
    @Override
    public void notifyAddChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
        this.A.removeChild(modelObject, modelObject2);
        if (this.B != null) {
            this.B.addChild(modelObject, modelObject2, n);
        }
    }
    
    @Override
    public void notifyRemoveChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
        this.A.addChild(modelObject, modelObject2, n);
        if (this.B != null) {
            this.B.removeChild(modelObject, modelObject2);
        }
    }
    
    @Override
    public void notifyChange(final IModelObject modelObject, final String s, final Object o, final Object o2) {
        this.A.setAttribute(modelObject, s, o2);
        if (this.B != null) {
            this.B.setAttribute(modelObject, s, o);
        }
    }
    
    @Override
    public void notifyClear(final IModelObject modelObject, final String s, final Object o) {
        this.A.setAttribute(modelObject, s, o);
        if (this.B != null) {
            this.B.removeAttribute(modelObject, s);
        }
    }
    
    @Override
    public void notifyDirty(final IModelObject modelObject, final boolean b) {
    }
}
