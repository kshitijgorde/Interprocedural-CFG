// 
// Decompiled by Procyon v0.5.30
// 

package a.b.a.a;

import java.io.Serializable;

public class b implements a, Serializable
{
    private int a;
    private int b;
    private static String[] z;
    
    public b() {
        this(4);
    }
    
    public b(final int n) {
        this(n, 0);
    }
    
    public b(final int n, final int n2) {
        this.a(n);
        this.b(n2);
    }
    
    public int a() {
        return this.a;
    }
    
    public void a(final int a) {
        this.a = a;
    }
    
    public int b() {
        return this.b;
    }
    
    public void b(final int b) {
        this.b = b;
    }
    
    public boolean equals(final Object o) {
        try {
            if (o == null) {
                return false;
            }
            final b b = (b)o;
            return this.a() == b.a() && this.b() == b.b();
        }
        catch (ClassCastException ex) {
            return false;
        }
    }
    
    public String toString() {
        return new String(a.b.a.a.b.z[1] + this.b() + a.b.a.a.b.z[0] + this.a() + "}");
    }
    
    static {
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = ":@Fg0d\u0004\u000f7".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '\u0001';
                    break;
                }
                case 1: {
                    c2 = '`';
                    break;
                }
                case 2: {
                    c2 = '5';
                    break;
                }
                case 3: {
                    c2 = '\u0017';
                    break;
                }
                default: {
                    c2 = 'U';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "R\u0003Gx9m%Sq0b\u0014\u0015l1h\u0012Pt!h\u000f[-u".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '\u0001';
                    break;
                }
                case 1: {
                    c4 = '`';
                    break;
                }
                case 2: {
                    c4 = '5';
                    break;
                }
                case 3: {
                    c4 = '\u0017';
                    break;
                }
                default: {
                    c4 = 'U';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        b.z = z;
    }
}
