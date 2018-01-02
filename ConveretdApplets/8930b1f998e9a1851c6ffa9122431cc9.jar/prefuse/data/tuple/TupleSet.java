// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.tuple;

import java.beans.PropertyChangeListener;
import prefuse.data.event.TupleSetListener;
import prefuse.data.util.Sort;
import prefuse.data.expression.Predicate;
import java.util.Iterator;
import prefuse.data.expression.Expression;
import prefuse.data.Schema;
import prefuse.data.Tuple;

public interface TupleSet
{
    public static final Tuple[] EMPTY_ARRAY = new Tuple[0];
    
    Tuple addTuple(final Tuple p0);
    
    Tuple setTuple(final Tuple p0);
    
    boolean removeTuple(final Tuple p0);
    
    void clear();
    
    boolean containsTuple(final Tuple p0);
    
    int getTupleCount();
    
    boolean isAddColumnSupported();
    
    void addColumns(final Schema p0);
    
    void addColumn(final String p0, final Class p1);
    
    void addColumn(final String p0, final Class p1, final Object p2);
    
    void addColumn(final String p0, final String p1);
    
    void addColumn(final String p0, final Expression p1);
    
    Iterator tuples();
    
    Iterator tuples(final Predicate p0);
    
    Iterator tuples(final Predicate p0, final Sort p1);
    
    void addTupleSetListener(final TupleSetListener p0);
    
    void removeTupleSetListener(final TupleSetListener p0);
    
    void addPropertyChangeListener(final PropertyChangeListener p0);
    
    void addPropertyChangeListener(final String p0, final PropertyChangeListener p1);
    
    void removePropertyChangeListener(final PropertyChangeListener p0);
    
    void removePropertyChangeListener(final String p0, final PropertyChangeListener p1);
    
    void putClientProperty(final String p0, final Object p1);
    
    Object getClientProperty(final String p0);
}
