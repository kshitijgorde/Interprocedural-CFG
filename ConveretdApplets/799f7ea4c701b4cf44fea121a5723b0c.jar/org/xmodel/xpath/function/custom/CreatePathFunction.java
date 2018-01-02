// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function.custom;

import org.xmodel.xpath.expression.Context;
import org.xmodel.ModelAlgorithms;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.IPath;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.IPathListener;
import org.xmodel.xpath.function.Function;

public class CreatePathFunction extends Function
{
    public static final String name = "create-path";
    final IPathListener m;
    
    public CreatePathFunction() {
        this.m = new IPathListener() {
            @Override
            public void notifyAdd(final IContext context, final IPath path, final int n, final List<IModelObject> list) {
                CreatePathFunction.this.getParent().notifyChange(CreatePathFunction.this, context);
            }
            
            @Override
            public void notifyRemove(final IContext context, final IPath path, final int n, final List<IModelObject> list) {
                CreatePathFunction.this.getParent().notifyChange(CreatePathFunction.this, context);
            }
        };
    }
    
    @Override
    public String getName() {
        return "create-path";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.STRING;
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.assertArgs(1, 2);
        this.assertType(context, IExpression.ResultType.NODES);
        final List<IModelObject> evaluateNodes = this.getArgument(0).evaluateNodes(context);
        if (evaluateNodes.size() == 0) {
            return "";
        }
        final IPath a = this.A(context, evaluateNodes.get(0));
        return (a != null) ? a.toString() : "";
    }
    
    private IPath A(final IContext context, final IModelObject modelObject) throws ExpressionException {
        final IExpression argument = this.getArgument(1);
        if (argument != null) {
            final List<IModelObject> evaluateNodes = argument.evaluateNodes(context);
            final IModelObject modelObject2 = (evaluateNodes.size() > 0) ? evaluateNodes.get(0) : null;
            if (modelObject2 != null) {
                return ModelAlgorithms.createRelativePath(modelObject, modelObject2);
            }
        }
        return ModelAlgorithms.createIdentityPath(modelObject);
    }
    
    @Override
    public void bind(final IContext context) {
        super.bind(context);
        try {
            final List<IModelObject> evaluateNodes = this.getArgument(0).evaluateNodes(context);
            if (evaluateNodes.size() > 0) {
                final IModelObject modelObject = evaluateNodes.get(0);
                this.A(context, modelObject).addPathListener(new Context(modelObject), this.m);
            }
        }
        catch (ExpressionException ex) {
            this.handleException(this, context, ex);
        }
    }
    
    @Override
    public void unbind(final IContext context) {
        super.unbind(context);
        try {
            final List<IModelObject> evaluateNodes = this.getArgument(0).evaluateNodes(context);
            if (evaluateNodes.size() > 0) {
                final IModelObject modelObject = evaluateNodes.get(0);
                this.A(context, modelObject).removePathListener(new Context(modelObject), this.m);
            }
        }
        catch (ExpressionException ex) {
            this.handleException(this, context, ex);
        }
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        this.getParent().notifyChange(this, context);
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        this.getParent().notifyChange(this, context);
    }
}
