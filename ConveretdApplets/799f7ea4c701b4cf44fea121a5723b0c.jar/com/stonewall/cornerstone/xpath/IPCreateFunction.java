// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.xpath;

import com.stonewall.cornerstone.entity.util.IpAddr;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.function.StringFunction;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class IPCreateFunction extends Function
{
    public static final String name = "cm:ip-create";
    
    @Override
    public String getName() {
        return "cm:ip-create";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.STRING;
    }
    
    @Override
    public void addArgument(IExpression argument) {
        if (argument.getType().equals(IExpression.ResultType.NODES)) {
            argument = new StringFunction(argument);
        }
        else if (!argument.getType().equals(IExpression.ResultType.STRING)) {
            try {
                throw new ExpressionException(this, "Error: illegal argument type: " + argument.toString());
            }
            catch (ExpressionException ex) {}
        }
        super.addArgument(argument);
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.assertArgs(2, 2);
        this.assertType(context, IExpression.ResultType.STRING);
        final String ip = this.getArguments().get(0).evaluateString(context);
        final String mask = this.getArguments().get(1).evaluateString(context);
        try {
            final IpAddr ipAddr = new IpAddr(ip, mask);
            return ipAddr.toString();
        }
        catch (Exception e) {
            return "";
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String newValue, final String oldValue) {
        final IExpression parent = this.getParent();
        if (parent == null) {
            return;
        }
        parent.notifyChange(this, context);
    }
}
