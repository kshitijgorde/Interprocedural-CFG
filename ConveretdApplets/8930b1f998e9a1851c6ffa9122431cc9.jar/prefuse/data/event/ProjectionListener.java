// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.event;

import prefuse.data.util.ColumnProjection;
import java.util.EventListener;

public interface ProjectionListener extends EventListener
{
    void projectionChanged(final ColumnProjection p0);
}
