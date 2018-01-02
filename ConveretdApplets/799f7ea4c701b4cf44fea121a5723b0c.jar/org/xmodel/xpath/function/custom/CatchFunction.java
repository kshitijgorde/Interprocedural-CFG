// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function.custom;

import org.xmodel.xpath.expression.ExpressionException;
import java.util.Collections;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.function.Function;

public class CatchFunction extends Function
{
    public static final String name = "catch";
    
    @Override
    public String getName() {
        return "catch";
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
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        try {
            return this.getArgument(0).evaluateNodes(context);
        }
        catch (ExpressionException ex) {
            final IModelObject queryFirst = this.getArgument(1).queryFirst(context);
            if (queryFirst != null) {
                Xlate.set(queryFirst, ex.getMessage());
            }
            if (this.getArguments().size() == 3) {
                return this.getArgument(2).evaluateNodes(context);
            }
            return Collections.emptyList();
        }
    }
    
    @Override
    public double evaluateNumber(final IContext context) throws ExpressionException {
        try {
            return this.getArgument(0).evaluateNumber(context);
        }
        catch (ExpressionException ex) {
            final IModelObject queryFirst = this.getArgument(1).queryFirst(context);
            if (queryFirst != null) {
                Xlate.set(queryFirst, ex.getMessage());
            }
            if (this.getArguments().size() == 3) {
                return this.getArgument(2).evaluateNumber(context);
            }
            return 0.0;
        }
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        try {
            return this.getArgument(0).evaluateString(context);
        }
        catch (ExpressionException ex) {
            final IModelObject queryFirst = this.getArgument(1).queryFirst(context);
            if (queryFirst != null) {
                Xlate.set(queryFirst, ex.getMessage());
            }
            if (this.getArguments().size() == 3) {
                return this.getArgument(2).evaluateString(context);
            }
            return "";
        }
    }
    
    @Override
    public boolean evaluateBoolean(final IContext context) throws ExpressionException {
        try {
            return this.getArgument(0).evaluateBoolean(context);
        }
        catch (ExpressionException ex) {
            final IModelObject queryFirst = this.getArgument(1).queryFirst(context);
            if (queryFirst != null) {
                Xlate.set(queryFirst, ex.getMessage());
            }
            return this.getArguments().size() == 3 && this.getArgument(2).evaluateBoolean(context);
        }
    }
    
    @Override
    public void bind(final IContext context) {
        this.getArgument(0).bind(context);
    }
    
    @Override
    public void unbind(final IContext context) {
        this.getArgument(0).unbind(context);
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        if (expression == this.getArgument(0)) {
            this.getParent().notifyAdd(this, context, list);
        }
        else {
            this.getParent().notifyChange(this, context);
        }
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        if (expression == this.getArgument(0)) {
            this.getParent().notifyRemove(this, context, list);
        }
        else {
            this.getParent().notifyChange(this, context);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final boolean b) {
        if (expression == this.getArgument(0)) {
            this.getParent().notifyChange(this, context, b);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
        if (expression == this.getArgument(0)) {
            this.getParent().notifyChange(this, context, n, n2);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        if (expression == this.getArgument(0)) {
            this.getParent().notifyChange(this, context, s, s2);
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context) {
        this.getParent().notifyChange(this, context);
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
        if (expression == this.getArgument(0)) {
            this.getParent().notifyValue(this, array, modelObject, o, o2);
        }
    }
}
