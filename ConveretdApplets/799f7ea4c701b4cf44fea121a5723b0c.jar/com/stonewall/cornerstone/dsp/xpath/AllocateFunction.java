// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.xpath;

import org.xmodel.xpath.expression.ExpressionException;
import java.util.Iterator;
import java.util.Set;
import java.util.List;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import java.util.TreeSet;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class AllocateFunction extends Function
{
    public static final String name = "dsp:allocate";
    
    @Override
    public String getName() {
        return "dsp:allocate";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.STRING;
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.assertArgs(1, 2);
        this.assertType(context, 0, IExpression.ResultType.NODES);
        final int arguments = this.getArguments().size();
        if (arguments == 2) {
            this.assertType(context, 1, IExpression.ResultType.NUMBER);
        }
        final IExpression defaultExpression = (arguments == 2) ? this.getArguments().get(1) : null;
        String defaultValue = "1";
        if (defaultExpression != null) {
            defaultValue = defaultExpression.evaluateString(context);
        }
        final List<IModelObject> nodeSets = this.getArguments().get(0).evaluateNodes(context);
        if (nodeSets.isEmpty()) {
            return defaultValue;
        }
        final Set<Integer> numbers = new TreeSet<Integer>();
        for (final IModelObject theNode : nodeSets) {
            numbers.add(Xlate.get(theNode, 0));
        }
        int current = new Integer(defaultValue);
        for (final int n : numbers) {
            if (n != current) {
                break;
            }
            current = n + 1;
        }
        return String.valueOf(current);
    }
    
    @Override
    public void bind(final IContext context) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void unbind(final IContext context) {
        throw new UnsupportedOperationException();
    }
}
