// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.util;

import risco_table.table.SimpleSortableTableRow;

public class ColumnSortableVector extends SortableVector
{
    int[] indexes;
    ColumnSortable compare;
    boolean ascending;
    private int m_iColumnType;
    private String m_sDateFormat;
    
    public synchronized void sort(final int columnIndex, final ColumnSortable comp, final int columnType, final String sDateFormat) {
        this.m_iColumnType = columnType;
        this.m_sDateFormat = sDateFormat;
        this.sort(columnIndex, 0, this.size() - 1, comp);
    }
    
    public synchronized void sort(final int[] columnIndexes, final ColumnSortable comp, final int[] columnTypes, final boolean[] ascending, final String[] sDateFormats) {
        this.sort(columnIndexes, columnTypes, ascending, sDateFormats, 0, this.size() - 1, comp);
    }
    
    public void sort(final int[] columnIndexes, final int[] columnTypes, final boolean[] ascending, final String[] sDateFormats, int start, final int end) {
        if (start >= end) {
            return;
        }
        this.swap(start, (start + end) / 2);
        this.compare = (SimpleSortableTableRow)super.elementData[0];
        if (this.indexes == null) {
            for (int i = start + 1; i <= end; ++i) {
                if (this.compare.compare(super.elementData[start], super.elementData[i], columnIndexes, columnTypes, ascending, sDateFormats) > 0) {
                    this.swap(++start, i);
                }
            }
        }
        else {
            for (int i = start + 1; i <= end; ++i) {
                if (this.compare.compare(super.elementData[this.indexes[start]], super.elementData[this.indexes[i]], columnIndexes, columnTypes, ascending, sDateFormats) > 0) {
                    this.swap(++start, i);
                }
            }
        }
        this.swap(start, start);
        this.sort(columnIndexes, columnTypes, ascending, sDateFormats, start, start - 1);
        this.sort(columnIndexes, columnTypes, ascending, sDateFormats, start + 1, end);
    }
    
    public synchronized void sort(final int[] columnIndexes, final int[] columnTypes, final boolean[] ascending, final String[] sDateFormats, final int start, final int end, final ColumnSortable comp) {
        this.sort(columnIndexes, columnTypes, ascending, sDateFormats, start, end, comp, null);
    }
    
    public synchronized void sort(final int[] columnIndexes, final int[] columnTypes, final boolean[] ascending, final String[] sDateFormats, final int start, final int end, final ColumnSortable comp, final int[] indexes) {
        this.compare = comp;
        this.indexes = indexes;
        if (indexes != null) {
            for (int i = start; i <= end; ++i) {
                indexes[i] = i;
            }
        }
        this.sort(columnIndexes, columnTypes, ascending, sDateFormats, start, end);
    }
    
    public synchronized void sort(final int[] columnIndexes, final int[] columnTypes, final boolean[] ascending, final String[] sDateFormats, final ColumnSortable comp) {
        this.sort(columnIndexes, columnTypes, ascending, sDateFormats, 0, this.size() - 1, comp);
    }
    
    public ColumnSortableVector() {
        this.m_iColumnType = 1;
        this.m_sDateFormat = "MM/dd/yyyy";
    }
    
    public ColumnSortableVector(final int initialCapacity) {
        super(initialCapacity);
        this.m_iColumnType = 1;
        this.m_sDateFormat = "MM/dd/yyyy";
    }
    
    public ColumnSortableVector(final int initialCapacity, final int capacityIncrement) {
        super(initialCapacity, capacityIncrement);
        this.m_iColumnType = 1;
        this.m_sDateFormat = "MM/dd/yyyy";
    }
    
    public boolean isAscending() {
        return this.ascending;
    }
    
    public void setAscending(final boolean ascending) {
        this.ascending = ascending;
    }
    
    public void sort(final int columnIndex, int start, final int end) {
        if (start >= end) {
            return;
        }
        this.swap(start, (start + end) / 2);
        if (this.indexes == null) {
            if (this.ascending) {
                for (int i = start + 1; i <= end; ++i) {
                    if (this.compare.compare(super.elementData[start], super.elementData[i], columnIndex, this.m_iColumnType, this.m_sDateFormat) > 0) {
                        this.swap(++start, i);
                    }
                }
            }
            else {
                for (int i = start + 1; i <= end; ++i) {
                    if (this.compare.compare(super.elementData[start], super.elementData[i], columnIndex, this.m_iColumnType, this.m_sDateFormat) < 0) {
                        this.swap(++start, i);
                    }
                }
            }
        }
        else if (this.ascending) {
            for (int i = start + 1; i <= end; ++i) {
                if (this.compare.compare(super.elementData[this.indexes[start]], super.elementData[this.indexes[i]], columnIndex, this.m_iColumnType, this.m_sDateFormat) > 0) {
                    this.swap(++start, i);
                }
            }
        }
        else {
            for (int i = start + 1; i <= end; ++i) {
                if (this.compare.compare(super.elementData[this.indexes[start]], super.elementData[this.indexes[i]], columnIndex, this.m_iColumnType, this.m_sDateFormat) < 0) {
                    this.swap(++start, i);
                }
            }
        }
        this.swap(start, start);
        this.sort(columnIndex, start, start - 1);
        this.sort(columnIndex, start + 1, end);
    }
    
    public synchronized void sort(final int columnIndex, final int start, final int end, final ColumnSortable comp) {
        this.sort(columnIndex, start, end, comp, null);
    }
    
    public synchronized void sort(final int columnIndex, final int start, final int end, final ColumnSortable comp, final int[] indexes) {
        this.compare = comp;
        this.indexes = indexes;
        if (indexes != null) {
            for (int i = start; i <= end; ++i) {
                indexes[i] = i;
            }
        }
        this.sort(columnIndex, start, end);
    }
    
    public synchronized void sort(final int columnIndex, final ColumnSortable comp) {
        this.sort(columnIndex, 0, this.size() - 1, comp);
    }
}
