// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.B;

import java.util.Collections;
import org.xmodel.IPath;
import org.xmodel.IPathListener;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.log.Log;

public class E implements R
{
    private static Log D;
    A C;
    
    static {
        E.D = Log.getLog("org.xmodel.path");
    }
    
    public E(final A c) {
        this.C = c;
    }
    
    @Override
    public void B(final IContext context) {
    }
    
    @Override
    public void A(final IContext context) {
    }
    
    @Override
    public void D(final List<IModelObject> list) {
        this.B().B(list, this.A());
        final IPathListener a = this.C.A();
        if (a != null) {
            try {
                final IPath d = this.C.D();
                a.notifyAdd(this.C.B(), d, d.length(), list);
            }
            catch (Exception ex) {
                E.D.exception(ex);
            }
        }
    }
    
    @Override
    public void B(final List<IModelObject> list) {
        final IPathListener a = this.C.A();
        if (a != null) {
            try {
                final IPath d = this.C.D();
                a.notifyRemove(this.C.B(), d, d.length(), list);
            }
            catch (Exception ex) {
                E.D.exception(ex);
            }
        }
        this.B().A(list, this.A());
    }
    
    @Override
    public void B(final IModelObject modelObject) {
        this.A(Collections.singletonList(modelObject));
    }
    
    @Override
    public void A(final IModelObject modelObject) {
        this.C(Collections.singletonList(modelObject));
    }
    
    @Override
    public void A(final List<IModelObject> list) {
        this.B().D(list, this.A());
        final IPathListener a = this.C.A();
        if (a != null) {
            try {
                final IPath d = this.C.D();
                a.notifyAdd(this.C.B(), d, d.length(), list);
            }
            catch (Exception ex) {
                E.D.exception(ex);
            }
        }
    }
    
    @Override
    public void C(final List<IModelObject> list) {
        final IPathListener a = this.C.A();
        if (a != null) {
            try {
                final IPath d = this.C.D();
                a.notifyRemove(this.C.B(), d, d.length(), list);
            }
            catch (Exception ex) {
                E.D.exception(ex);
            }
        }
        this.B().C(list, this.A());
    }
    
    @Override
    public A B() {
        return this.C;
    }
    
    @Override
    public int A() {
        return this.C.D().length();
    }
    
    @Override
    public R A(final A a) {
        return new E(a);
    }
}
