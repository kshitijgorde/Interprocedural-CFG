// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.column;

import prefuse.data.DataTypeException;
import prefuse.data.DataReadOnlyException;
import java.util.BitSet;

public class BooleanColumn extends AbstractColumn
{
    private BitSet m_bits;
    private int m_size;
    
    public BooleanColumn() {
        this(0, 10, false);
    }
    
    public BooleanColumn(final int n) {
        this(n, n, false);
    }
    
    public BooleanColumn(final int size, final int n, final boolean b) {
        super(Boolean.TYPE, new Boolean(b));
        if (n < size) {
            throw new IllegalArgumentException("Capacity value can not be less than the row count.");
        }
        (this.m_bits = new BitSet(n)).set(0, n, b);
        this.m_size = size;
    }
    
    public int getRowCount() {
        return this.m_size;
    }
    
    public void setMaximumRow(final int size) {
        if (size > this.m_size) {
            this.m_bits.set(this.m_size, size, (boolean)this.m_defaultValue);
        }
        this.m_size = size;
    }
    
    public Object get(final int n) {
        return new Boolean(this.getBoolean(n));
    }
    
    public void set(final Object o, final int n) throws DataTypeException {
        if (this.m_readOnly) {
            throw new DataReadOnlyException();
        }
        if (o != null) {
            if (o instanceof Boolean) {
                this.setBoolean((boolean)o, n);
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
    
    public boolean getBoolean(final int n) throws DataTypeException {
        if (n < 0 || n > this.m_size) {
            throw new IllegalArgumentException("Row index out of bounds: " + n);
        }
        return this.m_bits.get(n);
    }
    
    public void setBoolean(final boolean b, final int n) throws DataTypeException {
        if (this.m_readOnly) {
            throw new DataReadOnlyException();
        }
        if (n < 0 || n >= this.m_size) {
            throw new IllegalArgumentException("Row index out of bounds: " + n);
        }
        final boolean value = this.m_bits.get(n);
        if (value == b) {
            return;
        }
        this.m_bits.set(n, b);
        this.fireColumnEvent(n, value);
    }
}
