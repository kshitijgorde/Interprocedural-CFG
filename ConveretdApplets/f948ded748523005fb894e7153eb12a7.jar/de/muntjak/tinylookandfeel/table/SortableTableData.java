// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.table;

import javax.swing.JTable;

public interface SortableTableData
{
    public static final int SORT_ASCENDING = 1;
    public static final int SORT_DESCENDING = 2;
    
    boolean isColumnSortable(final int p0);
    
    boolean supportsMultiColumnSort();
    
    void sortColumns(final int[] p0, final int[] p1, final JTable p2);
}
