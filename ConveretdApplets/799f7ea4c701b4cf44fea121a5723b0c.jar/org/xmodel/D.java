// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import org.xmodel.B.P;
import org.xmodel.xpath.variable.IVariableScope;
import org.xmodel.xpath.variable.VariableScope;
import org.xmodel.xpath.variable.VariableSource;
import org.xmodel.xpath.expression.IContext;
import java.util.ArrayList;
import org.xmodel.xpath.variable.IVariableSource;
import org.xmodel.B.A;
import java.util.List;
import org.xmodel.xpath.expression.IExpression;

public abstract class D implements IPath, E
{
    private IExpression C;
    private B[] E;
    private List<IModelObject> B;
    private IPath A;
    protected A chainProto;
    private Boolean F;
    private IVariableSource D;
    
    public D() {
        this.E = new B[0];
        this.B = new ArrayList<IModelObject>();
    }
    
    @Override
    public void setParent(final IExpression c) {
        this.C = c;
    }
    
    public void addElement(final B b) {
        this.A = null;
        this.F = null;
        if (this.chainProto != null) {
            throw new IllegalStateException("Unable to add elements to bound path.");
        }
        final ArrayList<B> list = new ArrayList<B>(this.E.length + 1);
        for (int i = 0; i < this.E.length; ++i) {
            list.add(this.E[i]);
        }
        list.add(b);
        this.E = list.toArray(new B[0]);
    }
    
    public void addElement(final int n, final B b) {
        this.A = null;
        this.F = null;
        if (this.chainProto != null) {
            throw new IllegalStateException("Unable to add elements to bound path.");
        }
        final ArrayList<B> list = new ArrayList<B>(this.E.length + 1);
        for (int i = 0; i < this.E.length; ++i) {
            list.add(this.E[i]);
        }
        list.add(n, b);
        this.E = list.toArray(new B[0]);
    }
    
    public B removeElement(final int n) {
        this.A = null;
        this.F = null;
        if (this.chainProto != null) {
            throw new IllegalStateException("Unable to remove elements from bound path.");
        }
        final ArrayList<Object> list = new ArrayList<Object>(this.E.length + 1);
        for (int i = 0; i < this.E.length; ++i) {
            list.add(this.E[i]);
        }
        final B b = list.remove(n);
        this.E = list.toArray(new B[0]);
        return b;
    }
    
    @Override
    public boolean isAbsolute(final IContext context) {
        if (this.F != null) {
            return this.F;
        }
        if (this.E.length == 0) {
            final Boolean value = false;
            this.F = value;
            return value;
        }
        if ((this.E[0].axis() & 0x1) == 0x0) {
            final Boolean value2 = false;
            this.F = value2;
            return value2;
        }
        for (int i = 0; i < this.E.length; ++i) {
            final G predicate = this.E[i].predicate();
            if (predicate != null && !predicate.isAbsolute(context)) {
                final Boolean value3 = false;
                this.F = value3;
                return value3;
            }
        }
        final Boolean value4 = true;
        this.F = value4;
        return value4;
    }
    
    @Override
    public B getPathElement(final int n) {
        return this.E[n];
    }
    
    @Override
    public int length() {
        return this.E.length;
    }
    
    @Override
    public IVariableSource getVariableSource() {
        if (this.C != null) {
            return this.C.getVariableSource();
        }
        if (this.D == null) {
            (this.D = new VariableSource()).addScope(new VariableScope("local", 1));
        }
        return this.D;
    }
    
    @Override
    public void setVariable(final String s, final String s2) {
        this.getVariableSource().getScope("local").set(s, s2);
    }
    
    @Override
    public List<IModelObject> query(final IModelObject modelObject, final List<IModelObject> list) {
        return this.query(null, modelObject, this.length(), list);
    }
    
    @Override
    public List<IModelObject> query(final IContext context, final List<IModelObject> list) {
        return this.query(context, context.getObject(), this.length(), list);
    }
    
    protected List<IModelObject> query(final IContext context, final IModelObject modelObject, final int n, List<IModelObject> list) {
        if (list == null) {
            list = new ArrayList<IModelObject>();
        }
        final boolean b = n % 2 == 0;
        List<IModelObject> list2 = b ? this.B : list;
        List<IModelObject> list3 = b ? list : this.B;
        list3.clear();
        list3.add(modelObject);
        for (int i = 0; i < n; ++i) {
            final B b2 = this.E[i];
            final List<IModelObject> list4 = list2;
            list2 = list3;
            list3 = list4;
            list3.clear();
            for (int size = list2.size(), j = 0; j < size; ++j) {
                b2.query(context, list2.get(j), list3);
            }
        }
        return list;
    }
    
    @Override
    public IModelObject queryFirst(final IModelObject modelObject) {
        final List<IModelObject> query = this.query(modelObject, null);
        if (query.size() == 0) {
            return null;
        }
        return query.get(0);
    }
    
    @Override
    public void addPathListener(final IContext context, final IPathListener pathListener) {
        if (context == null) {
            throw new IllegalArgumentException("Attempt to add listener to null context: path:" + this.toString());
        }
        if (this.chainProto == null) {
            this.chainProto = new P(this, null, null);
        }
        this.query(context, null);
        final C pathListeners = context.getObject().getPathListeners();
        if (pathListeners.A(context, pathListener) == null) {
            final P p2 = new P(this, this.chainProto, context, pathListener);
            p2.B(context.getObject());
            pathListeners.A(p2);
        }
    }
    
    @Override
    public void removePathListener(final IContext context, final IPathListener pathListener) {
        if (context == null) {
            throw new IllegalArgumentException("Attempt to remove listener from null context: path:" + this.toString());
        }
        final A b = context.getObject().getPathListeners().B(context, pathListener);
        if (b == null) {
            return;
        }
        b.A(context.getObject());
    }
    
    @Override
    public IPath clone() {
        final CanonicalPath canonicalPath = new CanonicalPath(this);
        canonicalPath.C = this.C;
        canonicalPath.chainProto = this.chainProto;
        return canonicalPath;
    }
}
