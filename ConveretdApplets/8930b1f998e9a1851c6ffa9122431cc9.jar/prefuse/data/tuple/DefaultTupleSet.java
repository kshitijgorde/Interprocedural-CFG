// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.tuple;

import java.util.Iterator;
import prefuse.data.Tuple;
import java.util.LinkedHashSet;
import prefuse.data.event.EventConstants;

public class DefaultTupleSet extends AbstractTupleSet implements EventConstants
{
    protected LinkedHashSet m_tuples;
    
    public DefaultTupleSet() {
        this.m_tuples = new LinkedHashSet();
    }
    
    public int getTupleCount() {
        return this.m_tuples.size();
    }
    
    public Tuple addTuple(Tuple addInternal) {
        addInternal = this.addInternal(addInternal);
        if (addInternal != null) {
            this.fireTupleEvent(addInternal, 1);
        }
        return addInternal;
    }
    
    public Tuple setTuple(Tuple addInternal) {
        final Tuple[] clearInternal = this.clearInternal();
        addInternal = this.addInternal(addInternal);
        final Tuple[] array;
        if (addInternal != null) {
            array = new Tuple[] { addInternal };
        }
        this.fireTupleEvent(array, clearInternal);
        return addInternal;
    }
    
    protected final Tuple addInternal(final Tuple tuple) {
        if (this.m_tuples.add(tuple)) {
            return tuple;
        }
        return null;
    }
    
    public boolean containsTuple(final Tuple tuple) {
        return this.m_tuples.contains(tuple);
    }
    
    public boolean removeTuple(final Tuple tuple) {
        final boolean removeInternal = this.removeInternal(tuple);
        if (removeInternal) {
            this.fireTupleEvent(tuple, -1);
        }
        return removeInternal;
    }
    
    protected final boolean removeInternal(final Tuple tuple) {
        return this.m_tuples.remove(tuple);
    }
    
    public void clear() {
        if (this.getTupleCount() > 0) {
            this.fireTupleEvent(null, this.clearInternal());
        }
    }
    
    public Tuple[] clearInternal() {
        final Tuple[] array = new Tuple[this.getTupleCount()];
        final Iterator tuples = this.tuples();
        int n = 0;
        while (tuples.hasNext()) {
            array[n] = tuples.next();
            ++n;
        }
        this.m_tuples.clear();
        return array;
    }
    
    public Iterator tuples() {
        return this.m_tuples.iterator();
    }
    
    public Tuple[] toArray() {
        final Tuple[] array = new Tuple[this.getTupleCount()];
        this.m_tuples.toArray(array);
        return array;
    }
}
