import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class CgmPicture extends Cgm
{
    CgmPrimList PrimList;
    CgmContext cgm;
    Font DefaultFont;
    CgmText PreviousText;
    static final int E_SEGM = 45;
    static final int B_SEGM1 = 8;
    static final int B_SEGM2 = 4;
    
    CgmPicture(final CgmContext cgm, final String name) {
        this.PrimList = new CgmPrimList(1000, 500);
        this.DefaultFont = new Font("Courier", 0, 12);
        this.PreviousText = null;
        this.cgm = cgm;
        super.name = name;
        super.applet = cgm.applet;
        super.Width = cgm.Width;
        super.Height = cgm.Height;
        super.ax = cgm.ax;
        super.bx = cgm.bx;
        super.ay = cgm.ay;
        super.by = cgm.by;
        super.EdgeWidthMode = cgm.EdgeWidthMode;
        super.LineWidthMode = cgm.LineWidthMode;
        super.MarkerSizeMode = cgm.MarkerSizeMode;
        super.EdgesVisible = cgm.EdgesVisible;
        super.EdgeType = cgm.EdgeType;
        super.LineType = cgm.LineType;
        super.MarkerType = cgm.MarkerType;
        super.EdgeWidth = cgm.EdgeWidth;
        super.LineWidth = cgm.LineWidth;
        super.MarkerSize = cgm.MarkerSize;
        super.InteriorStyle = cgm.InteriorStyle;
        super.HatchIndex = cgm.HatchIndex;
        super.FillColor = cgm.FillColor;
        super.EdgeColor = cgm.EdgeColor;
        super.LineColor = cgm.LineColor;
        super.MarkerColor = cgm.MarkerColor;
        super.BackColor = cgm.BackColor;
        super.TextColor = cgm.TextColor;
        super.CharacterHeight = cgm.CharacterHeight;
        super.CharacterExpansion = cgm.CharacterExpansion;
        super.CharacterSpacing = cgm.CharacterSpacing;
        super.FontIndex = cgm.FontIndex;
        super.CharOri = cgm.CharOri;
        super.CharSlant = cgm.CharSlant;
        super.TextAlignVert = cgm.TextAlignVert;
        super.TextAlignHor = cgm.TextAlignHor;
    }
    
    private final void anyPoly(final CgmPrimitivePoly cgmPrimitivePoly, final double[] array) {
        final int n = array.length / 2;
        double x = 1.0;
        double y = 1.0;
        double n2 = 0.0;
        double n3 = 0.0;
        int n4 = 0;
        for (int i = 0; i < n; ++i) {
            final double n5 = cgmPrimitivePoly.xpoints[i] = super.ax * array[n4++] + super.bx;
            final double n6 = cgmPrimitivePoly.ypoints[i] = super.ay * array[n4++] + super.by;
            if (x > n5) {
                x = n5;
            }
            if (y > n6) {
                y = n6;
            }
            if (n2 < n5) {
                n2 = n5;
            }
            if (n3 < n6) {
                n3 = n6;
            }
        }
        cgmPrimitivePoly.x = x;
        cgmPrimitivePoly.y = y;
        cgmPrimitivePoly.Width = n2 - x;
        cgmPrimitivePoly.Height = n3 - y;
        this.PrimList.addElement(cgmPrimitivePoly);
    }
    
    final void bezier(final int n, final double[] array) {
        final int length = array.length;
        if (length > 220) {
            this.polygon(array, false);
            return;
        }
        final int n2 = length - 1;
        final int n3 = length - 2;
        final int n4 = length - 3;
        final int n5 = length / 2;
        final int n6 = ((int)(Math.log(length) * 8.0) / n5 + 4) * n5;
        final int n7 = n5 - 1;
        int n8 = 0;
        double n9 = 1.0;
        for (int i = 0; i <= n7; ++i) {
            if (i > 0) {
                n9 = n9 * (n5 - i) / i;
            }
            final int n10 = n8++;
            array[n10] *= n9;
            final int n11 = n8++;
            array[n11] *= n9;
        }
        final double[] array2 = new double[n6 * 2 + 2];
        final double[] array3 = new double[length];
        for (int j = 0; j <= n6; ++j) {
            final double n12 = j / n6;
            double n13 = 1.0;
            for (int k = 0; k < length; array3[k] = array[k++] * n13, array3[k] = array[k++] * n13, n13 *= n12) {}
            final int n14;
            array2[n14 = j + j] = array3[n3];
            final int n15;
            array2[n15 = n14 + 1] = array3[n2];
            double n17;
            final double n16 = n17 = 1.0 - n12;
            double[] array4;
            int n18;
            double[] array5;
            int n19;
            for (int l = n4; l >= 1; array4[n18] += array3[l--] * n17, array5 = array2, n19 = n14, array5[n19] += array3[l--] * n17, n17 *= n16) {
                array4 = array2;
                n18 = n15;
            }
        }
        this.polygon(array2, false);
    }
    
    final void cellArray(final double n, final double n2, final double n3, final double n4, final int[] array, final int n5, final int n6) {
        this.PrimList.addElement(new CgmImage(super.applet.createImage(new MemoryImageSource(n5, n6, array, 0, n5)), super.applet, super.ax * n + super.bx, super.ay * n2 + super.by, super.ax * n3 + super.bx, super.ay * n4 + super.by));
    }
    
    final void circarc(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final int n8) {
        this.elliparc(n, n2, n, n2 - n7, n + n7, n2, n3, n4, n5, n6, n8);
    }
    
    final void circarc(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final int n7) {
        final double n8 = n * n;
        final double n9 = n2 * n2;
        final double n10 = n2 - n4;
        final double n11 = n - n3;
        final double n12 = n3 * n3;
        final double n13 = n4 * n4;
        final double n14 = n4 - n6;
        final double n15 = n3 - n5;
        final double n16 = n5 * n5;
        final double n17 = n6 * n6;
        final double n18 = ((n2 - n6) * (n * n - n12 + n9 - n13) + n10 * (n16 - n12 + n17 - n13)) / (2.0 * (n14 * n11 - n15 * n10));
        final double n19 = (n14 == 0.0) ? ((n8 - n12 + n9 - n13 - 2.0 * n18 * n11) / (2.0 * n10)) : ((n12 - n16 + n13 - n17 - 2.0 * n18 * n15) / (2.0 * n14));
        this.circarc(n18, n19, n - n18, n2 - n19, n5 - n18, n6 - n19, Math.sqrt((n - n18) * (n - n18) + (n2 - n19) * (n2 - n19)), n7);
    }
    
    final void disjtLine(final double[] array) {
        final int n = array.length / 2;
        if (n > 1) {
            final CgmDisjtLine lineAttributes = new CgmDisjtLine(n);
            this.anyPoly(lineAttributes, array);
            this.setLineAttributes(lineAttributes);
        }
    }
    
    final void elliparc(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final double n8, final double n9, final double n10, final int n11) {
        final double n12 = 3.141592653589793;
        final double n13 = n12 + n12;
        final double n14 = n3 - n;
        final double n15 = n4 - n2;
        final double n16 = n5 - n;
        final double n17 = n6 - n2;
        double atan = n12 / 2.0;
        final double n18 = (Math.abs(n16) > Math.abs(n14)) ? (Math.atan(n17 / n16) + atan) : Math.atan(n15 / n14);
        double atan2;
        if (n7 != 0.0) {
            atan2 = Math.atan(n8 / n7);
            if (n7 < 0.0) {
                atan2 += n12;
            }
        }
        else {
            atan2 = ((n8 < 0.0) ? (-atan) : atan);
        }
        if (n9 != 0.0) {
            atan = Math.atan(n10 / n9);
            if (n9 < 0.0) {
                atan += n12;
            }
        }
        else if (n10 < 0.0) {
            atan = -atan;
        }
        if (n3 < n5 && n4 < n6 && n18 < 0.0) {
            final double n19 = atan2;
            atan2 = atan;
            atan = n19;
        }
        double n20 = atan - atan2;
        if (n20 < 0.0) {
            n20 += n13;
        }
        final double n21 = n14 * n14 + n15 * n15;
        final double n22 = n16 * n16 + n17 * n17;
        final double n23 = n21 * n22;
        final double n24 = n21 - n22;
        final double n25 = atan2 - n18;
        final double[] array = new double[90 + ((n11 >= 0) ? 4 : 2)];
        int i;
        for (i = 0; i <= 45; ++i) {
            final double n26 = n20 * i / 45.0 + n25;
            final double cos = Math.cos(n26);
            final double sqrt = Math.sqrt(n23 / (n21 - n24 * cos * cos));
            array[2 * i] = n + sqrt * Math.cos(n26 + n18);
            array[2 * i + 1] = n2 + sqrt * Math.sin(n26 + n18);
        }
        if (n11 >= 0) {
            array[2 * i] = array[0];
            array[2 * i + 1] = array[1];
        }
        this.polygon(array, n11 >= 0);
    }
    
    final void ellipse(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        if (super.EdgeType <= 1 && super.InteriorStyle <= 1 && ((n == n3 && n2 == n6) || (n == n5 && n2 == n4))) {
            final CgmOval fillAndLine = new CgmOval(super.ax * n + super.bx, super.ay * n2 + super.by, (n == n3) ? (super.ax * (n5 - n)) : (super.ax * (n3 - n)), (n2 == n6) ? (super.ay * (n4 - n2)) : (super.ay * (n6 - n2)));
            this.setFillAndLine(fillAndLine);
            this.PrimList.addElement(fillAndLine);
            return;
        }
        final double n7 = 6.283185307179586;
        final double n8 = n7 / 45.0;
        final double n9 = n3 - n;
        final double n10 = n4 - n2;
        final double n11 = n5 - n;
        final double n12 = n6 - n2;
        final double n13 = (Math.abs(n11) > Math.abs(n9)) ? (Math.atan(n12 / n11) + n7 / 4.0) : Math.atan(n10 / n9);
        final double n14 = n9 * n9 + n10 * n10;
        final double n15 = n11 * n11 + n12 * n12;
        final double[] array = new double[90];
        for (int i = 0; i < 45; ++i) {
            final double n16 = n14 * n15;
            final double n17 = n14;
            final double n18 = n14 - n15;
            final double n19;
            final double cos = Math.cos(n19 = n8 * i);
            final double sqrt = Math.sqrt(n16 / (n17 - n18 * cos * cos));
            array[2 * i] = n + sqrt * Math.cos(n19 + n13);
            array[2 * i + 1] = n2 + sqrt * Math.sin(n19 + n13);
        }
        this.polygon(array, true);
    }
    
    final int find(final double n, final double n2) {
        return this.PrimList.find(n, n2);
    }
    
    final int find(final String s, final int n) {
        return this.PrimList.find(s, n);
    }
    
    final void image(final Image image) {
        this.PrimList.addElement(new CgmImage(image, super.applet));
    }
    
    final void marker(final double[] array) {
        final int n = array.length / 2;
        if (n > 0) {
            final CgmMarker cgmMarker = new CgmMarker(n);
            this.anyPoly(cgmMarker, array);
            cgmMarker.LineColor = super.MarkerColor;
            cgmMarker.LineWidth = super.MarkerSize * super.ax;
            cgmMarker.LineType = super.MarkerType;
        }
    }
    
    final void polygon(final double[] array, final boolean b) {
        final int n = array.length / 2;
        if (n > 1) {
            final CgmPolygon cgmPolygon = new CgmPolygon(n, b);
            this.anyPoly(cgmPolygon, array);
            if (b) {
                this.setFillAndLine(cgmPolygon);
                return;
            }
            this.setLineAttributes(cgmPolygon);
        }
    }
    
    final void rectangle(final double n, final double n2, final double n3, final double n4) {
        if (super.EdgeType <= 1 && super.InteriorStyle <= 1) {
            final CgmRectangle fillAndLine = new CgmRectangle(super.ax * n + super.bx, super.ay * n2 + super.by, super.ax * n3 + super.bx, super.ay * n4 + super.by);
            this.setFillAndLine(fillAndLine);
            this.PrimList.addElement(fillAndLine);
            return;
        }
        this.polygon(new double[] { n, n2, n3, n2, n3, n4, n, n4 }, true);
    }
    
    final void render(final Graphics graphics, final double n, final double n2, final boolean b) {
        final Rectangle clipBounds = graphics.getClipBounds();
        final double n3 = clipBounds.x / n;
        final double n4 = clipBounds.y / n2;
        this.PrimList.render(graphics, n, n2, n3, clipBounds.width / n + n3, n4, clipBounds.height / n2 + n4, b);
    }
    
    final int replaceText(final int n, final String s) {
        return this.PrimList.replaceText(n, s);
    }
    
    final void setFillAndLine(final CgmPrimitive cgmPrimitive) {
        switch (super.InteriorStyle) {
            case 1:
            case 2: {
                cgmPrimitive.IntStyle = 1;
                break;
            }
            case 3: {
                cgmPrimitive.IntStyle = super.HatchIndex + 1;
                break;
            }
            default: {
                cgmPrimitive.IntStyle = 0;
                break;
            }
        }
        cgmPrimitive.FillColor = super.FillColor;
        if (super.EdgesVisible) {
            cgmPrimitive.LineColor = super.EdgeColor;
            cgmPrimitive.LineWidth = super.EdgeWidth * super.ax;
            cgmPrimitive.LineType = super.EdgeType;
        }
    }
    
    final void setLineAttributes(final CgmPrimitive cgmPrimitive) {
        cgmPrimitive.LineColor = super.LineColor;
        cgmPrimitive.LineWidth = super.LineWidth * super.ax;
        cgmPrimitive.LineType = super.LineType;
    }
    
    final void text(final boolean b, final double n, final double n2, final double n3, final double n4, final boolean b2, final String s) {
        final String trim = s.trim();
        if (trim.length() == 0) {
            return;
        }
        final CgmText cgmText = new CgmText(Math.abs(super.ax * n), Math.abs(super.ay * n2), super.ax * n3 + super.bx, super.ay * n4 + super.by, super.CharacterHeight, super.CharOri, super.CharSlant, super.CharacterSpacing, trim, super.TextAlignHor, super.TextAlignVert, super.TextPath);
        cgmText.applet = super.applet;
        cgmText.FillColor = super.TextColor;
        cgmText.setFont((super.FontIndex > this.cgm.FontList.size()) ? this.DefaultFont : this.cgm.FontList.elementAt(super.FontIndex - 1));
        cgmText.CharacterExpansion = super.CharacterExpansion;
        cgmText.Previous = (b ? this.PreviousText : null);
        this.PreviousText = (b2 ? null : cgmText);
        this.PrimList.addElement(cgmText);
    }
}
