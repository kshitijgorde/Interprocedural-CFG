// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.layout.xaction;

import java.util.Iterator;
import org.xidget.ifeature.ILayoutFeature;
import java.util.List;
import org.xidget.IXidget;
import org.xmodel.xpath.expression.IContext;

public class LayoutAlignTopAction extends AbstractLayoutAction
{
    @Override
    protected void layout(final IContext context, final IXidget xidget, final List<IXidget> list, final int n) {
        final ILayoutFeature layoutFeature = xidget.getFeature(ILayoutFeature.class);
        final Iterator<IXidget> iterator = list.iterator();
        while (iterator.hasNext()) {
            layoutFeature.attachContainer(iterator.next(), ILayoutFeature.Side.top, 0);
        }
    }
}
