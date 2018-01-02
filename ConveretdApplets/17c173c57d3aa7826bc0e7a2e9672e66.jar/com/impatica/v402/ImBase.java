// 
// Decompiled by Procyon v0.5.30
// 

package com.impatica.v402;

public class ImBase
{
    final void I(final String s) {
        System.out.println(s);
    }
    
    static final int I(final int n, final int n2) {
        if (n == 0 || n2 == 0) {
            return 0;
        }
        if (n == 32768) {
            return n2;
        }
        if (n2 == 32768) {
            return n;
        }
        return n * n2 >> 15;
    }
    
    static final void I(final StringBuffer sb, final StringBuffer sb2) {
        final int length = sb2.length();
        sb.setLength(length);
        for (int i = 0; i < length; ++i) {
            sb.setCharAt(i, sb2.charAt(i));
        }
    }
}
