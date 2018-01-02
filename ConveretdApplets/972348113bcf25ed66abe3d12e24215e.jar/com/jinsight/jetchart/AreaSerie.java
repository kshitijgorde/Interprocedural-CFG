// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.awt.Rectangle;
import java.awt.Color;

public class AreaSerie extends GraphSerie
{
    private k H;
    private boolean I;
    
    public void setAreaLinesEnabled(final boolean i) {
        this.I = i;
    }
    
    void b() {
        final boolean g = GraphSerie.G;
        if (super.E > super.A.length - 1) {
            return;
        }
        int n = 0;
        final Color color = super.e.getColor();
        super.e.setColor(super.s);
        final Rectangle j = super.d.bI.j();
        final Rectangle k = super.d.bI.k();
        final boolean bf = super.d.bf;
        final int[] array = new int[super.A.length - super.E + 3];
        final int[] array2 = new int[super.B.length - super.E + 3];
        Label_0178: {
            if (bf) {
                array[0] = k.x + super.y;
                array2[0] = super.B[super.E] - super.y;
                if (!g) {
                    break Label_0178;
                }
            }
            array[0] = super.A[super.E] + super.y;
            array2[0] = j.y - super.y;
        }
        int n2 = super.E;
        while (true) {
            while (true) {
                Label_0216: {
                    if (!g) {
                        break Label_0216;
                    }
                    array[n2 + 1 - super.E] = super.A[n2] + super.y;
                    ++n2;
                }
                if (n2 < super.A.length) {
                    continue;
                }
                break;
            }
            n2 = super.E;
            if (g) {
                continue;
            }
            break;
        }
        int[] array3;
        int[] array4;
        int[] array5;
        int[] array6;
        int n3 = 0;
        int n4;
        int n5;
        int n6;
        boolean b;
        boolean b2;
        boolean b3;
        boolean b4;
        boolean b5 = false;
        boolean b6;
        boolean b7;
        boolean b8;
        boolean b9;
        int n7;
        int[] e;
        int n8;
        int[] array7;
        int[] array8;
        int[] array9;
        int[] array10;
        Label_1371_Outer:Label_1409_Outer:
        while (true) {
            while (true) {
                Label_0269: {
                    if (!g) {
                        break Label_0269;
                    }
                    array2[n2 + 1 - super.E] = super.B[n2] - super.y;
                    ++n2;
                }
                if (n2 < super.B.length) {
                    continue;
                }
                break;
            }
            if (!g) {
                Label_0455: {
                    if (!bf) {
                        array[array.length - 2] = super.A[super.A.length - 1] + super.y;
                        array[array.length - 1] = super.A[super.E] + super.y;
                        array2[array2.length - 2] = j.y - super.y;
                        array2[array2.length - 1] = array2[array2.length - 2];
                        if (!g) {
                            break Label_0455;
                        }
                    }
                    array[array.length - 2] = k.x + super.y;
                    array[array.length - 1] = array[array.length - 2];
                    array2[array2.length - 2] = super.B[super.B.length - 1] - super.y;
                    array2[array2.length - 1] = super.B[super.E] - super.y;
                }
                if (super.d.bc) {
                    array3 = new int[4];
                    array4 = new int[4];
                    array5 = new int[4];
                    array6 = new int[4];
                    n = (bf ? super.d.p : super.d.o);
                    while (true) {
                        Label_2084: {
                            while (true) {
                                Label_2079: {
                                    if (!bf) {
                                        n3 = 0;
                                        n4 = 0;
                                        n5 = 0;
                                        n6 = 0;
                                        while (true) {
                                            Label_1346: {
                                                if (!g) {
                                                    break Label_1346;
                                                }
                                                b = (array2[n6] - n >= j.y && array2[n6 + 1] - n >= j.y);
                                                b2 = (array2[n6] - n > j.y && array2[n6 + 1] - n < j.y);
                                                b3 = (array2[n6] - n <= j.y && array2[n6 + 1] - n <= j.y);
                                                b4 = (array2[n6] - n < j.y && array2[n6 + 1] - n > j.y);
                                                Label_0812: {
                                                    if (b2) {
                                                        n3 = (array[n6 + 1] - array[n6]) * (j.y - super.y - array2[n6 + 1]) / (array2[n6] - array2[n6 + 1]);
                                                        n4 = array[n6 + 1] - n3 - array[n6];
                                                        n5 = array2[n6] - (j.y - super.y);
                                                        Label_0798: {
                                                            if (n4 > n5) {
                                                                array3[0] = array[n6] + n4;
                                                                if (!g) {
                                                                    break Label_0798;
                                                                }
                                                            }
                                                            array3[0] = array[n6];
                                                        }
                                                        if (!g) {
                                                            break Label_0812;
                                                        }
                                                    }
                                                    array3[0] = array[n6];
                                                }
                                                Label_0948: {
                                                    if (array2[n6 + 1] <= j.y - super.y || b) {
                                                        array3[1] = array[n6 + 1];
                                                        array3[2] = array[n6 + 1] + n;
                                                        if (!g) {
                                                            break Label_0948;
                                                        }
                                                    }
                                                    array3[1] = array[n6] + (array[n6 + 1] - array[n6]) * (j.y - super.y - array2[n6]) / ((array2[n6 + 1] - array2[n6] == 0) ? 1 : (array2[n6 + 1] - array2[n6]));
                                                    array3[2] = array3[1] + n;
                                                }
                                                array3[3] = array3[0] + n;
                                                Label_1011: {
                                                    if (b2) {
                                                        Label_0997: {
                                                            if (n4 > n5) {
                                                                array4[0] = j.y - super.y;
                                                                if (!g) {
                                                                    break Label_0997;
                                                                }
                                                            }
                                                            array4[0] = array2[n6];
                                                        }
                                                        if (!g) {
                                                            break Label_1011;
                                                        }
                                                    }
                                                    array4[0] = array2[n6];
                                                }
                                                Label_1068: {
                                                    if (b4) {
                                                        array4[1] = j.y - super.y;
                                                        array4[2] = array4[1] - n;
                                                        if (!g) {
                                                            break Label_1068;
                                                        }
                                                    }
                                                    array4[1] = array2[n6 + 1];
                                                    array4[2] = array2[n6 + 1] - n;
                                                }
                                                array4[3] = array4[0] - n;
                                                super.e.setColor(super.s.darker());
                                                super.e.fillPolygon(array3, array4, 4);
                                                super.e.setColor(Color.black);
                                                super.e.drawPolygon(array3, array4, 4);
                                                if (b4 || b || b2) {
                                                    Label_1197: {
                                                        if (b4) {
                                                            array5[0] = array3[1];
                                                            array5[1] = array3[2];
                                                            if (!g) {
                                                                break Label_1197;
                                                            }
                                                        }
                                                        if (b || b2) {
                                                            array5[0] = array[n6];
                                                            array5[1] = array[n6] + n;
                                                        }
                                                    }
                                                    Label_1256: {
                                                        if (b2 && n4 > n5) {
                                                            array5[2] = array3[0] + n;
                                                            array5[3] = array3[0];
                                                            if (!g) {
                                                                break Label_1256;
                                                            }
                                                        }
                                                        array5[2] = array[n6 + 1] + n;
                                                        array5[3] = array[n6 + 1];
                                                    }
                                                    array6[0] = j.y - super.y;
                                                    array6[2] = (array6[1] = array6[0] - n);
                                                    array6[3] = array6[0];
                                                    super.e.setColor(super.s.darker());
                                                    super.e.fillPolygon(array5, array6, 4);
                                                    super.e.setColor(Color.black);
                                                    super.e.drawPolygon(array5, array6, 4);
                                                }
                                                ++n6;
                                            }
                                            if (n6 >= array.length - 2) {
                                                break Label_2084;
                                            }
                                            continue Label_1371_Outer;
                                        }
                                    }
                                    else {
                                        n3 = array.length - 2;
                                        if (!g) {
                                            break Label_2079;
                                        }
                                    }
                                    Label_1414: {
                                        if (array[n3] >= k.x + super.y && array[n3 + 1] >= k.x + super.y) {
                                            b5 = true;
                                            break Label_1414;
                                        }
                                        b5 = false;
                                    }
                                    b6 = b5;
                                    b7 = (array[n3] <= k.x + super.y && array[n3 + 1] <= k.x + super.y);
                                    b8 = (array[n3] > k.x + super.y && array[n3 + 1] < k.x + super.y);
                                    b9 = (array[n3] < k.x + super.y && array[n3 + 1] > k.x + super.y);
                                    Label_1804: {
                                        if (b6 || b7) {
                                            array3[0] = array[n3];
                                            array3[1] = array[n3 + 1];
                                            array3[2] = array[n3 + 1] + n;
                                            array3[3] = array[n3] + n;
                                            array4[0] = array2[n3];
                                            array4[1] = array2[n3 + 1];
                                            array4[2] = array2[n3 + 1] - n;
                                            array4[3] = array2[n3] - n;
                                            if (!g) {
                                                break Label_1804;
                                            }
                                        }
                                        if (b8 || b9) {
                                            array3[0] = k.x + super.y;
                                            array3[1] = array3[0] + n;
                                            array3[2] = array[n3 + 1] + n;
                                            array3[3] = array[n3 + 1];
                                            array4[0] = array2[n3 + 1] - (array[n3 + 1] - (k.x + super.y)) * (array2[n3 + 1] - array2[n3]) / (array[n3 + 1] - array[n3]);
                                            array4[1] = array4[0] - n;
                                            array4[2] = array2[n3 + 1] - n;
                                            array4[3] = array2[n3 + 1];
                                        }
                                    }
                                    if (b8 || b7 || b9) {
                                        array5[0] = k.x + super.y;
                                        array5[1] = k.x + super.y;
                                        array5[2] = k.x + super.y + n;
                                        array5[3] = k.x + super.y + n;
                                        Label_1960: {
                                            if (b8) {
                                                array6[0] = array4[0];
                                                array6[1] = array2[n3 + 1];
                                                if (!g) {
                                                    break Label_1960;
                                                }
                                            }
                                            if (b9) {
                                                array6[0] = array2[n3];
                                                array6[1] = array4[0];
                                                if (!g) {
                                                    break Label_1960;
                                                }
                                            }
                                            if (b7) {
                                                array6[0] = array2[n3];
                                                array6[1] = array2[n3 + 1];
                                            }
                                        }
                                        array6[2] = array6[1] - n;
                                        array6[3] = array6[0] - n;
                                        super.e.setColor(super.s.darker());
                                        super.e.fillPolygon(array5, array6, 4);
                                        super.e.setColor(Color.black);
                                        super.e.drawPolygon(array5, array6, 4);
                                    }
                                    super.e.setColor(super.s.darker());
                                    super.e.fillPolygon(array3, array4, 4);
                                    super.e.setColor(Color.black);
                                    super.e.drawPolygon(array3, array4, 4);
                                    --n3;
                                }
                                if (n3 >= 0) {
                                    continue Label_1409_Outer;
                                }
                                break;
                            }
                        }
                        if (g) {
                            continue;
                        }
                        break;
                    }
                    super.e.setColor(super.s);
                }
                super.e.fillPolygon(array, array2, array.length);
                super.e.setColor(Color.black);
                super.e.drawPolygon(array, array2, array.length);
                if (this.I) {
                    n7 = 0;
                    while (true) {
                        Label_2229: {
                            if (!g) {
                                break Label_2229;
                            }
                            Label_2226: {
                                if (!bf) {
                                    super.e.drawLine(array[n7], array2[n7], array[n7], j.y - super.y);
                                    if (!g) {
                                        break Label_2226;
                                    }
                                }
                                super.e.drawLine(array[n7], array2[n7], k.x + super.y, array2[n7]);
                            }
                            ++n7;
                        }
                        if (n7 < array.length) {
                            continue;
                        }
                        break;
                    }
                }
                if (super.y != 0) {
                    e = super.d.bI.e();
                    n8 = e[e.length - 1];
                    if (!bf) {
                        super.e.setColor(super.d.bF.d);
                        array7 = new int[] { j.x, j.x + super.y, j.x + super.y + j.width, j.x + j.width };
                        array8 = new int[] { j.y, j.y - super.y, j.y - super.y, j.y };
                        super.e.fillPolygon(array7, array8, 4);
                        super.e.setColor(Color.black);
                        super.e.drawPolygon(array7, array8, 4);
                        super.e.setColor(super.d.bF.d);
                        if (n8 + super.y + 1 < j.x + j.width + super.y - 1) {
                            super.e.drawLine(n8 + super.y + 1, j.y - super.y, j.x + j.width + super.y - 1, j.y - super.y);
                        }
                        super.e.drawLine(j.x + super.y + 1, j.y - super.y, array[0] - 1, j.y - super.y);
                    }
                    else {
                        super.e.setColor(super.d.bE.d);
                        array9 = new int[] { k.x, k.x + super.y, k.x + super.y, k.x };
                        array10 = new int[] { k.y, k.y - super.y, k.y - super.y + k.height, k.y + k.height };
                        super.e.fillPolygon(array9, array10, 4);
                        super.e.setColor(Color.black);
                        super.e.drawPolygon(array9, array10, 4);
                        super.e.setColor(super.d.bE.d);
                        if (array2[0] - 1 > k.y - super.y + 1) {
                            super.e.drawLine(k.x + super.y, k.y - super.y + 1, k.x + super.y, array2[0] - 1);
                        }
                        super.e.drawLine(k.x + super.y, e[0] - super.y + 1, k.x + super.y, k.y + k.height - super.y - 1);
                        if (k.y - super.y < array2[0]) {
                            super.e.setColor(super.d.bE.d);
                            array9[0] = k.x + super.y;
                            array9[1] = k.x + super.y;
                            array9[2] = k.x + super.y + n;
                            array9[3] = k.x + super.y + n;
                            array10[0] = array2[0];
                            array10[1] = k.y - super.y + 1;
                            array10[2] = k.y - super.y + 1 - n;
                            array10[3] = array2[0] - n;
                            super.e.fillPolygon(array9, array10, 4);
                        }
                    }
                }
                super.e.setColor(color);
                if (this.H == null) {
                    this.H = new k();
                }
                this.H.a(super.d, super.e);
                return;
            }
            continue;
        }
    }
    
    void f() {
        final boolean g = GraphSerie.G;
        int n = 0;
        while (true) {
            while (true) {
                Label_0044: {
                    if (!g) {
                        break Label_0044;
                    }
                    this.b(n, super.A[n] - 2 + super.y, super.B[n] - 2 - super.y);
                    ++n;
                }
                if (n < super.A.length) {
                    continue;
                }
                break;
            }
            if (!g) {
                return;
            }
            continue;
        }
    }
    
    public AreaSerie(final double[] array, final String s) {
        super(array, s);
        this.I = true;
    }
}
