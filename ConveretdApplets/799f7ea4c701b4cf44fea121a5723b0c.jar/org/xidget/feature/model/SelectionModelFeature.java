// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.feature.model;

import org.xidget.ifeature.IBindFeature;
import java.util.Iterator;
import org.xmodel.Reference;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xpath.XPath;
import java.util.Collections;
import org.xmodel.ModelListener;
import org.xidget.ifeature.model.ISelectionUpdateFeature;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.ExpressionListener;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IExpression;
import org.xidget.IXidget;
import org.xmodel.IModelListener;
import org.xmodel.xpath.expression.IExpressionListener;
import org.xidget.ifeature.model.ISelectionModelFeature;

public class SelectionModelFeature implements ISelectionModelFeature
{
    private IExpressionListener variableListener;
    private IModelListener parentListener;
    protected IXidget xidget;
    private String varName;
    private IExpression varExpr;
    private IModelObject parent;
    
    public SelectionModelFeature(final IXidget xidget) {
        this.variableListener = new ExpressionListener() {
            @Override
            public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
                SelectionModelFeature.this.xidget.getFeature(ISelectionUpdateFeature.class).displaySelect(list);
            }
            
            @Override
            public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
                SelectionModelFeature.this.xidget.getFeature(ISelectionUpdateFeature.class).displayDeselect(list);
            }
        };
        this.parentListener = new ModelListener() {
            @Override
            public void notifyAddChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
                SelectionModelFeature.this.xidget.getFeature(ISelectionUpdateFeature.class).displaySelect(Collections.singletonList(modelObject2));
            }
            
            @Override
            public void notifyRemoveChild(final IModelObject modelObject, final IModelObject modelObject2, final int n) {
                SelectionModelFeature.this.xidget.getFeature(ISelectionUpdateFeature.class).displayDeselect(Collections.singletonList(modelObject2));
            }
        };
        this.xidget = xidget;
    }
    
    @Override
    public void setSourceVariable(final String varName) {
        final StatefulContext context = this.getContext();
        if (this.varName != null) {
            this.varExpr.removeNotifyListener(context, this.variableListener);
        }
        this.varName = varName;
        this.varExpr = XPath.createExpression("$" + this.varName, false);
        if (!context.getScope().isDefined(this.varName)) {
            context.getScope().set(this.varName, Collections.emptyList());
        }
        if (this.varName != null) {
            this.varExpr.addNotifyListener(context, this.variableListener);
        }
    }
    
    @Override
    public void setSourceNode(final IModelObject parent) {
        if (this.parent != null) {
            this.parent.removeModelListener(this.parentListener);
            this.xidget.getFeature(ISelectionUpdateFeature.class).displayDeselect(this.parent.getChildren());
        }
        this.parent = parent;
        if (this.parent != null) {
            this.parent.addModelListener(this.parentListener);
            this.xidget.getFeature(ISelectionUpdateFeature.class).displaySelect(this.parent.getChildren());
        }
    }
    
    @Override
    public void select(final Object o) {
        if (this.varName != null) {
            final StatefulContext context = this.getContext();
            if (context != null) {
                context.getScope().insert(this.varName, o);
            }
        }
        if (this.parent != null && o instanceof IModelObject) {
            this.parent.addChild(new Reference((IModelObject)o));
        }
    }
    
    @Override
    public void deselect(final Object o) {
        if (this.varName != null) {
            final StatefulContext context = this.getContext();
            if (context != null) {
                context.getScope().remove(this.varName, o);
            }
        }
        if (this.parent != null && o instanceof IModelObject) {
            this.parent.removeChild(new Reference((IModelObject)o));
        }
    }
    
    @Override
    public void setSelection(List<?> emptyList) {
        if (emptyList == null) {
            emptyList = Collections.emptyList();
        }
        if (this.varName != null) {
            final StatefulContext context = this.getContext();
            if (context != null) {
                context.getScope().set(this.varName, emptyList);
            }
        }
        if (this.parent != null) {
            this.parent.removeChildren();
            final Iterator<?> iterator = emptyList.iterator();
            while (iterator.hasNext()) {
                this.parent.addChild(new Reference((IModelObject)iterator.next()));
            }
        }
    }
    
    @Override
    public List<?> getSelection() {
        final StatefulContext context = this.getContext();
        if (context != null && this.varName != null) {
            final List list = (List)context.getScope().get(this.varName);
            if (list != null) {
                return (List<?>)list;
            }
        }
        if (this.parent != null) {
            return this.parent.getChildren();
        }
        return Collections.emptyList();
    }
    
    protected StatefulContext getContext() {
        return this.xidget.getFeature(IBindFeature.class).getBoundContext();
    }
}
