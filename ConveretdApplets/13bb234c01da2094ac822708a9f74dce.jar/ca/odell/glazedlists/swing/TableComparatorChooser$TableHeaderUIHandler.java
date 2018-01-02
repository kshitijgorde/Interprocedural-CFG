// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.swing;

import ca.odell.glazedlists.impl.SortIconFactory;
import ca.odell.glazedlists.impl.gui.SortingState;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.util.Comparator;
import javax.swing.table.TableCellRenderer;
import ca.odell.glazedlists.impl.gui.SortingStrategy;
import javax.swing.event.TableModelListener;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.SortedList;
import javax.swing.Icon;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import ca.odell.glazedlists.gui.AbstractTableComparatorChooser;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

class TableComparatorChooser$TableHeaderUIHandler implements PropertyChangeListener
{
    final /* synthetic */ TableComparatorChooser a;
    
    private TableComparatorChooser$TableHeaderUIHandler(final TableComparatorChooser a) {
        this.a = a;
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        this.a.d();
    }
}
