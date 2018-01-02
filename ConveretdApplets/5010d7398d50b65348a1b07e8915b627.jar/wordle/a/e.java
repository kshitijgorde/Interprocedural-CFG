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
import javax.swing.event.TableModelListener;
import java.awt.event.MouseListener;
import java.util.Comparator;
import javax.swing.table.TableModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.JTableHeader;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class e extends MouseAdapter
{
    private /* synthetic */ i a;
    
    private e(final i a, final byte b) {
        this.a = a;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        final TableColumnModel columnModel = ((JTableHeader)mouseEvent.getSource()).getColumnModel();
        final int modelIndex;
        if ((modelIndex = columnModel.getColumn(columnModel.getColumnIndexAtX(mouseEvent.getX())).getModelIndex()) != -1) {
            final int a = this.a.a(modelIndex);
            if (!mouseEvent.isControlDown()) {
                i.c(this.a);
            }
            this.a.a(modelIndex, (a + (mouseEvent.isShiftDown() ? -1 : 1) + 4) % 3 - 1);
        }
    }
}
