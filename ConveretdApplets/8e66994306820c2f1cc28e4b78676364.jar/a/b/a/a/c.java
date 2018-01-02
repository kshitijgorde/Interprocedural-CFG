// 
// Decompiled by Procyon v0.5.30
// 

package a.b.a.a;

import java.io.Serializable;

public class c implements a, Serializable
{
    private long a;
    private static String z;
    
    public c() {
        this(4000L);
    }
    
    public c(final long n) {
        this.a(n);
    }
    
    public long a() {
        return this.a;
    }
    
    public void a(final long a) {
        if (a >= 0L) {
            this.a = a;
        }
        else {
            this.a = 4000L;
        }
    }
    
    public boolean equals(final Object o) {
        try {
            return o != null && this.a() == ((c)o).a();
        }
        catch (ClassCastException ex) {
            return false;
        }
    }
    
    public String toString() {
        return new String(c.z + this.a() + "}");
    }
    
    static {
        final char[] charArray = "_`gnv_da}Vjjkig,wj\u007famxge}6,".toCharArray();
        final int i = charArray.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = charArray[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '\f';
                    break;
                }
                case 1: {
                    c2 = '\f';
                    break;
                }
                case 2: {
                    c2 = '\u000e';
                    break;
                }
                case 3: {
                    c2 = '\n';
                    break;
                }
                default: {
                    c2 = '\u0013';
                    break;
                }
            }
            charArray[n2] = (char)(c ^ c2);
        }
        c.z = new String(charArray).intern();
    }
}
