// 
// Decompiled by Procyon v0.5.30
// 

package com.nephos6.platform.ui.xpath;

import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.ExpressionException;
import com.stonewall.cornerstone.utility.Encrypted;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class EncryptFunction extends Function
{
    public static final String name = "ui:encrypt";
    
    @Override
    public String getName() {
        return "ui:encrypt";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.STRING;
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.assertArgs(1, 1);
        return Encrypted.encrypt(this.getArgument(0).evaluateString(context));
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> nodes) {
        this.getParent().notifyChange(this, context);
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> nodes) {
        this.getParent().notifyChange(this, context);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final boolean newValue) {
        this.getParent().notifyChange(this, context, Boolean.toString(newValue), Boolean.toString(!newValue));
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final double newValue, final double oldValue) {
        this.getParent().notifyChange(this, context, Double.toString(newValue), Double.toString(oldValue));
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String newValue, final String oldValue) {
        this.getParent().notifyChange(this, context, newValue, oldValue);
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] contexts, final IModelObject object, final Object newValue, final Object oldValue) {
        final String newString = (newValue != null) ? newValue.toString() : "";
        final String oldString = (oldValue != null) ? oldValue.toString() : "";
        this.getParent().notifyChange(this, contexts[0], newString, oldString);
    }
    
    @Override
    public boolean requiresValueNotification(final IExpression argument) {
        return true;
    }
}
