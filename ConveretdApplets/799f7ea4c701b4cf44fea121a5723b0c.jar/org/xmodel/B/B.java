// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.B;

import org.xmodel.IModelListener;
import org.xmodel.IModelObject;

public class B extends J
{
    public B(final A a, final int n) {
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
        return new B(a, this.U);
    }
    
    @Override
    public void notifyAddChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
        if (this.V.evaluate(this.B().B(), this.B().D(), modelObject2)) {
            this.E().B(modelObject2);
        }
    }
    
    @Override
    public void notifyRemoveChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
        if (this.V.evaluate(this.B().B(), this.B().D(), modelObject2)) {
            this.E().A(modelObject2);
        }
    }
}
