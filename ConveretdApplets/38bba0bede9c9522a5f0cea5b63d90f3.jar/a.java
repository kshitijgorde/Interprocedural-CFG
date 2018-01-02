// 
// Decompiled by Procyon v0.5.30
// 

final class a
{
    private int[] a;
    private int[] b;
    private int c;
    private Connect4 d;
    private int e;
    
    public final int a() {
        return this.e;
    }
    
    private boolean c(final int n) {
        return this.b[n] < 114;
    }
    
    private int d(final int n, final int n2) {
        final int n3 = this.b[n2];
        this.a[n3] = n;
        final int[] b = this.b;
        b[n2] += 13;
        return n3;
    }
    
    private int b(final int n, final int[] array, int n2) {
        int n3 = -1;
        final int[] array2 = { 0 };
        final int[] array3 = { 0 };
        int n4 = -1;
        array[0] = -1;
        --n2;
        int i = 3;
        array3[0] = 0;
        while (i < 10) {
            if (this.c(i)) {
                if (array[0] == -1) {
                    array[0] = i;
                }
                final int n5;
                final int d = this.d(n, n5 = i);
                Label_0311: {
                    switch (this.a(d, array3)) {
                        case 1: {
                            array[0] = i;
                            i = 10;
                            n3 = 1;
                            break;
                        }
                        case -1: {
                            if (n3 == -1) {
                                array[0] = i;
                                n3 = 0;
                                i = 10;
                                break;
                            }
                            break;
                        }
                        default: {
                            if (n2 != 0) {
                                switch (this.b((n == -1) ? 1 : -1, array2, n2)) {
                                    case -1: {
                                        if (n3 != 1 || array3[0] > n4) {
                                            array[0] = i;
                                            n3 = 1;
                                            n4 = array3[0];
                                            break Label_0311;
                                        }
                                        break Label_0311;
                                    }
                                    case 0: {
                                        if (n3 == -1 || (n3 == 0 && array3[0] > n4)) {
                                            array[0] = i;
                                            n3 = 0;
                                            n4 = array3[0];
                                            break Label_0311;
                                        }
                                        break Label_0311;
                                    }
                                    case 1: {
                                        if (n3 == -1 && array3[0] > n4) {
                                            array[0] = i;
                                            n4 = array3[0];
                                            break Label_0311;
                                        }
                                        break Label_0311;
                                    }
                                }
                            }
                            else {
                                if (n3 == -1 && array3[0] > n4) {
                                    array[0] = i;
                                    n3 = 0;
                                    n4 = array3[0];
                                    break;
                                }
                                break;
                            }
                            break;
                        }
                    }
                }
                this.a[d] = 0;
                final int[] b = this.b;
                final int n6 = n5;
                b[n6] -= 13;
            }
            if (n2 == this.c) {
                final StringBuffer sb;
                (sb = new StringBuffer(30)).append("Analyzing");
                for (int j = 3; j < i; ++j) {
                    sb.append(".");
                }
                this.d.a(sb.toString());
            }
            ++i;
            ++this.e;
        }
        return n3;
    }
    
    public a(final Connect4 d) {
        this.a = new int[156];
        this.b = new int[13];
        this.d = d;
        for (int i = 0; i < 156; ++i) {
            if (i % 13 > 2 && i % 13 < 10 && i / 13 > 2 && i / 13 < 9) {
                this.a[i] = 0;
            }
            else {
                this.a[i] = 99;
            }
        }
        for (int j = 0; j < 13; ++j) {
            this.b[j] = j + 39;
        }
    }
    
    public final boolean a(final int n) {
        return n >= 0 && n < 7 && this.c(n + 3);
    }
    
    public final int a(int n, final int[] array) {
        final int n2 = this.a[n];
        int n6;
        int n5;
        int n4;
        int n3 = n4 = (n5 = (n6 = 1));
        int n10;
        int n9;
        int n8;
        int n7 = n8 = (n9 = (n10 = 0));
        int n18;
        int n17;
        int n16;
        int n15;
        int n14;
        int n13;
        int n12;
        int n11 = n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = n))))));
        int n34;
        int n33;
        int n32;
        int n31;
        int n30;
        int n29;
        int n28;
        int n27;
        int n26;
        int n25;
        int n24;
        int n23;
        int n22;
        int n21;
        int n20;
        int n19 = n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = (n32 = (n33 = (n34 = 1))))))))))))));
        if (n2 == 0 || n2 == 99) {
            return 0;
        }
        n = ((this.a[n] == -1) ? 1 : -1);
        for (int i = 1; i < 4; ++i) {
            if (n21 != 0) {
                final int n35;
                if ((n35 = this.a[++n14]) != 99) {
                    if (n20 != 0) {
                        if (n35 == n2) {
                            ++n4;
                        }
                        else {
                            n20 = 0;
                            if (n35 == 0) {
                                ++n8;
                            }
                            else {
                                n21 = 0;
                            }
                        }
                    }
                    else if (n35 != n) {
                        ++n8;
                    }
                    else {
                        n21 = 0;
                    }
                }
                else {
                    n21 = 0;
                }
            }
            if (n22 != 0) {
                final int n36;
                if ((n36 = this.a[--n13]) != 99) {
                    if (n19 != 0) {
                        if (n36 == n2) {
                            ++n4;
                        }
                        else {
                            n19 = 0;
                            if (n36 == 0) {
                                ++n8;
                            }
                            else {
                                n22 = 0;
                            }
                        }
                    }
                    else if (n36 != n) {
                        ++n8;
                    }
                    else {
                        n22 = 0;
                    }
                }
                else {
                    n22 = 0;
                }
            }
            if (n25 != 0) {
                final int[] a = this.a;
                n16 += 14;
                final int n37;
                if ((n37 = a[n16]) != 99) {
                    if (n23 != 0) {
                        if (n37 == n2) {
                            ++n3;
                        }
                        else {
                            n23 = 0;
                            if (n37 == 0) {
                                ++n7;
                            }
                            else {
                                n25 = 0;
                            }
                        }
                    }
                    else if (n37 != n) {
                        ++n7;
                    }
                    else {
                        n25 = 0;
                    }
                }
                else {
                    n25 = 0;
                }
            }
            if (n26 != 0) {
                final int[] a2 = this.a;
                n17 -= 14;
                final int n38;
                if ((n38 = a2[n17]) != 99) {
                    if (n24 != 0) {
                        if (n38 == n2) {
                            ++n3;
                        }
                        else {
                            n24 = 0;
                            if (n38 == 0) {
                                ++n7;
                            }
                            else {
                                n26 = 0;
                            }
                        }
                    }
                    else if (n38 != n) {
                        ++n7;
                    }
                    else {
                        n26 = 0;
                    }
                }
                else {
                    n26 = 0;
                }
            }
            if (n29 != 0) {
                final int[] a3 = this.a;
                n15 += 12;
                final int n39;
                if ((n39 = a3[n15]) != 99) {
                    if (n27 != 0) {
                        if (n39 == n2) {
                            ++n5;
                        }
                        else {
                            n27 = 0;
                            if (n39 == 0) {
                                ++n9;
                            }
                            else {
                                n29 = 0;
                            }
                        }
                    }
                    else if (n39 != n) {
                        ++n9;
                    }
                    else {
                        n29 = 0;
                    }
                }
                else {
                    n29 = 0;
                }
            }
            if (n30 != 0) {
                final int[] a4 = this.a;
                n18 -= 12;
                final int n40;
                if ((n40 = a4[n18]) != 99) {
                    if (n28 != 0) {
                        if (n40 == n2) {
                            ++n5;
                        }
                        else {
                            n28 = 0;
                            if (n40 == 0) {
                                ++n9;
                            }
                            else {
                                n30 = 0;
                            }
                        }
                    }
                    else if (n40 != n) {
                        ++n9;
                    }
                    else {
                        n30 = 0;
                    }
                }
                else {
                    n30 = 0;
                }
            }
            if (n33 != 0) {
                final int[] a5 = this.a;
                n12 += 13;
                final int n41;
                if ((n41 = a5[n12]) != 99) {
                    if (n31 != 0) {
                        if (n41 == n2) {
                            ++n6;
                        }
                        else {
                            n31 = 0;
                            if (n41 == 0) {
                                ++n10;
                            }
                            else {
                                n33 = 0;
                            }
                        }
                    }
                    else if (n41 != n) {
                        ++n10;
                    }
                    else {
                        n33 = 0;
                    }
                }
                else {
                    n33 = 0;
                }
            }
            if (n34 != 0) {
                final int[] a6 = this.a;
                n11 -= 13;
                final int n42;
                if ((n42 = a6[n11]) != 99) {
                    if (n32 != 0) {
                        if (n42 == n2) {
                            ++n6;
                        }
                        else {
                            n32 = 0;
                            if (n42 == 0) {
                                ++n10;
                            }
                            else {
                                n34 = 0;
                            }
                        }
                    }
                    else if (n42 != n) {
                        ++n10;
                    }
                    else {
                        n34 = 0;
                    }
                }
                else {
                    n34 = 0;
                }
            }
        }
        array[0] = 0;
        if (n8 + n4 > 3) {
            array[0] = (n4 - 1) * 6 + n8;
        }
        if (n7 + n3 > 3) {
            final int n43 = 0;
            array[n43] += (n3 - 1) * 6 + n7;
        }
        if (n9 + n5 > 3) {
            final int n44 = 0;
            array[n44] += (n5 - 1) * 6 + n9;
        }
        if (n10 + n6 > 3) {
            final int n45 = 0;
            array[n45] += (n6 - 1) * 6 + n10;
        }
        if (n4 > 3 || n3 > 3 || n5 > 3 || n6 > 3) {
            return 1;
        }
        for (int j = 3; j < 10; ++j) {
            if (this.b[j] < 156) {
                return 0;
            }
        }
        return -1;
    }
    
    public final int a(final int n, final int n2) {
        int d = -1;
        if (this.a(n2)) {
            d = this.d(n, n2 + 3);
        }
        return d;
    }
    
    public final int a(int b, final int[] array, final int n) {
        this.c = n - 1;
        this.e = 0;
        b = this.b(1, array, n);
        final int n2 = 0;
        array[n2] -= 3;
        return b;
    }
    
    public static int b(final int n, final int n2) {
        return (n + 3) * 13 + n2 + 3;
    }
    
    public final int b(int n) {
        return (n = n) / 13 - 3;
    }
    
    public final int c(final int n, final int n2) {
        return this.a[b(n, n2)];
    }
}
