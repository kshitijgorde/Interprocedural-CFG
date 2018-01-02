// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.layout.xaction;

import org.xidget.ifeature.ILayoutFeature;
import java.util.List;
import org.xidget.IXidget;
import org.xmodel.xpath.expression.IContext;

public class LayoutPackXAction extends AbstractLayoutAction
{
    @Override
    protected void layout(final IContext context, final IXidget xidget, final List<IXidget> list, final int n) {
        xidget.getFeature(ILayoutFeature.class).packContainer(list, ILayoutFeature.Side.right, 0);
    }
}
