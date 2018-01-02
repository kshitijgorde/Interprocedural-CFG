// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings;

public class CodeRange
{
    public static boolean isInCodeRange(final int[] p, final int code) {
        int low = 0;
        int high;
        final int n = high = p[0];
        while (low < high) {
            final int x = low + high >> 1;
            if (code > p[(x << 1) + 2]) {
                low = x + 1;
            }
            else {
                high = x;
            }
        }
        return low < n && code >= p[(low << 1) + 1];
    }
    
    public static boolean isInCodeRange(final int[] p, final int offset, final int code) {
        int low = 0;
        int high;
        final int n = high = p[offset];
        while (low < high) {
            final int x = low + high >> 1;
            if (code > p[(x << 1) + 2 + offset]) {
                low = x + 1;
            }
            else {
                high = x;
            }
        }
        return low < n && code >= p[(low << 1) + 1 + offset];
    }
}
