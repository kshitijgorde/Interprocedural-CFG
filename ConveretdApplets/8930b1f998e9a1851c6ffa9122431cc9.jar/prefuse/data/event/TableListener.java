// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.event;

import prefuse.data.Table;
import java.util.EventListener;

public interface TableListener extends EventListener
{
    void tableChanged(final Table p0, final int p1, final int p2, final int p3, final int p4);
}
