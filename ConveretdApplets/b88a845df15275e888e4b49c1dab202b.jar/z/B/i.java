// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

class i
{
    private static final int Q = 0;
    private static final int F = 1;
    private static byte[] R;
    private static int[] G;
    private static int[] C;
    private static int[] I;
    private static int[] D;
    private static int[] A;
    private static int[] N;
    private static int[] P;
    private static int[][] J;
    private static int[][] H;
    private static int[][] B;
    private static int[][] O;
    private static int[][] E;
    private boolean K;
    private int M;
    private int L;
    
    i(final boolean k) {
        this.K = k;
    }
    
    private int B(final byte[] array, final int n, int n2, final int n3) {
        if (array == null) {
            return n3;
        }
        int i = n + (n2 >>> 3);
        int n4 = n + (n3 >>> 3);
        if (n4 == array.length) {
            --n4;
        }
        final int n5 = n2 & 0x7;
        int n6;
        if ((array[i] & 128 >>> n5) != 0x0) {
            n6 = (~array[i] & 255 >>> n5);
            while (i < n4) {
                if (n6 != 0) {
                    break;
                }
                n6 = (~array[++i] & 0xFF);
            }
        }
        else {
            if ((n6 = (array[i] & 255 >>> n5)) != 0) {
                n2 = (i - n) * 8 + z.B.i.R[n6];
                return (n2 < n3) ? n2 : n3;
            }
            while (i < n4) {
                if ((n6 = (array[++i] & 0xFF)) != 0) {
                    n2 = (i - n) * 8 + z.B.i.R[n6];
                    return (n2 < n3) ? n2 : n3;
                }
            }
        }
        n2 = (i - n) * 8 + z.B.i.R[n6];
        return (n2 < n3) ? n2 : n3;
    }
    
    private void A() {
        this.L = 0;
        this.M = 0;
    }
    
    private int A(final byte[] array, final int n, int n2, final int n3) {
        int n4 = n;
        int i = n2 >>> 6;
        n2 &= 0x3F;
        if (i != 0) {
            while (i > 40) {
                final int n5 = z.B.i.H[n3][40];
                this.M |= (n5 & 0xFFF80000) >>> this.L;
                this.L += (n5 & 0xFFFF);
                while (this.L > 7) {
                    array[n4++] = (byte)(this.M >>> 24);
                    this.M <<= 8;
                    this.L -= 8;
                }
                i -= 40;
            }
            final int n6 = z.B.i.H[n3][i];
            this.M |= (n6 & 0xFFF80000) >>> this.L;
            this.L += (n6 & 0xFFFF);
            while (this.L > 7) {
                array[n4++] = (byte)(this.M >>> 24);
                this.M <<= 8;
                this.L -= 8;
            }
        }
        final int n7 = z.B.i.J[n3][n2];
        this.M |= (n7 & 0xFFF80000) >>> this.L;
        this.L += (n7 & 0xFFFF);
        while (this.L > 7) {
            array[n4++] = (byte)(this.M >>> 24);
            this.M <<= 8;
            this.L -= 8;
        }
        return n4 - n;
    }
    
    private int A(final byte[] array, final int n, final int[][] array2, final int n2) {
        int n3 = n;
        final int n4 = array2[0][n2];
        this.M |= (n4 & 0xFFF80000) >>> this.L;
        this.L += (n4 & 0xFFFF);
        while (this.L > 7) {
            array[n3++] = (byte)(this.M >>> 24);
            this.M <<= 8;
            this.L -= 8;
        }
        return n3 - n;
    }
    
    private int A(final boolean b, final boolean b2, final boolean b3, final byte[] array, final int n) {
        int n2 = n;
        if (b2) {
            this.L += ((this.L <= 4) ? (4 - this.L) : (12 - this.L));
        }
        if (b) {
            this.M |= 1048576 >>> this.L;
            this.L += 12;
        }
        else {
            this.M |= (b3 ? 1572864 : 1048576) >>> this.L;
            this.L += 13;
        }
        while (this.L > 7) {
            array[n2++] = (byte)(this.M >>> 24);
            this.M <<= 8;
            this.L -= 8;
        }
        return n2 - n;
    }
    
    private int A(final byte[] array, final int n) {
        int n2 = n;
        this.M |= 1048832 >>> this.L;
        this.L += 24;
        while (this.L > 0) {
            array[n2++] = (byte)(this.M >>> 24);
            this.M <<= 8;
            this.L -= 8;
        }
        return n2 - n;
    }
    
    private int A(final byte[] array, final int n, final int n2, final int n3, final byte[] array2, final int n4) {
        int i = n2;
        final int n5 = i + n3;
        int n6 = n4;
        final int n7 = (array[n + (i >>> 3)] & 0xFF) >>> 7 - (i & 0x7) & 0x1;
        int n8 = 1;
        if (n7 != 0) {
            n6 += this.A(array2, n6, 0, 0);
        }
        else {
            n8 = 0;
        }
        while (i < n5) {
            final int n9 = this.B(array, n, i, n5) - i;
            n6 += this.A(array2, n6, n9, n8);
            i += n9;
            n8 ^= 0x1;
        }
        return n6 - n4;
    }
    
    synchronized int A(final byte[] array, final int n, final int n2, final int n3, final byte[] array2) {
        this.A();
        int a = this.A(array, n, n2, n3, array2, 0);
        while (this.L > 0) {
            array2[a++] = (byte)(this.M >>> 24);
            this.M <<= 8;
            this.L -= 8;
        }
        if (this.K) {
            final byte[] m = w.M;
            for (int i = 0; i < a; ++i) {
                array2[i] = m[array2[i] & 0xFF];
            }
        }
        return a;
    }
    
    synchronized int A(final boolean b, final boolean b2, final byte[] array, final int n, final int n2, final int n3, final int n4, final byte[] array2) {
        int n5 = 0;
        int n6 = 0;
        this.A();
        final int n7 = 2;
        for (int i = 0; i < n4; ++i) {
            if (b || i % n7 == 0) {
                final int n8 = n6 + this.A(b, b2, true, array2, n6);
                n6 = n8 + this.A(array, n5, n2, n3, array2, n8);
            }
            else {
                n6 += this.A(b, b2, false, array2, n6);
                final int n9 = n5 - n;
                int n10 = n2;
                final int n11 = n10 + n3;
                int b3 = (((array[n5 + (n10 >>> 3)] & 0xFF) >>> 7 - (n10 & 0x7) & 0x1) != 0x0) ? n10 : this.B(array, n5, n10, n11);
                int n12 = (((array[n9 + (n10 >>> 3)] & 0xFF) >>> 7 - (n10 & 0x7) & 0x1) != 0x0) ? n10 : this.B(array, n9, n10, n11);
                int n13 = 0;
                while (true) {
                    final int b4 = this.B(array, n9, n12, n11);
                    if (b4 < b3) {
                        n6 += this.A(array2, n6, i.B, 0);
                        n10 = b4;
                    }
                    else {
                        final int n14 = n12 - b3 + 3;
                        if (n14 <= 6 && n14 >= 0) {
                            n6 += this.A(array2, n6, i.O, n14);
                            n10 = b3;
                        }
                        else {
                            final int b5 = this.B(array, n5, b3, n11);
                            final int n15 = n6 + this.A(array2, n6, i.E, 0);
                            final int n16 = n15 + this.A(array2, n15, b3 - n10, n13);
                            n6 = n16 + this.A(array2, n16, b5 - b3, n13 ^ 0x1);
                            n10 = b5;
                        }
                    }
                    if (n10 >= n11) {
                        break;
                    }
                    n13 = ((array[n5 + (n10 >>> 3)] & 0xFF) >>> 7 - (n10 & 0x7) & 0x1);
                    b3 = this.B(array, n5, n10, n11);
                    n12 = this.B(array, n9, n10, n11);
                    if (((array[n9 + (n12 >>> 3)] & 0xFF) >>> 7 - (n12 & 0x7) & 0x1) != n13) {
                        continue;
                    }
                    n12 = this.B(array, n9, n12, n11);
                }
            }
            n5 += n;
        }
        for (int j = 0; j < 6; ++j) {
            n6 += this.A(b, b2, true, array2, n6);
        }
        while (this.L > 0) {
            array2[n6++] = (byte)(this.M >>> 24);
            this.M <<= 8;
            this.L -= 8;
        }
        if (this.K) {
            for (int k = 0; k < n6; ++k) {
                array2[k] = w.M[array2[k] & 0xFF];
            }
        }
        return n6;
    }
    
    public synchronized int A(final byte[] array, final int n, final int n2, final int n3, int n4, final byte[] array2) {
        byte[] array3 = null;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        this.A();
        while (n4-- != 0) {
            int n8 = n2;
            final int n9 = n8 + n3;
            int b = (((array[n6 + (n8 >>> 3)] & 0xFF) >>> 7 - (n8 & 0x7) & 0x1) != 0x0) ? n8 : this.B(array, n6, n8, n9);
            int n10 = (((array3 == null) ? 0 : ((array3[n5 + (n8 >>> 3)] & 0xFF) >>> 7 - (n8 & 0x7) & 0x1)) != 0) ? n8 : this.B(array3, n5, n8, n9);
            int n11 = 0;
            while (true) {
                final int b2 = this.B(array3, n5, n10, n9);
                if (b2 < b) {
                    n7 += this.A(array2, n7, i.B, 0);
                    n8 = b2;
                }
                else {
                    final int n12 = n10 - b + 3;
                    if (n12 <= 6 && n12 >= 0) {
                        n7 += this.A(array2, n7, i.O, n12);
                        n8 = b;
                    }
                    else {
                        final int b3 = this.B(array, n6, b, n9);
                        final int n13 = n7 + this.A(array2, n7, i.E, 0);
                        final int n14 = n13 + this.A(array2, n13, b - n8, n11);
                        n7 = n14 + this.A(array2, n14, b3 - b, n11 ^ 0x1);
                        n8 = b3;
                    }
                }
                if (n8 >= n9) {
                    break;
                }
                n11 = ((array[n6 + (n8 >>> 3)] & 0xFF) >>> 7 - (n8 & 0x7) & 0x1);
                b = this.B(array, n6, n8, n9);
                n10 = this.B(array3, n5, n8, n9);
                if (((array3 == null) ? 0 : ((array3[n5 + (n10 >>> 3)] & 0xFF) >>> 7 - (n10 & 0x7) & 0x1)) != n11) {
                    continue;
                }
                n10 = this.B(array3, n5, n10, n9);
            }
            array3 = array;
            n5 = n6;
            n6 += n;
        }
        final int n15 = n7 + this.A(array2, n7);
        if (this.K) {
            for (int i = 0; i < n15; ++i) {
                array2[i] = w.M[array2[i] & 0xFF];
            }
        }
        return n15;
    }
    
    static {
        i.R = new byte[] { 8, 7, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        i.G = new int[] { 230686730, 1073741827, -1073741822, -2147483646, 1610612739, 805306372, 536870916, 402653189, 335544326, 268435462, 134217735, 167772167, 234881031, 67108872, 117440520, 201326601, 96469002, 100663306, 33554442, 216006667, 218103819, 226492427, 115343371, 83886091, 48234507, 50331659, 211812364, 212860940, 213909516, 214958092, 109051916, 110100492, 111149068, 112197644, 220200972, 221249548, 222298124, 223346700, 224395276, 225443852, 113246220, 114294796, 228589580, 229638156, 88080396, 89128972, 90177548, 91226124, 104857612, 105906188, 85983244, 87031820, 37748748, 57671692, 58720268, 40894476, 41943052, 92274700, 93323276, 45088780, 46137356, 94371852, 106954764, 108003340 };
        i.C = new int[] { 889192456, 469762054, 1879048196, -2147483644, -1342177276, -1073741820, -536870908, -268435452, -1744830459, -1610612731, 939524101, 1073741829, 536870918, 201326598, -805306362, -738197498, -1476395002, -1409286138, 1308622855, 402653191, 268435463, 771751943, 100663303, 134217735, 1342177287, 1442840583, 637534215, 1207959559, 805306375, 33554440, 50331656, 436207624, 452984840, 301989896, 318767112, 335544328, 352321544, 369098760, 385875976, 671088648, 687865864, 704643080, 721420296, 738197512, 754974728, 67108872, 83886088, 167772168, 184549384, 1375731720, 1392508936, 1409286152, 1426063368, 603979784, 620757000, 1476395016, 1493172232, 1509949448, 1526726664, 1241513992, 1258291208, 838860808, 855638024, 872415240 };
        i.I = new int[] { 0, 62914570, 209715212, 210763788, 95420428, 53477388, 54525964, 55574540, 56623117, 57147405, 38797325, 39321613, 39845901, 40370189, 59768845, 60293133, 60817421, 61341709, 61865997, 62390285, 42991629, 43515917, 44040205, 44564493, 47185933, 47710221, 52428813, 52953101, 16777227, 25165835, 27262987, 18874380, 19922956, 20971532, 22020108, 23068684, 24117260, 29360140, 30408716, 31457292, 32505868, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        i.D = new int[] { 0, -671088635, -1879048187, 1543503878, 1845493767, 905969672, 922746888, 1677721608, 1694498824, 1744830472, 1728053256, 1711276041, 1719664649, 1761607689, 1769996297, 1778384905, 1786773513, 1795162121, 1803550729, 1811939337, 1820327945, 1828716553, 1837105161, 1275068425, 1283457033, 1291845641, 1610612742, 1300234249, 16777227, 25165835, 27262987, 18874380, 19922956, 20971532, 22020108, 23068684, 24117260, 29360140, 30408716, 31457292, 32505868, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        i.A = new int[] { 268435460 };
        i.N = new int[] { 100663303, 201326598, 1610612739, -2147483647, 1073741827, 134217734, 67108871 };
        i.P = new int[] { 536870915 };
        i.J = new int[][] { i.C, i.G };
        i.H = new int[][] { i.D, i.I };
        i.B = new int[][] { i.A, i.A };
        i.O = new int[][] { i.N, i.N };
        i.E = new int[][] { i.P, i.P };
    }
}
