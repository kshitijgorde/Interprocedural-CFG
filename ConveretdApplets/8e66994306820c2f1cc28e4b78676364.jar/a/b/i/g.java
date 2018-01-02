// 
// Decompiled by Procyon v0.5.30
// 

package a.b.i;

import a.b.c.a;

public class g implements k
{
    private String a;
    private String b;
    private int c;
    private static String[] z;
    
    public g(final String s, final String s2, final int n) {
        this.a(s);
        this.b(s2);
        this.a(n);
    }
    
    public void a(final String a) {
        this.a = a;
    }
    
    public String a() {
        return this.a;
    }
    
    public void b(final String b) {
        this.b = b;
    }
    
    public String b() {
        return this.b;
    }
    
    public void a(final int c) {
        this.c = c;
    }
    
    public int c() {
        return this.c;
    }
    
    public boolean equals(final Object o) {
        try {
            final g g = (g)o;
            return this.a().equals(g.a()) && this.b().equals(g.b()) && this.c() == g.c();
        }
        catch (ClassCastException ex) {
            return false;
        }
    }
    
    public String toString() {
        return new String(g.z[2] + this.b() + g.z[3] + g.z[1] + this.c() + g.z[3] + g.z[0] + this.a() + "}");
    }
    
    public int a() {
        return 0;
    }
    
    public int a(final byte[] array, final int n) throws a {
        return 0;
    }
    
    public int a(final byte[] array) throws a {
        return 0;
    }
    
    public int b(final byte[] array, final int n) throws a {
        return 0;
    }
    
    public byte[] a() throws a {
        return new byte[0];
    }
    
    static {
        final String[] z = new String[4];
        final int n = 0;
        final char[] charArray = "5~Ggo5rGdC\u0003!\t".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'G';
                    break;
                }
                case 1: {
                    c2 = '\u001b';
                    break;
                }
                case 2: {
                    c2 = ')';
                    break;
                }
                case 3: {
                    c2 = '\u0003';
                    break;
                }
                default: {
                    c2 = '\n';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "(}Opo3!\t".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'G';
                    break;
                }
                case 1: {
                    c4 = '\u001b';
                    break;
                }
                case 2: {
                    c4 = ')';
                    break;
                }
                case 3: {
                    c4 = '\u0003';
                    break;
                }
                default: {
                    c4 = '\n';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "\u0003rZsf&b{f{2~ZwC)}Fqg&o@ldg`Eby3XFm~\"u]Jn};".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'G';
                    break;
                }
                case 1: {
                    c6 = '\u001b';
                    break;
                }
                case 2: {
                    c6 = ')';
                    break;
                }
                case 3: {
                    c6 = '\u0003';
                    break;
                }
                default: {
                    c6 = '\n';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "|;".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'G';
                    break;
                }
                case 1: {
                    c8 = '\u001b';
                    break;
                }
                case 2: {
                    c8 = ')';
                    break;
                }
                case 3: {
                    c8 = '\u0003';
                    break;
                }
                default: {
                    c8 = '\n';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        g.z = z;
    }
}
