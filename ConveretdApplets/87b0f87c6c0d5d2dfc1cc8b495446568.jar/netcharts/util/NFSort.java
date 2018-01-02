// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.util.Vector;

public class NFSort implements NFCompare
{
    private static void a(final Vector vector, final int n, final int n2) {
        final Object element = vector.elementAt(n);
        vector.setElementAt(vector.elementAt(n2), n);
        vector.setElementAt(element, n2);
    }
    
    public int compare(final Object o, final Object o2) {
        return o.toString().compareTo(o2.toString());
    }
    
    public static void qsort(final Vector vector) {
        qsort(vector, new NFSort());
    }
    
    public static void qsort(final Vector vector, final NFCompare nfCompare) {
        qsort(vector, 0, vector.size() - 1, nfCompare);
    }
    
    public static void qsort(final Vector vector, final int n, final int n2) {
        qsort(vector, n, n2, new NFSort());
    }
    
    public static void qsort(final Vector vector, int n, final int n2, final NFCompare nfCompare) {
        if (n >= n2) {
            return;
        }
        a(vector, n, (n + n2) / 2);
        for (int i = n + 1; i <= n2; ++i) {
            if (nfCompare.compare(vector.elementAt(i), vector.elementAt(n)) < 0) {
                a(vector, ++n, i);
            }
        }
        a(vector, n, n);
        qsort(vector, n, n - 1, nfCompare);
        qsort(vector, n + 1, n2, nfCompare);
    }
    
    public static void qsort(final String[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        a(array, 0, array.length - 1);
    }
    
    private static void a(final String[] array, final int n, final int n2) {
        if (n >= n2) {
            return;
        }
        final int n3 = (n + n2) / 2;
        final String s = array[n];
        array[n] = array[n3];
        array[n3] = s;
        int n4 = n;
        for (int i = n + 1; i <= n2; ++i) {
            if (array[i].compareTo(array[n]) < 0) {
                ++n4;
                final String s2 = array[n4];
                array[n4] = array[i];
                array[i] = s2;
            }
        }
        final String s3 = array[n];
        array[n] = array[n4];
        array[n4] = s3;
        a(array, n, n4 - 1);
        a(array, n4 + 1, n2);
    }
    
    public static void main(final String[] array) {
        final String[] array2 = { "Fred", "Sally", "Amy", "Oscar", "Sammy", "Billy" };
        qsort(array2);
        for (int i = 0; i < array2.length; ++i) {
            NFDebug.print(array2[i]);
        }
        System.exit(-1);
    }
}
