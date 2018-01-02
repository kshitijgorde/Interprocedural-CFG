// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.swing;

import ca.odell.glazedlists.impl.SortIconFactory;
import ca.odell.glazedlists.impl.gui.SortingState;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.table.TableCellRenderer;
import ca.odell.glazedlists.impl.gui.SortingStrategy;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.SortedList;
import javax.swing.Icon;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import ca.odell.glazedlists.gui.AbstractTableComparatorChooser;
import java.util.Comparator;
import javax.swing.event.TableModelEvent;
import java.beans.PropertyChangeEvent;
import javax.swing.event.TableModelListener;
import java.beans.PropertyChangeListener;

class TableComparatorChooser$TableModelHandler implements PropertyChangeListener, TableModelListener
{
    final /* synthetic */ TableComparatorChooser a;
    
    private TableComparatorChooser$TableModelHandler(final TableComparatorChooser a) {
        this.a = a;
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final EventTableModel eventTableModel = (propertyChangeEvent.getOldValue() instanceof EventTableModel) ? ((EventTableModel)propertyChangeEvent.getOldValue()) : null;
        final EventTableModel eventTableModel2 = (propertyChangeEvent.getNewValue() instanceof EventTableModel) ? ((EventTableModel)propertyChangeEvent.getNewValue()) : null;
        if (eventTableModel != null) {
            eventTableModel.removeTableModelListener(this);
        }
        if (eventTableModel2 != null) {
            eventTableModel2.addTableModelListener(this);
            this.a.a(eventTableModel2.a());
        }
    }
    
    public void tableChanged(final TableModelEvent tableModelEvent) {
        if (tableModelEvent.getFirstRow() == -1 && tableModelEvent.getColumn() == -1 && this.a.k.getModel() instanceof EventTableModel) {
            this.a.a(((EventTableModel)this.a.k.getModel()).a());
        }
        final Comparator d = this.a.e.d();
        if (d != this.a.f) {
            this.a.a(d);
        }
    }
}
