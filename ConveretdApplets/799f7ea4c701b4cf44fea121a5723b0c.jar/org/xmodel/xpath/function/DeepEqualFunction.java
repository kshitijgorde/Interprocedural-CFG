// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import org.xmodel.xpath.expression.ExpressionException;
import java.util.List;
import org.xmodel.IChangeSet;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.diff.IXmlMatcher;
import org.xmodel.diff.DefaultXmlMatcher;
import org.xmodel.diff.XmlDiffer;

public class DeepEqualFunction extends Function
{
    public static final String name = "deep-equal";
    private XmlDiffer w;
    
    public DeepEqualFunction() {
        this.w = new XmlDiffer(new DefaultXmlMatcher(true));
    }
    
    @Override
    public String getName() {
        return "deep-equal";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.BOOLEAN;
    }
    
    @Override
    public boolean evaluateBoolean(final IContext context) throws ExpressionException {
        this.assertArgs(2, 2);
        this.assertType(context, IExpression.ResultType.NODES);
        final List<IModelObject> evaluateNodes = this.getArgument(0).evaluateNodes(context);
        final List<IModelObject> evaluateNodes2 = this.getArgument(1).evaluateNodes(context);
        if (evaluateNodes.size() != evaluateNodes2.size()) {
            return false;
        }
        if (evaluateNodes.size() == 0) {
            return true;
        }
        for (int i = 0; i < evaluateNodes.size(); ++i) {
            if (!this.w.diff(evaluateNodes.get(i), evaluateNodes2.get(i), null)) {
                return false;
            }
        }
        return true;
    }
}
