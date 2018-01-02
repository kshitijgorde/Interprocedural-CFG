// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.xaction;

import org.xidget.NamedContexts;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xaction.GuardedAction;

public class DeleteNamedContext extends GuardedAction
{
    private IExpression nameExpr;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.nameExpr = xActionDocument.getExpression();
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        NamedContexts.remove(this.nameExpr.evaluateString(context));
        return null;
    }
}
