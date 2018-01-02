// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.feature.model;

import org.xmodel.xpath.expression.IContext;
import java.util.Collections;
import java.util.List;
import org.xmodel.xpath.expression.StatefulContext;
import org.xidget.ifeature.IBindFeature;
import org.xmodel.xpath.expression.IExpression;
import org.xidget.IXidget;
import org.xidget.ifeature.model.IMultiValueModelFeature;

public class MultiValueModelFeature implements IMultiValueModelFeature
{
    protected IXidget xidget;
    private IExpression expression;
    private String variable;
    
    public MultiValueModelFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public void setSourceExpression(final IExpression expression) {
        this.expression = expression;
    }
    
    @Override
    public void setSourceVariable(final String variable) {
        this.variable = variable;
    }
    
    @Override
    public void insertValue(final int n, final Object o) {
        if (this.variable == null) {
            return;
        }
        final StatefulContext boundContext = this.xidget.getFeature(IBindFeature.class).getBoundContext();
        if (boundContext != null) {
            boundContext.getScope().insert(this.variable, o, n);
        }
    }
    
    @Override
    public void updateValue(final int n, final Object o) {
        if (this.variable == null) {
            return;
        }
        this.removeValue(n);
        this.insertValue(n, o);
    }
    
    @Override
    public void removeValue(final int n) {
        if (this.variable == null) {
            return;
        }
        final StatefulContext boundContext = this.xidget.getFeature(IBindFeature.class).getBoundContext();
        if (boundContext != null) {
            boundContext.getScope().remove(this.variable, n);
        }
    }
    
    @Override
    public void setValues(final List<?> list) {
        if (this.variable == null) {
            return;
        }
        final StatefulContext boundContext = this.xidget.getFeature(IBindFeature.class).getBoundContext();
        if (boundContext != null) {
            boundContext.getScope().set(this.variable, list);
        }
    }
    
    @Override
    public List<?> getValues() {
        final StatefulContext boundContext = this.xidget.getFeature(IBindFeature.class).getBoundContext();
        if (boundContext == null) {
            return Collections.emptyList();
        }
        if (this.expression != null) {
            return this.expression.evaluateNodes(boundContext);
        }
        if (this.variable == null) {
            return Collections.emptyList();
        }
        final Object value = boundContext.getScope().get(this.variable);
        if (value instanceof List) {
            return (List<?>)value;
        }
        return Collections.singletonList(value);
    }
}
