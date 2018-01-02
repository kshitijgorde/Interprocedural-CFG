// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.util;

import prefuse.data.CascadedTable;
import prefuse.util.collections.IntIntTreeMap;
import prefuse.data.Table;
import prefuse.util.collections.IntIntSortedMap;
import prefuse.data.column.IntColumn;

public class FilteredRowManager extends RowManager
{
    protected IntColumn m_childToParent;
    protected IntIntSortedMap m_parentToChild;
    
    public FilteredRowManager(final Table table) {
        super(table);
        this.m_childToParent = new IntColumn(table.getRowCount());
        this.m_parentToChild = new IntIntTreeMap(false);
        this.clear();
    }
    
    public void clear() {
        super.clear();
        this.m_parentToChild.clear();
        for (int i = 0; i < this.m_childToParent.getRowCount(); ++i) {
            this.m_childToParent.setInt(-1, i);
        }
    }
    
    public int addRow(final int n) {
        final int addRow = super.addRow();
        this.put(addRow, n);
        return addRow;
    }
    
    public boolean releaseRow(final int n) {
        if (super.releaseRow(n)) {
            this.remove(n);
            return true;
        }
        return false;
    }
    
    public int getColumnRow(final int n, final int n2) {
        return ((CascadedTable)this.m_table).getParentTable().getColumnRow(this.getParentRow(n), n2);
    }
    
    public int getTableRow(final int n, final int n2) {
        return this.getChildRow(n);
    }
    
    public int getParentRow(final int n) {
        if (n >= this.m_childToParent.getRowCount()) {
            return -1;
        }
        return this.m_childToParent.getInt(n);
    }
    
    public int getChildRow(final int n) {
        final int value = this.m_parentToChild.get(n);
        return (value == Integer.MIN_VALUE) ? -1 : value;
    }
    
    public void put(final int n, final int n2) {
        if (n >= this.m_childToParent.getRowCount()) {
            this.m_childToParent.setMaximumRow(n + 1);
        }
        this.m_childToParent.setInt(n2, n);
        this.m_parentToChild.put(n2, n);
    }
    
    public void remove(final int n) {
        final int int1 = this.m_childToParent.getInt(n);
        this.m_childToParent.setInt(-1, n);
        this.m_parentToChild.remove(int1);
    }
}
