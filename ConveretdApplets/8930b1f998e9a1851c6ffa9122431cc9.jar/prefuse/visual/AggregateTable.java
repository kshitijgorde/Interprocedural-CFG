// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.visual;

import java.util.HashSet;
import java.util.Iterator;
import prefuse.util.collections.IntIterator;
import prefuse.data.util.Index;
import prefuse.data.Tuple;
import prefuse.visual.tuple.TableAggregateItem;
import prefuse.Visualization;
import prefuse.data.Schema;
import prefuse.data.Table;

public class AggregateTable extends VisualTable
{
    protected Table m_aggregated;
    protected static final String AGGREGATE = "aggregate";
    protected static final String MEMBER_HASH = "hash";
    protected static final String MEMBER = "member";
    protected static final Schema AGGREGATED_SCHEMA;
    
    public AggregateTable(final Visualization visualization, final String s) {
        this(visualization, s, VisualItem.SCHEMA);
    }
    
    public AggregateTable(final Visualization visualization, final String s, final Schema schema) {
        super(visualization, s, schema, TableAggregateItem.class);
        (this.m_aggregated = AggregateTable.AGGREGATED_SCHEMA.instantiate()).index("aggregate");
        this.m_aggregated.index("hash");
    }
    
    public int getAggregateSize(final int n) {
        int n2 = 0;
        final AggregatedIterator aggregatedIterator = new AggregatedIterator(n);
        while (aggregatedIterator.hasNext()) {
            ++n2;
            aggregatedIterator.next();
        }
        return n2;
    }
    
    public void addToAggregate(final int n, final VisualItem visualItem) {
        this.validRowCheck(n, true);
        if (!this.aggregateContains(n, visualItem)) {
            final int addRow = this.m_aggregated.addRow();
            this.m_aggregated.setInt(addRow, "aggregate", n);
            this.m_aggregated.setInt(addRow, "hash", this.getHashCode(visualItem));
            this.m_aggregated.set(addRow, "member", visualItem);
            this.fireTableEvent(n, n, -1, 0);
        }
    }
    
    public void removeFromAggregate(final int n, final VisualItem visualItem) {
        this.validRowCheck(n, true);
        final int aggregatedRow = this.getAggregatedRow(n, visualItem);
        if (aggregatedRow >= 0) {
            this.m_aggregated.removeRow(aggregatedRow);
            this.fireTableEvent(n, n, -1, 0);
        }
    }
    
    public void removeAllFromAggregate(final int n) {
        this.clearAggregateMappings(n, true);
    }
    
    protected void clearAggregateMappings(final int n, final boolean b) {
        final Index index = this.m_aggregated.index("aggregate");
        boolean b2 = false;
        final IntIterator rows = index.rows(n);
        while (rows.hasNext()) {
            final int nextInt = rows.nextInt();
            rows.remove();
            this.m_aggregated.removeRow(nextInt);
            b2 = true;
        }
        if (b && b2) {
            this.fireTableEvent(n, n, -1, 0);
        }
    }
    
    public boolean aggregateContains(final int n, final VisualItem visualItem) {
        return this.getAggregatedRow(n, visualItem) >= 0;
    }
    
    protected int getAggregatedRow(final int n, final VisualItem visualItem) {
        final Index index = this.m_aggregated.index("hash");
        final int hashCode = this.getHashCode(visualItem);
        final int value = index.get(hashCode);
        if (value < 0) {
            return -1;
        }
        if (this.m_aggregated.getInt(value, "aggregate") == n) {
            return value;
        }
        final IntIterator rows = index.rows(hashCode);
        while (rows.hasNext()) {
            final int nextInt = rows.nextInt();
            if (this.m_aggregated.getInt(nextInt, "aggregate") == n) {
                return nextInt;
            }
        }
        return -1;
    }
    
    public Iterator aggregatedTuples(final int n) {
        return new AggregatedIterator(n);
    }
    
    public Iterator getAggregates(final Tuple tuple) {
        final IntIterator rows = this.m_aggregated.getIndex("hash").rows(this.getHashCode(tuple));
        final HashSet<Tuple> set = new HashSet<Tuple>();
        while (rows.hasNext()) {
            set.add(this.getTuple(this.m_aggregated.getInt(rows.nextInt(), "aggregate")));
        }
        return set.iterator();
    }
    
    protected int getHashCode(final Tuple tuple) {
        return tuple.hashCode();
    }
    
    protected boolean validRowCheck(final int n, final boolean b) {
        if (this.isValidRow(n)) {
            return true;
        }
        if (b) {
            throw new IllegalArgumentException("Invalid row value: " + n);
        }
        return false;
    }
    
    protected void fireTableEvent(final int n, final int n2, final int n3, final int n4) {
        if (n3 == -1 && n4 == -1) {
            for (int i = n; i <= n2; ++i) {
                this.clearAggregateMappings(i, false);
            }
        }
        super.fireTableEvent(n, n2, n3, n4);
    }
    
    static {
        (AGGREGATED_SCHEMA = new Schema()).addColumn("aggregate", Integer.TYPE);
        AggregateTable.AGGREGATED_SCHEMA.addColumn("hash", Integer.TYPE);
        AggregateTable.AGGREGATED_SCHEMA.addColumn("member", Tuple.class);
    }
    
    protected class AggregatedIterator implements Iterator
    {
        private IntIterator m_rows;
        private Tuple m_next;
        
        public AggregatedIterator(final int n) {
            this.m_next = null;
            this.m_rows = AggregateTable.this.m_aggregated.index("aggregate").rows(n);
            this.advance();
        }
        
        public boolean hasNext() {
            return this.m_next != null;
        }
        
        public Object next() {
            final Tuple next = this.m_next;
            this.advance();
            return next;
        }
        
        private void advance() {
            while (this.m_rows.hasNext()) {
                final int nextInt = this.m_rows.nextInt();
                final Tuple next = (Tuple)AggregateTable.this.m_aggregated.get(nextInt, "member");
                if (next.isValid()) {
                    this.m_next = next;
                    return;
                }
                AggregateTable.this.m_aggregated.removeRow(nextInt);
            }
            this.m_next = null;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
