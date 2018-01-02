// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.layout.xaction;

import java.util.Iterator;
import org.xmodel.IModelObject;
import java.util.List;
import org.xidget.ifeature.IWidgetFeature;
import org.xidget.IXidget;
import java.util.ArrayList;
import org.xidget.ifeature.IWidgetContainerFeature;
import org.xidget.Creator;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xaction.GuardedAction;

public abstract class AbstractLayoutAction extends GuardedAction
{
    private IExpression xidgetsExpr;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.xidgetsExpr = xActionDocument.getExpression("xidgets", true);
    }
    
    @Override
    protected Object[] doAction(final IContext context) {
        final Creator instance = Creator.getInstance();
        final IXidget xidget = instance.findXidget(context.getObject());
        final int spacing = xidget.getFeature(IWidgetContainerFeature.class).getSpacing();
        if (this.xidgetsExpr == null) {
            final ArrayList<IXidget> list = new ArrayList<IXidget>();
            for (final IXidget xidget2 : xidget.getChildren()) {
                final IWidgetFeature widgetFeature = xidget2.getFeature(IWidgetFeature.class);
                if (widgetFeature != null && widgetFeature.getVisible()) {
                    list.add(xidget2);
                }
            }
            if (xidget.getChildren().size() > 0) {
                this.layout(context, xidget, list, spacing);
            }
        }
        else {
            final List<IModelObject> evaluateNodes = this.xidgetsExpr.evaluateNodes(context);
            final ArrayList list2 = new ArrayList<IXidget>(evaluateNodes.size());
            final Iterator<IModelObject> iterator2 = evaluateNodes.iterator();
            while (iterator2.hasNext()) {
                final IXidget xidget3 = instance.findXidget(iterator2.next());
                if (xidget3 != null) {
                    list2.add(xidget3);
                }
            }
            this.layout(context, xidget, (List<IXidget>)list2, spacing);
        }
        return null;
    }
    
    protected abstract void layout(final IContext p0, final IXidget p1, final List<IXidget> p2, final int p3);
}
