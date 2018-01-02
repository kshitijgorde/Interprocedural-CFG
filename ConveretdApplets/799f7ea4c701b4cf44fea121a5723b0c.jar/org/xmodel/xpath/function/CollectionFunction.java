// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import org.xmodel.xpath.expression.ExpressionException;
import java.util.Collections;
import org.xmodel.ModelRegistry;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class CollectionFunction extends Function
{
    public static final String name = "collection";
    
    @Override
    public String getName() {
        return "collection";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.NODES;
    }
    
    @Override
    public List<IModelObject> evaluateNodes(final IContext context) throws ExpressionException {
        this.assertArgs(1, 1);
        this.assertType(context, IExpression.ResultType.STRING);
        final List<IModelObject> roots = ModelRegistry.getInstance().getModel().getRoots(this.getArgument(0).evaluateString(context));
        if (roots == null) {
            return Collections.emptyList();
        }
        return roots;
    }
    
    @Override
    public void bind(final IContext context) {
    }
    
    @Override
    public void unbind(final IContext context) {
    }
}
