// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.layout.xaction;

import org.xmodel.IModelObject;
import org.xidget.ifeature.ILayoutFeature;
import java.util.List;
import org.xidget.IXidget;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.expression.IExpression;

public class LayoutGridYAction extends AbstractLayoutAction
{
    private IExpression handleExpr;
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        this.handleExpr = xActionDocument.getExpression("handle", true);
    }
    
    @Override
    protected void layout(final IContext context, final IXidget xidget, final List<IXidget> list, final int n) {
        final ILayoutFeature layoutFeature = xidget.getFeature(ILayoutFeature.class);
        final int n2 = n / 2;
        float n4;
        final float n3 = n4 = 1.0f / list.size();
        final int n5 = list.size() - 1;
        final boolean b = this.handleExpr != null && this.handleExpr.evaluateBoolean(context);
        for (int i = 0; i < n5; ++i, n4 += n3) {
            layoutFeature.attachContainer(list.get(i), ILayoutFeature.Side.bottom, n4, null, -n2, b);
        }
        for (int j = 1; j <= n5; ++j) {
            layoutFeature.attachPeer(list.get(j), ILayoutFeature.Side.top, list.get(j - 1), ILayoutFeature.Side.bottom, n);
        }
        layoutFeature.attachContainer(list.get(0), ILayoutFeature.Side.top, 0);
        layoutFeature.attachContainer(list.get(n5), ILayoutFeature.Side.bottom, 0);
    }
}
