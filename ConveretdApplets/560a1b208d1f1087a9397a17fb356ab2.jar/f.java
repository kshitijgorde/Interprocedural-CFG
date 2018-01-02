// 
// Decompiled by Procyon v0.5.30
// 

class f
{
    protected static int[] a;
    protected static int[] b;
    protected static int[][][] c;
    protected static int[][][] d;
    protected static int[][][] e;
    protected static int[][] f;
    protected static int[] g;
    protected int[][] h;
    
    void a(final byte[] array, final int n, final int n2) {
        final int a = q.a;
        int n3 = n;
        while (true) {
            Label_1048: {
                if (a == 0) {
                    break Label_1048;
                }
                int n4 = f.d[0][array[n3] & 0xFF][0] | f.d[1][array[n3 + 1] & 0xFF][0] | f.d[2][array[n3 + 2] & 0xFF][0] | f.d[3][array[n3 + 3] & 0xFF][0] | f.d[4][array[n3 + 4] & 0xFF][0] | f.d[5][array[n3 + 5] & 0xFF][0] | f.d[6][array[n3 + 6] & 0xFF][0] | f.d[7][array[n3 + 7] & 0xFF][0];
                int n5 = f.d[0][array[n3] & 0xFF][1] | f.d[1][array[n3 + 1] & 0xFF][1] | f.d[2][array[n3 + 2] & 0xFF][1] | f.d[3][array[n3 + 3] & 0xFF][1] | f.d[4][array[n3 + 4] & 0xFF][1] | f.d[5][array[n3 + 5] & 0xFF][1] | f.d[6][array[n3 + 6] & 0xFF][1] | f.d[7][array[n3 + 7] & 0xFF][1];
                int i = 15;
            Block_2:
                while (true) {
                    final int n6 = (f.e[0][n5 & 0xFF][0] | f.e[1][n5 >>> 8 & 0xFF][0] | f.e[2][n5 >>> 16 & 0xFF][0] | f.e[3][n5 >>> 24 & 0xFF][0]) ^ this.h[i][0];
                    final int n7 = (f.e[0][n5 & 0xFF][1] | f.e[1][n5 >>> 8 & 0xFF][1] | f.e[2][n5 >>> 16 & 0xFF][1] | f.e[3][n5 >>> 24 & 0xFF][1]) ^ this.h[i][1];
                    final int n8 = n4 ^ (f.f[7][n7 >>> 10 & 0x3F] | f.f[6][n7 >>> 4 & 0x3F] | f.f[5][(n7 << 2 & 0x3C) | (n6 >>> 30 & 0x3)] | f.f[4][n6 >>> 24 & 0x3F] | f.f[3][n6 >>> 18 & 0x3F] | f.f[2][n6 >>> 12 & 0x3F] | f.f[1][n6 >>> 6 & 0x3F] | f.f[0][n6 & 0x3F]);
                    n4 = n5;
                    n5 = n8;
                    --i;
                    while (i < 0) {
                        i = (f.c[0][n5 & 0xFF][0] | f.c[1][n5 >>> 8 & 0xFF][0] | f.c[2][n5 >>> 16 & 0xFF][0] | f.c[3][n5 >>> 24 & 0xFF][0] | f.c[4][n4 & 0xFF][0] | f.c[5][n4 >>> 8 & 0xFF][0] | f.c[6][n4 >>> 16 & 0xFF][0] | f.c[7][n4 >>> 24 & 0xFF][0]);
                        final int n9 = f.c[0][n5 & 0xFF][1] | f.c[1][n5 >>> 8 & 0xFF][1] | f.c[2][n5 >>> 16 & 0xFF][1] | f.c[3][n5 >>> 24 & 0xFF][1] | f.c[4][n4 & 0xFF][1] | f.c[5][n4 >>> 8 & 0xFF][1] | f.c[6][n4 >>> 16 & 0xFF][1] | f.c[7][n4 >>> 24 & 0xFF][1];
                        array[n3] = (byte)(i & 0xFF);
                        array[n3 + 1] = (byte)(i >>> 8 & 0xFF);
                        array[n3 + 2] = (byte)(i >>> 16 & 0xFF);
                        array[n3 + 3] = (byte)(i >>> 24 & 0xFF);
                        array[n3 + 4] = (byte)(n9 & 0xFF);
                        array[n3 + 5] = (byte)(n9 >>> 8 & 0xFF);
                        array[n3 + 6] = (byte)(n9 >>> 16 & 0xFF);
                        array[n3 + 7] = (byte)(n9 >>> 24 & 0xFF);
                        if (a == 0) {
                            break Block_2;
                        }
                    }
                }
                n3 += 8;
            }
            if (n3 >= n + n2) {
                return;
            }
            continue;
        }
    }
    
    f(final long n) {
        this.h = new int[16][2];
        this.c(n);
    }
    
    protected long a(final long n) {
        long n2 = 0L;
        int n3 = 0;
        long n5 = 0L;
    Label_0005:
        while (true) {
            final long n4 = n2 | (n >> f.b[n3] & 0x1L) << n3;
            do {
                n2 = n5;
                if (++n3 < 48) {
                    continue Label_0005;
                }
                n5 = n2;
            } while (q.a != 0);
            break;
        }
        return n5;
    }
    
    protected long b(final long n) {
        long n2 = 0L;
        int n3 = 0;
        long n5 = 0L;
    Label_0005:
        while (true) {
            final long n4 = n2 | (n >> f.a[n3] & 0x1L) << n3;
            do {
                n2 = n5;
                if (++n3 < 56) {
                    continue Label_0005;
                }
                n5 = n2;
            } while (q.a != 0);
            break;
        }
        return n5;
    }
    
    static {
        final int i = q.a;
        f.a = new int[56];
        f.b = new int[48];
        f.c = new int[8][256][2];
        f.d = new int[8][256][2];
        f.e = new int[4][256][2];
        f.f = new int[8][64];
        f.g = new int[16];
        final int[] array = new int[64];
        int[] array2 = { 959523105, 420546817, 993209123, 454232835, 1026895141, 487918853, 1060581159, 521604871, 942680096, 403703808, 976366114, 437389826, 1010052132, 471075844, 1043738150, 504761862 };
        int n = 0;
        int[] array4;
        while (true) {
            while (true) {
                Label_0206: {
                    if (i == 0) {
                        break Label_0206;
                    }
                    final int[] array3 = array;
                    array3[n] = (array2[n / 4] >>> 8 * (3 - n % 4) & 0xFF);
                    ++n;
                }
                if (n < array.length) {
                    continue;
                }
                break;
            }
            array2 = new int[48];
            int[] array3;
            array4 = (array3 = new int[8]);
            array4[0] = 1040222308;
            array4[1] = 105027816;
            array4[2] = 243575148;
            array4[3] = 382122480;
            array4[4] = 520669812;
            array4[5] = 659217144;
            array4[6] = 797764476;
            array4[7] = 936311776;
            if (i != 0) {
                continue;
            }
            break;
        }
        int[] array5 = array4;
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0300: {
                    if (i == 0) {
                        break Label_0300;
                    }
                    array2[n2] = (array5[n2 / 6] >>> 5 * (5 - n2 % 6) & 0x1F);
                    ++n2;
                }
                if (n2 < array2.length) {
                    continue;
                }
                break;
            }
            array5 = new int[] { 942680096, 403703808, 959523105, 420546817, 976366114, 437389826, 993209123, 1043738150, 504761862, 1026895141, 487918853, 1010052132, 471075844, 454232835 };
            n2 = 0;
            if (i != 0) {
                if (i != 0) {
                    continue;
                }
            }
            break;
        }
        while (true) {
            if (n2 >= f.a.length) {
                array5 = new int[] { 219154967, 262683, 235213833, 370281219, 419892998, 437455873, 674438692, 775298343, 841752623, 724575799, 557067561, 824384543 };
                n2 = 0;
                if (i == 0) {
                    break;
                }
            }
            else {
                f.a[n2] = (array5[n2 / 4] >>> 8 * (3 - n2 % 4) & 0xFF);
            }
            ++n2;
        }
        int[] array6;
        while (true) {
            while (true) {
                Label_0547: {
                    if (i == 0) {
                        break Label_0547;
                    }
                    final int[] b = f.b;
                    b[n2] = (array5[n2 / 4] >>> 8 * (3 - n2 % 4) & 0xFF);
                    ++n2;
                }
                if (n2 < f.b.length) {
                    continue;
                }
                break;
            }
            array5 = new int[32];
            int[] b;
            array6 = (b = new int[8]);
            array6[0] = 252056340;
            array6[1] = 470489872;
            array6[2] = 923161;
            array6[3] = 68230665;
            array6[4] = 17241869;
            array6[5] = 521798152;
            array6[6] = 302783749;
            array6[7] = 352977688;
            if (i != 0) {
                continue;
            }
            break;
        }
        int[] array7 = array6;
        int n3 = 0;
        int[] array9;
        while (true) {
            while (true) {
                Label_0647: {
                    if (i == 0) {
                        break Label_0647;
                    }
                    final int[] array8 = array5;
                    array8[n3] = (array7[n3 / 4] >>> 8 * (3 - n3 % 4) & 0xFF);
                    ++n3;
                }
                if (n3 < array5.length) {
                    continue;
                }
                break;
            }
            array7 = new int[64];
            int[] array8;
            array9 = (array8 = new int[16]);
            array9[0] = 654782223;
            array9[1] = 924270367;
            array9[2] = 637939214;
            array9[3] = 907427358;
            array9[4] = 621096205;
            array9[5] = 890584349;
            array9[6] = 604253196;
            array9[7] = 873741340;
            array9[8] = 587410187;
            array9[9] = 856898331;
            array9[10] = 570567178;
            array9[11] = 840055322;
            array9[12] = 553724169;
            array9[13] = 823212313;
            array9[14] = 536881160;
            array9[15] = 806369304;
            if (i != 0) {
                continue;
            }
            break;
        }
        final int[] array10 = array9;
        int n4 = 0;
        while (true) {
            Label_0796: {
                if (i == 0) {
                    break Label_0796;
                }
                array7[n4] = (array10[n4 / 4] >>> 8 * (3 - n4 % 4) & 0xFF);
                ++n4;
            }
            if (n4 < array7.length) {
                continue;
            }
            break;
        }
        final int[][][] array11 = new int[8][16][4];
        final int[] array12 = { -274256819, 64875217, 1088047383, -49040286, 1091420594, -42418417, 510013003, -928021583, -660714968, 1946887357, -414638156, -1966030114, 518201316, 1200996984, -1953450543, 554073223, 643856886, -281655478, -628317495, 1131026708, -80305649, 575634579, 1694226492, -1617828534, -1275480437, -664741609, 758611070, 344797096, -2074449711, 514004388, -1324514334, 1920522877, 957448250, -1406904596, -171968608, 1531538335, -1479385655, 1619460405, -938372874, -1225458084, 1657287571, -1051525802, -1675377558, 937754889, -847907714, -1158893877, 1993235085, -331638800, 1555816021, -1765724128, 961896719, -1598249245, -1874065504, 1505401854, -1549648557, 89604133, 86304108, 1006535561, 1390939029, 1847744566, 2056231703, -2061932446, 259319336, -640795189 };
        int n5 = 0;
        int n6 = 0;
        int n8 = 0;
        while (true) {
            Label_1278: {
                if (i == 0) {
                    break Label_1278;
                }
                int n7 = n8;
                int j = 0;
                int n9 = 0;
                int n10;
                int n11;
                Label_1228_Outer:Label_1250_Outer:
                do {
                    n10 = array12[n5];
                    n11 = 7;
                    while (true) {
                        while (true) {
                            Label_1253: {
                                if (i == 0) {
                                    break Label_1253;
                                }
                                array11[n11][n6][n7] = (n10 & 0xF);
                                --n11;
                                n10 = j >>> n9;
                            }
                            if (n11 >= 0) {
                                continue Label_1250_Outer;
                            }
                            break;
                        }
                        ++n7;
                        ++n5;
                        j = n7;
                        n9 = 4;
                        if (i == 0) {
                            continue Label_1228_Outer;
                        }
                        continue;
                    }
                } while (j < n9);
                ++n6;
            }
            if (n6 < 16) {
                continue;
            }
            n8 = 1521117865;
            if (i != 0) {
                continue;
            }
            break;
        }
        int n12 = n8;
        int n13 = f.g.length - 1;
    Label_1339_Outer:
        while (true) {
            while (true) {
                Label_1326: {
                    if (i == 0) {
                        break Label_1326;
                    }
                    f.g[n13] = (n12 & 0x3);
                    --n13;
                    final int n14;
                    n12 = n14;
                }
                if (n13 >= 0) {
                    continue;
                }
                break;
            }
            final int n14 = 0;
            if (i == 0) {
                int n15 = n14;
                int n19 = 0;
            Label_1339:
                while (true) {
                    while (true) {
                        int n16 = 0;
                    Label_1342:
                        while (true) {
                            f.f[n15][n16] = array11[n15][n16 >> 1 & 0xF][(n16 >> 4 & 0x2) | (n16 & 0x1)] << (n15 << 2);
                            int n17 = 0;
                            do {
                                int n18 = n19;
                                int n21 = 0;
                                int n22 = 0;
                            Block_23:
                                while (true) {
                                    n17 |= (f.f[n15][n16] >> array5[n18] & 0x1) << n18;
                                    ++n18;
                                    int k = 0;
                                    int n20 = 0;
                                    while (k >= n20) {
                                        f.f[n15][n16] = n17;
                                        k = ++n16;
                                        n20 = 64;
                                        if (i == 0) {
                                            if (k < n20) {
                                                continue Label_1342;
                                            }
                                            n21 = ++n15;
                                            n22 = 8;
                                            if (i == 0) {
                                                break Block_23;
                                            }
                                            continue Label_1339_Outer;
                                        }
                                    }
                                }
                                if (n21 < n22) {
                                    continue Label_1339;
                                }
                                n19 = 0;
                            } while (i != 0);
                            break Label_1339;
                        }
                    }
                    break;
                }
                int n23 = n19;
                int n27 = 0;
            Label_1482:
                while (true) {
                    while (true) {
                        int n24 = 0;
                    Label_1485:
                        while (true) {
                            f.e[n23][n24][0] = 0;
                            f.e[n23][n24][1] = 0;
                            final int n25 = n24 << (n23 << 3);
                            do {
                                int n26 = n27;
                                int n31 = 0;
                                int n32 = 0;
                            Block_29:
                                while (true) {
                                    final int n28 = n25 >> array2[n26] & 0x1;
                                    final int[] array13 = f.e[n23][n24];
                                    final int n29 = n26 >> 5;
                                    array13[n29] |= n28 << (n26 & 0x1F);
                                    ++n26;
                                    int l = 0;
                                    int n30 = 0;
                                    while (l >= n30) {
                                        l = ++n24;
                                        n30 = 256;
                                        if (i == 0) {
                                            if (l < n30) {
                                                continue Label_1485;
                                            }
                                            n31 = ++n23;
                                            n32 = 4;
                                            if (i == 0) {
                                                break Block_29;
                                            }
                                            continue Label_1339_Outer;
                                        }
                                    }
                                }
                                if (n31 < n32) {
                                    continue Label_1482;
                                }
                                n27 = 0;
                            } while (i != 0);
                            break Label_1482;
                        }
                    }
                    break;
                }
                int n33 = n27;
            Block_35:
                while (true) {
                    int n34 = 0;
                    int n35 = 0;
                    do {
                        int n36 = 0;
                    Label_1608:
                        while (true) {
                            while (true) {
                                f.d[n33][n36][0] = 0;
                                f.d[n33][n36][1] = 0;
                                f.c[n33][n36][0] = 0;
                                f.c[n33][n36][1] = 0;
                                final int[] array14 = new int[2];
                                array14[n33 >> 2] = n36 << ((n33 & 0x3) << 3);
                                int n37 = 0;
                                while (true) {
                                    final int n38 = array14[array[n37] >> 5] >> (array[n37] & 0x1F) & 0x1;
                                    final int[] array15 = f.d[n33][n36];
                                    final int n39 = n37 >> 5;
                                    array15[n39] |= n38 << (n37 & 0x1F);
                                    final int n40 = array14[array7[n37] >> 5] >> (array7[n37] & 0x1F) & 0x1;
                                    final int[] array16 = f.c[n33][n36];
                                    final int n41 = n37 >> 5;
                                    array16[n41] |= n40 << (n37 & 0x1F);
                                    ++n37;
                                    int n42 = 0;
                                    int n43 = 0;
                                    while (n42 >= n43) {
                                        n42 = ++n36;
                                        n43 = 256;
                                        if (i == 0) {
                                            if (n42 < n43) {
                                                continue Label_1608;
                                            }
                                            n34 = ++n33;
                                            n35 = 8;
                                            if (i == 0) {
                                                continue Block_35;
                                            }
                                            continue Label_1339_Outer;
                                        }
                                    }
                                }
                            }
                            break;
                        }
                    } while (n34 < n35);
                    break;
                }
                return;
            }
            continue;
        }
    }
    
    protected long a(final int n, long n2) {
        final int a = q.a;
        int n3 = 0;
        while (true) {
            Label_0037: {
                if (a == 0) {
                    break Label_0037;
                }
                final long n4;
                n2 = ((n2 & 0x7FFFFFF7FFFFFFL) << 1 | n4 >> 27);
                ++n3;
            }
            if (n3 < n) {
                continue;
            }
            final long n4 = n2;
            if (a == 0) {
                return n4;
            }
            continue;
        }
    }
    
    void c(final long n) {
        long n2 = this.b(n);
        int n3 = 0;
        do {
            n2 = this.a(f.g[n3], n2);
            final long a = this.a(n2);
            this.h[n3][0] = (int)(a & -1L);
            this.h[n3][1] = (int)(a >>> 32);
        } while (++n3 < 16);
    }
    
    void a(final byte[] array) {
        this.a(array, 0, array.length);
    }
}
