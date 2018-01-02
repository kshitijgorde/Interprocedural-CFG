// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction.trigger;

import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.XActionDocument;

public interface ITrigger
{
    void configure(final XActionDocument p0);
    
    XActionDocument getDocument();
    
    void activate(final IContext p0);
    
    void deactivate(final IContext p0);
}
