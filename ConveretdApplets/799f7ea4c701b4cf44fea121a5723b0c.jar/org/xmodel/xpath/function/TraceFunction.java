// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.log.Log;

public class TraceFunction extends Function
{
    public static final String name = "trace";
    private static Log ¢;
    
    static {
        TraceFunction.¢ = Log.getLog("org.xmodel.xpath.function");
    }
    
    @Override
    public String getName() {
        return "trace";
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
    public void bind(final IContext context) {
        System.out.println(this.E(context));
        System.out.println("  bind: " + context);
        this.getArgument(0).bind(context);
    }
    
    @Override
    public void unbind(final IContext context) {
        System.out.println(this.E(context));
        System.out.println("  unbind: " + context);
        this.getArgument(0).unbind(context);
    }
    
    @Override
    public boolean evaluateBoolean(final IContext context) throws ExpressionException {
        final boolean evaluateBoolean = this.getArgument(0).evaluateBoolean(context);
        System.out.println(this.E(context));
        System.out.println("  eval: " + context + ", " + evaluateBoolean);
        return evaluateBoolean;
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        final List<IModelObject> evaluateNodes = this.getArgument(0).evaluateNodes(context);
        System.out.println(this.E(context));
        System.out.println("  eval: " + context + ", " + evaluateNodes.size());
        for (int i = 0; i < evaluateNodes.size(); ++i) {
            System.out.println("    [" + i + "] " + evaluateNodes.get(i));
        }
        return evaluateNodes;
    }
    
    @Override
    public double evaluateNumber(final IContext context) throws ExpressionException {
        final double evaluateNumber = this.getArgument(0).evaluateNumber(context);
        System.out.println(this.E(context));
        System.out.println("  eval: " + context + ", " + evaluateNumber);
        return evaluateNumber;
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        final String evaluateString = this.getArgument(0).evaluateString(context);
        System.out.println(this.E(context));
        System.out.println("  eval: " + context + ", " + evaluateString);
        return evaluateString;
    }
    
    private String E(final IContext context) {
        try {
            final IExpression argument = this.getArgument(1);
            if (argument == null) {
                return "\n" + this.toString();
            }
            return argument.evaluateString(context);
        }
        catch (ExpressionException ex) {
            TraceFunction.¢.exception(ex);
            return "Error in trace prefix: ";
        }
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        System.out.println(this.E(context));
        System.out.println("  added: " + context + ", " + list.size());
        for (int i = 0; i < list.size(); ++i) {
            System.out.println("    [" + i + "] " + list.get(i));
        }
        this.getParent().notifyAdd(this, context, list);
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        System.out.println(this.E(context));
        System.out.println("  removed: " + context + ", " + list.size());
        for (int i = 0; i < list.size(); ++i) {
            System.out.println("    [" + i + "] " + list.get(i));
        }
        this.getParent().notifyRemove(this, context, list);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final boolean b) {
        System.out.println(this.E(context));
        System.out.println("  changed: new=" + b + ", context=" + context);
        this.getParent().notifyChange(this, context, b);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
        System.out.println(this.E(context));
        System.out.println("  changed: new=" + n + ", old=" + n2 + ", context=" + context);
        this.getParent().notifyChange(this, context, n, n2);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        System.out.println(this.E(context));
        System.out.println("  changed: new=" + s + ", old=" + s2 + ", context=" + context);
        this.getParent().notifyChange(this, context, s, s2);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context) {
        System.out.println(this.E(context));
        System.out.println("  changed: context=" + context);
        this.getParent().notifyChange(this, context);
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
        System.out.println(this.E(array[0]));
        System.out.println("  changed: new=" + o + ", old=" + o2);
        this.getParent().notifyValue(this, array, modelObject, o, o2);
    }
}
