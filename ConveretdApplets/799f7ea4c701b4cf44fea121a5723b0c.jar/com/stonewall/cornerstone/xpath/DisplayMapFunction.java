// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.xpath;

import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.Context;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.XPath;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class DisplayMapFunction extends Function
{
    public static final String name = "cm:displayMap";
    private IExpression entryExpr;
    
    public DisplayMapFunction() {
        this.entryExpr = XPath.createExpression("map[@model=$model]/@display");
    }
    
    @Override
    public String getName() {
        return "cm:displayMap";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.STRING;
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.assertArgs(2, 2);
        this.assertType(context, 0, IExpression.ResultType.NODES);
        this.assertType(context, 1, IExpression.ResultType.STRING);
        final IModelObject displayMap = this.getArguments().get(0).queryFirst(context);
        final String model = this.getArguments().get(1).evaluateString(context);
        this.entryExpr.setVariable("model", model);
        return this.entryExpr.evaluateString(new Context(displayMap), "");
    }
}
