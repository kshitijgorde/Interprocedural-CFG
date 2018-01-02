// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.event;

import prefuse.data.Graph;
import java.util.EventListener;

public interface GraphListener extends EventListener
{
    void graphChanged(final Graph p0, final String p1, final int p2, final int p3, final int p4, final int p5);
}
