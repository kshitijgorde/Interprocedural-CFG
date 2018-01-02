// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature;

import org.xmodel.xpath.expression.StatefulContext;

public interface IDragAndDropFeature
{
    boolean isDragEnabled();
    
    boolean isDropEnabled();
    
    boolean canDrag(final StatefulContext p0);
    
    boolean canDrop(final StatefulContext p0);
    
    void drag(final StatefulContext p0);
    
    void drop(final StatefulContext p0);
}
