// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.xaction;

import java.util.Iterator;
import org.xmodel.IModelObject;
import org.xidget.config.TagException;
import org.xidget.IXidget;
import org.xidget.ifeature.IWidgetFeature;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xaction.XActionException;
import org.xidget.Creator;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xaction.GuardedAction;

public class CreateXidgetAction extends GuardedAction
{
    private IExpression contextExpr;
    private IExpression parentExpr;
    private IExpression xidgetExpr;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.contextExpr = xActionDocument.getExpression("context", true);
        this.parentExpr = xActionDocument.getExpression("parent", true);
        this.xidgetExpr = xActionDocument.getExpression("xidget", true);
        if (this.xidgetExpr == null) {
            this.xidgetExpr = xActionDocument.getExpression();
        }
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final Creator instance = Creator.getInstance();
        final IModelObject queryFirst = this.xidgetExpr.queryFirst(context);
        if (queryFirst == null) {
            throw new XActionException("Xidget not found at: " + this.xidgetExpr);
        }
        try {
            final StatefulContext statefulContext = new StatefulContext(context, queryFirst);
            final Iterator<IXidget> iterator = instance.create(instance.findXidget((this.parentExpr != null) ? this.parentExpr.queryFirst(context) : null), statefulContext, this.createBindContext(context, statefulContext)).iterator();
            while (iterator.hasNext()) {
                final IWidgetFeature widgetFeature = iterator.next().getFeature(IWidgetFeature.class);
                if (widgetFeature != null) {
                    widgetFeature.setVisible(true);
                }
            }
        }
        catch (TagException ex) {
            throw new XActionException(ex);
        }
        return null;
    }
    
    private StatefulContext createBindContext(final IContext context, final StatefulContext statefulContext) {
        if (this.contextExpr != null) {
            final IModelObject queryFirst = this.contextExpr.queryFirst(context);
            if (queryFirst != null) {
                return new StatefulContext(statefulContext, queryFirst);
            }
        }
        return statefulContext;
    }
}
