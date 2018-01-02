// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.tuple;

import java.util.Iterator;
import prefuse.util.collections.IntIterator;
import prefuse.util.StringLib;
import java.util.logging.Logger;
import prefuse.data.Tuple;
import prefuse.data.Table;
import prefuse.data.Graph;

public class TupleManager
{
    protected Graph m_graph;
    protected Table m_table;
    protected Class m_tupleType;
    private TableTuple[] m_tuples;
    
    public TupleManager(final Table table, final Graph graph, final Class clazz) {
        this.init(table, graph, clazz);
    }
    
    public void init(final Table table, final Graph graph, final Class tupleType) {
        if (this.m_table != null) {
            throw new IllegalStateException("This TupleManager has already been initialized");
        }
        this.m_table = table;
        this.m_graph = graph;
        this.m_tupleType = tupleType;
        this.m_tuples = null;
    }
    
    public Class getTupleType() {
        return this.m_tupleType;
    }
    
    private void ensureTupleArray() {
        final int rowCount = this.m_table.getRowCount();
        if (this.m_tuples == null) {
            this.m_tuples = new TableTuple[rowCount];
        }
        else if (this.m_tuples.length < rowCount) {
            final TableTuple[] tuples = new TableTuple[Math.max(3 * this.m_tuples.length / 2 + 1, rowCount)];
            System.arraycopy(this.m_tuples, 0, tuples, 0, this.m_tuples.length);
            this.m_tuples = tuples;
        }
    }
    
    public Tuple getTuple(final int n) {
        if (!this.m_table.isValidRow(n)) {
            throw new IllegalArgumentException("Invalid row index: " + n);
        }
        this.ensureTupleArray();
        if (this.m_tuples[n] == null) {
            return this.m_tuples[n] = this.newTuple(n);
        }
        return this.m_tuples[n];
    }
    
    protected TableTuple newTuple(final int n) {
        try {
            final TableTuple tableTuple = this.m_tupleType.newInstance();
            tableTuple.init(this.m_table, this.m_graph, n);
            return tableTuple;
        }
        catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).warning(ex.getMessage() + "\n" + StringLib.getStackTrace(ex));
            return null;
        }
    }
    
    public void invalidate(final int n) {
        if (this.m_tuples == null || n < 0 || n >= this.m_tuples.length) {
            return;
        }
        if (this.m_tuples[n] != null) {
            this.m_tuples[n].invalidate();
            this.m_tuples[n] = null;
        }
    }
    
    public void invalidateAll() {
        if (this.m_tuples == null) {
            return;
        }
        for (int i = 0; i < this.m_tuples.length; ++i) {
            this.invalidate(i);
        }
    }
    
    public Iterator iterator(final IntIterator intIterator) {
        return new TupleManagerIterator(this, intIterator);
    }
    
    public class TupleManagerIterator implements Iterator
    {
        private TupleManager m_tuples;
        private IntIterator m_rows;
        
        public TupleManagerIterator(final TupleManager tuples, final IntIterator rows) {
            this.m_tuples = tuples;
            this.m_rows = rows;
        }
        
        public boolean hasNext() {
            return this.m_rows.hasNext();
        }
        
        public Object next() {
            return this.m_tuples.getTuple(this.m_rows.nextInt());
        }
        
        public void remove() {
            this.m_rows.remove();
        }
    }
}
