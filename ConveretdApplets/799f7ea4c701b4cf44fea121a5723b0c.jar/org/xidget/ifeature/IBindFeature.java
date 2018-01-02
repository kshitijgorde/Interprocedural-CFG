// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature;

import java.util.List;
import org.xmodel.xpath.expression.StatefulContext;
import org.xidget.binding.IXidgetBinding;

public interface IBindFeature
{
    void addBindingBeforeChildren(final IXidgetBinding p0);
    
    void addBindingAfterChildren(final IXidgetBinding p0);
    
    void remove(final IXidgetBinding p0);
    
    void bind(final StatefulContext p0);
    
    void unbind(final StatefulContext p0);
    
    List<StatefulContext> getBoundContexts();
    
    StatefulContext getBoundContext();
}
