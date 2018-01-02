// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data;

import prefuse.util.PrefuseLib;
import java.util.HashMap;

public class Schema implements Cloneable
{
    private String[] m_names;
    private Class[] m_types;
    private Object[] m_dflts;
    private HashMap m_lookup;
    private int m_size;
    private boolean m_locked;
    
    public Schema() {
        this(10);
    }
    
    public Schema(final int n) {
        this.m_names = new String[n];
        this.m_types = new Class[n];
        this.m_dflts = new Object[n];
        this.m_size = 0;
        this.m_locked = false;
    }
    
    public Schema(final String[] array, final Class[] array2) {
        this(array.length);
        if (array.length != array2.length) {
            throw new IllegalArgumentException("Input arrays should be the same length");
        }
        for (int i = 0; i < array.length; ++i) {
            this.addColumn(array[i], array2[i], null);
        }
    }
    
    public Schema(final String[] array, final Class[] array2, final Object[] array3) {
        this(array.length);
        if (array.length != array2.length || array2.length != array3.length) {
            throw new IllegalArgumentException("Input arrays should be the same length");
        }
        for (int i = 0; i < array.length; ++i) {
            this.addColumn(array[i], array2[i], array3[i]);
        }
    }
    
    public Object clone() {
        final Schema schema = new Schema(this.m_size);
        for (int i = 0; i < this.m_size; ++i) {
            schema.addColumn(this.m_names[i], this.m_types[i], this.m_dflts[i]);
        }
        return schema;
    }
    
    protected void initLookup() {
        this.m_lookup = new HashMap();
        for (int i = 0; i < this.m_names.length; ++i) {
            this.m_lookup.put(this.m_names[i], new Integer(i));
        }
    }
    
    public Schema lockSchema() {
        this.m_locked = true;
        return this;
    }
    
    public boolean isLocked() {
        return this.m_locked;
    }
    
    public void addColumn(final String s, final Class clazz) {
        this.addColumn(s, clazz, null);
    }
    
    public void addColumn(final String s, final Class clazz, final Object o) {
        if (this.m_locked) {
            throw new IllegalStateException("Can not add column to a locked Schema.");
        }
        if (s == null) {
            throw new IllegalArgumentException("Null column names are not allowed.");
        }
        if (clazz == null) {
            throw new IllegalArgumentException("Null column types are not allowed.");
        }
        for (int i = 0; i < this.m_size; ++i) {
            if (this.m_names[i].equals(s)) {
                throw new IllegalArgumentException("Duplicate column names are not allowed: " + this.m_names[i]);
            }
        }
        if (this.m_names.length == this.m_size) {
            final int n = 3 * this.m_names.length / 2 + 1;
            final String[] names = new String[n];
            final Class[] types = new Class[n];
            final Object[] dflts = new Object[n];
            System.arraycopy(this.m_names, 0, names, 0, this.m_size);
            System.arraycopy(this.m_types, 0, types, 0, this.m_size);
            System.arraycopy(this.m_dflts, 0, dflts, 0, this.m_size);
            this.m_names = names;
            this.m_types = types;
            this.m_dflts = dflts;
        }
        this.m_names[this.m_size] = s;
        this.m_types[this.m_size] = clazz;
        this.m_dflts[this.m_size] = o;
        if (this.m_lookup != null) {
            this.m_lookup.put(s, new Integer(this.m_size));
        }
        ++this.m_size;
    }
    
    public void addInterpolatedColumn(final String s, final Class clazz, final Object o) {
        this.addColumn(s, clazz, o);
        this.addColumn(PrefuseLib.getStartField(s), clazz, o);
        this.addColumn(PrefuseLib.getEndField(s), clazz, o);
    }
    
    public void addInterpolatedColumn(final String s, final Class clazz) {
        this.addInterpolatedColumn(s, clazz, null);
    }
    
    public int getColumnCount() {
        return this.m_size;
    }
    
    public String getColumnName(final int n) {
        return this.m_names[n];
    }
    
    public int getColumnIndex(final String s) {
        if (this.m_lookup == null) {
            this.initLookup();
        }
        final Integer n = this.m_lookup.get(s);
        return (n == null) ? -1 : n;
    }
    
    public Class getColumnType(final int n) {
        return this.m_types[n];
    }
    
    public Class getColumnType(final String s) {
        final int columnIndex = this.getColumnIndex(s);
        return (columnIndex < 0) ? null : this.m_types[columnIndex];
    }
    
    public Object getDefault(final int n) {
        return this.m_dflts[n];
    }
    
    public Object getDefault(final String s) {
        final int columnIndex = this.getColumnIndex(s);
        return (columnIndex < 0) ? null : this.m_dflts[columnIndex];
    }
    
    public void setDefault(final int n, final Object o) {
        if (this.m_locked) {
            throw new IllegalStateException("Can not update default values of a locked Schema.");
        }
        this.m_dflts[n] = o;
    }
    
    public void setDefault(final String s, final Object o) {
        if (this.m_locked) {
            throw new IllegalStateException("Can not update default values of a locked Schema.");
        }
        this.m_dflts[this.getColumnIndex(s)] = o;
    }
    
    public void setDefault(final String s, final int n) {
        this.setDefault(s, new Integer(n));
    }
    
    public void setDefault(final String s, final long n) {
        this.setDefault(s, new Long(n));
    }
    
    public void setDefault(final String s, final float n) {
        this.setDefault(s, new Float(n));
    }
    
    public void setDefault(final String s, final double n) {
        this.setDefault(s, new Double(n));
    }
    
    public void setDefault(final String s, final boolean b) {
        this.setDefault(s, b ? Boolean.TRUE : Boolean.FALSE);
    }
    
    public void setInterpolatedDefault(final String s, final Object o) {
        this.setDefault(s, o);
        this.setDefault(PrefuseLib.getStartField(s), o);
        this.setDefault(PrefuseLib.getEndField(s), o);
    }
    
    public void setInterpolatedDefault(final String s, final int n) {
        this.setInterpolatedDefault(s, new Integer(n));
    }
    
    public void setInterpolatedDefault(final String s, final long n) {
        this.setInterpolatedDefault(s, new Long(n));
    }
    
    public void setInterpolatedDefault(final String s, final float n) {
        this.setInterpolatedDefault(s, new Float(n));
    }
    
    public void setInterpolatedDefault(final String s, final double n) {
        this.setInterpolatedDefault(s, new Double(n));
    }
    
    public void setInterpolatedDefault(final String s, final boolean b) {
        this.setInterpolatedDefault(s, b ? Boolean.TRUE : Boolean.FALSE);
    }
    
    public boolean equals(final Object o) {
        if (!(o instanceof Schema)) {
            return false;
        }
        final Schema schema = (Schema)o;
        if (this.m_size != schema.getColumnCount()) {
            return false;
        }
        for (int i = 0; i < this.m_size; ++i) {
            if (!this.m_names[i].equals(schema.getColumnName(i)) || !this.m_types[i].equals(schema.getColumnType(i)) || !this.m_dflts[i].equals(schema.getDefault(i))) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isAssignableFrom(final Schema schema) {
        final int columnCount = schema.getColumnCount();
        if (columnCount > this.m_size) {
            return false;
        }
        for (int i = 0; i < columnCount; ++i) {
            final int columnIndex = this.getColumnIndex(schema.getColumnName(i));
            if (columnIndex < 0) {
                return false;
            }
            if (!this.m_types[columnIndex].equals(schema.getColumnType(i))) {
                return false;
            }
        }
        return true;
    }
    
    public int hashCode() {
        int n = 0;
        for (int i = 0; i < this.m_size; ++i) {
            final int n2 = i + 1;
            int n3 = n2 * this.m_names[i].hashCode() ^ n2 * this.m_types[i].hashCode();
            if (this.m_dflts[i] != null) {
                n3 ^= this.m_dflts[i].hashCode();
            }
            n ^= n3;
        }
        return n;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Schema[");
        for (int i = 0; i < this.m_size; ++i) {
            if (i > 0) {
                sb.append(' ');
            }
            sb.append('(').append(this.m_names[i]).append(", ");
            sb.append(this.m_types[i].getName()).append(", ");
            sb.append(this.m_dflts[i]).append(')');
        }
        sb.append(']');
        return sb.toString();
    }
    
    public Table instantiate() {
        return this.instantiate(0);
    }
    
    public Table instantiate(final int n) {
        final Table table = new Table(n, this.m_size);
        for (int i = 0; i < this.m_size; ++i) {
            table.addColumn(this.m_names[i], this.m_types[i], this.m_dflts[i]);
        }
        return table;
    }
}
