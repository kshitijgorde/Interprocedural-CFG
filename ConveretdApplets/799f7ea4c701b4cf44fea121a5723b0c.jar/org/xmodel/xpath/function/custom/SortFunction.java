// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function.custom;

import org.xmodel.Xlate;
import org.xmodel.xpath.expression.SubContext;
import org.xmodel.xpath.expression.ExpressionException;
import java.util.Comparator;
import java.util.Collections;
import org.xmodel.xpath.expression.Context;
import java.util.ArrayList;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class SortFunction extends Function
{
    static final char[] q;
    
    static {
        q = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    }
    
    @Override
    public String getName() {
        return "sort";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.NODES;
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        this.assertArgs(2, Integer.MAX_VALUE);
        this.assertType(context, 0, IExpression.ResultType.NODES);
        final List<IModelObject> evaluateNodes = this.getArgument(0).evaluateNodes(context);
        final ArrayList list = new ArrayList<Object>(evaluateNodes.size());
        final int size = evaluateNodes.size();
        for (int i = 0; i < size; ++i) {
            list.add(new Context(evaluateNodes.get(i), i + 1, size));
        }
        Collections.sort((List<Object>)list, (Comparator<? super Object>)new _A((_A)null));
        for (int j = 0; j < size; ++j) {
            evaluateNodes.set(j, ((Context)list.get(j)).getObject());
        }
        return evaluateNodes;
    }
    
    @Override
    public void bind(final IContext context) {
        final IExpression argument = this.getArgument(0);
        argument.bind(context);
        this.B(context, argument.evaluateNodes(context));
    }
    
    @Override
    public void unbind(final IContext context) {
        final IExpression argument = this.getArgument(0);
        argument.unbind(context);
        this.A(context, argument.evaluateNodes(context));
    }
    
    private void B(final IContext context, final List<IModelObject> list) {
        final List<IExpression> arguments = this.getArguments();
        for (int i = 0; i < list.size(); ++i) {
            final IModelObject modelObject = list.get(i);
            for (int j = 1; j < arguments.size(); ++j) {
                arguments.get(j).bind(new SubContext(context, modelObject, i + 1, list.size()));
            }
        }
    }
    
    private void A(final IContext context, final List<IModelObject> list) {
        final List<IExpression> arguments = this.getArguments();
        for (int i = 0; i < list.size(); ++i) {
            final IModelObject modelObject = list.get(i);
            for (int j = 1; j < arguments.size(); ++j) {
                arguments.get(j).unbind(new SubContext(context, modelObject, i + 1, list.size()));
            }
        }
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        if (expression == this.getArgument(0)) {
            this.B(context, list);
            this.getParent().notifyChange(this, context);
        }
        else {
            this.getParent().notifyChange(this, context.getParent());
        }
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        if (expression == this.getArgument(0)) {
            this.A(context, list);
            this.getParent().notifyChange(this, context);
        }
        else {
            this.getParent().notifyChange(this, context.getParent());
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final boolean b) {
        this.getParent().notifyChange(this, context.getParent());
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
        this.getParent().notifyChange(this, context.getParent());
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        this.getParent().notifyChange(this, context.getParent());
    }
    
    @Override
    public boolean requiresValueNotification(final IExpression expression) {
        return expression != this.getArgument(0);
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
        this.getParent().notifyChange(this, array[0].getParent());
    }
    
    private class _A implements Comparator<IContext>
    {
        private static /* synthetic */ int[] B;
        
        public int A(final IContext context, final IContext context2) {
            try {
                int n = 0;
                final List<IExpression> arguments = SortFunction.this.getArguments();
                for (int i = 1; i < arguments.size(); ++i) {
                    final IExpression expression = arguments.get(i);
                    switch (A()[expression.getType(context).ordinal()]) {
                        case 1: {
                            final List<IModelObject> evaluateNodes = expression.evaluateNodes(context);
                            final List<IModelObject> evaluateNodes2 = expression.evaluateNodes(context2);
                            if (evaluateNodes.size() == 0) {
                                return 1;
                            }
                            if (evaluateNodes2.size() == 0) {
                                return -1;
                            }
                            n = this.A(Xlate.get((IModelObject)evaluateNodes.get(0), ""), Xlate.get((IModelObject)evaluateNodes2.get(0), ""));
                            if (n != 0) {
                                return n;
                            }
                            break;
                        }
                        case 2: {
                            n = this.A(expression.evaluateString(context), expression.evaluateString(context2));
                            if (n != 0) {
                                return n;
                            }
                            break;
                        }
                        case 3: {
                            final double evaluateNumber = expression.evaluateNumber(context);
                            final double evaluateNumber2 = expression.evaluateNumber(context2);
                            if (evaluateNumber < evaluateNumber2) {
                                return -1;
                            }
                            if (evaluateNumber > evaluateNumber2) {
                                return 1;
                            }
                            n = 0;
                            break;
                        }
                        case 4: {
                            final boolean evaluateBoolean = expression.evaluateBoolean(context);
                            final boolean evaluateBoolean2 = expression.evaluateBoolean(context2);
                            if (evaluateBoolean && !evaluateBoolean2) {
                                return -1;
                            }
                            if (evaluateBoolean && evaluateBoolean2) {
                                return 1;
                            }
                            n = 0;
                            break;
                        }
                    }
                }
                return n;
            }
            catch (ExpressionException ex) {
                return 0;
            }
        }
        
        private int A(final String s, final String s2) {
            final int a = this.A(s);
            final int a2 = this.A(s2);
            if (a != a2) {
                return s.compareTo(s2);
            }
            int i = 0;
            while (i < a) {
                final char char1 = s.charAt(i);
                final char char2 = s2.charAt(i);
                if (char1 != char2) {
                    final int numericValue = Character.getNumericValue(char1);
                    final int numericValue2 = Character.getNumericValue(char2);
                    if (numericValue < numericValue2) {
                        return -1;
                    }
                    if (numericValue > numericValue2) {
                        return 1;
                    }
                    return 0;
                }
                else {
                    ++i;
                }
            }
            double doubleValue = 0.0;
            double doubleValue2 = 0.0;
            if (a < s.length()) {
                doubleValue = Double.valueOf(s.substring(a));
            }
            if (a2 < s2.length()) {
                doubleValue2 = Double.valueOf(s2.substring(a2));
            }
            if (doubleValue < doubleValue2) {
                return -1;
            }
            if (doubleValue > doubleValue2) {
                return 1;
            }
            return 0;
        }
        
        private int A(final String s) {
            for (int i = s.length() - 1; i >= 0; --i) {
                char char1;
                int n;
                for (char1 = s.charAt(i), n = 0; n < SortFunction.q.length && char1 != SortFunction.q[n]; ++n) {}
                if (n == SortFunction.q.length) {
                    return i + 1;
                }
            }
            return 0;
        }
        
        static /* synthetic */ int[] A() {
            final int[] b = _A.B;
            if (b != null) {
                return b;
            }
            final int[] b2 = new int[IExpression.ResultType.values().length];
            try {
                b2[IExpression.ResultType.BOOLEAN.ordinal()] = 4;
            }
            catch (NoSuchFieldError noSuchFieldError) {}
            try {
                b2[IExpression.ResultType.NODES.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError2) {}
            try {
                b2[IExpression.ResultType.NUMBER.ordinal()] = 3;
            }
            catch (NoSuchFieldError noSuchFieldError3) {}
            try {
                b2[IExpression.ResultType.STRING.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError4) {}
            try {
                b2[IExpression.ResultType.UNDEFINED.ordinal()] = 5;
            }
            catch (NoSuchFieldError noSuchFieldError5) {}
            return _A.B = b2;
        }
    }
}
