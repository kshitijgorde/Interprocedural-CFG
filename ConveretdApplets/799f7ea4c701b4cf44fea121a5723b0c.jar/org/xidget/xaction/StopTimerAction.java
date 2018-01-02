// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.xaction;

import org.xidget.Creator;
import org.xidget.ifeature.IAsyncFeature;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xaction.GuardedAction;

public class StopTimerAction extends GuardedAction
{
    private IExpression idExpr;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.idExpr = xActionDocument.getExpression("id", true);
        if (this.idExpr == null) {
            xActionDocument.getExpression();
        }
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final String evaluateString = this.idExpr.evaluateString(context);
        final IAsyncFeature asyncFeature = Creator.getToolkit().getFeature(IAsyncFeature.class);
        if (asyncFeature != null) {
            asyncFeature.cancel(evaluateString);
        }
        return null;
    }
}
