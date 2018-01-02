// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import org.xmodel.IChangeSet;
import org.xmodel.ModelAlgorithms;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.IModelObjectFactory;

public class CreatePathAction extends GuardedAction
{
    private IModelObjectFactory _;
    private IExpression a;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.a = xActionDocument.getExpression("source", false);
        if (this.a == null) {
            this.a = xActionDocument.getExpression(xActionDocument.getRoot());
        }
        this._ = this.getFactory(xActionDocument.getRoot());
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        ModelAlgorithms.createPathSubtree(context, this.a, this._, null);
        return null;
    }
}
