// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import java.util.Iterator;
import org.xmodel.IModelObject;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class ExpressionListenerList2 implements IExpressionListener
{
    List<_A> G;
    boolean H;
    
    public ExpressionListenerList2() {
        this.G = new ArrayList<_A>(1);
    }
    
    public void addListener(final IContext b, final IExpressionListener expressionListener) {
        final List<IExpressionListener> listeners = this.findListeners(b);
        if (listeners == null) {
            final _A a = new _A();
            a.B = b;
            a.C.add(expressionListener);
            if (expressionListener.requiresValueNotification()) {
                this.H = true;
            }
        }
        else if (!listeners.contains(expressionListener)) {
            listeners.add(expressionListener);
            if (expressionListener.requiresValueNotification()) {
                this.H = true;
            }
        }
    }
    
    public boolean removeListener(final IContext context, final IExpressionListener expressionListener) {
        final List<IExpressionListener> listeners = this.findListeners(context);
        return listeners != null && listeners.remove(expressionListener);
    }
    
    public Collection<IContext> getContexts() {
        return this.findContexts();
    }
    
    public List<IExpressionListener> getListeners(final IContext context) {
        return this.findListeners(context);
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        final List<IExpressionListener> listeners = this.findListeners(context);
        if (listeners == null) {
            return;
        }
        final Object[] array = listeners.toArray();
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
        final List<IExpressionListener> listeners = this.findListeners(context);
        if (listeners == null) {
            return;
        }
        final Object[] array = listeners.toArray();
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
        final List<IExpressionListener> listeners = this.findListeners(context);
        if (listeners == null) {
            return;
        }
        final Object[] array = listeners.toArray();
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
        final List<IExpressionListener> listeners = this.findListeners(context);
        if (listeners == null) {
            return;
        }
        final Object[] array = listeners.toArray();
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
        final List<IExpressionListener> listeners = this.findListeners(context);
        if (listeners == null) {
            return;
        }
        final Object[] array = listeners.toArray();
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
        final List<IExpressionListener> listeners = this.findListeners(context);
        if (listeners == null) {
            return;
        }
        final Object[] array = listeners.toArray();
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
        return this.H;
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
        for (final IContext context : array) {
            final List<IExpressionListener> listeners = this.findListeners(context);
            if (listeners != null) {
                final IContext[] array2 = { null };
                final Object[] array3 = listeners.toArray();
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
        final List<IExpressionListener> listeners = this.findListeners(context);
        if (listeners == null) {
            return;
        }
        final Object[] array = listeners.toArray();
        for (int i = 0; i < array.length; ++i) {
            try {
                ((IExpressionListener)array[i]).handleException(expression, context, ex);
            }
            catch (Exception ex2) {
                ex2.printStackTrace(System.err);
            }
        }
    }
    
    protected Collection<IContext> findContexts() {
        final ArrayList<IContext> list = new ArrayList<IContext>();
        final Iterator<_A> iterator = this.G.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().B);
        }
        return list;
    }
    
    protected List<IExpressionListener> findListeners(final IContext context) {
        for (final _A a : this.G) {
            if (a.B.equals(context)) {
                return a.C;
            }
        }
        return null;
    }
    
    class _A
    {
        IContext B;
        List<IExpressionListener> C;
        
        _A() {
            this.C = new ArrayList<IExpressionListener>(1);
        }
    }
}
