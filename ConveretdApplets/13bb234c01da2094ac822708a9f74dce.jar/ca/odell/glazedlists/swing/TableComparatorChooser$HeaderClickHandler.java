// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.swing;

import ca.odell.glazedlists.impl.SortIconFactory;
import ca.odell.glazedlists.impl.gui.SortingState;
import java.awt.event.ActionEvent;
import java.util.Comparator;
import javax.swing.table.TableCellRenderer;
import javax.swing.event.TableModelListener;
import java.beans.PropertyChangeListener;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.SortedList;
import javax.swing.Icon;
import java.awt.event.ActionListener;
import ca.odell.glazedlists.gui.AbstractTableComparatorChooser;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import ca.odell.glazedlists.impl.gui.SortingStrategy;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;

class TableComparatorChooser$HeaderClickHandler extends MouseAdapter
{
    private final JTable b;
    private final SortingStrategy c;
    private boolean d;
    final /* synthetic */ TableComparatorChooser a;
    
    public TableComparatorChooser$HeaderClickHandler(final TableComparatorChooser a, final JTable b, final SortingStrategy c) {
        this.a = a;
        this.d = false;
        this.b = b;
        this.c = c;
        b.getTableHeader().addMouseListener(this);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.d) {
            return;
        }
        if (this.b.getTableHeader().getCursor() == Cursor.getPredefinedCursor(11)) {
            return;
        }
        if (!this.a.a(mouseEvent)) {
            return;
        }
        final int convertColumnIndexToModel = this.b.convertColumnIndexToModel(this.b.getColumnModel().getColumnIndexAtX(mouseEvent.getX()));
        final int clickCount = mouseEvent.getClickCount();
        if (clickCount >= 1 && convertColumnIndexToModel != -1) {
            this.c.a(this.a.g, convertColumnIndexToModel, clickCount, mouseEvent.isShiftDown(), mouseEvent.isControlDown() || mouseEvent.isMetaDown());
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.d = mouseEvent.isPopupTrigger();
    }
}
