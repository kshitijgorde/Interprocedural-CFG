// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.listeners;

import org.xmodel.IModelListener;
import org.xmodel.IAncestorListener;
import org.xmodel.IModelObject;
import org.xmodel.ModelListener;

public class ClimbingListener extends ModelListener
{
    IModelObject \u00e3;
    IAncestorListener \u00e4;
    
    public ClimbingListener(final IAncestorListener \u00e4) {
        this.\u00e4 = \u00e4;
    }
    
    public void addListenerToTree(IModelObject parent) {
        this.\u00e3 = parent;
        while (parent != null) {
            parent.addModelListener(this);
            parent = parent.getParent();
        }
    }
    
    public void removeListenerFromTree(IModelObject parent) {
        while (parent != null) {
            parent.removeModelListener(this);
            parent = parent.getParent();
        }
    }
    
    @Override
    public void notifyParent(final IModelObject modelObject, final IModelObject modelObject2, final IModelObject modelObject3) {
        if (modelObject2 == null) {
            if (modelObject3 == null) {
                return;
            }
            this.A(modelObject, modelObject3);
        }
        else {
            if (modelObject2 == modelObject3) {
                return;
            }
            if (modelObject3 == null) {
                this.B(modelObject, modelObject2);
            }
            else {
                this.A(modelObject, modelObject2, modelObject3);
            }
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof ClimbingListener) {
            return o == this;
        }
        return o instanceof IAncestorListener && o == this.\u00e4;
    }
    
    private final void B(final IModelObject modelObject, final IModelObject modelObject2) {
        this.addListenerToTree(modelObject2);
        this.\u00e4.notifyAttach(this.\u00e3, modelObject, modelObject2, null);
    }
    
    private final void A(final IModelObject modelObject, final IModelObject modelObject2, final IModelObject modelObject3) {
        this.removeListenerFromTree(modelObject3);
        this.addListenerToTree(modelObject2);
        this.\u00e4.notifyAttach(this.\u00e3, modelObject, modelObject2, modelObject3);
    }
    
    private final void A(final IModelObject modelObject, final IModelObject modelObject2) {
        this.removeListenerFromTree(modelObject2);
        this.\u00e4.notifyDetach(this.\u00e3, modelObject, modelObject2);
    }
    
    @Override
    public String toString() {
        return "ClimbingListener( object=" + this.\u00e3 + ")";
    }
}
