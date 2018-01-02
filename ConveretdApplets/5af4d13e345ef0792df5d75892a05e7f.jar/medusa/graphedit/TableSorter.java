// 
// Decompiled by Procyon v0.5.30
// 

package medusa.graphedit;

import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import javax.swing.table.TableColumnModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.event.TableModelEvent;
import java.util.Iterator;
import java.util.Arrays;
import javax.swing.Icon;
import javax.swing.table.TableCellRenderer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.event.TableModelListener;
import java.awt.event.MouseListener;
import javax.swing.table.JTableHeader;
import java.util.Comparator;
import javax.swing.table.TableModel;
import javax.swing.table.AbstractTableModel;

public class TableSorter extends AbstractTableModel
{
    protected TableModel tableModel;
    public static final int DESCENDING = -1;
    public static final int NOT_SORTED = 0;
    public static final int ASCENDING = 1;
    private static Directive EMPTY_DIRECTIVE;
    public static final Comparator COMPARABLE_COMAPRATOR;
    public static final Comparator LEXICAL_COMPARATOR;
    private Row[] viewToModel;
    private int[] modelToView;
    private JTableHeader tableHeader;
    private MouseListener mouseListener;
    private TableModelListener tableModelListener;
    private Map columnComparators;
    private List sortingColumns;
    
    public TableSorter() {
        this.columnComparators = new HashMap();
        this.sortingColumns = new ArrayList();
        this.mouseListener = new MouseHandler();
        this.tableModelListener = new TableModelHandler();
    }
    
    public TableSorter(final TableModel tableModel) {
        this();
        this.setTableModel(tableModel);
    }
    
    public TableSorter(final TableModel tableModel, final JTableHeader tableHeader) {
        this();
        this.setTableHeader(tableHeader);
        this.setTableModel(tableModel);
    }
    
    private void clearSortingState() {
        this.viewToModel = null;
        this.modelToView = null;
    }
    
    public TableModel getTableModel() {
        return this.tableModel;
    }
    
    public void setTableModel(final TableModel tableModel) {
        if (this.tableModel != null) {
            this.tableModel.removeTableModelListener(this.tableModelListener);
        }
        this.tableModel = tableModel;
        if (this.tableModel != null) {
            this.tableModel.addTableModelListener(this.tableModelListener);
        }
        this.clearSortingState();
        this.fireTableStructureChanged();
    }
    
    public JTableHeader getTableHeader() {
        return this.tableHeader;
    }
    
    public void setTableHeader(final JTableHeader tableHeader) {
        if (this.tableHeader != null) {
            this.tableHeader.removeMouseListener(this.mouseListener);
            final TableCellRenderer defaultRenderer = this.tableHeader.getDefaultRenderer();
            if (defaultRenderer instanceof SortableHeaderRenderer) {
                this.tableHeader.setDefaultRenderer(((SortableHeaderRenderer)defaultRenderer).tableCellRenderer);
            }
        }
        this.tableHeader = tableHeader;
        if (this.tableHeader != null) {
            this.tableHeader.addMouseListener(this.mouseListener);
            this.tableHeader.setDefaultRenderer(new SortableHeaderRenderer(this.tableHeader.getDefaultRenderer()));
        }
    }
    
    public boolean isSorting() {
        return this.sortingColumns.size() != 0;
    }
    
    private Directive getDirective(final int column) {
        for (int i = 0; i < this.sortingColumns.size(); ++i) {
            final Directive directive = this.sortingColumns.get(i);
            if (directive.column == column) {
                return directive;
            }
        }
        return TableSorter.EMPTY_DIRECTIVE;
    }
    
    public int getSortingStatus(final int column) {
        return this.getDirective(column).direction;
    }
    
    private void sortingStatusChanged() {
        this.clearSortingState();
        this.fireTableDataChanged();
        if (this.tableHeader != null) {
            this.tableHeader.repaint();
        }
    }
    
    public void setSortingStatus(final int column, final int status) {
        final Directive directive = this.getDirective(column);
        if (directive != TableSorter.EMPTY_DIRECTIVE) {
            this.sortingColumns.remove(directive);
        }
        if (status != 0) {
            this.sortingColumns.add(new Directive(column, status));
        }
        this.sortingStatusChanged();
    }
    
    protected Icon getHeaderRendererIcon(final int column, final int size) {
        final Directive directive = this.getDirective(column);
        if (directive == TableSorter.EMPTY_DIRECTIVE) {
            return null;
        }
        return new Arrow(directive.direction == -1, size, this.sortingColumns.indexOf(directive));
    }
    
    private void cancelSorting() {
        this.sortingColumns.clear();
        this.sortingStatusChanged();
    }
    
    public void setColumnComparator(final Class type, final Comparator comparator) {
        if (comparator == null) {
            this.columnComparators.remove(type);
        }
        else {
            this.columnComparators.put(type, comparator);
        }
    }
    
    protected Comparator getComparator(final int column) {
        final Class columnType = this.tableModel.getColumnClass(column);
        final Comparator comparator = this.columnComparators.get(columnType);
        if (comparator != null) {
            return comparator;
        }
        if (Comparable.class.isAssignableFrom(columnType)) {
            return TableSorter.COMPARABLE_COMAPRATOR;
        }
        return TableSorter.LEXICAL_COMPARATOR;
    }
    
    private Row[] getViewToModel() {
        if (this.viewToModel == null) {
            final int tableModelRowCount = this.tableModel.getRowCount();
            this.viewToModel = new Row[tableModelRowCount];
            for (int row = 0; row < tableModelRowCount; ++row) {
                this.viewToModel[row] = new Row(row);
            }
            if (this.isSorting()) {
                Arrays.sort(this.viewToModel);
            }
        }
        return this.viewToModel;
    }
    
    public int modelIndex(final int viewIndex) {
        return this.getViewToModel()[viewIndex].modelIndex;
    }
    
    private int[] getModelToView() {
        if (this.modelToView == null) {
            final int n = this.getViewToModel().length;
            this.modelToView = new int[n];
            for (int i = 0; i < n; ++i) {
                this.modelToView[this.modelIndex(i)] = i;
            }
        }
        return this.modelToView;
    }
    
    public int getRowCount() {
        return (this.tableModel == null) ? 0 : this.tableModel.getRowCount();
    }
    
    public int getColumnCount() {
        return (this.tableModel == null) ? 0 : this.tableModel.getColumnCount();
    }
    
    public String getColumnName(final int column) {
        return this.tableModel.getColumnName(column);
    }
    
    public Class getColumnClass(final int column) {
        return this.tableModel.getColumnClass(column);
    }
    
    public boolean isCellEditable(final int row, final int column) {
        return this.tableModel.isCellEditable(this.modelIndex(row), column);
    }
    
    public Object getValueAt(final int row, final int column) {
        return this.tableModel.getValueAt(this.modelIndex(row), column);
    }
    
    public void setValueAt(final Object aValue, final int row, final int column) {
        this.tableModel.setValueAt(aValue, this.modelIndex(row), column);
    }
    
    static {
        TableSorter.EMPTY_DIRECTIVE = new Directive(-1, 0);
        COMPARABLE_COMAPRATOR = new Comparator() {
            public int compare(final Object o1, final Object o2) {
                return ((Comparable)o1).compareTo(o2);
            }
        };
        LEXICAL_COMPARATOR = new Comparator() {
            public int compare(final Object o1, final Object o2) {
                return o1.toString().compareTo(o2.toString());
            }
        };
    }
    
    private class Row implements Comparable
    {
        private int modelIndex;
        
        public Row(final int index) {
            this.modelIndex = index;
        }
        
        public int compareTo(final Object o) {
            final int row1 = this.modelIndex;
            final int row2 = ((Row)o).modelIndex;
            for (final Directive directive : TableSorter.this.sortingColumns) {
                final int column = directive.column;
                final Object o2 = TableSorter.this.tableModel.getValueAt(row1, column);
                final Object o3 = TableSorter.this.tableModel.getValueAt(row2, column);
                int comparison = 0;
                if (o2 == null && o3 == null) {
                    comparison = 0;
                }
                else if (o2 == null) {
                    comparison = -1;
                }
                else if (o3 == null) {
                    comparison = 1;
                }
                else {
                    comparison = TableSorter.this.getComparator(column).compare(o2, o3);
                }
                if (comparison != 0) {
                    return (directive.direction == -1) ? (-comparison) : comparison;
                }
            }
            return 0;
        }
    }
    
    private class TableModelHandler implements TableModelListener
    {
        public void tableChanged(final TableModelEvent e) {
            if (!TableSorter.this.isSorting()) {
                TableSorter.this.clearSortingState();
                TableSorter.this.fireTableChanged(e);
                return;
            }
            if (e.getFirstRow() == -1) {
                TableSorter.this.cancelSorting();
                TableSorter.this.fireTableChanged(e);
                return;
            }
            final int column = e.getColumn();
            if (e.getFirstRow() == e.getLastRow() && column != -1 && TableSorter.this.getSortingStatus(column) == 0 && TableSorter.this.modelToView != null) {
                final int viewIndex = TableSorter.this.getModelToView()[e.getFirstRow()];
                TableSorter.this.fireTableChanged(new TableModelEvent(TableSorter.this, viewIndex, viewIndex, column, e.getType()));
                return;
            }
            TableSorter.this.clearSortingState();
            TableSorter.this.fireTableDataChanged();
        }
    }
    
    private class MouseHandler extends MouseAdapter
    {
        public void mouseClicked(final MouseEvent e) {
            final JTableHeader h = (JTableHeader)e.getSource();
            final TableColumnModel columnModel = h.getColumnModel();
            final int viewColumn = columnModel.getColumnIndexAtX(e.getX());
            final int column = columnModel.getColumn(viewColumn).getModelIndex();
            if (column != -1) {
                int status = TableSorter.this.getSortingStatus(column);
                if (!e.isControlDown()) {
                    TableSorter.this.cancelSorting();
                }
                status += (e.isShiftDown() ? -1 : 1);
                status = (status + 4) % 3 - 1;
                TableSorter.this.setSortingStatus(column, status);
            }
        }
    }
    
    private static class Arrow implements Icon
    {
        private boolean descending;
        private int size;
        private int priority;
        
        public Arrow(final boolean descending, final int size, final int priority) {
            this.descending = descending;
            this.size = size;
            this.priority = priority;
        }
        
        public void paintIcon(final Component c, final Graphics g, final int x, int y) {
            final Color color = (c == null) ? Color.GRAY : c.getBackground();
            final int dx = (int)(this.size / 2 * Math.pow(0.8, this.priority));
            final int dy = this.descending ? dx : (-dx);
            y = y + 5 * this.size / 6 + (this.descending ? (-dy) : 0);
            final int shift = this.descending ? 1 : -1;
            g.translate(x, y);
            g.setColor(color.darker());
            g.drawLine(dx / 2, dy, 0, 0);
            g.drawLine(dx / 2, dy + shift, 0, shift);
            g.setColor(color.brighter());
            g.drawLine(dx / 2, dy, dx, 0);
            g.drawLine(dx / 2, dy + shift, dx, shift);
            if (this.descending) {
                g.setColor(color.darker().darker());
            }
            else {
                g.setColor(color.brighter().brighter());
            }
            g.drawLine(dx, 0, 0, 0);
            g.setColor(color);
            g.translate(-x, -y);
        }
        
        public int getIconWidth() {
            return this.size;
        }
        
        public int getIconHeight() {
            return this.size;
        }
    }
    
    private class SortableHeaderRenderer implements TableCellRenderer
    {
        private TableCellRenderer tableCellRenderer;
        
        public SortableHeaderRenderer(final TableCellRenderer tableCellRenderer) {
            this.tableCellRenderer = tableCellRenderer;
        }
        
        public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
            final Component c = this.tableCellRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (c instanceof JLabel) {
                final JLabel l = (JLabel)c;
                l.setHorizontalTextPosition(2);
                final int modelColumn = table.convertColumnIndexToModel(column);
                l.setIcon(TableSorter.this.getHeaderRendererIcon(modelColumn, l.getFont().getSize()));
            }
            return c;
        }
    }
    
    private static class Directive
    {
        private int column;
        private int direction;
        
        public Directive(final int column, final int direction) {
            this.column = column;
            this.direction = direction;
        }
    }
}
