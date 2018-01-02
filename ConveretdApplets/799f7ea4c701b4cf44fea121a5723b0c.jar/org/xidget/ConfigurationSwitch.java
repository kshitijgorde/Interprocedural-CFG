// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget;

import org.xmodel.xpath.expression.IExpressionListener;
import java.util.Iterator;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.StatefulContext;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import org.xmodel.xpath.expression.IExpression;
import java.util.Map;
import org.xmodel.xpath.expression.ExpressionListener;

public class ConfigurationSwitch<T> extends ExpressionListener
{
    private IListener<T> listener;
    private T defaultHandler;
    private Map<IExpression, T> cases;
    private boolean ignore;
    
    public ConfigurationSwitch(final IListener<T> listener) {
        this.listener = listener;
        this.cases = new HashMap<IExpression, T>();
        this.ignore = false;
    }
    
    public void setDefaultHandler(final T defaultHandler) {
        this.defaultHandler = defaultHandler;
    }
    
    public void addCase(final IExpression expression, final T t) {
        if (expression.getType() != IExpression.ResultType.BOOLEAN) {
            throw new IllegalArgumentException("Expression is not boolean: " + expression);
        }
        this.cases.put(expression, t);
    }
    
    public void removeCase(final IExpression expression) {
        this.cases.remove(expression);
    }
    
    public int getCaseCount() {
        return this.cases.size();
    }
    
    public List<T> getHandlers() {
        if (this.defaultHandler != null) {
            final ArrayList<T> list = new ArrayList<T>((Collection<? extends T>)this.cases.values());
            list.add(0, this.defaultHandler);
            return list;
        }
        return new ArrayList<T>((Collection<? extends T>)this.cases.values());
    }
    
    public void bind(final StatefulContext statefulContext) {
        try {
            final IExpression matchingCase = this.findMatchingCase(statefulContext);
            if (matchingCase == null) {
                this.bindAllCases(statefulContext);
                if (this.defaultHandler != null) {
                    this.listener.notifyMatch(statefulContext, this.defaultHandler);
                }
            }
            else {
                this.listener.notifyMatch(statefulContext, this.cases.get(matchingCase));
                this.bindCase(statefulContext, matchingCase);
            }
        }
        catch (ExpressionException ex) {
            Log.printf("handler", "Failed to evaluate condition for ConditionalSwitch: %s\n", ex);
        }
    }
    
    public void unbind(final StatefulContext statefulContext) {
        try {
            final IExpression matchingCase = this.findMatchingCase(statefulContext);
            if (matchingCase == null) {
                this.unbindAllCases(statefulContext);
                if (this.defaultHandler != null) {
                    this.listener.notifyMismatch(statefulContext, this.defaultHandler);
                }
            }
            else {
                this.listener.notifyMismatch(statefulContext, this.cases.get(matchingCase));
                this.unbindCase(statefulContext, matchingCase);
            }
        }
        catch (ExpressionException ex) {
            Log.printf("handler", "Failed to evaluate condition for ConditionalSwitch: %s\n", ex);
        }
    }
    
    public T getSelectedCase(final IContext context) {
        try {
            final IExpression matchingCase = this.findMatchingCase(context);
            if (matchingCase != null) {
                return this.cases.get(matchingCase);
            }
        }
        catch (ExpressionException ex) {
            Log.printf("handler", "Failed to evaluate condition for ConditionalSwitch: %s\n", ex);
        }
        return null;
    }
    
    private IExpression findMatchingCase(final IContext context) throws ExpressionException {
        for (final IExpression expression : this.cases.keySet()) {
            if (expression.evaluateBoolean(context)) {
                return expression;
            }
        }
        return null;
    }
    
    private void bindAllCases(final IContext context) {
        final Iterator<IExpression> iterator = this.cases.keySet().iterator();
        while (iterator.hasNext()) {
            this.bindCase(context, iterator.next());
        }
    }
    
    private void unbindAllCases(final IContext context) {
        final Iterator<IExpression> iterator = this.cases.keySet().iterator();
        while (iterator.hasNext()) {
            this.unbindCase(context, iterator.next());
        }
    }
    
    private void bindCase(final IContext context, final IExpression expression) {
        expression.addListener(context, this);
    }
    
    private void unbindCase(final IContext context, final IExpression expression) {
        expression.removeListener(context, this);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final boolean b) {
        if (this.ignore) {
            return;
        }
        if (b) {
            for (final IExpression expression2 : this.cases.keySet()) {
                if (expression2 != expression) {
                    this.unbindCase(context, expression2);
                }
            }
            if (this.defaultHandler != null) {
                this.ignore = true;
                this.listener.notifyMismatch((StatefulContext)context, this.defaultHandler);
                this.ignore = false;
            }
            final T value = this.cases.get(expression);
            if (value != null) {
                this.ignore = true;
                this.listener.notifyMatch((StatefulContext)context, value);
                this.ignore = false;
            }
        }
        else {
            final T value2 = this.cases.get(expression);
            if (value2 != null) {
                this.ignore = true;
                this.listener.notifyMismatch((StatefulContext)context, value2);
                this.ignore = false;
            }
            try {
                final IExpression matchingCase = this.findMatchingCase(context);
                if (matchingCase == null) {
                    for (final IExpression expression3 : this.cases.keySet()) {
                        if (expression3 != expression) {
                            this.bindCase(context, expression3);
                        }
                    }
                    if (this.defaultHandler != null) {
                        this.ignore = true;
                        this.listener.notifyMatch((StatefulContext)context, this.defaultHandler);
                        this.ignore = false;
                    }
                }
                else {
                    this.bindCase(context, matchingCase);
                    this.ignore = true;
                    final T value3 = this.cases.get(matchingCase);
                    if (value3 != null) {
                        this.listener.notifyMatch((StatefulContext)context, value3);
                    }
                    this.ignore = false;
                }
            }
            catch (ExpressionException ex) {
                Log.printf("handler", "Unable to evaluate conditional model expression: %s\n", ex);
            }
        }
    }
    
    public interface IListener<T>
    {
        void notifyMatch(final StatefulContext p0, final T p1);
        
        void notifyMismatch(final StatefulContext p0, final T p1);
    }
}
