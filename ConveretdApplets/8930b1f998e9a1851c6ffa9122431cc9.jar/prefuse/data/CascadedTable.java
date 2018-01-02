// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data;

import prefuse.data.expression.Expression;
import prefuse.util.collections.CompositeIterator;
import prefuse.data.expression.BooleanLiteral;
import prefuse.data.event.ExpressionListener;
import prefuse.data.util.AcceptAllColumnProjection;
import prefuse.data.event.ProjectionListener;
import prefuse.util.collections.IntIterator;
import java.util.Iterator;
import prefuse.data.column.Column;
import prefuse.data.column.ColumnMetadata;
import prefuse.data.event.TableListener;
import prefuse.data.util.CascadedRowManager;
import prefuse.data.tuple.TableTuple;
import prefuse.data.expression.Predicate;
import prefuse.data.util.ColumnProjection;
import java.util.ArrayList;

public class CascadedTable extends Table
{
    protected Table m_parent;
    protected ArrayList m_pnames;
    protected ColumnProjection m_colFilter;
    protected Predicate m_rowFilter;
    protected Listener m_listener;
    
    public CascadedTable(final Table table) {
        this(table, null, null);
    }
    
    public CascadedTable(final Table table, final Predicate predicate) {
        this(table, predicate, null);
    }
    
    public CascadedTable(final Table table, final ColumnProjection columnProjection) {
        this(table, null, columnProjection);
    }
    
    public CascadedTable(final Table table, final Predicate predicate, final ColumnProjection columnProjection) {
        this(table, predicate, columnProjection, TableTuple.class);
    }
    
    protected CascadedTable(final Table parent, final Predicate rowFilter, final ColumnProjection columnProjection, final Class clazz) {
        super(0, 0, clazz);
        this.m_parent = parent;
        this.m_pnames = new ArrayList();
        this.m_rows = new CascadedRowManager(this);
        this.m_listener = new Listener();
        this.setColumnProjection(columnProjection);
        this.setRowFilter(rowFilter);
        this.m_parent.addTableListener(this.m_listener);
    }
    
    protected CascadedTable() {
        this(TableTuple.class);
    }
    
    protected CascadedTable(final Class clazz) {
        super(0, 0, clazz);
        this.m_pnames = new ArrayList();
    }
    
    protected void filterColumns() {
        if (this.m_parent == null) {
            return;
        }
        for (int i = 0; i < this.m_pnames.size(); ++i) {
            final String s = this.m_pnames.get(i);
            final Column column = this.m_parent.getColumn(i);
            final boolean contains = this.m_names.contains(s);
            if (!this.m_colFilter.include(column, s) || contains) {
                this.m_pnames.remove(i--);
                if (!contains) {
                    ((ColumnEntry)this.m_entries.get(s)).dispose();
                    this.m_entries.remove(s);
                }
                this.fireTableEvent(this.m_rows.getMinimumRow(), this.m_rows.getMaximumRow(), i, -1);
            }
        }
        this.m_pnames.clear();
        final Iterator columnNames = this.m_parent.getColumnNames();
        int n = 0;
        int size = this.m_columns.size();
        while (columnNames.hasNext()) {
            final String s2 = columnNames.next();
            final Column column2 = this.m_parent.getColumn(n);
            if (this.m_colFilter.include(column2, s2) && !this.m_names.contains(s2)) {
                this.m_pnames.add(s2);
                final ColumnEntry columnEntry = this.m_entries.get(s2);
                if (columnEntry == null) {
                    this.m_entries.put(s2, new ColumnEntry(size++, column2, new ColumnMetadata(this, s2)));
                    this.fireTableEvent(this.m_rows.getMinimumRow(), this.m_rows.getMaximumRow(), n, 1);
                }
                else {
                    columnEntry.colnum = size++;
                }
                this.m_lastCol = this.m_columns.size() - 1;
            }
            ++n;
        }
    }
    
    public void filterRows() {
        if (this.m_parent == null) {
            return;
        }
        final CascadedRowManager cascadedRowManager = (CascadedRowManager)this.m_rows;
        final IntIterator rows = this.m_rows.rows();
        while (rows.hasNext()) {
            final int nextInt = rows.nextInt();
            if (!this.m_rowFilter.getBoolean(this.m_parent.getTuple(cascadedRowManager.getParentRow(nextInt)))) {
                this.removeCascadedRow(nextInt);
            }
        }
        final Iterator tuples = this.m_parent.tuples(this.m_rowFilter);
        while (tuples.hasNext()) {
            final int row = tuples.next().getRow();
            if (cascadedRowManager.getChildRow(row) == -1) {
                this.addCascadedRow(row);
            }
        }
    }
    
    public ColumnProjection getColumnProjection() {
        return this.m_colFilter;
    }
    
    public void setColumnProjection(final ColumnProjection columnProjection) {
        if (this.m_colFilter != null) {
            this.m_colFilter.removeProjectionListener(this.m_listener);
        }
        (this.m_colFilter = ((columnProjection == null) ? new AcceptAllColumnProjection() : columnProjection)).addProjectionListener(this.m_listener);
        this.filterColumns();
    }
    
    public Predicate getRowFilter() {
        return this.m_rowFilter;
    }
    
    public void setRowFilter(final Predicate predicate) {
        if (this.m_rowFilter != null) {
            this.m_rowFilter.removeExpressionListener(this.m_listener);
        }
        this.m_rowFilter = ((predicate == null) ? BooleanLiteral.TRUE : predicate);
        if (this.m_rowFilter != BooleanLiteral.TRUE) {
            this.m_rowFilter.addExpressionListener(this.m_listener);
        }
        this.filterRows();
    }
    
    public int getColumnCount() {
        return this.m_columns.size() + this.m_pnames.size();
    }
    
    public int getLocalColumnCount() {
        return this.m_columns.size();
    }
    
    public Table getParentTable() {
        return this.m_parent;
    }
    
    public int getParentRow(final int n) {
        return ((CascadedRowManager)this.m_rows).getParentRow(n);
    }
    
    public int getChildRow(final int n) {
        return ((CascadedRowManager)this.m_rows).getChildRow(n);
    }
    
    public int addRow() {
        if (this.m_parent != null) {
            throw new IllegalStateException("Add row not supported for CascadedTable.");
        }
        return super.addRow();
    }
    
    public void addRows(final int n) {
        if (this.m_parent != null) {
            throw new IllegalStateException("Add rows not supported for CascadedTable.");
        }
        super.addRows(n);
    }
    
    public boolean removeRow(final int n) {
        if (this.m_parent != null) {
            throw new IllegalStateException("Remove row not supported for CascadedTable.");
        }
        return super.removeRow(n);
    }
    
    protected int addCascadedRow(final int n) {
        final int addRow = this.m_rows.addRow();
        ((CascadedRowManager)this.m_rows).put(addRow, n);
        this.updateRowCount();
        this.fireTableEvent(addRow, addRow, -1, 1);
        return addRow;
    }
    
    protected boolean removeCascadedRow(final int n) {
        final boolean removeRow = super.removeRow(n);
        if (removeRow) {
            ((CascadedRowManager)this.m_rows).remove(n);
        }
        return removeRow;
    }
    
    public String getColumnName(final int n) {
        final int size = this.m_names.size();
        if (n >= size) {
            return (String)this.m_pnames.get(n - size);
        }
        return this.m_names.get(n);
    }
    
    public int getColumnNumber(final Column column) {
        int n = this.m_columns.indexOf(column);
        if (n == -1 && this.m_parent != null) {
            final int columnNumber = this.m_parent.getColumnNumber(column);
            if (columnNumber == -1) {
                return columnNumber;
            }
            n = this.m_pnames.indexOf(this.m_parent.getColumnName(columnNumber));
            if (n != -1) {
                n += this.m_columns.size();
            }
        }
        return n;
    }
    
    public Column getColumn(final int lastCol) {
        this.m_lastCol = lastCol;
        final int size = this.m_names.size();
        if (lastCol >= size && this.m_parent != null) {
            return this.m_parent.getColumn((String)this.m_pnames.get(lastCol - size));
        }
        return this.m_columns.get(lastCol);
    }
    
    protected boolean hasColumn(final String s) {
        final int columnNumber = this.getColumnNumber(s);
        return columnNumber >= 0 && columnNumber < this.getLocalColumnCount();
    }
    
    protected Iterator getColumnNames() {
        if (this.m_parent == null) {
            return this.m_names.iterator();
        }
        return new CompositeIterator(this.m_names.iterator(), this.m_pnames.iterator());
    }
    
    protected void invalidateSchema() {
        super.invalidateSchema();
        this.filterColumns();
    }
    
    private class Listener implements TableListener, ProjectionListener, ExpressionListener
    {
        public void tableChanged(final Table table, final int n, final int n2, final int n3, final int n4) {
            if (table != CascadedTable.this.m_parent) {
                return;
            }
            final CascadedRowManager cascadedRowManager = (CascadedRowManager)CascadedTable.this.m_rows;
            switch (n4) {
                case 0: {
                    if (n3 == -1) {
                        break;
                    }
                    for (int i = n; i <= n2; ++i) {
                        final int childRow;
                        if ((childRow = cascadedRowManager.getChildRow(i)) != -1) {
                            if (CascadedTable.this.m_rowFilter.getBoolean(CascadedTable.this.m_parent.getTuple(i))) {
                                final int columnNumber = CascadedTable.this.getColumnNumber(CascadedTable.this.m_parent.getColumnName(n3));
                                if (columnNumber >= CascadedTable.this.getLocalColumnCount()) {
                                    CascadedTable.this.fireTableEvent(childRow, childRow, columnNumber, 0);
                                }
                            }
                            else {
                                CascadedTable.this.removeCascadedRow(childRow);
                            }
                        }
                        else {
                            final int childRow2;
                            if (CascadedTable.this.m_rowFilter.getBoolean(CascadedTable.this.m_parent.getTuple(i)) && (childRow2 = cascadedRowManager.getChildRow(i)) < 0) {
                                CascadedTable.this.addCascadedRow(i);
                            }
                        }
                    }
                    break;
                }
                case -1: {
                    if (n3 == -1) {
                        for (int j = n; j <= n2; ++j) {
                            final int childRow3;
                            if ((childRow3 = cascadedRowManager.getChildRow(j)) != -1) {
                                CascadedTable.this.removeCascadedRow(childRow3);
                            }
                        }
                        break;
                    }
                    CascadedTable.this.filterColumns();
                    break;
                }
                case 1: {
                    if (n3 == -1) {
                        for (int k = n; k <= n2; ++k) {
                            if (CascadedTable.this.m_rowFilter.getBoolean(CascadedTable.this.m_parent.getTuple(k)) && cascadedRowManager.getChildRow(k) < 0) {
                                CascadedTable.this.addCascadedRow(k);
                            }
                        }
                        break;
                    }
                    CascadedTable.this.filterColumns();
                    break;
                }
            }
        }
        
        public void projectionChanged(final ColumnProjection columnProjection) {
            if (columnProjection == CascadedTable.this.m_colFilter) {
                CascadedTable.this.filterColumns();
            }
        }
        
        public void expressionChanged(final Expression expression) {
            if (expression == CascadedTable.this.m_rowFilter) {
                CascadedTable.this.filterRows();
            }
        }
    }
}
