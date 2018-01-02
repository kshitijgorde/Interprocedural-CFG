// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.awt.Rectangle;
import java.awt.Color;

public class LineSerie extends GraphSerie
{
    private k H;
    int I;
    
    public void setThickness(int i) {
        if (i < 1) {
            i = 1;
        }
        this.I = i;
    }
    
    void f() {
        final boolean g = GraphSerie.G;
        final Color color = super.e.getColor();
        int n = 0;
        while (true) {
            while (true) {
                Label_0117: {
                    if (!g) {
                        break Label_0117;
                    }
                    Label_0114: {
                        if (n < super.E && !g) {
                            break Label_0114;
                        }
                        if (super.o && !super.d.bc) {
                            super.e.setColor(super.u);
                            super.e.fillRect(super.A[n] - 2, super.B[n] - 2, 5, 5);
                        }
                        this.b(n, super.A[n] - 2 + super.y, super.B[n] - 2 - super.y);
                    }
                    ++n;
                }
                if (n < super.a.length) {
                    continue;
                }
                break;
            }
            super.e.setColor(color);
            if (!g) {
                return;
            }
            continue;
        }
    }
    
    void b() {
        final boolean g = GraphSerie.G;
        final Color color = super.e.getColor();
        super.e.setColor(super.s);
        if (!super.d.bc) {
            int n = 0;
            while (true) {
                Label_0114: {
                    if (!g) {
                        break Label_0114;
                    }
                    if ((n >= super.E || g) && n < super.a.length - 1) {
                        super.d.bI.a(super.e, super.A[n], super.B[n], super.A[n + 1], super.B[n + 1], this.I);
                    }
                    ++n;
                }
                if (n < super.a.length) {
                    continue;
                }
                break;
            }
        }
        else {
            final int[] array = new int[4];
            final int[] array2 = new int[4];
            final boolean bf = super.d.bf;
            final int n2 = bf ? super.d.p : super.d.o;
            final Rectangle j = super.d.bI.j();
            final Rectangle k = super.d.bI.k();
            if (!bf) {
                int n3 = 0;
                int n4 = 0;
                int n5 = 0;
                int n6 = 0;
                while (true) {
                    Label_1616: {
                        if (!g) {
                            break Label_1616;
                        }
                        Label_1613: {
                            if (n6 >= super.E || g) {
                                final int n7 = super.A[n6] + super.y;
                                final int n8 = super.A[n6 + 1] + super.y;
                                final int n9 = super.B[n6] - super.y;
                                final int n10 = super.B[n6 + 1] - super.y;
                                final boolean b = super.a[n6] < 0.0 && super.a[n6 + 1] > 0.0;
                                final boolean b2 = super.a[n6] > 0.0 && super.a[n6 + 1] < 0.0;
                                if (b || b2) {
                                    n3 = n8 - (n8 - n7) * (j.y - super.y - n10) / (n9 - n10);
                                    n5 = n8 - (n8 - n7) * (j.y - n10) / (n9 - n10);
                                    n4 = n8 + n2 - (n8 + n2 - (n7 + n2)) * (j.y - (n10 - n2)) / (n9 - n2 - (n10 - n2));
                                }
                                array[0] = n7;
                                array2[0] = n9;
                                final int[] array3 = new int[4];
                                final int[] array4 = new int[4];
                                final int[] array5 = new int[5];
                                final int[] array6 = new int[5];
                                int n11 = 0;
                                Label_1485: {
                                    if (b2) {
                                        array[1] = n3;
                                        array[2] = array[1] + n2;
                                        Label_0676: {
                                            if (n4 > j.x + j.width) {
                                                array5[0] = n5;
                                                array5[1] = j.x + j.width;
                                                n11 = n2 * (n4 - (j.x + j.width)) / (n4 - n5);
                                                array5[2] = j.x + j.width + n11;
                                                array5[3] = n8 + n2;
                                                array5[4] = n8;
                                                if (!g) {
                                                    break Label_0676;
                                                }
                                            }
                                            array3[0] = n5;
                                            array3[1] = n4;
                                            array3[2] = n8 + n2;
                                            array3[3] = n8;
                                        }
                                        array2[1] = j.y - super.y;
                                        array2[2] = array2[1] - n2;
                                        Label_0797: {
                                            if (n4 > j.x + j.width) {
                                                array6[0] = j.y;
                                                array6[1] = j.y;
                                                array6[2] = j.y - n11;
                                                array6[3] = n10 - n2;
                                                array6[4] = n10;
                                                if (!g) {
                                                    break Label_0797;
                                                }
                                            }
                                            array4[0] = j.y;
                                            array4[1] = j.y;
                                            array4[2] = n10 - n2;
                                            array4[3] = n10;
                                        }
                                        super.e.setColor(super.s);
                                        Label_0897: {
                                            if (n4 > j.x + j.width) {
                                                super.e.fillPolygon(array5, array6, 5);
                                                super.e.setColor(Color.black);
                                                super.e.drawPolygon(array5, array6, 5);
                                                if (!g) {
                                                    break Label_0897;
                                                }
                                            }
                                            super.e.fillPolygon(array3, array4, 4);
                                            super.e.setColor(Color.black);
                                            super.e.drawPolygon(array3, array4, 4);
                                        }
                                        if (!g) {
                                            break Label_1485;
                                        }
                                    }
                                    if (b) {
                                        Label_1161: {
                                            if (n5 < j.x + j.width && n4 > j.x + j.width) {
                                                array5[0] = n7;
                                                array5[1] = n5;
                                                array5[2] = j.x + j.width;
                                                n11 = (n5 + n2 - (j.x + j.width)) * n2 / (j.x + j.width + n2 - n5);
                                                array5[3] = j.x + j.width + n11;
                                                array5[4] = n7 + n2;
                                                if (!g) {
                                                    break Label_1161;
                                                }
                                            }
                                            if (n5 >= j.x + j.width) {
                                                array5[0] = n7;
                                                if (n4 != (array5[1] = n5)) {
                                                    n11 = n2 * (n5 - (j.x + j.width)) / (n4 - n5);
                                                }
                                                array5[2] = j.x + j.width + n11;
                                                array5[3] = j.x + j.width + n11 + n2;
                                                array5[4] = n7 + n2;
                                                if (!g) {
                                                    break Label_1161;
                                                }
                                            }
                                            array[1] = n5;
                                            array[2] = n4;
                                        }
                                        array3[0] = n3;
                                        array3[2] = (array3[1] = n8) + n2;
                                        array3[3] = n3 + n2;
                                        Label_1361: {
                                            if (n5 < j.x + j.width && n4 > j.x + j.width) {
                                                array6[0] = n9;
                                                array6[1] = j.y;
                                                array6[2] = j.y;
                                                array6[3] = j.y - n11;
                                                array6[4] = n9 - n2;
                                                if (!g) {
                                                    break Label_1361;
                                                }
                                            }
                                            if (n5 >= j.x + j.width) {
                                                array6[0] = n9;
                                                array6[1] = j.y;
                                                array6[2] = j.y - n11;
                                                array6[3] = j.y - n11 - n2;
                                                array6[4] = n9 - n2;
                                                if (!g) {
                                                    break Label_1361;
                                                }
                                            }
                                            array2[1] = j.y;
                                            array2[2] = j.y;
                                        }
                                        array4[0] = j.y - super.y;
                                        array4[2] = (array4[1] = n10) - n2;
                                        array4[3] = j.y - super.y - n2;
                                        super.e.setColor(super.s);
                                        super.e.fillPolygon(array3, array4, 4);
                                        super.e.setColor(Color.black);
                                        super.e.drawPolygon(array3, array4, 4);
                                        if (!g) {
                                            break Label_1485;
                                        }
                                    }
                                    array[1] = n8;
                                    array[2] = array[1] + n2;
                                    array2[1] = n10;
                                    array2[2] = array2[1] - n2;
                                }
                                array[3] = n7 + n2;
                                array2[3] = n9 - n2;
                                if (b && n4 > j.x + j.width) {
                                    super.e.setColor(super.s);
                                    super.e.fillPolygon(array5, array6, 5);
                                    super.e.setColor(Color.black);
                                    super.e.drawPolygon(array5, array6, 5);
                                    if (!g) {
                                        break Label_1613;
                                    }
                                }
                                super.e.setColor(super.s);
                                super.e.fillPolygon(array, array2, 4);
                                super.e.setColor(Color.black);
                                super.e.drawPolygon(array, array2, 4);
                            }
                        }
                        ++n6;
                    }
                    if (n6 < super.a.length - 1) {
                        continue;
                    }
                    break;
                }
            }
            else {
                int n12 = super.a.length - 1;
                while (true) {
                    Label_3110: {
                        if (!g) {
                            break Label_3110;
                        }
                        Label_3107: {
                            if (n12 > super.E || g) {
                                final int n13 = super.A[n12] + super.y;
                                final int n14 = super.A[n12 - 1] + super.y;
                                final int n15 = super.B[n12] - super.y;
                                final int n16 = super.B[n12 - 1] - super.y;
                                int n17 = 0;
                                int n18 = 0;
                                int n19 = 0;
                                final boolean b3 = super.a[n12] < 0.0 && super.a[n12 - 1] > 0.0;
                                final boolean b4 = super.a[n12] > 0.0 && super.a[n12 - 1] < 0.0;
                                if (b3 || b4) {
                                    n17 = n15 - (n15 - n16) * (n13 - (k.x + super.y)) / (n13 - n14);
                                    n19 = n15 - (n15 - n16) * (n13 - k.x) / (n13 - n14);
                                    n18 = n16 - n2 + (n15 - n2 - (n16 - n2)) * (k.x - (n14 + n2)) / (n13 + n2 - (n14 + n2));
                                }
                                array[0] = n13;
                                array2[0] = n15;
                                final int[] array7 = new int[4];
                                final int[] array8 = new int[4];
                                final int[] array9 = new int[5];
                                final int[] array10 = new int[5];
                                int n20 = 0;
                                Label_3012: {
                                    if (b4) {
                                        array2[2] = (array2[1] = n17) - n2;
                                        array2[3] = n15 - n2;
                                        Label_2179: {
                                            if (n19 > k.y && n18 < k.y) {
                                                array10[0] = n19;
                                                array10[1] = k.y;
                                                n20 = n2 * (k.y - n18) / (n19 - n18);
                                                array10[2] = k.y - n20;
                                                array10[3] = n16 - n2;
                                                array10[4] = n16;
                                                if (!g) {
                                                    break Label_2179;
                                                }
                                            }
                                            if (n19 <= k.y) {
                                                n20 = n2 * (k.y - n19) / (n19 - n18);
                                                array10[0] = k.y - n20;
                                                array10[1] = k.y - n20;
                                                array10[2] = array10[1] - n2;
                                                array10[3] = n16 - n2;
                                                array10[4] = n16;
                                                if (!g) {
                                                    break Label_2179;
                                                }
                                            }
                                            array8[0] = n19;
                                            array8[2] = (array8[1] = n16) - n2;
                                            array8[3] = n18;
                                        }
                                        array[1] = k.x + super.y;
                                        array[2] = k.x + super.y + n2;
                                        array[3] = n13 + n2;
                                        Label_2388: {
                                            if (n19 > k.y && n18 < k.y) {
                                                array9[0] = k.x;
                                                array9[1] = k.x;
                                                array9[2] = k.x + n20;
                                                array9[3] = n14 + n2;
                                                array9[4] = n14;
                                                if (!g) {
                                                    break Label_2388;
                                                }
                                            }
                                            if (n19 <= k.y) {
                                                array9[0] = k.x + n20;
                                                array9[1] = k.x + n20;
                                                array9[2] = k.x + n20 + n2;
                                                array9[3] = n14 + n2;
                                                array9[4] = n14;
                                                if (!g) {
                                                    break Label_2388;
                                                }
                                            }
                                            array7[0] = k.x;
                                            array7[2] = (array7[1] = n14) + n2;
                                            array7[3] = k.x;
                                        }
                                        super.e.setColor(super.s);
                                        Label_2482: {
                                            if (n18 < k.y) {
                                                super.e.fillPolygon(array9, array10, 5);
                                                super.e.setColor(Color.black);
                                                super.e.drawPolygon(array9, array10, 5);
                                                if (!g) {
                                                    break Label_2482;
                                                }
                                            }
                                            super.e.fillPolygon(array7, array8, 4);
                                            super.e.setColor(Color.black);
                                            super.e.drawPolygon(array7, array8, 4);
                                        }
                                        if (!g) {
                                            break Label_3012;
                                        }
                                    }
                                    if (b3) {
                                        Label_2680: {
                                            if (n19 > k.y && n18 < k.y) {
                                                array10[1] = (array10[0] = n15) - n2;
                                                n20 = (k.y - n18) * n2 / (n19 - n18);
                                                array10[2] = k.y - n20;
                                                array10[3] = k.y;
                                                array10[4] = n19;
                                                if (!g) {
                                                    break Label_2680;
                                                }
                                            }
                                            if (n19 <= k.y) {
                                                n20 = n2 * (k.y - n19) / (n19 - n18);
                                                array10[0] = k.y - n20;
                                                array10[1] = k.y - n20;
                                                array10[2] = array10[1] - n2;
                                                array10[3] = n15 - n2;
                                                array10[4] = n15;
                                                if (!g) {
                                                    break Label_2680;
                                                }
                                            }
                                            array2[1] = n15 - n2;
                                            array2[2] = n18;
                                            array2[3] = n19;
                                        }
                                        array8[0] = n19;
                                        array8[2] = (array8[1] = n16) - n2;
                                        array8[3] = n19 - n2;
                                        Label_2873: {
                                            if (n19 > k.y && n18 < k.y) {
                                                array9[1] = (array9[0] = n13) + n2;
                                                array9[2] = k.x + n20;
                                                array9[3] = k.x;
                                                array9[4] = k.x;
                                                if (!g) {
                                                    break Label_2873;
                                                }
                                            }
                                            if (n19 <= k.y) {
                                                array9[0] = k.x + n20;
                                                array9[1] = k.x + n20;
                                                array9[2] = k.x + n20 + n2;
                                                array9[3] = n13 + n2;
                                                array9[4] = n13;
                                                if (!g) {
                                                    break Label_2873;
                                                }
                                            }
                                            array[1] = n13 + n2;
                                            array[2] = k.x;
                                            array[3] = k.x;
                                        }
                                        array7[0] = k.x + super.y;
                                        array7[2] = (array7[1] = n14) + n2;
                                        array7[3] = k.x + super.y + n2;
                                        super.e.setColor(super.s);
                                        super.e.fillPolygon(array7, array8, 4);
                                        super.e.setColor(Color.black);
                                        super.e.drawPolygon(array7, array8, 4);
                                        if (!g) {
                                            break Label_3012;
                                        }
                                    }
                                    array[1] = n14;
                                    array[2] = array[1] + n2;
                                    array[3] = n13 + n2;
                                    array2[2] = (array2[1] = n16) - n2;
                                    array2[3] = n15 - n2;
                                }
                                super.e.setColor(super.s);
                                if (b3 && n18 < k.y) {
                                    super.e.fillPolygon(array9, array10, 5);
                                    super.e.setColor(Color.black);
                                    super.e.drawPolygon(array9, array10, 5);
                                    if (!g) {
                                        break Label_3107;
                                    }
                                }
                                super.e.fillPolygon(array, array2, 4);
                                super.e.setColor(Color.black);
                                super.e.drawPolygon(array, array2, 4);
                            }
                        }
                        --n12;
                    }
                    if (n12 > 0) {
                        continue;
                    }
                    break;
                }
            }
        }
        super.e.setColor(color);
        if (this.H == null) {
            this.H = new k();
        }
        this.H.a(super.d, super.e);
    }
    
    public LineSerie(final double[] array, final String s) {
        super(array, s);
        this.I = 1;
    }
}
