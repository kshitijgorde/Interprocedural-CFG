// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.B;

import org.xmodel.xpath.expression.IContext;
import java.util.Collections;
import java.util.Iterator;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.PathElement;
import org.xmodel.B;

public abstract class J extends F
{
    B V;
    
    protected J(final A a, final int n) {
        super(a, n);
        final B pathElement = a.D().getPathElement(n);
        this.V = new PathElement(pathElement.axis(), pathElement.type());
    }
    
    @Override
    public void D(final List<IModelObject> list) {
        this.B().B(list, this.A());
        this.E(list);
        final Iterator<IModelObject> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.D(iterator.next());
        }
        final List<IModelObject> query = this.V.query(this.B().B(), list, null);
        final R e = this.E();
        if (query.size() > 0) {
            e.D(query);
        }
    }
    
    @Override
    public void B(final List<IModelObject> list) {
        final Iterator<IModelObject> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.C(iterator.next());
        }
        final List<IModelObject> query = this.V.query(this.B().B(), list, null);
        final R e = this.E();
        if (query.size() > 0) {
            e.B(query);
        }
        this.F(list);
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
        this.E(list);
        final IContext b = this.B().B();
        final R e = this.E();
        final List<IModelObject> query = this.V.query(b, list, null);
        if (query.size() > 0) {
            e.A(query);
        }
        final Iterator<IModelObject> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.D(iterator.next());
        }
    }
    
    @Override
    public void C(final List<IModelObject> list) {
        final Iterator<IModelObject> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.C(iterator.next());
        }
        final IContext b = this.B().B();
        final R e = this.E();
        final List<IModelObject> query = this.V.query(b, list, null);
        if (query.size() > 0) {
            e.C(query);
        }
        this.F(list);
        this.B().C(list, this.A());
    }
    
    protected abstract void D(final IModelObject p0);
    
    protected abstract void C(final IModelObject p0);
    
    @Override
    public void notifyDirty(final IModelObject modelObject, final boolean b) {
    }
    
    protected R E() {
        return this.S.C()[this.U + 1];
    }
}
