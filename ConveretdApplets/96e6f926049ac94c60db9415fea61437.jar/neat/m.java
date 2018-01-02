// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import neat.system.f;
import neat.system.cb;

public class m implements cb
{
    private static f a;
    public int b;
    k c;
    int d;
    int e;
    m f;
    m g;
    byte[] h;
    int i;
    private static /* synthetic */ Class j;
    private static String[] z;
    
    static m a(final k c) {
        final m m = (m)neat.m.a.a();
        m.c = c;
        return m;
    }
    
    public void f() {
        m.a.a(this);
    }
    
    public void g() {
        this.b = 0;
        this.d = -1;
        this.e = -1;
        this.i = 0;
    }
    
    public void h() {
        this.c = null;
        this.f = null;
        this.g = null;
        this.h = null;
    }
    
    public String toString() {
        return m.z[3] + this.d + m.z[2] + this.e + m.z[1] + this.b + m.z[0] + this.i;
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public m() {
        this.c = null;
        this.f = null;
        this.g = null;
        this.h = null;
    }
    
    static {
        final String[] z = new String[5];
        final int n = 0;
        final char[] charArray = "\u0010|\t\u0004W\u0006".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '<';
                            break;
                        }
                        case 1: {
                            c2 = '\u0010';
                            break;
                        }
                        case 2: {
                            c2 = 'f';
                            break;
                        }
                        case 3: {
                            c2 = 'g';
                            break;
                        }
                        default: {
                            c2 = '<';
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
        final char[] charArray2 = "\u0010c\\".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '<';
                            break;
                        }
                        case 1: {
                            c4 = '\u0010';
                            break;
                        }
                        case 2: {
                            c4 = 'f';
                            break;
                        }
                        case 3: {
                            c4 = 'g';
                            break;
                        }
                        default: {
                            c4 = '<';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "\u0010q\u000f]".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0330: {
                if (n10 > 1) {
                    break Label_0330;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '<';
                            break;
                        }
                        case 1: {
                            c6 = '\u0010';
                            break;
                        }
                        case 2: {
                            c6 = 'f';
                            break;
                        }
                        case 3: {
                            c6 = 'g';
                            break;
                        }
                        default: {
                            c6 = '<';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        z[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "Ut\\".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0446: {
                if (n14 > 1) {
                    break Label_0446;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = '<';
                            break;
                        }
                        case 1: {
                            c8 = '\u0010';
                            break;
                        }
                        case 2: {
                            c8 = 'f';
                            break;
                        }
                        case 3: {
                            c8 = 'g';
                            break;
                        }
                        default: {
                            c8 = '<';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        z[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "Ru\u0007\u0013\u0012Q".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0562: {
                if (n18 > 1) {
                    break Label_0562;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = '<';
                            break;
                        }
                        case 1: {
                            c10 = '\u0010';
                            break;
                        }
                        case 2: {
                            c10 = 'f';
                            break;
                        }
                        case 3: {
                            c10 = 'g';
                            break;
                        }
                        default: {
                            c10 = '<';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 <= n20) {
                z[n17] = new String(charArray5).intern();
                m.z = z;
                m.a = new f((m.j != null) ? m.j : (m.j = a(m.z[4])));
                return;
            }
            continue;
        }
    }
}
