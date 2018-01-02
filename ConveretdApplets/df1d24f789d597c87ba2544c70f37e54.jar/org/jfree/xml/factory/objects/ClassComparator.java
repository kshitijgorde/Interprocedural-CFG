// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

import java.io.Serializable;
import java.util.Comparator;

public class ClassComparator implements Comparator, Serializable
{
    public int compare(final Object o, final Object o2) {
        final Class clazz = (Class)o;
        final Class clazz2 = (Class)o2;
        if (clazz.equals(o2)) {
            return 0;
        }
        if (clazz.isAssignableFrom(clazz2)) {
            return -1;
        }
        if (!clazz2.isAssignableFrom(clazz2)) {
            throw new IllegalArgumentException("The classes share no relation");
        }
        return 1;
    }
    
    public boolean isComparable(final Class clazz, final Class clazz2) {
        return clazz.isAssignableFrom(clazz2) || clazz2.isAssignableFrom(clazz);
    }
}
