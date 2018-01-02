// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding;

import org.xmodel.xpath.expression.IExpressionListener;
import org.xidget.config.TagProcessor;
import org.xmodel.IModelObject;
import org.xidget.IXidget;

public interface IBindingRule
{
    boolean applies(final IXidget p0, final IModelObject p1);
    
    IExpressionListener getListener(final TagProcessor p0, final IXidget p1, final IModelObject p2);
}
