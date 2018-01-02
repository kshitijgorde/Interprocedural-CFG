// 
// Decompiled by Procyon v0.5.30
// 

package cfg8;

final class j
{
    private u a;
    private x b;
    protected static int c;
    private static String[] z;
    
    j() {
        this.a = new u();
        (this.b = this.a.a()).a(j.z[2]);
    }
    
    u a() {
        return this.a;
    }
    
    protected String b() {
        return this.a.b();
    }
    
    protected x c() {
        return this.b;
    }
    
    protected int d() {
        return t.d(this.GetQty());
    }
    
    public String GetQty() {
        final String getQuestion = this.GetQuestion(j.z[1]);
        if (RotationImageFilter.a == 0 && getQuestion.length() >= 1) {}
        return getQuestion;
    }
    
    protected w a(final String s, final boolean b) {
        final int a = RotationImageFilter.a;
        final x w = this.b.w(j.z[4]);
        w u;
        final x x = (x)(u = w);
        if (a == 0) {
            if (x == null) {
                return null;
            }
            u = w.u(s);
        }
        final w w2 = u;
        if (b) {
            final w w3 = w2;
            if (a == 0) {
                if (w3 != null) {
                    return w2;
                }
                w.y(s);
            }
            return w3;
        }
        return w2;
    }
    
    public String GetQuestion(final String s) {
        final w a = this.a(s, false);
        return (RotationImageFilter.a == 0 && a == null) ? "" : a.b();
    }
    
    public String GetQuestionAttrib(final String s, final String s2) {
        final w a = this.a(s, false);
        return (RotationImageFilter.a == 0 && a == null) ? "" : a.d(s2);
    }
    
    public String GetQuestionData(final String s) {
        return this.GetQuestionAttrib(s, j.z[3]);
    }
    
    protected boolean e() {
        return this.b.r(j.z[0]);
    }
    
    protected double f() {
        final int a = RotationImageFilter.a;
        j j = this;
        double n = 0.0;
        Label_0051: {
            if (a == 0) {
                if (this.e()) {
                    n = this.b.q(cfg8.j.z[8]);
                    if (a == 0) {
                        break Label_0051;
                    }
                }
                j = this;
            }
            n = j.b.q(cfg8.j.z[7]);
        }
        final double q;
        final double n2 = q = this.b.q(cfg8.j.z[6]);
        if (a == 0) {
            if (q != 0.0) {
                final double n3 = n;
                if (a == 0) {
                    if (n3 != 0.0) {
                        n -= t.a(n, n2, cfg8.j.c);
                    }
                }
            }
        }
        return q;
    }
    
    protected void a(final String s) {
        this.b.c(j.z[5], s);
    }
    
    protected void a(final String s, final String s2) {
        this.b.g(s, s2);
    }
    
    protected void a(final String s, final double n) {
        this.b.d(s, n);
    }
    
    public void SetQuestion(final String s, final String s2) {
        this.a(s, true).b(s2);
    }
    
    public void SetQuestionAttrib(final String s, final String s2, final String s3) {
        this.a(s, true).c(s2, s3);
    }
    
    protected void b(final String s) {
        this.a.a(s);
        final x a = this.a.a();
        a.a(j.z[2]);
        a.e(j.z[1], this.d());
    }
    
    static {
        final String[] z = new String[9];
        final int n = 0;
        final char[] charArray = "aZ.x\u001b`K2d\u0019i".toCharArray();
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
                            c2 = '\f';
                            break;
                        }
                        case 1: {
                            c2 = ';';
                            break;
                        }
                        case 2: {
                            c2 = '@';
                            break;
                        }
                        case 3: {
                            c2 = '\r';
                            break;
                        }
                        default: {
                            c2 = 'z';
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
        final char[] charArray2 = "}O9".toCharArray();
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
                            c4 = '\f';
                            break;
                        }
                        case 1: {
                            c4 = ';';
                            break;
                        }
                        case 2: {
                            c4 = '@';
                            break;
                        }
                        case 3: {
                            c4 = '\r';
                            break;
                        }
                        default: {
                            c4 = 'z';
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
        final char[] charArray3 = "eO%`".toCharArray();
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
                            c6 = '\f';
                            break;
                        }
                        case 1: {
                            c6 = ';';
                            break;
                        }
                        case 2: {
                            c6 = '@';
                            break;
                        }
                        case 3: {
                            c6 = '\r';
                            break;
                        }
                        default: {
                            c6 = 'z';
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
        final char[] charArray4 = "hZ4l".toCharArray();
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
                            c8 = '\f';
                            break;
                        }
                        case 1: {
                            c8 = ';';
                            break;
                        }
                        case 2: {
                            c8 = '@';
                            break;
                        }
                        case 3: {
                            c8 = '\r';
                            break;
                        }
                        default: {
                            c8 = 'z';
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
        final char[] charArray5 = "}N%~\u000eeT.~".toCharArray();
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
                            c10 = '\f';
                            break;
                        }
                        case 1: {
                            c10 = ';';
                            break;
                        }
                        case 2: {
                            c10 = '@';
                            break;
                        }
                        case 3: {
                            c10 = '\r';
                            break;
                        }
                        default: {
                            c10 = 'z';
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
        z[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "xB0h".toCharArray();
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
                            c12 = '\f';
                            break;
                        }
                        case 1: {
                            c12 = ';';
                            break;
                        }
                        case 2: {
                            c12 = '@';
                            break;
                        }
                        case 3: {
                            c12 = '\r';
                            break;
                        }
                        default: {
                            c12 = 'z';
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
        z[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "hR3n\u0015yU4".toCharArray();
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
                            c14 = '\f';
                            break;
                        }
                        case 1: {
                            c14 = ';';
                            break;
                        }
                        case 2: {
                            c14 = '@';
                            break;
                        }
                        case 3: {
                            c14 = '\r';
                            break;
                        }
                        default: {
                            c14 = 'z';
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
        z[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "|I)n\u001f".toCharArray();
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
                            c16 = '\f';
                            break;
                        }
                        case 1: {
                            c16 = ';';
                            break;
                        }
                        case 2: {
                            c16 = '@';
                            break;
                        }
                        case 3: {
                            c16 = '\r';
                            break;
                        }
                        default: {
                            c16 = 'z';
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
        z[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "aZ.x\u001b`Z-b\u000fbO".toCharArray();
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
                            c18 = '\f';
                            break;
                        }
                        case 1: {
                            c18 = ';';
                            break;
                        }
                        case 2: {
                            c18 = '@';
                            break;
                        }
                        case 3: {
                            c18 = '\r';
                            break;
                        }
                        default: {
                            c18 = 'z';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c17 ^ c18);
                    ++n36;
                } while (n34 == 0);
            }
            if (n34 <= n36) {
                z[n33] = new String(charArray9).intern();
                j.z = z;
                j.c = 2;
                return;
            }
            continue;
        }
    }
}
