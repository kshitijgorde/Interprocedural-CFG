// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.util;

import prefuse.util.collections.IntIterator;
import prefuse.util.collections.ObjectIntSortedMap;
import prefuse.util.collections.BooleanIntSortedMap;
import prefuse.util.collections.DoubleIntSortedMap;
import prefuse.util.collections.FloatIntSortedMap;
import prefuse.util.collections.LongIntSortedMap;
import prefuse.util.collections.IntIntSortedMap;
import prefuse.util.collections.IncompatibleComparatorException;
import prefuse.util.collections.SortedMapFactory;
import java.util.Comparator;
import prefuse.util.collections.IntSortedMap;
import prefuse.data.column.Column;
import prefuse.data.Table;
import prefuse.data.event.TableListener;
import prefuse.data.event.ColumnListener;

public class TreeIndex implements Index, ColumnListener, TableListener
{
    protected Table m_table;
    protected RowManager m_rows;
    protected Column m_col;
    protected IntSortedMap m_index;
    protected boolean m_reindex;
    protected int m_colidx;
    
    public TreeIndex(final Table table, final RowManager rows, final Column col, final Comparator comparator) throws IncompatibleComparatorException {
        this.m_table = table;
        this.m_rows = rows;
        this.m_col = col;
        this.m_index = SortedMapFactory.getMap(col.getColumnType(), comparator, false);
        this.index();
        this.m_col.addColumnListener(this);
        this.m_table.addTableListener(this);
    }
    
    public void dispose() {
        this.m_col.removeColumnListener(this);
        this.m_table.removeTableListener(this);
    }
    
    public Comparator getComparator() {
        return this.m_index.comparator();
    }
    
    public int size() {
        return this.m_index.size();
    }
    
    private int getColumnIndex() {
        if (this.m_table.getColumn(this.m_colidx) != this.m_col) {
            this.m_colidx = this.m_table.getColumnNumber(this.m_col);
        }
        return this.m_colidx;
    }
    
    public void index() {
        this.m_index.clear();
        final int columnIndex = this.getColumnIndex();
        this.m_colidx = columnIndex;
        final IntIterator rows = this.m_rows.rows();
        if (this.m_index instanceof IntIntSortedMap) {
            final IntIntSortedMap intIntSortedMap = (IntIntSortedMap)this.m_index;
            while (rows.hasNext()) {
                final int nextInt = rows.nextInt();
                intIntSortedMap.put(this.m_col.getInt(this.m_table.getColumnRow(nextInt, columnIndex)), nextInt);
            }
        }
        else if (this.m_index instanceof LongIntSortedMap) {
            final LongIntSortedMap longIntSortedMap = (LongIntSortedMap)this.m_index;
            while (rows.hasNext()) {
                final int nextInt2 = rows.nextInt();
                longIntSortedMap.put(this.m_col.getLong(this.m_table.getColumnRow(nextInt2, columnIndex)), nextInt2);
            }
        }
        else if (this.m_index instanceof FloatIntSortedMap) {
            final FloatIntSortedMap floatIntSortedMap = (FloatIntSortedMap)this.m_index;
            while (rows.hasNext()) {
                final int nextInt3 = rows.nextInt();
                floatIntSortedMap.put(this.m_col.getFloat(this.m_table.getColumnRow(nextInt3, columnIndex)), nextInt3);
            }
        }
        else if (this.m_index instanceof DoubleIntSortedMap) {
            final DoubleIntSortedMap doubleIntSortedMap = (DoubleIntSortedMap)this.m_index;
            while (rows.hasNext()) {
                final int nextInt4 = rows.nextInt();
                doubleIntSortedMap.put(this.m_col.getDouble(this.m_table.getColumnRow(nextInt4, columnIndex)), nextInt4);
            }
        }
        else if (this.m_index instanceof BooleanIntSortedMap) {
            final BooleanIntSortedMap booleanIntSortedMap = (BooleanIntSortedMap)this.m_index;
            while (rows.hasNext()) {
                final int nextInt5 = rows.nextInt();
                booleanIntSortedMap.put(this.m_col.getBoolean(this.m_table.getColumnRow(nextInt5, columnIndex)), nextInt5);
            }
        }
        else {
            if (!(this.m_index instanceof ObjectIntSortedMap)) {
                throw new IllegalStateException();
            }
            final ObjectIntSortedMap objectIntSortedMap = (ObjectIntSortedMap)this.m_index;
            while (rows.hasNext()) {
                final int nextInt6 = rows.nextInt();
                objectIntSortedMap.put(this.m_col.get(this.m_table.getColumnRow(nextInt6, columnIndex)), nextInt6);
            }
        }
        this.m_reindex = false;
    }
    
    public void tableChanged(final Table table, final int n, final int n2, final int n3, final int n4) {
        if (n4 == 0 || table != this.m_table || n3 != -1) {
            return;
        }
        final boolean b = n4 == 1;
        for (int i = n; i <= n2; ++i) {
            this.rowChanged(i, b);
        }
    }
    
    private void rowChanged(final int n, final boolean b) {
        final int columnRow = this.m_rows.getColumnRow(n, this.getColumnIndex());
        if (this.m_index instanceof IntIntSortedMap) {
            final IntIntSortedMap intIntSortedMap = (IntIntSortedMap)this.m_index;
            final int int1 = this.m_col.getInt(n);
            if (b) {
                intIntSortedMap.put(int1, n);
            }
            else {
                intIntSortedMap.remove(int1, n);
            }
        }
        else if (this.m_index instanceof LongIntSortedMap) {
            final LongIntSortedMap longIntSortedMap = (LongIntSortedMap)this.m_index;
            final long long1 = this.m_col.getLong(columnRow);
            if (b) {
                longIntSortedMap.put(long1, n);
            }
            else {
                longIntSortedMap.remove(long1, n);
            }
        }
        else if (this.m_index instanceof FloatIntSortedMap) {
            final FloatIntSortedMap floatIntSortedMap = (FloatIntSortedMap)this.m_index;
            final float float1 = this.m_col.getFloat(columnRow);
            if (b) {
                floatIntSortedMap.put(float1, n);
            }
            else {
                floatIntSortedMap.remove(float1, n);
            }
        }
        else if (this.m_index instanceof DoubleIntSortedMap) {
            final DoubleIntSortedMap doubleIntSortedMap = (DoubleIntSortedMap)this.m_index;
            final double double1 = this.m_col.getDouble(columnRow);
            if (b) {
                doubleIntSortedMap.put(double1, n);
            }
            else {
                doubleIntSortedMap.remove(double1, n);
            }
        }
        else if (this.m_index instanceof BooleanIntSortedMap) {
            final BooleanIntSortedMap booleanIntSortedMap = (BooleanIntSortedMap)this.m_index;
            final boolean boolean1 = this.m_col.getBoolean(columnRow);
            if (b) {
                booleanIntSortedMap.put(boolean1, n);
            }
            else {
                booleanIntSortedMap.remove(boolean1, n);
            }
        }
        else {
            if (!(this.m_index instanceof ObjectIntSortedMap)) {
                throw new IllegalStateException();
            }
            final ObjectIntSortedMap objectIntSortedMap = (ObjectIntSortedMap)this.m_index;
            final Object value = this.m_col.get(columnRow);
            if (b) {
                objectIntSortedMap.put(value, n);
            }
            else {
                objectIntSortedMap.remove(value, n);
            }
        }
    }
    
    public void columnChanged(final Column column, final int n, final int n2, final int n3) {
        this.m_reindex = true;
    }
    
    public void columnChanged(final Column column, final int n, final boolean b) {
        final int tableRow = this.m_rows.getTableRow(n, this.getColumnIndex());
        if (tableRow < 0) {
            return;
        }
        ((BooleanIntSortedMap)this.m_index).remove(b, tableRow);
        ((BooleanIntSortedMap)this.m_index).put(column.getBoolean(n), tableRow);
    }
    
    public void columnChanged(final Column column, final int n, final int n2) {
        final int tableRow = this.m_rows.getTableRow(n, this.getColumnIndex());
        if (tableRow < 0) {
            return;
        }
        ((IntIntSortedMap)this.m_index).remove(n2, tableRow);
        ((IntIntSortedMap)this.m_index).put(column.getInt(n), tableRow);
    }
    
    public void columnChanged(final Column column, final int n, final long n2) {
        final int tableRow = this.m_rows.getTableRow(n, this.getColumnIndex());
        if (tableRow < 0) {
            return;
        }
        ((LongIntSortedMap)this.m_index).remove(n2, tableRow);
        ((LongIntSortedMap)this.m_index).put(column.getLong(n), tableRow);
    }
    
    public void columnChanged(final Column column, final int n, final float n2) {
        final int tableRow = this.m_rows.getTableRow(n, this.getColumnIndex());
        if (tableRow < 0) {
            return;
        }
        ((FloatIntSortedMap)this.m_index).remove(n2, tableRow);
        ((FloatIntSortedMap)this.m_index).put(column.getFloat(n), tableRow);
    }
    
    public void columnChanged(final Column column, final int n, final double n2) {
        final int tableRow = this.m_rows.getTableRow(n, this.getColumnIndex());
        if (tableRow < 0) {
            return;
        }
        ((DoubleIntSortedMap)this.m_index).remove(n2, tableRow);
        ((DoubleIntSortedMap)this.m_index).put(column.getDouble(n), tableRow);
    }
    
    public void columnChanged(final Column column, final int n, final Object o) {
        final int tableRow = this.m_rows.getTableRow(n, this.getColumnIndex());
        if (tableRow < 0) {
            return;
        }
        ((ObjectIntSortedMap)this.m_index).remove(o, tableRow);
        ((ObjectIntSortedMap)this.m_index).put(column.get(n), tableRow);
    }
    
    public int minimum() {
        return this.m_index.getMinimum();
    }
    
    public int maximum() {
        return this.m_index.getMaximum();
    }
    
    public int median() {
        return this.m_index.getMedian();
    }
    
    public int uniqueCount() {
        return this.m_index.getUniqueCount();
    }
    
    public IntIterator allRows(final int n) {
        return this.m_index.valueIterator((n & 0x20) > 0);
    }
    
    public IntIterator rows(Object min_KEY, Object max_KEY, final int n) {
        if (!(this.m_index instanceof ObjectIntSortedMap)) {
            throw new IllegalStateException();
        }
        final boolean b = (n & 0x10) > 0;
        final boolean b2 = (n & 0x8) > 0;
        final boolean b3 = (n & 0x2) > 0;
        if (min_KEY == null) {
            min_KEY = ObjectIntSortedMap.MIN_KEY;
        }
        if (max_KEY == null) {
            max_KEY = ObjectIntSortedMap.MAX_KEY;
        }
        final ObjectIntSortedMap objectIntSortedMap = (ObjectIntSortedMap)this.m_index;
        if (b) {
            return objectIntSortedMap.valueRangeIterator(max_KEY, b3, min_KEY, b2);
        }
        return objectIntSortedMap.valueRangeIterator(min_KEY, b2, max_KEY, b3);
    }
    
    public IntIterator rows(final int n, final int n2, final int n3) {
        if (!(this.m_index instanceof IntIntSortedMap)) {
            throw new IllegalStateException();
        }
        final boolean b = (n3 & 0x10) > 0;
        final boolean b2 = (n3 & 0x8) > 0;
        final boolean b3 = (n3 & 0x2) > 0;
        final IntIntSortedMap intIntSortedMap = (IntIntSortedMap)this.m_index;
        if (b) {
            return intIntSortedMap.valueRangeIterator(n2, b3, n, b2);
        }
        return intIntSortedMap.valueRangeIterator(n, b2, n2, b3);
    }
    
    public IntIterator rows(final long n, final long n2, final int n3) {
        if (!(this.m_index instanceof LongIntSortedMap)) {
            throw new IllegalStateException();
        }
        final boolean b = (n3 & 0x10) > 0;
        final boolean b2 = (n3 & 0x8) > 0;
        final boolean b3 = (n3 & 0x2) > 0;
        final LongIntSortedMap longIntSortedMap = (LongIntSortedMap)this.m_index;
        if (b) {
            return longIntSortedMap.valueRangeIterator(n2, b3, n, b2);
        }
        return longIntSortedMap.valueRangeIterator(n, b2, n2, b3);
    }
    
    public IntIterator rows(final float n, final float n2, final int n3) {
        if (!(this.m_index instanceof FloatIntSortedMap)) {
            throw new IllegalStateException();
        }
        final boolean b = (n3 & 0x10) > 0;
        final boolean b2 = (n3 & 0x8) > 0;
        final boolean b3 = (n3 & 0x2) > 0;
        final FloatIntSortedMap floatIntSortedMap = (FloatIntSortedMap)this.m_index;
        if (b) {
            return floatIntSortedMap.valueRangeIterator(n2, b3, n, b2);
        }
        return floatIntSortedMap.valueRangeIterator(n, b2, n2, b3);
    }
    
    public IntIterator rows(final double n, final double n2, final int n3) {
        if (!(this.m_index instanceof DoubleIntSortedMap)) {
            throw new IllegalStateException();
        }
        final boolean b = (n3 & 0x10) > 0;
        final boolean b2 = (n3 & 0x8) > 0;
        final boolean b3 = (n3 & 0x2) > 0;
        final DoubleIntSortedMap doubleIntSortedMap = (DoubleIntSortedMap)this.m_index;
        if (b) {
            return doubleIntSortedMap.valueRangeIterator(n2, b3, n, b2);
        }
        return doubleIntSortedMap.valueRangeIterator(n, b2, n2, b3);
    }
    
    public IntIterator rows(final int n) {
        return this.rows(n, n, 42);
    }
    
    public IntIterator rows(final long n) {
        return this.rows(n, n, 42);
    }
    
    public IntIterator rows(final float n) {
        return this.rows(n, n, 42);
    }
    
    public IntIterator rows(final double n) {
        return this.rows(n, n, 42);
    }
    
    public IntIterator rows(final boolean b) {
        if (!(this.m_index instanceof BooleanIntSortedMap)) {
            throw new IllegalStateException();
        }
        return ((BooleanIntSortedMap)this.m_index).valueRangeIterator(b, true, b, true);
    }
    
    public IntIterator rows(final Object o) {
        return this.rows(o, o, 42);
    }
    
    public int get(final double n) {
        return ((DoubleIntSortedMap)this.m_index).get(n);
    }
    
    public int get(final float n) {
        return ((FloatIntSortedMap)this.m_index).get(n);
    }
    
    public int get(final int n) {
        return ((IntIntSortedMap)this.m_index).get(n);
    }
    
    public int get(final long n) {
        return ((LongIntSortedMap)this.m_index).get(n);
    }
    
    public int get(final Object o) {
        return ((ObjectIntSortedMap)this.m_index).get(o);
    }
}
