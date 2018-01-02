// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.c;

import flaxchat.i.k;
import flaxchat.a.i;

public class h implements i
{
    public int a(final Object o, final Object o2) {
        return this.a(((k)o).toString(), ((k)o2).toString());
    }
    
    private String a(final String s) {
        if (s.startsWith("\u0003")) {
            return s.substring(3);
        }
        return s;
    }
    
    private int a(String a, String a2) {
        final int l = a.l;
        a = this.a(a);
        a2 = this.a(a2);
        final int length = a.length();
        final int length2 = a2.length();
        int min = Math.min(length, length2);
        final char[] charArray = a.toLowerCase().toCharArray();
        final char[] charArray2 = a2.toLowerCase().toCharArray();
        int n = 0;
        int n2 = 0;
        while (true) {
            Label_0106: {
                if (l == 0) {
                    break Label_0106;
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
            return 1;
        }
        if (c == '&') {
            return 2;
        }
        if (c == '@') {
            return 3;
        }
        if (c == '%') {
            return 4;
        }
        if (c == '+') {
            return 5;
        }
        return c;
    }
}
