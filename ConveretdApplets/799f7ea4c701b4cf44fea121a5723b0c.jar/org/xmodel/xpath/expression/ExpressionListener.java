// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import java.util.Iterator;
import org.xmodel.IModel;
import java.util.ArrayList;
import org.xmodel.diff.ListDiffer;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.log.Log;

public class ExpressionListener implements IExpressionListener
{
    private static Log J;
    private static /* synthetic */ int[] I;
    
    static {
        ExpressionListener.J = Log.getLog("org.xmodel.xpath.expression");
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final boolean b) {
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context) {
        try {
            final IModel model = context.getModel();
            switch (C()[expression.getType(context).ordinal()]) {
                case 1: {
                    model.revert();
                    final List<IModelObject> evaluateNodes = expression.evaluateNodes(context);
                    model.restore();
                    final List<IModelObject> evaluateNodes2 = expression.evaluateNodes(context);
                    List<IModelObject> list = null;
                    List<IModelObject> list2 = null;
                    final ListDiffer listDiffer = new ListDiffer();
                    listDiffer.diff(evaluateNodes, evaluateNodes2);
                    for (final ListDiffer.Change change : listDiffer.getChanges()) {
                        if (change.rIndex >= 0) {
                            if (list == null) {
                                list = new ArrayList<IModelObject>();
                            }
                            for (int i = 0; i < change.count; ++i) {
                                list.add(evaluateNodes2.get(change.rIndex + i));
                            }
                        }
                        else {
                            if (list2 == null) {
                                list2 = new ArrayList<IModelObject>();
                            }
                            for (int j = 0; j < change.count; ++j) {
                                list2.add(evaluateNodes.get(change.lIndex + j));
                            }
                        }
                    }
                    if (list2 != null) {
                        this.notifyRemove(expression, context, list2);
                    }
                    if (list != null) {
                        this.notifyAdd(expression, context, list);
                        break;
                    }
                    break;
                }
                case 3: {
                    model.revert();
                    final double evaluateNumber = expression.evaluateNumber(context);
                    model.restore();
                    final double evaluateNumber2 = expression.evaluateNumber(context);
                    if (evaluateNumber2 != evaluateNumber) {
                        this.notifyChange(expression, context, evaluateNumber2, evaluateNumber);
                        break;
                    }
                    break;
                }
                case 4: {
                    model.revert();
                    final boolean evaluateBoolean = expression.evaluateBoolean(context);
                    model.restore();
                    final boolean evaluateBoolean2 = expression.evaluateBoolean(context);
                    if (evaluateBoolean2 != evaluateBoolean) {
                        this.notifyChange(expression, context, evaluateBoolean2);
                        break;
                    }
                    break;
                }
                case 2: {
                    model.revert();
                    final String evaluateString = expression.evaluateString(context);
                    model.restore();
                    final String evaluateString2 = expression.evaluateString(context);
                    if (!evaluateString2.equals(evaluateString)) {
                        this.notifyChange(expression, context, evaluateString2, evaluateString);
                        break;
                    }
                    break;
                }
            }
        }
        catch (ExpressionException ex) {
            this.handleException(expression, context, ex);
        }
    }
    
    @Override
    public boolean requiresValueNotification() {
        return true;
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
        for (int length = array.length, i = 0; i < length; ++i) {
            this.notifyChange(expression, array[i], (o != null) ? o.toString() : "", (o2 != null) ? o2.toString() : "");
        }
    }
    
    @Override
    public void handleException(final IExpression expression, final IContext context, final Exception ex) {
        System.err.println("Expression Error: " + expression + ", " + context);
        ExpressionListener.J.exception(ex);
    }
    
    static /* synthetic */ int[] C() {
        final int[] i = ExpressionListener.I;
        if (i != null) {
            return i;
        }
        final int[] j = new int[IExpression.ResultType.values().length];
        try {
            j[IExpression.ResultType.BOOLEAN.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            j[IExpression.ResultType.NODES.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        try {
            j[IExpression.ResultType.NUMBER.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError3) {}
        try {
            j[IExpression.ResultType.STRING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError4) {}
        try {
            j[IExpression.ResultType.UNDEFINED.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError5) {}
        return ExpressionListener.I = j;
    }
}
