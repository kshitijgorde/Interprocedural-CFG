// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature;

import org.xmodel.xpath.expression.StatefulContext;

public interface IWidgetContextFeature
{
    void createAssociation(final Object p0, final StatefulContext p1);
    
    void removeAssociation(final Object p0);
    
    StatefulContext getContext(final Object p0);
}
