// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.B;

import org.xmodel.xpath.expression.IContext;
import org.xmodel.IPath;
import org.xmodel.IModelListener;
import org.xmodel.IModelObject;

public class M extends J
{
    public M(final A a, final int n) {
        super(a, n);
    }
    
    @Override
    protected void D(final IModelObject modelObject) {
        for (IModelObject parent = modelObject; parent != null; parent = parent.getParent()) {
            modelObject.addModelListener(this);
        }
    }
    
    @Override
    protected void C(final IModelObject modelObject) {
        for (IModelObject parent = modelObject; parent != null; parent = parent.getParent()) {
            modelObject.removeModelListener(this);
        }
    }
    
    @Override
    public R A(final A a) {
        return new M(a, this.U);
    }
    
    @Override
    public void notifyParent(final IModelObject modelObject, final IModelObject modelObject2, final IModelObject modelObject3) {
        final IPath d = this.B().D();
        final IContext b = this.B().B();
        if (modelObject3 != null) {
            this.C(modelObject3);
            final IModelObject root = modelObject3.getRoot();
            if (this.V.evaluate(b, d, root)) {
                this.E().A(root);
            }
        }
        else if (this.V.evaluate(b, d, modelObject)) {
            this.E().A(modelObject);
        }
        if (modelObject2 != null) {
            this.D(modelObject2);
            final IModelObject root2 = modelObject.getRoot();
            if (this.V.evaluate(b, d, root2)) {
                this.E().B(root2);
            }
        }
        else if (this.V.evaluate(b, d, modelObject)) {
            this.E().B(modelObject);
        }
    }
}
