// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.table;

import java.util.EventObject;

public class ColumnSortEvent extends EventObject
{
    private int columnIndex;
    private boolean ascending;
    private boolean multisort;
    private int[] columnTypes;
    private int[] columnIndexes;
    private boolean[] ascendingFlags;
    private String[] sDateFormats;
    private int columnType;
    private String m_sDateFormat;
    
    public ColumnSortEvent(final Object source, final int columnIndex, final boolean ascending, final int columnType, final String sDateFormat) {
        super(source);
        this.multisort = false;
        this.columnType = 1;
        this.m_sDateFormat = "MM/dd/yyyy";
        this.multisort = false;
        this.columnType = columnType;
        this.columnIndex = columnIndex;
        this.ascending = ascending;
        this.m_sDateFormat = sDateFormat;
    }
    
    public ColumnSortEvent(final Object source, final int[] columnIndexes, final int[] columnTypes, final boolean[] ascending, final String[] sDateFormats) {
        super(source);
        this.multisort = false;
        this.columnType = 1;
        this.m_sDateFormat = "MM/dd/yyyy";
        this.multisort = true;
        this.columnIndexes = columnIndexes;
        this.columnTypes = columnTypes;
        this.ascendingFlags = ascending;
        this.sDateFormats = sDateFormats;
    }
    
    public int[] getColumnTypes() {
        return this.columnTypes;
    }
    
    public String[] getDateFormats() {
        return this.sDateFormats;
    }
    
    public int[] getColumnIndexes() {
        return this.columnIndexes;
    }
    
    public boolean[] getAscendingFlags() {
        return this.ascendingFlags;
    }
    
    public boolean getMultisort() {
        return this.multisort;
    }
    
    public int getColumnType() {
        return this.columnType;
    }
    
    public String getDateFormat() {
        return this.m_sDateFormat;
    }
    
    public ColumnSortEvent(final Object source, final int columnIndex, final boolean ascending) {
        super(source);
        this.multisort = false;
        this.columnType = 1;
        this.m_sDateFormat = "MM/dd/yyyy";
        if (this.multisort) {
            this.ascending = this.ascendingFlags[columnIndex];
        }
        else {
            this.ascending = ascending;
        }
        this.multisort = false;
        this.columnIndex = columnIndex;
    }
    
    public boolean getAscending() {
        return this.ascending;
    }
    
    public int getColumnIndex() {
        return this.columnIndex;
    }
}
