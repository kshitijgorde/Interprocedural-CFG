import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class bk extends ai
{
    int a(final Point point) {
        final boolean l = c.l;
        point.x <<= 16;
        point.y <<= 16;
        super.ae.a(point);
        int n = point.x + 16384 >> 16;
        int n2 = point.y + 16384 >> 16;
        final int c = super.af.c;
        int n3 = 1;
        int n4 = c;
        if (n < 0) {
            n = 0;
        }
        if (n >= c - 1) {
            n = c - 1;
            n3 = 0;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        if (n2 >= super.af.d - 1) {
            n2 = super.af.d - 1;
            n4 = 0;
        }
        int n17 = 0;
        Label_0839: {
            if (super.ag) {
                final int[] b = super.af.b;
                final int n5 = b[n2 * c + n];
                final int n6 = b[n2 * c + n + n3];
                final int n7 = b[n2 * c + n + n4];
                final int n8 = b[n2 * c + n + n4 + n3];
                final int n9 = point.y + 16384 >> 8 & 0xFF;
                final int n10 = point.x + 16384 >> 8 & 0xFF;
                int n11 = (255 - n10) * (255 - n9) >> 8;
                int n12 = n10 * (255 - n9) >> 8;
                int n13 = (255 - n10) * n9 >> 8;
                int n14 = n10 * n9 >> 8;
                final int n15 = (n5 >> 24 & 0xFF) * n11 + (n6 >> 24 & 0xFF) * n12 + (n7 >> 24 & 0xFF) * n13 + (n8 >> 24 & 0xFF) * n14 & 0xFF00;
                if ((n5 >> 24 & 0xFF) == 0x0) {
                    n11 = 0;
                }
                if ((n6 >> 24 & 0xFF) == 0x0) {
                    n12 = 0;
                }
                if ((n7 >> 24 & 0xFF) == 0x0) {
                    n13 = 0;
                }
                if ((n8 >> 24 & 0xFF) == 0x0) {
                    n14 = 0;
                }
                final int n16 = n11 + n12 + n13 + n14;
                if (n16 == 0) {
                    n17 = 0;
                    if (!l) {
                        break Label_0839;
                    }
                }
                if (n16 > 253) {
                    n17 = (((n5 & 0xFF) * n11 + (n6 & 0xFF) * n12 + (n7 & 0xFF) * n13 + (n8 & 0xFF) * n14 & 0xFF00) >> 8) + ((n5 >> 8 & 0xFF) * n11 + (n6 >> 8 & 0xFF) * n12 + (n7 >> 8 & 0xFF) * n13 + (n8 >> 8 & 0xFF) * n14 & 0xFF00) + (((n5 >> 16 & 0xFF) * n11 + (n6 >> 16 & 0xFF) * n12 + (n7 >> 16 & 0xFF) * n13 + (n8 >> 16 & 0xFF) * n14 & 0xFF00) << 8) + (n15 << 16);
                    if (!l) {
                        break Label_0839;
                    }
                }
                final int n18 = 65536 / n16;
                n17 = ((((n5 & 0xFF) * n11 + (n6 & 0xFF) * n12 + (n7 & 0xFF) * n13 + (n8 & 0xFF) * n14) * n18 & 0xFF0000) >> 16) + ((((n5 >> 8 & 0xFF) * n11 + (n6 >> 8 & 0xFF) * n12 + (n7 >> 8 & 0xFF) * n13 + (n8 >> 8 & 0xFF) * n14) * n18 & 0xFF0000) >> 8) + (((n5 >> 16 & 0xFF) * n11 + (n6 >> 16 & 0xFF) * n12 + (n7 >> 16 & 0xFF) * n13 + (n8 >> 16 & 0xFF) * n14) * n18 & 0xFF0000) + (n15 << 16);
                if (!l) {
                    break Label_0839;
                }
            }
            n17 = super.af.b[n + n2 * c];
        }
        if (super.ad != null) {
            return super.ad.b(n17);
        }
        return n17;
    }
    
    void a(final int[] array, int n, final int n2, final Point point) {
        final boolean l = c.l;
        point.x <<= 16;
        point.y <<= 16;
        super.ae.a(point);
        int n3 = point.x + 16384;
        int n4 = point.y + 16384;
        final int a = super.ae.a;
        final int b = super.ae.b;
        final int c = super.af.c;
        final int d = super.af.d;
        final int[] b2 = super.af.b;
        if (super.r) {
            if (super.ag) {
                int n5 = 0;
                while (true) {
                    Label_0988: {
                        if (!l) {
                            break Label_0988;
                        }
                        int n6 = n3 >> 16;
                        int n7 = n4 >> 16;
                        int n8 = c;
                        int n9 = 1;
                        if (n6 < 0) {
                            n6 = 0;
                        }
                        if (n6 >= c - 1) {
                            n6 = c - 1;
                            n9 = 0;
                        }
                        if (n7 < 0) {
                            n7 = 0;
                        }
                        if (n7 >= d - 1) {
                            n7 = d - 1;
                            n8 = 0;
                        }
                        final int n10 = b2[n7 * c + n6];
                        final int n11 = b2[n7 * c + n6 + n9];
                        final int n12 = b2[n7 * c + n6 + n8];
                        final int n13 = b2[n7 * c + n6 + n8 + n9];
                        final int n14 = n4 >> 8 & 0xFF;
                        final int n15 = n3 >> 8 & 0xFF;
                        int n16 = (255 - n15) * (255 - n14) >> 8;
                        int n17 = n15 * (255 - n14) >> 8;
                        int n18 = (255 - n15) * n14 >> 8;
                        int n19 = n15 * n14 >> 8;
                        final int n20 = (n10 >> 24 & 0xFF) * n16 + (n11 >> 24 & 0xFF) * n17 + (n12 >> 24 & 0xFF) * n18 + (n13 >> 24 & 0xFF) * n19 & 0xFF00;
                        if ((n10 >> 24 & 0xFF) == 0x0) {
                            n16 = 0;
                        }
                        if ((n11 >> 24 & 0xFF) == 0x0) {
                            n17 = 0;
                        }
                        if ((n12 >> 24 & 0xFF) == 0x0) {
                            n18 = 0;
                        }
                        if ((n13 >> 24 & 0xFF) == 0x0) {
                            n19 = 0;
                        }
                        final int n21 = n16 + n17 + n18 + n19;
                        int b3 = 0;
                        Label_0869: {
                            if (n21 == 0) {
                                b3 = 0;
                                if (!l) {
                                    break Label_0869;
                                }
                            }
                            if (n21 > 253) {
                                b3 = (((n10 & 0xFF) * n16 + (n11 & 0xFF) * n17 + (n12 & 0xFF) * n18 + (n13 & 0xFF) * n19 & 0xFF00) >> 8) + ((n10 >> 8 & 0xFF) * n16 + (n11 >> 8 & 0xFF) * n17 + (n12 >> 8 & 0xFF) * n18 + (n13 >> 8 & 0xFF) * n19 & 0xFF00) + (((n10 >> 16 & 0xFF) * n16 + (n11 >> 16 & 0xFF) * n17 + (n12 >> 16 & 0xFF) * n18 + (n13 >> 16 & 0xFF) * n19 & 0xFF00) << 8) + (n20 << 16);
                                if (!l) {
                                    break Label_0869;
                                }
                            }
                            final int n22 = 65536 / n21;
                            b3 = ((((n10 & 0xFF) * n16 + (n11 & 0xFF) * n17 + (n12 & 0xFF) * n18 + (n13 & 0xFF) * n19) * n22 & 0xFF0000) >> 16) + ((((n10 >> 8 & 0xFF) * n16 + (n11 >> 8 & 0xFF) * n17 + (n12 >> 8 & 0xFF) * n18 + (n13 >> 8 & 0xFF) * n19) * n22 & 0xFF0000) >> 8) + (((n10 >> 16 & 0xFF) * n16 + (n11 >> 16 & 0xFF) * n17 + (n12 >> 16 & 0xFF) * n18 + (n13 >> 16 & 0xFF) * n19) * n22 & 0xFF0000) + (n20 << 16);
                        }
                        if (super.ad != null) {
                            b3 = super.ad.b(b3);
                        }
                        int n23 = b3 >>> 24;
                        if (n23 > 0) {
                            ++n23;
                        }
                        final int n24 = 256 - n23;
                        final int n25 = array[n];
                        array[n++] = (((b3 & 0xFF00) * n23 + (n25 & 0xFF00) * n24 >> 8 & 0xFF00) | ((b3 & 0xFF00FF) * n23 + (n25 & 0xFF00FF) * n24 >> 8 & 0xFF00FF) | 0xFF000000);
                        n3 += a;
                        n4 += b;
                        ++n5;
                    }
                    if (n5 < n2) {
                        continue;
                    }
                    break;
                }
            }
            else {
                int n26 = 0;
                while (true) {
                    Label_1203: {
                        if (!l) {
                            break Label_1203;
                        }
                        int n27 = n3 >> 16;
                        int n28 = n4 >> 16;
                        Label_1045: {
                            if (n27 < 0) {
                                n27 = 0;
                                if (!l) {
                                    break Label_1045;
                                }
                            }
                            if (n27 >= c) {
                                n27 = c - 1;
                            }
                        }
                        Label_1071: {
                            if (n28 < 0) {
                                n28 = 0;
                                if (!l) {
                                    break Label_1071;
                                }
                            }
                            if (n28 >= d) {
                                n28 = d - 1;
                            }
                        }
                        int b4 = b2[n27 + n28 * c];
                        if (super.ad != null) {
                            b4 = super.ad.b(b4);
                        }
                        int n29 = b4 >>> 24;
                        if (n29 > 0) {
                            ++n29;
                        }
                        final int n30 = 256 - n29;
                        final int n31 = array[n];
                        array[n++] = (((b4 & 0xFF00) * n29 + (n31 & 0xFF00) * n30 >> 8 & 0xFF00) | ((b4 & 0xFF00FF) * n29 + (n31 & 0xFF00FF) * n30 >> 8 & 0xFF00FF) | 0xFF000000);
                        n3 += a;
                        n4 += b;
                        ++n26;
                    }
                    if (n26 < n2) {
                        continue;
                    }
                    break;
                }
            }
        }
        else if (super.ag) {
            int n32 = 0;
            while (true) {
                Label_2012: {
                    if (!l) {
                        break Label_2012;
                    }
                    int n33 = n3 >> 16;
                    int n34 = n4 >> 16;
                    int n35 = c;
                    int n36 = 1;
                    if (n33 < 0) {
                        n33 = 0;
                    }
                    if (n33 >= c - 1) {
                        n33 = c - 1;
                        n36 = 0;
                    }
                    if (n34 < 0) {
                        n34 = 0;
                    }
                    if (n34 >= d - 1) {
                        n34 = d - 1;
                        n35 = 0;
                    }
                    final int n37 = b2[n34 * c + n33];
                    final int n38 = b2[n34 * c + n33 + n36];
                    final int n39 = b2[n34 * c + n33 + n35];
                    final int n40 = b2[n34 * c + n33 + n35 + n36];
                    final int n41 = n4 >> 8 & 0xFF;
                    final int n42 = n3 >> 8 & 0xFF;
                    int n43 = (255 - n42) * (255 - n41) >> 8;
                    int n44 = n42 * (255 - n41) >> 8;
                    int n45 = (255 - n42) * n41 >> 8;
                    int n46 = n42 * n41 >> 8;
                    final int n47 = (n37 >> 24 & 0xFF) * n43 + (n38 >> 24 & 0xFF) * n44 + (n39 >> 24 & 0xFF) * n45 + (n40 >> 24 & 0xFF) * n46 & 0xFF00;
                    if ((n37 >> 24 & 0xFF) == 0x0) {
                        n43 = 0;
                    }
                    if ((n38 >> 24 & 0xFF) == 0x0) {
                        n44 = 0;
                    }
                    if ((n39 >> 24 & 0xFF) == 0x0) {
                        n45 = 0;
                    }
                    if ((n40 >> 24 & 0xFF) == 0x0) {
                        n46 = 0;
                    }
                    final int n48 = n43 + n44 + n45 + n46;
                    int b5 = 0;
                    Label_1969: {
                        if (n48 == 0) {
                            b5 = 0;
                            if (!l) {
                                break Label_1969;
                            }
                        }
                        if (n48 > 253) {
                            b5 = (((n37 & 0xFF) * n43 + (n38 & 0xFF) * n44 + (n39 & 0xFF) * n45 + (n40 & 0xFF) * n46 & 0xFF00) >> 8) + ((n37 >> 8 & 0xFF) * n43 + (n38 >> 8 & 0xFF) * n44 + (n39 >> 8 & 0xFF) * n45 + (n40 >> 8 & 0xFF) * n46 & 0xFF00) + (((n37 >> 16 & 0xFF) * n43 + (n38 >> 16 & 0xFF) * n44 + (n39 >> 16 & 0xFF) * n45 + (n40 >> 16 & 0xFF) * n46 & 0xFF00) << 8) + (n47 << 16);
                            if (!l) {
                                break Label_1969;
                            }
                        }
                        final int n49 = 65536 / n48;
                        b5 = ((((n37 & 0xFF) * n43 + (n38 & 0xFF) * n44 + (n39 & 0xFF) * n45 + (n40 & 0xFF) * n46) * n49 & 0xFF0000) >> 16) + ((((n37 >> 8 & 0xFF) * n43 + (n38 >> 8 & 0xFF) * n44 + (n39 >> 8 & 0xFF) * n45 + (n40 >> 8 & 0xFF) * n46) * n49 & 0xFF0000) >> 8) + (((n37 >> 16 & 0xFF) * n43 + (n38 >> 16 & 0xFF) * n44 + (n39 >> 16 & 0xFF) * n45 + (n40 >> 16 & 0xFF) * n46) * n49 & 0xFF0000) + (n47 << 16);
                    }
                    if (super.ad != null) {
                        b5 = super.ad.b(b5);
                    }
                    array[n++] = b5;
                    n3 += a;
                    n4 += b;
                    ++n32;
                }
                if (n32 < n2) {
                    continue;
                }
                break;
            }
        }
        else {
            final int n50 = n3 >> 16;
            final int n51 = n4 >> 16;
            if (super.aj && n2 > 32 && n50 >= 0 && n50 + n2 < c && n51 >= 0 && n51 < d) {
                System.arraycopy(b2, n50 + n51 * c, array, n, n2);
                if (!l) {
                    return;
                }
            }
            int n52 = 0;
            while (true) {
                Label_2225: {
                    if (!l) {
                        break Label_2225;
                    }
                    int n53 = n3 >> 16;
                    int n54 = n4 >> 16;
                    Label_2143: {
                        if (n53 < 0) {
                            n53 = 0;
                            if (!l) {
                                break Label_2143;
                            }
                        }
                        if (n53 >= c) {
                            n53 = c - 1;
                        }
                    }
                    Label_2169: {
                        if (n54 < 0) {
                            n54 = 0;
                            if (!l) {
                                break Label_2169;
                            }
                        }
                        if (n54 >= d) {
                            n54 = d - 1;
                        }
                    }
                    int b6 = b2[n53 + n54 * c];
                    if (super.ad != null) {
                        b6 = super.ad.b(b6);
                    }
                    array[n++] = b6;
                    n3 += a;
                    n4 += b;
                    ++n52;
                }
                if (n52 < n2) {
                    continue;
                }
                break;
            }
        }
    }
}
