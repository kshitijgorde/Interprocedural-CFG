// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.column;

import prefuse.data.DataTypeException;
import java.util.Iterator;
import prefuse.data.expression.ExpressionAnalyzer;
import prefuse.data.event.ColumnListener;
import prefuse.data.event.ExpressionListener;
import java.util.BitSet;
import java.util.Set;
import prefuse.data.Table;
import prefuse.data.expression.Expression;

public class ExpressionColumn extends AbstractColumn
{
    private Expression m_expr;
    private Table m_table;
    private Set m_columns;
    private BitSet m_valid;
    private Column m_cache;
    private Listener m_lstnr;
    
    public ExpressionColumn(final Table table, final Expression expr) {
        super(expr.getType(table.getSchema()));
        this.m_table = table;
        this.m_expr = expr;
        this.m_lstnr = new Listener();
        this.init();
        final int rowCount = this.m_table.getRowCount();
        this.m_cache = ColumnFactory.getColumn(this.getColumnType(), rowCount);
        this.m_valid = new BitSet(rowCount);
        this.m_expr.addExpressionListener(this.m_lstnr);
    }
    
    protected void init() {
        if (this.m_columns != null && this.m_columns.size() > 0) {
            final Iterator<String> iterator = this.m_columns.iterator();
            while (iterator.hasNext()) {
                this.m_table.getColumn(iterator.next()).removeColumnListener(this.m_lstnr);
            }
        }
        this.m_columns = ExpressionAnalyzer.getReferencedColumns(this.m_expr);
        for (final String s : this.m_columns) {
            if (this.m_table.getColumn(s) == null) {
                throw new IllegalArgumentException("Table must contain all columns referenced by the expression. Bad column name: " + s);
            }
        }
        final Iterator<String> iterator3 = this.m_columns.iterator();
        while (iterator3.hasNext()) {
            this.m_table.getColumn(iterator3.next()).addColumnListener(this.m_lstnr);
        }
    }
    
    public int getRowCount() {
        return this.m_cache.getRowCount();
    }
    
    public void setMaximumRow(final int maximumRow) {
        this.m_cache.setMaximumRow(maximumRow);
    }
    
    public boolean isCacheValid(final int n) {
        return this.m_valid.get(n);
    }
    
    public void invalidateCache(final int n, final int n2) {
        this.m_valid.clear(n, n2 + 1);
    }
    
    public void revertToDefault(final int n) {
    }
    
    public boolean canSet(final Class clazz) {
        return false;
    }
    
    public Object get(final int n) {
        this.rangeCheck(n);
        if (this.isCacheValid(n)) {
            return this.m_cache.get(n);
        }
        final Object value = this.m_expr.get(this.m_table.getTuple(n));
        if (this.m_cache.canSet((value == null) ? Object.class : value.getClass())) {
            this.m_cache.set(value, n);
            this.m_valid.set(n);
        }
        return value;
    }
    
    public void set(final Object o, final int n) throws DataTypeException {
        throw new UnsupportedOperationException();
    }
    
    private void rangeCheck(final int n) {
        if (n < 0 || n >= this.getRowCount()) {
            throw new IndexOutOfBoundsException();
        }
    }
    
    public boolean getBoolean(final int n) throws DataTypeException {
        if (!this.canGetBoolean()) {
            throw new DataTypeException(Boolean.TYPE);
        }
        this.rangeCheck(n);
        if (this.isCacheValid(n)) {
            return this.m_cache.getBoolean(n);
        }
        final boolean boolean1 = this.m_expr.getBoolean(this.m_table.getTuple(n));
        this.m_cache.setBoolean(boolean1, n);
        this.m_valid.set(n);
        return boolean1;
    }
    
    private void computeNumber(final int n) {
        if (this.m_columnType == Integer.TYPE || this.m_columnType == Byte.TYPE) {
            this.m_cache.setInt(this.m_expr.getInt(this.m_table.getTuple(n)), n);
        }
        else if (this.m_columnType == Long.TYPE) {
            this.m_cache.setLong(this.m_expr.getLong(this.m_table.getTuple(n)), n);
        }
        else if (this.m_columnType == Float.TYPE) {
            this.m_cache.setFloat(this.m_expr.getFloat(this.m_table.getTuple(n)), n);
        }
        else {
            this.m_cache.setDouble(this.m_expr.getDouble(this.m_table.getTuple(n)), n);
        }
        this.m_valid.set(n);
    }
    
    public int getInt(final int n) throws DataTypeException {
        if (!this.canGetInt()) {
            throw new DataTypeException(Integer.TYPE);
        }
        this.rangeCheck(n);
        if (!this.isCacheValid(n)) {
            this.computeNumber(n);
        }
        return this.m_cache.getInt(n);
    }
    
    public double getDouble(final int n) throws DataTypeException {
        if (!this.canGetDouble()) {
            throw new DataTypeException(Double.TYPE);
        }
        this.rangeCheck(n);
        if (!this.isCacheValid(n)) {
            this.computeNumber(n);
        }
        return this.m_cache.getDouble(n);
    }
    
    public float getFloat(final int n) throws DataTypeException {
        if (!this.canGetFloat()) {
            throw new DataTypeException(Float.TYPE);
        }
        this.rangeCheck(n);
        if (!this.isCacheValid(n)) {
            this.computeNumber(n);
        }
        return this.m_cache.getFloat(n);
    }
    
    public long getLong(final int n) throws DataTypeException {
        if (!this.canGetLong()) {
            throw new DataTypeException(Long.TYPE);
        }
        this.rangeCheck(n);
        if (!this.isCacheValid(n)) {
            this.computeNumber(n);
        }
        return this.m_cache.getLong(n);
    }
    
    private class Listener implements ColumnListener, ExpressionListener
    {
        public void columnChanged(final int n, final int n2) {
            if (n == n2 && ExpressionColumn.this.isCacheValid(n)) {
                if (!ExpressionColumn.this.m_table.isValidRow(n)) {
                    return;
                }
                ExpressionColumn.this.invalidateCache(n, n2);
                final Class columnType = ExpressionColumn.this.getColumnType();
                if (Integer.TYPE == columnType) {
                    ExpressionColumn.this.fireColumnEvent(n, ExpressionColumn.this.m_cache.getInt(n));
                }
                else if (Long.TYPE == columnType) {
                    ExpressionColumn.this.fireColumnEvent(n, ExpressionColumn.this.m_cache.getLong(n));
                }
                else if (Float.TYPE == columnType) {
                    ExpressionColumn.this.fireColumnEvent(n, ExpressionColumn.this.m_cache.getFloat(n));
                }
                else if (Double.TYPE == columnType) {
                    ExpressionColumn.this.fireColumnEvent(n, ExpressionColumn.this.m_cache.getDouble(n));
                }
                else if (Boolean.TYPE == columnType) {
                    ExpressionColumn.this.fireColumnEvent(n, ExpressionColumn.this.m_cache.getBoolean(n));
                }
                else {
                    ExpressionColumn.this.fireColumnEvent(n, ExpressionColumn.this.m_cache.get(n));
                }
            }
            else {
                ExpressionColumn.this.invalidateCache(n, n2);
                ExpressionColumn.this.fireColumnEvent(0, n, n2);
            }
        }
        
        public void columnChanged(final Column column, final int n, final boolean b) {
            this.columnChanged(n, n);
        }
        
        public void columnChanged(final Column column, final int n, final double n2) {
            this.columnChanged(n, n);
        }
        
        public void columnChanged(final Column column, final int n, final float n2) {
            this.columnChanged(n, n);
        }
        
        public void columnChanged(final Column column, final int n, final int n2, final int n3) {
            this.columnChanged(n2, n3);
        }
        
        public void columnChanged(final Column column, final int n, final int n2) {
            this.columnChanged(n, n);
        }
        
        public void columnChanged(final Column column, final int n, final long n2) {
            this.columnChanged(n, n);
        }
        
        public void columnChanged(final Column column, final int n, final Object o) {
            this.columnChanged(n, n);
        }
        
        public void expressionChanged(final Expression expression) {
            this.columnChanged(0, ExpressionColumn.this.m_cache.getRowCount() - 1);
            ExpressionColumn.this.init();
        }
    }
}
