// 
// Decompiled by Procyon v0.5.30
// 

package d.b.a;

public class u extends o
{
    public static final int void = 10;
    private int char;
    private int c;
    private int a;
    private int[] for;
    private int case;
    private int d;
    private int if;
    private int[] int;
    private int[] byte;
    private float[] try;
    private float[] long;
    private float[] new;
    private float[] else;
    private float[][] do;
    private float[][] e;
    private int[][] goto;
    private int[][] b;
    
    public u(final int char1, final int c, final int a, final int[] for1, final int d, final int if1) {
        this.char = char1;
        this.c = c;
        this.a = a;
        this.for = for1;
        this.d = d;
        this.if = if1;
        this.int = new int[a];
        this.byte = new int[a];
        this.case = 1 << d;
        this.do = new float[10][char1];
        this.e = new float[10][char1];
        this.b = new int[10][a];
        this.goto = new int[10][a];
        this.try = new float[char1];
        this.long = new float[char1];
        this.else = new float[char1];
        this.new = new float[this.case];
    }
    
    public final void a(final float[] array, final float[] array2, final float[] array3, final float[] array4, final int n, final int n2, final float[] array5, final int n3, final float[] array6, final ad ad, final int n4) {
        int n5 = n4;
        if (n5 > 10) {
            n5 = 10;
        }
        final float[] array7 = new float[this.case * this.c];
        final int[] array8 = new int[n5];
        final float[] array9 = new float[n5];
        final float[] array10 = new float[n5];
        final float[] array11 = new float[n5];
        for (int i = 0; i < n5; ++i) {
            for (int j = 0; j < this.a; ++j) {
                this.goto[i][j] = (this.b[i][j] = -1);
            }
        }
        for (int k = 0; k < n5; ++k) {
            for (int l = 0; l < n2; ++l) {
                this.do[k][l] = array[l];
            }
        }
        for (int n6 = 0; n6 < this.case; ++n6) {
            final int n7 = n6 * this.c;
            final int n8 = n6 * this.c;
            for (int n9 = 0; n9 < this.c; ++n9) {
                array7[n7 + n9] = 0.0f;
                for (int n10 = 0; n10 <= n9; ++n10) {
                    final float[] array12 = array7;
                    final int n11 = n7 + n9;
                    array12[n11] += (float)(0.03125 * this.for[n8 + n10] * array6[n9 - n10]);
                }
            }
            this.new[n6] = 0.0f;
            for (int n12 = 0; n12 < this.c; ++n12) {
                final float[] new1 = this.new;
                final int n13 = n6;
                new1[n13] += array7[n7 + n12] * array7[n7 + n12];
            }
        }
        for (int n14 = 0; n14 < n5; ++n14) {
            array11[n14] = 0.0f;
        }
        for (int n15 = 0; n15 < this.a; ++n15) {
            final int n16 = n15 * this.c;
            for (int n17 = 0; n17 < n5; ++n17) {
                array10[n17] = -1.0f;
            }
            for (int n18 = 0; n18 < n5; ++n18) {
                if (this.if != 0) {
                    r.if(this.do[n18], n16, array7, this.c, this.case, this.new, n5, array8, array9);
                }
                else {
                    r.a(this.do[n18], n16, array7, this.c, this.case, this.new, n5, array8, array9);
                }
                for (int n19 = 0; n19 < n5; ++n19) {
                    final float[] array13 = this.do[n18];
                    for (int n20 = n16; n20 < n16 + this.c; ++n20) {
                        this.try[n20] = array13[n20];
                    }
                    float n21 = 1.0f;
                    int n22 = array8[n19];
                    if (n22 >= this.case) {
                        n21 = -1.0f;
                        n22 -= this.case;
                    }
                    final int n23 = n22 * this.c;
                    if (n21 > 0.0f) {
                        for (int n24 = 0; n24 < this.c; ++n24) {
                            final float[] try1 = this.try;
                            final int n25 = n16 + n24;
                            try1[n25] -= array7[n23 + n24];
                        }
                    }
                    else {
                        for (int n26 = 0; n26 < this.c; ++n26) {
                            final float[] try2 = this.try;
                            final int n27 = n16 + n26;
                            try2[n27] += array7[n23 + n26];
                        }
                    }
                    float n28 = array11[n18];
                    for (int n29 = n16; n29 < n16 + this.c; ++n29) {
                        n28 += this.try[n29] * this.try[n29];
                    }
                    if (n28 < array10[n5 - 1] || array10[n5 - 1] < -0.5) {
                        for (int n30 = n16 + this.c; n30 < n2; ++n30) {
                            this.try[n30] = array13[n30];
                        }
                        for (int n31 = 0; n31 < this.c; ++n31) {
                            float n32 = 1.0f;
                            int n33 = array8[n19];
                            if (n33 >= this.case) {
                                n32 = -1.0f;
                                n33 -= this.case;
                            }
                            final float n34 = n32 * 0.03125f * this.for[n33 * this.c + n31];
                            for (int n35 = this.c - n31, n36 = n16 + this.c; n36 < n2; ++n36, ++n35) {
                                final float[] try3 = this.try;
                                final int n37 = n36;
                                try3[n37] -= n34 * array6[n35];
                            }
                        }
                        for (int n38 = 0; n38 < n5; ++n38) {
                            if (n28 < array10[n38] || array10[n38] < -0.5) {
                                for (int n39 = n5 - 1; n39 > n38; --n39) {
                                    for (int n40 = n16 + this.c; n40 < n2; ++n40) {
                                        this.e[n39][n40] = this.e[n39 - 1][n40];
                                    }
                                    for (int n41 = 0; n41 < this.a; ++n41) {
                                        this.goto[n39][n41] = this.goto[n39 - 1][n41];
                                    }
                                    array10[n39] = array10[n39 - 1];
                                }
                                for (int n42 = n16 + this.c; n42 < n2; ++n42) {
                                    this.e[n38][n42] = this.try[n42];
                                }
                                for (int n43 = 0; n43 < this.a; ++n43) {
                                    this.goto[n38][n43] = this.b[n18][n43];
                                }
                                this.goto[n38][n15] = array8[n19];
                                array10[n38] = n28;
                                break;
                            }
                        }
                    }
                }
                if (n15 == 0) {
                    break;
                }
            }
            final float[][] do1 = this.do;
            this.do = this.e;
            this.e = do1;
            for (int n44 = 0; n44 < n5; ++n44) {
                for (int n45 = 0; n45 < this.a; ++n45) {
                    this.b[n44][n45] = this.goto[n44][n45];
                }
            }
            for (int n46 = 0; n46 < n5; ++n46) {
                array11[n46] = array10[n46];
            }
        }
        for (int n47 = 0; n47 < this.a; ++n47) {
            ad.a(this.int[n47] = this.goto[0][n47], this.d + this.if);
        }
        for (int n48 = 0; n48 < this.a; ++n48) {
            float n49 = 1.0f;
            int n50 = this.int[n48];
            if (n50 >= this.case) {
                n49 = -1.0f;
                n50 -= this.case;
            }
            for (int n51 = 0; n51 < this.c; ++n51) {
                this.long[this.c * n48 + n51] = n49 * 0.03125f * this.for[n50 * this.c + n51];
            }
        }
        for (int n52 = 0; n52 < n2; ++n52) {
            final int n53 = n3 + n52;
            array5[n53] += this.long[n52];
        }
        ae.a(this.long, 0, array2, array3, array4, this.else, n2, n);
        for (int n54 = 0; n54 < n2; ++n54) {
            final int n55 = n54;
            array[n55] -= this.else[n54];
        }
    }
    
    public final void a(final float[] array, final int n, final int n2, final ad ad) {
        for (int i = 0; i < this.a; ++i) {
            if (this.if != 0) {
                this.byte[i] = ad.a(1);
            }
            else {
                this.byte[i] = 0;
            }
            this.int[i] = ad.a(this.d);
        }
        for (int j = 0; j < this.a; ++j) {
            float n3 = 1.0f;
            if (this.byte[j] != 0) {
                n3 = -1.0f;
            }
            for (int k = 0; k < this.c; ++k) {
                final int n4 = n + this.c * j + k;
                array[n4] += n3 * 0.03125f * this.for[this.int[j] * this.c + k];
            }
        }
    }
}
