// 
// Decompiled by Procyon v0.5.30
// 

package cfg8;

import java.awt.Component;

public final class MiscEditApp extends ItemApplet
{
    private e Q;
    private static String[] R;
    
    public MiscEditApp() {
        super.C = MiscEditApp.R[9];
        (this.Q = new e(this)).a(super.v);
    }
    
    public void init() {
        final int a = RotationImageFilter.a;
        super.init();
        this.Q.setBackground(this.getBackground());
        this.add(this.Q, null);
        final int length = super.m.length();
        final int n = 0;
        Object o = null;
        Label_0091: {
            if (a == 0) {
                if (length > n) {
                    this.c(super.m);
                }
                final String s = (String)(o = this.getParameter(MiscEditApp.R[8], "N"));
                if (a != 0) {
                    break Label_0091;
                }
                s.equals("Y");
            }
            if (length == n) {
                super.v.c(true);
            }
            o = super.g;
        }
        synchronized (o) {
            super.e = true;
        }
    }
    
    public void doLayout() {
        super.doLayout();
        this.Q.setBounds(super.L.a());
        this.Q.doLayout();
        synchronized (super.g) {
            super.f = true;
        }
    }
    
    public void SetFormData(final String s, final String s2) {
        final x c = super.A.c();
        final x v = c.v(MiscEditApp.R[10]);
        if (RotationImageFilter.a == 0 && v == null) {
            c.z(MiscEditApp.R[10]);
            goto Label_0044;
        }
        v.g(s, s2);
    }
    
    protected void c(final String s) {
        final int a = RotationImageFilter.a;
        final u b = this.b(s);
        if (a != 0 || b != null) {
            final x a2 = b.a();
            final String concat = String.valueOf(MiscEditApp.R[4]).concat(String.valueOf(a2.o(MiscEditApp.R[2])));
            this.Q.a(concat);
            super.v.e(concat);
            x x = a2.v(MiscEditApp.R[0]);
            x v;
            final x x2 = v = x;
            x x4;
            final x x3 = x4 = null;
            if (a == 0) {
                if (x2 != x3) {
                    super.v.a(x);
                }
                x = a2.v(MiscEditApp.R[1]);
                final x x5;
                v = (x5 = x);
                final Object o;
                x4 = (x)(o = null);
            }
            if (a == 0) {
                if (x2 != x3) {
                    this.Q.a(x);
                    super.v.c(x);
                }
                x = (v = a2.v(MiscEditApp.R[3]));
                x4 = null;
            }
            if (v != x4) {
                super.v.d(x);
            }
        }
    }
    
    protected void d() {
        this.Q.a();
    }
    
    protected void i() {
        final x a = super.A.a().a();
        a.x(MiscEditApp.R[3]);
        a.x(MiscEditApp.R[7]);
        a.x(MiscEditApp.R[5]);
        a.x(MiscEditApp.R[6]);
        final x z = a.z(MiscEditApp.R[3]);
        final x z2 = a.z(MiscEditApp.R[7]);
        final x z3 = a.z(MiscEditApp.R[6]);
        super.v.e(z);
        super.v.f(z2);
        super.v.g(z3);
    }
    
    static {
        final String[] r = new String[11];
        final int n = 0;
        final char[] charArray = "\u001b7`-I\u000f:w7".toCharArray();
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
                            c2 = 'm';
                            break;
                        }
                        case 1: {
                            c2 = 'V';
                            break;
                        }
                        case 2: {
                            c2 = '\u0012';
                            break;
                        }
                        case 3: {
                            c2 = 'D';
                            break;
                        }
                        default: {
                            c2 = '(';
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
        r[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "\t3a-O\u0003".toCharArray();
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
                            c4 = 'm';
                            break;
                        }
                        case 1: {
                            c4 = 'V';
                            break;
                        }
                        case 2: {
                            c4 = '\u0012';
                            break;
                        }
                        case 3: {
                            c4 = 'D';
                            break;
                        }
                        default: {
                            c4 = '(';
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
        r[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "\u0004;s#M\u001d7f,".toCharArray();
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
                            c6 = 'm';
                            break;
                        }
                        case 1: {
                            c6 = 'V';
                            break;
                        }
                        case 2: {
                            c6 = '\u0012';
                            break;
                        }
                        case 3: {
                            c6 = 'D';
                            break;
                        }
                        default: {
                            c6 = '(';
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
        r[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "\u001c#w7\\\u00049|7".toCharArray();
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
                            c8 = 'm';
                            break;
                        }
                        case 1: {
                            c8 = 'V';
                            break;
                        }
                        case 2: {
                            c8 = '\u0012';
                            break;
                        }
                        case 3: {
                            c8 = 'D';
                            break;
                        }
                        default: {
                            c8 = '(';
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
        r[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "\n$s4@\u00045ak".toCharArray();
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
                            c10 = 'm';
                            break;
                        }
                        case 1: {
                            c10 = 'V';
                            break;
                        }
                        case 2: {
                            c10 = '\u0012';
                            break;
                        }
                        case 3: {
                            c10 = 'D';
                            break;
                        }
                        default: {
                            c10 = '(';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 > n20) {
                continue;
            }
            break;
        }
        r[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "\u001b7`7".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0678: {
                if (n22 > 1) {
                    break Label_0678;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = 'm';
                            break;
                        }
                        case 1: {
                            c12 = 'V';
                            break;
                        }
                        case 2: {
                            c12 = '\u0012';
                            break;
                        }
                        case 3: {
                            c12 = 'D';
                            break;
                        }
                        default: {
                            c12 = '(';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 > n24) {
                continue;
            }
            break;
        }
        r[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "\u0004;s#M".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0798: {
                if (n26 > 1) {
                    break Label_0798;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = 'm';
                            break;
                        }
                        case 1: {
                            c14 = 'V';
                            break;
                        }
                        case 2: {
                            c14 = '\u0012';
                            break;
                        }
                        case 3: {
                            c14 = 'D';
                            break;
                        }
                        default: {
                            c14 = '(';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 > n28) {
                continue;
            }
            break;
        }
        r[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "\u000b:s#[".toCharArray();
        int length8;
        int n31;
        final int n30 = n31 = (length8 = charArray8.length);
        int n32 = 0;
        while (true) {
            Label_0918: {
                if (n30 > 1) {
                    break Label_0918;
                }
                length8 = (n31 = n32);
                do {
                    final char c15 = charArray8[n31];
                    char c16 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c16 = 'm';
                            break;
                        }
                        case 1: {
                            c16 = 'V';
                            break;
                        }
                        case 2: {
                            c16 = '\u0012';
                            break;
                        }
                        case 3: {
                            c16 = 'D';
                            break;
                        }
                        default: {
                            c16 = '(';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n32;
                } while (n30 == 0);
            }
            if (n30 > n32) {
                continue;
            }
            break;
        }
        r[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = ".\u0017^\u0007".toCharArray();
        int length9;
        int n35;
        final int n34 = n35 = (length9 = charArray9.length);
        int n36 = 0;
        while (true) {
            Label_1038: {
                if (n34 > 1) {
                    break Label_1038;
                }
                length9 = (n35 = n36);
                do {
                    final char c17 = charArray9[n35];
                    char c18 = '\0';
                    switch (n36 % 5) {
                        case 0: {
                            c18 = 'm';
                            break;
                        }
                        case 1: {
                            c18 = 'V';
                            break;
                        }
                        case 2: {
                            c18 = '\u0012';
                            break;
                        }
                        case 3: {
                            c18 = 'D';
                            break;
                        }
                        default: {
                            c18 = '(';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c17 ^ c18);
                    ++n36;
                } while (n34 == 0);
            }
            if (n34 > n36) {
                continue;
            }
            break;
        }
        r[n33] = new String(charArray9).intern();
        final int n37 = 9;
        final char[] charArray10 = "\u0000?a'".toCharArray();
        int length10;
        int n39;
        final int n38 = n39 = (length10 = charArray10.length);
        int n40 = 0;
        while (true) {
            Label_1158: {
                if (n38 > 1) {
                    break Label_1158;
                }
                length10 = (n39 = n40);
                do {
                    final char c19 = charArray10[n39];
                    char c20 = '\0';
                    switch (n40 % 5) {
                        case 0: {
                            c20 = 'm';
                            break;
                        }
                        case 1: {
                            c20 = 'V';
                            break;
                        }
                        case 2: {
                            c20 = '\u0012';
                            break;
                        }
                        case 3: {
                            c20 = 'D';
                            break;
                        }
                        default: {
                            c20 = '(';
                            break;
                        }
                    }
                    charArray10[length10] = (char)(c19 ^ c20);
                    ++n40;
                } while (n38 == 0);
            }
            if (n38 > n40) {
                continue;
            }
            break;
        }
        r[n37] = new String(charArray10).intern();
        final int n41 = 10;
        final char[] charArray11 = "\u000b9`)".toCharArray();
        int length11;
        int n43;
        final int n42 = n43 = (length11 = charArray11.length);
        int n44 = 0;
        while (true) {
            Label_1278: {
                if (n42 > 1) {
                    break Label_1278;
                }
                length11 = (n43 = n44);
                do {
                    final char c21 = charArray11[n43];
                    char c22 = '\0';
                    switch (n44 % 5) {
                        case 0: {
                            c22 = 'm';
                            break;
                        }
                        case 1: {
                            c22 = 'V';
                            break;
                        }
                        case 2: {
                            c22 = '\u0012';
                            break;
                        }
                        case 3: {
                            c22 = 'D';
                            break;
                        }
                        default: {
                            c22 = '(';
                            break;
                        }
                    }
                    charArray11[length11] = (char)(c21 ^ c22);
                    ++n44;
                } while (n42 == 0);
            }
            if (n42 <= n44) {
                r[n41] = new String(charArray11).intern();
                MiscEditApp.R = r;
                return;
            }
            continue;
        }
    }
}
