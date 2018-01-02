// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.h;

import flaxchat.i.k;

public class e
{
    public Object a;
    public e b;
    private static String z;
    
    public e(final Object a, final e b) {
        this.a = a;
        this.b = b;
    }
    
    public String toString() {
        return e.z + this.a.toString() + "]";
    }
    
    public k a() {
        return (k)this.a;
    }
    
    static {
        e.z = z(z("bbiAr"));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= ')';
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
                    c2 = ',';
                    break;
                }
                case 1: {
                    c2 = '\r';
                    break;
                }
                case 2: {
                    c2 = '\r';
                    break;
                }
                case 3: {
                    c2 = '$';
                    break;
                }
                default: {
                    c2 = ')';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
