// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.util;

import prefuse.util.collections.IntIterator;
import prefuse.util.collections.IntIntTreeMap;
import prefuse.util.collections.IntIntSortedMap;
import prefuse.data.Table;

public class RowManager
{
    protected Table m_table;
    private IntIntSortedMap m_openrows;
    private int m_firstid;
    private int m_curid;
    
    public RowManager(final Table table) {
        this.m_firstid = 0;
        this.m_curid = -1;
        this.m_table = table;
    }
    
    public Table getTable() {
        return this.m_table;
    }
    
    public int getMinimumRow() {
        return this.m_firstid;
    }
    
    public int getMaximumRow() {
        return this.m_curid;
    }
    
    public int getRowCount() {
        return 1 + this.m_curid - this.m_firstid - ((this.m_openrows == null) ? 0 : this.m_openrows.size());
    }
    
    public boolean isValidRow(final int n) {
        return n >= this.m_firstid && n <= this.m_curid && (this.m_openrows == null || !this.m_openrows.containsKey(n));
    }
    
    public void clear() {
        this.m_openrows = null;
        this.m_firstid = 0;
        this.m_curid = -1;
    }
    
    public int addRow() {
        int remove;
        if (this.m_openrows == null || this.m_openrows.isEmpty()) {
            remove = ((this.m_firstid == 0) ? (++this.m_curid) : (--this.m_firstid));
        }
        else {
            remove = this.m_openrows.remove(this.m_openrows.firstKey());
        }
        return remove;
    }
    
    public boolean releaseRow(final int n) {
        if (n < 0) {
            return false;
        }
        if (this.m_openrows != null && this.m_openrows.containsKey(n)) {
            return false;
        }
        if (n == this.m_curid) {
            --this.m_curid;
        }
        else if (n == this.m_firstid) {
            ++this.m_firstid;
        }
        else {
            if (this.m_openrows == null) {
                this.m_openrows = new IntIntTreeMap(false);
            }
            this.m_openrows.put(n, n);
        }
        return true;
    }
    
    public int getColumnRow(final int n, final int n2) {
        return this.isValidRow(n) ? n : -1;
    }
    
    public int getTableRow(final int n, final int n2) {
        return this.isValidRow(n) ? n : -1;
    }
    
    public IntIterator columnRows(final int n) {
        return new ColumnRowIterator(this.rows(), n);
    }
    
    public IntIterator columnRows(final int n, final boolean b) {
        return new ColumnRowIterator(this.rows(b), n);
    }
    
    public IntIterator columnRows(final IntIterator intIterator, final int n) {
        return new ColumnRowIterator(intIterator, n);
    }
    
    public IntIterator rows() {
        return new RowIterator(false);
    }
    
    public IntIterator rows(final boolean b) {
        return new RowIterator(b);
    }
    
    public class ColumnRowIterator extends IntIterator
    {
        private IntIterator rows;
        private int row;
        private int col;
        
        public ColumnRowIterator(final IntIterator rows, final int col) {
            this.rows = rows;
            this.col = col;
        }
        
        public boolean hasNext() {
            return this.rows.hasNext();
        }
        
        public int nextInt() {
            this.row = this.rows.nextInt();
            return RowManager.this.getColumnRow(this.row, this.col);
        }
        
        public void remove() {
            RowManager.this.m_table.removeRow(this.row);
        }
    }
    
    public class RowIterator extends IntIterator
    {
        boolean reverse;
        int last;
        int next;
        
        public RowIterator(final boolean reverse) {
            this.last = -1;
            this.reverse = reverse;
            this.next = this.advance(reverse ? RowManager.this.m_curid : RowManager.this.m_firstid);
        }
        
        public boolean hasNext() {
            return this.reverse ? (this.next >= 0) : (this.next <= RowManager.this.m_curid);
        }
        
        public int nextInt() {
            this.last = this.next;
            this.next = this.advance(this.reverse ? (--this.next) : (++this.next));
            return this.last;
        }
        
        public void remove() {
            RowManager.this.m_table.removeRow(this.last);
        }
        
        private final int advance(int n) {
            if (RowManager.this.m_openrows == null) {
                return n;
            }
            if (this.reverse) {
                while (n >= 0 && RowManager.this.m_openrows.containsKey(n)) {
                    --n;
                }
            }
            else {
                while (n <= RowManager.this.m_curid && RowManager.this.m_openrows.containsKey(n)) {
                    ++n;
                }
            }
            return n;
        }
    }
}
