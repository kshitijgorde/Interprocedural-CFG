// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.f;

public class c
{
    private String a;
    private String b;
    private int c;
    private static String z;
    
    public c() {
    }
    
    public c(final String a, final String s, final int c) {
        this.a = a;
        this.b = ((s == null) ? "" : s);
        this.c = c;
    }
    
    public String a() {
        return this.a;
    }
    
    public int b() {
        return this.c;
    }
    
    public String toString() {
        return String.valueOf(this.c) + flaxchat.f.c.z + this.a + flaxchat.f.c.z + this.b;
    }
    
    static {
        c.z = z(z("s\u0019\u0015:"));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'F';
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
                    c2 = 'S';
                    break;
                }
                case 1: {
                    c2 = '9';
                    break;
                }
                case 2: {
                    c2 = '5';
                    break;
                }
                case 3: {
                    c2 = '\u001a';
                    break;
                }
                default: {
                    c2 = 'F';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
