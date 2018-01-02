// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import java.util.Iterator;
import org.xmodel.IModelListener;
import org.xmodel.ModelAlgorithms;
import org.xmodel.IChangeSet;
import org.xmodel.IModelObjectFactory;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.IPath;
import org.xmodel.IPathListener;

public class A extends L implements IPathListener
{
    private IPath \u00de;
    private boolean \u00df;
    
    public A(final IPath \u00fe) {
        (this.\u00de = \u00fe).setParent(this);
    }
    
    @Override
    public String getName() {
        return "path";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.NODES;
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        return this.\u00de.query(context, null);
    }
    
    @Override
    public boolean isAbsolute(final IContext context) {
        return this.\u00de.isAbsolute(context);
    }
    
    @Override
    public void createSubtree(final IContext context, final IModelObjectFactory modelObjectFactory, final IChangeSet set) {
        ModelAlgorithms.createPathSubtree(context, this.\u00de, modelObjectFactory, set);
    }
    
    @Override
    public void bind(final IContext context) {
        try {
            this.\u00df = true;
            this.\u00de.addPathListener(context, this);
        }
        finally {
            this.\u00df = false;
        }
        this.\u00df = false;
    }
    
    @Override
    public void unbind(final IContext context) {
        try {
            this.\u00df = true;
            this.\u00de.removePathListener(context, this);
        }
        finally {
            this.\u00df = false;
        }
        this.\u00df = false;
    }
    
    @Override
    public void notifyAdd(final IContext context, final IPath path, final int n, final List<IModelObject> list) {
        if (n < path.length() || this.k == null) {
            return;
        }
        if (!this.\u00df) {
            this.k.notifyAdd(this, context, list);
        }
        if (this.k.requiresValueNotification(this)) {
            final B b = new B(this, context);
            final Iterator<IModelObject> iterator = list.iterator();
            while (iterator.hasNext()) {
                iterator.next().addModelListener(b);
            }
        }
    }
    
    @Override
    public void notifyRemove(final IContext context, final IPath path, final int n, final List<IModelObject> list) {
        if (n < path.length() || this.k == null) {
            return;
        }
        if (!this.\u00df) {
            this.k.notifyRemove(this, context, list);
        }
        if (this.k.requiresValueNotification(this)) {
            for (final IModelObject modelObject : list) {
                final B a = B.A(modelObject, this, context);
                if (a != null) {
                    modelObject.removeModelListener(a);
                }
            }
        }
    }
    
    public IPath P() {
        return this.\u00de;
    }
    
    @Override
    protected IExpression cloneOne() {
        return new A(this.\u00de.clone());
    }
    
    @Override
    public String toString() {
        return ModelAlgorithms.pathToString(this.\u00de);
    }
}
