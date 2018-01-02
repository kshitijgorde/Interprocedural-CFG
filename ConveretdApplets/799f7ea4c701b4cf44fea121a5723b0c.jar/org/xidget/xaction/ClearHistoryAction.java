// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.xaction;

import org.xmodel.IModelObject;
import org.xmodel.Xlate;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xaction.GuardedAction;

public class ClearHistoryAction extends GuardedAction
{
    private IExpression historyExpr;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.historyExpr = xActionDocument.getExpression("history", true);
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final IModelObject queryFirst = this.historyExpr.queryFirst(context);
        Xlate.set(queryFirst, "index", 0);
        queryFirst.removeChildren();
        return null;
    }
}
