// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.B;

import org.xmodel.xpath.expression.IContext;
import org.xmodel.IPath;
import java.util.List;
import java.util.ArrayList;
import org.xmodel.IModelListener;
import org.xmodel.IModelObject;

public class K extends J
{
    public K(final A a, final int n) {
        super(a, n);
    }
    
    @Override
    protected void D(final IModelObject modelObject) {
        for (IModelObject parent = modelObject; parent != null; parent = parent.getParent()) {
            parent.addModelListener(this);
        }
    }
    
    @Override
    protected void C(final IModelObject modelObject) {
        for (IModelObject parent = modelObject; parent != null; parent = parent.getParent()) {
            parent.removeModelListener(this);
        }
    }
    
    @Override
    public R A(final A a) {
        return new K(a, this.U);
    }
    
    @Override
    public void notifyParent(final IModelObject modelObject, final IModelObject modelObject2, final IModelObject modelObject3) {
        final IPath d = this.B().D();
        final IContext b = this.B().B();
        if (modelObject3 != null) {
            this.C(modelObject3);
            final ArrayList<IModelObject> list = new ArrayList<IModelObject>(3);
            for (IModelObject parent = modelObject3; parent != null; parent = parent.getParent()) {
                if (this.V.evaluate(b, d, parent)) {
                    list.add(parent);
                }
            }
            this.E().C(list);
        }
        if (modelObject2 != null) {
            final ArrayList<IModelObject> list2 = new ArrayList<IModelObject>(3);
            for (IModelObject parent2 = modelObject2; parent2 != null; parent2 = parent2.getParent()) {
                if (this.V.evaluate(b, d, parent2)) {
                    list2.add(parent2);
                }
            }
            this.E().A(list2);
            this.D(modelObject2);
        }
    }
}
