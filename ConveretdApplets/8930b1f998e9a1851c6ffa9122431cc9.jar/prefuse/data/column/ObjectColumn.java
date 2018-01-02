// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.column;

import prefuse.data.DataTypeException;
import prefuse.data.DataReadOnlyException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Logger;

public class ObjectColumn extends AbstractColumn
{
    private Object[] m_values;
    private int m_size;
    
    public ObjectColumn() {
        this(Object.class);
    }
    
    public ObjectColumn(final Class clazz) {
        this(clazz, 0, 10, null);
    }
    
    public ObjectColumn(final int n) {
        this(Object.class, n, n, null);
    }
    
    public ObjectColumn(final Class clazz, final int n) {
        this(clazz, n, n, null);
    }
    
    public ObjectColumn(final Class clazz, final int size, final int n, final Object o) {
        super(clazz, o);
        if (n < size) {
            throw new IllegalArgumentException("Capacity value can not be less than the row count.");
        }
        this.m_values = new Object[n];
        try {
            final Method method = ((Cloneable)o).getClass().getMethod("clone", (Class<?>[])null);
            for (int i = 0; i < n; ++i) {
                this.m_values[i] = method.invoke(this.m_defaultValue, (Object[])null);
            }
        }
        catch (Exception ex) {
            if (o != null) {
                Logger.getLogger(this.getClass().getName()).fine("Default value of type \"" + o.getClass().getName() + "\" is not " + "cloneable. Using Object reference directly.");
            }
            Arrays.fill(this.m_values, o);
        }
        this.m_size = size;
    }
    
    public int getRowCount() {
        return this.m_size;
    }
    
    public void setMaximumRow(final int size) {
        if (size > this.m_values.length) {
            final int max = Math.max(3 * this.m_values.length / 2 + 1, size);
            final Object[] values = new Object[max];
            System.arraycopy(this.m_values, 0, values, 0, this.m_size);
            try {
                final Method method = ((Cloneable)this.m_defaultValue).getClass().getMethod("clone", (Class<?>[])null);
                for (int i = this.m_size; i < max; ++i) {
                    values[i] = method.invoke(this.m_defaultValue, (Object[])null);
                }
            }
            catch (Exception ex) {
                Arrays.fill(values, this.m_size, max, this.m_defaultValue);
            }
            this.m_values = values;
        }
        this.m_size = size;
    }
    
    public void revertToDefault(final int n) {
        try {
            this.set(((Cloneable)this.m_defaultValue).getClass().getMethod("clone", (Class<?>[])null).invoke(this.m_defaultValue, (Object[])null), n);
        }
        catch (Exception ex) {
            this.set(this.m_defaultValue, n);
        }
    }
    
    public Object get(final int n) {
        if (n < 0 || n > this.m_size) {
            throw new IllegalArgumentException("Row index out of bounds: " + n);
        }
        return this.m_values[n];
    }
    
    public void set(final Object o, final int n) {
        if (this.m_readOnly) {
            throw new DataReadOnlyException();
        }
        if (n < 0 || n > this.m_size) {
            throw new IllegalArgumentException("Row index out of bounds: " + n);
        }
        if (o != null && !this.canSet(o.getClass())) {
            throw new DataTypeException(o.getClass());
        }
        final Object o2 = this.m_values[n];
        if (o2 == o) {
            return;
        }
        this.m_values[n] = o;
        this.fireColumnEvent(n, o2);
    }
}
