// 
// Decompiled by Procyon v0.5.30
// 

public class d
{
    al[] a;
    
    d() {
        this.a = new al[4];
    }
    
    static void a(final al[] array, final int n, final t t, final int n2, final int n3) {
        final float n4 = t.bc * t.bi;
        for (int i = 0; i < n; ++i) {
            final float n5 = t.bc * array[i].try + t.aI * array[i].if;
            final float n6 = t.bi * n5 + t.bB * array[i].byte;
            if (n6 >= 0.01f) {
                final float n7 = t.aZ / n6;
                array[i].int = (-t.bc * array[i].if + t.aI * array[i].try) * n7 + n2;
                array[i].for = -(t.bi * array[i].byte - t.bB * n5) * n7 + n3;
                array[i].new = true;
            }
            else {
                array[i].new = false;
            }
        }
    }
    
    void a(final al[] array, final int n, final int n2, final an an, final t t, final boolean b) {
        a(array, n * n2, t, t.a5.goto + (t.a1 >> 1), t.a5.else + (t.aS >> 1));
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
                    a(this.a, an.c, an.long, an.e, t.a5.c, t.a5.long, t.a5.e, t.a5.goto, t.a5.else, t.a5.d, t.a5.void, an.f, b);
                }
            }
            n5 += n;
            n6 += n;
        }
    }
    
    protected void a(final an an, final an an2, final al[] array, final boolean b) {
        a(array, an.c, an.long, an.e, an2.c, an2.long, an2.e, 0, 0, an2.long, an2.e, an.f, b);
    }
    
    private static void a(final al[] array, final int[] array2, final int n, final int n2, final int[] array3, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final boolean b, final boolean b2) {
        if (array2 == null || array3 == null) {
            return;
        }
        final int n9 = (n5 + n7 > n3) ? n3 : (n5 + n7);
        final int n10 = (n5 > 0) ? n5 : 0;
        final int n11 = (n6 + n8 > n4) ? n4 : (n6 + n8);
        final int n12 = (n6 > 0) ? n6 : 0;
        if ((n9 < array[0].int && n9 < array[1].int && n9 < array[2].int && n9 < array[3].int) || (array[0].int < n10 && array[1].int < n10 && array[2].int < n10 && array[3].int < n10) || (n11 < array[0].for && n11 < array[1].for && n11 < array[2].for && n11 < array[3].for) || (array[0].for < n12 && array[1].for < n12 && array[2].for < n12 && array[3].for < n12)) {
            return;
        }
        final float for1 = array[0].for;
        int n13 = 0;
        final float n14 = (for1 > array[1].for) ? array[n13 = 1].for : for1;
        final float n15 = (n14 > array[2].for) ? array[n13 = 2].for : n14;
        final float n16 = (n15 > array[3].for) ? array[n13 = 3].for : n15;
        int n17 = n13 - 1 & 0x3;
        int n18 = n13 + 1 & 0x3;
        int i = 3;
        int n19 = (n16 > n12) ? ((int)n16) : n12;
        int n20 = (int)array[n18].for;
        int n21 = (int)array[n17].for;
        while (i > 0) {
            final al al;
            final al al2;
            final float n22 = 1.0f / ((al = array[n17]).for - (al2 = array[n17 + 1 & 0x3]).for);
            final float do1 = al2.do;
            final float n23 = (al.do - al2.do) * n22;
            final float n25;
            float n24 = do1 + n23 * (n25 = n19 - al2.for);
            final float n27;
            float n26 = al2.a + (n27 = (al.a - al2.a) * n22) * n25;
            final float n29;
            float n28 = al2.int + (n29 = (al.int - al2.int) * n22) * n25;
            final al al3;
            final al al4;
            final float n30 = 1.0f / ((al3 = array[n18]).for - (al4 = array[n18 - 1 & 0x3]).for);
            final float do2 = al4.do;
            final float n31 = (al3.do - al4.do) * n30;
            final float n33;
            float n32 = do2 + n31 * (n33 = n19 - al4.for);
            final float n35;
            float n34 = al4.a + (n35 = (al3.a - al4.a) * n30) * n33;
            final float n37;
            float n36 = al4.int + (n37 = (al3.int - al4.int) * n30) * n33;
            final float n38 = n23;
            final float n39 = n27;
            final float n40 = n31;
            final float n41 = n35;
            final float n42 = Math.min(Math.min(n21, n20), n6 + n8);
            float n43 = n19 * n3;
            try {
                while (n19 < n42) {
                    n28 += n29;
                    n36 += n37;
                    n24 += n38;
                    n32 += n40;
                    n26 += n39;
                    n34 += n41;
                    final int n44;
                    final int n45;
                    if ((n44 = ((n28 > n10) ? ((int)n28) : n10)) < (n45 = (int)((n36 > n9) ? (n9 + n43) : ((int)n36 + n43)))) {
                        final float n46 = n44 - n28;
                        final float n47 = 1.0f / (n36 - n28);
                        final float n48 = (n32 - n24) * n47;
                        int n49 = (int)((n24 + n48 * n46) * 65535.0f) - (b2 ? 32768 : 0);
                        final int n50 = (int)(n48 * 65535.0f);
                        final float n51 = (n34 - n26) * n47;
                        int n52 = (int)((n26 + n51 * n46) * 65535.0f) - (b2 ? 32768 : 0);
                        final int n53 = (int)(n51 * 65535.0f);
                        final int n54 = (int)(n44 + n43);
                        if (b2) {
                            if (b) {
                                for (int j = n54; j < n45; ++j) {
                                    n49 += n50;
                                    n52 += n53;
                                    final int if1 = if(n49, n52, array2, n, n2);
                                    if (if1 < 0) {
                                        array3[j] = if1;
                                    }
                                }
                            }
                            else {
                                for (int k = n54; k < n45; ++k) {
                                    n49 += n50;
                                    n52 += n53;
                                    array3[k] = a(n49, n52, array2, n, n2);
                                }
                            }
                        }
                        else if (b) {
                            for (int l = n54; l < n45; ++l) {
                                n49 += n50;
                                n52 += n53;
                                final int n55 = array2[(n49 >> 16) + (n52 >> 16) * n];
                                if (n55 < 0) {
                                    array3[l] = n55;
                                }
                            }
                        }
                        else {
                            for (int n56 = n54; n56 < n45; ++n56) {
                                n49 += n50;
                                n52 += n53;
                                array3[n56] = array2[(n49 >> 16) + (n52 >> 16) * n];
                            }
                        }
                    }
                    n43 += n3;
                    ++n19;
                }
            }
            catch (Exception ex) {}
            if (n19 == n4) {
                return;
            }
            if (n21 > n20) {
                n20 = (int)array[n18 = (n18 + 1 & 0x3)].for;
            }
            else if (n21 < n20) {
                n21 = (int)array[n17 = (n17 - 1 & 0x3)].for;
            }
            else {
                n20 = (int)array[n18 = (n18 + 1 & 0x3)].for;
                n21 = (int)array[n17 = (n17 - 1 & 0x3)].for;
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
    
    public static void a(final an an, final an an2) {
        final an an3 = new an();
        an3.long = an.long;
        an3.e = an.e;
        an3.c = an.c;
        an3.f = an.f;
        final an an6;
        final an an5;
        final an an4 = an5 = (an6 = new an());
        final int long1 = an2.long;
        an5.d = long1;
        an6.long = long1;
        final an an7 = an4;
        final an an8 = an4;
        final int e = an2.e;
        an8.void = e;
        an7.e = e;
        an4.c = an2.c;
        an4.f = an2.f;
        for (int i = 0; i < an2.long * an2.e; ++i) {
            an4.c[i] = -16711936;
        }
        a(an3, an4, 0, 0, an4.long, an4.e);
    }
    
    public static void a(final an an, final an an2, final int n, final int n2, final int n3, final int n4) {
        a(an, 0, an.long, an2, n, n2, n3, n4);
    }
    
    public static void a(final an an, final int n, final int n2, final an an2, int n3, int n4, final int n5, final int n6) {
        if (an.c == null || an2.c == null) {
            return;
        }
        if (n3 < an2.long && n4 < an2.e) {
            int n7 = n;
            int n8 = 0;
            int long1 = n3 + n5;
            int e = n4 + n6;
            if (long1 > an2.long) {
                long1 = an2.long;
            }
            if (long1 > an2.d + an2.goto) {
                long1 = an2.d + an2.goto;
            }
            if (e > an2.e) {
                e = an2.e;
            }
            if (e > an2.void + an2.else) {
                e = an2.void + an2.else;
            }
            int n9 = 0;
            final int n10 = (an2.goto > 0) ? an2.goto : 0;
            final int n11 = (an2.else > 0) ? an2.else : 0;
            if (n2 == n5 && an.e == n6) {
                if (n3 < n10) {
                    n7 += Math.abs(n3 - n10);
                    n3 = n10;
                }
                if (n4 < n11) {
                    n8 += Math.abs(n4 - n11);
                    n4 = n11;
                }
                int n12 = n4 * an2.long;
                int n13 = n8 * an.long + n7;
                if (an.f) {
                    for (int i = n4; i < e; ++i) {
                        int n14 = n13;
                        int n15 = n12 + n3;
                        for (int j = n3; j < long1; ++j) {
                            if ((an.c[n14] & 0xFF000000) != 0x0) {
                                an2.c[n15] = an.c[n14];
                            }
                            ++n14;
                            ++n15;
                        }
                        n13 += an.long;
                        n12 += an2.long;
                    }
                }
                else {
                    for (int k = n4; k < e; ++k) {
                        int n16 = n13;
                        int n17 = n12 + n3;
                        for (int l = n3; l < long1; ++l) {
                            an2.c[n17++] = an.c[n16++];
                        }
                        n13 += an.long;
                        n12 += an2.long;
                    }
                }
            }
            else {
                final int n18 = (an.e << 16) / n6;
                final int n19 = (n2 << 16) / n5;
                if (n3 < n10) {
                    n7 += Math.abs(n3 - n10) * n2 / n5;
                    n3 = n10;
                }
                if (n4 < n11) {
                    n8 = Math.abs(n4 - n11) * an.e / n6;
                    n4 = n11;
                }
                int n20 = n4 * an2.long;
                for (int n21 = n4; n21 < e; ++n21) {
                    final int n22 = ((n9 >> 16) + n8) * an.long + n7;
                    int n23 = 0;
                    int n24 = n20 + n3;
                    for (int n25 = n3; n25 < long1; ++n25) {
                        an2.c[n24++] = an.c[(n23 >> 16) + n22];
                        n23 += n19;
                    }
                    n9 += n18;
                    n20 += an2.long;
                }
            }
        }
    }
    
    public static void a(final an an, final int n, final int n2, final int n3, final int n4, final int n5) {
        a(an, n, n2, n3, n4, (an.goto > 0) ? an.goto : 0, (an.long < an.goto + an.d) ? an.long : (an.goto + an.d), (an.else > 0) ? an.else : 0, (an.e < an.else + an.void) ? an.e : (an.else + an.void), n5);
    }
    
    public static void a(final an an, int n, int n2, int n3, int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        final int abs = Math.abs(n3 - n);
        final int abs2 = Math.abs(n4 - n2);
        n += an.goto;
        n2 += an.else;
        n3 += an.goto;
        n4 += an.else;
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
                        an.c[n12 + n13 * an.long] = n9;
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
                        final int n20 = an.c[n12 + n13 * an.long];
                        an.c[n12 + n13 * an.long] = (0xFF000000 | (((n20 & 0xFF0000) * n16 >> 8) + n17 & 0xFF0000) | (((n20 & 0xFF00) * n16 >> 8) + n18 & 0xFF00) | (((n20 & 0xFF) * n16 >> 8) + n19 & 0xFF));
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
                        an.c[n12 + n13 * an.long] = n9;
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
                        final int n27 = an.c[n12 + n13 * an.long];
                        an.c[n12 + n13 * an.long] = (0xFF000000 | (((n27 & 0xFF0000) * n23 >> 8) + n24 & 0xFF0000) | (((n27 & 0xFF00) * n23 >> 8) + n25 & 0xFF00) | (((n27 & 0xFF) * n23 >> 8) + n26 & 0xFF));
                    }
                }
            }
        }
    }
    
    public static void if(final an an, final int n, final int n2, int n3, int n4, final int n5) {
        final int long1 = an.long;
        final int e = an.e;
        final int n6 = (n < 0) ? 0 : n;
        if (n > long1) {
            return;
        }
        final int n7 = (n + n3 > long1) ? long1 : (n + n3);
        if (n7 < 0) {
            return;
        }
        final int n8 = (n2 < 0) ? 0 : n2;
        if (n2 > e) {
            return;
        }
        final int n9 = (n2 + n4 > e) ? e : (n2 + n4);
        if (n9 < 0) {
            return;
        }
        int n10 = n8 * long1 + n6;
        final int n11 = long1 - (n7 - n6);
        n3 = n7 - n6;
        n4 = n9 - n8;
        for (int i = 0; i < n4; ++i) {
            for (int j = 0; j < n3; ++j) {
                an.c[n10++] = n5;
            }
            n10 += n11;
        }
    }
}
