// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.xaction;

import org.xmodel.IModelObject;
import org.xidget.IToolkit;
import org.xmodel.xpath.expression.StatefulContext;
import org.xidget.Creator;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xaction.GuardedAction;

public class OpenMessageDialogAction extends GuardedAction
{
    private IExpression titleExpr;
    private IExpression imageExpr;
    private IExpression messageExpr;
    private IExpression messageTypeExpr;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.titleExpr = xActionDocument.getExpression("title", true);
        this.imageExpr = xActionDocument.getExpression("image", true);
        this.messageTypeExpr = xActionDocument.getExpression("type", true);
        this.messageExpr = xActionDocument.getExpression("message", true);
        if (this.messageExpr == null) {
            this.messageExpr = xActionDocument.getExpression();
        }
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final String evaluateString = this.titleExpr.evaluateString(context);
        final String evaluateString2 = this.messageExpr.evaluateString(context);
        final IModelObject modelObject = (this.imageExpr != null) ? this.imageExpr.queryFirst(context) : null;
        Creator.getToolkit().openMessageDialog((StatefulContext)context, evaluateString, (modelObject != null) ? modelObject.getValue() : null, evaluateString2, IToolkit.MessageType.valueOf(this.messageTypeExpr.evaluateString(context)));
        return null;
    }
}
