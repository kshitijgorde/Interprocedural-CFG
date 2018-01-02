// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.B;

import org.xmodel.xpath.expression.IExpression;
import org.xmodel.IModel;
import java.util.Collections;
import org.xmodel.G;
import org.xmodel.xpath.expression.SubContext;
import java.util.ArrayList;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IExpressionListener;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.ModelAlgorithms;
import org.xmodel.IPath;
import org.xmodel.xpath.expression.ExpressionListener;

public class Q extends ExpressionListener implements R
{
    R Q;
    IPath R;
    
    public Q(final R q) {
        this.Q = q;
        this.R = ModelAlgorithms.createCandidatePath(this.B().D(), this.A() - 1);
    }
    
    protected Q(final Q q, final A a) {
        this.Q = q.Q.A(a);
        this.R = q.R;
    }
    
    @Override
    public void B(final IContext context) {
        this.D().A(this);
    }
    
    @Override
    public void A(final IContext context) {
        this.D().B(this);
    }
    
    @Override
    public void D(final List<IModelObject> list) {
        final IContext b = this.B().B();
        final ArrayList<IModelObject> list2 = new ArrayList<IModelObject>(list.size());
        final G d = this.D();
        for (int i = 0; i < list.size(); ++i) {
            final IModelObject modelObject = list.get(i);
            d.bind(new SubContext(b, modelObject, i + 1, list.size()));
            if (d.A(b, this.R, modelObject)) {
                list2.add(modelObject);
            }
        }
        if (list2.size() > 0) {
            this.Q.D(list2);
        }
    }
    
    @Override
    public void B(final List<IModelObject> list) {
        final IContext b = this.B().B();
        final ArrayList<IModelObject> list2 = new ArrayList<IModelObject>(list.size());
        final G d = this.D();
        for (int i = 0; i < list.size(); ++i) {
            final IModelObject modelObject = list.get(i);
            d.unbind(new SubContext(b, modelObject, i + 1, list.size()));
            if (d.A(b, this.R, modelObject)) {
                list2.add(modelObject);
            }
        }
        if (list2.size() > 0) {
            this.Q.B(list2);
        }
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
        final A b = this.B();
        final List<IModelObject> query = this.R.query(b.B(), null);
        final int indexOfSubList = Collections.indexOfSubList(query, list);
        final int size = query.size();
        final IContext b2 = b.B();
        final G d = this.D();
        for (int i = 0; i < list.size(); ++i) {
            d.bind(new SubContext(b2, (IModelObject)list.get(i), i + indexOfSubList + 1, size));
        }
        final ArrayList<IModelObject> list2 = new ArrayList<IModelObject>(list.size());
        for (int j = 0; j < list.size(); ++j) {
            final IModelObject modelObject = (IModelObject)list.get(j);
            if (d.A(b2, this.R, modelObject)) {
                list2.add(modelObject);
            }
        }
        if (list2.size() > 0) {
            this.Q.A(list2);
        }
    }
    
    @Override
    public void C(final List<IModelObject> list) {
        final A b = this.B();
        final IContext b2 = b.B();
        final IModel model = b2.getModel();
        model.revert();
        final List<IModelObject> query = this.R.query(b2, null);
        model.restore();
        final int indexOfSubList = Collections.indexOfSubList(query, list);
        final int size = query.size();
        final IContext b3 = b.B();
        final G d = this.D();
        for (int i = 0; i < list.size(); ++i) {
            d.unbind(new SubContext(b3, (IModelObject)list.get(i), i + indexOfSubList + 1, size));
        }
        model.revert();
        final ArrayList<IModelObject> list2 = new ArrayList<IModelObject>(list.size());
        for (int j = 0; j < list.size(); ++j) {
            final IModelObject modelObject = (IModelObject)list.get(j);
            if (d.A(b3, this.R, modelObject)) {
                list2.add(modelObject);
            }
        }
        model.restore();
        if (list2.size() > 0) {
            this.Q.C(list2);
        }
    }
    
    @Override
    public A B() {
        return this.Q.B();
    }
    
    @Override
    public int A() {
        return this.Q.A();
    }
    
    @Override
    public R A(final A a) {
        return new Q(this, a);
    }
    
    public G D() {
        return this.B().D().getPathElement(this.A() - 1).predicate();
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
        if (context.getParent() != this.B().B()) {
            return;
        }
        final IContext parent = context.getParent();
        final R r = this.B().C()[this.A()];
        context.getModel().revert();
        final List<IModelObject> query = this.R.query(parent, null);
        context.getModel().restore();
        final List<IModelObject> query2 = this.R.query(parent, null);
        r.B(query);
        r.D(query2);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final boolean b) {
        if (context.getParent() != this.B().B()) {
            return;
        }
        if (b) {
            this.Q.B(context.getObject());
        }
        else {
            this.Q.A(context.getObject());
        }
    }
    
    @Override
    public boolean requiresValueNotification() {
        return false;
    }
    
    @Override
    public String toString() {
        return "PredicateGuard: " + this.Q.B();
    }
}
