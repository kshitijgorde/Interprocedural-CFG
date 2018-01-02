// 
// Decompiled by Procyon v0.5.30
// 

package magnus;

import java.awt.Point;
import java.text.DecimalFormat;
import java.awt.Color;
import java.awt.Graphics;

class GraphKin extends Kinetic
{
    private double v;
    private double s;
    private double u;
    private double r;
    public static double z;
    public static double t;
    public static double w;
    
    public GraphKin(final double v, final double s, final double u, final double r) {
        this.v = v;
        this.s = s;
        this.u = u;
        this.r = r;
    }
    
    void a(final double v) {
        this.v = v;
    }
    
    void do(final double s) {
        this.s = s;
    }
    
    void for(final double u) {
        this.u = u;
    }
    
    void if(final double r) {
        this.r = r;
    }
    
    double do() {
        return this.v;
    }
    
    double for() {
        return this.s;
    }
    
    double int() {
        return this.u;
    }
    
    double if() {
        return this.r;
    }
    
    void a(final Graphics graphics) {
        graphics.setColor(Color.blue);
        this.if(graphics);
    }
    
    void if(final Graphics graphics) {
        double doubleValue = Double.valueOf(Kinetic.g.getText());
        double doubleValue2 = Double.valueOf(Kinetic.e.getText());
        double doubleValue3 = Double.valueOf(Kinetic.do.getText());
        final double n = doubleValue + doubleValue2 + doubleValue3;
        final DecimalFormat decimalFormat = new DecimalFormat("###0.##");
        final int n2 = 500;
        final double n3 = 10.0 / n2;
        final int[] array = new int[n2];
        final int[] array2 = new int[n2];
        final int[] array3 = new int[n2];
        final int[] array4 = new int[n2];
        final int[] array5 = new int[n2];
        final int[] array6 = new int[n2];
        final int[] array7 = new int[n2];
        final int[] array8 = new int[n2];
        final int[] array9 = new int[n2];
        final int[] array10 = new int[n2];
        final int[] array11 = new int[n2];
        final int[] array12 = new int[n2];
        final int[] array13 = new int[n2];
        final double n4 = 10.0;
        final double n5 = GraphKin.z + GraphKin.w;
        final double n6 = GraphKin.w - GraphKin.t;
        final double n7 = GraphKin.t + GraphKin.w;
        if (Kinetic.else.getState()) {
            for (double v = this.v, n8 = 0.0; n8 < n2; ++n8, v += n3) {
                final double n9 = (GraphKin.t * doubleValue2 - GraphKin.z * doubleValue) / n4;
                final double n10 = (GraphKin.z * doubleValue - n7 * doubleValue2) / n4;
                final double n11 = GraphKin.w * doubleValue2 / n4;
                final double n12 = (GraphKin.t * (doubleValue2 + n10 / 2.0) - GraphKin.z * (doubleValue + n9 / 2.0)) / n4;
                final double n13 = (GraphKin.z * (doubleValue + n9 / 2.0) - n7 * (doubleValue2 + n10 / 2.0)) / n4;
                final double n14 = GraphKin.w * (doubleValue2 + n10 / 2.0) / n4;
                final double n15 = (GraphKin.t * (doubleValue2 + n13 / 2.0) - GraphKin.z * (doubleValue + n12 / 2.0)) / n4;
                final double n16 = (GraphKin.z * (doubleValue + n12 / 2.0) - n7 * (doubleValue2 + n13 / 2.0)) / n4;
                final double n17 = GraphKin.w * (doubleValue2 + n13 / 2.0) / n4;
                final double n18 = (GraphKin.t * (doubleValue2 + n16) - GraphKin.z * (doubleValue + n15)) / n4;
                final double n19 = GraphKin.z * (doubleValue + n15) - n7 * (doubleValue2 + n16) / n4;
                final double n20 = GraphKin.w * (doubleValue2 + n16) / n4;
                double n21 = n9 / 6.0 + n12 / 3.0 + n15 / 3.0 + n18 / 6.0;
                double n22 = n11 / 6.0 + n14 / 3.0 + n17 / 3.0 + n20 / 6.0;
                if (doubleValue + n21 < 0.0) {
                    if (n21 > -1.0E-7) {
                        n22 = -n22 * doubleValue / n21;
                    }
                    n21 = -doubleValue;
                }
                if (doubleValue3 + n22 < 0.0) {
                    if (n22 > -1.0E-7) {
                        n21 = -n21 * doubleValue3 / n22;
                    }
                    n22 = -doubleValue3;
                }
                final double n23 = n - doubleValue - doubleValue3;
                if (n23 < n21 + n22) {
                    final double n24 = n23 / (n21 + n22);
                    n21 *= n24;
                    n22 *= n24;
                }
                doubleValue += n21;
                doubleValue3 += n22;
                doubleValue2 = n - doubleValue - doubleValue3;
                final Point a = this.a(v, doubleValue);
                array[(int)n8] = a.x;
                array2[(int)n8] = a.y;
                array3[(int)n8] = this.a(v, doubleValue2).y;
                array4[(int)n8] = this.a(v, doubleValue3).y;
            }
        }
        final double doubleValue4 = Double.valueOf(Kinetic.g.getText());
        final double doubleValue5 = Double.valueOf(Kinetic.e.getText());
        final double doubleValue6 = Double.valueOf(Kinetic.do.getText());
        if (Kinetic.new.getState()) {
            for (double v2 = this.v, n25 = 0.0; n25 < n2; ++n25, v2 += n3) {
                final double n26 = doubleValue4 * Math.exp(GraphKin.z * (1.0 / (1.0 + GraphKin.w / GraphKin.t) - 1.0) * (n25 / n4));
                final Point a2 = this.a(v2, n26);
                array[(int)n25] = a2.x;
                array5[(int)n25] = a2.y;
                array6[(int)n25] = this.a(v2, doubleValue5).y;
                array7[(int)n25] = this.a(v2, doubleValue4 + doubleValue6 - n26).y;
            }
        }
        if (Kinetic.byte.getState()) {
            for (double v3 = this.v, n27 = 0.0; n27 < n2; ++n27, v3 += n3) {
                final double n28 = (doubleValue4 + doubleValue5 + doubleValue6) * (1.0 - Math.exp(-GraphKin.z * GraphKin.w / (GraphKin.z + GraphKin.t) * (n27 / n4)));
                array10[(int)n27] = this.a(v3, n28).y;
                final double n29 = (doubleValue4 + doubleValue5 + doubleValue6 - n28) * GraphKin.z / (GraphKin.z + GraphKin.t);
                array9[(int)n27] = this.a(v3, n29).y;
                final Point a3 = this.a(v3, n29 * GraphKin.t / GraphKin.z);
                array8[(int)n27] = a3.y;
                array[(int)n27] = a3.x;
            }
        }
        if (Kinetic.h.getState()) {
            for (double v4 = this.v, n30 = 0.0; n30 < n2; ++n30, v4 += n3) {
                final double n31 = n30 / n4;
                final double n32 = doubleValue4 * Math.exp(-GraphKin.z * n31);
                array11[(int)n30] = this.a(v4, n32).y;
                final double n33 = GraphKin.z / (GraphKin.w - GraphKin.z) * n32 - (doubleValue4 * GraphKin.z / (GraphKin.w - GraphKin.z) - doubleValue5) * Math.exp(-GraphKin.w * n31);
                array12[(int)n30] = this.a(v4, n33).y;
                final Point a4 = this.a(v4, doubleValue4 + doubleValue5 + doubleValue6 - n32 - n33);
                array13[(int)n30] = a4.y;
                array[(int)n30] = a4.x;
            }
        }
        graphics.setColor(new Color(255, 255, 255));
        graphics.drawString("time", 410, 330);
        graphics.drawString("c ", 2, 10);
        for (int i = 0; i < 10; ++i) {
            final int x = this.a(this.v + i, 0.0).x;
            graphics.setColor(new Color(100, 100, 100));
            graphics.drawLine(this.a(this.v + i, 0.0).x, 0, this.a(this.v + i, 0.0).x, 300);
            graphics.setColor(Color.white);
            graphics.drawString("" + i, x, 318);
        }
        for (int j = 0; j < 10; ++j) {
            final int y = this.a(0.0, this.u + 0.2 * j).y;
            graphics.setColor(new Color(100, 100, 100));
            graphics.drawLine(this.a(0.0, this.u + 0.2 * j).x, y, this.a(2.0 * this.s, this.u + 0.2 * j).x, this.a(2.0 * this.s, this.u + 0.2 * j).y);
            graphics.setColor(Color.white);
            graphics.drawString("" + decimalFormat.format(0.0 + j * 0.2), 3, y);
        }
        if (Kinetic.else.getState()) {
            for (int n34 = 0; n34 + 1 < n2; ++n34) {
                if (array2[n34] < 300) {
                    graphics.setColor(Color.red);
                    graphics.drawLine(array[n34], array2[n34], array[n34 + 1], array2[n34 + 1]);
                }
                if (array3[n34] < 300) {
                    graphics.setColor(Color.green);
                    graphics.drawLine(array[n34], array3[n34], array[n34 + 1], array3[n34 + 1]);
                }
                if (array4[n34] < 300) {
                    graphics.setColor(Color.blue);
                    graphics.drawLine(array[n34], array4[n34], array[n34 + 1], array4[n34 + 1]);
                }
            }
        }
        if (Kinetic.new.getState()) {
            for (int n35 = 0; n35 + 1 < n2; ++n35) {
                if (array5[n35] < 300) {
                    graphics.setColor(new Color(255, 255, 127));
                    graphics.drawLine(array[n35], array5[n35], array[n35 + 1], array5[n35 + 1]);
                }
                if (array6[n35] < 300) {
                    graphics.setColor(new Color(191, 191, 191));
                    graphics.drawLine(array[n35], array6[n35], array[n35 + 1], array6[n35 + 1]);
                }
                if (array7[n35] < 300) {
                    graphics.setColor(new Color(255, 191, 127));
                    graphics.drawLine(array[n35], array7[n35], array[n35 + 1], array7[n35 + 1]);
                }
            }
        }
        if (Kinetic.byte.getState()) {
            for (int n36 = 0; n36 + 1 < n2; ++n36) {
                if (array8[n36] < 300) {
                    graphics.setColor(new Color(127, 255, 127));
                    graphics.drawLine(array[n36], array8[n36], array[n36 + 1], array8[n36 + 1]);
                }
                if (array9[n36] < 300) {
                    graphics.setColor(new Color(127, 255, 255));
                    graphics.drawLine(array[n36], array9[n36], array[n36 + 1], array9[n36 + 1]);
                }
                if (array10[n36] < 300) {
                    graphics.setColor(new Color(127, 127, 255));
                    graphics.drawLine(array[n36], array10[n36], array[n36 + 1], array10[n36 + 1]);
                }
            }
        }
        if (Kinetic.h.getState()) {
            for (int n37 = 0; n37 + 1 < n2; ++n37) {
                if (array11[n37] < 300) {
                    graphics.setColor(new Color(255, 191, 191));
                    graphics.drawLine(array[n37], array11[n37], array[n37 + 1], array11[n37 + 1]);
                }
                if (array12[n37] < 300) {
                    graphics.setColor(new Color(255, 127, 255));
                    graphics.drawLine(array[n37], array12[n37], array[n37 + 1], array12[n37 + 1]);
                }
                if (array13[n37] < 300) {
                    graphics.setColor(new Color(191, 127, 255));
                    graphics.drawLine(array[n37], array13[n37], array[n37 + 1], array13[n37 + 1]);
                }
            }
        }
    }
    
    Point a(final double n, final double n2) {
        return new Point((int)((n - this.v) * 300.0 / (this.s - this.v) + 30.0), (int)((this.r - n2) * 300.0 / (this.r - this.u)));
    }
}
