// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.xaction;

import org.xidget.IXidget;
import org.xidget.Creator;
import org.xidget.ifeature.IFocusFeature;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xaction.GuardedAction;

public class FocusAction extends GuardedAction
{
    private IExpression focusExpr;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.focusExpr = xActionDocument.getExpression();
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final IFocusFeature focusFeature = Creator.getToolkit().getFeature(IFocusFeature.class);
        if (focusFeature != null) {
            final IXidget xidget = Creator.getInstance().findXidget(this.focusExpr.queryFirst(context));
            if (xidget != null) {
                focusFeature.setFocus(xidget);
            }
        }
        return null;
    }
}
