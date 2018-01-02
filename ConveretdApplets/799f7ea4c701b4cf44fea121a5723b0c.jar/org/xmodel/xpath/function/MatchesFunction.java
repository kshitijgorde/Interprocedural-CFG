// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import java.util.List;
import java.util.regex.PatternSyntaxException;
import org.xmodel.xpath.expression.ExpressionException;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class MatchesFunction extends Function
{
    @Override
    public String getName() {
        return "matches";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.BOOLEAN;
    }
    
    @Override
    public boolean evaluateBoolean(final IContext context) throws ExpressionException {
        this.assertArgs(2, 3);
        this.assertType(context, 1, IExpression.ResultType.STRING);
        this.assertType(context, 2, IExpression.ResultType.STRING);
        final Pattern d = this.D(context);
        if (d == null) {
            return false;
        }
        final IExpression argument = this.getArgument(0);
        if (argument.getType(context) == IExpression.ResultType.STRING) {
            return d.matcher(argument.evaluateString(context)).find();
        }
        if (argument.getType(context) == IExpression.ResultType.NODES) {
            final Iterator<IModelObject> iterator = argument.evaluateNodes(context).iterator();
            while (iterator.hasNext()) {
                if (d.matcher(Xlate.get((IModelObject)iterator.next(), "")).find()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private Pattern D(final IContext context) throws ExpressionException {
        try {
            return Pattern.compile(this.getArgument(1).evaluateString(context), this.C(context));
        }
        catch (PatternSyntaxException ex) {
            throw new ExpressionException(this, "Invalid regular expression.", ex);
        }
    }
    
    private int C(final IContext context) throws ExpressionException {
        final IExpression argument = this.getArgument(2);
        if (argument == null) {
            return 0;
        }
        int n = 0;
        final String evaluateString = argument.evaluateString(context);
        if (evaluateString.contains("s")) {
            n |= 0x20;
        }
        if (evaluateString.contains("m")) {
            n |= 0x8;
        }
        if (evaluateString.contains("i")) {
            n |= 0x2;
        }
        if (evaluateString.contains("x")) {
            n |= 0x4;
        }
        return n;
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
    public void notifyChange(final IExpression expression, final IContext context) {
        this.getParent().notifyChange(this, context);
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
        for (int length = array.length, i = 0; i < length; ++i) {
            this.getParent().notifyChange(this, array[i]);
        }
    }
}
