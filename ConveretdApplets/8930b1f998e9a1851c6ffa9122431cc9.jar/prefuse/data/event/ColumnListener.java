// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.event;

import prefuse.data.column.Column;
import java.util.EventListener;

public interface ColumnListener extends EventListener
{
    void columnChanged(final Column p0, final int p1, final int p2, final int p3);
    
    void columnChanged(final Column p0, final int p1, final int p2);
    
    void columnChanged(final Column p0, final int p1, final long p2);
    
    void columnChanged(final Column p0, final int p1, final float p2);
    
    void columnChanged(final Column p0, final int p1, final double p2);
    
    void columnChanged(final Column p0, final int p1, final boolean p2);
    
    void columnChanged(final Column p0, final int p1, final Object p2);
}
