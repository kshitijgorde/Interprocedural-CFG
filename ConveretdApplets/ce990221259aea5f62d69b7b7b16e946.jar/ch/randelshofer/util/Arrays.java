// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.util;

public class Arrays
{
    private static void sort1(final int[] array, final int n, final int n2) {
        if (n2 < 7) {
            for (int i = n; i < n2 + n; ++i) {
                for (int n3 = i; n3 > n && array[n3 - 1] > array[n3]; --n3) {
                    swap(array, n3, n3 - 1);
                }
            }
            return;
        }
        int n4 = n + n2 / 2;
        if (n2 > 7) {
            int med3 = n;
            int med4 = n + n2 - 1;
            if (n2 > 40) {
                final int n5 = n2 / 8;
                med3 = med3(array, med3, med3 + n5, med3 + 2 * n5);
                n4 = med3(array, n4 - n5, n4, n4 + n5);
                med4 = med3(array, med4 - 2 * n5, med4 - n5, med4);
            }
            n4 = med3(array, med3, n4, med4);
        }
        final int n6 = array[n4];
        int n8;
        int n7 = n8 = n;
        int n10;
        int n9 = n10 = n + n2 - 1;
        while (true) {
            if (n8 <= n9 && array[n8] <= n6) {
                if (array[n8] == n6) {
                    swap(array, n7++, n8);
                }
                ++n8;
            }
            else {
                while (n9 >= n8 && array[n9] >= n6) {
                    if (array[n9] == n6) {
                        swap(array, n9, n10--);
                    }
                    --n9;
                }
                if (n8 > n9) {
                    break;
                }
                swap(array, n8++, n9--);
            }
        }
        final int n11 = n + n2;
        final int min = Math.min(n7 - n, n8 - n7);
        vecswap(array, n, n8 - min, min);
        final int min2 = Math.min(n10 - n9, n11 - n10 - 1);
        vecswap(array, n8, n11 - min2, min2);
        final int n12;
        if ((n12 = n8 - n7) > 1) {
            sort1(array, n, n12);
        }
        final int n13;
        if ((n13 = n10 - n9) > 1) {
            sort1(array, n11 - n13, n13);
        }
    }
    
    private static void swap(final int[] array, final int n, final int n2) {
        final int n3 = array[n];
        array[n] = array[n2];
        array[n2] = n3;
    }
    
    private static void vecswap(final int[] array, int n, int n2, final int n3) {
        for (int i = 0; i < n3; ++i, ++n, ++n2) {
            swap(array, n, n2);
        }
    }
    
    private static int med3(final int[] array, final int n, final int n2, final int n3) {
        return (array[n] < array[n2]) ? ((array[n2] < array[n3]) ? n2 : ((array[n] < array[n3]) ? n3 : n)) : ((array[n2] > array[n3]) ? n2 : ((array[n] > array[n3]) ? n3 : n));
    }
    
    public static void sort(final Object[] array) {
        mergeSort(array.clone(), array, 0, array.length);
    }
    
    public static void sort(final Object[] array, final int n, final int n2) {
        rangeCheck(array.length, n, n2);
        mergeSort(array.clone(), array, n, n2);
    }
    
    private static void mergeSort(final Object[] array, final Object[] array2, final int n, final int n2) {
        final int n3 = n2 - n;
        if (n3 < 7) {
            for (int i = n; i < n2; ++i) {
                for (int n4 = i; n4 > n && ((Comparable)array2[n4 - 1]).compareTo(array2[n4]) > 0; --n4) {
                    swap(array2, n4, n4 - 1);
                }
            }
            return;
        }
        final int n5 = (n + n2) / 2;
        mergeSort(array2, array, n, n5);
        mergeSort(array2, array, n5, n2);
        if (((Comparable)array[n5 - 1]).compareTo(array[n5]) <= 0) {
            System.arraycopy(array, n, array2, n, n3);
            return;
        }
        int j = n;
        int n6 = n;
        int n7 = n5;
        while (j < n2) {
            if (n7 >= n2 || (n6 < n5 && ((Comparable)array[n6]).compareTo(array[n7]) <= 0)) {
                array2[j] = array[n6++];
            }
            else {
                array2[j] = array[n7++];
            }
            ++j;
        }
    }
    
    private static void swap(final Object[] array, final int n, final int n2) {
        final Object o = array[n];
        array[n] = array[n2];
        array[n2] = o;
    }
    
    private static void rangeCheck(final int n, final int n2, final int n3) {
        if (n2 > n3) {
            throw new IllegalArgumentException("fromIndex(" + n2 + ") > toIndex(" + n3 + ")");
        }
        if (n2 < 0) {
            throw new ArrayIndexOutOfBoundsException(n2);
        }
        if (n3 > n) {
            throw new ArrayIndexOutOfBoundsException(n3);
        }
    }
    
    public static boolean equals(final int[] array, final int[] array2) {
        if (array == array2) {
            return true;
        }
        if (array == null || array2 == null) {
            return false;
        }
        final int length = array.length;
        if (array2.length != length) {
            return false;
        }
        for (int i = 0; i < length; ++i) {
            if (array[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }
}
