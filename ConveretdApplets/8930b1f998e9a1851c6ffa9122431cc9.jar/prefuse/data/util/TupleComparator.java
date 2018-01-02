// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.util;

import prefuse.util.collections.LiteralComparator;
import prefuse.data.Tuple;
import prefuse.util.collections.DefaultLiteralComparator;
import java.util.Comparator;

public class TupleComparator implements Comparator
{
    private String m_field;
    private int m_col;
    private Comparator m_cmp;
    private Class m_type;
    private int m_rev;
    
    public TupleComparator(final String s, final Class clazz, final boolean b) {
        this(s, clazz, b, DefaultLiteralComparator.getInstance());
    }
    
    public TupleComparator(final String field, final Class type, final boolean b, final Comparator cmp) {
        this.m_field = field;
        this.m_col = -1;
        this.m_type = type;
        this.m_rev = (b ? 1 : -1);
        this.m_cmp = cmp;
    }
    
    public TupleComparator(final int n, final Class clazz, final boolean b) {
        this(n, clazz, b, DefaultLiteralComparator.getInstance());
    }
    
    public TupleComparator(final int col, final Class type, final boolean b, final Comparator cmp) {
        this.m_field = null;
        this.m_col = col;
        this.m_type = type;
        this.m_rev = (b ? 1 : -1);
        this.m_cmp = cmp;
    }
    
    public int compare(final Object o, final Object o2) {
        final Tuple tuple = (Tuple)o;
        final Tuple tuple2 = (Tuple)o2;
        int n;
        if (this.m_col == -1) {
            if (this.m_type == Integer.TYPE || this.m_type == Byte.TYPE) {
                n = ((LiteralComparator)this.m_cmp).compare(tuple.getInt(this.m_field), tuple2.getInt(this.m_field));
            }
            else if (this.m_type == Double.TYPE) {
                n = ((LiteralComparator)this.m_cmp).compare(tuple.getDouble(this.m_field), tuple2.getDouble(this.m_field));
            }
            else if (this.m_type == Long.TYPE) {
                n = ((LiteralComparator)this.m_cmp).compare(tuple.getLong(this.m_field), tuple2.getLong(this.m_field));
            }
            else if (this.m_type == Float.TYPE) {
                n = ((LiteralComparator)this.m_cmp).compare(tuple.getFloat(this.m_field), tuple2.getFloat(this.m_field));
            }
            else if (this.m_type == Boolean.TYPE) {
                n = ((LiteralComparator)this.m_cmp).compare(tuple.getBoolean(this.m_field), tuple2.getBoolean(this.m_field));
            }
            else {
                if (this.m_type.isPrimitive()) {
                    throw new IllegalStateException("Unsupported type: " + this.m_type.getName());
                }
                n = this.m_cmp.compare(tuple.get(this.m_field), tuple2.get(this.m_field));
            }
        }
        else if (this.m_type == Integer.TYPE || this.m_type == Byte.TYPE) {
            n = ((LiteralComparator)this.m_cmp).compare(tuple.getInt(this.m_col), tuple2.getInt(this.m_col));
        }
        else if (this.m_type == Double.TYPE) {
            n = ((LiteralComparator)this.m_cmp).compare(tuple.getDouble(this.m_col), tuple2.getDouble(this.m_col));
        }
        else if (this.m_type == Long.TYPE) {
            n = ((LiteralComparator)this.m_cmp).compare(tuple.getLong(this.m_col), tuple2.getLong(this.m_col));
        }
        else if (this.m_type == Float.TYPE) {
            n = ((LiteralComparator)this.m_cmp).compare(tuple.getFloat(this.m_col), tuple2.getFloat(this.m_col));
        }
        else if (this.m_type == Boolean.TYPE) {
            n = ((LiteralComparator)this.m_cmp).compare(tuple.getBoolean(this.m_col), tuple2.getBoolean(this.m_col));
        }
        else {
            if (this.m_type.isPrimitive()) {
                throw new IllegalStateException("Unsupported type: " + this.m_type.getName());
            }
            n = this.m_cmp.compare(tuple.get(this.m_col), tuple2.get(this.m_col));
        }
        return this.m_rev * n;
    }
}
