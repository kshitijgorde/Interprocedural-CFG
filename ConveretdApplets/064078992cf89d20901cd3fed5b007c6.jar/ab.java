// 
// Decompiled by Procyon v0.5.30
// 

public class ab
{
    aa D;
    int bj;
    boolean al;
    l Z;
    private static final int ay = 13;
    private static final int a4 = 2;
    private static final int aQ = 1;
    private static final int o = 2446;
    private static final int new = 3196;
    private static final int z = 4433;
    private static final int f = 6270;
    private static final int a3 = 7373;
    private static final int aO = 9633;
    private static final int b = 12299;
    private static final int a1 = 15137;
    private static final int y = 16069;
    private static final int l = 16819;
    private static final int a0 = 20995;
    private static final int T = 25172;
    private static final int bh = 4096;
    private static final int P = 10;
    private static final int aM = 8;
    private static final int be = 4;
    private static final int case = 4;
    private static final int n = 4;
    private static final int t = 12288;
    private static final int g = 100;
    private static final int bi = 32696;
    private static final int do = 32696;
    private static final int ak = -200;
    private static final int az = -201;
    private static final int i = -202;
    private static final int F = -203;
    private static final int ad = -204;
    private static final int aJ = -205;
    private static final int aT = -206;
    private static final int aa = -207;
    private static final int aE = -208;
    private static final int aj = -209;
    private static final int else = -210;
    private static final int U = -211;
    private static final int aC = -212;
    private static final int aN = -213;
    private static final int byte = -215;
    private static final int m = -216;
    private static final int aA = -217;
    private static final int try = -218;
    private static final int if = -219;
    private static final int aD = -221;
    private static final int ah = -222;
    private static final int aP = -223;
    private static final int C = -225;
    private static final int void = -226;
    private static final int h = -227;
    private static final int s = -228;
    private static final int aq = -229;
    private static final int q = -232;
    private static final int H = -233;
    public static final int bk = -234;
    private static final int v = 0;
    private static final int bc = 1;
    private static final int a7 = 2;
    private static final int bb = 3;
    private static final int a6 = 4;
    private static final int u = -1;
    private static final int V = 1;
    private static final int a5 = 0;
    private static final int a2 = 16;
    private static final int[] as;
    private static final int[] long;
    private static final int[] A;
    int char;
    int x;
    aa K;
    int r;
    byte[][] aY;
    byte[][] aX;
    short[][] B;
    int au;
    int aV;
    int[] w;
    int[] M;
    int[] R;
    int[] G;
    int[] aL;
    int[] c;
    int j;
    int[] ax;
    int[] aW;
    int[] E;
    int int;
    int a;
    int a9;
    int k;
    int bg;
    int for;
    int Y;
    int ac;
    int bd;
    int X;
    int[] bf;
    int ab;
    int L;
    int S;
    int goto;
    int bo;
    byte[][] aU;
    c[] af;
    o[] bm;
    o[] ap;
    int e;
    int[] Q;
    int O;
    int ao;
    int p;
    boolean[] I;
    byte[] at;
    int aR;
    long bl;
    int aK;
    int an;
    int J;
    int N;
    int aw;
    int ba;
    int[] ag;
    c[] aH;
    c[] ar;
    short[] aS;
    int[] aB;
    byte[] W;
    int[] aZ;
    int[] ai;
    int d;
    long[] a8;
    long[] ae;
    byte[] aI;
    byte[] aG;
    short[] aF;
    int am;
    boolean bn;
    int av;
    
    static {
        as = new int[] { 0, 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384 };
        long = new int[] { 0, -1, -3, -7, -15, -31, -63, -127, -255, -511, -1023, -2047, -4095, -8191, -16383, -32767 };
        A = new int[] { 0, 1, 8, 16, 9, 2, 3, 10, 17, 24, 32, 25, 18, 11, 4, 5, 12, 19, 26, 33, 40, 48, 41, 34, 27, 20, 13, 6, 7, 14, 21, 28, 35, 42, 49, 56, 57, 50, 43, 36, 29, 22, 15, 23, 30, 37, 44, 51, 58, 59, 52, 45, 38, 31, 39, 46, 53, 60, 61, 54, 47, 55, 62, 63 };
    }
    
    public ab() {
        this.D = null;
        this.bj = -1;
        this.al = false;
        this.aY = new byte[8][];
        this.aX = new byte[8][];
        this.B = new short[4][];
        this.w = new int[4];
        this.M = new int[4];
        this.R = new int[4];
        this.G = new int[4];
        this.aL = new int[4];
        this.c = new int[4];
        this.ax = new int[4];
        this.aW = new int[4];
        this.E = new int[4];
        this.bf = new int[10];
        this.aU = new byte[100][];
        this.af = new c[8];
        this.bm = new o[4];
        this.ap = new o[4];
        this.Q = new int[4];
        this.I = new boolean[1];
        this.at = new byte[4480];
        this.ag = new int[4];
        this.aH = new c[10];
        this.ar = new c[10];
        this.aB = new int[12288];
        this.aZ = new int[256];
        this.ai = new int[256];
        this.a8 = new long[256];
        this.ae = new long[256];
        this.aF = new short[64];
    }
    
    public boolean a(final aa d, final l z) {
        this.Z = z;
        this.do(this.D = d);
        if (this.i() != 0) {
            return false;
        }
        if (this.v() == 0) {
            this.D.case = new an();
            return true;
        }
        if (this.i() == -234) {
            System.out.println("Error: progressive Jpeg not supported.");
            return false;
        }
        return false;
    }
    
    public boolean p() {
        final int o = this.o();
        final int a = this.a();
        if (o * 6 == a) {
            this.al = true;
        }
        boolean b = true;
        int long1 = o;
        int e = a;
        int n = 9;
        System.gc();
        while (b && n > 0) {
            b = false;
            try {
                this.D.case.c = new int[long1 * e];
            }
            catch (OutOfMemoryError outOfMemoryError) {
                if (!this.D.g) {
                    System.out.println("Not enough memory to open " + this.D.l);
                    this.D.case.c = new int[1];
                    this.D.case.long = 0;
                    this.D.case.e = 0;
                    return false;
                }
                this.D.case.c = null;
                System.gc();
                b = true;
                long1 = o * n / 10;
                if (this.al) {
                    e = long1 * 6;
                }
                else {
                    e = a * n / 10;
                }
                --n;
            }
        }
        if (o != long1) {
            System.out.println("Image degraded");
        }
        this.D.case.long = long1;
        this.D.case.e = e;
        if (this.D.else != null && this.D.else.case != null && this.D.else.case.for) {
            d.a(this.D.else.case, this.D.case);
        }
        else {
            int n2 = 0;
            boolean b2 = false;
            int n3 = 0;
            for (int i = 0; i < e; ++i) {
                if (n3 == 100) {
                    b2 = !b2;
                    n3 = 0;
                }
                int n4 = 0;
                boolean b3 = false;
                for (int j = 0; j < long1; ++j) {
                    if (n4 == 100) {
                        b3 = !b3;
                        n4 = 0;
                    }
                    this.D.case.c[n2++] = ((b2 == b3) ? -3355444 : -1);
                    ++n4;
                }
                ++n3;
            }
        }
        this.D.case.b = true;
        this.D.for = true;
        if (long1 == o && e == a) {
            int n5 = 0;
            int n6 = 0;
            final byte[][] array = new byte[1][];
            final int[] array2 = { 0 };
            int n7 = 0;
            for (int k = 0; k < e; ++k) {
                if (this.a(array, array2) != 0) {
                    break;
                }
                ++n5;
                final byte[] array3 = array[0];
                int n8 = 0;
                final int t = this.t();
                if (this.x() == 3) {
                    for (int l = 0; l < long1; ++l, n8 += t) {
                        this.D.case.c[l + n7] = (0xFF000000 | (array3[n8] & 0xFF) << 16 | (array3[1 + n8] & 0xFF) << 8 | (array3[2 + n8] & 0xFF));
                    }
                }
                else if (this.x() == 1) {
                    for (int long2 = this.D.case.long; long2 > 0; --long2, n8 += t) {
                        this.D.case.c[this.D.case.long - long2 + n7] = (0xFF000000 | (array3[n8] & 0xFF) << 16 | (array3[n8] & 0xFF) << 8 | (array3[n8] & 0xFF));
                    }
                }
                ++n6;
                n7 += long1;
                if (this.Z.h) {
                    return false;
                }
            }
        }
        else {
            if (this.al) {
                int n9 = 0;
                int n10 = 0;
                for (int n11 = 0; n11 < 6; ++n11) {
                    this.a(o, o, n9, long1, long1, n10);
                    n9 += o;
                    n10 += long1;
                    if (this.Z.h) {
                        return false;
                    }
                }
            }
            else {
                this.a(o, a, 0, long1, e, 0);
            }
            if (this.Z.h) {
                return false;
            }
        }
        this.D.case.for = true;
        return this.D.byte = true;
    }
    
    private void a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final byte[][] array = new byte[1][];
        final int[] array2 = { 0 };
        boolean b = false;
        final byte[][] array3 = null;
        int n7 = n / n4;
        byte[][] array4;
        if (n7 >= 2) {
            b = true;
            array4 = new byte[n7][];
        }
        else {
            n7 = 0;
            array4 = new byte[2][];
        }
        final int n8 = (n << 16) / n4;
        final int n9 = (n2 << 16) / n5;
        int n10 = 0;
        int n11 = n4 * n6;
        this.a(array, array2);
        ++this.bj;
        for (int i = 0; i < n5; ++i) {
            final int n12 = (n10 >> 16) + n3;
            n10 += n9;
            while (this.bj < n12) {
                this.a(array, array2);
                ++this.bj;
                if (this.Z.h) {
                    return;
                }
            }
            array4[0] = array[0];
            int n13 = 0;
            for (int j = 1; j < n7; ++j) {
                if (this.a(array, array2) == 0) {
                    array4[j] = array[0];
                    ++this.bj;
                    ++n13;
                }
                else {
                    array4[j] = null;
                }
                if (this.Z.h) {
                    return;
                }
            }
            final int t = this.t();
            if (this.x() == 3) {
                int n14 = 0;
                if (!b) {
                    for (int k = 0; k < n4; ++k) {
                        final int n15 = (n14 >> 16) * t;
                        if (array4[1] == null || k == n4) {
                            this.D.case.c[k + n11] = (0xFF000000 | (array4[0][n15] & 0xFF) << 16 | (array4[0][1 + n15] & 0xFF) << 8 | (array4[0][2 + n15] & 0xFF));
                        }
                        else {
                            final int n16 = n15 + t;
                            this.D.case.c[k + n11] = if((array4[0][n15] & 0xFF) << 16 | (array4[0][1 + n15] & 0xFF) << 8 | (array4[0][2 + n15] & 0xFF), (array4[0][n16] & 0xFF) << 16 | (array4[0][1 + n16] & 0xFF) << 8 | (array4[0][2 + n16] & 0xFF), (array4[1][n15] & 0xFF) << 16 | (array4[1][1 + n15] & 0xFF) << 8 | (array4[1][2 + n15] & 0xFF), (array4[1][n16] & 0xFF) << 16 | (array4[1][1 + n16] & 0xFF) << 8 | (array4[1][2 + n16] & 0xFF), n14, n10);
                        }
                        n14 += n8;
                    }
                }
                else {
                    for (int l = 0; l < n4; ++l) {
                        final int n17 = (n14 >> 16) * t;
                        int n18 = 0;
                        int n19 = 0;
                        int n20 = 0;
                        int n21 = 0;
                        for (int n22 = 0; n22 < n13; ++n22) {
                            int n23 = n17;
                            for (int n24 = 0; n24 < n13; ++n24) {
                                n18 += (array4[n22][n23] & 0xFF);
                                n19 += (array4[n22][n23 + 1] & 0xFF);
                                n20 += (array4[n22][n23 + 2] & 0xFF);
                                n23 += t;
                                ++n21;
                            }
                        }
                        this.D.case.c[l + n11] = (0xFF000000 | (n18 / n21 & 0xFF) << 16 | (n19 / n21 & 0xFF) << 8 | (n20 / n21 & 0xFF));
                        n14 += n8;
                    }
                }
            }
            else if (this.x() == 1) {
                int n25 = 0;
                if (!b) {
                    for (int n26 = 0; n26 < n4; ++n26) {
                        final int n27 = (n25 >> 16) * t;
                        if (array4[1] == null || n26 == n4) {
                            this.D.case.c[n26 + n11] = (0xFF000000 | (array4[0][n27] & 0xFF) << 16 | (array4[0][n27] & 0xFF) << 8 | (array4[0][n27] & 0xFF));
                        }
                        else {
                            final int n28 = n27 + t;
                            final int n29 = array4[0][n27] & 0xFF;
                            final int n30 = array4[0][n28] & 0xFF;
                            final int n31 = array4[1][n27] & 0xFF;
                            final int n32 = array4[1][n28] & 0xFF;
                            this.D.case.c[n26 + n11] = if(n29 << 16 | n29 << 8 | n29, n30 << 16 | n30 << 8 | n30, n31 << 16 | n31 << 8 | n31, n32 << 16 | n32 << 8 | n32, n25, n10);
                        }
                        n25 += n8;
                    }
                }
                else {
                    for (int n33 = 0; n33 < n4; ++n33) {
                        final int n34 = (n25 >> 16) * t;
                        int n35 = 0;
                        int n36 = 0;
                        for (int n37 = 0; n37 < n13; ++n37) {
                            int n38 = n34;
                            for (int n39 = 0; n39 < n13; ++n39) {
                                n35 += (array4[n37][n38] & 0xFF);
                                n38 += t;
                                ++n36;
                            }
                        }
                        final int n40 = n35 / n36 & 0xFF;
                        this.D.case.c[n33 + n11] = (0xFF000000 | n40 << 16 | n40 << 8 | (n40 & 0xFF));
                        n25 += n8;
                    }
                }
            }
            n11 += n4;
        }
    }
    
    private static int if(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final int n7 = n5 >> 8 & 0xFF;
        final int n8 = n6 >> 8 & 0xFF;
        final int n9 = (256 - n7) * (256 - n8);
        final int n10 = n7 * (256 - n8);
        final int n11 = (256 - n7) * n8;
        final int n12 = n7 * n8;
        return (n9 * (n >> 16 & 0xFF) + n10 * (n2 >> 16 & 0xFF) + n11 * (n3 >> 16 & 0xFF) + n12 * (n4 >> 16 & 0xFF) & 0xFF0000) + ((n9 * (n >> 8 & 0xFF) + n10 * (n2 >> 8 & 0xFF) + n11 * (n3 >> 8 & 0xFF) + n12 * (n4 >> 8 & 0xFF) & 0xFF0000) >> 8) + (n9 * (n & 0xFF) + n10 * (n2 & 0xFF) + (n11 * (n3 & 0xFF) + n12 * (n4 & 0xFF)) >> 16) - 16777216;
    }
    
    int do(final int n, final int n2) {
        return n + (1 << n2 - 1) >> n2;
    }
    
    short a(final short n) {
        if ((n & 0xFF00) != 0x0) {
            return (short)(~n >> 15 & 0xFF);
        }
        return n;
    }
    
    void a(final short[] array, final byte[] array2, final int n) {
        int n2 = 0;
        for (int i = 7; i >= 0; --i) {
            if ((array[1 + n2] | array[2 + n2] | array[3 + n2] | array[4 + n2] | array[5 + n2] | array[6 + n2] | array[7 + n2]) == 0x0) {
                final short n3 = (short)(array[n2] << 2);
                array[1 + n2] = (array[0 + n2] = n3);
                array[3 + n2] = (array[2 + n2] = n3);
                array[5 + n2] = (array[4 + n2] = n3);
                array[7 + n2] = (array[6 + n2] = n3);
                n2 += 8;
            }
            else {
                final short n4 = array[2 + n2];
                final short n5 = array[6 + n2];
                final short n6 = (short)((n4 + n5) * 4433);
                final short n7 = (short)(n6 + n5 * -15137);
                final short n8 = (short)(n6 + n4 * 6270);
                final int n9 = array[n2] + array[4 + n2] << 13;
                final int n10 = array[n2] - array[4 + n2] << 13;
                final short n11 = (short)(n9 + n8);
                final short n12 = (short)(n9 - n8);
                final short n13 = (short)(n10 + n7);
                final short n14 = (short)(n10 - n7);
                final short n15 = array[7 + n2];
                final short n16 = array[5 + n2];
                final short n17 = array[3 + n2];
                final short n18 = array[1 + n2];
                final short n19 = (short)(n15 + n18);
                final short n20 = (short)(n16 + n17);
                final short n21 = (short)(n15 + n17);
                final short n22 = (short)(n16 + n18);
                final short n23 = (short)((n21 + n22) * 9633);
                final short n24 = (short)(n15 * 2446);
                final short n25 = (short)(n16 * 16819);
                final short n26 = (short)(n17 * 25172);
                final short n27 = (short)(n18 * 12299);
                final short n28 = (short)(n19 * -7373);
                final short n29 = (short)(n20 * -20995);
                final short n30 = (short)(n21 * -16069);
                final short n31 = (short)(n22 * -3196);
                final short n32 = (short)(n30 + n23);
                final short n33 = (short)(n31 + n23);
                final short n34 = (short)(n24 + (n28 + n32));
                final short n35 = (short)(n25 + (n29 + n33));
                final short n36 = (short)(n26 + (n29 + n32));
                final short n37 = (short)(n27 + (n28 + n33));
                array[0 + n2] = (short)this.do(n11 + n37, 11);
                array[7 + n2] = (short)this.do(n11 - n37, 11);
                array[1 + n2] = (short)this.do(n13 + n36, 11);
                array[6 + n2] = (short)this.do(n13 - n36, 11);
                array[2 + n2] = (short)this.do(n14 + n35, 11);
                array[5 + n2] = (short)this.do(n14 - n35, 11);
                array[3 + n2] = (short)this.do(n12 + n34, 11);
                array[4 + n2] = (short)this.do(n12 - n34, 11);
                n2 += 8;
            }
        }
        int n38 = 0;
        int n39 = 0;
        for (int j = 7; j >= 0; --j) {
            if ((array[8 + n38] | array[16 + n38] | array[24 + n38] | array[32 + n38] | array[40 + n38] | array[48 + n38] | array[56 + n38]) == 0x0) {
                int n40;
                if ((n40 = (short)((short)this.do(array[n38], 5) + 128)) < 0) {
                    n40 = 0;
                }
                else if (n40 > 255) {
                    n40 = 255;
                }
                array2[0 + n39 + n] = (byte)n40;
                array2[8 + n39 + n] = (byte)n40;
                array2[16 + n39 + n] = (byte)n40;
                array2[24 + n39 + n] = (byte)n40;
                array2[32 + n39 + n] = (byte)n40;
                array2[40 + n39 + n] = (byte)n40;
                array2[48 + n39 + n] = (byte)n40;
                array2[56 + n39 + n] = (byte)n40;
                ++n38;
                ++n39;
            }
            else {
                final short n41 = array[16 + n38];
                final short n42 = array[48 + n38];
                final short n43 = (short)((n41 + n42) * 4433);
                final short n44 = (short)(n43 + n42 * -15137);
                final short n45 = (short)(n43 + n41 * 6270);
                final int n46 = array[0 + n38] + array[32 + n38] << 13;
                final int n47 = array[0 + n38] - array[32 + n38] << 13;
                final short n48 = (short)(n46 + n45);
                final short n49 = (short)(n46 - n45);
                final short n50 = (short)(n47 + n44);
                final short n51 = (short)(n47 - n44);
                final short n52 = array[56 + n38];
                final short n53 = array[40 + n38];
                final short n54 = array[24 + n38];
                final short n55 = array[8 + n38];
                final short n56 = (short)(n52 + n55);
                final short n57 = (short)(n53 + n54);
                final short n58 = (short)(n52 + n54);
                final short n59 = (short)(n53 + n55);
                final short n60 = (short)((n58 + n59) * 9633);
                final short n61 = (short)(n52 * 2446);
                final short n62 = (short)(n53 * 16819);
                final short n63 = (short)(n54 * 25172);
                final short n64 = (short)(n55 * 12299);
                final short n65 = (short)(n56 * -7373);
                final short n66 = (short)(n57 * -20995);
                final short n67 = (short)(n58 * -16069);
                final short n68 = (short)(n59 * -3196);
                final short n69 = (short)(n67 + n60);
                final short n70 = (short)(n68 + n60);
                final short n71 = (short)(n61 + (n65 + n69));
                final short n72 = (short)(n62 + (n66 + n70));
                final short n73 = (short)(n63 + (n66 + n69));
                final short n74 = (short)(n64 + (n65 + n70));
                array2[0 + n39 + n] = (byte)this.a((short)(this.do(n48 + n74, 18) + 128));
                array2[56 + n39 + n] = (byte)this.a((short)(this.do(n48 - n74, 18) + 128));
                array2[8 + n39 + n] = (byte)this.a((short)(this.do(n50 + n73, 18) + 128));
                array2[48 + n39 + n] = (byte)this.a((short)(this.do(n50 - n73, 18) + 128));
                array2[16 + n39 + n] = (byte)this.a((short)(this.do(n51 + n72, 18) + 128));
                array2[40 + n39 + n] = (byte)this.a((short)(this.do(n51 - n72, 18) + 128));
                array2[24 + n39 + n] = (byte)this.a((short)(this.do(n49 + n71, 18) + 128));
                array2[32 + n39 + n] = (byte)this.a((short)(this.do(n49 - n71, 18) + 128));
                ++n38;
                ++n39;
            }
        }
    }
    
    public void long() {
        if (this.K != null) {
            this.K = null;
        }
        for (int i = 0; i < 100; ++i) {
            this.aU[i] = null;
        }
    }
    
    public void do(final int am) throws Exception {
        this.am = am;
        this.long();
        throw new Exception();
    }
    
    public byte[] int(final int n) throws Exception {
        int n2;
        for (n2 = 0; n2 < 100 && this.aU[n2] != null; ++n2) {}
        if (n2 == 100) {
            this.do(-221);
        }
        final byte[] array = new byte[n];
        if (array == null) {
            this.do(-233);
        }
        return this.aU[n2] = array;
    }
    
    public void a(final byte[] array, final int n, final short n2, int i) {
        final byte b = (byte)(n2 & 0xFF);
        final byte b2 = (byte)(n2 >> 8 & 0xFF);
        while (i > 0) {
            array[2 * i - 1 + n] = b2;
            array[2 * i - 2 + n] = b;
            --i;
        }
    }
    
    public void goto() throws Exception {
        this.ao = 0;
        this.O = 0;
        if (this.I[0]) {
            return;
        }
        do {
            final int a = this.K.a(this.at, this.ao + 128, 4096 - this.ao, this.I);
            if (a == -1) {
                this.do(-232);
            }
            this.ao += a;
        } while (this.ao < 4096 && !this.I[0]);
        this.av += this.ao;
        this.a(this.at, this.ao + this.O + 128, (short)(-9729), 64);
    }
    
    public void m() throws Exception {
        final byte[] array = new byte[17];
        final byte[] array2 = new byte[256];
        int i = this.for(16);
        if (i < 2) {
            this.do(-202);
        }
        i -= 2;
        while (i != 0) {
            final int for1 = this.for(8);
            array[0] = 0;
            byte b = 0;
            for (int j = 1; j <= 16; ++j) {
                array[j] = (byte)this.for(8);
                b += array[j];
            }
            if (b > 255) {
                this.do(-200);
            }
            for (byte b2 = 0; b2 < b; ++b2) {
                array2[b2] = (byte)this.for(8);
            }
            final byte b3 = (byte)(17 + b);
            if (i < b3) {
                this.do(-202);
            }
            i -= b3;
            if ((for1 & 0x10) > 16) {
                this.do(-201);
            }
            final int n = (for1 & 0xF) + ((for1 & 0x10) >> 4) * 4;
            if (n >= 8) {
                this.do(-201);
            }
            if (this.aY[n] == null) {
                this.aY[n] = this.int(17);
            }
            if (this.aX[n] == null) {
                this.aX[n] = this.int(256);
            }
            System.arraycopy(array, 0, this.aY[n], 0, 17);
            System.arraycopy(array2, 0, this.aX[n], 0, 256);
        }
    }
    
    public void e() throws Exception {
        int i = this.for(16);
        if (i < 2) {
            this.do(-203);
        }
        int n3;
        for (i -= 2; i != 0; i -= n3) {
            final int for1 = this.for(8);
            final int n = for1 >> 4;
            final int n2 = for1 & 0xF;
            if (n2 >= 4) {
                this.do(-204);
            }
            if (this.B[n2] == null) {
                this.B[n2] = new short[64];
            }
            for (int j = 0; j < 64; ++j) {
                int for2 = this.for(8);
                if (n != 0) {
                    for2 = (for2 << 8) + this.for(8);
                }
                this.B[n2][j] = (short)for2;
            }
            n3 = 65;
            if (n != 0) {
                n3 += 64;
            }
            if (i < n3) {
                this.do(-219);
            }
        }
    }
    
    public void s() throws Exception {
        final int for1 = this.for(16);
        if (this.for(8) != 8) {
            this.do(-205);
        }
        this.x = this.for(16);
        if (this.x < 1 || this.x > 32696) {
            this.do(-206);
        }
        this.char = this.for(16);
        if (this.char < 1 || this.char > 32696) {
            this.do(-207);
        }
        this.aV = this.for(8);
        if (this.aV > 4) {
            this.do(-208);
        }
        if (for1 != this.aV * 3 + 8) {
            this.do(-209);
        }
        for (int i = 0; i < this.aV; ++i) {
            this.G[i] = this.for(8);
            this.w[i] = this.for(4);
            this.M[i] = this.for(4);
            this.R[i] = this.for(8);
        }
    }
    
    public void int() throws Exception {
        int i = this.for(16);
        if (i < 2) {
            this.do(-210);
        }
        for (i -= 2; i != 0; --i) {
            this.for(8);
        }
    }
    
    public void for() throws Exception {
        if (this.for(16) != 4) {
            this.do(-211);
        }
        this.aK = this.for(16);
    }
    
    public void g() throws Exception {
        int i = this.for(16);
        final int for1 = this.for(8);
        this.j = for1;
        i -= 3;
        if (i != for1 * 2 + 3 || for1 < 1 || for1 > 4) {
            this.do(-212);
        }
        for (int j = 0; j < for1; ++j) {
            final int for2 = this.for(8);
            final int for3 = this.for(8);
            i -= 2;
            int n;
            for (n = 0; n < this.aV && for2 != this.G[n]; ++n) {}
            if (n >= this.aV) {
                this.do(-213);
            }
            this.ax[j] = n;
            this.aW[n] = (for3 >> 4 & 0xF);
            this.E[n] = (for3 & 0xF) + 4;
        }
        this.int = this.for(8);
        this.a = this.for(8);
        this.k = this.for(4);
        this.a9 = this.for(4);
        if (this.r == 0) {
            this.int = 0;
            this.a = 63;
        }
        for (i -= 3; i != 0; --i) {
            this.for(8);
        }
    }
    
    public int D() throws Exception {
        int n = 0;
        int i;
        while (true) {
            ++n;
            if (this.for(8) == 255) {
                do {
                    i = this.for(8);
                } while (i == 255);
                if (i != 0) {
                    break;
                }
                continue;
            }
        }
        return i;
    }
    
    public int h() throws Exception {
        int d = 0;
    Label_0256:
        while (true) {
            d = this.D();
            switch (d) {
                case 192:
                case 193:
                case 194:
                case 195:
                case 197:
                case 198:
                case 199:
                case 201:
                case 202:
                case 203:
                case 205:
                case 206:
                case 207:
                case 216:
                case 217:
                case 218: {
                    break Label_0256;
                }
                case 196: {
                    this.m();
                    continue;
                }
                case 204: {
                    this.do(-215);
                    continue;
                }
                case 219: {
                    this.e();
                    continue;
                }
                case 221: {
                    this.for();
                    continue;
                }
                case 1:
                case 200:
                case 208:
                case 209:
                case 210:
                case 211:
                case 212:
                case 213:
                case 214:
                case 215: {
                    this.do(-216);
                    continue;
                }
                default: {
                    this.int();
                    continue;
                }
            }
        }
        return d;
    }
    
    public void new() throws Exception {
        final int for1 = this.for(8);
        int n = this.for(8);
        if (for1 == 255 && n == 216) {
            return;
        }
        int n2 = 512;
        int n3;
        do {
            if (--n2 == 0) {
                this.do(-217);
            }
            n3 = n;
            n = this.for(8);
        } while (n3 != 255 || n != 216);
        if ((int)(this.bl >> 8 & 0xFFL) != 255) {
            this.do(-217);
        }
    }
    
    public void A() throws Exception {
        this.new();
        switch (this.h()) {
            case 194: {
                this.r = 1;
            }
            case 192:
            case 193: {
                this.s();
                break;
            }
            case 201: {
                this.do(-215);
                break;
            }
            default: {
                this.do(-218);
                break;
            }
        }
    }
    
    public int u() throws Exception {
        final int h = this.h();
        if (h == 217) {
            return 0;
        }
        if (h != 218) {
            this.do(-216);
        }
        this.g();
        return 1;
    }
    
    public void a(final aa k) throws Exception {
        this.am = 0;
        this.bn = false;
        final boolean b = false;
        this.x = (b ? 1 : 0);
        this.char = (b ? 1 : 0);
        this.K = k;
        this.r = 0;
        this.au = 0;
        this.aV = 0;
        this.j = 0;
        this.int = 0;
        this.a = 0;
        this.a9 = 0;
        this.k = 0;
        this.bg = 0;
        this.for = 0;
        this.Y = 0;
        this.ac = 0;
        this.bd = 0;
        this.X = 0;
        this.ab = 0;
        this.L = 0;
        this.S = 0;
        this.goto = 0;
        this.bo = 0;
        this.e = 0;
        this.O = 0;
        this.ao = 0;
        this.I[0] = false;
        this.p = 0;
        this.aK = 0;
        this.an = 0;
        this.J = 0;
        this.N = 0;
        this.aw = 0;
        this.ba = 0;
        this.W = null;
        this.av = 0;
        this.goto();
        this.aR = 16;
        this.bl = 0L;
        this.for(16);
        this.for(16);
        for (int i = 0; i < 12288; ++i) {
            this.aB[i] = 64;
        }
    }
    
    public int a(final double n) {
        return (int)(n * 65536.0 + 0.5);
    }
    
    public void d() {
        for (int i = 0; i <= 255; ++i) {
            final int n = i * 2 - 256;
            this.aZ[i] = this.a(0.701) * n + 32768 >> 16;
            this.ai[i] = this.a(0.886) * n + 32768 >> 16;
            this.a8[i] = -this.a(0.35707) * n;
            this.ae[i] = -this.a(0.17207) * n + 32768;
        }
    }
    
    public void w() throws Exception {
        if (this.aR == 16) {
            this.a((byte)(this.bl >> 16 & 0xFFL));
        }
        if (this.aR >= 8) {
            this.a((byte)(this.bl >> 24 & 0xFFL));
        }
        this.a((byte)(this.bl & 0xFFL));
        this.a((byte)(this.bl >> 8 & 0xFFL));
        this.if(this.aR = 16);
        this.if(16);
    }
    
    public void c() {
        final short[] as = this.aS;
        final byte[] w = this.W;
        int n = 0;
        int n2 = 0;
        for (int i = this.ac; i > 0; --i) {
            System.arraycopy(as, n, this.aF, 0, 64);
            this.a(this.aF, w, n2);
            n += 64;
            n2 += 64;
        }
    }
    
    public o a(final int do1, final int if1, final int a, final int new1) throws Exception {
        final o o = new o();
        o.do = do1;
        o.if = if1;
        o.a = a;
        o.new = new1;
        o.int = a * new1 * 2;
        o.for = this.int(o.int * do1 * if1);
        return o;
    }
    
    public int a(final o o, final int n, final int n2) throws Exception {
        if (n >= o.do) {
            this.do(-229);
        }
        if (n2 >= o.if) {
            this.do(-229);
        }
        return n * o.int + n2 * (o.int * o.do);
    }
    
    public void r() throws Exception {
        int n = 0;
        final int[] array = new int[4];
        for (int i = 0; i < 4; ++i) {
            array[i] = 0;
        }
        for (int j = 0; j < this.bd; ++j) {
            int n2 = 0;
            int n3 = 0;
            int k = 0;
        Label_0410:
            while (k < this.Y) {
                final int n4 = this.bf[k];
                final short[] as = this.aS;
                final int n5 = n * 64;
                final short[] array2 = this.B[this.R[n4]];
                final byte[] for1 = this.ap[n4].for;
                final int a = this.a(this.ap[n4], array[n4] + n2, this.Q[n4] + n3);
                final byte[] for2 = this.bm[n4].for;
                final int a2 = this.a(this.bm[n4], array[n4] + n2, this.Q[n4] + n3);
                as[0 + n5] = (short)((for2[0 + a2] & 0xFF) + ((for2[1 + a2] & 0xFF) << 8));
                for (int l = 0; l < 63; ++l) {
                    as[1 + n5 + l] = (short)(((for1[2 * (1 + l) + a + 1] & 0xFF) << 8) + (for1[2 * (1 + l) + a] & 0xFF));
                }
                while (true) {
                    for (int n6 = 63; n6 > 0; --n6) {
                        if (as[ab.A[n6] + n5] != 0) {
                            while (n6 >= 0) {
                                if (as[ab.A[n6] + n5] != 0) {
                                    final short[] array3 = as;
                                    final int n7 = ab.A[n6] + n5;
                                    array3[n7] *= array2[n6];
                                }
                                --n6;
                            }
                            ++n;
                            if (this.j == 1) {
                                final int[] array4 = array;
                                final int n8 = n4;
                                ++array4[n8];
                            }
                            else if (++n2 == this.w[n4]) {
                                n2 = 0;
                                if (++n3 == this.M[n4]) {
                                    n3 = 0;
                                    final int[] array5 = array;
                                    final int n9 = n4;
                                    array5[n9] += this.w[n4];
                                }
                            }
                            ++k;
                            continue Label_0410;
                        }
                    }
                    continue;
                }
            }
        }
        if (this.j == 1) {
            final int[] q = this.Q;
            final int n10 = this.ax[0];
            ++q[n10];
        }
        else {
            for (int n11 = 0; n11 < this.j; ++n11) {
                final int n12 = this.ax[n11];
                final int[] q2 = this.Q;
                final int n13 = n12;
                q2[n13] += this.M[n12];
            }
        }
    }
    
    int if(final int n, final int n2) {
        return (n < ab.as[n2]) ? (n + ab.long[n2]) : n;
    }
    
    int a(final int n, final int n2) {
        return (n < ab.as[n2]) ? (n + ab.long[n2]) : n;
    }
    
    public void try() throws Exception {
        int n = 0;
        for (int i = 0; i < this.bd; ++i) {
            if (this.aK != 0 && this.an == 0) {
                this.j();
            }
            for (int j = 0; j < this.Y; ++j) {
                final int n2 = this.bf[j];
                final short[] as = this.aS;
                final int n3 = n * 64;
                final short[] array = this.B[this.R[n2]];
                int n4;
                if ((n4 = this.a(this.af[this.aW[n2]])) != 0) {
                    n4 = this.if(this.if(n4), n4);
                }
                as[0 + n3] = (short)((this.ag[n2] += n4) * array[0]);
                final int n5 = this.aB[n];
                final c c = this.af[this.E[n2]];
                int k;
                for (k = 1; k < 64; ++k) {
                    final int a = this.a(c);
                    final int n6 = a >> 4;
                    final int n7 = a & 0xF;
                    if (n7 != 0) {
                        if (n6 != 0) {
                            if (k + n6 > 63) {
                                this.do(-227);
                            }
                            if (k < n5) {
                                for (int l = (n6 < n5 - k) ? n6 : (n5 - k), n8 = k; l != 0; --l, as[ab.A[n8++] + n3] = 0) {}
                            }
                            k += n6;
                        }
                        as[ab.A[k] + n3] = (short)(this.if(this.if(n7), n7) * array[k]);
                    }
                    else {
                        if (n6 != 15) {
                            break;
                        }
                        if (k + 15 > 63) {
                            this.do(-227);
                        }
                        if (k < n5) {
                            for (int n9 = (16 < n5 - k) ? 16 : (n5 - k), n10 = k; n9 != 0; --n9, as[ab.A[n10++] + n3] = 0) {}
                        }
                        k += 15;
                    }
                }
                if (k < n5) {
                    for (int n11 = k; n11 < n5; as[ab.A[n11++] + n3] = 0) {}
                }
                this.aB[n] = k;
                ++n;
            }
            --this.an;
        }
    }
    
    public void a(final int n, final c c) {
        final byte[] array = new byte[257];
        final int[] array2 = new int[257];
        int n2 = 0;
        for (int i = 1; i <= 16; ++i) {
            for (byte b = 1; b <= this.aY[n][i]; ++b) {
                array[n2++] = (byte)i;
            }
        }
        array[n2] = 0;
        final int n3 = n2;
        int n4 = 0;
        byte b2 = array[0];
        int n5 = 0;
        while (array[n5] != 0) {
            while (array[n5] == b2) {
                array2[n5++] = n4;
                ++n4;
            }
            n4 <<= 1;
            ++b2;
        }
        int n6 = 0;
        for (int j = 0; j < 256; ++j) {
            c.if[j] = 0;
            c.do[j] = 0;
            c.a[n6++] = 0;
            c.a[n6++] = 0;
        }
        int n7 = -1;
        for (int k = 0; k < n3; ++k) {
            final int n8 = this.aX[n][k] & 0xFF;
            final int n9 = array2[k];
            final byte b3 = array[k];
            c.do[n8] = b3;
            if (b3 <= 8) {
                int n10 = n9 << 8 - b3;
                for (int l = 1 << 8 - b3; l > 0; --l) {
                    c.if[n10] = n8;
                    ++n10;
                }
            }
            else {
                final int n11 = n9 >> b3 - 8 & 0xFF;
                int n12 = c.if[n11];
                if (n12 == 0) {
                    n12 = (c.if[n11] = n7);
                    n7 -= 2;
                }
                int n13 = n9 << 16 - (b3 - 8);
                for (int n14 = b3; n14 > 9; --n14) {
                    if ((n13 & 0x8000) == 0x0) {
                        --n12;
                    }
                    if (c.a[-n12 - 1] == 0) {
                        c.a[-n12 - 1] = n7;
                        n12 = n7;
                        n7 -= 2;
                    }
                    else {
                        n12 = c.a[-n12 - 1];
                    }
                    n13 <<= 1;
                }
                if ((n13 & 0x8000) == 0x0) {
                    --n12;
                }
                c.a[-n12 - 1] = n8;
            }
        }
    }
    
    public void C() throws Exception {
        for (int i = 0; i < this.j; ++i) {
            if (this.B[this.R[this.ax[i]]] == null) {
                this.do(-222);
            }
        }
    }
    
    public void B() throws Exception {
        for (int i = 0; i < this.j; ++i) {
            if (this.int == 0 && this.aY[this.aW[this.ax[i]]] == null) {
                this.do(-223);
            }
            if (this.a > 0 && this.aY[this.E[this.ax[i]]] == null) {
                this.do(-223);
            }
        }
        for (int j = 0; j < 8; ++j) {
            if (this.aY[j] != null) {
                if (this.af[j] == null) {
                    this.af[j] = new c();
                }
                this.a(j, this.af[j]);
            }
        }
        for (int k = 0; k < this.Y; ++k) {
            this.aH[k] = this.af[this.aW[this.bf[k]]];
            this.ar[k] = this.af[this.E[this.bf[k]]];
        }
    }
    
    public void f() {
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < this.aV; ++i) {
            if (this.w[i] > n) {
                n = this.w[i];
            }
            if (this.M[i] > n2) {
                n2 = this.M[i];
            }
        }
        for (int j = 0; j < this.aV; ++j) {
            this.aL[j] = ((this.char * this.w[j] + (n - 1)) / n + 7) / 8;
            this.c[j] = ((this.x * this.M[j] + (n2 - 1)) / n2 + 7) / 8;
        }
        if (this.j == 1) {
            this.bd = this.aL[this.ax[0]];
            this.X = this.c[this.ax[0]];
        }
        else {
            this.bd = ((this.char + 7) / 8 + (n - 1)) / n;
            this.X = ((this.x + 7) / 8 + (n2 - 1)) / n2;
        }
        if (this.j == 1) {
            this.bf[0] = this.ax[0];
            this.Y = 1;
        }
        else {
            this.Y = 0;
            for (int k = 0; k < this.j; ++k) {
                for (int n3 = this.ax[k], l = this.w[n3] * this.M[n3]; l != 0; --l, this.bf[this.Y++] = n3) {}
            }
        }
    }
    
    public int l() throws Exception {
        if (this.u() == 0) {
            return 0;
        }
        this.f();
        this.B();
        this.C();
        for (int i = 0; i < this.aV; ++i) {
            this.ag[i] = 0;
        }
        this.e = 0;
        if (this.aK != 0) {
            this.an = this.aK;
            this.J = 0;
        }
        this.w();
        return 1;
    }
    
    public void b() throws Exception {
        if (this.aV == 1) {
            this.au = 0;
            this.aw = 1;
            this.bg = 8;
            this.for = 8;
        }
        else if (this.aV == 3) {
            if (this.w[1] != 1 || this.M[1] != 1 || this.w[2] != 1 || this.M[2] != 1) {
                this.do(-226);
            }
            if (this.w[0] == 1 && this.M[0] == 1) {
                this.au = 1;
                this.aw = 3;
                this.bg = 8;
                this.for = 8;
            }
            else if (this.w[0] == 2 && this.M[0] == 1) {
                this.au = 2;
                this.aw = 4;
                this.bg = 16;
                this.for = 8;
            }
            else if (this.w[0] == 1 && this.M[0] == 2) {
                this.au = 3;
                this.aw = 4;
                this.bg = 8;
                this.for = 16;
            }
            else if (this.w[0] == 2 && this.M[0] == 2) {
                this.au = 4;
                this.aw = 6;
                this.bg = 16;
                this.for = 16;
            }
            else {
                this.do(-226);
            }
        }
        else {
            this.do(-225);
        }
        this.N = (this.char + (this.bg - 1)) / this.bg;
        this.ba = (this.x + (this.for - 1)) / this.for;
        if (this.au == 0) {
            this.bo = 1;
        }
        else {
            this.bo = 4;
        }
        this.goto = (this.char + 15 & 0xFFF0) * this.bo;
        this.S = this.char * this.bo;
        this.aI = this.int(this.goto + 8);
        this.aG = this.int(this.goto + 8);
        this.ac = this.N * this.aw;
        if (this.ac > 12288) {
            this.do(-229);
        }
        this.aS = new short[this.ac * 64 + 4];
        for (int i = 0; i < this.ac; ++i) {
            this.aB[i] = 64;
        }
        this.W = this.int(this.ac * 64 + 8);
        this.ab = this.x;
        this.L = 0;
        this.d();
    }
    
    public void j() throws Exception {
        int byte1 = 0;
        int n;
        for (n = 1536; n > 0 && this.byte() != 255; --n) {}
        if (n == 0) {
            this.do(-228);
        }
        while (n > 0 && (byte1 = this.byte()) == 255) {
            --n;
        }
        if (n == 0) {
            this.do(-228);
        }
        if (byte1 != this.J + 208) {
            this.do(-228);
        }
        for (int i = 0; i < this.aV; ++i) {
            this.ag[i] = 0;
        }
        this.e = 0;
        this.an = this.aK;
        this.J = (this.J + 1 & 0x7);
        this.if(this.aR = 16);
        this.if(16);
    }
    
    public void n() throws Exception {
        if (this.l() == 0) {
            this.do(-216);
        }
    }
    
    public void if() throws Exception {
        this.b();
        if (this.r != 0) {
            this.am = -234;
            throw new Exception();
        }
        this.n();
    }
    
    public void if(final aa aa) throws Exception {
        this.a(aa);
        this.A();
    }
    
    public void k() {
        final int n = this.for - this.L;
        final byte[] ai = this.aI;
        final byte[] ag = this.aG;
        final byte[] w = this.W;
        final byte[] w2 = this.W;
        int n2 = 0;
        int n3 = 0;
        int n4;
        if (n < 8) {
            n4 = n * 8;
        }
        else {
            n4 = 128 + (n & 0x7) * 8;
        }
        int n5 = 256 + (n >> 1) * 8;
        for (int i = this.N; i > 0; --i) {
            for (int j = 0; j < 2; ++j) {
                for (int k = 0; k < 8; k += 2) {
                    final int n6 = w2[0 + n5] & 0xFF;
                    final int n7 = w2[64 + n5] & 0xFF;
                    final int n8 = this.aZ[n7];
                    final int n9 = (int)(this.a8[n7] + this.ae[n6] >> 16);
                    final int n10 = this.ai[n6];
                    final int n11 = w[k + n4] & 0xFF;
                    ai[0 + n2] = this.a(n11 + n8);
                    ai[1 + n2] = this.a(n11 + n9);
                    ai[2 + n2] = this.a(n11 + n10);
                    final int n12 = w[k + 1 + n4] & 0xFF;
                    ai[4 + n2] = this.a(n12 + n8);
                    ai[5 + n2] = this.a(n12 + n9);
                    ai[6 + n2] = this.a(n12 + n10);
                    final int n13 = w[k + 8 + n4] & 0xFF;
                    ag[0 + n3] = this.a(n13 + n8);
                    ag[1 + n3] = this.a(n13 + n9);
                    ag[2 + n3] = this.a(n13 + n10);
                    final int n14 = w[k + 8 + 1 + n4] & 0xFF;
                    ag[4 + n3] = this.a(n14 + n8);
                    ag[5 + n3] = this.a(n14 + n9);
                    ag[6 + n3] = this.a(n14 + n10);
                    n2 += 8;
                    n3 += 8;
                    ++n5;
                }
                n4 += 64;
            }
            n4 += 256;
            n5 += 376;
        }
    }
    
    public void do() {
        final int n = this.for - this.L;
        final byte[] ai = this.aI;
        final byte[] w = this.W;
        final byte[] w2 = this.W;
        int n2 = n * 8;
        int n3 = 128 + n * 8;
        int n4 = 0;
        for (int i = this.N; i > 0; --i) {
            for (int j = 0; j < 2; ++j) {
                for (int k = 0; k < 4; ++k) {
                    final int n5 = w2[0 + n3] & 0xFF;
                    final int n6 = w2[64 + n3] & 0xFF;
                    final int n7 = this.aZ[n6];
                    final int n8 = (int)(this.a8[n6] + this.ae[n5] >> 16);
                    final int n9 = this.ai[n5];
                    final int n10 = w[(k << 1) + n2] & 0xFF;
                    ai[0 + n4] = this.a(n10 + n7);
                    ai[1 + n4] = this.a(n10 + n8);
                    ai[2 + n4] = this.a(n10 + n9);
                    final int n11 = w[(k << 1) + 1 + n2] & 0xFF;
                    ai[4 + n4] = this.a(n11 + n7);
                    ai[5 + n4] = this.a(n11 + n8);
                    ai[6 + n4] = this.a(n11 + n9);
                    n4 += 8;
                    ++n3;
                }
                n2 += 64;
            }
            n2 += 128;
            n3 += 248;
        }
    }
    
    public void y() {
        final int n = this.for - this.L;
        final byte[] ai = this.aI;
        final byte[] ag = this.aG;
        int n2 = 0;
        int n3 = 0;
        final byte[] w = this.W;
        final byte[] w2 = this.W;
        int n4;
        if (n < 8) {
            n4 = n * 8;
        }
        else {
            n4 = 64 + (n & 0x7) * 8;
        }
        int n5 = 128 + (n >> 1) * 8;
        for (int i = this.N; i > 0; --i) {
            for (int j = 0; j < 8; ++j) {
                final int n6 = w2[0 + j + n5] & 0xFF;
                final int n7 = w2[64 + j + n5] & 0xFF;
                final int n8 = this.aZ[n7];
                final int n9 = (int)(this.a8[n7] + this.ae[n6] >> 16);
                final int n10 = this.ai[n6];
                final int n11 = w[j + n4] & 0xFF;
                ai[0 + n2] = this.a(n11 + n8);
                ai[1 + n2] = this.a(n11 + n9);
                ai[2 + n2] = this.a(n11 + n10);
                final int n12 = w[8 + j + n4] & 0xFF;
                ag[0 + n3] = this.a(n12 + n8);
                ag[1 + n3] = this.a(n12 + n9);
                ag[2 + n3] = this.a(n12 + n10);
                n2 += 4;
                n3 += 4;
            }
            n4 += 256;
            n5 += 256;
        }
    }
    
    public void void() {
        final int n = this.for - this.L;
        final byte[] ai = this.aI;
        int n2 = n * 8;
        int n3 = 0;
        final byte[] w = this.W;
        for (int i = this.N; i > 0; --i) {
            for (int j = 0; j < 8; ++j) {
                final int n4 = w[j + n2] & 0xFF;
                final int n5 = w[64 + j + n2] & 0xFF;
                final int n6 = w[128 + j + n2] & 0xFF;
                ai[0 + n3] = this.a(n4 + this.aZ[n6]);
                ai[1 + n3] = this.a((int)(n4 + (this.a8[n6] + this.ae[n5] >> 16)));
                ai[2 + n3] = this.a(n4 + this.ai[n5]);
                n3 += 4;
            }
            n2 += 192;
        }
    }
    
    public void q() {
        final int n = this.for - this.L;
        final byte[] ai = this.aI;
        final byte[] w = this.W;
        int n2 = 0;
        int n3 = n * 8;
        for (int i = this.N; i > 0; --i) {
            ai[0 + n2] = w[0 + n3];
            ai[1 + n2] = w[1 + n3];
            ai[2 + n2] = w[2 + n3];
            ai[3 + n2] = w[3 + n3];
            ai[4 + n2] = w[4 + n3];
            ai[5 + n2] = w[5 + n3];
            ai[6 + n2] = w[6 + n3];
            ai[7 + n2] = w[7 + n3];
            n3 += 64;
            n2 += 8;
        }
    }
    
    public void case() throws Exception {
        if (this.r == 0) {
            this.for(this.aR = 16);
            this.for(16);
            this.h();
        }
        this.av -= this.ao;
    }
    
    public int a(final long n, final byte b) {
        return (int)(n << b | n >> 32 - b);
    }
    
    public int byte() throws Exception {
        if (this.ao == 0) {
            this.goto();
            if (this.ao == 0) {
                final int p = this.p;
                this.p ^= 0x1;
                if (p != 0) {
                    return 217;
                }
                return 255;
            }
        }
        final int n = this.at[this.O + 128] & 0xFF;
        ++this.O;
        --this.ao;
        return n;
    }
    
    public int a(final boolean[] array) throws Exception {
        if (this.ao == 0) {
            this.goto();
            if (this.ao == 0) {
                array[0] = true;
                final int p = this.p;
                this.p ^= 0x1;
                if (p != 0) {
                    return 217;
                }
                return 255;
            }
        }
        array[0] = false;
        final int n = this.at[this.O + 128] & 0xFF;
        ++this.O;
        --this.ao;
        return n;
    }
    
    public void a(final byte b) {
        --this.O;
        this.at[this.O + 128] = b;
        ++this.ao;
    }
    
    public byte char() throws Exception {
        final boolean[] array = { false };
        final int a = this.a(array);
        if (a != 255) {
            return (byte)a;
        }
        if (array[0]) {
            return -1;
        }
        final int a2 = this.a(array);
        if (array[0]) {
            this.a((byte)(-1));
            return -1;
        }
        if (a2 == 0) {
            return -1;
        }
        this.a((byte)a2);
        this.a((byte)(-1));
        return -1;
    }
    
    public int for(int n) throws Exception {
        final int n2 = (int)(this.bl >> 16 - n & (1 << n) - 1);
        final int ar = this.aR - n;
        this.aR = ar;
        if (ar <= 0) {
            this.bl = this.a(this.bl, (byte)(n += this.aR));
            this.bl = ((this.bl & 0xFFFFL) | this.byte() << 24 | this.byte() << 16);
            this.bl = this.a(this.bl << 32 >>> 32, (byte)(-this.aR));
            this.aR += 16;
        }
        else {
            this.bl = this.a(this.bl << 32 >>> 32, (byte)n);
        }
        this.bl = this.bl << 32 >>> 32;
        return n2;
    }
    
    public int if(int n) throws Exception {
        final int n2 = (int)this.bl >> 16 - n & (1 << n) - 1;
        final int ar = this.aR - n;
        this.aR = ar;
        if (ar <= 0) {
            this.bl = this.a(this.bl, (byte)(n += this.aR));
            this.bl = ((this.bl & 0xFFFFL) | (this.char() & 0xFF) << 24 | (this.char() & 0xFF) << 16);
            this.bl = this.a(this.bl << 32 >>> 32, (byte)(-this.aR));
            this.aR += 16;
        }
        else {
            this.bl = this.a(this.bl << 32 >>> 32, (byte)n);
        }
        this.bl = this.bl << 32 >>> 32;
        return n2;
    }
    
    public int a(final c c) throws Exception {
        int i = c.if[(int)(this.bl >> 8 & 0xFFL)];
        if (i < 0) {
            this.if(8);
            do {
                i = c.a[~i + (1 - this.if(1))];
            } while (i < 0);
        }
        else {
            this.if(c.do[i]);
        }
        return i;
    }
    
    public byte a(int n) {
        if ((n & 0xFFFFFF00) != 0x0) {
            n = (~n >> 31 & 0xFF);
        }
        return (byte)n;
    }
    
    void do(final aa aa) {
        try {
            this.if(aa);
        }
        catch (Exception ex) {}
    }
    
    int v() {
        if (this.bn) {
            return 0;
        }
        if (this.am != 0) {
            return -1;
        }
        try {
            this.if();
        }
        catch (Exception ex) {
            return -1;
        }
        this.bn = true;
        return 0;
    }
    
    int a(final byte[][] array, final int[] array2) {
        if (this.am != 0 || !this.bn) {
            return -1;
        }
        if (this.ab == 0) {
            return 1;
        }
        if (this.L == 0) {
            try {
                if (this.r != 0) {
                    this.r();
                }
                else {
                    this.try();
                }
                if (this.ab <= this.for) {
                    this.case();
                }
                this.c();
                this.L = this.for;
            }
            catch (Exception ex) {}
        }
        switch (this.au) {
            case 4: {
                if ((this.L & 0x1) == 0x0) {
                    this.k();
                    array[0] = this.aI;
                    break;
                }
                array[0] = this.aG;
                break;
            }
            case 2: {
                this.do();
                array[0] = this.aI;
                break;
            }
            case 3: {
                if ((this.L & 0x1) == 0x0) {
                    this.y();
                    array[0] = this.aI;
                    break;
                }
                array[0] = this.aG;
                break;
            }
            case 1: {
                this.void();
                array[0] = this.aI;
                break;
            }
            case 0: {
                this.q();
                array[0] = this.aI;
                break;
            }
        }
        array2[0] = this.S;
        --this.L;
        --this.ab;
        return 0;
    }
    
    int i() {
        return this.am;
    }
    
    int o() {
        return this.char;
    }
    
    int a() {
        return this.x;
    }
    
    int x() {
        return this.aV;
    }
    
    int t() {
        return this.bo;
    }
    
    int z() {
        return this.char * this.t();
    }
    
    int else() {
        return this.av;
    }
}
