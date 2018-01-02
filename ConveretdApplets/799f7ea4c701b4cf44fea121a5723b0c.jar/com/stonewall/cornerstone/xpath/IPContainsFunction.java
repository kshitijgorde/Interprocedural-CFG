// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.xpath;

import org.xmodel.xpath.expression.ExpressionException;
import com.stonewall.cornerstone.entity.util.IpAddr;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.function.StringFunction;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class IPContainsFunction extends Function
{
    public static final String name = "cm:ip-contains";
    
    @Override
    public String getName() {
        return "cm:ip-contains";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.BOOLEAN;
    }
    
    @Override
    public void addArgument(IExpression argument) {
        if (argument.getType() != IExpression.ResultType.STRING) {
            argument = new StringFunction(argument);
        }
        super.addArgument(argument);
    }
    
    @Override
    public boolean evaluateBoolean(final IContext context) throws ExpressionException {
        this.assertArgs(2, 2);
        this.assertType(context, IExpression.ResultType.STRING);
        final String parentip = this.getArguments().get(0).evaluateString(context);
        final String childip = this.getArguments().get(1).evaluateString(context);
        try {
            final IpAddr parentipAddr = new IpAddr(parentip);
            final IpAddr childipAddr = new IpAddr(childip);
            return parentipAddr.contains(childipAddr);
        }
        catch (Exception e) {
            return false;
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
