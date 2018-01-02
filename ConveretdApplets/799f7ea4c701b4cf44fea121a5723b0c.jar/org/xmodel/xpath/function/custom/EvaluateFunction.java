// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function.custom;

import org.xmodel.xpath.parser.generated.TokenMgrError;
import org.xmodel.xpath.XPath;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import org.xmodel.xpath.expression.ExpressionException;
import java.util.Iterator;
import org.xmodel.xpath.expression.Context;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class EvaluateFunction extends Function
{
    public static final String name = "evaluate";
    
    @Override
    public String getName() {
        return "evaluate";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.UNDEFINED;
    }
    
    @Override
    public IExpression.ResultType getType(final IContext context) {
        return this.getArgument(2).getType(context);
    }
    
    @Override
    public boolean evaluateBoolean(final IContext context) throws ExpressionException {
        this.assertArgs(3, 3);
        this.assertType(context, 0, IExpression.ResultType.NODES);
        final IExpression a = this.A(this.getArgument(1).evaluateString(context));
        if (a == null) {
            return this.getArgument(2).evaluateBoolean(context);
        }
        final Iterator<IModelObject> iterator = this.getArgument(0).evaluateNodes(context).iterator();
        while (iterator.hasNext()) {
            if (!a.evaluateBoolean(new Context(context, iterator.next()))) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        this.assertArgs(3, 3);
        this.assertType(context, 0, IExpression.ResultType.NODES);
        final IExpression a = this.A(this.getArgument(1).evaluateString(context));
        if (a == null) {
            return this.getArgument(2).evaluateNodes(context);
        }
        final List<IModelObject> evaluateNodes = this.getArgument(0).evaluateNodes(context);
        final ArrayList<Object> list = new ArrayList<Object>();
        final Iterator<IModelObject> iterator = evaluateNodes.iterator();
        while (iterator.hasNext()) {
            list.addAll(a.evaluateNodes(new Context(context, iterator.next())));
        }
        return (List<IModelObject>)list;
    }
    
    @Override
    public double evaluateNumber(final IContext context) throws ExpressionException {
        this.assertArgs(3, 3);
        this.assertType(context, 0, IExpression.ResultType.NODES);
        final IExpression a = this.A(this.getArgument(1).evaluateString(context));
        if (a == null) {
            return this.getArgument(2).evaluateNumber(context);
        }
        return a.evaluateNumber(new Context(context, this.getArgument(0).queryFirst(context)));
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.assertArgs(3, 3);
        this.assertType(context, 0, IExpression.ResultType.NODES);
        final IExpression a = this.A(this.getArgument(1).evaluateString(context));
        if (a == null) {
            return this.getArgument(2).evaluateString(context);
        }
        return a.evaluateString(new Context(context, this.getArgument(0).queryFirst(context)));
    }
    
    private IExpression A(final String s) {
        try {
            return XPath.createExpression(s, false);
        }
        catch (Exception ex) {
            return null;
        }
        catch (TokenMgrError tokenMgrError) {
            return null;
        }
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        this.getParent().notifyChange(this, context);
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        this.getParent().notifyChange(this, context);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        this.getParent().notifyChange(this, context);
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
        this.getParent().notifyChange(this, array[0]);
    }
    
    @Override
    public boolean requiresValueNotification(final IExpression expression) {
        return expression == this.getArgument(1);
    }
}
