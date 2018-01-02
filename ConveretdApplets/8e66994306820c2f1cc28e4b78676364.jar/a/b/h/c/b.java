// 
// Decompiled by Procyon v0.5.30
// 

package a.b.h.c;

import a.b.a.a.a;
import a.b.o.c.h;

public class b implements d
{
    private h a;
    private a b;
    private String c;
    private static String[] z;
    
    public b() {
        this(null, null, null);
    }
    
    public b(final h h, final a a, final String s) {
        this.a(h);
        this.a(a);
        this.a(s);
    }
    
    public h a() {
        return this.a;
    }
    
    public void a(final h a) {
        this.a = a;
    }
    
    public a b() {
        return this.b;
    }
    
    public void a(final a b) {
        this.b = b;
    }
    
    public String a() {
        return this.c;
    }
    
    public void a(final String c) {
        this.c = c;
    }
    
    public String toString() {
        return new String(a.b.h.c.b.z[1] + this.a() + a.b.h.c.b.z[0] + this.a().toString() + a.b.h.c.b.z[2] + this.b().toString() + "}");
    }
    
    static {
        final String[] z = new String[3];
        final int n = 0;
        final char[] charArray = "r>\u0005\u0017'={\b\fsi".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'I';
                    break;
                }
                case 1: {
                    c2 = '\u001e';
                    break;
                }
                case 2: {
                    c2 = 'f';
                    break;
                }
                case 3: {
                    c2 = 'x';
                    break;
                }
                default: {
                    c2 = 'I';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\rw\u0015\b%(g5\u001d=ie/<si".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'I';
                    break;
                }
                case 1: {
                    c4 = '\u001e';
                    break;
                }
                case 2: {
                    c4 = 'f';
                    break;
                }
                case 3: {
                    c4 = 'x';
                    break;
                }
                default: {
                    c4 = 'I';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "r>\u0003\u001e/,}\u0012Bi".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'I';
                    break;
                }
                case 1: {
                    c6 = '\u001e';
                    break;
                }
                case 2: {
                    c6 = 'f';
                    break;
                }
                case 3: {
                    c6 = 'x';
                    break;
                }
                default: {
                    c6 = 'I';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        b.z = z;
    }
}
