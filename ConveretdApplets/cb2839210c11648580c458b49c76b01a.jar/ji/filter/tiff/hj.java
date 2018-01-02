// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.tiff;

public class hj
{
    private static byte[] a;
    private static int[] b;
    private static int[] c;
    private static int[] d;
    private static int[] e;
    private static int[] f;
    private static int[] g;
    private static int[] h;
    private static int[][] i;
    private static int[][] j;
    private static int[][] k;
    private static int[][] l;
    private static int[][] m;
    private int n;
    private int o;
    
    private int a(final byte[] array, final int n, int n2, final int n3) {
        if (array == null) {
            return n3;
        }
        int i = n + (n2 >>> 3);
        int n4 = n + (n3 >>> 3);
        if (n4 == array.length) {
            --n4;
        }
        if (i == array.length) {
            --i;
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
                n2 = (i - n) * 8 + hj.a[n6];
                return (n2 < n3) ? n2 : n3;
            }
            while (i < n4) {
                if ((n6 = (array[++i] & 0xFF)) != 0) {
                    n2 = (i - n) * 8 + hj.a[n6];
                    return (n2 < n3) ? n2 : n3;
                }
            }
        }
        n2 = (i - n) * 8 + hj.a[n6];
        return (n2 < n3) ? n2 : n3;
    }
    
    private void a() {
        this.o = 0;
        this.n = 0;
    }
    
    private int b(final byte[] array, final int n, int n2, final int n3) {
        int n4 = n;
        int i = n2 >>> 6;
        n2 &= 0x3F;
        if (i != 0) {
            while (i > 40) {
                final int n5 = hj.j[n3][40];
                this.n |= (n5 & 0xFFF80000) >>> this.o;
                this.o += (n5 & 0xFFFF);
                while (this.o > 7) {
                    array[n4++] = (byte)(this.n >>> 24);
                    this.n <<= 8;
                    this.o -= 8;
                }
                i -= 40;
            }
            final int n6 = hj.j[n3][i];
            this.n |= (n6 & 0xFFF80000) >>> this.o;
            this.o += (n6 & 0xFFFF);
            while (this.o > 7) {
                array[n4++] = (byte)(this.n >>> 24);
                this.n <<= 8;
                this.o -= 8;
            }
        }
        final int n7 = hj.i[n3][n2];
        this.n |= (n7 & 0xFFF80000) >>> this.o;
        this.o += (n7 & 0xFFFF);
        while (this.o > 7) {
            array[n4++] = (byte)(this.n >>> 24);
            this.n <<= 8;
            this.o -= 8;
        }
        return n4 - n;
    }
    
    private int a(final byte[] array, final int n, final int[][] array2, final int n2) {
        int n3 = n;
        final int n4 = array2[0][n2];
        this.n |= (n4 & 0xFFF80000) >>> this.o;
        this.o += (n4 & 0xFFFF);
        while (this.o > 7) {
            array[n3++] = (byte)(this.n >>> 24);
            this.n <<= 8;
            this.o -= 8;
        }
        return n3 - n;
    }
    
    private int a(final byte[] array, final int n) {
        int n2 = n;
        this.n |= 1048832 >>> this.o;
        this.o += 24;
        while (this.o > 0) {
            array[n2++] = (byte)(this.n >>> 24);
            this.n <<= 8;
            this.o -= 8;
        }
        return n2 - n;
    }
    
    public byte[] a(final byte[] array, final int n, int n2) {
        final byte[] array2 = new byte[n2 * ((int)Math.ceil(((n + 1) / 2 * 9 + 2) / 8.0) + 2) + 12];
        final int n3 = (n + 7) / 8;
        final int n4 = 0;
        byte[] array3 = null;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        this.a();
        while (n2-- != 0) {
            int n8 = n4;
            final int n9 = n8 + n;
            int a = (((array[n6 + (n8 >>> 3)] & 0xFF) >>> 7 - (n8 & 0x7) & 0x1) != 0x0) ? n8 : this.a(array, n6, n8, n9);
            int n10 = (((array3 == null) ? 0 : ((array3[n5 + (n8 >>> 3)] & 0xFF) >>> 7 - (n8 & 0x7) & 0x1)) != 0) ? n8 : this.a(array3, n5, n8, n9);
            int n11 = 0;
            while (true) {
                final int a2 = this.a(array3, n5, n10, n9);
                if (a2 < a) {
                    n7 += this.a(array2, n7, hj.k, 0);
                    n8 = a2;
                }
                else {
                    final int n12 = n10 - a + 3;
                    if (n12 <= 6 && n12 >= 0) {
                        n7 += this.a(array2, n7, hj.l, n12);
                        n8 = a;
                    }
                    else {
                        final int a3 = this.a(array, n6, a, n9);
                        final int n13 = n7 + this.a(array2, n7, hj.m, 0);
                        final int n14 = n13 + this.b(array2, n13, a - n8, n11);
                        n7 = n14 + this.b(array2, n14, a3 - a, n11 ^ 0x1);
                        n8 = a3;
                    }
                }
                if (n8 >= n9) {
                    break;
                }
                n11 = ((array[n6 + (n8 >>> 3)] & 0xFF) >>> 7 - (n8 & 0x7) & 0x1);
                a = this.a(array, n6, n8, n9);
                n10 = this.a(array3, n5, n8, n9);
                if (((array3 == null) ? 0 : ((array3[n5 + (n10 >>> 3)] & 0xFF) >>> 7 - (n10 & 0x7) & 0x1)) != n11) {
                    continue;
                }
                n10 = this.a(array3, n5, n10, n9);
            }
            array3 = array;
            n5 = n6;
            n6 += n3;
        }
        final int n15 = n7 + this.a(array2, n7);
        final byte[] array4 = new byte[n15];
        System.arraycopy(array2, 0, array4, 0, n15);
        return array4;
    }
    
    static {
        hj.a = new byte[] { 8, 7, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        hj.b = new int[] { 230686730, 1073741827, -1073741822, -2147483646, 1610612739, 805306372, 536870916, 402653189, 335544326, 268435462, 134217735, 167772167, 234881031, 67108872, 117440520, 201326601, 96469002, 100663306, 33554442, 216006667, 218103819, 226492427, 115343371, 83886091, 48234507, 50331659, 211812364, 212860940, 213909516, 214958092, 109051916, 110100492, 111149068, 112197644, 220200972, 221249548, 222298124, 223346700, 224395276, 225443852, 113246220, 114294796, 228589580, 229638156, 88080396, 89128972, 90177548, 91226124, 104857612, 105906188, 85983244, 87031820, 37748748, 57671692, 58720268, 40894476, 41943052, 92274700, 93323276, 45088780, 46137356, 94371852, 106954764, 108003340 };
        hj.c = new int[] { 889192456, 469762054, 1879048196, -2147483644, -1342177276, -1073741820, -536870908, -268435452, -1744830459, -1610612731, 939524101, 1073741829, 536870918, 201326598, -805306362, -738197498, -1476395002, -1409286138, 1308622855, 402653191, 268435463, 771751943, 100663303, 134217735, 1342177287, 1442840583, 637534215, 1207959559, 805306375, 33554440, 50331656, 436207624, 452984840, 301989896, 318767112, 335544328, 352321544, 369098760, 385875976, 671088648, 687865864, 704643080, 721420296, 738197512, 754974728, 67108872, 83886088, 167772168, 184549384, 1375731720, 1392508936, 1409286152, 1426063368, 603979784, 620757000, 1476395016, 1493172232, 1509949448, 1526726664, 1241513992, 1258291208, 838860808, 855638024, 872415240 };
        hj.d = new int[] { 0, 62914570, 209715212, 210763788, 95420428, 53477388, 54525964, 55574540, 56623117, 57147405, 38797325, 39321613, 39845901, 40370189, 59768845, 60293133, 60817421, 61341709, 61865997, 62390285, 42991629, 43515917, 44040205, 44564493, 47185933, 47710221, 52428813, 52953101, 16777227, 25165835, 27262987, 18874380, 19922956, 20971532, 22020108, 23068684, 24117260, 29360140, 30408716, 31457292, 32505868, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        hj.e = new int[] { 0, -671088635, -1879048187, 1543503878, 1845493767, 905969672, 922746888, 1677721608, 1694498824, 1744830472, 1728053256, 1711276041, 1719664649, 1761607689, 1769996297, 1778384905, 1786773513, 1795162121, 1803550729, 1811939337, 1820327945, 1828716553, 1837105161, 1275068425, 1283457033, 1291845641, 1610612742, 1300234249, 16777227, 25165835, 27262987, 18874380, 19922956, 20971532, 22020108, 23068684, 24117260, 29360140, 30408716, 31457292, 32505868, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        hj.f = new int[] { 268435460 };
        hj.g = new int[] { 100663303, 201326598, 1610612739, -2147483647, 1073741827, 134217734, 67108871 };
        hj.h = new int[] { 536870915 };
        hj.i = new int[][] { hj.c, hj.b };
        hj.j = new int[][] { hj.e, hj.d };
        hj.k = new int[][] { hj.f, hj.f };
        hj.l = new int[][] { hj.g, hj.g };
        hj.m = new int[][] { hj.h, hj.h };
    }
}
