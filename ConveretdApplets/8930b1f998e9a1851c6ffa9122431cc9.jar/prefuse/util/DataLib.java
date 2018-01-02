// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util;

import java.util.HashMap;
import java.util.Map;
import prefuse.data.Table;
import prefuse.data.tuple.TupleSet;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Comparator;
import prefuse.util.collections.DefaultLiteralComparator;
import prefuse.data.Tuple;
import java.util.Iterator;

public class DataLib
{
    public static Object[] toArray(final Iterator iterator, final String s) {
        Object[] resize = new Object[100];
        int n = 0;
        while (iterator.hasNext()) {
            if (n >= resize.length) {
                resize = ArrayLib.resize(resize, 3 * resize.length / 2);
            }
            resize[n] = iterator.next().get(s);
            ++n;
        }
        return ArrayLib.trim(resize, n);
    }
    
    public static double[] toDoubleArray(final Iterator iterator, final String s) {
        double[] resize = new double[100];
        int n = 0;
        while (iterator.hasNext()) {
            if (n >= resize.length) {
                resize = ArrayLib.resize(resize, 3 * resize.length / 2);
            }
            resize[n] = iterator.next().getDouble(s);
            ++n;
        }
        return ArrayLib.trim(resize, n);
    }
    
    public static Object[] ordinalArray(final Iterator iterator, final String s) {
        return ordinalArray(iterator, s, DefaultLiteralComparator.getInstance());
    }
    
    public static Object[] ordinalArray(final Iterator iterator, final String s, final Comparator comparator) {
        final HashSet<Object> set = new HashSet<Object>();
        while (iterator.hasNext()) {
            set.add(iterator.next().get(s));
        }
        final Object[] array = set.toArray();
        Arrays.sort(array, comparator);
        return array;
    }
    
    public static Object[] ordinalArray(final TupleSet set, final String s) {
        return ordinalArray(set, s, DefaultLiteralComparator.getInstance());
    }
    
    public static Object[] ordinalArray(final TupleSet set, final String s, final Comparator comparator) {
        if (set instanceof Table) {
            return ((Table)set).getMetadata(s).getOrdinalArray();
        }
        return ordinalArray(set.tuples(), s, comparator);
    }
    
    public static Map ordinalMap(final Iterator iterator, final String s) {
        return ordinalMap(iterator, s, DefaultLiteralComparator.getInstance());
    }
    
    public static Map ordinalMap(final Iterator iterator, final String s, final Comparator comparator) {
        final Object[] ordinalArray = ordinalArray(iterator, s, comparator);
        final HashMap<Object, Integer> hashMap = new HashMap<Object, Integer>();
        for (int i = 0; i < ordinalArray.length; ++i) {
            hashMap.put(ordinalArray[i], new Integer(i));
        }
        return hashMap;
    }
    
    public static Map ordinalMap(final TupleSet set, final String s) {
        return ordinalMap(set, s, DefaultLiteralComparator.getInstance());
    }
    
    public static Map ordinalMap(final TupleSet set, final String s, final Comparator comparator) {
        if (set instanceof Table) {
            return ((Table)set).getMetadata(s).getOrdinalMap();
        }
        return ordinalMap(set.tuples(), s, comparator);
    }
    
    public static int count(final Iterator iterator, final String s) {
        int n = 0;
        while (iterator.hasNext()) {
            ++n;
            iterator.next();
        }
        return n;
    }
    
    public static int uniqueCount(final Iterator iterator, final String s) {
        final HashSet<Object> set = new HashSet<Object>();
        while (iterator.hasNext()) {
            set.add(iterator.next().get(s));
        }
        return set.size();
    }
    
    public static Tuple min(final Iterator iterator, final String s) {
        return min(iterator, s, DefaultLiteralComparator.getInstance());
    }
    
    public static Tuple min(final Iterator iterator, final String s, final Comparator comparator) {
        Tuple tuple = null;
        Object value = null;
        if (iterator.hasNext()) {
            tuple = iterator.next();
            value = tuple.get(s);
        }
        while (iterator.hasNext()) {
            final Tuple tuple2 = iterator.next();
            final Object value2 = tuple2.get(s);
            if (comparator.compare(value2, value) < 0) {
                tuple = tuple2;
                value = value2;
            }
        }
        return tuple;
    }
    
    public static Tuple min(final TupleSet set, final String s, final Comparator comparator) {
        if (set instanceof Table) {
            final Table table = (Table)set;
            return table.getTuple(table.getMetadata(s).getMinimumRow());
        }
        return min(set.tuples(), s, comparator);
    }
    
    public static Tuple min(final TupleSet set, final String s) {
        return min(set, s, DefaultLiteralComparator.getInstance());
    }
    
    public static Tuple max(final Iterator iterator, final String s) {
        return max(iterator, s, DefaultLiteralComparator.getInstance());
    }
    
    public static Tuple max(final Iterator iterator, final String s, final Comparator comparator) {
        Tuple tuple = null;
        Object value = null;
        if (iterator.hasNext()) {
            tuple = iterator.next();
            value = tuple.get(s);
        }
        while (iterator.hasNext()) {
            final Tuple tuple2 = iterator.next();
            final Object value2 = tuple2.get(s);
            if (comparator.compare(value2, value) > 0) {
                tuple = tuple2;
                value = value2;
            }
        }
        return tuple;
    }
    
    public static Tuple max(final TupleSet set, final String s, final Comparator comparator) {
        if (set instanceof Table) {
            final Table table = (Table)set;
            return table.getTuple(table.getMetadata(s).getMaximumRow());
        }
        return max(set.tuples(), s, comparator);
    }
    
    public static Tuple max(final TupleSet set, final String s) {
        return max(set, s, DefaultLiteralComparator.getInstance());
    }
    
    public static Tuple median(final Iterator iterator, final String s) {
        return median(iterator, s, DefaultLiteralComparator.getInstance());
    }
    
    public static Tuple median(final Iterator iterator, final String s, final Comparator comparator) {
        Object[] resize = new Tuple[100];
        int n = 0;
        while (iterator.hasNext()) {
            if (n >= resize.length) {
                resize = ArrayLib.resize(resize, 3 * resize.length / 2);
            }
            resize[n] = iterator.next();
            ++n;
        }
        ArrayLib.trim(resize, n);
        final Object[] array = new Object[resize.length];
        final int[] array2 = new int[resize.length];
        for (int i = 0; i < resize.length; ++i) {
            array[array2[i] = i] = ((Tuple)resize[i]).get(s);
        }
        ArrayLib.sort(array, array2, comparator);
        return (Tuple)resize[array2[array2.length / 2]];
    }
    
    public static Tuple median(final TupleSet set, final String s, final Comparator comparator) {
        if (set instanceof Table) {
            final Table table = (Table)set;
            return table.getTuple(table.getMetadata(s).getMedianRow());
        }
        return median(set.tuples(), s, comparator);
    }
    
    public static Tuple median(final TupleSet set, final String s) {
        return median(set, s, DefaultLiteralComparator.getInstance());
    }
    
    public static double mean(final Iterator iterator, final String s) {
        try {
            int n = 0;
            double n2 = 0.0;
            while (iterator.hasNext()) {
                n2 += iterator.next().getDouble(s);
                ++n;
            }
            return n2 / n;
        }
        catch (Exception ex) {
            return Double.NaN;
        }
    }
    
    public static double deviation(final Iterator iterator, final String s) {
        return deviation(iterator, s, mean(iterator, s));
    }
    
    public static double deviation(final Iterator iterator, final String s, final double n) {
        try {
            int n2 = 0;
            double n3 = 0.0;
            while (iterator.hasNext()) {
                final double n4 = iterator.next().getDouble(s) - n;
                n3 += n4 * n4;
                ++n2;
            }
            return Math.sqrt(n3 / n2);
        }
        catch (Exception ex) {
            return Double.NaN;
        }
    }
    
    public static double sum(final Iterator iterator, final String s) {
        try {
            double n = 0.0;
            while (iterator.hasNext()) {
                n += iterator.next().getDouble(s);
            }
            return n;
        }
        catch (Exception ex) {
            return Double.NaN;
        }
    }
    
    public static Class inferType(final TupleSet set, final String s) {
        if (set instanceof Table) {
            return ((Table)set).getColumnType(s);
        }
        Class columnType = null;
        final Iterator tuples = set.tuples();
        while (tuples.hasNext()) {
            final Tuple tuple = tuples.next();
            if (columnType == null) {
                columnType = tuple.getColumnType(s);
            }
            else {
                final Class columnType2;
                if (columnType.equals(columnType2 = tuple.getColumnType(s))) {
                    continue;
                }
                if (columnType2.isAssignableFrom(columnType)) {
                    columnType = columnType2;
                }
                else {
                    if (!columnType.isAssignableFrom(columnType2)) {
                        throw new IllegalArgumentException("The data field [" + s + "] does not have " + "a consistent type across provided Tuples");
                    }
                    continue;
                }
            }
        }
        return columnType;
    }
}
