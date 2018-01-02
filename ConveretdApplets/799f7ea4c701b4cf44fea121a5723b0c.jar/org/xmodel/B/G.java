// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.B;

import org.xmodel.IModelListener;
import org.xmodel.IModelObject;

public class G extends J
{
    public G(final A a, final int n) {
        super(a, n);
    }
    
    @Override
    protected void D(final IModelObject modelObject) {
        modelObject.addModelListener(this);
    }
    
    @Override
    protected void C(final IModelObject modelObject) {
        modelObject.removeModelListener(this);
    }
    
    @Override
    public R A(final A a) {
        return new G(a, this.U);
    }
    
    @Override
    public void notifyParent(final IModelObject modelObject, final IModelObject modelObject2, final IModelObject modelObject3) {
        if (modelObject3 != null) {
            this.E().A(modelObject3);
        }
        if (modelObject2 != null) {
            this.E().B(modelObject2);
        }
    }
}
