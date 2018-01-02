// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.h;

import flaxchat.i.b;

public class g
{
    private String a;
    private String b;
    private String c;
    private static String z;
    
    public g(final String s, final String s2) {
        this.b(s);
        this.a(s2);
    }
    
    public void a(final String b) {
        this.b = b;
        this.c = this.b.toLowerCase();
    }
    
    public void b(final String a) {
        this.a = a;
    }
    
    public String a() {
        return this.a;
    }
    
    public boolean b() {
        return this.a.indexOf(64) >= 0;
    }
    
    public boolean c() {
        return this.a.indexOf(43) >= 0;
    }
    
    public String d() {
        return this.b;
    }
    
    public String toString() {
        if (this.b()) {
            return "@" + '\u0003' + flaxchat.i.b.c(g.z) + this.d();
        }
        return String.valueOf(this.a()) + this.d();
    }
    
    public boolean equals(final Object o) {
        return o instanceof g && ((g)o).c.equals(this.c);
    }
    
    public int hashCode() {
        return this.c.hashCode();
    }
    
    static {
        g.z = z(z("A=\u0003_\u0014K?3e\u000bA?"));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'g';
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
                    c2 = '.';
                    break;
                }
                case 1: {
                    c2 = 'M';
                    break;
                }
                case 2: {
                    c2 = 'p';
                    break;
                }
                case 3: {
                    c2 = '\n';
                    break;
                }
                default: {
                    c2 = 'g';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
