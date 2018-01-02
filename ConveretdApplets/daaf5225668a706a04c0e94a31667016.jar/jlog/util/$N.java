// 
// Decompiled by Procyon v0.5.30
// 

package jlog.util;

import java.text.Collator;

public class $N
{
    public static void $O(final String[] array, final Collator collator) {
        $O(array, collator, 0, array.length - 1);
    }
    
    static void $O(final String[] array, final Collator collator, final int n, int i) {
        if (i <= n) {
            return;
        }
        final String s = array[i];
        int n2 = n - 1;
        while (true) {
            if (collator != null) {
                while (n2 != i && collator.compare(array[++n2], s) < 0) {}
                while (i != n) {
                    if (collator.compare(array[--i], s) <= 0) {
                        break;
                    }
                }
            }
            else {
                while (n2 != i && array[++n2].compareTo(s) < 0) {}
                while (i != n && array[--i].compareTo(s) > 0) {}
            }
            if (n2 >= i) {
                break;
            }
            final String s2 = array[n2];
            array[n2] = array[i];
            array[i] = s2;
        }
        final String s3 = array[n2];
        array[n2] = array[i];
        array[i] = s3;
        $O(array, collator, n, n2 - 1);
        $O(array, collator, n2 + 1, i);
    }
}
