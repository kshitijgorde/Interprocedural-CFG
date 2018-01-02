// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.f;

import java.util.NoSuchElementException;
import java.util.Enumeration;

public final class g implements Enumeration
{
    private a a;
    private int b;
    private static String z;
    
    g(final a a) {
        this.a = a;
        this.b = 0;
    }
    
    public boolean hasMoreElements() {
        return this.b < this.a.b;
    }
    
    public Object nextElement() {
        synchronized (this.a) {
            if (this.b < this.a.b) {
                // monitorexit(this.a)
                return this.a.a[this.b++];
            }
        }
        // monitorexit(this.a)
        throw new NoSuchElementException(g.z);
    }
    
    static {
        g.z = z(z("q\"V%dU\u0002[$fB5T%dU"));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '\u000b';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '\'';
                    break;
                }
                case 1: {
                    c2 = 'G';
                    break;
                }
                case 2: {
                    c2 = '5';
                    break;
                }
                case 3: {
                    c2 = 'Q';
                    break;
                }
                default: {
                    c2 = '\u000b';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
