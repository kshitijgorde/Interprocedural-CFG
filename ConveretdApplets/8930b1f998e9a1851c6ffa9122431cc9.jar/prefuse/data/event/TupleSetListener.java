// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.event;

import prefuse.data.Tuple;
import prefuse.data.tuple.TupleSet;
import java.util.EventListener;

public interface TupleSetListener extends EventListener
{
    void tupleSetChanged(final TupleSet p0, final Tuple[] p1, final Tuple[] p2);
}
