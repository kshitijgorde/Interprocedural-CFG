// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function.custom;

import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class StaticFunction extends Function
{
    @Override
    public String getName() {
        return "static";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return this.getArgument(0).getType();
    }
    
    @Override
    public IExpression.ResultType getType(final IContext context) {
        return this.getArgument(0).getType(context);
    }
    
    @Override
    public boolean evaluateBoolean(final IContext context) throws ExpressionException {
        this.assertArgs(1, 1);
        return this.getArgument(0).evaluateBoolean(context);
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        this.assertArgs(1, 1);
        return this.getArgument(0).evaluateNodes(context);
    }
    
    @Override
    public double evaluateNumber(final IContext context) throws ExpressionException {
        this.assertArgs(1, 1);
        return this.getArgument(0).evaluateNumber(context);
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.assertArgs(1, 1);
        return this.getArgument(0).evaluateString(context);
    }
    
    @Override
    public void bind(final IContext context) {
    }
    
    @Override
    public void unbind(final IContext context) {
    }
    
    @Override
    public boolean isAbsolute(final IContext context) {
        return true;
    }
}
