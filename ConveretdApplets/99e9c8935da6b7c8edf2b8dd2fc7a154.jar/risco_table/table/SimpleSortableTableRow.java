// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.table;

import risco_table.TableCell.ITableCell;
import risco_table.util.ColumnSortable;

public class SimpleSortableTableRow extends SimpleTableRow implements ColumnSortable
{
    public SimpleSortableTableRow(final ITableCell[] strings) {
        super(strings);
    }
    
    public int compare(final Object object1, final Object object2, final int columnIndex, final int columnType, final String sDateFormat) {
        final int iRetVal = ((TableRow)object1).getStrings()[columnIndex].compare(((TableRow)object2).getStrings()[columnIndex]);
        return iRetVal;
    }
    
    public int compare(final Object object1, final Object object2, final int[] columnIndexes, final int[] columnTypes, final boolean[] ascending, final String[] sDateFormats) {
        int retVal = 0;
        for (int k = 0; k < columnIndexes.length; ++k) {
            if (k + 1 == columnIndexes.length) {
                retVal = this.compare(object1, object2, columnIndexes[k], columnTypes[k], sDateFormats[k]);
            }
            else {
                retVal = this.compareMulti(object1, object2, columnIndexes[k], columnTypes[k], sDateFormats[k]);
            }
            if (!ascending[k]) {
                retVal = 0 - retVal;
            }
            if (retVal != 0 || k == columnIndexes.length) {
                return retVal;
            }
        }
        return 0;
    }
    
    public int compareMulti(final Object object1, final Object object2, final int columnIndex, final int columnType, final String sDateFormat) {
        final int iRetVal = ((TableRow)object1).getStrings()[columnIndex].compareMulti(((TableRow)object2).getStrings()[columnIndex]);
        return iRetVal;
    }
}
