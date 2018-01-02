// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data;

import prefuse.data.event.TableListener;
import prefuse.util.TypeLib;
import prefuse.data.util.FilterIteratorFactory;
import prefuse.data.util.TableIterator;
import prefuse.data.util.Sort;
import prefuse.data.expression.Predicate;
import java.util.Date;
import prefuse.util.collections.IncompatibleComparatorException;
import java.util.Comparator;
import prefuse.data.util.TreeIndex;
import prefuse.data.util.Index;
import prefuse.data.column.ColumnMetadata;
import prefuse.data.expression.Expression;
import prefuse.data.expression.parser.ExpressionParser;
import prefuse.data.column.ColumnFactory;
import prefuse.util.collections.IntIterator;
import java.util.Iterator;
import prefuse.data.column.Column;
import prefuse.data.tuple.TableTuple;
import prefuse.data.tuple.TupleManager;
import prefuse.data.util.RowManager;
import java.util.HashMap;
import java.util.ArrayList;
import prefuse.util.collections.CopyOnWriteArrayList;
import prefuse.data.event.ColumnListener;
import prefuse.data.tuple.AbstractTupleSet;

public class Table extends AbstractTupleSet implements ColumnListener
{
    protected CopyOnWriteArrayList m_listeners;
    protected ArrayList m_columns;
    protected ArrayList m_names;
    protected HashMap m_entries;
    protected RowManager m_rows;
    protected TupleManager m_tuples;
    protected int m_modCount;
    protected int m_lastCol;
    protected Schema m_schema;
    
    public Table() {
        this(0, 0);
    }
    
    public Table(final int n, final int n2) {
        this(n, n2, TableTuple.class);
    }
    
    protected Table(final int n, final int n2, final Class clazz) {
        this.m_modCount = 0;
        this.m_lastCol = -1;
        this.m_listeners = new CopyOnWriteArrayList();
        this.m_columns = new ArrayList(n2);
        this.m_names = new ArrayList(n2);
        this.m_rows = new RowManager(this);
        this.m_entries = new HashMap(n2 + 5);
        this.m_tuples = new TupleManager(this, null, clazz);
        if (n > 0) {
            this.addRows(n);
        }
    }
    
    public int getColumnCount() {
        return this.m_columns.size();
    }
    
    public Class getColumnType(final int n) {
        return this.getColumn(n).getColumnType();
    }
    
    public Class getColumnType(final String s) {
        final Column column = this.getColumn(s);
        return (column == null) ? null : column.getColumnType();
    }
    
    public int getRowCount() {
        return this.m_rows.getRowCount();
    }
    
    public int getMinimumRow() {
        return this.m_rows.getMinimumRow();
    }
    
    public int getMaximumRow() {
        return this.m_rows.getMaximumRow();
    }
    
    public boolean isCellEditable(final int n, final int n2) {
        return this.m_rows.isValidRow(n) && this.getColumn(n2).isCellEditable(n);
    }
    
    public int getModificationCount() {
        return this.m_modCount;
    }
    
    public void setTupleManager(final TupleManager tuples) {
        this.m_tuples.invalidateAll();
        this.m_tuples = tuples;
    }
    
    public Schema getSchema() {
        if (this.m_schema == null) {
            final Schema schema = new Schema();
            for (int i = 0; i < this.getColumnCount(); ++i) {
                schema.addColumn(this.getColumnName(i), this.getColumnType(i), this.getColumn(i).getDefaultValue());
            }
            schema.lockSchema();
            this.m_schema = schema;
        }
        return this.m_schema;
    }
    
    protected void invalidateSchema() {
        this.m_schema = null;
    }
    
    public int getColumnRow(final int n, final int n2) {
        return this.m_rows.getColumnRow(n, n2);
    }
    
    public int getTableRow(final int n, final int n2) {
        return this.m_rows.getTableRow(n, n2);
    }
    
    public int addRow() {
        final int addRow = this.m_rows.addRow();
        this.updateRowCount();
        this.fireTableEvent(addRow, addRow, -1, 1);
        return addRow;
    }
    
    public void addRows(final int n) {
        for (int i = 0; i < n; ++i) {
            this.addRow();
        }
    }
    
    protected void updateRowCount() {
        final int maximumRow = this.m_rows.getMaximumRow() + 1;
        final Iterator columns = this.getColumns();
        while (columns.hasNext()) {
            columns.next().setMaximumRow(maximumRow);
        }
    }
    
    public boolean removeRow(final int n) {
        if (this.m_rows.isValidRow(n)) {
            this.fireTableEvent(n, n, -1, -1);
            this.m_tuples.invalidate(n);
            this.m_rows.releaseRow(n);
            final Iterator columns = this.getColumns();
            while (columns.hasNext()) {
                columns.next().revertToDefault(n);
            }
            return true;
        }
        return false;
    }
    
    public void clear() {
        final IntIterator rows = this.rows(true);
        while (rows.hasNext()) {
            this.removeRow(rows.nextInt());
        }
    }
    
    public boolean isValidRow(final int n) {
        return this.m_rows.isValidRow(n);
    }
    
    protected boolean hasColumn(final String s) {
        return this.getColumnNumber(s) != -1;
    }
    
    public String getColumnName(final int n) {
        return this.m_names.get(n);
    }
    
    public int getColumnNumber(final String s) {
        final ColumnEntry columnEntry = this.m_entries.get(s);
        return (columnEntry == null) ? -1 : columnEntry.colnum;
    }
    
    public int getColumnNumber(final Column column) {
        return this.m_columns.indexOf(column);
    }
    
    public Column getColumn(final int lastCol) {
        this.m_lastCol = lastCol;
        return this.m_columns.get(lastCol);
    }
    
    public Column getColumn(final String s) {
        final ColumnEntry columnEntry = this.m_entries.get(s);
        return (columnEntry != null) ? columnEntry.column : null;
    }
    
    public void addColumn(final String s, final Class clazz) {
        this.addColumn(s, clazz, null);
    }
    
    public void addColumn(final String s, final Class clazz, final Object o) {
        this.addColumn(s, ColumnFactory.getColumn(clazz, this.m_rows.getMaximumRow() + 1, o));
    }
    
    public void addColumn(final String s, final String s2) {
        final Expression parse = ExpressionParser.parse(s2);
        final Throwable error = ExpressionParser.getError();
        if (error != null) {
            throw new RuntimeException(error);
        }
        this.addColumn(s, parse);
    }
    
    public void addColumn(final String s, final Expression expression) {
        this.addColumn(s, ColumnFactory.getColumn(this, expression));
    }
    
    public void addConstantColumn(final String s, final Class clazz, final Object o) {
        this.addColumn(s, ColumnFactory.getConstantColumn(clazz, o));
    }
    
    protected void addColumn(final String s, final Column column) {
        final int columnNumber = this.getColumnNumber(s);
        if (columnNumber >= 0 && columnNumber < this.m_columns.size()) {
            throw new IllegalArgumentException("Table already has column with name \"" + s + "\"");
        }
        this.m_columns.add(column);
        this.m_names.add(s);
        this.m_lastCol = this.m_columns.size() - 1;
        final ColumnEntry columnEntry = this.m_entries.put(s, new ColumnEntry(this.m_lastCol, column, new ColumnMetadata(this, s)));
        if (columnEntry != null) {
            columnEntry.dispose();
        }
        this.invalidateSchema();
        column.addColumnListener(this);
        this.fireTableEvent(this.m_rows.getMinimumRow(), this.m_rows.getMaximumRow(), this.m_lastCol, 1);
    }
    
    protected Column removeColumn(final int n) {
        if (n < 0 || n >= this.m_columns.size()) {
            throw new IllegalArgumentException("Column index is not legal.");
        }
        final String s = this.m_names.get(n);
        ((ColumnEntry)this.m_entries.get(s)).dispose();
        final Column column = this.m_columns.remove(n);
        this.m_entries.remove(s);
        this.m_names.remove(n);
        this.renumberColumns();
        this.m_lastCol = -1;
        this.invalidateSchema();
        column.removeColumnListener(this);
        this.fireTableEvent(this.m_rows.getMinimumRow(), this.m_rows.getMaximumRow(), n, -1);
        return column;
    }
    
    public Column removeColumn(final String s) {
        final int index = this.m_names.indexOf(s);
        if (index < 0) {
            throw new IllegalArgumentException("No such column.");
        }
        return this.removeColumn(index);
    }
    
    public void removeColumn(final Column column) {
        final int index = this.m_columns.indexOf(column);
        if (index < 0) {
            throw new IllegalArgumentException("No such column.");
        }
        this.removeColumn(index);
    }
    
    protected void renumberColumns() {
        final Iterator<String> iterator = (Iterator<String>)this.m_names.iterator();
        int colnum = 0;
        while (iterator.hasNext()) {
            this.m_entries.get(iterator.next()).colnum = colnum;
            ++colnum;
        }
    }
    
    protected Iterator getColumns() {
        return this.m_columns.iterator();
    }
    
    protected Iterator getColumnNames() {
        return this.m_names.iterator();
    }
    
    public ColumnMetadata getMetadata(final String s) {
        final ColumnEntry columnEntry = this.m_entries.get(s);
        if (columnEntry == null) {
            throw new IllegalArgumentException("Unknown column name: " + s);
        }
        return columnEntry.metadata;
    }
    
    public Index index(final String s) {
        final ColumnEntry columnEntry = this.m_entries.get(s);
        if (columnEntry == null) {
            throw new IllegalArgumentException("Unknown column name: " + s);
        }
        if (columnEntry.index != null) {
            return columnEntry.index;
        }
        final Column column = columnEntry.column;
        try {
            columnEntry.index = new TreeIndex(this, this.m_rows, column, null);
        }
        catch (IncompatibleComparatorException ex) {}
        return columnEntry.index;
    }
    
    public Index getIndex(final String s) {
        final ColumnEntry columnEntry = this.m_entries.get(s);
        if (columnEntry == null) {
            throw new IllegalArgumentException("Unknown column name: " + s);
        }
        return columnEntry.index;
    }
    
    protected Index getIndex(final String s, final Class clazz, final boolean b) {
        if (!clazz.equals(this.getColumnType(s))) {
            throw new IllegalArgumentException("Column type does not match.");
        }
        if (this.getIndex(s) == null && b) {
            this.index(s);
        }
        return this.getIndex(s);
    }
    
    public boolean removeIndex(final String s) {
        final ColumnEntry columnEntry = this.m_entries.get(s);
        if (columnEntry == null) {
            throw new IllegalArgumentException("Unknown column name: " + s);
        }
        if (columnEntry.index == null) {
            return false;
        }
        columnEntry.index.dispose();
        columnEntry.index = null;
        return true;
    }
    
    public Tuple getTuple(final int n) {
        return this.m_tuples.getTuple(n);
    }
    
    public Tuple addTuple(final Tuple tuple) {
        if (tuple.getTable() == this) {
            return null;
        }
        final Schema schema = tuple.getSchema();
        if (this.getSchema().isAssignableFrom(schema)) {
            final int addRow = this.addRow();
            for (int i = 0; i < schema.getColumnCount(); ++i) {
                this.set(addRow, schema.getColumnName(i), tuple.get(i));
            }
            return this.getTuple(addRow);
        }
        return null;
    }
    
    public Tuple setTuple(final Tuple tuple) {
        this.clear();
        return this.addTuple(tuple);
    }
    
    public boolean removeTuple(final Tuple tuple) {
        if (this.containsTuple(tuple)) {
            this.removeRow(tuple.getRow());
            return true;
        }
        return false;
    }
    
    public boolean containsTuple(final Tuple tuple) {
        return tuple.getTable() == this && this.isValidRow(tuple.getRow());
    }
    
    public int getTupleCount() {
        return this.getRowCount();
    }
    
    public boolean isAddColumnSupported() {
        return true;
    }
    
    public boolean canGet(final String s, final Class clazz) {
        final Column column = this.getColumn(s);
        return column != null && column.canGet(clazz);
    }
    
    public boolean canSet(final String s, final Class clazz) {
        final Column column = this.getColumn(s);
        return column != null && column.canSet(clazz);
    }
    
    public Object get(int columnRow, final String s) {
        final int columnNumber = this.getColumnNumber(s);
        columnRow = this.getColumnRow(columnRow, columnNumber);
        return this.getColumn(columnNumber).get(columnRow);
    }
    
    public void set(int columnRow, final String s, final Object o) {
        final int columnNumber = this.getColumnNumber(s);
        columnRow = this.getColumnRow(columnRow, columnNumber);
        this.getColumn(columnNumber).set(o, columnRow);
    }
    
    public Object get(int columnRow, final int n) {
        columnRow = this.getColumnRow(columnRow, n);
        return this.getColumn(n).get(columnRow);
    }
    
    public void set(int columnRow, final int n, final Object o) {
        columnRow = this.getColumnRow(columnRow, n);
        this.getColumn(n).set(o, columnRow);
    }
    
    public Object getDefault(final String s) {
        return this.getColumn(this.getColumnNumber(s)).getDefaultValue();
    }
    
    public void revertToDefault(int columnRow, final String s) {
        final int columnNumber = this.getColumnNumber(s);
        columnRow = this.getColumnRow(columnRow, columnNumber);
        this.getColumn(columnNumber).revertToDefault(columnRow);
    }
    
    public final boolean canGetInt(final String s) {
        final Column column = this.getColumn(s);
        return column != null && column.canGetInt();
    }
    
    public final boolean canSetInt(final String s) {
        final Column column = this.getColumn(s);
        return column != null && column.canSetInt();
    }
    
    public final int getInt(int columnRow, final String s) {
        final int columnNumber = this.getColumnNumber(s);
        columnRow = this.getColumnRow(columnRow, columnNumber);
        return this.getColumn(columnNumber).getInt(columnRow);
    }
    
    public final void setInt(int columnRow, final String s, final int n) {
        final int columnNumber = this.getColumnNumber(s);
        columnRow = this.getColumnRow(columnRow, columnNumber);
        this.getColumn(columnNumber).setInt(n, columnRow);
    }
    
    public final int getInt(int columnRow, final int n) {
        columnRow = this.getColumnRow(columnRow, n);
        return this.getColumn(n).getInt(columnRow);
    }
    
    public final void setInt(int columnRow, final int n, final int n2) {
        columnRow = this.getColumnRow(columnRow, n);
        this.getColumn(n).setInt(n2, columnRow);
    }
    
    public final boolean canGetLong(final String s) {
        final Column column = this.getColumn(s);
        return column != null && column.canGetLong();
    }
    
    public final boolean canSetLong(final String s) {
        final Column column = this.getColumn(s);
        return column != null && column.canSetLong();
    }
    
    public final long getLong(int columnRow, final String s) {
        final int columnNumber = this.getColumnNumber(s);
        columnRow = this.getColumnRow(columnRow, columnNumber);
        return this.getColumn(columnNumber).getLong(columnRow);
    }
    
    public final void setLong(int columnRow, final String s, final long n) {
        final int columnNumber = this.getColumnNumber(s);
        columnRow = this.getColumnRow(columnRow, columnNumber);
        this.getColumn(columnNumber).setLong(n, columnRow);
    }
    
    public final long getLong(int columnRow, final int n) {
        columnRow = this.getColumnRow(columnRow, n);
        return this.getColumn(n).getLong(columnRow);
    }
    
    public final void setLong(int columnRow, final int n, final long n2) {
        columnRow = this.getColumnRow(columnRow, n);
        this.getColumn(n).setLong(n2, columnRow);
    }
    
    public final boolean canGetFloat(final String s) {
        final Column column = this.getColumn(s);
        return column != null && column.canGetFloat();
    }
    
    public final boolean canSetFloat(final String s) {
        final Column column = this.getColumn(s);
        return column != null && column.canSetFloat();
    }
    
    public final float getFloat(int columnRow, final String s) {
        final int columnNumber = this.getColumnNumber(s);
        columnRow = this.getColumnRow(columnRow, columnNumber);
        return this.getColumn(columnNumber).getFloat(columnRow);
    }
    
    public final void setFloat(int columnRow, final String s, final float n) {
        final int columnNumber = this.getColumnNumber(s);
        columnRow = this.getColumnRow(columnRow, columnNumber);
        this.getColumn(columnNumber).setFloat(n, columnRow);
    }
    
    public final float getFloat(int columnRow, final int n) {
        columnRow = this.getColumnRow(columnRow, n);
        return this.getColumn(n).getFloat(columnRow);
    }
    
    public final void setFloat(int columnRow, final int n, final float n2) {
        columnRow = this.getColumnRow(columnRow, n);
        this.getColumn(n).setFloat(n2, columnRow);
    }
    
    public final boolean canGetDouble(final String s) {
        final Column column = this.getColumn(s);
        return column != null && column.canGetDouble();
    }
    
    public final boolean canSetDouble(final String s) {
        final Column column = this.getColumn(s);
        return column != null && column.canSetDouble();
    }
    
    public final double getDouble(int columnRow, final String s) {
        final int columnNumber = this.getColumnNumber(s);
        columnRow = this.getColumnRow(columnRow, columnNumber);
        return this.getColumn(columnNumber).getDouble(columnRow);
    }
    
    public final void setDouble(int columnRow, final String s, final double n) {
        final int columnNumber = this.getColumnNumber(s);
        columnRow = this.getColumnRow(columnRow, columnNumber);
        this.getColumn(columnNumber).setDouble(n, columnRow);
    }
    
    public final double getDouble(int columnRow, final int n) {
        columnRow = this.getColumnRow(columnRow, n);
        return this.getColumn(n).getDouble(columnRow);
    }
    
    public final void setDouble(int columnRow, final int n, final double n2) {
        columnRow = this.getColumnRow(columnRow, n);
        this.getColumn(n).setDouble(n2, columnRow);
    }
    
    public final boolean canGetBoolean(final String s) {
        final Column column = this.getColumn(s);
        return column != null && column.canGetBoolean();
    }
    
    public final boolean canSetBoolean(final String s) {
        final Column column = this.getColumn(s);
        return column != null && column.canSetBoolean();
    }
    
    public final boolean getBoolean(int columnRow, final String s) {
        final int columnNumber = this.getColumnNumber(s);
        columnRow = this.getColumnRow(columnRow, columnNumber);
        return this.getColumn(columnNumber).getBoolean(columnRow);
    }
    
    public final void setBoolean(int columnRow, final String s, final boolean b) {
        final int columnNumber = this.getColumnNumber(s);
        columnRow = this.getColumnRow(columnRow, columnNumber);
        this.getColumn(columnNumber).setBoolean(b, columnRow);
    }
    
    public final boolean getBoolean(int columnRow, final int n) {
        columnRow = this.getColumnRow(columnRow, n);
        return this.getColumn(n).getBoolean(columnRow);
    }
    
    public final void setBoolean(int columnRow, final int n, final boolean b) {
        columnRow = this.getColumnRow(columnRow, n);
        this.getColumn(n).setBoolean(b, columnRow);
    }
    
    public final boolean canGetString(final String s) {
        final Column column = this.getColumn(s);
        return column != null && column.canGetString();
    }
    
    public final boolean canSetString(final String s) {
        final Column column = this.getColumn(s);
        return column != null && column.canSetString();
    }
    
    public final String getString(int columnRow, final String s) {
        final int columnNumber = this.getColumnNumber(s);
        columnRow = this.getColumnRow(columnRow, columnNumber);
        return this.getColumn(columnNumber).getString(columnRow);
    }
    
    public final void setString(int columnRow, final String s, final String s2) {
        final int columnNumber = this.getColumnNumber(s);
        columnRow = this.getColumnRow(columnRow, columnNumber);
        this.getColumn(columnNumber).setString(s2, columnRow);
    }
    
    public final String getString(int columnRow, final int n) {
        columnRow = this.getColumnRow(columnRow, n);
        return this.getColumn(n).getString(columnRow);
    }
    
    public final void setString(int columnRow, final int n, final String s) {
        columnRow = this.getColumnRow(columnRow, n);
        this.getColumn(n).setString(s, columnRow);
    }
    
    public final boolean canGetDate(final String s) {
        final Column column = this.getColumn(s);
        return column != null && column.canGetDate();
    }
    
    public final boolean canSetDate(final String s) {
        final Column column = this.getColumn(s);
        return column != null && column.canSetDate();
    }
    
    public final Date getDate(int columnRow, final String s) {
        final int columnNumber = this.getColumnNumber(s);
        columnRow = this.getColumnRow(columnRow, columnNumber);
        return this.getColumn(columnNumber).getDate(columnRow);
    }
    
    public final void setDate(int columnRow, final String s, final Date date) {
        final int columnNumber = this.getColumnNumber(s);
        columnRow = this.getColumnRow(columnRow, columnNumber);
        this.getColumn(columnNumber).setDate(date, columnRow);
    }
    
    public final Date getDate(int columnRow, final int n) {
        columnRow = this.getColumnRow(columnRow, n);
        return this.getColumn(n).getDate(columnRow);
    }
    
    public final void setDate(int columnRow, final int n, final Date date) {
        columnRow = this.getColumnRow(columnRow, n);
        this.getColumn(n).setDate(date, columnRow);
    }
    
    public Table select(final Predicate predicate, final Sort sort) {
        final Table instantiate = this.getSchema().instantiate();
        final Iterator tuples = this.tuples(predicate, sort);
        while (tuples.hasNext()) {
            instantiate.addTuple(tuples.next());
        }
        return instantiate;
    }
    
    public void remove(final Predicate predicate) {
        final IntIterator rows = this.rows(predicate);
        while (rows.hasNext()) {
            this.removeRow(rows.nextInt());
        }
    }
    
    public TableIterator iterator() {
        return this.iterator(this.rows());
    }
    
    public TableIterator iterator(final IntIterator intIterator) {
        return new TableIterator(this, intIterator);
    }
    
    public Iterator tuples() {
        return this.m_tuples.iterator(this.rows());
    }
    
    public Iterator tuplesReversed() {
        return this.m_tuples.iterator(this.rows(true));
    }
    
    public Iterator tuples(final IntIterator intIterator) {
        return this.m_tuples.iterator(intIterator);
    }
    
    public IntIterator rows() {
        return this.m_rows.rows();
    }
    
    public IntIterator rows(final Predicate predicate) {
        return FilterIteratorFactory.rows(this, predicate);
    }
    
    public IntIterator rows(final boolean b) {
        return this.m_rows.rows(b);
    }
    
    public IntIterator rowsSortedBy(final String s, final boolean b) {
        return this.getIndex(s, this.getColumnType(s), true).allRows(b ? 32 : 16);
    }
    
    public IntIterator rangeSortedBy(final String s, final int n, final int n2, final int n3) {
        return this.getIndex(s, Integer.TYPE, true).rows(n, n2, n3);
    }
    
    public IntIterator rangeSortedBy(final String s, final long n, final long n2, final int n3) {
        return this.getIndex(s, Long.TYPE, true).rows(n, n2, n3);
    }
    
    public IntIterator rangeSortedBy(final String s, final float n, final float n2, final int n3) {
        return this.getIndex(s, Float.TYPE, true).rows(n, n2, n3);
    }
    
    public IntIterator rangeSortedBy(final String s, final double n, final double n2, final int n3) {
        return this.getIndex(s, Double.TYPE, true).rows(n, n2, n3);
    }
    
    public IntIterator rangeSortedBy(final String s, final Object o, final Object o2, final int n) {
        final Class sharedType = TypeLib.getSharedType(o, o2);
        if (sharedType == null) {
            throw new IllegalArgumentException("Incompatible arguments");
        }
        return this.getIndex(s, sharedType, true).rows(o, o2, n);
    }
    
    public void columnChanged(final Column column, final int n, final boolean b) {
        this.handleColumnChanged(column, n, n);
    }
    
    public void columnChanged(final Column column, final int n, final double n2) {
        this.handleColumnChanged(column, n, n);
    }
    
    public void columnChanged(final Column column, final int n, final float n2) {
        this.handleColumnChanged(column, n, n);
    }
    
    public void columnChanged(final Column column, final int n, final int n2) {
        this.handleColumnChanged(column, n, n);
    }
    
    public void columnChanged(final Column column, final int n, final long n2) {
        this.handleColumnChanged(column, n, n);
    }
    
    public void columnChanged(final Column column, final int n, final Object o) {
        this.handleColumnChanged(column, n, n);
    }
    
    public void columnChanged(final Column column, final int n, final int n2, final int n3) {
        this.handleColumnChanged(column, n2, n3);
    }
    
    protected void handleColumnChanged(final Column column, int n, final int n2) {
        while (!this.isValidRow(n) && n <= n2) {
            ++n;
        }
        if (n > n2) {
            return;
        }
        int n3;
        if (this.m_lastCol != -1 && column == this.getColumn(this.m_lastCol)) {
            n3 = this.m_lastCol;
        }
        else {
            n3 = this.getColumnNumber(column);
        }
        if (n3 >= 0) {
            this.fireTableEvent(n, n2, n3, 0);
        }
    }
    
    public void addTableListener(final TableListener tableListener) {
        if (!this.m_listeners.contains(tableListener)) {
            this.m_listeners.add(tableListener);
        }
    }
    
    public void removeTableListener(final TableListener tableListener) {
        this.m_listeners.remove(tableListener);
    }
    
    protected void fireTableEvent(final int n, final int n2, final int n3, final int n4) {
        ++this.m_modCount;
        if (n4 != 0 && n3 == -1) {
            this.fireTupleEvent(this, n, n2, n4);
        }
        if (!this.m_listeners.isEmpty()) {
            final Object[] array = this.m_listeners.getArray();
            for (int i = 0; i < array.length; ++i) {
                ((TableListener)array[i]).tableChanged(this, n, n2, n3, n4);
            }
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Table[");
        sb.append("rows=").append(this.getRowCount());
        sb.append(", cols=").append(this.getColumnCount());
        sb.append(", maxrow=").append(this.m_rows.getMaximumRow());
        sb.append("]");
        return sb.toString();
    }
    
    protected static class ColumnEntry
    {
        public int colnum;
        public Column column;
        public ColumnMetadata metadata;
        public Index index;
        
        public ColumnEntry(final int colnum, final Column column, final ColumnMetadata metadata) {
            this.colnum = colnum;
            this.column = column;
            this.metadata = metadata;
            this.index = null;
        }
        
        public void dispose() {
            if (this.metadata != null) {
                this.metadata.dispose();
            }
            if (this.index != null) {
                this.index.dispose();
            }
        }
    }
}
