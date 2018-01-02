// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.util;

import prefuse.data.CascadedTable;
import prefuse.data.Table;

public class CascadedRowManager extends FilteredRowManager
{
    public CascadedRowManager(final Table table) {
        super(table);
    }
    
    public int getColumnRow(final int n, final int n2) {
        if (!this.isValidRow(n)) {
            return -1;
        }
        if (n2 >= ((CascadedTable)this.getTable()).getLocalColumnCount()) {
            return ((CascadedTable)this.m_table).getParentTable().getColumnRow(this.getParentRow(n), n2);
        }
        return n;
    }
    
    public int getTableRow(final int n, final int n2) {
        int childRow;
        if (n2 < ((CascadedTable)this.getTable()).getLocalColumnCount()) {
            childRow = n;
        }
        else {
            childRow = this.getChildRow(n);
        }
        return this.isValidRow(childRow) ? childRow : -1;
    }
}
