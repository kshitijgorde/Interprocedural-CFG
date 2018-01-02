// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.c;

import flaxchat.d.k;

public class h implements flaxchat.e.h
{
    public int a(final Object o, final Object o2) {
        return this.a(((k)o).toString(), ((k)o2).toString());
    }
    
    private int a(final String s, final String s2) {
        final int i = a.i;
        final int length = s.length();
        final int length2 = s2.length();
        int min = Math.min(length, length2);
        final char[] charArray = s.toLowerCase().toCharArray();
        final char[] charArray2 = s2.toLowerCase().toCharArray();
        int n = 0;
        int n2 = 0;
        while (true) {
            Label_0094: {
                if (i == 0) {
                    break Label_0094;
                }
                final char c = charArray[n++];
                final char c2 = charArray2[n2++];
                if (c != c2) {
                    return this.a(c) - this.a(c2);
                }
            }
            if (min-- == 0) {
                return length - length2;
            }
            continue;
        }
    }
    
    private int a(final char c) {
        if (c == '~') {
            return 0;
        }
        if (c == '&') {
            return 1;
        }
        if (c == '@') {
            return 2;
        }
        if (c == '%') {
            return 3;
        }
        if (c == '+') {
            return 4;
        }
        return c;
    }
}
