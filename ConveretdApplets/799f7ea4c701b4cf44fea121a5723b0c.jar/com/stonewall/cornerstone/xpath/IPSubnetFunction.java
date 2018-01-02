// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.xpath;

import org.xmodel.IModelObject;
import com.stonewall.cornerstone.entity.util.IpAddr;
import org.xmodel.xpath.expression.ExpressionException;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class IPSubnetFunction extends Function
{
    public static final String name = "cm:ip-mask";
    
    @Override
    public String getName() {
        return "cm:ip-mask";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.STRING;
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.assertArgs(1, 2);
        final List<IExpression> arguments = this.getArguments();
        if (arguments.size() == 1) {
            final String address = this.getArgument(0).evaluateString(context);
            return this.getSubnet(address);
        }
        final String address = this.getArgument(0).evaluateString(context);
        final String netmask = this.getArgument(1).evaluateString(context);
        return this.getSubnet(address, netmask);
    }
    
    private String getSubnet(final String address) {
        final IpAddr ip = IpAddr.create(address);
        final IpAddr subnet = ip.getSubnet();
        return subnet.toString();
    }
    
    private String getSubnet(final String address, final String netmask) {
        final IpAddr ip = IpAddr.create(address, netmask);
        final IpAddr subnet = ip.getSubnet();
        return subnet.toString();
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String newValue, final String oldValue) {
        final List<IExpression> arguments = this.getArguments();
        if (arguments.size() == 1) {
            final String oldResult = this.getSubnet(oldValue);
            final String newResult = this.getSubnet(newValue);
            this.getParent().notifyChange(this, context, newResult, oldResult);
        }
        else if (expression == this.getArgument(0)) {
            context.getModel().revert();
            final String oldResult = this.getSubnet(oldValue, this.getArgument(1).evaluateString(context));
            context.getModel().restore();
            final String newResult = this.getSubnet(newValue, this.getArgument(1).evaluateString(context));
            this.getParent().notifyChange(this, context, newResult, oldResult);
        }
        else {
            context.getModel().revert();
            final String oldResult = this.getSubnet(this.getArgument(0).evaluateString(context), oldValue);
            context.getModel().restore();
            final String newResult = this.getSubnet(this.getArgument(0).evaluateString(context), newValue);
            this.getParent().notifyChange(this, context, newResult, oldResult);
        }
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] contexts, final IModelObject object, final Object newValue, final Object oldValue) {
        final String oldString = (oldValue != null) ? oldValue.toString() : "";
        final String newString = (newValue != null) ? newValue.toString() : "";
        this.notifyChange(expression, contexts[0], newString, oldString);
    }
}
