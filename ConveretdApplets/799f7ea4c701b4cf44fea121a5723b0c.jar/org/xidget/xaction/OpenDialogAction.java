// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.xaction;

import org.xmodel.IModelObject;
import org.xidget.ifeature.dialog.IDialogFeature;
import org.xidget.ifeature.IBindFeature;
import org.xidget.config.TagException;
import org.xmodel.xpath.expression.StatefulContext;
import org.xidget.Creator;
import org.xidget.IXidget;
import org.xmodel.xaction.XActionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xaction.ScriptAction;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xaction.GuardedAction;

public class OpenDialogAction extends GuardedAction
{
    private IExpression dialogExpr;
    private IExpression contextExpr;
    private ScriptAction onClose;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.dialogExpr = xActionDocument.getExpression("config", true);
        this.contextExpr = xActionDocument.getExpression("context", true);
        this.onClose = xActionDocument.createChildScript("onClose", new String[0]);
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final IModelObject queryFirst = this.dialogExpr.queryFirst(context);
        if (queryFirst == null) {
            throw new XActionException("Unable to open dialog because configuration not found: " + this.dialogExpr);
        }
        IXidget xidget;
        try {
            xidget = Creator.getInstance().create(null, new StatefulContext(context, queryFirst)).get(0);
        }
        catch (TagException ex) {
            throw new XActionException("Unable to create dialog: " + queryFirst, ex);
        }
        final IModelObject modelObject = (this.contextExpr != null) ? this.contextExpr.queryFirst(context) : context.getObject();
        if (modelObject == null) {
            return null;
        }
        final StatefulContext statefulContext = new StatefulContext(context, modelObject);
        final IBindFeature bindFeature = xidget.getFeature(IBindFeature.class);
        bindFeature.bind(statefulContext);
        try {
            xidget.getFeature(IDialogFeature.class).open(statefulContext);
            if (this.onClose != null) {
                return this.onClose.run(statefulContext);
            }
        }
        finally {
            bindFeature.unbind(statefulContext);
            Creator.getInstance().destroy(xidget);
        }
        bindFeature.unbind(statefulContext);
        Creator.getInstance().destroy(xidget);
        return null;
    }
}
