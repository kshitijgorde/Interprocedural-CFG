// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import java.util.Iterator;
import org.xmodel.IModelListener;
import org.xmodel.IChangeSet;
import org.xmodel.IModelObjectFactory;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;
import org.xmodel.xpath.variable.IVariableSource;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.variable.IVariableScope;
import org.xmodel.xpath.variable.IVariableListener;

public class E extends L
{
    final IVariableListener \u00c5;
    private String \u00c4;
    
    public E(final String \u00e4) {
        this.\u00c5 = new IVariableListener() {
            @Override
            public void notifyAdd(final String s, final IVariableScope variableScope, final IContext context, final List<IModelObject> list) {
                E.this.D(context, list);
                E.this.k.notifyAdd(E.this, context, list);
            }
            
            @Override
            public void notifyRemove(final String s, final IVariableScope variableScope, final IContext context, final List<IModelObject> list) {
                E.this.C(context, list);
                E.this.k.notifyRemove(E.this, context, list);
            }
            
            @Override
            public void notifyChange(final String s, final IVariableScope variableScope, final IContext context, final Boolean b) {
                E.this.k.notifyChange(E.this, context, b);
            }
            
            @Override
            public void notifyChange(final String s, final IVariableScope variableScope, final IContext context, final Number n, final Number n2) {
                E.this.k.notifyChange(E.this, context, n.doubleValue(), n2.doubleValue());
            }
            
            @Override
            public void notifyChange(final String s, final IVariableScope variableScope, final IContext context, final String s2, final String s3) {
                E.this.k.notifyChange(E.this, context, s2, s3);
            }
        };
        this.\u00c4 = \u00e4;
    }
    
    @Override
    public String getName() {
        return "variable";
    }
    
    @Override
    public IExpression.ResultType getType() {
        final IVariableSource variableSource = this.getVariableSource();
        if (variableSource == null) {
            return IExpression.ResultType.UNDEFINED;
        }
        return variableSource.getVariableType(this.\u00c4);
    }
    
    @Override
    public IExpression.ResultType getType(final IContext context) {
        final IVariableSource variableSource = this.getVariableSource();
        try {
            variableSource.addScope(context.getScope());
            return variableSource.getVariableType(this.\u00c4, context);
        }
        finally {
            variableSource.removeScope(context.getScope());
        }
    }
    
    @Override
    public boolean evaluateBoolean(final IContext context) throws ExpressionException {
        final IExpression.ResultType type = this.getType(context);
        if (type == IExpression.ResultType.UNDEFINED) {
            return false;
        }
        final IVariableSource variableSource = this.getVariableSource();
        try {
            if (type == IExpression.ResultType.BOOLEAN) {
                variableSource.addScope(context.getScope());
                return (boolean)variableSource.getVariable(this.\u00c4, context);
            }
            return super.evaluateBoolean(context);
        }
        finally {
            variableSource.removeScope(context.getScope());
        }
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        final IExpression.ResultType type = this.getType(context);
        if (type == IExpression.ResultType.UNDEFINED) {
            return Collections.emptyList();
        }
        final IVariableSource variableSource = this.getVariableSource();
        try {
            variableSource.addScope(context.getScope());
            if (type == IExpression.ResultType.NODES) {
                return new ArrayList<IModelObject>((Collection<?>)variableSource.getVariable(this.\u00c4, context));
            }
            return super.evaluateNodes(context);
        }
        finally {
            variableSource.removeScope(context.getScope());
        }
    }
    
    @Override
    public double evaluateNumber(final IContext context) throws ExpressionException {
        final IExpression.ResultType type = this.getType(context);
        if (type == IExpression.ResultType.UNDEFINED) {
            return 0.0;
        }
        final IVariableSource variableSource = this.getVariableSource();
        try {
            variableSource.addScope(context.getScope());
            if (type == IExpression.ResultType.NUMBER) {
                return ((Number)variableSource.getVariable(this.\u00c4, context)).doubleValue();
            }
            return super.evaluateNumber(context);
        }
        finally {
            variableSource.removeScope(context.getScope());
        }
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        final IExpression.ResultType type = this.getType(context);
        if (type == IExpression.ResultType.UNDEFINED) {
            return "";
        }
        final IVariableSource variableSource = this.getVariableSource();
        try {
            variableSource.addScope(context.getScope());
            if (type != IExpression.ResultType.STRING) {
                return super.evaluateString(context);
            }
            final Object variable = variableSource.getVariable(this.\u00c4, context);
            if (variable == null) {
                return "";
            }
            return variable.toString();
        }
        finally {
            variableSource.removeScope(context.getScope());
        }
    }
    
    @Override
    public void createSubtree(final IContext context, final IModelObjectFactory modelObjectFactory, final IChangeSet set) {
        if (context instanceof StatefulContext) {
            ((StatefulContext)context).set(this.\u00c4, Collections.emptyList());
        }
    }
    
    @Override
    public boolean isAbsolute(final IContext context) {
        return context == null || !context.getScope().isDefined(this.\u00c4);
    }
    
    @Override
    public boolean requiresOrdinalContext() {
        return true;
    }
    
    @Override
    public void bind(final IContext context) {
        final IVariableSource variableSource = this.getVariableSource();
        try {
            variableSource.addScope(context.getScope());
            IVariableScope variableScope = variableSource.getVariableScope(this.\u00c4);
            if (variableScope == null) {
                variableScope = context.getScope();
            }
            variableScope.addListener(this.\u00c4, context, this.\u00c5);
            final Object value = variableScope.get(this.\u00c4, context);
            if (value instanceof List) {
                this.D(context, (List<IModelObject>)value);
            }
        }
        catch (ExpressionException ex) {
            this.handleException(this, context, ex);
            return;
        }
        finally {
            variableSource.removeScope(context.getScope());
        }
        variableSource.removeScope(context.getScope());
    }
    
    @Override
    public void unbind(final IContext context) {
        final IVariableSource variableSource = this.getVariableSource();
        try {
            variableSource.addScope(context.getScope());
            final IVariableScope variableScope = variableSource.getVariableScope(this.\u00c4);
            if (variableScope != null) {
                variableScope.removeListener(this.\u00c4, context, this.\u00c5);
                final Object value = variableScope.get(this.\u00c4, context);
                if (value instanceof List) {
                    this.C(context, (List<IModelObject>)value);
                }
            }
        }
        catch (ExpressionException ex) {
            this.handleException(this, context, ex);
            return;
        }
        finally {
            variableSource.removeScope(context.getScope());
        }
        variableSource.removeScope(context.getScope());
    }
    
    @Override
    protected IExpression cloneOne() {
        return new E(this.\u00c4);
    }
    
    @Override
    public String toString() {
        return "$" + this.\u00c4;
    }
    
    private void D(final IContext context, final List<IModelObject> list) {
        if (this.k.requiresValueNotification(this)) {
            final B b = new B(this, context);
            final Iterator<IModelObject> iterator = list.iterator();
            while (iterator.hasNext()) {
                iterator.next().addModelListener(b);
            }
        }
    }
    
    private void C(final IContext context, final List<IModelObject> list) {
        if (this.k.requiresValueNotification(this)) {
            for (final IModelObject modelObject : list) {
                final B a = B.A(modelObject, this, context);
                if (a != null) {
                    modelObject.removeModelListener(a);
                }
            }
        }
    }
}
