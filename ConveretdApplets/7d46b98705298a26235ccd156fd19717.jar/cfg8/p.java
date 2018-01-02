// 
// Decompiled by Procyon v0.5.30
// 

package cfg8;

import java.util.Vector;

class p
{
    final g a;
    private p b;
    private String c;
    private String d;
    private Vector e;
    private static String[] z;
    
    p(final g a, final x x, final p b, final Vector vector, final String d) {
        final int a2 = RotationImageFilter.a;
        this.a = a;
        this.e = new Vector();
        this.b = b;
        this.c = x.d(p.z[0]);
        this.d = x.d(p.z[1]);
        final int length = this.d.length();
        if (a2 == 0) {
            if (length < 1) {
                this.d = d;
            }
            x.k();
        }
        int j = length;
        int i = 0;
        while (true) {
            while (i < j) {
                this.e.addElement(new p(a, x.d(i), this, vector, this.d));
                ++i;
                if (a2 != 0) {
                    int k = 0;
                    while (k < j) {
                        vector.addElement(new n(a, x.c(k), this));
                        ++k;
                        if (a2 != 0) {
                            break;
                        }
                    }
                    return;
                }
                if (a2 != 0) {
                    break;
                }
            }
            j = x.j();
            continue;
        }
    }
    
    protected boolean a() {
        final int a = RotationImageFilter.a;
        boolean length;
        final int n = (length = (this.c.length() != 0)) ? 1 : 0;
        if (a == 0) {
            if (n < 1) {
                return false;
            }
            final boolean o;
            length = (o = this.a.o(this.c));
        }
        if (a == 0) {
            if (n == 0) {
                length = true;
            }
            else {
                length = false;
            }
        }
        return length;
    }
    
    protected String b() {
        return this.d;
    }
    
    static {
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = "dT(U]p".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0097: {
                if (n2 > 1) {
                    break Label_0097;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '\u0002';
                            break;
                        }
                        case 1: {
                            c2 = '=';
                            break;
                        }
                        case 2: {
                            c2 = 'D';
                            break;
                        }
                        case 3: {
                            c2 = '!';
                            break;
                        }
                        default: {
                            c2 = '8';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "gK%M".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0213: {
                if (n6 > 1) {
                    break Label_0213;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '\u0002';
                            break;
                        }
                        case 1: {
                            c4 = '=';
                            break;
                        }
                        case 2: {
                            c4 = 'D';
                            break;
                        }
                        case 3: {
                            c4 = '!';
                            break;
                        }
                        default: {
                            c4 = '8';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 <= n8) {
                z[n5] = new String(charArray2).intern();
                p.z = z;
                return;
            }
            continue;
        }
    }
}
