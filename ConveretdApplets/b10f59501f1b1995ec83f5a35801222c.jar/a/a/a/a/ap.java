// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class ap
{
    aq[] a;
    
    ap() {
        this.a = new aq[4];
    }
    
    static void a(final aq[] array, final int n, final f f, final int n2, final int n3) {
        for (int i = 0; i < n; ++i) {
            final float n4 = f.eU * array[i].try + f.eI * array[i].if;
            final float n5 = f.eS * n4 + f.eY * array[i].byte;
            if (n5 >= 0.01f) {
                final float n6 = f.eH / n5;
                array[i].int = (-f.eU * array[i].if + f.eI * array[i].try) * n6 + n2;
                array[i].for = -(f.eS * array[i].byte - f.eY * n4) * n6 + n3;
                array[i].new = true;
            }
            else {
                array[i].new = false;
            }
        }
    }
    
    void a(final aq[] array, final int n, final int n2, final ad ad, final f f, final boolean b) {
        a(array, n * n2, f, f.eE.i + (f.eL >> 1), f.eE.h + (f.eQ >> 1));
        final int n3 = n - 1;
        final int n4 = n2 - 1;
        int n5 = 0;
        int n6 = n;
        for (int i = 0; i < n4; ++i) {
            for (int j = 0; j < n3; ++j) {
                if (array[j + n5].new & array[j + 1 + n5].new & array[j + n6].new & array[j + 1 + n6].new) {
                    this.a[0] = array[j + n5];
                    this.a[1] = array[j + 1 + n5];
                    this.a[3] = array[j + n6];
                    this.a[2] = array[j + 1 + n6];
                    a(this.a, ad.q, ad.j, ad.u, f.eE.q, f.eE.j, f.eE.u, f.eE.i, f.eE.h, f.eE.t, f.eE.k, ad.v, ad.p, b, ad.g);
                }
            }
            n5 += n;
            n6 += n;
        }
    }
    
    protected void a(final ad ad, final ad ad2, final aq[] array, final boolean b) {
        a(array, ad.q, ad.j, ad.u, ad2.q, ad2.j, ad2.u, 0, 0, ad2.j, ad2.u, ad.v, ad.p, b, ad.g);
    }
    
    private static void a(final aq[] array, final int[] array2, final int n, final int n2, final int[] array3, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final boolean b, final boolean b2, final boolean b3, final int n9) {
        if (array2 == null || array3 == null) {
            return;
        }
        final int n10 = (n5 + n7 > n3) ? n3 : (n5 + n7);
        final int n11 = (n5 > 0) ? n5 : 0;
        final int n12 = (n6 + n8 > n4) ? n4 : (n6 + n8);
        final int n13 = (n6 > 0) ? n6 : 0;
        if ((n10 < array[0].int && n10 < array[1].int && n10 < array[2].int && n10 < array[3].int) || (array[0].int < n11 && array[1].int < n11 && array[2].int < n11 && array[3].int < n11) || (n12 < array[0].for && n12 < array[1].for && n12 < array[2].for && n12 < array[3].for) || (array[0].for < n13 && array[1].for < n13 && array[2].for < n13 && array[3].for < n13)) {
            return;
        }
        final float for1 = array[0].for;
        int n14 = 0;
        final float n15 = (for1 > array[1].for) ? array[n14 = 1].for : for1;
        final float n16 = (n15 > array[2].for) ? array[n14 = 2].for : n15;
        final float n17 = (n16 > array[3].for) ? array[n14 = 3].for : n16;
        int n18 = n14 - 1 & 0x3;
        int n19 = n14 + 1 & 0x3;
        int i = 3;
        int n20 = (n17 > n13) ? ((int)n17) : n13;
        int n21 = (int)array[n19].for;
        int n22 = (int)array[n18].for;
        while (i > 0) {
            final aq aq;
            final aq aq2;
            final float n23 = 1.0f / ((aq = array[n18]).for - (aq2 = array[n18 + 1 & 0x3]).for);
            final float do1 = aq2.do;
            final float n24 = (aq.do - aq2.do) * n23;
            final float n26;
            float n25 = do1 + n24 * (n26 = n20 - aq2.for);
            final float n28;
            float n27 = aq2.a + (n28 = (aq.a - aq2.a) * n23) * n26;
            final float n30;
            float n29 = aq2.int + (n30 = (aq.int - aq2.int) * n23) * n26;
            final aq aq3;
            final aq aq4;
            final float n31 = 1.0f / ((aq3 = array[n19]).for - (aq4 = array[n19 - 1 & 0x3]).for);
            final float do2 = aq4.do;
            final float n32 = (aq3.do - aq4.do) * n31;
            final float n34;
            float n33 = do2 + n32 * (n34 = n20 - aq4.for);
            final float n36;
            float n35 = aq4.a + (n36 = (aq3.a - aq4.a) * n31) * n34;
            final float n38;
            float n37 = aq4.int + (n38 = (aq3.int - aq4.int) * n31) * n34;
            final float n39 = n24;
            final float n40 = n28;
            final float n41 = n32;
            final float n42 = n36;
            final float n43 = Math.min(Math.min(n22, n21), n6 + n8);
            float n44 = n20 * n3;
            try {
                while (n20 < n43) {
                    n29 += n30;
                    n37 += n38;
                    n25 += n39;
                    n33 += n41;
                    n27 += n40;
                    n35 += n42;
                    final int n45;
                    final int n46;
                    if ((n45 = ((n29 > n11) ? ((int)n29) : n11)) < (n46 = (int)((n37 > n10) ? (n10 + n44) : ((int)n37 + n44)))) {
                        final float n47 = n45 - n29;
                        final float n48 = 1.0f / (n37 - n29);
                        final float n49 = (n33 - n25) * n48;
                        int n50 = (int)((n25 + n49 * n47) * 65535.0f) - (b3 ? 32768 : 0);
                        final int n51 = (int)(n49 * 65535.0f);
                        final float n52 = (n35 - n27) * n48;
                        int n53 = (int)((n27 + n52 * n47) * 65535.0f) - (b3 ? 32768 : 0);
                        final int n54 = (int)(n52 * 65535.0f);
                        final int n55 = (int)(n45 + n44);
                        if (n9 != 0) {
                            if (b3) {
                                if (b2) {
                                    for (int j = n55; j < n46; ++j) {
                                        n50 += n51;
                                        n53 += n54;
                                        final int if1 = if(n50, n53, array2, n, n2);
                                        final int n56 = array3[j];
                                        final int n57 = if1 >> 24 & 0xFF;
                                        final int n58 = 255 - n57;
                                        array3[j] = (0xFF000000 | (((n56 & 0xFF0000) * n58 >> 8) + ((if1 & 0xFF0000) * n57 >> 8) & 0xFF0000) | (((n56 & 0xFF00) * n58 >> 8) + ((if1 & 0xFF00) * n57 >> 8) & 0xFF00) | (((n56 & 0xFF) * n58 >> 8) + ((if1 & 0xFF) * n57 >> 8) & 0xFF));
                                    }
                                }
                                else if (b) {
                                    for (int k = n55; k < n46; ++k) {
                                        n50 += n51;
                                        n53 += n54;
                                        final int do3 = do(n50, n53, array2, n, n2);
                                        if (do3 < 0) {
                                            array3[k] = do3;
                                        }
                                    }
                                }
                                else {
                                    for (int l = n55; l < n46; ++l) {
                                        n50 += n51;
                                        n53 += n54;
                                        array3[l] = a(n50, n53, array2, n, n2);
                                    }
                                }
                            }
                            else if (b2) {
                                for (int n59 = n55; n59 < n46; ++n59) {
                                    n50 += n51;
                                    n53 += n54;
                                    final int n60 = array2[(n50 >> 16) + (n53 >> 16 << n9)];
                                    if ((n60 & 0xFF000000) != 0x0) {
                                        final int n61 = array3[n59];
                                        final int n62 = n60 >> 24 & 0xFF;
                                        final int n63 = 255 - n62;
                                        array3[n59] = (0xFF000000 | (((n61 & 0xFF0000) * n63 >> 8) + ((n60 & 0xFF0000) * n62 >> 8) & 0xFF0000) | (((n61 & 0xFF00) * n63 >> 8) + ((n60 & 0xFF00) * n62 >> 8) & 0xFF00) | (((n61 & 0xFF) * n63 >> 8) + ((n60 & 0xFF) * n62 >> 8) & 0xFF));
                                    }
                                }
                            }
                            else if (b) {
                                for (int n64 = n55; n64 < n46; ++n64) {
                                    n50 += n51;
                                    n53 += n54;
                                    final int n65 = array2[(n50 >> 16) + (n53 >> 16 << n9)];
                                    if (n65 < 0) {
                                        array3[n64] = n65;
                                    }
                                }
                            }
                            else {
                                for (int n66 = n55; n66 < n46; ++n66) {
                                    n50 += n51;
                                    n53 += n54;
                                    array3[n66] = array2[(n50 >> 16) + (n53 >> 16 << n9)];
                                }
                            }
                        }
                        else if (b3) {
                            if (b2) {
                                for (int n67 = n55; n67 < n46; ++n67) {
                                    n50 += n51;
                                    n53 += n54;
                                    final int if2 = if(n50, n53, array2, n, n2);
                                    final int n68 = array3[n67];
                                    final int n69 = if2 >> 24 & 0xFF;
                                    final int n70 = 255 - n69;
                                    array3[n67] = (0xFF000000 | (((n68 & 0xFF0000) * n70 >> 8) + ((if2 & 0xFF0000) * n69 >> 8) & 0xFF0000) | (((n68 & 0xFF00) * n70 >> 8) + ((if2 & 0xFF00) * n69 >> 8) & 0xFF00) | (((n68 & 0xFF) * n70 >> 8) + ((if2 & 0xFF) * n69 >> 8) & 0xFF));
                                }
                            }
                            else if (b) {
                                for (int n71 = n55; n71 < n46; ++n71) {
                                    n50 += n51;
                                    n53 += n54;
                                    final int do4 = do(n50, n53, array2, n, n2);
                                    if (do4 < 0) {
                                        array3[n71] = do4;
                                    }
                                }
                            }
                            else {
                                for (int n72 = n55; n72 < n46; ++n72) {
                                    n50 += n51;
                                    n53 += n54;
                                    array3[n72] = a(n50, n53, array2, n, n2);
                                }
                            }
                        }
                        else if (b2) {
                            for (int n73 = n55; n73 < n46; ++n73) {
                                n50 += n51;
                                n53 += n54;
                                final int n74 = array2[(n50 >> 16) + (n53 >> 16) * n];
                                if ((n74 & 0xFF000000) != 0x0) {
                                    final int n75 = array3[n73];
                                    final int n76 = n74 >> 24 & 0xFF;
                                    final int n77 = 255 - n76;
                                    array3[n73] = (0xFF000000 | (((n75 & 0xFF0000) * n77 >> 8) + ((n74 & 0xFF0000) * n76 >> 8) & 0xFF0000) | (((n75 & 0xFF00) * n77 >> 8) + ((n74 & 0xFF00) * n76 >> 8) & 0xFF00) | (((n75 & 0xFF) * n77 >> 8) + ((n74 & 0xFF) * n76 >> 8) & 0xFF));
                                }
                            }
                        }
                        else if (b) {
                            for (int n78 = n55; n78 < n46; ++n78) {
                                n50 += n51;
                                n53 += n54;
                                final int n79 = array2[(n50 >> 16) + (n53 >> 16) * n];
                                if (n79 < 0) {
                                    array3[n78] = n79;
                                }
                            }
                        }
                        else {
                            for (int n80 = n55; n80 < n46; ++n80) {
                                n50 += n51;
                                n53 += n54;
                                array3[n80] = array2[(n50 >> 16) + (n53 >> 16) * n];
                            }
                        }
                    }
                    n44 += n3;
                    ++n20;
                }
            }
            catch (Exception ex) {}
            if (n20 == n4) {
                return;
            }
            if (n22 > n21) {
                n21 = (int)array[n19 = (n19 + 1 & 0x3)].for;
            }
            else if (n22 < n21) {
                n22 = (int)array[n18 = (n18 - 1 & 0x3)].for;
            }
            else {
                n21 = (int)array[n19 = (n19 + 1 & 0x3)].for;
                n22 = (int)array[n18 = (n18 - 1 & 0x3)].for;
                --i;
            }
            --i;
        }
    }
    
    private static int a(int n, int n2, final int[] array, final int n3, final int n4) {
        n = ((n > 0) ? n : 0);
        n2 = ((n2 > 0) ? n2 : 0);
        if (n >> 16 >= n3 - 1) {
            if (n2 >> 16 >= n4 - 1) {
                return array[n3 * (n2 >> 16) + (n >> 16)];
            }
            final int n5 = (n >> 16) + (n2 >> 16) * n3;
            final int n6 = n2 >> 8 & 0xFF;
            final int n7 = 255 - n6;
            final int n8 = n6;
            final int n9 = n5 + n3;
            return ((n7 * (array[n5] >> 16 & 0xFF) + n8 * (array[n9] >> 16 & 0xFF) & 0xFF00) << 8) + (n7 * (array[n5] >> 8 & 0xFF) + n8 * (array[n9] >> 8 & 0xFF) & 0xFF00) + (n7 * (array[n5] & 0xFF) + n8 * (array[n9] & 0xFF) >> 8) - 16777216;
        }
        else {
            if (n2 >> 16 >= n4 - 1) {
                final int n10 = (n >> 16) + (n2 >> 16) * n3;
                final int n11 = n >> 8 & 0xFF;
                final int n12 = 255 - n11;
                final int n13 = n11;
                return ((n12 * (array[n10] >> 16 & 0xFF) + n13 * (array[n10 + 1] >> 16 & 0xFF) & 0xFF00) << 8) + (n12 * (array[n10] >> 8 & 0xFF) + n13 * (array[n10 + 1] >> 8 & 0xFF) & 0xFF00) + (n12 * (array[n10] & 0xFF) + n13 * (array[n10 + 1] & 0xFF) >> 8) - 16777216;
            }
            final int n14 = (n >> 16) + (n2 >> 16) * n3;
            final int n15 = n >> 8 & 0xFF;
            final int n16 = n2 >> 8 & 0xFF;
            final int n17 = (256 - n15) * (256 - n16);
            final int n18 = n15 * (256 - n16);
            final int n19 = (256 - n15) * n16;
            final int n20 = n15 * n16;
            final int n21 = n14 + n3;
            return (n17 * (array[n14] >> 16 & 0xFF) + n18 * (array[n14 + 1] >> 16 & 0xFF) + n19 * (array[n21] >> 16 & 0xFF) + n20 * (array[n21 + 1] >> 16 & 0xFF) & 0xFF0000) + ((n17 * (array[n14] >> 8 & 0xFF) + n18 * (array[n14 + 1] >> 8 & 0xFF) + n19 * (array[n21] >> 8 & 0xFF) + n20 * (array[n21 + 1] >> 8 & 0xFF) & 0xFF0000) >> 8) + (n17 * (array[n14] & 0xFF) + n18 * (array[n14 + 1] & 0xFF) + (n19 * (array[n21] & 0xFF) + n20 * (array[n21 + 1] & 0xFF)) >> 16) - 16777216;
        }
    }
    
    private static int if(int n, int n2, final int[] array, final int n3, final int n4) {
        n = ((n > 0) ? n : 0);
        n2 = ((n2 > 0) ? n2 : 0);
        if (n >> 16 >= n3 - 1) {
            if (n2 >> 16 >= n4 - 1) {
                return array[n3 * (n2 >> 16) + (n >> 16)];
            }
            final int n5 = (n >> 16) + (n2 >> 16) * n3;
            final int n6 = n2 >> 8 & 0xFF;
            final int n7 = 255 - n6;
            final int n8 = n6;
            final int n9 = n5 + n3;
            if ((array[n9] & 0xFF000000) == 0x0) {
                return array[n5];
            }
            return (n7 * (array[n5] >> 16 & 0xFF) + n8 * (array[n9] >> 16 & 0xFF) & 0xFF00) << 8 | (n7 * (array[n5] >> 8 & 0xFF) + n8 * (array[n9] >> 8 & 0xFF) & 0xFF00) | n7 * (array[n5] & 0xFF) + n8 * (array[n9] & 0xFF) >> 8 | (array[n5] & 0xFF000000);
        }
        else if (n2 >> 16 >= n4 - 1) {
            final int n10 = (n >> 16) + (n2 >> 16) * n3;
            final int n11 = n >> 8 & 0xFF;
            final int n12 = 255 - n11;
            final int n13 = n11;
            if ((array[n10 + 1] & 0xFF000000) == 0x0) {
                return array[n10];
            }
            return (n12 * (array[n10] >> 16 & 0xFF) + n13 * (array[n10 + 1] >> 16 & 0xFF) & 0xFF00) << 8 | (n12 * (array[n10] >> 8 & 0xFF) + n13 * (array[n10 + 1] >> 8 & 0xFF) & 0xFF00) | n12 * (array[n10] & 0xFF) + n13 * (array[n10 + 1] & 0xFF) >> 8 | (array[n10] & 0xFF000000);
        }
        else {
            final int n14 = (n >> 16) + (n2 >> 16) * n3;
            final int n15 = n >> 8 & 0xFF;
            final int n16 = n2 >> 8 & 0xFF;
            final int n17 = (256 - n15) * (256 - n16);
            final int n18 = n15 * (256 - n16);
            final int n19 = (256 - n15) * n16;
            final int n20 = n15 * n16;
            final int n21 = n14 + n3;
            if ((array[n14 + 1] & 0xFF000000) == 0x0 || (array[n21] & 0xFF000000) == 0x0 || (array[n21 + 1] & 0xFF000000) == 0x0) {
                return array[n14];
            }
            return (n17 * (array[n14] >> 16 & 0xFF) + n18 * (array[n14 + 1] >> 16 & 0xFF) + n19 * (array[n21] >> 16 & 0xFF) + n20 * (array[n21 + 1] >> 16 & 0xFF) & 0xFF0000) | (n17 * (array[n14] >> 8 & 0xFF) + n18 * (array[n14 + 1] >> 8 & 0xFF) + n19 * (array[n21] >> 8 & 0xFF) + n20 * (array[n21 + 1] >> 8 & 0xFF) & 0xFF0000) >> 8 | n17 * (array[n14] & 0xFF) + n18 * (array[n14 + 1] & 0xFF) + (n19 * (array[n21] & 0xFF) + n20 * (array[n21 + 1] & 0xFF)) >> 16 | (array[n14] & 0xFF000000);
        }
    }
    
    private static int do(int n, int n2, final int[] array, final int n3, final int n4) {
        n = ((n > 0) ? n : 0);
        n2 = ((n2 > 0) ? n2 : 0);
        if (n >> 16 >= n3 - 1) {
            if (n2 >> 16 >= n4 - 1) {
                return array[n3 * (n2 >> 16) + (n >> 16)];
            }
            final int n5 = (n >> 16) + (n2 >> 16) * n3;
            final int n6 = n2 >> 8 & 0xFF;
            final int n7 = 255 - n6;
            final int n8 = n6;
            final int n9 = n5 + n3;
            if (array[n9] >= 0) {
                return array[n5];
            }
            return ((n7 * (array[n5] >> 16 & 0xFF) + n8 * (array[n9] >> 16 & 0xFF) & 0xFF00) << 8) + (n7 * (array[n5] >> 8 & 0xFF) + n8 * (array[n9] >> 8 & 0xFF) & 0xFF00) + (n7 * (array[n5] & 0xFF) + n8 * (array[n9] & 0xFF) >> 8) - 16777216;
        }
        else if (n2 >> 16 >= n4 - 1) {
            final int n10 = (n >> 16) + (n2 >> 16) * n3;
            final int n11 = n >> 8 & 0xFF;
            final int n12 = 255 - n11;
            final int n13 = n11;
            if (array[n10 + 1] >= 0 || array[n10] >= 0) {
                return array[n10];
            }
            return ((n12 * (array[n10] >> 16 & 0xFF) + n13 * (array[n10 + 1] >> 16 & 0xFF) & 0xFF00) << 8) + (n12 * (array[n10] >> 8 & 0xFF) + n13 * (array[n10 + 1] >> 8 & 0xFF) & 0xFF00) + (n12 * (array[n10] & 0xFF) + n13 * (array[n10 + 1] & 0xFF) >> 8) - 16777216;
        }
        else {
            final int n14 = (n >> 16) + (n2 >> 16) * n3;
            final int n15 = n >> 8 & 0xFF;
            final int n16 = n2 >> 8 & 0xFF;
            final int n17 = (256 - n15) * (256 - n16);
            final int n18 = n15 * (256 - n16);
            final int n19 = (256 - n15) * n16;
            final int n20 = n15 * n16;
            final int n21 = n14 + n3;
            if (array[n14 + 1] >= 0 || array[n21] >= 0 || array[n21 + 1] >= 0) {
                return array[n14];
            }
            return (n17 * (array[n14] >> 16 & 0xFF) + n18 * (array[n14 + 1] >> 16 & 0xFF) + n19 * (array[n21] >> 16 & 0xFF) + n20 * (array[n21 + 1] >> 16 & 0xFF) & 0xFF0000) + ((n17 * (array[n14] >> 8 & 0xFF) + n18 * (array[n14 + 1] >> 8 & 0xFF) + n19 * (array[n21] >> 8 & 0xFF) + n20 * (array[n21 + 1] >> 8 & 0xFF) & 0xFF0000) >> 8) + (n17 * (array[n14] & 0xFF) + n18 * (array[n14 + 1] & 0xFF) + (n19 * (array[n21] & 0xFF) + n20 * (array[n21 + 1] & 0xFF)) >> 16) - 16777216;
        }
    }
    
    public static void a(final af af, final af af2) {
        final ad ad = new ad();
        ad.j = af.do;
        ad.u = af.case;
        ad.q = af.new;
        ad.v = af.char;
        final ad ad4;
        final ad ad3;
        final ad ad2 = ad3 = (ad4 = new ad());
        final int do1 = af2.do;
        ad3.t = do1;
        ad4.j = do1;
        final ad ad5 = ad2;
        final ad ad6 = ad2;
        final int case1 = af2.case;
        ad6.k = case1;
        ad5.u = case1;
        ad2.q = af2.new;
        ad2.v = af2.char;
        for (int i = 0; i < af2.do * af2.case; ++i) {
            ad2.q[i] = -16711936;
        }
        a(ad, ad2, 0, 0, ad2.j, ad2.u);
    }
    
    public static void a(final ad ad, final ad ad2, final int n, final int n2, final int n3, final int n4) {
        a(ad, 0, ad.j, ad2, n, n2, n3, n4);
    }
    
    public static void a(final ad ad, final int n, final int n2, final ad ad2, int n3, int n4, final int n5, final int n6) {
        if (ad.q == null || ad2.q == null) {
            return;
        }
        if (n3 < ad2.j && n4 < ad2.u) {
            int n7 = n;
            int n8 = 0;
            int j = n3 + n5;
            int u = n4 + n6;
            if (j > ad2.j) {
                j = ad2.j;
            }
            if (j > ad2.t + ad2.i) {
                j = ad2.t + ad2.i;
            }
            if (u > ad2.u) {
                u = ad2.u;
            }
            if (u > ad2.k + ad2.h) {
                u = ad2.k + ad2.h;
            }
            int n9 = 0;
            final int n10 = (ad2.i > 0) ? ad2.i : 0;
            final int n11 = (ad2.h > 0) ? ad2.h : 0;
            if (n2 == n5 && ad.u == n6) {
                if (n3 < n10) {
                    n7 += Math.abs(n3 - n10);
                    n3 = n10;
                }
                if (n4 < n11) {
                    n8 += Math.abs(n4 - n11);
                    n4 = n11;
                }
                int n12 = n4 * ad2.j;
                int n13 = n8 * ad.j + n7;
                if (ad.p) {
                    for (int i = n4; i < u; ++i) {
                        int n14 = n13;
                        int n15 = n12 + n3;
                        for (int k = n3; k < j; ++k) {
                            final int n16 = ad.q[n14];
                            if ((n16 & 0xFF000000) == 0xFF000000) {
                                ad2.q[n15] = n16;
                            }
                            else if ((n16 & 0xFF000000) != 0x0) {
                                final int n17 = ad2.q[n15];
                                final int n18 = n16 >> 24 & 0xFF;
                                final int n19 = 255 - n18;
                                ad2.q[n15] = ((n17 & 0xFF000000) | (((n17 & 0xFF0000) * n19 >> 8) + ((n16 & 0xFF0000) * n18 >> 8) & 0xFF0000) | (((n17 & 0xFF00) * n19 >> 8) + ((n16 & 0xFF00) * n18 >> 8) & 0xFF00) | (((n17 & 0xFF) * n19 >> 8) + ((n16 & 0xFF) * n18 >> 8) & 0xFF));
                            }
                            ++n14;
                            ++n15;
                        }
                        n13 += ad.j;
                        n12 += ad2.j;
                    }
                }
                else if (ad.v) {
                    for (int l = n4; l < u; ++l) {
                        int n20 = n13;
                        int n21 = n12 + n3;
                        for (int n22 = n3; n22 < j; ++n22) {
                            if ((ad.q[n20] & 0xFF000000) != 0x0) {
                                ad2.q[n21] = ad.q[n20];
                            }
                            ++n20;
                            ++n21;
                        }
                        n13 += ad.j;
                        n12 += ad2.j;
                    }
                }
                else {
                    for (int n23 = n4; n23 < u; ++n23) {
                        int n24 = n13;
                        int n25 = n12 + n3;
                        for (int n26 = n3; n26 < j; ++n26) {
                            ad2.q[n25++] = ad.q[n24++];
                        }
                        n13 += ad.j;
                        n12 += ad2.j;
                    }
                }
            }
            else {
                final int n27 = (ad.u << 16) / n6;
                final int n28 = (n2 << 16) / n5;
                if (n3 < n10) {
                    n7 += Math.abs(n3 - n10) * n2 / n5;
                    n3 = n10;
                }
                if (n4 < n11) {
                    n8 = Math.abs(n4 - n11) * ad.u / n6;
                    n4 = n11;
                }
                int n29 = n4 * ad2.j;
                if (ad.p) {
                    for (int n30 = n4; n30 < u; ++n30) {
                        final int n31 = ((n9 >> 16) + n8) * ad.j + n7;
                        int n32 = 0;
                        int n33 = n29 + n3;
                        for (int n34 = n3; n34 < j; ++n34) {
                            final int n35 = ad.q[(n32 >> 16) + n31];
                            if ((n35 & 0xFF000000) == 0xFF000000) {
                                ad2.q[n33] = n35;
                            }
                            else if ((n35 & 0xFF000000) != 0x0) {
                                final int n36 = ad2.q[n33];
                                final int n37 = n35 >> 24 & 0xFF;
                                final int n38 = 255 - n37;
                                ad2.q[n33] = ((n36 & 0xFF000000) | (((n36 & 0xFF0000) * n38 >> 8) + ((n35 & 0xFF0000) * n37 >> 8) & 0xFF0000) | (((n36 & 0xFF00) * n38 >> 8) + ((n35 & 0xFF00) * n37 >> 8) & 0xFF00) | (((n36 & 0xFF) * n38 >> 8) + ((n35 & 0xFF) * n37 >> 8) & 0xFF));
                            }
                            ++n33;
                            n32 += n28;
                        }
                        n9 += n27;
                        n29 += ad2.j;
                    }
                }
                else if (ad.v) {
                    for (int n39 = n4; n39 < u; ++n39) {
                        final int n40 = ((n9 >> 16) + n8) * ad.j + n7;
                        int n41 = 0;
                        int n42 = n29 + n3;
                        for (int n43 = n3; n43 < j; ++n43) {
                            if ((ad.q[(n41 >> 16) + n40] & 0xFF000000) != 0x0) {
                                ad2.q[n42] = ad.q[(n41 >> 16) + n40];
                            }
                            ++n42;
                            n41 += n28;
                        }
                        n9 += n27;
                        n29 += ad2.j;
                    }
                }
                else {
                    for (int n44 = n4; n44 < u; ++n44) {
                        final int n45 = ((n9 >> 16) + n8) * ad.j + n7;
                        int n46 = 0;
                        int n47 = n29 + n3;
                        for (int n48 = n3; n48 < j; ++n48) {
                            ad2.q[n47++] = ad.q[(n46 >> 16) + n45];
                            n46 += n28;
                        }
                        n9 += n27;
                        n29 += ad2.j;
                    }
                }
            }
        }
    }
    
    public static void a(final ad ad, final int n, final int n2, final int n3, final int n4, final int n5) {
        a(ad, n, n2, n3, n4, (ad.i > 0) ? ad.i : 0, (ad.j < ad.i + ad.t) ? ad.j : (ad.i + ad.t), (ad.h > 0) ? ad.h : 0, (ad.u < ad.h + ad.k) ? ad.u : (ad.h + ad.k), n5);
    }
    
    public static void a(final ad ad, int n, int n2, int n3, int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        final int abs = Math.abs(n3 - n);
        final int abs2 = Math.abs(n4 - n2);
        n += ad.i;
        n2 += ad.h;
        n3 += ad.i;
        n4 += ad.h;
        int n10;
        if (n < n3) {
            n10 = 1;
        }
        else {
            n10 = -1;
        }
        int n11;
        if (n2 < n4) {
            n11 = 1;
        }
        else {
            n11 = -1;
        }
        int n12 = n;
        int n13 = n2;
        if (abs > abs2) {
            int n14 = abs >> 1;
            if ((n9 & 0xFF000000) == 0xFF000000) {
                for (int i = 0; i < abs; ++i) {
                    n12 += n10;
                    n14 += abs2;
                    if (n14 > abs) {
                        n14 -= abs;
                        n13 += n11;
                    }
                    if (n12 >= n5 && n12 < n6 && n13 >= n7 && n13 < n8) {
                        ad.q[n12 + n13 * ad.j] = n9;
                    }
                }
            }
            else if ((n9 & 0xFF000000) != 0x0) {
                final int n15 = n9 >> 24 & 0xFF;
                final int n16 = 255 - n15;
                final int n17 = (n9 & 0xFF0000) * n15 >> 8;
                final int n18 = (n9 & 0xFF00) * n15 >> 8;
                final int n19 = (n9 & 0xFF) * n15 >> 8;
                for (int j = 0; j < abs; ++j) {
                    n12 += n10;
                    n14 += abs2;
                    if (n14 > abs) {
                        n14 -= abs;
                        n13 += n11;
                    }
                    if (n12 >= n5 && n12 < n6 && n13 >= n7 && n13 < n8) {
                        final int n20 = ad.q[n12 + n13 * ad.j];
                        ad.q[n12 + n13 * ad.j] = (0xFF000000 | (((n20 & 0xFF0000) * n16 >> 8) + n17 & 0xFF0000) | (((n20 & 0xFF00) * n16 >> 8) + n18 & 0xFF00) | (((n20 & 0xFF) * n16 >> 8) + n19 & 0xFF));
                    }
                }
            }
        }
        else {
            int n21 = abs2 >> 1;
            if ((n9 & 0xFF000000) == 0xFF000000) {
                for (int k = 0; k < abs2; ++k) {
                    n13 += n11;
                    n21 += abs;
                    if (n21 > abs2) {
                        n21 -= abs2;
                        n12 += n10;
                    }
                    if (n12 >= n5 && n12 < n6 && n13 >= n7 && n13 < n8) {
                        ad.q[n12 + n13 * ad.j] = n9;
                    }
                }
            }
            else if ((n9 & 0xFF000000) != 0x0) {
                final int n22 = n9 >> 24 & 0xFF;
                final int n23 = 255 - n22;
                final int n24 = (n9 & 0xFF0000) * n22 >> 8 & 0xFF0000;
                final int n25 = (n9 & 0xFF00) * n22 >> 8 & 0xFF00;
                final int n26 = (n9 & 0xFF) * n22 >> 8 & 0xFF;
                for (int l = 0; l < abs2; ++l) {
                    n13 += n11;
                    n21 += abs;
                    if (n21 > abs2) {
                        n21 -= abs2;
                        n12 += n10;
                    }
                    if (n12 >= n5 && n12 < n6 && n13 >= n7 && n13 < n8) {
                        final int n27 = ad.q[n12 + n13 * ad.j];
                        ad.q[n12 + n13 * ad.j] = (0xFF000000 | (((n27 & 0xFF0000) * n23 >> 8) + n24 & 0xFF0000) | (((n27 & 0xFF00) * n23 >> 8) + n25 & 0xFF00) | (((n27 & 0xFF) * n23 >> 8) + n26 & 0xFF));
                    }
                }
            }
        }
    }
    
    public static void if(final ad ad, final int n, final int n2, int n3, int n4, final int n5) {
        final int j = ad.j;
        final int u = ad.u;
        final int n6 = (n < 0) ? 0 : n;
        if (n > j) {
            return;
        }
        final int n7 = (n + n3 > j) ? j : (n + n3);
        if (n7 < 0) {
            return;
        }
        final int n8 = (n2 < 0) ? 0 : n2;
        if (n2 > u) {
            return;
        }
        final int n9 = (n2 + n4 > u) ? u : (n2 + n4);
        if (n9 < 0) {
            return;
        }
        int n10 = n8 * j + n6;
        final int n11 = j - (n7 - n6);
        n3 = n7 - n6;
        n4 = n9 - n8;
        if ((n5 & 0xFF000000) != 0xFF000000 && (n5 & 0xFF000000) != 0x0) {
            final int n12 = n5 >> 24 & 0xFF;
            final int n13 = 255 - n12;
            final int n14 = (n5 & 0xFF0000) * n12 >> 8;
            final int n15 = (n5 & 0xFF00) * n12 >> 8;
            final int n16 = (n5 & 0xFF) * n12 >> 8;
            for (int i = 0; i < n4; ++i) {
                for (int k = 0; k < n3; ++k) {
                    final int n17 = ad.q[n10];
                    ad.q[n10++] = (0xFF000000 | (((n17 & 0xFF0000) * n13 >> 8) + n14 & 0xFF0000) | (((n17 & 0xFF00) * n13 >> 8) + n15 & 0xFF00) | (((n17 & 0xFF) * n13 >> 8) + n16 & 0xFF));
                }
                n10 += n11;
            }
        }
        else if ((n5 & 0xFF000000) != 0x0) {
            for (int l = 0; l < n4; ++l) {
                for (int n18 = 0; n18 < n3; ++n18) {
                    ad.q[n10++] = n5;
                }
                n10 += n11;
            }
        }
    }
}
