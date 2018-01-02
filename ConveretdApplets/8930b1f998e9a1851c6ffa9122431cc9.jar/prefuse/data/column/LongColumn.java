// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.column;

import prefuse.data.DataTypeException;
import prefuse.data.DataReadOnlyException;
import java.util.Arrays;

public class LongColumn extends AbstractColumn
{
    private long[] m_values;
    private int m_size;
    
    public LongColumn() {
        this(0, 10, 0L);
    }
    
    public LongColumn(final int n) {
        this(n, n, 0L);
    }
    
    public LongColumn(final int size, final int n, final long n2) {
        super(Long.TYPE, new Long(n2));
        if (n < size) {
            throw new IllegalArgumentException("Capacity value can not be less than the row count.");
        }
        Arrays.fill(this.m_values = new long[n], n2);
        this.m_size = size;
    }
    
    public int getRowCount() {
        return this.m_size;
    }
    
    public void setMaximumRow(final int size) {
        if (size > this.m_values.length) {
            final int max = Math.max(3 * this.m_values.length / 2 + 1, size);
            final long[] values = new long[max];
            System.arraycopy(this.m_values, 0, values, 0, this.m_size);
            Arrays.fill(values, this.m_size, max, (long)this.m_defaultValue);
            this.m_values = values;
        }
        this.m_size = size;
    }
    
    public Object get(final int n) {
        return new Long(this.getLong(n));
    }
    
    public void set(final Object o, final int n) throws DataTypeException {
        if (this.m_readOnly) {
            throw new DataReadOnlyException();
        }
        if (o != null) {
            if (o instanceof Number) {
                this.setLong(((Number)o).longValue(), n);
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
    
    public long getLong(final int n) throws DataTypeException {
        if (n < 0 || n > this.m_size) {
            throw new IllegalArgumentException("Row index out of bounds: " + n);
        }
        return this.m_values[n];
    }
    
    public void setLong(final long n, final int n2) throws DataTypeException {
        if (this.m_readOnly) {
            throw new DataReadOnlyException();
        }
        if (n2 < 0 || n2 >= this.m_size) {
            throw new IllegalArgumentException("Row index out of bounds: " + n2);
        }
        final long n3 = this.m_values[n2];
        if (n3 == n) {
            return;
        }
        this.m_values[n2] = n;
        this.fireColumnEvent(n2, n3);
    }
    
    public int getInt(final int n) throws DataTypeException {
        return (int)this.getLong(n);
    }
    
    public float getFloat(final int n) throws DataTypeException {
        return this.getLong(n);
    }
    
    public double getDouble(final int n) throws DataTypeException {
        return this.getLong(n);
    }
}
