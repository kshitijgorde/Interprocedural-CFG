// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.expression;

import org.xmodel.IModel;
import java.util.Collections;
import org.xmodel.IModelObject;
import java.util.List;

public abstract class ExtentExpressionListener extends ExpressionListener
{
    public abstract void notifyChange(final IExpression p0, final IContext p1, final List<IModelObject> p2, final List<IModelObject> p3);
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        final IModel model = context.getModel();
        List<IModelObject> list2 = Collections.emptyList();
        Label_0071: {
            try {
                model.revert();
                list2 = expression.evaluateNodes(context);
            }
            catch (ExpressionException ex) {
                this.handleException(expression, context, ex);
                break Label_0071;
            }
            finally {
                model.restore();
            }
            model.restore();
        }
        List<IModelObject> list3 = Collections.emptyList();
        try {
            list3 = expression.evaluateNodes(context);
        }
        catch (ExpressionException ex2) {
            this.handleException(expression, context, ex2);
        }
        this.notifyChange(expression, context, list3, list2);
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        final IModel model = context.getModel();
        List<IModelObject> list2 = Collections.emptyList();
        Label_0071: {
            try {
                model.revert();
                list2 = expression.evaluateNodes(context);
            }
            catch (ExpressionException ex) {
                this.handleException(expression, context, ex);
                break Label_0071;
            }
            finally {
                model.restore();
            }
            model.restore();
        }
        List<IModelObject> list3 = Collections.emptyList();
        try {
            list3 = expression.evaluateNodes(context);
        }
        catch (ExpressionException ex2) {
            this.handleException(expression, context, ex2);
        }
        this.notifyChange(expression, context, list3, list2);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context) {
        if (expression.getType(context) == IExpression.ResultType.NODES) {
            final IModel model = context.getModel();
            List<IModelObject> list = Collections.emptyList();
            Label_0079: {
                try {
                    model.revert();
                    list = expression.evaluateNodes(context);
                }
                catch (ExpressionException ex) {
                    this.handleException(expression, context, ex);
                    break Label_0079;
                }
                finally {
                    model.restore();
                }
                model.restore();
            }
            List<IModelObject> list2 = Collections.emptyList();
            try {
                list2 = expression.evaluateNodes(context);
            }
            catch (ExpressionException ex2) {
                this.handleException(expression, context, ex2);
            }
            this.notifyChange(expression, context, list2, list);
        }
    }
}
