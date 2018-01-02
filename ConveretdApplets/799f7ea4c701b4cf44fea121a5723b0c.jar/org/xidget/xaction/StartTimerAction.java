// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.xaction;

import org.xidget.Creator;
import org.xidget.ifeature.IAsyncFeature;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xaction.IXAction;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xaction.GuardedAction;

public class StartTimerAction extends GuardedAction
{
    private IExpression idExpr;
    private IExpression delayExpr;
    private IExpression repeatExpr;
    private IXAction script;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.idExpr = xActionDocument.getExpression("id", true);
        this.delayExpr = xActionDocument.getExpression("delay", true);
        this.repeatExpr = xActionDocument.getExpression("repeat", true);
        this.script = xActionDocument.createScript("delay", "repeat");
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final String evaluateString = this.idExpr.evaluateString(context);
        final int n = (int)((this.delayExpr != null) ? this.delayExpr.evaluateNumber(context) : -1.0);
        final boolean b = this.repeatExpr != null && this.repeatExpr.evaluateBoolean(context);
        final IAsyncFeature asyncFeature = Creator.getToolkit().getFeature(IAsyncFeature.class);
        if (asyncFeature != null) {
            asyncFeature.schedule(evaluateString, n, b, new Task(context));
        }
        return null;
    }
    
    private class Task implements Runnable
    {
        private IContext context;
        
        public Task(final IContext context) {
            this.context = context;
        }
        
        @Override
        public void run() {
            StartTimerAction.this.script.run(this.context);
        }
    }
}
