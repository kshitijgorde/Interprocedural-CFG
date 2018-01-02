// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;

public class LoopAction extends GuardedAction
{
    private IExpression \u00e1;
    private IExpression \u00e0;
    private ScriptAction \u00e2;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.\u00e1 = xActionDocument.getExpression("while", true);
        this.\u00e0 = xActionDocument.getExpression("count", true);
        this.\u00e2 = xActionDocument.createScript("while", "count");
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        if (this.\u00e0 == null) {
            while (this.\u00e1 == null || this.\u00e1.evaluateBoolean(context)) {
                final Object[] run = this.\u00e2.run(context);
                if (run != null) {
                    return run;
                }
            }
        }
        else if (this.\u00e1 == null) {
            for (int n = (int)this.\u00e0.evaluateNumber(context), i = 0; i < n; ++i) {
                final Object[] run2 = this.\u00e2.run(context);
                if (run2 != null) {
                    return run2;
                }
            }
        }
        else {
            for (int n2 = (int)this.\u00e0.evaluateNumber(context), n3 = 0; n3 < n2 && this.\u00e1.evaluateBoolean(context); ++n3) {
                final Object[] run3 = this.\u00e2.run(context);
                if (run3 != null) {
                    return run3;
                }
            }
        }
        return null;
    }
}
