// 
// Decompiled by Procyon v0.5.30
// 

package cfg8;

import java.util.Vector;

final class u
{
    private String a;
    private String b;
    private x c;
    private static String[] z;
    
    public u() {
        this.a = "";
        this.b = "";
        this.c = new x(this);
    }
    
    protected x a() {
        return this.c;
    }
    
    protected String b() {
        final int a = RotationImageFilter.a;
        String s = u.z[16];
        final int length = this.b.length();
        final int n = 0;
        final String a2;
        if (a == 0) {
            if (length > n) {
                s = String.valueOf(s).concat(String.valueOf(String.valueOf(String.valueOf(u.z[13]).concat(String.valueOf(this.b))).concat(String.valueOf("\""))));
            }
            s = String.valueOf(s).concat(String.valueOf(u.z[14]));
            a2 = this.a;
            if (a != 0) {
                return a2;
            }
            a2.length();
        }
        if (length > n) {
            s = String.valueOf(s).concat(String.valueOf(String.valueOf(String.valueOf(u.z[15]).concat(String.valueOf(this.a))).concat(String.valueOf(u.z[17]))));
        }
        String.valueOf(s).concat(String.valueOf(this.c.i()));
        return a2;
    }
    
    protected boolean a(final String s) {
        final int a = RotationImageFilter.a;
        this.c.l();
        final Vector<String> vector = new Vector<String>();
        final int length = s.length();
        int i = 0;
        int n3;
        while (true) {
            while (i < length) {
                final int index;
                int n = index = s.indexOf(10, i);
                final int n2 = 0;
                if (a != 0) {
                    if (index > n2) {
                        n3 = 1;
                    }
                    else {
                        n3 = 0;
                    }
                    return n3 != 0;
                }
                String s2 = null;
                Label_0078: {
                    if (index < n2) {
                        s2 = s.substring(i);
                        if (a == 0) {
                            break Label_0078;
                        }
                    }
                    s2 = s.substring(i, n);
                }
                int length2;
                final int n4 = length2 = s2.length();
                int n6;
                final int n5 = n6 = 1;
                if (a == 0) {
                    Label_0207: {
                        if (n4 > n5) {
                            int index2;
                            final int n7 = index2 = s2.indexOf(u.z[5]);
                            int n9;
                            final int n8 = n9 = 0;
                            if (a == 0) {
                                if (n7 >= n8) {
                                    n = s.indexOf(u.z[7], i);
                                    if (a == 0) {
                                        if (n < 0) {
                                            return false;
                                        }
                                        n += 2;
                                    }
                                    if (a == 0) {
                                        break Label_0207;
                                    }
                                }
                                final int index3;
                                index2 = (index3 = s2.indexOf(u.z[8]));
                                final int n10;
                                n9 = (n10 = 0);
                            }
                            Label_0201: {
                                Label_0196: {
                                    if (a == 0) {
                                        if (n7 < n8) {
                                            break Label_0201;
                                        }
                                        n = s.indexOf(u.z[6], i);
                                        if (a != 0) {
                                            break Label_0196;
                                        }
                                        index2 = n;
                                        n9 = 0;
                                    }
                                    if (index2 < n9) {
                                        return false;
                                    }
                                    n += 3;
                                }
                                if (a == 0) {
                                    break Label_0207;
                                }
                            }
                            vector.addElement(s2);
                        }
                    }
                    final int n11;
                    length2 = (n11 = n);
                    final int n12;
                    n6 = (n12 = 0);
                }
                if (a == 0) {
                    if (n4 < n5 && a == 0) {
                        break;
                    }
                    length2 = n;
                    n6 = 1;
                }
                i = length2 + n6;
                if (a != 0) {
                    break;
                }
            }
            final int a2;
            n3 = (a2 = x.a(this.c, vector, 0));
            if (a == 0) {
                continue;
            }
            break;
        }
        return n3 != 0;
    }
    
    static String b(final String s) {
        final int a = RotationImageFilter.a;
        String s2 = s;
        if (a == 0) {
            if (s == null) {
                return "";
            }
            s2 = "";
        }
        String s3 = s2;
        final int length = s.length();
        int i = 0;
        while (i < length) {
            final String s4 = s;
            if (a != 0) {
                return s4;
            }
            final char char1 = s.charAt(i);
            Label_0224: {
                Label_0192: {
                    Label_0170: {
                        Label_0148: {
                            Label_0126: {
                                if (a == 0) {
                                    switch (char1) {
                                        case 38: {
                                            s3 = String.valueOf(s3).concat(String.valueOf(u.z[12]));
                                            break;
                                        }
                                        case 60: {
                                            break Label_0126;
                                        }
                                        case 62: {
                                            break Label_0148;
                                        }
                                        case 34: {
                                            break Label_0170;
                                        }
                                        case 128: {
                                            break Label_0192;
                                        }
                                    }
                                }
                                if (a == 0) {
                                    break Label_0224;
                                }
                            }
                            s3 = String.valueOf(s3).concat(String.valueOf(u.z[10]));
                            if (a == 0) {
                                break Label_0224;
                            }
                        }
                        s3 = String.valueOf(s3).concat(String.valueOf(u.z[11]));
                        if (a == 0) {
                            break Label_0224;
                        }
                    }
                    s3 = String.valueOf(s3).concat(String.valueOf(u.z[9]));
                    if (a == 0) {
                        break Label_0224;
                    }
                }
                s3 = String.valueOf(s3).concat(String.valueOf('¤'));
                if (a == 0) {
                    break Label_0224;
                }
                s3 = String.valueOf(s3).concat(String.valueOf(char1));
            }
            ++i;
            if (a != 0) {
                break;
            }
        }
        return s3;
    }
    
    static String c(final String s) {
        final int a = RotationImageFilter.a;
        String s2 = "";
        int i = s.indexOf(38);
        int n = 0;
        while (true) {
            while (i >= 0) {
                final int index = s.indexOf(59, i + 1);
                final int n3;
                final int n2 = n3 = index;
                final int length;
                final int n4 = length = 0;
                if (a != 0) {
                    if (n3 < length) {
                        s2 = String.valueOf(s2).concat(String.valueOf(s.substring(n)));
                    }
                    s2.replace('¤', '\u20ac');
                    return s2;
                }
                if (a == 0 && n2 < n4) {
                    return s2;
                }
                if (n2 > n4) {
                    s2 = String.valueOf(s2).concat(String.valueOf(s.substring(n, i)));
                }
                final String substring = s.substring(i + 1, index);
                int n10;
                int n9;
                int n8;
                int n7;
                int n6;
                final int n5 = n6 = (n7 = (n8 = (n9 = (n10 = (substring.equals(u.z[3]) ? 1 : 0)))));
                Label_0265: {
                    Label_0259: {
                        if (a == 0) {
                            if (n5 != 0) {
                                s2 = String.valueOf(s2).concat(String.valueOf('&'));
                                if (a == 0) {
                                    break Label_0259;
                                }
                            }
                            final int n11;
                            n6 = (n11 = (n7 = (n8 = (n9 = (n10 = (substring.equals(u.z[2]) ? 1 : 0))))));
                        }
                        if (a == 0) {
                            if (n5 != 0) {
                                s2 = String.valueOf(s2).concat(String.valueOf('<'));
                                if (a == 0) {
                                    break Label_0259;
                                }
                            }
                            n7 = (n6 = (n8 = (n9 = (n10 = (substring.equals(u.z[1]) ? 1 : 0)))));
                        }
                        if (a == 0) {
                            if (n6 != 0) {
                                s2 = String.valueOf(s2).concat(String.valueOf('>'));
                                if (a == 0) {
                                    break Label_0259;
                                }
                            }
                            n8 = (n7 = (n9 = (n10 = (substring.equals(u.z[0]) ? 1 : 0))));
                        }
                        if (a == 0) {
                            if (n7 != 0) {
                                s2 = String.valueOf(s2).concat(String.valueOf('\"'));
                                if (a == 0) {
                                    break Label_0259;
                                }
                            }
                            n9 = (n8 = (n10 = (substring.equals(u.z[4]) ? 1 : 0)));
                        }
                        if (a != 0) {
                            break Label_0265;
                        }
                        if (n8 != 0) {
                            s2 = String.valueOf(s2).concat(String.valueOf("\u20ac"));
                        }
                    }
                    n = index + 1;
                    n10 = (n9 = n);
                }
                if (a == 0) {
                    if (n9 >= s.length() && a == 0) {
                        break;
                    }
                    n10 = s.indexOf(38, n);
                }
                i = n10;
                if (a != 0) {
                    break;
                }
            }
            int n3 = n;
            int length = s.length();
            continue;
        }
    }
    
    static {
        final String[] z = new String[18];
        final int n = 0;
        final char[] charArray = "\u007f=PB".toCharArray();
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
                            c2 = '\u000e';
                            break;
                        }
                        case 1: {
                            c2 = 'H';
                            break;
                        }
                        case 2: {
                            c2 = '?';
                            break;
                        }
                        case 3: {
                            c2 = '6';
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
        final char[] charArray2 = "i<".toCharArray();
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
                            c4 = '\u000e';
                            break;
                        }
                        case 1: {
                            c4 = 'H';
                            break;
                        }
                        case 2: {
                            c4 = '?';
                            break;
                        }
                        case 3: {
                            c4 = '6';
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
        final char[] charArray3 = "b<".toCharArray();
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
                            c6 = '\u000e';
                            break;
                        }
                        case 1: {
                            c6 = 'H';
                            break;
                        }
                        case 2: {
                            c6 = '?';
                            break;
                        }
                        case 3: {
                            c6 = '6';
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
        final char[] charArray4 = "o%O".toCharArray();
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
                            c8 = '\u000e';
                            break;
                        }
                        case 1: {
                            c8 = 'H';
                            break;
                        }
                        case 2: {
                            c8 = '?';
                            break;
                        }
                        case 3: {
                            c8 = '6';
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
        final char[] charArray5 = "k=MY".toCharArray();
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
                            c10 = '\u000e';
                            break;
                        }
                        case 1: {
                            c10 = 'H';
                            break;
                        }
                        case 2: {
                            c10 = '?';
                            break;
                        }
                        case 3: {
                            c10 = '6';
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
        final char[] charArray6 = "2w".toCharArray();
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
                            c12 = '\u000e';
                            break;
                        }
                        case 1: {
                            c12 = 'H';
                            break;
                        }
                        case 2: {
                            c12 = '?';
                            break;
                        }
                        case 3: {
                            c12 = '6';
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
        final char[] charArray7 = "#e\u0001".toCharArray();
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
                            c14 = '\u000e';
                            break;
                        }
                        case 1: {
                            c14 = 'H';
                            break;
                        }
                        case 2: {
                            c14 = '?';
                            break;
                        }
                        case 3: {
                            c14 = '6';
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
        final char[] charArray8 = "1v".toCharArray();
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
                            c16 = '\u000e';
                            break;
                        }
                        case 1: {
                            c16 = 'H';
                            break;
                        }
                        case 2: {
                            c16 = '?';
                            break;
                        }
                        case 3: {
                            c16 = '6';
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
        final char[] charArray9 = "2i\u0012\u001b".toCharArray();
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
                            c18 = '\u000e';
                            break;
                        }
                        case 1: {
                            c18 = 'H';
                            break;
                        }
                        case 2: {
                            c18 = '?';
                            break;
                        }
                        case 3: {
                            c18 = '6';
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
            if (n34 > n36) {
                continue;
            }
            break;
        }
        z[n33] = new String(charArray9).intern();
        final int n37 = 9;
        final char[] charArray10 = "(9JY\u000e5".toCharArray();
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
                            c20 = '\u000e';
                            break;
                        }
                        case 1: {
                            c20 = 'H';
                            break;
                        }
                        case 2: {
                            c20 = '?';
                            break;
                        }
                        case 3: {
                            c20 = '6';
                            break;
                        }
                        default: {
                            c20 = 'z';
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
        z[n37] = new String(charArray10).intern();
        final int n41 = 10;
        final char[] charArray11 = "($K\r".toCharArray();
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
                            c22 = '\u000e';
                            break;
                        }
                        case 1: {
                            c22 = 'H';
                            break;
                        }
                        case 2: {
                            c22 = '?';
                            break;
                        }
                        case 3: {
                            c22 = '6';
                            break;
                        }
                        default: {
                            c22 = 'z';
                            break;
                        }
                    }
                    charArray11[length11] = (char)(c21 ^ c22);
                    ++n44;
                } while (n42 == 0);
            }
            if (n42 > n44) {
                continue;
            }
            break;
        }
        z[n41] = new String(charArray11).intern();
        final int n45 = 11;
        final char[] charArray12 = "(/K\r".toCharArray();
        int length12;
        int n47;
        final int n46 = n47 = (length12 = charArray12.length);
        int n48 = 0;
        while (true) {
            Label_1398: {
                if (n46 > 1) {
                    break Label_1398;
                }
                length12 = (n47 = n48);
                do {
                    final char c23 = charArray12[n47];
                    char c24 = '\0';
                    switch (n48 % 5) {
                        case 0: {
                            c24 = '\u000e';
                            break;
                        }
                        case 1: {
                            c24 = 'H';
                            break;
                        }
                        case 2: {
                            c24 = '?';
                            break;
                        }
                        case 3: {
                            c24 = '6';
                            break;
                        }
                        default: {
                            c24 = 'z';
                            break;
                        }
                    }
                    charArray12[length12] = (char)(c23 ^ c24);
                    ++n48;
                } while (n46 == 0);
            }
            if (n46 > n48) {
                continue;
            }
            break;
        }
        z[n45] = new String(charArray12).intern();
        final int n49 = 12;
        final char[] charArray13 = "()RFA".toCharArray();
        int length13;
        int n51;
        final int n50 = n51 = (length13 = charArray13.length);
        int n52 = 0;
        while (true) {
            Label_1518: {
                if (n50 > 1) {
                    break Label_1518;
                }
                length13 = (n51 = n52);
                do {
                    final char c25 = charArray13[n51];
                    char c26 = '\0';
                    switch (n52 % 5) {
                        case 0: {
                            c26 = '\u000e';
                            break;
                        }
                        case 1: {
                            c26 = 'H';
                            break;
                        }
                        case 2: {
                            c26 = '?';
                            break;
                        }
                        case 3: {
                            c26 = '6';
                            break;
                        }
                        default: {
                            c26 = 'z';
                            break;
                        }
                    }
                    charArray13[length13] = (char)(c25 ^ c26);
                    ++n52;
                } while (n50 == 0);
            }
            if (n50 > n52) {
                continue;
            }
            break;
        }
        z[n49] = new String(charArray13).intern();
        final int n53 = 13;
        final char[] charArray14 = ".-QU\u0015j!QQG,".toCharArray();
        int length14;
        int n55;
        final int n54 = n55 = (length14 = charArray14.length);
        int n56 = 0;
        while (true) {
            Label_1638: {
                if (n54 > 1) {
                    break Label_1638;
                }
                length14 = (n55 = n56);
                do {
                    final char c27 = charArray14[n55];
                    char c28 = '\0';
                    switch (n56 % 5) {
                        case 0: {
                            c28 = '\u000e';
                            break;
                        }
                        case 1: {
                            c28 = 'H';
                            break;
                        }
                        case 2: {
                            c28 = '?';
                            break;
                        }
                        case 3: {
                            c28 = '6';
                            break;
                        }
                        default: {
                            c28 = 'z';
                            break;
                        }
                    }
                    charArray14[length14] = (char)(c27 ^ c28);
                    ++n56;
                } while (n54 == 0);
            }
            if (n54 > n56) {
                continue;
            }
            break;
        }
        z[n53] = new String(charArray14).intern();
        final int n57 = 14;
        final char[] charArray15 = "1v5".toCharArray();
        int length15;
        int n59;
        final int n58 = n59 = (length15 = charArray15.length);
        int n60 = 0;
        while (true) {
            Label_1758: {
                if (n58 > 1) {
                    break Label_1758;
                }
                length15 = (n59 = n60);
                do {
                    final char c29 = charArray15[n59];
                    char c30 = '\0';
                    switch (n60 % 5) {
                        case 0: {
                            c30 = '\u000e';
                            break;
                        }
                        case 1: {
                            c30 = 'H';
                            break;
                        }
                        case 2: {
                            c30 = '?';
                            break;
                        }
                        case 3: {
                            c30 = '6';
                            break;
                        }
                        default: {
                            c30 = 'z';
                            break;
                        }
                    }
                    charArray15[length15] = (char)(c29 ^ c30);
                    ++n60;
                } while (n58 == 0);
            }
            if (n58 > n60) {
                continue;
            }
            break;
        }
        z[n57] = new String(charArray15).intern();
        final int n61 = 15;
        final char[] charArray16 = "2wG[\u0016#;KO\u0016k;WS\u001fzhKO\nku\u001dB\u001fv<\u0010N\tbj\u001f^\bk.\u0002\u0014".toCharArray();
        int length16;
        int n63;
        final int n62 = n63 = (length16 = charArray16.length);
        int n64 = 0;
        while (true) {
            Label_1878: {
                if (n62 > 1) {
                    break Label_1878;
                }
                length16 = (n63 = n64);
                do {
                    final char c31 = charArray16[n63];
                    char c32 = '\0';
                    switch (n64 % 5) {
                        case 0: {
                            c32 = '\u000e';
                            break;
                        }
                        case 1: {
                            c32 = 'H';
                            break;
                        }
                        case 2: {
                            c32 = '?';
                            break;
                        }
                        case 3: {
                            c32 = '6';
                            break;
                        }
                        default: {
                            c32 = 'z';
                            break;
                        }
                    }
                    charArray16[length16] = (char)(c31 ^ c32);
                    ++n64;
                } while (n62 == 0);
            }
            if (n62 > n64) {
                continue;
            }
            break;
        }
        z[n61] = new String(charArray16).intern();
        final int n65 = 16;
        final char[] charArray17 = "2wG[\u0016.>ZD\tg'Q\u000bX?f\u000f\u0014".toCharArray();
        int length17;
        int n67;
        final int n66 = n67 = (length17 = charArray17.length);
        int n68 = 0;
        while (true) {
            Label_1998: {
                if (n66 > 1) {
                    break Label_1998;
                }
                length17 = (n67 = n68);
                do {
                    final char c33 = charArray17[n67];
                    char c34 = '\0';
                    switch (n68 % 5) {
                        case 0: {
                            c34 = '\u000e';
                            break;
                        }
                        case 1: {
                            c34 = 'H';
                            break;
                        }
                        case 2: {
                            c34 = '?';
                            break;
                        }
                        case 3: {
                            c34 = '6';
                            break;
                        }
                        default: {
                            c34 = 'z';
                            break;
                        }
                    }
                    charArray17[length17] = (char)(c33 ^ c34);
                    ++n68;
                } while (n66 == 0);
            }
            if (n66 > n68) {
                continue;
            }
            break;
        }
        z[n65] = new String(charArray17).intern();
        final int n69 = 17;
        final char[] charArray18 = ",w\u0001<".toCharArray();
        int length18;
        int n71;
        final int n70 = n71 = (length18 = charArray18.length);
        int n72 = 0;
        while (true) {
            Label_2118: {
                if (n70 > 1) {
                    break Label_2118;
                }
                length18 = (n71 = n72);
                do {
                    final char c35 = charArray18[n71];
                    char c36 = '\0';
                    switch (n72 % 5) {
                        case 0: {
                            c36 = '\u000e';
                            break;
                        }
                        case 1: {
                            c36 = 'H';
                            break;
                        }
                        case 2: {
                            c36 = '?';
                            break;
                        }
                        case 3: {
                            c36 = '6';
                            break;
                        }
                        default: {
                            c36 = 'z';
                            break;
                        }
                    }
                    charArray18[length18] = (char)(c35 ^ c36);
                    ++n72;
                } while (n70 == 0);
            }
            if (n70 <= n72) {
                z[n69] = new String(charArray18).intern();
                u.z = z;
                return;
            }
            continue;
        }
    }
}
