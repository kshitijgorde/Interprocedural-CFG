// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.layout.xaction;

import java.util.Iterator;
import org.xidget.ifeature.ILayoutFeature;
import java.util.List;
import org.xidget.IXidget;
import org.xmodel.xpath.expression.IContext;

public class LayoutFillXAction extends AbstractLayoutAction
{
    @Override
    protected void layout(final IContext context, final IXidget xidget, final List<IXidget> list, final int n) {
        final ILayoutFeature layoutFeature = xidget.getFeature(ILayoutFeature.class);
        for (final IXidget xidget2 : list) {
            layoutFeature.attachContainer(xidget2, ILayoutFeature.Side.left, 0);
            layoutFeature.attachContainer(xidget2, ILayoutFeature.Side.right, 0);
        }
    }
}
