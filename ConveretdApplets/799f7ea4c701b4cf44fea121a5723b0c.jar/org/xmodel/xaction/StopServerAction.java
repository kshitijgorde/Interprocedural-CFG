// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import org.xmodel.net.Server;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class StopServerAction extends GuardedAction
{
    private IExpression \u00e9;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.\u00e9 = xActionDocument.getExpression();
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        ((Server)this.\u00e9.queryFirst(context).getValue()).stop();
        return null;
    }
}
