// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import java.util.Iterator;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class DeleteAction extends GuardedAction
{
    IExpression L;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.L = xActionDocument.getExpression("target", false);
        if (this.L == null) {
            this.L = xActionDocument.getExpression(xActionDocument.getRoot());
        }
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final Iterator<IModelObject> iterator = this.L.evaluateNodes(context).iterator();
        while (iterator.hasNext()) {
            iterator.next().removeFromParent();
        }
        return null;
    }
}
