// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.layout.xaction;

import org.xidget.ifeature.ILayoutFeature;
import java.util.List;
import org.xidget.IXidget;
import org.xmodel.xpath.expression.IContext;

public class LayoutLeftRightAction extends AbstractLayoutAction
{
    @Override
    protected void layout(final IContext context, final IXidget xidget, final List<IXidget> list, final int n) {
        final ILayoutFeature layoutFeature = xidget.getFeature(ILayoutFeature.class);
        layoutFeature.attachContainer(list.get(0), ILayoutFeature.Side.left, 0);
        for (int i = 1; i < list.size(); ++i) {
            layoutFeature.attachPeer(list.get(i), ILayoutFeature.Side.left, list.get(i - 1), ILayoutFeature.Side.right, n);
        }
    }
}
