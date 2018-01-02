// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.layout.xaction;

import org.xidget.IXidget;
import java.util.Iterator;
import org.xidget.ifeature.IWidgetContainerFeature;
import org.xmodel.IModelObject;
import java.util.List;
import org.xidget.Creator;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xaction.GuardedAction;

public class RelayoutAction extends GuardedAction
{
    private IExpression xidgetExpr;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.xidgetExpr = xActionDocument.getExpression();
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final Creator instance = Creator.getInstance();
        final Iterator<IModelObject> iterator = this.xidgetExpr.query(context, null).iterator();
        while (iterator.hasNext()) {
            final IXidget xidget = instance.findXidget(iterator.next());
            if (xidget != null) {
                final IWidgetContainerFeature widgetContainerFeature = xidget.getFeature(IWidgetContainerFeature.class);
                if (widgetContainerFeature == null) {
                    continue;
                }
                widgetContainerFeature.relayout();
            }
        }
        return null;
    }
}
