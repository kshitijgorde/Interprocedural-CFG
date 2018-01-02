// 
// Decompiled by Procyon v0.5.30
// 

package a.b.p;

public abstract class b
{
    private static String[] z;
    
    public static int a(final byte[] array, final int n) throws a {
        if (array == null) {
            throw new a(b.z[4]);
        }
        if (n >= array.length || n < 0) {
            throw new a(b.z[5] + n + b.z[1] + array.length + ".");
        }
        if (n + 4 > array.length) {
            throw new a(b.z[2] + array.length + b.z[3] + (n + 4) + ".");
        }
        int n2 = 0;
        for (int i = n; i < 4 + n; ++i) {
            try {
                int n3 = array[i];
                if (n3 < 0) {
                    n3 += 256;
                }
                n2 = (n2 << 8) + n3;
            }
            catch (IndexOutOfBoundsException ex) {
                throw new a(b.z[0]);
            }
        }
        return n2;
    }
    
    public static int a(final int n, final byte[] array, final int n2) throws a {
        if (array == null) {
            throw new a(b.z[20]);
        }
        if (n2 >= array.length || n2 < 0) {
            throw new a(b.z[17] + n2 + b.z[1] + array.length + ".");
        }
        if (n2 + 4 > array.length) {
            throw new a(b.z[19] + array.length + b.z[18] + (n2 + 4) + ".");
        }
        for (int i = 0; i < 4; ++i) {
            array[3 - i + n2] = (byte)(n >> i * 8 & 0xFF);
        }
        return 4;
    }
    
    public static long b(final byte[] array, final int n) throws a {
        if (array == null) {
            throw new a(b.z[10]);
        }
        if (n >= array.length || n < 0) {
            throw new a(b.z[8] + n + b.z[1] + array.length + ".");
        }
        if (n + 8 > array.length) {
            throw new a(b.z[7] + array.length + b.z[6] + (n + 8) + ".");
        }
        long n2 = 0L;
        for (int i = n; i < 8 + n; ++i) {
            try {
                int n3 = array[i];
                if (n3 < 0) {
                    n3 += 256;
                }
                n2 = (n2 << 8) + n3;
            }
            catch (IndexOutOfBoundsException ex) {
                throw new a(b.z[9]);
            }
        }
        return n2;
    }
    
    public static int a(final long n, final byte[] array, final int n2) throws a {
        if (array == null) {
            throw new a(b.z[10]);
        }
        if (n2 >= array.length || n2 < 0) {
            throw new a(b.z[15] + n2 + b.z[1] + array.length + ".");
        }
        if (n2 + 8 > array.length) {
            throw new a(b.z[16] + array.length + b.z[6] + (n2 + 8) + ".");
        }
        for (int i = 0; i < 8; ++i) {
            array[7 - i + n2] = (byte)(n >> i * 8 & 0xFFL);
        }
        return 8;
    }
    
    public static byte[] a(final byte[] array, final int n, final int n2) throws a {
        if (array == null) {
            throw new a(b.z[11]);
        }
        final byte[] array2 = new byte[n2];
        try {
            System.arraycopy(array, n, array2, 0, n2);
        }
        catch (IndexOutOfBoundsException ex) {
            throw new a(b.z[13] + (array.length - n) + b.z[14] + n2 + b.z[12]);
        }
        return array2;
    }
    
    static {
        final String[] z = new String[21];
        final int n = 0;
        final char[] charArray = "{&\fk\u0016\\)\u0014.\u0014@'\u001bk\u0017A!\u0016iD].Xg\nF-\u001fk\u0016\u0012/\u001d`\u0001@)\fg\u000b\\h\u001eo\r^-\u001c ".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '2';
                    break;
                }
                case 1: {
                    c2 = 'H';
                    break;
                }
                case 2: {
                    c2 = 'x';
                    break;
                }
                case 3: {
                    c2 = '\u000e';
                    break;
                }
                default: {
                    c2 = 'd';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\u0012?\u0011z\f\u0012)\u0016.\u0005@:\u0019wD^-\u0016i\u0010Zh\u0017hD".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '2';
                    break;
                }
                case 1: {
                    c4 = 'H';
                    break;
                }
                case 2: {
                    c4 = 'x';
                    break;
                }
                case 3: {
                    c4 = '\u000e';
                    break;
                }
                default: {
                    c4 = 'd';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "q)\u0016`\u000bFh\u001fk\nW:\u0019z\u0001\u0012)\u0016.\r\\<\u001di\u0001@h\u001e|\u000b_h\ff\u0001\u0012,\u0019z\u0005\u0012)\n|\u0005KsXo\u0016@)\u0001.\rAh".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '2';
                    break;
                }
                case 1: {
                    c6 = 'H';
                    break;
                }
                case 2: {
                    c6 = 'x';
                    break;
                }
                case 3: {
                    c6 = '\u000e';
                    break;
                }
                default: {
                    c6 = 'd';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "\u0012$\u0017`\u0003\u001eh\u001a{\u0010\u0012!\u0016z\u0001U-\n.\u0000S<\u0019.\u0001\\,\u000b.\u0005Fh\ba\u0017[<\u0011a\n\u0012".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '2';
                    break;
                }
                case 1: {
                    c8 = 'H';
                    break;
                }
                case 2: {
                    c8 = 'x';
                    break;
                }
                case 3: {
                    c8 = '\u000e';
                    break;
                }
                default: {
                    c8 = 'd';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "q)\u0016`\u000bFh\u001fk\nW:\u0019z\u0001\u0012)\u0016.\r\\<\u001di\u0001@h\u001e|\u000b_h\u0019.\nG$\u0014.\u0000S<\u0019.\u0005@:\u0019wJ".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = '2';
                    break;
                }
                case 1: {
                    c10 = 'H';
                    break;
                }
                case 2: {
                    c10 = 'x';
                    break;
                }
                case 3: {
                    c10 = '\u000e';
                    break;
                }
                default: {
                    c10 = 'd';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "q)\u0016`\u000bFh\u000bz\u0005@<Xo\n\u0012!\u0016z\u0001U-\n#\u0003W&\u001d|\u0005F!\u0017`DT:\u0017cDB'\u000bg\u0010['\u0016.".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = '2';
                    break;
                }
                case 1: {
                    c12 = 'H';
                    break;
                }
                case 2: {
                    c12 = 'x';
                    break;
                }
                case 3: {
                    c12 = '\u000e';
                    break;
                }
                default: {
                    c12 = 'd';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "\u0012$\u0017`\u0003\u001eh\u001a{\u0010\u0012$\u0017`\u0003\u0012,\u0019z\u0005\u0012-\u0016j\u0017\u0012)\f.\u0014];\u0011z\r]&X".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = '2';
                    break;
                }
                case 1: {
                    c14 = 'H';
                    break;
                }
                case 2: {
                    c14 = 'x';
                    break;
                }
                case 3: {
                    c14 = '\u000e';
                    break;
                }
                default: {
                    c14 = 'd';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "q)\u0016`\u000bFh\u001fk\nW:\u0019z\u0001\u0012)\u0016.\b]&\u001f.\u0002@'\u0015.\u0010Z-Xj\u0005F)Xo\u0016@)\u00015DS:\no\u001d\u0012!\u000b.".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = '2';
                    break;
                }
                case 1: {
                    c16 = 'H';
                    break;
                }
                case 2: {
                    c16 = 'x';
                    break;
                }
                case 3: {
                    c16 = '\u000e';
                    break;
                }
                default: {
                    c16 = 'd';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "q)\u0016`\u000bFh\u000bz\u0005@<Xo\n\u0012$\u0017`\u0003\u001f/\u001d`\u0001@)\fg\u000b\\h\u001e|\u000b_h\ba\u0017[<\u0011a\n\u0012".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = '2';
                    break;
                }
                case 1: {
                    c18 = 'H';
                    break;
                }
                case 2: {
                    c18 = 'x';
                    break;
                }
                case 3: {
                    c18 = '\u000e';
                    break;
                }
                default: {
                    c18 = 'd';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "{&\fk\u0016\\)\u0014.\u0014@'\u001bk\u0017A!\u0016iD].Xb\u000b\\/Xi\u0001\\-\no\u0010['\u0016.\u0002S!\u0014k\u0000\u001c".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = '2';
                    break;
                }
                case 1: {
                    c20 = 'H';
                    break;
                }
                case 2: {
                    c20 = 'x';
                    break;
                }
                case 3: {
                    c20 = '\u000e';
                    break;
                }
                default: {
                    c20 = 'd';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = "q)\u0016`\u000bFh\u001fk\nW:\u0019z\u0001\u0012)Xb\u000b\\/Xh\u0016]%XoD\\=\u0014bDV)\foDS:\no\u001d\u001c".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = '2';
                    break;
                }
                case 1: {
                    c22 = 'H';
                    break;
                }
                case 2: {
                    c22 = 'x';
                    break;
                }
                case 3: {
                    c22 = '\u000e';
                    break;
                }
                default: {
                    c22 = 'd';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        z[n31] = new String(charArray11).intern();
        final int n34 = 11;
        final char[] charArray12 = "|'Xg\nB=\f.\u0005@:\u0019wDT:\u0017cDE \u0011m\f\u0012<\u0017.\u0014G$\u0014.\u0000S<\u0019.\u0010]h\u000bz\u0011T.V".toCharArray();
        final int length8 = charArray12.length;
        for (int n35 = 0; length8 > n35; ++n35) {
            final int n36 = n35;
            final char c23 = charArray12[n36];
            char c24 = '\0';
            switch (n35 % 5) {
                case 0: {
                    c24 = '2';
                    break;
                }
                case 1: {
                    c24 = 'H';
                    break;
                }
                case 2: {
                    c24 = 'x';
                    break;
                }
                case 3: {
                    c24 = '\u000e';
                    break;
                }
                default: {
                    c24 = 'd';
                    break;
                }
            }
            charArray12[n36] = (char)(c23 ^ c24);
        }
        z[n34] = new String(charArray12).intern();
        final int n37 = 12;
        final char[] charArray13 = "\u001bf".toCharArray();
        final int length9 = charArray13.length;
        for (int n38 = 0; length9 > n38; ++n38) {
            final int n39 = n38;
            final char c25 = charArray13[n39];
            char c26 = '\0';
            switch (n38 % 5) {
                case 0: {
                    c26 = '2';
                    break;
                }
                case 1: {
                    c26 = 'H';
                    break;
                }
                case 2: {
                    c26 = 'x';
                    break;
                }
                case 3: {
                    c26 = '\u000e';
                    break;
                }
                default: {
                    c26 = 'd';
                    break;
                }
            }
            charArray13[n39] = (char)(c25 ^ c26);
        }
        z[n37] = new String(charArray13).intern();
        final int n40 = 13;
        final char[] charArray14 = "|'\f.\u0001\\'\ri\f\u0012*\u0001z\u0001Ah\u0011`DF \u001d.\r\\8\rzDS:\no\u001d\u0012`".toCharArray();
        final int length10 = charArray14.length;
        for (int n41 = 0; length10 > n41; ++n41) {
            final int n42 = n41;
            final char c27 = charArray14[n42];
            char c28 = '\0';
            switch (n41 % 5) {
                case 0: {
                    c28 = '2';
                    break;
                }
                case 1: {
                    c28 = 'H';
                    break;
                }
                case 2: {
                    c28 = 'x';
                    break;
                }
                case 3: {
                    c28 = '\u000e';
                    break;
                }
                default: {
                    c28 = 'd';
                    break;
                }
            }
            charArray14[n42] = (char)(c27 ^ c28);
        }
        z[n40] = new String(charArray14).intern();
        final int n43 = 14;
        final char[] charArray15 = "\u001bh\faDA)\fg\u0017T1Xz\fWh\u001co\u0010Sh\nk\u0015G-\u000bzD\u001a".toCharArray();
        final int length11 = charArray15.length;
        for (int n44 = 0; length11 > n44; ++n44) {
            final int n45 = n44;
            final char c29 = charArray15[n45];
            char c30 = '\0';
            switch (n44 % 5) {
                case 0: {
                    c30 = '2';
                    break;
                }
                case 1: {
                    c30 = 'H';
                    break;
                }
                case 2: {
                    c30 = 'x';
                    break;
                }
                case 3: {
                    c30 = '\u000e';
                    break;
                }
                default: {
                    c30 = 'd';
                    break;
                }
            }
            charArray15[n45] = (char)(c29 ^ c30);
        }
        z[n43] = new String(charArray15).intern();
        final int n46 = 15;
        final char[] charArray16 = "q)\u0016`\u000bFh\u000f|\rF-XoD^'\u0016iDS<X~\u000bA!\fg\u000b\\h".toCharArray();
        final int length12 = charArray16.length;
        for (int n47 = 0; length12 > n47; ++n47) {
            final int n48 = n47;
            final char c31 = charArray16[n48];
            char c32 = '\0';
            switch (n47 % 5) {
                case 0: {
                    c32 = '2';
                    break;
                }
                case 1: {
                    c32 = 'H';
                    break;
                }
                case 2: {
                    c32 = 'x';
                    break;
                }
                case 3: {
                    c32 = '\u000e';
                    break;
                }
                default: {
                    c32 = 'd';
                    break;
                }
            }
            charArray16[n48] = (char)(c31 ^ c32);
        }
        z[n46] = new String(charArray16).intern();
        final int n49 = 16;
        final char[] charArray17 = "q)\u0016`\u000bFh\u000f|\rF-XoD^'\u0016iD[&\faDF \u001d.\u0003[>\u001d`DS:\no\u001d\th\u0019|\u0016S1Xg\u0017\u0012".toCharArray();
        final int length13 = charArray17.length;
        for (int n50 = 0; length13 > n50; ++n50) {
            final int n51 = n50;
            final char c33 = charArray17[n51];
            char c34 = '\0';
            switch (n50 % 5) {
                case 0: {
                    c34 = '2';
                    break;
                }
                case 1: {
                    c34 = 'H';
                    break;
                }
                case 2: {
                    c34 = 'x';
                    break;
                }
                case 3: {
                    c34 = '\u000e';
                    break;
                }
                default: {
                    c34 = 'd';
                    break;
                }
            }
            charArray17[n51] = (char)(c33 ^ c34);
        }
        z[n49] = new String(charArray17).intern();
        final int n52 = 17;
        final char[] charArray18 = "q)\u0016`\u000bFh\u000f|\rF-Xo\n\u0012!\u0016zDS<X~\u000bA!\fg\u000b\\h".toCharArray();
        final int length14 = charArray18.length;
        for (int n53 = 0; length14 > n53; ++n53) {
            final int n54 = n53;
            final char c35 = charArray18[n54];
            char c36 = '\0';
            switch (n53 % 5) {
                case 0: {
                    c36 = '2';
                    break;
                }
                case 1: {
                    c36 = 'H';
                    break;
                }
                case 2: {
                    c36 = 'x';
                    break;
                }
                case 3: {
                    c36 = '\u000e';
                    break;
                }
                default: {
                    c36 = 'd';
                    break;
                }
            }
            charArray18[n54] = (char)(c35 ^ c36);
        }
        z[n52] = new String(charArray18).intern();
        final int n55 = 18;
        final char[] charArray19 = "\u0012$\u0017`\u0003\u001eh\u001a{\u0010\u0012!\u0016zDV)\foDW&\u001c}DS<X~\u000bA!\fg\u000b\\h".toCharArray();
        final int length15 = charArray19.length;
        for (int n56 = 0; length15 > n56; ++n56) {
            final int n57 = n56;
            final char c37 = charArray19[n57];
            char c38 = '\0';
            switch (n56 % 5) {
                case 0: {
                    c38 = '2';
                    break;
                }
                case 1: {
                    c38 = 'H';
                    break;
                }
                case 2: {
                    c38 = 'x';
                    break;
                }
                case 3: {
                    c38 = '\u000e';
                    break;
                }
                default: {
                    c38 = 'd';
                    break;
                }
            }
            charArray19[n57] = (char)(c37 ^ c38);
        }
        z[n55] = new String(charArray19).intern();
        final int n58 = 19;
        final char[] charArray20 = "q)\u0016`\u000bFh\u000f|\rF-Xo\n\u0012!\u0016zD[&\faDF \u001d.\u0003[>\u001d`DS:\no\u001d\th\u0019|\u0016S1Xg\u0017\u0012".toCharArray();
        final int length16 = charArray20.length;
        for (int n59 = 0; length16 > n59; ++n59) {
            final int n60 = n59;
            final char c39 = charArray20[n60];
            char c40 = '\0';
            switch (n59 % 5) {
                case 0: {
                    c40 = '2';
                    break;
                }
                case 1: {
                    c40 = 'H';
                    break;
                }
                case 2: {
                    c40 = 'x';
                    break;
                }
                case 3: {
                    c40 = '\u000e';
                    break;
                }
                default: {
                    c40 = 'd';
                    break;
                }
            }
            charArray20[n60] = (char)(c39 ^ c40);
        }
        z[n58] = new String(charArray20).intern();
        final int n61 = 20;
        final char[] charArray21 = "q)\u0016`\u000bFh\u001fk\nW:\u0019z\u0001\u0012)\u0016.\r\\<Xh\u0016]%XoD\\=\u0014bDV)\foDS:\no\u001d\u001c".toCharArray();
        final int length17 = charArray21.length;
        for (int n62 = 0; length17 > n62; ++n62) {
            final int n63 = n62;
            final char c41 = charArray21[n63];
            char c42 = '\0';
            switch (n62 % 5) {
                case 0: {
                    c42 = '2';
                    break;
                }
                case 1: {
                    c42 = 'H';
                    break;
                }
                case 2: {
                    c42 = 'x';
                    break;
                }
                case 3: {
                    c42 = '\u000e';
                    break;
                }
                default: {
                    c42 = 'd';
                    break;
                }
            }
            charArray21[n63] = (char)(c41 ^ c42);
        }
        z[n61] = new String(charArray21).intern();
        b.z = z;
    }
}
