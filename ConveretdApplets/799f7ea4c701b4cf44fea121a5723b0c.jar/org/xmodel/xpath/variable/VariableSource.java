// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.variable;

import java.util.Collection;
import java.util.HashSet;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import java.util.Iterator;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class VariableSource implements IVariableSource
{
    IVariableSource A;
    List<IVariableScope> B;
    
    public VariableSource() {
        this.B = new ArrayList<IVariableScope>(1);
    }
    
    @Override
    public void setParent(final IVariableSource a) {
        this.A = a;
    }
    
    @Override
    public IVariableSource getParent() {
        return this.A;
    }
    
    @Override
    public void addScope(final IVariableScope variableScope) {
        if (variableScope == null) {
            return;
        }
        for (int i = 0; i < this.B.size(); ++i) {
            final IVariableScope variableScope2 = this.B.get(i);
            if (variableScope2 == variableScope) {
                return;
            }
            if (variableScope2.getPrecedence() > variableScope.getPrecedence()) {
                variableScope.internal_setSource(this);
                this.B.add(i, variableScope);
                return;
            }
        }
        variableScope.internal_setSource(this);
        this.B.add(variableScope);
    }
    
    @Override
    public void removeScope(final IVariableScope variableScope) {
        if (variableScope == null) {
            return;
        }
        for (int i = this.B.size() - 1; i >= 0; --i) {
            if (this.B.get(i) == variableScope) {
                variableScope.internal_setSource(null);
                this.B.remove(i);
                return;
            }
        }
    }
    
    @Override
    public List<IVariableScope> getScopes() {
        return Collections.unmodifiableList((List<? extends IVariableScope>)this.B);
    }
    
    @Override
    public IVariableScope getScope(final String s) {
        for (final IVariableScope variableScope : this.B) {
            if (variableScope.getName().equals(s)) {
                return variableScope;
            }
        }
        return null;
    }
    
    @Override
    public IVariableScope getVariableScope(final String s) {
        for (int i = this.B.size() - 1; i >= 0; --i) {
            final IVariableScope variableScope = this.B.get(i);
            if (variableScope.isDefined(s)) {
                return variableScope;
            }
        }
        if (this.A != null) {
            return this.A.getVariableScope(s);
        }
        return null;
    }
    
    @Override
    public IExpression.ResultType getVariableType(final String s) {
        for (int i = this.B.size() - 1; i >= 0; --i) {
            final IVariableScope variableScope = this.B.get(i);
            if (variableScope.isDefined(s)) {
                return variableScope.getType(s);
            }
        }
        if (this.A != null) {
            return this.A.getVariableType(s);
        }
        return IExpression.ResultType.UNDEFINED;
    }
    
    @Override
    public IExpression.ResultType getVariableType(final String s, final IContext context) {
        for (int i = this.B.size() - 1; i >= 0; --i) {
            final IVariableScope variableScope = this.B.get(i);
            if (variableScope.isDefined(s)) {
                return variableScope.getType(s, context);
            }
        }
        if (this.A != null) {
            return this.A.getVariableType(s, context);
        }
        return IExpression.ResultType.UNDEFINED;
    }
    
    @Override
    public Object getVariable(final String s, final IContext context) throws ExpressionException {
        final Object value = this.getVariableScope(s).get(s, context);
        if (value != null) {
            return value;
        }
        if (this.A != null) {
            return this.A.getVariable(s, context);
        }
        return null;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        final HashSet<String> set = new HashSet<String>();
        final Iterator<IVariableScope> iterator = this.getScopes().iterator();
        while (iterator.hasNext()) {
            set.addAll((Collection<?>)iterator.next().getAll());
        }
        final ArrayList list = new ArrayList<String>(set);
        Collections.sort((List<Comparable>)list);
        for (final String s : list) {
            sb.append(s);
            sb.append("=");
            sb.append(this.getVariableScope(s).get(s));
        }
        return sb.toString();
    }
}
