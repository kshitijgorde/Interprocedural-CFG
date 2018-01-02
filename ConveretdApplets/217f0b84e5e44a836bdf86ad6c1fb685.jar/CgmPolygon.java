import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class CgmPolygon extends CgmPrimitivePoly
{
    int p;
    static final double HATCHWIDTH = 0.002;
    static final int LINE_HATCH_SIZE = 8;
    static final int LINE_POINT_SIZE = 4;
    static final int LINE_GAP_SIZE = 4;
    
    CgmPolygon(final int points, final boolean closed) {
        final int n = points + 1;
        super.xpoints = new double[points];
        super.ypoints = new double[points];
        super.xpoints1 = new int[n];
        super.ypoints1 = new int[n];
        super.points = points;
        super.closed = closed;
        this.p = (closed ? n : points);
    }
    
    final void draw(final Graphics graphics, final double previousW, final double previousH, final boolean b) {
        if (super.previousW != previousW || super.previousH != previousH) {
            for (int i = 0; i < super.points; ++i) {
                super.xpoints1[i] = (int)(super.xpoints[i] * previousW + 0.6);
                super.ypoints1[i] = (int)(super.ypoints[i] * previousH + 0.6);
            }
            super.lw = (int)(super.LineWidth * previousW + 0.5);
            super.xpoints1[super.points] = super.xpoints1[0];
            super.ypoints1[super.points] = super.ypoints1[0];
            super.previousW = previousW;
            super.previousH = previousH;
        }
        if (b && super.IntStyle >= 1) {
            graphics.setColor(super.FillColor);
            if (super.IntStyle == 1) {
                graphics.fillPolygon(super.xpoints1, super.ypoints1, this.p);
            }
            else {
                final double n = (super.IntStyle < 4 || super.IntStyle == 6) ? 0.002 : 0.001;
                final Shape clip = graphics.getClip();
                if (super.IntStyle == 2 || super.IntStyle == 4 || super.IntStyle >= 6) {
                    int n2 = (int)(n * previousH);
                    if (n2 == 0) {
                        n2 = 1;
                    }
                    final int n3 = n2 * 5;
                    final int n4 = (int)(super.y * previousH) / n3 * n3;
                    final int n5 = (int)((super.y + super.Height) * previousH);
                    final int n6 = (int)previousW;
                    for (int j = n4; j <= n5; j += n3) {
                        graphics.clipRect(0, j, n6, n2);
                        graphics.fillPolygon(super.xpoints1, super.ypoints1, this.p);
                        graphics.setClip(clip);
                    }
                }
                if (super.IntStyle == 3 || super.IntStyle >= 5) {
                    int n7 = (int)(n * previousW);
                    if (n7 == 0) {
                        n7 = 1;
                    }
                    final int n8 = n7 * 5;
                    final int n9 = (int)(super.x * previousW) / n8 * n8;
                    final int n10 = (int)((super.x + super.Width) * previousH);
                    final int n11 = (int)previousH;
                    for (int k = n9; k <= n10; k += n8) {
                        graphics.clipRect(k, 0, n7, n11);
                        graphics.fillPolygon(super.xpoints1, super.ypoints1, this.p);
                        graphics.setClip(clip);
                    }
                }
            }
        }
        if (super.LineColor != null) {
            graphics.setColor(super.LineColor);
            if (super.LineType > 1 && this.p < previousW) {
                drawHatchedLine(graphics, super.xpoints1, super.ypoints1, this.p, super.lw, super.LineType);
                return;
            }
            if (super.lw <= 1) {
                graphics.drawPolyline(super.xpoints1, super.ypoints1, this.p);
                return;
            }
            if (this.p == 2) {
                drawLine(graphics, super.xpoints1[0], super.ypoints1[0], super.xpoints1[1], super.ypoints1[1], super.lw);
                return;
            }
            drawPolyline(graphics, super.xpoints1, super.ypoints1, this.p, super.lw);
        }
    }
    
    static final void drawHatchedLine(final Graphics graphics, final int[] array, final int[] array2, final int n, final int n2, final int n3) {
        int n4 = array[1];
        int n5 = array2[1];
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        double n10 = 0.0;
        double n11 = 0.0;
        switch (n3) {
            case 3: {
                n10 = 4.0;
                n11 = 4.0;
                break;
            }
            case 4: {
                n10 = 8.0;
                n11 = 4.0;
                break;
            }
            default: {
                n10 = 8.0;
                n11 = 8.0;
                break;
            }
        }
        final double n12 = n10 + n11 + 8.0;
        final double n13 = n10 / n12;
        final double n14 = n11 / n12;
        final double n15 = (n10 + 4.0) / n12;
        final Rectangle clipBounds = graphics.getClipBounds();
        final int x = clipBounds.x;
        final int y = clipBounds.y;
        final int n16 = clipBounds.width + x;
        final int n17 = clipBounds.height + y;
        for (int i = 1; i < n; ++i) {
            final int n18 = array[i];
            final int n19 = array2[i];
            if (n18 >= x && n4 <= n16 && n19 >= y && n5 <= n17) {
                final double n20 = n18 - n4;
                final double n21 = n19 - n5;
                int n22 = (int)((n20 > n21) ? (n20 / n12) : (n21 / n12));
                if (n22 == 0) {
                    n22 = 1;
                }
                final double n23 = n20 / n22;
                final double n24 = n21 / n22;
                final int n25 = (int)(n23 * n15);
                final int n26 = (int)(n24 * n15);
                final int n27 = (int)(n23 * n13);
                final int n28 = (int)(n24 * n13);
                final int n29 = (int)(n23 * n14);
                final int n30 = (int)(n24 * n14);
                if (n27 == 0 && n28 == 0) {
                    if (n2 > 1) {
                        drawLine(graphics, n4, n5, n18, n19, n2);
                    }
                    else {
                        graphics.drawLine(n4, n5, n18, n19);
                    }
                }
                else {
                    if (n2 > 1) {
                        if (n20 == 0.0) {
                            n6 = n2;
                            n7 = 0;
                        }
                        else if (n21 == 0.0) {
                            n7 = n2;
                            n6 = 0;
                        }
                        else {
                            final double atan;
                            n6 = -(int)(n2 * Math.sin(atan = Math.atan(n21 / n20)) + 0.5);
                            n7 = (int)(n2 * Math.cos(atan) + 0.5);
                        }
                        n8 = n6 >> 1;
                        n9 = n7 >> 1;
                    }
                    for (int j = 0; j < n22; ++j) {
                        final int n31 = n4 + (int)(j * n23);
                        final int n32 = n5 + (int)(j * n24);
                        final int n33 = n31 + n27;
                        final int n34 = n32 + n28;
                        final int n35 = n31 + n25;
                        final int n36 = n32 + n26;
                        final int n37 = n35 + n29;
                        final int n38 = n36 + n30;
                        if (n2 <= 1) {
                            graphics.drawLine(n31, n32, n33, n34);
                            graphics.drawLine(n35, n36, n37, n38);
                        }
                        else {
                            graphics.fillPolygon(new int[] { n31 - n8, n33 - n8, n33 + n6 - n8, n31 + n6 - n8 }, new int[] { n32 - n9, n34 - n9, n34 + n7 - n9, n32 + n7 - n9 }, 4);
                            graphics.fillPolygon(new int[] { n35 - n8, n37 - n8, n37 + n6 - n8, n35 + n6 - n8 }, new int[] { n36 - n9, n38 - n9, n38 + n7 - n9, n36 + n7 - n9 }, 4);
                        }
                    }
                }
            }
            n4 = n18;
            n5 = n19;
        }
    }
    
    static final void drawLine(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n == n3) {
            if (n2 != n4) {
                graphics.fillRect(n - (n5 >> 1), (n2 < n4) ? n2 : n4, n5, (n4 < n2) ? (n2 - n4) : (n4 - n2));
            }
            return;
        }
        if (n2 == n4) {
            graphics.fillRect((n < n3) ? n : n3, n2 - (n5 >> 1), (n3 < n) ? (n - n3) : (n3 - n), n5);
            return;
        }
        final double atan = Math.atan((n4 - n2) / (n3 - n));
        final int n6 = -(int)(n5 * Math.sin(atan) + 0.5);
        final int n7 = (int)(n5 * Math.cos(atan) + 0.5);
        final int n8 = n6 >> 1;
        final int n9 = n7 >> 1;
        graphics.fillPolygon(new int[] { n - n8, n3 - n8, n3 + n6 - n8, n + n6 - n8 }, new int[] { n2 - n9, n4 - n9, n4 + n7 - n9, n2 + n7 - n9 }, 4);
    }
    
    static final void drawPolyline(final Graphics graphics, final int[] array, final int[] array2, final int n, final int n2) {
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        double n7 = 0.0;
        double n8 = 0.0;
        double n9 = 0.0;
        double n10 = 0.0;
        final double n11 = n2 * 0.5;
        final int n12 = n - 1;
        int n13 = 0;
        int n14 = n12;
        int n15 = 0;
        int n16 = -2;
        if (array[0] == array[n12] && array2[0] == array2[n12]) {
            n13 = -1;
            n14 = n;
        }
        for (int i = n13; i < n14; ++i) {
            final int n17 = (i < 0) ? (n - 2) : ((i == n12) ? 0 : i);
            final int n18 = n17 + 1;
            final double n19 = array2[n18] - array2[n17];
            final double n20 = array[n17] - array[n18];
            if (n16 < n13) {
                n7 = n19;
                n8 = -n20;
                n16 = i;
                int n21;
                int n22;
                if (n8 == 0.0) {
                    n21 = ((n7 < 0.0) ? (-n2) : n2);
                    n22 = 0;
                }
                else {
                    final double atan;
                    n21 = (int)(n2 * Math.sin(atan = Math.atan(n7 / n8)) + 0.5);
                    n22 = (int)(n2 * Math.cos(atan) + 0.5);
                    if (n8 < 0.0) {
                        n21 = -n21;
                    }
                    if (n8 > 0.0) {
                        n22 = -n22;
                    }
                }
                final int n23 = n21 >> 1;
                final int n24 = n22 >> 1;
                n3 = array[n17] - n23;
                n5 = array2[n17] - n24;
                n4 = array[n17] + n21 - n23;
                n6 = array2[n17] + n22 - n24;
            }
            final double n25 = array[n18] * array2[n17] - array2[n18] * array[n17];
            final double n26 = Math.sqrt(n19 * n19 + n20 * n20) * n11;
            final double n27 = n25 + n26;
            final double n28 = n25 - n26;
            if (n7 != 0.0 || n8 != 0.0) {
                final double n29 = n7 * n20 - n19 * n8;
                if (n29 != 0.0) {
                    n15 = i;
                    final int n30 = (int)((n27 * n8 - n9 * n20) / n29 + 0.5);
                    final int n31 = (int)((n9 * n19 - n27 * n7) / n29 + 0.5);
                    final int n32 = (int)((n28 * n8 - n10 * n20) / n29 + 0.5);
                    final int n33 = (int)((n10 * n19 - n28 * n7) / n29 + 0.5);
                    if (i > 0 && i < n) {
                        graphics.fillPolygon(new int[] { n3, n30, n32, n4 }, new int[] { n5, n31, n33, n6 }, 4);
                    }
                    if (i > n16) {
                        n3 = n30;
                        n5 = n31;
                        n4 = n32;
                        n6 = n33;
                    }
                }
            }
            n7 = n19;
            n8 = n20;
            n9 = n27;
            n10 = n28;
        }
        final double n34 = array2[n15] - array2[n12];
        final double n35 = array[n15] - array[n12];
        if (n34 != 0.0 || n35 != 0.0) {
            int n36;
            int n37;
            if (n35 == 0.0) {
                n36 = ((n34 >= 0.0) ? (-n2) : n2);
                n37 = 0;
            }
            else {
                final double atan2;
                n36 = (int)(n2 * Math.sin(atan2 = Math.atan(n34 / n35)) + 0.5);
                n37 = (int)(n2 * Math.cos(atan2) + 0.5);
                if (n34 < 0.0 && n35 > 0.0) {
                    n36 = -n36;
                }
                if (n35 < 0.0) {
                    n37 = -n37;
                }
            }
            final int n38 = n36 >> 1;
            final int n39 = n37 >> 1;
            graphics.fillPolygon(new int[] { n3, array[n12] - n38, array[n12] + n36 - n38, n4 }, new int[] { n5, array2[n12] - n39, array2[n12] + n37 - n39, n6 }, 4);
        }
    }
    
    final boolean find(final double n, final double n2) {
        if (super.visibility != 0 && super.IntStyle >= 1 && super.closed && n >= super.x && n2 >= super.y && n <= super.x + super.Width && n2 <= super.y + super.Height) {
            double n3 = super.xpoints[0];
            double n4 = super.ypoints[0];
            int n5 = 0;
            for (int i = 1; i <= super.points; ++i) {
                double n6;
                double n7;
                if (i < super.points) {
                    n6 = super.xpoints[i];
                    n7 = super.ypoints[i];
                }
                else {
                    n6 = super.xpoints[0];
                    n7 = super.ypoints[0];
                }
                final double n8 = n7 - n4;
                if (n8 != 0.0) {
                    final double n9 = (n2 - n4) / n8;
                    if (n9 >= 0.0 && n9 <= 1.0 && (n6 - n3) * n9 >= n - n3) {
                        ++n5;
                    }
                }
                n3 = n6;
                n4 = n7;
            }
            return (n5 & 0x1) != 0x0;
        }
        return false;
    }
    
    final void setClosed() {
        if (!super.closed) {
            ++this.p;
        }
        super.closed = true;
    }
}
