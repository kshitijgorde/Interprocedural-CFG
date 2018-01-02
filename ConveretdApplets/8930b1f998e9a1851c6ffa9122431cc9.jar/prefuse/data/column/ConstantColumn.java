// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.column;

import prefuse.data.event.ColumnListener;
import prefuse.data.DataTypeException;

public class ConstantColumn extends AbstractColumn
{
    private int m_size;
    
    public ConstantColumn(final Class clazz, final Object o) {
        super(clazz, o);
    }
    
    public int getRowCount() {
        return this.m_size + 1;
    }
    
    public void setMaximumRow(final int size) {
        this.m_size = size;
    }
    
    public Object get(final int n) {
        if (n < 0 || n > this.m_size) {
            throw new IllegalArgumentException("Row index out of bounds: " + n);
        }
        return super.m_defaultValue;
    }
    
    public void set(final Object o, final int n) throws DataTypeException {
        throw new UnsupportedOperationException("Can't set values on a ConstantColumn");
    }
    
    public boolean canSet(final Class clazz) {
        return false;
    }
    
    public void addColumnListener(final ColumnListener columnListener) {
    }
    
    public void removeColumnListener(final ColumnListener columnListener) {
    }
}
