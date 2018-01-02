// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.util;

import prefuse.data.event.ProjectionListener;
import prefuse.data.column.Column;

public interface ColumnProjection
{
    boolean include(final Column p0, final String p1);
    
    void addProjectionListener(final ProjectionListener p0);
    
    void removeProjectionListener(final ProjectionListener p0);
}
