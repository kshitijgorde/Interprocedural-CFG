// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature.tree;

import org.xidget.tree.Row;

public interface ITreeExpandFeature
{
    void expand(final Row p0);
    
    void collapse(final Row p0);
    
    void rowAdded(final Row p0);
    
    void rowRemoved(final Row p0);
}
