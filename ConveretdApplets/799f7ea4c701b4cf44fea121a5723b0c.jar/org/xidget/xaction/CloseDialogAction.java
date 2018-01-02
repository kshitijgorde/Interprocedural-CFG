// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.xaction;

import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.StatefulContext;
import org.xidget.IXidget;
import org.xidget.ifeature.dialog.IDialogFeature;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.XPath;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xaction.GuardedAction;

public class CloseDialogAction extends GuardedAction
{
    private final IExpression xidgetExpr;
    
    public CloseDialogAction() {
        this.xidgetExpr = XPath.createExpression("$dialog");
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final IModelObject queryFirst = this.xidgetExpr.queryFirst(context);
        if (queryFirst == null) {
            return null;
        }
        ((IXidget)queryFirst.getValue()).getFeature(IDialogFeature.class).close((StatefulContext)context);
        return null;
    }
}
