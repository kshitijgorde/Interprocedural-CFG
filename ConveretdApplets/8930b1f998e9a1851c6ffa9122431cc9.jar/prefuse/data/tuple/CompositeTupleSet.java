// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.tuple;

import prefuse.data.expression.parser.ExpressionParser;
import prefuse.data.expression.Expression;
import prefuse.data.expression.Predicate;
import prefuse.util.collections.CompositeIterator;
import prefuse.data.Table;
import prefuse.data.Tuple;
import java.util.Iterator;
import prefuse.data.event.TupleSetListener;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Map;
import java.util.logging.Logger;

public class CompositeTupleSet extends AbstractTupleSet
{
    private static final Logger s_logger;
    private Map m_map;
    private Set m_sets;
    private int m_count;
    private Listener m_lstnr;
    
    public CompositeTupleSet() {
        this(true);
    }
    
    protected CompositeTupleSet(final boolean b) {
        this.m_map = new LinkedHashMap();
        this.m_sets = new HashSet();
        this.m_count = 0;
        this.m_lstnr = (b ? new Listener() : null);
    }
    
    public void addSet(final String s, final TupleSet set) {
        if (this.hasSet(s)) {
            throw new IllegalArgumentException("Name already in use: " + s);
        }
        this.m_map.put(s, set);
        this.m_sets.add(set);
        this.m_count += set.getTupleCount();
        if (this.m_lstnr != null) {
            set.addTupleSetListener(this.m_lstnr);
        }
    }
    
    public boolean hasSet(final String s) {
        return this.m_map.containsKey(s);
    }
    
    public boolean containsSet(final TupleSet set) {
        return this.m_sets.contains(set);
    }
    
    public TupleSet getSet(final String s) {
        return this.m_map.get(s);
    }
    
    public Iterator setNames() {
        return this.m_map.keySet().iterator();
    }
    
    public Iterator sets() {
        return this.m_map.values().iterator();
    }
    
    public TupleSet removeSet(final String s) {
        final TupleSet set = this.m_map.remove(s);
        if (set != null) {
            this.m_sets.remove(set);
            if (this.m_lstnr != null) {
                set.removeTupleSetListener(this.m_lstnr);
            }
        }
        return set;
    }
    
    public void removeAllSets() {
        final Iterator<Map.Entry<K, TupleSet>> iterator = this.m_map.entrySet().iterator();
        while (iterator.hasNext()) {
            final TupleSet set = iterator.next().getValue();
            iterator.remove();
            this.m_sets.remove(set);
            if (this.m_lstnr != null) {
                set.removeTupleSetListener(this.m_lstnr);
            }
        }
        this.m_count = 0;
    }
    
    public void clear() {
        final Iterator<Map.Entry<K, TupleSet>> iterator = this.m_map.entrySet().iterator();
        while (iterator.hasNext()) {
            iterator.next().getValue().clear();
        }
        this.m_count = 0;
    }
    
    public Tuple addTuple(final Tuple tuple) {
        throw new UnsupportedOperationException();
    }
    
    public Tuple setTuple(final Tuple tuple) {
        throw new UnsupportedOperationException();
    }
    
    public boolean removeTuple(final Tuple tuple) {
        final Table table = tuple.getTable();
        return this.m_sets.contains(table) && table.removeTuple(tuple);
    }
    
    public boolean containsTuple(final Tuple tuple) {
        final Iterator<Map.Entry<K, TupleSet>> iterator = this.m_map.entrySet().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getValue().containsTuple(tuple)) {
                return true;
            }
        }
        return false;
    }
    
    public int getTupleCount() {
        if (this.m_lstnr != null) {
            return this.m_count;
        }
        int n = 0;
        final Iterator<Map.Entry<K, TupleSet>> iterator = this.m_map.entrySet().iterator();
        int n2 = 0;
        while (iterator.hasNext()) {
            n += iterator.next().getValue().getTupleCount();
            ++n2;
        }
        return n;
    }
    
    public Iterator tuples() {
        final CompositeIterator compositeIterator = new CompositeIterator(this.m_map.size());
        final Iterator<Map.Entry<K, TupleSet>> iterator = this.m_map.entrySet().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            compositeIterator.setIterator(n, iterator.next().getValue().tuples());
            ++n;
        }
        return compositeIterator;
    }
    
    public Iterator tuples(final Predicate predicate) {
        final CompositeIterator compositeIterator = new CompositeIterator(this.m_map.size());
        final Iterator<Map.Entry<K, TupleSet>> iterator = this.m_map.entrySet().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            compositeIterator.setIterator(n, iterator.next().getValue().tuples(predicate));
            ++n;
        }
        return compositeIterator;
    }
    
    public boolean isAddColumnSupported() {
        return true;
    }
    
    public void addColumn(final String s, final Class clazz, final Object o) {
        for (final Map.Entry<K, TupleSet> entry : this.m_map.entrySet()) {
            final TupleSet set = entry.getValue();
            if (set.isAddColumnSupported()) {
                try {
                    set.addColumn(s, clazz, o);
                }
                catch (IllegalArgumentException ex) {}
            }
            else {
                CompositeTupleSet.s_logger.fine("Skipped addColumn for " + entry.getKey());
            }
        }
    }
    
    public void addColumn(final String s, final Class clazz) {
        for (final Map.Entry<K, TupleSet> entry : this.m_map.entrySet()) {
            final TupleSet set = entry.getValue();
            if (set.isAddColumnSupported()) {
                try {
                    set.addColumn(s, clazz);
                }
                catch (IllegalArgumentException ex) {}
            }
            else {
                CompositeTupleSet.s_logger.fine("Skipped addColumn for " + entry.getKey());
            }
        }
    }
    
    public void addColumn(final String s, final Expression expression) {
        for (final Map.Entry<K, TupleSet> entry : this.m_map.entrySet()) {
            final TupleSet set = entry.getValue();
            if (set.isAddColumnSupported()) {
                try {
                    set.addColumn(s, expression);
                }
                catch (IllegalArgumentException ex) {}
            }
            else {
                CompositeTupleSet.s_logger.fine("Skipped addColumn for " + entry.getKey());
            }
        }
    }
    
    public void addColumn(final String s, final String s2) {
        final Expression parse = ExpressionParser.parse(s2);
        final Throwable error = ExpressionParser.getError();
        if (error != null) {
            throw new RuntimeException(error);
        }
        this.addColumn(s, parse);
    }
    
    static {
        s_logger = Logger.getLogger(CompositeTupleSet.class.getName());
    }
    
    private class Listener implements TupleSetListener
    {
        public void tupleSetChanged(final TupleSet set, final Tuple[] array, final Tuple[] array2) {
            CompositeTupleSet.this.m_count += array.length - array2.length;
            CompositeTupleSet.this.fireTupleEvent(array, array2);
        }
    }
}
