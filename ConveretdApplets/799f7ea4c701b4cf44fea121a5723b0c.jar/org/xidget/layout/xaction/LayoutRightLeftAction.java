// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.layout.xaction;

import org.xidget.ifeature.ILayoutFeature;
import java.util.List;
import org.xidget.IXidget;
import org.xmodel.xpath.expression.IContext;

public class LayoutRightLeftAction extends AbstractLayoutAction
{
    @Override
    protected void layout(final IContext context, final IXidget xidget, final List<IXidget> list, final int n) {
        final ILayoutFeature layoutFeature = xidget.getFeature(ILayoutFeature.class);
        final int n2 = list.size() - 1;
        layoutFeature.attachContainer(list.get(n2), ILayoutFeature.Side.right, 0);
        for (int i = 0; i < n2; ++i) {
            layoutFeature.attachPeer(list.get(i), ILayoutFeature.Side.right, list.get(i + 1), ILayoutFeature.Side.left, -n);
        }
    }
}
