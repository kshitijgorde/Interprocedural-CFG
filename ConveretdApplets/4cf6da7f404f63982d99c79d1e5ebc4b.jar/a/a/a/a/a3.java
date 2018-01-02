// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class a3
{
    a4[] a;
    
    a3() {
        this.a = new a4[4];
    }
    
    static void a(final a4[] array, final int n, final h h, final int n2, final int n3) {
        for (int i = 0; i < n; ++i) {
            final float n4 = h.eV * array[i].try + h.eJ * array[i].if;
            final float n5 = h.eT * n4 + h.eZ * array[i].byte;
            if (n5 >= 0.01f) {
                final float n6 = h.eI / n5;
                array[i].int = (-h.eV * array[i].if + h.eJ * array[i].try) * n6 + n2;
                array[i].for = -(h.eT * array[i].byte - h.eZ * n4) * n6 + n3;
                array[i].new = true;
            }
            else {
                array[i].new = false;
            }
        }
    }
    
    void a(final a4[] array, final int n, final int n2, final ap ap, final h h, final boolean b) {
        a(array, n * n2, h, h.eF.n + (h.eM >> 1), h.eF.m + (h.eR >> 1));
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
                    a(this.a, ap.x, ap.s, ap.r, h.eF.x, h.eF.s, h.eF.r, h.eF.n, h.eF.m, h.eF.l, h.eF.p, ap.v, ap.i, b, ap.k);
                }
            }
            n5 += n;
            n6 += n;
        }
    }
    
    protected void a(final ap ap, final ap ap2, final a4[] array, final boolean b) {
        a(array, ap.x, ap.s, ap.r, ap2.x, ap2.s, ap2.r, 0, 0, ap2.s, ap2.r, ap.v, ap.i, b, ap.k);
    }
    
    private static void a(final a4[] array, final int[] array2, final int n, final int n2, final int[] array3, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final boolean b, final boolean b2, final boolean b3, final int n9) {
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
            final a4 a4;
            final a4 a5;
            final float n23 = 1.0f / ((a4 = array[n18]).for - (a5 = array[n18 + 1 & 0x3]).for);
            final float do1 = a5.do;
            final float n24 = (a4.do - a5.do) * n23;
            final float n26;
            float n25 = do1 + n24 * (n26 = n20 - a5.for);
            final float n28;
            float n27 = a5.a + (n28 = (a4.a - a5.a) * n23) * n26;
            final float n30;
            float n29 = a5.int + (n30 = (a4.int - a5.int) * n23) * n26;
            final a4 a6;
            final a4 a7;
            final float n31 = 1.0f / ((a6 = array[n19]).for - (a7 = array[n19 - 1 & 0x3]).for);
            final float do2 = a7.do;
            final float n32 = (a6.do - a7.do) * n31;
            final float n34;
            float n33 = do2 + n32 * (n34 = n20 - a7.for);
            final float n36;
            float n35 = a7.a + (n36 = (a6.a - a7.a) * n31) * n34;
            final float n38;
            float n37 = a7.int + (n38 = (a6.int - a7.int) * n31) * n34;
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
    
    public static void a(final ar ar, final ar ar2) {
        final ap ap = new ap();
        ap.s = ar.do;
        ap.r = ar.else;
        ap.x = ar.new;
        ap.v = ar.goto;
        final ap ap4;
        final ap ap3;
        final ap ap2 = ap3 = (ap4 = new ap());
        final int do1 = ar2.do;
        ap3.l = do1;
        ap4.s = do1;
        final ap ap5 = ap2;
        final ap ap6 = ap2;
        final int else1 = ar2.else;
        ap6.p = else1;
        ap5.r = else1;
        ap2.x = ar2.new;
        ap2.v = ar2.goto;
        for (int i = 0; i < ar2.do * ar2.else; ++i) {
            ap2.x[i] = -16711936;
        }
        a(ap, ap2, 0, 0, ap2.s, ap2.r);
    }
    
    public static void a(final ap ap, final ap ap2, final int n, final int n2, final int n3, final int n4) {
        a(ap, 0, ap.s, ap2, n, n2, n3, n4);
    }
    
    public static void a(final ap ap, final int n, final int n2, final ap ap2, int n3, int n4, final int n5, final int n6) {
        if (ap.x == null || ap2.x == null) {
            return;
        }
        if (n3 < ap2.s && n4 < ap2.r) {
            int n7 = n;
            int n8 = 0;
            int s = n3 + n5;
            int r = n4 + n6;
            if (s > ap2.s) {
                s = ap2.s;
            }
            if (s > ap2.l + ap2.n) {
                s = ap2.l + ap2.n;
            }
            if (r > ap2.r) {
                r = ap2.r;
            }
            if (r > ap2.p + ap2.m) {
                r = ap2.p + ap2.m;
            }
            int n9 = 0;
            final int n10 = (ap2.n > 0) ? ap2.n : 0;
            final int n11 = (ap2.m > 0) ? ap2.m : 0;
            if (n2 == n5 && ap.r == n6) {
                if (n3 < n10) {
                    n7 += Math.abs(n3 - n10);
                    n3 = n10;
                }
                if (n4 < n11) {
                    n8 += Math.abs(n4 - n11);
                    n4 = n11;
                }
                int n12 = n4 * ap2.s;
                int n13 = n8 * ap.s + n7;
                if (ap.i) {
                    for (int i = n4; i < r; ++i) {
                        int n14 = n13;
                        int n15 = n12 + n3;
                        for (int j = n3; j < s; ++j) {
                            final int n16 = ap.x[n14];
                            if ((n16 & 0xFF000000) == 0xFF000000) {
                                ap2.x[n15] = n16;
                            }
                            else if ((n16 & 0xFF000000) != 0x0) {
                                final int n17 = ap2.x[n15];
                                final int n18 = n16 >> 24 & 0xFF;
                                final int n19 = 255 - n18;
                                ap2.x[n15] = ((n17 & 0xFF000000) | (((n17 & 0xFF0000) * n19 >> 8) + ((n16 & 0xFF0000) * n18 >> 8) & 0xFF0000) | (((n17 & 0xFF00) * n19 >> 8) + ((n16 & 0xFF00) * n18 >> 8) & 0xFF00) | (((n17 & 0xFF) * n19 >> 8) + ((n16 & 0xFF) * n18 >> 8) & 0xFF));
                            }
                            ++n14;
                            ++n15;
                        }
                        n13 += ap.s;
                        n12 += ap2.s;
                    }
                }
                else if (ap.v) {
                    for (int k = n4; k < r; ++k) {
                        int n20 = n13;
                        int n21 = n12 + n3;
                        for (int l = n3; l < s; ++l) {
                            if ((ap.x[n20] & 0xFF000000) != 0x0) {
                                ap2.x[n21] = ap.x[n20];
                            }
                            ++n20;
                            ++n21;
                        }
                        n13 += ap.s;
                        n12 += ap2.s;
                    }
                }
                else {
                    for (int n22 = n4; n22 < r; ++n22) {
                        int n23 = n13;
                        int n24 = n12 + n3;
                        for (int n25 = n3; n25 < s; ++n25) {
                            ap2.x[n24++] = ap.x[n23++];
                        }
                        n13 += ap.s;
                        n12 += ap2.s;
                    }
                }
            }
            else {
                final int n26 = (ap.r << 16) / n6;
                final int n27 = (n2 << 16) / n5;
                if (n3 < n10) {
                    n7 += Math.abs(n3 - n10) * n2 / n5;
                    n3 = n10;
                }
                if (n4 < n11) {
                    n8 = Math.abs(n4 - n11) * ap.r / n6;
                    n4 = n11;
                }
                int n28 = n4 * ap2.s;
                if (ap.i) {
                    for (int n29 = n4; n29 < r; ++n29) {
                        final int n30 = ((n9 >> 16) + n8) * ap.s + n7;
                        int n31 = 0;
                        int n32 = n28 + n3;
                        for (int n33 = n3; n33 < s; ++n33) {
                            final int n34 = ap.x[(n31 >> 16) + n30];
                            if ((n34 & 0xFF000000) == 0xFF000000) {
                                ap2.x[n32] = n34;
                            }
                            else if ((n34 & 0xFF000000) != 0x0) {
                                final int n35 = ap2.x[n32];
                                final int n36 = n34 >> 24 & 0xFF;
                                final int n37 = 255 - n36;
                                ap2.x[n32] = ((n35 & 0xFF000000) | (((n35 & 0xFF0000) * n37 >> 8) + ((n34 & 0xFF0000) * n36 >> 8) & 0xFF0000) | (((n35 & 0xFF00) * n37 >> 8) + ((n34 & 0xFF00) * n36 >> 8) & 0xFF00) | (((n35 & 0xFF) * n37 >> 8) + ((n34 & 0xFF) * n36 >> 8) & 0xFF));
                            }
                            ++n32;
                            n31 += n27;
                        }
                        n9 += n26;
                        n28 += ap2.s;
                    }
                }
                else if (ap.v) {
                    for (int n38 = n4; n38 < r; ++n38) {
                        final int n39 = ((n9 >> 16) + n8) * ap.s + n7;
                        int n40 = 0;
                        int n41 = n28 + n3;
                        for (int n42 = n3; n42 < s; ++n42) {
                            if ((ap.x[(n40 >> 16) + n39] & 0xFF000000) != 0x0) {
                                ap2.x[n41] = ap.x[(n40 >> 16) + n39];
                            }
                            ++n41;
                            n40 += n27;
                        }
                        n9 += n26;
                        n28 += ap2.s;
                    }
                }
                else {
                    for (int n43 = n4; n43 < r; ++n43) {
                        final int n44 = ((n9 >> 16) + n8) * ap.s + n7;
                        int n45 = 0;
                        int n46 = n28 + n3;
                        for (int n47 = n3; n47 < s; ++n47) {
                            ap2.x[n46++] = ap.x[(n45 >> 16) + n44];
                            n45 += n27;
                        }
                        n9 += n26;
                        n28 += ap2.s;
                    }
                }
            }
        }
    }
    
    public static void a(final ap ap, final int n, final int n2, final int n3, final int n4, final int n5) {
        a(ap, n, n2, n3, n4, (ap.n > 0) ? ap.n : 0, (ap.s < ap.n + ap.l) ? ap.s : (ap.n + ap.l), (ap.m > 0) ? ap.m : 0, (ap.r < ap.m + ap.p) ? ap.r : (ap.m + ap.p), n5);
    }
    
    public static void a(final ap ap, int n, int n2, int n3, int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        final int abs = Math.abs(n3 - n);
        final int abs2 = Math.abs(n4 - n2);
        n += ap.n;
        n2 += ap.m;
        n3 += ap.n;
        n4 += ap.m;
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
                        ap.x[n12 + n13 * ap.s] = n9;
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
                        final int n20 = ap.x[n12 + n13 * ap.s];
                        ap.x[n12 + n13 * ap.s] = (0xFF000000 | (((n20 & 0xFF0000) * n16 >> 8) + n17 & 0xFF0000) | (((n20 & 0xFF00) * n16 >> 8) + n18 & 0xFF00) | (((n20 & 0xFF) * n16 >> 8) + n19 & 0xFF));
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
                        ap.x[n12 + n13 * ap.s] = n9;
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
                        final int n27 = ap.x[n12 + n13 * ap.s];
                        ap.x[n12 + n13 * ap.s] = (0xFF000000 | (((n27 & 0xFF0000) * n23 >> 8) + n24 & 0xFF0000) | (((n27 & 0xFF00) * n23 >> 8) + n25 & 0xFF00) | (((n27 & 0xFF) * n23 >> 8) + n26 & 0xFF));
                    }
                }
            }
        }
    }
    
    public static void if(final ap ap, final int n, final int n2, int n3, int n4, final int n5) {
        final int s = ap.s;
        final int r = ap.r;
        final int n6 = (n < 0) ? 0 : n;
        if (n > s) {
            return;
        }
        final int n7 = (n + n3 > s) ? s : (n + n3);
        if (n7 < 0) {
            return;
        }
        final int n8 = (n2 < 0) ? 0 : n2;
        if (n2 > r) {
            return;
        }
        final int n9 = (n2 + n4 > r) ? r : (n2 + n4);
        if (n9 < 0) {
            return;
        }
        int n10 = n8 * s + n6;
        final int n11 = s - (n7 - n6);
        n3 = n7 - n6;
        n4 = n9 - n8;
        if ((n5 & 0xFF000000) != 0xFF000000 && (n5 & 0xFF000000) != 0x0) {
            final int n12 = n5 >> 24 & 0xFF;
            final int n13 = 255 - n12;
            final int n14 = (n5 & 0xFF0000) * n12 >> 8;
            final int n15 = (n5 & 0xFF00) * n12 >> 8;
            final int n16 = (n5 & 0xFF) * n12 >> 8;
            for (int i = 0; i < n4; ++i) {
                for (int j = 0; j < n3; ++j) {
                    final int n17 = ap.x[n10];
                    ap.x[n10++] = (0xFF000000 | (((n17 & 0xFF0000) * n13 >> 8) + n14 & 0xFF0000) | (((n17 & 0xFF00) * n13 >> 8) + n15 & 0xFF00) | (((n17 & 0xFF) * n13 >> 8) + n16 & 0xFF));
                }
                n10 += n11;
            }
        }
        else if ((n5 & 0xFF000000) != 0x0) {
            for (int k = 0; k < n4; ++k) {
                for (int l = 0; l < n3; ++l) {
                    ap.x[n10++] = n5;
                }
                n10 += n11;
            }
        }
    }
    
    public static void a(final ap ap, final int n, final ap ap2, final int n2, int n3) {
        if (ap.x == null || ap2.x == null) {
            return;
        }
        if (n2 < ap2.s && n3 < ap2.r) {
            final int n4 = 0;
            int n5 = 0;
            int s = n2 + ap.s;
            int r = n3 + ap.r;
            if (s > ap2.s) {
                s = ap2.s;
            }
            if (s > ap2.l + ap2.n) {
                s = ap2.l + ap2.n;
            }
            if (r > ap2.r) {
                r = ap2.r;
            }
            if (r > ap2.p + ap2.m) {
                r = ap2.p + ap2.m;
            }
            final int n6 = (ap2.m > 0) ? ap2.m : 0;
            if (n3 < n6) {
                n5 += Math.abs(n3 - n6);
                n3 = n6;
            }
            int n7 = n3 * ap2.s;
            int n8 = n5 * ap.s + n4;
            final int n9 = ap.x[0];
            for (int i = n3; i < r; ++i) {
                int n10 = n8;
                int n11 = n7 + n2;
                for (int j = n2; j < s; ++j) {
                    if (ap.x[n10] != n9) {
                        final int n12 = ap2.x[n11];
                        boolean b = true;
                        final int n13 = (n12 & 0xFF0000) >> 16;
                        final int n14 = (n12 & 0xFF00) >> 8;
                        final int n15 = n12 & 0xFF;
                        if (n15 + n14 + n13 > 384) {
                            b = false;
                        }
                        final int n16 = 100;
                        ap2.x[n11] = (0xFF000000 | a(n13, b, n16) << 16 | a(n14, b, n16) << 8 | a(n15, b, n16));
                    }
                    ++n10;
                    ++n11;
                }
                n8 += ap.s;
                n7 += ap2.s;
            }
        }
    }
    
    public static final int a(final int n, final boolean b, final int n2) {
        final int n3 = n + (b ? n2 : (-n2));
        return (n3 > 255) ? (n - n2) : ((n3 < 0) ? (n + n2) : n3);
    }
}
