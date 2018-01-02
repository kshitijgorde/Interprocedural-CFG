// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import org.xmodel.IModel;
import java.util.ArrayList;
import org.xmodel.xpath.variable.IVariableSource;
import org.xmodel.IChangeSet;
import org.xmodel.IModelObjectFactory;
import java.util.Iterator;
import org.xmodel.IModelObject;
import org.xmodel.log.Log;
import java.util.List;
import org.xmodel.IPath;
import org.xmodel.G;

public class J extends L implements G
{
    IPath \u00d1;
    boolean \u00cc;
    boolean \u00d0;
    List<IExpressionListener> \u00cf;
    private static Log \u00ce;
    private static /* synthetic */ int[] \u00cd;
    
    static {
        J.\u00ce = Log.getLog("org.xmodel.xpath.expression");
    }
    
    public J(final IPath path) {
        this.A(path);
    }
    
    @Override
    public void A(final IPath \u00f1) {
        this.\u00d1 = \u00f1;
    }
    
    @Override
    public boolean A(final IContext context, final IPath path, final IModelObject modelObject) {
        if (this.requiresOrdinalContext()) {
            final List<IModelObject> query = path.query(context, null);
            return this.evaluateBoolean(new SubContext(context, modelObject, query.indexOf(modelObject) + 1, query.size()), false);
        }
        return this.evaluateBoolean(new SubContext(context, modelObject, 0, 0), false);
    }
    
    @Override
    public String getName() {
        return "predicate";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.BOOLEAN;
    }
    
    @Override
    public boolean requiresOrdinalContext() {
        if (!this.\u00cc) {
            if (this.l != null) {
                for (final IExpression expression : this.l) {
                    if (expression.requiresOrdinalContext() || expression.getType() == IExpression.ResultType.NUMBER) {
                        this.\u00d0 = true;
                        break;
                    }
                }
            }
            else {
                this.\u00d0 = false;
            }
        }
        return this.\u00d0;
    }
    
    @Override
    public boolean evaluateBoolean(final IContext context) throws ExpressionException {
        for (final IExpression expression : this.l) {
            if (expression.getType(context) == IExpression.ResultType.NUMBER) {
                if ((int)expression.evaluateNumber(context) != context.getPosition()) {
                    return false;
                }
                continue;
            }
            else {
                if (!expression.evaluateBoolean(context)) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    @Override
    public void createSubtree(final IContext context, final IModelObjectFactory modelObjectFactory, final IChangeSet set) {
        final Iterator<IExpression> iterator = this.getArguments().iterator();
        while (iterator.hasNext()) {
            iterator.next().createSubtree(context, modelObjectFactory, set);
        }
    }
    
    @Override
    public IVariableSource getVariableSource() {
        if (this.\u00d1 != null) {
            return this.\u00d1.getVariableSource();
        }
        if (this.k != null) {
            return this.k.getVariableSource();
        }
        return null;
    }
    
    @Override
    public void A(final IExpressionListener expressionListener) {
        if (this.\u00cf == null) {
            this.\u00cf = new ArrayList<IExpressionListener>(1);
        }
        this.\u00cf.add(expressionListener);
    }
    
    @Override
    public void B(final IExpressionListener expressionListener) {
        if (this.\u00cf != null) {
            this.\u00cf.remove(expressionListener);
        }
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        try {
            context.getModel().revert();
            final List<IModelObject> evaluateNodes = expression.evaluateNodes(context);
            context.getModel().restore();
            if (evaluateNodes.size() == 0) {
                this.notifyChange(expression, context, true);
            }
        }
        catch (ExpressionException ex) {
            this.handleException(expression, context, ex);
        }
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        try {
            if (expression.evaluateNodes(context).size() == 0) {
                this.notifyChange(expression, context, false);
            }
        }
        catch (ExpressionException ex) {
            this.handleException(expression, context, ex);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final boolean b) {
        if (this.k != null) {
            this.k.notifyChange(this, context, b);
        }
        if (this.\u00cf != null) {
            IExpressionListener[] array;
            for (int length = (array = this.\u00cf.toArray(new IExpressionListener[0])).length, i = 0; i < length; ++i) {
                array[i].notifyChange(this, context, b);
            }
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
        if (this.k != null) {
            this.k.notifyChange(this, context, n, n2);
        }
        if (this.\u00cf != null) {
            IExpressionListener[] array;
            for (int length = (array = this.\u00cf.toArray(new IExpressionListener[0])).length, i = 0; i < length; ++i) {
                array[i].notifyChange(this, context, n, n2);
            }
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        if (s.length() == 0 && s2.length() > 0) {
            this.notifyChange(expression, context, false);
        }
        else if (s.length() > 0 && s2.length() == 0) {
            this.notifyChange(expression, context, true);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context) {
        try {
            final IModel model = context.getModel();
            switch (I()[expression.getType(context).ordinal()]) {
                case 1: {
                    model.revert();
                    final List<IModelObject> evaluateNodes = expression.evaluateNodes(context);
                    model.restore();
                    final List<IModelObject> evaluateNodes2 = expression.evaluateNodes(context);
                    if (evaluateNodes.size() == 0 && evaluateNodes2.size() > 0) {
                        this.notifyChange(this, context, true);
                    }
                    if (evaluateNodes.size() > 0 && evaluateNodes2.size() == 0) {
                        this.notifyChange(this, context, false);
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
                        this.notifyChange(this, context, evaluateNumber2, evaluateNumber);
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
                        this.notifyChange(this, context, evaluateBoolean2);
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
                        this.notifyChange(this, context, evaluateString2, evaluateString);
                        break;
                    }
                    break;
                }
            }
        }
        catch (ExpressionException ex) {
            this.handleException(this, context, ex);
        }
    }
    
    @Override
    public boolean requiresValueNotification(final IExpression expression) {
        return false;
    }
    
    @Override
    public void handleException(final IExpression expression, final IContext context, final Exception ex) {
        if (this.\u00cf == null) {
            System.err.println("Expression Error: " + expression + ", " + context);
            J.\u00ce.exception(ex);
            return;
        }
        final Iterator<IExpressionListener> iterator = this.\u00cf.iterator();
        while (iterator.hasNext()) {
            iterator.next().handleException(this, context, ex);
        }
    }
    
    public G L() {
        return (J)super.clone();
    }
    
    @Override
    protected IExpression cloneOne() {
        return new J(this.\u00d1);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (final IExpression expression : this.getArguments()) {
            sb.append('[');
            sb.append(expression.toString());
            sb.append(']');
        }
        return sb.toString();
    }
    
    static /* synthetic */ int[] I() {
        final int[] \u00ed = J.\u00cd;
        if (\u00ed != null) {
            return \u00ed;
        }
        final int[] \u00ed2 = new int[IExpression.ResultType.values().length];
        try {
            \u00ed2[IExpression.ResultType.BOOLEAN.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            \u00ed2[IExpression.ResultType.NODES.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        try {
            \u00ed2[IExpression.ResultType.NUMBER.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError3) {}
        try {
            \u00ed2[IExpression.ResultType.STRING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError4) {}
        try {
            \u00ed2[IExpression.ResultType.UNDEFINED.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError5) {}
        return J.\u00cd = \u00ed2;
    }
}
