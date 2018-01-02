// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui.about;

import javax.swing.table.TableColumnModel;
import org.jfree.ui.SortableTableModel;
import org.jfree.ui.SortableTable;

public class SystemProperties
{
    public static SortableTable createSystemPropertiesTable() {
        final SortableTable sortableTable = new SortableTable(new SystemPropertiesTableModel());
        final TableColumnModel columnModel = sortableTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(200);
        columnModel.getColumn(1).setPreferredWidth(350);
        sortableTable.setAutoResizeMode(2);
        return sortableTable;
    }
}
