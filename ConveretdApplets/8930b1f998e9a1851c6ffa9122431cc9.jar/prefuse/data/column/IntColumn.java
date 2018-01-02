// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.column;

import prefuse.data.DataTypeException;
import prefuse.data.DataReadOnlyException;
import java.util.Arrays;

public class IntColumn extends AbstractColumn
{
    private int[] m_values;
    private int m_size;
    
    public IntColumn() {
        this(0, 10, -1);
    }
    
    public IntColumn(final int n) {
        this(n, n, -1);
    }
    
    public IntColumn(final int size, final int n, final int n2) {
        super(Integer.TYPE, new Integer(n2));
        if (n < size) {
            throw new IllegalArgumentException("Capacity value can not be less than the row count.");
        }
        Arrays.fill(this.m_values = new int[n], n2);
        this.m_size = size;
    }
    
    public int getRowCount() {
        return this.m_size;
    }
    
    public void setMaximumRow(final int size) {
        if (size > this.m_values.length) {
            final int max = Math.max(3 * this.m_values.length / 2 + 1, size);
            final int[] values = new int[max];
            System.arraycopy(this.m_values, 0, values, 0, this.m_size);
            Arrays.fill(values, this.m_size, max, (int)this.m_defaultValue);
            this.m_values = values;
        }
        this.m_size = size;
    }
    
    public Object get(final int n) {
        return new Integer(this.getInt(n));
    }
    
    public void set(final Object o, final int n) throws DataTypeException {
        if (this.m_readOnly) {
            throw new DataReadOnlyException();
        }
        if (o != null) {
            if (o instanceof Number) {
                this.setInt(((Number)o).intValue(), n);
            }
            else {
                if (!(o instanceof String)) {
                    throw new DataTypeException(o.getClass());
                }
                this.setString((String)o, n);
            }
            return;
        }
        throw new DataTypeException("Column does not accept null values");
    }
    
    public int getInt(final int n) throws DataTypeException {
        if (n < 0 || n > this.m_size) {
            throw new IllegalArgumentException("Row index out of bounds: " + n);
        }
        return this.m_values[n];
    }
    
    public void setInt(final int n, final int n2) throws DataTypeException {
        if (this.m_readOnly) {
            throw new DataReadOnlyException();
        }
        if (n2 < 0 || n2 >= this.m_size) {
            throw new IllegalArgumentException("Row index out of bounds: " + n2);
        }
        final int n3 = this.m_values[n2];
        if (n3 == n) {
            return;
        }
        this.m_values[n2] = n;
        this.fireColumnEvent(n2, n3);
    }
    
    public long getLong(final int n) throws DataTypeException {
        return this.getInt(n);
    }
    
    public float getFloat(final int n) throws DataTypeException {
        return this.getInt(n);
    }
    
    public double getDouble(final int n) throws DataTypeException {
        return this.getInt(n);
    }
}
