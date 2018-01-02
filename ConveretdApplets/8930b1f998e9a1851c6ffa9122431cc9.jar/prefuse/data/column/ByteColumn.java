// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.column;

import prefuse.data.DataTypeException;
import prefuse.data.DataReadOnlyException;
import java.util.Arrays;

public class ByteColumn extends AbstractColumn
{
    private byte[] m_values;
    private int m_size;
    
    public ByteColumn() {
        this(0, 10, (byte)0);
    }
    
    public ByteColumn(final int n) {
        this(n, n, (byte)0);
    }
    
    public ByteColumn(final int size, final int n, final byte b) {
        super(Byte.TYPE, new Byte(b));
        if (n < size) {
            throw new IllegalArgumentException("Capacity value can not be less than the row count.");
        }
        Arrays.fill(this.m_values = new byte[n], b);
        this.m_size = size;
    }
    
    public int getRowCount() {
        return this.m_size;
    }
    
    public void setMaximumRow(final int size) {
        if (size > this.m_values.length) {
            final int max = Math.max(3 * this.m_values.length / 2 + 1, size);
            final byte[] values = new byte[max];
            System.arraycopy(this.m_values, 0, values, 0, this.m_size);
            Arrays.fill(values, this.m_size, max, (byte)this.m_defaultValue);
            this.m_values = values;
        }
        this.m_size = size;
    }
    
    public Object get(final int n) {
        return new Byte(this.getByte(n));
    }
    
    public void set(final Object o, final int n) throws DataTypeException {
        if (this.m_readOnly) {
            throw new DataReadOnlyException();
        }
        if (o != null) {
            if (o instanceof Number) {
                this.setInt(((Number)o).byteValue(), n);
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
    
    public byte getByte(final int n) throws DataTypeException {
        if (n < 0 || n > this.m_size) {
            throw new IllegalArgumentException("Row index out of bounds: " + n);
        }
        return this.m_values[n];
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
        final byte b = this.m_values[n2];
        if (b == n) {
            return;
        }
        this.m_values[n2] = (byte)n;
        this.fireColumnEvent(n2, b);
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
