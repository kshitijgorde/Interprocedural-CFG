// 
// Decompiled by Procyon v0.5.30
// 

package C;

import java.awt.Point;

public class Z
{
    private static int[] abs;
    private static int[] arraycopy;
    private static final int[] length;
    private static final int[][] max;
    private static final int[] min;
    private static int[] notify;
    private static final int[] printStackTrace;
    private static final int[] toBinaryString;
    private static final int[] wait;
    private static final int[] x;
    private static final int[] y;
    private static final int[] I;
    private static final int[] Z;
    private static final int[] C;
    private static final int[] B;
    private static final int[] D;
    private static final int[] F;
    private static final int[] J;
    private static final int[] S;
    private static final int[] A;
    private static final int[] E;
    private static final int[] G;
    private static final int[] H;
    private static final int[] K;
    private static final int[] L;
    private static final int[] M;
    private static final int[] N;
    private static final int[] O;
    private static final int[] P;
    private static final int[] Q;
    private static final int[] R;
    private static final byte[] T;
    private static final int[] U;
    private static final int[] V;
    private static final int[] W;
    private static final byte[] X;
    private static final int[] Y;
    private static final int[] c;
    private static final int[] b;
    private static final byte[] d;
    private static final int[] f;
    private static final int[] j;
    private static final int[] a;
    private static final byte[] e;
    private static final int[] g;
    private static final int[] h;
    private static final int[] k;
    private static final byte[] m;
    private static final int[] n;
    private static final int[] II;
    private static final int[] ZI;
    private static final byte[] CI;
    private static final int[] BI;
    private static final int[] DI;
    private static final int[] FI;
    private static final byte[] JI;
    private static final int[] SI;
    private static final int[] AI;
    private static final int[] z;
    private static final byte[] u;
    private static final int[] r;
    private static final int[] p;
    private static final int[] o;
    private int EI;
    private int GI;
    private int HI;
    private int KI;
    private int LI;
    private int MI;
    private int NI;
    private int OI;
    private int PI;
    private int QI;
    private int RI;
    private int TI;
    private int UI;
    private int VI;
    private int WI;
    private int XI;
    private int YI;
    private int iI;
    private int zI;
    private int cI;
    private int bI;
    private int dI;
    private int fI;
    private int jI;
    private int sI;
    private boolean aI;
    private int eI;
    private boolean gI;
    private int hI;
    private int kI;
    private boolean lI;
    private int mI;
    private int nI;
    private int oI;
    private int pI;
    private int qI;
    private int rI;
    private int tI;
    private int uI;
    private int vI;
    private int wI;
    private int[] xI;
    private int yI;
    private int IZ;
    private int ZZ;
    private int CZ;
    private int BZ;
    private byte[] DZ;
    private byte[] FZ;
    private int JZ;
    private int[][] SZ;
    private int[][] AZ;
    private int[][][][] EZ;
    private boolean[][] GZ;
    private int[][] HZ;
    private boolean[][] KZ;
    private int[][][][] LZ;
    private int[][] MZ;
    private int[][][] NZ;
    private int[] OZ;
    private int[][] PZ;
    private int QZ;
    private int RZ;
    private int TZ;
    private int UZ;
    private int VZ;
    private int WZ;
    private int XZ;
    private int YZ;
    private int iZ;
    private int zZ;
    private int cZ;
    private int bZ;
    private int dZ;
    private int fZ;
    private int jZ;
    private int sZ;
    private int aZ;
    private int eZ;
    private int gZ;
    private int[][] hZ;
    private byte[][] kZ;
    private int[] lZ;
    private int[] mZ;
    private int nZ;
    private int oZ;
    private int pZ;
    private int qZ;
    private int rZ;
    private int tZ;
    private boolean uZ;
    private boolean vZ;
    private int wZ;
    public C xZ;
    private C yZ;
    private C IC;
    private boolean ZC;
    private boolean CC;
    private boolean BC;
    private C DC;
    private int FC;
    private int JC;
    private int SC;
    private int AC;
    private int EC;
    private boolean GC;
    private int HC;
    private int KC;
    private int LC;
    private int MC;
    private final int[] NC;
    private final int[] OC;
    private final int[] PC;
    private final int[] QC;
    private int[] RC;
    private final int[] TC;
    private byte[] UC;
    private byte[] VC;
    private byte[] WC;
    private Point[][][] XC;
    private Point[][] YC;
    private Point[][] iC;
    private Point zC;
    private Point cC;
    private Point bC;
    private int[] dC;
    private int[] fC;
    private int[] jC;
    private int[] sC;
    private int[] aC;
    private int eC;
    private int gC;
    private byte[] hC;
    private int[] kC;
    private int[] lC;
    private int[] mC;
    private int nC;
    private byte[] oC;
    private int[] pC;
    private int[] qC;
    private int[] rC;
    private int tC;
    private byte[] uC;
    private int[] vC;
    private int[] wC;
    private int[] xC;
    private int yC;
    private byte[] IB;
    private int[] ZB;
    private int[] CB;
    private int[] BB;
    private int DB;
    private byte[] FB;
    private int[] JB;
    private int[] SB;
    private int[] AB;
    private int EB;
    private byte[] GB;
    private int[] HB;
    private int[] KB;
    private int[] LB;
    private int MB;
    private byte[] NB;
    private int[] OB;
    private int[] PB;
    private int[] QB;
    private int w;
    private byte[] v;
    private int[] t;
    private int[] s;
    private int[] q;
    private int[] i;
    private byte[] RB;
    public int TB;
    public int UB;
    public int VB;
    private int l;
    public boolean WB;
    public boolean XB;
    private byte[] YB;
    
    private Z() {
        this.xI = new int[] { 13, 15, 17, 19, 21, 23 };
        this.FZ = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, -128, -127, -126, -125, -124, -123, -122, -121, -120, -119, -118, -117, -116, -115, -114, -113, -112, -111, -110, -109, -108, -107, -106, -105, -104, -103, -102, -101, -100, -99, -98, -97, -96, -95, -94, -93, -92, -91, -90, -89, -88, -87, -86, -85, -84, -83, -82, -81, -80, -79, -78, -77, -76, -75, -74, -73, -72, -71, -70, -69, -68, -67, -66, -65, -64, -63, -62, -61, -60, -59, -58, -57, -56, -55, -54, -53, -52, -51, -50, -49, -48, -47, -46, -45, -44, -43, -42, -41, -40, -39, -38, -37, -36, -35, -34, -33, -32, -31, -30, -29, -28, -27, -26, -25, -24, -23, -22, -21, -20, -19, -18, -17, -16, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        this.NC = new int[36];
        this.OC = new int[36];
        this.PC = new int[36];
        this.QC = new int[36];
        this.TC = new int[2048];
        this.mI = 5;
        final boolean wz = true;
        this.rI = (wz ? 1 : 0);
        this.qI = (wz ? 1 : 0);
        this.wZ = (wz ? 1 : 0);
        this.yI = 8;
        this.eI = -1;
        this.DZ = new byte[8];
        this.QZ = 4;
        this.kZ = new byte[6][];
        this.lZ = new int[6];
        final boolean uz = true;
        this.lI = uz;
        this.CC = uz;
        this.uZ = uz;
        this.RC = new int[64];
        this.UC = new byte[289];
        this.VC = new byte[81];
        this.WC = new byte[81];
        this.dC = new int[7];
        this.fC = new int[7];
        final int n = 64;
        this.MB = n;
        this.EB = n;
        this.tC = n;
        this.gC = n;
        final int n2 = 128;
        this.yC = n2;
        this.nC = n2;
        this.DB = 4096;
        this.w = 16;
        this.i = new int[2];
        this.sZ = 9;
        C.Z.notify = C.Z.abs;
        for (int i = 0; i < 64; ++i) {
            this.RC[i] = 0;
        }
        this.hZ = new int[6][64];
        this.mZ = new int[6];
        this.iC = new Point[128][4];
        this.YC = new Point[128][4];
        for (int j = 0; j < 128; ++j) {
            for (int k = 0; k < 4; ++k) {
                this.YC[j][k] = new Point(0, 0);
                this.iC[j][k] = new Point(0, 0);
            }
        }
        this.zC = new Point(0, 0);
        this.cC = new Point(0, 0);
        this.bC = new Point(0, 0);
        this.MZ = new int[128][6];
        this.NZ = new int[128][6][7];
        this.OZ = new int[6];
        this.PZ = new int[6][7];
        this.jC = C.Z.E;
        this.sC = C.Z.H;
        this.aC = C.Z.L;
    }
    
    public Z(final byte[] yb) {
        this();
        this.WB = true;
        this.YB = yb;
    }
    
    private final void abs() {
        this.XC = new Point[this.SC][this.EC][4];
        for (int i = 0; i < this.SC; ++i) {
            for (int j = 0; j < this.EC; ++j) {
                for (int k = 0; k < 4; ++k) {
                    this.XC[i][j][k] = new Point(0, 0);
                }
            }
        }
    }
    
    private static final int[] arraycopy(final int[] array, final int[] array2, final int n) {
        final int[] array3 = new int[n];
        for (int i = 0; i < array[0]; ++i) {
            array3[i] = array2[0];
        }
        int j;
        for (j = 0; j < array.length - 1; ++j) {
            for (int k = array[j]; k < array[j + 1]; ++k) {
                array3[k] = array2[j];
            }
        }
        for (int l = array[j]; l < n; ++l) {
            array3[l] = array2[j];
        }
        return array3;
    }
    
    private static final byte[] length(final int[] array, final byte[] array2, final int n) {
        final byte[] array3 = new byte[n];
        for (int i = 0; i < array[0]; ++i) {
            array3[i] = array2[0];
        }
        int j;
        for (j = 0; j < array.length - 1; ++j) {
            for (int k = array[j]; k < array[j + 1]; ++k) {
                array3[k] = array2[j];
            }
        }
        for (int l = array[j]; l < n; ++l) {
            array3[l] = array2[j];
        }
        return array3;
    }
    
    private final void max() {
        this.hC = length(C.Z.W, C.Z.T, this.gC);
        this.lC = arraycopy(C.Z.W, C.Z.U, this.gC);
        this.mC = arraycopy(C.Z.W, C.Z.V, this.gC);
        this.oC = length(C.Z.b, C.Z.X, this.nC);
        this.qC = arraycopy(C.Z.b, C.Z.Y, this.nC);
        this.rC = arraycopy(C.Z.b, C.Z.c, this.nC);
        this.uC = length(C.Z.a, C.Z.d, this.tC);
        this.wC = arraycopy(C.Z.a, C.Z.f, this.tC);
        this.xC = arraycopy(C.Z.a, C.Z.j, this.tC);
        this.IB = length(C.Z.k, C.Z.e, this.yC);
        this.CB = arraycopy(C.Z.k, C.Z.g, this.yC);
        this.BB = arraycopy(C.Z.k, C.Z.h, this.yC);
        this.FB = length(C.Z.ZI, C.Z.m, this.DB);
        this.SB = arraycopy(C.Z.ZI, C.Z.n, this.DB);
        this.AB = arraycopy(C.Z.ZI, C.Z.II, this.DB);
        this.NB = length(C.Z.z, C.Z.JI, this.MB);
        this.PB = arraycopy(C.Z.z, C.Z.SI, this.MB);
        this.QB = arraycopy(C.Z.z, C.Z.AI, this.MB);
        this.GB = length(C.Z.FI, C.Z.CI, this.EB);
        this.KB = arraycopy(C.Z.FI, C.Z.BI, this.EB);
        this.LB = arraycopy(C.Z.FI, C.Z.DI, this.EB);
        this.v = length(C.Z.o, C.Z.u, this.w);
        this.s = arraycopy(C.Z.o, C.Z.r, this.w);
        this.q = arraycopy(C.Z.o, C.Z.p, this.w);
    }
    
    private final void min(final int n) {
        if (n == 0) {
            this.kC = this.lC;
            this.pC = this.qC;
            this.vC = this.wC;
            this.ZB = this.CB;
            this.JB = this.SB;
            this.OB = this.PB;
            this.HB = this.KB;
            this.t = this.s;
        }
        else if (n == 1) {
            this.kC = this.mC;
            this.pC = this.rC;
            this.vC = this.xC;
            this.ZB = this.BB;
            this.JB = this.AB;
            this.OB = this.QB;
            this.HB = this.LB;
            this.t = this.q;
        }
    }
    
    private final void notify(final int n, final int n2) {
        int n6;
        int n5;
        int n4;
        int n3 = n4 = (n5 = (n6 = 0));
        final int n7 = n / this.SC;
        final int n8 = n2 / this.SC;
        final int n9 = n2 - n8 * this.SC;
        final int n10 = n7 * 16 * this.nZ + (n - n7 * this.SC) * 16;
        int n11;
        if (n7 == n8) {
            n11 = n8 * 16 * this.nZ + n9 * 16 + 15;
        }
        else {
            n11 = (n7 + 1) * 16 * this.nZ - 1;
            if (n8 - n7 > 1) {
                n3 = n8 - n7;
                n4 = (n7 + 1) * 16 * this.nZ;
            }
            n5 = n8 * 16 * this.nZ;
            n6 = n8 * 16 * this.nZ + n9 * 16 + 15;
        }
        for (int i = 0; i < 16; ++i) {
            int n12 = n10 + this.nZ * i;
            for (int j = n10; j <= n11; ++j) {
                this.xZ.Y[n12++] = 16;
            }
        }
        final int n13 = this.nZ >> 1;
        final int n14 = n10 / this.nZ;
        final int n15 = n14 * n13 / 2 + (n10 - n14 * this.nZ) / 2;
        final int n16 = n11 / this.nZ;
        final int n17 = n16 * n13 / 2 + (n11 - n16 * this.nZ) / 2;
        for (int k = 0; k < 8; ++k) {
            int n18 = n15 + n13 * k;
            for (int l = n15; l <= n17; ++l) {
                this.xZ.U[n18] = -128;
                this.xZ.V[n18++] = -128;
            }
        }
        if (n3 > 0) {
            final int n19 = n3 * this.nZ * 16;
            int n20 = n4;
            for (int n21 = 0; n21 < n19; ++n21) {
                this.xZ.Y[n20++] = 16;
            }
            final int n22 = n19 >> 2;
            int n23 = n4 >> 2;
            for (int n24 = 0; n24 < n22; ++n24) {
                this.xZ.U[n23] = -128;
                this.xZ.V[n23++] = -128;
            }
        }
        for (int n25 = 0; n25 < 16; ++n25) {
            int n26 = n5 + this.nZ * n25;
            for (int n27 = n5; n27 <= n6; ++n27) {
                this.xZ.Y[n26++] = 16;
            }
        }
        final int n28 = n5 / this.nZ;
        final int n29 = n28 * n13 / 2 + (n5 - n28 * this.nZ) / 2;
        final int n30 = n5 / this.nZ;
        final int n31 = n30 * n13 / 2 + (n6 - n30 * this.nZ) / 2;
        for (int n32 = 0; n32 < 8; ++n32) {
            int n33 = n29 + n13 * n32;
            for (int n34 = n29; n34 <= n31; ++n34) {
                this.xZ.U[n33] = -128;
                this.xZ.V[n33++] = -128;
            }
        }
    }
    
    private final void printStackTrace(final int n, final int n2) {
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        final int n7 = n / this.SC;
        final int n8 = n2 / this.SC;
        final int n9 = n7 * 16 * this.nZ + (n - n7 * this.SC) * 16;
        int n10;
        if (n7 == n8) {
            n10 = n8 * 16 * this.nZ + (n2 - n8 * this.SC) * 16 + 15;
        }
        else {
            n10 = (n7 + 1) * 16 * this.nZ - 1;
            if (n8 - n7 > 1) {
                n4 = n8 - n7;
                n3 = (n7 + 1) * 16 * this.nZ;
            }
            n5 = n8 * 16 * this.nZ;
            n6 = n8 * 16 * this.nZ + (n2 - n8 * this.SC) * 16 + 15;
        }
        for (int i = 0; i < 16; ++i) {
            int n11 = n9 + this.nZ * i;
            for (int j = n9; j <= n10; ++j) {
                this.xZ.Y[n11] = this.DC.Y[n11];
                ++n11;
            }
        }
        final int n12 = this.nZ >> 1;
        final int n13 = n9 / this.nZ;
        final int n14 = n13 * n12 / 2 + (n9 - n13 * this.nZ) / 2;
        final int n15 = n10 / this.nZ;
        final int n16 = n15 * n12 / 2 + (n10 - n15 * this.nZ) / 2;
        for (int k = 0; k < 8; ++k) {
            int n17 = n14 + n12 * k;
            for (int l = n14; l <= n16; ++l) {
                this.xZ.U[n17] = this.DC.U[n17];
                this.xZ.V[n17] = this.DC.V[n17];
                ++n17;
            }
        }
        if (n4 > 0) {
            final int n18 = n4 * this.nZ * 16;
            int n19 = n3;
            for (int n20 = 0; n20 < n18; ++n20) {
                this.xZ.Y[n19] = this.DC.Y[n19];
                ++n19;
            }
            final int n21 = n18 >> 2;
            int n22 = n3 >> 2;
            for (int n23 = 0; n23 < n21; ++n23) {
                this.xZ.U[n22] = this.DC.U[n22];
                this.xZ.V[n22] = this.DC.V[n22];
                ++n22;
            }
        }
        for (int n24 = 0; n24 < 16; ++n24) {
            int n25 = n5 + this.nZ * n24;
            for (int n26 = n5; n26 <= n6; ++n26) {
                this.xZ.Y[n25] = this.DC.Y[n25];
                ++n25;
            }
        }
        final int n27 = n5 / this.nZ;
        final int n28 = n27 * n12 / 2 + (n5 - n27 * this.nZ) / 2;
        final int n29 = n6 / this.nZ;
        final int n30 = n29 * n12 / 2 + (n6 - this.nZ * n29) / 2;
        for (int n31 = 0; n31 < 8; ++n31) {
            int n32 = n28 + n12 * n31;
            for (int n33 = n28; n33 <= n30; ++n33) {
                this.xZ.U[n32] = this.DC.U[n32];
                this.xZ.V[n32] = this.DC.V[n32];
                ++n32;
            }
        }
    }
    
    private final void toBinaryString(final int n, final int n2) {
        this.gZ = n / this.SC - 1;
        this.AC = n - (this.gZ + 1) * this.SC;
        if (this.AC != 0) {
            ++this.gZ;
            this.HC = this.NC[this.gZ] + (this.AC - 1) * 16;
            this.KC = this.OC[this.gZ] + (this.AC - 1) * 8;
            this.LC = this.PC[this.gZ] + (this.AC - 1) * 16;
            this.MC = this.QC[this.gZ] + (this.AC - 1) * 8;
        }
        for (int i = n; i <= n2; ++i) {
            this.AC = i - i / this.SC * this.SC;
            if (this.AC == 0) {
                ++this.gZ;
                this.HC = this.NC[this.gZ];
                this.KC = this.OC[this.gZ];
                this.LC = this.PC[this.gZ];
                this.MC = this.QC[this.gZ];
            }
            else {
                this.HC += 16;
                this.KC += 8;
                this.LC += 16;
                this.MC += 8;
            }
            this.YC[this.AC][0].x = this.XC[this.AC][this.gZ][0].x + this.XC[this.AC][this.gZ][1].x + this.XC[this.AC][this.gZ][2].x + this.XC[this.AC][this.gZ][3].x >> 2;
            this.YC[this.AC][0].y = this.XC[this.AC][this.gZ][0].y + this.XC[this.AC][this.gZ][1].y + this.XC[this.AC][this.gZ][2].y + this.XC[this.AC][this.gZ][3].y >> 2;
            this.f();
            final Point point = this.XC[this.AC][this.gZ][0];
            final Point point2 = this.XC[this.AC][this.gZ][1];
            final Point point3 = this.XC[this.AC][this.gZ][2];
            final Point point4 = this.XC[this.AC][this.gZ][3];
            final Point point5 = this.XC[this.AC][this.gZ][0];
            final Point point6 = this.XC[this.AC][this.gZ][1];
            final Point point7 = this.XC[this.AC][this.gZ][2];
            final Point point8 = this.XC[this.AC][this.gZ][3];
            final boolean b = false;
            point8.y = (b ? 1 : 0);
            point7.y = (b ? 1 : 0);
            point6.y = (b ? 1 : 0);
            point5.y = (b ? 1 : 0);
            point4.x = (b ? 1 : 0);
            point3.x = (b ? 1 : 0);
            point2.x = (b ? 1 : 0);
            point.x = (b ? 1 : 0);
        }
    }
    
    private final int wait(final int sz) {
        while (true) {
            switch (sz) {
                default: {
                    return 2;
                }
                case 6: {
                    if (this.gI) {
                        this.EC = (this.rZ + 15) / 16;
                        this.tZ = this.rZ;
                        this.rZ = this.EC * 16;
                    }
                    else {
                        this.EC = this.rZ >> 4;
                        this.tZ = this.EC << 4;
                    }
                    if (this.gI) {
                        this.SC = (this.nZ + 15) / 16;
                        this.oZ = this.nZ;
                        this.nZ = this.SC * 16;
                    }
                    else {
                        this.SC = this.nZ >> 4;
                        this.oZ = this.SC << 4;
                    }
                    for (int i = 0; i < this.EC; ++i) {
                        this.NC[i] = (this.nZ << 4) * i;
                        this.OC[i] = (this.nZ << 2) * i;
                        this.PC[i] = 16 + (32 + this.nZ << 4) * (i + 1);
                        this.QC[i] = 8 + (16 + (this.nZ >> 1) << 3) * (i + 1);
                    }
                    this.pZ = this.nZ >> 1;
                    this.qZ = this.nZ << 3;
                    if (!this.ZC) {
                        this.xZ = new C(this.nZ, this.rZ);
                        this.DC = new C(this.nZ, this.rZ);
                    }
                    final int[] mz = this.mZ;
                    final int n = 0;
                    final int[] mz2 = this.mZ;
                    final int n2 = 1;
                    final int[] mz3 = this.mZ;
                    final int n3 = 2;
                    final int[] mz4 = this.mZ;
                    final int n4 = 3;
                    final int nz = this.nZ;
                    mz3[n3] = (mz4[n4] = nz);
                    mz[n] = (mz2[n2] = nz);
                    this.mZ[4] = (this.mZ[5] = this.pZ);
                    this.sZ = sz;
                    return 0;
                }
                case 1: {
                    this.nZ = 128;
                    this.rZ = 96;
                    continue;
                }
                case 2: {
                    this.nZ = 176;
                    this.rZ = 144;
                    continue;
                }
                case 3: {
                    this.nZ = 352;
                    this.rZ = 288;
                    continue;
                }
            }
            break;
        }
    }
    
    public final int I() {
        if (this.gI) {
            return this.kI;
        }
        return 30000;
    }
    
    private final int x(final byte[] rb, final int n, final int tb) {
        final int q = q(rb, n);
        p(rb, n);
        o(rb, n);
        n(rb, n);
        if (q == 0) {
            this.RB = rb;
            final int n2 = 4 + n;
            this.VB = n2;
            this.UB = n2;
            this.TB = tb;
            this.m();
            int i = 1;
            while (this.H(31) != 0) {
                try {
                    if (!this.BC && 3 == this.toBinaryString()) {
                        return 3;
                    }
                    if (i == 0) {
                        continue;
                    }
                    i = this.I(false);
                    continue;
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    return 2;
                }
                break;
            }
            return i;
        }
        return 2;
    }
    
    private final void y() {
        if (this.G(28) != 18) {
            throw new Exception();
        }
        this.K(4);
        if (this.G(1) != 0) {
            throw new Exception();
        }
        if (this.G(8) >= 5) {
            throw new Exception();
        }
        if (this.G(1) == 1) {
            if (this.lI) {
                this.wZ = this.G(4);
            }
            else if (this.G(4) != this.wZ) {
                throw new Exception();
            }
            this.K(3);
        }
        if (this.G(4) == 15) {
            this.K(16);
        }
        if (this.G(1) == 1) {
            if (this.G(2) != 1) {
                throw new Exception();
            }
            this.K(1);
            if (this.G(1) == 1) {
                this.K(15);
                if (this.G(1) != 1) {
                    throw new Exception();
                }
                this.K(15);
                if (this.G(1) != 1) {
                    throw new Exception();
                }
                this.K(15);
                if (this.G(1) != 1) {
                    throw new Exception();
                }
                this.K(14);
                if (this.G(1) != 1) {
                    throw new Exception();
                }
                this.K(15);
                if (this.G(1) != 1) {
                    throw new Exception();
                }
            }
        }
        this.nI = this.G(2);
        if (this.nI != 0) {
            throw new Exception();
        }
        if (this.G(1) != 1) {
            throw new Exception();
        }
        if (this.lI) {
            this.kI = this.G(16);
        }
        else if (this.G(16) != this.kI) {
            throw new Exception();
        }
        if (this.G(1) != 1) {
            throw new Exception();
        }
        if (this.G(1) == 1) {
            this.K(Integer.toBinaryString(this.kI - 1).length());
        }
        if (this.lI) {
            this.VZ = 0;
            this.WZ = 0;
            this.XZ = 0;
        }
        if (this.nI == 2) {
            throw new Exception();
        }
        if (this.nI == 0) {
            if (this.G(1) != 1) {
                throw new Exception();
            }
            if (this.lI) {
                this.nZ = this.G(13);
            }
            else if (this.G(13) != this.nZ) {
                throw new Exception();
            }
            if (this.G(1) != 1) {
                throw new Exception();
            }
            if (this.lI) {
                this.rZ = this.G(13);
            }
            else {
                this.K(13);
            }
            if (this.G(1) != 1) {
                throw new Exception();
            }
        }
        this.pI = this.G(1);
        if (this.pI == 1) {
            throw new Exception();
        }
        if (this.G(1) != 1) {
            throw new Exception();
        }
        if (this.wZ == 1) {
            this.hI = this.G(1);
        }
        else {
            this.hI = this.G(2);
        }
        if (this.hI != 0) {
            throw new Exception();
        }
        if (this.nI != 0 && this.wZ != 1) {
            this.G(1);
        }
        if (this.G(1) == 1) {
            throw new Exception();
        }
        if (this.nI == 3) {
            this.K(3);
            throw new Exception();
        }
        if (this.G(1) == 1) {
            throw new Exception();
        }
        if (this.wZ != 1 && this.G(1) == 1) {
            throw new Exception();
        }
        if (this.lI) {
            this.oI = this.G(1);
        }
        else if (this.G(1) != this.oI) {
            throw new Exception();
        }
        if (this.oI == 0) {
            this.arraycopy();
        }
        if (this.lI) {
            this.YZ = this.G(1);
        }
        else if (this.G(1) != this.YZ) {
            throw new Exception();
        }
        this.eC = 0;
        if (this.lI) {
            this.tI = this.G(1);
        }
        else if (this.G(1) != this.tI) {
            throw new Exception();
        }
        if (this.tI == 1) {
            if (this.lI) {
                this.eC = this.G(1);
            }
            else if (this.G(1) != this.eC) {
                throw new Exception();
            }
            if (this.lI) {
                if (this.eC == 1) {
                    this.max();
                }
                this.SZ = new int[128][128];
                this.AZ = new int[128][128];
                this.EZ = new int[128][128][6][2];
                this.GZ = new boolean[128][128];
                this.HZ = new int[128][128];
                this.LZ = new int[128][128][4][2];
            }
        }
        if (this.wZ != 1) {
            if (this.G(1) == 1) {
                throw new Exception();
            }
            if (this.G(1) == 1) {
                throw new Exception();
            }
        }
        if (this.lI) {
            this.EI = this.G(1);
        }
        else if (this.G(1) != this.EI) {
            throw new Exception();
        }
        if (this.lI) {
            this.wait(6);
        }
        this.lI = false;
    }
    
    private final void arraycopy() {
        this.LI = this.G(2);
        if (this.LI == 3 || this.LI == 2) {
            return;
        }
        if (this.G(1) == 0) {
            this.MI = this.G(1);
            this.NI = this.G(1);
            this.OI = this.G(1);
            this.PI = this.G(1);
            this.QI = this.G(1);
            this.RI = this.G(1);
        }
        if (this.G(1) == 0) {
            this.TI = this.G(1);
            this.UI = this.G(1);
            this.VI = this.G(1);
            this.WI = this.G(1);
        }
        if (this.G(1) != 1) {
            throw new Exception();
        }
        if (this.G(1) == 0) {
            this.XI = this.G(1);
            this.YI = this.G(1);
            this.iI = this.G(1);
            this.zI = this.G(1);
        }
        if (this.G(1) == 0) {
            this.cI = this.G(1);
            this.bI = this.G(1);
            this.dI = this.G(1);
            this.fI = this.G(1);
            this.jI = this.G(1);
            this.sI = this.G(1);
        }
        if (this.G(1) != 1) {
            throw new Exception();
        }
        if (this.LI == 1 && this.G(1) == 0) {
            this.K(2);
        }
    }
    
    private final void length() {
        int n = 0;
        if (this.G(1) != 0) {
            throw new Exception();
        }
        while ((this.l & 0x7) != 0x0) {
            ++n;
            if (this.G(1) != 1) {
                throw new Exception();
            }
        }
    }
    
    private final int max(final int n) {
        if (n > 26) {
            throw new Exception();
        }
        final int n2 = this.l & 0x7;
        int n3;
        if (n2 == 0) {
            if ((this.H(8) & 0x7F) != 0x7F) {
                return this.H(n);
            }
            n3 = this.H(8 + n);
        }
        else {
            n3 = this.H(n2 + n);
        }
        return n3 << 32 - n >>> 32 - n;
    }
    
    private final int min() {
        if (this.bZ == 0 || this.nI == 2) {
            return 17;
        }
        if (this.bZ == 1 || this.bZ == 3) {
            return this.qI + 16;
        }
        return Math.max(this.qI, this.rI) + 16;
    }
    
    private final void printStackTrace() {
        if ((this.G(16) << 16 | (this.G(16) & 0xFFFF)) != 0x1B2) {
            throw new Exception();
        }
        while (this.H(24) != 1) {
            this.G(8);
        }
        if (this.wZ == 1) {
            this.length();
        }
    }
    
    private final int toBinaryString() {
        while (this.H(17) != 1) {
            this.K(1);
        }
        final int h = this.H(22);
        if (h != 32 && h != 63 && !this.BC) {
            return 3;
        }
        this.K(22);
        if (h == 32) {
            this.BC = true;
        }
        else if (h == 63) {
            return 0;
        }
        if (h == 32) {
            this.BC = true;
            final int g = this.G(8);
            this.cZ = this.G(8);
            this.zZ = (this.cZ & 0x7);
            if (this.zZ == 7) {
                final int u = this.U();
                if (u == 2) {
                    return u;
                }
            }
            else {
                this.iZ = this.zZ;
                this.cZ = this.G(5);
                if ((this.cZ & 0x7) != 0x0) {
                    return 2;
                }
                this.bZ = (this.cZ >> 4 & 0x1);
                this.fZ = (this.cZ & 0x8) >> 3;
            }
            this.eZ = this.G(5);
            if (this.zZ != 7) {
                this.aZ = this.G(1);
                if (this.aZ != 0) {
                    this.G(2);
                }
            }
            int i;
            do {
                i = this.G(1);
                if (i == 1) {
                    this.K(8);
                }
            } while (i == 1);
            if (this.iZ != this.sZ) {
                final int wait = this.wait(this.iZ);
                if (wait == 2) {
                    return wait;
                }
            }
            if (this.XC == null) {
                this.abs();
                this.KZ = new boolean[128][128];
            }
            this.UZ = this.TZ;
            this.TZ = g;
            if (this.TZ >= this.UZ) {
                this.xZ.Z = (this.TZ - this.UZ) * 1001;
            }
            else {
                this.xZ.Z = (this.TZ + 256 - this.UZ) * 1001;
            }
            if (this.xZ.Z < 30000) {
                this.xZ.I = 0;
            }
            else {
                this.xZ.I = this.xZ.Z / 30000;
                this.xZ.Z %= 30000;
            }
            this.x();
        }
        return 1;
    }
    
    private final void x() {
        if (!this.ZC) {
            final C xz = this.xZ;
            this.xZ = this.DC;
            this.DC = xz;
        }
        if (this.bZ == 2 && this.EI == 0) {
            this.xZ = this.IC;
            this.DC = this.yZ;
        }
        final byte[][] kz = this.kZ;
        final int n = 0;
        final byte[][] kz2 = this.kZ;
        final int n2 = 1;
        final byte[][] kz3 = this.kZ;
        final int n3 = 2;
        final byte[][] kz4 = this.kZ;
        final int n4 = 3;
        final byte[] y = this.xZ.Y;
        kz3[n3] = (kz4[n4] = y);
        kz[n] = (kz2[n2] = y);
        this.kZ[4] = this.xZ.V;
        this.kZ[5] = this.xZ.U;
        if (this.nZ != this.oZ || this.rZ != this.tZ) {
            I(this.DC.Y, this.nZ, this.rZ, this.oZ, this.tZ);
            I(this.DC.V, this.pZ, this.rZ >> 1, this.oZ >> 1, this.tZ >> 1);
            I(this.DC.U, this.pZ, this.rZ >> 1, this.oZ >> 1, this.tZ >> 1);
        }
        final boolean gz = false;
        this.uI = (gz ? 1 : 0);
        this.FC = (gz ? 1 : 0);
        this.gZ = (gz ? 1 : 0);
    }
    
    private final void Z() {
        this.CC = false;
        if ((this.G(16) << 16 | (this.G(16) & 0xFFFF)) != 0x1B6) {
            throw new Exception();
        }
        this.bZ = this.G(2);
        this.x();
        if (this.eC == 1) {
            this.dZ = this.bZ;
            this.min(this.bZ);
        }
        int n = 0;
        int i;
        do {
            i = this.G(1);
            n += i;
        } while (i != 0);
        if (this.G(1) != 1) {
            throw new Exception();
        }
        final int g = this.G(Integer.toBinaryString(this.kI - 1).length());
        if (!this.ZC) {
            this.VZ += n;
            this.xZ.I = this.VZ - this.WZ;
            if (g >= this.XZ) {
                this.xZ.Z = g - this.XZ;
            }
            else {
                final C xz = this.xZ;
                --xz.I;
                this.xZ.Z = this.kI - this.XZ + g;
            }
            this.WZ = this.VZ;
            this.XZ = g;
        }
        if (this.G(1) != 1) {
            throw new Exception();
        }
        if (this.G(1) == 0) {
            this.c();
            this.length();
            this.CC = true;
            return;
        }
        if (this.nI != 2 && this.bZ == 1) {
            this.RZ = this.G(1);
        }
        if (this.nI != 0) {
            if (this.hI != 1 || this.bZ != 0) {
                this.K(13);
                if (this.G(1) != 1) {
                    throw new Exception();
                }
                this.K(13);
                if (this.G(1) != 1) {
                    throw new Exception();
                }
                this.K(13);
                if (this.G(1) != 1) {
                    throw new Exception();
                }
                this.K(13);
            }
            if (this.nI != 2 && this.EI == 1 && this.HI == 1) {
                this.G(1);
            }
            this.G(1);
            if (this.G(1) == 1) {
                this.G(8);
            }
        }
        if (this.oI == 0) {
            this.b();
        }
        if (this.nI != 2) {
            this.vI = this.G(3);
            if (this.pI == 1) {
                this.K(2);
            }
        }
        if (this.hI == 1 && this.bZ == 3) {
            throw new Exception();
        }
        if (this.nI == 2) {
            throw new Exception();
        }
        final int g2 = this.G(this.mI);
        this.eZ = g2;
        this.wI = g2;
        if (this.nI == 3) {
            throw new Exception();
        }
        if (this.bZ != 0) {
            this.qI = this.G(3);
        }
        if (this.bZ == 2) {
            this.rI = this.G(3);
        }
        if (this.EI == 0 && this.nI != 0 && this.bZ != 0) {
            this.G(1);
        }
        this.GC = true;
        if (this.EI == 0) {
            this.S();
            this.length();
            while (!this.T() && this.F()) {
                this.J();
                this.S();
                this.length();
            }
        }
        else {
            this.CC = true;
        }
    }
    
    private final boolean C() {
        return this.vI == 0 || (this.vI != 7 && this.wI < this.xI[this.vI - 1]);
    }
    
    private final int B() {
        return Integer.toBinaryString((this.nZ + 15) / 16 * ((this.rZ + 15) / 16)).length();
    }
    
    private final boolean D() {
        this.min();
        if (!this.gI || this.max(24) != 0) {
            return false;
        }
        final int n = this.l & 0x7;
        if (n != 0) {
            return this.H(n) == (1 << n - 1) - 1;
        }
        return this.H(8) == 127;
    }
    
    private final boolean F() {
        if (this.gI && this.max(this.min()) == 1) {
            final int n = this.l & 0x7;
            return n == 0 || this.H(n) == (1 << n - 1) - 1;
        }
        return false;
    }
    
    private final void J() {
        if (this.G(this.min()) != 1) {
            throw new Exception();
        }
        this.uI = this.G(this.B());
        if (this.nI != 2) {
            this.eZ = this.G(this.mI);
            this.wI = this.eZ;
        }
        this.CZ = this.G(1);
        if (this.CZ == 1) {
            int n = 0;
            int i;
            do {
                i = this.G(1);
                n += i;
            } while (i != 0);
            if (this.G(1) != 1) {
                throw new Exception();
            }
            this.K(Integer.toBinaryString(this.kI - 1).length());
            if (this.G(1) != 1) {
                throw new Exception();
            }
            final int g = this.G(2);
            if (this.bZ != g && this.eC == 1) {
                this.dZ = this.bZ;
                this.min(this.bZ);
            }
            this.bZ = g;
            if (this.nI != 2) {
                if (this.G(3) != this.vI) {
                    throw new Exception();
                }
                if (g != 0 && this.qI != this.G(3)) {
                    throw new Exception();
                }
                if (g == 2) {
                    this.G(3);
                }
            }
        }
    }
    
    private final void S() {
        if (this.tI == 1) {
            this.A();
        }
        else {
            this.H();
        }
    }
    
    private final void A() {
        if (this.bZ == 0 || this.bZ == 1) {
            this.E();
        }
        else if (this.bZ != 2) {
            throw new Exception();
        }
    }
    
    private final void E() {
        int n = this.uI / this.SC;
        int n2 = this.uI - n * this.SC;
        final int ez = this.eZ;
        this.IZ = 0;
        final int n3 = (this.bZ != 0) ? 126977 : 438273;
        final byte b = (byte)((this.bZ != 0) ? 17 : 19);
        final int n4 = 1 << this.qI - 1;
        while (this.nI == 0) {
            boolean b2 = false;
            if (this.bZ == 1) {
                b2 = (this.G(1) == 1);
            }
            Label_0394: {
                if (!(this.KZ[n][n2] = b2)) {
                    final int n5 = (this.bZ != 0) ? this.i() : this.X();
                    if (n5 == -1) {
                        break Label_0394;
                    }
                    this.SZ[n][n2] = n5;
                    final int n6 = n5 >> 4;
                    if (n6 < 3) {
                        for (byte b3 = (byte)((n6 != 2) ? 1 : 4), b4 = 0; b4 < b3; ++b4) {
                            this.LZ[n][n2][b4][0] = this.S(n4);
                            this.LZ[n][n2][b4][1] = this.A(n4);
                        }
                    }
                    if (this.bZ == 0) {
                        if (n6 == 4) {
                            if (this.IZ != 0) {
                                this.wI = this.eZ;
                            }
                            final int g = this.G(2);
                            this.AZ[n][n2] = g;
                            this.eZ = C.Z.min[this.eZ + C.Z.length[g]];
                            if (this.IZ == 0) {
                                this.wI = this.eZ;
                            }
                        }
                        else if (this.IZ != 0) {
                            this.wI = this.eZ;
                        }
                        if (this.C()) {
                            this.I(n, n2);
                        }
                    }
                }
                if (n2 == this.SC - 1) {
                    ++n;
                    n2 = 0;
                }
                else {
                    ++n2;
                }
                ++this.IZ;
            }
            if (this.H(b) == n3) {
                this.K(b);
                int n7 = this.uI / this.SC;
                int n8 = this.uI - n7 * this.SC;
                for (int i = 0; i < this.IZ; ++i) {
                    final int n9 = this.SZ[n7][n8] >> 4;
                    if (!this.KZ[n7][n8]) {
                        if (n9 >= 3) {
                            this.GZ[n7][n8] = (this.G(1) == 1);
                        }
                        int y = this.Y();
                        if (n9 >= 3) {
                            y >>= 4;
                        }
                        this.HZ[n7][n8] = y;
                        if (this.bZ == 1) {
                            if (n9 == 1 || n9 == 4) {
                                if (this.IZ != 0) {
                                    this.wI = this.eZ;
                                }
                                final int g2 = this.G(2);
                                this.AZ[n7][n8] = g2;
                                this.eZ = C.Z.min[this.eZ + C.Z.length[g2]];
                                if (this.IZ == 0) {
                                    this.wI = this.eZ;
                                }
                            }
                            else if (this.IZ != 0) {
                                this.wI = this.eZ;
                            }
                            if (n9 >= 3 && this.C()) {
                                this.I(n7, n8);
                            }
                        }
                    }
                    if (n8 == this.SC - 1) {
                        ++n7;
                        n8 = 0;
                    }
                    else {
                        ++n8;
                    }
                }
                this.eZ = ez;
                do {
                    this.P();
                } while (this.FC - this.uI != this.IZ);
                return;
            }
        }
        throw new Exception();
    }
    
    private final void I(final int n, final int n2) {
        for (int i = 0; i < 6; ++i) {
            if (i < 4) {
                this.EZ[n][n2][i][0] = this.L();
            }
            else {
                this.EZ[n][n2][i][0] = this.K();
            }
            if (this.EZ[n][n2][i][0] != 0) {
                this.EZ[n][n2][i][1] = this.G(this.EZ[n][n2][i][0]);
                if (this.EZ[n][n2][i][0] > 8) {
                    if (this.G(1) != 1) {
                        throw new Exception();
                    }
                }
            }
            else {
                this.EZ[n][n2][i][1] = 0;
            }
        }
    }
    
    private final boolean G() {
        for (int n = this.JC + this.SC - 1, i = this.FC - this.JC; i <= n; ++i) {
            if (!this.KZ[this.gZ][i]) {
                return false;
            }
        }
        if (this.gZ + 1 == this.EC) {
            return true;
        }
        for (int j = this.gZ + 1; j < this.EC; ++j) {
            for (int k = 0; k < this.SC; ++k) {
                if (!this.KZ[j][k]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private final void H() {
        do {
            this.P();
        } while ((!this.D() || (this.bZ == 2 && (this.GI == 0 || (this.EI == 1 && this.KI == 3)) && !this.G())) && this.FC < this.EC * this.SC && !this.F());
        if (this.tI == 0) {
            if (this.bZ == 0) {
                for (int i = this.H(9); i == 1; i = this.H(9)) {
                    this.K(9);
                }
            }
            else if (this.bZ == 1) {
                for (int j = this.H(1); j == 1; j = this.H(1)) {
                    this.K(1);
                }
                for (int k = this.H(10); k == 1; k = this.H(10)) {
                    this.K(10);
                }
            }
        }
    }
    
    private final int K() {
        if (this.G(1) == 0) {
            int n = 2;
            while (this.G(1) == 0) {
                ++n;
            }
            return n;
        }
        return (this.G(1) != 1) ? 1 : 0;
    }
    
    private final int L() {
        if (this.G(1) != 0) {
            return (this.G(1) != 1) ? 2 : 1;
        }
        if (this.G(1) == 1) {
            return (this.G(1) != 1) ? 3 : 0;
        }
        int n = 4;
        while (this.G(1) == 0) {
            ++n;
        }
        return n;
    }
    
    private final void M() {
        if (this.G(1) == 1) {
            this.K(4);
            if (this.G(1) == 1) {
                this.K(8);
                this.K(8);
                this.K(8);
            }
        }
    }
    
    private final void N() {
        if (this.H(32) == 432) {
            this.K(16);
            this.K(16);
            this.K(8);
            while (this.H(32) == 434) {
                this.printStackTrace();
            }
            if (this.H(32) != 437) {
                throw new Exception();
            }
            this.K(16);
            this.K(16);
            if (this.G(1) == 1) {
                this.K(7);
            }
            if (this.G(4) != 1) {
                throw new Exception();
            }
            this.M();
            this.length();
            while (this.H(32) == 434) {
                this.printStackTrace();
            }
        }
    }
    
    public final boolean I(final byte[] rb, final int n, final int tb) {
        this.RB = rb;
        this.VB = n;
        this.UB = n;
        this.TB = tb;
        this.m();
        this.N();
        final int h = this.H(32);
        if (h >= 256 && h <= 287) {
            final int n2 = this.G(16) << 16 | (this.G(16) & 0xFFFF);
            this.gI = true;
            this.y();
            this.length();
            this.abs();
            this.KZ = new boolean[128][128];
            while (434 == this.H(32)) {
                this.printStackTrace();
            }
            return true;
        }
        return false;
    }
    
    public final int Z(final byte[] rb, final int n, final int tb) {
        this.RB = rb;
        this.VB = n;
        this.UB = n;
        this.TB = tb;
        this.m();
        ++this.eI;
        if (this.gI) {
            try {
                final int h = this.H(32);
                if (h >= 256 && h <= 287) {
                    final int n2 = this.G(16) << 16 | (this.G(16) & 0xFFFF);
                    this.y();
                    this.length();
                    while (this.H(32) == 434) {
                        this.printStackTrace();
                    }
                }
                if (this.H(32) == 435) {
                    this.O();
                }
                if (this.H(32) == 438) {
                    if (!this.CC) {
                        if (this.ZZ != 0) {
                            if (this.ZZ == 2) {
                                this.toBinaryString(this.FC, this.EC * this.SC - 1);
                            }
                            else {
                                this.printStackTrace(this.FC, this.EC * this.SC - 1);
                            }
                        }
                        else {
                            this.notify(this.FC, this.EC * this.SC - 1);
                        }
                        this.CC = true;
                        this.BC = false;
                        final boolean gz = false;
                        this.uI = (gz ? 1 : 0);
                        this.FC = (gz ? 1 : 0);
                        this.gZ = (gz ? 1 : 0);
                        return 5;
                    }
                    this.Z();
                }
                else {
                    while (!this.T() && this.F()) {
                        this.J();
                        if (this.FC == 0) {
                            if (this.CZ == 0) {
                                return 1;
                            }
                            final int ui = this.uI;
                            this.x();
                            this.uI = ui;
                        }
                        if (this.uI > this.FC) {
                            if (this.ZZ != 0) {
                                if (this.ZZ == 2) {
                                    this.toBinaryString(this.FC, this.uI - 1);
                                }
                                else {
                                    this.printStackTrace(this.FC, this.uI - 1);
                                }
                            }
                            else {
                                this.notify(this.FC, this.uI - 1);
                            }
                            this.FC = this.uI;
                            this.gZ = this.FC / this.SC;
                            final int n3 = this.FC - this.gZ * this.SC;
                            this.HC = this.NC[this.gZ] + (n3 << 4);
                            this.KC = this.OC[this.gZ] + (n3 << 3);
                            this.LC = this.PC[this.gZ] + (n3 << 4);
                            this.MC = this.QC[this.gZ] + (n3 << 3);
                        }
                        if (this.uI < this.FC) {
                            if (this.ZZ != 0) {
                                if (this.ZZ == 2) {
                                    this.toBinaryString(this.FC, this.EC * this.SC - 1);
                                }
                                else {
                                    this.printStackTrace(this.FC, this.EC * this.SC - 1);
                                }
                            }
                            else {
                                this.notify(this.FC, this.EC * this.SC - 1);
                            }
                            this.CC = true;
                            this.BC = false;
                            final boolean gz2 = false;
                            this.uI = (gz2 ? 1 : 0);
                            this.FC = (gz2 ? 1 : 0);
                            this.gZ = (gz2 ? 1 : 0);
                            return 5;
                        }
                        this.S();
                        this.length();
                    }
                }
                if (this.CC) {
                    return 0;
                }
                return 1;
            }
            catch (Exception ex) {
                return 4;
            }
        }
        if (this.uZ) {
            if (this.H(22) == 32) {
                this.vZ = false;
            }
            else {
                this.vZ = true;
            }
            this.uZ = false;
        }
        try {
            if (this.vZ) {
                return this.x(rb, n, this.TB);
            }
            return this.I(true);
        }
        catch (Exception ex2) {
            return 4;
        }
    }
    
    private final void O() {
        if ((this.G(16) << 16 | (this.G(16) & 0xFFFF)) != 0x1B3) {
            throw new Exception();
        }
        this.K(20);
        this.length();
        while (this.H(32) == 434) {
            this.printStackTrace();
        }
    }
    
    private final void P() {
        this.JC = this.gZ * this.SC;
        if (this.bZ == 0) {
            this.V();
        }
        else if (this.bZ == 1) {
            this.W();
        }
    }
    
    private final int I(final boolean b) {
        if (!b) {
            this.R();
            this.P();
            this.Q();
            return this.T() ? 0 : 1;
        }
        this.CC = false;
        final int binaryString = this.toBinaryString();
        if (binaryString != 1) {
            return binaryString;
        }
        this.GC = true;
        this.P();
        while (!this.T()) {
            this.GC = false;
            this.R();
            this.P();
        }
        return 0;
    }
    
    private final void Q() {
        if (this.H(22) == 63) {
            this.K(22);
            return;
        }
        final int n = this.l & 0x7;
        if (this.H(n) != 0) {
            return;
        }
        if (this.H(n + 22) == 63) {
            this.K(n + 22);
        }
    }
    
    private final void R() {
        if (this.r()) {
            final int h = this.H(22);
            if (h >= 33 && h <= 62) {
                this.K(22);
                this.BC = true;
                this.GC = true;
                final int gz = h & 0x1F;
                if (gz != this.gZ) {
                    this.gZ = gz;
                    this.FC = this.gZ * this.SC;
                }
                if (this.aZ != 0) {
                    this.K(2);
                }
                this.G(2);
                this.eZ = this.G(5);
            }
        }
    }
    
    private final boolean T() {
        if (this.FC == this.EC * this.SC) {
            this.CC = true;
            this.BC = false;
            if (this.jZ == 1) {
                this.I(this.xZ.Y, 0);
                this.Z(this.xZ.Y, 0);
                this.I(this.xZ.V, 1);
                this.Z(this.xZ.V, 1);
                this.I(this.xZ.U, 1);
                this.Z(this.xZ.U, 1);
            }
            return true;
        }
        return this.CC = false;
    }
    
    private final int U() {
        final int g = this.G(3);
        if (g == 1) {
            this.iZ = this.G(3);
            this.G(1);
            this.fZ = this.G(1);
            this.jZ = (this.G(4) & 0x1);
            this.G(9);
        }
        this.bZ = this.G(3);
        this.K(6);
        this.aZ = this.G(1);
        if (this.aZ != 0) {
            this.G(2);
        }
        if (this.iZ == 6 && g == 1) {
            this.G(4);
            final int nz = this.G(9) + 1 << 2;
            if (nz != this.nZ) {
                this.sZ = 9;
                this.nZ = nz;
            }
            this.G(1);
            final int rz = this.G(9) << 2;
            if (rz != this.rZ) {
                this.sZ = 9;
                this.rZ = rz;
            }
        }
        return 1;
    }
    
    private final void I(final byte[] array, final int n) {
        final int n2 = this.nZ >> n;
        for (int n3 = this.rZ >> n, i = 8; i < n3; i += 8) {
            final int n4 = i >> 4 - n;
            final int n5 = i + (n - 1 << 3) >> 4;
            for (int n6 = i * n2, j = 0; j < n2; ++j, ++n6) {
                final int n7 = j >> 4 - n;
                if (this.TC[(n4 + 1) * (n7 + 1)] > 0 || this.TC[(n5 + 1) * (n7 + 1)] > 0) {
                    final byte b = (byte)((0xFF & array[n6 - (n2 << 1)]) - (0xFF & (byte)(array[n6 - n2] << 2)) + (0xFF & (byte)(array[n6] << 2)) - (0xFF & array[n6 + n2]) >> 3);
                    final int n8 = ((b >= 0) ? 1 : -1) * Math.max(0, Math.abs(b) - Math.max(0, Math.abs(b) - C.Z.printStackTrace[this.eZ - 1] << 1));
                    final int min = Math.min(Math.abs(n8 >> 1), Math.max(-Math.abs(n8 >> 1), array[n6 - (n2 << 1)] - array[n6 + n2] >> 2));
                    array[n6 + n2] = (byte)((0xFF & array[n6 + n2]) + min);
                    array[n6] = (byte)C.Z.R[(0xFF & array[n6]) - n8 + 128];
                    array[n6 - n2] = (byte)C.Z.R[(0xFF & array[n6 - n2]) + n8 + 128];
                    array[n6 - (n2 << 1)] = (byte)((0xFF & array[n6 - (n2 << 1)]) - min);
                }
            }
        }
    }
    
    private final void Z(final byte[] array, final int n) {
        final int n2 = this.nZ >> n;
        final int n3 = this.rZ >> n;
        for (int i = 8; i < n2; i += 8) {
            final int n4 = i >> 4 - n;
            final int n5 = i + (n - 1 << 3) >> 4;
            for (int n6 = i, j = 0; j < n3; ++j, n6 += n2) {
                final int n7 = j >> 4 - n;
                if (this.TC[(n7 + 1) * (n4 + 1)] > 0 || this.TC[(n7 + 1) * (n5 + 1)] > 0) {
                    final byte b = (byte)((0xFF & array[n6 - 2]) - (0xFF & array[n6 - 1] << 2) + (0xFF & array[n6] << 2) - (0xFF & array[n6 + 1]) >> 3);
                    final int n8 = ((b >= 0) ? 1 : -1) * Math.max(0, Math.abs(b) - Math.max(0, Math.abs(b) - C.Z.printStackTrace[this.eZ - 1] << 1));
                    final int min = Math.min(Math.abs(n8 >> 1), Math.max(-Math.abs(n8 >> 1), array[n6 - 2] - array[n6 + 1] >> 2));
                    array[n6 + 1] = (byte)((0xFF & array[n6 + 1]) + min);
                    array[n6] = (byte)C.Z.R[(0xFF & array[n6]) - n8 + 128];
                    array[n6 - 1] = (byte)C.Z.R[(0xFF & array[n6 - 1]) + n8 + 128];
                    array[n6 - 2] = (byte)((0xFF & array[n6 - 2]) - min);
                }
            }
        }
    }
    
    private final int I(final int n) {
        final boolean b = n < 4;
        int n2;
        if (this.eZ < 5) {
            n2 = 8;
        }
        else if (this.eZ < 25) {
            if (b) {
                if (this.eZ < 9) {
                    n2 = 2 * this.eZ;
                }
                else {
                    n2 = this.eZ + 8;
                }
            }
            else {
                n2 = (this.eZ + 13) / 2;
            }
        }
        else if (b) {
            n2 = 2 * this.eZ - 16;
        }
        else {
            n2 = this.eZ - 6;
        }
        return n2;
    }
    
    private final int Z(final int n) {
        int fc;
        if (n == 1 || n == 3) {
            fc = this.FC;
        }
        else {
            fc = this.FC - 1;
        }
        if (fc < this.uI || (this.FC == this.JC && n != 1 && n != 3) || this.TC[fc] != 2) {
            return 1 << this.yI + 2;
        }
        if (n == 0 || n == 2) {
            return this.MZ[this.FC - this.JC - 1][n + 1];
        }
        if (n == 1 || n == 3) {
            return this.MZ[this.FC - this.JC][n - 1];
        }
        return this.MZ[this.FC - this.JC - 1][n];
    }
    
    private final int C(final int n) {
        int fc;
        if (n == 2 || n == 3) {
            fc = this.FC;
        }
        else {
            fc = this.FC - this.SC;
        }
        if (fc < this.uI || this.TC[fc] != 2) {
            return 1 << this.yI + 2;
        }
        if (n == 0 || n == 1) {
            return this.MZ[this.FC - this.JC][n + 2];
        }
        if (n == 2 || n == 3) {
            return this.MZ[this.FC - this.JC][n - 2];
        }
        return this.MZ[this.FC - this.JC][n];
    }
    
    private final int B(final int n) {
        int fc;
        if (n == 0 || n > 3) {
            fc = this.FC - this.SC - 1;
        }
        else if (n == 1) {
            fc = this.FC - this.SC;
        }
        else if (n == 2) {
            fc = this.FC - 1;
        }
        else {
            fc = this.FC;
        }
        if (fc < this.uI || (this.FC == this.JC && n != 1 && n != 3) || this.TC[fc] != 2) {
            return 1 << this.yI + 2;
        }
        if (n == 0) {
            return this.OZ[3];
        }
        if (n == 1) {
            return this.MZ[this.FC - this.JC][2];
        }
        if (n == 2) {
            return this.MZ[this.FC - this.JC - 1][1];
        }
        if (n == 3) {
            return this.MZ[this.FC - this.JC][0];
        }
        return this.OZ[n];
    }
    
    private static final int Z(final int n, final int n2) {
        if (n >= 0) {
            return (n + (n2 >> 1)) / n2;
        }
        return (n - (n2 >> 1)) / n2;
    }
    
    private final void D(final int n) {
        boolean b = false;
        int g2;
        int g;
        int n2 = g = (g2 = 0);
        final int n3 = n >> 4;
        this.TC[this.FC] = 2;
        if (this.gI) {
            if (this.tI == 0) {
                g2 = this.G(1);
            }
            else {
                g2 = (this.GZ[this.gZ][this.FC - this.JC] ? 1 : 0);
            }
        }
        int n4;
        if (this.tI == 0) {
            n4 = this.Y() >> 4;
        }
        else {
            n4 = this.HZ[this.gZ][this.FC - this.JC];
        }
        if (n3 == 4) {
            if (this.FC != this.uI) {
                this.wI = this.eZ;
            }
            if (this.tI == 0) {
                this.eZ = C.Z.min[this.eZ + C.Z.length[this.H(2)]];
                this.K(2);
            }
            else {
                this.eZ = C.Z.min[this.eZ + C.Z.length[this.AZ[this.gZ][this.FC - this.JC]]];
            }
        }
        else if (this.FC != this.uI) {
            this.wI = this.eZ;
        }
        int n5 = n4 << 2 | (n & 0x3);
        if (this.gI) {
            this.QZ = 5;
            this.jC = C.Z.N;
            this.sC = C.Z.O;
            this.aC = C.Z.P;
        }
        for (int i = 0; i < 6; ++i) {
            int n6 = 1;
            this.JZ = 0;
            if (this.gI) {
                if (this.C()) {
                    int n7;
                    if (this.tI == 0) {
                        if (i < 4) {
                            n7 = this.L();
                        }
                        else {
                            n7 = this.K();
                        }
                    }
                    else {
                        n7 = this.EZ[this.gZ][this.FC - this.JC][i][0];
                    }
                    g = 0;
                    if (n7 != 0) {
                        if (this.tI == 0) {
                            g = this.G(n7);
                        }
                        else {
                            g = this.EZ[this.gZ][this.FC - this.JC][i][1];
                        }
                        if ((g >> n7 - 1 & 0x1) == 0x0) {
                            g = (~g << 32 - n7 >>> 32 - n7) * -1;
                        }
                        if (n7 > 8 && this.tI == 0 && this.G(1) != 1) {
                            throw new Exception();
                        }
                    }
                }
                else {
                    n6 = 0;
                }
            }
            else {
                int g3 = this.G(8);
                if (g3 == 255) {
                    g3 = 128;
                }
                if ((this.hZ[i][0] = g3 << 13) != 0) {
                    this.JZ |= C.Z.arraycopy[0];
                }
            }
            if (this.gI) {
                final int z = this.Z(i);
                final int b2 = this.B(i);
                final int c = this.C(i);
                b = (Math.abs(z - b2) >= Math.abs(b2 - c));
                if (b) {
                    n2 = Z(z, this.I(i));
                }
                else {
                    n2 = Z(c, this.I(i));
                }
            }
            if ((n5 & 0x20) != 0x0) {
                this.I(n6, i, g2 == 1, b, n3);
            }
            else {
                for (int j = n6; j < 64; ++j) {
                    this.hZ[i][j] = 0;
                }
                if (this.gI) {
                    this.I(b, g2 == 1, i);
                }
            }
            if (this.gI) {
                int n8 = n2 + ((n6 != 0) ? g : this.hZ[i][0]);
                if (n8 > 2047) {
                    n8 = 2047;
                }
                if (n8 < -2048) {
                    n8 = -2048;
                }
                this.hZ[i][0] = n8 * this.I(i);
                if (this.hZ[i][0] > (1 << this.yI + 3) - 1) {
                    this.hZ[i][0] = (1 << this.yI + 3) - 1;
                }
                if (this.hZ[i][0] < -(1 << this.yI + 3)) {
                    this.hZ[i][0] = -(1 << this.yI + 3);
                }
                final int[] array = this.hZ[i];
                final int n9 = 0;
                if ((array[n9] <<= 10) != 0) {
                    this.JZ |= C.Z.arraycopy[0];
                }
                else {
                    this.JZ &= ~C.Z.arraycopy[0];
                }
                this.OZ[i] = this.MZ[this.FC - this.JC][i];
                this.MZ[this.FC - this.JC][i] = this.hZ[i][0] / C.Z.notify[0];
            }
            this.BZ = 0;
            this.g(this.hZ[i], this.kZ[i], this.lZ[i], this.mZ[i]);
            n5 <<= 1;
        }
    }
    
    private final void I(final boolean b, final boolean b2, final int n) {
        for (int i = 0; i < 7; ++i) {
            if (b2) {
                if (b) {
                    final int j = this.I(n, 8 * (i + 1), b);
                    this.PZ[n][i] = Z(j, this.eZ) * this.eZ;
                    if (j != 0) {
                        this.hZ[n][i + 1 << 3] = this.F(Z(j, this.eZ)) * C.Z.notify[8 * (i + 1)];
                        this.JZ |= C.Z.arraycopy[i + 1 << 3];
                    }
                    this.NZ[this.FC - this.JC][n][i] = 0;
                }
                else {
                    final int k = this.I(n, i + 1, b);
                    this.NZ[this.FC - this.JC][n][i] = Z(k, this.eZ) * this.eZ;
                    if (k != 0) {
                        this.hZ[n][i + 1] = this.F(Z(k, this.eZ)) * C.Z.notify[i + 1];
                        this.JZ |= C.Z.arraycopy[i + 1];
                    }
                    this.PZ[n][i] = 0;
                }
            }
            else {
                this.NZ[this.FC - this.JC][n][i] = 0;
                this.PZ[n][i] = 0;
            }
        }
    }
    
    private final void V() {
        if (this.FC == this.JC) {
            this.HC = this.NC[this.gZ];
            this.KC = this.OC[this.gZ];
        }
        while (this.FC < this.JC + this.SC) {
            final int n = this.FC - this.JC;
            try {
                if ((this.tI == 0 && this.F()) || (this.D() && this.tI == 0) || (this.FC - this.uI == this.IZ && this.tI == 1)) {
                    return;
                }
            }
            catch (Exception ex2) {
                return;
            }
            int i;
            do {
                if (this.tI == 0) {
                    i = this.X();
                }
                else {
                    i = this.SZ[this.gZ][this.FC - this.JC];
                }
            } while (i == -1);
            this.lZ[0] = this.HC;
            this.lZ[1] = this.lZ[0] + 8;
            this.lZ[2] = this.lZ[0] + this.qZ;
            this.lZ[3] = this.lZ[2] + 8;
            this.lZ[4] = this.KC;
            this.lZ[5] = this.KC;
            final Point point = this.XC[n][this.gZ][0];
            final Point point2 = this.XC[n][this.gZ][1];
            final Point point3 = this.XC[n][this.gZ][2];
            final Point point4 = this.XC[n][this.gZ][3];
            final Point point5 = this.XC[n][this.gZ][0];
            final Point point6 = this.XC[n][this.gZ][1];
            final Point point7 = this.XC[n][this.gZ][2];
            final Point point8 = this.XC[n][this.gZ][3];
            final boolean b = false;
            point8.y = (b ? 1 : 0);
            point7.y = (b ? 1 : 0);
            point6.y = (b ? 1 : 0);
            point5.y = (b ? 1 : 0);
            point4.x = (b ? 1 : 0);
            point3.x = (b ? 1 : 0);
            point2.x = (b ? 1 : 0);
            point.x = (b ? 1 : 0);
            try {
                this.D(i);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return;
            }
            this.HC += 16;
            this.KC += 8;
            ++this.FC;
            if (this.tI == 1 && this.FC - this.uI == this.IZ) {
                if (this.FC == this.SC) {
                    this.GC = false;
                }
                if (this.FC == this.JC + this.SC) {
                    ++this.gZ;
                }
                return;
            }
        }
        if (this.FC == this.SC) {
            this.GC = false;
        }
        ++this.gZ;
    }
    
    private final void W() {
        int i = 0;
        final int[] min = C.Z.min;
        if (this.FC == this.JC) {
            this.HC = this.NC[this.gZ];
            this.KC = this.OC[this.gZ];
            this.LC = this.PC[this.gZ];
            this.MC = this.QC[this.gZ];
        }
        while (this.FC < this.JC + this.SC) {
            if ((this.tI == 0 && this.F()) || (this.D() && this.tI == 0) || (this.FC - this.uI == this.IZ && this.tI == 1)) {
                return;
            }
            this.AC = this.FC - this.JC;
            int g;
            do {
                if (this.tI == 0) {
                    g = this.G(1);
                    this.KZ[this.FC / this.SC][this.AC] = (g == 1);
                }
                else {
                    g = (this.KZ[this.FC / this.SC][this.AC] ? 1 : 0);
                }
                if (g != 0) {
                    x(this.DC.Y, this.HC, this.xZ.Y, this.HC, this.nZ, this.nZ);
                    u(this.DC.U, this.KC, this.xZ.U, this.KC, this.pZ, this.pZ);
                    u(this.DC.V, this.KC, this.xZ.V, this.KC, this.pZ, this.pZ);
                    final Point point = this.YC[this.AC][0];
                    final Point point2 = this.YC[this.AC][1];
                    final Point point3 = this.YC[this.AC][2];
                    final Point point4 = this.YC[this.AC][3];
                    final boolean b = false;
                    point4.x = (b ? 1 : 0);
                    point3.x = (b ? 1 : 0);
                    point2.x = (b ? 1 : 0);
                    point.x = (b ? 1 : 0);
                    final Point point5 = this.YC[this.AC][0];
                    final Point point6 = this.YC[this.AC][1];
                    final Point point7 = this.YC[this.AC][2];
                    final Point point8 = this.YC[this.AC][3];
                    final boolean b2 = false;
                    point8.y = (b2 ? 1 : 0);
                    point7.y = (b2 ? 1 : 0);
                    point6.y = (b2 ? 1 : 0);
                    point5.y = (b2 ? 1 : 0);
                    final Point point9 = this.XC[this.AC][this.gZ][0];
                    final Point point10 = this.XC[this.AC][this.gZ][1];
                    final Point point11 = this.XC[this.AC][this.gZ][2];
                    final Point point12 = this.XC[this.AC][this.gZ][3];
                    final Point point13 = this.XC[this.AC][this.gZ][0];
                    final Point point14 = this.XC[this.AC][this.gZ][1];
                    final Point point15 = this.XC[this.AC][this.gZ][2];
                    final Point point16 = this.XC[this.AC][this.gZ][3];
                    final boolean b3 = false;
                    point16.y = (b3 ? 1 : 0);
                    point15.y = (b3 ? 1 : 0);
                    point14.y = (b3 ? 1 : 0);
                    point13.y = (b3 ? 1 : 0);
                    point12.x = (b3 ? 1 : 0);
                    point11.x = (b3 ? 1 : 0);
                    point10.x = (b3 ? 1 : 0);
                    point9.x = (b3 ? 1 : 0);
                    this.HC += 16;
                    this.KC += 8;
                    this.LC += 16;
                    this.MC += 8;
                    if (this.FC != this.uI) {
                        this.wI = this.eZ;
                        break;
                    }
                    break;
                }
                else if (this.tI == 0) {
                    i = this.i();
                }
                else {
                    i = this.SZ[this.FC / this.SC][this.FC - this.JC];
                }
            } while (i == -1);
            if (g == 0) {
                final int n = i >> 4;
                this.lZ[0] = this.HC;
                this.lZ[1] = this.lZ[0] + 8;
                this.lZ[2] = this.lZ[0] + this.qZ;
                this.lZ[3] = this.lZ[2] + 8;
                this.lZ[4] = this.KC;
                this.lZ[5] = this.KC;
                if (n >= 3) {
                    this.D(i);
                    final Point point17 = this.YC[this.AC][0];
                    final Point point18 = this.YC[this.AC][1];
                    final Point point19 = this.YC[this.AC][2];
                    final Point point20 = this.YC[this.AC][3];
                    final boolean b4 = false;
                    point20.x = (b4 ? 1 : 0);
                    point19.x = (b4 ? 1 : 0);
                    point18.x = (b4 ? 1 : 0);
                    point17.x = (b4 ? 1 : 0);
                    final Point point21 = this.YC[this.AC][0];
                    final Point point22 = this.YC[this.AC][1];
                    final Point point23 = this.YC[this.AC][2];
                    final Point point24 = this.YC[this.AC][3];
                    final boolean b5 = false;
                    point24.y = (b5 ? 1 : 0);
                    point23.y = (b5 ? 1 : 0);
                    point22.y = (b5 ? 1 : 0);
                    point21.y = (b5 ? 1 : 0);
                    final Point point25 = this.XC[this.AC][this.gZ][0];
                    final Point point26 = this.XC[this.AC][this.gZ][1];
                    final Point point27 = this.XC[this.AC][this.gZ][2];
                    final Point point28 = this.XC[this.AC][this.gZ][3];
                    final Point point29 = this.XC[this.AC][this.gZ][0];
                    final Point point30 = this.XC[this.AC][this.gZ][1];
                    final Point point31 = this.XC[this.AC][this.gZ][2];
                    final Point point32 = this.XC[this.AC][this.gZ][3];
                    final boolean b6 = false;
                    point32.y = (b6 ? 1 : 0);
                    point31.y = (b6 ? 1 : 0);
                    point30.y = (b6 ? 1 : 0);
                    point29.y = (b6 ? 1 : 0);
                    point28.x = (b6 ? 1 : 0);
                    point27.x = (b6 ? 1 : 0);
                    point26.x = (b6 ? 1 : 0);
                    point25.x = (b6 ? 1 : 0);
                }
                else {
                    this.TC[this.FC] = 1;
                    int y;
                    if (this.tI == 0) {
                        y = this.Y();
                    }
                    else {
                        y = this.HZ[this.FC / this.SC][this.FC - this.JC];
                    }
                    if (n == 1) {
                        if (this.FC != this.uI) {
                            this.wI = this.eZ;
                        }
                        if (this.tI == 0) {
                            this.eZ = min[this.eZ + C.Z.length[this.H(2)]];
                            this.K(2);
                        }
                        else {
                            this.eZ = C.Z.min[this.eZ + C.Z.length[this.AZ[this.gZ][this.FC - this.JC]]];
                        }
                    }
                    else if (this.FC != this.uI) {
                        this.wI = this.eZ;
                    }
                    if (this.KI != 3 || this.EI != 1) {
                        for (byte b7 = (byte)((n != 2) ? 1 : 4), b8 = 0; b8 < b7; ++b8) {
                            this.E(b8);
                        }
                    }
                    else {
                        final Point point33 = this.YC[this.AC][0];
                        final Point point34 = this.YC[this.AC][1];
                        final Point point35 = this.YC[this.AC][2];
                        final Point point36 = this.YC[this.AC][3];
                        final boolean b9 = false;
                        point36.x = (b9 ? 1 : 0);
                        point35.x = (b9 ? 1 : 0);
                        point34.x = (b9 ? 1 : 0);
                        point33.x = (b9 ? 1 : 0);
                        final Point point37 = this.YC[this.AC][0];
                        final Point point38 = this.YC[this.AC][1];
                        final Point point39 = this.YC[this.AC][2];
                        final Point point40 = this.YC[this.AC][3];
                        final boolean b10 = false;
                        point40.y = (b10 ? 1 : 0);
                        point39.y = (b10 ? 1 : 0);
                        point38.y = (b10 ? 1 : 0);
                        point37.y = (b10 ? 1 : 0);
                    }
                    this.XC[this.AC][this.gZ][0].x = this.YC[this.AC][0].x;
                    this.XC[this.AC][this.gZ][1].x = this.YC[this.AC][1].x;
                    this.XC[this.AC][this.gZ][2].x = this.YC[this.AC][2].x;
                    this.XC[this.AC][this.gZ][3].x = this.YC[this.AC][3].x;
                    this.XC[this.AC][this.gZ][0].y = this.YC[this.AC][0].y;
                    this.XC[this.AC][this.gZ][1].y = this.YC[this.AC][1].y;
                    this.XC[this.AC][this.gZ][2].y = this.YC[this.AC][2].y;
                    this.XC[this.AC][this.gZ][3].y = this.YC[this.AC][3].y;
                    if (this.KI != 3 || this.EI != 1) {
                        if (n == 2) {
                            for (int j = 0; j < 4; ++j) {
                                this.J(j);
                            }
                            this.d();
                        }
                        else {
                            this.f();
                        }
                    }
                    int n2 = y << 2 | (i & 0x3);
                    if (this.gI) {
                        this.QZ = 4;
                        this.jC = C.Z.E;
                        this.sC = C.Z.H;
                        this.aC = C.Z.L;
                    }
                    for (int k = 0; k < 6; ++k) {
                        this.JZ = 0;
                        if ((n2 & 0x20) != 0x0) {
                            this.I(0, k, false, false, n);
                            this.BZ = 1;
                            this.g(this.hZ[k], this.kZ[k], this.lZ[k], this.mZ[k]);
                        }
                        else {
                            final int n3 = this.FC - this.JC;
                            for (int l = 0; l < 7; ++l) {
                                this.NZ[n3][k][l] = 0;
                                this.PZ[k][l] = 0;
                            }
                        }
                        this.OZ[k] = this.MZ[this.FC - this.JC][k];
                        n2 <<= 1;
                    }
                }
                this.HC += 16;
                this.KC += 8;
                this.LC += 16;
                this.MC += 8;
            }
            else {
                this.TC[this.FC] = 0;
                for (int n4 = 0; n4 < 6; ++n4) {
                    this.OZ[n4] = this.MZ[this.FC - this.JC][n4];
                }
            }
            ++this.FC;
            if (this.tI == 1 && this.FC - this.uI == this.IZ) {
                if (this.FC == this.SC) {
                    this.GC = false;
                }
                if (this.FC == this.JC + this.SC) {
                    ++this.gZ;
                }
                return;
            }
        }
        final Point[][] yc = this.YC;
        this.YC = this.iC;
        this.iC = yc;
        if (this.FC == this.SC) {
            this.GC = false;
        }
        ++this.gZ;
    }
    
    private static final int I(final int n, final int n2, final int n3) {
        if (n3 >= 3) {
            if (n == 0) {
                if (n2 == 0) {
                    return 27;
                }
                if (n2 == 1) {
                    return 10;
                }
                if (n2 == 2) {
                    return 5;
                }
                if (n2 == 3) {
                    return 4;
                }
                if (n2 <= 7) {
                    return 3;
                }
                if (n2 <= 9) {
                    return 2;
                }
                return (n2 > 14) ? -1 : 1;
            }
            else {
                if (n2 == 0) {
                    return 8;
                }
                if (n2 == 1) {
                    return 3;
                }
                if (n2 <= 6) {
                    return 2;
                }
                return (n2 > 20) ? -1 : 1;
            }
        }
        else if (n == 0) {
            if (n2 == 0) {
                return 12;
            }
            if (n2 == 1) {
                return 6;
            }
            if (n2 == 2) {
                return 4;
            }
            if (n2 <= 6) {
                return 3;
            }
            if (n2 <= 10) {
                return 2;
            }
            return (n2 > 26) ? -1 : 1;
        }
        else {
            if (n2 == 0) {
                return 3;
            }
            if (n2 == 1) {
                return 2;
            }
            return (n2 > 40) ? -1 : 1;
        }
    }
    
    private static final int Z(final int n, final int n2, final int n3) {
        if (n3 >= 3) {
            if (n == 0) {
                if (n2 == 1) {
                    return 14;
                }
                if (n2 == 2) {
                    return 9;
                }
                if (n2 == 3) {
                    return 7;
                }
                if (n2 == 4) {
                    return 3;
                }
                if (n2 == 5) {
                    return 2;
                }
                if (n2 <= 10) {
                    return 1;
                }
                return (n2 > 27) ? -1 : 0;
            }
            else {
                if (n2 == 1) {
                    return 20;
                }
                if (n2 == 2) {
                    return 6;
                }
                if (n2 == 3) {
                    return 1;
                }
                return (n2 > 8) ? -1 : 0;
            }
        }
        else if (n == 0) {
            if (n2 == 1) {
                return 26;
            }
            if (n2 == 2) {
                return 10;
            }
            if (n2 == 3) {
                return 6;
            }
            if (n2 == 4) {
                return 2;
            }
            if (n2 <= 6) {
                return 1;
            }
            return (n2 > 12) ? -1 : 0;
        }
        else {
            if (n2 == 1) {
                return 40;
            }
            if (n2 == 2) {
                return 1;
            }
            return (n2 != 3) ? -1 : 0;
        }
    }
    
    private final void I(final int n, final int n2, final boolean b, final boolean b2, final int n3) {
        int n4 = 0;
        int n5 = 0;
        int i = 0;
        int n6 = 0;
        int[] array = C.Z.toBinaryString;
        final int[] notify = C.Z.notify;
        final int[] array2 = this.hZ[n2];
        if (this.gI) {
            for (int j = 0; j < 7; ++j) {
                this.dC[j] = 63489;
                this.fC[j] = 63489;
            }
            if (b) {
                if (b2) {
                    array = C.Z.x;
                }
                else {
                    array = C.Z.wait;
                }
            }
        }
        System.arraycopy(this.RC, n, array2, n, 64 - n);
        int n7 = n - 1;
        if (this.eC == 0) {
            int k;
            do {
                final int h = this.H(13);
                int n9;
                int n10;
                if (h >= 1024) {
                    final int n8 = (h >> 6) - 16;
                    n9 = this.jC[n8];
                    n10 = C.Z.G[n8];
                }
                else if (h >= 256) {
                    final int n11 = (h >> 3) - 32;
                    n9 = this.sC[n11];
                    n10 = C.Z.K[n11];
                }
                else {
                    final int n12 = (h >> 1) - 8;
                    n9 = this.aC[n12];
                    n10 = C.Z.M[n12];
                }
                this.K(n10 + 1);
                final int n13 = n9;
                int g;
                int g2;
                if (n13 != 7167) {
                    g = (((h >> 12 - n10 & 0x1) == 0x0) ? (n13 & (1 << this.QZ) - 1) : (-(n13 & (1 << this.QZ) - 1)));
                    g2 = (n13 >> this.QZ & (1 << 10 - this.QZ) - 1);
                    k = (n13 & 0x1000);
                }
                else if (this.gI) {
                    if (this.H(1) == 0) {
                        this.K(1);
                        final int h2 = this.H(13);
                        int n15;
                        int n16;
                        if (h2 >= 1024) {
                            final int n14 = (h2 >> 6) - 16;
                            n15 = this.jC[n14];
                            n16 = C.Z.G[n14];
                        }
                        else if (h2 >= 256) {
                            final int n17 = (h2 >> 3) - 32;
                            n15 = this.sC[n17];
                            n16 = C.Z.K[n17];
                        }
                        else {
                            final int n18 = (h2 >> 1) - 8;
                            n15 = this.aC[n18];
                            n16 = C.Z.M[n18];
                        }
                        this.K(n16 + 1);
                        final int n19 = n15;
                        final int n20 = ((h2 >> 12 - n16 & 0x1) == 0x0) ? (n19 & (1 << this.QZ) - 1) : (-(n19 & (1 << this.QZ) - 1));
                        g2 = (n19 >> this.QZ & (1 << 10 - this.QZ) - 1);
                        k = (n19 & 0x1000);
                        g = ((n20 < 0) ? -1 : 1) * (Math.abs(n20) + I(k, Math.abs(g2), n3));
                    }
                    else if (this.H(2) == 2) {
                        this.K(2);
                        final int h3 = this.H(13);
                        int n22;
                        int n23;
                        if (h3 >= 1024) {
                            final int n21 = (h3 >> 6) - 16;
                            n22 = this.jC[n21];
                            n23 = C.Z.G[n21];
                        }
                        else if (h3 >= 256) {
                            final int n24 = (h3 >> 3) - 32;
                            n22 = this.sC[n24];
                            n23 = C.Z.K[n24];
                        }
                        else {
                            final int n25 = (h3 >> 1) - 8;
                            n22 = this.aC[n25];
                            n23 = C.Z.M[n25];
                        }
                        this.K(n23 + 1);
                        final int n26 = n22;
                        g = (((h3 >> 12 - n23 & 0x1) == 0x0) ? (n26 & (1 << this.QZ) - 1) : (-(n26 & (1 << this.QZ) - 1)));
                        final int n27 = n26 >> this.QZ & (1 << 10 - this.QZ) - 1;
                        k = (n26 & 0x1000);
                        g2 = n27 + Z(k, Math.abs(g), n3) + 1;
                    }
                    else {
                        if (this.H(2) != 3) {
                            throw new Exception();
                        }
                        this.K(2);
                        k = this.G(1);
                        g2 = this.G(6);
                        if (this.G(1) != 1) {
                            throw new Exception();
                        }
                        g = this.G(12);
                        if (g >>> 11 == 1) {
                            g -= 4096;
                        }
                        if (this.G(1) != 1) {
                            throw new Exception();
                        }
                    }
                }
                else {
                    final int g3 = this.G(15);
                    g = g3 << 24 >> 24;
                    g2 = (g3 >> 8 & 0x3F);
                    k = (g3 & 0x4000);
                }
                n7 = (n7 + (g2 + 1) & 0x3F);
                final int n28 = array[n7];
                if (g != 0) {
                    if (this.gI) {
                        if (b && ((n28 >= 1 && n28 <= 7 && !b2) || ((n28 & 0x7) == 0x0 && n28 != 0 && b2))) {
                            final int l = this.I(n2, n28, b2);
                            if (l >= 0) {
                                g += (l + (this.eZ >> 1)) / this.eZ;
                            }
                            else {
                                g += (l - (this.eZ >> 1)) / this.eZ;
                            }
                        }
                        if (g <= 2047) {
                            if (g < -2048) {
                                g = -2048;
                            }
                        }
                        else {
                            g = 2047;
                        }
                        final int n29 = g * this.eZ;
                        if (n28 >= 1 && n28 <= 7) {
                            this.dC[n28 - 1] = n29;
                        }
                        if ((n28 & 0x7) == 0x0 && n28 != 0) {
                            this.fC[(n28 >> 3) - 1] = n29;
                        }
                    }
                    if (g != 0) {
                        if (n28 == 0 && n3 >= 3) {
                            array2[n28] = g;
                            this.JZ |= C.Z.arraycopy[n28];
                        }
                        else {
                            final int[][] max = C.Z.max;
                            final boolean b3 = g < 0;
                            final int n30 = b3 ? (-g) : g;
                            int n31;
                            if ((this.eZ & 0x1) != 0x0) {
                                n31 = (b3 ? (-max[this.eZ][n30]) : max[this.eZ][n30]);
                            }
                            else {
                                n31 = (b3 ? (1 - max[this.eZ][n30]) : (max[this.eZ][n30] - 1));
                            }
                            final int[] array3 = array2;
                            final int n32 = n28;
                            final int n33 = n31 * notify[n28];
                            array3[n32] = n33;
                            if (n33 == 0) {
                                continue;
                            }
                            this.JZ |= C.Z.arraycopy[n28];
                        }
                    }
                    else {
                        this.JZ &= ~C.Z.arraycopy[n28];
                    }
                }
            } while (k == 0);
        }
        else {
            if (this.bZ == 1) {
                if (n3 >= 3 && this.dZ == 1) {
                    this.min(0);
                    this.dZ = 0;
                }
                else if (n3 < 3 && this.dZ == 0) {
                    this.min(1);
                    this.dZ = 1;
                }
            }
            do {
                boolean b4 = false;
                final int h4 = this.H(16);
                final int n34 = h4 >> 13;
                int n36;
                if (n34 == 3) {
                    final int n35 = (h4 & 0x1FFF) >> 1;
                    n6 = this.JB[n35];
                    n36 = this.FB[n35];
                }
                else if (n34 == 2) {
                    if (h4 >> 8 == 95) {
                        final int n37 = (h4 & 0xFF) >> 1;
                        n6 = this.ZB[n37];
                        n36 = this.IB[n37];
                    }
                    else {
                        final int n38 = (h4 >> 6 & 0x7F) >> 1;
                        n6 = this.vC[n38];
                        n36 = this.uC[n38];
                    }
                }
                else if (n34 == 1) {
                    if (h4 >> 8 == 63) {
                        final int n39 = (h4 & 0xFF) >> 1;
                        n6 = this.pC[n39];
                        n36 = this.oC[n39];
                    }
                    else {
                        final int n40 = (h4 >> 6 & 0x7F) >> 1;
                        n6 = this.kC[n40];
                        n36 = this.hC[n40];
                    }
                }
                else if (n34 == 4) {
                    if (h4 >> 8 == 128) {
                        final int n41 = (h4 & 0xFF) >> 1 >> 1;
                        n6 = this.HB[n41];
                        n36 = this.GB[n41];
                    }
                    else {
                        final int n42 = (h4 >> 6 & 0x7F) >> 1;
                        n6 = this.OB[n42];
                        n36 = this.NB[n42];
                    }
                }
                else {
                    final int h5 = this.H(4);
                    if (h5 == 0) {
                        final int h6 = this.H(30);
                        n36 = 30;
                        i = (h6 >> 24 & 0x1);
                        n5 = (h6 >> 18 & 0x3F);
                        n4 = (h6 >> 6 & 0x7FF);
                        if ((h6 & 0x1) != 0x0) {
                            n4 = -n4;
                        }
                        b4 = true;
                    }
                    else {
                        n6 = this.t[h5];
                        n36 = this.v[h5];
                    }
                }
                if (!b4) {
                    final int g4 = this.G(n36);
                    i = n6 >> 12;
                    n4 = (n6 & 0x3F);
                    if ((g4 & 0x1) != 0x0) {
                        n4 = -n4;
                    }
                    n5 = (n6 >> 6 & 0x3F);
                }
                else {
                    this.K(n36);
                }
                n7 = (n7 + (n5 + 1) & 0x3F);
                final int n43 = array[n7];
                if (n4 != 0) {
                    if (this.gI) {
                        if (b && ((n43 >= 1 && n43 <= 7 && !b2) || ((n43 & 0x7) == 0x0 && n43 != 0 && b2))) {
                            n4 += Z(this.I(n2, n43, b2), this.eZ);
                        }
                        if (n4 <= 2047) {
                            if (n4 < -2048) {
                                n4 = -2048;
                            }
                        }
                        else {
                            n4 = 2047;
                        }
                        final int n44 = n4 * this.eZ;
                        if (n43 >= 1 && n43 <= 7) {
                            this.dC[n43 - 1] = n44;
                        }
                        if ((n43 & 0x7) == 0x0 && n43 != 0) {
                            this.fC[(n43 >> 3) - 1] = n44;
                        }
                    }
                    if (n4 == 0) {
                        continue;
                    }
                    if (n43 == 0 && n3 >= 3) {
                        array2[n43] = n4;
                        this.JZ |= C.Z.arraycopy[0];
                    }
                    else {
                        if ((array2[n43] = this.F(n4) * notify[n43]) == 0) {
                            continue;
                        }
                        this.JZ |= C.Z.arraycopy[n43];
                    }
                }
            } while (i == 0);
        }
        if (this.gI && b) {
            if (b2) {
                for (int n45 = 0; n45 < 7; ++n45) {
                    if (this.fC[n45] == 63489) {
                        final int m = this.I(n2, 8 * (n45 + 1), b2);
                        if (m != 0) {
                            int n46;
                            if (m >= 0) {
                                n46 = (m + (this.eZ >> 1)) / this.eZ;
                            }
                            else {
                                n46 = (m - (this.eZ >> 1)) / this.eZ;
                            }
                            final int[][] max2 = C.Z.max;
                            final boolean b5 = n46 < 0;
                            final int n47 = b5 ? (-n46) : n46;
                            int n48;
                            if ((this.eZ & 0x1) != 0x0) {
                                n48 = (b5 ? (-max2[this.eZ][n47]) : max2[this.eZ][n47]);
                            }
                            else {
                                n48 = (b5 ? (1 - max2[this.eZ][n47]) : (max2[this.eZ][n47] - 1));
                            }
                            this.fC[n45] = n46 * this.eZ;
                            final int[] array4 = array2;
                            final int n49 = n45 + 1 << 3;
                            final int n50 = array4[n49] + n48 * notify[8 * (n45 + 1)];
                            array4[n49] = n50;
                            if (n50 != 0) {
                                this.JZ |= C.Z.arraycopy[n45 + 1 << 3];
                            }
                        }
                    }
                }
            }
            else {
                for (int n51 = 0; n51 < 7; ++n51) {
                    if (this.dC[n51] == 63489) {
                        final int i2 = this.I(n2, n51 + 1, b2);
                        if (i2 != 0) {
                            int n52;
                            if (i2 >= 0) {
                                n52 = (i2 + (this.eZ >> 1)) / this.eZ;
                            }
                            else {
                                n52 = (i2 - (this.eZ >> 1)) / this.eZ;
                            }
                            this.dC[n51] = n52 * this.eZ;
                            final int[][] max3 = C.Z.max;
                            final boolean b6 = n52 < 0;
                            final int n53 = b6 ? (-n52) : n52;
                            int n54;
                            if ((this.eZ & 0x1) != 0x0) {
                                n54 = (b6 ? (-max3[this.eZ][n53]) : max3[this.eZ][n53]);
                            }
                            else {
                                n54 = (b6 ? (1 - max3[this.eZ][n53]) : (max3[this.eZ][n53] - 1));
                            }
                            final int[] array5 = array2;
                            final int n55 = n51 + 1;
                            final int n56 = array5[n55] + n54 * notify[n51 + 1];
                            array5[n55] = n56;
                            if (n56 != 0) {
                                this.JZ |= C.Z.arraycopy[n51 + 1];
                            }
                        }
                    }
                }
            }
        }
        if (this.gI) {
            for (int n57 = 0; n57 < 7; ++n57) {
                this.dC[n57] = ((this.dC[n57] != 63489) ? this.dC[n57] : 0);
                this.NZ[this.FC - this.JC][n2][n57] = this.dC[n57];
                this.fC[n57] = ((this.fC[n57] != 63489) ? this.fC[n57] : 0);
                this.PZ[n2][n57] = this.fC[n57];
            }
        }
    }
    
    private final int I(final int n, final int n2, final boolean b) {
        if (b) {
            if (n == 0 || n == 2) {
                if (this.FC - 1 >= this.uI && this.FC != this.JC && this.TC[this.FC - 1] == 2) {
                    return this.PZ[n + 1][n2 / 8 - 1];
                }
            }
            else {
                if (n == 1 || n == 3) {
                    return this.PZ[n - 1][n2 / 8 - 1];
                }
                if (this.FC - 1 >= this.uI && this.FC != this.JC && this.TC[this.FC - 1] == 2) {
                    return this.PZ[n][n2 / 8 - 1];
                }
            }
        }
        else if (n == 0 || n == 1) {
            if (this.FC - this.SC >= this.uI && this.TC[this.FC - this.SC] == 2) {
                return this.NZ[this.FC - this.JC][n + 2][n2 - 1];
            }
        }
        else {
            if (n == 2 || n == 3) {
                return this.NZ[this.FC - this.JC][n - 2][n2 - 1];
            }
            if (this.FC - this.SC >= this.uI && this.TC[this.FC - this.SC] == 2) {
                return this.NZ[this.FC - this.JC][n][n2 - 1];
            }
        }
        return 0;
    }
    
    private final int F(final int n) {
        final int[][] max = C.Z.max;
        final boolean b = n < 0;
        final int n2 = b ? (-n) : n;
        if ((this.eZ & 0x1) != 0x0) {
            return b ? (-max[this.eZ][n2]) : max[this.eZ][n2];
        }
        return b ? (1 - max[this.eZ][n2]) : (max[this.eZ][n2] - 1);
    }
    
    private final int X() {
        final int h = this.H(9);
        if (h == 1) {
            this.K(9);
            return -1;
        }
        final int n = h >> 3;
        final int n2 = C.Z.y[n];
        this.K(C.Z.I[n]);
        return n2;
    }
    
    private final int Y() {
        final int h = this.H(6);
        final int n = C.Z.B[h];
        this.K(C.Z.D[h]);
        return n;
    }
    
    private final int i() {
        final int h = this.H(9);
        final int n = C.Z.Z[h];
        this.K(C.Z.C[h]);
        return n;
    }
    
    private final int z() {
        final int h = this.H(13);
        int n2;
        int n3;
        if (h >= 192) {
            final int n = h >> 5;
            n2 = C.Z.F[n];
            n3 = C.Z.J[n];
        }
        else {
            final int n4 = h;
            n2 = C.Z.S[n4];
            n3 = C.Z.A[n4];
        }
        this.K(n3);
        return n2;
    }
    
    private final void c() {
        System.arraycopy(this.DC.Y, 0, this.xZ.Y, 0, this.nZ * this.rZ);
        System.arraycopy(this.DC.V, 0, this.xZ.V, 0, this.nZ * this.rZ / 4);
        System.arraycopy(this.DC.U, 0, this.xZ.U, 0, this.nZ * this.rZ / 4);
    }
    
    private static final void I(final byte[] array, final int n, final int n2, final int n3, final int n4) {
        if (n != n3) {
            for (int i = n3 - 1, n5 = n3; i < n * n4; i += n, n5 += n) {
                for (int j = 0; j < n - n3; ++j) {
                    array[n5 + j] = array[i];
                }
            }
        }
        if (n2 != n4) {
            final int n6 = n * (n4 - 1);
            for (int k = n * n4; k < n2 * n; k += n) {
                System.arraycopy(array, n6, array, k, n3);
            }
        }
        if (n != n3 && n2 != n4) {
            final int n7 = n * (n4 - 1) + n3;
            for (int l = n7 + n; l < n2 * n; l += n) {
                System.arraycopy(array, n7, array, l, n - n3);
            }
        }
    }
    
    private final void J(final int n) {
        int x = this.YC[this.AC][n].x;
        int y = this.YC[this.AC][n].y;
        final int n2 = this.HC / this.nZ;
        final int n3 = this.HC - n2 * this.nZ;
        final byte b = (byte)((n != 0 && n != 2) ? 8 : 0);
        final byte b2 = (byte)((n != 0 && n != 1) ? 8 : 0);
        if (2 * (n3 + b) + x < -16) {
            x = -((n3 + b) * 2) - 16;
        }
        else if (2 * (n3 + b) + x > 2 * this.nZ) {
            x = 2 * (this.nZ - n3 - b);
        }
        if (2 * (n2 + b2) + y < -16) {
            y = -(2 * (n2 + b2)) - 16;
        }
        else if (2 * (n2 + b2) + y > 2 * this.rZ) {
            y = 2 * (this.rZ - n2 - b2);
        }
        final int n4 = x >> 1;
        final byte b3 = (byte)(x & 0x1);
        final int n5 = y >> 1;
        final byte b4 = (byte)(y & 0x1);
        final byte b5 = (byte)(2 * (n3 + b) + x);
        final byte b6 = (byte)(2 * (n2 + b2) + y);
        final int z = Z(b5, b6, this.nZ, this.rZ, 9);
        if (z != -1) {
            I(z, b5, b6, this.nZ, this.rZ, 9, this.DC.Y, this.UC);
        }
        if (this.fZ == 0 && !this.gI && !I(b5, b6, this.nZ, this.rZ, 8) && !this.aI) {
            this.aI = true;
        }
        byte[] array;
        int n6;
        int nz;
        if (z != -1) {
            array = this.UC;
            n6 = 0;
            nz = 9;
        }
        else {
            array = this.DC.Y;
            n6 = this.HC + b + (n5 + b2) * this.nZ + n4;
            nz = this.nZ;
        }
        final byte[] y2 = this.xZ.Y;
        final int n7 = this.HC + (b + b2 * this.nZ);
        if (b3 != 0 && b4 != 0) {
            this.s(array, n6, y2, n7, nz, this.nZ);
        }
        else if (b3 != 0 || b4 != 0) {
            this.t(array, n6, y2, n7, b3, b4, nz, this.nZ);
        }
        else {
            u(array, n6, y2, n7, nz, this.nZ);
        }
    }
    
    private final void d() {
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < 4; ++i) {
            n += this.YC[this.AC][i].x;
            n2 += this.YC[this.AC][i].y;
        }
        int n3 = (n >> 3) + (((n & 0xF) >= 3) ? (((n & 0xF) >= 8) ? (((n & 0xF) >= 14) ? 1 : 0) : 1) : 0);
        int n4 = (n2 >> 3) + (((n2 & 0xF) >= 3) ? (((n2 & 0xF) >= 8) ? (((n2 & 0xF) >= 14) ? 1 : 0) : 1) : 0);
        final int n5 = this.KC / this.pZ;
        final int n6 = this.KC - n5 * this.pZ;
        if (2 * n6 + n3 < -16) {
            n3 = -n6 * 2 - 16;
        }
        else if (2 * n6 + n3 > this.nZ) {
            n3 = 2 * (this.pZ - n6);
        }
        if (2 * n5 + n4 < -16) {
            n4 = -n5 * 2 - 16;
        }
        else if (2 * n5 + n4 > this.rZ << 1) {
            n4 = this.rZ - (n5 << 1);
        }
        final int n7 = n3 >> 1;
        final int n8 = n3 & 0x1;
        final int n9 = n4 >> 1;
        final int n10 = n4 & 0x1;
        final int n11 = 2 * n6 + n3;
        final int n12 = 2 * n5 + n4;
        final int z = Z(n11, n12, this.pZ, this.rZ >> 1, 9);
        if (z != -1) {
            I(z, n11, n12, this.pZ, this.rZ >> 1, 9, this.DC.U, this.VC);
            I(z, n11, n12, this.pZ, this.rZ >> 1, 9, this.DC.V, this.WC);
        }
        if (this.fZ == 0 && !this.gI && !I(n11, n12, this.pZ, this.rZ >> 1, 8) && !this.aI) {
            this.aI = true;
        }
        final int kc = this.KC;
        final byte[] v = this.xZ.V;
        final byte[] u = this.xZ.U;
        byte[] array;
        int n13;
        byte[] array2;
        int pz;
        if (z != -1) {
            array = this.VC;
            n13 = 0;
            array2 = this.WC;
            pz = 9;
        }
        else {
            n13 = this.KC + n9 * this.pZ + n7;
            array2 = this.DC.V;
            array = this.DC.U;
            pz = this.pZ;
        }
        if (n8 != 0 && n10 != 0) {
            this.s(array, n13, u, kc, pz, this.pZ);
            this.s(array2, n13, v, kc, pz, this.pZ);
        }
        else if (n8 != 0 || n10 != 0) {
            this.t(array, n13, u, kc, n8, n10, pz, this.pZ);
            this.t(array2, n13, v, kc, n8, n10, pz, this.pZ);
        }
        else {
            u(array, n13, u, kc, pz, this.pZ);
            u(array2, n13, v, kc, pz, this.pZ);
        }
    }
    
    private static final boolean I(final int n, final int n2, final int n3, final int n4, final int n5) {
        final int n6 = n3 << 1;
        final int n7 = n4 << 1;
        final int n8 = n5 << 1;
        return n >= 0 && n <= n6 - n8 && n2 >= 0 && n2 <= n7 - n8;
    }
    
    private static final int Z(final int n, final int n2, final int n3, final int n4, final int n5) {
        int n6 = -1;
        final int n7 = n3 << 1;
        final int n8 = n4 << 1;
        final int n9 = (n5 << 1) - 1;
        if (n < 0) {
            if (n2 < 0) {
                n6 = 0;
            }
            else if (n2 > n8 - n9) {
                n6 = 1;
            }
            else {
                n6 = 2;
            }
        }
        else if (n > n7 - n9) {
            if (n2 < 0) {
                n6 = 3;
            }
            else if (n2 > n8 - n9) {
                n6 = 4;
            }
            else {
                n6 = 5;
            }
        }
        else if (n2 < 0) {
            n6 = 6;
        }
        else if (n2 > n8 - n9) {
            n6 = 7;
        }
        return n6;
    }
    
    private static final void I(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final byte[] array, final byte[] array2) {
        switch (n) {
            case 0: {
                final int n7 = -(n2 >> 1);
                final int n8 = -(n3 >> 1);
                for (int i = 0; i < n6 - n8; ++i) {
                    System.arraycopy(array, i * n4, array2, n7 + n6 * (n8 + i), n6 - n7);
                }
                for (int j = 0; j < n8; ++j) {
                    for (int k = 0; k < n7; ++k) {
                        array2[j * n6 + k] = array[0];
                    }
                }
                for (int l = n7; l < n6; ++l) {
                    for (int n9 = 0; n9 < n8; ++n9) {
                        array2[n9 * n6 + l] = array[l - n7];
                    }
                }
                for (int n10 = n8; n10 < n6; ++n10) {
                    for (int n11 = 0; n11 < n7; ++n11) {
                        array2[n10 * n6 + n11] = array[(n10 - n8) * n4];
                    }
                }
                break;
            }
            case 1: {
                final int n12 = -(n2 >> 1);
                int n13 = n5 - 1 - (n3 >> 1);
                if (n13 < 0) {
                    n13 = 0;
                }
                for (int n14 = 0; n14 <= n13; ++n14) {
                    System.arraycopy(array, (n5 - n13 + n14 - 1) * n4, array2, n12 + n6 * n14, n6 - n12);
                }
                final byte b = array[(n5 - 1) * n4];
                for (int n15 = n13 + 1; n15 < n6; ++n15) {
                    for (int n16 = 0; n16 < n12; ++n16) {
                        array2[n15 * n6 + n16] = b;
                    }
                }
                for (int n17 = 0; n17 < n12; ++n17) {
                    for (int n18 = 0; n18 <= n13; ++n18) {
                        array2[n18 * n6 + n17] = array[(n5 + n18 - n13 - 1) * n4];
                    }
                }
                for (int n19 = n12; n19 < n6; ++n19) {
                    final byte b2 = array[(n5 - 1) * n4 + n19 - n12];
                    for (int n20 = n13 + 1; n20 < n6; ++n20) {
                        array2[n20 * n6 + n19] = b2;
                    }
                }
                break;
            }
            case 2: {
                final int n21 = -(n2 >> 1);
                final int n22 = n3 >> 1;
                for (int n23 = 0; n23 < n6; ++n23) {
                    System.arraycopy(array, (n22 + n23) * n4, array2, n21 + n6 * n23, n6 - n21);
                }
                for (int n24 = 0; n24 < n6; ++n24) {
                    final byte b3 = array[(n22 + n24) * n4];
                    for (int n25 = 0; n25 < n21; ++n25) {
                        array2[n24 * n6 + n25] = b3;
                    }
                }
                break;
            }
            case 3: {
                int n26 = n4 - 1 - (n2 >> 1);
                if (n26 < 0) {
                    n26 = 0;
                }
                int n28;
                int n27;
                for (n27 = (n28 = -(n3 >> 1)); n28 < n6; ++n28) {
                    System.arraycopy(array, (n28 - n27) * n4 + n4 - n26 - 1, array2, n6 * n28, n26 + 1);
                }
                final byte b4 = array[n4 - 1];
                for (int n29 = 0; n29 < n27; ++n29) {
                    for (int n30 = n26 + 1; n30 < n6; ++n30) {
                        array2[n29 * n6 + n30] = b4;
                    }
                }
                for (int n31 = 0; n31 <= n26; ++n31) {
                    final byte b5 = array[n4 - n26 + n31 - 1];
                    for (int n32 = 0; n32 < n27; ++n32) {
                        array2[n32 * n6 + n31] = b5;
                    }
                }
                for (int n33 = n27; n33 < n6; ++n33) {
                    final byte b6 = array[(n33 - n27) * n4 + n4 - 1];
                    for (int n34 = n26 + 1; n34 < n6; ++n34) {
                        array2[n33 * n6 + n34] = b6;
                    }
                }
                break;
            }
            case 4: {
                int n35 = n4 - 1 - (n2 >> 1);
                if (n35 < 0) {
                    n35 = 0;
                }
                int n36 = n5 - 1 - (n3 >> 1);
                if (n36 < 0) {
                    n36 = 0;
                }
                for (int n37 = 0; n37 <= n36; ++n37) {
                    System.arraycopy(array, (n5 - n36 + n37 - 1) * n4 + n4 - n35 - 1, array2, n6 * n37, n35 + 1);
                }
                final byte b7 = array[n4 * n5 - 1];
                for (int n38 = n36 + 1; n38 < n6; ++n38) {
                    for (int n39 = n35 + 1; n39 < n6; ++n39) {
                        array2[n38 * n6 + n39] = b7;
                    }
                }
                for (int n40 = 0; n40 <= n35; ++n40) {
                    final byte b8 = array[n4 * n5 - n35 + n40 - 1];
                    for (int n41 = n36 + 1; n41 < n6; ++n41) {
                        array2[n41 * n6 + n40] = b8;
                    }
                }
                for (int n42 = 0; n42 <= n36; ++n42) {
                    final byte b9 = array[(n5 - n36 + n42) * n4 - 1];
                    for (int n43 = n35 + 1; n43 < n6; ++n43) {
                        array2[n42 * n6 + n43] = b9;
                    }
                }
                break;
            }
            case 5: {
                int n44 = n4 - (n2 >> 1) - 1;
                if (n44 < 0) {
                    n44 = 0;
                }
                final int n45 = n3 >> 1;
                for (int n46 = 0; n46 < n6; ++n46) {
                    System.arraycopy(array, (n45 + n46) * n4 + n4 - n44 - 1, array2, n6 * n46, n44 + 1);
                }
                for (int n47 = 0; n47 < n6; ++n47) {
                    final byte b10 = array[(n45 + n47) * n4 + n4 - 1];
                    for (int n48 = n44 + 1; n48 < n6; ++n48) {
                        array2[n47 * n6 + n48] = b10;
                    }
                }
                break;
            }
            case 6: {
                final int n49 = n2 >> 1;
                int n51;
                int n50;
                for (n50 = (n51 = -(n3 >> 1)); n51 < n6; ++n51) {
                    System.arraycopy(array, (n51 - n50) * n4 + n49, array2, n6 * n51, n6);
                }
                for (int n52 = 0; n52 < n6; ++n52) {
                    final byte b11 = array[n49 + n52];
                    for (int n53 = 0; n53 < n50; ++n53) {
                        array2[n53 * n6 + n52] = b11;
                    }
                }
                break;
            }
            case 7: {
                final int n54 = n2 >> 1;
                int n55 = n5 - 1 - (n3 >> 1);
                if (n55 < 0) {
                    n55 = 0;
                }
                for (int n56 = 0; n56 <= n55; ++n56) {
                    System.arraycopy(array, (n5 - 1 + n56 - n55) * n4 + n54, array2, n6 * n56, n6);
                }
                for (int n57 = 0; n57 < n6; ++n57) {
                    final byte b12 = array[(n5 - 1) * n4 + n54 + n57];
                    for (int n58 = n55 + 1; n58 < n6; ++n58) {
                        array2[n58 * n6 + n57] = b12;
                    }
                }
                break;
            }
        }
    }
    
    private final void f() {
        int x = this.YC[this.AC][0].x;
        int y = this.YC[this.AC][0].y;
        final int n = this.HC / this.nZ;
        final int n2 = this.HC - n * this.nZ;
        if (this.gI) {
            if ((n2 << 1) + x < -32) {
                x = -(n2 << 1) - 32;
            }
            else if ((n2 << 1) + x > 2 * this.nZ) {
                x = 2 * (this.nZ - n2);
            }
            if ((n << 1) + y < -32) {
                y = -(n << 1) - 32;
            }
            else if ((n << 1) + y > 2 * this.rZ) {
                y = this.rZ - n << 1;
            }
        }
        else {
            if (x < -32 && this.AC == 0) {
                x = -32;
            }
            else if (x > 32 && this.AC == this.EC - 1) {
                x = 32;
            }
            if (y < -32 && this.gZ == 0) {
                y = -32;
            }
            else if (y > 32 && this.gZ == this.EC - 1) {
                y = 32;
            }
        }
        final int n3 = x >> 1;
        final int n4 = x & 0x1;
        final int n5 = y >> 1;
        final int n6 = y & 0x1;
        final int n7 = (n2 << 1) + x;
        final int n8 = (n << 1) + y;
        final int z = Z(n7, n8, this.nZ, this.rZ, 17);
        if (z != -1) {
            I(z, n7, n8, this.nZ, this.rZ, 17, this.DC.Y, this.UC);
        }
        if (this.fZ == 0 && !this.gI && !I(n7, n8, this.nZ, this.rZ, 16) && !this.aI) {
            this.aI = true;
        }
        byte[] array;
        int n9;
        int nz;
        if (z != -1) {
            array = this.UC;
            n9 = 0;
            nz = 17;
        }
        else {
            array = this.DC.Y;
            n9 = this.HC + n5 * this.nZ + n3;
            nz = this.nZ;
        }
        final byte[] y2 = this.xZ.Y;
        final int hc = this.HC;
        if (n4 != 0 && n6 != 0) {
            this.v(array, n9, y2, hc, nz, this.nZ);
        }
        else if (n4 != 0 || n6 != 0) {
            this.w(array, n9, y2, hc, n4, n6, nz, this.nZ);
        }
        else {
            x(array, n9, y2, hc, nz, this.nZ);
        }
        final int n10 = n4 | (n3 & 0x1);
        final int n11 = n3 >> 1;
        final int n12 = n6 | (n5 & 0x1);
        final int n13 = n5 >> 1;
        final byte[] u = this.xZ.U;
        final int kc = this.KC;
        final byte[] v = this.xZ.V;
        byte[] array2;
        int n14;
        byte[] array3;
        int pz;
        if (z != -1) {
            I(z, n7 >> 1, n8 >> 1, this.pZ, this.rZ >> 1, 9, this.DC.U, this.VC);
            I(z, n7 >> 1, n8 >> 1, this.pZ, this.rZ >> 1, 9, this.DC.V, this.WC);
            array2 = this.VC;
            n14 = 0;
            array3 = this.WC;
            pz = 9;
        }
        else {
            array2 = this.DC.U;
            n14 = this.KC + n13 * this.pZ + n11;
            array3 = this.DC.V;
            pz = this.pZ;
        }
        if (n10 != 0 && n12 != 0) {
            this.s(array2, n14, u, kc, pz, this.pZ);
            this.s(array3, n14, v, kc, pz, this.pZ);
        }
        else if (n10 != 0 || n12 != 0) {
            this.t(array2, n14, u, kc, n10, n12, pz, this.pZ);
            this.t(array3, n14, v, kc, n10, n12, pz, this.pZ);
        }
        else {
            u(array2, n14, u, kc, pz, this.pZ);
            u(array3, n14, v, kc, pz, this.pZ);
        }
    }
    
    private final void I(final int n, final Point point) {
        int fc;
        if (n == 0 || n == 2) {
            fc = this.FC - 1;
        }
        else {
            fc = this.FC;
        }
        if (fc < this.JC || (this.gI && fc < this.uI)) {
            point.x = -2049;
        }
        else {
            int n2;
            if (n == 0 || n == 2) {
                n2 = n + 1;
            }
            else {
                n2 = n - 1;
            }
            final int n3 = fc - this.gZ * this.SC;
            point.x = this.YC[n3][n2].x;
            point.y = this.YC[n3][n2].y;
        }
    }
    
    private final void Z(final int n, final Point point) {
        int fc;
        if (n == 0 || n == 1) {
            fc = this.FC - this.SC;
        }
        else {
            fc = this.FC;
        }
        if ((this.GC && n < 2) || (this.gI && fc < this.uI)) {
            point.x = -2049;
        }
        else if (n == 0 || n == 1) {
            point.x = this.iC[this.AC][n + 2].x;
            point.y = this.iC[this.AC][n + 2].y;
        }
        else {
            point.x = this.YC[this.AC][0].x;
            point.y = this.YC[this.AC][0].y;
        }
    }
    
    private final void C(final int n, final Point point) {
        int fc;
        if (n == 0 || n == 1) {
            fc = this.FC - this.SC + 1;
        }
        else {
            fc = this.FC;
        }
        if (n < 2 && (this.GC || (this.gI && fc < this.uI) || fc - this.JC == 0)) {
            point.x = -2049;
        }
        else if (n < 2) {
            point.x = this.iC[this.AC + 1][2].x;
            point.y = this.iC[this.AC + 1][2].y;
        }
        else {
            point.x = this.YC[this.AC][1].x;
            point.y = this.YC[this.AC][1].y;
        }
    }
    
    private final int S(final int n) {
        final int z = this.z();
        int n2;
        if (this.gI && this.qI != 1 && z != 0) {
            n2 = (Math.abs(z) - 1) * n + this.G(this.qI - 1) + 1;
            if (z < 0) {
                n2 = -n2;
            }
        }
        else {
            n2 = z;
        }
        return n2;
    }
    
    private final int A(final int n) {
        final int z = this.z();
        int n2;
        if (this.gI && this.qI != 1 && z != 0) {
            n2 = (Math.abs(z) - 1) * n + this.G(this.qI - 1) + 1;
            if (z < 0) {
                n2 = -n2;
            }
        }
        else {
            n2 = z;
        }
        return n2;
    }
    
    private final void E(final int n) {
        final int n2 = 1 << this.qI - 1;
        int s;
        if (this.tI == 0) {
            s = this.S(n2);
        }
        else {
            s = this.LZ[this.FC / this.SC][this.FC - this.JC][n][0];
        }
        int a;
        if (this.tI == 0) {
            a = this.A(n2);
        }
        else {
            a = this.LZ[this.FC / this.SC][this.FC - this.JC][n][1];
        }
        this.I(n, this.zC);
        this.Z(n, this.cC);
        this.C(n, this.bC);
        int x;
        int y;
        if (this.zC.x == -2049) {
            if (this.cC.x == -2049) {
                if (this.bC.x == -2049) {
                    x = this.y(s, 0, n2);
                    y = this.y(a, 0, n2);
                }
                else {
                    x = this.y(s, this.bC.x, n2);
                    y = this.y(a, this.bC.y, n2);
                }
            }
            else if (this.bC.x == -2049) {
                x = this.y(s, this.cC.x, n2);
                y = this.y(a, this.cC.y, n2);
            }
            else {
                final int z = z(0, this.cC.x, this.bC.x);
                final int z2 = z(0, this.cC.y, this.bC.y);
                x = this.y(s, z, n2);
                y = this.y(a, z2, n2);
            }
        }
        else if (this.cC.x == -2049) {
            if (this.bC.x == -2049) {
                x = this.y(s, this.zC.x, n2);
                y = this.y(a, this.zC.y, n2);
            }
            else {
                final int z3 = z(this.zC.x, 0, this.bC.x);
                final int z4 = z(this.zC.y, 0, this.bC.y);
                x = this.y(s, z3, n2);
                y = this.y(a, z4, n2);
            }
        }
        else if (this.bC.x == -2049) {
            final int z5 = z(this.zC.x, this.cC.x, 0);
            final int z6 = z(this.zC.y, this.cC.y, 0);
            x = this.y(s, z5, n2);
            y = this.y(a, z6, n2);
        }
        else {
            final int z7 = z(this.zC.x, this.cC.x, this.bC.x);
            final int z8 = z(this.zC.y, this.cC.y, this.bC.y);
            x = this.y(s, z7, n2);
            y = this.y(a, z8, n2);
        }
        if (n == 0) {
            final Point point = this.YC[this.AC][0];
            final Point point2 = this.YC[this.AC][1];
            final Point point3 = this.YC[this.AC][2];
            final Point point4 = this.YC[this.AC][3];
            final int n3 = x;
            point4.x = n3;
            point3.x = n3;
            point2.x = n3;
            point.x = n3;
            final Point point5 = this.YC[this.AC][0];
            final Point point6 = this.YC[this.AC][1];
            final Point point7 = this.YC[this.AC][2];
            final Point point8 = this.YC[this.AC][3];
            final int n4 = y;
            point8.y = n4;
            point7.y = n4;
            point6.y = n4;
            point5.y = n4;
        }
        else {
            this.YC[this.AC][n].x = x;
            this.YC[this.AC][n].y = y;
        }
    }
    
    private static final int z(final int n, final int n2, final int n3) {
        return (n < n2) ? ((n < n3) ? ((n2 < n3) ? n2 : n3) : n) : ((n2 < n3) ? ((n < n3) ? n : n3) : n2);
    }
    
    private final int y(final int n, final int n2, final int n3) {
        if (this.gI) {
            final int n4 = 32 * n3 - 1;
            final int n5 = -32 * n3;
            final int n6 = 64 * n3;
            int n7 = n2 + n;
            if (n7 < n5) {
                n7 += n6;
            }
            if (n7 > n4) {
                n7 -= n6;
            }
            return n7;
        }
        return (this.fZ != 0) ? ((n2 < 33) ? ((n2 > -32) ? (n + n2) : (-(-(n + n2) & 0x3F))) : (n + n2 & 0x3F)) : ((n + n2 + 96 & 0x3F) - 32);
    }
    
    private static final void x(final byte[] array, int n, final byte[] array2, int n2, final int n3, final int n4) {
        for (int i = 0; i < 16; ++i) {
            System.arraycopy(array, n, array2, n2, 16);
            n2 += n4;
            n += n3;
        }
    }
    
    private final void w(final byte[] array, final int n, final byte[] array2, int n2, final int n3, final int n4, final int n5, final int n6) {
        int n7 = n;
        int n8 = n + n4 * n5 + n3;
        final int n9 = 1 - this.RZ;
        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j, array2[n2 + j] = (byte)((0xFF & array[n7 + j]) + (0xFF & array[n8 + j]) + n9 >> 1), ++j, array2[n2 + j] = (byte)((0xFF & array[n7 + j]) + (0xFF & array[n8 + j]) + n9 >> 1), ++j, array2[n2 + j] = (byte)((0xFF & array[n7 + j]) + (0xFF & array[n8 + j]) + n9 >> 1), ++j) {
                array2[n2 + j] = (byte)((0xFF & array[n7 + j]) + (0xFF & array[n8 + j]) + n9 >> 1);
            }
            n2 += n6;
            n7 += n5;
            n8 += n5;
        }
    }
    
    private final void v(final byte[] array, final int n, final byte[] array2, int n2, final int n3, final int n4) {
        int n5 = n;
        int n6 = n + n3;
        final int n7 = n4 - 15;
        final int n8 = n3 - 16;
        for (int i = 0; i < 16; ++i) {
            final int n9 = 0xFF & array[n5++];
            final int n10 = 0xFF & array[n5++];
            final int n11 = 0xFF & array[n5++];
            final int n12 = 0xFF & array[n5++];
            final int n13 = 0xFF & array[n5++];
            final int n14 = 0xFF & array[n5++];
            final int n15 = 0xFF & array[n5++];
            final int n16 = 0xFF & array[n5++];
            final int n17 = 0xFF & array[n5++];
            final int n18 = 0xFF & array[n5++];
            final int n19 = 0xFF & array[n5++];
            final int n20 = 0xFF & array[n5++];
            final int n21 = 0xFF & array[n5++];
            final int n22 = 0xFF & array[n5++];
            final int n23 = 0xFF & array[n5++];
            final int n24 = 0xFF & array[n5++];
            final int n25 = 0xFF & array[n5];
            final int n26 = 0xFF & array[n6++];
            final int n27 = 0xFF & array[n6++];
            final int n28 = 0xFF & array[n6++];
            final int n29 = 0xFF & array[n6++];
            final int n30 = 0xFF & array[n6++];
            final int n31 = 0xFF & array[n6++];
            final int n32 = 0xFF & array[n6++];
            final int n33 = 0xFF & array[n6++];
            final int n34 = 0xFF & array[n6++];
            final int n35 = 0xFF & array[n6++];
            final int n36 = 0xFF & array[n6++];
            final int n37 = 0xFF & array[n6++];
            final int n38 = 0xFF & array[n6++];
            final int n39 = 0xFF & array[n6++];
            final int n40 = 0xFF & array[n6++];
            final int n41 = 0xFF & array[n6++];
            final int n42 = 0xFF & array[n6];
            array2[n2++] = (byte)(n9 + n10 + n26 + n27 + 2 - this.RZ >> 2);
            array2[n2++] = (byte)(n10 + n11 + n27 + n28 + 2 - this.RZ >> 2);
            array2[n2++] = (byte)(n11 + n12 + n28 + n29 + 2 - this.RZ >> 2);
            array2[n2++] = (byte)(n12 + n13 + n29 + n30 + 2 - this.RZ >> 2);
            array2[n2++] = (byte)(n13 + n14 + n30 + n31 + 2 - this.RZ >> 2);
            array2[n2++] = (byte)(n14 + n15 + n31 + n32 + 2 - this.RZ >> 2);
            array2[n2++] = (byte)(n15 + n16 + n32 + n33 + 2 - this.RZ >> 2);
            array2[n2++] = (byte)(n16 + n17 + n33 + n34 + 2 - this.RZ >> 2);
            array2[n2++] = (byte)(n17 + n18 + n34 + n35 + 2 - this.RZ >> 2);
            array2[n2++] = (byte)(n18 + n19 + n35 + n36 + 2 - this.RZ >> 2);
            array2[n2++] = (byte)(n19 + n20 + n36 + n37 + 2 - this.RZ >> 2);
            array2[n2++] = (byte)(n20 + n21 + n37 + n38 + 2 - this.RZ >> 2);
            array2[n2++] = (byte)(n21 + n22 + n38 + n39 + 2 - this.RZ >> 2);
            array2[n2++] = (byte)(n22 + n23 + n39 + n40 + 2 - this.RZ >> 2);
            array2[n2++] = (byte)(n23 + n24 + n40 + n41 + 2 - this.RZ >> 2);
            array2[n2] = (byte)(n24 + n25 + n41 + n42 + 2 - this.RZ >> 2);
            n2 += n7;
            n5 += n8;
            n6 += n8;
        }
    }
    
    private static final void u(final byte[] array, int n, final byte[] array2, int n2, final int n3, final int n4) {
        for (int i = 0; i < 8; ++i) {
            System.arraycopy(array, n, array2, n2, 8);
            n2 += n4;
            n += n3;
        }
    }
    
    private final void t(final byte[] array, final int n, final byte[] array2, int n2, final int n3, final int n4, final int n5, final int n6) {
        int n7 = n;
        int n8 = n + n4 * n5 + n3;
        final int n9 = n6 - 8;
        final int n10 = 1 - this.RZ;
        for (int i = 0; i < 8; ++i) {
            array2[n2++] = (byte)((0xFF & array[n7]) + (0xFF & array[n8]) + n10 >> 1);
            array2[n2++] = (byte)((0xFF & array[n7 + 1]) + (0xFF & array[n8 + 1]) + n10 >> 1);
            array2[n2++] = (byte)((0xFF & array[n7 + 2]) + (0xFF & array[n8 + 2]) + n10 >> 1);
            array2[n2++] = (byte)((0xFF & array[n7 + 3]) + (0xFF & array[n8 + 3]) + n10 >> 1);
            array2[n2++] = (byte)((0xFF & array[n7 + 4]) + (0xFF & array[n8 + 4]) + n10 >> 1);
            array2[n2++] = (byte)((0xFF & array[n7 + 5]) + (0xFF & array[n8 + 5]) + n10 >> 1);
            array2[n2++] = (byte)((0xFF & array[n7 + 6]) + (0xFF & array[n8 + 6]) + n10 >> 1);
            array2[n2++] = (byte)((0xFF & array[n7 + 7]) + (0xFF & array[n8 + 7]) + n10 >> 1);
            n2 += n9;
            n7 += n5;
            n8 += n5;
        }
    }
    
    private final void s(final byte[] array, final int n, final byte[] array2, int n2, final int n3, final int n4) {
        int n5 = n;
        int n6 = n + n3;
        final int n7 = n4 - 7;
        final int n8 = n3 - 8;
        for (int i = 0; i < 8; ++i) {
            final int n9 = 0xFF & array[n5++];
            final int n10 = 0xFF & array[n5++];
            final int n11 = 0xFF & array[n5++];
            final int n12 = 0xFF & array[n5++];
            final int n13 = 0xFF & array[n5++];
            final int n14 = 0xFF & array[n5++];
            final int n15 = 0xFF & array[n5++];
            final int n16 = 0xFF & array[n5++];
            final int n17 = 0xFF & array[n5];
            final int n18 = 0xFF & array[n6++];
            final int n19 = 0xFF & array[n6++];
            final int n20 = 0xFF & array[n6++];
            final int n21 = 0xFF & array[n6++];
            final int n22 = 0xFF & array[n6++];
            final int n23 = 0xFF & array[n6++];
            final int n24 = 0xFF & array[n6++];
            final int n25 = 0xFF & array[n6++];
            final int n26 = 0xFF & array[n6];
            array2[n2++] = (byte)(n9 + n10 + n18 + n19 + 2 - this.RZ >> 2);
            array2[n2++] = (byte)(n10 + n11 + n19 + n20 + 2 - this.RZ >> 2);
            array2[n2++] = (byte)(n11 + n12 + n20 + n21 + 2 - this.RZ >> 2);
            array2[n2++] = (byte)(n12 + n13 + n21 + n22 + 2 - this.RZ >> 2);
            array2[n2++] = (byte)(n13 + n14 + n22 + n23 + 2 - this.RZ >> 2);
            array2[n2++] = (byte)(n14 + n15 + n23 + n24 + 2 - this.RZ >> 2);
            array2[n2++] = (byte)(n15 + n16 + n24 + n25 + 2 - this.RZ >> 2);
            array2[n2] = (byte)(n16 + n17 + n25 + n26 + 2 - this.RZ >> 2);
            n2 += n7;
            n5 += n8;
            n6 += n8;
        }
    }
    
    private final boolean r() {
        if (this.H(16) == 0) {
            int n = 0;
            int h;
            while ((h = this.H(17)) != 1 && (this.l & 0x7) != 0x0) {
                this.K(1);
                ++n;
            }
            return h == 1;
        }
        return false;
    }
    
    private static final int q(final byte[] array, final int n) {
        int n2;
        if ((array[n] & 0x80) == 0x0) {
            n2 = 0;
        }
        else if ((array[n] & 0x40) == 0x0) {
            n2 = 1;
        }
        else {
            n2 = 2;
        }
        return n2;
    }
    
    private static final int p(final byte[] array, final int n) {
        return (array[n] & 0x38) >> 3;
    }
    
    private static final int o(final byte[] array, final int n) {
        return array[n] & 0x7;
    }
    
    private static final int n(final byte[] array, final int n) {
        return (array[n + 1] & 0xE0) >> 5;
    }
    
    private final void m() {
        this.i[0] = this.j();
        this.i[1] = this.j();
        this.l = 32;
    }
    
    private final int j() {
        int n = this.TB + this.VB - this.UB;
        if (n >= 4) {
            return this.RB[this.UB++] << 24 | (this.RB[this.UB++] & 0xFF) << 16 | (this.RB[this.UB++] & 0xFF) << 8 | (this.RB[this.UB++] & 0xFF);
        }
        if (this.WB) {
            synchronized (this.YB) {
                final boolean xb = true;
                this.XB = xb;
                int n2 = xb ? 1 : 0;
                this.YB.notify();
                while (this.XB && n2 != 0) {
                    try {
                        this.YB.wait();
                    }
                    catch (InterruptedException ex) {
                        n2 = 0;
                    }
                    if (n2 != 0) {
                        return this.j();
                    }
                }
            }
        }
        int n3 = 0;
        for (int i = 0; i < 4; ++i) {
            n3 <<= 8;
            if (n != 0) {
                n3 |= (this.RB[this.UB++] & 0xFF);
                --n;
            }
        }
        return n3;
    }
    
    private final int G(final int n) {
        final int h = this.H(n);
        this.K(n);
        return h;
    }
    
    private final int H(final int n) {
        return this.i[0] >>> 32 - n;
    }
    
    private final void K(final int n) {
        final int l = this.l - n;
        if (l > 0) {
            this.i[0] = (this.i[0] << n | this.i[1] >>> 32 - n);
            final int[] i = this.i;
            final int n2 = 1;
            i[n2] <<= n;
            this.l = l;
        }
        else {
            if (this.l != 0) {
                this.i[0] = (this.i[0] << this.l | this.i[1] >>> 32 - this.l);
            }
            this.i[1] = this.j();
            this.l = 32 + l;
            if (l != 0) {
                this.i[0] = (this.i[0] << -l | this.i[1] >>> this.l);
                final int[] j = this.i;
                final int n3 = 1;
                j[n3] <<= -l;
            }
        }
    }
    
    public final int s() {
        return (this.UB - this.VB <= this.TB) ? (this.UB - this.VB - (4 + (this.l >> 3))) : this.TB;
    }
    
    private final void g(final int[] array, final byte[] array2, final int n, final int n2) {
        final int n3 = this.JZ >> 8;
        final int n4 = this.JZ & 0xFF;
        final int n5 = 0;
        array[n5] += 4096;
        if (n3 == 1) {
            if (n4 == 1) {
                this.f(array, array2, n, n2);
            }
            else {
                this.e(array, array2, n, n2);
            }
        }
        else if (n4 == 1) {
            this.d(array, array2, n, n2);
        }
        else {
            this.c(array, array2, n, n2, n4 | 0x1);
        }
    }
    
    private final void f(final int[] array, final byte[] array2, final int n, final int n2) {
        final int n3 = array[0] >> 13;
        if (this.BZ == 0) {
            final byte b = this.FZ[256 + n3];
            int n4 = n;
            for (int i = 8; i > 0; --i) {
                for (int j = 0; j < 8; ++j) {
                    array2[n4 + j] = b;
                }
                n4 += n2;
            }
        }
        else {
            int n5 = n;
            final int n6 = 256 + n3;
            for (int k = 8; k > 0; --k) {
                array2[n5] = this.FZ[n6 + (array2[n5] & 0xFF)];
                ++n5;
                array2[n5] = this.FZ[n6 + (array2[n5] & 0xFF)];
                ++n5;
                array2[n5] = this.FZ[n6 + (array2[n5] & 0xFF)];
                ++n5;
                array2[n5] = this.FZ[n6 + (array2[n5] & 0xFF)];
                ++n5;
                array2[n5] = this.FZ[n6 + (array2[n5] & 0xFF)];
                ++n5;
                array2[n5] = this.FZ[n6 + (array2[n5] & 0xFF)];
                ++n5;
                array2[n5] = this.FZ[n6 + (array2[n5] & 0xFF)];
                ++n5;
                array2[n5] = this.FZ[n6 + (array2[n5] & 0xFF)];
                n5 += n2 - 7;
            }
        }
    }
    
    private final void e(final int[] array, final byte[] array2, final int n, final int n2) {
        final int n3 = array[1];
        final int n4 = array[3];
        final int n5 = array[7];
        final int n6 = array[5];
        final int n7 = n3 + n5;
        final int n8 = -n4 + n6;
        final int n9 = n3 - n5;
        final int n10 = n4 + n6;
        final int n11 = n7 + n8;
        final int n12 = -n7 + n8;
        final int n13 = (n12 >> 1) + (n12 >> 3) + (n12 >> 4) + (n12 >> 6) + (n12 >> 8);
        final int n14 = (n9 >> 2) + (n9 >> 3) + (n9 >> 8) + (n9 >> 9) + (n9 >> 10) + (n9 >> 11) + (n9 >> 12) + ((n10 >> 1) + (n10 >> 2) + (n10 >> 3) + (n10 >> 5) + (n10 >> 6) + (n10 >> 9));
        final int n15 = (n9 >> 1) + (n9 >> 2) + (n9 >> 3) + (n9 >> 5) + (n9 >> 6) + (n9 >> 9) - ((n10 >> 2) + (n10 >> 3) + (n10 >> 8) + (n10 >> 9) + (n10 >> 10) + (n10 >> 11) + (n10 >> 12));
        final int n16 = -n13 + n14;
        final int n17 = n11 - n14;
        final int n18 = n13 + n15;
        final int n19 = array[0];
        final int n20 = array[4];
        final int n21 = array[2];
        final int n22 = array[6];
        final int n23 = n21 - n22;
        final int n24 = n21 + n22;
        final int n25 = (n24 >> 1) + (n24 >> 3) + (n24 >> 4) + (n24 >> 6) + (n24 >> 8);
        final int n26 = n19 + n20;
        final int n27 = n19 - n20;
        final int n28 = n23 - n25;
        final int n29 = n26 + n25;
        final int n30 = n27 + n28;
        final int n31 = n27 - n28;
        final int n32 = n26 - n25;
        if (this.BZ == 0) {
            this.DZ[0] = this.FZ[256 + (n29 + n16 >> 13)];
            this.DZ[1] = this.FZ[256 + (n30 + n15 >> 13)];
            this.DZ[2] = this.FZ[256 + (n31 + n17 >> 13)];
            this.DZ[3] = this.FZ[256 + (n32 + n18 >> 13)];
            this.DZ[4] = this.FZ[256 + (n32 - n18 >> 13)];
            this.DZ[5] = this.FZ[256 + (n31 - n17 >> 13)];
            this.DZ[6] = this.FZ[256 + (n30 - n15 >> 13)];
            this.DZ[7] = this.FZ[256 + (n29 - n16 >> 13)];
            int n33 = n;
            for (int i = 8; i > 0; --i) {
                for (int j = 0; j < 8; ++j) {
                    array2[n33 + j] = this.DZ[j];
                }
                n33 += n2;
            }
        }
        else {
            int n34 = n;
            for (int k = 8; k > 0; --k) {
                array2[n34] = this.FZ[256 + ((n29 + n16 >> 13) + (array2[n34] & 0xFF))];
                ++n34;
                array2[n34] = this.FZ[256 + ((n30 + n15 >> 13) + (array2[n34] & 0xFF))];
                ++n34;
                array2[n34] = this.FZ[256 + ((n31 + n17 >> 13) + (array2[n34] & 0xFF))];
                ++n34;
                array2[n34] = this.FZ[256 + ((n32 + n18 >> 13) + (array2[n34] & 0xFF))];
                ++n34;
                array2[n34] = this.FZ[256 + ((n32 - n18 >> 13) + (array2[n34] & 0xFF))];
                ++n34;
                array2[n34] = this.FZ[256 + ((n31 - n17 >> 13) + (array2[n34] & 0xFF))];
                ++n34;
                array2[n34] = this.FZ[256 + ((n30 - n15 >> 13) + (array2[n34] & 0xFF))];
                ++n34;
                array2[n34] = this.FZ[256 + ((n29 - n16 >> 13) + (array2[n34] & 0xFF))];
                n34 += n2 - 7;
            }
        }
    }
    
    private final void d(final int[] array, final byte[] array2, final int n, final int n2) {
        final int n3 = array[8];
        final int n4 = array[24];
        final int n5 = array[56];
        final int n6 = array[40];
        final int n7 = n3 + n5;
        final int n8 = -n4 + n6;
        final int n9 = n3 - n5;
        final int n10 = n4 + n6;
        final int n11 = n7 + n8;
        final int n12 = -n7 + n8;
        final int n13 = (n12 >> 1) + (n12 >> 3) + (n12 >> 4) + (n12 >> 6) + (n12 >> 8);
        final int n14 = (n9 >> 2) + (n9 >> 3) + (n9 >> 8) + (n9 >> 9) + (n9 >> 10) + (n9 >> 11) + (n9 >> 12) + ((n10 >> 1) + (n10 >> 2) + (n10 >> 3) + (n10 >> 5) + (n10 >> 6) + (n10 >> 9));
        final int n15 = (n9 >> 1) + (n9 >> 2) + (n9 >> 3) + (n9 >> 5) + (n9 >> 6) + (n9 >> 9) - ((n10 >> 2) + (n10 >> 3) + (n10 >> 8) + (n10 >> 9) + (n10 >> 10) + (n10 >> 11) + (n10 >> 12));
        final int n16 = -n13 + n14;
        final int n17 = n11 - n14;
        final int n18 = n13 + n15;
        final int n19 = array[0];
        final int n20 = array[32];
        final int n21 = array[16];
        final int n22 = array[48];
        final int n23 = n21 - n22;
        final int n24 = n21 + n22;
        final int n25 = (n24 >> 1) + (n24 >> 3) + (n24 >> 4) + (n24 >> 6) + (n24 >> 8);
        final int n26 = n19 + n20;
        final int n27 = n19 - n20;
        final int n28 = n23 - n25;
        final int n29 = n26 + n25;
        final int n30 = n27 + n28;
        final int n31 = n27 - n28;
        final int n32 = n26 - n25;
        array[0] = n29 + n16 >> 13;
        array[8] = n30 + n15 >> 13;
        array[16] = n31 + n17 >> 13;
        array[24] = n32 + n18 >> 13;
        array[32] = n32 - n18 >> 13;
        array[40] = n31 - n17 >> 13;
        array[48] = n30 - n15 >> 13;
        array[56] = n29 - n16 >> 13;
        if (this.BZ == 0) {
            int n33 = n;
            for (int i = 0; i < 57; i += 8) {
                final byte b = this.FZ[256 + array[i]];
                for (int j = 0; j < 8; ++j) {
                    array2[n33 + j] = b;
                }
                n33 += n2;
            }
        }
        else {
            int n34 = n;
            for (int k = 0; k < 8; ++k) {
                final int n35 = array[k << 3] + 256;
                array2[n34] = this.FZ[n35 + (array2[n34] & 0xFF)];
                ++n34;
                array2[n34] = this.FZ[n35 + (array2[n34] & 0xFF)];
                ++n34;
                array2[n34] = this.FZ[n35 + (array2[n34] & 0xFF)];
                ++n34;
                array2[n34] = this.FZ[n35 + (array2[n34] & 0xFF)];
                ++n34;
                array2[n34] = this.FZ[n35 + (array2[n34] & 0xFF)];
                ++n34;
                array2[n34] = this.FZ[n35 + (array2[n34] & 0xFF)];
                ++n34;
                array2[n34] = this.FZ[n35 + (array2[n34] & 0xFF)];
                ++n34;
                array2[n34] = this.FZ[n35 + (array2[n34] & 0xFF)];
                n34 += n2 - 7;
            }
        }
    }
    
    private final void c(final int[] array, final byte[] array2, final int n, final int n2, int n3) {
        for (int i = 0; i < 8; ++i, n3 >>= 1) {
            if ((n3 & 0x1) != 0x0) {
                final int n4 = array[i + 8];
                final int n5 = array[i + 24];
                final int n6 = array[i + 56];
                final int n7 = array[i + 40];
                final int n8 = n4 + n6;
                final int n9 = -n5 + n7;
                final int n10 = n4 - n6;
                final int n11 = n5 + n7;
                final int n12 = n8 + n9;
                final int n13 = -n8 + n9;
                final int n14 = (n13 >> 1) + (n13 >> 3) + (n13 >> 4) + (n13 >> 6) + (n13 >> 8);
                final int n15 = (n10 >> 2) + (n10 >> 3) + (n10 >> 8) + (n10 >> 9) + (n10 >> 10) + (n10 >> 11) + (n10 >> 12) + ((n11 >> 1) + (n11 >> 2) + (n11 >> 3) + (n11 >> 5) + (n11 >> 6) + (n11 >> 9));
                final int n16 = (n10 >> 1) + (n10 >> 2) + (n10 >> 3) + (n10 >> 5) + (n10 >> 6) + (n10 >> 9) - ((n11 >> 2) + (n11 >> 3) + (n11 >> 8) + (n11 >> 9) + (n11 >> 10) + (n11 >> 11) + (n11 >> 12));
                final int n17 = -n14 + n15;
                final int n18 = n12 - n15;
                final int n19 = n14 + n16;
                final int n20 = array[i + 0];
                final int n21 = array[i + 32];
                final int n22 = array[i + 16];
                final int n23 = array[i + 48];
                final int n24 = n22 - n23;
                final int n25 = n22 + n23;
                final int n26 = (n25 >> 1) + (n25 >> 3) + (n25 >> 4) + (n25 >> 6) + (n25 >> 8);
                final int n27 = n20 + n21;
                final int n28 = n20 - n21;
                final int n29 = n24 - n26;
                final int n30 = n27 + n26;
                final int n31 = n28 + n29;
                final int n32 = n28 - n29;
                final int n33 = n27 - n26;
                array[i + 0] = n30 + n17;
                array[i + 8] = n31 + n16;
                array[i + 16] = n32 + n18;
                array[i + 24] = n33 + n19;
                array[i + 32] = n33 - n19;
                array[i + 40] = n32 - n18;
                array[i + 48] = n31 - n16;
                array[i + 56] = n30 - n17;
            }
        }
        for (int j = 0; j < 64; j += 8) {
            if ((array[j + 0] | array[j + 1] | array[j + 2] | array[j + 3] | array[j + 4] | array[j + 5] | array[j + 6] | array[j + 7]) != 0x0) {
                final int n34 = array[j + 1];
                final int n35 = array[j + 3];
                final int n36 = array[j + 7];
                final int n37 = array[j + 5];
                final int n38 = n34 + n36;
                final int n39 = -n35 + n37;
                final int n40 = n34 - n36;
                final int n41 = n35 + n37;
                final int n42 = n38 + n39;
                final int n43 = -n38 + n39;
                final int n44 = (n43 >> 1) + (n43 >> 3) + (n43 >> 4) + (n43 >> 6) + (n43 >> 8);
                final int n45 = (n40 >> 2) + (n40 >> 3) + (n40 >> 8) + (n40 >> 9) + (n40 >> 10) + (n40 >> 11) + (n40 >> 12) + ((n41 >> 1) + (n41 >> 2) + (n41 >> 3) + (n41 >> 5) + (n41 >> 6) + (n41 >> 9));
                final int n46 = (n40 >> 1) + (n40 >> 2) + (n40 >> 3) + (n40 >> 5) + (n40 >> 6) + (n40 >> 9) - ((n41 >> 2) + (n41 >> 3) + (n41 >> 8) + (n41 >> 9) + (n41 >> 10) + (n41 >> 11) + (n41 >> 12));
                final int n47 = -n44 + n45;
                final int n48 = n42 - n45;
                final int n49 = n44 + n46;
                final int n50 = array[j + 0];
                final int n51 = array[j + 4];
                final int n52 = array[j + 2];
                final int n53 = array[j + 6];
                final int n54 = n52 - n53;
                final int n55 = n52 + n53;
                final int n56 = (n55 >> 1) + (n55 >> 3) + (n55 >> 4) + (n55 >> 6) + (n55 >> 8);
                final int n57 = n50 + n51;
                final int n58 = n50 - n51;
                final int n59 = n54 - n56;
                final int n60 = n57 + n56;
                final int n61 = n58 + n59;
                final int n62 = n58 - n59;
                final int n63 = n57 - n56;
                array[j + 0] = n60 + n47 >> 13;
                array[j + 1] = n61 + n46 >> 13;
                array[j + 2] = n62 + n48 >> 13;
                array[j + 3] = n63 + n49 >> 13;
                array[j + 4] = n63 - n49 >> 13;
                array[j + 5] = n62 - n48 >> 13;
                array[j + 6] = n61 - n46 >> 13;
                array[j + 7] = n60 - n47 >> 13;
            }
        }
        int n64 = 0;
        int n65 = n;
        if (this.BZ == 0) {
            for (int k = 8; k > 0; --k) {
                array2[n65] = this.FZ[256 + array[n64]];
                array2[n65 + 1] = this.FZ[256 + array[n64 + 1]];
                array2[n65 + 2] = this.FZ[256 + array[n64 + 2]];
                array2[n65 + 3] = this.FZ[256 + array[n64 + 3]];
                array2[n65 + 4] = this.FZ[256 + array[n64 + 4]];
                array2[n65 + 5] = this.FZ[256 + array[n64 + 5]];
                array2[n65 + 6] = this.FZ[256 + array[n64 + 6]];
                array2[n65 + 7] = this.FZ[256 + array[n64 + 7]];
                n65 += n2;
                n64 += 8;
            }
        }
        else {
            for (int l = 8; l > 0; --l) {
                array2[n65] = this.FZ[256 + (array[n64] + (array2[n65] & 0xFF))];
                ++n65;
                array2[n65] = this.FZ[256 + (array[n64 + 1] + (array2[n65] & 0xFF))];
                ++n65;
                array2[n65] = this.FZ[256 + (array[n64 + 2] + (array2[n65] & 0xFF))];
                ++n65;
                array2[n65] = this.FZ[256 + (array[n64 + 3] + (array2[n65] & 0xFF))];
                ++n65;
                array2[n65] = this.FZ[256 + (array[n64 + 4] + (0xFF & array2[n65]))];
                ++n65;
                array2[n65] = this.FZ[256 + (array[n64 + 5] + (array2[n65] & 0xFF))];
                ++n65;
                array2[n65] = this.FZ[256 + (array[n64 + 6] + (array2[n65] & 0xFF))];
                ++n65;
                array2[n65] = this.FZ[256 + (array[n64 + 7] + (array2[n65] & 0xFF))];
                n65 += n2 - 7;
                n64 += 8;
            }
        }
    }
    
    private final void b() {
        if (this.LI == 0) {
            if (this.bZ == 0) {
                if (this.MI == 1) {
                    this.G(8);
                }
                if (this.NI == 1) {
                    this.G(8);
                }
                if (this.OI == 1) {
                    this.G(8);
                }
                if (this.PI == 1) {
                    this.G(8);
                }
                if (this.QI == 1) {
                    this.G(8);
                }
                if (this.RI == 1) {
                    this.G(8);
                }
                if (this.TI == 1) {
                    this.G(8);
                }
                if (this.WI == 1) {
                    this.G(8);
                }
                if (this.XI == 1) {
                    this.G(8);
                }
                if (this.YI == 1) {
                    this.G(8);
                }
                if (this.iI == 1) {
                    this.G(8);
                }
                if (this.zI == 1) {
                    this.G(4);
                }
            }
            if (this.bZ == 1) {
                if (this.MI == 1) {
                    this.G(8);
                }
                if (this.NI == 1) {
                    this.G(8);
                }
                if (this.OI == 1) {
                    this.G(8);
                }
                if (this.PI == 1) {
                    this.G(8);
                }
                if (this.QI == 1) {
                    this.G(8);
                }
                if (this.RI == 1) {
                    this.G(8);
                }
                if (this.TI == 1) {
                    this.G(8);
                }
                if (this.WI == 1) {
                    this.G(8);
                }
                if (this.XI == 1) {
                    this.G(8);
                }
                if (this.YI == 1) {
                    this.G(8);
                }
                if (this.iI == 1) {
                    this.G(8);
                }
                if (this.zI == 1) {
                    this.G(4);
                }
                if (this.UI == 1) {
                    this.G(8);
                }
                if (this.VI == 1) {
                    this.G(8);
                }
                if (this.cI == 1) {
                    this.G(8);
                }
                if (this.bI == 1) {
                    this.G(8);
                }
                if (this.fI == 1) {
                    this.G(8);
                }
                if (this.jI == 1) {
                    this.G(8);
                }
                if (this.sI == 1) {
                    this.G(8);
                }
            }
            if (this.bZ == 2) {
                if (this.MI == 1) {
                    this.G(8);
                }
                if (this.NI == 1) {
                    this.G(8);
                }
                if (this.OI == 1) {
                    this.G(8);
                }
                if (this.PI == 1) {
                    this.G(8);
                }
                if (this.QI == 1) {
                    this.G(8);
                }
                if (this.RI == 1) {
                    this.G(8);
                }
                if (this.TI == 1) {
                    this.G(8);
                }
                if (this.WI == 1) {
                    this.G(8);
                }
                if (this.XI == 1) {
                    this.G(8);
                }
                if (this.YI == 1) {
                    this.G(8);
                }
                if (this.iI == 1) {
                    this.G(8);
                }
                if (this.zI == 1) {
                    this.G(4);
                }
                if (this.UI == 1) {
                    this.G(8);
                }
                if (this.VI == 1) {
                    this.G(8);
                }
                if (this.cI == 1) {
                    this.G(8);
                }
                if (this.bI == 1) {
                    this.G(8);
                }
                if (this.jI == 1) {
                    this.G(8);
                }
                if (this.sI == 1) {
                    this.G(8);
                }
                if (this.dI == 1) {
                    this.G(8);
                }
                if (this.fI == 1) {
                    this.G(8);
                }
            }
        }
    }
    
    static {
        C.Z.abs = new int[] { 1024, 1303, 1892, 738, 1024, 3711, 784, 871, 1303, 1659, 2408, 940, 1303, 4724, 998, 1108, 1892, 2408, 3496, 1364, 1892, 6858, 1448, 1609, 738, 940, 1364, 532, 738, 2676, 565, 628, 1024, 1303, 1892, 738, 1024, 3711, 784, 871, 3711, 4724, 6858, 2676, 3711, 13452, 2841, 3156, 784, 998, 1448, 565, 784, 2841, 600, 667, 871, 1108, 1609, 628, 871, 3156, 667, 741 };
        C.Z.arraycopy = new int[] { 257, 258, 260, 264, 272, 288, 320, 384, 513, 514, 516, 520, 528, 544, 576, 640, 1025, 1026, 1028, 1032, 1040, 1056, 1088, 1152, 2049, 2050, 2052, 2056, 2064, 2080, 2112, 2176, 4097, 4098, 4100, 4104, 4112, 4128, 4160, 4224, 8193, 8194, 8196, 8200, 8208, 8224, 8256, 8320, 16385, 16386, 16388, 16392, 16400, 16416, 16448, 16512, 32769, 32770, 32772, 32776, 32784, 32800, 32832, 32896 };
        length = new int[] { 1, 0, 3, 4 };
        C.Z.notify = new int[] { 512, 710, 669, 602, 512, 402, 277, 141, 710, 985, 928, 835, 710, 558, 384, 196, 669, 928, 874, 787, 669, 526, 362, 185, 602, 835, 787, 708, 602, 473, 326, 166, 512, 710, 669, 602, 512, 402, 277, 141, 402, 558, 526, 473, 402, 316, 218, 111, 277, 384, 362, 326, 277, 218, 150, 76, 141, 196, 185, 166, 141, 111, 76, 39 };
        wait = new int[] { 0, 1, 2, 3, 8, 9, 16, 17, 10, 11, 4, 5, 6, 7, 15, 14, 13, 12, 19, 18, 24, 25, 32, 33, 26, 27, 20, 21, 22, 23, 28, 29, 30, 31, 34, 35, 40, 41, 48, 49, 42, 43, 36, 37, 38, 39, 44, 45, 46, 47, 50, 51, 56, 57, 58, 59, 52, 53, 54, 55, 60, 61, 62, 63 };
        x = new int[] { 0, 8, 16, 24, 1, 9, 2, 10, 17, 25, 32, 40, 48, 56, 57, 49, 41, 33, 26, 18, 3, 11, 4, 12, 19, 27, 34, 42, 50, 58, 35, 43, 51, 59, 20, 28, 5, 13, 6, 14, 21, 29, 36, 44, 52, 60, 37, 45, 53, 61, 22, 30, 7, 15, 23, 31, 38, 46, 54, 62, 39, 47, 55, 63 };
        T = new byte[] { 6, 6, 7, 7, 8, 8, 9, 9, 10, 10 };
        U = new int[] { 129, 193, 257, 321, 385, 449, 513, 577, 641, 322 };
        V = new int[] { 3, 193, 66, 385, 4, 130, 5, 6, 7, 68 };
        W = new int[] { 0, 16, 32, 40, 48, 52, 56, 58, 60, 61 };
        X = new byte[] { 11, 11, 12, 12, 13, 13, 14, 14, 15, 15, 16, 16 };
        Y = new int[] { 705, 769, 833, 578, 642, 260, 897, 961, 1025, 1089, 27, 201 };
        c = new int[] { 8, 9, 10, 11, 12, 71, 13, 14, 17, 18, 19, 199 };
        b = new int[] { 0, 32, 64, 80, 96, 104, 112, 116, 120, 122, 124, 125 };
        d = new byte[] { 6, 6, 7, 7, 8, 8, 9, 9, 10, 10 };
        f = new int[] { 66, 4, 5, 6, 130, 67, 194, 258, 131, 195 };
        j = new int[] { 257, 321, 449, 513, 577, 641, 67, 194, 131, 322 };
        a = new int[] { 0, 16, 32, 40, 48, 52, 56, 58, 60, 61 };
        e = new byte[] { 11, 11, 12, 12, 13, 13, 14, 14, 15, 15, 16, 16 };
        g = new int[] { 386, 450, 323, 387, 324, 388, 706, 515, 1153, 516, 389, 453 };
        h = new int[] { 69, 195, 70, 132, 133, 196, 15, 16, 73, 74, 261, 452 };
        k = new int[] { 0, 32, 64, 80, 96, 104, 112, 116, 120, 122, 124, 125 };
        m = new byte[] { 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12, 13, 13, 14, 14, 15, 15, 16, 16, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12, 13, 13, 14, 14, 15, 15, 16, 16, 9, 9, 10, 10, 11, 11, 12, 12, 13, 13, 14, 14, 15, 15, 16, 16, 10, 10, 11, 11, 12, 12, 13, 13, 14, 14, 15, 15, 16, 16, 11, 11, 12, 12, 13, 13, 14, 14, 15, 15, 16, 16, 12, 12, 13, 13, 14, 14, 15, 15, 13, 13, 14, 14, 15, 15, 14, 14, 15, 15, 15, 15 };
        n = new int[] { 4289, 4353, 7, 4545, 68, 69, 70, 10, 514, 259, 451, 196, 197, 261, 579, 452, 325, 262, 580, 770, 4609, 4673, 8, 9, 11, 4162, 132, 71, 133, 134, 74, 18, 198, 135, 326, 199, 1217, 4165, 4098, 4865, 5057, 5121, 12, 13, 72, 73, 19, 22, 136, 137, 200, 138, 4227, 4930, 5185, 5249, 14, 5441, 15, 16, 4163, 4290, 75, 20, 139, 76, 6721, 6785, 5505, 5569, 17, 4099, 4354, 5953, 21, 23, 77, 24, 6849, 6913, 4226, 5761, 6017, 6081, 4100, 4418, 25, 26, 6145, 6209, 4482, 4546, 4101, 4164, 4610, 4674, 4738, 4802, 4866, 6529 };
        II = new int[] { 4289, 4353, 705, 4545, 258, 769, 961, 1025, 386, 450, 259, 323, 387, 451, 72, 197, 134, 135, 1090, 2369, 4609, 4673, 833, 897, 1089, 4162, 514, 578, 642, 1345, 706, 1729, 260, 324, 198, 388, 2433, 4165, 4098, 4865, 5057, 5121, 1153, 1217, 1409, 1473, 1793, 1857, 515, 770, 579, 834, 4227, 4930, 5185, 5249, 1281, 5441, 1537, 1601, 4163, 4290, 1921, 1985, 898, 962, 6721, 6785, 5505, 5569, 1665, 4099, 4354, 5953, 2049, 2113, 1026, 2177, 6849, 6913, 4226, 5761, 6017, 6081, 4100, 4418, 2241, 2305, 6145, 6209, 4482, 4546, 4101, 4164, 4610, 4674, 4738, 4802, 4866, 6529 };
        ZI = new int[] { 0, 512, 1024, 1280, 1536, 1664, 1792, 1856, 1920, 1952, 1984, 2000, 2016, 2024, 2032, 2036, 2040, 2042, 2044, 2045, 2048, 2304, 2560, 2688, 2816, 2880, 2944, 2976, 3008, 3024, 3040, 3048, 3056, 3060, 3064, 3066, 3068, 3069, 3072, 3200, 3328, 3392, 3456, 3488, 3520, 3536, 3552, 3560, 3568, 3572, 3576, 3578, 3580, 3581, 3584, 3648, 3712, 3744, 3776, 3792, 3808, 3816, 3824, 3828, 3832, 3834, 3836, 3837, 3840, 3872, 3904, 3920, 3936, 3944, 3952, 3956, 3960, 3962, 3964, 3965, 3968, 3984, 4000, 4008, 4016, 4020, 4024, 4026, 4032, 4040, 4048, 4052, 4056, 4058, 4064, 4068, 4072, 4074, 4080, 4082 };
        CI = new byte[] { 15, 15, 14, 14, 13, 13, 12, 12, 11, 11 };
        BI = new int[] { 6593, 6657, 6401, 6465, 6273, 6337, 5825, 5889, 5633, 5697 };
        DI = new int[] { 6593, 6657, 6401, 6465, 6273, 6337, 5825, 5889, 5633, 5697 };
        FI = new int[] { 2, 3, 4, 6, 8, 12, 16, 24, 32, 48 };
        JI = new byte[] { 10, 10, 9, 9, 8, 8, 7, 7, 6, 6 };
        SI = new int[] { 5313, 5377, 4929, 4993, 4737, 4801, 4417, 4481, 4161, 4225 };
        AI = new int[] { 5313, 5377, 4929, 4993, 4737, 4801, 4417, 4481, 4161, 4225 };
        z = new int[] { 2, 3, 4, 6, 8, 12, 16, 24, 32, 48 };
        u = new byte[] { 5, 5, 5, 4, 4 };
        r = new int[] { 65, 3, 4097, 1, 2 };
        p = new int[] { 2, 129, 4097, 1, 65 };
        o = new int[] { 1, 10, 11, 12, 14 };
        max = new int[32][2048];
        min = new int[36];
        printStackTrace = new int[31];
        toBinaryString = new int[64];
        y = new int[64];
        I = new int[64];
        Z = new int[512];
        C = new int[512];
        B = new int[64];
        D = new int[64];
        F = new int[256];
        J = new int[256];
        S = new int[256];
        A = new int[256];
        E = new int[112];
        G = new int[112];
        H = new int[96];
        K = new int[96];
        L = new int[120];
        M = new int[120];
        N = new int[112];
        O = new int[96];
        P = new int[120];
        Q = new int[1024];
        R = new int[512];
        int n2 = 0;
        int n3 = 0;
        int n4 = 1;
        for (int i = 0; i < 32; ++i) {
            final int n5 = n2 * 8 + n3;
            C.Z.toBinaryString[i] = n5;
            C.Z.toBinaryString[63 - i] = 63 - n5;
            n2 -= n4;
            n3 += n4;
            if (n3 < 0) {
                n4 = 1;
                n3 = 0;
            }
            if (n2 < 0) {
                n4 = -1;
                n2 = 0;
            }
        }
        for (int l = 0; l < 1024; ++l) {
            C.Z.Q[l] = ((l >= 256) ? ((l >= 512) ? 0 : 255) : l);
        }
        for (int n6 = 0; n6 < C.Z.max.length; ++n6) {
            for (int n7 = 0; n7 < C.Z.max[n6].length; ++n7) {
                if (n7 == 0) {
                    C.Z.max[n6][n7] = 0;
                }
                else {
                    final int n8 = n6 * (2 * n7 + 1);
                    C.Z.max[n6][n7] = ((n8 >= -2048) ? ((n8 <= 2047) ? n8 : 2047) : -2048);
                }
            }
        }
        for (int n9 = 0; n9 < C.Z.min.length; ++n9) {
            final int n10 = n9 - 2;
            C.Z.min[n9] = ((n10 >= 1) ? ((n10 <= 31) ? n10 : 31) : 1);
        }
        for (int n11 = 1; n11 < C.Z.y.length; ++n11) {
            C.Z.y[n11] = ((n11 > 3) ? ((n11 > 7) ? ((n11 > 31) ? 48 : (n11 / 8 + 48)) : 64) : (64 + n11));
            C.Z.I[n11] = ((n11 > 3) ? ((n11 > 7) ? ((n11 > 31) ? 1 : 3) : 4) : 6);
        }
        for (int n12 = 1; n12 < C.Z.Z.length; ++n12) {
            C.Z.Z[n12] = ((n12 > 1) ? ((n12 > 4) ? ((n12 > 5) ? ((n12 > 9) ? ((n12 > 11) ? ((n12 > 15) ? ((n12 > 19) ? ((n12 > 23) ? ((n12 > 27) ? ((n12 > 31) ? ((n12 > 39) ? ((n12 > 47) ? ((n12 > 63) ? ((n12 > 95) ? ((n12 > 127) ? ((n12 > 191) ? ((n12 >= 256) ? 0 : 16) : 32) : 1) : 2) : 48) : 3) : 64) : 17) : 18) : 33) : 34) : 51) : 35) : (53 - n12 / 2)) : 19) : (69 - n12)) : -1);
            C.Z.C[n12] = ((n12 > 5) ? ((n12 > 11) ? ((n12 > 31) ? ((n12 > 47) ? ((n12 > 63) ? ((n12 > 127) ? ((n12 > 255) ? 1 : 3) : 4) : 5) : 6) : 7) : 8) : 9);
        }
        for (int n13 = 2; n13 < C.Z.B.length; ++n13) {
            C.Z.B[n13] = ((n13 > 2) ? ((n13 > 3) ? ((n13 > 5) ? ((n13 > 7) ? ((n13 > 9) ? ((n13 > 11) ? ((n13 > 15) ? ((n13 > 19) ? ((n13 > 23) ? ((n13 > 27) ? ((n13 > 31) ? ((n13 > 35) ? ((n13 > 39) ? ((n13 > 43) ? ((n13 > 47) ? 240 : 120) : 180) : 60) : 210) : 90) : 225) : 165) : 195) : 15) : 30) : 45) : 75) : 135) : 150) : 105);
            C.Z.D[n13] = ((n13 > 3) ? ((n13 > 11) ? ((n13 > 47) ? 2 : 4) : 5) : 6);
        }
        for (int n14 = 6; n14 < C.Z.F.length; ++n14) {
            C.Z.F[n14] = ((n14 > 11) ? ((n14 > 15) ? ((n14 > 31) ? ((n14 > 63) ? ((n14 >= 128) ? 0 : (1 - (0x1 & n14 >> 5) * 2)) : (2 * (1 - (0x1 & n14 >> 4) * 2))) : (3 * (1 - (0x1 & n14 >> 3) * 2))) : (4 * (1 - (n14 & 0x2)))) : ((10 - n14 / 2) * (1 - (n14 & 0x1) * 2)));
            C.Z.J[n14] = ((n14 > 11) ? ((n14 > 15) ? ((n14 > 31) ? ((n14 > 63) ? ((n14 > 127) ? 1 : 3) : 4) : 5) : 7) : 8);
        }
        for (int n15 = 0; n15 < C.Z.S.length; ++n15) {
            C.Z.S[n15] = ((n15 >= 4) ? ((n15 != 4) ? ((n15 != 5) ? ((n15 > 7) ? ((n15 > 31) ? ((n15 > 143) ? ((19 - (n15 >> 4)) * (1 - (0x1 & n15 >> 3) * 2)) : ((28 - (n15 >> 3)) * (1 - (0x1 & n15 >> 2) * 2))) : ((32 - (n15 >> 2)) * (1 - (0x1 & n15 >> 1) * 2))) : (31 * (1 - (n15 & 0x1) * 2))) : -32) : 32) : 0);
            C.Z.A[n15] = ((n15 >= 4) ? ((n15 > 7) ? ((n15 > 31) ? ((n15 > 143) ? ((n15 > 191) ? 5 : 10) : 11) : 12) : 13) : 5);
        }
        for (int n16 = 0; n16 < C.Z.E.length; ++n16) {
            C.Z.E[n16] = ((n16 > 3) ? ((n16 > 6) ? ((n16 > 7) ? ((n16 > 15) ? ((n16 > 23) ? ((n16 > 25) ? ((n16 > 27) ? ((n16 > 39) ? ((n16 > 47) ? ((n16 > 79) ? ((n16 > 95) ? ((n16 > 103) ? 2 : 33) : 17) : 1) : 4097) : (193 - n16 / 4 * 16)) : 3) : 18) : (273 - n16 / 2 * 16)) : (4225 - n16 / 2 * 16)) : 4) : (257 - n16 * 16)) : (4225 - n16 * 16));
            C.Z.G[n16] = ((n16 > 7) ? ((n16 > 27) ? ((n16 > 39) ? ((n16 > 47) ? ((n16 > 79) ? ((n16 > 95) ? 4 : 3) : 2) : 4) : 5) : 6) : 7);
        }
        for (int n17 = 0; n17 < C.Z.H.length; ++n17) {
            C.Z.H[n17] = ((n17 > 1) ? ((n17 > 17) ? ((n17 > 19) ? ((n17 > 35) ? ((n17 > 39) ? ((n17 > 43) ? ((n17 > 75) ? ((n17 > 83) ? ((n17 > 91) ? 5 : (349 - n17 / 4 * 15)) : (529 - n17 / 4 * 16)) : (4529 - n17 / 4 * 16)) : (27 - n17 / 2)) : (354 - n17 / 2 * 16)) : (513 - n17 / 2 * 16)) : 4098) : (4497 - n17 / 2 * 16)) : (9 - n17));
            C.Z.K[n17] = ((n17 > 1) ? ((n17 > 43) ? 8 : 9) : 10);
        }
        for (int n18 = 0; n18 < C.Z.L.length; ++n18) {
            C.Z.L[n18] = ((n18 > 3) ? ((n18 > 7) ? ((n18 > 23) ? ((n18 > 43) ? ((n18 > 51) ? ((n18 > 55) ? ((n18 > 59) ? ((n18 > 63) ? ((n18 > 71) ? ((n18 > 72) ? ((n18 > 73) ? ((n18 > 74) ? ((n18 > 75) ? ((n18 > 76) ? ((n18 > 77) ? ((n18 > 78) ? ((n18 > 79) ? ((n18 > 87) ? 7167 : (n18 * 16 + 3345)) : 417) : 401) : 162) : 99) : 83) : 67) : 36) : 22) : (n18 / 2 * 16 + 4049)) : (n18 / 2 * 16 - 111)) : (n18 / 2 * 9 - 240)) : 20) : (227 - n18 / 4 * 16)) : (242 - n18 / 4 * 16)) : (4577 - n18 / 4 * 16)) : (13 - n18 / 2)) : (4114 - n18 / 2 * 15));
            C.Z.M[n18] = ((n18 > 7) ? ((n18 > 55) ? ((n18 > 71) ? ((n18 > 87) ? 6 : 12) : 11) : 10) : 11);
        }
        for (int n19 = 0; n19 < C.Z.P.length; ++n19) {
            C.Z.P[n19] = ((n19 > 3) ? ((n19 > 7) ? ((n19 > 11) ? ((n19 > 15) ? ((n19 > 19) ? ((n19 > 23) ? ((n19 > 27) ? ((n19 > 31) ? ((n19 > 35) ? ((n19 > 43) ? ((n19 > 47) ? ((n19 > 55) ? ((n19 > 59) ? ((n19 > 61) ? ((n19 > 63) ? ((n19 > 67) ? ((n19 > 71) ? ((n19 > 74) ? ((n19 > 75) ? ((n19 > 76) ? ((n19 > 77) ? ((n19 > 78) ? ((n19 > 79) ? ((n19 > 80) ? ((n19 > 81) ? ((n19 > 83) ? ((n19 > 87) ? 7167 : (n19 * 32 + 1953)) : (32 * n19 + 1634)) : 4104) : 449) : 227) : 69) : 42) : 195) : 41) : (n19 - 47)) : (n19 / 2 * 32 + 3489)) : (n19 / 2 * 32 + 3170)) : 290) : 40) : (n19 / 2 - 5)) : (32 - n19 / 4)) : 39) : (388 - n19 / 4 * 32)) : 131) : 258) : 163) : 417) : 4101) : 4131) : 4162) : (24 - n19 / 2)) : (4103 - n19 / 2));
        }
        for (int n20 = 0; n20 < C.Z.O.length; ++n20) {
            C.Z.O[n20] = ((n20 > 1) ? ((n20 > 11) ? ((n20 > 13) ? ((n20 > 15) ? ((n20 > 19) ? ((n20 > 25) ? ((n20 > 29) ? ((n20 > 33) ? ((n20 > 35) ? ((n20 > 37) ? ((n20 > 43) ? ((n20 > 55) ? ((n20 > 59) ? ((n20 > 71) ? ((n20 > 75) ? ((n20 > 79) ? ((n20 > 83) ? (33 - n20 / 4) : 36) : 98) : 4385) : (801 - 32 * (n20 / 4))) : 4099) : (4705 - 32 * (n20 / 4))) : (34 - n20 / 2)) : 130) : 16) : (53 - n20 / 2)) : (515 - 32 * (n20 / 2))) : (546 - 32 * (n20 / 2))) : (641 - 32 * (n20 / 2))) : 4100) : 4130) : (4577 - 32 * (n20 / 2))) : (18 - n20));
        }
        for (int n21 = 0; n21 < C.Z.N.length; ++n21) {
            C.Z.N[n21] = ((n21 > 1) ? ((n21 > 2) ? ((n21 > 3) ? ((n21 > 4) ? ((n21 > 5) ? ((n21 > 6) ? ((n21 > 7) ? ((n21 > 9) ? ((n21 > 11) ? ((n21 > 15) ? ((n21 > 19) ? ((n21 > 23) ? ((n21 > 25) ? ((n21 > 27) ? ((n21 > 31) ? ((n21 > 39) ? ((n21 > 47) ? ((n21 > 95) ? ((n21 > 103) ? 3 : 33) : ((n21 / 16 - 1) / 2)) : 4097) : (13 - n21 / 4)) : 65) : 6) : 34) : (18 - n21 / 2)) : (385 - 32 * (n21 / 2))) : (4353 - 32 * (n21 / 2))) : 161) : 4098) : 9) : 35) : 66) : 225) : 4257) : 193) : (4225 - 32 * n21));
        }
        for (int n22 = 0; n22 < 31; ++n22) {
            C.Z.printStackTrace[n22] = ((n22 >= 6) ? ((n22 >= 9) ? ((n22 >= 13) ? ((n22 + 2) / 3 + 2) : (n22 + 1 >> 1)) : 4) : ((n22 >> 1) + 1));
        }
        for (int n23 = 0; n23 < 512; ++n23) {
            C.Z.R[n23] = ((n23 >= 128) ? ((n23 >= 384) ? 255 : (n23 - 128)) : 0);
        }
    }
}
