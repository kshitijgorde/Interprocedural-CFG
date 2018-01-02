// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import org.xmodel.IModelObject;
import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressionListenerList implements IExpressionListener
{
    Map<IContext, List<IExpressionListener>> F;
    boolean E;
    
    public ExpressionListenerList() {
        this.F = new HashMap<IContext, List<IExpressionListener>>(1);
    }
    
    public void addListener(final IContext context, final IExpressionListener expressionListener) {
        List<IExpressionListener> list = this.F.get(context);
        if (list == null) {
            list = new ArrayList<IExpressionListener>(1);
            this.F.put(context, list);
        }
        if (!list.contains(expressionListener)) {
            list.add(expressionListener);
        }
        if (expressionListener.requiresValueNotification()) {
            this.E = true;
        }
    }
    
    public boolean removeListener(final IContext context, final IExpressionListener expressionListener) {
        final List<IExpressionListener> list = this.F.get(context);
        if (list != null && list.remove(expressionListener)) {
            if (list.size() == 0) {
                this.F.remove(context);
            }
            return true;
        }
        return false;
    }
    
    public Collection<IContext> getContexts() {
        return this.F.keySet();
    }
    
    public List<IExpressionListener> getListeners(final IContext context) {
        return this.F.get(context);
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        final List<IExpressionListener> list2 = this.F.get(context);
        if (list2 == null) {
            return;
        }
        final Object[] array = list2.toArray();
        for (int i = 0; i < array.length; ++i) {
            try {
                ((IExpressionListener)array[i]).notifyAdd(expression, context, list);
            }
            catch (Exception ex) {
                ((IExpressionListener)array[i]).handleException(expression, context, ex);
            }
        }
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        final List<IExpressionListener> list2 = this.F.get(context);
        if (list2 == null) {
            return;
        }
        final Object[] array = list2.toArray();
        for (int i = 0; i < array.length; ++i) {
            try {
                ((IExpressionListener)array[i]).notifyRemove(expression, context, list);
            }
            catch (Exception ex) {
                ((IExpressionListener)array[i]).handleException(expression, context, ex);
            }
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final boolean b) {
        final List<IExpressionListener> list = this.F.get(context);
        if (list == null) {
            return;
        }
        final Object[] array = list.toArray();
        for (int i = 0; i < array.length; ++i) {
            try {
                ((IExpressionListener)array[i]).notifyChange(expression, context, b);
            }
            catch (Exception ex) {
                ((IExpressionListener)array[i]).handleException(expression, context, ex);
            }
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
        final List<IExpressionListener> list = this.F.get(context);
        if (list == null) {
            return;
        }
        final Object[] array = list.toArray();
        for (int i = 0; i < array.length; ++i) {
            try {
                ((IExpressionListener)array[i]).notifyChange(expression, context, n, n2);
            }
            catch (Exception ex) {
                ((IExpressionListener)array[i]).handleException(expression, context, ex);
            }
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        final List<IExpressionListener> list = this.F.get(context);
        if (list == null) {
            return;
        }
        final Object[] array = list.toArray();
        for (int i = 0; i < array.length; ++i) {
            try {
                ((IExpressionListener)array[i]).notifyChange(expression, context, s, s2);
            }
            catch (Exception ex) {
                ((IExpressionListener)array[i]).handleException(expression, context, ex);
            }
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context) {
        final List<IExpressionListener> list = this.F.get(context);
        if (list == null) {
            return;
        }
        final Object[] array = list.toArray();
        for (int i = 0; i < array.length; ++i) {
            try {
                ((IExpressionListener)array[i]).notifyChange(expression, context);
            }
            catch (Exception ex) {
                ((IExpressionListener)array[i]).handleException(expression, context, ex);
            }
        }
    }
    
    @Override
    public boolean requiresValueNotification() {
        return this.E;
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
        for (final IContext context : array) {
            final List<IExpressionListener> list = this.F.get(context);
            if (list != null) {
                final IContext[] array2 = { null };
                final Object[] array3 = list.toArray();
                for (int j = 0; j < array3.length; ++j) {
                    try {
                        array2[0] = context;
                        ((IExpressionListener)array3[j]).notifyValue(expression, array2, modelObject, o, o2);
                    }
                    catch (Exception ex) {
                        ((IExpressionListener)array3[j]).handleException(expression, context, ex);
                    }
                }
            }
        }
    }
    
    @Override
    public void handleException(final IExpression expression, final IContext context, final Exception ex) {
        final List<IExpressionListener> list = this.F.get(context);
        if (list == null) {
            return;
        }
        final Object[] array = list.toArray();
        for (int i = 0; i < array.length; ++i) {
            try {
                ((IExpressionListener)array[i]).handleException(expression, context, ex);
            }
            catch (Exception ex2) {
                ex2.printStackTrace(System.err);
            }
        }
    }
}
