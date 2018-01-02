// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.util;

import java.util.Iterator;
import java.util.List;

public abstract class ObjectUtils
{
    public static boolean equal(final Object o, final Object o2) {
        if (o != null) {
            return o.equals(o2);
        }
        return o2 == null;
    }
    
    public static int hashCode(final Object o) {
        int hashCode = 0;
        if (o != null) {
            hashCode = o.hashCode();
        }
        return hashCode;
    }
    
    public static Object clone(final Object o) throws CloneNotSupportedException {
        Object clone = o;
        if (o != null && o instanceof PublicCloneable) {
            clone = ((PublicCloneable)o).clone();
        }
        return clone;
    }
    
    public static List clone(final List list) throws CloneNotSupportedException {
        List<Object> list2 = null;
        if (list != null) {
            try {
                final List list3 = (List)list.getClass().newInstance();
                final Iterator<Object> iterator = list.iterator();
                while (iterator.hasNext()) {
                    list3.add(clone(iterator.next()));
                }
                list2 = (List<Object>)list3;
            }
            catch (Exception ex) {
                throw new CloneNotSupportedException("ObjectUtils.clone(List) - Exception.");
            }
        }
        return list2;
    }
    
    public static boolean equalOrBothNull(final Object o, final Object o2) {
        if (o != null) {
            return o.equals(o2);
        }
        return o2 == null;
    }
}
