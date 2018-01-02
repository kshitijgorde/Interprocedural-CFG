// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.swing;

import ca.odell.glazedlists.impl.SortIconFactory;
import ca.odell.glazedlists.impl.gui.SortingState;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.util.Comparator;
import ca.odell.glazedlists.impl.gui.SortingStrategy;
import javax.swing.event.TableModelListener;
import java.beans.PropertyChangeListener;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.SortedList;
import java.awt.event.ActionListener;
import ca.odell.glazedlists.gui.AbstractTableComparatorChooser;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.Icon;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.plaf.UIResource;

class TableComparatorChooser$SortArrowHeaderRenderer implements UIResource, TableCellRenderer
{
    private TableCellRenderer b;
    final /* synthetic */ TableComparatorChooser a;
    
    public TableComparatorChooser$SortArrowHeaderRenderer(final TableComparatorChooser a, final TableCellRenderer b) {
        this.a = a;
        this.b = b;
    }
    
    public Component getTableCellRendererComponent(final JTable table, final Object o, final boolean b, final boolean b2, final int n, final int n2) {
        if (n2 < 0) {
            return this.a(table, o, b, b2, n, n2);
        }
        final Icon icon = TableComparatorChooser.m[this.a.a(n2)];
        Component component;
        if (this.b instanceof SortableRenderer) {
            ((SortableRenderer)this.b).a(icon);
            component = this.a(table, o, b, b2, n, n2);
        }
        else {
            component = this.a(table, o, b, b2, n, n2);
            if (component instanceof JLabel) {
                final JLabel label = (JLabel)component;
                label.setIcon(icon);
                label.setHorizontalTextPosition(10);
            }
        }
        return component;
    }
    
    private Component a(final JTable table, final Object o, final boolean b, final boolean b2, final int n, final int n2) {
        try {
            return this.b.getTableCellRendererComponent(table, o, b, b2, n, n2);
        }
        catch (RuntimeException ex) {
            this.b = new DefaultTableCellRenderer();
            return this.b.getTableCellRendererComponent(table, o, b, b2, n, n2);
        }
    }
}
