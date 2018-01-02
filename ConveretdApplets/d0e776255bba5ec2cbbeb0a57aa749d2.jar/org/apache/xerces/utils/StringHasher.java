// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.utils;

public final class StringHasher
{
    public static int hashString(final String s, final int n) {
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            n2 += n2 * 37 + (n2 >> 24) + s.charAt(i);
        }
        final int n3 = n2 & Integer.MAX_VALUE;
        return (n3 == 0) ? 1 : n3;
    }
    
    public static int hashChars(final char[] array, int n, final int n2) {
        int n3 = 0;
        for (int i = 0; i < n2; ++i) {
            n3 += n3 * 37 + (n3 >> 24) + (array[n++] & '\uffff');
        }
        final int n4 = n3 & Integer.MAX_VALUE;
        return (n4 == 0) ? 1 : n4;
    }
    
    public static int hashChar(int n, final int n2) {
        n += n * 37 + (n >> 24) + n2;
        return n;
    }
    
    public static int finishHash(int n) {
        n &= Integer.MAX_VALUE;
        return (n == 0) ? 1 : n;
    }
}
