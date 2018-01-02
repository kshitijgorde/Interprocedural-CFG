// 
// Decompiled by Procyon v0.5.30
// 

package cfg8;

import java.util.Date;

class s
{
    final g a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private double g;
    private static String[] z;
    
    s(final g a, final String b, final String c, final String d) {
        this.a = a;
        this.b = "";
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = 0.0;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = "";
    }
    
    s(final g a, final w w) {
        this.a = a;
        this.b = "";
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = 0.0;
        this.b = w.d(s.z[2]);
        this.c = w.d(s.z[0]);
        this.d = w.b();
        this.e = w.d(s.z[1]);
    }
    
    protected boolean a() {
        final boolean equals = this.c.equals(s.z[4]);
        if (RotationImageFilter.a == 0 && !equals) {}
        return equals;
    }
    
    protected boolean b() {
        final boolean equals = this.c.equals(s.z[3]);
        if (RotationImageFilter.a == 0 && !equals) {}
        return equals;
    }
    
    protected String c() {
        return this.b;
    }
    
    protected String d() {
        return this.f;
    }
    
    protected double e() {
        return this.g;
    }
    
    protected boolean f() {
        final int a = RotationImageFilter.a;
        boolean length;
        final int n = (length = (this.e.length() != 0)) ? 1 : 0;
        if (a == 0) {
            if (n <= 0) {
                length = false;
                return length;
            }
            final boolean o;
            length = (o = this.a.o(this.e));
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
    
    protected void g() {
        final int a = RotationImageFilter.a;
        final boolean a2 = this.a();
        final boolean b = true;
        s s = null;
        Label_0133: {
            if (a == 0) {
                if (a2 == b) {
                    this.f = this.a.s(this.d);
                    this.g = 0.0;
                    if (a == 0) {
                        return;
                    }
                }
                s = this;
                if (a != 0) {
                    break Label_0133;
                }
                this.b();
            }
            if (a2 == b) {
                this.g = this.a.r(this.d);
                final Date a3 = t.a((long)this.g);
                Label_0113: {
                    Label_0107: {
                        if (a == 0) {
                            if (a3 == null) {
                                break Label_0107;
                            }
                            this.f = t.a(a3, cfg8.g.i(this.a).q);
                        }
                        if (a == 0) {
                            break Label_0113;
                        }
                    }
                    this.f = "";
                }
                if (a == 0) {
                    return;
                }
            }
            this.g = this.a.r(this.d);
            s = this;
        }
        s.f = cfg8.g.c(this.a).format(this.g);
    }
    
    static {
        final String[] z = new String[5];
        final int n = 0;
        final char[] charArray = "[`\f\u0010".toCharArray();
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
                            c2 = '/';
                            break;
                        }
                        case 1: {
                            c2 = '\u0019';
                            break;
                        }
                        case 2: {
                            c2 = '|';
                            break;
                        }
                        case 3: {
                            c2 = 'u';
                            break;
                        }
                        default: {
                            c2 = '\u0015';
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
        final char[] charArray2 = "Ip\u0010\u0001p]".toCharArray();
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
                            c4 = '/';
                            break;
                        }
                        case 1: {
                            c4 = '\u0019';
                            break;
                        }
                        case 2: {
                            c4 = '|';
                            break;
                        }
                        case 3: {
                            c4 = 'u';
                            break;
                        }
                        default: {
                            c4 = '\u0015';
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
        final char[] charArray3 = "Ax\u0011\u0010".toCharArray();
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
                            c6 = '/';
                            break;
                        }
                        case 1: {
                            c6 = '\u0019';
                            break;
                        }
                        case 2: {
                            c6 = '|';
                            break;
                        }
                        case 3: {
                            c6 = 'u';
                            break;
                        }
                        default: {
                            c6 = '\u0015';
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
        final char[] charArray4 = "Kx\b\u0010".toCharArray();
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
                            c8 = '/';
                            break;
                        }
                        case 1: {
                            c8 = '\u0019';
                            break;
                        }
                        case 2: {
                            c8 = '|';
                            break;
                        }
                        case 3: {
                            c8 = 'u';
                            break;
                        }
                        default: {
                            c8 = '\u0015';
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
        final char[] charArray5 = "\\m\u000e\u001c{H".toCharArray();
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
                            c10 = '/';
                            break;
                        }
                        case 1: {
                            c10 = '\u0019';
                            break;
                        }
                        case 2: {
                            c10 = '|';
                            break;
                        }
                        case 3: {
                            c10 = 'u';
                            break;
                        }
                        default: {
                            c10 = '\u0015';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 <= n20) {
                z[n17] = new String(charArray5).intern();
                s.z = z;
                return;
            }
            continue;
        }
    }
}
