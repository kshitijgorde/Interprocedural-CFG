// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.controlpanel;

import java.util.Iterator;
import java.util.Vector;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JTable;
import de.muntjak.tinylookandfeel.table.SortableTableData;

public class TinyTableModel extends NonSortableTableModel implements SortableTableData
{
    public TinyTableModel() {
        super(true);
    }
    
    public boolean isColumnSortable(final int n) {
        return !this.data.isEmpty() && this.getValueAt(0, n) instanceof Comparable;
    }
    
    public boolean supportsMultiColumnSort() {
        return true;
    }
    
    public void sortColumns(final int[] array, final int[] array2, final JTable table) {
        final int[] selectedRows = table.getSelectedRows();
        final int[] selectedColumns = table.getSelectedColumns();
        int n = 0;
        final Iterator<Record> iterator = this.data.iterator();
        while (iterator.hasNext()) {
            iterator.next().setOldRow(n++);
        }
        if (array.length == 0) {
            Collections.sort((List<Object>)this.data, new Comparator() {
                public int compare(final Object o, final Object o2) {
                    return ((Comparable)((Record)o).getValueAt(0)).compareTo(((Record)o2).getValueAt(0));
                }
            });
        }
        else {
            Collections.sort((List<Object>)this.data, new Comparator() {
                public int compare(final Object o, final Object o2) {
                    final Record record = (Record)o;
                    final Record record2 = (Record)o2;
                    int i = 0;
                    while (i < array.length) {
                        final int compareTo = ((Comparable)record.getValueAt(array[i])).compareTo(record2.getValueAt(array[i]));
                        if (compareTo != 0) {
                            if (array2[i] == 2) {
                                return -compareTo;
                            }
                            return compareTo;
                        }
                        else {
                            ++i;
                        }
                    }
                    return 0;
                }
            });
        }
        this.fireTableDataChanged();
        int n2 = 0;
        final Iterator<Record> iterator2 = this.data.iterator();
        while (iterator2.hasNext()) {
            iterator2.next().setNewRow(n2++);
        }
        final Vector vector = (Vector)this.data.clone();
        Collections.sort((List<Object>)vector, new Comparator() {
            public int compare(final Object o, final Object o2) {
                if (((Record)o).getOldRow() > ((Record)o2).getOldRow()) {
                    return 1;
                }
                return -1;
            }
        });
        for (int i = 0; i < selectedRows.length; ++i) {
            final int newRow = vector.get(selectedRows[i]).getNewRow();
            table.addRowSelectionInterval(newRow, newRow);
        }
        for (int j = 0; j < selectedColumns.length; ++j) {
            table.addColumnSelectionInterval(selectedColumns[j], selectedColumns[j]);
        }
    }
}
