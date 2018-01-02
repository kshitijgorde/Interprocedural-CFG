// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.B;

import org.xmodel.IPathListener;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.log.Log;
import org.xmodel.IModelListener;

public abstract class F implements R, IModelListener
{
    private static Log T;
    A S;
    int U;
    
    static {
        F.T = Log.getLog("org.xmodel.path");
    }
    
    protected F(final A s, final int u) {
        this.S = s;
        this.U = u;
    }
    
    @Override
    public void B(final IContext context) {
    }
    
    @Override
    public void A(final IContext context) {
    }
    
    protected void E(final List<IModelObject> list) {
        if (list.size() == 0) {
            return;
        }
        final IPathListener a = this.S.A();
        if (a != null) {
            try {
                a.notifyAdd(this.S.B(), this.S.D(), this.U, list);
            }
            catch (Exception ex) {
                F.T.exception(ex);
            }
        }
    }
    
    protected void F(final List<IModelObject> list) {
        if (list.size() == 0) {
            return;
        }
        final IPathListener a = this.S.A();
        if (a != null) {
            try {
                a.notifyRemove(this.S.B(), this.S.D(), this.U, list);
            }
            catch (Exception ex) {
                F.T.exception(ex);
            }
        }
    }
    
    @Override
    public A B() {
        return this.S;
    }
    
    @Override
    public int A() {
        return this.U;
    }
    
    @Override
    public String toString() {
        return this.S.toString();
    }
    
    @Override
    public void notifyParent(final IModelObject modelObject, final IModelObject modelObject2, final IModelObject modelObject3) {
    }
    
    @Override
    public void notifyAddChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
    }
    
    @Override
    public void notifyRemoveChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
    }
    
    @Override
    public void notifyChange(final IModelObject modelObject, final String s, final Object o, final Object o2) {
    }
    
    @Override
    public void notifyClear(final IModelObject modelObject, final String s, final Object o) {
    }
    
    @Override
    public void notifyDirty(final IModelObject modelObject, final boolean b) {
    }
}
