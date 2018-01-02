// 
// Decompiled by Procyon v0.5.30
// 

package wordle.a;

import java.util.Arrays;
import javax.swing.Icon;
import javax.swing.table.TableCellRenderer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.MouseListener;
import javax.swing.table.JTableHeader;
import java.util.Comparator;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

final class j implements TableModelListener
{
    private /* synthetic */ i a;
    
    private j(final i a, final byte b) {
        this.a = a;
    }
    
    public final void tableChanged(final TableModelEvent tableModelEvent) {
        if (!this.a.a()) {
            this.a.b();
            this.a.fireTableChanged(tableModelEvent);
            return;
        }
        if (tableModelEvent.getFirstRow() == -1) {
            i.c(this.a);
            this.a.fireTableChanged(tableModelEvent);
            return;
        }
        final int column = tableModelEvent.getColumn();
        if (tableModelEvent.getFirstRow() == tableModelEvent.getLastRow() && column != -1 && this.a.a(column) == 0 && this.a.f != null) {
            final int n = i.e(this.a)[tableModelEvent.getFirstRow()];
            this.a.fireTableChanged(new TableModelEvent(this.a, n, n, column, tableModelEvent.getType()));
            return;
        }
        this.a.b();
        this.a.fireTableDataChanged();
    }
}
