// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.search;

import prefuse.data.Tuple;
import java.util.Iterator;
import prefuse.data.tuple.DefaultTupleSet;

public abstract class SearchTupleSet extends DefaultTupleSet
{
    public abstract String getQuery();
    
    public abstract void search(final String p0);
    
    public void index(final Iterator iterator, final String s) {
        while (iterator.hasNext()) {
            this.index(iterator.next(), s);
        }
    }
    
    public abstract void index(final Tuple p0, final String p1);
    
    public abstract void unindex(final Tuple p0, final String p1);
    
    public abstract boolean isUnindexSupported();
    
    public Tuple addTuple(final Tuple tuple) {
        throw new UnsupportedOperationException();
    }
    
    public boolean removeTuple(final Tuple tuple) {
        throw new UnsupportedOperationException();
    }
}
