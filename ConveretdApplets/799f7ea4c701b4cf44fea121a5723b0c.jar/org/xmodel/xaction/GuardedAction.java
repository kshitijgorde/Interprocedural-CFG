// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import org.xmodel.xpath.expression.IContext;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IExpression;

public abstract class GuardedAction extends XAction
{
    private IExpression K;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.K = xActionDocument.getExpression("when", false);
        if (this.K == null) {
            final IModelObject attributeNode = xActionDocument.getRoot().getAttributeNode("when");
            if (attributeNode != null) {
                this.K = xActionDocument.getExpression(attributeNode);
            }
        }
        if (this.K == null) {
            this.K = xActionDocument.getExpression("condition", false);
        }
    }
    
    @Override
    public final Object[] doRun(final IContext context) {
        if (this.K == null || this.K.evaluateBoolean(context)) {
            return this.doAction(context);
        }
        return null;
    }
    
    protected abstract Object[] doAction(final IContext p0);
}
