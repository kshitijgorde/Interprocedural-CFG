// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.collections;

import prefuse.data.DataTypeException;
import java.util.Date;
import java.util.Comparator;

public class SortedMapFactory
{
    public static IntSortedMap getMap(final Class clazz, final Comparator comparator, final boolean b) throws IncompatibleComparatorException {
        if (!comparatorCheck(clazz, comparator)) {
            throw new IncompatibleComparatorException();
        }
        if (clazz.equals(Integer.TYPE) || clazz.equals(Byte.TYPE)) {
            return new IntIntTreeMap((LiteralComparator)comparator, !b);
        }
        if (clazz.equals(Long.TYPE) || clazz.isAssignableFrom(Date.class)) {
            return new LongIntTreeMap((LiteralComparator)comparator, !b);
        }
        if (clazz.equals(Float.TYPE)) {
            return new FloatIntTreeMap((LiteralComparator)comparator, !b);
        }
        if (clazz.equals(Double.TYPE)) {
            return new DoubleIntTreeMap((LiteralComparator)comparator, !b);
        }
        if (clazz.equals(Boolean.TYPE)) {
            return new BooleanIntBitSetMap();
        }
        if (Object.class.isAssignableFrom(clazz)) {
            return new ObjectIntTreeMap(comparator, !b);
        }
        throw new DataTypeException("No map available for the provided type");
    }
    
    public static boolean comparatorCheck(final Class clazz, final Comparator comparator) {
        if (comparator == null) {
            return true;
        }
        if (clazz.equals(Integer.TYPE)) {
            if (!(comparator instanceof LiteralIterator)) {
                return false;
            }
            try {
                ((LiteralComparator)comparator).compare(0, 0);
                return true;
            }
            catch (Exception ex) {
                return false;
            }
        }
        if (clazz.equals(Long.TYPE)) {
            if (!(comparator instanceof LiteralIterator)) {
                return false;
            }
            try {
                ((LiteralComparator)comparator).compare(0L, 0L);
                return true;
            }
            catch (Exception ex2) {
                return false;
            }
        }
        if (clazz.equals(Float.TYPE)) {
            if (!(comparator instanceof LiteralIterator)) {
                return false;
            }
            try {
                ((LiteralComparator)comparator).compare(0.0f, 0.0f);
                return true;
            }
            catch (Exception ex3) {
                return false;
            }
        }
        if (clazz.equals(Double.TYPE)) {
            if (!(comparator instanceof LiteralIterator)) {
                return false;
            }
            try {
                ((LiteralComparator)comparator).compare(0.0, 0.0);
                return true;
            }
            catch (Exception ex4) {
                return false;
            }
        }
        if (clazz.equals(Boolean.TYPE)) {
            if (!(comparator instanceof LiteralIterator)) {
                return false;
            }
            try {
                ((LiteralComparator)comparator).compare(false, false);
                return true;
            }
            catch (Exception ex5) {
                return false;
            }
        }
        if (Object.class.isAssignableFrom(clazz)) {
            return true;
        }
        throw new DataTypeException("No comparator available for the provided type");
    }
}
