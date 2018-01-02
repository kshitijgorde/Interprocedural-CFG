// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.util;

import prefuse.data.Schema;
import prefuse.util.collections.CompositeComparator;
import prefuse.data.Tuple;
import prefuse.util.collections.NullComparator;
import prefuse.data.Table;
import java.util.Comparator;
import prefuse.data.tuple.TupleSet;
import java.util.Arrays;

public class Sort
{
    private static final String ASC = " ASC";
    private static final String DESC = " DESC";
    private static final String asc;
    private static final String desc;
    private String[] m_fields;
    private boolean[] m_ascend;
    
    public Sort() {
        this(new String[0], new boolean[0]);
    }
    
    public Sort(final String[] array) {
        this(array, new boolean[array.length]);
        Arrays.fill(this.m_ascend, true);
    }
    
    public Sort(final String[] fields, final boolean[] ascend) {
        this.m_fields = fields;
        this.m_ascend = ascend;
    }
    
    public void add(final String s, final boolean b) {
        final String[] fields = new String[this.m_fields.length + 1];
        System.arraycopy(this.m_fields, 0, fields, 0, this.m_fields.length);
        fields[this.m_fields.length] = s;
        this.m_fields = fields;
        final boolean[] ascend = new boolean[this.m_fields.length + 1];
        System.arraycopy(this.m_ascend, 0, ascend, 0, this.m_ascend.length);
        ascend[this.m_ascend.length] = b;
        this.m_ascend = ascend;
    }
    
    public int size() {
        return this.m_fields.length;
    }
    
    public String getField(final int n) {
        return this.m_fields[n];
    }
    
    public boolean isAscending(final int n) {
        return this.m_ascend[n];
    }
    
    public Comparator getComparator(final TupleSet set) {
        Schema schema;
        if (set instanceof Table) {
            schema = ((Table)set).getSchema();
        }
        else {
            if (set.getTupleCount() == 0) {
                return new NullComparator();
            }
            schema = set.tuples().next().getSchema();
        }
        final CompositeComparator compositeComparator = new CompositeComparator(this.m_fields.length);
        for (int i = 0; i < this.m_fields.length; ++i) {
            compositeComparator.add(new TupleComparator(this.m_fields[i], schema.getColumnType(this.m_fields[i]), this.m_ascend[i]));
        }
        return compositeComparator;
    }
    
    private static void subparse(String s, final Object[] array) {
        s = s.trim();
        array[1] = Boolean.TRUE;
        if (s.endsWith(" DESC") || s.endsWith(Sort.desc)) {
            array[1] = Boolean.FALSE;
            s = s.substring(0, s.length() - " DESC".length()).trim();
        }
        else if (s.endsWith(" ASC") || s.endsWith(Sort.asc)) {
            s = s.substring(0, s.length() - " ASC".length()).trim();
        }
        if (s.startsWith("[")) {
            if (s.lastIndexOf("[") != 0 || !s.endsWith("]") || s.indexOf("]") != s.length()) {
                throw new RuntimeException();
            }
            array[0] = s.substring(1, s.length() - 1);
        }
        else {
            if (s.indexOf(" ") >= 0 || s.indexOf("\t") >= 0) {
                throw new RuntimeException();
            }
            array[0] = s;
        }
    }
    
    public static Sort parse(final String s) {
        final Sort sort = new Sort();
        final Object[] array = new Object[2];
        int i = 0;
        final int length = s.length();
        int n = s.indexOf(44);
        final int index = s.indexOf(91);
        while (i < length) {
            if (n < 0) {
                subparse(s.substring(i), array);
                sort.add((String)array[0], (boolean)array[1]);
                break;
            }
            if (index < 0 || n < index) {
                subparse(s.substring(i, n), array);
                sort.add((String)array[0], (boolean)array[1]);
                i = n + 1;
                n = s.indexOf(i, 44);
            }
            else {
                final int index2 = s.indexOf(index, 93);
                if (index2 < 0) {
                    throw new RuntimeException();
                }
                final int index3 = s.indexOf(index2, 44);
                subparse(s.substring(i, index3), array);
                sort.add((String)array[0], (boolean)array[1]);
                i = index3 + 1;
                n = s.indexOf(i, 44);
            }
        }
        return sort;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.m_fields.length; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append('[').append(this.m_fields[i]).append(']');
            sb.append(this.m_ascend[i] ? " ASC" : " DESC");
        }
        return sb.toString();
    }
    
    static {
        asc = " ASC".toLowerCase();
        desc = " DESC".toLowerCase();
    }
}
