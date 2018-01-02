// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import org.xmodel.xpath.expression.ExpressionException;
import java.util.List;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class NameFunction extends Function
{
    @Override
    public String getName() {
        return "name";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.STRING;
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.assertArgs(0, 1);
        this.assertType(context, IExpression.ResultType.NODES);
        final List<IExpression> arguments = this.getArguments();
        if (arguments.size() == 0) {
            final IModelObject object = context.getObject();
            if (object != null) {
                return object.getType();
            }
        }
        else {
            final List<IModelObject> evaluateNodes = arguments.get(0).evaluateNodes(context);
            if (evaluateNodes.size() > 0) {
                return evaluateNodes.get(0).getType();
            }
        }
        return "";
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
        if (this.getParent() != null) {
            this.notifyChange(this, context);
        }
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
        if (this.getParent() != null) {
            this.notifyChange(this, context);
        }
    }
}
