// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class g
{
    public static final int a(final char[] array, final char[] array2, int min) {
        min = Math.min(array.length, array2.length);
        for (int i = 0; i < min; ++i) {
            if (array[i] != array2[i]) {
                if (array[i] == '\0') {
                    return -1;
                }
                if (array2[i] == '\0') {
                    return 1;
                }
                if (array[i] < array2[i]) {
                    return -1;
                }
                if (array[i] > array2[i]) {
                    return 1;
                }
            }
            else if (array[i] == '\0' || array2[i] == '\0') {
                return 0;
            }
        }
        return 0;
    }
    
    public static final int if(final char[] array, final char[] array2) {
        if (array == null || array2 == null) {
            return -1;
        }
        final int min = Math.min(array.length, array2.length);
        for (int i = 0; i < min; ++i) {
            int n = array[i];
            if (n >= 65 && n <= 90) {
                n = n - 65 + 97;
            }
            int n2 = array2[i];
            if (n2 >= 65 && n2 <= 90) {
                n2 = n2 - 65 + 97;
            }
            if (n != n2) {
                if (n == 0) {
                    return -1;
                }
                if (n2 == 0) {
                    return 1;
                }
                if (n < n2) {
                    return -1;
                }
                if (n > n2) {
                    return 1;
                }
            }
            else if (n == 0) {
                return 0;
            }
        }
        if (min < array.length && array[min] != '\0') {
            return 1;
        }
        if (min < array2.length && array2[min] != '\0') {
            return -1;
        }
        return 0;
    }
    
    public static final int a(final char[] array, final char[] array2) {
        return a(array, array2, Math.max(array.length, array2.length));
    }
    
    public static final char[] a(char[] array, final char[] array2, final char[] array3) {
        int i = 0;
        int n = 0;
        while (i < array2.length) {
            if (array2[i] == '\0') {
                break;
            }
            ++i;
        }
        while (n < array3.length && array3[n] != '\0') {
            ++n;
        }
        if (array == null || array.length < i + n + 1) {
            array = new char[i + n + 1];
        }
        int j;
        for (j = 0; j < i; ++j) {
            array[j] = array2[j];
        }
        for (int k = 0; k < n; ++k, ++j) {
            array[j] = array3[k];
        }
        array[j] = '\0';
        return array;
    }
    
    public static final void do(final char[] array, final char[] array2) {
        int n = 0;
        if (array.length > 0) {
            do {
                array2[n] = array[n];
                ++n;
            } while (array[n] != '\0');
        }
        array2[n] = '\0';
    }
    
    public static final int a(final char[] array) {
        final int length = array.length;
        for (int i = 0; i < length; ++i) {
            if (array[i] == '\0') {
                return i;
            }
        }
        return length;
    }
}
