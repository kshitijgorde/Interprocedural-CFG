// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.xaction;

import org.xmodel.xpath.variable.IVariableScope;
import org.xmodel.IModelObject;
import org.xidget.IToolkit;
import org.xmodel.Xlate;
import org.xmodel.xpath.expression.StatefulContext;
import org.xidget.Creator;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.Conventions;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xaction.IXAction;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xaction.GuardedAction;

public class OpenConfirmDialogAction extends GuardedAction
{
    private IExpression targetExpr;
    private IExpression titleExpr;
    private IExpression imageExpr;
    private IExpression messageExpr;
    private IExpression allowCancelExpr;
    private String variable;
    private IXAction onClose;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.variable = Conventions.getVarName(xActionDocument.getRoot(), false, new String[0]);
        this.targetExpr = xActionDocument.getExpression("target", true);
        this.allowCancelExpr = xActionDocument.getExpression("allowCancel", true);
        this.titleExpr = xActionDocument.getExpression("title", true);
        this.imageExpr = xActionDocument.getExpression("image", true);
        this.messageExpr = xActionDocument.getExpression("message", true);
        if (this.messageExpr == null) {
            this.messageExpr = xActionDocument.getExpression();
        }
        this.onClose = xActionDocument.createChildScript("onClose", new String[0]);
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final String evaluateString = this.titleExpr.evaluateString(context);
        final String evaluateString2 = this.messageExpr.evaluateString(context);
        final boolean b = this.allowCancelExpr != null && this.allowCancelExpr.evaluateBoolean(context);
        final IModelObject modelObject = (this.imageExpr != null) ? this.imageExpr.queryFirst(context) : null;
        final IToolkit.Confirmation openConfirmDialog = Creator.getToolkit().openConfirmDialog((StatefulContext)context, evaluateString, (modelObject != null) ? modelObject.getValue() : null, evaluateString2, b);
        if (this.targetExpr != null) {
            final IModelObject queryFirst = this.targetExpr.queryFirst(context);
            if (queryFirst != null) {
                Xlate.set(queryFirst, openConfirmDialog.name());
            }
        }
        if (this.variable != null) {
            final IVariableScope scope = context.getScope();
            if (scope != null) {
                scope.set(this.variable, openConfirmDialog.name());
            }
        }
        if (this.onClose != null && openConfirmDialog == IToolkit.Confirmation.yes) {
            return this.onClose.run(context);
        }
        return null;
    }
}
