// 
// Decompiled by Procyon v0.5.30
// 

public class b extends a
{
    private static final String[] z;
    
    public String a() {
        final int u = a.u;
        String s = new String();
        Label_0152: {
            switch (this.j) {
                case 0: {
                    s = b.z[3] + b.b[this.n];
                    if (u != 0) {
                        break Label_0152;
                    }
                    return s;
                }
                case 1: {
                    int n = 0;
                    while (b.c[n] != this.n && n < b.c.length) {
                        ++n;
                        if (u != 0) {
                            break;
                        }
                    }
                    s = b.z[3] + b.d[n];
                    if (u != 0) {
                        break Label_0152;
                    }
                    return s;
                }
                case 2: {
                    s = b.z[1];
                    if (u != 0) {
                        break Label_0152;
                    }
                    return s;
                }
                case 3: {
                    s = b.z[2] + this.g[this.m] + b.z[4];
                    if (u != 0) {
                        break;
                    }
                    return s;
                }
            }
        }
        System.err.println(b.z[0]);
        return s;
    }
    
    public String a(final String s) {
        final int u = a.u;
        String s2 = new String();
        Label_0254: {
            switch (this.j) {
                case 0: {
                    s2 = b.z[7] + (int)Math.round(this.o) + " " + b.b[this.n] + " " + s;
                    if (u != 0) {
                        break Label_0254;
                    }
                    return s2;
                }
                case 1: {
                    int n = 0;
                    while (b.c[n] != this.n && n < b.c.length) {
                        ++n;
                        if (u != 0) {
                            break;
                        }
                    }
                    s2 = b.z[7] + (int)Math.round(this.o) + " " + b.d[n] + " " + s;
                    if (u != 0) {
                        break Label_0254;
                    }
                    return s2;
                }
                case 2: {
                    s2 = b.z[8] + s + b.z[5] + ub.a(this.o, 1) + b.z[6];
                    if (u != 0) {
                        break Label_0254;
                    }
                    return s2;
                }
                case 3: {
                    s2 = b.z[10] + s + b.z[5] + ub.a(this.o, 1) + b.z[9];
                    if (u != 0) {
                        break;
                    }
                    return s2;
                }
            }
        }
        System.err.println(b.z[11]);
        return s2;
    }
    
    static {
        final String[] z2 = new String[12];
        final int n = 0;
        final char[] charArray = "$Wy,6\u0004]/9#\u001d\\/$4M||93\u0000X{(\u0019\fWy,)C^j9\u000b\u0018\\|93\u0002W'd".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'm';
                    break;
                }
                case 1: {
                    c2 = '9';
                    break;
                }
                case 2: {
                    c2 = '\u000f';
                    break;
                }
                case 3: {
                    c2 = 'M';
                    break;
                }
                default: {
                    c2 = 'Z';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z2[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\u0019Qjm6\bWh92MVim.\u0005\\/./\u001fOj".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'm';
                    break;
                }
                case 1: {
                    c4 = '9';
                    break;
                }
                case 2: {
                    c4 = '\u000f';
                    break;
                }
                case 3: {
                    c4 = 'M';
                    break;
                }
                default: {
                    c4 = 'Z';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z2[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "\u0019Qjm;\u001f\\nm5\u000b\u0019{%?M".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'm';
                    break;
                }
                case 1: {
                    c6 = '9';
                    break;
                }
                case 2: {
                    c6 = '\u000f';
                    break;
                }
                case 3: {
                    c6 = 'M';
                    break;
                }
                default: {
                    c6 = 'Z';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z2[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "\u0019Qjm4\u0018Tm((MVim".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'm';
                    break;
                }
                case 1: {
                    c8 = '9';
                    break;
                }
                case 2: {
                    c8 = '\u000f';
                    break;
                }
                case 3: {
                    c8 = 'M';
                    break;
                }
                default: {
                    c8 = 'Z';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z2[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "MJg,*\b".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'm';
                    break;
                }
                case 1: {
                    c10 = '9';
                    break;
                }
                case 2: {
                    c10 = '\u000f';
                    break;
                }
                case 3: {
                    c10 = 'M';
                    break;
                }
                default: {
                    c10 = 'Z';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z2[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "MP|m".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'm';
                    break;
                }
                case 1: {
                    c12 = '9';
                    break;
                }
                case 2: {
                    c12 = '\u000f';
                    break;
                }
                case 3: {
                    c12 = 'M';
                    break;
                }
                default: {
                    c12 = 'Z';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z2[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "MLa$.\u001e\u0019c\"4\n".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = 'm';
                    break;
                }
                case 1: {
                    c14 = '9';
                    break;
                }
                case 2: {
                    c14 = '\u000f';
                    break;
                }
                case 3: {
                    c14 = 'M';
                    break;
                }
                default: {
                    c14 = 'Z';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z2[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "\u0019Qj??MX}(z".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = 'm';
                    break;
                }
                case 1: {
                    c16 = '9';
                    break;
                }
                case 2: {
                    c16 = '\u000f';
                    break;
                }
                case 3: {
                    c16 = 'M';
                    break;
                }
                default: {
                    c16 = 'Z';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z2[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "\u0019Qjm9\u0018Ky(z".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = 'm';
                    break;
                }
                case 1: {
                    c18 = '9';
                    break;
                }
                case 2: {
                    c18 = '\u000f';
                    break;
                }
                case 3: {
                    c18 = 'M';
                    break;
                }
                default: {
                    c18 = 'Z';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z2[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "MJ~8;\u001f\\/84\u0004M|".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = 'm';
                    break;
                }
                case 1: {
                    c20 = '9';
                    break;
                }
                case 2: {
                    c20 = '\u000f';
                    break;
                }
                case 3: {
                    c20 = 'M';
                    break;
                }
                default: {
                    c20 = 'Z';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z2[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = "\u0019Qjm;\u001f\\nm".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = 'm';
                    break;
                }
                case 1: {
                    c22 = '9';
                    break;
                }
                case 2: {
                    c22 = '\u000f';
                    break;
                }
                case 3: {
                    c22 = 'M';
                    break;
                }
                default: {
                    c22 = 'Z';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        z2[n31] = new String(charArray11).intern();
        final int n34 = 11;
        final char[] charArray12 = "$Wy,6\u0004]/9#\u001d\\/$4M||93\u0000X{(\u0019\fWy,)C^j9\u001f\u0015Ic,4\fMf\"4E\u0010".toCharArray();
        final int length8 = charArray12.length;
        for (int n35 = 0; length8 > n35; ++n35) {
            final int n36 = n35;
            final char c23 = charArray12[n36];
            char c24 = '\0';
            switch (n35 % 5) {
                case 0: {
                    c24 = 'm';
                    break;
                }
                case 1: {
                    c24 = '9';
                    break;
                }
                case 2: {
                    c24 = '\u000f';
                    break;
                }
                case 3: {
                    c24 = 'M';
                    break;
                }
                default: {
                    c24 = 'Z';
                    break;
                }
            }
            charArray12[n36] = (char)(c23 ^ c24);
        }
        z2[n34] = new String(charArray12).intern();
        z = z2;
    }
}
