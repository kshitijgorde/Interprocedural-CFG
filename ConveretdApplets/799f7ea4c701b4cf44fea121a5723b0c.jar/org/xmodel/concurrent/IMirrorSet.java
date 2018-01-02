// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.concurrent;

import org.xmodel.IModelObject;

public interface IMirrorSet
{
    IModelObject get(final boolean p0);
    
    void attach();
    
    void detach();
}
