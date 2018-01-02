// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.xaction;

import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xaction.GuardedAction;

public class ExitAction extends GuardedAction
{
    private IExpression exitExpr;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.exitExpr = xActionDocument.getExpression();
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        System.exit((int)this.exitExpr.evaluateNumber(context));
        return null;
    }
}
