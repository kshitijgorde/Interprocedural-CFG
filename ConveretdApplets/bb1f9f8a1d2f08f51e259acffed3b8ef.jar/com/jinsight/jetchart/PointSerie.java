// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.awt.Rectangle;
import java.awt.Color;

public class PointSerie extends GraphSerie
{
    public static final int SQUARE = 0;
    public static final int CIRCLE = 1;
    public static final int TRIANGLE = 2;
    private int H;
    private k I;
    
    public void setShape(final int h) {
        this.H = h;
    }
    
    void b() {
        final boolean g = GraphSerie.G;
        final Rectangle j = super.d.bI.j();
        final Rectangle k = super.d.bI.k();
        if (super.d.bc) {
            final int n = super.d.bf ? super.d.p : super.d.o;
            final int[] array = new int[4];
            final int[] array2 = new int[4];
            final int[] array3 = new int[4];
            final int[] array4 = new int[4];
            int n2 = 0;
            while (true) {
                Label_1799: {
                    if (!g) {
                        break Label_1799;
                    }
                    Label_1796: {
                        if (n2 >= super.E || g) {
                            if (this.H == 0) {
                                array2[0] = super.B[n2] - super.y - 3;
                                array2[3] = super.B[n2] - 3 - super.y;
                                array[0] = super.A[n2] - 3 + super.y;
                                array[3] = super.A[n2] + super.y + 4;
                                array4[0] = super.B[n2] - super.y - 3;
                                array4[3] = super.B[n2] - super.y + 4;
                                array3[0] = super.A[n2] + 4 + super.y;
                                array3[3] = super.A[n2] + super.y + 4;
                                final int n3 = array[0] + array2[0] - j.y;
                                final int n4 = n3 + 7;
                                Label_1065: {
                                    if (!super.d.bf) {
                                        array2[1] = super.B[n2] - 3 - super.y - n;
                                        Label_0395: {
                                            if (super.a[n2] < 0.0 && array2[1] <= j.y && n4 < j.x + j.width) {
                                                array2[1] = j.y;
                                                array2[2] = j.y;
                                                if (!g) {
                                                    break Label_0395;
                                                }
                                            }
                                            array2[2] = super.B[n2] - 3 - super.y - n;
                                        }
                                        array[1] = super.A[n2] - 3 + super.y + n;
                                        Label_0478: {
                                            if (super.a[n2] < 0.0 && array2[1] == j.y) {
                                                array[1] = n3;
                                                array[2] = n4;
                                                if (!g) {
                                                    break Label_0478;
                                                }
                                            }
                                            array[2] = super.A[n2] - 3 + n + super.y + 7;
                                        }
                                        array4[1] = super.B[n2] - 3 - super.y - n;
                                        Label_0579: {
                                            if (super.a[n2] < 0.0 && array4[1] <= j.y && n4 < j.x + j.width) {
                                                array4[1] = j.y;
                                                array4[2] = j.y;
                                                if (!g) {
                                                    break Label_0579;
                                                }
                                            }
                                            array4[2] = super.B[n2] - 3 - super.y - n + 7;
                                        }
                                        array3[1] = super.A[n2] - 3 + n + super.y + 7;
                                        Label_0672: {
                                            if (super.a[n2] < 0.0 && array4[1] == j.y) {
                                                array3[1] = array[2];
                                                array3[2] = array3[1] + 7;
                                                if (!g) {
                                                    break Label_0672;
                                                }
                                            }
                                            array3[2] = super.A[n2] - 3 + n + super.y + 7;
                                        }
                                        if (!g) {
                                            break Label_1065;
                                        }
                                    }
                                    final int n5 = array2[0] - (k.x - array[0]);
                                    final int n6 = n5 + 7;
                                    array[2] = super.A[n2] - 3 + n + super.y + 7;
                                    Label_0796: {
                                        if (super.a[n2] < 0.0 && array[2] >= k.x && n5 > k.y) {
                                            array[1] = k.x;
                                            array[2] = k.x;
                                            if (!g) {
                                                break Label_0796;
                                            }
                                        }
                                        array[1] = super.A[n2] - 3 + super.y + n;
                                    }
                                    array2[1] = super.B[n2] - 3 - super.y - n;
                                    Label_0876: {
                                        if (super.a[n2] < 0.0 && array[2] == k.x) {
                                            array2[1] = n5;
                                            array2[2] = n6;
                                            if (!g) {
                                                break Label_0876;
                                            }
                                        }
                                        array2[2] = super.B[n2] - 3 - super.y - n;
                                    }
                                    array3[1] = super.A[n2] - 3 + n + super.y + 7;
                                    Label_0975: {
                                        if (super.a[n2] < 0.0 && array3[1] >= k.x && n5 > k.y) {
                                            array3[1] = k.x;
                                            array3[2] = k.x;
                                            if (!g) {
                                                break Label_0975;
                                            }
                                        }
                                        array3[2] = super.A[n2] - 3 + n + super.y + 7;
                                    }
                                    array4[1] = super.B[n2] - 3 - super.y - n;
                                    if (super.a[n2] < 0.0 && array3[1] == k.x) {
                                        array4[1] = array2[2];
                                        array4[2] = array4[1] + 7;
                                        if (!g) {
                                            break Label_1065;
                                        }
                                    }
                                    array4[2] = super.B[n2] - 3 - super.y - n + 7;
                                }
                                super.e.setColor(super.s.darker());
                                super.e.fillPolygon(array, array2, 4);
                                super.e.setColor(Color.black);
                                super.e.drawPolygon(array, array2, 4);
                                super.e.setColor(super.s.darker());
                                super.e.fillPolygon(array3, array4, 4);
                                super.e.setColor(Color.black);
                                super.e.drawPolygon(array3, array4, 4);
                                if (!g) {
                                    break Label_1796;
                                }
                            }
                            if (this.H == 1) {
                                super.e.setColor(super.s.darker());
                                int n7 = n;
                                while (true) {
                                    Label_1244: {
                                        if (!g) {
                                            break Label_1244;
                                        }
                                        super.e.fillOval(super.A[n2] - 5 + n7 + super.y, super.B[n2] - 5 - n7 - super.y, 11, 11);
                                        --n7;
                                    }
                                    if (n7 >= 0) {
                                        continue;
                                    }
                                    break;
                                }
                            }
                            else if (this.H == 2) {
                                super.e.setColor(super.s.darker());
                                final int[] array5 = new int[4];
                                final int[] array6 = new int[4];
                                array5[0] = super.A[n2] + super.y;
                                array5[3] = super.A[n2] + super.y + 5;
                                array6[0] = super.B[n2] - 5 - super.y;
                                array6[3] = super.B[n2] + 5 - super.y;
                                Label_1762: {
                                    if (!super.d.bf) {
                                        final int n8 = array5[0] + (array6[0] - j.y);
                                        final int n9 = n8 + 15;
                                        array6[1] = super.B[n2] - 5 - super.y - n;
                                        Label_1485: {
                                            if (super.a[n2] < 0.0 && array6[1] <= j.y && n9 < j.x + j.width) {
                                                array6[1] = j.y;
                                                array6[2] = j.y;
                                                if (!g) {
                                                    break Label_1485;
                                                }
                                            }
                                            array6[2] = super.B[n2] + 5 - super.y - n;
                                        }
                                        Label_1563: {
                                            if (super.a[n2] < 0.0 && array6[1] == j.y) {
                                                array5[1] = n8;
                                                array5[2] = n9;
                                                if (!g) {
                                                    break Label_1563;
                                                }
                                            }
                                            array5[1] = super.A[n2] + super.y + n;
                                            array5[2] = super.A[n2] + super.y + 5 + n;
                                        }
                                        if (!g) {
                                            break Label_1762;
                                        }
                                    }
                                    final int n10 = array6[0] - (k.x - array5[0]);
                                    final int n11 = n10 + 15;
                                    array5[1] = super.A[n2] + super.y + n;
                                    Label_1682: {
                                        if (super.a[n2] < 0.0 && array5[1] >= k.x && n10 > k.y) {
                                            array5[1] = k.x;
                                            array5[2] = k.x;
                                            if (!g) {
                                                break Label_1682;
                                            }
                                        }
                                        array5[2] = super.A[n2] + 5 + super.y + n;
                                    }
                                    if (super.a[n2] < 0.0 && array5[1] == k.x) {
                                        array6[1] = n10;
                                        array6[2] = n11;
                                        if (!g) {
                                            break Label_1762;
                                        }
                                    }
                                    array6[1] = super.B[n2] - 5 - super.y - n;
                                    array6[2] = super.B[n2] + 5 - super.y - n;
                                }
                                super.e.fillPolygon(array5, array6, 4);
                                super.e.setColor(Color.black);
                                super.e.drawPolygon(array5, array6, 4);
                            }
                        }
                    }
                    ++n2;
                }
                if (n2 < super.A.length) {
                    continue;
                }
                break;
            }
        }
        int n12 = 0;
        while (true) {
            while (true) {
                Label_2193: {
                    if (!g) {
                        break Label_2193;
                    }
                    Label_2190: {
                        if (n12 < super.E && !g) {
                            break Label_2190;
                        }
                        super.e.setColor(super.s);
                        final PointSerie pointSerie = this;
                        if (pointSerie.H == 0) {
                            super.e.fillRect(super.A[n12] - 3 + super.y, super.B[n12] - 3 - super.y, 7, 7);
                            super.e.setColor(Color.black);
                            super.e.drawRect(super.A[n12] - 3 + super.y, super.B[n12] - 3 - super.y, 7, 7);
                            if (!g) {
                                break Label_2190;
                            }
                        }
                        if (this.H == 1) {
                            super.e.fillOval(super.A[n12] - 5 + super.y, super.B[n12] - 5 - super.y, 11, 11);
                            super.e.setColor(Color.black);
                            super.e.drawOval(super.A[n12] - 4 + super.y, super.B[n12] - 4 - super.y, 9, 9);
                            if (!g) {
                                break Label_2190;
                            }
                        }
                        if (this.H == 2) {
                            final int[] array7 = { super.A[n12] - 5 + super.y, super.A[n12] + super.y, super.A[n12] + super.y + 5 };
                            final int[] array8 = { super.B[n12] + 5 - super.y, super.B[n12] - 5 - super.y, super.B[n12] + 5 - super.y };
                            super.e.setColor(super.s);
                            super.e.fillPolygon(array7, array8, 3);
                            super.e.setColor(Color.black);
                            super.e.drawPolygon(array7, array8, 3);
                        }
                    }
                    ++n12;
                }
                if (n12 < super.A.length) {
                    continue;
                }
                break;
            }
            final PointSerie pointSerie = this;
            if (!g) {
                if (this.I == null) {
                    this.I = new k();
                }
                this.I.a(super.d, super.e);
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
    
    public PointSerie(final double[] array, final String s) {
        super(array, s);
        this.H = 0;
    }
}
