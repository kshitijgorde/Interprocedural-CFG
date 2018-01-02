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
import java.beans.PropertyChangeListener;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.SortedList;
import javax.swing.Icon;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import ca.odell.glazedlists.gui.AbstractTableComparatorChooser;

public class TableComparatorChooser extends AbstractTableComparatorChooser
{
    private TableComparatorChooser$SortArrowHeaderRenderer h;
    private final TableComparatorChooser$TableHeaderUIHandler i;
    private final TableComparatorChooser$TableModelHandler j;
    private JTable k;
    private ActionListener l;
    private static Icon[] m;
    private final TableComparatorChooser$HeaderClickHandler n;
    
    protected TableComparatorChooser(final JTable k, final SortedList list, final Object o, final TableFormat tableFormat) {
        super(list, tableFormat);
        this.i = new TableComparatorChooser$TableHeaderUIHandler(this, null);
        this.j = new TableComparatorChooser$TableModelHandler(this, null);
        a(o);
        (this.k = k).addPropertyChangeListener("model", this.j);
        this.k.getTableHeader().addPropertyChangeListener("UI", this.i);
        this.d();
        k.getModel().addTableModelListener(this.j);
        this.n = new TableComparatorChooser$HeaderClickHandler(this, k, (SortingStrategy)o);
    }
    
    private void d() {
        final TableCellRenderer defaultRenderer = this.k.getTableHeader().getDefaultRenderer();
        final Class<? extends TableCellRenderer> clazz = (defaultRenderer == null) ? null : defaultRenderer.getClass();
        if (clazz != TableComparatorChooser$SortArrowHeaderRenderer.class && clazz != null) {
            this.h = new TableComparatorChooser$SortArrowHeaderRenderer(this, defaultRenderer);
            this.k.getTableHeader().setDefaultRenderer(this.h);
        }
    }
    
    public static TableComparatorChooser a(final JTable table, final SortedList list, final Object o) {
        return a(table, list, o, ((EventTableModel)table.getModel()).a());
    }
    
    public static TableComparatorChooser a(final JTable table, final SortedList list, final Object o, final TableFormat tableFormat) {
        return new TableComparatorChooser(table, list, o, tableFormat);
    }
    
    private static void a(final Object o) {
        if (!(o instanceof SortingStrategy)) {
            throw new IllegalArgumentException("Unrecognized sorting strategy, \"" + o + "\", use one of AbstractTableComparatorChooser.SINGLE_COLUMN, AbstractTableComparatorChooser.MULTIPLE_COLUMN_MOUSE, or AbstractTableComparatorChooser.MULTIPLE_COLUMN_KEYBOARD");
        }
    }
    
    protected void a(final Comparator comparator) {
        super.a(comparator);
        this.k.getTableHeader().revalidate();
        this.k.getTableHeader().repaint();
    }
    
    protected final void b() {
        super.b();
        this.k.getTableHeader().revalidate();
        this.k.getTableHeader().repaint();
        if (this.l != null) {
            this.l.actionPerformed(new ActionEvent(this, 0, "sort"));
        }
    }
    
    protected final int a(final int n) {
        return super.a(this.k.convertColumnIndexToModel(n));
    }
    
    protected boolean a(final MouseEvent mouseEvent) {
        return mouseEvent.getButton() == 1;
    }
    
    static {
        TableComparatorChooser.m = SortIconFactory.a();
    }
}
