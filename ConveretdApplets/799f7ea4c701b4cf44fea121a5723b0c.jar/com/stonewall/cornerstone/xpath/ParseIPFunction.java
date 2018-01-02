// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.xpath;

import com.stonewall.cornerstone.entity.util.IpAddr;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class ParseIPFunction extends Function
{
    public static final String name = "cm:parse-ip";
    
    @Override
    public String getName() {
        return "cm:parse-ip";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.STRING;
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.assertArgs(1, 1);
        return this.parseIP(this.getArgument(0).evaluateString(context));
    }
    
    private String parseIP(final String ipMask) {
        try {
            if (ipMask.length() > 0) {
                final IpAddr address = new IpAddr(ipMask);
                return address.getAddressString();
            }
        }
        catch (Exception e) {
            throw new ExpressionException(this, "Unable to evaluate " + this.toString() + ":", e);
        }
        return "";
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String newValue, final String oldValue) {
        this.getParent().notifyChange(this, context, this.parseIP(newValue), this.parseIP(oldValue));
    }
}
